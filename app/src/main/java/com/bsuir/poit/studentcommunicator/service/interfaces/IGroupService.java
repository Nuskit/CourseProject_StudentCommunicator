package com.bsuir.poit.studentcommunicator.service.interfaces;


import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IGroupService {
    String getNumberGroup()  throws ServiceException;
    List<String> getSubGroupNames()  throws ServiceException;
    boolean setGroupDistribution(Map<String, List<Integer>> distributionStudents) throws ServiceException;
    Map<String,List<String>> getSubGroupStudents() throws ServiceException;
}
