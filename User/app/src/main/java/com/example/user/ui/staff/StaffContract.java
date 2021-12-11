package com.example.user.ui.staff;

import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;

import java.util.List;

public interface StaffContract {

    interface Presenter {
        void getStaffs(Staff staff);
        void getDepartment();

    }

    interface View {
        void showStaffs(Staff staffs);

        void showError();

        void onGetDepartmentSuccess(DepartmentResponse departmentResponse);

    }

}
