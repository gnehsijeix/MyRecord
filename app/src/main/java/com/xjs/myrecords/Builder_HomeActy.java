package com.xjs.myrecords;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.myrecords.annotation.TestFragment;
import com.xjs.wrapper.ActivityBuilderWrapper;
import com.xjs.wrapper.FragmentBuilderWrapper;
import com.xjs.wrapper.Router;
import com.xjs.wrapper.SupportFragmentBuilderWrapper;

public class Builder_HomeActy {

    public static ActivityBuilderWrapper builder(int progress, String password) {

        return new ActivityBuilderWrapper(ARouter.getInstance().build("///")
                .withInt("_progress", progress)
                .withString("_password", password));
    }
}

