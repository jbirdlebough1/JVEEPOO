package com.inuker.bluetooth.library.bluetrum.manager;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/manager/OtaError.class */
public enum OtaError {
    NOT_CONNECTED(1000, "设备未连接"),
    NOT_INIT(1001, "未初始化"),
    NOT_FOUND_OTA_SERVICE(1003, "找不到OTA服务（FF12）"),
    NOT_FOUND_OTA_DATA_IN(1004, "设备没有找到dataInCharacteristic（FF14）"),
    NOT_FOUND_OTA_DATA_OUT(1005, "设备没有找到dataOutCharacteristic（FF15）"),
    NOT_FOUND_OTA_CHARACTERISTIC(1006, "找不到dataInCharacteristic或者dataOutCharacteristic"),
    NOT_FOUND_CLIENT_CHARACTERISTIC_CONFIG(1007, "获取不到Client Characteristic config"),
    CAN_NOT_SUBSCRIBE_DATA_IN(1008, "订阅Data In不成功"),
    REPORT_FROM_DEVICE(2000, "有点问题，错误代码"),
    REFUSED_BY_DEVICE(2001, "设备拒绝了升级"),
    TIMEOUT_RECEIVE_RESPONSE(2002, "等待设备回复超时");

    private final int errorCode;
    private final String description;
    private byte deviceErrorCode;

    OtaError(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return this.description;
    }

    public byte getDeviceErrorCode() {
        return this.deviceErrorCode;
    }

    public void setDeviceErrorCode(byte deviceErrorCode) {
        this.deviceErrorCode = deviceErrorCode;
    }
}
