package com.bsuir.poit.studentcommunicator.model;


import java.util.List;
import java.util.Objects;

public class LessonAttendance {
    private final String time;
    private final String type;
    private final String name;
    private final String teacher;
    private final List<GroupAttendance> group;

    public LessonAttendance(String time, String type, String name, String teacher, List<GroupAttendance> group) {
        this.time = time;
        this.type = type;
        this.name = name;
        this.teacher = teacher;
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
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
                Objects.equals(name, that.name) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, name, teacher, group);
    }
}
