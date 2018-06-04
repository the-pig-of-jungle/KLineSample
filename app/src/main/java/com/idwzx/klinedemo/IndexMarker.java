package com.idwzx.klinedemo;

import android.graphics.Canvas;
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

public class IndexMarker implements IMarker {
    private Entry mEntry;
    private Highlight mHighlight;
    private IndexChart mIndexChart;
    private ViewPortHandler mViewPortHandler;
    private RectF mUtilRect;
    private Paint mMarkerPaint;

    private RectF mContentRect;


    private int mRectBgColor;

    private float mRectBorderWidth;


    public IndexMarker(IndexChart indexChart, @ColorInt int rectBgColor) {
        mIndexChart = indexChart;
        mUtilRect = new RectF();
        mMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mViewPortHandler = mIndexChart.getViewPortHandler();
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
        MPPointD mpPointD = mIndexChart.getValuesByTouchPoint(mIndexChart.getTouchX(), mIndexChart.getTouchY(), YAxis.AxisDependency.LEFT);
        String yLabel = "";
        float[] entries = mIndexChart.getAxisLeft().mEntries;

//        EasyYAxisValueFormatter formatter = (EasyYAxisValueFormatter) mIndexChart.getAxisLeft().getValueFormatter();

//        yLabel = formatter.getEasyFormattedValue((float) mpPointD.y, mIndexChart.getAxisLeft());

//        yLabel = "";
//
//        if (mIndexChart.getTouchX() >= mIndexChart.getWidth() * 0.5f) {
//            mUtilRect.set(mIndexChart.getViewPortHandler().contentLeft(), mIndexChart.getTouchY() - (mIndexChart.getXAxis().getTextSize()) / 2 - padding, mIndexChart.getViewPortHandler().contentLeft() + mMarkerPaint.measureText(formatter.getEasyFormattedValue((float) mIndexChart.getValuesByTouchPoint(mIndexChart.getTouchX(), mIndexChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mIndexChart.getAxisLeft()))
//                            + padding * 2,
//                    mIndexChart.getTouchY() + (mIndexChart.getXAxis().getTextSize()) / 2 + padding);
//        } else {
//            mUtilRect.set(mIndexChart.getViewPortHandler().contentRight() - mMarkerPaint.measureText(formatter.getEasyFormattedValue((float) mIndexChart.getValuesByTouchPoint(mIndexChart.getTouchX(), mIndexChart.getTouchY(), YAxis.AxisDependency.LEFT).y, mIndexChart.getAxisLeft())) - padding * 2, mIndexChart.getTouchY() - (mIndexChart.getXAxis().getTextSize()) / 2 - padding, mIndexChart.getViewPortHandler().contentRight(),
//                    mIndexChart.getTouchY() + (mIndexChart.getXAxis().getTextSize()) / 2 + padding);
//        }

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setStrokeWidth(mIndexChart.getData().getCandleData().getDataSetByIndex(0).getHighlightLineWidth());
        mMarkerPaint.setColor(mIndexChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        canvas.drawLine(mContentRect.left, mIndexChart.getTouchY(), mContentRect.right, mIndexChart.getTouchY(), mMarkerPaint);


        mMarkerPaint.setColor(mRectBgColor);

        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.STROKE);
        mMarkerPaint.setStrokeWidth(mRectBorderWidth);
        mMarkerPaint.setColor(mIndexChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        canvas.drawRect(mUtilRect, mMarkerPaint);


        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setColor(mIndexChart.getAxisLeft().getTextColor());
        mMarkerPaint.setTextSize(mIndexChart.getAxisLeft().getTextSize());

        Paint.FontMetrics fontMetrics = mMarkerPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (mUtilRect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(yLabel,
                mUtilRect.centerX() - mMarkerPaint.measureText(yLabel) / 2, baseLineY, mMarkerPaint);
    }

    private void drawXMarker(Canvas canvas, float padding) {
        String xLabel = mIndexChart.getXAxis().getValueFormatter().getFormattedValue(mEntry.getX(), mIndexChart.getXAxis());

        float left = mIndexChart.getTouchX() - mMarkerPaint.measureText(xLabel) / 2 - padding;
        float top = mContentRect.bottom;
        float right = mIndexChart.getTouchX() + mMarkerPaint.measureText(xLabel) / 2 + padding;
        float bottom = mIndexChart.getHeight() - mMarkerPaint.getStrokeWidth() / 2;
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

        mMarkerPaint.setColor(mIndexChart.getData().getCandleData().getDataSetByIndex(0).getHighLightColor());
        mMarkerPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mUtilRect, mMarkerPaint);

        mMarkerPaint.setStyle(Paint.Style.FILL);
        mMarkerPaint.setColor(mIndexChart.getXAxis().getTextColor());
        mMarkerPaint.setTextSize(mIndexChart.getXAxis().getTextSize());

        Paint.FontMetrics fontMetrics = mMarkerPaint.getFontMetrics();
        float textTop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float textBottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (mUtilRect.centerY() - textTop / 2 - textBottom / 2);//基线中间点的y轴计算公式

        canvas.drawText(xLabel, mUtilRect.centerX() - mMarkerPaint.measureText(xLabel) / 2, baseLineY, mMarkerPaint);
    }

    private boolean needDrawYMarker(Canvas canvas) {
        return mIndexChart.getTouchY() <= mContentRect.bottom && mIndexChart.getTouchY() >= mContentRect.top;
    }

}
