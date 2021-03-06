package com.idwzx.klinedemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
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
    private IndexChart mIndexChart;

    private List<KLineEntity> mKLineEntities;

    private List<CandleEntry> mCandleEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKLineChart = findViewById(R.id.combine_chart);
        mIndexChart = findViewById(R.id.index_chart);


        ChartUtils.processKLineYAxisLeft(mKLineChart);


        ChartUtils.processKlineYAxisRight(mKLineChart);







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



        candleDataSet.setHighLightColor(Color.parseColor("#323232"));

        candleDataSet.setDecreasingColor(Color.parseColor("#00ff00"));
        candleDataSet.setIncreasingColor(Color.parseColor("#ff0000"));



        candleDataSet.setShadowColorSameAsCandle(true);


        final CandleData candleData = new CandleData(candleDataSet);


        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);


        combinedData.setData(lineData);


        final ViewPortHandler handler = mKLineChart.getViewPortHandler();




        mKLineChart.setData(combinedData);

        mKLineChart.setOnChartGestureListener(new ChartGestureListener(mKLineChart));



        mVolumeChart = findViewById(R.id.volume_chart);




        mKLineChart.setDoubleTapToZoomEnabled(false);


        mKLineChart.setDrawMarkers(true);

        mKLineChart.setMarker(new KLineMarker(mKLineChart, ContextCompat.getColor(getApplicationContext(), R.color.marker_bg_color)));


        mKLineChart.moveViewTo(candleEntryList.size() - 1, 0, YAxis.AxisDependency.LEFT);
        mKLineChart.animateXY(1000, 1000);


        mVolumeChart = findViewById(R.id.volume_chart);






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

        final List<BarEntry> riseEntries = new ArrayList<>();
        final List<BarEntry> fallEntries = new ArrayList<>();

        for (int i = 0; i < mCandleEntries.size(); i++) {

            BarEntry barEntry = new BarEntry(i, 100);
            if (mCandleEntries.get(i).getOpen() <= mCandleEntries.get(i).getClose()) {
                riseEntries.add(barEntry);
            } else {
                fallEntries.add(barEntry);
            }

        }
        BarDataSet riseDataSet = new BarDataSet(riseEntries, "");

        riseDataSet.setHighlightEnabled(true);
        riseDataSet.setBarBorderColor(Color.RED);

        riseDataSet.setHighLightAlpha(0);


        BarDataSet fallDataSet = new BarDataSet(fallEntries, "");

        fallDataSet.setHighlightEnabled(true);

        riseDataSet.setColor(Color.TRANSPARENT);
        fallDataSet.setColor(Color.GREEN);


        fallDataSet.setBarBorderColor(Color.GREEN);
        fallDataSet.setHighLightAlpha(0);


        BarData barData = new BarData();
        barData.addDataSet(riseDataSet);
        barData.addDataSet(fallDataSet);
        barData.setDrawValues(false);




        CombinedData volumeChartData = new CombinedData();
        volumeChartData.setData(barData);
        mVolumeChart.setData(volumeChartData);
        mVolumeChart.setLeftLabel("成交量");
        mVolumeChart.setOnChartGestureListener(new ChartGestureListener(mVolumeChart));
        mKLineChart.setUnionVolumeOrAmountChart(mVolumeChart);
        mKLineChart.setUnionIndexChart(mIndexChart);



        mVolumeChart.setLeftLabelSize(Utils.convertDpToPixel(14f));
        mVolumeChart.setLeftLabelColor(Color.parseColor("#323232"));
        mVolumeChart.moveViewTo(mCandleEntries.size() - 1, 0, YAxis.AxisDependency.LEFT);
        mVolumeChart.setMarker(new VolumeMarker(mVolumeChart, ContextCompat.getColor(getApplicationContext(), R.color.marker_bg_color),
                Utils.convertDpToPixel(1), Color.parseColor("#323232")));
        volumeXAxis.setLabelCount(2, true);
        volumeXAxis.setAxisMinimum(-0.5f);
        volumeXAxis.setDrawLabels(false);

        volumeXAxis.setAxisMaximum(mCandleEntries.size() - 0.5f);

        mVolumeChart.setUnionKLineChart(mKLineChart);
        mVolumeChart.setUnionIndexChart(mIndexChart);

        mVolumeChart.animateXY(1000, 1000);

        mIndexChart.setScaleYEnabled(false);

        mIndexChart.getXAxis().setAxisMinimum(-0.5f);

        mIndexChart.getXAxis().setAxisMaximum(mCandleEntries.size() - 0.5f);
        mIndexChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        mIndexChart.getAxisRight().setEnabled(false);


        mIndexChart.setData(combinedData);



        mIndexChart.setUnionKLineChart(mKLineChart);
        mIndexChart.setUnionVolumeOrAmountChart(mVolumeChart);
        mIndexChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                mIndexChart.highlightValue(null);
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                mIndexChart.highlightValue(null);
                mIndexChart.setDragEnabled(true);
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
                mIndexChart.highlightValue(mIndexChart.getHighlightByTouchPoint(me.getX(), me.getY()));
                mIndexChart.setDragEnabled(false);

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {

            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                mIndexChart.setDragEnabled(true);
                mIndexChart.highlightValue(null);
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                mIndexChart.highlightValue(null);
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                mIndexChart.setDragEnabled(true);
                mIndexChart.highlightValue(null);
            }
        });

        mIndexChart.setMarker(new IndexMarker(mIndexChart,Color.RED));
        mIndexChart.moveViewTo(mCandleEntries.size() - 1,0, YAxis.AxisDependency.LEFT);

        mIndexChart.animateXY(1000,1000);




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
