package com.bsuir.poit.studentcommunicator.infrastructure.session;

import com.bsuir.poit.studentcommunicator.infrastructure.profilelevel.ProfileLevel;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;

public interface ISession {
    void setAccount(String login, String password, int accountId, ProfileLevel profileLevel);
    void resetAccount();
    String getLogin();
    String getPassword();
    boolean isLogin();
    void setAccountInformation(UserInformation userInformation);
    String getGroup();
    int getAccountId();
    void setProfileId(int profileId);
    int getProfileId();
    ProfileLevel getProfileLevel();
}
