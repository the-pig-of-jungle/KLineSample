package com.idwzx.klinedemo;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class ChartValueSelectedListener implements OnChartValueSelectedListener {

    private BaseCombinedChart mSrcChart;
    private BaseCombinedChart[] mUnionCharts;

    public ChartValueSelectedListener(BaseCombinedChart srcChart, BaseCombinedChart[] unionCharts) {
        mSrcChart = srcChart;
        mUnionCharts = unionCharts;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
            for (int index = 0;index < mUnionCharts.length;index++){
                Highlight highlight = mUnionCharts[index].getHighlightByTouchPoint(mSrcChart.getTouchX(),0);
                mUnionCharts[index].highlightValue(highlight);
            }
    }

    @Override
    public void onNothingSelected() {
            for (int index = 0;index < mUnionCharts.length;index++){
                mUnionCharts[index].highlightValue(null);
            }
    }
}
