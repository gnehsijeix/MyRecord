package com.xjs.arouterbuilder.feature.transformers;

import com.squareup.javapoet.TypeName;
import com.xjs.arouterbuilder.feature.TypeTransformer;
import com.xjs.arouterbuilder.utils.TypeUtils;

import java.io.Serializable;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/14
 *         desc:
 */

public class SerializableTypeTransformerImpl implements TypeTransformer {

    private Types typeUtils;

    public SerializableTypeTransformerImpl(Types typeUtils) {
        this.typeUtils = typeUtils;
    }

    @Override
    public boolean isForType(Element element) {
        Element typeElement = typeUtils.asElement(element.asType());
        return typeElement instanceof TypeElement
                && TypeUtils.recursionIsImplements(
                        (TypeElement) typeElement, TypeName.get(Serializable.class), typeUtils);
    }

    @Override
    public CharSequence transform(Element element) {
        return "Serializable";
    }

}
