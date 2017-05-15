package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.presenter.impl.ScheduleFragmentPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
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
    private ScheduleFragmentPresenter presenter;

    @Before
    public void initTest(){
        scheduleView = getScheduleView();
        notificationService = getNotificationService();
        scheduleService = getScheduleService();
        presenter = getPresenter(scheduleView,
                getServiceUnitOfWork(scheduleService, notificationService));
    }

    private static ScheduleFragmentPresenter getPresenter(IScheduleView scheduleView,
                                                          IServiceUnitOfWork unitOfWork){
        ScheduleFragmentPresenter scheduleFragmentPresenter = new ScheduleFragmentPresenter(unitOfWork);
        scheduleFragmentPresenter.init(scheduleView);
        return scheduleFragmentPresenter;
    }

    private static IScheduleView getScheduleView(){
        return mock(IScheduleView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IScheduleService scheduleService,
                                                           INotificationService notificationService) {
        return new MockService.ServiceUOFBuilder()
                .setScheduleService(scheduleService)
                .setNotificationService(notificationService)
                .build();
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

    private static List<LessonSchedule> getExpectedLessons() {
        return new ArrayList<LessonSchedule>() {{
            add(getExpectedLesson());
        }};
    }

    private List<LessonSchedule> getExpectedHaveLessonNotifications() {
        return new ArrayList<LessonSchedule>() {{
            add(getExpectedLesson());
        }};
    }

    private static LessonSchedule getExpectedLesson(){
        return new LessonSchedule(MOCK_TIME, MOCK_TYPE, MOCK_NAME, MOCK_TEACHER, MOCK_GROUP,
                MOCK_POSITION, getExpectedLessonNotifications());
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
        List<LessonSchedule> expectedLessonSchedules = getExpectedLessons();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(scheduleService.getLessons(any(Date.class))).thenReturn(expectedLessonSchedules);

        presenter.loadLessonsToday();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(scheduleService, times(1)).getLessons(expectedDate);
        verify(scheduleView, times(1)).setSchedule(expectedLessonSchedules);
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
        List<LessonSchedule> expectedLessonScheduleNotifications = getExpectedHaveLessonNotifications();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(notificationService.haveNewLessonNotifications(any(Date.class))).thenReturn(expectedLessonScheduleNotifications);

        presenter.updateLessonsNotification();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notificationService, times(1)).haveNewLessonNotifications(expectedDate);
        verify(scheduleView, times(1)).updateLessonsNotification(expectedDate, expectedLessonScheduleNotifications);
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
        LessonSchedule expectedLessonSchedule = getExpectedLesson();
        when(notificationService.getLessonNotifications(any(LessonSchedule.class))).thenReturn(expectedLessonNotifications);

        presenter.loadLessonNotification(expectedLessonSchedule);

        verify(notificationService, times(1)).getLessonNotifications(expectedLessonSchedule);
        verify(scheduleView, times(1)).setLessonNotifications(expectedLessonSchedule, expectedLessonNotifications);
    }

    @Test
    public void load_lesson_notification_exception() throws ServiceException {
        when(notificationService.getLessonNotifications(any(LessonSchedule.class))).thenThrow(ServiceException.class);

        presenter.loadLessonNotification(getExpectedLesson());

        verify(notificationService, times(1)).getLessonNotifications(any(LessonSchedule.class));
        verify(scheduleView, times(1)).talkException(null);
    }
}
