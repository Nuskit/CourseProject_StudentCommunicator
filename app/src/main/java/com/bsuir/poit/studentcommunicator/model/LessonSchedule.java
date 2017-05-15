package com.bsuir.poit.studentcommunicator.model;

import java.util.List;
import java.util.Objects;

//TODO: equals if value null
public class LessonSchedule {
    private final String time;
    private final String type;
    private final String subject;
    private final String teacher;
    private final String group;
    private final String position;
    private final List<LessonNotification> notifications;

    public LessonSchedule(String time, String type, String subject, String teacher, String group,
                          String position, List<LessonNotification> notifications){
        this.time = time;
        this.type = type;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.position = position;
        this.notifications = notifications;
    }

    public String getType(){
        return type;
    }


    public String getSubject(){
        return subject;
    }

    public String getTeacher(){
        return teacher;
    }

    public String getGroup(){
        return group;
    }

    public String getPosition(){
        return position;
    }

    public List<LessonNotification> getNotifications(){
        return notifications;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonSchedule that = (LessonSchedule) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(type, that.type) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(group, that.group) &&
                Objects.equals(position, that.position) &&
                Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, subject, teacher, group, position, notifications);
    }
}
