package com.xjs.myrecords.dependcyInject.sample;

import android.util.Log;

/**
 * create xjs
 * date  2017/7/9
 * description
 */

public class SingletonObject {
    private static final String TAG = "SingletonObject";
    private String mTag;
    public SingletonObject(String tag){
        mTag = tag;
    }
    public void printTag() {
        Log.d(TAG, "printTag: "+mTag);
    }
}
