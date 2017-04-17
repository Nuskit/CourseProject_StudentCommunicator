package com.bsuir.poit.studentcommunicator.presenter;

import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IResetLoginView;

public class ResetLoginPresenter {
    private final IResetLoginView resetLoginView;
    private final IServiceUnitOfWork serviceUnitOfWork;

    public ResetLoginPresenter(IResetLoginView resetLoginView, IServiceUnitOfWork serviceUnitOfWork){
        this.resetLoginView = resetLoginView;
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    public void resetLogin(){
        try {
            String email = resetLoginView.getEmail();
            resetLoginView.resetLoginCompleted(serviceUnitOfWork.getUserService().resetLogin(email));
        }catch (Exception e){
            resetLoginView.talkException(e.getMessage());
        }
    }
}
