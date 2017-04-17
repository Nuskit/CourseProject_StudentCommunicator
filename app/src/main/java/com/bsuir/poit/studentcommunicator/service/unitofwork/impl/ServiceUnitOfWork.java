package com.bsuir.poit.studentcommunicator.service.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUniversityService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.service.unitofwork.impl.inject.IServiceUnitOfWorkInject;

public class ServiceUnitOfWork implements IServiceUnitOfWork {
    private final IUserService loginService;
    private final INotifierService notifierService;
    private final IGroupService groupService;
    private final IUniversityService universityService;
    private final IScheduleService scheduleService;

    //TODO: set as inject
    public ServiceUnitOfWork(IServiceUnitOfWorkInject serviceInject){
        loginService = serviceInject.getLoginService();
        notifierService = serviceInject.getNotifierService();
        groupService = serviceInject.getGroupService();
        universityService = serviceInject.getUniversityService();
        scheduleService = serviceInject.getScheduleService();
    }

    @Override
    public IUserService getUserService() {
        return loginService;
    }

    @Override
    public INotifierService getNotifierService() {
        return notifierService;
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
