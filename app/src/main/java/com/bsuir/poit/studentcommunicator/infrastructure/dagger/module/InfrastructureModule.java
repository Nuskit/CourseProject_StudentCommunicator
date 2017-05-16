package com.bsuir.poit.studentcommunicator.infrastructure.dagger.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.App;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.infrastructure.http.impl.OkHttp;
import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.infrastructure.session.impl.PreferenceSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InfrastructureModule {

    private final App app;

    public InfrastructureModule(App app){
        this.app = app;
    }

    @Provides
    @NonNull
    @Singleton
    Context providesContext(){
        return app;
    }

    @Provides
    @NonNull
    @Singleton
    public DateManager providesDateManager(){
        return new DateManager();
    }

    @Provides
    @NonNull
    @Singleton
    public ISession providesSession(Context context) {
        return new PreferenceSession(context);
    }

    @Provides
    @NonNull
    @Singleton
    public IHttp providesHttp(DateManager dateManager){
        return new OkHttp(dateManager);
    }
}
