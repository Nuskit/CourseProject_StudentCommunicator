package com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UILoginScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.LoginViewModule;
import com.bsuir.poit.studentcommunicator.ui.activity.LoginActivity;

import dagger.Component;

@UILoginScope
@Component(dependencies = ServiceComponent.class, modules = LoginViewModule.class)
public interface UILoginComponent {
    void inject(LoginActivity loginActivity);
}
