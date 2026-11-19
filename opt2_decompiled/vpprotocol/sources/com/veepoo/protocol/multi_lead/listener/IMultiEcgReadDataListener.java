package com.veepoo.protocol.multi_lead.listener;

import com.veepoo.protocol.multi_lead.data.MultiEcgDataResult;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/listener/IMultiEcgReadDataListener.class */
public interface IMultiEcgReadDataListener {
    void onReadDataChange(MultiEcgDataResult multiEcgDataResult);

    void onReadDataProgress(int i, float f);

    void onReadDataFail();

    void onReadComplete();
}
