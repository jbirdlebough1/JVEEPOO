package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EJH76SNCodeError.class */
public enum EJH76SNCodeError {
    SUCCESS(0, "成功"),
    DEVICE_FAILED(1000, "设备返回失败"),
    INVALID_FORMAT(1001, "格式错误：SN码只能为数字"),
    INVALID_LENGTH(1002, "长度错误：SN码固定10位"),
    ALL_ZERO(1003, "SN码不能全为0");

    private final int code;
    private final String des;

    EJH76SNCodeError(int i, String str) {
        this.code = i;
        this.des = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getDes() {
        return this.des;
    }
}
