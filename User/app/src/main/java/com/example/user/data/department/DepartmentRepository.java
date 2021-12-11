package com.example.user.data.department;

import com.example.user.data.response.DepartmentResponse;

import retrofit2.Call;

public class DepartmentRepository implements DepartmentDataSource {
    private static DepartmentRepository sInstance;
    private static DepartmentRemoteDataSource mRemote;

    private DepartmentRepository(DepartmentRemoteDataSource departmentRemoteDataSource) {
        mRemote = departmentRemoteDataSource;

    }

    public static DepartmentRepository getInstance(DepartmentRemoteDataSource departmentRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new DepartmentRepository(departmentRemoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Call<DepartmentResponse> getDepartments() {
        return mRemote.getDepartments();
    }
}
