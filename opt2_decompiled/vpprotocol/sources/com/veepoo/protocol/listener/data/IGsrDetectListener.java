package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.GsrDetectResult;
import com.veepoo.protocol.model.enums.GsrDetectAck;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IGsrDetectListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IGsrDetectListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onGsrDetectProgress", "", "progress", "", "onGsrDetectSuccess", "detectResult", "Lcom/veepoo/protocol/model/datas/GsrDetectResult;", "onGsrDetectFailed", "detectAck", "Lcom/veepoo/protocol/model/enums/GsrDetectAck;", "onGsrDetectStop", "vpprotocol_release"})
public interface IGsrDetectListener extends IListener {
    void onGsrDetectProgress(int progress);

    void onGsrDetectSuccess(@NotNull GsrDetectResult detectResult);

    void onGsrDetectFailed(@NotNull GsrDetectAck detectAck);

    void onGsrDetectStop();
}
