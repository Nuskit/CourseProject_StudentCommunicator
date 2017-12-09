package com.bsuir.poit.studentcommunicator.dao.intefaces;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;

public interface INotificationDao {
    boolean haveNewNotificationMessages(int profileId) throws DaoException;
}
