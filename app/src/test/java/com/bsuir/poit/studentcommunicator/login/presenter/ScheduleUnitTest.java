package com.bsuir.poit.studentcommunicator.login.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.presenter.SchedulePresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IScheduleView;

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

    private static IServiceUnitOfWork getServiceUnitOfWork(IScheduleService scheduleService) {
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getScheduleService()).thenReturn(scheduleService);
        return unitOfWork;
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(INotifierService notifierService){
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getNotifierService()).thenReturn(notifierService);
        return unitOfWork;
    }

    private static IUserService getUserService() {
        return mock(IUserService.class);
    }

    private IScheduleService getScheduleService() {
        return mock(IScheduleService.class);
    }

    private INotifierService getNotifierService(){
        return mock(INotifierService.class);
    }

    private static String MOCK_TIME = "15.02.1992";
    private static String MOCK_TYPE = "lk";
    private static String MOCK_NAME = "SPP";
    private static String MOCK_TEACHER = "Jonatan";
    private static String MOCK_GROUP = "56FK12";
    //TODO: fill test subgroups
    private static List<String> MOCK_SUBGROUPS = null;
    private static String MOCK_POSITION = "512-b";

    private static List<Lesson> getExpectedLessons(){
        return new ArrayList<Lesson>()
        {
            {add(getExpectedLesson());}
        };
    }

    private List<Lesson> getExpectedHaveLessonNotifiers() {
        return new ArrayList<Lesson>(){
            {
                add(getExpectedLesson());
            }
        };
    }

    private static Lesson getExpectedLesson(){
        return new Lesson(MOCK_TIME, MOCK_TYPE, MOCK_NAME, MOCK_TEACHER, MOCK_GROUP,
                MOCK_SUBGROUPS, MOCK_POSITION, getExpectedLessonNotifications());
    }

    private static List<LessonNotification> getExpectedLessonNotifications(){
        return new ArrayList<LessonNotification>()
        {
            {
                add(getExpectedLessonNotification());
            }
        };
    }

    private static Date MOCK_DATE_TIME = Calendar.getInstance().getTime();
    private static String MOCK_AUTHOR = "Jonni";
    private static String MOCK_DESCRIPTION = "Some description";

    private static LessonNotification getExpectedLessonNotification(){
        return new LessonNotification(MOCK_DATE_TIME, MOCK_AUTHOR, MOCK_DESCRIPTION);
    }

    @Test
    public void load_lessons_date() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        List<Lesson> expectedLessons = getExpectedLessons();
        IScheduleView scheduleView = getScheduleView();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        IScheduleService scheduleService = getScheduleService();
        when(scheduleService.getLessons(any(Date.class))).thenReturn(expectedLessons);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(scheduleService), getSession());

        presenter.loadLessonsToday();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(scheduleService, times(1)).getLessons(expectedDate);
        verify(scheduleView, times(1)).setSchedule(expectedLessons);
    }

    @Test
    public void load_lessons_date_exception() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        IScheduleView scheduleView = getScheduleView();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        IScheduleService scheduleService = getScheduleService();
        when(scheduleService.getLessons(any(Date.class))).thenThrow(ServiceException.class);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(scheduleService), getSession());

        presenter.loadLessonsToday();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(scheduleService, times(1)).getLessons(expectedDate);
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void update_lesson_notifiers() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        List<Lesson> expectedLessonNotifiers = getExpectedHaveLessonNotifiers();
        IScheduleView scheduleView = getScheduleView();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        INotifierService notifierService = getNotifierService();
        when(notifierService.haveNewLessonNotifiers(any(Date.class))).thenReturn(expectedLessonNotifiers);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.updateLessonsNotifier();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notifierService, times(1)).haveNewLessonNotifiers(expectedDate);
        verify(scheduleView, times(1)).updateLessonsNotifier(expectedDate, expectedLessonNotifiers);
    }

    @Test
    public void update_lesson_notifiers_exception() throws ServiceException {
        Date expectedDate = MOCK_DATE_TIME;
        IScheduleView scheduleView = getScheduleView();
        when(scheduleView.getScheduleDate()).thenReturn(expectedDate);
        INotifierService notifierService = getNotifierService();
        when(notifierService.haveNewLessonNotifiers(any(Date.class))).thenThrow(ServiceException.class);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.updateLessonsNotifier();

        verify(scheduleView, times(1)).getScheduleDate();
        verify(notifierService, times(1)).haveNewLessonNotifiers(expectedDate);
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void load_lesson_notification() throws ServiceException {
        List<LessonNotification> expectedLessonNotifications = getExpectedLessonNotifications();
        Lesson expectedLesson = getExpectedLesson();
        IScheduleView scheduleView = getScheduleView();
        INotifierService notifierService = getNotifierService();
        when(notifierService.getLessonNotifiers(any(Lesson.class))).thenReturn(expectedLessonNotifications);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.loadLessonNotification(expectedLesson);

        verify(notifierService, times(1)).getLessonNotifiers(expectedLesson);
        verify(scheduleView, times(1)).setLessonNotifiers(expectedLesson, expectedLessonNotifications);
    }

    @Test
    public void load_lesson_notification_exception() throws ServiceException {
        IScheduleView scheduleView = getScheduleView();
        INotifierService notifierService = getNotifierService();
        when(notifierService.getLessonNotifiers(any(Lesson.class))).thenThrow(ServiceException.class);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.loadLessonNotification(getExpectedLesson());

        verify(notifierService, times(1)).getLessonNotifiers(any(Lesson.class));
        verify(scheduleView, times(1)).talkException(null);
    }

    @Test
    public void update_notifier_messages() throws ServiceException {
        boolean expectedUpdated = Boolean.TRUE;
        IScheduleView scheduleView = getScheduleView();
        INotifierService notifierService = getNotifierService();
        when(notifierService.haveNewNotifierMessages()).thenReturn(expectedUpdated);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.updateNotifierMessages();

        verify(notifierService, times(1)).haveNewNotifierMessages();
        verify(scheduleView, times(1)).setBarNotifier(expectedUpdated);
    }

    @Test
    public void update_notifier_messages_exception() throws ServiceException {
        IScheduleView scheduleView = getScheduleView();
        INotifierService notifierService = getNotifierService();
        when(notifierService.haveNewNotifierMessages()).thenThrow(ServiceException.class);
        SchedulePresenter presenter = getPresenter(scheduleView, getServiceUnitOfWork(notifierService), getSession());

        presenter.updateNotifierMessages();

        verify(notifierService, times(1)).haveNewNotifierMessages();
        verify(scheduleView, times(1)).talkException(null);
    }
}
