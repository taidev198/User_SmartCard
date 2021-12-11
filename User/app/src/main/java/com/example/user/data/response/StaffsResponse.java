package com.example.user.data.response;

import com.example.user.data.model.Staff;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StaffsResponse {
    @SerializedName("staffs")
    @Expose
    private List<Staff> mStaffs;

    public List<Staff> getmStaffs() {
        return mStaffs;
    }

    public void setmStaffs(List<Staff> mStaffs) {
        this.mStaffs = mStaffs;
    }
}
