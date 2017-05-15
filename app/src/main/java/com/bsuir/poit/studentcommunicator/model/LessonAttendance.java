package com.bsuir.poit.studentcommunicator.model;


import java.util.List;
import java.util.Objects;

public class LessonAttendance {
    private final String time;
    private final String type;
    private final String subject;
    private final String teacher;
    private final List<GroupAttendance> group;

    public LessonAttendance(String time, String type, String subject, String teacher, List<GroupAttendance> group) {
        this.time = time;
        this.type = type;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<GroupAttendance> getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAttendance that = (LessonAttendance) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(type, that.type) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, subject, teacher, group);
    }
}
