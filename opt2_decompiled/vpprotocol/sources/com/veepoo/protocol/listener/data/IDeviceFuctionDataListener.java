package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage1;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage2;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage3;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage4;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage5;
import com.veepoo.protocol.model.datas.FunctionDeviceSupportData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IDeviceFuctionDataListener.class */
public interface IDeviceFuctionDataListener extends IListener {
    @Deprecated
    void onFunctionSupportDataChange(FunctionDeviceSupportData functionDeviceSupportData);

    void onDeviceFunctionPackage1Report(DeviceFunctionPackage1 deviceFunctionPackage1);

    void onDeviceFunctionPackage2Report(DeviceFunctionPackage2 deviceFunctionPackage2);

    void onDeviceFunctionPackage3Report(DeviceFunctionPackage3 deviceFunctionPackage3);

    void onDeviceFunctionPackage4Report(DeviceFunctionPackage4 deviceFunctionPackage4);

    void onDeviceFunctionPackage5Report(DeviceFunctionPackage5 deviceFunctionPackage5);
}
