package com.xjs.myrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xjs.myrecords.test.TestBean;
import com.xjs.myrecords.view.CircleProgressView;

@Route(path = "/com/xjs/routebuilder/home")
public class HomeActivity extends AppCompatActivity {
    private CircleProgressView mCircleProgressView;


    @Autowired(name = "_progress", required = true, desc = "lira")
    int mProgress = 0;
    @Autowired(required = true)
    String mPassword = new String("aaa");
    @Autowired(name = "isFirst")
    boolean first;
    @Autowired
    char aChar;
    @Autowired
    Float aFloat;
    @Autowired
    double aDouble;
    @Autowired
    TestBean testBean;

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCircleProgressView = (CircleProgressView) findViewById(R.id.main_circle_progress_view);
    }
}
