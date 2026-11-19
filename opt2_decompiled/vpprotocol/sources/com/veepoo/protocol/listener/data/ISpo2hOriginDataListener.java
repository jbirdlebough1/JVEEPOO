package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.Spo2hOriginData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISpo2hOriginDataListener.class */
public interface ISpo2hOriginDataListener extends IListener {
    void onReadOriginProgress(float f);

    void onReadOriginProgressDetail(int i, String str, int i2, int i3);

    void onSpo2hOriginListener(Spo2hOriginData spo2hOriginData);

    void onReadOriginComplete();
}
