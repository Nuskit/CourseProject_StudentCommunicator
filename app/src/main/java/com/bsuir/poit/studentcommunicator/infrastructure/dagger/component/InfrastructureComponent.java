package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component;


import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.InfrastructureModule;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = InfrastructureModule.class)
public interface InfrastructureComponent {
    DateManager getDateManager();
    IHttp getHttp();
    ISession getSession();
}
