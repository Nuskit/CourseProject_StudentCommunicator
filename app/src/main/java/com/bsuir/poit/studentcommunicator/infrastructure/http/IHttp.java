package com.bsuir.poit.studentcommunicator.infrastructure.http;

import com.bsuir.poit.studentcommunicator.infrastructure.http.exception.HttpException;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;

import java.util.Date;
import java.util.List;

public interface IHttp {
    boolean checkLogin(String email, String password) throws HttpException;
    List<LessonSchedule> getSchedule(Date currentDate) throws HttpException;
}
