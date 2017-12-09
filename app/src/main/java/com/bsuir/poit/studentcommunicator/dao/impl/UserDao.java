package com.bsuir.poit.studentcommunicator.dao.impl;

import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;

public class UserDao implements IUserDao {
    private final IHttp http;

    public UserDao(IHttp http) {
        this.http = http;
    }

    @Override
    public Integer checkLogin(String email, String password) throws DaoException {
        try {
            return http.checkLogin(email, password);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
