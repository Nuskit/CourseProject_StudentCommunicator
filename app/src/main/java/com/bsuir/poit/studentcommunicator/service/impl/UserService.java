package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.profilelevel.ProfileLevel;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.service.AbstractSessionService;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;

//TODO: complete
public class UserService extends AbstractSessionService implements IUserService {


    public UserService(IDaoUnitOfWork daoUnitOfWork, ISession session) {
        super(daoUnitOfWork, session);
    }

    @Override
    public boolean checkLogin(String email, String password) throws ServiceException {
        try {
            Integer accountId = daoUnitOfWork.getUserDao().checkLogin(email, password);
            boolean isLogin = accountId != null;
            if (isLogin){
                session.setAccount(email, password, accountId, ProfileLevel.None);
            }
            return isLogin;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkCanLogin() throws ServiceException {
        return session.isLogin();
    }

    @Override
    public void logoutLogin(){
        session.resetAccount();
    }

    @Override
    public boolean resetLogin(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserInformation getInformation(String login, String password) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
