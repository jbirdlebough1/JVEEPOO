package com.veepoo.protocol.model.datas;

import android.text.format.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TimeData.class */
public class TimeData {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;
    public int weekDay;

    public TimeData() {
    }

    public TimeData(int i, int i2) {
        this.hour = i;
        this.minute = i2;
    }

    public static String getTwoStr(int i) {
        String str = i + "";
        String str2 = str;
        if (str.length() < 2) {
            str2 = "0" + str2;
        }
        return str2;
    }

    public static int getSysMonth() {
        Time time = new Time();
        time.setToNow();
        return time.month + 1;
    }

    public static int getSystemMonth() {
        return Calendar.getInstance().get(2);
    }

    @Deprecated
    public static int getSysYear() {
        Time time = new Time();
        time.setToNow();
        return time.year;
    }

    public static int getSystemYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getSysDay() {
        Time time = new Time();
        time.setToNow();
        return time.monthDay;
    }

    public static int getSystemDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getSysHour() {
        Time time = new Time();
        time.setToNow();
        return time.hour;
    }

    public static int getSystemHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getSysMiute() {
        Time time = new Time();
        time.setToNow();
        return time.minute;
    }

    public static int getSystemMinute() {
        return Calendar.getInstance().get(12);
    }

    public static int getSysSecond() {
        Time time = new Time();
        time.setToNow();
        return time.second;
    }

    public static int getSystemSecond() {
        return Calendar.getInstance().get(13);
    }

    public static TimeData getTimeBeanByTimestampSecond(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(((long) i) * 1000);
        return new TimeData(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public int getWeekDay() {
        return this.weekDay;
    }

    public void setWeekDay(int i) {
        this.weekDay = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public String getClock() {
        return String.format(Locale.US, "%02d:%02d", Integer.valueOf(this.hour), Integer.valueOf(this.minute));
    }

    public String getClockAndSecond() {
        return String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf(this.hour), Integer.valueOf(this.minute), Integer.valueOf(this.second));
    }

    public String getClock12() {
        int i = this.hour;
        int i2 = i;
        Object obj = i >= 12 ? "PM" : "AM";
        if (i2 == 0) {
            i2 = 12;
        } else if (i2 > 12) {
            i2 %= 12;
        }
        return String.format(Locale.US, "%02d:%02d%s", Integer.valueOf(i2), Integer.valueOf(this.minute), obj);
    }

    public String getHourMinute() {
        return String.format(Locale.US, "%02d-%02d", Integer.valueOf(this.hour), Integer.valueOf(this.minute));
    }

    public String getMonthDay() {
        return String.format(Locale.US, "%02d-%02d", Integer.valueOf(this.month), Integer.valueOf(this.day));
    }

    public String getDateADC() {
        return String.format(Locale.US, "%02d_%02d", Integer.valueOf(this.month), Integer.valueOf(this.day));
    }

    public int getHMValue() {
        return (this.hour * 60) + this.minute;
    }

    public String toDatabaseDateString() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(toCalendar().getTime());
    }

    public String getDateForDb() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(toCalendar().getTime());
    }

    public String getDateForADC() {
        if (getYear() == 0) {
            this.year = Calendar.getInstance().get(1);
        }
        return new SimpleDateFormat("yyyy_MM_dd", Locale.US).format(toCalendar().getTime());
    }

    public String getNullDateForDb() {
        return Calendar.getInstance().get(1) + "-255-255";
    }

    public String getDateAndClockForDb() {
        return toDatabaseDateString() + "-" + getClock();
    }

    public String getDateAndClockAndSecondForDb() {
        return toDatabaseDateString() + "-" + getClockAndSecond();
    }

    public String toFullDateTimeNoSeconds() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(toCalendar().getTime());
    }

    public String toFullDateTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(toCalendar().getTime());
    }

    public String getDateAndClockSeconds4GBandDb() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(toCalendar().getTime());
    }

    public String getDateAndClock4GBandDb() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(toCalendar().getTime());
    }

    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(this.year, this.month - 1, this.day, this.hour, this.minute, this.second);
        return calendar;
    }

    public void setCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
    }

    public int getTimestampSeconds() {
        return (int) (toCalendar().getTimeInMillis() / 1000);
    }

    public boolean isLeadTime() {
        return toCalendar().getTimeInMillis() > Calendar.getInstance().getTimeInMillis();
    }

    public String toString() {
        return "TimeData [" + toFullDateTimeString() + "]";
    }

    public TimeData(int i, int i2, int i3) {
        this.year = i;
        this.month = i2;
        this.day = i3;
    }

    public TimeData(int i, int i2, int i3, int i4) {
        this(Calendar.getInstance().get(1), i, i2, i3, i4);
    }

    public TimeData(int i, int i2, int i3, int i4, int i5) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
    }

    public TimeData(int i, int i2, int i3, int i4, int i5, int i6) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
    }

    public TimeData(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(i, i2, i3, i4, i5);
        this.second = i6;
        this.weekDay = i7;
    }

    public TimeData(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j * 1000));
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
    }
}
