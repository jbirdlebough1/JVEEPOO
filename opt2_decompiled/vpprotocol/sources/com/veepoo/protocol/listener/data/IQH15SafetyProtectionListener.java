package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.EQH15SetProtectionFuncAckState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IQH15SafetyProtectionListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IQH15SafetyProtectionListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onQH15SafetyProtectionSetResult", "", "ackState", "Lcom/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState;", "onQH15SafetyProtectionStateChanged", "isOpen", "", "timestamp", "", "vpprotocol_release"})
public interface IQH15SafetyProtectionListener extends IListener {
    void onQH15SafetyProtectionSetResult(@NotNull EQH15SetProtectionFuncAckState ackState);

    void onQH15SafetyProtectionStateChanged(boolean isOpen, long timestamp);
}
