package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component;


import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.ServiceScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.ServiceModule;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;

import javax.inject.Singleton;

import dagger.Component;

@ServiceScope
@Component(dependencies = DaoComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    IServiceUnitOfWork getServiceUOF();
    DateManager getDateManager();
}
