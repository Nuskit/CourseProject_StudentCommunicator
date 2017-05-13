package com.bsuir.poit.studentcommunicator.view;

import com.bsuir.poit.studentcommunicator.model.GroupAttendance;

import java.util.List;

public interface IAttendanceLessonView extends IExceptionView{
    void showAttendanceGroups(List<GroupAttendance> attendanceInformation);
    void updateAttendanceGroups(List<GroupAttendance> changedAttendance);
}
