package com.david.calendaralarm.schedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.david.calendaralarm.R;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.utils.AlarmContentUtils;
import com.david.calendaralarm.utils.Const;
import org.joda.time.DateTime;

/**
 * Display this page when the alarm clock rings
 */
public class AlarmActivity extends AppCompatActivity {

    private AlarmDAO alarmDAO;
    private AlarmController alarmController;
    private long ringDurationMs;

    protected TextView currentHourTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupWindowFlags();
        setAppTheme();

        setContentView(R.layout.activity_alarm);
        currentHourTextView = findViewById(R.id.activityAlarmCurrentHour);
        findViewById(R.id.activityAlarmLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        alarmDAO = new AlarmDAO();
        alarmController = new AlarmController(getApplicationContext());

//        removeExecutedAlarmFromDatabase();
        showCurrentHour();
        setupRingDuration();
        countDownRingDuration();
    }

    /**
     * Setting full screen of mobile phone
     */
    private void setupWindowFlags() {
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Setting mobile theme
     */
    private void setAppTheme() {
        int themeId = AppCompatDelegate.MODE_NIGHT_NO;
        getDelegate().setLocalNightMode(themeId);
    }

    /**
     * Set the current time
     */
    private void showCurrentHour() {
        String currentHour = AlarmContentUtils.getTitle(DateTime.now());
        currentHourTextView.setText(currentHour);
    }

    /**
     * Get the alarm clock pause time interval
     */
    private void setupRingDuration() {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        String ringDurationString = preferences.getString(getString(R.string.key_ring_duration),
                String.valueOf(Const.DEFAULTS.RING_DURATION_MS));
        ringDurationMs = Long.parseLong(ringDurationString);
    }

    /**
     * Close this page in a certain time
     */
    private void countDownRingDuration() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, ringDurationMs);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        alarmController.dismissCurrentlyPlayingAlarm();
        alarmDAO.cleanUp();
    }
}
