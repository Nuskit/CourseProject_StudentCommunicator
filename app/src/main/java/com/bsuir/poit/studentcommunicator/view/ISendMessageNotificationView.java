package com.bsuir.poit.studentcommunicator.view;

import com.bsuir.poit.studentcommunicator.model.Receiver;

import java.util.List;

public interface ISendMessageNotificationView extends IExceptionView{
    void setReceivers(List<Receiver> receivers);
    String getReason();
    String getMessage();
    List<Receiver> getChoicedReceivers();
    void sendNotifyMessageCompleted(boolean isSended);
}
