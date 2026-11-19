package com.inuker.bluetooth.library.connect;

import com.inuker.bluetooth.library.IBluetoothBase;
import com.inuker.bluetooth.library.connect.listener.GattResponseListener;
import com.inuker.bluetooth.library.model.BleGattProfile;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/IBleRequestProcessor.class */
public interface IBleRequestProcessor extends IBluetoothBase {
    public static final int STATUS_DEVICE_CONNECTED = 2;
    public static final int STATUS_DEVICE_DISCONNECTED = 0;
    public static final int STATUS_DEVICE_SERVICE_READY = 19;

    void registerGattResponseListener(int i, GattResponseListener gattResponseListener);

    void unregisterGattResponseListener(int i);

    void notifyRequestResult();

    int getConnectStatus();

    BleGattProfile getGattProfile();

    boolean openBluetoothGatt();

    void disconnect();

    void closeBluetoothGatt();

    boolean readCharacteristic(UUID uuid, UUID uuid2);

    boolean writeCharacteristic(UUID uuid, UUID uuid2, byte[] bArr);

    boolean writeCharacteristicWithNoRsp(UUID uuid, UUID uuid2, byte[] bArr);

    boolean setCharacteristicNotification(UUID uuid, UUID uuid2, boolean z);

    boolean readRemoteRssi();

    void refreshCache();
}
