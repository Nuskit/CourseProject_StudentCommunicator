package com.bsuir.poit.studentcommunicator.infrastructure.session.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.bsuir.poit.studentcommunicator.infrastructure.session.ISession;
import com.bsuir.poit.studentcommunicator.infrastructure.session.dto.UserInformation;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceSession implements ISession{

    private static final String DEFAULT_EMPTY_VALUE = "";
    private static final int PREFERENCES_MODE = MODE_PRIVATE;
    private static final String STORAGE_NAME = "AccountStorage";
    private static final String KEY_ACCOUNT = "key_account";
    private static final String KEY_PASSWORD = "key_password";
    private final Context context;

    public PreferenceSession(Context context){
        this.context = context;
    }

    private SharedPreferences getSharedPreferences(){
        return context.getSharedPreferences(STORAGE_NAME, PREFERENCES_MODE);
    }

    @Override
    public void setAccount(String login, String password) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_ACCOUNT, login);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    @Override
    public void resetAccount() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_ACCOUNT, DEFAULT_EMPTY_VALUE);
        editor.putString(KEY_PASSWORD, DEFAULT_EMPTY_VALUE);
        editor.commit();
    }

    @Override
    public String getLogin() {
        return getSharedPreferences().getString(KEY_ACCOUNT, DEFAULT_EMPTY_VALUE);
    }

    @Override
    public String getPassword() {
        return getSharedPreferences().getString(KEY_PASSWORD, DEFAULT_EMPTY_VALUE);
    }

    @Override
    public boolean isLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return !(sharedPreferences.getString(KEY_ACCOUNT, DEFAULT_EMPTY_VALUE).equals(DEFAULT_EMPTY_VALUE)
                || sharedPreferences.getString(KEY_PASSWORD, DEFAULT_EMPTY_VALUE).equals(DEFAULT_EMPTY_VALUE));
    }

    @Override
    public void setAccountInformation(UserInformation userInformation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getGroup() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAuthorId() {
        throw new UnsupportedOperationException();
    }
}
