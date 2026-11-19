package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ECpuType.class */
public enum ECpuType {
    UNKONW("unkonw"),
    NORIC("noric"),
    HUITOP("huitop");

    String value;

    ECpuType(String str) {
        this.value = str;
    }

    public static ECpuType getECpuType(String str) {
        str.hashCode();
        if (str.equals("huitop")) {
            return HUITOP;
        }
        return !str.equals("noric") ? UNKONW : NORIC;
    }

    public String getValue() {
        return this.value;
    }
}
