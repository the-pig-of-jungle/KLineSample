package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;

import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;


import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;


public class KLineChart extends BaseCombinedChart {

    private Paint mXLabelPaint;

    private String mStartYLabel;
    private String mEndYLabel;


    private Paint mYLabelPaint;

    public KLineChart(Context context) {
        super(context);
    }

    public KLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public float transferToUnionTouchY(BaseCombinedChart chart,float srcTouchY) {
        return srcTouchY - getHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float minOffset = dp2px(getMinOffset());

        float marginTop = minOffset + getExtraTopOffset();
        float marginBottom = minOffset + getExtraBottomOffset();

        float vAdded = dp2px(3);

        float marginLeft = minOffset + getExtraLeftOffset();

        float marginRight = minOffset + getExtraRightOffset();

        float hAdded = dp2px(4);

        drawYLabel(canvas, marginTop, marginBottom, vAdded, marginLeft, hAdded);

        drawXLabel(canvas, marginBottom, marginLeft, marginRight);


    }


    private void drawYLabel(Canvas canvas, float marginTop, float marginBottom, float vAdded, float marginLeft, float hAdded) {
        canvas.drawText(getStartYLabel(),marginLeft + hAdded,getHeight() - marginBottom - vAdded,getYLabelPaint());
        canvas.drawText(getEndYLabel(),marginLeft + hAdded,marginTop + getYLabelPaint().getTextSize(),getYLabelPaint());
    }

    private void drawXLabel(Canvas canvas, float marginBottom, float marginLeft, float marginRight) {
        int labelCount = getXAxis().getLabelCount();

        float labelY = getHeight() - (marginBottom - getXLabelPaint().getTextSize()) / 2;

        canvas.drawText(getXAxis().getFormattedLabel(0),marginLeft,labelY,getXLabelPaint());

        String endXLabel = getXAxis().getFormattedLabel(labelCount - 1);

        canvas.drawText(endXLabel,getWidth() - marginRight - getXLabelPaint().measureText(endXLabel),labelY,getXLabelPaint());
    }

    private float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }


    public Paint getXLabelPaint() {
        if (mXLabelPaint == null){
            mXLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mXLabelPaint.setColor(getXAxis().getTextColor());
            mXLabelPaint.setTextSize(getXAxis().getTextSize());
        }
        return mXLabelPaint;
    }

    public Paint getYLabelPaint() {
        if (mYLabelPaint == null){
            mYLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mYLabelPaint.setColor(getAxisLeft().getTextColor());
            mYLabelPaint.setTextSize(getAxisLeft().getTextSize());
        }
        return mYLabelPaint;
    }



    private String ensureNotNull(String str) {
        return str == null ? "" : str;
    }



    public String getStartYLabel() {
        return ensureNotNull(mStartYLabel);
    }

    public void setStartYLabel(String startYLabel) {
        mStartYLabel = startYLabel;
    }

    public String getEndYLabel() {
        return ensureNotNull(mEndYLabel);
    }

    public void setEndYLabel(String endYLabel) {
        mEndYLabel = endYLabel;
    }



    public float getMinOffsetPx(){
        return dp2px(getMinOffset());
    }

}
