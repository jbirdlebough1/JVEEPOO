package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EBloodGlucoseRiskLevel.class */
public enum EBloodGlucoseRiskLevel {
    NONE(0),
    LOW(1),
    MIDDLE(2),
    HIGH(3);

    public int value;

    EBloodGlucoseRiskLevel(int i) {
        this.value = i;
    }

    public static EBloodGlucoseRiskLevel fromValue(int i) {
        if (i == 1) {
            return LOW;
        }
        if (i == 2) {
            return MIDDLE;
        }
        return i == 3 ? HIGH : NONE;
    }
}
