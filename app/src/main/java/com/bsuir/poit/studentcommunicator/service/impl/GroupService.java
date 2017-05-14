package com.bsuir.poit.studentcommunicator.service.impl;

import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;

import java.util.List;
import java.util.Map;

public class GroupService implements IGroupService {
    @Override
    public String getNumberGroup() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getSubGroupNames() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setGroupDistribution(Map<String, List<Integer>> distributionStudents) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, List<String>> getSubGroupStudents() throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
