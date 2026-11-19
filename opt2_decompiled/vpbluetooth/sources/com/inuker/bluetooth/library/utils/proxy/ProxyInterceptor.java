package com.inuker.bluetooth.library.utils.proxy;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/proxy/ProxyInterceptor.class */
public interface ProxyInterceptor {
    boolean onIntercept(Object obj, Method method, Object[] objArr);
}
