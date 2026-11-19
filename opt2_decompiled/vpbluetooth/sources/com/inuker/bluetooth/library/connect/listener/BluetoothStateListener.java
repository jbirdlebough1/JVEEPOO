package com.inuker.bluetooth.library.connect.listener;

import com.inuker.bluetooth.library.receiver.listener.BluetoothClientListener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/BluetoothStateListener.class */
public abstract class BluetoothStateListener extends BluetoothClientListener {
    public abstract void onBluetoothStateChanged(boolean z);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onSyncInvoke(Object... args) {
        boolean openOrClosed = ((Boolean) args[0]).booleanValue();
        onBluetoothStateChanged(openOrClosed);
    }
}
