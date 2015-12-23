/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiemanh.vn.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author KiemPQ
 */
public class AppUtils {

    private static final String DEFAULT_PATERN = "dd/MM/yyyy HH:mm:ss";
    public static final String TIME_PATERN = "dd-MM-yyyy HH:mm:ss.SSS";
    private static int countAccount = 0;

    public synchronized static void setCountAccount() {
        countAccount++;
    }

    public static int getCountAccount() {
        return countAccount;
    }

    public static String curTime24(String space) {
        String result = "";
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String month = (intMonth < 10) ? "0" + intMonth : String.valueOf(intMonth);
        int intDay = Calendar.getInstance().get(Calendar.DATE);
        String day = (intDay < 10) ? "0" + intDay : String.valueOf(intDay);
        int intHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String hh = (intHour < 10) ? "0" + intHour : String.valueOf(intHour);
        int intMinute = Calendar.getInstance().get(Calendar.MINUTE);
        String mm = (intMinute < 10) ? "0" + intMinute : String.valueOf(intMinute);
        int intSecond = Calendar.getInstance().get(Calendar.SECOND);
        String ss = (intSecond < 10) ? "0" + intSecond : String.valueOf(intSecond);
        result = day + space + month + space + year + " " + hh + ":" + mm + ":" + ss;
        return result;
    }

    public static String formatDate(long datetime) {
        // TODO Auto-generated method stub
        return formatDate(new Date(datetime), DEFAULT_PATERN);
    }

    public static String formatDate(Date d, String patern) throws NullPointerException, IllegalArgumentException {
        if (d == null) {
            return null;
        }
        return new SimpleDateFormat(patern).format(d);
    }

    public static long getLongCurrentTime() {
        long rs = 0;
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATERN);
            String time = dateFormat.format(date.getTime());
            rs = dateFormat.parse(time).getTime();
        } catch (ParseException ex) {
            return rs;
        }
        return rs;
    }

    public static long parseLongTime(String time) {
        if (time == null) {
            return 0;
        }
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATERN);
        long rs = 0;
        try {
            rs = dateFormat.parse(time).getTime();
        } catch (ParseException ex) {
            return rs;
        }
        return rs;
    }

    public static long parseTime(String time) {
        if (time == null) {
            return 0;
        }
        DateFormat dateFormat = new SimpleDateFormat(TIME_PATERN);
        long rs = 0;
        try {
            rs = dateFormat.parse(time).getTime();
        } catch (ParseException ex) {
            return rs;
        }
        return rs;
    }

    public static String parseString(Object o) {
        if (o == null) {
            return "";
        }
        String value = String.valueOf(o);
        if ("null".equals(value)) {
            value = "";
        }
        return value;
    }

    public static long parseLong(Object o) {
        if (o == null) {
            return 0;
        }
        long value = 0;
        try {
            value = Long.parseLong(String.valueOf(o));
        } catch (Exception e) {
        }
        return value;
    }

    public static int parseInt(Object o) {
        if (o == null) {
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(String.valueOf(o));
        } catch (Exception e) {
        }
        return value;
    }

    public static double parseDouble(Object o) {
        if (o == null) {
            return 0;
        }
        double value = 0;
        try {
            value = Double.parseDouble(String.valueOf(o));
        } catch (Exception e) {
        }
        return value;
    }
}
