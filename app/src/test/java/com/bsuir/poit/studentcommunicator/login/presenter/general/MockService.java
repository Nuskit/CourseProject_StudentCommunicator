package com.bsuir.poit.studentcommunicator.login.presenter.general;

import com.bsuir.poit.studentcommunicator.service.interfaces.IGroupService;
import com.bsuir.poit.studentcommunicator.service.interfaces.INotifierService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IScheduleService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUniversityService;
import com.bsuir.poit.studentcommunicator.service.interfaces.IUserService;
import com.bsuir.poit.studentcommunicator.service.unitofwork.IServiceUnitOfWork;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockService {
    public static class SeviceUOFBuilder{
        private INotifierService notifierService;
        private IGroupService groupService;
        private IScheduleService scheduleService;
        private IUniversityService universityService;
        private IUserService userService;

        public SeviceUOFBuilder setNotifierService(INotifierService notifierService) {
            this.notifierService = notifierService;

            return this;
        }

        public SeviceUOFBuilder setGroupService(IGroupService groupService) {
            this.groupService = groupService;

            return this;
        }

        public SeviceUOFBuilder setScheduleService(IScheduleService scheduleService) {
            this.scheduleService = scheduleService;

            return this;
        }

        public SeviceUOFBuilder setUniversityService(IUniversityService universityService) {
            this.universityService = universityService;

            return this;
        }

        public SeviceUOFBuilder setUserService(IUserService userService) {
            this.userService = userService;

            return this;
        }

        public IServiceUnitOfWork build(){
            IServiceUnitOfWork mockUOF = mock(IServiceUnitOfWork.class);

            when(mockUOF.getNotifierService()).thenReturn(notifierService);
            when(mockUOF.getGroupService()).thenReturn(groupService);
            when(mockUOF.getScheduleService()).thenReturn(scheduleService);
            when(mockUOF.getUniversityService()).thenReturn(universityService);
            when(mockUOF.getUserService()).thenReturn(userService);

            return mockUOF;
        }
    }


    public static INotifierService getNotifierService() {
        return mock(INotifierService.class);
    }

    public static IGroupService getGroupService() {
        return mock(IGroupService.class);
    }

    public static IScheduleService getScheduleService() {
        return mock(IScheduleService.class);
    }

    public static IUniversityService getUniversityService() {
        return mock(IUniversityService.class);
    }

    public static IUserService getUserService() {
        return mock(IUserService.class);
    }
}
