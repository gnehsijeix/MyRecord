package com.xjs.arouterbuilder;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.auto.service.AutoService;
import com.xjs.arouterbuilder.feature.RouteAnnotationFilter;
import com.xjs.arouterbuilder.utils.Logger;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


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
public class BuilderProcessor extends AbstractProcessor {

    private Filer filer;
    private Logger logger;
    private Types typeUtils;
    private Elements elementUtils;
    private RouteAnnotationFilter routeAnnotationFilter;

    private static final String MESSAGE_TAG = "BuilderCoder";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        processingEnv.getMessager();
        processingEnv.getLocale();
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        logger = new Logger(processingEnv.getMessager());
        routeAnnotationFilter = RouteAnnotationFilter.create(typeUtils);
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedTypes = new HashSet<>(4);
        supportedTypes.add(Route.class.getCanonicalName());
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

        logger.info("process start >>>>");
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Route.class);

        Map<TypeElement, List<Element>> elementListMap = findAndSetFiled(elements);
        try {
            BuilderClassGenerator.create(elementListMap, logger, filer, typeUtils, elementUtils);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("process error" + e.getMessage());
        }
        logger.info("process end <<<<");
        return false;
    }

    private Map<TypeElement, List<Element>> findAndSetFiled(Set<? extends Element> classElements) {
        Map<TypeElement, List<Element>> elementListMap = new HashMap<>();

        for (Element element : classElements) {
            TypeElement classElement = (TypeElement) element;
            logger.info(String.format("filter->%1$s", classElement.getQualifiedName()));
            boolean isSkip = routeAnnotationFilter.filter(classElement);
            logger.info(String.format("filter<-%1$s", String.valueOf(isSkip)));
            if (isSkip) {
                continue;
            }
            List<? extends Element> elements = elementUtils.getAllMembers(classElement);
            elementListMap.put(classElement, new ArrayList<>());
            for (Element memberElement : elements) {
                if (memberElement.getKind() == ElementKind.FIELD) {
                    if (memberElement.getAnnotation(Autowired.class) != null) {
                        elementListMap.get(classElement).add(memberElement);
                    }
                }

            }
        }
        return elementListMap;
    }
}
