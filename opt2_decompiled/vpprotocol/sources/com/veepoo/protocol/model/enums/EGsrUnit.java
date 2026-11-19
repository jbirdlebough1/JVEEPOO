package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGsrUnit.class */
public enum EGsrUnit {
    NONE(0),
    ug_dL(1),
    nmol_L(2);

    private int value;

    EGsrUnit(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
