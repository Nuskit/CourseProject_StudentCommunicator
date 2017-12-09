package com.bsuir.poit.studentcommunicator.general;


import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;

import static org.mockito.Mockito.mock;

public class MockSession {

    public static ISession getSession() {
        return mock(ISession.class);
    }
}
