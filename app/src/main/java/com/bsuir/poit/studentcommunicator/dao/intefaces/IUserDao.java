package com.bsuir.poit.studentcommunicator.dao.intefaces;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;

public interface IUserDao {
    Integer checkLogin(String email, String password) throws DaoException;
}
