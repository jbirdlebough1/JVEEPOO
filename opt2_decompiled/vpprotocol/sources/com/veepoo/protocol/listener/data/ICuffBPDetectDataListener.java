package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.VPCuffBPTestData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICuffBPDetectDataListener.class */
public interface ICuffBPDetectDataListener extends IListener {
    void onCuffDataChange(boolean z, VPCuffBPTestData vPCuffBPTestData);
}
