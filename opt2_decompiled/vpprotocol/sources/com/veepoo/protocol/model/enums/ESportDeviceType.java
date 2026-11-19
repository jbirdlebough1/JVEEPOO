package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESportDeviceType.class */
public enum ESportDeviceType {
    APP(0),
    DEVICE(1);

    private int value;

    ESportDeviceType(int i) {
        this.value = i;
    }

    public static ESportDeviceType getSportType(int i) {
        for (ESportDeviceType eSportDeviceType : values()) {
            if (eSportDeviceType.value == i) {
                return eSportDeviceType;
            }
        }
        return APP;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
