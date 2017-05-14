package com.bsuir.poit.studentcommunicator.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.App;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.DaggerUIComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.UIComponent;
import com.bsuir.poit.studentcommunicator.view.IExceptionView;

public abstract class BaseActivity extends AppCompatActivity implements IExceptionView{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).getComponent());
    }

    protected abstract void setupComponent(ServiceComponent component);

    @Override
    public void talkException(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
