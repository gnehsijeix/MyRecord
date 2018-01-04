package com.xjs.code;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleTypeVisitor7;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;


/**
 * iterable 可迭代的
 * convenient  方便
 * concrete 混合
 * examines 检查
 * compute 计算
 * noteworthy 值得注意
 * condition 考虑
 * facility 设施
 */

@AutoService(Processor.class)
public class BuilderCoderProcessor extends AbstractProcessor {


    // 元素操作辅助类
    private Elements elementsUtils;
    private Types typeUtil;
    private Filer filer;
    private Messager messager;
    private static final String GENERATE_CLASS_NAME_PREFIX = "RBuilder";
    private static final String FIELD_ROUTE_PATH = "routePath";
    private static final String MESSAGE_TAG = "BuilderCoder";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementsUtils = processingEnv.getElementUtils();
        typeUtil = processingEnv.getTypeUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedTypes = new HashSet<>(4);
        supportedTypes.add(Autowired.class.getCanonicalName());
        return supportedTypes;
    }

    /**
     * 在这方法中处理
     *
     * @param annotations ;
     * @param roundEnv    ;
     * @return .
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        messager.printMessage(Diagnostic.Kind.NOTE, "BuilderCoder process start");

        Map<TypeElement, List<Element>> elementsMap = new HashMap<>();
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Autowired.class);
        messager.printMessage(Diagnostic.Kind.NOTE, "BuilderCoder find element size:" + elements.size());
        for (Element element : elements) {
            findAndSetFiled(element, elementsMap);
        }
        for (Map.Entry<TypeElement, List<Element>> entry : elementsMap.entrySet()) {
            JavaFile javaFile = generateClass(entry.getKey(), entry.getValue());
            try {
                javaFile.writeTo(filer);
                messager.printMessage(Diagnostic.Kind.NOTE, "BuilderCoder process finish");
            } catch (IOException e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.ERROR, String.format("%s generate build code error %s", MESSAGE_TAG, e.getMessage()));
            }
        }
        return false;
    }

    private void findAndSetFiled(Element element, Map<TypeElement, List<Element>> elementListMap) {

        //获取父级元素
        TypeElement classElement = (TypeElement) element.getEnclosingElement();
        if (!elementListMap.containsKey(classElement)) {
            elementListMap.put(classElement, new ArrayList<Element>());
        }
        //将这个元素加入到列表中
        elementListMap.get(classElement).add(element);
    }


    /**
     * public class ABuilderClass{
     * <p>
     * <p>
     * }
     * <p>
     * <p>
     * 生成类文件
     *
     * @param classElement 类元素
     * @param elementList  字段元素
     */
    private JavaFile generateClass(TypeElement classElement, List<Element> elementList) {
        messager.printMessage(Diagnostic.Kind.NOTE, "BuilderCoder generateClass:" + classElement.getQualifiedName() + "start");
        List<FieldSpec> fieldSpecList = new ArrayList<>();
        List<MethodSpec> methodSpecList = new ArrayList<>();

        for (Element fieldElement : elementList) {
            messager.printMessage(Diagnostic.Kind.NOTE, "BuilderCoder generateClass:" + classElement.getQualifiedName() + "field:" + fieldElement.getSimpleName().toString());
            FieldSpec fieldSpec = createFieldSpec(fieldElement);
            printMessage("generate field%s finish", fieldElement);
            fieldSpecList.add(fieldSpec);

            MethodSpec methodSpec = createMethodSpec(classElement, fieldElement);
            methodSpecList.add(methodSpec);
            printMessage("generate field method %s finish", fieldElement);

        }

        //路由路径字段
        String routePath = classElement.getAnnotation(Route.class).path();
        FieldSpec routePathFieldSpec = FieldSpec.builder(String.class
                , classElement.getSimpleName() + FIELD_ROUTE_PATH
                , Modifier.PRIVATE)
                .initializer("$S", routePath)
                .build();
        fieldSpecList.add(routePathFieldSpec);

        //构造函数
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build();
        methodSpecList.add(constructor);

        //build函数
        MethodSpec buildMethod = MethodSpec.methodBuilder("build")
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("com.alibaba.android.arouter.facade", "Postcard"))
                .addStatement("return $N", "null")
                .build();
        methodSpecList.add(buildMethod);

        //类名
        TypeSpec classTypeSpec = TypeSpec
                .classBuilder(getClassName(classElement))
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecList)
                .addMethods(methodSpecList)
                .build();

        return JavaFile.builder(((PackageElement) classElement.getEnclosingElement()).getQualifiedName().toString(), classTypeSpec)
                .build();
    }

    private FieldSpec createFieldSpec(Element element) {
        VariableElement variableElement = (VariableElement) element;
        printMessage(String.format("field:%s TypeName:%s", element.getSimpleName().toString(), TypeName.get(element.asType()).toString()));
        return FieldSpec.builder(getTypeName(element)
                , variableElement.getSimpleName().toString()
                , Modifier.PRIVATE)
                //.initializer("$T", variableElement.getConstantValue())
                .build();
    }




    private MethodSpec createMethodSpec(TypeElement typeElement, Element element) {

        boolean request = element.getAnnotation(Autowired.class).required();
        ParameterSpec parameterSpec = ParameterSpec.builder(getTypeName(element),
                element.getSimpleName().toString().toLowerCase()).build();

        String fieldName = element.getSimpleName().toString();
        String methodSetPrefix = request ? "mustSet" : "set";

        return MethodSpec.methodBuilder(methodSetPrefix + fieldName.substring(0, 1) + fieldName.substring(0, fieldName.length() - 1))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameterSpec)
                .returns(ClassName.bestGuess(getClassName(typeElement)))
                .addStatement("this.$N = $N", element.getSimpleName().toString(), parameterSpec.name)
                .addStatement("return this")
                .build();
    }

    private void printMessage(String format, Object... a) {
        printMessage(String.format(format, a));
    }

    private void printMessage(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format("%s %s", MESSAGE_TAG, message));
    }

    private static TypeName getTypeName(Element element) {
        return TypeName.get(element.asType());
    }

    private static String getClassName(TypeElement element) {
        return GENERATE_CLASS_NAME_PREFIX + element.getSimpleName();
    }


}
