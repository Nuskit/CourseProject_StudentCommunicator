package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.presenter.impl.LoginPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginTest {

    private IUserService userService;
    private ILoginView loginView;
    private LoginPresenter presenter;

    @Before
    public void initTest() {
        userService = getUserService();
        loginView = getLoginView();
        presenter = getPresenter(loginView, getServiceUnitOfWork(userService));
    }
    private static final String MOCK_LOGIN = "email";
    private static final String MOCK_PASSWORD = "email";

    private static LoginPresenter getPresenter(ILoginView loginView, IServiceUnitOfWork unitOfWork){
        return new LoginPresenter(loginView, unitOfWork);
    }

    private static ILoginView getLoginView(){
        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getEmail()).thenReturn(MOCK_LOGIN);
        when(loginView.getPassword()).thenReturn(MOCK_PASSWORD);
        return loginView;
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IUserService userService){
        return new MockService.ServiceUOFBuilder().setUserService(userService).build();
    }

    private static IUserService getUserService(){
        return MockService.getUserService();
    }

    @Test
    public void login_access() throws ServiceException {
        final boolean exceptedValue = true;
        when(userService.checkLogin(MOCK_LOGIN, MOCK_PASSWORD)).thenReturn(exceptedValue);

        presenter.checkLogin();

        verify(loginView, times(1)).getEmail();
        verify(loginView, times(1)).getPassword();
        verify(userService, times(1)).checkLogin(MOCK_LOGIN, MOCK_PASSWORD);
        verify(loginView).loginComplete(exceptedValue);
    }

    @Test
    public void login_exception() throws ServiceException{
        when(userService.checkLogin(anyString(), anyString())).thenThrow(ServiceException.class);

        presenter.checkLogin();

        verify(userService, times(1)).checkLogin(MOCK_LOGIN, MOCK_PASSWORD);
        verify(loginView, times(1)).talkException(null);
    }
}
