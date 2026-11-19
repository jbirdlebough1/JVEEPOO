package com.veepoo.protocol.listener.base;

import com.inuker.bluetooth.library.connect.response.BleWriteResponse;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/base/IBleWriteResponse.class */
public interface IBleWriteResponse extends BleWriteResponse {
    void onResponse(int i);
}
