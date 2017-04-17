package com.bsuir.poit.studentcommunicator.service.impl.user;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;

//TODO: complete
public abstract class UserService implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    public UserService(IDaoUnitOfWork daoUnitOfWork){
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public boolean checkLogin(String email, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean resetLogin(String email) {
        throw new UnsupportedOperationException();
    }
}
