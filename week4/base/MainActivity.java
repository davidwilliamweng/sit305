package com.david.calendaralarm.app.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.RealmManager;
import com.david.calendaralarm.tabs.addalarm.alarm.AlarmFragment;
import com.david.calendaralarm.tabs.addalarm.calendar.CalendarFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Interface of Program
 */
public class MainActivity extends AppCompatActivity
        implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private FragmentManager fragmentManager;
    private int lastBottomViewClickedId = -1;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView bottomNavigationBar;
    @BindView(R.id.addAlarmBtn)
    Button addAlarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RealmManager.initializeRealmConfig();
        fragmentManager = getSupportFragmentManager();
        bottomNavigationBar.setOnNavigationItemSelectedListener(this);
        bottomNavigationBar.setSelectedItemId(R.id.action_alarm);
        addAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = fragmentManager.findFragmentById(R.id.main_activity_container);
                if(f != null && f instanceof AlarmFragment){
                    ((AlarmFragment)f).showSetTimeDialog();
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int menuItemId = menuItem.getItemId();
        if (lastBottomViewClickedId != menuItemId) {
            switch (menuItemId) {
                case R.id.action_alarm:
                    addAlarmBtn.setVisibility(View.VISIBLE);
                    replaceFragment(new AlarmFragment());
                    break;
                case R.id.action_calendar:
                    addAlarmBtn.setVisibility(View.GONE);
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

}
