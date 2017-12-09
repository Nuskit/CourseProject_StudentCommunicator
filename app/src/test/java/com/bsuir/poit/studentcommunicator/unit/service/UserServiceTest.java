package com.bsuir.poit.studentcommunicator.unit.service;


import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.general.MockDao;
import com.bsuir.poit.studentcommunicator.infrastructure.profilelevel.ProfileLevel;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.impl.UserService;

import org.junit.Before;
import org.junit.Test;

import static com.bsuir.poit.studentcommunicator.general.MockSession.getSession;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private IUserDao userDao;
    private ISession session;
    private UserService service;

    @Before
    public void initTest() {
        userDao = getUserDao();
        session = getSession();
        service = getService(getDaoUnitOfWork(userDao), session);
    }

    private static UserService getService(
            IDaoUnitOfWork unitOfWork,
            ISession session){
        return new UserService(unitOfWork, session);
    }

    private static IDaoUnitOfWork getDaoUnitOfWork(IUserDao userDao) {
        return new MockDao.DaoUOFBuilder().setUserDao(userDao).build();
    }

    private static IUserDao getUserDao(){
        return MockDao.getUserDao();
    }

    @Test
    public void check_can_login() throws ServiceException {
        final boolean expectedIsLogin = true;
        when(session.isLogin()).thenReturn(expectedIsLogin);

        boolean isCanLogin = service.checkCanLogin();

        assertEquals(isCanLogin, expectedIsLogin);
    }

    private static final String MOCK_EMAIL = "Test_email";
    private static final String MOCK_PASSWORD = "Test_password";

    @Test
    public void check_login_success() throws DaoException, ServiceException {
        final int expectedLoginId = 7;
        final boolean expectedIsLogin = true;
        when(userDao.checkLogin(MOCK_EMAIL, MOCK_PASSWORD)).thenReturn(expectedLoginId);

        boolean isLogin = service.checkLogin(MOCK_EMAIL, MOCK_PASSWORD);

        assertEquals(isLogin, expectedIsLogin);
        verify(userDao, times(1)).checkLogin(eq(MOCK_EMAIL), eq(MOCK_PASSWORD));
        verify(session, times(1)).setAccount(eq(MOCK_EMAIL), eq(MOCK_PASSWORD), eq(expectedLoginId), any(ProfileLevel.class));
    }

    @Test
    public void check_login_exception() throws DaoException {
        when(userDao.checkLogin(anyString(), anyString())).thenThrow(DaoException.class);

        try {
            service.checkLogin(MOCK_EMAIL, MOCK_PASSWORD);
        }catch (ServiceException e) {
            verify(userDao, times(1)).checkLogin(MOCK_EMAIL, MOCK_PASSWORD);
            verify(session, times(0)).setAccount(anyString(), anyString(), anyInt(), any(ProfileLevel.class));
        }
        //verify(notificationService, times(1)).getNewUserNotifications(any(Date.class));
        //verify(viewMessageNotification, times(1)).talkException(null);
    }

    @Test
    public void check_logout(){

        service.logoutLogin();

        verify(session, times(1)).resetAccount();
    }

}
