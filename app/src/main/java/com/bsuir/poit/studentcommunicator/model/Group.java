package com.bsuir.poit.studentcommunicator.model;


import java.util.List;

public class Group extends AbstractGroup<Student>{
    public Group(String name, List<Student> students) {
        super(name, students);
    }
}
