package com.bsuir.poit.studentcommunicator.unit.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.unit.presenter.AbstractSessionPresenter;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

public class LoginPresenter extends AbstractSessionPresenter {
    private final ILoginView loginView;

    public LoginPresenter(ILoginView loginView, IServiceUnitOfWork serviceUnitOfWork, ISession session){
        super(serviceUnitOfWork, session);
        this.loginView = loginView;
    }

    public void checkLogin(){
        try{
            String email = loginView.getEmail();
            String password = loginView.getPassword();
            boolean isLogined = serviceUnitOfWork.getUserService().checkLogin(email, password);
            if (isLogined) {
                session.setAccount(email, password);
            }
            loginView.loginComplete(isLogined);
        }catch (Exception e){
            loginView.talkException(e.getMessage());
        }
    }
}
