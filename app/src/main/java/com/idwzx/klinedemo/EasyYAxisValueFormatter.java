package com.idwzx.klinedemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public abstract class EasyYAxisValueFormatter implements IAxisValueFormatter {
    private EasyCombinedChart mEasyCombinedChart;

    public EasyYAxisValueFormatter(EasyCombinedChart easyCombinedChart) {
        mEasyCombinedChart = easyCombinedChart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String str = getEasyFormattedValue(value,axis);
        if (value == axis.getAxisMinimum()){
            mEasyCombinedChart.setStartYLabel(str);
            return "";
        }

        if (value == axis.getAxisMaximum()){
            mEasyCombinedChart.setEndYLabel(str);
            return "";
        }
        return str;
    }

    public abstract String getEasyFormattedValue(float value, AxisBase axis);

}
