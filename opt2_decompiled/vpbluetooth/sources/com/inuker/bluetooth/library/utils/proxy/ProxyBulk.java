package com.inuker.bluetooth.library.utils.proxy;

import android.util.Log;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/proxy/ProxyBulk.class */
public class ProxyBulk {
    public Object object;
    public Method method;
    public Object[] args;

    public ProxyBulk(Object object, Method method, Object[] args) {
        this.object = object;
        this.method = method;
        this.args = args;
    }

    public Object safeInvoke() {
        Object result = null;
        try {
            BluetoothLog.v(String.format("safeInvoke method = %s, object = %s", this.method, this.object));
            result = this.method.invoke(this.object, this.args);
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e("ProxyBulk", "发送错误-------------> e = " + e.getMessage());
            BluetoothLog.e(e);
        }
        return result;
    }

    public static Object safeInvoke(Object obj) {
        return ((ProxyBulk) obj).safeInvoke();
    }
}
