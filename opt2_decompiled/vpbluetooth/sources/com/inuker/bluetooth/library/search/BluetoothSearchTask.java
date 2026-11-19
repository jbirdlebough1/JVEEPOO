package com.inuker.bluetooth.library.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/BluetoothSearchTask.class */
public class BluetoothSearchTask implements Handler.Callback {
    private static final int MSG_SEARCH_TIMEOUT = 34;
    private int searchType;
    private int searchDuration;
    private BluetoothSearcher mBluetoothSearcher;
    private Handler mHandler;

    public BluetoothSearchTask(SearchTask task) {
        setSearchType(task.getSearchType());
        setSearchDuration(task.getSearchDuration());
        this.mHandler = new Handler(Looper.myLooper(), this);
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public void setSearchDuration(int searchDuration) {
        this.searchDuration = searchDuration;
    }

    public boolean isBluetoothLeSearch() {
        return this.searchType == 2;
    }

    public boolean isBluetoothClassicSearch() {
        return this.searchType == 1;
    }

    private BluetoothSearcher getBluetoothSearcher() {
        if (this.mBluetoothSearcher == null) {
            this.mBluetoothSearcher = BluetoothSearcher.newInstance(this.searchType);
        }
        return this.mBluetoothSearcher;
    }

    public void start(BluetoothSearchResponse response) {
        getBluetoothSearcher().startScanBluetooth(response);
        this.mHandler.sendEmptyMessageDelayed(MSG_SEARCH_TIMEOUT, this.searchDuration);
    }

    public void cancel() {
        this.mHandler.removeCallbacksAndMessages(null);
        getBluetoothSearcher().cancelScanBluetooth();
    }

    public String toString() {
        String type;
        if (isBluetoothLeSearch()) {
            type = "Ble";
        } else if (isBluetoothClassicSearch()) {
            type = "classic";
        } else {
            type = "unknown";
        }
        if (this.searchDuration >= 1000) {
            return String.format("%s search (%ds)", type, Integer.valueOf(this.searchDuration / 1000));
        }
        return String.format("%s search (%.1fs)", type, Double.valueOf((1.0d * ((double) this.searchDuration)) / 1000.0d));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SEARCH_TIMEOUT /* 34 */:
                getBluetoothSearcher().stopScanBluetooth();
                break;
        }
        return true;
    }
}
