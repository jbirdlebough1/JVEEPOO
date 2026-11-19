package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EYM23ProOptStatus.class */
public enum EYM23ProOptStatus {
    UNSUPPORT(0, "设备不支持该协议"),
    SUCCESS(1, "成功"),
    FAILED(2, "失败"),
    UNKONW(-1, "未知");

    private final int tag;
    private final String des;

    EYM23ProOptStatus(int i, String str) {
        this.tag = i;
        this.des = str;
    }

    public static EYM23ProOptStatus fromTag(byte b) {
        for (EYM23ProOptStatus eYM23ProOptStatus : values()) {
            if (eYM23ProOptStatus.tag == (b & 255)) {
                return eYM23ProOptStatus;
            }
        }
        return UNKONW;
    }

    public int getTag() {
        return this.tag;
    }

    public String getDes() {
        return this.des;
    }
}
