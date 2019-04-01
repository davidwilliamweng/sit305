package com.david.calendaralarm.tabs.addalarm.alarm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.david.calendaralarm.R;
import com.david.calendaralarm.app.MyApplication;
import com.david.calendaralarm.data.AlarmDAO;
import com.david.calendaralarm.data.pojo.Alarm;
import com.david.calendaralarm.schedule.AlarmController;
import org.joda.time.DateTime;
import java.util.UUID;

public final class AddEditAlarmActivity extends AppCompatActivity {

    TextView tvCancel;
    TextView tvTitle;
    TextView tvSave;
    TimePicker timePicker;
    EditText etLabel;
    CheckBox cbMon;
    CheckBox cbTues;
    CheckBox cbWed;
    CheckBox cbThurs;
    CheckBox cbFri;
    CheckBox cbSat;
    CheckBox cbSun;
    private TextView tvDel;

    private Alarm alarm;
    private AlarmController alarmController;
    private AlarmDAO alarmDAO;
    private int hour,minute;
    private enum STATE{
        ADD,EDIT
    }
    private STATE state;
    private String oldtime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_alarm);
        setupUi();
    }

    @TargetApi(23)
    private void setupUi(){
        tvCancel = findViewById(R.id.tvCancel);
        tvTitle = findViewById(R.id.tvTitle);
        tvSave = findViewById(R.id.tvSave);
        timePicker = findViewById(R.id.timePicker);
        etLabel = findViewById(R.id.etLabel);
        cbMon = findViewById(R.id.cbMon);
        cbTues = findViewById(R.id.cbTues);
        cbWed = findViewById(R.id.cbWed);
        cbThurs = findViewById(R.id.cbThurs);
        cbFri = findViewById(R.id.cbFri);
        cbSat = findViewById(R.id.cbSat);
        cbSun = findViewById(R.id.cbSun);
        tvDel = findViewById(R.id.tvDel);
        hour = DateTime.now().getHourOfDay();
        minute = DateTime.now().getMinuteOfHour();
        timePicker.setIs24HourView(true);
        alarm = MyApplication.getInstance().getAlarm();
        state = STATE.ADD;
        tvDel.setVisibility(View.GONE);
        if(alarm != null){
            tvDel.setVisibility(View.VISIBLE);
            state = STATE.EDIT;
            etLabel.setText(alarm.getLabel());
            oldtime = alarm.getTime();
            DateTime dt = DateTime.parse(oldtime);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour = dt.getHourOfDay();
                minute = dt.getMinuteOfHour();
                timePicker.setHour(hour);
                timePicker.setMinute(minute);
            }
            String days = alarm.getDays();
            if(!TextUtils.isEmpty(days) && days.contains(",")){
                String[] array = days.split(",");
                for (int i = 0; i < array.length; i++){
                    if(TextUtils.isEmpty(array[i])){
                        continue;
                    }
                    switch (array[i]){
                        case "Mon":
                            cbMon.setChecked(true);
                            break;
                        case "Tues":
                            cbTues.setChecked(true);
                            break;
                        case "Wed":
                            cbWed.setChecked(true);
                            break;
                        case "Thurs":
                            cbThurs.setChecked(true);
                            break;
                        case "Fri":
                            cbFri.setChecked(true);
                            break;
                        case "Sat":
                            cbSat.setChecked(true);
                            break;
                        case "Sun":
                            cbSun.setChecked(true);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        alarmController = new AlarmController(this);
        alarmDAO = new AlarmDAO();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == STATE.EDIT){
                    alarmDAO.removeFromRealmById(alarm.getId());
                    alarmController.cancelAlarm(alarm);
                }
                alarm = new Alarm();
                alarm.setId(UUID.randomUUID().toString());
                alarm.setLabel(etLabel.getText().toString().equals("") ? "Good morning ~" : etLabel.getText().toString());

                Intent intent = getIntent();
                // Must be add opearation in here.
                if(intent != null && !TextUtils.isEmpty(intent.getStringExtra("date"))){
                    alarm.setDays(intent.getStringExtra("date"));
                    String[] datearray = intent.getStringExtra("date").split("-");
                    String ddd;
                    if(datearray.length == 3){
                         ddd = DateTime.now().withYear(Integer.parseInt(datearray[0]))
                                 .withMonthOfYear(Integer.parseInt(datearray[1]))
                                 .withDayOfMonth(Integer.parseInt(datearray[2]))
                                 .withHourOfDay(hour)
                                 .withMinuteOfHour(minute)
                                 .withSecondOfMinute(0)
                                 .withMillisOfSecond(0)
                                 .toString();
                    }else {
                        ddd = DateTime.now()
                                .withHourOfDay(hour)
                                .withMinuteOfHour(minute)
                                .withSecondOfMinute(0)
                                .withMillisOfSecond(0)
                                .plus(9999).toString();
                    }
                    alarm.setTime(ddd);
                    Log.i("hel:", "set alarm:" + ddd);
                }else{
                    if(state == STATE.EDIT){
                        DateTime dt = DateTime.parse(oldtime);
                        String ddd = DateTime.now().withYear(dt.getYear())
                                .withMonthOfYear(dt.getMonthOfYear())
                                .withDayOfMonth(dt.getDayOfMonth())
                                .withHourOfDay(hour)
                                .withMinuteOfHour(minute)
                                .withSecondOfMinute(0)
                                .withMillisOfSecond(0)
                                .toString();
                        alarm.setTime(ddd);
                        Log.i("hel:", "set alarm:" + ddd);
                    }else{
                        String ddd = DateTime.now().withHourOfDay(hour)
                                .withMinuteOfHour(minute).withSecondOfMinute(0).withMillisOfSecond(0).toString();
                        alarm.setTime(ddd);
                        Log.i("hel:", "set alarm:" + ddd);
                    }

                    StringBuffer sb = new StringBuffer();
                    if(cbMon.isChecked()){
                        sb.append("Mon,");
                    }
                    if(cbTues.isChecked()){
                        sb.append("Tues,");
                    }
                    if(cbWed.isChecked()){
                        sb.append("Wed,");
                    }
                    if(cbThurs.isChecked()){
                        sb.append("Thurs,");
                    }
                    if(cbFri.isChecked()){
                        sb.append("Fri,");
                    }
                    if(cbSat.isChecked()){
                        sb.append("Sat,");
                    }
                    if(cbSun.isChecked()){
                        sb.append("Sun,");
                    }
                    if(sb.toString().equals("")) {
                        sb.append("Once");
                    }
                    alarm.setDays(sb.toString());
                }

                alarmDAO.saveIfNotDuplicate(alarm);
                alarmController.setAlarm(alarm);

                setResult(1);
                finish();
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
                hour = hourOfDay;
                minute = minuteOfHour;
            }
        });
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmDAO.removeFromRealmById(alarm.getId());
                alarmController.cancelAlarm(alarm);
                setResult(2);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().setAlarm(null);
    }

}
