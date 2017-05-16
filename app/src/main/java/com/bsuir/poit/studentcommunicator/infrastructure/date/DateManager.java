package com.bsuir.poit.studentcommunicator.infrastructure.date;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
    public Date getCurrentTime(){
        return Calendar.getInstance().getTime();
    }
    public String getMonthDayAfter(int afterDays){
        Calendar c = getCalendarAfter(afterDays);
        return String.format("%02d.%02d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1);
    }

    private static Calendar getCalendarAfter(int afterDays){
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.DAY_OF_MONTH, afterDays + c.get(Calendar.DAY_OF_MONTH));
        return c;
    }

    public Date getDateAfter(int afterDays){
        return getCalendarAfter(afterDays).getTime();
    }
    public String getToolBarTime() {
        Date currentTime = getCurrentTime();
        return new SimpleDateFormat("EEE, d MMM yyyy").format(currentTime);
    }

    public String getScheduleDate(Date currentDate) {
        return new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
    }
}
