package com.david.calendaralarm.tabs.addalarm.alarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.david.calendaralarm.R;
import com.david.calendaralarm.data.pojo.Alarm;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AddEditAlarmActivity extends AppCompatActivity {

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    @BindView(R.id.et_label)
    EditText etLabel;
    @BindView(R.id.cb_mon)
    CheckBox cbMon;
    @BindView(R.id.cb_tues)
    CheckBox cbTues;
    @BindView(R.id.cb_wed)
    CheckBox cbWed;
    @BindView(R.id.cb_thurs)
    CheckBox cbThurs;
    @BindView(R.id.cb_fri)
    CheckBox cbFri;
    @BindView(R.id.cb_sat)
    CheckBox cbSat;
    @BindView(R.id.cb_sun)
    CheckBox cbSun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_alarm);
        ButterKnife.bind(this);
        setupUi();
    }

    private void setupUi(){
//        Alarm alarm = getAlarm();
//        ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());
//        etLabel.setText(alarm.getLabel());
//        setDayCheckboxes(alarm);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDayCheckboxes(Alarm alarm) {
//        cbMon.setChecked(alarm.getDay(Alarm.MON));
//        cbTues.setChecked(alarm.getDay(Alarm.TUES));
//        cbWed.setChecked(alarm.getDay(Alarm.WED));
//        cbThurs.setChecked(alarm.getDay(Alarm.THURS));
//        cbFri.setChecked(alarm.getDay(Alarm.FRI));
//        cbSat.setChecked(alarm.getDay(Alarm.SAT));
//        cbSun.setChecked(alarm.getDay(Alarm.SUN));
    }

}
