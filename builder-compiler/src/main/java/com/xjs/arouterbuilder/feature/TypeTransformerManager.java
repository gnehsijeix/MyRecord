package com.xjs.arouterbuilder.feature;

import com.squareup.javapoet.TypeName;
import com.xjs.arouterbuilder.feature.transformers.BoxPrimitiveTypeTransformerImp;
import com.xjs.arouterbuilder.feature.transformers.ParcelableTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.PrimitiveTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.SerializableTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.StringTypeTransformerImpl;
import com.xjs.arouterbuilder.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/14
 *         desc:
 */

public class TypeTransformerManager {

    private Logger logger;
    private List<TypeTransformer> typeTransformerSet;
    private Types typeUtils;

    public TypeTransformerManager(Logger logger, Types typeUtils) {
        this.typeTransformerSet = new ArrayList<>();
        this.logger = logger;
        this.typeUtils = typeUtils;
    }

    public TypeTransformerManager addTypeTransformer(TypeTransformer typeTransformer) {
        typeTransformerSet.add(typeTransformer);
        return this;
    }


    public CharSequence transform(Element target) {
        logger.info(target.getSimpleName() + "transformType:" + TypeName.get(target.asType()).toString());
        for (int i = 0; i < typeTransformerSet.size(); i++) {
            TypeTransformer typeTransformer = typeTransformerSet.get(i);
            if (typeTransformer.isForType(target)) {
                return typeTransformer.transform(target);
            }
        }
        return "Object";
    }

    public void init() {
        addTypeTransformer(new PrimitiveTypeTransformerImpl());
        addTypeTransformer(new BoxPrimitiveTypeTransformerImp());
        addTypeTransformer(new StringTypeTransformerImpl());
        addTypeTransformer(new SerializableTypeTransformerImpl(typeUtils));
        addTypeTransformer(new ParcelableTypeTransformerImpl(typeUtils));
    }
}
