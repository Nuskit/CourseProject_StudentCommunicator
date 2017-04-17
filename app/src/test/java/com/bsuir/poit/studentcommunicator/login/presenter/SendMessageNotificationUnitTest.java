package com.bsuir.poit.studentcommunicator.login.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.model.MessageNotification;
import com.bsuir.poit.studentcommunicator.model.Receiver;
import com.bsuir.poit.studentcommunicator.presenter.SendMessageNotificationPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.ISendMessageNotificationView;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SendMessageNotificationUnitTest {
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
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getNotifierService()).thenReturn(notifierService);
        return unitOfWork;
    }

    private static INotifierService getNotifierService(){
        return mock(INotifierService.class);
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

    private List<Receiver> getExpectedListReceiver(){
        return new ArrayList<Receiver>(){
            {
                add(getExpectedReceiver());
            }
        };
    }

    private Receiver getExpectedReceiver(){
        return new Receiver(MOCK_RECEIVER_ID, MOCK_RECEIVER_NAME);
    }

    @Test
    public void send_notify_sucess() throws ServiceException {
        boolean expectedSended = Boolean.TRUE;
        INotifierService mockService = getNotifierService();
        when(mockService.sendNotifyMessage(any(MessageNotification.class))).thenReturn(expectedSended);
        ISendMessageNotificationView sendMessageNotificationView = getSendMessageNotificationView();
        ISession session = getSession();
        SendMessageNotificationPresenter presenter = getPresenter(sendMessageNotificationView,
                getServiceUnitOfWork(mockService), session);

        presenter.sendNotifyMessage();

        verify(mockService, times(1)).sendNotifyMessage(any(MessageNotification.class));
        verify(sendMessageNotificationView, times(1)).sendNotifyMessageCompleted(expectedSended);
    }

    @Test
    public void send_notify_exception() throws ServiceException {
        INotifierService mockService = getNotifierService();
        when(mockService.sendNotifyMessage(any(MessageNotification.class))).thenThrow(ServiceException.class);
        ISendMessageNotificationView sendMessageNotificationView = getSendMessageNotificationView();
        ISession session = getSession();
        SendMessageNotificationPresenter presenter = getPresenter(sendMessageNotificationView,
                getServiceUnitOfWork(mockService), session);

        presenter.sendNotifyMessage();

        verify(mockService, times(1)).sendNotifyMessage(any(MessageNotification.class));
        verify(sendMessageNotificationView, times(1)).talkException(null);
    }


    @Test
    public void send_notify_check_message() throws ServiceException {
        List<Receiver> expectedListReceivers = getExpectedListReceiver();
        MessageNotification expectedMessageNotification = getExpectedMessageNotification();
        INotifierService mockService = getNotifierService();
        ISendMessageNotificationView sendMessageNotificationView = getSendMessageNotificationView();
        when(sendMessageNotificationView.getReason()).thenReturn(MOCK_REASON);
        when(sendMessageNotificationView.getMessage()).thenReturn(MOCK_MESSAGE);
        when(sendMessageNotificationView.getChoicedReceivers()).thenReturn(expectedListReceivers);
        ISession session = getSession();
        SendMessageNotificationPresenter presenter = getPresenter(sendMessageNotificationView,
                getServiceUnitOfWork(mockService), session);

        presenter.sendNotifyMessage();
        verify(sendMessageNotificationView, times(1)).getReason();
        verify(session, times(1)).getAuthorId();
        verify(sendMessageNotificationView, times(1)).getMessage();
        verify(sendMessageNotificationView, times(1)).getChoicedReceivers();
        verify(mockService, times(1)).sendNotifyMessage(expectedMessageNotification);
    }
}
