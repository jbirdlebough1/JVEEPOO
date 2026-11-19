package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EBloodComponentDetectState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/veepoo/protocol/model/enums/EBloodComponentDetectState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "setCode", "(B)V", "getDes", "()Ljava/lang/String;", "setDes", "(Ljava/lang/String;)V", "ENABLE", "DETECTING", "LOW_POWER", "BUSY", "WEAR_ERROR", "UNKNOWN", "vpprotocol_release"})
public enum EBloodComponentDetectState {
    ENABLE((byte) 0, "设备可用"),
    DETECTING((byte) 1, "正在测试"),
    LOW_POWER((byte) 2, "低电量"),
    BUSY((byte) 3, "设备正在测量其它数据，App端显示设备正忙"),
    WEAR_ERROR((byte) 4, "佩戴未通过"),
    UNKNOWN((byte) -1, "未知错误");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private byte code;

    @NotNull
    private String des;

    EBloodComponentDetectState(byte b, String str) {
        this.code = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EBloodComponentDetectState> getEntries() {
        return $ENTRIES;
    }

    public final byte getCode() {
        return this.code;
    }

    public final void setCode(byte b) {
        this.code = b;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }

    public final void setDes(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.des = str;
    }
}
