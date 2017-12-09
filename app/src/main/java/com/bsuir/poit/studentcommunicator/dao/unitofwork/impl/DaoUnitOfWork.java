package com.bsuir.poit.studentcommunicator.dao.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.dao.intefaces.INotificationDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

public class DaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;
    private final IScheduleDao scheduleDao;
    private final INotificationDao notificationDao;

    public DaoUnitOfWork(IUserDao userDao, IScheduleDao scheduleDao, INotificationDao notificationDao){
        this.userDao = userDao;
        this.scheduleDao = scheduleDao;
        this.notificationDao = notificationDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    @Override
    public INotificationDao getNotificationDao() {
        return notificationDao;
    }
}
