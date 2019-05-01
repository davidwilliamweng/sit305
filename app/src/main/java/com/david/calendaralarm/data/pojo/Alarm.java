package com.david.calendaralarm.data.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Alarm extends RealmObject {

    @PrimaryKey
    @Required
    private String id;

    private String label;
    private String time;
    private String days;
    private String ringtone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRingtone() {
        return ringtone;
    }

    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", time='" + time + '\'' +
                ", days='" + days + '\'' +
                ", ringtone='" + ringtone + '\'' +
                '}';
    }
}
