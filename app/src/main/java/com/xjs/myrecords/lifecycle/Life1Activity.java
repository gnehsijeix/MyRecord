package com.xjs.myrecords.lifecycle;

import android.os.Bundle;

import com.xjs.myrecords.R;

public class Life1Activity extends BaseLogLifecycleActivity {
    private String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life1);
    }
}
