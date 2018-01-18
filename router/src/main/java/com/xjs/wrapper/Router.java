package com.xjs.wrapper;

/**
 * @author xjs
 *         on 2018/1/15
 *         desc:
 */

public final class Router {
    /**
     * 只是为了统一路由跳转的入口
     *
     * @param wrapper ；
     * @param <T>     ；
     * @return ；
     */
    public static <T> T with(IBuilderWrapper<T> wrapper) {
        return wrapper.getTarget();
    }

}
