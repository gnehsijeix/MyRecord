package com.xjs.myrecords.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * create xjs
 * date  2017/8/10
 * description
 */

public class NestedScrollChild extends LinearLayout implements NestedScrollingChild {
    public NestedScrollChild(Context context) {
        this(context, null);
    }

    public NestedScrollChild(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollChild(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable @Size(value = 2) int[] offsetInWindow) {
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }
}
