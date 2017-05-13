package com.bsuir.poit.studentcommunicator.view;

import com.bsuir.poit.studentcommunicator.model.MessageNotification;

import java.util.List;

public interface IViewMessageNotificationView extends IExceptionView{
    void showNewMessageNotifications(List<MessageNotification> newUserNotifications);
    List<MessageNotification> setOldMessageNotifications(List<MessageNotification> oldUserNotifications);
    void showOldMessages(List<MessageNotification> messageNotifications);
}
