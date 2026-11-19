package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQX17VibrationMode.class */
public enum EQX17VibrationMode {
    START(0),
    END(1),
    NOTIFY(2),
    ALERT(3),
    CONFIRM(4),
    BEAT(5),
    CONNECTED(6),
    ERROR(7);

    private int value;

    EQX17VibrationMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
