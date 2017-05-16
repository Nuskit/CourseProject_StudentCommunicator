package com.bsuir.poit.studentcommunicator.dao.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

public class DaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;
    private final IScheduleDao scheduleDao;

    public DaoUnitOfWork(IUserDao userDao, IScheduleDao scheduleDao){
        this.userDao = userDao;
        this.scheduleDao = scheduleDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IScheduleDao getScheduleDao() {
        return scheduleDao;
    }
}
