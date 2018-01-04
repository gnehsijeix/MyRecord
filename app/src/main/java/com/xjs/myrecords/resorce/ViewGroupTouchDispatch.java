package com.xjs.myrecords.resorce;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * create xjs
 * date  2017/7/17
 * description
 */

public class ViewGroupTouchDispatch extends ViewGroup {

    public ViewGroupTouchDispatch(Context context) {
        this(context, null);
    }

    public ViewGroupTouchDispatch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupTouchDispatch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
