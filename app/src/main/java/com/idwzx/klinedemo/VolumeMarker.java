package com.idwzx.klinedemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class VolumeMarker implements IMarker {

    private Entry mEntry;
    private Highlight mHighlight;
    private VolumeOrAmountChart mVolumeOrAmountChart;
    private ViewPortHandler mViewPortHandler;
    private RectF mUtilRect;
    private Paint mMarkerPaint;

    private RectF mContentRect;


    private int mRectBgColor;

    private float mRectBorderWidth;

    private float mIndicatorLineWidth;

    private int mIndicatorLineColor;

    public VolumeMarker(VolumeOrAmountChart chart, @ColorInt int rectBgColor,float indicatorLineWidth,int indicatorLineColor) {
        mVolumeOrAmountChart = chart;
        mUtilRect = new RectF();
        mMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mViewPortHandler = mVolumeOrAmountChart.getViewPortHandler();
        mContentRect = mViewPortHandler.getContentRect();
        mRectBgColor = rectBgColor;
        mRectBorderWidth = Utils.convertDpToPixel(1f);
        mIndicatorLineWidth = indicatorLineWidth;
        mIndicatorLineColor = indicatorLineColor;
    }

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mEntry = e;
        mHighlight = highlight;
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        float padding = Utils.convertDpToPixel(3);
        if (needDrawYMarker(canvas)) {
            drawYMarker(canvas, padding);
        }

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setStrokeWidth(mIndicatorLineWidth);
        mMarkerPaint.setColor(mIndicatorLineColor);

        canvas.drawLine(posX,mContentRect.top,posX,mContentRect.bottom,mMarkerPaint);
    }

    private void drawYMarker(Canvas canvas, float padding) {
        MPPointD mpPointD = mVolumeOrAmountChart.getValuesByTouchPoint(mVolumeOrAmountChart.getTouchX(), mVolumeOrAmountChart.getTouchY(), YAxis.AxisDependency.LEFT);
        String yLabel = "";
        float[] entries = mVolumeOrAmountChart.getAxisLeft().mEntries;

        IAxisValueFormatter formatter =  mVolumeOrAmountChart.getAxisLeft().getValueFormatter();

        yLabel = formatter.getFormattedValue((float) mpPointD.y, mVolumeOrAmountChart.getAxisLeft());


        if (mVolumeOrAmountChart.getTouchX() >= mVolumeOrAmountChart.getWidth() * 0.5f) {
            mUtilRect.set(mVolumeOrAmountChart.getViewPortHandler().contentLeft(), mVolumeOrAmountChart.getTouchY() - (mVolumeOrAmountChart.getXAxis().getTextSize()) / 2 - padding, mVolumeOrAmountChart.getViewPortHandler().contentLeft() + mMarkerPaint.measureText(formatter.getFormattedValue((float) mVolumeOrAmountChart.getValuesByTouchPoint(mVolumeOrAmountChart.getTouchX(), mVolumeOrAmountChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mVolumeOrAmountChart.getAxisLeft()))
                            + padding * 2,
                    mVolumeOrAmountChart.getTouchY() + (mVolumeOrAmountChart.getXAxis().getTextSize()) / 2 + padding);
        } else {
            mUtilRect.set(mVolumeOrAmountChart.getViewPortHandler().contentRight() - mMarkerPaint.measureText(formatter.getFormattedValue((float) mVolumeOrAmountChart.getValuesByTouchPoint(mVolumeOrAmountChart.getTouchX(), mVolumeOrAmountChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mVolumeOrAmountChart.getAxisLeft())) - padding * 2, mVolumeOrAmountChart.getTouchY() - (mVolumeOrAmountChart.getXAxis().getTextSize()) / 2 - padding, mVolumeOrAmountChart.getViewPortHandler().contentRight(),
                    mVolumeOrAmountChart.getTouchY() + (mVolumeOrAmountChart.getXAxis().getTextSize()) / 2 + padding);
        }

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setStrokeWidth(mIndicatorLineWidth);
        mMarkerPaint.setColor(mIndicatorLineColor);
        canvas.drawLine(mContentRect.left, mVolumeOrAmountChart.getTouchY(), mContentRect.right, mVolumeOrAmountChart.getTouchY(), mMarkerPaint);

        mMarkerPaint.setColor(mRectBgColor);

        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.STROKE);
        mMarkerPaint.setStrokeWidth(mRectBorderWidth);
        mMarkerPaint.setColor(mIndicatorLineColor);
        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setColor(mVolumeOrAmountChart.getAxisLeft().getTextColor());
        mMarkerPaint.setTextSize(mVolumeOrAmountChart.getAxisLeft().getTextSize());

        Paint.FontMetrics fontMetrics = mMarkerPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (mUtilRect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(yLabel,
                mUtilRect.centerX() - mMarkerPaint.measureText(yLabel) / 2, baseLineY, mMarkerPaint);
    }

    private boolean needDrawYMarker(Canvas canvas) {
        return mVolumeOrAmountChart.getTouchY() <= mContentRect.bottom && mVolumeOrAmountChart.getTouchY() >= mContentRect.top;
    }
}
