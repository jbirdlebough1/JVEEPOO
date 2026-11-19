package com.timaimee.vpdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    // Use ThreadLocal to ensure SimpleDateFormat In multi-threaded concurrency (such as Bluetooth data parsing）时绝对安全
    private static final ThreadLocal<SimpleDateFormat> FMT_YMD_HMS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };

    private static final ThreadLocal<SimpleDateFormat> FMT_YMD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };

    private static final ThreadLocal<SimpleDateFormat> FMT_HMS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        }
    };

    private static final ThreadLocal<SimpleDateFormat> FMT_HM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    };

    /**
     * Get the current full date and time string
     * @return 例如: 2026-05-27 15:30:45
     */
    public static String getCurrentDateTimeStr() {
        return FMT_YMD_HMS.get().format(new Date());
    }

    /**
     * 获取当前日期字符串
     * @return 例如: 2026-05-27
     */
    public static String getCurrentDateStr() {
        return FMT_YMD.get().format(new Date());
    }

    /**
     * 获取当前时分秒字符串
     * @return 例如: 15:30:45
     */
    public static String getCurrentTimeStr() {
        return FMT_HMS.get().format(new Date());
    }

    /**
     * 获取当前时分字符串
     * @return 例如: 15:30
     */
    public static String getCurrentHourMinuteStr() {
        return FMT_HM.get().format(new Date());
    }

    /**
     * Get the current time string in a custom format
     * @param pattern 格式模板，如 "yyyyMMddHHmmss"
     */
    public static String getCurrentTimeCustom(String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date());
    }

    /**
     * 获取当前是当天的第几分钟 (0 - 1439)
     * Commonly used for calculations such as: Sleep interval、设备特定打点时间轴等
     */
    public static int getCurrentMinuteOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时制
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }

    /**
     * Get the individual numerical components of the current time
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        // Calendar.MONTH 是从 0 开始的（0代表1月），所以需要 +1
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // 24小时制
    }

    public static int getCurrentMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getCurrentSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * Get the current Unix timestamp（秒级）
     */
    public static long getCurrentEpochSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static String secondsTimestampFormat(long secondsTimestamp) {
        long timestamp = secondsTimestamp * 1000;
        Date date = new Date(timestamp);
        return FMT_YMD_HMS.get().format(date);
    }
}