package com.david.calendaralarm.utils;

import org.joda.time.DateTime;

public class AlarmContentUtils {

    /**
     * formatting time, if the length of hours or minutes is not enough two, then fill in zero
     * @param executionDateString time of string type
     * @return hour and minutes of the formate time
     */
    public static String getTitle(String executionDateString) {
        DateTime executionDate = DateTime.parse(executionDateString);
        return getTitle(executionDate);
    }

    public static String getTitle(DateTime executionDate) {
        return getFormattedTime(executionDate);
    }

    /**
     * formatting time, if the length of hours or minutes is not enough two, then fill in zero
     * @param date the unformat time
     * @return hour and minutes of the formate time
     */
    private static String getFormattedTime(DateTime date) {
        long hour = date.getHourOfDay();
        long minute = date.getMinuteOfHour();
        return getFormattedHourOrMinute(hour)
                + ":"
                + getFormattedHourOrMinute(minute);
    }

    /**
     * formatting time, if the length of hours or minutes is not enough two, then fill in zero
     * @param hourOrMinute hour or minutes
     * @return the formate time
     */
    private static String getFormattedHourOrMinute(long hourOrMinute) {
        return hourOrMinute < 10
                ? "0" + String.valueOf(hourOrMinute)
                : String.valueOf(hourOrMinute);
    }

}
