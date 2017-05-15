package com.bsuir.poit.studentcommunicator.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UILoginComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.presenter.impl.ScheduleFragmentPresenter;
import com.bsuir.poit.studentcommunicator.ui.adapter.ScheduleLessonsRecyclerViewAdapter;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ScheduleDayLessonsFragment extends BaseFragment implements IScheduleView {

    private static final String DATE_NUMBER = "DATE_NUMBER";
    @Inject
    ScheduleFragmentPresenter scheduleFragmentPresenter;
    @Inject
    DateManager dateManager;
    private List<LessonSchedule> lessonSchedules;
    private ScheduleLessonsRecyclerViewAdapter scheduleLessonsAdapter;

    public ScheduleDayLessonsFragment() {
        lessonSchedules = new ArrayList<>();
    }

    public static ScheduleDayLessonsFragment newInstance(int afterDays) {
        ScheduleDayLessonsFragment fragment = new ScheduleDayLessonsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DATE_NUMBER, afterDays);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int afterDays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedulelessons_list, container, false);
        ButterKnife.bind(this, view);

        afterDays = getArguments().getInt(DATE_NUMBER);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            scheduleLessonsAdapter = new ScheduleLessonsRecyclerViewAdapter(lessonSchedules);
            recyclerView.setAdapter(scheduleLessonsAdapter);
        }
        return view;
    }

    @Override
    protected void initDiComponent() {
        getComponent(UIMainWorkComponent.class).inject(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        scheduleFragmentPresenter.init(this);
        scheduleFragmentPresenter.onCreate();
    }

    @Override
    public Date getScheduleDate() {
        return dateManager.getDateAfter(afterDays);
    }

    @Override
    public void setSchedule(List<LessonSchedule> lessonSchedules) {
        this.lessonSchedules.clear();
        this.lessonSchedules.addAll(lessonSchedules);
        scheduleLessonsAdapter.notifyDataSetChanged();

    }

    @Override
    public void updateLessonsNotification(Date currentDate, List<LessonSchedule> notificationLessonSchedules) {
        //TODO: add notification
    }

    @Override
    public void setLessonNotifications(LessonSchedule lessonSchedule, List<LessonNotification> lessonNotifications) {
        //TODO: add notification
    }

    @Override
    public void talkException(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
