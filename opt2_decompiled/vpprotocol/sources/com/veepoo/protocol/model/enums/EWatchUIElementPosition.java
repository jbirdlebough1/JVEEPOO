package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EWatchUIElementPosition.class */
public enum EWatchUIElementPosition {
    CENTER_TOP(1),
    CENTER_CENTER(2),
    CENTER_BOTTOM(3),
    LEFT_TOP(4),
    RIGHT_TOP(5),
    LEFT_BOTTOM(6),
    RIGHT_BOTTOM(7);

    int value;

    EWatchUIElementPosition(int i) {
        this.value = i;
    }

    public static EWatchUIElementPosition getWatchUIElementPosition(int i) {
        switch (i) {
            case 1:
                return CENTER_TOP;
            case 2:
                return CENTER_CENTER;
            case 3:
                return CENTER_BOTTOM;
            case 4:
                return LEFT_TOP;
            case 5:
                return RIGHT_TOP;
            case 6:
                return LEFT_BOTTOM;
            case 7:
                return RIGHT_BOTTOM;
            default:
                return CENTER_CENTER;
        }
    }

    public int getValue() {
        return this.value;
    }
}
