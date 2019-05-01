package com.david.calendaralarm.utils;

public class TimeUtils {


    /**
     * formatting time, if the length of hours and minutes is not enough two, then fill in zero
     * @param year year of date
     * @param month month of year
     * @param day day of month
     * @return the formate time
     */
    public static String formatDatatime(int year, int month, int day){
        StringBuffer sb = new StringBuffer(year + "-");
        if(month < 10){
            sb.append("0");
        }
        sb.append(month + "-");
        if(day < 10){
            sb.append("0");
        }
        sb.append(day + "");
        return sb.toString();
    }

}
