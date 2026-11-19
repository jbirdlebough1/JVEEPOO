package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.HRVOriginData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IHRVOriginDataListener.class */
public interface IHRVOriginDataListener extends IListener {
    void onReadOriginProgress(float f);

    void onReadOriginProgressDetail(int i, String str, int i2, int i3);

    void onHRVOriginListener(HRVOriginData hRVOriginData);

    void onDayHrvScore(int i, String str, int i2);

    void onReadOriginComplete();
}
