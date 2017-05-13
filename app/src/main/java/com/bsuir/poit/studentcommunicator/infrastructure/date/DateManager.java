package com.bsuir.poit.studentcommunicator.infrastructure.date;


import java.util.Calendar;
import java.util.Date;

public class DateManager {
    public Date getCurrentTime(){
        return Calendar.getInstance().getTime();
    }
}
