package com.david.calendaralarm.tabs.addalarm.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import com.david.calendaralarm.R;
import org.joda.time.DateTime;


public class WakeUpAtSetTimeView extends LinearLayout {

    protected TimePicker timePicker;

    private DateTime dateTime;

    public WakeUpAtSetTimeView(Context context) {
        super(context);
    }

    public WakeUpAtSetTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WakeUpAtSetTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        timePicker = findViewById(R.id.dateTimeSelect);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                setDateTime(hourOfDay, minute);
            }
        });
    }

    private void setDateTime(int hourOfDay, int minute) {
        DateTime currentDate = DateTime.now();
        DateTime pickedDate = currentDate.withHourOfDay(hourOfDay)
                .withMinuteOfHour(minute);

        this.dateTime = currentDate.getHourOfDay() > hourOfDay
                ? pickedDate.plusDays(1)
                : pickedDate;
    }

    public DateTime getDateTime() {
        if (dateTime == null) {
            dateTime = DateTime.now();
        }
        return dateTime;
    }
}
