package com.inuker.bluetooth.library.connect.listener;

import com.inuker.bluetooth.library.receiver.listener.BluetoothClientListener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/BleConnectStatusListener.class */
public abstract class BleConnectStatusListener extends BluetoothClientListener {
    public abstract void onConnectStatusChanged(String str, int i);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onSyncInvoke(Object... args) {
        String mac = (String) args[0];
        int status = ((Integer) args[1]).intValue();
        onConnectStatusChanged(mac, status);
    }
}
