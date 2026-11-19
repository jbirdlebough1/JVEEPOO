package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EWeatherType.class */
public enum EWeatherType {
    C(0),
    F(1);

    private int value;

    EWeatherType(int i) {
        this.value = i;
    }

    public static EWeatherType getWeatherTypeByValue(int i) {
        if (i != 0 && i == 1) {
            return F;
        }
        return C;
    }

    public int getValue() {
        return this.value;
    }
}
