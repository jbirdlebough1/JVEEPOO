package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IFindDevicelistener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IFindDevicelistener;", "Lcom/veepoo/protocol/listener/base/IListener;", "unSupportFindDeviceByPhone", "", "findedDevice", "unFindDevice", "findingDevice", "vpprotocol_release"})
public interface IFindDevicelistener extends IListener {
    void unSupportFindDeviceByPhone();

    void findedDevice();

    void unFindDevice();

    void findingDevice();
}
