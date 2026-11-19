package com.veepoo.protocol.listener.base;

import com.inuker.bluetooth.library.model.BleGattProfile;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/base/IConnectResponse.class */
public interface IConnectResponse {
    void connectState(int i, BleGattProfile bleGattProfile, boolean z);
}
