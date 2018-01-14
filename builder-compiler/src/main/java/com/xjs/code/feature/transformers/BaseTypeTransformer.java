package com.xjs.code.feature.transformers;

import com.xjs.code.feature.TypeTransformer;

import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc:
 */

public abstract class BaseTypeTransformer implements TypeTransformer{
    protected Types typesUtils;
    public BaseTypeTransformer(Types typesUtils) {
        this.typesUtils = typesUtils;
    }
}
