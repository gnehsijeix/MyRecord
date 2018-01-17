package com.xjs.routebuilder.feature;

import com.squareup.javapoet.TypeName;
import com.xjs.routebuilder.feature.transformers.BoxPrimitiveTypeTransformer;
import com.xjs.routebuilder.feature.transformers.ParcelableTypeTransformer;
import com.xjs.routebuilder.feature.transformers.PrimitiveTypeTransformer;
import com.xjs.routebuilder.feature.transformers.SerializableTypeTransformer;
import com.xjs.routebuilder.feature.transformers.StringTypeTransformer;
import com.xjs.routebuilder.utils.Logger;

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
        addTypeTransformer(new PrimitiveTypeTransformer());
        addTypeTransformer(new BoxPrimitiveTypeTransformer());
        addTypeTransformer(new StringTypeTransformer());
        addTypeTransformer(new SerializableTypeTransformer(typeUtils));
        addTypeTransformer(new ParcelableTypeTransformer(typeUtils));
    }
}