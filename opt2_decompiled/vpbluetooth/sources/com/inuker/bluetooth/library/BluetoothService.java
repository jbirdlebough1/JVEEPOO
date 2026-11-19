package com.inuker.bluetooth.library;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.inuker.bluetooth.library.utils.BluetoothLog;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/BluetoothService.class */
public class BluetoothService extends Service {

    @SuppressLint({"StaticFieldLeak"})
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BluetoothLog.v("BluetoothService onCreate");
        mContext = getApplicationContext();
        BluetoothContext.set(mContext);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        BluetoothLog.v("BluetoothService onBind");
        return BluetoothServiceImpl.getInstance();
    }
}
