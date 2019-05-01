package com.david.calendaralarm.app;

import android.app.Application;
import android.content.Context;

import com.david.calendaralarm.data.pojo.Alarm;

import java.lang.ref.WeakReference;

import io.realm.Realm;

/**
 * One app one application
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        // Init realm DB.
        Realm.init(this);
    }

    /**
     * Single instaince of Application
     * @return The single object
     */
    public static MyApplication getInstance()
    {
        return myApplication;
    }

    private Alarm alarm;

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}