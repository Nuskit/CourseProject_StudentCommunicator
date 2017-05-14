package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.model.GroupAttendance;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.ILessonService;

import java.util.Date;
import java.util.List;


public class LessonService implements ILessonService {
    @Override
    public List<GroupAttendance> getAttendanceInformation(int lessonId) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GroupAttendance> getChangedAttendance(int lessonId, Date lastUpdateTime, Date currentUpdateTime) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
