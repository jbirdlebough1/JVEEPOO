package com.inuker.bluetooth.library.receiver.listener;

import com.inuker.bluetooth.library.BluetoothClientImpl;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BluetoothStateChangeListener.class */
public abstract class BluetoothStateChangeListener extends BluetoothReceiverListener {
    protected abstract void onBluetoothStateChanged(int i, int i2);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onInvoke(Object... args) {
        int prevState = ((Integer) args[0]).intValue();
        int curState = ((Integer) args[1]).intValue();
        if (curState == 10 || curState == 13) {
            BluetoothClientImpl.getInstance(null).stopSearch();
        }
        onBluetoothStateChanged(prevState, curState);
    }

    @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener
    public String getName() {
        return BluetoothStateChangeListener.class.getSimpleName();
    }
}
