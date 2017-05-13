package com.bsuir.poit.studentcommunicator.login.presenter.unit;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.login.presenter.general.MockService;
import com.bsuir.poit.studentcommunicator.presenter.LoginPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginUnitTest {

    private IUserService userService;
    private ILoginView loginView;
    private ISession session;
    private LoginPresenter presenter;

    @Before
    public void initTest() {
        userService = getUserService();
        loginView = getLoginView();
        session = getSession();
        presenter = getPresenter(loginView, getServiceUnitOfWork(userService), session);
    }
    private static final String MOCK_LOGIN = "email";
    private static final String MOCK_PASSWORD = "email";

    private static LoginPresenter getPresenter(ILoginView loginView, IServiceUnitOfWork unitOfWork,
                                               ISession session){
        return new LoginPresenter(loginView, unitOfWork, session);
    }

    private static ISession getSession(){
        return mock(ISession.class);
    }

    private static ILoginView getLoginView(){
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getEmail()).thenReturn(MOCK_LOGIN);
        when(loginView.getPassword()).thenReturn(MOCK_PASSWORD);
        return loginView;
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IUserService userService){
        return new MockService.SeviceUOFBuilder().setUserService(userService).build();
    }

    private static IUserService getUserService(){
        return MockService.getUserService();
    }

    @Test
    public void login_access() throws ServiceException {
        final boolean exceptedValue = true;
        when(userService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);

        presenter.checkLogin();

        verify(loginView, times(1)).getEmail();
        verify(loginView, times(1)).getPassword();
        verify(userService, times(1)).checkLogin(anyString(), anyString());
        verify(loginView).loginComplete(exceptedValue);
    }

    @Test
    public void session_updated() throws ServiceException {
        final boolean exceptedValue = true;
        when(userService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);

        presenter.checkLogin();

        verify(session, times(1)).setAccount(MOCK_LOGIN, MOCK_PASSWORD);
    }


    @Test
    public void session_no_login() throws ServiceException {
        final boolean exceptedValue = false;
        when(userService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);

        presenter.checkLogin();

        verify(session, times(0)).setAccount(MOCK_LOGIN, MOCK_PASSWORD);
    }

    @Test
    public void login_exception() throws ServiceException{
        when(userService.checkLogin(anyString(), anyString())).thenThrow(ServiceException.class);

        presenter.checkLogin();

        verify(userService, times(1)).checkLogin(MOCK_LOGIN, MOCK_PASSWORD);
        verify(loginView, times(1)).talkException(null);
    }
}
