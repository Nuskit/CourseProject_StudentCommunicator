package com.bsuir.poit.studentcommunicator.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.DaggerUILoginComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UILoginComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.helper.IHasComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.LoginViewModule;
import com.bsuir.poit.studentcommunicator.presenter.impl.LoginPresenter;
import com.bsuir.poit.studentcommunicator.view.ILoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView, IHasComponent<UILoginComponent> {

    @Inject LoginPresenter loginPresenter;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Intent a= new Intent(this, MainWorkActivity.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(a);
    }

    @Override
    protected void setupComponent(ServiceComponent component) {
        uiLoginComponent = DaggerUILoginComponent.builder()
                .serviceComponent(component)
                .loginViewModule(new LoginViewModule(this))
                .build();
        uiLoginComponent.inject(this);
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void loginComplete(boolean isLogin) {
        Toast.makeText(this, String.valueOf(isLogin), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sign_in_button)
    public void button_sign_in(){
        loginPresenter.checkLogin();
    }

    private UILoginComponent uiLoginComponent;

    @Override
    public UILoginComponent getComponent() {
        return uiLoginComponent;
    }
}
