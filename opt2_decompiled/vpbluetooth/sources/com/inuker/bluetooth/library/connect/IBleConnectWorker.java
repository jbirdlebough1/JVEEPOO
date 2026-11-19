package com.inuker.bluetooth.library.connect;

import com.inuker.bluetooth.library.connect.listener.GattResponseListener;
import com.inuker.bluetooth.library.model.BleGattProfile;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/IBleConnectWorker.class */
public interface IBleConnectWorker {
    boolean openGatt();

    void closeGatt();

    boolean discoverService();

    int getCurrentStatus();

    void registerGattResponseListener(GattResponseListener gattResponseListener);

    void clearGattResponseListener(GattResponseListener gattResponseListener);

    boolean refreshDeviceCache();

    boolean readCharacteristic(UUID uuid, UUID uuid2);

    boolean writeCharacteristic(UUID uuid, UUID uuid2, byte[] bArr);

    boolean readDescriptor(UUID uuid, UUID uuid2, UUID uuid3);

    boolean writeDescriptor(UUID uuid, UUID uuid2, UUID uuid3, byte[] bArr);

    boolean writeCharacteristicWithNoRsp(UUID uuid, UUID uuid2, byte[] bArr);

    boolean setCharacteristicNotification(UUID uuid, UUID uuid2, boolean z);

    boolean setCharacteristicIndication(UUID uuid, UUID uuid2, boolean z);

    boolean readRemoteRssi();

    boolean requestMtu(int i);

    BleGattProfile getGattProfile();
}
