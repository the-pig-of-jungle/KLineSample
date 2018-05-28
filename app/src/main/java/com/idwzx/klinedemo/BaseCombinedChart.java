package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCombinedChart extends CombinedChart implements IBaseChart {


    private boolean mLongPressing;

    private List<BaseCombinedChart> mChartsOfUnion;

    public BaseCombinedChart(Context context) {
        super(context);
        initSomething();
    }

    private void initSomething() {
        mChartsOfUnion = new ArrayList<>();
    }

    public BaseCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSomething();
    }

    public BaseCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSomething();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int chartCount = getChartsOfUnion().size();
        if (isLongPressing() && chartCount != 0){
            for (int index = 0;index < chartCount;index++){
                getChartsOfUnion().get(index).postInvalidate();
            }
        }
    }

    @Override
    public boolean isLongPressing() {
        return mLongPressing;
    }

    @Override
    public void setLongPressing(boolean longPressing) {
        mLongPressing = longPressing;
    }

    private float mTouchX;
    private float mTouchY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;

        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mTouchX = event.getX();
                mTouchY = event.getY();
                for (int index = 0;index < mChartsOfUnion.size();index++){
                    mChartsOfUnion.get(index).setTouchX(mTouchX);
                    mChartsOfUnion.get(index).setTouchY(transferToUnionTouchY(mChartsOfUnion.get(index),mTouchY));
                }
                break;
            case MotionEvent.ACTION_UP:
                mLongPressing = false;
                break;
        }



        if (action == MotionEvent.ACTION_UP){
            highlightValue(null,true);
            setDragEnabled(true);
        }

        boolean consumed = super.onTouchEvent(event);
        return consumed;
    }

    @Override
    public abstract float transferToUnionTouchY(BaseCombinedChart chart,float srcTouchY);

    public List<BaseCombinedChart> getChartsOfUnion() {
        return mChartsOfUnion;
    }

    public void setChartsOfUnion(List<BaseCombinedChart> chartsOfUnion) {
        mChartsOfUnion = chartsOfUnion;
    }

    @Override
    public float getTouchX() {
        return mTouchX;
    }
    @Override
    public void setTouchX(float touchX) {
        mTouchX = touchX;
    }
    @Override
    public float getTouchY() {
        return mTouchY;
    }
    @Override
    public void setTouchY(float touchY) {
        mTouchY = touchY;
    }


    @Override
    public void addUnionChart(BaseCombinedChart baseCombinedChart){
        mChartsOfUnion.add(baseCombinedChart);
    }
}
