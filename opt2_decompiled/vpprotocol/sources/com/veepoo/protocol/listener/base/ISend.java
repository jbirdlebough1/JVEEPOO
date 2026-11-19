package com.veepoo.protocol.listener.base;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/base/ISend.class */
public interface ISend {
    int send(byte[] bArr, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse);
}
