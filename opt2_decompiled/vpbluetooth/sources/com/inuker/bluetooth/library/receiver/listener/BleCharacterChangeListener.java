package com.inuker.bluetooth.library.receiver.listener;

import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/listener/BleCharacterChangeListener.class */
public abstract class BleCharacterChangeListener extends BluetoothReceiverListener {
    protected abstract void onCharacterChanged(String str, UUID uuid, UUID uuid2, byte[] bArr);

    @Override // com.inuker.bluetooth.library.receiver.listener.AbsBluetoothListener
    public void onInvoke(Object... args) {
        String mac = (String) args[0];
        UUID service = (UUID) args[1];
        UUID character = (UUID) args[2];
        byte[] value = (byte[]) args[3];
        onCharacterChanged(mac, service, character, value);
    }

    @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener
    public String getName() {
        return BleCharacterChangeListener.class.getSimpleName();
    }
}
