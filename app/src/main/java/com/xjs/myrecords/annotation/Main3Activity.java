package com.xjs.myrecords.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xjs.myrecords.R;

import okhttp3.OkHttpClient;

@BuilderTarget()
public class Main3Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
}
