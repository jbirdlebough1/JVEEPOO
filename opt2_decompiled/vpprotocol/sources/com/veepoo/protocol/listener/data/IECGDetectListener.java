package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDetectState;
import com.veepoo.protocol.model.datas.EcgDiagnosis;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IECGDetectListener.class */
public interface IECGDetectListener extends IListener {
    void onEcgDetectInfoChange(EcgDetectInfo ecgDetectInfo);

    void onEcgDetectStateChange(EcgDetectState ecgDetectState);

    void onEcgDetectResultChange(EcgDetectResult ecgDetectResult);

    void onEcgDetectDiagnosisChange(EcgDiagnosis ecgDiagnosis);

    void onEcgADCChange(int[] iArr, int[] iArr2);
}
