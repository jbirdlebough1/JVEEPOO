package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.ESafetyProtectionState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISafetyProtectionOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/ISafetyProtectionOptListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onPhoneSafetyProtectionOpt", "", "isStart", "", "onWatchSafetyProtectionOpt", "timestamp", "", "onPhoneSafetyProtectionStartError", "status", "Lcom/veepoo/protocol/model/enums/ESafetyProtectionState;", "vpprotocol_release"})
public interface ISafetyProtectionOptListener extends IListener {
    void onPhoneSafetyProtectionOpt(boolean isStart);

    void onWatchSafetyProtectionOpt(boolean isStart, long timestamp);

    void onPhoneSafetyProtectionStartError(@NotNull ESafetyProtectionState status);
}
