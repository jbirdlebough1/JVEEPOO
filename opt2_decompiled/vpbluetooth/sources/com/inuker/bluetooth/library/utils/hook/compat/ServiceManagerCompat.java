package com.inuker.bluetooth.library.utils.hook.compat;

import android.os.IBinder;
import com.inuker.bluetooth.library.utils.hook.utils.HookUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/compat/ServiceManagerCompat.class */
public class ServiceManagerCompat {
    private static Class<?> serviceManager = HookUtils.getClass("android.os.ServiceManager");
    private static Field sCache = HookUtils.getField(serviceManager, "sCache");
    private static Method getService;

    static {
        sCache.setAccessible(true);
        getService = HookUtils.getMethod(serviceManager, "getService", String.class);
    }

    public static Class<?> getServiceManager() {
        return serviceManager;
    }

    public static Field getCacheField() {
        return sCache;
    }

    public static HashMap<String, IBinder> getCacheValue() {
        return (HashMap) HookUtils.getValue(sCache);
    }

    public static Method getService() {
        return getService;
    }
}
