package com.bsuir.poit.studentcommunicator.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.DaggerUIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.helper.IHasComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.MainWorkViewModule;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.presenter.impl.MainWorkActivityPresenter;
import com.bsuir.poit.studentcommunicator.ui.fragment.ScheduleLessonPagerFragment;
import com.bsuir.poit.studentcommunicator.view.IMainWorkView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainWorkActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainWorkView, IHasComponent<UIMainWorkComponent> {

    @Inject
    MainWorkActivityPresenter mainWorkActivityPresenter;
    @Inject
    DateManager dateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        mainWorkActivityPresenter.onCreate();
    }

    private UIMainWorkComponent uiMainWorkComponent;

    @Override
    protected void setupComponent(ServiceComponent component) {
        uiMainWorkComponent = DaggerUIMainWorkComponent.builder()
                .serviceComponent(component)
                .mainWorkViewModule(new MainWorkViewModule(this))
                .build();
        uiMainWorkComponent.inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private MenuItem currentItem = null;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case (R.id.nav_schedule): {


                ScheduleLessonPagerFragment lesson = (ScheduleLessonPagerFragment) getSupportFragmentManager().findFragmentById(R.id.schedule_lesson_pager);

                if (lesson == null) {
                    lesson = ScheduleLessonPagerFragment.newInstance();
                }
                changeVisibilityMenuItem(item);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.empty_schedule, lesson);
                ft.commit();

                break;
            }
            case (R.id.nav_notification): {
                break;
            }

            case (R.id.nav_settings): {
                break;
            }

            case (R.id.nav_logout): {
                logout();
                break;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeVisibilityMenuItem(MenuItem newMenuItem){
        if (currentItem != null){
            currentItem.setVisible(true);
        }
        newMenuItem.setVisible(false);
        currentItem = newMenuItem;
    }

    private void logout() {
        mainWorkActivityPresenter.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public UIMainWorkComponent getComponent() {
        return uiMainWorkComponent;
    }


    @BindView(R.id.toolbar_date)
    TextView toolbarDate;

    @BindView(R.id.toolbar_head)
    TextView toolBarGroup;

    @BindView(R.id.toolbar_logo)
    ImageView toolBarLogo;

    @BindView(R.id.toolbar_notification)
    ImageView toolBarNotification;

    @Override
    public void setBarTime(String currentTime) {
        toolbarDate.setText(currentTime);
    }

    @Override
    public void setBarNotification(boolean haveNewNotifications) {
        toolBarNotification.setImageResource(haveNewNotifications
                ? R.mipmap.ic_cloud_notification
                : R.mipmap.ic_cloud_no_notification);
    }

    @Override
    public void setBarSubGroups(List<String> subGroups) {
        //TODO : may be remove
    }

    @Override
    public void setBarLogo(String universityLogo) {
        toolBarLogo.setImageResource(R.mipmap.ic_bsuir_logo);

    }

    @Override
    public void setBarGroup(String numberGroup) {
        toolBarGroup.setText(numberGroup);
    }
}
