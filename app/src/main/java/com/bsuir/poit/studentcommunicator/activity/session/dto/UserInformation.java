package com.bsuir.poit.studentcommunicator.activity.session.dto;

import java.util.List;
import java.util.Map;

public class UserInformation {
    public enum Role{
        STUDENT, TEACHER, ADMINISTRATION
    }

    public Map<String, List<Role>> universityRoles;
}
