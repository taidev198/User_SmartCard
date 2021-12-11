package com.example.user.ui.staff;

import com.example.user.data.api.ApiClient;
import com.example.user.data.api.UtilsApi;
import com.example.user.data.department.DepartmentRemoteDataSource;
import com.example.user.data.department.DepartmentRepository;
import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.staff.StaffRemoteDataSource;
import com.example.user.data.staff.StaffRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffPresenter implements StaffContract.Presenter {
    private ApiClient apiClient;
    private StaffRepository mDepartmentRepository;
    private StaffContract.View mView;

    public StaffPresenter(StaffRepository departmentRepository, StaffContract.View view) {
        mDepartmentRepository = departmentRepository;
        mView = view;
    }

    @Override
    public void getStaffs(Staff staff) {
        apiClient = UtilsApi.getAPIService();
        StaffRepository.
                getInstance(StaffRemoteDataSource.
                        getInstance())
                .login(staff)
                .enqueue(new Callback<StaffResponse>() {
                    @Override
                    public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                        mView.showStaffs(response.body().getStaff());
                    }

                    @Override
                    public void onFailure(Call<StaffResponse> call, Throwable t) {
                        t.printStackTrace();
                        mView.showError();
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
                        mView.showError();
                    }
                });
    }

}
