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
    private BaseCombinedChart[] mChartsOfUnion;

    public UnionChartGestureListener(BaseCombinedChart srcChart, BaseCombinedChart[] chartsOfUnion) {
        this.mSrcChart = srcChart;
        this.mChartsOfUnion = chartsOfUnion;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        mSrcChart.highlightValue(null);
        syncCharts();
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        mSrcChart.setDragEnabled(true);
        mSrcChart.highlightValue(null,true);
        syncCharts();
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Highlight highlight = mSrcChart.getHighlightByTouchPoint(me.getX(), me.getY());
        mSrcChart.highlightValue(highlight, true);
        mSrcChart.setDragEnabled(false);
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
        mSrcChart.setDragEnabled(true);
        syncCharts();
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//        Log.d(TAG, "onChartScale " + scaleX + "/" + scaleY + " X=" + me.getX() + "Y=" + me.getY());
        syncCharts();
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
//        Log.d(TAG, "onChartTranslate " + dX + "/" + dY + " X=" + me.getX() + "Y=" + me.getY());
        mSrcChart.highlightValue(null);
        syncCharts();

    }

    public void syncCharts() {
        Matrix srcMatrix;
        float[] srcVals = new float[9];
        Matrix unionMatrix;
        float[] dstVals = new float[9];
        // get src chart translation matrix:
        srcMatrix = mSrcChart.getViewPortHandler().getMatrixTouch();
        srcMatrix.getValues(srcVals);
        // apply X axis scaling and position to dst charts:
        for (Chart dstChart : mChartsOfUnion) {
            if (dstChart == null) {
                return;
            }
            if (dstChart.getVisibility() == View.VISIBLE) {
                unionMatrix = dstChart.getViewPortHandler().getMatrixTouch();
                unionMatrix.getValues(dstVals);

                dstVals[Matrix.MSCALE_X] = srcVals[Matrix.MSCALE_X];
                dstVals[Matrix.MSKEW_X] = srcVals[Matrix.MSKEW_X];
                dstVals[Matrix.MTRANS_X] = srcVals[Matrix.MTRANS_X];
                dstVals[Matrix.MSKEW_Y] = srcVals[Matrix.MSKEW_Y];
                dstVals[Matrix.MSCALE_Y] = srcVals[Matrix.MSCALE_Y];
                dstVals[Matrix.MTRANS_Y] = srcVals[Matrix.MTRANS_Y];
                dstVals[Matrix.MPERSP_0] = srcVals[Matrix.MPERSP_0];
                dstVals[Matrix.MPERSP_1] = srcVals[Matrix.MPERSP_1];
                dstVals[Matrix.MPERSP_2] = srcVals[Matrix.MPERSP_2];

                unionMatrix.setValues(dstVals);
                dstChart.getViewPortHandler().refresh(unionMatrix, dstChart, true);
            }
        }
    }
}