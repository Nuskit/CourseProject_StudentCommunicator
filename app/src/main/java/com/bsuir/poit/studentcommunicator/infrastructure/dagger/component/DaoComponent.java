package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component;


import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.DaoScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.DaoModule;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;

import dagger.Component;

@Component(dependencies = InfrastructureComponent.class, modules = DaoModule.class)
@DaoScope
public interface DaoComponent {
    IDaoUnitOfWork getDaoUOF();
    ISession getSession();
    DateManager getDateManager();
}
