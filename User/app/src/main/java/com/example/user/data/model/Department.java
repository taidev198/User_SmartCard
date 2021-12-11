package com.example.user.data.model;

import com.google.gson.annotations.SerializedName;

public class Department {
    @SerializedName("id")
    private int mid;
    @SerializedName("name")
    private String name;
    @SerializedName("quanlity")
    private int mQuanity;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmQuanity() {
        return mQuanity;
    }

    public void setmQuanity(int mQuanity) {
        this.mQuanity = mQuanity;
    }
}
