package com.bsuir.poit.studentcommunicator.unit.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.unit.presenter.AbstractSessionPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO: init as difference user(ext student, teacher)
//TODO: check having internet
public class SchedulePresenter extends AbstractSessionPresenter {
    private final IScheduleView scheduleView;

    public SchedulePresenter(IScheduleView scheduleView, IServiceUnitOfWork serviceUnitOfWork,
                             ISession session){
        super(serviceUnitOfWork, session);
        this.scheduleView = scheduleView;
    }

    private void initBar() throws ServiceException {
        initBarTime();
        initBarGroup();
        initBarLogo();
        initBarSubGroups();
        initBarNotifications();
    }

    private void initBarNotifications() throws ServiceException {
        updateNotificationMessages();
    }

    private void initBarSubGroups() throws ServiceException {
        List<String> subGroups = serviceUnitOfWork.getGroupService().getSubGroupNames();
        scheduleView.setBarSubGroups(subGroups);
    }

    private void initBarLogo() throws ServiceException {
        String universityLogo = serviceUnitOfWork.getUniversityService().getLogo();
        scheduleView.setBarLogo(universityLogo);
    }

    private void initBarGroup() throws ServiceException {
        String numberGroup = serviceUnitOfWork.getGroupService().getNumberGroup();
        scheduleView.setBarGroup(numberGroup);
    }

    private void initBarTime(){
        scheduleView.setBarTime(Calendar.getInstance().getTime());
    }

    //TODO: if login, check changes information
    private void initUser() throws ServiceException {
        UserInformation userInformation = serviceUnitOfWork.getUserService()
                .getInformation(session.getLogin(), session.getPassword());
        session.setAccountInformation(userInformation);
    }

    private void initSchedule() {
        loadLessonsToday();
        //TODO: set clickable, for teacher
    }

    private Date getScheduleDate(){
        return scheduleView.getScheduleDate();
    }

    public void onCreate(){
        try{
            initUser();
            initBar();
            initSchedule();
        }catch (Exception e) {
            //TODO: move to login menu and message problem
        }
    }

    public void loadLessonsToday(){
        try {
            List<Lesson> lessons = serviceUnitOfWork.getScheduleService().getLessons(getScheduleDate());
            scheduleView.setSchedule(lessons);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void updateNotificationMessages(){
        try {
            boolean haveNewNotifications = serviceUnitOfWork.getNotificationService().haveNewNotificationMessages();
            scheduleView.setBarNotification(haveNewNotifications);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void updateLessonsNotification(){
        try {
            Date currentDate = getScheduleDate();
            List<Lesson> notificationLessons = serviceUnitOfWork.getNotificationService().haveNewLessonNotifications(currentDate);
            scheduleView.updateLessonsNotification(currentDate, notificationLessons);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void loadLessonNotification(Lesson lesson){
        try{
            List<LessonNotification> lessonNotifications = serviceUnitOfWork.getNotificationService().getLessonNotifications(lesson);
            scheduleView.setLessonNotifications(lesson, lessonNotifications);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }
}
