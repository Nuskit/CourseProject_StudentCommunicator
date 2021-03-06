package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl;

import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UILoginScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.AbstractViewModule;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginViewModule extends AbstractViewModule<ILoginView> {
    public LoginViewModule(ILoginView view) {
        super(view);
    }
}
