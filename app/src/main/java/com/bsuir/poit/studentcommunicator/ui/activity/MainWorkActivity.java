package com.bsuir.poit.studentcommunicator.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ServiceComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.DaggerUIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.helper.IHasComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.module.impl.MainWorkViewModule;
import com.bsuir.poit.studentcommunicator.presenter.impl.MainWorkActivityPresenter;
import com.bsuir.poit.studentcommunicator.ui.fragment.ScheduleLessonFragment;
import com.bsuir.poit.studentcommunicator.view.IMainWorkView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainWorkActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainWorkView, IHasComponent<UIMainWorkComponent> {

    @Inject
    MainWorkActivityPresenter mainWorkActivityPresenter;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


            ScheduleLessonFragment lesson = (ScheduleLessonFragment) getFragmentManager().findFragmentById(R.id.schedule_lesson);

            if (lesson == null){
                lesson = ScheduleLessonFragment.newInstance();
            }

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.empty_schedule, lesson);
            ft.addToBackStack("Back");
            ft.commit();


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public UIMainWorkComponent getComponent() {
        return uiMainWorkComponent;
    }


    @BindView(R.id.toolbar_date)
    TextView toolbarDate;

    @BindView(R.id.toolbar_head)
    TextView toolBarGroup;

    @Override
    public void setBarTime(Date currentTime) {
        toolbarDate.setText(String.valueOf(currentTime));
    }

    @Override
    public void setBarNotification(boolean haveNewNotifications) {
        //TODO: add image
        Toast.makeText(this, haveNewNotifications ? "Get notification": "No notification", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBarSubGroups(List<String> subGroups) {
        //TODO : may be remove
    }

    @Override
    public void setBarLogo(String universityLogo) {
        //TODO: load image
    }

    @Override
    public void setBarGroup(String numberGroup) {
        toolBarGroup.setText(numberGroup);
    }
}
