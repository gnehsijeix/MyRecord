package com.xjs.myrecords.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xjs.myrecords.R;

/**
 * create xjs
 * date  2017/7/19
 * description
 */

public class CircleProgressView extends View {
    /**
     * 圆环的宽（并不是指水平宽 而是粗细）
     */
    private int mAnnulusWidth;
    /**
     * 圆环的半径
     */
    private int mAnnulusRadius;
    /**
     * 扇形的半径
     */
    private int mSectorRadius;
    /**
     * 圆环与扇形进度的间隔宽
     */
    private int mIntervalWidth;
    /**
     * 当前进度
     */
    private int mProgress;
    /**
     * 圆环的颜色
     */
    private int mAnnulusColor;
    /**
     * 扇形进度的颜色
     */
    private int mProgressSectorColor;
    /**
     * 是否绘制圆环
     */
    private boolean drawAnnulus = true;
    /**
     * 用于绘制圆环的画笔
     */
    private Paint mAnnulsPaint;
    /**
     * 用于绘制扇形进度的画笔
     */
    private Paint mProgressSectorPaint;
    private RectF mSectorRectF;

    /**
     * 默认的最大进度为100
     */
    private static final int MAX_PROGRESS = 100;
    private static final int DEFAULT_ANNULUS_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_PROGRESS_SECTOR_COLOR = 0xFFFFFFFF;

    private static final String TAG = "CircleProgressView";
    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray attrArray = context.obtainStyledAttributes(attrs,
                R.styleable.CircleProgressView);
        mAnnulusWidth = attrArray.getDimensionPixelSize(R.styleable.CircleProgressView_annulus_width,0);
        mAnnulusColor = attrArray.getColor(R.styleable.CircleProgressView_annulus_color,DEFAULT_ANNULUS_COLOR);
        mProgressSectorColor = attrArray.getColor(R.styleable.CircleProgressView_progress_sector_color,DEFAULT_PROGRESS_SECTOR_COLOR);
        mIntervalWidth = attrArray.getDimensionPixelSize(R.styleable.CircleProgressView_annulus_and_sector_interval,0);
        attrArray.recycle();

        drawAnnulus = mAnnulusWidth!=0;
        if (drawAnnulus) {
            mAnnulsPaint = new Paint();
            mAnnulsPaint.setColor(mAnnulusColor);
            mAnnulsPaint.setStyle(Paint.Style.STROKE);
            mAnnulsPaint.setAntiAlias(true);
            mAnnulsPaint.setStrokeWidth(mAnnulusWidth);
        }
        mProgressSectorPaint = new Paint();
        mProgressSectorPaint.setAntiAlias(true);
        mProgressSectorPaint.setColor(mProgressSectorColor);
        mProgressSectorPaint.setStyle(Paint.Style.FILL);
        mSectorRectF = new RectF();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //Log.i(TAG, "onSizeChanged: "+"width:"+w+"height:"+h);
        if (drawAnnulus) {
            mAnnulusRadius = w/2 - mAnnulusWidth/2;
            mSectorRadius = mAnnulusRadius-mAnnulusWidth - mIntervalWidth;
            float interval = w/2-mSectorRadius;
            mSectorRectF.set(interval,interval,interval+mSectorRadius*2,interval+mSectorRadius*2);
        } else {
            mSectorRadius = w/2-1 ;
            mSectorRectF.set(0,0,w,w);
        }

    }

    /**
     * 更新进度
     * @param progress 进度百分比
     */
    public void updateProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        //画圆环
        if (drawAnnulus) {
            canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2, mAnnulusRadius,mAnnulsPaint);
        }
        //画扇形
        canvas.drawArc(mSectorRectF,0,mProgress*1f/MAX_PROGRESS*360,true,mProgressSectorPaint);
    }
}
