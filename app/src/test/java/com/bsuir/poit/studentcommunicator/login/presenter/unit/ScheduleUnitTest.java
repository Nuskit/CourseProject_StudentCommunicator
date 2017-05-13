package com.bsuir.poit.studentcommunicator.login.presenter.unit;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.login.presenter.general.MockService;
import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.presenter.SchedulePresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
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
public class ScheduleUnitTest {

    private IScheduleView scheduleView;
    private INotifierService notifierService;
    private IScheduleService scheduleService;
    private SchedulePresenter presenter;

    @Before
    public void initTest(){
        scheduleView = getScheduleView();
        notifierService = getNotifierService();
        scheduleService = getScheduleService();
        presenter = getPresenter(scheduleView,
                getServiceUnitOfWork(scheduleService, notifierService), getSession());
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
                                                           INotifierService notifierService) {
        return new MockService.SeviceUOFBuilder()
                .setScheduleService(scheduleService)
                .setNotifierService(notifierService)
                .build();
    }

    private static IUserService getUserService() {
        return MockService.getUserService();
    }

    private IScheduleService getScheduleService() {
        return MockService.getScheduleService();
    }

    private INotifierService getNotifierService(){
        return MockService.getNotifierService();
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

    private List<Lesson> getExpectedHaveLessonNotifiers() {
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
    public void update_lesson_notifiers() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        List<Lesson> expectedLessonNotifiers = getExpectedHaveLessonNotifiers();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(notifierService.haveNewLessonNotifiers(any(Date.class))).thenReturn(expectedLessonNotifiers);

        presenter.updateLessonsNotifier();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notifierService, times(1)).haveNewLessonNotifiers(expectedDate);
        verify(scheduleView, times(1)).updateLessonsNotifier(expectedDate, expectedLessonNotifiers);
    }

    @Test
    public void update_lesson_notifiers_exception() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        when(notifierService.haveNewLessonNotifiers(any(Date.class))).thenThrow(ServiceException.class);

        presenter.updateLessonsNotifier();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notifierService, times(1)).haveNewLessonNotifiers(expectedDate);
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void load_lesson_notification() throws ServiceException {
        List<LessonNotification> expectedLessonNotifications = getExpectedLessonNotifications();
        Lesson expectedLesson = getExpectedLesson();
        when(notifierService.getLessonNotifiers(any(Lesson.class))).thenReturn(expectedLessonNotifications);

        presenter.loadLessonNotification(expectedLesson);

        verify(notifierService, times(1)).getLessonNotifiers(expectedLesson);
        verify(scheduleView, times(1)).setLessonNotifiers(expectedLesson, expectedLessonNotifications);
    }

    @Test
    public void load_lesson_notification_exception() throws ServiceException {
        when(notifierService.getLessonNotifiers(any(Lesson.class))).thenThrow(ServiceException.class);

        presenter.loadLessonNotification(getExpectedLesson());

        verify(notifierService, times(1)).getLessonNotifiers(any(Lesson.class));
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void update_notifier_messages() throws ServiceException {
        final boolean expectedUpdated = true;
        when(notifierService.haveNewNotifierMessages()).thenReturn(expectedUpdated);

        presenter.updateNotifierMessages();

        verify(notifierService, times(1)).haveNewNotifierMessages();
        verify(scheduleView, times(1)).setBarNotifier(expectedUpdated);
    }

    @Test
    public void update_notifier_messages_exception() throws ServiceException {
        when(notifierService.haveNewNotifierMessages()).thenThrow(ServiceException.class);

        presenter.updateNotifierMessages();

        verify(notifierService, times(1)).haveNewNotifierMessages();
        verify(scheduleView, times(1)).talkException(null);
    }
}
