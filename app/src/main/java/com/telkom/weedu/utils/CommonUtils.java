package com.telkom.weedu.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.provider.Settings;

import com.telkom.weedu.data.api.model.edumail.Person;
import com.telkom.weedu.data.api.model.edumail.Target;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ghiyatshanif on 2/21/17.
 * purpose : put all commonly used methods here
 * notes : you can use ContextProvider.get() to provide context
 */

public final class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMyAppRunning(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        boolean run = false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcess.processName.equals(packageName)) {
                    run = true;
                    break;
                }
            }
        }

        return run;
    }

    public static boolean isPasswordStrongEnough(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String getReceiverEmails(ArrayList<Target> listConversationTo, ArrayList<String> listContentHeaderTo) {
        StringBuilder receivers = new StringBuilder();
        for (int i = 0; i < listConversationTo.size(); i++) {
            for (String s : listContentHeaderTo) {
                if (!s.equalsIgnoreCase(listConversationTo.get(i).getMailbox() + "@" + listConversationTo.get(i).getDomain())) {
                    receivers.append(s);
                } else {
                    receivers.append(listConversationTo.get(i).getMailbox() + "@" + listConversationTo.get(i).getDomain());
                }
            }
            if (i != listConversationTo.size() - 1) {
                receivers.append(", ");
            }
        }

        return receivers.toString();
    }

    public static String getRecepientEmails(ArrayList<Person> list) {
        String recepients = "";
        if (list != null){
            if (list.size() > 0){
                StringBuilder receivers = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    receivers.append(list.get(i).getEmail());
                    if (i != list.size() - 1) {
                        receivers.append(",");
                    }
                }

                recepients = receivers.toString();
            }
        }
        return recepients;
    }

    public static String getFirstName(String fullName) {
        String[] name = fullName.split(" ");

        return name[0];
    }

    public static String getLastName(String fullName) {
        String[] name = fullName.split(" ");

        String lastName = "";
        for (int i = 1; i < name.length; i++) {
            lastName += " " + name[i];
        }

//        if no last name then return firstname
        if ("".equals(lastName.trim())) {
            return name[0];
        } else {
            return lastName.trim();
        }
    }

    // 2017-04-23T10:00:00.000Z
    public static String getReadableDate(String unformatted) {
        String formatted = "";

        String[] strings = unformatted.split("T");
        formatted = strings[0];

        return formatted;

    }

    // 2017-04-23T10:00:00.000Z
    public static String getReadableHour(String unformatted) {
        String formatted = "";

        String[] date = unformatted.split("T");
        String[] hours = date[1].split(":");

        return hours[0] + "." + hours[1];

    }
}