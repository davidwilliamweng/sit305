package com.david.calendaralarm.schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Intent service = new Intent(context, AlarmService.class);
        if (extras != null) {
            service.putExtras(extras);
        } else {
            Log.e(getClass().getName(), "onReceive(): extras == null");
        }
        context.startService(service);
    }
}
