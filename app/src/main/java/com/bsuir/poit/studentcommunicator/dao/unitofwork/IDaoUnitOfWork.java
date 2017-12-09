package com.bsuir.poit.studentcommunicator.dao.unitofwork;

import com.bsuir.poit.studentcommunicator.dao.intefaces.INotificationDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;

public interface IDaoUnitOfWork {
    IUserDao getUserDao();
    IScheduleDao getScheduleDao();
    INotificationDao getNotificationDao();
}
