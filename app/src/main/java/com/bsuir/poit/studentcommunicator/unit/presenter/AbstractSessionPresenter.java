package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;

public abstract class AbstractSessionPresenter {
    protected final IServiceUnitOfWork serviceUnitOfWork;
    protected final ISession session;

    public AbstractSessionPresenter(IServiceUnitOfWork serviceUnitOfWork, ISession session){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }
}
