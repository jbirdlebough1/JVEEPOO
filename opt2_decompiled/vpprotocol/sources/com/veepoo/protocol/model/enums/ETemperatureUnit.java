package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ETemperatureUnit.class */
public enum ETemperatureUnit {
    NONE(0),
    CELSIUS(1),
    FAHRENHEIT(2);

    private int value;

    ETemperatureUnit(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
