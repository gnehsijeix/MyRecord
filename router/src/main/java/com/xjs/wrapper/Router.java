package com.xjs.wrapper;


/**
 * @author xjs
 *         on 2018/1/15
 *         desc:
 */

public final class Router {
    public static <T> T build(IBuildable<T> buildable) {
        return buildable.build();
    }
}
