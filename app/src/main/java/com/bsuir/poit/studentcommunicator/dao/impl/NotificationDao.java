package com.bsuir.poit.studentcommunicator.dao.impl;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.dao.intefaces.INotificationDao;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.infrastructure.http.exception.HttpException;

import java.util.Calendar;
import java.util.Random;

public class NotificationDao implements INotificationDao {

    private final IHttp http;

    public NotificationDao(IHttp http){
        this.http = http;
    }

    @Override
    public boolean haveNewNotificationMessages(int profileId) throws DaoException {
        try {
            return new Random(Calendar.getInstance().getTimeInMillis()).nextBoolean();//http.haveNewNotifications(profileId);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
