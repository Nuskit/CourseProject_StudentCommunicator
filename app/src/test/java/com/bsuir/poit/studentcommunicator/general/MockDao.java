package com.bsuir.poit.studentcommunicator.general;


import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDao {

    public static class DaoUOFBuilder {
        private IUserDao userDao;
        private IScheduleDao scheduleDao;

        public MockDao.DaoUOFBuilder setUserDao(IUserDao userDao) {
            this.userDao = userDao;

            return this;
        }

        public MockDao.DaoUOFBuilder setScheduleDao(IScheduleDao scheduleDao) {
            this.scheduleDao = scheduleDao;

            return this;
        }

        public IDaoUnitOfWork build() {
            IDaoUnitOfWork mockUOF = mock(IDaoUnitOfWork.class);

            when(mockUOF.getUserDao()).thenReturn(userDao);
            when(mockUOF.getScheduleDao()).thenReturn(scheduleDao);

            return mockUOF;
        }
    }

    public static IUserDao getUserDao() {
        return mock(IUserDao.class);
    }

    public static IScheduleDao getScheduleDao() {
        return mock(IScheduleDao.class);
    }
}
