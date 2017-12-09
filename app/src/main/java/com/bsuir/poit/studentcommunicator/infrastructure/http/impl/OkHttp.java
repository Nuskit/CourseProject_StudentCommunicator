package com.bsuir.poit.studentcommunicator.infrastructure.http.impl;

import android.os.AsyncTask;

import com.bsuir.poit.studentcommunicator.infrastructure.date.DateManager;
import com.bsuir.poit.studentcommunicator.infrastructure.http.IHttp;
import com.bsuir.poit.studentcommunicator.infrastructure.http.exception.HttpException;
import com.bsuir.poit.studentcommunicator.model.LessonNotification;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp implements IHttp {
    private static final String BASE_ADDRESS = "http://192.168.43.21/";
    private static final String POST_LOGIN = "login.php";
    private static final String POST_SCHEDULE = "schedule.php";
    private static final String POST_HAVE_NOTIFICATION = "have_notification.php";

    private final OkHttpClient httpClient;
    private final DateManager dateManager;

    public OkHttp(DateManager dateManager) {
       httpClient = new OkHttpClient();
        this.dateManager = dateManager;
    }


    private static String getUrlWithPost(String postPart){
        return String.format("%s%s", BASE_ADDRESS, postPart);
    }

    @Override
    public Integer checkLogin(String email, String password) throws HttpException{
        try {
            HttpUrl.Builder builder = HttpUrl.parse(getUrlWithPost(POST_LOGIN)).newBuilder();
            builder.addQueryParameter("login", email);
            builder.addQueryParameter("password", password);
            String url = builder.build().toString();

            AsyncTask task = new AsyncTask<String, Void, Integer>() {
                @Override
                protected Integer doInBackground(String... params) {
                    Integer loginId = null;

                    Request request = new Request.Builder().url(params[0]).build();

                    try {
                        Response response = httpClient.newCall(request).execute();

                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                        JSONObject jsonObject = new JSONObject(response.body().string());
                        loginId = (Integer) jsonObject.get("profileId");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return loginId;
                }
            }.execute(url);
            return (Integer) task.get();
        }catch (Exception e){
            throw new HttpException(e);
        }
    }

    @Override
    public List<LessonSchedule> getSchedule(Date currentDate) throws HttpException {
        try {
            HttpUrl.Builder builder = HttpUrl.parse(getUrlWithPost(POST_SCHEDULE)).newBuilder();
            builder.addQueryParameter("schedule_date", dateManager.getScheduleDate(currentDate));
            String url = builder.build().toString();

            AsyncTask task = new AsyncTask<String, Void, List<LessonSchedule>>() {
                @Override
                protected List<LessonSchedule> doInBackground(String... params) {
                    final List<LessonSchedule> lessonSchedules = new ArrayList<>();

                    Request request = new Request.Builder().url(params[0]).build();

                    try {
                        Response response = httpClient.newCall(request).execute();

                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);

                        JSONArray jsonarray = new JSONArray(response.body().string());
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            lessonSchedules.add(new LessonSchedule(
                                    String.format("%s - %s", jsonobject.getString("start_time"), jsonobject.getString("end_time")),
                                    jsonobject.getString("lesson_type"),
                                    jsonobject.getString("lesson_subject"),
                                    jsonobject.getString("teacher_name"),
                                    jsonobject.getString("lesson_group"),
                                    jsonobject.getString("lesson_place"),
                                    null
                            ));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return lessonSchedules;
                }
            }.execute(url);
            return (List<LessonSchedule>) task.get();
        }catch (Exception e){
            throw new HttpException(e);
        }
    }

    @Override
    public boolean haveNewNotifications(int profileId) throws HttpException {
        try {
            HttpUrl.Builder builder = HttpUrl.parse(getUrlWithPost(POST_HAVE_NOTIFICATION)).newBuilder();
            builder.addQueryParameter("profile_id", String.valueOf(profileId));
            String url = builder.build().toString();


            AsyncTask task = new AsyncTask<String, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    boolean haveNewNotification = false;

                    Request request = new Request.Builder().url(params[0]).build();

                    try {
                        Response response = httpClient.newCall(request).execute();

                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                        JSONObject jsonObject = new JSONObject(response.body().string());
                        haveNewNotification = (Boolean)jsonObject.get("haveNewNotification");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return haveNewNotification;
                }
            }.execute(url);
            return (Boolean)task.get();
        }catch (Exception e){
            throw new HttpException(e);
        }
    }
}
