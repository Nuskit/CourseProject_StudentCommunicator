package com.bsuir.poit.studentcommunicator.login.presenter;

import com.bsuir.poit.studentcommunicator.activity.session.ISession;
import com.bsuir.poit.studentcommunicator.presenter.DistributionStudentPresenter;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IDistributionStudentView;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DistributionStudentUnitTest {
    private static DistributionStudentPresenter getPresenter(
            IDistributionStudentView distributionStudentView, 
            IServiceUnitOfWork unitOfWork, ISession session){
        return new DistributionStudentPresenter(distributionStudentView, unitOfWork, session);
    }

    private static final String MOCK_SESSION_GROUP = "test_group";

    private static ISession getSession(){
        ISession session = mock(ISession.class);
        when(session.getGroup()).thenReturn(MOCK_SESSION_GROUP);
        return session;
    }

    private static IDistributionStudentView getDistributionStudentView(){
        return mock(IDistributionStudentView.class);
    }

    private static IServiceUnitOfWork getServiceUnitOfWork(IGroupService groupService){
        IServiceUnitOfWork unitOfWork = mock(IServiceUnitOfWork.class);
        when(unitOfWork.getGroupService()).thenReturn(groupService);
        return unitOfWork;
    }

    private static IGroupService getGroupService(){
        return mock(IGroupService.class);
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
        return new ArrayList<Integer>() {
            {
                for (int i = 0; i < NUMBER_SUBGROUP_STUDENTS; i++) {
                    add(i);
                }
            }
        };
    }

    @Test
    public void save_subgroups() throws ServiceException {
        Map<String, List<Integer>> expectedDistributionStudents = getExpectedDistributionStudents();
        boolean expectedSaved = Boolean.TRUE;
        IGroupService mockService = getGroupService();
        when(mockService.setGroupDistribution(anyString(), ArgumentMatchers.<Map<String, List<Integer>>>any())).thenReturn(expectedSaved);
        IDistributionStudentView distributionStudentView = getDistributionStudentView();
        when(distributionStudentView.getDistributionStudents()).thenReturn(expectedDistributionStudents);
        ISession session = getSession();
        DistributionStudentPresenter presenter = getPresenter(distributionStudentView, getServiceUnitOfWork(mockService), session);

        presenter.saveSubGroups();
        verify(session, times(1)).getGroup();
        verify(distributionStudentView, times(1)).getDistributionStudents();
        verify(mockService, times(1)).setGroupDistribution(MOCK_SESSION_GROUP, expectedDistributionStudents);
        verify(distributionStudentView, times(1)).setSaved(expectedSaved);
    }

    @Test
    public void save_subgroups_exception() throws ServiceException {
        Map<String, List<Integer>> expectedDistributionStudents = getExpectedDistributionStudents();
        IGroupService mockService = getGroupService();
        when(mockService.setGroupDistribution(anyString(), ArgumentMatchers.<Map<String, List<Integer>>>any())).thenThrow(ServiceException.class);
        IDistributionStudentView distributionStudentView = getDistributionStudentView();
        when(distributionStudentView.getDistributionStudents()).thenReturn(expectedDistributionStudents);
        ISession session = getSession();
        DistributionStudentPresenter presenter = getPresenter(distributionStudentView, getServiceUnitOfWork(mockService), session);

        presenter.saveSubGroups();
        verify(session, times(1)).getGroup();
        verify(distributionStudentView, times(1)).getDistributionStudents();
        verify(mockService, times(1)).setGroupDistribution(MOCK_SESSION_GROUP, expectedDistributionStudents);
        verify(distributionStudentView, times(1)).talkException(null);
    }
}
