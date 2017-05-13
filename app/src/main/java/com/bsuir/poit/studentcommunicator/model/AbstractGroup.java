package com.bsuir.poit.studentcommunicator.model;


import java.util.List;
import java.util.Objects;

public abstract class AbstractGroup<TypeStudentInformation> {
    private final String name;
    private final List<TypeStudentInformation> students;

    public AbstractGroup(String name, List<TypeStudentInformation> students) {
        this.name = name;
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGroup group = (AbstractGroup) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, students);
    }
}
