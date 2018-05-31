package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCombinedChart extends CombinedChart implements IBaseChart {


    private boolean mLongPressing;

    private List<BaseCombinedChart> mChartsOfUnion;
    private boolean mCanUnion = true;


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
        if (mCanUnion){
            mChartsOfUnion.get(0).setCanUnion(false);
        }

        int action = event.getAction() & MotionEvent.ACTION_MASK;

        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mTouchX = event.getX();
                mTouchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mLongPressing = false;
                break;
        }



        if (action == MotionEvent.ACTION_UP){
            highlightValue(null,false);
            setDragEnabled(true);
        }

        boolean consumed = super.onTouchEvent(event);

        if (mCanUnion){
            mChartsOfUnion.get(0).onTouchEvent(event);
        }

        if (action == MotionEvent.ACTION_UP){
//            highlightValue(null,false);
            setDragEnabled(true);
            setCanUnion(true);
        }

        return consumed;
    }

    public boolean isCanUnion() {
        return mCanUnion;
    }

    public void setCanUnion(boolean canUnion) {
        mCanUnion = canUnion;
    }

    public void syncCharts() {
        Matrix srcMatrix;
        float[] srcValues = new float[9];
        Matrix unionMatrix;
        float[] unionValues = new float[9];
        // get src chart translation matrix:
        srcMatrix = getViewPortHandler().getMatrixTouch();
        srcMatrix.getValues(srcValues);
        // apply X axis scaling and position to dst charts:
        for (Chart dstChart : mChartsOfUnion) {
            if (dstChart == null) {
                return;
            }
            if (dstChart.getVisibility() == View.VISIBLE) {
                unionMatrix = dstChart.getViewPortHandler().getMatrixTouch();
                unionMatrix.getValues(unionValues);

                unionValues[Matrix.MSCALE_X] = srcValues[Matrix.MSCALE_X];
                unionValues[Matrix.MSKEW_X] = srcValues[Matrix.MSKEW_X];
                unionValues[Matrix.MTRANS_X] = srcValues[Matrix.MTRANS_X];
                unionValues[Matrix.MSKEW_Y] = srcValues[Matrix.MSKEW_Y];
                unionValues[Matrix.MSCALE_Y] = srcValues[Matrix.MSCALE_Y];
                unionValues[Matrix.MTRANS_Y] = srcValues[Matrix.MTRANS_Y];
                unionValues[Matrix.MPERSP_0] = srcValues[Matrix.MPERSP_0];
                unionValues[Matrix.MPERSP_1] = srcValues[Matrix.MPERSP_1];
                unionValues[Matrix.MPERSP_2] = srcValues[Matrix.MPERSP_2];

                unionMatrix.setValues(unionValues);
                dstChart.getViewPortHandler().refresh(unionMatrix, dstChart, true);
            }
        }
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
