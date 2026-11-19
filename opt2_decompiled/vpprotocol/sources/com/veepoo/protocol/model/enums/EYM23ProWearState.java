package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EYM23ProWearState.class */
public enum EYM23ProWearState {
    WEARED(0, "佩戴通过"),
    NOT_WEAR(1, "未佩戴"),
    UNKONW(-1, "未知");

    private final int tag;
    private final String des;

    EYM23ProWearState(int i, String str) {
        this.tag = i;
        this.des = str;
    }

    public static EYM23ProWearState fromTag(byte b) {
        for (EYM23ProWearState eYM23ProWearState : values()) {
            if (eYM23ProWearState.tag == (b & 255)) {
                return eYM23ProWearState;
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
