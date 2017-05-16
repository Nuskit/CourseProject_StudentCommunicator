package com.bsuir.poit.studentcommunicator.view;

import java.util.Date;
import java.util.List;

public interface IMainWorkView extends IExceptionView{
    void setBarTime(String dateFormat);
    void setBarNotification(boolean haveNewNotifications);
    void setBarSubGroups(List<String> subGroups);
    void setBarLogo(String universityLogo);
    void setBarGroup(String numberGroup);
}
