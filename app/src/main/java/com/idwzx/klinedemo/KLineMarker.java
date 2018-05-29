package com.idwzx.klinedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.RectF;
import android.support.annotation.ColorInt;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class KLineMarker implements IMarker {
    private Entry mEntry;
    private Highlight mHighlight;
    private KLineChart mKLineChart;
    private ViewPortHandler mViewPortHandler;
    private RectF mUtilRect;
    private Paint mMarkerPaint;

    private RectF mContentRect;


    private int mRectBgColor;

    private float mRectBorderWidth;




    public KLineMarker(KLineChart kLineChart, @ColorInt int rectBgColor) {
        mKLineChart = kLineChart;
        mUtilRect = new RectF();
        mMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mViewPortHandler = mKLineChart.getViewPortHandler();
        mContentRect = mViewPortHandler.getContentRect();
        mRectBgColor = rectBgColor;
        mRectBorderWidth = Utils.convertDpToPixel(1f);

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

        drawXMarker(canvas, padding);

    }

    private void drawYMarker(Canvas canvas, float padding) {
        MPPointD mpPointD = mKLineChart.getValuesByTouchPoint(mKLineChart.getTouchX(), mKLineChart.getTouchY(), YAxis.AxisDependency.LEFT);
        String yLabel = "";
        float[] entries = mKLineChart.getAxisLeft().mEntries;

        EasyYAxisValueFormatter formatter = (EasyYAxisValueFormatter) mKLineChart.getAxisLeft().getValueFormatter();

        yLabel = formatter.getEasyFormattedValue((float) mpPointD.y, mKLineChart.getAxisLeft());


        if (mKLineChart.getTouchX() >= mKLineChart.getWidth() * 0.5f) {
            mUtilRect.set(mKLineChart.getViewPortHandler().contentLeft(), mKLineChart.getTouchY() - (mKLineChart.getXAxis().getTextSize()) / 2 - padding, mKLineChart.getViewPortHandler().contentLeft() + mMarkerPaint.measureText(formatter.getEasyFormattedValue((float) mKLineChart.getValuesByTouchPoint(mKLineChart.getTouchX(), mKLineChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mKLineChart.getAxisLeft()))
                            + padding * 2,
                    mKLineChart.getTouchY() + (mKLineChart.getXAxis().getTextSize()) / 2 + padding);
        } else {
            mUtilRect.set(mKLineChart.getViewPortHandler().contentRight() - mMarkerPaint.measureText(formatter.getEasyFormattedValue((float) mKLineChart.getValuesByTouchPoint(mKLineChart.getTouchX(), mKLineChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mKLineChart.getAxisLeft())) - padding * 2, mKLineChart.getTouchY() - (mKLineChart.getXAxis().getTextSize()) / 2 - padding, mKLineChart.getViewPortHandler().contentRight(),
                    mKLineChart.getTouchY() + (mKLineChart.getXAxis().getTextSize()) / 2 + padding);
        }

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setStrokeWidth(mKLineChart.getData().getCandleData().getDataSetByIndex(0).getHighlightLineWidth());
        mMarkerPaint.setColor(mKLineChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        canvas.drawLine(mContentRect.left, mKLineChart.getTouchY(), mContentRect.right, mKLineChart.getTouchY(), mMarkerPaint);


        mMarkerPaint.setColor(mRectBgColor);

        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.STROKE);
        mMarkerPaint.setStrokeWidth(mRectBorderWidth);
        mMarkerPaint.setColor(mKLineChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setColor(mKLineChart.getAxisLeft().getTextColor());
        mMarkerPaint.setTextSize(mKLineChart.getAxisLeft().getTextSize());

        Paint.FontMetrics fontMetrics = mMarkerPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (mUtilRect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(yLabel,
                mUtilRect.centerX() - mMarkerPaint.measureText(yLabel) / 2, baseLineY, mMarkerPaint);
    }

    private void drawXMarker(Canvas canvas, float padding) {
        String xLabel = mKLineChart.getXAxis().getValueFormatter().getFormattedValue(mEntry.getX(), mKLineChart.getXAxis());

        float left = mKLineChart.getTouchX() - mMarkerPaint.measureText(xLabel) / 2 - padding;
        float top = mContentRect.bottom;
        float right = mKLineChart.getTouchX() + mMarkerPaint.measureText(xLabel) / 2 + padding;
        float bottom = mKLineChart.getHeight() - mMarkerPaint.getStrokeWidth() / 2;
        mUtilRect.set(left,top,right,bottom);
        if (left < mContentRect.left){
            left = mContentRect.left;
            right = left + mUtilRect.width();
            mUtilRect.set(left,top,right,bottom);
        }

        if (right > mContentRect.right){
            right = mContentRect.right;
            left = right - mUtilRect.width();
            mUtilRect.set(left,top,right,bottom);
        }



        mMarkerPaint.setColor(mRectBgColor);
        mMarkerPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mUtilRect, mMarkerPaint);

        mMarkerPaint.setColor(mKLineChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        mMarkerPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mUtilRect, mMarkerPaint);

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setColor(mKLineChart.getXAxis().getTextColor());
        mMarkerPaint.setTextSize(mKLineChart.getXAxis().getTextSize());

        Paint.FontMetrics fontMetrics = mMarkerPaint.getFontMetrics();
        float textTop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float textBottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (mUtilRect.centerY() - textTop / 2 - textBottom / 2);//基线中间点的y轴计算公式

        canvas.drawText(xLabel, mUtilRect.centerX() - mMarkerPaint.measureText(xLabel) / 2, baseLineY, mMarkerPaint);
    }

    private boolean needDrawYMarker(Canvas canvas) {
        return mKLineChart.getTouchY() <= mContentRect.bottom && mKLineChart.getTouchY() >= mContentRect.top;
    }

}
