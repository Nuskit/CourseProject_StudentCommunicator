package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UIScore;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.AbstractViewModule;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.LoginViewModule;
import com.bsuir.poit.studentcommunicator.ui.activity.LoginActivity;

import dagger.Component;

@UIScore
@Component(dependencies = ServiceComponent.class, modules = {LoginViewModule.class})
public interface UIComponent {
    void inject(LoginActivity loginActivity);
}
