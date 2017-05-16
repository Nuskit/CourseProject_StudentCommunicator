package com.bsuir.poit.studentcommunicator.service;

import com.bsuir.poit.studentcommunicator.dao.unitofwork.IDaoUnitOfWork;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;

public abstract class AbstractSessionService {
    protected final IDaoUnitOfWork daoUnitOfWork;
    protected final ISession session;

    public AbstractSessionService(IDaoUnitOfWork daoUnitOfWork, ISession session){
        this.daoUnitOfWork = daoUnitOfWork;
        this.session = session;
    }
}
