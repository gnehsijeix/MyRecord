package com.xjs.wrapper;

import android.app.Fragment;

/**
 * @author xjs
 *         on 2018/1/16
 *         desc:
 */

public class FragmentBuilderWrapper<T extends Fragment> extends IBuilderWrapper<T> {

    public FragmentBuilderWrapper(T target) {
        super(target);
    }
}
