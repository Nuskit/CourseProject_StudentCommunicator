package com.bsuir.poit.studentcommunicator.dao.impl;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;

import java.util.Date;
import java.util.List;

public class ScheduleDao implements IScheduleDao {

    public IHttp http;

    public ScheduleDao(IHttp http){
        this.http = http;
    }

    @Override
    public List<LessonSchedule> getLessons(Date currentDate) throws DaoException {
        try {
            return http.getSchedule(currentDate);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
