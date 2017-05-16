package com.bsuir.poit.studentcommunicator.dao.intefaces;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;

import java.util.Date;
import java.util.List;

public interface IScheduleDao {
    List<LessonSchedule> getLessons(Date currentDate) throws DaoException;
}
