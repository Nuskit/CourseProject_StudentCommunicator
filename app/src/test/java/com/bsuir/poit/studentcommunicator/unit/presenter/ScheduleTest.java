package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.unit.presenter.impl.SchedulePresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO: test onCreate presenter method
public class ScheduleTest {

    private IScheduleView scheduleView;
    private INotificationService notificationService;
    private IScheduleService scheduleService;
    private SchedulePresenter presenter;

    @Before
    public void initTest(){
        scheduleView = getScheduleView();
        notificationService = getNotificationService();
        scheduleService = getScheduleService();
        presenter = getPresenter(scheduleView,
                getServiceUnitOfWork(scheduleService, notificationService), getSession());
    }

    private static SchedulePresenter getPresenter(IScheduleView scheduleView, IServiceUnitOfWork unitOfWork,
                                                  ISession session){
        return new SchedulePresenter(scheduleView, unitOfWork, session);
    }

    private static ISession getSession(){
        return mock(ISession.class);
    }

    private static IScheduleView getScheduleView(){
        return mock(IScheduleView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IScheduleService scheduleService,
                                                           INotificationService notificationService) {
        return new MockService.SeviceUOFBuilder()
                .setScheduleService(scheduleService)
                .setNotificationService(notificationService)
                .build();
    }

    private static IUserService getUserService() {
        return MockService.getUserService();
    }

    private IScheduleService getScheduleService() {
        return MockService.getScheduleService();
    }

    private INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static final String MOCK_TIME = "15.02.1992";
    private static final String MOCK_TYPE = "lk";
    private static final String MOCK_NAME = "SPP";
    private static final String MOCK_TEACHER = "Jonatan";
    private static final String MOCK_GROUP = "56FK12";
    //TODO: fill test subgroups
    private static final List<String> MOCK_SUBGROUPS = null;
    private static final String MOCK_POSITION = "512-b";

    private static List<Lesson> getExpectedLessons() {
        return new ArrayList<Lesson>() {{
            add(getExpectedLesson());
        }};
    }

    private List<Lesson> getExpectedHaveLessonNotifications() {
        return new ArrayList<Lesson>() {{
            add(getExpectedLesson());
        }};
    }

    private static Lesson getExpectedLesson(){
        return new Lesson(MOCK_TIME, MOCK_TYPE, MOCK_NAME, MOCK_TEACHER, MOCK_GROUP,
                MOCK_SUBGROUPS, MOCK_POSITION, getExpectedLessonNotifications());
    }

    private static List<LessonNotification> getExpectedLessonNotifications() {
        return new ArrayList<LessonNotification>() {{
            add(getExpectedLessonNotification());
        }};
    }

    private static final Date MOCK_DATE_TIME = Calendar.getInstance().getTime();
    private static final String MOCK_AUTHOR = "Jonni";
    private static final String MOCK_DESCRIPTION = "Some description";

    private static LessonNotification getExpectedLessonNotification(){
        return new LessonNotification(MOCK_DATE_TIME, MOCK_AUTHOR, MOCK_DESCRIPTION);
    }

    @Test
    public void load_lessons_date() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        List<Lesson> expectedLessons = getExpectedLessons();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(scheduleService.getLessons(any(Date.class))).thenReturn(expectedLessons);

        presenter.loadLessonsToday();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(scheduleService, times(1)).getLessons(expectedDate);
        verify(scheduleView, times(1)).setSchedule(expectedLessons);
    }

    @Test
    public void load_lessons_date_exception() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(scheduleService.getLessons(any(Date.class))).thenThrow(ServiceException.class);

        presenter.loadLessonsToday();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(scheduleService, times(1)).getLessons(expectedDate);
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void update_lesson_notifications() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        List<Lesson> expectedLessonNotifications = getExpectedHaveLessonNotifications();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(notificationService.haveNewLessonNotifications(any(Date.class))).thenReturn(expectedLessonNotifications);

        presenter.updateLessonsNotification();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notificationService, times(1)).haveNewLessonNotifications(expectedDate);
        verify(scheduleView, times(1)).updateLessonsNotification(expectedDate, expectedLessonNotifications);
    }

    @Test
    public void update_lesson_notifications_exception() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(notificationService.haveNewLessonNotifications(any(Date.class))).thenThrow(ServiceException.class);

        presenter.updateLessonsNotification();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notificationService, times(1)).haveNewLessonNotifications(expectedDate);
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void load_lesson_notification() throws ServiceException {
        List<LessonNotification> expectedLessonNotifications = getExpectedLessonNotifications();
        Lesson expectedLesson = getExpectedLesson();
        when(notificationService.getLessonNotifications(any(Lesson.class))).thenReturn(expectedLessonNotifications);

        presenter.loadLessonNotification(expectedLesson);

        verify(notificationService, times(1)).getLessonNotifications(expectedLesson);
        verify(scheduleView, times(1)).setLessonNotifications(expectedLesson, expectedLessonNotifications);
    }

    @Test
    public void load_lesson_notification_exception() throws ServiceException {
        when(notificationService.getLessonNotifications(any(Lesson.class))).thenThrow(ServiceException.class);

        presenter.loadLessonNotification(getExpectedLesson());

        verify(notificationService, times(1)).getLessonNotifications(any(Lesson.class));
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void update_notification_messages() throws ServiceException {
        final boolean expectedUpdated = true;
        when(notificationService.haveNewNotificationMessages()).thenReturn(expectedUpdated);

        presenter.updateNotificationMessages();

        verify(notificationService, times(1)).haveNewNotificationMessages();
        verify(scheduleView, times(1)).setBarNotification(expectedUpdated);
    }

    @Test
    public void update_notification_messages_exception() throws ServiceException {
        when(notificationService.haveNewNotificationMessages()).thenThrow(ServiceException.class);

        presenter.updateNotificationMessages();

        verify(notificationService, times(1)).haveNewNotificationMessages();
        verify(scheduleView, times(1)).talkException(null);
    }
}
