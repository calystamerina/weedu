package com.telkom.weedu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class DateUtils {
    public static String formatEdumailDate(String date) {
        String formattedTimeDate = null;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String today = getCurrentDate();
        String[] d = date.split("T");
        String[] days = d[0].split("-");
        String[] times = d[1].split(":");

        if (d[0].equalsIgnoreCase(today)) {
            formattedTimeDate = times[0] + ":" + times[1];
        } else if (Integer.parseInt(days[0]) == year) {
            formattedTimeDate = days[2] + " " + getMonth(Integer.parseInt(days[1]));
        } else {
            formattedTimeDate = d[0];
        }

        return formattedTimeDate;
    }

    public static String getReadableEdumailDate(String date) {
        String formattedTimeDate = null;
        String[] d = date.split("T");
        String[] days = d[0].split("-");
        String[] times = d[1].split(":");

        formattedTimeDate = days[2] + " " + getMonth(Integer.parseInt(days[1])) + " " + days[0] + " " + times[0] + ":" + times[1];

        return formattedTimeDate;
    }

    private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private static String getMonth(int i) {
        String[] months = new String[]{"Januari", "Februari", "Maret", "April", "Mei",
                "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

        return months[i - 1];
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        return year;
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;

        return month;
    }

    public static String getReadableDate(String date) {
        String readableDate = null;
        String[] months = new String[]{
                "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
                "September", "Oktober", "November", "Desember"
        };

        String[] t = date.split("T");
        String[] d = t[0].split("-");
        int year = Integer.valueOf(d[0]);
        int month = Integer.valueOf(d[1]);
        int day = Integer.valueOf(d[2]);

        readableDate = day + " " + months[month - 1] + " " + year;

        return readableDate;
    }

    public static String getLocalDayName(String date) {
        String readableDate = null;
        String[] days = new String[]{
                "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"
        };

        String[] t = date.split("T");
        String[] d = t[0].split("-");
        int year = Integer.valueOf(d[0]);
        int month = Integer.valueOf(d[1]);
        int day = Integer.valueOf(d[2]);
        Date date1 = new Date();

        String dateString = String.format("%d-%d-%d", year, month, day);
        try {
            date1 = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Then get the day of week from the Date based on specific locale.
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date1);
        String localName = "";

        switch (dayOfWeek) {
            case "Sunday":
                localName = "Minggu";
                break;
            case "Monday":
                localName = "Senin";
                break;
            case "Tuesday":
                localName = "Selasa";
                break;
            case "Wednesday":
                localName = "Rabu";
                break;
            case "Thursday":
                localName = "Kamis";
                break;
            case "Friday":
                localName = "Jumat";
                break;
            case "Saturday":
                localName = "Sabtu";
                break;
            default:
                break;
        }
        return localName;
    }

    public static String getReadableTime(String date) {
        String readableDate = null;

        String[] t = date.split("T");
        String[] d = t[1].split(":");
        String hour = d[0];
        String min = d[1];

        readableDate = hour + ":" + min;

        return readableDate;
    }
}
