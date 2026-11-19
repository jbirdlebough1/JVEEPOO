package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ECPUPlatform.class */
public enum ECPUPlatform {
    UNKNOWN("Unknown"),
    NORDIC("Nordic"),
    GOODIX("汇顶");

    private String name;

    ECPUPlatform(String str) {
        this.name = str;
    }

    public static ECPUPlatform getEPlatform(String str) {
        str.hashCode();
        if (str.equals("Nordic")) {
            return NORDIC;
        }
        return !str.equals("汇顶") ? UNKNOWN : GOODIX;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
