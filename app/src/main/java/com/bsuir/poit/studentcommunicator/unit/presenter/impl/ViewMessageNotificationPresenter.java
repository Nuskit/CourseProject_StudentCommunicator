package com.bsuir.poit.studentcommunicator.unit.presenter.impl;


import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.unit.presenter.AbstractSessionPresenter;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IViewMessageNotificationView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewMessageNotificationPresenter extends AbstractSessionPresenter{
    private final IViewMessageNotificationView messageNotificationView;
    private Date oldMessageTime;

    public ViewMessageNotificationPresenter(IViewMessageNotificationView messageNotificationView,
                                            IServiceUnitOfWork serviceUnitOfWork, ISession session) {
        super(serviceUnitOfWork, session);
        this.messageNotificationView = messageNotificationView;
        oldMessageTime = getCurrentTime();
    }

    public void onCreate(){
        loadOldMessageNotifications();
    }

    private Date getCurrentTime(){
        return Calendar.getInstance().getTime();
    }

    public void loadNewMessageNotifications(){
        try
        {
            messageNotificationView.showNewMessageNotifications(
                    serviceUnitOfWork.getNotificationService().getNewUserNotifications(
                            session.getAuthorId(), getCurrentTime()));
        }catch (Exception e){
            messageNotificationView.talkException(e.getMessage());
        }
    }

    public void loadOldMessageNotifications(){
        try{
            List<MessageNotification> messageNotifications =
                    serviceUnitOfWork.getNotificationService().getOldUserNotifications(
                            session.getAuthorId(), oldMessageTime);

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
