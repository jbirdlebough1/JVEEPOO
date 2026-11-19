package com.veepoo.protocol.model;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/DayState.class */
public enum DayState {
    TODAY(0),
    YESTERDAY(1),
    BEFORE_YESTERDAY(2);

    int state;

    DayState(int i) {
        this.state = i;
    }

    public static DayState getDayState(int i) {
        if (i == 0) {
            return TODAY;
        }
        if (i != 1) {
            return i != 2 ? TODAY : BEFORE_YESTERDAY;
        }
        return YESTERDAY;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }
}
