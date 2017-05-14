package com.bsuir.poit.studentcommunicator.service.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.interfaces.ILessonService;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUniversityService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;

public class ServiceUnitOfWork implements IServiceUnitOfWork {
    private final IUserService userService;
    private final INotificationService notificationService;
    private final IGroupService groupService;
    private final IUniversityService universityService;
    private final IScheduleService scheduleService;
    private final ILessonService lessonService;

    //TODO: set as inject
    public ServiceUnitOfWork(IUserService userService,
                             INotificationService notificationService,
                             IGroupService groupService,
                             IUniversityService universityService,
                             IScheduleService scheduleService,
                             ILessonService lessonService){
        this.userService = userService;
        this.notificationService = notificationService;
        this.groupService = groupService;
        this.universityService = universityService;
        this.scheduleService = scheduleService;
        this.lessonService = lessonService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public INotificationService getNotificationService() {
        return notificationService;
    }

    @Override
    public IGroupService getGroupService() {
        return groupService;
    }

    @Override
    public IUniversityService getUniversityService() {
        return universityService;
    }

    @Override
    public IScheduleService getScheduleService() {
        return scheduleService;
    }

    @Override
    public ILessonService getLessonService() {
        return lessonService;
    }
}
