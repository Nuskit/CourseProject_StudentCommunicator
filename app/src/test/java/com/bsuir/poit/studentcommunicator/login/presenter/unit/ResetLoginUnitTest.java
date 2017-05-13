package com.bsuir.poit.studentcommunicator.login.presenter.unit;

import com.bsuir.poit.studentcommunicator.login.presenter.general.MockService;
import com.bsuir.poit.studentcommunicator.presenter.ResetLoginPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IResetLoginView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResetLoginUnitTest {

    private IUserService userService;
    private IResetLoginView resetLoginView;
    private ResetLoginPresenter presenter;

    @Before
    public void initTest(){
        userService = getUserService();
        resetLoginView = getResetLoginView();
        presenter = getPresenter(resetLoginView, getServiceUnitOfWork(userService));
    }
    
    private static final String MOCK_EMAIL = "email";

    private static ResetLoginPresenter getPresenter(IResetLoginView resetLoginView, IServiceUnitOfWork unitOfWork){
        return new ResetLoginPresenter(resetLoginView, unitOfWork);
    }

    private static IResetLoginView getResetLoginView(){
        IResetLoginView resetLoginView = mock(IResetLoginView.class);
        when(resetLoginView.getEmail()).thenReturn(MOCK_EMAIL);
        return resetLoginView;
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IUserService userService){
        return new MockService.SeviceUOFBuilder().setUserService(userService).build();
    }

    private static IUserService getUserService(){
        return MockService.getUserService();
    }

    @Test
    public void reset_success() throws ServiceException {
        final boolean exceptedValue = true;
        when(userService.resetLogin(anyString())).thenReturn(exceptedValue);

        presenter.resetLogin();

        verify(resetLoginView, times(1)).getEmail();
        verify(userService, times(1)).resetLogin(MOCK_EMAIL);
        verify(resetLoginView).resetLoginCompleted(exceptedValue);
    }

    @Test
    public void reset_exception() throws ServiceException{
        when(userService.resetLogin(anyString())).thenThrow(ServiceException.class);

        presenter.resetLogin();

        verify(userService, times(1)).resetLogin(anyString());
        verify(resetLoginView, times(1)).talkException(null);
    }
}
