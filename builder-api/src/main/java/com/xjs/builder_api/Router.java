package com.xjs.builder_api;

import com.xjs.builder_api.buidable.IBuildable;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc:
 */

public class Router {
    public static  <T> T build(IBuildable<T> iBuildable){
        return iBuildable.build();
    }
}
