package com.example.user.ui.main;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.user.R;
import com.example.user.data.model.Department;
import com.example.user.data.model.Staff;
import com.example.user.ui.base.BaseActivity;
import com.example.user.ui.home.HomeFragment;
import com.example.user.ui.staff.StaffFragment;
import com.example.user.ui.statistical.StatisticalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends BaseActivity implements HomeFragment.OnGetDepartmentsListener,
        StaffFragment.OnGetStaffsListener<Staff>,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private int mCurrentPosition;
//    @Override
    //   protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        apiClient = UtilsApi.getAPIService();
//
//        DepartmentRepository
//                .getInstance(DepartmentRemoteDataSource
//                .getInstance())
//                .getDepartments()
//                .enqueue(new Callback<DepartmentResponse>() {
//            @Override
//            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
//                System.out.println(response.raw().request().url() + " url");
//                System.out.println(response.body().getmDepartments().get(0).getName().toString());
//            }
//
//            @Override
//            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
//
//            }
//        });

//    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
        mBottomNavigationView = findViewById(R.id.navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPlayed(Department department) {

    }

    @Override
    public void onGetStaffsSuccess(List<Staff> staff) {

    }

    @Override
    public void onGetDepartmentsSuccess(List<Department> departments) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                mCurrentPosition = 0;
                replaceFragment(getSupportFragmentManager(), HomeFragment.newInstance((Staff) getIntent().
                        getSerializableExtra("user")));
                break;
            case R.id.action_profile:
                mCurrentPosition = 1;
                replaceFragment(getSupportFragmentManager(),
                        StaffFragment.newInstance((Staff) getIntent().
                                getSerializableExtra("user")));
                break;
            case R.id.action_:
                mCurrentPosition = 2;
                replaceFragment(getSupportFragmentManager(),
                        StatisticalFragment.newInstance((Staff) getIntent().
                                getSerializableExtra("user")));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mCurrentPosition == 0)
            mCurrentPosition = 2;
        else {
            mCurrentPosition = mCurrentPosition - 1;
        }
        switch (mCurrentPosition) {
            case 0:
                mBottomNavigationView.setSelectedItemId( R.id.action_home);
//                replaceFragment(getSupportFragmentManager(), HomeFragment.newInstance((Staff) getIntent().
//                        getSerializableExtra("user")));
                break;
            case 1:
                mBottomNavigationView.setSelectedItemId( R.id.action_profile);
//                replaceFragment(getSupportFragmentManager(),
//                        StaffFragment.newInstance((Staff) getIntent().
//                                getSerializableExtra("user")));
                break;
            case 2:
                mBottomNavigationView.setSelectedItemId( R.id.action_);
                replaceFragment(getSupportFragmentManager(),
                        StatisticalFragment.newInstance((Staff) getIntent().
                                getSerializableExtra("user")));
                break;
        }
    }
}