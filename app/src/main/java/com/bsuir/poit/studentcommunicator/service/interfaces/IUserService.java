package com.bsuir.poit.studentcommunicator.service.interfaces;

import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

public interface IUserService {
    boolean checkLogin(String email, String password) throws ServiceException;
    boolean resetLogin(String email) throws ServiceException;
    UserInformation getInformation(String login, String password) throws ServiceException;
}
