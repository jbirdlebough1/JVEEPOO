package com.inuker.bluetooth.library.connect;

import com.inuker.bluetooth.library.IBluetoothBase;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/IBluetoothApi.class */
public interface IBluetoothApi extends IBluetoothBase {
    public static final int CODE_CONNECT = 1;
    public static final int CODE_DISCONNECT = 2;
    public static final int CODE_READ = 3;
    public static final int CODE_WRITE = 4;
    public static final int CODE_WRITE_NORSP = 5;
    public static final int CODE_NOTIFY = 6;
    public static final int CODE_UNNOTIFY = 7;
    public static final int CODE_READ_RSSI = 8;
    public static final int CODE_REFRESH = 9;
    public static final int CODE_SEARCH = 10;
    public static final int CODE_STOP_SESARCH = 11;
}
