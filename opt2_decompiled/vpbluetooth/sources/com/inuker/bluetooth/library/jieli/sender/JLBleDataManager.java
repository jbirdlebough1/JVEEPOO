package com.inuker.bluetooth.library.jieli.sender;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.util.Log;
import com.inuker.bluetooth.library.connect.BleConnectWorker;
import com.inuker.bluetooth.library.jieli.JLConstants;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/JLBleDataManager.class */
public class JLBleDataManager implements IBleOp {
    private static final String TAG = JLBleDataManager.class.getSimpleName() + "-杰理-";
    private BluetoothGatt gatt;
    private BluetoothDevice device;
    private int mtu;
    private SendBleDataThread mSendBleDataThread;
    private BleConnectWorker worker;
    private final OnWriteDataCallback callback = new OnWriteDataCallback() { // from class: com.inuker.bluetooth.library.jieli.sender.JLBleDataManager.1
        @Override // com.inuker.bluetooth.library.jieli.sender.OnWriteDataCallback
        public void onBleResult(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, boolean result, byte[] data) {
        }
    };

    @Override // com.inuker.bluetooth.library.jieli.sender.IBleOp
    public int getBleMtu() {
        return this.worker.getMtu();
    }

    private JLBleDataManager() {
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/JLBleDataManager$ClassHolder.class */
    private static class ClassHolder {
        private static final JLBleDataManager INSTANCE = new JLBleDataManager();

        private ClassHolder() {
        }
    }

    public static JLBleDataManager getInstance() {
        return ClassHolder.INSTANCE;
    }

    public void init(BleConnectWorker worker) {
        this.worker = worker;
        this.gatt = worker.getBluetoothGatt();
        this.device = worker.getBluetoothDevice();
        Log.e(TAG, "初始化杰理蓝牙数据管理类, Device[" + this.device.getName() + "," + this.device.getAddress() + "] Gatt = " + this.gatt.toString());
    }

    @Override // com.inuker.bluetooth.library.jieli.sender.IBleOp
    public boolean writeDataByBle(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID, byte[] data) {
        return this.worker.writeCharacteristic(serviceUUID, characteristicUUID, data);
    }

    private void writeDataByBleAsync(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        addSendTask(device, serviceUUID, characteristicUUID, data, callback);
    }

    public void writeDataByBleAsync(byte[] data, OnWriteDataCallback callback) {
        addSendTask(this.device, JLConstants.JL_UUID_SERVICE, JLConstants.JL_UUID_WRITE, data, callback);
    }

    public void writeDataByBleAsync(byte[] data) {
        addSendTask(this.device, JLConstants.JL_UUID_SERVICE, JLConstants.JL_UUID_WRITE, data, this.callback);
    }

    private void addSendTask(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        boolean ret = false;
        if (this.mSendBleDataThread != null && this.gatt != null && BluetoothUtil.deviceEquals(device, this.gatt.getDevice())) {
            ret = this.mSendBleDataThread.addSendTask(this.gatt, serviceUUID, characteristicUUID, data, callback);
        }
        if (!ret) {
            callback.onBleResult(device, serviceUUID, characteristicUUID, false, data);
        }
    }

    public void wakeupSendThread(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID, int status, byte[] data) {
        if (this.mSendBleDataThread != null) {
            SendBleDataThread.BleSendTask task = new SendBleDataThread.BleSendTask(gatt, serviceUUID, characteristicUUID, data, null);
            task.setStatus(status);
            this.mSendBleDataThread.wakeupSendThread(task);
        }
    }

    public void startSendDataThread() {
        if (this.mSendBleDataThread != null) {
            stopSendDataThread();
        }
        if (this.mSendBleDataThread == null) {
            this.mSendBleDataThread = new SendBleDataThread(this, new OnThreadStateListener() { // from class: com.inuker.bluetooth.library.jieli.sender.JLBleDataManager.2
                @Override // com.inuker.bluetooth.library.jieli.sender.OnThreadStateListener
                public void onStart(long id, String name) {
                }

                @Override // com.inuker.bluetooth.library.jieli.sender.OnThreadStateListener
                public void onEnd(long id, String name) {
                    JLBleDataManager.this.mSendBleDataThread = null;
                }
            });
            this.mSendBleDataThread.start();
        }
    }

    public void stopSendDataThread() {
        if (this.mSendBleDataThread != null) {
            this.mSendBleDataThread.stopThread();
            this.mSendBleDataThread = null;
        }
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int mtu) {
        Log.e(TAG, "设置mtu值：" + mtu);
        this.mtu = mtu;
    }
}
