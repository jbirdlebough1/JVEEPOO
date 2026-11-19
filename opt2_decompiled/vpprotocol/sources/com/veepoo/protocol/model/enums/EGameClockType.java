package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGameClockType.class */
public enum EGameClockType {
    WEEKLY_REPEAT(1, "星期重复", "Weekly repeat"),
    SINGLE(2, "单次不重复", "Single"),
    TEMPORARY(3, "临时", "Temporary");

    private final int value;
    private final String cnName;
    private final String enName;

    EGameClockType(int i, String str, String str2) {
        this.value = i;
        this.cnName = str;
        this.enName = str2;
    }

    public static EGameClockType fromValue(int i) {
        for (EGameClockType eGameClockType : values()) {
            if (eGameClockType.value == i) {
                return eGameClockType;
            }
        }
        return SINGLE;
    }

    public int getValue() {
        return this.value;
    }

    public String getCnName() {
        return this.cnName;
    }

    public String getEnName() {
        return this.enName;
    }

    public boolean isWeeklyRepeat() {
        return this == WEEKLY_REPEAT;
    }

    public boolean hasDate() {
        return this == SINGLE || this == TEMPORARY;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.cnName;
    }
}
