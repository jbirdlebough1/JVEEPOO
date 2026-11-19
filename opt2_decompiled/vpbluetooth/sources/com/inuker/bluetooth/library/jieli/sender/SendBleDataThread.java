package com.inuker.bluetooth.library.jieli.sender;

import android.bluetooth.BluetoothGatt;
import android.os.SystemClock;
import android.util.Log;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/SendBleDataThread.class */
public class SendBleDataThread extends Thread {
    private static final String TAG = "SendBleDataThread";
    private final LinkedBlockingQueue<BleSendTask> mQueue;
    private volatile boolean isDataSend;
    private volatile boolean isThreadWaiting;
    private volatile boolean isWaitingForCallback;
    private volatile int retryNum;
    private final IBleOp mBleManager;
    private final OnThreadStateListener mListener;
    private BleSendTask mCurrentTask;

    public SendBleDataThread(IBleOp manager, OnThreadStateListener listener) {
        super(TAG);
        this.mQueue = new LinkedBlockingQueue<>();
        this.isDataSend = false;
        this.isThreadWaiting = false;
        this.isWaitingForCallback = false;
        this.retryNum = 0;
        this.mBleManager = manager;
        this.mListener = listener;
    }

    public boolean addSendTask(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        if (null == this.mBleManager || null == gatt || null == serviceUUID || null == characteristicUUID || null == data || data.length == 0) {
            return false;
        }
        int mtu = this.mBleManager.getBleMtu();
        Log.d(TAG, "addSendTask : " + mtu);
        int dataLen = data.length;
        int blockCount = dataLen / mtu;
        boolean ret = false;
        for (int i = 0; i < blockCount; i++) {
            byte[] mBlockData = new byte[mtu];
            System.arraycopy(data, i * mtu, mBlockData, 0, mBlockData.length);
            ret = addSendData(gatt, serviceUUID, characteristicUUID, mBlockData, callback);
        }
        if (0 != dataLen % mtu) {
            byte[] noBlockData = new byte[dataLen % mtu];
            System.arraycopy(data, dataLen - (dataLen % mtu), noBlockData, 0, noBlockData.length);
            ret = addSendData(gatt, serviceUUID, characteristicUUID, noBlockData, callback);
        }
        return ret;
    }

    public void wakeupSendThread(BleSendTask sendTask) {
        if (null == sendTask || (this.mCurrentTask != null && this.mCurrentTask.equals(sendTask))) {
            if (sendTask != null) {
                sendTask.setCallback(this.mCurrentTask.getCallback());
                this.mCurrentTask = sendTask;
            }
            synchronized (this.mQueue) {
                if (this.isThreadWaiting) {
                    if (this.isWaitingForCallback) {
                        this.mQueue.notifyAll();
                    } else {
                        this.mQueue.notify();
                    }
                } else if (this.isWaitingForCallback) {
                    this.mQueue.notify();
                }
            }
        }
    }

    private boolean addSendData(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        boolean ret = false;
        if (this.isDataSend) {
            BleSendTask sendTask = new BleSendTask(gatt, serviceUUID, characteristicUUID, data, callback);
            try {
                this.mQueue.put(sendTask);
                ret = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ret && this.isThreadWaiting && !this.isWaitingForCallback) {
                this.isThreadWaiting = false;
                synchronized (this.mQueue) {
                    this.mQueue.notify();
                }
            }
        }
        return ret;
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isDataSend = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isDataSend = false;
        wakeupSendThread(null);
    }

    private void callbackResult(BleSendTask task, boolean result) {
        if (task != null && task.getCallback() != null) {
            if (task.getBleGatt() == null) {
                return;
            }
            task.getCallback().onBleResult(task.getBleGatt().getDevice(), task.getServiceUUID(), task.getCharacteristicUUID(), result, task.getData());
            return;
        }
        Log.i(TAG, "getCallback is null.");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.d(TAG, "send ble data thread is started.");
        if (this.mListener != null) {
            this.mListener.onStart(getId(), getName());
        }
        if (this.mBleManager != null) {
            synchronized (this.mQueue) {
                while (this.isDataSend) {
                    this.mCurrentTask = null;
                    this.isThreadWaiting = false;
                    this.isWaitingForCallback = false;
                    if (this.mQueue.isEmpty()) {
                        this.isThreadWaiting = true;
                        Log.d(TAG, "queue is empty, so waiting for data");
                        try {
                            this.mQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.mCurrentTask = this.mQueue.peek();
                        if (this.mCurrentTask != null) {
                            this.isWaitingForCallback = this.mBleManager.writeDataByBle(this.mCurrentTask.mGatt, this.mCurrentTask.getServiceUUID(), this.mCurrentTask.getCharacteristicUUID(), this.mCurrentTask.getData());
                            if (this.isWaitingForCallback) {
                                try {
                                    this.mQueue.wait(8000L);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                            } else {
                                this.mCurrentTask.setStatus(-1);
                            }
                            if (this.mCurrentTask.getStatus() != 0) {
                                this.retryNum++;
                                if (this.retryNum >= 3) {
                                    callbackResult(this.mCurrentTask, false);
                                } else if (this.mCurrentTask.getStatus() != -1) {
                                    this.mCurrentTask.setStatus(-1);
                                    SystemClock.sleep(20L);
                                }
                            } else {
                                callbackResult(this.mCurrentTask, true);
                            }
                        }
                        this.retryNum = 0;
                        this.mQueue.poll();
                    }
                }
            }
            this.isWaitingForCallback = false;
            this.isThreadWaiting = false;
            this.mQueue.clear();
            if (this.mListener != null) {
                this.mListener.onEnd(getId(), getName());
            }
            Log.d(TAG, "send ble data thread exit.");
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/SendBleDataThread$BleSendTask.class */
    public static class BleSendTask {
        private BluetoothGatt mGatt;
        private UUID serviceUUID;
        private UUID characteristicUUID;
        private byte[] data;
        private int status = -1;
        private OnWriteDataCallback mCallback;

        public BleSendTask(BluetoothGatt gatt, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
            this.mGatt = gatt;
            this.serviceUUID = serviceUUID;
            this.characteristicUUID = characteristicUUID;
            this.data = data;
            this.mCallback = callback;
        }

        public BluetoothGatt getBleGatt() {
            return this.mGatt;
        }

        public void setDevice(BluetoothGatt gatt) {
            this.mGatt = gatt;
        }

        public UUID getServiceUUID() {
            return this.serviceUUID;
        }

        public void setServiceUUID(UUID serviceUUID) {
            this.serviceUUID = serviceUUID;
        }

        public UUID getCharacteristicUUID() {
            return this.characteristicUUID;
        }

        public void setCharacteristicUUID(UUID characteristicUUID) {
            this.characteristicUUID = characteristicUUID;
        }

        public byte[] getData() {
            return this.data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        public OnWriteDataCallback getCallback() {
            return this.mCallback;
        }

        public void setCallback(OnWriteDataCallback callback) {
            this.mCallback = callback;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String toString() {
            return "BleSendTask{mGatt=" + this.mGatt + ", serviceUUID=" + this.serviceUUID + ", characteristicUUID=" + this.characteristicUUID + ", data=" + Arrays.toString(this.data) + ", status=" + this.status + ", mCallback=" + this.mCallback + '}';
        }

        public int hashCode() {
            if (this.mGatt != null && this.serviceUUID != null && this.characteristicUUID != null) {
                return this.mGatt.hashCode() + this.serviceUUID.hashCode() + this.characteristicUUID.hashCode();
            }
            return super.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof BleSendTask) {
                BleSendTask other = (BleSendTask) obj;
                return this.mGatt != null && this.serviceUUID != null && this.characteristicUUID != null && this.mGatt.equals(other.getBleGatt()) && this.serviceUUID.equals(other.getServiceUUID()) && this.characteristicUUID.equals(other.getCharacteristicUUID());
            }
            return false;
        }
    }
}
