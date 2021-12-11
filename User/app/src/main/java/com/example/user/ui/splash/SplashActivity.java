package com.example.user.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;
import com.example.user.data.model.Staff;
import com.example.user.ui.login.LoginActivity;
import com.example.user.ui.main.MainActivity;
import com.google.gson.Gson;

public class SplashActivity extends AppCompatActivity {

    private Staff mStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences("SHARE", MODE_PRIVATE);
        mStaff = new Gson().fromJson(preferences.getString("user", ""), Staff.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null == mStaff) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    //To pass:
                    intent.putExtra("user", mStaff);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);
    }
}