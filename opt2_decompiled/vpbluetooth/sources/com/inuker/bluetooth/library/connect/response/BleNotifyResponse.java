package com.inuker.bluetooth.library.connect.response;

import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/response/BleNotifyResponse.class */
public interface BleNotifyResponse extends BleResponse {
    void onNotify(UUID uuid, UUID uuid2, byte[] bArr);
}
