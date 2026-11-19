package com.inuker.bluetooth.library.receiver.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BluetoothBondListener.class */
public abstract class BluetoothBondListener extends BluetoothClientListener {
    public abstract void onBondStateChanged(String str, int i);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onSyncInvoke(Object... args) {
        String mac = (String) args[0];
        int bondState = ((Integer) args[1]).intValue();
        onBondStateChanged(mac, bondState);
    }
}
