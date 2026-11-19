package com.inuker.bluetooth.library;

import android.bluetooth.BluetoothGatt;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.inuker.bluetooth.library.connect.CharacterChangeCallbackManager;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.listener.BluetoothStateListener;
import com.inuker.bluetooth.library.connect.listener.ICharacterChangeCallback;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleMtuResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleReadResponse;
import com.inuker.bluetooth.library.connect.response.BleReadRssiResponse;
import com.inuker.bluetooth.library.connect.response.BleUnnotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.connect.response.BluetoothResponse;
import com.inuker.bluetooth.library.jieli.response.RcspAuthResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.receiver.BluetoothReceiver;
import com.inuker.bluetooth.library.receiver.listener.BleConnectStatusChangeListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondStateChangeListener;
import com.inuker.bluetooth.library.receiver.listener.BluetoothStateChangeListener;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.ListUtils;
import com.inuker.bluetooth.library.utils.proxy.ProxyBulk;
import com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/BluetoothClientImpl.class */
public class BluetoothClientImpl implements IBluetoothClient, ProxyInterceptor, Handler.Callback, ICharacterChangeCallback {
    private static final int MSG_INVOKE_PROXY = 1;
    private static final int MSG_REG_RECEIVER = 2;
    private static final String TAG = BluetoothClientImpl.class.getSimpleName();
    private Context mContext;
    private volatile IBluetoothService mBluetoothService;
    private static volatile IBluetoothClient sInstance;
    private CountDownLatch mCountDownLatch;
    private final HandlerThread mWorkerThread;
    private final Handler mWorkerHandler;
    private HashMap<String, HashMap<String, List<BleNotifyResponse>>> mNotifyResponses;
    private HashMap<String, List<BleConnectStatusListener>> mConnectStatusListeners;
    private List<BluetoothStateListener> mBluetoothStateListeners;
    private List<BluetoothBondListener> mBluetoothBondListeners;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            BluetoothLog.v("onServiceConnected");
            BluetoothClientImpl.this.mBluetoothService = IBluetoothService.Stub.asInterface(service);
            BluetoothClientImpl.this.notifyBluetoothManagerReady();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            BluetoothLog.v("onServiceDisconnected");
            BluetoothClientImpl.this.mBluetoothService = null;
        }
    };

    private BluetoothClientImpl(Context context) {
        this.mContext = context.getApplicationContext();
        BluetoothContext.set(this.mContext);
        this.mWorkerThread = new HandlerThread(TAG);
        this.mWorkerThread.start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper(), this);
        this.mNotifyResponses = new HashMap<>();
        this.mConnectStatusListeners = new HashMap<>();
        this.mBluetoothStateListeners = new LinkedList();
        this.mBluetoothBondListeners = new LinkedList();
        this.mWorkerHandler.obtainMessage(2).sendToTarget();
        CharacterChangeCallbackManager.getInstance().registerCallback(this);
    }

    public static IBluetoothClient getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BluetoothClientImpl.class) {
                if (sInstance == null) {
                    BluetoothClientImpl client = new BluetoothClientImpl(context);
                    sInstance = (IBluetoothClient) ProxyUtils.getProxy(client, IBluetoothClient.class, client);
                }
            }
        }
        return sInstance;
    }

    private IBluetoothService getBluetoothService() {
        BluetoothLog.v("getBluetoothService");
        if (this.mBluetoothService == null) {
            bindServiceSync();
        }
        return this.mBluetoothService;
    }

    private void bindServiceSync() {
        checkRuntime(true);
        BluetoothLog.v("bindServiceSync");
        this.mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setClass(this.mContext, BluetoothService.class);
        if (this.mContext.bindService(intent, this.mConnection, 1)) {
            BluetoothLog.v("BluetoothService registered");
            waitBluetoothManagerReady();
        } else {
            BluetoothLog.v("BluetoothService not registered");
            this.mBluetoothService = BluetoothServiceImpl.getInstance();
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void connect(String mac, BleConnectOptions options, final BleConnectResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putParcelable("extra.options", options);
        safeCallBluetoothApi(1, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.2
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    data.setClassLoader(getClass().getClassLoader());
                    BleGattProfile profile = (BleGattProfile) data.getParcelable("extra.gatt.profile");
                    response.onResponse(code, profile);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void disconnect(String mac) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        safeCallBluetoothApi(2, args, null);
        clearNotifyListener(mac);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerConnectStatusListener(String mac, BleConnectStatusListener listener) {
        checkRuntime(true);
        List<BleConnectStatusListener> listeners = this.mConnectStatusListeners.get(mac);
        if (listeners == null) {
            listeners = new ArrayList();
            this.mConnectStatusListeners.put(mac, listeners);
        }
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
            Log.d(TAG + "", "registerConnectStatusListener: " + mac);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterConnectStatusListener(String mac, BleConnectStatusListener listener) {
        checkRuntime(true);
        List<BleConnectStatusListener> listeners = this.mConnectStatusListeners.get(mac);
        if (listener != null && !ListUtils.isEmpty(listeners)) {
            listeners.remove(listener);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void read(String mac, UUID service, UUID character, final BleReadResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        safeCallBluetoothApi(3, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.3
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code, data.getByteArray("extra.byte.value"));
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void write(String mac, UUID service, UUID character, byte[] value, final BleWriteResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        args.putByteArray("extra.byte.value", value);
        safeCallBluetoothApi(4, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.4
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void readDescriptor(String mac, UUID service, UUID character, UUID descriptor, final BleReadResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        args.putSerializable(Constants.EXTRA_DESCRIPTOR_UUID, descriptor);
        safeCallBluetoothApi(13, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.5
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code, data.getByteArray("extra.byte.value"));
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void writeDescriptor(String mac, UUID service, UUID character, UUID descriptor, byte[] value, final BleWriteResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        args.putSerializable(Constants.EXTRA_DESCRIPTOR_UUID, descriptor);
        args.putByteArray("extra.byte.value", value);
        safeCallBluetoothApi(14, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.6
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void writeNoRsp(String mac, UUID service, UUID character, byte[] value, final BleWriteResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        args.putByteArray("extra.byte.value", value);
        safeCallBluetoothApi(5, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.7
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code);
                }
            }
        });
    }

    private void saveNotifyListener(String mac, UUID service, UUID character, BleNotifyResponse response) {
        checkRuntime(true);
        HashMap<String, List<BleNotifyResponse>> listenerMap = this.mNotifyResponses.get(mac);
        if (listenerMap == null) {
            listenerMap = new HashMap<>();
            this.mNotifyResponses.put(mac, listenerMap);
        }
        String key = generateCharacterKey(service, character);
        List<BleNotifyResponse> responses = listenerMap.get(key);
        if (responses == null) {
            responses = new ArrayList();
            listenerMap.put(key, responses);
        }
        responses.add(response);
    }

    private void removeNotifyListener(String mac, UUID service, UUID character) {
        checkRuntime(true);
        HashMap<String, List<BleNotifyResponse>> listenerMap = this.mNotifyResponses.get(mac);
        if (listenerMap != null) {
            String key = generateCharacterKey(service, character);
            listenerMap.remove(key);
        }
    }

    private void clearNotifyListener(String mac) {
        checkRuntime(true);
        this.mNotifyResponses.remove(mac);
    }

    private String generateCharacterKey(UUID service, UUID character) {
        return String.format(Locale.CHINA, "%s_%s", service, character);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void notify(final String mac, final UUID service, final UUID character, final BleNotifyResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        safeCallBluetoothApi(6, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.8
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    if (code == 0) {
                        BluetoothClientImpl.this.saveNotifyListener(mac, service, character, response);
                    }
                    response.onResponse(code);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unnotify(final String mac, final UUID service, final UUID character, final BleUnnotifyResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        safeCallBluetoothApi(7, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.9
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                BluetoothClientImpl.this.removeNotifyListener(mac, service, character);
                if (response != null) {
                    response.onResponse(code);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void indicate(final String mac, final UUID service, final UUID character, final BleNotifyResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putSerializable("extra.service.uuid", service);
        args.putSerializable("extra.character.uuid", character);
        safeCallBluetoothApi(10, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.10
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    if (code == 0) {
                        BluetoothClientImpl.this.saveNotifyListener(mac, service, character, response);
                    }
                    response.onResponse(code);
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unindicate(String mac, UUID service, UUID character, BleUnnotifyResponse response) {
        unnotify(mac, service, character, response);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void readRssi(String mac, final BleReadRssiResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        safeCallBluetoothApi(8, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.11
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code, Integer.valueOf(data.getInt("extra.rssi", 0)));
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void requestMtu(String mac, int mtu, final BleMtuResponse response) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putInt(Constants.EXTRA_MTU, mtu);
        safeCallBluetoothApi(22, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.12
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response != null) {
                    response.onResponse(code, Integer.valueOf(data.getInt(Constants.EXTRA_MTU, 23)));
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void startJLDeviceAuth(String mac, final RcspAuthResponse response) {
        if (response == null) {
            BluetoothLog.e("设备认证接口不能为空");
            return;
        }
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        response.onRcspAuthStart();
        safeCallBluetoothApi(23, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.13
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                BluetoothLog.e("-杰理- 设备认证 code = " + code);
                switch (code) {
                    case -1:
                        response.onRcspAuthFailed();
                        break;
                    case 0:
                        response.onRcspAuthSuccess();
                        break;
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public BluetoothGatt getGatt(String mac) {
        return sInstance.getGatt(mac);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void search(SearchRequest request, final SearchResponse response) {
        Bundle args = new Bundle();
        args.putParcelable("extra.request", request);
        safeCallBluetoothApi(11, args, new BluetoothResponse() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.14
            @Override // com.inuker.bluetooth.library.connect.response.BluetoothResponse
            protected void onAsyncResponse(int code, Bundle data) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (response == null) {
                    return;
                }
                data.setClassLoader(getClass().getClassLoader());
                switch (code) {
                    case 1:
                        response.onSearchStarted();
                        return;
                    case 2:
                        response.onSearchStopped();
                        return;
                    case 3:
                        response.onSearchCanceled();
                        return;
                    case 4:
                        SearchResult device = (SearchResult) data.getParcelable("extra.search.result");
                        response.onDeviceFounded(device);
                        return;
                    default:
                        throw new IllegalStateException("unknown code");
                }
            }
        });
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void stopSearch() {
        safeCallBluetoothApi(12, null, null);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerBluetoothStateListener(BluetoothStateListener listener) {
        checkRuntime(true);
        if (listener != null && !this.mBluetoothStateListeners.contains(listener)) {
            this.mBluetoothStateListeners.add(listener);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterBluetoothStateListener(BluetoothStateListener listener) {
        checkRuntime(true);
        if (listener != null) {
            this.mBluetoothStateListeners.remove(listener);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerBluetoothBondListener(BluetoothBondListener listener) {
        checkRuntime(true);
        if (listener != null && !this.mBluetoothBondListeners.contains(listener)) {
            this.mBluetoothBondListeners.add(listener);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterBluetoothBondListener(BluetoothBondListener listener) {
        checkRuntime(true);
        if (listener != null) {
            this.mBluetoothBondListeners.remove(listener);
        }
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void clearRequest(String mac, int type) {
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        args.putInt(Constants.EXTRA_TYPE, type);
        safeCallBluetoothApi(20, args, null);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void refreshCache(String mac) {
        checkRuntime(true);
        Bundle args = new Bundle();
        args.putString("extra.mac", mac);
        safeCallBluetoothApi(21, args, null);
    }

    private void safeCallBluetoothApi(int code, Bundle args, BluetoothResponse response) {
        checkRuntime(true);
        BluetoothLog.v(String.format(Locale.CHINA, "safeCallBluetoothApi code = %d", Integer.valueOf(code)));
        try {
            IBluetoothService service = getBluetoothService();
            BluetoothLog.v(String.format(Locale.CHINA, "IBluetoothService = %s", service));
            if (service != null) {
                service.callBluetoothApi(code, args != null ? args : new Bundle(), response);
            } else {
                response.onResponse(-6, null);
            }
        } catch (Throwable e) {
            BluetoothLog.e(e);
        }
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        this.mWorkerHandler.obtainMessage(1, new ProxyBulk(object, method, args)).sendToTarget();
        return true;
    }

    private void notifyBluetoothManagerReady() {
        if (this.mCountDownLatch != null) {
            this.mCountDownLatch.countDown();
            this.mCountDownLatch = null;
        }
    }

    private void waitBluetoothManagerReady() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                ProxyBulk.safeInvoke(msg.obj);
                break;
            case 2:
                registerBluetoothReceiver();
                break;
        }
        return true;
    }

    private void registerBluetoothReceiver() {
        checkRuntime(true);
        BluetoothReceiver.getInstance().register(new BluetoothStateChangeListener() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.15
            @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothStateChangeListener
            protected void onBluetoothStateChanged(int prevState, int curState) {
                BluetoothClientImpl.this.checkRuntime(true);
                BluetoothClientImpl.this.dispatchBluetoothStateChanged(curState);
            }
        });
        BluetoothReceiver.getInstance().register(new BluetoothBondStateChangeListener() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.16
            @Override // com.inuker.bluetooth.library.receiver.listener.BluetoothBondStateChangeListener
            protected void onBondStateChanged(String mac, int bondState) {
                BluetoothClientImpl.this.checkRuntime(true);
                BluetoothClientImpl.this.dispatchBondStateChanged(mac, bondState);
            }
        });
        BluetoothReceiver.getInstance().register(new BleConnectStatusChangeListener() { // from class: com.inuker.bluetooth.library.BluetoothClientImpl.17
            @Override // com.inuker.bluetooth.library.receiver.listener.BleConnectStatusChangeListener
            protected void onConnectStatusChanged(String mac, int status) {
                BluetoothClientImpl.this.checkRuntime(true);
                if (status == 32) {
                    BluetoothClientImpl.this.clearNotifyListener(mac);
                }
                BluetoothClientImpl.this.dispatchConnectionStatus(mac, status);
            }
        });
    }

    @Override // com.inuker.bluetooth.library.connect.listener.ICharacterChangeCallback
    public void onCharacterChanged(String mac, UUID service, UUID character, byte[] value) {
        dispatchCharacterNotify(mac, service, character, value);
    }

    private void dispatchCharacterNotify(String mac, UUID service, UUID character, byte[] value) {
        HashMap<String, List<BleNotifyResponse>> notifyMap = this.mNotifyResponses.get(mac);
        if (notifyMap != null) {
            String key = generateCharacterKey(service, character);
            List<BleNotifyResponse> responses = notifyMap.get(key);
            if (responses != null) {
                for (BleNotifyResponse response : responses) {
                    response.onNotify(service, character, value);
                }
            }
        }
    }

    private void dispatchConnectionStatus(String mac, int status) {
        checkRuntime(true);
        List<BleConnectStatusListener> listeners = this.mConnectStatusListeners.get(mac);
        if (!ListUtils.isEmpty(listeners)) {
            for (BleConnectStatusListener listener : listeners) {
                listener.invokeSync(mac, Integer.valueOf(status));
            }
        }
    }

    private void dispatchBluetoothStateChanged(int currentState) {
        checkRuntime(true);
        if (currentState == 10 || currentState == 12) {
            for (BluetoothStateListener listener : this.mBluetoothStateListeners) {
                Object[] objArr = new Object[1];
                objArr[0] = Boolean.valueOf(currentState == 12);
                listener.invokeSync(objArr);
            }
        }
    }

    private void dispatchBondStateChanged(String mac, int bondState) {
        checkRuntime(true);
        for (BluetoothBondListener listener : this.mBluetoothBondListeners) {
            listener.invokeSync(mac, Integer.valueOf(bondState));
        }
    }

    private void checkRuntime(boolean async) {
        Looper targetLooper = async ? this.mWorkerHandler.getLooper() : Looper.getMainLooper();
        if (Looper.myLooper() != targetLooper) {
            throw new RuntimeException();
        }
    }
}
