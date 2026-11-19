package com.veepoo.protocol.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.veepoo.protocol.VPOperateManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_ae.class */
public final class vp_ae {
    public static final vp_ae vp_a = new vp_ae();
    private static final SimpleDateFormat vp_b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss||SSS", Locale.CHINA);

    private vp_ae() {
    }

    private final boolean vp_j(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        Intrinsics.checkNotNull(string);
        for (String str : (String[]) StringsKt.split$default(string, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0])) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
            if (componentNameUnflattenFromString != null && TextUtils.equals(context.getPackageName(), componentNameUnflattenFromString.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private final String vp_i(Context context) {
        String packageName = context.getPackageName();
        Object systemService = context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(packageName) ? "【电池优化】HBand已被用户加入电池优化白名单" : "【电池优化】HBand受电池优化限制";
    }

    private final String vp_a(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            Object systemService = context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            if (((ActivityManager) systemService).isBackgroundRestricted()) {
                return "【后台服务运行限制】不受限";
            }
        }
        return "【后台服务运行限制】受限制";
    }

    public final String vp_f(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_a(context, "android.permission.ACCESS_FINE_LOCATION") ? "【定位】权限已开启" : "【定位】权限未获取";
    }

    public final String vp_b(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_a(context, "android.permission.ACCESS_BACKGROUND_LOCATION") ? "【后台定位】权限已开启" : "【后台定位】权限未获取";
    }

    public final String vp_c(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_a(context, "android.permission.BLUETOOTH_SCAN") ? "【蓝牙扫描】权限已开启" : "【蓝牙扫描】权限未获取";
    }

    public final String vp_h(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_a(context, "android.permission.WRITE_EXTERNAL_STORAGE") ? "【读写】权限已开启" : "【读写】权限未获取";
    }

    public final String vp_d(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_a(context, "android.permission.CAMERA") ? "【相机】权限已开启" : "【相机】权限未获取";
    }

    public final String vp_g(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return vp_j(context) ? "【通知栏读取】权限已获取" : "【通知栏读取】权限未获取";
    }

    public final String vp_e(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Build.VERSION.SDK_INT >= 30 ? context.getPackageManager().getInstallSourceInfo(context.getPackageName()).getInstallingPackageName() : context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    public final String vp_b() {
        return Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
    }

    public final String vp_d() {
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str, "RELEASE");
        return str;
    }

    public final String vp_c() {
        String str = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(str, "MODEL");
        return str;
    }

    public final String vp_a() {
        String str = Build.BRAND;
        Intrinsics.checkNotNullExpressionValue(str, "BRAND");
        return str;
    }

    public final String vp_a(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, "deviceNumber");
        Intrinsics.checkNotNullParameter(str2, "oadVersion");
        StringBuilder sb = new StringBuilder("====================手机和App信息START====================");
        vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(vp_af.vp_a(sb, "当前时间：" + vp_b.format(new Date())), "手机厂商：" + vp_a()), "手机型号：" + vp_c()), "Android系统版本号：" + vp_d()), "当前语言：" + vp_b()), "设备号：" + str), "MacAddress：" + VPOperateManager.getInstance().getMacAddress()), "固件版本：" + str2), "安装器包名：" + vp_e(context)), vp_i(context)), vp_a(context)), vp_f(context)), vp_b(context)), vp_c(context)), vp_h(context)), vp_d(context)), vp_g(context)), "====================手机和App信息E N D====================");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final boolean vp_a(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(str, "permission");
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }
}
