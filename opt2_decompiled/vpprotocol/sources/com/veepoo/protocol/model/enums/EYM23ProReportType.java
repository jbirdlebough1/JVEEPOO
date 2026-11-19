package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EYM23ProReportType.class */
public enum EYM23ProReportType {
    WEAR_CHANGE(1, "佩戴状态变更上报(含首次检测结果)"),
    TIMED_REPORT(2, "每秒定时上报"),
    UNKONW(-1, "未知");

    private final int tag;
    private final String des;

    EYM23ProReportType(int i, String str) {
        this.tag = i;
        this.des = str;
    }

    public static EYM23ProReportType fromTag(byte b) {
        for (EYM23ProReportType eYM23ProReportType : values()) {
            if (eYM23ProReportType.tag == (b & 255)) {
                return eYM23ProReportType;
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
