package com.xjs.myrecords.dependcyInject.sample;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * create xjs
 * date  2017/7/8
 * description
 */

public class Person {
    private String mName;
    private static final String TAG = "Person";
    transient Context mContext;
    //@Inject
    public Person(Context context) {
        mContext = context;
        mName = "default";
        Log.i(TAG, "Person: person create with context");
    }
    public Person(String name) {
        this.mContext = null;
        this.mName = name;
        Log.i(TAG, "Person: person create with name");

    }

    public void printMessage() {
        Log.i(TAG, "printMessage: I am a person; name:"+mName);
        if (mContext!=null) {
            Log.i(TAG, "printMessage: context:"+mContext);
        }
    }
}
