package com.bsuir.poit.studentcommunicator.view;

import com.bsuir.poit.studentcommunicator.model.Lesson;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;

import java.util.Date;
import java.util.List;

public interface IScheduleView extends IExceptionView {
    void setBarTime(Date time);
    void setBarGroup(String numberGroup);
    void setBarLogo(String universityLogo);
    void setBarSubGroups(List<String> subGroups);
    void setBarNotification(boolean haveNewNotifications);
    Date getScheduleDate();
    void setSchedule(List<Lesson> lessons);
    void updateLessonsNotification(Date currentDate, List<Lesson> notificationLessons);
    void setLessonNotifications(Lesson lesson, List<LessonNotification> lessonNotifications);
}
