package com.bsuir.poit.studentcommunicator.service.impl.user;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;

//TODO: complete
public class UserService implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    public UserService(IDaoUnitOfWork daoUnitOfWork){
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public boolean checkLogin(String email, String password) {
        return email.equals("test");
    }

    @Override
    public boolean resetLogin(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserInformation getInformation(String login, String password) throws ServiceException {
        return null;
    }
}
