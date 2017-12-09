package com.bsuir.poit.studentcommunicator.general;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;

import java.util.Calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDate {

    private static Calendar calendar;

    private static final int MOCK_YEAR = 2000;
    private static final int MOCK_MONTH = 1;
    private static final int MOCK_DATE = 1;

    static {
        calendar = Calendar.getInstance();
        calendar.set(MOCK_YEAR, MOCK_MONTH, MOCK_DATE);
    }

    public static DateManager getDateManager() {
        DateManager mock = mock(DateManager.class);
        when(mock.getCurrentTime()).thenReturn(calendar.getTime());
        return mock;
    }
}
