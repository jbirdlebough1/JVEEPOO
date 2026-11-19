package com.inuker.bluetooth.library.receiver.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BluetoothBondStateChangeListener.class */
public abstract class BluetoothBondStateChangeListener extends BluetoothReceiverListener {
    protected abstract void onBondStateChanged(String str, int i);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onInvoke(Object... args) {
        String mac = (String) args[0];
        int bondState = ((Integer) args[1]).intValue();
        onBondStateChanged(mac, bondState);
    }

    @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener
    public String getName() {
        return BluetoothBondStateChangeListener.class.getSimpleName();
    }
}
