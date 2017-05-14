package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component;


import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.ServiceModule;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ServiceModule.class)
public interface ServiceComponent {
    IServiceUnitOfWork getServiceUOF();
}
