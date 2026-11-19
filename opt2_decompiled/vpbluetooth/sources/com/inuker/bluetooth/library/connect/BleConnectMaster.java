package com.inuker.bluetooth.library.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.utils.proxy.ProxyBulk;
import com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.lang.reflect.Method;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/BleConnectMaster.class */
public class BleConnectMaster implements IBleConnectMaster, ProxyInterceptor, Handler.Callback {
    private Handler mHandler;
    private String mAddress;
    private BleConnectDispatcher mBleConnectDispatcher;

    private BleConnectMaster(String mac, Looper looper) {
        this.mAddress = mac;
        this.mHandler = new Handler(looper, this);
    }

    private BleConnectDispatcher getConnectDispatcher() {
        if (this.mBleConnectDispatcher == null) {
            this.mBleConnectDispatcher = BleConnectDispatcher.newInstance(this.mAddress);
        }
        return this.mBleConnectDispatcher;
    }

    static IBleConnectMaster newInstance(String mac, Looper looper) {
        BleConnectMaster master = new BleConnectMaster(mac, looper);
        return (IBleConnectMaster) ProxyUtils.getProxy(master, IBleConnectMaster.class, master);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void connect(BleConnectOptions options, BleGeneralResponse response) {
        getConnectDispatcher().connect(options, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void disconnect() {
        getConnectDispatcher().disconnect();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void read(UUID service, UUID character, BleGeneralResponse response) {
        getConnectDispatcher().read(service, character, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void write(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        getConnectDispatcher().write(service, character, bytes, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void writeNoRsp(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        getConnectDispatcher().writeNoRsp(service, character, bytes, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void readDescriptor(UUID service, UUID character, UUID descriptor, BleGeneralResponse response) {
        getConnectDispatcher().readDescriptor(service, character, descriptor, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void writeDescriptor(UUID service, UUID character, UUID descriptor, byte[] value, BleGeneralResponse response) {
        getConnectDispatcher().writeDescriptor(service, character, descriptor, value, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void notify(UUID service, UUID character, BleGeneralResponse response) {
        getConnectDispatcher().notify(service, character, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void unnotify(UUID service, UUID character, BleGeneralResponse response) {
        getConnectDispatcher().unnotify(service, character, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void readRssi(BleGeneralResponse response) {
        getConnectDispatcher().readRemoteRssi(response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void indicate(UUID service, UUID character, BleGeneralResponse response) {
        getConnectDispatcher().indicate(service, character, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void requestMtu(int mtu, BleGeneralResponse response) {
        getConnectDispatcher().requestMtu(mtu, response);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void clearRequest(int clearType) {
        getConnectDispatcher().clearRequest(clearType);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void refreshCache() {
        getConnectDispatcher().refreshCache();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectMaster
    public void startJLDeviceAuth(BleGeneralResponse response) {
        getConnectDispatcher().startJLDeviceAuth(response);
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        this.mHandler.obtainMessage(0, new ProxyBulk(object, method, args)).sendToTarget();
        return true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        ProxyBulk.safeInvoke(msg.obj);
        return true;
    }
}
