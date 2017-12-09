package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.presenter.impl.ViewMessageNotificationPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IViewMessageNotificationView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

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

public class ViewMessageNotificationTest {

    private INotificationService notificationService;
    private IViewMessageNotificationView viewMessageNotification;
    private ViewMessageNotificationPresenter presenter;

    @Before
    public void initTest() {
        notificationService = getNotificationService();
        viewMessageNotification = getViewMessageNotificationView();
        presenter = getPresenter(viewMessageNotification, getServiceUnitOfWork(notificationService),
                getDateManager());
    }

    private static ViewMessageNotificationPresenter getPresenter(
            IViewMessageNotificationView viewMessageNotificationView, IServiceUnitOfWork unitOfWork,
            DateManager dateManager){
        return new ViewMessageNotificationPresenter(viewMessageNotificationView, unitOfWork, dateManager);
    }

    private static IViewMessageNotificationView getViewMessageNotificationView(){
        return mock(IViewMessageNotificationView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(INotificationService notificationService) {
        return new MockService.ServiceUOFBuilder().setNotificationService(notificationService).build();
    }

    private static INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static final String MOCK_REASON = "test_reason";
    private static final String MOCK_MESSAGE = "test_message";
    private static final String MOCK_OTHER_AUTHOR = "JONI";
    private static final Date MOCK_DATE = Calendar.getInstance().getTime();

    private MessageNotification getExpectedMessageNotification(){
        return new MessageNotification(MOCK_REASON, MOCK_OTHER_AUTHOR, MOCK_DATE, MOCK_MESSAGE);
    }

    private List<MessageNotification> getExpectedListMessageNotification(){
        return new ArrayList<MessageNotification>(){{
            add(getExpectedMessageNotification());
        }};
    }

    private List<MessageNotification> getEmptyListMessageNotification(){
        return new ArrayList<>();
    }

    @Test
    public void show_new_messages() throws ServiceException {
        List<MessageNotification> expectedMessageNotification = getExpectedListMessageNotification();
        when(notificationService.getNewUserNotifications(any(Date.class)))
                .thenReturn(expectedMessageNotification);

        presenter.loadNewMessageNotifications();

        verify(notificationService, times(1)).getNewUserNotifications(any(Date.class));
        verify(viewMessageNotification, times(1)).showNewMessageNotifications(expectedMessageNotification);
    }

    @Test
    public void show_new_messages_exception() throws ServiceException {
        when(notificationService.getNewUserNotifications(any(Date.class)))
                .thenThrow(ServiceException.class);

        presenter.loadNewMessageNotifications();

        verify(notificationService, times(1)).getNewUserNotifications(any(Date.class));
        verify(viewMessageNotification, times(1)).talkException(null);
    }

    @Test
    public void show_old_messages() throws ServiceException {
        List<MessageNotification> expectedMessageNotification = getExpectedListMessageNotification();
        when(notificationService.getOldUserNotifications(ArgumentMatchers.<Date>any()))
                .thenReturn(expectedMessageNotification);

        presenter.loadOldMessageNotifications();

        verify(notificationService, times(1)).getOldUserNotifications(ArgumentMatchers.<Date>any());
        verify(viewMessageNotification, times(1)).showOldMessages(expectedMessageNotification);
    }

    @Test
    public void show_old_messages_exception() throws ServiceException {
        when(notificationService.getOldUserNotifications(ArgumentMatchers.<Date>any()))
                .thenThrow(ServiceException.class);

        presenter.loadOldMessageNotifications();

        verify(notificationService, times(1)).getOldUserNotifications(ArgumentMatchers.<Date>any());
        verify(viewMessageNotification, times(1)).talkException(null);
    }

    @Test
    public void show_zero_old_messages() throws ServiceException {
        List<MessageNotification> expectedMessageNotification = getExpectedListMessageNotification();
        List<MessageNotification> expectedEmptyMessageNotification = getEmptyListMessageNotification();

        //first not important
        when(notificationService.getOldUserNotifications(ArgumentMatchers.<Date>any()))
                .thenReturn(expectedMessageNotification, expectedEmptyMessageNotification);

        presenter.loadOldMessageNotifications();
        presenter.loadOldMessageNotifications();

        verify(notificationService, times(2)).getOldUserNotifications(ArgumentMatchers.<Date>any());
        verify(viewMessageNotification, times(2)).showOldMessages(ArgumentMatchers.<List<MessageNotification>>any());
        verify(viewMessageNotification, times(1)).showOldMessages(expectedEmptyMessageNotification);
    }
}
