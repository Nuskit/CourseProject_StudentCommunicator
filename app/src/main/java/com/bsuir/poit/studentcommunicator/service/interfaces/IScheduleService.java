package com.bsuir.poit.studentcommunicator.service.interfaces;

import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface IScheduleService {
    List<LessonSchedule> getLessons(Date currentDate) throws ServiceException;
}
