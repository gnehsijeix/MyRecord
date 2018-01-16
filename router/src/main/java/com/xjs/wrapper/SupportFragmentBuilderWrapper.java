package com.xjs.wrapper;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;

/**
 * @author xjs
 *         on 2018/1/16
 *         desc:
 */

public class SupportFragmentBuilderWrapper<T extends Fragment> extends IBuilderWrapper<T> {

    public SupportFragmentBuilderWrapper(T wrapper) {
        super(wrapper);
    }
}
