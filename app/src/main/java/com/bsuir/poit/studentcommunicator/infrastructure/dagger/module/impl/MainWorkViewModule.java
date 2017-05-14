package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl;

import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UIMainWorkScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.AbstractViewModule;
import com.bsuir.poit.studentcommunicator.view.IMainWorkView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainWorkViewModule extends AbstractViewModule<IMainWorkView>{
    public MainWorkViewModule(IMainWorkView view) {
        super(view);
    }
}
