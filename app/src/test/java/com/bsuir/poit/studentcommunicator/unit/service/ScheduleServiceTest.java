package com.bsuir.poit.studentcommunicator.unit.service;


import com.bsuir.poit.studentcommunicator.dao.exception.DaoException;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.general.MockDao;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.impl.ScheduleService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScheduleServiceTest {

    private IScheduleDao scheduleDao;
    private ScheduleService service;

    @Before
    public void initTest() {
        scheduleDao = getScheduleDao();
        service = getService(getDaoUnitOfWork(scheduleDao));
    }

    private static ScheduleService getService(
            IDaoUnitOfWork unitOfWork){
        return new ScheduleService(unitOfWork);
    }

    private static IScheduleDao getScheduleDao(){
        return MockDao.getScheduleDao();
    }

    private static IDaoUnitOfWork getDaoUnitOfWork(IScheduleDao scheduleDao) {
        return new MockDao.DaoUOFBuilder().setScheduleDao(scheduleDao).build();
    }

    private static final String MOCK_TIME = "Test_time";
    private static final String MOCK_TYPE = "Test_type";
    private static final String MOCK_SUBJECT = "Test_subject";
    private static final String MOCK_TEACHER = "Test_teacher";
    private static final String MOCK_GROUP = "Test_group";
    private static final String MOCK_POSITION = "Test_position";


    private LessonSchedule getExpectedLessonSchedule(){
        return new LessonSchedule(MOCK_TIME, MOCK_TYPE, MOCK_SUBJECT, MOCK_TEACHER,
                MOCK_GROUP, MOCK_POSITION, null);
    }

    private List<LessonSchedule> getExpectedListSchedule(){
        return new ArrayList<LessonSchedule>(){{
            add(getExpectedLessonSchedule());
        }};
    }

    @Test
    public void check_get_lessons() throws ServiceException, DaoException {
        List<LessonSchedule> expectedSchedule = getExpectedListSchedule();

        when(scheduleDao.getLessons(any(Date.class))).thenReturn(expectedSchedule);

        List<LessonSchedule> checkedSchedule = service.getLessons(Calendar.getInstance().getTime());

        assertEquals(checkedSchedule, expectedSchedule);
        verify(scheduleDao, times(1)).getLessons(any(Date.class));
    }

    @Test
    public void check_get_lessons_exception() throws DaoException {
        when(scheduleDao.getLessons(any(Date.class))).thenThrow(DaoException.class);

        try {
            service.getLessons(Calendar.getInstance().getTime());
            Assert.fail();
        }catch (ServiceException e) {
            verify(scheduleDao, times(1)).getLessons(any(Date.class));
        }
    }
}
