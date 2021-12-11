package com.example.user.ui.staff;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.user.R;
import com.example.user.data.model.Department;
import com.example.user.data.model.Staff;
import com.example.user.data.response.DepartmentResponse;
import com.example.user.data.staff.StaffRemoteDataSource;
import com.example.user.data.staff.StaffRepository;
import com.example.user.ui.base.BaseFragment;
import com.example.user.ui.base.OnRecyclerItemClickListener;
import com.example.user.ui.login.LoginActivity;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StaffFragment extends BaseFragment implements StaffContract.View,
        OnRecyclerItemClickListener<Staff> {


    protected StaffContract.Presenter mPresenter;

    protected OnGetStaffsListener<Staff> mCallback;
    protected static Staff mStaff;
    private TextView text_id;
    private TextView text_name;
    private TextView text_address;
    private TextView text_birth;
    private TextView text_sex;
    private TextView text_department;
    private ImageView imv_avatar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnLogout;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


    public static StaffFragment newInstance(Staff staff) {
        mStaff = staff;
        return new StaffFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnGetStaffsListener<Staff>) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTrackClickListener");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initComponents(View view) {
        mPresenter = new StaffPresenter(StaffRepository.
                getInstance(StaffRemoteDataSource.
                        getInstance()), this);
        text_id = view.findViewById(R.id.tv_id);
        text_name = view.findViewById(R.id.tv_name);
        text_address = view.findViewById(R.id.tv_address);
        text_birth = view.findViewById(R.id.tv_birth);
        text_sex = view.findViewById(R.id.tv_sex);
        text_department = view.findViewById(R.id.tv_department);
        imv_avatar = view.findViewById(R.id.imv_avatar);
        btnLogout = view.findViewById(R.id.btn_logout);
        swipeRefreshLayout = view.findViewById(R.id.sfl);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getStaffs(mStaff);
            }
        });
        btnLogout.setOnClickListener(view1 -> {
            SharedPreferences preferences = getContext().getSharedPreferences("SHARE", MODE_PRIVATE);
            preferences.edit().clear().apply();
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        });
    }

    @Override
    protected void initData() {
        text_name.setText(mStaff.getFullname());
        text_address.setText(mStaff.getAddress());
        Date date = new Date(mStaff.getBirth());
        text_birth.setText(simpleDateFormat.format(date));
        text_sex.setText(mStaff.getGender().equals("0") ? "Nam" : "Nữ");
        text_id.setText(mStaff.getId());
        text_department.setText(mStaff.getDepartment());
        try {
            byte[] imageByteArray = Base64.decode(mStaff.getAvatar(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imv_avatar.setImageBitmap(bmp);
        }catch (Exception e){

        }


    }

    @Override
    public void showStaffs(Staff staff) {
        mStaff = staff;
        mPresenter.getDepartment();

    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetDepartmentSuccess(DepartmentResponse departmentResponse) {
        swipeRefreshLayout.setRefreshing(false);

        for (Department department : departmentResponse.getmDepartments()) {
            if (department.getMid() == mStaff.getIdDepartment()) {
                mStaff.setDepartment(department.getName());
                break;
            }
        }

        SharedPreferences preferences = getContext().getSharedPreferences("SHARE", MODE_PRIVATE);
        preferences.edit().putString("user", new Gson().toJson(mStaff)).apply();
        initData();
    }

    @Override
    public void onItemClicked(View view, long pos, Staff item) {

    }

    public interface OnGetStaffsListener<S> {
        void onPlayed(Department department);

        void onGetStaffsSuccess(List<Staff> staff);
    }
}
