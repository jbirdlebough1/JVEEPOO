package com.veepoo.protocol.multi_lead.data;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b(\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B1\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nB\t\b\u0016¢\u0006\u0004\b\t\u0010\u000bJ\b\u0010-\u001a\u00020.H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0012\"\u0004\b'\u0010\u0014R\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014¨\u0006/"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectState;", "", "leadFlag", "", "progress", "", "heart", "rr", "qt", "<init>", "([IIIII)V", "()V", "getLeadFlag", "()[I", "setLeadFlag", "([I)V", "leadI", "getLeadI", "()I", "setLeadI", "(I)V", "leadII", "getLeadII", "setLeadII", "leadIII", "getLeadIII", "setLeadIII", "leadAVR", "getLeadAVR", "setLeadAVR", "leadAVL", "getLeadAVL", "setLeadAVL", "leadAVF", "getLeadAVF", "setLeadAVF", "getProgress", "setProgress", "getHeart", "setHeart", "hrv", "getHrv", "setHrv", "getQt", "setQt", "toString", "", "vpprotocol_release"})
public final class EcgMultiLeadDetectState {

    @NotNull
    private int[] leadFlag;
    private int leadI;
    private int leadII;
    private int leadIII;
    private int leadAVR;
    private int leadAVL;
    private int leadAVF;
    private int progress;
    private int heart;
    private int hrv;
    private int qt;

    public EcgMultiLeadDetectState() {
        this.leadFlag = new int[6];
    }

    @NotNull
    public final int[] getLeadFlag() {
        return this.leadFlag;
    }

    public final void setLeadFlag(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.leadFlag = iArr;
    }

    public final int getLeadI() {
        return this.leadI;
    }

    public final void setLeadI(int i) {
        this.leadI = i;
    }

    public final int getLeadII() {
        return this.leadII;
    }

    public final void setLeadII(int i) {
        this.leadII = i;
    }

    public final int getLeadIII() {
        return this.leadIII;
    }

    public final void setLeadIII(int i) {
        this.leadIII = i;
    }

    public final int getLeadAVR() {
        return this.leadAVR;
    }

    public final void setLeadAVR(int i) {
        this.leadAVR = i;
    }

    public final int getLeadAVL() {
        return this.leadAVL;
    }

    public final void setLeadAVL(int i) {
        this.leadAVL = i;
    }

    public final int getLeadAVF() {
        return this.leadAVF;
    }

    public final void setLeadAVF(int i) {
        this.leadAVF = i;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final int getHeart() {
        return this.heart;
    }

    public final void setHeart(int i) {
        this.heart = i;
    }

    public final int getHrv() {
        return this.hrv;
    }

    public final void setHrv(int i) {
        this.hrv = i;
    }

    public final int getQt() {
        return this.qt;
    }

    public final void setQt(int i) {
        this.qt = i;
    }

    @NotNull
    public String toString() {
        String string = Arrays.toString(this.leadFlag);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return "EcgMultiLeadDetectState(leadFlag=" + string + ", leadI=" + this.leadI + ", leadII=" + this.leadII + ", leadIII=" + this.leadIII + ", leadAVR=" + this.leadAVR + ", leadAVL=" + this.leadAVL + ", leadAVF=" + this.leadAVF + ", progress=" + this.progress + ", heart=" + this.heart + ", hrv=" + this.hrv + ", qt=" + this.qt + ")";
    }

    public EcgMultiLeadDetectState(@NotNull int[] iArr, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(iArr, "leadFlag");
        this.leadFlag = iArr;
        this.progress = i;
        this.heart = i2;
        this.hrv = i3;
        this.qt = i4;
        if (!(!(iArr.length == 0)) || iArr.length <= 5) {
            return;
        }
        this.leadI = iArr[0];
        this.leadII = iArr[1];
        this.leadIII = iArr[2];
        this.leadAVR = iArr[3];
        this.leadAVL = iArr[4];
        this.leadAVF = iArr[5];
    }
}
