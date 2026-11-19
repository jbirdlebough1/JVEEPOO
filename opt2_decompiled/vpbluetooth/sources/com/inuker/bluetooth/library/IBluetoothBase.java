package com.inuker.bluetooth.library;

import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothBase.class */
public interface IBluetoothBase {
    public static final String EXTRA_MAC = "extra.mac";
    public static final String EXTRA_SERVICE_UUID = "extra.service.uuid";
    public static final String EXTRA_CHARACTER_UUID = "extra.character.uuid";
    public static final String EXTRA_BYTE_VALUE = "extra.byte.value";
    public static final String EXTRA_CODE = "extra.code";
    public static final String EXTRA_STATUS = "extra.status";
    public static final String EXTRA_STATE = "extra.state";
    public static final String EXTRA_RSSI = "extra.rssi";
    public static final String EXTRA_VERSION = "extra.version";
    public static final String EXTRA_REQUEST = "extra.request";
    public static final String EXTRA_SEARCH_RESULT = "extra.search.result";
    public static final String EXTRA_GATT_PROFILE = "extra.gatt.profile";
    public static final String EXTRA_OPTIONS = "extra.options";
    public static final int REQUEST_SUCCESS = 0;
    public static final int REQUEST_FAILED = -1;
    public static final int REQUEST_CANCELED = -2;
    public static final int ILLEGAL_ARGUMENT = -3;
    public static final int BLE_NOT_SUPPORTED = -4;
    public static final int BLUETOOTH_DISABLED = -5;
    public static final int CONNECTION_NOT_READY = -6;
    public static final int REQUEST_TIMEDOUT = -7;
    public static final int REQUEST_OVERFLOW = -8;
    public static final int REQUEST_EXCEPTION = -9;
    public static final int SERVICE_UNREADY = -10;
    public static final int SEARCH_START = 1;
    public static final int SEARCH_STOP = 2;
    public static final int SEARCH_CANCEL = 3;
    public static final int DEVICE_FOUND = 4;
    public static final int STATUS_CONNECTED = 16;
    public static final int STATUS_DISCONNECTED = 32;
    public static final String ACTION_CONNECT_STATUS_CHANGED = "action.connect_status_changed";
    public static final String ACTION_CHARACTER_CHANGED = "action.character_changed";
    public static final int SEARCH_TYPE_CLASSIC = 1;
    public static final int SEARCH_TYPE_BLE = 2;
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
}
