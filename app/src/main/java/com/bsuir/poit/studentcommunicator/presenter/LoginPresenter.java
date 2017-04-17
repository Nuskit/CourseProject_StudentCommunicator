package com.bsuir.poit.studentcommunicator.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

public class LoginPresenter {
    private final ILoginView loginView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final ISession session;

    public LoginPresenter(ILoginView loginView, IServiceUnitOfWork serviceUnitOfWork, ISession session){
        this.loginView = loginView;
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
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
