package com.bsuir.poit.studentcommunicator.service.interfaces;


import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface INotifierService {
    boolean haveNewNotifierMessages() throws ServiceException;
    List<LessonNotification> getLessonNotifiers(Lesson lesson) throws ServiceException;
    List<Lesson> haveNewLessonNotifiers(Date currentDate) throws ServiceException;
    List<Receiver> getRecievers() throws ServiceException;
    boolean sendNotifyMessage(MessageNotification messageNotification) throws ServiceException;
}
