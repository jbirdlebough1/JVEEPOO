package com.inuker.bluetooth.library.search;

import android.bluetooth.BluetoothAdapter;
import com.inuker.bluetooth.library.search.classic.BluetoothClassicSearcher;
import com.inuker.bluetooth.library.search.le.BluetoothLESearcher;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/BluetoothSearcher.class */
public class BluetoothSearcher {
    protected BluetoothAdapter mBluetoothAdapter;
    protected BluetoothSearchResponse mSearchResponse;

    public static BluetoothSearcher newInstance(int type) {
        switch (type) {
            case 1:
                return BluetoothClassicSearcher.getInstance();
            case 2:
                return BluetoothLESearcher.getInstance();
            default:
                throw new IllegalStateException(String.format("unknown search type %d", Integer.valueOf(type)));
        }
    }

    protected void startScanBluetooth(BluetoothSearchResponse callback) {
        this.mSearchResponse = callback;
        notifySearchStarted();
    }

    protected void stopScanBluetooth() {
        notifySearchStopped();
        this.mSearchResponse = null;
    }

    protected void cancelScanBluetooth() {
        notifySearchCanceled();
        this.mSearchResponse = null;
    }

    private void notifySearchStarted() {
        if (this.mSearchResponse != null) {
            this.mSearchResponse.onSearchStarted();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyDeviceFounded(SearchResult device) {
        if (this.mSearchResponse != null) {
            this.mSearchResponse.onDeviceFounded(device);
        }
    }

    private void notifySearchStopped() {
        if (this.mSearchResponse != null) {
            this.mSearchResponse.onSearchStopped();
        }
    }

    private void notifySearchCanceled() {
        if (this.mSearchResponse != null) {
            this.mSearchResponse.onSearchCanceled();
        }
    }
}
