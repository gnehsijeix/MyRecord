package com.xjs.myrecords.screenadapter;

import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xjs.myrecords.R;

public class ScreenAdaptActivity extends AppCompatActivity {
    private static final String TAG = "ScreenAdaptActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String string = getResources().getString(R.string.test_screen_adapt);
        Log.i(TAG, "onCreate: screen message:" + string);
        int densityDpi = getResources().getDisplayMetrics().densityDpi;
        Log.i(TAG, "onCreate: densityDpi:"+densityDpi);
        float density = getResources().getDisplayMetrics().density;
        Log.i(TAG, "onCreate: density:"+density);
        float xdpi = getResources().getDisplayMetrics().xdpi;
        Log.i(TAG, "onCreate: xdpi:"+xdpi);
        Log.i(TAG, "onCreate: 1dp equas:"+SizeUtils.dp2px(this,1));
    }
}
