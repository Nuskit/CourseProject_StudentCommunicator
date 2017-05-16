package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Pack200;


public class ScheduleService implements IScheduleService {

    private final IDaoUnitOfWork daoUnitOfWork;

    public ScheduleService(IDaoUnitOfWork daoUnitOfWork){
        this.daoUnitOfWork = daoUnitOfWork;
    }


    @Override
    public List<LessonSchedule> getLessons(Date currentDate) throws ServiceException {
        try{
            return daoUnitOfWork.getScheduleDao().getLessons(currentDate);
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
