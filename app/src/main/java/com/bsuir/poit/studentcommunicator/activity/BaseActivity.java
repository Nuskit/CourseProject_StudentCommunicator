package com.bsuir.poit.studentcommunicator.activity;

import android.app.Activity;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.view.IExceptionView;

public class BaseActivity extends Activity implements IExceptionView{
    @Override
    public void talkException(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
