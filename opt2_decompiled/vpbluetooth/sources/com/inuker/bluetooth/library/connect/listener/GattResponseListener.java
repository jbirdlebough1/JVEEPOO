package com.inuker.bluetooth.library.connect.listener;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/GattResponseListener.class */
public interface GattResponseListener {
    public static final int GATT_RESP_SERVICE_DISCOVER = 1;
    public static final int GATT_RESP_CHARACTER_READ = 2;
    public static final int GATT_RESP_CHARACTER_WRITE = 3;
    public static final int GATT_RESP_DESCRIPTOR_WRITE = 4;
    public static final int GATT_RESP_READ_RSSI = 5;
    public static final int GATT_RESP_DISCONNECT = 6;

    void onConnectStatusChanged(boolean z);
}
