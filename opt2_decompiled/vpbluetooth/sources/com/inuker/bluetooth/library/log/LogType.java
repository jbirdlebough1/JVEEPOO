package com.inuker.bluetooth.library.log;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/LogType.class */
public enum LogType {
    BLE_WRITE("ble_write.txt"),
    BLE_CHANGE("ble_change.txt"),
    BLE_CONNECT("ble_connect.txt"),
    OTHER("hb_other.txt");

    private String fileName;

    LogType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
