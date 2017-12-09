package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;


import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.dao.unitofwork.impl.DaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.ServiceScope;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.service.impl.GroupService;
import com.bsuir.poit.studentcommunicator.service.impl.LessonService;
import com.bsuir.poit.studentcommunicator.service.impl.NotificationService;
import com.bsuir.poit.studentcommunicator.service.impl.ScheduleService;
import com.bsuir.poit.studentcommunicator.service.impl.UniversityService;
import com.bsuir.poit.studentcommunicator.service.impl.UserService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.interfaces.ILessonService;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotificationService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUniversityService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.service.unitofwork.impl.ServiceUnitOfWork;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @NonNull
    @ServiceScope
    public IServiceUnitOfWork providesServiceUnitOfWork(IUserService userService,
                                                        INotificationService notificationService,
                                                        IGroupService groupService,
                                                        IUniversityService universityService,
                                                        IScheduleService scheduleService,
                                                        ILessonService lessonService){
        return new ServiceUnitOfWork(userService, notificationService, groupService,
                universityService, scheduleService, lessonService);
    }


    @Provides
    @NonNull
    @ServiceScope
    public IUserService providesUserService(IDaoUnitOfWork daoUnitOfWork, ISession session) {
        return new UserService(daoUnitOfWork, session);
    }


    @Provides
    @NonNull
    @ServiceScope
    public INotificationService providesNotificationService(IDaoUnitOfWork daoUnitOfWork, ISession session) {
        return new NotificationService(daoUnitOfWork, session);
    }


    @Provides
    @NonNull
    @ServiceScope
    public IGroupService providesGroupService() {
        return new GroupService();
    }


    @Provides
    @NonNull
    @ServiceScope
    public IUniversityService providesUniversityService() {
        return new UniversityService();
    }


    @Provides
    @NonNull
    @ServiceScope
    public IScheduleService providesScheduleService(IDaoUnitOfWork daoUnitOfWork) {
        return new ScheduleService(daoUnitOfWork);
    }


    @Provides
    @NonNull
    @ServiceScope
    public ILessonService providesLessonService() {
        return new LessonService();
    }
}
