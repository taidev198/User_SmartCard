package com.example.user.data.department;

import com.example.user.data.api.ApiClient;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.api.UtilsApi;

import retrofit2.Call;

public class DepartmentRemoteDataSource implements DepartmentDataSource{

    private static DepartmentRemoteDataSource sInstance;
    private static ApiClient mApi;
    private DepartmentRemoteDataSource(ApiClient apiClient) {
        mApi = apiClient;
    }

    public static DepartmentRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DepartmentRemoteDataSource(UtilsApi.getAPIService());
        }
        return sInstance;
    }

    @Override
    public Call<DepartmentResponse> getDepartments() {
        return mApi.getDepartments();
    }
}
