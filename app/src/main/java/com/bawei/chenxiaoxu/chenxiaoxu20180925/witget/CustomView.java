package com.bawei.chenxiaoxu.chenxiaoxu20180925.witget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/25.
 */

public class CustomView extends ViewGroup {

    private int mleftMargin=20;
    private int mtopMargin=20;
    private int measuredHeight;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int leftMargin=mleftMargin;
        int topMargin=mtopMargin;

        int viewHeight=0;
        int viewWeight=0;

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        switch (modeHeight){
            case MeasureSpec.AT_MOST:
                int measureHeight=0;
                for (int i = 0; i < getChildCount(); i++) {
                    int measuredWidth = getChildAt(i).getMeasuredWidth();
                    measuredHeight = getChildAt(i).getMeasuredHeight();
                    if(leftMargin+measuredWidth+mleftMargin>getWidth()){
                        leftMargin=mleftMargin;
                        topMargin+=measureHeight+mtopMargin;
                    }
                    leftMargin+=measuredWidth+mleftMargin;
                }
                topMargin+=measureHeight+mtopMargin;
                break;
        }
        setMeasuredDimension(sizeWidth,topMargin);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftMargin=mleftMargin;
        int topMargin=mtopMargin;
        for (int i = 0; i < getChildCount(); i++) {
            int measuredWidth = getChildAt(i).getMeasuredWidth();
            int measuredHeight = getChildAt(i).getMeasuredHeight();

            if(leftMargin+measuredWidth+mleftMargin>getWidth()){
                leftMargin=mleftMargin;
                topMargin+=measuredHeight+mtopMargin;
                getChildAt(i).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }else{
                getChildAt(i).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }
            leftMargin+=measuredWidth+mleftMargin;
        }
    }
}
