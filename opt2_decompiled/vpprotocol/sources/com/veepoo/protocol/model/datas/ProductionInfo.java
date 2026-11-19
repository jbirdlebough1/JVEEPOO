package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ProductionInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/veepoo/protocol/model/datas/ProductionInfo;", "", "<init>", "()V", "tpVersion", "", "getTpVersion", "()Ljava/lang/String;", "setTpVersion", "(Ljava/lang/String;)V", "vpprotocol_release"})
public final class ProductionInfo {

    @NotNull
    private String tpVersion = "";

    @NotNull
    public final String getTpVersion() {
        return this.tpVersion;
    }

    public final void setTpVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tpVersion = str;
    }
}
