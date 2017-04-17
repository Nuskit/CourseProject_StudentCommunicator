package com.bsuir.poit.studentcommunicator.login.presenter;

import com.bsuir.poit.studentcommunicator.presenter.ResetLoginPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IResetLoginView;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResetLoginUnitTest {
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
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getUserService()).thenReturn(userService);
        return unitOfWork;
    }

    private static IUserService getUserService(){
        return mock(IUserService.class);
    }

    @Test
    public void reset_success() throws ServiceException {
        boolean exceptedValue = Boolean.TRUE;
        IUserService mockService = getUserService();
        IResetLoginView resetLoginView = getResetLoginView();
        when(mockService.resetLogin(anyString())).thenReturn(exceptedValue);
        ResetLoginPresenter presenter = getPresenter(resetLoginView, getServiceUnitOfWork(mockService));

        presenter.resetLogin();

        verify(resetLoginView, times(1)).getEmail();
        verify(mockService, times(1)).resetLogin(MOCK_EMAIL);
        verify(resetLoginView).resetLoginCompleted(exceptedValue);
    }

    @Test
    public void reset_exception() throws ServiceException{
        IUserService mockService = getUserService();
        IResetLoginView resetLoginView = getResetLoginView();
        when(mockService.resetLogin(anyString())).thenThrow(ServiceException.class);
        ResetLoginPresenter presenter = getPresenter(resetLoginView, getServiceUnitOfWork(mockService));

        presenter.resetLogin();

        verify(mockService, times(1)).resetLogin(anyString());
        verify(resetLoginView, times(1)).talkException(null);
    }
}
