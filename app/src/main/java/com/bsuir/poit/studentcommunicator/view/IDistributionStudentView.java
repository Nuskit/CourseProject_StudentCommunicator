package com.bsuir.poit.studentcommunicator.view;

import java.util.List;
import java.util.Map;

public interface IDistributionStudentView extends IExceptionView{
    Map<String,List<Integer>> getDistributionStudents();
    void setSubGroupStudents(Map<String, List<String>> distributionStudents);
    void setSaved(boolean isSaved);
}
