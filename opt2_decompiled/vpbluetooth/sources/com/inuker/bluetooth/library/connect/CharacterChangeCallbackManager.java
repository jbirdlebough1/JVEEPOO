package com.inuker.bluetooth.library.connect;

import com.inuker.bluetooth.library.connect.listener.ICharacterChangeCallback;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/CharacterChangeCallbackManager.class */
public class CharacterChangeCallbackManager {
    private static final String TAG = "CharacterChangeCallbackManager";
    private static volatile CharacterChangeCallbackManager sInstance;
    private volatile ICharacterChangeCallback mCallback;

    private CharacterChangeCallbackManager() {
    }

    public static CharacterChangeCallbackManager getInstance() {
        if (sInstance == null) {
            synchronized (CharacterChangeCallbackManager.class) {
                if (sInstance == null) {
                    sInstance = new CharacterChangeCallbackManager();
                }
            }
        }
        return sInstance;
    }

    public void registerCallback(ICharacterChangeCallback callback) {
        BluetoothLog.v("CharacterChangeCallbackManager registerCallback");
        this.mCallback = callback;
    }

    public void unregisterCallback() {
        BluetoothLog.v("CharacterChangeCallbackManager unregisterCallback");
        this.mCallback = null;
    }

    public void onCharacterChanged(String mac, UUID service, UUID character, byte[] value) {
        ICharacterChangeCallback callback = this.mCallback;
        if (callback != null) {
            callback.onCharacterChanged(mac, service, character, value);
        } else {
            BluetoothLog.w("CharacterChangeCallbackManager callback is null, data notification dropped for " + mac);
        }
    }
}
