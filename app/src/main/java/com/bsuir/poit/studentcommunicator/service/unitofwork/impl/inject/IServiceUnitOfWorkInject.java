package com.bsuir.poit.studentcommunicator.service.unitofwork.impl.inject;


import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUniversityService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;

public interface IServiceUnitOfWorkInject {
    IUserService getLoginService();
    INotifierService getNotifierService();
    IGroupService getGroupService();
    IUniversityService getUniversityService();
    IScheduleService getScheduleService();
}
