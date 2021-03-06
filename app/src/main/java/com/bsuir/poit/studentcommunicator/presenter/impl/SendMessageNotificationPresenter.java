package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.model.SendMessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ISendMessageNotificationView;

import java.util.List;

public class SendMessageNotificationPresenter {
    private final ISendMessageNotificationView sendMessageNotificationView;
    private final IServiceUnitOfWork serviceUnitOfWork;

    public SendMessageNotificationPresenter(ISendMessageNotificationView sendMessageNotificationView,
                                            IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.sendMessageNotificationView = sendMessageNotificationView;
    }

    private void initReceivers() throws ServiceException {
        List<Receiver> receivers = serviceUnitOfWork.getNotificationService().getReceivers();
        sendMessageNotificationView.setReceivers(receivers);
    }

    public void onCreate() {
        try {
            initReceivers();
            }catch (Exception e){
            sendMessageNotificationView.talkException(e.getMessage());
        }
    }

    public void sendNotifyMessage(){
        try{
             boolean isSend = serviceUnitOfWork.getNotificationService().sendNotifyMessage(
                     new SendMessageNotification(
                             sendMessageNotificationView.getReason(),
                             sendMessageNotificationView.getMessage(),
                             sendMessageNotificationView.getSelectedReceivers()
             ));
            sendMessageNotificationView.sendNotifyMessageCompleted(isSend);
        }catch (Exception e){
            sendMessageNotificationView.talkException(e.getMessage());
        }
    }
}
