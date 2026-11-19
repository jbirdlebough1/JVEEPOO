package com.inuker.bluetooth.library.receiver.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BluetoothReceiverListener.class */
public abstract class BluetoothReceiverListener extends AbsBluetoothListener {
    public abstract String getName();

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public final void onSyncInvoke(Object... args) {
        throw new UnsupportedOperationException();
    }
}
