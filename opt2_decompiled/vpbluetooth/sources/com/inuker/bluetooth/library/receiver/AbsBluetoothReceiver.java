package com.inuker.bluetooth.library.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import com.inuker.bluetooth.library.utils.ListUtils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/AbsBluetoothReceiver.class */
public abstract class AbsBluetoothReceiver {
    protected Context mContext = BluetoothContext.get();
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected IReceiverDispatcher mDispatcher;

    abstract List<String> getActions();

    abstract boolean onReceive(Context context, Intent intent);

    protected AbsBluetoothReceiver(IReceiverDispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    boolean containsAction(String action) {
        List<String> actions = getActions();
        if (!ListUtils.isEmpty(actions) && !TextUtils.isEmpty(action)) {
            return actions.contains(action);
        }
        return false;
    }

    protected List<BluetoothReceiverListener> getListeners(Class<?> clazz) {
        List<BluetoothReceiverListener> listeners = this.mDispatcher.getListeners(clazz);
        return listeners != null ? listeners : Collections.EMPTY_LIST;
    }
}
