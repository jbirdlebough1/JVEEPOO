package com.inuker.bluetooth.library.utils.proxy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/proxy/ProxyInvocationHandler.class */
public class ProxyInvocationHandler implements InvocationHandler, ProxyInterceptor, Handler.Callback {
    private Object subject;
    private ProxyInterceptor interceptor;
    private boolean weakRef;
    private boolean postUI;
    private Handler uiHandler;

    public ProxyInvocationHandler(Object subject) {
        this(subject, null);
    }

    public ProxyInvocationHandler(Object subject, ProxyInterceptor interceptor) {
        this(subject, interceptor, false);
    }

    public ProxyInvocationHandler(Object subject, ProxyInterceptor interceptor, boolean weakRef) {
        this(subject, interceptor, weakRef, false);
    }

    public ProxyInvocationHandler(Object subject, ProxyInterceptor interceptor, boolean weakRef, boolean postUI) {
        this.weakRef = weakRef;
        this.interceptor = interceptor;
        this.postUI = postUI;
        this.subject = getObject(subject);
        this.uiHandler = new Handler(Looper.getMainLooper(), this);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object subject = getObject();
        if (!onIntercept(subject, method, args)) {
            ProxyBulk bulk = new ProxyBulk(subject, method, args);
            return this.postUI ? postSafeInvoke(bulk) : safeInvoke(bulk);
        }
        return null;
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        if (this.interceptor != null) {
            try {
                return this.interceptor.onIntercept(object, method, args);
            } catch (Exception e) {
                BluetoothLog.e(e);
                return false;
            }
        }
        return false;
    }

    private Object getObject(Object object) {
        return this.weakRef ? new WeakReference(object) : object;
    }

    private Object getObject() {
        if (this.weakRef) {
            return ((WeakReference) this.subject).get();
        }
        return this.subject;
    }

    private Object postSafeInvoke(ProxyBulk bulk) {
        this.uiHandler.obtainMessage(0, bulk).sendToTarget();
        return null;
    }

    private Object safeInvoke(ProxyBulk bulk) {
        try {
            return bulk.safeInvoke();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        ProxyBulk.safeInvoke(msg.obj);
        return true;
    }
}
