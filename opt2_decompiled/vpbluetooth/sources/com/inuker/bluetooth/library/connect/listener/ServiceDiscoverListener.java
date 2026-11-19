package com.inuker.bluetooth.library.connect.listener;

import com.inuker.bluetooth.library.model.BleGattProfile;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/ServiceDiscoverListener.class */
public interface ServiceDiscoverListener extends GattResponseListener {
    void onServicesDiscovered(int i, BleGattProfile bleGattProfile);
}
