package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.model.SendMessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.unit.presenter.impl.SendMessageNotificationPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ISendMessageNotificationView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SendMessageNotificationTest {

    private INotificationService notificationService;
    private ISendMessageNotificationView sendMessageNotificationView;
    private ISession session;
    private SendMessageNotificationPresenter presenter;

    @Before
    public void initTest() {
        notificationService = getNotificationService();
        sendMessageNotificationView = getSendMessageNotificationView();
        session = getSession();
        presenter = getPresenter(sendMessageNotificationView, getServiceUnitOfWork(notificationService),
                session);
    }

    private static SendMessageNotificationPresenter getPresenter(
            ISendMessageNotificationView sendMessageNotificationView,
            IServiceUnitOfWork unitOfWork, ISession session){
        return new SendMessageNotificationPresenter(sendMessageNotificationView, unitOfWork, session);
    }
    
    private static final int MOCK_AUTHOR_ID = 1;

    private static ISession getSession(){
        ISession session = mock(ISession.class);
        when(session.getAuthorId()).thenReturn(MOCK_AUTHOR_ID);
        return session;
    }

    private static ISendMessageNotificationView getSendMessageNotificationView(){
        return mock(ISendMessageNotificationView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(INotificationService notificationService) {
        return new MockService.SeviceUOFBuilder().setNotificationService(notificationService).build();
    }

    private static INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static final String MOCK_REASON = "test_reason";
    private static final String MOCK_MESSAGE = "test_message";

    private SendMessageNotification getExpectedSendMessageNotification(){
        return new SendMessageNotification(
                MOCK_REASON, MOCK_AUTHOR_ID, MOCK_MESSAGE, getExpectedListReceiver()
        );
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
    public void send_notify_success() throws ServiceException {
        final boolean expectedSend = true;
        when(notificationService.sendNotifyMessage(any(SendMessageNotification.class))).thenReturn(expectedSend);

        presenter.sendNotifyMessage();

        verify(notificationService, times(1)).sendNotifyMessage(any(SendMessageNotification.class));
        verify(sendMessageNotificationView, times(1)).sendNotifyMessageCompleted(expectedSend);
    }

    @Test
    public void send_notify_exception() throws ServiceException {
        when(notificationService.sendNotifyMessage(any(SendMessageNotification.class))).thenThrow(ServiceException.class);

        presenter.sendNotifyMessage();

        verify(notificationService, times(1)).sendNotifyMessage(any(SendMessageNotification.class));
        verify(sendMessageNotificationView, times(1)).talkException(null);
    }


    @Test
    public void send_notify_check_message() throws ServiceException {
        List<Receiver> expectedListReceivers = getExpectedListReceiver();
        SendMessageNotification expectedSendMessageNotification = getExpectedSendMessageNotification();
        when(sendMessageNotificationView.getReason()).thenReturn(MOCK_REASON);
        when(sendMessageNotificationView.getMessage()).thenReturn(MOCK_MESSAGE);
        when(sendMessageNotificationView.getSelectedReceivers()).thenReturn(expectedListReceivers);

        presenter.sendNotifyMessage();

        verify(sendMessageNotificationView, times(1)).getReason();
        verify(session, times(1)).getAuthorId();
        verify(sendMessageNotificationView, times(1)).getMessage();
        verify(sendMessageNotificationView, times(1)).getSelectedReceivers();
        verify(notificationService, times(1)).sendNotifyMessage(expectedSendMessageNotification);
    }
}
