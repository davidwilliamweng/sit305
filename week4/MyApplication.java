package com.david.calendaralarm.app;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import io.realm.Realm;

/**
 * One app one application
 */
public class MyApplication extends Application {

    private static WeakReference<Context> contextWeakReference;

    @Override
    public void onCreate() {
        super.onCreate();
        // Init realm DB.
        Realm.init(this);
        contextWeakReference = new WeakReference<>(getApplicationContext());
    }

    public static Context getContext() {
        return contextWeakReference.get();
    }
}
