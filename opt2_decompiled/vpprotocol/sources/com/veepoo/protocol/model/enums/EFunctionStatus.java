package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EFunctionStatus.class */
public enum EFunctionStatus {
    UNSUPPORT,
    SUPPORT,
    SUPPORT_OPEN,
    SUPPORT_CLOSE,
    UNKONW;

    public boolean isHaveFunction() {
        return (this == UNSUPPORT || this == UNKONW) ? false : true;
    }

    public boolean isOpen() {
        return this == SUPPORT_OPEN;
    }

    public boolean isClose() {
        return this == SUPPORT_CLOSE;
    }
}
