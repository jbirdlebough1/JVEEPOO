package com.inuker.bluetooth.library.search;

import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/IBluetoothSearchHelper.class */
public interface IBluetoothSearchHelper {
    void startSearch(BluetoothSearchRequest bluetoothSearchRequest, BluetoothSearchResponse bluetoothSearchResponse);

    void stopSearch();
}
