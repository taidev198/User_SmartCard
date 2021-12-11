package com.example.user.ui.statistical;

import com.example.user.data.model.Staff;

import java.util.List;

public interface StatisticalContract {
    interface Presenter {
        void getStaffs(int id);
    }

    interface View {
        void showStaffs(List<Staff> staffs);

        void showError(Exception e);
    }
}
