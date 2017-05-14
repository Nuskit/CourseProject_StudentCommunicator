package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.presenter.BaseFragmentPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

//TODO: init as difference user(ext student, teacher)
//TODO: check having internet
public class ScheduleFragmentPresenter implements BaseFragmentPresenter<IScheduleView> {
    private IScheduleView scheduleView;
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public ScheduleFragmentPresenter(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    //TODO: if login, check changes information
    /* init in other class
    private void initUser() throws ServiceException {
        UserInformation userInformation = serviceUnitOfWork.getUserService().getInformation();
        session.setAccountInformation(userInformation);
    }*/

    private void initSchedule() {
        loadLessonsToday();
        //TODO: set clickable, for teacher
    }

    private Date getScheduleDate(){
        return scheduleView.getScheduleDate();
    }

    public void onCreate(){
        try{
            //initUser();
            initSchedule();
        }catch (Exception e) {
            //TODO: move to login menu and message problem
        }
    }

    public void loadLessonsToday(){
        try {
            List<LessonSchedule> lessonSchedules = serviceUnitOfWork.getScheduleService().getLessons(getScheduleDate());
            scheduleView.setSchedule(lessonSchedules);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void updateLessonsNotification(){
        try {
            Date currentDate = getScheduleDate();
            List<LessonSchedule> notificationLessonSchedules = serviceUnitOfWork.getNotificationService().haveNewLessonNotifications(currentDate);
            scheduleView.updateLessonsNotification(currentDate, notificationLessonSchedules);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void loadLessonNotification(LessonSchedule lessonSchedule){
        try{
            List<LessonNotification> lessonNotifications = serviceUnitOfWork.getNotificationService().getLessonNotifications(lessonSchedule);
            scheduleView.setLessonNotifications(lessonSchedule, lessonNotifications);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    @Override
    public void init(IScheduleView view) {
        scheduleView = view;
    }
}
