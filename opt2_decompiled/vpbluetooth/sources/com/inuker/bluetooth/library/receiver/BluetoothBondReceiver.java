package com.inuker.bluetooth.library.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondStateChangeListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/BluetoothBondReceiver.class */
public class BluetoothBondReceiver extends AbsBluetoothReceiver {
    private static final String[] ACTIONS = {"android.bluetooth.device.action.BOND_STATE_CHANGED"};

    protected BluetoothBondReceiver(IReceiverDispatcher dispatcher) {
        super(dispatcher);
    }

    public static BluetoothBondReceiver newInstance(IReceiverDispatcher dispatcher) {
        return new BluetoothBondReceiver(dispatcher);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    List<String> getActions() {
        return Arrays.asList(ACTIONS);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    boolean onReceive(Context context, Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int state = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
        if (device != null) {
            onBondStateChanged(device.getAddress(), state);
            return true;
        }
        return true;
    }

    private void onBondStateChanged(String mac, int bondState) {
        List<BluetoothReceiverListener> listeners = getListeners(BluetoothBondStateChangeListener.class);
        for (BluetoothReceiverListener listener : listeners) {
            listener.invoke(mac, Integer.valueOf(bondState));
        }
    }
}
