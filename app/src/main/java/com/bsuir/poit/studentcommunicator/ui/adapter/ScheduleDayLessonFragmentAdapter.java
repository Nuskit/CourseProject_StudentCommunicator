package com.bsuir.poit.studentcommunicator.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.ui.fragment.ScheduleDayLessonsFragment;

import java.util.Calendar;

public class ScheduleDayLessonFragmentAdapter extends FragmentPagerAdapter {

    private final DateManager dateManager;

    public ScheduleDayLessonFragmentAdapter(FragmentManager fm, DateManager dateManager) {
        super(fm);
        this.dateManager = dateManager;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleDayLessonsFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dateManager.getMonthDayAfter(position);
    }
}