package com.xjs.routebuilder;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.xjs.routebuilder.feature.TypeTransformerManager;
import com.xjs.routebuilder.utils.Logger;
import com.xjs.routebuilder.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static com.xjs.routebuilder.Constants.ALBABA_POSTCARD_FIELD_NAME;
import static com.xjs.routebuilder.Constants.GENERATE_CLASS_NAME_PREFIX;

/**
 * @author xjs
 *         on  2018/1/13
 *         desc:
 */

public class BuilderGenerator {

    private TypeElement classElement;
    private List<Element> fieldElements;

    private Logger logger;
    private TypeTransformerManager typeTransformerManager;

    private static final String STATIC_BUILDER_METHOD_NAME = "builder";

    public BuilderGenerator(TypeElement classElement, List<Element> fieldElements,
                             Logger logger, TypeTransformerManager transformerManager) {
        this.classElement = classElement;
        this.fieldElements = fieldElements;
        this.typeTransformerManager = transformerManager;
        this.logger = logger;
    }

    /**
     * 生成类文件
     *
     * @param classElement 类元素
     * @param elementList  字段元素
     */
    private JavaFile generateClass(TypeElement classElement, List<Element> elementList) {

        logger("generate class > %1$s", classElement.getSimpleName());
        List<MethodSpec> methodSpecList = new ArrayList<>();
        List<Element> musetElements = new ArrayList<>();
        List<Element> setElements = new ArrayList<>();

        for (Element element : elementList) {
            Autowired autowired = element.getAnnotation(Autowired.class);
            if (autowired.required()) {
                musetElements.add(element);
            } else {
                setElements.add(element);
            }
        }


        //类名
        TypeSpec classTypeSpec = TypeSpec
                .classBuilder(getClassName(classElement))
                .addModifiers(Modifier.PUBLIC)
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

    public static List<int[]> recursionGetCombination(int position) {
        List<int[]> listList = new ArrayList<>();
        int[] singleSelfArray = new int[]{position};
        listList.add(singleSelfArray);
        if (position == 0) {
            return listList;
        }
        int[] fullIterator = new int[position];

        for (int i = position - 1; i >= 0; i--) {
            int[] iterator = new int[2];
            iterator[0] = i;
            iterator[1] = position;
            listList.add(iterator);
        }
        listList.add(fullIterator);
        listList.addAll(recursionGetCombination(position - 1));
        return listList;
    }

    /**
     * new RouteBuilderWrapper(ARouter.getInstance().build("///")
     * .withInt("_progress", progress)
     * .withString("_password", password))
     *
     * @param typeElement class Element
     * @param elements    parameter element
     * @return ;
     */
    private MethodSpec createStaticBuilderMethod(TypeElement typeElement, List<Element> elements) {
        List<ParameterSpec> parameterSpecList = new ArrayList<>();

        CodeBlock.Builder arouterParamCodeBlockBuilder = CodeBlock.builder().add("$N.getInstance()", Constants.ALBABA_AROUTE_QUALIFIED_CLASS_NAME);
        for (Element element : elements) {
            String parameterName = replaceStartWith_m_privateField(element.getSimpleName().toString());
            ParameterSpec parameterSpec = ParameterSpec.builder(TypeName.get(element.asType())
                    , parameterName)
                    .build();
            parameterSpecList.add(parameterSpec);
            arouterParamCodeBlockBuilder.add(".with$N($S,$N)"
                    , typeTransformerManager.transform(element)
                    , getFieldKey(element)
                    , parameterName);
        }

        CodeBlock codeBlock = CodeBlock.of("new $N($N)"
                , Constants.ROUTE_BUILDER_WRAPPER_CLASS_NAME
                , arouterParamCodeBlockBuilder.toString());

        return MethodSpec.methodBuilder(STATIC_BUILDER_METHOD_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameters(parameterSpecList)
                .addStatement(codeBlock)
                .returns(ClassName.bestGuess(Constants.ROUTE_BUILDER_WRAPPER_CLASS_NAME))
                .build();
    }


    private MethodSpec createMethodSpec(TypeElement typeElement, Element element) {

        String parameterName = replaceStartWith_m_privateField(element.getSimpleName().toString());

        ParameterSpec parameterSpec = ParameterSpec.builder(TypeName.get(element.asType())
                , parameterName)
                .build();

        return MethodSpec.methodBuilder(getMethodName(element))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameterSpec)
                .returns(ClassName.bestGuess(getClassName(typeElement)))
                .addStatement("this.$N.with$N($S,$N)"
                        , ALBABA_POSTCARD_FIELD_NAME
                        , typeTransformerManager.transform(element)
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
        return StringUtil.firstCharacterToUpper(fieldName);
    }

    private String replaceUnderLine(String fieldName) {
        return StringUtil.replaceUnderlineAndfirstToUpper(fieldName, "_", "");
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

    public static JavaFile create(TypeElement classElement, List<Element> fieldElements, Logger logger, TypeTransformerManager transformerManager) {
        return new BuilderGenerator(classElement, fieldElements, logger, transformerManager).generate();
    }

}

