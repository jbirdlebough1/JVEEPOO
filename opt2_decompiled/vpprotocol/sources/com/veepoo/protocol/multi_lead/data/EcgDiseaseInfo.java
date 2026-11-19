package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EDisease;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgDiseaseInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/EcgDiseaseInfo;", "", "<init>", "()V", "type", "Lcom/veepoo/protocol/multi_lead/enums/EDisease;", "getType", "()Lcom/veepoo/protocol/multi_lead/enums/EDisease;", "setType", "(Lcom/veepoo/protocol/multi_lead/enums/EDisease;)V", "prod", "", "getProd", "()I", "setProd", "(I)V", "len", "getLen", "setLen", "riskLevel", "getRiskLevel", "setRiskLevel", "position", "", "getPosition", "()[I", "setPosition", "([I)V", "toString", "", "vpprotocol_release"})
public final class EcgDiseaseInfo {
    private int prod;
    private int len;
    private int riskLevel;

    @NotNull
    private EDisease type = EDisease.AtrialPrematureBeat;

    @NotNull
    private int[] position = new int[0];

    @NotNull
    public final EDisease getType() {
        return this.type;
    }

    public final void setType(@NotNull EDisease eDisease) {
        Intrinsics.checkNotNullParameter(eDisease, "<set-?>");
        this.type = eDisease;
    }

    public final int getProd() {
        return this.prod;
    }

    public final void setProd(int i) {
        this.prod = i;
    }

    public final int getLen() {
        return this.len;
    }

    public final void setLen(int i) {
        this.len = i;
    }

    public final int getRiskLevel() {
        return this.riskLevel;
    }

    public final void setRiskLevel(int i) {
        this.riskLevel = i;
    }

    @NotNull
    public final int[] getPosition() {
        return this.position;
    }

    public final void setPosition(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.position = iArr;
    }

    @NotNull
    public String toString() {
        EDisease eDisease = this.type;
        int i = this.prod;
        int i2 = this.len;
        int i3 = this.riskLevel;
        String string = Arrays.toString(this.position);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return "EcgDiseaseInfo(type=" + eDisease + ", prod=" + i + ", len=" + i2 + ", riskLevel=" + i3 + ", position=" + string + ")";
    }
}
