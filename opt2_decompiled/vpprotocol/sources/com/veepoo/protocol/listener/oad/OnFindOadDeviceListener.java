package com.veepoo.protocol.listener.oad;

import com.veepoo.protocol.model.enums.ECPUPlatform;
import com.veepoo.protocol.model.enums.ECpuType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/oad/OnFindOadDeviceListener.class */
public interface OnFindOadDeviceListener {
    @Deprecated
    void findOadDevice(String str, ECpuType eCpuType);

    void findOadDevice(String str, ECPUPlatform eCPUPlatform);

    void unKnowCpu();
}
