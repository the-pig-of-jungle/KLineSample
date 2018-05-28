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
        return 0;
    }
}
