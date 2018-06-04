package com.idwzx.klinedemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;

import java.text.DecimalFormat;

public class ChartUtils {
    public static void processChart(BaseCombinedChart chart) {
//        chart.setScaleEnabled(true);
//        chart.setScaleYEnabled(false);
//        chart.setAutoScaleMinMaxEnabled(true);
//        chart.setDrawBorders(true);
//        chart.setBorderWidth(1);
//        chart.setBorderColor(chart.getXAxis().getGridColor());
//        Description description = new Description();
//        description.setText("");
//        chart.setDescription(description);
//
//        chart.getLegend().setEnabled(false);
//
//        chart.setHighlightPerTapEnabled(false);
//        chart.setHighlightPerDragEnabled(true);
//        chart.setDrawMarkers(true);
//        chart.setDoubleTapToZoomEnabled(false);
//
//        chart.getViewPortHandler().setMaximumScaleX(9f);
//        chart.getViewPortHandler().getMatrixTouch().postScale(4f, 1f);
    }

    public static void processKLineYAxisLeft(KLineChart kLineChart) {
        YAxis left = kLineChart.getAxisLeft();

        left.setDrawGridLines(true);

        left.setDrawAxisLine(false);


        left.setValueFormatter(new EasyYAxisValueFormatter(kLineChart) {
            private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

            @Override
            public String getEasyFormattedValue(float value, AxisBase axis) {
                return mDecimalFormat.format(value);
            }
        });

        left.setDrawZeroLine(false);
        left.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        left.setDrawLabels(true);

        Legend legend = kLineChart.getLegend();

        legend.setEnabled(false);

        left.enableGridDashedLine(16, 10, 0);

        left.setDrawZeroLine(false);


        left.setLabelCount(4, true);
    }

    public static void processKlineYAxisRight(KLineChart kLineChart) {
        YAxis right = kLineChart.getAxisRight();

        right.setEnabled(false);

        right.setDrawZeroLine(false);
    }
}
