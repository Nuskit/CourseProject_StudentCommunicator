package com.bsuir.poit.studentcommunicator.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.presenter.impl.ScheduleFragmentPresenter;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class ScheduleLessonFragment extends BaseFragment implements IScheduleView{

    @Inject
    ScheduleFragmentPresenter scheduleFragmentPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.getComponent(UIMainWorkComponent.class).inject(this);
    }

    public static ScheduleLessonFragment newInstance(){
        return new ScheduleLessonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_lesson, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        scheduleFragmentPresenter.init(this);
    }

    @Override
    protected void initDiComponent() {

    }

    @Override
    public void talkException(String message) {

    }

    @Override
    public void setBarTime(Date time) {

    }

    @Override
    public void setBarGroup(String numberGroup) {

    }

    @Override
    public void setBarLogo(String universityLogo) {

    }

    @Override
    public void setBarSubGroups(List<String> subGroups) {

    }

    @Override
    public void setBarNotification(boolean haveNewNotifications) {

    }

    @Override
    public Date getScheduleDate() {
        return null;
    }

    @Override
    public void setSchedule(List<LessonSchedule> lessonSchedules) {

    }

    @Override
    public void updateLessonsNotification(Date currentDate, List<LessonSchedule> notificationLessonSchedules) {

    }

    @Override
    public void setLessonNotifications(LessonSchedule lessonSchedule, List<LessonNotification> lessonNotifications) {

    }
}
