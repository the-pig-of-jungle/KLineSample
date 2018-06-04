package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Description;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCombinedChart extends CombinedChart implements IBaseChart {
    
    

    protected KLineChart mKLineChart;

    protected VolumeOrAmountChart mVolumeOrAmountChart;

    protected IndexChart mIndexChart;

    protected boolean mLongPressing;

    protected boolean mCanUnionOthers;


    public BaseCombinedChart(Context context) {
        super(context);
        initSomething();
    }

    public BaseCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSomething();
    }

    public BaseCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSomething();
    }

    protected void initSomething() {
        mCanUnionOthers = true;

        setScaleEnabled(true);
        setScaleYEnabled(false);
        setAutoScaleMinMaxEnabled(true);
        setDrawBorders(true);
        setBorderWidth(IBaseChart.DEFAULT_CHART_BORDER_WIDTH);
        setBorderColor(getXAxis().getGridColor());
        Description description = new Description();
        description.setText("");
        setDescription(description);

        getLegend().setEnabled(false);

        setHighlightPerTapEnabled(false);
        setHighlightPerDragEnabled(true);
        setDrawMarkers(true);
        setDoubleTapToZoomEnabled(false);

        getViewPortHandler().setMaximumScaleX(9f);
        getViewPortHandler().getMatrixTouch().postScale(4f, 1f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (mCanUnionOthers) {
                    mTouchY = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                mLongPressing = false;
                break;
        }

        boolean consumed = super.onTouchEvent(event);

        if (mCanUnionOthers) {
            union(mKLineChart, event);
            union(mVolumeOrAmountChart, event);
            union(mIndexChart, event);
        }

        if (action == MotionEvent.ACTION_UP) {
            highlightValue(null);
            setDragEnabled(true);
            setCanUnionOthers(true);
        }

        return consumed;
    }

    private void union(BaseCombinedChart chart, MotionEvent event) {
        if (chart != null) {
            chart.setCanUnionOthers(false);
            chart.setTouchY(transferToUnionTouchY(chart, mTouchY));
            chart.onTouchEvent(event);
        }
    }

    public boolean isCanUnionOthers() {
        return mCanUnionOthers;
    }

    public void setCanUnionOthers(boolean canUnionOthers) {
        mCanUnionOthers = canUnionOthers;
    }


    @Override
    public abstract float transferToUnionTouchY(BaseCombinedChart chart, float srcTouchY);


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




    public KLineChart getUnionKLineChart() {
        return mKLineChart;
    }

    public void setUnionKLineChart(KLineChart KLineChart) {
        mKLineChart = KLineChart;
    }


    public VolumeOrAmountChart getUnionVolumeOrAmountChart() {
        return mVolumeOrAmountChart;
    }

    public void setUnionVolumeOrAmountChart(VolumeOrAmountChart volumeOrAmountChart) {
        mVolumeOrAmountChart = volumeOrAmountChart;
    }


    public IndexChart getUnionIndexChart() {
        return mIndexChart;
    }

    public void setUnionIndexChart(IndexChart indexChart) {
        mIndexChart = indexChart;
    }


    protected int getUnionChartHeight(BaseCombinedChart chart) {
        return chart == null ? 0 : chart.getHeight();
    }
}
