package com.example.user.data.api;

import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.response.StaffsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {


    @GET("department/getAll")
    Call<DepartmentResponse> getDepartments();

    @GET("user/department/get/")
    Call<StaffsResponse> getStaffs(@Query(value = "id", encoded = true) int id);

    @POST("user/login")
    Call<StaffResponse> login(@Body Staff staff);
}
