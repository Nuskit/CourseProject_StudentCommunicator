package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;


import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UIScore;
import com.bsuir.poit.studentcommunicator.view.IExceptionView;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AbstractViewModule<T extends IExceptionView> {

    private final T view;

    public AbstractViewModule(T view){
        this.view = view;
    }

    @Provides
    @NonNull
    @UIScore
    public T getView(){
        return view;
    }
}
