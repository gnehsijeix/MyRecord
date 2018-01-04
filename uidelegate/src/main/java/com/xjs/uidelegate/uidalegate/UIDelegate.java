package com.xjs.uidelegate.uidalegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * create xjs
 * date  2017/8/8
 * description : ui模块具体完成
 */

public interface UIDelegate {

    UIUnbinder bind(@NonNull Activity activity);

    UIUnbinder bind(@NonNull Fragment fragment);

    UIUnbinder bind(@NonNull UITarget<?> target);

    interface UIUnbinder {
        void unbind();
    }
}
