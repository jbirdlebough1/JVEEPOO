package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.FindDeviceData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IFindDeviceDatalistener.class */
public interface IFindDeviceDatalistener extends IListener {
    void onFindDevice(FindDeviceData findDeviceData);
}
