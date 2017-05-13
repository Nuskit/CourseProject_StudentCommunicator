package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

public class LoginPresenter {
    private final ILoginView loginView;
    private final IServiceUnitOfWork serviceUnitOfWork;

    public LoginPresenter(ILoginView loginView, IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.loginView = loginView;
    }

    public void checkLogin(){
        try{
            String email = loginView.getEmail();
            String password = loginView.getPassword();
            loginView.loginComplete(serviceUnitOfWork.getUserService().checkLogin(email, password));
        }catch (Exception e){
            loginView.talkException(e.getMessage());
        }
    }
}
