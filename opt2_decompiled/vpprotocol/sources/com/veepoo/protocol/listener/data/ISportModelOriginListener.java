package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.SportModelGPSWatchOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginItemData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISportModelOriginListener.class */
public interface ISportModelOriginListener extends IListener {
    void onReadOriginProgress(float f);

    void onReadOriginProgressDetail(int i, String str, int i2, int i3);

    void onHeadChangeListListener(SportModelOriginHeadData sportModelOriginHeadData);

    void onGPSWatchSportModeHeadChange(SportModelGPSWatchOriginHeadData sportModelGPSWatchOriginHeadData);

    void onItemChangeListListener(List<SportModelOriginItemData> list);

    void onReadOriginComplete();
}
