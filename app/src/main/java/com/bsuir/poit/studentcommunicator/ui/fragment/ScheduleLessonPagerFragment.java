package com.bsuir.poit.studentcommunicator.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation.UIMainWorkScope;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UILoginComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.dagger.component.ui.UIMainWorkComponent;
import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.ui.adapter.ScheduleDayLessonFragmentAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleLessonPagerFragment extends BaseFragment{

    @Inject
    DateManager dateManager;

    public static ScheduleLessonPagerFragment newInstance(){
        return new ScheduleLessonPagerFragment();
    }


    @BindView(R.id.schedule_pager)
    ViewPager viewPager;

    @BindView(R.id.schedule_tabs)
    PagerSlidingTabStrip tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_pager, container, false);
        ButterKnife.bind(this, view);

        //TODO : change
        viewPager.setAdapter(new ScheduleDayLessonFragmentAdapter(getActivity().getSupportFragmentManager(), dateManager));

        tabs.setViewPager(viewPager);

        return view;
    }

    @Override
    protected void initDiComponent() {
        getComponent(UIMainWorkComponent.class).inject(this);
    }
}
