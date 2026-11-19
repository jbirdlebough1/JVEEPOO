package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EWomenStatus.class */
public enum EWomenStatus {
    NONE(0),
    MENES(1),
    PREREADY(2),
    PREING(3),
    MAMAMI(4);

    private int value;

    EWomenStatus(int i) {
        this.value = i;
    }

    public static EWomenStatus getStatusByValue(int i) {
        switch (i) {
            case 0:
                return NONE;
            case 1:
                return MENES;
            case 2:
                return PREREADY;
            case 3:
                return PREING;
            case 4:
                return MAMAMI;
            default:
                return NONE;
        }
    }

    public int getValue() {
        return this.value;
    }
}
