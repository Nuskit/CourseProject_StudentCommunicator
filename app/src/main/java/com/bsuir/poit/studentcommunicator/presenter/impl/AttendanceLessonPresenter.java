package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IAttendanceLessonView;

import java.util.Date;

public class AttendanceLessonPresenter {
    private final IAttendanceLessonView attendanceLessonView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final DateManager dateManager;
    private int lessonId;
    private Date lastUpdateTime;

    public AttendanceLessonPresenter(IAttendanceLessonView attendanceLessonView,
                                     IServiceUnitOfWork serviceUnitOfWork, DateManager dateManager) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.attendanceLessonView = attendanceLessonView;
        this.dateManager = dateManager;
    }

    public void onCreate(int lessonId){
        this.lessonId = lessonId;
        lastUpdateTime = getCurrentTime();
        loadViewData();
    }

    private Date getCurrentTime(){
        return dateManager.getCurrentTime();
    }

    private void loadViewData() {
        try {
            attendanceLessonView.showAttendanceGroups(serviceUnitOfWork.getLessonService()
                    .getAttendanceInformation(lessonId));
        }catch (Exception e){
            attendanceLessonView.talkException(e.getMessage());
        }
    }

    public void updateAttendanceGroups(){
        try{
            Date currentUpdateTime = getCurrentTime();
            attendanceLessonView.updateAttendanceGroups(serviceUnitOfWork.getLessonService()
                    .getChangedAttendance(lessonId, lastUpdateTime, currentUpdateTime));
            lastUpdateTime = currentUpdateTime;
        }catch (Exception e){
            attendanceLessonView.talkException(e.getMessage());
        }
    }
}
