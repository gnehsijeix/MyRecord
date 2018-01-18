package com.xjs.myrecords;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.wrapper.RouteBuilderWrapper;
import com.xjs.wrapper.Router;

public class Builder_HomeActy extends Activity {

    public static RouteBuilderWrapper builder(int progress, String password) {
        Router.with(Builder_HomeActivity.builder(100, ""))
                .navigation();

        return new RouteBuilderWrapper(ARouter.getInstance().build("///")
                .withInt("_progress", progress)
                .withString("_password", password));
    }
}

