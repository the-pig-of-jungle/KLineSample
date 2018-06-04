package com.idwzx.klinedemo;

import android.content.Context;
import android.util.AttributeSet;

public class IndexChart extends BaseCombinedChart {
    public IndexChart(Context context) {
        super(context);
    }

    public IndexChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public float transferToUnionTouchY(BaseCombinedChart chart, float srcTouchY) {

        if (chart instanceof KLineChart){
            int totalHeight = getUnionChartHeight(mKLineChart) + getUnionChartHeight(mVolumeOrAmountChart);
            return srcTouchY + totalHeight;
        }

        if (chart instanceof VolumeOrAmountChart){
            return srcTouchY + getUnionChartHeight(mVolumeOrAmountChart);
        }

        return srcTouchY;

    }
}
