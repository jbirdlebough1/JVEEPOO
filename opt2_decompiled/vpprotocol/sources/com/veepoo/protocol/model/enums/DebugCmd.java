package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DebugCmd.class */
public enum DebugCmd {
    CMD1(1, "清除手环数据和参数，并关机"),
    CMD2(2, "清除手环数据"),
    CMD3(3, "清除手环参数"),
    CMD4(4, "关机");

    public int cmd;
    public String des;

    DebugCmd(int i, String str) {
        this.cmd = i;
        this.des = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "DebugCmd{cmd=" + this.cmd + ", des='" + this.des + "'}";
    }
}
