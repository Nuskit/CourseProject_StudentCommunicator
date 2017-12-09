package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;

import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.dao.impl.NotificationDao;
import com.bsuir.poit.studentcommunicator.dao.impl.ScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.impl.UserDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.INotificationDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IScheduleDao;
import com.bsuir.poit.studentcommunicator.dao.intefaces.IUserDao;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.impl.DaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.DaoScope;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Provides
    @NonNull
    @DaoScope
    public IDaoUnitOfWork providesDaoUOF(IUserDao userDao, IScheduleDao scheduleDao, INotificationDao notificationDao){
        return new DaoUnitOfWork(userDao, scheduleDao, notificationDao);
    }

    @Provides
    @NonNull
    @DaoScope
    public IUserDao providesUserDao(IHttp http){
        return new UserDao(http);
    }

    @Provides
    @NonNull
    @DaoScope
    public IScheduleDao providesScheduleDao(IHttp http){
        return new ScheduleDao(http);
    }

    @Provides
    @NonNull
    @DaoScope
    public INotificationDao providesNotificationDao(IHttp http){
        return new NotificationDao(http);
    }
}
