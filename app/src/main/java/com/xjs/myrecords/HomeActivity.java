package com.xjs.myrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.myrecords.test.TestBean;
import com.xjs.myrecords.view.CircleProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path = "/com/xjs/arouterbuilder/home")
public class HomeActivity extends AppCompatActivity {

    private CircleProgressView mCircleProgressView;

    @Autowired(name = "_progress", required = true, desc = "lira")
    int mProgress = 0;

    @Autowired
    TestBean testBean;

    @Autowired(name = "_password")
    String mPassword;

    @Autowired(name =  "_test_bean_map",required = true)
    Map<String,TestBean> testBeanMap;

    @Autowired
    List<TestBean> testBeanArrayList;




    TestBean testBean2;

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCircleProgressView = (CircleProgressView) findViewById(R.id.main_circle_progress_view);
    }
}
