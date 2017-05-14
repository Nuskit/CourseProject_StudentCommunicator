package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.model.SendMessageNotification;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;

import java.util.Date;
import java.util.List;


public class NotificationService implements INotificationService {
    @Override
    public boolean haveNewNotificationMessages() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<LessonNotification> getLessonNotifications(LessonSchedule lessonSchedule) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<LessonSchedule> haveNewLessonNotifications(Date currentDate) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Receiver> getReceivers() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean sendNotifyMessage(SendMessageNotification messageNotification) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MessageNotification> getNewUserNotifications(Date currentTime) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MessageNotification> getOldUserNotifications(Date oldMessageTime) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
