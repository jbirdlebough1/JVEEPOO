package com.veepoo.protocol.multi_lead.data;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgMultiLeadPreInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadPreInfo;", "", "frequency", "", "signalNum", "status", "<init>", "(III)V", "()V", "getFrequency", "()I", "setFrequency", "(I)V", "getSignalNum", "setSignalNum", "getStatus", "setStatus", "toString", "", "vpprotocol_release"})
public final class EcgMultiLeadPreInfo {
    private int frequency;
    private int signalNum;
    private int status;

    public EcgMultiLeadPreInfo() {
        this.frequency = 250;
        this.signalNum = 6;
    }

    public final int getFrequency() {
        return this.frequency;
    }

    public final void setFrequency(int i) {
        this.frequency = i;
    }

    public final int getSignalNum() {
        return this.signalNum;
    }

    public final void setSignalNum(int i) {
        this.signalNum = i;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    @NotNull
    public String toString() {
        return "EcgMultiLeadPreInfo(frequency=" + this.frequency + ", signalNum=" + this.signalNum + ")";
    }

    public EcgMultiLeadPreInfo(int i, int i2, int i3) {
        this.frequency = i;
        this.signalNum = i2;
        this.status = i3;
    }
}
