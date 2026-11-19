package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.TemptureData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ITemptureDataListener.class */
public interface ITemptureDataListener extends IOriginProgressListener {
    void onTemptureDataListDataChange(List<TemptureData> list);
}
