package com.xjs.myrecords.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * create xjs
 * date  2017/8/20
 * description
 */

public class UnlimiteRecyclerView extends RecyclerView {
    public UnlimiteRecyclerView(Context context) {
        this(context, null);
    }

    public UnlimiteRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnlimiteRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int widthS = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec),MeasureSpec.UNSPECIFIED);
        int heightS = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightSpec),MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthS, heightS);
    }
}
