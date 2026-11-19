package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/NetworkSever4gConfigInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010.\u001a\u00020\u0005H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\tR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010%\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001e\u0010(\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b)\u0010\r\"\u0004\b*\u0010\u000fR\u001e\u0010+\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b,\u0010\u001a\"\u0004\b-\u0010\u001c¨\u0006/"}, d2 = {"Lcom/veepoo/protocol/model/datas/NetworkSever4gConfigInfo;", "", "<init>", "()V", "ipAddress", "", "getIpAddress", "()Ljava/lang/String;", "setIpAddress", "(Ljava/lang/String;)V", "port", "", "getPort", "()Ljava/lang/Integer;", "setPort", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "userName", "getUserName", "setUserName", "password", "getPassword", "setPassword", "syncTime", "", "getSyncTime", "()Ljava/lang/Long;", "setSyncTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "switch4g", "", "getSwitch4g", "()Ljava/lang/Boolean;", "setSwitch4g", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "switchUpload", "getSwitchUpload", "setSwitchUpload", "reportInterval", "getReportInterval", "setReportInterval", "recoveryTime", "getRecoveryTime", "setRecoveryTime", "toString", "vpprotocol_release"})
public final class NetworkSever4gConfigInfo {

    @Nullable
    private String ipAddress;

    @Nullable
    private Integer port;

    @Nullable
    private String userName;

    @Nullable
    private String password;

    @Nullable
    private Long syncTime;

    @Nullable
    private Boolean switch4g;

    @Nullable
    private Boolean switchUpload;

    @Nullable
    private Integer reportInterval;

    @Nullable
    private Long recoveryTime;

    @Nullable
    public final String getIpAddress() {
        return this.ipAddress;
    }

    public final void setIpAddress(@Nullable String str) {
        this.ipAddress = str;
    }

    @Nullable
    public final Integer getPort() {
        return this.port;
    }

    public final void setPort(@Nullable Integer num) {
        this.port = num;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    public final void setUserName(@Nullable String str) {
        this.userName = str;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(@Nullable String str) {
        this.password = str;
    }

    @Nullable
    public final Long getSyncTime() {
        return this.syncTime;
    }

    public final void setSyncTime(@Nullable Long l) {
        this.syncTime = l;
    }

    @Nullable
    public final Boolean getSwitch4g() {
        return this.switch4g;
    }

    public final void setSwitch4g(@Nullable Boolean bool) {
        this.switch4g = bool;
    }

    @Nullable
    public final Boolean getSwitchUpload() {
        return this.switchUpload;
    }

    public final void setSwitchUpload(@Nullable Boolean bool) {
        this.switchUpload = bool;
    }

    @Nullable
    public final Integer getReportInterval() {
        return this.reportInterval;
    }

    public final void setReportInterval(@Nullable Integer num) {
        this.reportInterval = num;
    }

    @Nullable
    public final Long getRecoveryTime() {
        return this.recoveryTime;
    }

    public final void setRecoveryTime(@Nullable Long l) {
        this.recoveryTime = l;
    }

    @NotNull
    public String toString() {
        return "NetworkSever4gConfigInfo(ipAddress=" + this.ipAddress + ", port=" + this.port + ", userName=" + this.userName + ", password=" + this.password + ", syncTime=" + this.syncTime + ", switch4g=" + this.switch4g + ", switchUpload=" + this.switchUpload + ", reportInterval=" + this.reportInterval + ", recoveryTime=" + this.recoveryTime + ")";
    }
}
