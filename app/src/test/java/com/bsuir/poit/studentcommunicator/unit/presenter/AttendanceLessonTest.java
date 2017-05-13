package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.model.GroupAttendance;
import com.bsuir.poit.studentcommunicator.model.StudentAttendance;
import com.bsuir.poit.studentcommunicator.presenter.impl.AttendanceLessonPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.ILessonService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IAttendanceLessonView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bsuir.poit.studentcommunicator.general.MockDate.getDateManager;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AttendanceLessonTest {

    private ILessonService lessonService;
    private IAttendanceLessonView attendanceLessonView;
    private AttendanceLessonPresenter presenter;

    @Before
    public void initTest(){
        lessonService = getLessonService();
        attendanceLessonView = getAttendanceLessonView();
        presenter = getPresenter(attendanceLessonView, getServiceUnitOfWork(lessonService), getDateManager());
    }


    private static AttendanceLessonPresenter getPresenter(
            IAttendanceLessonView AttendanceLessonView,
            IServiceUnitOfWork unitOfWork, DateManager dateManager){
        return new AttendanceLessonPresenter(AttendanceLessonView, unitOfWork, dateManager);
    }

    private static IAttendanceLessonView getAttendanceLessonView(){
        return mock(IAttendanceLessonView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(ILessonService lessonService){
        return new MockService.ServiceUOFBuilder().setLessonService(lessonService).build();
    }

    private static ILessonService getLessonService(){
        return MockService.getLessonService();
    }

    private static final String MOCK_GROUP_NAME = "test_group";

    private static List<StudentAttendance> getExpectedListStudent(){
        return new ArrayList<StudentAttendance>(){{
            add(getExpectedStudent());
        }};
    }

    private static StudentAttendance getExpectedStudent() {
        return new StudentAttendance();
    }

    private static GroupAttendance getExpectedAttendanceGroup(){
        return new GroupAttendance(MOCK_GROUP_NAME, getExpectedListStudent());
    }

    private static List<GroupAttendance> getExpectedListAttendanceGroup(){
        return new ArrayList<GroupAttendance>(){{
            add(getExpectedAttendanceGroup());
        }};
    }

    private static final int MOCK_LESSON_ID = 3;

    @Test
    public void load_attendance_groups() throws ServiceException {
        List<GroupAttendance> expectedGroupAttendance = getExpectedListAttendanceGroup();
        when(lessonService.getAttendanceInformation(MOCK_LESSON_ID)).thenReturn(expectedGroupAttendance);

        presenter.onCreate(MOCK_LESSON_ID);

        verify(lessonService, times(1)).getAttendanceInformation(eq(MOCK_LESSON_ID));
        verify(attendanceLessonView, times(1)).showAttendanceGroups(expectedGroupAttendance);
    }

    @Test
    public void load_attendance_groups_exception() throws ServiceException {
        when(lessonService.getAttendanceInformation(anyInt())).thenThrow(ServiceException.class);

        presenter.onCreate(MOCK_LESSON_ID);

        verify(lessonService, times(1)).getAttendanceInformation(anyInt());
        verify(attendanceLessonView, times(1)).talkException(null);
    }

    @Test
    public void update_attendance_groups() throws ServiceException {
        List<GroupAttendance> expectedGroupAttendance = getExpectedListAttendanceGroup();
        when(lessonService.getChangedAttendance(anyInt(), ArgumentMatchers.<Date>any(),
                any(Date.class))).thenReturn(expectedGroupAttendance);

        presenter.updateAttendanceGroups();

        verify(lessonService, times(1)).getChangedAttendance(anyInt(), ArgumentMatchers.<Date>any(),
                any(Date.class));
        verify(attendanceLessonView, times(1)).updateAttendanceGroups(expectedGroupAttendance);
    }

    @Test
    public void update_attendance_groups_exception() throws ServiceException {
        when(lessonService.getChangedAttendance(anyInt(), ArgumentMatchers.<Date>any(),
                any(Date.class))).thenThrow(ServiceException.class);

        presenter.updateAttendanceGroups();

        verify(lessonService, times(1)).getChangedAttendance(anyInt(), ArgumentMatchers.<Date>any(), any(Date.class));
        verify(attendanceLessonView, times(1)).talkException(null);
    }
}
