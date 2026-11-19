package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EBloodFatUnit.class */
public enum EBloodFatUnit {
    NONE(0),
    mmol_L(1),
    mg_dl(2);

    private int value;

    EBloodFatUnit(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
