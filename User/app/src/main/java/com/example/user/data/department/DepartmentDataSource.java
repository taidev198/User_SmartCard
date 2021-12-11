package com.example.user.data.department;

import com.example.user.data.response.DepartmentResponse;

import retrofit2.Call;

public interface DepartmentDataSource {

    Call<DepartmentResponse> getDepartments();
}
