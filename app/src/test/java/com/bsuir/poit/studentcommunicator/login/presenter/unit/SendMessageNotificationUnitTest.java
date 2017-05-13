package com.bsuir.poit.studentcommunicator.login.presenter.unit;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.login.presenter.general.MockService;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.presenter.SendMessageNotificationPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ISendMessageNotificationView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SendMessageNotificationUnitTest {

    private INotifierService notifierService;
    private ISendMessageNotificationView sendMessageNotificationView;
    private ISession session;
    private SendMessageNotificationPresenter presenter;

    @Before
    public void initTest() {
        notifierService = getNotifierService();
        sendMessageNotificationView = getSendMessageNotificationView();
        session = getSession();
        presenter = getPresenter(sendMessageNotificationView, getServiceUnitOfWork(notifierService),
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

    private static IServiceUnitOfWork getServiceUnitOfWork(INotifierService notifierService) {
        return new MockService.SeviceUOFBuilder().setNotifierService(notifierService).build();
    }

    private static INotifierService getNotifierService(){
        return MockService.getNotifierService();
    }

    private static final String MOCK_REASON = "test_reason";
    private static final String MOCK_MESSAGE = "test_message";

    private MessageNotification getExpectedMessageNotification(){
        return new MessageNotification(
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
        when(notifierService.sendNotifyMessage(any(MessageNotification.class))).thenReturn(expectedSend);

        presenter.sendNotifyMessage();

        verify(notifierService, times(1)).sendNotifyMessage(any(MessageNotification.class));
        verify(sendMessageNotificationView, times(1)).sendNotifyMessageCompleted(expectedSend);
    }

    @Test
    public void send_notify_exception() throws ServiceException {
        when(notifierService.sendNotifyMessage(any(MessageNotification.class))).thenThrow(ServiceException.class);

        presenter.sendNotifyMessage();

        verify(notifierService, times(1)).sendNotifyMessage(any(MessageNotification.class));
        verify(sendMessageNotificationView, times(1)).talkException(null);
    }


    @Test
    public void send_notify_check_message() throws ServiceException {
        List<Receiver> expectedListReceivers = getExpectedListReceiver();
        MessageNotification expectedMessageNotification = getExpectedMessageNotification();
        when(sendMessageNotificationView.getReason()).thenReturn(MOCK_REASON);
        when(sendMessageNotificationView.getMessage()).thenReturn(MOCK_MESSAGE);
        when(sendMessageNotificationView.getChoicedReceivers()).thenReturn(expectedListReceivers);

        presenter.sendNotifyMessage();

        verify(sendMessageNotificationView, times(1)).getReason();
        verify(session, times(1)).getAuthorId();
        verify(sendMessageNotificationView, times(1)).getMessage();
        verify(sendMessageNotificationView, times(1)).getChoicedReceivers();
        verify(notifierService, times(1)).sendNotifyMessage(expectedMessageNotification);
    }
}
