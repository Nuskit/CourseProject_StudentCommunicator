package com.bsuir.poit.studentcommunicator.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsuir.poit.studentcommunicator.infrastructure.dagger.helper.IHasComponent;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initDiComponent();
        super.onCreate(savedInstanceState);
    }

    abstract protected void initDiComponent();

    public <T> T getComponent(Class<T> clazz){
        Activity activity = getActivity();
        IHasComponent<T> has = (IHasComponent<T>) activity;
        return has.getComponent();
    }
}
