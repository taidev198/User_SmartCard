package com.example.user.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.user.R;
import com.example.user.data.model.Department;
import com.example.user.data.model.Staff;
import com.example.user.ui.base.BaseFragment;
import com.example.user.ui.base.OnRecyclerItemClickListener;
import com.example.user.ui.staff.StaffFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View,
        OnRecyclerItemClickListener<Department>, StaffFragment.OnGetStaffsListener<Staff> {

    protected RecyclerView mRecyclerView;
    protected HomeContract.Presenter mPresenter;
    protected HomeAdapter mAdapter;
    private CalendarView calendarView;
    protected OnGetDepartmentsListener mCallback;
    private static Staff mUser;
    private List<Calendar> calendarList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    public static HomeFragment newInstance(Staff user) {
        mUser = user;
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnGetDepartmentsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTrackClickListener");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initComponents(View view) {
        calendarView = view.findViewById(R.id.calendarView);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        mPresenter = new HomePresenter(this);

        swipeRefreshLayout = view.findViewById(R.id.sfl);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mUser == null) {
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                mPresenter.getStaffs(mUser);
            }
        });
    }

    @Override
    protected void initData() {
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.calendar_day_background));
        calendarView.setEvents(events);
        if (null != mUser && null != mUser.getLateDate()) {
            for (Date date : mUser.getLateDate()) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date);
                calendarList.add(calendar1);
            }
            calendarView.setHighlightedDays(calendarList);
        }
    }

    @Override
    public void showStaffs(Staff staff) {
        try {
            swipeRefreshLayout.setRefreshing(false);
            mUser.setLateDate(staff.getLateDate());
            SharedPreferences preferences = getContext().getSharedPreferences("SHARE", MODE_PRIVATE);
            preferences.edit().putString("user", new Gson().toJson(mUser)).apply();
            initData();
        } catch (Exception e) {

        }


    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClicked(View view, long pos, Department item) {

    }

    @Override
    public void onPlayed(Department department) {

    }

    @Override
    public void onGetStaffsSuccess(List<Staff> staff) {

    }


    public interface OnGetDepartmentsListener {
        void onPlayed(Department department);

        void onGetDepartmentsSuccess(List<Department> departments);
    }
}
