package com.bsuir.poit.studentcommunicator.presenter.impl;

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
            resetLoginView.resetLoginCompleted(serviceUnitOfWork.getUserService().resetLogin(
                    resetLoginView.getEmail()));
        }catch (Exception e){
            resetLoginView.talkException(e.getMessage());
        }
    }
}
