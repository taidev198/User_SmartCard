package com.example.user.data.staff;

import com.example.user.data.model.Staff;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.response.StaffsResponse;

import retrofit2.Call;

public interface StaffDataSource {

    Call<StaffsResponse> getStaffs(int id);

    Call<StaffResponse> login(Staff staff);
}
