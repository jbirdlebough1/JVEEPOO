package com.inuker.bluetooth.library.receiver;

import android.content.Context;
import android.content.Intent;
import com.inuker.bluetooth.library.receiver.listener.BleConnectStatusChangeListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/BleConnectStatusChangeReceiver.class */
public class BleConnectStatusChangeReceiver extends AbsBluetoothReceiver {
    private static final String[] ACTIONS = {"action.connect_status_changed"};

    protected BleConnectStatusChangeReceiver(IReceiverDispatcher dispatcher) {
        super(dispatcher);
    }

    public static BleConnectStatusChangeReceiver newInstance(IReceiverDispatcher dispatcher) {
        return new BleConnectStatusChangeReceiver(dispatcher);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    List<String> getActions() {
        return Arrays.asList(ACTIONS);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    boolean onReceive(Context context, Intent intent) {
        String mac = intent.getStringExtra("extra.mac");
        int status = intent.getIntExtra("extra.status", 0);
        BluetoothLog.v(String.format("onConnectStatusChanged for %s, status = %d", mac, Integer.valueOf(status)));
        onConnectStatusChanged(mac, status);
        return true;
    }

    private void onConnectStatusChanged(String mac, int status) {
        List<BluetoothReceiverListener> listeners = getListeners(BleConnectStatusChangeListener.class);
        for (BluetoothReceiverListener listener : listeners) {
            listener.invoke(mac, Integer.valueOf(status));
        }
    }
}
