package com.xjs.code;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.google.auto.service.AutoService;
import com.xjs.code.utils.Logger;


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
public class BuilderCoderProcessor extends AbstractProcessor {

    private Filer filer;
    private Logger logger;
    private Types typeUtils;

    private static final String MESSAGE_TAG = "BuilderCoder";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
        processingEnv.getMessager();
        processingEnv.getLocale();
        filer = processingEnv.getFiler();
        logger = new Logger(processingEnv.getMessager());
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

        logger.info("process start >>>>");
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Autowired.class);
        Map<TypeElement, List<Element>> elementListMap = findAndSetFiled(elements);
        try {
            BuilderClassFactory.create(elementListMap, logger, filer, typeUtils);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("process error" + e.getMessage());
        }
        logger.info("process end <<<<");
        return false;
    }

    private Map<TypeElement, List<Element>> findAndSetFiled(Set<? extends Element> elements) {
        Map<TypeElement, List<Element>> elementListMap = new HashMap<>();
        for (Element element : elements) {
            //获取父级元素
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            if (!elementListMap.containsKey(classElement)) {
                elementListMap.put(classElement, new ArrayList<>());
            }
            elementListMap.get(classElement).add(element);
        }
        return elementListMap;
    }
}
