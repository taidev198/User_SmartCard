package com.example.user.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.data.model.Department;
import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.response.StaffResponse;
import com.example.user.data.staff.StaffRemoteDataSource;
import com.example.user.data.staff.StaffRepository;
import com.example.user.ui.base.BaseActivity;
import com.example.user.ui.base.OnRecyclerItemClickListener;
import com.example.user.ui.main.MainActivity;
import com.example.user.utils.Tools;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements LoginContract.View,
        OnRecyclerItemClickListener<Staff>,
        View.OnClickListener {


    private LoginPresenter mPresenter;
    private RelativeLayout mLoginBtn;
    private TextInputEditText mUsername;
    private TextInputEditText mPass;
    private Pattern pattern;
    private Pattern patternPass;
    private final String USERNAME_PATTERN = "^[A-Z0-9]*$";
    private final String PASS_PATTERN = "^[a-zA-Z1-9]{6,8}$";
    private Staff mStaff;
    private CheckBox cbRemember;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initComponents() {
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this, R.color.white);
        pattern = Pattern.compile(USERNAME_PATTERN);
        patternPass = Pattern.compile(PASS_PATTERN);
        mLoginBtn = findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
        mUsername = findViewById(R.id.text_username);
        mPass = findViewById(R.id.text_pass);
        cbRemember = findViewById(R.id.cb_remember);

    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(StaffRepository.
                getInstance(StaffRemoteDataSource.
                        getInstance()), this);
    }

    @Override
    public void onItemClicked(View view, long pos, Staff item) {

    }

    @Override
    public void onLoginSuccess(StaffResponse staff) {
        if (staff.getMessage().equals("login sucessfully")) {
            mPresenter.getDepartment();
            mStaff = staff.getStaff();
        } else System.out.println("bugs");

    }

    @Override
    public void onLoginFailure(String error) {

    }

    @Override
    public void onGetDepartmentSuccess(DepartmentResponse departmentResponse) {
        for (Department department : departmentResponse.getmDepartments()) {
            if (department.getMid() == mStaff.getIdDepartment()) {
                mStaff.setDepartment(department.getName());
                break;
            }
        }
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        //To pass:
        if (cbRemember.isChecked()){
            SharedPreferences preferences = getSharedPreferences("SHARE", MODE_PRIVATE);
            preferences.edit().putString("user", new Gson().toJson(mStaff)).apply();
        }

        intent.putExtra("user", mStaff);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_login == view.getId()) {
            if (validateLogin()) {
                Staff staff = new Staff();
                staff.setId(mUsername.getText().toString());
                staff.setPassword(mPass.getText().toString());
                mPresenter.doLogin(staff);
            }
        }
    }

    private boolean validateLogin() {
        if (mUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!pattern.matcher(mUsername.getText().toString()).matches()) {
            Toast.makeText(this, "Tên đăng nhập chưa đúng định dạng!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if (!patternPass.matcher(mPass.getText().toString()).matches()) {
//            Toast.makeText(this, "Mật khẩu chưa đúng định dạng!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }
}
