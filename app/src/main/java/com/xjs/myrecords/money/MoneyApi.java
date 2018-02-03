package com.xjs.myrecords.money;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjs
 *         on  2018/1/27
 *         desc: which classes that are related to
 */

public class MoneyApi extends AppCompatActivity {

    public void activityManager(Context context) {
        //Interact with the overall activities running in the system.
        // 与运行在系统的整体全部activities相互作用
        // Context.ACTIVITY_SERVICE
        /*
        * use with getSystemService() to retrieve a activityManager for interacting
        * with the global system state
        *
        * 以 getSystemService 的方式 找到(检索) 一个正在与全局的系统状态交互着的 activityManager
        *
        * */
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        assert activityManager != null;

        // 获取系统这个app会内存大小已MB返回
        int limitMemoryMB = activityManager.getMemoryClass();
        int largeLimitMemoryMB = activityManager.getLargeMemoryClass();
        // Application 标签中 < android:largeHead = "true"> 中设置申请大的内存堆 大部分手机无效
        /*
        * 一般情况下两个相等·
        * */
    }

    public void runtimeApi() {
        float totalMemory = Runtime.getRuntime().totalMemory();
        float freeMemory = Runtime.getRuntime().freeMemory();
        float maxMemory = Runtime.getRuntime().maxMemory();

    }

    /**
     * {@link ComponentCallbacks2#TRIM_MEMORY_BACKGROUND}
     */
    public void onTrimMemory() {

        /*
        * 在activity onTrimMemory(int level)
        * 在Application onTrimMemory(int level)

        * */
    }

    public void dataStructure() {
        //使用V4包下的 ArrayMap()
        SparseArray sparseArray = new SparseArray();
        Map<String, Object> map = new ArrayMap<>();
        Map<String, Object> hasMap = new HashMap<>();
    }

    /**
     * 内存抖动
     * 反复申请生命周期短的大内存对象
     * 考虑对象的复用
     */
    public void memoryJittr() {

        int length = 50000;
        for (int i = 0; i < 10; i++) {
            String[] strings = new String[length];
            for (int i1 = 0; i1 < length; i1++) {
                strings[i1] = String.valueOf(i1);
            }
        }

    }

    /**
     * 对象的复用
     */
    public void objectRecycle() {


        //避免在 onDraw()里创建对象
    }

    /**
     * 内存泄露
     */
    public void memoryLank() {
        // activity  heap越来越少 gc频繁
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
