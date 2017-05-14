package com.bsuir.poit.studentcommunicator.presenter.impl;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.service.exception.ServiceException;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;
import com.bsuir.poit.studentcommunicator.view.IMainWorkView;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class MainWorkActivityPresenter {

    private final IMainWorkView mainWorkView;
    private final IServiceUnitOfWork serviceUnitOfWork;
    private final DateManager dateManager;

    @Inject
    public MainWorkActivityPresenter(IMainWorkView mainWorkView, IServiceUnitOfWork serviceUnitOfWork, DateManager dateManager) {
        this.mainWorkView = mainWorkView;
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.dateManager = dateManager;
    }

    public void onCreate() {
        try {
            initBar();
        } catch (Exception e) {
            mainWorkView.talkException(e.getMessage());
        }
    }


    private void initBar() throws ServiceException {
        initBarTime();
        initBarGroup();
        initBarLogo();
        initBarSubGroups();
        initBarNotifications();
    }


    private void initBarTime() {
        mainWorkView.setBarTime(dateManager.getCurrentTime());
    }

    private void initBarNotifications() throws ServiceException {
        updateNotificationMessages();
    }

    public void updateNotificationMessages() {
        try {
            mainWorkView.setBarNotification(
                    serviceUnitOfWork.getNotificationService().haveNewNotificationMessages());
        } catch (Exception e) {
            mainWorkView.talkException(e.getMessage());
        }
    }

    private void initBarSubGroups() throws ServiceException {
        List<String> subGroups = serviceUnitOfWork.getGroupService().getSubGroupNames();
        mainWorkView.setBarSubGroups(subGroups);
    }

    private void initBarLogo() throws ServiceException {
        String universityLogo = serviceUnitOfWork.getUniversityService().getLogo();
        mainWorkView.setBarLogo(universityLogo);
    }

    private void initBarGroup() throws ServiceException {
        String numberGroup = serviceUnitOfWork.getGroupService().getNumberGroup();
        mainWorkView.setBarGroup(numberGroup);
    }
}