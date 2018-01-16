package com.xjs.wrapper;

/**
 * @author xjs
 *         on 2018/1/16
 *         desc:
 */

public class IBuilderWrapper<T> {
    private T target;

    public IBuilderWrapper(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }
}
