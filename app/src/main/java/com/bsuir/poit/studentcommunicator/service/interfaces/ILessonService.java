package com.bsuir.poit.studentcommunicator.service.interfaces;

import com.bsuir.poit.studentcommunicator.model.GroupAttendance;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface ILessonService {
    List<GroupAttendance> getAttendanceInformation(int lessonId) throws ServiceException;
    List<GroupAttendance> getChangedAttendance(int lessonId, Date lastUpdateTime, Date currentUpdateTime) throws ServiceException;
}
