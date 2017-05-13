package com.bsuir.poit.studentcommunicator.infrastructure.session;

import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;

public interface ISession {
    void setAccount(String login, String password);
    String getLogin();
    String getPassword();
    void setAccountInformation(UserInformation userInformation);
    String getGroup();
    int getAuthorId();
}
