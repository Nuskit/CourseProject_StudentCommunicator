package com.bsuir.poit.studentcommunicator.infrastructure.dagger;

import android.app.Application;
import android.content.Context;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.DaggerInfrastructureComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.DaggerServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.InfrastructureComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.InfrastructureModule;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.ServiceModule;


public class App extends Application {

    public static ServiceComponent getComponent(){
        return serviceComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    private static InfrastructureComponent infrastructureComponent;
    private static ServiceComponent serviceComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        buildComponents();
    }

    private static void buildComponents() {
        infrastructureComponent = DaggerInfrastructureComponent.builder().infrastructureModule(new InfrastructureModule()).build();
        serviceComponent = DaggerServiceComponent.builder().infrastructureComponent(infrastructureComponent)
                .serviceModule(new ServiceModule()).build();
    }
}
