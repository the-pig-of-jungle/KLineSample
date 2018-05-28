package com.idwzx.klinedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.reflect.TypeToken;
import com.idwzx.klinedemo.bean.DataTest;
import com.idwzx.klinedemo.bean.KLineEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private KLineChart mCombinedChart;
    private VolumeOrAmountChart mVolumeChart;

    private TextView mStartDate;
    private TextView mEndDate;

    private List<KLineEntity> mKLineEntities;

    private List<CandleEntry> mCandleEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCombinedChart = findViewById(R.id.combine_chart);


        mCombinedChart.setScaleYEnabled(false);


        mCombinedChart.setAutoScaleMinMaxEnabled(true);

        mCombinedChart.setBorderWidth(1);

        mCombinedChart.setDrawBorders(true);


        Description description = new Description();
        description.setText("");
        mCombinedChart.setDescription(description);


        YAxis left = mCombinedChart.getAxisLeft();


        left.setDrawGridLines(true);

        left.setDrawAxisLine(false);


        left.setValueFormatter(new EasyYAxisValueFormatter(mCombinedChart) {
            @Override
            public String getEasyFormattedValue(float value, AxisBase axis) {
                return value + "";
            }
        });

        left.setDrawZeroLine(false);
        left.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        left.setDrawLabels(true);

        Legend legend = mCombinedChart.getLegend();

        legend.setEnabled(false);

        left.enableGridDashedLine(16, 10, 0);

        left.setDrawZeroLine(false);


        left.setLabelCount(4, true);


        YAxis right = mCombinedChart.getAxisRight();


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

        mCombinedChart.setHighlightFullBarEnabled(false);


        candleDataSet.setHighLightColor(Color.parseColor("#323232"));

        candleDataSet.setDecreasingColor(Color.parseColor("#00ff00"));
        candleDataSet.setIncreasingColor(Color.parseColor("#ff0000"));


        candleDataSet.setShadowColorSameAsCandle(true);


        final CandleData candleData = new CandleData(candleDataSet);


        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);


        combinedData.setData(lineData);


        final ViewPortHandler handler = mCombinedChart.getViewPortHandler();

        handler.setMaximumScaleX(9f);
        handler.getMatrixTouch().postScale(4f, 1f);


        mCombinedChart.setData(combinedData);
        mCombinedChart.setOnChartValueSelectedListener(new ChartValueSelectedListener(mCombinedChart,
                new BaseCombinedChart[]{mVolumeChart}));


        mCombinedChart.setHighlightPerTapEnabled(false);
        mCombinedChart.setHighlightPerDragEnabled(true);

        mVolumeChart = findViewById(R.id.volume_chart);




        mCombinedChart.setOnChartGestureListener(new UnionChartGestureListener(mCombinedChart,new BaseCombinedChart[]{mVolumeChart}));

        mCombinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mVolumeChart.highlightValue(mVolumeChart.getHighlightByTouchPoint(mCombinedChart.getTouchX(),0));
            }

            @Override
            public void onNothingSelected() {
                mVolumeChart.highlightValue(null);
            }
        });



        mCombinedChart.setDoubleTapToZoomEnabled(false);


        mCombinedChart.setDrawMarkers(true);

        mCombinedChart.setMarker(new IMarker() {

            Entry mEntry;

            @Override
            public MPPointF getOffset() {


                MPPointF mpPointF = new MPPointF(-(mCombinedChart.getWidth() / 2), -(mCombinedChart.getHeight() / 2));
                return mpPointF;
            }

            @Override
            public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {

                return getOffset();
            }

            @Override
            public void refreshContent(Entry e, Highlight highlight) {
                mEntry = e;
            }

            @Override
            public void draw(Canvas canvas, float posX, float posY) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setTextSize(mCombinedChart.getAxisLeft().getTextSize());
                paint.setStrokeWidth(mCombinedChart.getData().getCandleData().getDataSetByIndex(0).getHighlightLineWidth());
                if (mCombinedChart.getTouchY() <= mCombinedChart.getViewPortHandler().contentBottom()) {
                    canvas.drawLine(mCombinedChart.getViewPortHandler().contentLeft(), mCombinedChart.getTouchY(), mCombinedChart.getViewPortHandler().contentRight(), mCombinedChart.getTouchY(), paint);
                    canvas.drawText(mCombinedChart.getValuesByTouchPoint(mCombinedChart.getTouchX(), mCombinedChart.getTouchY(), YAxis.AxisDependency.LEFT).y + "",
                            mCombinedChart.getViewPortHandler().contentLeft(), mCombinedChart.getTouchY(), paint);
                }

            }
        });


        mCombinedChart.moveViewTo(candleEntryList.size() -1,0, YAxis.AxisDependency.LEFT);
        mCombinedChart.animateXY(1000, 1000);


        mVolumeChart = findViewById(R.id.volume_chart);

        mVolumeChart.setScaleEnabled(true);
        mVolumeChart.setScaleYEnabled(false);
        mVolumeChart.setAutoScaleMinMaxEnabled(true);
        mVolumeChart.setDrawBorders(true);
        mVolumeChart.setBorderWidth(1);
        mVolumeChart.setDrawMarkers(true);
        mVolumeChart.setHighlightPerDragEnabled(true);
        mVolumeChart.setHighlightPerTapEnabled(true);
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
        barDataSet.setHighLightAlpha(0);


        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);









        CombinedData volumeChartData = new CombinedData();
        volumeChartData.setData(barData);
        mVolumeChart.setData(volumeChartData);
        mVolumeChart.setLeftLabel("成交量");
        mVolumeChart.setOnChartGestureListener(new UnionChartGestureListener(mVolumeChart,new BaseCombinedChart[]{mCombinedChart}));

        mVolumeChart.setOnChartValueSelectedListener(new ChartValueSelectedListener(mVolumeChart,
                new BaseCombinedChart[]{mCombinedChart}));
        mCombinedChart.addUnionChart(mVolumeChart);

        mVolumeChart.setLeftLabelSize(Utils.convertDpToPixel(14f));
        mVolumeChart.setLeftLabelColor(Color.parseColor("#323232"));
        mVolumeChart.setMarker(new IMarker() {
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

            }

            @Override
            public void draw(Canvas canvas, float posX, float posY) {

                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

                paint.setTextSize(mVolumeChart.getAxisLeft().getTextSize());

                paint.setStrokeWidth(mCombinedChart.getData().getCandleData().getDataSets().get(0).getHighlightLineWidth());

                canvas.drawLine(mVolumeChart.getViewPortHandler().contentLeft(), mVolumeChart.getTouchY(), mVolumeChart.getViewPortHandler().contentRight(), mVolumeChart.getTouchY(), paint);
                canvas.drawLine(posX, mVolumeChart.getViewPortHandler().contentTop(), posX, mVolumeChart.getViewPortHandler().contentBottom(), paint);
                canvas.drawText(mVolumeChart.getValuesByTouchPoint(mVolumeChart.getTouchX(), mVolumeChart.getTouchY(), YAxis.AxisDependency.LEFT).y + "",
                        mVolumeChart.getViewPortHandler().contentLeft(), mVolumeChart.getTouchY(), paint);

            }
        });
        volumeXAxis.setLabelCount(2,true);
        volumeXAxis.setAxisMinimum(-0.5f);
        volumeXAxis.setDrawLabels(false);

        volumeXAxis.setAxisMaximum(mCandleEntries.size() - 0.5f);

        mVolumeChart.addUnionChart(mCombinedChart);
        mVolumeChart.animateXY(1000,1000);
    }

    private void initXAxis(final List<KLineEntity> lineEntities) {
        final XAxis xAxis = mCombinedChart.getXAxis();

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
