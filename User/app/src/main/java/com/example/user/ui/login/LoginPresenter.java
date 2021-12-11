package com.example.user.ui.login;

import com.example.user.data.department.DepartmentRemoteDataSource;
import com.example.user.data.department.DepartmentRepository;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.staff.StaffRemoteDataSource;
import com.example.user.data.staff.StaffRepository;
import com.example.user.data.api.ApiClient;
import com.example.user.data.api.UtilsApi;
import com.example.user.data.model.Staff;
import com.example.user.data.response.StaffResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private ApiClient apiClient;
    private StaffRepository mStaffRepo;
    private LoginContract.View mView;
    public LoginPresenter(StaffRepository staffRepository, LoginContract.View view) {
        mStaffRepo = staffRepository;
        mView = view;
        apiClient = UtilsApi.getAPIService();

    }

    @Override
    public void doLogin(Staff staff) {
        StaffRepository.
                getInstance(StaffRemoteDataSource.
                getInstance())
                .login(staff)
                .enqueue(new Callback<StaffResponse>() {
                    @Override
                    public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                        mView.onLoginSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<StaffResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void getDepartment() {
        DepartmentRepository.
                getInstance(DepartmentRemoteDataSource.getInstance())
                .getDepartments()
                .enqueue(new Callback<DepartmentResponse>() {
                    @Override
                    public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                        mView.onGetDepartmentSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
