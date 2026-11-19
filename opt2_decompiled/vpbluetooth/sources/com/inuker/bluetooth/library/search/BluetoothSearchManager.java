package com.inuker.bluetooth.library.search;

import android.os.Bundle;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/BluetoothSearchManager.class */
public class BluetoothSearchManager {
    public static void search(SearchRequest request, final BleGeneralResponse response) {
        BluetoothSearchRequest requestWrapper = new BluetoothSearchRequest(request);
        BluetoothSearchHelper.getInstance().startSearch(requestWrapper, new BluetoothSearchResponse() { // from class: com.inuker.bluetooth.library.search.BluetoothSearchManager.1
            @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
            public void onSearchStarted() {
                response.onResponse(1, null);
            }

            @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
            public void onDeviceFounded(SearchResult device) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("extra.search.result", device);
                response.onResponse(4, bundle);
            }

            @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
            public void onSearchStopped() {
                response.onResponse(2, null);
            }

            @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
            public void onSearchCanceled() {
                response.onResponse(3, null);
            }
        });
    }

    public static void stopSearch() {
        BluetoothSearchHelper.getInstance().stopSearch();
    }
}
