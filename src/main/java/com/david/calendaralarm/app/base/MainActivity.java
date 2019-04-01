package com.david.calendaralarm.app.base;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.tabs.addalarm.alarm.AlarmFragment;
import com.david.calendaralarm.tabs.addalarm.calendar.CalendarFragment;

/**
 * Main user interface of this program
 */
public class MainActivity extends AppCompatActivity
        implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private FragmentManager fragmentManager;
    private int lastBottomViewClickedId = -1;
    private ObjectAnimator wakeUpAtButtonAnimator;

    BottomNavigationView bottomNavigationBar;
    LinearLayout addAlarmBtn;

    private final int ANIM_TIME_IN_MS = 300;
    private final float POS_TO_MAKE_BUTTON_GONE = 100f;
    private final float POS_TO_MAKE_BUTTON_VISIBLE = -50f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        addAlarmBtn = findViewById(R.id.addAlarmBtn);
        RealmManager.initializeRealmConfig();
        fragmentManager = getSupportFragmentManager();
        bottomNavigationBar.setOnNavigationItemSelectedListener(this);
        bottomNavigationBar.setSelectedItemId(R.id.actionAlarm);
        addAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = fragmentManager.findFragmentById(R.id.main_activity_container);
                if(f != null && f instanceof AlarmFragment){
                    ((AlarmFragment)f).showSetTimeDialog();
                }else{
                    ((CalendarFragment)f).showSetTimeDialog();
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int menuItemId = menuItem.getItemId();
        if (lastBottomViewClickedId != menuItemId) {
            switch (menuItemId) {
                case R.id.actionAlarm:
                    animateWakeUpAtButton(POS_TO_MAKE_BUTTON_GONE, POS_TO_MAKE_BUTTON_VISIBLE);
                    replaceFragment(new AlarmFragment());
                    break;
                case R.id.actionCalendar:
                    animateWakeUpAtButton(POS_TO_MAKE_BUTTON_GONE, POS_TO_MAKE_BUTTON_VISIBLE);
                    replaceFragment(new CalendarFragment());
                    break;
                default:
                    break;
            }
        }
        lastBottomViewClickedId = menuItemId;
        return true;
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.grow_fade_in_center, R.anim.fast_fade_out)
                .replace(R.id.main_activity_container, newFragment)
                .commit();
    }

    private boolean isButtonAfterFirstPress;

    @Override
    public void onBackPressed() {
        if (!isButtonAfterFirstPress) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.toast_double_back_to_exit), Toast.LENGTH_SHORT).show();
            isButtonAfterFirstPress = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isButtonAfterFirstPress = false;
                }
            }, 2000);
        }else{
            finish();
        }
    }

    private void cancelAnimationIfNeeded() {
        if (wakeUpAtButtonAnimator !=null && wakeUpAtButtonAnimator.isRunning()){
            wakeUpAtButtonAnimator.cancel();
        }
    }

    private void animateWakeUpAtButton(final float startPosY, final float endPosY) {
            cancelAnimationIfNeeded();

            wakeUpAtButtonAnimator = ObjectAnimator.ofFloat(addAlarmBtn,
                    "translationY", startPosY, endPosY);
            wakeUpAtButtonAnimator.setDuration(ANIM_TIME_IN_MS);
            wakeUpAtButtonAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    addAlarmBtn.setVisibility(View.VISIBLE);
                    Fragment f = fragmentManager.findFragmentById(R.id.main_activity_container);
                    if(f != null && f instanceof CalendarFragment){
                        addAlarmBtn.setBackground(getResources().getDrawable(R.drawable.button_fab_extended));
                    }else{
                        addAlarmBtn.setBackground(getResources().getDrawable(R.drawable.button_fab_extended_drawable));
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    addAlarmBtn.setVisibility(View.GONE);
                }
            });
            wakeUpAtButtonAnimator.start();
    }



}
