package com.example.user.ui.home;

import com.example.user.data.department.DepartmentRemoteDataSource;
import com.example.user.data.department.DepartmentRepository;
import com.example.user.data.api.ApiClient;
import com.example.user.data.api.UtilsApi;
import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.staff.StaffRemoteDataSource;
import com.example.user.data.staff.StaffRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter{
    private ApiClient apiClient;
    private DepartmentRepository mDepartmentRepository;
    private HomeContract.View mView;

    public HomePresenter( HomeContract.View view) {
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
}
