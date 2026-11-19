package com.inuker.bluetooth.library.connect.response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.inuker.bluetooth.library.IResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/response/BluetoothResponse.class */
public abstract class BluetoothResponse extends IResponse.Stub implements Handler.Callback {
    private static final int MSG_RESPONSE = 1;
    private final Handler mHandler;

    protected abstract void onAsyncResponse(int i, Bundle bundle);

    protected BluetoothResponse() {
        if (Looper.myLooper() == null) {
            throw new RuntimeException();
        }
        this.mHandler = new Handler(Looper.myLooper(), this);
    }

    @Override // com.inuker.bluetooth.library.IResponse
    public void onResponse(int code, Bundle data) throws RemoteException {
        this.mHandler.obtainMessage(1, code, 0, data).sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == 1) {
            onAsyncResponse(msg.arg1, (Bundle) msg.obj);
            return true;
        }
        return true;
    }
}
