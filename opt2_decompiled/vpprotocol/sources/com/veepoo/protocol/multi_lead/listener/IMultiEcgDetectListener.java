package com.veepoo.protocol.multi_lead.listener;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.multi_lead.data.MultiEcgDetectInfo;
import com.veepoo.protocol.multi_lead.data.MultiEcgPreInfo;
import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/listener/IMultiEcgDetectListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J,\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/multi_lead/listener/IMultiEcgDetectListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onEcgDetectPreStart", "", "preInfo", "Lcom/veepoo/protocol/multi_lead/data/MultiEcgPreInfo;", "onEcgDeviceNotSupport", "onEcgDeviceNotInDetect", "onEcgDetectStateChange", "detectState", "Lcom/veepoo/protocol/multi_lead/data/MultiEcgDetectInfo;", "onEcgADCChange", "leadFlag", "Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "data", "", "gain", "", "packNum", "vpprotocol_release"})
public interface IMultiEcgDetectListener extends IListener {
    void onEcgDetectPreStart(@NotNull MultiEcgPreInfo preInfo);

    void onEcgDeviceNotSupport();

    void onEcgDeviceNotInDetect();

    void onEcgDetectStateChange(@NotNull MultiEcgDetectInfo detectState);

    void onEcgADCChange(@Nullable ELeadFlag leadFlag, @Nullable int[] data, int gain, int packNum);
}
