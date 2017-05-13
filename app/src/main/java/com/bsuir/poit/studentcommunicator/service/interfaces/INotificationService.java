package com.bsuir.poit.studentcommunicator.service.interfaces;


import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.SendMessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface INotificationService {
    boolean haveNewNotificationMessages() throws ServiceException;
    List<LessonNotification> getLessonNotifications(LessonSchedule lessonSchedule) throws ServiceException;
    List<LessonSchedule> haveNewLessonNotifications(Date currentDate) throws ServiceException;
    List<Receiver> getReceivers() throws ServiceException;
    boolean sendNotifyMessage(SendMessageNotification messageNotification) throws ServiceException;
    List<MessageNotification> getNewUserNotifications(Date currentTime) throws ServiceException;
    List<MessageNotification> getOldUserNotifications(Date oldMessageTime) throws ServiceException;
}
