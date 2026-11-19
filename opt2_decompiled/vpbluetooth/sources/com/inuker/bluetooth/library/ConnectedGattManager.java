package com.inuker.bluetooth.library;

import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/ConnectedGattManager.class */
public class ConnectedGattManager {
    private ConcurrentHashMap<String, BluetoothGatt> mGatts = new ConcurrentHashMap<>();

    private ConnectedGattManager() {
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/ConnectedGattManager$Holder.class */
    private static class Holder {
        private static final ConnectedGattManager INSTANCE = new ConnectedGattManager();

        private Holder() {
        }
    }

    public static ConnectedGattManager getInstance() {
        return Holder.INSTANCE;
    }

    public void putConnectedGatt(BluetoothGatt gatt) {
        if (gatt != null) {
            System.out.println("=========> putConnectedGatt <========= Gatt is " + gatt.getDevice().toString());
            String address = gatt.getDevice().getAddress();
            this.mGatts.put(address, gatt);
            return;
        }
        System.out.println("=========> putConnectedGatt <========= Gatt is null ");
    }

    public void removeConnectGatt(BluetoothGatt gatt) {
        if (gatt != null) {
            String address = gatt.getDevice().getAddress();
            System.out.println("=========> removeConnectGatt <========= Gatt is " + address);
            this.mGatts.remove(address);
            return;
        }
        System.out.println("=========> removeConnectGatt <========= Gatt is null ");
    }

    public BluetoothGatt getConnectedGatt(String mac) {
        if (!TextUtils.isEmpty(mac)) {
            return this.mGatts.get(mac);
        }
        return null;
    }

    public void removeGatt(String mac) {
        if (!TextUtils.isEmpty(mac)) {
            this.mGatts.remove(mac);
        }
    }

    public void clearGatts() {
        this.mGatts.clear();
    }
}
