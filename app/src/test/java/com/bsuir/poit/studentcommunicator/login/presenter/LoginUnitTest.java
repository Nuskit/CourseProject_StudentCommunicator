package com.bsuir.poit.studentcommunicator.login.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.presenter.LoginPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import org.junit.Test;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginUnitTest {
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
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getUserService()).thenReturn(userService);
        return unitOfWork;
    }

    private static IUserService getUserService(){
        return mock(IUserService.class);
    }

    @Test
    public void login_acess() throws ServiceException {
        boolean exceptedValue = Boolean.TRUE;
        IUserService mockService = getUserService();
        ILoginView loginView = getLoginView();
        when(mockService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);
        LoginPresenter presenter = getPresenter(loginView, getServiceUnitOfWork(mockService),
                getSession());

        presenter.checkLogin();

        verify(loginView, times(1)).getEmail();
        verify(loginView, times(1)).getPassword();
        verify(mockService, times(1)).checkLogin(anyString(), anyString());
        verify(loginView).loginComplete(exceptedValue);
    }

    @Test
    public void session_updated() throws ServiceException {
        boolean exceptedValue = Boolean.TRUE;
        IUserService mockService = getUserService();
        ILoginView loginView = getLoginView();
        ISession session = getSession();
        when(mockService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);
        LoginPresenter presenter = getPresenter(loginView, getServiceUnitOfWork(mockService), session);

        presenter.checkLogin();

        verify(session, times(1)).setAccount(MOCK_LOGIN, MOCK_PASSWORD);
    }


    @Test
    public void session_no_logined() throws ServiceException {
        boolean exceptedValue = Boolean.FALSE;
        IUserService mockService = getUserService();
        ILoginView loginView = getLoginView();
        ISession session = getSession();
        when(mockService.checkLogin(anyString(), anyString())).thenReturn(exceptedValue);
        LoginPresenter presenter = getPresenter(loginView, getServiceUnitOfWork(mockService), session);

        presenter.checkLogin();

        verify(session, times(0)).setAccount(MOCK_LOGIN, MOCK_PASSWORD);
    }

    @Test
    public void login_exception() throws ServiceException{
        IUserService mockService = getUserService();
        ILoginView loginView = getLoginView();
        when(mockService.checkLogin(anyString(), anyString())).thenThrow(ServiceException.class);
        LoginPresenter presenter = getPresenter(loginView, getServiceUnitOfWork(mockService), getSession());

        presenter.checkLogin();

        verify(mockService, times(1)).checkLogin(MOCK_LOGIN, MOCK_PASSWORD);
        verify(loginView, times(1)).talkException(null);
    }
}
