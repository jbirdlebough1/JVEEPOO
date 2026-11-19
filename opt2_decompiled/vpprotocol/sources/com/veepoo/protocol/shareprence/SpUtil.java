package com.veepoo.protocol.shareprence;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/shareprence/SpUtil.class */
public class SpUtil {
    public static String SP_NAME = "vpsdk";
    private static SharedPreferences vp_a;

    public static void clear(Context context) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().clear().commit();
    }

    public static void saveString(Context context, String str, String str2) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putString(str, str2).commit();
    }

    public static void saveBoolean(Context context, String str, boolean z) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putBoolean(str, z).commit();
    }

    public static String getString(Context context, String str, String str2) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        return vp_a.getString(str, str2);
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        return vp_a.getBoolean(str, z);
    }

    public static int getInt(Context context, String str, int i) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        return vp_a.getInt(str, i);
    }

    public static long getLong(Context context, String str, long j) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        return vp_a.getLong(str, j);
    }

    public static float getFloat(Context context, String str, float f) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        return vp_a.getFloat(str, f);
    }

    public static void cleanData(Context context, String str) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putString(str, "").commit();
    }

    public static void saveInt(Context context, String str, int i) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putInt(str, i).commit();
    }

    public static void saveLong(Context context, String str, long j) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putLong(str, j).commit();
    }

    public static void saveFloat(Context context, String str, float f) {
        if (vp_a == null) {
            vp_a = context.getSharedPreferences(SP_NAME, 0);
        }
        vp_a.edit().putFloat(str, f).commit();
    }
}
