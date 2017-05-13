package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.unit.presenter.impl.ViewMessageNotificationPresenter;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewMessageNotificationTest {

    private INotificationService notificationService;
    private IViewMessageNotificationView viewMessageNotification;
    private ISession session;
    private ViewMessageNotificationPresenter presenter;

    @Before
    public void initTest() {
        notificationService = getNotificationService();
        viewMessageNotification = getViewMessageNotificationView();
        session = getSession();
        presenter = getPresenter(viewMessageNotification, getServiceUnitOfWork(notificationService),
                session);
    }

    private static ViewMessageNotificationPresenter getPresenter(
            IViewMessageNotificationView viewMessageNotificationView,
            IServiceUnitOfWork unitOfWork, ISession session){
        return new ViewMessageNotificationPresenter(viewMessageNotificationView, unitOfWork, session);
    }

    private static final int MOCK_AUTHOR_ID = 1;

    private static ISession getSession(){
        ISession session = mock(ISession.class);
        when(session.getAuthorId()).thenReturn(MOCK_AUTHOR_ID);
        return session;
    }

    private static IViewMessageNotificationView getViewMessageNotificationView(){
        return mock(IViewMessageNotificationView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(INotificationService notificationService) {
        return new MockService.SeviceUOFBuilder().setNotificationService(notificationService).build();
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

    private static final int MOCK_RECEIVER_ID = 2;
    private static final String MOCK_RECEIVER_NAME = "Jon";

    private List<Receiver> getExpectedListReceiver() {
        return new ArrayList<Receiver>() {{
            add(getExpectedReceiver());
        }};
    }

    private Receiver getExpectedReceiver(){
        return new Receiver(MOCK_RECEIVER_ID, MOCK_RECEIVER_NAME);
    }

    @Test
    public void show_new_messages() throws ServiceException {
        List<MessageNotification> expectedMessageNotification = getExpectedListMessageNotification();
        when(notificationService.getNewUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class)))
                .thenReturn(expectedMessageNotification);

        presenter.loadNewMessageNotifications();

        verify(notificationService, times(1)).getNewUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class));
        verify(viewMessageNotification, times(1)).showNewMessageNotifications(expectedMessageNotification);
    }

    @Test
    public void show_new_messages_exception() throws ServiceException {
        when(notificationService.getNewUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class)))
                .thenThrow(ServiceException.class);

        presenter.loadNewMessageNotifications();

        verify(notificationService, times(1)).getNewUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class));
        verify(viewMessageNotification, times(1)).talkException(null);
    }

    @Test
    public void show_old_messages() throws ServiceException {
        List<MessageNotification> expectedMessageNotification = getExpectedListMessageNotification();
        when(notificationService.getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class)))
                .thenReturn(expectedMessageNotification);

        presenter.loadOldMessageNotifications();

        verify(notificationService, times(1)).getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class));
        verify(viewMessageNotification, times(1)).showOldMessages(expectedMessageNotification);
    }

    @Test
    public void show_old_messages_exception() throws ServiceException {
        when(notificationService.getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class)))
                .thenThrow(ServiceException.class);

        presenter.loadOldMessageNotifications();

        verify(notificationService, times(1)).getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class));
        verify(viewMessageNotification, times(1)).talkException(null);
    }

    @Test
    public void show_zero_old_messages() throws ServiceException {
        List<MessageNotification> expectedEmptyMessageNotification = getEmptyListMessageNotification();
        when(notificationService.getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class)))
                .thenReturn(getExpectedListMessageNotification(), expectedEmptyMessageNotification);

        presenter.loadOldMessageNotifications();
        presenter.loadOldMessageNotifications();

        verify(notificationService, times(2)).getOldUserNotifications(eq(MOCK_AUTHOR_ID), any(Date.class));
        verify(viewMessageNotification, times(2)).showOldMessages(ArgumentMatchers.<List<MessageNotification>>any());
        verify(viewMessageNotification, times(1)).showOldMessages(expectedEmptyMessageNotification);
    }
}
