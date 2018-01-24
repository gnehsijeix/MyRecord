package com.xjs.arouterbuilder.feature;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.javapoet.TypeName;
import com.xjs.arouterbuilder.feature.transformers.ArrayTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.BoxPrimitiveTypeTransformerImp;
import com.xjs.arouterbuilder.feature.transformers.ParcelableTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.PrimitiveTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.SerializableTypeTransformerImpl;
import com.xjs.arouterbuilder.feature.transformers.StringTypeTransformerImpl;
import com.xjs.arouterbuilder.utils.Logger;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/14
 *         desc:
 */

public class TypeTransformerManager {

    private Logger logger;
    private Types typeUtils;
    private Set<TypeTransformer> typeTransformerSet;

    public static TypeTransformerManager create(Logger logger, Types types) {
        return create(logger, types, null);
    }

    public static TypeTransformerManager create(Logger logger, Types types, Set<TypeTransformer> typeTransformerList) {
        return new TypeTransformerManager(logger, types, typeTransformerList);
    }


    private TypeTransformerManager(@NonNull Logger logger, @NonNull Types typeUtils, @Nullable Set<TypeTransformer> typeTransformerList) {
        if (typeTransformerList != null) {
            this.typeTransformerSet = typeTransformerList;
        } else {
            this.typeTransformerSet = new HashSet<>();
        }
        this.logger = logger;
        this.typeUtils = typeUtils;
        init();
    }


    public CharSequence transform(Element target) {
        logger.info(target.getSimpleName() + "->transformType:" + TypeName.get(target.asType()).toString());
        for (TypeTransformer typeTransformer : typeTransformerSet) {
            if (typeTransformer.accept(target)) {
                return typeTransformer.transform(target);
            }
        }
        return "Object";
    }

    private void init() {
        typeTransformerSet.add(new PrimitiveTypeTransformerImpl());
        typeTransformerSet.add(new BoxPrimitiveTypeTransformerImp());
        typeTransformerSet.add(new StringTypeTransformerImpl());
        typeTransformerSet.add(new SerializableTypeTransformerImpl(typeUtils));
        typeTransformerSet.add(new ParcelableTypeTransformerImpl(typeUtils));
        typeTransformerSet.add(new ArrayTypeTransformerImpl(typeUtils));
    }
}
