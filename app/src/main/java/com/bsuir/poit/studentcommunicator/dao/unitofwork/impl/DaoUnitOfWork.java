package com.bsuir.poit.studentcommunicator.dao.unitofwork.impl;

import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.impl.inject.IDaoUnitOfWorkInject;

public class DaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;

    public DaoUnitOfWork(){
        userDao = null;
        //userDao = daoInject.getUser();
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }
}
