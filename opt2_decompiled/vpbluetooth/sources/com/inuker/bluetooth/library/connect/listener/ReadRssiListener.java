package com.inuker.bluetooth.library.connect.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/ReadRssiListener.class */
public interface ReadRssiListener extends GattResponseListener {
    void onReadRemoteRssi(int i, int i2);
}
