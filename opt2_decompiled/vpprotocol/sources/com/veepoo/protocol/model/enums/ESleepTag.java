package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESleepTag.class */
public enum ESleepTag {
    OLD(0),
    PRECISE(1),
    NONE(2),
    KH_TYPE(3);

    private int value;

    ESleepTag(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
