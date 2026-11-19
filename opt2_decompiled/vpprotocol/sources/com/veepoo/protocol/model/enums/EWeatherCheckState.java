package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EWeatherCheckState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/veepoo/protocol/model/enums/EWeatherCheckState;", "", "des", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDes", "()Ljava/lang/String;", "START_CHECK", "PERMISSION_DISABLED", "GPS_DISABLED", "GPS_SIGNAL_WEAK", "GPS_SIGNAL_NORMAL", "GPS_SIGNAL_STRONG", "NETWORK_ERROR", "GET_LOCATION_NETWORK_ERROR", "NETWORK_NORMAL", "NOT_NEED_UPDATE", "UPDATE_FAILED", "CONNECT_DISCONNECT", "DEVICE_DETECTING", "UPDATE_SUCCESS", "vpprotocol_release"})
public enum EWeatherCheckState {
    START_CHECK("Start Detection"),
    PERMISSION_DISABLED("权限不可用"),
    GPS_DISABLED("gps不可用，未打开"),
    GPS_SIGNAL_WEAK("gps信号弱"),
    GPS_SIGNAL_NORMAL("gps信号一般"),
    GPS_SIGNAL_STRONG("gps信号强"),
    NETWORK_ERROR("网络连接异常"),
    GET_LOCATION_NETWORK_ERROR("获取地址名称，网络连接异常"),
    NETWORK_NORMAL("网络正常"),
    NOT_NEED_UPDATE("无需更新"),
    UPDATE_FAILED("更新失败"),
    CONNECT_DISCONNECT("设备连接断开"),
    DEVICE_DETECTING("设备测量中"),
    UPDATE_SUCCESS("无需更新");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @NotNull
    private final String des;

    EWeatherCheckState(String str) {
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EWeatherCheckState> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
