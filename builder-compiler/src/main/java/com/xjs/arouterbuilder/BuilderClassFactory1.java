package com.xjs.arouterbuilder;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.xjs.arouterbuilder.feature.TypeTransformer;
import com.xjs.arouterbuilder.utils.Logger;
import com.xjs.arouterbuilder.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static com.xjs.arouterbuilder.Constants.AROUTER_QUALIFIED_CLASS_NAME;
import static com.xjs.arouterbuilder.Constants.AROUTER_POSTCARD_FIELD_NAME;
import static com.xjs.arouterbuilder.Constants.AROUTER_POSTCARD_QUALIFIED_CLASS_NAME;
import static com.xjs.arouterbuilder.Constants.GENERATE_CLASS_NAME_PREFIX;

/**
 * @author xjs
 *         on  2018/1/13
 *         desc:
 */
@Deprecated
public class BuilderClassFactory1 {

    private TypeElement classElement;
    private List<Element> fieldElements;


    private Logger logger;
    private TypeTransformer transformerManager;


    private BuilderClassFactory1(TypeElement classElement, List<Element> fieldElements,
                                 Logger logger, TypeTransformer transformerManager) {
        this.classElement = classElement;
        this.fieldElements = fieldElements;
        this.transformerManager = transformerManager;
        this.logger = logger;
    }

    /**
     * 生成类文件
     *
     * @param classElement 类元素
     * @param elementList  字段元素
     */
    private JavaFile generateClass(TypeElement classElement, List<Element> elementList) {

        List<FieldSpec> fieldSpecList = new ArrayList<>();
        List<MethodSpec> methodSpecList = new ArrayList<>();

        logger("%1$s >>> ", classElement.getSimpleName());

        for (Element fieldElement : elementList) {
            MethodSpec methodSpec = createMethodSpec(classElement, fieldElement);
            methodSpecList.add(methodSpec);
            logger("parse method:[%2$s]", classElement.getQualifiedName(), fieldElement.getSimpleName().toString());
        }

        // postcard 字段
        FieldSpec postcardField = FieldSpec.builder(ClassName.bestGuess(AROUTER_POSTCARD_QUALIFIED_CLASS_NAME),
                AROUTER_POSTCARD_FIELD_NAME, Modifier.PRIVATE)
                .initializer("$N", "null")
                .build();
        fieldSpecList.add(postcardField);

        //构造函数
        String routePath = classElement.getAnnotation(Route.class).path();
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this.$N = $N.getInstance().build($S)"
                        , AROUTER_POSTCARD_FIELD_NAME
                        , AROUTER_QUALIFIED_CLASS_NAME
                        , routePath)
                .build();
        methodSpecList.add(constructor);

        //build函数
        MethodSpec buildMethod = MethodSpec.methodBuilder("build")
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.bestGuess(AROUTER_POSTCARD_QUALIFIED_CLASS_NAME))
                .addStatement("return this.$N", AROUTER_POSTCARD_FIELD_NAME)
                .build();
        methodSpecList.add(buildMethod);
        //类名
        TypeSpec classTypeSpec = TypeSpec
                .classBuilder(getClassName(classElement))
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecList)
                .addMethods(methodSpecList)
                .build();

        logger("Builder_ %1$s end <<< ", classElement.getSimpleName());

        return JavaFile.builder(getClassElementPackageName(classElement), classTypeSpec)
                .build();
    }

    private FieldSpec createFieldSpec(Element element) {

        VariableElement variableElement = (VariableElement) element;

        logger(String.format("field:%s TypeName:%s", element.getSimpleName().toString(), TypeName.get(element.asType()).toString()));


        FieldSpec.Builder builder = FieldSpec.builder(TypeName.get(element.asType())
                , variableElement.getSimpleName().toString()
                , Modifier.PRIVATE);
        if (variableElement.getConstantValue() != null) {
            builder.initializer("$N", variableElement.getConstantValue());
        }

        return builder.build();
    }

    private MethodSpec createMethodSpec(TypeElement typeElement, Element element) {

        String parameterName = element.getSimpleName().toString().toLowerCase();

        ParameterSpec parameterSpec = ParameterSpec.builder(TypeName.get(element.asType())
                , parameterName)
                .build();

        return MethodSpec.methodBuilder(getMethodName(element))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameterSpec)
                .returns(ClassName.bestGuess(getClassName(typeElement)))
                .addStatement("this.$N.with$N($S,$N)"
                        , AROUTER_POSTCARD_FIELD_NAME
                        , transformerManager.transform(element)
                        , getFieldKey(element)
                        , parameterName)
                .addStatement("return this")
                .build();
    }

    private String getMethodName(Element element) {

        Autowired autowired = element.getAnnotation(Autowired.class);
        String methodSetPrefix = autowired.required() ? "mustSet" : "set";
        String fieldName = autowired.name();
        if (fieldName.isEmpty()) {
            fieldName = element.getSimpleName().toString();
        }
        fieldName = replaceStartWith_m_privateField(fieldName);
        fieldName = replaceUnderLine(fieldName);
        fieldName = StringUtil.firstCharacterToUpper(fieldName);
        return methodSetPrefix + fieldName;
    }

    private String getFieldKey(Element element) {
        Autowired autowired = element.getAnnotation(Autowired.class);
        return autowired.name();
    }

    private String replaceStartWith_m_privateField(String fieldName) {
        //匹配 类似于 mUserName这样的
        String regex = "\\^m\\[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldName);
        if (matcher.find()) {
            return fieldName.substring(1);
        }
        return fieldName;
    }

    private String replaceUnderLine(String fieldName) {
        return StringUtil.replaceUnderlineAndFirstToUpper(fieldName, "_", "");
    }


    private static String getClassName(TypeElement element) {
        return GENERATE_CLASS_NAME_PREFIX + element.getSimpleName();
    }

    private static String getClassElementPackageName(TypeElement classElement) {
        return ((PackageElement) classElement.getEnclosingElement()).getQualifiedName().toString();
    }

    private JavaFile generate() {
        return generateClass(classElement, fieldElements);
    }

    private void logger(String format, Object... objects) {
        logger(String.format(format, objects));
    }

    private void logger(CharSequence character) {
        logger.info(character);

    }

    public static JavaFile create(TypeElement classElement, List<Element> fieldElements, Logger logger, TypeTransformer transformerManager) {
        return new BuilderClassFactory1(classElement, fieldElements, logger, transformerManager).generate();
    }

}
