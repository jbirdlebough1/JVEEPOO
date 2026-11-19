package com.inuker.bluetooth.library;

import android.bluetooth.BluetoothGatt;
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
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothClient.class */
public interface IBluetoothClient {
    void connect(String str, BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse);

    void disconnect(String str);

    void registerConnectStatusListener(String str, BleConnectStatusListener bleConnectStatusListener);

    void unregisterConnectStatusListener(String str, BleConnectStatusListener bleConnectStatusListener);

    void read(String str, UUID uuid, UUID uuid2, BleReadResponse bleReadResponse);

    void write(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void readDescriptor(String str, UUID uuid, UUID uuid2, UUID uuid3, BleReadResponse bleReadResponse);

    void writeDescriptor(String str, UUID uuid, UUID uuid2, UUID uuid3, byte[] bArr, BleWriteResponse bleWriteResponse);

    void writeNoRsp(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void notify(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void unnotify(String str, UUID uuid, UUID uuid2, BleUnnotifyResponse bleUnnotifyResponse);

    void indicate(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void unindicate(String str, UUID uuid, UUID uuid2, BleUnnotifyResponse bleUnnotifyResponse);

    void readRssi(String str, BleReadRssiResponse bleReadRssiResponse);

    void requestMtu(String str, int i, BleMtuResponse bleMtuResponse);

    void search(SearchRequest searchRequest, SearchResponse searchResponse);

    void stopSearch();

    void registerBluetoothStateListener(BluetoothStateListener bluetoothStateListener);

    void unregisterBluetoothStateListener(BluetoothStateListener bluetoothStateListener);

    void registerBluetoothBondListener(BluetoothBondListener bluetoothBondListener);

    void unregisterBluetoothBondListener(BluetoothBondListener bluetoothBondListener);

    void clearRequest(String str, int i);

    void refreshCache(String str);

    void startJLDeviceAuth(String str, RcspAuthResponse rcspAuthResponse);

    BluetoothGatt getGatt(String str);
}
