package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.AutoDetectOriginData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAutoDetectOriginDataListener.class */
public interface IAutoDetectOriginDataListener extends IListener {
    void onAutoDetectOriginDataChangeListener(List<AutoDetectOriginData> list);
}
