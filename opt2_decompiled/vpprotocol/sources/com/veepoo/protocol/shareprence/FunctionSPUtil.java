package com.veepoo.protocol.shareprence;

import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/shareprence/FunctionSPUtil.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/veepoo/protocol/shareprence/FunctionSPUtil;", "", "<init>", "()V", "getHeartRateFunctionStatus", "Lcom/veepoo/protocol/model/enums/EFunctionStatus;", "vpprotocol_release"})
public final class FunctionSPUtil {

    @NotNull
    public static final FunctionSPUtil INSTANCE = new FunctionSPUtil();

    private FunctionSPUtil() {
    }

    @NotNull
    public final EFunctionStatus getHeartRateFunctionStatus() {
        if (VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).isSupportFivemiuteHeart()) {
            return VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).isHeartRateDetectOpen() ? EFunctionStatus.SUPPORT_OPEN : EFunctionStatus.SUPPORT_CLOSE;
        }
        return EFunctionStatus.UNSUPPORT;
    }
}
