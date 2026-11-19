package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDiagnosis;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IECGReadDataListener.class */
public interface IECGReadDataListener {
    void readDataFinish(List<EcgDetectResult> list);

    void readDiagnosisDataFinish(List<EcgDiagnosis> list);
}
