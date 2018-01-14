package com.xjs.myrecords.resorce;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * create xjs
 * date  2017/7/16
 * description
 */

public class ViewDrawProcessView extends View{


    public ViewDrawProcessView(Context context) {
        this(context,null);
    }

    public ViewDrawProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewDrawProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //此函数设置mMeasureWidth & mMeasureHeight getMeasureWidth() 在此函数执行后可获取到值
        //setMeasuredDimension();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
