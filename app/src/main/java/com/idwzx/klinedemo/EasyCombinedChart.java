package com.idwzx.klinedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.highlight.Highlight;

public class EasyCombinedChart extends CombinedChart {


    public EasyCombinedChart(Context context) {
        super(context);
    }

    public EasyCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(150);

        canvas.drawText(getAxisLeft().getAxisMaximum()+ "",0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,getExtraTopOffset(),getResources().getDisplayMetrics()),paint);
    }


    protected float getYValue(int i) {
        float y = (getHeight()) * i / 4;

        return y;
    }
}
