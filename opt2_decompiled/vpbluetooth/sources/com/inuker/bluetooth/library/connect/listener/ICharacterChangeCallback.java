package com.inuker.bluetooth.library.connect.listener;

import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/ICharacterChangeCallback.class */
public interface ICharacterChangeCallback {
    void onCharacterChanged(String str, UUID uuid, UUID uuid2, byte[] bArr);
}
