package com.inuker.bluetooth.library.search.classic;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.inuker.bluetooth.library.search.BluetoothSearcher;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothUtils;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/classic/BluetoothClassicSearcher.class */
public class BluetoothClassicSearcher extends BluetoothSearcher {
    private BluetoothSearchReceiver mReceiver;

    private BluetoothClassicSearcher() {
        this.mBluetoothAdapter = BluetoothUtils.getBluetoothAdapter();
    }

    public static BluetoothClassicSearcher getInstance() {
        return BluetoothClassicSearcherHolder.instance;
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/classic/BluetoothClassicSearcher$BluetoothClassicSearcherHolder.class */
    private static class BluetoothClassicSearcherHolder {
        private static BluetoothClassicSearcher instance = new BluetoothClassicSearcher();

        private BluetoothClassicSearcherHolder() {
        }
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    public void startScanBluetooth(BluetoothSearchResponse callback) {
        super.startScanBluetooth(callback);
        registerReceiver();
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
        this.mBluetoothAdapter.startDiscovery();
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    public void stopScanBluetooth() {
        unregisterReceiver();
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
        super.stopScanBluetooth();
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    protected void cancelScanBluetooth() {
        unregisterReceiver();
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
        super.cancelScanBluetooth();
    }

    private void registerReceiver() {
        if (this.mReceiver == null) {
            this.mReceiver = new BluetoothSearchReceiver();
            BluetoothUtils.registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        }
    }

    private void unregisterReceiver() {
        if (this.mReceiver != null) {
            BluetoothUtils.unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/classic/BluetoothClassicSearcher$BluetoothSearchReceiver.class */
    private class BluetoothSearchReceiver extends BroadcastReceiver {
        private BluetoothSearchReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.device.action.FOUND")) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int rssi = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
                SearchResult xmDevice = new SearchResult(device, rssi, null);
                BluetoothClassicSearcher.this.notifyDeviceFounded(xmDevice);
            }
        }
    }
}
