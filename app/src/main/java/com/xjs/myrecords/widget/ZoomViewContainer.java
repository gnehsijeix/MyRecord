package com.xjs.myrecords.widget;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.support.v4.widget.ViewDragHelper;

/**
 * create xjs
 * date  2017/8/20
 * description
 */

public class ZoomViewContainer extends FrameLayout {

    GestureDetector gestureDetector;
    private static final String TAG = "ZoomViewContainer";

    public ZoomViewContainer(Context context) {
        this(context, null);
    }

    public ZoomViewContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomViewContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        gestureDetector = new GestureDetector(context, new ScaleGestureListener());


    }
    public void resetScale() {
        Log.i(TAG, "scale1: "+this.getWidth()+this.getHeight());
        ViewPropertyAnimator viewPropertyAnimator = this.animate();
        viewPropertyAnimator.scaleX(1).scaleY(1).setDuration(0).start();
    }


    public void scale2() {

        ViewPropertyAnimator viewPropertyAnimator = this.animate();
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: width:"+ZoomViewContainer.this.getWidth()+"height:"+ZoomViewContainer.this.getHeight()); ;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        viewPropertyAnimator.scaleX(2).scaleY(2).start();
    }

    public void scale3() {
        ViewPropertyAnimator viewPropertyAnimator = this.animate();
        viewPropertyAnimator.scaleX(3).scaleY(3);
    }

    public void reset() {
        this.scrollTo(0,0);
        this.resetScale();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
        //return super.onTouchEvent(event);
    }

    class ScaleGestureListener implements GestureDetector.OnGestureListener {
        private static final String TAG = "ScaleGestureListener";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown: ");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: distanceX:" + distanceX + "distanceY:" + distanceY);
            if (e2.getX() - e1.getX() > 0) {
                Log.i(TAG, "onScroll: 右滑" + (e2.getX() - e1.getX()));
            } else {
                Log.i(TAG, "onScroll: 左滑" + (e2.getX() - e1.getX()));
            }
            ZoomViewContainer.this.scrollBy((int) ((e1.getX() - e2.getX()) / 35 + distanceX), (int) (((e1.getY() - e2.getY()) / 35) + distanceY));
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling:velocityX:velocityX:" + velocityX);
            return false;
        }
    }
}
