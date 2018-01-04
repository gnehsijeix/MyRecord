package com.xjs.myrecords.resorce;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * create xjs
 * date  2017/7/17
 * description
 */

public class ViewTouchEvent extends View implements View.OnClickListener,View.OnTouchListener{
    public ViewTouchEvent(Context context) {
        this(context, null);
    }

    public ViewTouchEvent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewTouchEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnClickListener(this);
        this.setOnTouchListener(this);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
