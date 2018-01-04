package com.xjs.myrecords.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * create xjs
 * date  2017/7/23
 * description
 */

public class NestedScrollLinearLayout extends LinearLayout implements NestedScrollingParent {
    private int mTopHeight;

    public NestedScrollLinearLayout(Context context) {
        this(context, null);
    }

    public NestedScrollLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * onStartNestedScroll该方法，一定要按照自己的需求返回true，
     * 该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
     * 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断。
     *
     * @param child            Direct child of this ViewParent containing target
     * @param target           View that initiated the nested scroll
     * @param nestedScrollAxes
     * @return true if this ViewParent accepts the nested scroll operation
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        // return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        //return false;
        //9   2^2 + 2^5

        // 1001 << 3  100100
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

    }

    @Override
    public void onStopNestedScroll(View target) {

    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    /**
     * 从名字可以看出，在滑动之前会被调用，他的作用就是子类在滑动的时候，分发一下，是否有父类需要消费滑动，
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        /*
        * boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);
        if (hiddenTop || showTop)
        {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
        *
        *
        * */


    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
