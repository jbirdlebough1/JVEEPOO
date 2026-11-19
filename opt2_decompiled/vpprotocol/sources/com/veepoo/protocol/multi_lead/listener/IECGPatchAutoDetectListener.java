package com.veepoo.protocol.multi_lead.listener;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.multi_lead.data.ECGPatchAutoDetectData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/listener/IECGPatchAutoDetectListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/multi_lead/listener/IECGPatchAutoDetectListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onECGPatchAutoDetectResult", "", "data", "Lcom/veepoo/protocol/multi_lead/data/ECGPatchAutoDetectData;", "vpprotocol_release"})
public interface IECGPatchAutoDetectListener extends IListener {
    void onECGPatchAutoDetectResult(@NotNull ECGPatchAutoDetectData data);
}
