package com.xjs.routebuilder.feature.transformers;

import com.squareup.javapoet.ClassName;
import com.xjs.routebuilder.utils.TypeUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;


/**
 * @author xjs
 *         on  2018/1/15
 *         desc:
 */

public class ParcelableTypeTransformer extends BaseTypeTransformer {


    public ParcelableTypeTransformer(Types typesUtils) {
        super(typesUtils);
    }

    @Override
    public boolean isForType(Element element) {
        return element instanceof TypeElement &&
                TypeUtils.recursionIsImplements(
                        (TypeElement) element
                        , ClassName.get("android.os", "Parcelable")
                        , typesUtils);
    }

    @Override
    public CharSequence transform(Element element) {
        return null;
    }
}
