package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EYM23ProTestStatus.class */
public enum EYM23ProTestStatus {
    IDLE(0, "空闲/退出"),
    TESTING(1, "检测中"),
    REPAIRING(2, "修复中"),
    TEST_DONE(3, "检测完成"),
    REPAIR_DONE(4, "修复完成"),
    TEST_PAUSE(5, "检测暂停"),
    REPAIR_PAUSE(6, "修复暂停"),
    UNKONW(-1, "未知");

    private final int tag;
    private final String des;

    EYM23ProTestStatus(int i, String str) {
        this.tag = i;
        this.des = str;
    }

    public static EYM23ProTestStatus fromTag(byte b) {
        for (EYM23ProTestStatus eYM23ProTestStatus : values()) {
            if (eYM23ProTestStatus.tag == (b & 255)) {
                return eYM23ProTestStatus;
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
