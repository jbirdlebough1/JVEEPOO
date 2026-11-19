package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.AutoMeasureData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAutoMeasureSettingDataListener.class */
public interface IAutoMeasureSettingDataListener extends IListener {
    void onSettingDataChange(List<AutoMeasureData> list);

    void onSettingDataChangeFail();

    void onSettingDataChangeSuccess();
}
