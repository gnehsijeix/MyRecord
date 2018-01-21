package com.xjs.arouterbuilder.feature.transformers;

import com.squareup.javapoet.TypeName;
import com.xjs.arouterbuilder.feature.TypeTransformer;

import javax.lang.model.element.Element;

/**
 * @author xjs
 *         on  2018/1/14
 *         desc: String类型的
 */

public class StringTypeTransformerImpl implements TypeTransformer {

    @Override
    public boolean accept(Element element) {
        return TypeName.get(element.asType()).toString().equals(String.class.getName());
    }

    @Override
    public CharSequence transform(Element element) {
        return "String";
    }
}
