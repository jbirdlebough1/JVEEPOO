package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EWearHandMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/WearHandConfigData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/veepoo/protocol/model/datas/WearHandConfigData;", "", "cmd", "", "success", "", "wearMode", "Lcom/veepoo/protocol/model/enums/EWearHandMode;", "<init>", "(IZLcom/veepoo/protocol/model/enums/EWearHandMode;)V", "getCmd", "()I", "setCmd", "(I)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "getWearMode", "()Lcom/veepoo/protocol/model/enums/EWearHandMode;", "setWearMode", "(Lcom/veepoo/protocol/model/enums/EWearHandMode;)V", "toString", "", "vpprotocol_release"})
public final class WearHandConfigData {
    private int cmd;
    private boolean success;

    @NotNull
    private EWearHandMode wearMode;

    public WearHandConfigData() {
        this(0, false, null, 7, null);
    }

    public final int getCmd() {
        return this.cmd;
    }

    public final void setCmd(int i) {
        this.cmd = i;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    @NotNull
    public final EWearHandMode getWearMode() {
        return this.wearMode;
    }

    public final void setWearMode(@NotNull EWearHandMode eWearHandMode) {
        Intrinsics.checkNotNullParameter(eWearHandMode, "<set-?>");
        this.wearMode = eWearHandMode;
    }

    @NotNull
    public String toString() {
        return "WearHandConfigData(cmd=" + this.cmd + ", success=" + this.success + ", wearMode=" + this.wearMode.getDes() + ")";
    }

    public WearHandConfigData(int i, boolean z, @NotNull EWearHandMode eWearHandMode) {
        Intrinsics.checkNotNullParameter(eWearHandMode, "wearMode");
        this.cmd = i;
        this.success = z;
        this.wearMode = eWearHandMode;
    }

    public /* synthetic */ WearHandConfigData(int i, boolean z, EWearHandMode eWearHandMode, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? EWearHandMode.LEFT_HAND : eWearHandMode);
    }
}
