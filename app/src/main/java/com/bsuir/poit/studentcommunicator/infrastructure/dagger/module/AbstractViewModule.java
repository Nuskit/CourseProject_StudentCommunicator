package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;


import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UILoginScope;
import com.bsuir.poit.studentcommunicator.view.IExceptionView;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AbstractViewModule<T extends IExceptionView> {

    private final T view;

    public AbstractViewModule(T view){
        this.view = view;
    }

    @NonNull
    @Provides
    public T getView(){
        return view;
    }
}
