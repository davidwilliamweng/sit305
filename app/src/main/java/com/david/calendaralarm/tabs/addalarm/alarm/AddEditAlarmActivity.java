package com.david.calendaralarm.tabs.addalarm.alarm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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

    private TextView tvCancel;
    private TextView tvRingtone;
    private TextView tvSave;
    private TimePicker timePicker;
    private EditText etLabel;
    private CheckBox cbMon;
    private CheckBox cbTues;
    private CheckBox cbWed;
    private CheckBox cbThurs;
    private CheckBox cbFri;
    private CheckBox cbSat;
    private CheckBox cbSun;
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
    private String ringtoneuri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_alarm);
        Log.i("hel:", "AddEditAlarmActivity onCreate...");
        setupUi();
    }

    /**
     * Instantiate each component and set the state of the view according to the value passed by intent,
     * and if it is edited, instantiate the view according to the value passed by intent
     */
    @TargetApi(23)
    private void setupUi(){
        tvCancel = findViewById(R.id.tvCancel);
        tvRingtone = findViewById(R.id.tvRingtone);
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
        // If it's editing status
        if(alarm != null){
            tvDel.setVisibility(View.VISIBLE);
            state = STATE.EDIT;
            etLabel.setText(alarm.getLabel());
            oldtime = alarm.getTime();
            DateTime dt = DateTime.parse(oldtime);
             // New Api
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour = dt.getHourOfDay();
                minute = dt.getMinuteOfHour();
                timePicker.setHour(hour);
                timePicker.setMinute(minute);
            }
            if(!TextUtils.isEmpty(alarm.getRingtone())){
                Ringtone ringtone = RingtoneManager.getRingtone(this, Uri.parse(alarm.getRingtone()));
                tvRingtone.setText(ringtone.getTitle(this));
            }
            String days = alarm.getDays();
            // Set alarm days
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
                // If it's a calendar alarm clock
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
                    // If it's an edit alarm clock
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

                    // Save alarm days
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
                if(!TextUtils.isEmpty(ringtoneuri)) {
                    alarm.setRingtone(ringtoneuri);
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
        tvRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectRingtoneDialog();
            }
        });
    }

    /**
     * Show system ringtone setting dialog
     */
    private void showSelectRingtoneDialog() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
                getString(R.string.pref_ringtone_select_title));
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        startActivityForResult(intent, 1);
    }

    /**
     * Callback when selected system ringtone
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                ringtoneuri = uri.toString();
                Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
                String title = ringtone.getTitle(this);
                tvRingtone.setText(title);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().setAlarm(null);
    }

}
