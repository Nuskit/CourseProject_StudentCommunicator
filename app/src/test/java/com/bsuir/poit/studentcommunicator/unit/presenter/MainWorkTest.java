package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.presenter.impl.MainWorkActivityPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IMainWorkView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bsuir.poit.studentcommunicator.general.MockDate.getDateManager;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainWorkTest {
    private IMainWorkView mainWorkView;
    private INotificationService notificationService;
    private MainWorkActivityPresenter presenter;

    @Before
    public void initTest(){
        mainWorkView = getMainWorkView();
        notificationService = getNotificationService();
        presenter = getPresenter(mainWorkView, getServiceUnitOfWork(notificationService), getDateManager());
    }

    private static MainWorkActivityPresenter getPresenter(IMainWorkView mainWorkView,
                                                          IServiceUnitOfWork unitOfWork,
                                                          DateManager dateManager){
        return new MainWorkActivityPresenter(mainWorkView ,unitOfWork, dateManager);
    }

    private static IMainWorkView getMainWorkView(){
        return mock(IMainWorkView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(INotificationService notificationService) {
        return new MockService.ServiceUOFBuilder()
                .setNotificationService(notificationService)
                .build();
    }

    private INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    @Test
    public void update_notification_messages() throws ServiceException {
        final boolean expectedUpdated = true;
        when(notificationService.haveNewNotificationMessages()).thenReturn(expectedUpdated);

        presenter.updateNotificationMessages();

        verify(notificationService, times(1)).haveNewNotificationMessages();
        verify(mainWorkView, times(1)).setBarNotification(expectedUpdated);
    }

    @Test
    public void update_notification_messages_exception() throws ServiceException {
        when(notificationService.haveNewNotificationMessages()).thenThrow(ServiceException.class);

        presenter.updateNotificationMessages();

        verify(notificationService, times(1)).haveNewNotificationMessages();
        verify(mainWorkView, times(1)).talkException(null);
    }
}
