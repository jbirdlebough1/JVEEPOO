package com.inuker.bluetooth.library.connect.request;

import com.inuker.bluetooth.library.connect.IBleConnectDispatcher;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/IBleRequest.class */
public interface IBleRequest {
    void process(IBleConnectDispatcher iBleConnectDispatcher);

    void cancel();
}
