package com.xjs.testmodel;

import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;

/**
 * @author xjs
 *         on 2018/1/24
 *         desc:
 */

public class TestActivity extends AppCompatActivity {
    @Autowired(required = true, name = "lala")
    String wawa;
}
