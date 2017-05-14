package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui;


import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UIMainWorkScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.MainWorkViewModule;
import com.bsuir.poit.studentcommunicator.ui.activity.MainWorkActivity;
import com.bsuir.poit.studentcommunicator.ui.fragment.ScheduleLessonFragment;

import dagger.Component;

@UIMainWorkScope
@Component(dependencies = ServiceComponent.class, modules = MainWorkViewModule.class)
public interface UIMainWorkComponent {
    void inject(MainWorkActivity mainWorkActivity);
    void inject(ScheduleLessonFragment scheduleLessonFragment);
}
