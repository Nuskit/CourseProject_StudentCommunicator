package com.bsuir.poit.studentcommunicator.view;

public interface IResetLoginView extends IExceptionView{
    String getEmail();
    void resetLoginCompleted(boolean isReseted);
}
