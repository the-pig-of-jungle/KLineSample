package com.idwzx.klinedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.reflect.TypeToken;
import com.idwzx.klinedemo.bean.DataTest;
import com.idwzx.klinedemo.bean.KLineEntity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private KLineChart mKLineChart;
    private VolumeOrAmountChart mVolumeChart;

    private List<KLineEntity> mKLineEntities;

    private List<CandleEntry> mCandleEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKLineChart = findViewById(R.id.combine_chart);


        mKLineChart.setScaleYEnabled(false);


        mKLineChart.setAutoScaleMinMaxEnabled(true);

        mKLineChart.setBorderWidth(1);

        mKLineChart.setDrawBorders(true);


        Description description = new Description();
        description.setText("");
        mKLineChart.setDescription(description);


        YAxis left = mKLineChart.getAxisLeft();


        left.setDrawGridLines(true);

        left.setDrawAxisLine(false);


        left.setValueFormatter(new EasyYAxisValueFormatter(mKLineChart) {
            private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

            @Override
            public String getEasyFormattedValue(float value, AxisBase axis) {
                return mDecimalFormat.format(value);
            }
        });

        left.setDrawZeroLine(false);
        left.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        left.setDrawLabels(true);

        Legend legend = mKLineChart.getLegend();

        legend.setEnabled(false);

        left.enableGridDashedLine(16, 10, 0);

        left.setDrawZeroLine(false);


        left.setLabelCount(4, true);


        YAxis right = mKLineChart.getAxisRight();


        right.setEnabled(false);

        right.setDrawZeroLine(false);


        List<KLineEntity> lineEntities = GsonUtil.sGson.fromJson(DataTest.DATA_STR, new TypeToken<List<KLineEntity>>() {
        }.getType());

        mKLineEntities = lineEntities;


        List<CandleEntry> candleEntryList = new ArrayList<>();

        List<Entry> lineEntry = new ArrayList<>();


        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i);
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }

        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i + lineEntities.size(), kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i + lineEntities.size());
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }


        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i + lineEntities.size() * 2, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i + lineEntities.size() * 2);
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }


        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i + lineEntities.size() * 3, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i + lineEntities.size() * 3);
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }


        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i + lineEntities.size() * 4, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i + lineEntities.size() * 4);
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }

        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i + lineEntities.size() * 5, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
            if (i < 5) {
                continue;
            }
            Entry entry = new Entry();
            entry.setX(i + lineEntities.size() * 5);
            entry.setY(kLineEntity.getMA5Price());
            lineEntry.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntry, "");

        lineDataSet.setDrawCircleHole(false);

        lineDataSet.setDrawCircles(false);

        lineDataSet.setHighlightEnabled(false);

        lineDataSet.setColor(Color.parseColor("#0000ff"));

        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);

        candleEntryList.get(0).setHigh(13);


        mCandleEntries = candleEntryList;
        CandleDataSet candleDataSet = new CandleDataSet(candleEntryList, "");
        initXAxis(lineEntities);
        candleDataSet.setShowCandleBar(true);
        candleDataSet.setShadowColor(Color.parseColor("#ff00ff"));


        candleDataSet.setDrawValues(false);


        candleDataSet.setHighlightLineWidth(1f);
        candleDataSet.setDrawHorizontalHighlightIndicator(false);

        mKLineChart.setHighlightFullBarEnabled(false);


        candleDataSet.setHighLightColor(Color.parseColor("#323232"));

        candleDataSet.setDecreasingColor(Color.parseColor("#00ff00"));
        candleDataSet.setIncreasingColor(Color.parseColor("#ff0000"));


        candleDataSet.setShadowColorSameAsCandle(true);


        final CandleData candleData = new CandleData(candleDataSet);


        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);


        combinedData.setData(lineData);


        final ViewPortHandler handler = mKLineChart.getViewPortHandler();

        handler.setMaximumScaleX(9f);
        handler.getMatrixTouch().postScale(4f, 1f);


        mKLineChart.setData(combinedData);
        mKLineChart.setOnChartValueSelectedListener(new ChartValueSelectedListener(mKLineChart,
                new BaseCombinedChart[]{mVolumeChart}));


        mKLineChart.setHighlightPerTapEnabled(false);
        mKLineChart.setHighlightPerDragEnabled(true);

        mVolumeChart = findViewById(R.id.volume_chart);


        mKLineChart.setOnChartGestureListener(new UnionChartGestureListener(mKLineChart, new BaseCombinedChart[]{mVolumeChart}));

        mKLineChart.setOnChartValueSelectedListener(new ChartValueSelectedListener(mKLineChart,
                new BaseCombinedChart[]{mVolumeChart}));


        mKLineChart.setDoubleTapToZoomEnabled(false);


        mKLineChart.setDrawMarkers(true);

        mKLineChart.setMarker(new KLineMarker(mKLineChart, ContextCompat.getColor(getApplicationContext(), R.color.marker_bg_color)));


        mKLineChart.moveViewTo(candleEntryList.size() - 1, 0, YAxis.AxisDependency.LEFT);
        mKLineChart.animateXY(1000, 1000);


        mVolumeChart = findViewById(R.id.volume_chart);

        mVolumeChart.setScaleEnabled(true);
        mVolumeChart.setScaleYEnabled(false);
        mVolumeChart.setAutoScaleMinMaxEnabled(true);
        mVolumeChart.setDrawBorders(true);
        mVolumeChart.setBorderWidth(1);
        mVolumeChart.setDrawMarkers(true);
        mVolumeChart.setHighlightPerDragEnabled(true);
        mVolumeChart.setHighlightPerTapEnabled(false);
        mVolumeChart.setDoubleTapToZoomEnabled(false);

        mVolumeChart.getViewPortHandler().setMaximumScaleX(9f);
        mVolumeChart.getViewPortHandler().getMatrixTouch().postScale(4f, 1f);
        Description volumeDescription = new Description();
        volumeDescription.setText("");
        mVolumeChart.setDescription(volumeDescription);
        mVolumeChart.getLegend().setEnabled(false);
        YAxis volumeLeft = mVolumeChart.getAxisLeft();
        volumeLeft.setAxisMinimum(0);
        volumeLeft.setLabelCount(3, true);

        volumeLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        volumeLeft.setDrawAxisLine(false);
        volumeLeft.setDrawGridLines(true);
        volumeLeft.setDrawLabels(false);
        volumeLeft.enableGridDashedLine(2, 2, 0);

        mVolumeChart.getAxisRight().setEnabled(false);


        XAxis volumeXAxis = mVolumeChart.getXAxis();


        volumeXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        volumeXAxis.setDrawLabels(true);

        volumeXAxis.setAxisMinimum(-0.5f);
        volumeXAxis.setAxisMaximum(mCandleEntries.size() - 0.5f);

        volumeXAxis.setDrawAxisLine(false);


        volumeXAxis.setDrawGridLines(false);


        volumeXAxis.setXOffset(1);
        final List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < mCandleEntries.size(); i++) {
            BarEntry barEntry = new BarEntry(i, 100);

            barEntries.add(barEntry);
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setHighlightEnabled(true);
        barDataSet.setBarBorderColor(Color.BLACK);
        barDataSet.setBarBorderWidth(0.3f);






        barDataSet.setHighLightAlpha(0);

        List<Integer> colors = new ArrayList<>(mCandleEntries.size());

        for (int i = 0; i < mCandleEntries.size(); i++) {
            colors.add(mCandleEntries.get(i).getOpen() <= mCandleEntries.get(i).getClose() ? Color.RED : Color.GREEN);
        }
        barDataSet.setColors(colors);
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);


        CombinedData volumeChartData = new CombinedData();
        volumeChartData.setData(barData);
        mVolumeChart.setData(volumeChartData);
        mVolumeChart.setLeftLabel("成交量");
        mVolumeChart.setOnChartGestureListener(new UnionChartGestureListener(mVolumeChart, new BaseCombinedChart[]{mKLineChart}));

        mVolumeChart.setOnChartValueSelectedListener(new ChartValueSelectedListener(mVolumeChart,
                new BaseCombinedChart[]{mKLineChart}));
        mKLineChart.addUnionChart(mVolumeChart);

        mVolumeChart.setLeftLabelSize(Utils.convertDpToPixel(14f));
        mVolumeChart.setLeftLabelColor(Color.parseColor("#323232"));
        mVolumeChart.moveViewTo(mCandleEntries.size() - 1, 0, YAxis.AxisDependency.LEFT);
        mVolumeChart.setMarker(new VolumeMarker(mVolumeChart, ContextCompat.getColor(getApplicationContext(), R.color.marker_bg_color),
                Utils.convertDpToPixel(1), Color.parseColor("#323232")));
        volumeXAxis.setLabelCount(2, true);
        volumeXAxis.setAxisMinimum(-0.5f);
        volumeXAxis.setDrawLabels(false);

        volumeXAxis.setAxisMaximum(mCandleEntries.size() - 0.5f);

        mVolumeChart.addUnionChart(mKLineChart);
        mVolumeChart.animateXY(1000, 1000);
    }

    private void initXAxis(final List<KLineEntity> lineEntities) {
        final XAxis xAxis = mKLineChart.getXAxis();

        xAxis.setDrawLabels(true);

        xAxis.setDrawGridLines(false);


        xAxis.setDrawAxisLine(false);

        xAxis.setDrawLabels(false);

        xAxis.setLabelCount(2, true);

        xAxis.setAxisMinimum(-0.5f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAxisMaximum(mCandleEntries.size() - 0.5f);

        xAxis.setAvoidFirstLastClipping(true);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return mKLineEntities.get(((int) value) % mKLineEntities.size()).getDate();
            }
        });

    }


}
