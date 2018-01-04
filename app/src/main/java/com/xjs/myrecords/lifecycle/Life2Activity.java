package com.xjs.myrecords.lifecycle;

import android.os.Bundle;

import com.xjs.myrecords.R;

/**
 * @author xjs
 *         on  2017/12/30
 *         desc:
 */

public class Life2Activity extends BaseLogLifecycleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life1);
    }
}
