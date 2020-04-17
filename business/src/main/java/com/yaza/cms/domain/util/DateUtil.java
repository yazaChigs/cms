package com.yaza.cms.domain.util;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static Date getDateDiffMonth(int factor) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, factor);
        return calendar.getTime();
    }

    public static Integer getYearFromDate(Date  date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static String getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public static String getFrindlyName(Date date) {
        return getMonthFromDate(date) + " " + getYearFromDate(date);
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    public static Date getYesterdayDate(int factor) {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, factor);
        return calendar.getTime();
    }

}
