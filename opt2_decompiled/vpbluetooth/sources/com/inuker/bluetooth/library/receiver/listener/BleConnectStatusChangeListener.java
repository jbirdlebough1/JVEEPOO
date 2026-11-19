package com.inuker.bluetooth.library.receiver.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BleConnectStatusChangeListener.class */
public abstract class BleConnectStatusChangeListener extends BluetoothReceiverListener {
    protected abstract void onConnectStatusChanged(String str, int i);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onInvoke(Object... args) {
        String mac = (String) args[0];
        int status = ((Integer) args[1]).intValue();
        onConnectStatusChanged(mac, status);
    }

    @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener
    public String getName() {
        return BleConnectStatusChangeListener.class.getSimpleName();
    }
}
