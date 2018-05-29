package com.idwzx.klinedemo;


import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

/**
 * http://stackoverflow.com/questions/28521004/mpandroidchart-have-one-graph-mirror-the-zoom-swipes-on-a-sister-graph
 */
public class UnionChartGestureListener implements OnChartGestureListener {

    private static final String TAG = UnionChartGestureListener.class.getSimpleName();

    private BaseCombinedChart mSrcChart;
    private BaseCombinedChart[] mUnionCharts;

    public UnionChartGestureListener(BaseCombinedChart srcChart, BaseCombinedChart[] unionCharts) {
        this.mSrcChart = srcChart;
        this.mUnionCharts = unionCharts;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        mSrcChart.highlightValue(null,true);
        syncCharts();
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        if (!mSrcChart.isLongPressing()){
            mSrcChart.setDragEnabled(true);
            for (int index = 0;index < mUnionCharts.length;index++){
                mUnionCharts[index].setDragEnabled(true);
            }
            mSrcChart.highlightValue(null,true);
        }

        syncCharts();
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        mSrcChart.setDragEnabled(false);
        Highlight highlight = mSrcChart.getHighlightByTouchPoint(me.getX(), me.getY());
        mSrcChart.highlightValue(highlight, true);

        for (int index = 0;index < mUnionCharts.length;index++){
            mUnionCharts[index].setDragEnabled(false);
        }
        mSrcChart.setLongPressing(true);
        syncCharts();
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        syncCharts();
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        syncCharts();
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        mSrcChart.highlightValue(null,true);
        syncCharts();
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//        Log.d(TAG, "onChartScale " + scaleX + "/" + scaleY + " X=" + me.getX() + "Y=" + me.getY());

        mSrcChart.highlightValue(null,true);

        syncCharts();
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
//        Log.d(TAG, "onChartTranslate " + dX + "/" + dY + " X=" + me.getX() + "Y=" + me.getY());
        mSrcChart.highlightValue(null,true);
        syncCharts();

    }

    public void syncCharts() {
        Matrix srcMatrix;
        float[] srcValues = new float[9];
        Matrix unionMatrix;
        float[] unionValues = new float[9];
        // get src chart translation matrix:
        srcMatrix = mSrcChart.getViewPortHandler().getMatrixTouch();
        srcMatrix.getValues(srcValues);
        // apply X axis scaling and position to dst charts:
        for (Chart dstChart : mUnionCharts) {
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
}