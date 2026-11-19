package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGpsStatus.class */
public enum EGpsStatus {
    SIGNAL_NORML((byte) 1),
    SIGNAL_WEAK((byte) 2),
    GPS_PERMISSION_CLOSE((byte) 3);

    byte value;

    EGpsStatus(byte b) {
        this.value = b;
    }

    public byte getValue() {
        return this.value;
    }
}
