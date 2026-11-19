package com.inuker.bluetooth.library;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.inuker.bluetooth.library.connect.BleConnectManager;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.search.BluetoothSearchManager;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/BluetoothServiceImpl.class */
public class BluetoothServiceImpl extends IBluetoothService.Stub implements Handler.Callback {
    private static BluetoothServiceImpl sInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), this);

    private BluetoothServiceImpl() {
    }

    public static BluetoothServiceImpl getInstance() {
        if (sInstance == null) {
            synchronized (BluetoothServiceImpl.class) {
                if (sInstance == null) {
                    sInstance = new BluetoothServiceImpl();
                }
            }
        }
        return sInstance;
    }

    @Override // com.inuker.bluetooth.library.IBluetoothService
    public void callBluetoothApi(int code, Bundle args, final IResponse response) throws RemoteException {
        Message msg = this.mHandler.obtainMessage(code, new BleGeneralResponse() { // from class: com.inuker.bluetooth.library.BluetoothServiceImpl.1
            @Override // com.inuker.bluetooth.library.connect.response.BleTResponse
            public void onResponse(int code2, Bundle data) {
                if (response != null) {
                    if (data == null) {
                        data = new Bundle();
                    }
                    try {
                        response.onResponse(code2, data);
                    } catch (Throwable e) {
                        BluetoothLog.e(e);
                    }
                }
            }
        });
        args.setClassLoader(getClass().getClassLoader());
        msg.setData(args);
        msg.sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        Bundle args = msg.getData();
        String mac = args.getString("extra.mac");
        UUID service = (UUID) args.getSerializable("extra.service.uuid");
        UUID character = (UUID) args.getSerializable("extra.character.uuid");
        UUID descriptor = (UUID) args.getSerializable(Constants.EXTRA_DESCRIPTOR_UUID);
        byte[] value = args.getByteArray("extra.byte.value");
        BleGeneralResponse response = (BleGeneralResponse) msg.obj;
        switch (msg.what) {
            case 1:
                BleConnectOptions options = (BleConnectOptions) args.getParcelable("extra.options");
                BleConnectManager.connect(mac, options, response);
                break;
            case 2:
                BleConnectManager.disconnect(mac);
                break;
            case 3:
                BleConnectManager.read(mac, service, character, response);
                break;
            case 4:
                BleConnectManager.write(mac, service, character, value, response);
                break;
            case 5:
                BleConnectManager.writeNoRsp(mac, service, character, value, response);
                break;
            case 6:
                BleConnectManager.notify(mac, service, character, response);
                break;
            case 7:
                BleConnectManager.unnotify(mac, service, character, response);
                break;
            case 8:
                BleConnectManager.readRssi(mac, response);
                break;
            case 10:
                BleConnectManager.indicate(mac, service, character, response);
                break;
            case 11:
                SearchRequest request = (SearchRequest) args.getParcelable("extra.request");
                BluetoothSearchManager.search(request, response);
                break;
            case 12:
                BluetoothSearchManager.stopSearch();
                break;
            case 13:
                BleConnectManager.readDescriptor(mac, service, character, descriptor, response);
                break;
            case Constants.CODE_WRITE_DESCRIPTOR /* 14 */:
                BleConnectManager.writeDescriptor(mac, service, character, descriptor, value, response);
                break;
            case Constants.CODE_CLEAR_REQUEST /* 20 */:
                int clearType = args.getInt(Constants.EXTRA_TYPE, 0);
                BleConnectManager.clearRequest(mac, clearType);
                break;
            case Constants.CODE_REFRESH_CACHE /* 21 */:
                BleConnectManager.refreshCache(mac);
                break;
            case Constants.CODE_REQUEST_MTU /* 22 */:
                int mtu = args.getInt(Constants.EXTRA_MTU);
                BleConnectManager.requestMtu(mac, mtu, response);
                break;
            case 23:
                BleConnectManager.startJLDeviceAuth(mac, response);
                break;
        }
        return true;
    }
}
