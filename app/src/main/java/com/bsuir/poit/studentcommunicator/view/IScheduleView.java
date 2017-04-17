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
    void setBarNotifier(boolean haveNewNotifiers);
    Date getScheduleDate();
    void setSchedule(List<Lesson> lessons);
    void updateLessonsNotifier(Date currentDate, List<Lesson> notifierLessons);
    void setLessonNotifiers(Lesson lesson, List<LessonNotification> lessonNotifications);
}
