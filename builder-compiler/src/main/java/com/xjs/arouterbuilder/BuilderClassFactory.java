package com.xjs.arouterbuilder;

import com.squareup.javapoet.JavaFile;
import com.xjs.arouterbuilder.feature.TypeTransformerManager;
import com.xjs.arouterbuilder.utils.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/13
 *         desc:
 */

public class BuilderClassFactory {

    public static void create(Map<TypeElement, List<Element>> elementListMap, Logger logger, Filer filer, Types types) throws IOException {
        TypeTransformerManager typeTransformerManager = new TypeTransformerManager(logger,types);
        typeTransformerManager.init();
        for (Map.Entry<TypeElement, List<Element>> entry : elementListMap.entrySet()) {
            JavaFile javaFile = BuilderGenerator.create(entry.getKey(), entry.getValue(),logger,typeTransformerManager,types);
            javaFile.writeTo(filer);
        }

    }

}
