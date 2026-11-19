package com.veepoo.protocol.util;

import android.text.TextUtils;
import com.veepoo.protocol.model.datas.TimeData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_j.class */
public abstract class vp_j {
    public static final SimpleDateFormat vp_a = new SimpleDateFormat("yyyy-MM-dd-HH:mm");

    public static String vp_c() {
        return vp_a(0);
    }

    public static String vp_d() {
        return vp_a(-1);
    }

    public static String vp_a() {
        return vp_a(-2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.text.DateFormat, java.text.SimpleDateFormat] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    public static Date vp_e(String str) {
        ?? r5;
        Date date = date;
        Date date2 = new Date();
        if (str == null || str.length() == 0) {
            return date;
        }
        ?? simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat = simpleDateFormat.parse(str);
            r5 = simpleDateFormat;
        } catch (Exception unused) {
            simpleDateFormat.printStackTrace();
            r5 = date;
        }
        return r5;
    }

    public static int vp_b(String str, String str2) {
        return (int) (((vp_f(str2).getTime() - vp_f(str).getTime()) / 1000) / 60);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.text.DateFormat, java.text.SimpleDateFormat] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    public static Date vp_f(String str) {
        ?? r6;
        Date date = date;
        Date date2 = new Date();
        if (str == null || str.length() == 0) {
            return date;
        }
        ?? simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            simpleDateFormat = simpleDateFormat.parse(str);
            r6 = simpleDateFormat;
        } catch (Exception unused) {
            simpleDateFormat.printStackTrace();
            r6 = date;
        }
        return r6;
    }

    public static boolean vp_d(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
            return true;
        } catch (ParseException unused) {
            return false;
        }
    }

    public static boolean vp_c(String str) {
        try {
            SimpleDateFormat simpleDateFormat = vp_a;
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
            return true;
        } catch (ParseException unused) {
            return false;
        }
    }

    public static TimeData vp_a(String str) {
        TimeData timeData = new TimeData();
        if (str.length() < 10) {
            return timeData;
        }
        int iVp_b = vp_b(str.substring(0, 4));
        int iVp_b2 = vp_b(str.substring(5, 7));
        int iVp_b3 = vp_b(str.substring(8, 10));
        timeData.setYear(iVp_b);
        timeData.setMonth(iVp_b2);
        timeData.setDay(iVp_b3);
        return timeData;
    }

    public static int vp_b(String str) {
        int iFloatValue = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            iFloatValue = (int) Float.valueOf(str).floatValue();
        } catch (Exception unused) {
        }
        return iFloatValue;
    }

    public static String vp_b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static boolean vp_c(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
            return true;
        } catch (ParseException unused) {
            return false;
        }
    }

    public static int vp_a(String str, String str2) {
        return (int) (((((vp_e(str2).getTime() - vp_e(str).getTime()) / 1000) / 60) / 60) / 24);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.text.DateFormat, java.text.SimpleDateFormat] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    public static String vp_a(String str, int i) {
        ?? simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ?? r9 = 0;
        try {
            simpleDateFormat = simpleDateFormat.parse(str);
            r9 = simpleDateFormat;
        } catch (ParseException unused) {
            simpleDateFormat.printStackTrace();
        }
        ?? calendar = Calendar.getInstance();
        calendar.setTime(r9);
        calendar.add(5, i);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static boolean vp_b(TimeData timeData) {
        return vp_c(timeData.getDateAndClockForDb());
    }

    public static String vp_a(int i) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static boolean vp_a(TimeData timeData) {
        return vp_c(timeData.getDateAndClockAndSecondForDb(), "yyyy-MM-dd-HH:mm:ss");
    }
}
