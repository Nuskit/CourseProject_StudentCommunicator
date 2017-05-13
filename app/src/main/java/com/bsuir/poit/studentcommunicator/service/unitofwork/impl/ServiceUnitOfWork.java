package com.bsuir.poit.studentcommunicator.service.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
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

    //TODO: set as inject
    public ServiceUnitOfWork(IServiceUnitOfWork fakeOUF){
        userService = fakeOUF.getUserService();
        notificationService = fakeOUF.getNotificationService();
        groupService = fakeOUF.getGroupService();
        universityService = fakeOUF.getUniversityService();
        scheduleService = fakeOUF.getScheduleService();
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
}
