package com.inuker.bluetooth.library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.inuker.bluetooth.library.receiver.listener.BluetoothReceiverListener;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/receiver/BluetoothReceiver.class */
public class BluetoothReceiver extends BroadcastReceiver implements IBluetoothReceiver, Handler.Callback {
    private static final int MSG_REGISTER = 1;
    private static IBluetoothReceiver mReceiver;
    private final IReceiverDispatcher mDispatcher = new IReceiverDispatcher() { // from class: com.inuker.bluetooth.library.receiver.BluetoothReceiver.1
        @Override // com.inuker.bluetooth.library.receiver.IReceiverDispatcher
        public List<BluetoothReceiverListener> getListeners(Class<?> clazz) {
            return BluetoothReceiver.this.mListeners.get(clazz.getSimpleName());
        }
    };
    private final AbsBluetoothReceiver[] RECEIVERS = {BluetoothStateReceiver.newInstance(this.mDispatcher), BluetoothBondReceiver.newInstance(this.mDispatcher), BleConnectStatusChangeReceiver.newInstance(this.mDispatcher)};
    private Map<String, List<BluetoothReceiverListener>> mListeners = new HashMap();
    private Handler mHandler = new Handler(Looper.getMainLooper(), this);

    public static IBluetoothReceiver getInstance() {
        if (mReceiver == null) {
            synchronized (BluetoothReceiver.class) {
                if (mReceiver == null) {
                    mReceiver = new BluetoothReceiver();
                }
            }
        }
        return mReceiver;
    }

    private BluetoothReceiver() {
        BluetoothUtils.registerReceiver(this, getIntentFilter());
    }

    private IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        for (AbsBluetoothReceiver receiver : this.RECEIVERS) {
            List<String> actions = receiver.getActions();
            for (String action : actions) {
                filter.addAction(action);
            }
        }
        return filter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        BluetoothLog.v(String.format("BluetoothReceiver onReceive: %s", action));
        for (AbsBluetoothReceiver receiver : this.RECEIVERS) {
            if (receiver.containsAction(action) && receiver.onReceive(context, intent)) {
                return;
            }
        }
    }

    @Override // com.inuker.bluetooth.library.receiver.IBluetoothReceiver
    public void register(BluetoothReceiverListener listener) {
        this.mHandler.obtainMessage(1, listener).sendToTarget();
    }

    private void registerInner(BluetoothReceiverListener listener) {
        if (listener != null) {
            List<BluetoothReceiverListener> listeners = this.mListeners.get(listener.getName());
            if (listeners == null) {
                listeners = new LinkedList();
                this.mListeners.put(listener.getName(), listeners);
            }
            listeners.add(listener);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == 1) {
            registerInner((BluetoothReceiverListener) msg.obj);
            return true;
        }
        return true;
    }
}
