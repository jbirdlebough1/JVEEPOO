package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EBloodGlucoseStatus.class */
public enum EBloodGlucoseStatus {
    NONSUPPORT(-1),
    ENABLE(0),
    DETECTING(1),
    LOW_POWER(2),
    BUSY(3),
    WEARING_ERROR(4);

    public int status;

    EBloodGlucoseStatus(int i) {
        this.status = i;
    }
}
