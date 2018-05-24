package com.idwzx.klinedemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.CombinedHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.reflect.TypeToken;
import com.idwzx.klinedemo.bean.DataParse;
import com.idwzx.klinedemo.bean.DataTest;
import com.idwzx.klinedemo.bean.KLineEntity;
import com.idwzx.klinedemo.common.ConstantTest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CombinedChart mCombinedChart;

    private TextView mStartDate;
    private TextView mEndDate;

    private List<KLineEntity> mKLineEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCombinedChart = findViewById(R.id.combine_chart);

        mStartDate = findViewById(R.id.start_date);
        mEndDate = findViewById(R.id.end_date);

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

        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value == axis.getAxisMaximum() || value == axis.getAxisMinimum()){
                    return "";
                }else {
                    return value + "";
                }
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

        initXAxis(lineEntities);
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


        CandleDataSet candleDataSet = new CandleDataSet(candleEntryList, "");

        candleDataSet.setShowCandleBar(true);
        candleDataSet.setShadowColor(Color.parseColor("#ff00ff"));

        candleDataSet.setDrawHorizontalHighlightIndicator(false);


        candleDataSet.setDrawValues(false);


        candleDataSet.setHighLightColor(Color.parseColor("#323232"));

        candleDataSet.setDecreasingColor(Color.parseColor("#00ff00"));
        candleDataSet.setIncreasingColor(Color.parseColor("#ff0000"));


        candleDataSet.setShadowColorSameAsCandle(true);


        CandleData candleData = new CandleData(candleDataSet);


        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);


        combinedData.setData(lineData);


        final ViewPortHandler handler = mCombinedChart.getViewPortHandler();

        handler.setMaximumScaleX(9f);


        mCombinedChart.setData(combinedData);





        mCombinedChart.setOnDrawListener(new OnDrawListener() {
            @Override
            public void onEntryAdded(Entry entry) {

            }

            @Override
            public void onEntryMoved(Entry entry) {

            }

            @Override
            public void onDrawFinished(DataSet<?> dataSet) {

            }
        });


        mCombinedChart.setHighlightPerTapEnabled(false);
        mCombinedChart.setHighlightPerDragEnabled(true);

        mCombinedChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                mCombinedChart.setDragEnabled(true);
                Highlight highlight = mCombinedChart.getHighlightByTouchPoint(0, 0);
                int index = (int) highlight.getX();

                Log.d("main",index + "");

                mStartDate.setText(mKLineEntities.get(index % mKLineEntities.size()).getDate());
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
                Highlight highlight = mCombinedChart.getHighlightByTouchPoint(me.getX(), me.getY());
                mCombinedChart.highlightValue(highlight);
                mCombinedChart.setDragEnabled(false);
            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                Highlight[] highlights = mCombinedChart.getHighlighted();
                if (highlights == null) {
                    Highlight highlight = mCombinedChart.getHighlightByTouchPoint(me.getX(), me.getY());
                    mCombinedChart.highlightValue(highlight);
                } else {
                    mCombinedChart.highlightValue(null);
                }
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                me1.toString();
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                mCombinedChart.highlightValue(null);

            }
        });


        mCombinedChart.setDoubleTapToZoomEnabled(false);


        mCombinedChart.animateXY(1000, 1000);


    }

    private void initXAxis(final List<KLineEntity> lineEntities) {
        XAxis xAxis = mCombinedChart.getXAxis();

        xAxis.setDrawLabels(true);

        xAxis.setDrawGridLines(false);


        xAxis.setDrawAxisLine(false);

        xAxis.setDrawLabels(true);

        xAxis.setLabelCount(4);
        xAxis.setAxisMinimum(-0.5f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAvoidFirstLastClipping(true);

        xAxis.setAxisMaximum(mKLineEntities.size() * 6 - 0.5f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }


        });
    }


}
