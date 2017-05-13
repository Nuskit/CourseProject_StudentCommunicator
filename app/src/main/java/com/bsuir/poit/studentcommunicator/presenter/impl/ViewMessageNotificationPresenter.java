package com.bsuir.poit.studentcommunicator.presenter.impl;


import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IViewMessageNotificationView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewMessageNotificationPresenter {
    private final IViewMessageNotificationView messageNotificationView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final DateManager dateManager;
    private Date oldMessageTime;

    public ViewMessageNotificationPresenter(IViewMessageNotificationView messageNotificationView,
                                            IServiceUnitOfWork serviceUnitOfWork, DateManager dateManager) {
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.messageNotificationView = messageNotificationView;
        this.dateManager = dateManager;
    }

    public void onCreate(){
        oldMessageTime = getCurrentTime();
        loadOldMessageNotifications();
    }

    private Date getCurrentTime(){
        return dateManager.getCurrentTime();
    }

    public void loadNewMessageNotifications(){
        try
        {
            messageNotificationView.showNewMessageNotifications(serviceUnitOfWork
                    .getNotificationService().getNewUserNotifications(getCurrentTime()));
        }catch (Exception e){
            messageNotificationView.talkException(e.getMessage());
        }
    }

    public void loadOldMessageNotifications(){
        try{
            List<MessageNotification> messageNotifications = serviceUnitOfWork
                    .getNotificationService().getOldUserNotifications(oldMessageTime);

            updateOldMessageTime(messageNotifications);

            messageNotificationView.showOldMessages(messageNotifications);
     }catch (Exception e){
            messageNotificationView.talkException(e.getMessage());
        }
    }

    private void updateOldMessageTime(List<MessageNotification> messageNotifications) {
        int numberMessages = messageNotifications.size();
        if (numberMessages > 0) {
            oldMessageTime = messageNotifications.get(numberMessages-1).getCreateDate();
        }
    }
}
