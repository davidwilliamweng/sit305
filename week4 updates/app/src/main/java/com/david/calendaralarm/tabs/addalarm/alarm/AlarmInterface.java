package com.david.calendaralarm.tabs.addalarm.alarm;

import com.david.calendaralarm.data.pojo.Alarm;

public interface AlarmInterface {

    void deleteAlarmById(String id);

    void showEditDialog(Alarm alarm);

}
