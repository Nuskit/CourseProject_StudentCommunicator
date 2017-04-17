package com.bsuir.poit.studentcommunicator.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.activity.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO: init as difference user(ext student, teacher)
//TODO: check having internet
public class SchedulePresenter {
    private final IScheduleView scheduleView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final ISession session;

    public SchedulePresenter(IScheduleView scheduleView, IServiceUnitOfWork serviceUnitOfWork,
                             ISession session){
        this.scheduleView = scheduleView;
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    private void initBar() throws ServiceException {
        initBarTime();
        initBarGroup();
        initBarLogo();
        initBarSubGroups();
        initBarNotifiers();
    }

    private void initBarNotifiers() throws ServiceException {
        updateNotifierMessages();
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

    //TODO: if logined, check changes information
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

    public void updateNotifierMessages(){
        try {
            boolean haveNewNotifiers = serviceUnitOfWork.getNotifierService().haveNewNotifierMessages();
            scheduleView.setBarNotifier(haveNewNotifiers);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void updateLessonsNotifier(){
        try {
            Date currentDate = getScheduleDate();
            List<Lesson> notifierLessons = serviceUnitOfWork.getNotifierService().haveNewLessonNotifiers(currentDate);
            scheduleView.updateLessonsNotifier(currentDate, notifierLessons);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }

    public void loadLessonNotification(Lesson lesson){
        try{
            List<LessonNotification> lessonNotifications = serviceUnitOfWork.getNotifierService().getLessonNotifiers(lesson);
            scheduleView.setLessonNotifiers(lesson, lessonNotifications);
        }catch (Exception e){
            scheduleView.talkException(e.getMessage());
        }
    }
}
