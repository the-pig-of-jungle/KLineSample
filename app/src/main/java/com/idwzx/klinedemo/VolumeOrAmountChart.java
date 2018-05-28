package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.CombinedChart;
import com.idwzx.klinedemo.bean.KLineEntity;

public class VolumeOrAmountChart extends BaseCombinedChart {
    private String mLeftLabel = "";
    private float mLeftLabelSize;
    private int mLeftLabelColor;
    private Paint mLabelPaint;

    public VolumeOrAmountChart(Context context) {
        super(context);
    }

    public VolumeOrAmountChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumeOrAmountChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mLeftLabel,getViewPortHandler().contentLeft(),getViewPortHandler().contentTop() + getLeftLabelPaint().getTextSize(),getLeftLabelPaint());
        canvas.drawText(getAxisLeft().getFormattedLabel(2),getViewPortHandler().contentRight() - getRightLabelPaint().measureText(getAxisLeft().getFormattedLabel(2)),
                getViewPortHandler().contentTop() + getRightLabelPaint().getTextSize(),getRightLabelPaint());
    }

    public String getLeftLabel() {
        return mLeftLabel;
    }

    public void setLeftLabel(String leftLabel) {
        mLeftLabel = leftLabel;
    }

    public float getLeftLabelSize() {
        return mLeftLabelSize;
    }

    public void setLeftLabelSize(float leftLabelSize) {
        mLeftLabelSize = leftLabelSize;
    }

    public int getLeftLabelColor() {
        return mLeftLabelColor;
    }

    public void setLeftLabelColor(int leftLabelColor) {
        mLeftLabelColor = leftLabelColor;
    }

    public Paint getLeftLabelPaint() {
        if (mLabelPaint == null){
            mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLabelPaint.setTextSize(mLeftLabelSize);
            mLabelPaint.setColor(mLeftLabelColor);
        }
        return mLabelPaint;
    }

    public Paint getRightLabelPaint(){
        if (mLabelPaint == null){
            mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLabelPaint.setTextSize(getAxisLeft().getTextSize());
            mLabelPaint.setColor(getAxisLeft().getTextColor());
        }

        return mLabelPaint;
    }



    @Override
    public float transferToUnionTouchY(BaseCombinedChart chart,float srcTouchY) {

        if (chart instanceof KLineChart){
            return srcTouchY + chart.getHeight();
        }

        if (chart instanceof IndexChart){
            return srcTouchY - getHeight();
        }

        return srcTouchY;

    }

}
