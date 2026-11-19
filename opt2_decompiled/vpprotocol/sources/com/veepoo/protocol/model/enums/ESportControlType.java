package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESportControlType.class */
public enum ESportControlType {
    START(1),
    PAUSE(2),
    RESUME(3),
    STOP(4);

    private int value;

    ESportControlType(int i) {
        this.value = i;
    }

    public static ESportControlType getSportType(int i) {
        for (ESportControlType eSportControlType : values()) {
            if (eSportControlType.value == i) {
                return eSportControlType;
            }
        }
        return START;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
