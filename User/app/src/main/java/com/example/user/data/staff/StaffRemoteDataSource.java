package com.example.user.data.staff;

import com.example.user.data.api.ApiClient;
import com.example.user.data.api.UtilsApi;
import com.example.user.data.model.Staff;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.response.StaffsResponse;

import retrofit2.Call;

public class StaffRemoteDataSource implements StaffDataSource{

    private static StaffRemoteDataSource sInstance;
    private static ApiClient mApi;
    private StaffRemoteDataSource(ApiClient apiClient) {
        mApi = apiClient;
    }

    public static StaffRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new StaffRemoteDataSource(UtilsApi.getAPIService());
        }
        return sInstance;
    }

    @Override
    public Call<StaffsResponse> getStaffs(int id) {
        return mApi.getStaffs(id);
    }

    @Override
    public Call<StaffResponse> login(Staff staff) {
        return mApi.login(staff);
    }
}
