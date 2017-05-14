package com.bsuir.poit.studentcommunicator.infrastructure.dagger;

import android.app.Application;
import android.content.Context;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.DaggerServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.DaggerUIComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.UIComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.ServiceModule;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.LoginViewModule;
import com.bsuir.poit.studentcommunicator.view.IExceptionView;

import dagger.Module;


public class App extends Application {

    public static ServiceComponent getComponent(){
        return serviceComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    private static ServiceComponent serviceComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        buildComponents();
    }

    private static void buildComponents() {
        serviceComponent = DaggerServiceComponent.builder().serviceModule(new ServiceModule()).build();
    }
}
