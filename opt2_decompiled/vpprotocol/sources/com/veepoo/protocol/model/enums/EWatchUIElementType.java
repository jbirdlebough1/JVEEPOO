package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EWatchUIElementType.class */
public enum EWatchUIElementType {
    NONE(0),
    DATE_STYLE_1(1),
    DATE_STYLE_2(2),
    STEP(3),
    SLEEP(4),
    HEART(5),
    KCAL(6),
    DISTANCE(7),
    BLUETOOTH_BATTERY(8),
    TIME(99);

    int value;

    EWatchUIElementType(int i) {
        this.value = i;
    }

    public static EWatchUIElementType getEWatchUIElementType(int i) {
        switch (i) {
            case 0:
                return NONE;
            case 1:
                return DATE_STYLE_1;
            case 2:
                return DATE_STYLE_2;
            case 3:
                return STEP;
            case 4:
                return SLEEP;
            case 5:
                return HEART;
            case 6:
                return KCAL;
            case 7:
                return DISTANCE;
            case 8:
                return BLUETOOTH_BATTERY;
            default:
                return NONE;
        }
    }

    public int getValue() {
        return this.value;
    }
}
