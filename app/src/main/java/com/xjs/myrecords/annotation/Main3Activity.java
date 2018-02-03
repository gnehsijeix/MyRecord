package com.xjs.myrecords.annotation;

import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.myrecords.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

@BuilderTarget()
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String string = "mProgress";
        //匹配 类似于 mUserName这样的
        String regex = "m[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        boolean find = matcher.find();
        CharSequence charSequence;
        CharSequence[] charSequences;
        ParcelableSparseArray parcelableSparseArray;

        SparseArray<? extends Parcelable> sparseArray;
//
//        ARouter.getInstance().build("/home/build")
//                .withSparseParcelableArray()
//                .withIntegerArrayList()
//                .withParcelableArrayList()
//                .withStringArrayList()
//                .withCharSequenceArrayList()
        ArrayList<Parcelable> parcelableArrayList = new ArrayList<>();
        ARouter.getInstance().build("/")
//                .withStringArrayList()
                .withParcelableArrayList("aaa",parcelableArrayList);
//                .withIntegerArrayList()
//                .withCharSequenceArrayList()
//
    }
}
