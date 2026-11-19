package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.EYM23ProOptStatus;
import com.veepoo.protocol.model.enums.EYM23ProTestStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IYM23ProTestStatusListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IYM23ProTestStatusListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onYM23ProTestStatusResult", "", "status", "Lcom/veepoo/protocol/model/enums/EYM23ProOptStatus;", "testStatus", "Lcom/veepoo/protocol/model/enums/EYM23ProTestStatus;", "vpprotocol_release"})
public interface IYM23ProTestStatusListener extends IListener {
    void onYM23ProTestStatusResult(@NotNull EYM23ProOptStatus status, @NotNull EYM23ProTestStatus testStatus);
}
