package com.inuker.bluetooth.library;

import android.bluetooth.BluetoothGatt;
import android.content.Context;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.listener.BluetoothStateListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleMtuResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleReadResponse;
import com.inuker.bluetooth.library.connect.response.BleReadRssiResponse;
import com.inuker.bluetooth.library.connect.response.BleUnnotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.jieli.response.RcspAuthResponse;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondListener;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import com.inuker.bluetooth.library.utils.ByteUtils;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/BluetoothClient.class */
public class BluetoothClient implements IBluetoothClient {
    private IBluetoothClient mClient;

    public BluetoothClient(Context context) {
        if (context == null) {
            throw new NullPointerException("Context null");
        }
        this.mClient = BluetoothClientImpl.getInstance(context);
    }

    public void connect(String mac, BleConnectResponse response) {
        connect(mac, null, response);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void connect(String mac, BleConnectOptions options, BleConnectResponse response) {
        BluetoothLog.v(String.format("connect %s", mac));
        this.mClient.connect(mac, options, (BleConnectResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void disconnect(String mac) {
        BluetoothLog.v(String.format("disconnect %s", mac));
        this.mClient.disconnect(mac);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void read(String mac, UUID service, UUID character, BleReadResponse response) {
        BluetoothLog.v(String.format("read character for %s: service = %s, character = %s", mac, service, character));
        this.mClient.read(mac, service, character, (BleReadResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void write(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response) {
        BluetoothLog.v(String.format("write character for %s: service = %s, character = %s, value = %s", mac, service, character, ByteUtils.byteToString(value)));
        this.mClient.write(mac, service, character, value, (BleWriteResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void readDescriptor(String mac, UUID service, UUID character, UUID descriptor, BleReadResponse response) {
        BluetoothLog.v(String.format("readDescriptor for %s: service = %s, character = %s", mac, service, character));
        this.mClient.readDescriptor(mac, service, character, descriptor, (BleReadResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void writeDescriptor(String mac, UUID service, UUID character, UUID descriptor, byte[] value, BleWriteResponse response) {
        BluetoothLog.v(String.format("writeDescriptor for %s: service = %s, character = %s", mac, service, character));
        this.mClient.writeDescriptor(mac, service, character, descriptor, value, (BleWriteResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void writeNoRsp(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response) {
        BluetoothLog.v(String.format("writeNoRsp %s: service = %s, character = %s, value = %s", mac, service, character, ByteUtils.byteToString(value)));
        this.mClient.writeNoRsp(mac, service, character, value, (BleWriteResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void notify(String mac, UUID service, UUID character, BleNotifyResponse response) {
        BluetoothLog.v(String.format("notify %s: service = %s, character = %s", mac, service, character));
        this.mClient.notify(mac, service, character, (BleNotifyResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unnotify(String mac, UUID service, UUID character, BleUnnotifyResponse response) {
        BluetoothLog.v(String.format("unnotify %s: service = %s, character = %s", mac, service, character));
        this.mClient.unnotify(mac, service, character, (BleUnnotifyResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void indicate(String mac, UUID service, UUID character, BleNotifyResponse response) {
        BluetoothLog.v(String.format("indicate %s: service = %s, character = %s", mac, service, character));
        this.mClient.indicate(mac, service, character, (BleNotifyResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unindicate(String mac, UUID service, UUID character, BleUnnotifyResponse response) {
        BluetoothLog.v(String.format("indicate %s: service = %s, character = %s", mac, service, character));
        this.mClient.unindicate(mac, service, character, (BleUnnotifyResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void readRssi(String mac, BleReadRssiResponse response) {
        BluetoothLog.v(String.format("readRssi %s", mac));
        this.mClient.readRssi(mac, (BleReadRssiResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void requestMtu(String mac, int mtu, BleMtuResponse response) {
        BluetoothLog.v(String.format("requestMtu %s", mac));
        this.mClient.requestMtu(mac, mtu, (BleMtuResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void search(SearchRequest request, SearchResponse response) {
        BluetoothLog.v(String.format("search %s", request));
        this.mClient.search(request, (SearchResponse) ProxyUtils.getUIProxy(response));
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void stopSearch() {
        BluetoothLog.v(String.format("stopSearch", new Object[0]));
        this.mClient.stopSearch();
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerConnectStatusListener(String mac, BleConnectStatusListener listener) {
        this.mClient.registerConnectStatusListener(mac, listener);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterConnectStatusListener(String mac, BleConnectStatusListener listener) {
        this.mClient.unregisterConnectStatusListener(mac, listener);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerBluetoothStateListener(BluetoothStateListener listener) {
        this.mClient.registerBluetoothStateListener(listener);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterBluetoothStateListener(BluetoothStateListener listener) {
        this.mClient.unregisterBluetoothStateListener(listener);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void registerBluetoothBondListener(BluetoothBondListener listener) {
        this.mClient.registerBluetoothBondListener(listener);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void unregisterBluetoothBondListener(BluetoothBondListener listener) {
        this.mClient.unregisterBluetoothBondListener(listener);
    }

    public int getConnectStatus(String mac) {
        return BluetoothUtils.getConnectStatus(mac);
    }

    public boolean isBluetoothOpened() {
        return BluetoothUtils.isBluetoothEnabled();
    }

    public boolean openBluetooth() {
        return BluetoothUtils.openBluetooth();
    }

    public boolean closeBluetooth() {
        return BluetoothUtils.closeBluetooth();
    }

    public boolean isBleSupported() {
        return BluetoothUtils.isBleSupported();
    }

    public int getBondState(String mac) {
        return BluetoothUtils.getBondState(mac);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void clearRequest(String mac, int type) {
        this.mClient.clearRequest(mac, type);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void refreshCache(String mac) {
        this.mClient.refreshCache(mac);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public void startJLDeviceAuth(String mac, RcspAuthResponse response) {
        this.mClient.startJLDeviceAuth(mac, response);
    }

    @Override // com.inuker.bluetooth.library.IBluetoothClient
    public BluetoothGatt getGatt(String mac) {
        return ConnectedGattManager.getInstance().getConnectedGatt(mac);
    }
}
