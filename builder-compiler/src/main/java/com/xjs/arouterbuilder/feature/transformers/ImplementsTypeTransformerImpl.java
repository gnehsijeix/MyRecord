package com.xjs.arouterbuilder.feature.transformers;

import com.xjs.arouterbuilder.feature.TypeTransformer;

import javax.lang.model.element.Element;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc: 实现接口的获取
 */

public class ImplementsTypeTransformerImpl implements TypeTransformer {

    @Override
    public boolean accept(Element element) {
        return false;
    }

    @Override
    public CharSequence transform(Element element) {
        return null;
    }
}
