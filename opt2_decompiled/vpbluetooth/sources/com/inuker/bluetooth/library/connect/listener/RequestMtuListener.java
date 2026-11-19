package com.inuker.bluetooth.library.connect.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/RequestMtuListener.class */
public interface RequestMtuListener extends GattResponseListener {
    void onMtuChanged(int i, int i2);
}
