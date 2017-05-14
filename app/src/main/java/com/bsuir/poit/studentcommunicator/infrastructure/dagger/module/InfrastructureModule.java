package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;

import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InfrastructureModule {

    @Provides
    @NonNull
    @Singleton
    public DateManager providesDateManager(){
        return new DateManager();
    }
}
