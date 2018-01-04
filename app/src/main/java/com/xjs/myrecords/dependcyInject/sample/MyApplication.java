package com.xjs.myrecords.dependcyInject.sample;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * create xjs
 * date  2017/7/9
 * description
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        Log.i(TAG, "onCreate: context:"+context);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
