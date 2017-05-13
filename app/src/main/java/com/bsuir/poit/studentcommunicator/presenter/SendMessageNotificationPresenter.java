package com.bsuir.poit.studentcommunicator.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ISendMessageNotificationView;

import java.util.List;

public class SendMessageNotificationPresenter {
    private final ISendMessageNotificationView sendMessageNotificationView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final ISession session;

    public SendMessageNotificationPresenter(ISendMessageNotificationView sendMessageNotificationView,
                                            IServiceUnitOfWork serviceUnitOfWork, ISession session){
        this.sendMessageNotificationView = sendMessageNotificationView;
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
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
             boolean isSended = serviceUnitOfWork.getNotificationService().sendNotifyMessage(
                     new MessageNotification(
                             sendMessageNotificationView.getReason(),
                             session.getAuthorId(),
                             sendMessageNotificationView.getMessage(),
                             sendMessageNotificationView.getSelectedReceivers()
             ));
            sendMessageNotificationView.sendNotifyMessageCompleted(isSended);
        }catch (Exception e){
            sendMessageNotificationView.talkException(e.getMessage());
        }
    }
}
