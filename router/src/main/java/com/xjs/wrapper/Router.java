package com.xjs.wrapper;


import android.support.v4.app.Fragment;

/**
 * @author xjs
 *         on 2018/1/15
 *         desc:
 */

public final class Router {

    public static <T> T build(IBuilderWrapper<T> wrapper) {
        return wrapper.getTarget();
    }

    public static <T extends android.app.Fragment> T buildFragment(FragmentBuilderWrapper<T> fragmentBuilderWrapper) {
        return fragmentBuilderWrapper.getTarget();
    }

    public static <T extends Fragment> T buildFragment(SupportFragmentBuilderWrapper<T> supportFragmentBuilderWrapper) {
        return supportFragmentBuilderWrapper.getTarget();
    }
}
