package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;

import java.util.Date;
import java.util.List;


public class ScheduleService implements IScheduleService {
    @Override
    public List<LessonSchedule> getLessons(Date currentDate) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
