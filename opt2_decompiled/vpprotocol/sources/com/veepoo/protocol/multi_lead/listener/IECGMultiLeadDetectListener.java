package com.veepoo.protocol.multi_lead.listener;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadDetectResult;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadDetectState;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadPreInfo;
import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/listener/IECGMultiLeadDetectListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J(\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/multi_lead/listener/IECGMultiLeadDetectListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onEcgDetectPreInfoChange", "", "preInfo", "Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadPreInfo;", "onEcgDetectStateChange", "detectState", "Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectState;", "onDiseaseDiagnosisResults", "ecgDetectResult", "Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectResult;", "onEcgDetectFail", "onEcgDetectSuccess", "onEcgADCChange", "leadFlag", "Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "data", "", "gain", "", "packNum", "vpprotocol_release"})
public interface IECGMultiLeadDetectListener extends IListener {
    void onEcgDetectPreInfoChange(@NotNull EcgMultiLeadPreInfo preInfo);

    void onEcgDetectStateChange(@NotNull EcgMultiLeadDetectState detectState);

    void onDiseaseDiagnosisResults(@NotNull EcgMultiLeadDetectResult ecgDetectResult);

    void onEcgDetectFail();

    void onEcgDetectSuccess();

    void onEcgADCChange(@NotNull ELeadFlag leadFlag, @NotNull int[] data, int gain, int packNum);
}
