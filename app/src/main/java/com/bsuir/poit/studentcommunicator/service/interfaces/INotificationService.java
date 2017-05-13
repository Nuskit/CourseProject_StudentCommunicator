package com.bsuir.poit.studentcommunicator.service.interfaces;


import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.SendMessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface INotificationService {
    boolean haveNewNotificationMessages() throws ServiceException;
    List<LessonNotification> getLessonNotifications(Lesson lesson) throws ServiceException;
    List<Lesson> haveNewLessonNotifications(Date currentDate) throws ServiceException;
    List<Receiver> getReceivers() throws ServiceException;
    boolean sendNotifyMessage(SendMessageNotification messageNotification) throws ServiceException;
    List<MessageNotification> getNewUserNotifications(int login, Date currentTime) throws ServiceException;
    List<MessageNotification> getOldUserNotifications(int login, Date oldMessageTime) throws ServiceException;
}
