package com.inuker.bluetooth.library.receiver;

import android.content.Context;
import android.content.Intent;
import com.inuker.bluetooth.library.receiver.listener.BleCharacterChangeListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/BleCharacterChangeReceiver.class */
@Deprecated
public class BleCharacterChangeReceiver extends AbsBluetoothReceiver {
    private static final String[] ACTIONS = {"action.character_changed"};

    protected BleCharacterChangeReceiver(IReceiverDispatcher dispatcher) {
        super(dispatcher);
    }

    public static BleCharacterChangeReceiver newInstance(IReceiverDispatcher dispatcher) {
        return new BleCharacterChangeReceiver(dispatcher);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    List<String> getActions() {
        return Arrays.asList(ACTIONS);
    }

    @Override // com.inuker.bluetooth.library.receiver.AbsBluetoothReceiver
    boolean onReceive(Context context, Intent intent) {
        String mac = intent.getStringExtra("extra.mac");
        UUID service = (UUID) intent.getSerializableExtra("extra.service.uuid");
        UUID character = (UUID) intent.getSerializableExtra("extra.character.uuid");
        byte[] value = intent.getByteArrayExtra("extra.byte.value");
        onCharacterChanged(mac, service, character, value);
        return true;
    }

    private void onCharacterChanged(String mac, UUID service, UUID character, byte[] value) {
        List<BluetoothReceiverListener> listeners = getListeners(BleCharacterChangeListener.class);
        for (BluetoothReceiverListener listener : listeners) {
            listener.invoke(mac, service, character, value);
        }
    }
}
