package com.inuker.bluetooth.library.receiver;

import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/IReceiverDispatcher.class */
public interface IReceiverDispatcher {
    List<BluetoothReceiverListener> getListeners(Class<?> cls);
}
