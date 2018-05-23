package com.idwzx.klinedemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCombinedChart = findViewById(R.id.combine_chart);

        mCombinedChart.setScaleYEnabled(false);

        mCombinedChart.setDrawBorders(true);

        mCombinedChart.setBorderWidth(1);


        Description description = new Description();
        description.setText("");
        mCombinedChart.setDescription(description);

        mCombinedChart.setMinOffset(6);







        XAxis xAxis = mCombinedChart.getXAxis();

        xAxis.setDrawLabels(true);

        xAxis.setDrawGridLines(false);

        xAxis.setDrawAxisLine(false);

        xAxis.setAxisMinimum(-1);




        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAvoidFirstLastClipping(true);


        YAxis left = mCombinedChart.getAxisLeft();

        left.setDrawGridLines(true);

        left.setDrawAxisLine(false);

        left.setDrawZeroLine(false);
        left.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        left.setDrawLabels(true);

        Legend legend = mCombinedChart.getLegend();

        legend.setEnabled(false);

        left.enableGridDashedLine(16, 10, 0);

        left.setDrawZeroLine(false);


        left.setLabelCount(4, false);


        YAxis right = mCombinedChart.getAxisRight();

        right.setEnabled(false);

        right.setDrawZeroLine(false);


        List<KLineEntity> lineEntities = GsonUtil.sGson.fromJson(DataTest.DATA_STR, new TypeToken<List<KLineEntity>>() {
        }.getType());
        List<CandleEntry> candleEntryList = new ArrayList<>();

        for (int i = 0; i < lineEntities.size(); i++) {
            KLineEntity kLineEntity = lineEntities.get(i);
            CandleEntry candleEntry = new CandleEntry(i, kLineEntity.getHighPrice(), kLineEntity.getLowPrice(), kLineEntity.getOpenPrice(), kLineEntity.getClosePrice());
            candleEntryList.add(candleEntry);
        }


        CandleDataSet candleDataSet = new CandleDataSet(candleEntryList, "");

        candleDataSet.setShowCandleBar(true);
        candleDataSet.setShadowColor(Color.parseColor("#ff00ff"));

        candleDataSet.setDrawHorizontalHighlightIndicator(false);

        candleDataSet.setHighLightColor(Color.parseColor("#323232"));

        candleDataSet.setDecreasingColor(Color.parseColor("#00ff00"));
        candleDataSet.setIncreasingColor(Color.parseColor("#ff0000"));



        candleDataSet.setShadowColorSameAsCandle(true);



        CandleData candleData = new CandleData(candleDataSet);



        CombinedData combinedData = new CombinedData();
        combinedData.setData(candleData);


        mCombinedChart.getViewPortHandler().setMaximumScaleX(2);
        mCombinedChart.setData(combinedData);


        mCombinedChart.animateXY(1000, 1000);


    }
}
