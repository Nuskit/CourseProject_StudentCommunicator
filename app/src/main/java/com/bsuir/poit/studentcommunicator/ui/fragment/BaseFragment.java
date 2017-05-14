package com.bsuir.poit.studentcommunicator.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.helper.IHasComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDiComponent();
    }
    abstract protected void initDiComponent();

    public <T> T getComponent(Class<T> clazz){
        Activity activity = getActivity();
        IHasComponent<T> has = (IHasComponent<T>) activity;
        return has.getComponent();
    }
}
