package com.inuker.bluetooth.library.utils.hook;

import android.os.IBinder;
import com.inuker.bluetooth.library.utils.hook.compat.ServiceManagerCompat;
import com.inuker.bluetooth.library.utils.hook.utils.HookUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/BluetoothHooker.class */
public class BluetoothHooker {
    private static final String BLUETOOTH_MANAGER = "bluetooth_manager";

    public static void hook() {
        Method getService = ServiceManagerCompat.getService();
        IBinder iBinder = (IBinder) HookUtils.invoke(getService, null, BLUETOOTH_MANAGER);
        IBinder proxy = (IBinder) Proxy.newProxyInstance(iBinder.getClass().getClassLoader(), new Class[]{IBinder.class}, new BluetoothManagerBinderProxyHandler(iBinder));
        HashMap<String, IBinder> cache = ServiceManagerCompat.getCacheValue();
        cache.put(BLUETOOTH_MANAGER, proxy);
    }
}
