package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.TcmDiagnosis;
import com.veepoo.protocol.model.datas.TcmDiagnosisDetectState;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ITcmDiagnosisDetectListener.class */
public interface ITcmDiagnosisDetectListener extends IListener {
    void onTcmDiagnosisDetectInfoChange(EcgDetectInfo ecgDetectInfo);

    void onTcmDiagnosisDetectStateChange(TcmDiagnosisDetectState tcmDiagnosisDetectState);

    void onTcmDiagnosisDiagnosisChange(TcmDiagnosis tcmDiagnosis);

    void onEcgADCChange(int i, int[] iArr, int i2, int i3);
}
