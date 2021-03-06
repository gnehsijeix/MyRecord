package com.xjs.arouterbuilder.feature.transformers;

import com.xjs.arouterbuilder.feature.ITypeTransform;

import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc:
 */

public abstract class BaseTypeTransform implements ITypeTransform {
    protected Types typesUtils;
    public BaseTypeTransform(Types typesUtils) {
        this.typesUtils = typesUtils;
    }
}
