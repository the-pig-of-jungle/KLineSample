package com.idwzx.klinedemo;



import android.view.MotionEvent;

import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

public class ChartGestureListener implements OnChartGestureListener {
    private BaseCombinedChart mChart;

    public ChartGestureListener(BaseCombinedChart chart) {
        mChart = chart;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        if (!mChart.isLongPressing()){
            mChart.highlightValue(null);
        }

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        mChart.highlightValue(null);
        if (!mChart.isLongPressing()){
            mChart.setDragEnabled(true);
        }

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        mChart.setLongPressing(true);
        mChart.highlightValue(mChart.getHighlightByTouchPoint(me.getX(), me.getY()));
        mChart.setDragEnabled(false);
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        mChart.setDragEnabled(true);
        mChart.highlightValue(null);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        mChart.highlightValue(null);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        mChart.setDragEnabled(true);
        mChart.highlightValue(null);
    }
}