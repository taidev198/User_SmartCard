package com.example.user.ui.statistical;

import com.example.user.data.model.Staff;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class StatisticalPresenter {
    public int countColumnData = 2;
    public ArrayList<BarEntry> getDatToChart(Staff staff) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            int count = 0;
            for (Date date : staff.getLateDate()) {
                if ((date.getMonth()+1)  == i) {
                    count++;
                }
            }
            entries.add(new BarEntry(i, count));
            if (count != 0) {
                countColumnData ++;
            }

        }
        return entries;
    }

}
