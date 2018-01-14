package com.xjs.code.feature.transformers;

import com.squareup.javapoet.TypeName;
import com.xjs.code.feature.TypeTransformer;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc: 实现接口的获取
 */

public class ImplementsTypeTransformer implements TypeTransformer {

    @Override
    public boolean isForType(Element element) {
        return false;
    }

    @Override
    public CharSequence transform(Element element) {
        return null;
    }
}
