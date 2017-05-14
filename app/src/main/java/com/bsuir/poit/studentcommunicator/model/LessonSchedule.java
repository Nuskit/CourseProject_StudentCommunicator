package com.bsuir.poit.studentcommunicator.model;

import java.util.List;

//TODO: equals if value null
public class LessonSchedule {
    private final String time;
    private final String type;
    private final String name;
    private final String teacher;
    private final String group;
    private final List<String> subGroups;
    private final String position;
    private final List<LessonNotification> notifications;

    public LessonSchedule(String time, String type, String name, String teacher, String group,
                          List<String>  subGroups, String position, List<LessonNotification> notifications){
        this.time = time;
        this.type = type;
        this.name = name;
        this.teacher = teacher;
        this.group = group;
        this.subGroups = subGroups;
        this.position = position;
        this.notifications = notifications;
    }

    public String getType(){
        return type;
    }


    public String getName(){
        return name;
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

    public List<String> getSubGroups(){
        return subGroups;
    }

    public List<LessonNotification> getNotifications(){
        return notifications;
    }

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }

        if (this == object){
            return true;
        }

        if (this.getClass() != object.getClass()){
            return false;
        }

        LessonSchedule lessonSchedule = (LessonSchedule) object;
        return time.equals(lessonSchedule.time)
                && type.equals(lessonSchedule.type)
                && name.equals(lessonSchedule.name)
                && teacher.equals(lessonSchedule.teacher)
                && group.equals(lessonSchedule.group)
                && subGroups == null
                ? lessonSchedule.subGroups == null
                : subGroups.equals(lessonSchedule.subGroups)
                && position.equals(lessonSchedule.position)
                && notifications == null
                ? lessonSchedule.notifications == null
                : notifications.equals(lessonSchedule.notifications);
    }

    @Override
    public int hashCode(){
        int ht = 17;
        ht = 31 * ht + time.hashCode();
        ht = 31 * ht + type.hashCode();
        ht = 31 * ht + name.hashCode();
        ht = 31 * ht + teacher.hashCode();
        ht = 31 * ht + group.hashCode();
        ht = 31 * ht + (subGroups == null
                ? 0
                : subGroups.hashCode());
        ht = 31 * ht + position.hashCode();
        ht = 31 * ht + (notifications == null
                ? 0
                : notifications.hashCode());
        return ht;
    }
}