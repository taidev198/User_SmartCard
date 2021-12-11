package com.example.user.ui.home;

import com.example.user.data.model.Department;
import com.example.user.data.model.Staff;

import java.util.List;

public interface HomeContract {

    interface Presenter {
        void getStaffs(Staff staff);
    }

    interface View {
        void showStaffs(Staff staffs);

        void showError();
    }

}
