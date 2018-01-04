package com.xjs.myrecords.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.xjs.code.BuilderTarget;
import com.xjs.myrecords.R;

@BuilderTarget()
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
}
