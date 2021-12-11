package com.example.user.ui.login;

import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.response.StaffResponse;

public interface LoginContract {
    interface View {
        void onLoginSuccess(StaffResponse staff);

        void onLoginFailure(String error);

        void onGetDepartmentSuccess(DepartmentResponse departmentResponse);
    }

    interface Presenter {
        void doLogin(Staff staff);
        void getDepartment();
    }

}
