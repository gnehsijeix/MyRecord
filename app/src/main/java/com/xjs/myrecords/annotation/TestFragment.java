package com.xjs.myrecords.annotation;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.wrapper.ActivityBuilderWrapper;
import com.xjs.wrapper.SupportFragmentBuilderWrapper;

/**
 * @author xjs
 *         on 2018/1/16
 *         desc:
 */

public class TestFragment extends Fragment {

    public static SupportFragmentBuilderWrapper<TestFragment> builder(int progress, String password) {
        return new SupportFragmentBuilderWrapper<TestFragment>((TestFragment) ARouter.getInstance().build("//").navigation());
    }
}
