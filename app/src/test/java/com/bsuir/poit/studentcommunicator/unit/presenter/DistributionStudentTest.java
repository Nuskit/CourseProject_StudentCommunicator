package com.bsuir.poit.studentcommunicator.unit.presenter;

import com.bsuir.poit.studentcommunicator.general.MockService;
import com.bsuir.poit.studentcommunicator.presenter.impl.DistributionStudentPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IDistributionStudentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DistributionStudentTest {

    private IGroupService groupService;
    private IDistributionStudentView distributionStudentView;
    private DistributionStudentPresenter presenter;

    @Before
    public void initTest(){
        groupService = getGroupService();
        distributionStudentView = getDistributionStudentView();
        presenter = getPresenter(distributionStudentView, getServiceUnitOfWork(groupService));
    }


    private static DistributionStudentPresenter getPresenter(
            IDistributionStudentView distributionStudentView, IServiceUnitOfWork unitOfWork){
        return new DistributionStudentPresenter(distributionStudentView, unitOfWork);
    }

    private static IDistributionStudentView getDistributionStudentView(){
        return mock(IDistributionStudentView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IGroupService groupService){
        return new MockService.ServiceUOFBuilder().setGroupService(groupService).build();
    }

    private static IGroupService getGroupService(){
        return MockService.getGroupService();
    }

    private static final String MOCK_SUBGROUP_KEY = "test_subgroup";

    private Map<String, List<Integer>> getExpectedDistributionStudents(){
        return new HashMap<String, List<Integer>>() {
            {
                put(MOCK_SUBGROUP_KEY, getExpectedSubGroupStudents());
            }
        };
    }

    private static final int NUMBER_SUBGROUP_STUDENTS = 3;

    private List<Integer> getExpectedSubGroupStudents() {
        return new ArrayList<Integer>() {{
            for (int i = 0; i < NUMBER_SUBGROUP_STUDENTS; i++) {
                add(i);
            }
        }};
    }

    @Test
    public void save_subgroups() throws ServiceException {
        Map<String, List<Integer>> expectedDistributionStudents = getExpectedDistributionStudents();
        final boolean expectedSaved = true;
        when(groupService.setGroupDistribution(ArgumentMatchers.<Map<String, List<Integer>>>any())).thenReturn(expectedSaved);
        when(distributionStudentView.getDistributionStudents()).thenReturn(expectedDistributionStudents);

        presenter.saveSubGroups();

        verify(distributionStudentView, times(1)).getDistributionStudents();
        verify(groupService, times(1)).setGroupDistribution(expectedDistributionStudents);
        verify(distributionStudentView, times(1)).setSaved(expectedSaved);
    }

    @Test
    public void save_subgroups_exception() throws ServiceException {
        Map<String, List<Integer>> expectedDistributionStudents = getExpectedDistributionStudents();
        when(groupService.setGroupDistribution(ArgumentMatchers.<Map<String, List<Integer>>>any())).thenThrow(ServiceException.class);
        when(distributionStudentView.getDistributionStudents()).thenReturn(expectedDistributionStudents);

        presenter.saveSubGroups();

        verify(distributionStudentView, times(1)).getDistributionStudents();
        verify(groupService, times(1)).setGroupDistribution(expectedDistributionStudents);
        verify(distributionStudentView, times(1)).talkException(null);
    }
}
