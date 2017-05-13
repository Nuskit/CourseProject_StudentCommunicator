package com.bsuir.poit.studentcommunicator.model;


import java.security.acl.Group;
import java.util.List;

public class GroupAttendance extends AbstractGroup<StudentAttendance>{
    public GroupAttendance(String name, List<StudentAttendance> students) {
        super(name, students);
    }
}
