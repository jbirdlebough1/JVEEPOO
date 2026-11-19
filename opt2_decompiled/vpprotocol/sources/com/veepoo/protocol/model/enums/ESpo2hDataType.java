package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESpo2hDataType.class */
public enum ESpo2hDataType {
    TYPE_SPO2H(1),
    TYPE_HEART(2),
    TYPE_SLEEP(3),
    TYPE_BREATH(4),
    TYPE_LOWSPO2H(5),
    TYPE_BEATH_BREAK(6),
    TYPE_HRV(7),
    TYPE_SPO2H_MIN(8);

    private int index;

    ESpo2hDataType(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }
}
