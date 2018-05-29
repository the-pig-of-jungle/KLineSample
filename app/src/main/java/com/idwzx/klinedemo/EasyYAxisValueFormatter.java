package com.idwzx.klinedemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public abstract class EasyYAxisValueFormatter implements IAxisValueFormatter {
    private KLineChart mKLineChart;

    public EasyYAxisValueFormatter(KLineChart KLineChart) {
        mKLineChart = KLineChart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String str = getEasyFormattedValue(value,axis);
        if (value <= axis.mEntries[0] || value >= axis.mEntries[axis.mEntries.length - 1]){
            return "";
        }

        return str;
    }

    public abstract String getEasyFormattedValue(float value, AxisBase axis);

}
