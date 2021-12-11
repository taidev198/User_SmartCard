package com.example.user.ui.statistical;

import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.user.R;
import com.example.user.data.model.Staff;
import com.example.user.ui.base.BaseFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


public class StatisticalFragment extends BaseFragment {
    private static Staff mUser;
    private StatisticalPresenter mPresenter;
    BarChart barChart;
    int xy = 0, yx = 0;
    public static Fragment newInstance(Staff user) {
        mUser = user;
        return new StatisticalFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistical;
    }

    @Override
    protected void initComponents(View view) {
        mPresenter = new StatisticalPresenter();
        barChart = view.findViewById(R.id.bar_chart);
    }

    @Override
    protected void initData() {

        BarDataSet dataSet = new BarDataSet(mPresenter.getDatToChart(mUser), "data");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawValues(true);
        BarData data = new BarData(dataSet);
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value) + "";
            }
        });
//        data.setBarWidth(0.2f);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.setMaxVisibleValueCount(12);
        barChart.animateY(2000);
        barChart.getXAxis().setLabelCount(12);
        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getAxisLeft().setLabelCount(mPresenter.countColumnData , false);
        barChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
//                if (((int) value) == 0)
//                    return "";
                return ((int) value) + "";
            }
        });
        barChart.getAxisRight().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
//                if (((int) value) == 0)
//                    return "";
//
//                if (yx != (int) value){
//                    yx = (int) value;
//                    return ((int) value) + "";
//                }else {
//                    return "";
//                }
                return "";
            }
        });
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value)+"";
            }
        });
    }
}