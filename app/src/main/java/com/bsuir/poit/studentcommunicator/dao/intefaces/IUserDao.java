package com.bsuir.poit.studentcommunicator.dao.intefaces;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;

public interface IUserDao {
    boolean checkLogin(String email, String password) throws DaoException;
}
