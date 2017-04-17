package com.bsuir.poit.studentcommunicator.view;

public interface ILoginView extends IExceptionView{
    String getEmail();
    String getPassword();
    void loginComplete(boolean isLogined);
}
