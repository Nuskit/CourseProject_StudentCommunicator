package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IDistributionStudentView;

import java.util.List;
import java.util.Map;

//TODO: move header to simple class
public class DistributionStudentPresenter {
    private final IDistributionStudentView distributionStudentView;
    private final IServiceUnitOfWork serviceUnitOfWork;

    public DistributionStudentPresenter(IDistributionStudentView distributionStudentView,
                                        IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.distributionStudentView = distributionStudentView;
    }

    public void onCreate(){
        try {
            Map<String, List<String>> distributionStudents = serviceUnitOfWork.getGroupService().getSubGroupStudents();
            distributionStudentView.setSubGroupStudents(distributionStudents);
        }catch (Exception e){
            //TODO: think other error information
            distributionStudentView.talkException(e.getMessage());
        }
    }

    public void saveSubGroups() {
        try {
            Map<String, List<Integer>> distributionStudents = distributionStudentView.getDistributionStudents();
            boolean isSaved = serviceUnitOfWork.getGroupService().setGroupDistribution(distributionStudents);
            distributionStudentView.setSaved(isSaved);
        }catch (Exception e){
            distributionStudentView.talkException(e.getMessage());
        }
    }
}
