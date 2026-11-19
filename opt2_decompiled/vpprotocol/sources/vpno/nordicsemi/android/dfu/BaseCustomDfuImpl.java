package vpno.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.CRC32;
import vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import vpno.nordicsemi.android.dfu.internal.exception.DfuException;
import vpno.nordicsemi.android.dfu.internal.exception.HexFileValidationException;
import vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScannerFactory;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/BaseCustomDfuImpl.class */
abstract class BaseCustomDfuImpl extends BaseDfuImpl {
    private boolean mInitPacketInProgress;
    private boolean mFirmwareUploadInProgress;
    protected final int mPacketsBeforeNotification;
    protected int mPacketsSentSinceNotification;
    protected boolean mRemoteErrorOccurred;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/BaseCustomDfuImpl$BaseCustomBluetoothCallback.class */
    protected class BaseCustomBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        protected BaseCustomBluetoothCallback() {
            super();
        }

        protected void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i != 0) {
                BaseCustomDfuImpl baseCustomDfuImpl = BaseCustomDfuImpl.this;
                if (baseCustomDfuImpl.mResetRequestSent) {
                    baseCustomDfuImpl.mRequestCompleted = true;
                } else {
                    baseCustomDfuImpl.loge("Characteristic write Error: " + i);
                    BaseCustomDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
                }
            } else if (bluetoothGattCharacteristic.getUuid().equals(BaseCustomDfuImpl.this.getPacketCharacteristicUUID())) {
                BaseCustomDfuImpl baseCustomDfuImpl2 = BaseCustomDfuImpl.this;
                if (baseCustomDfuImpl2.mInitPacketInProgress) {
                    baseCustomDfuImpl2.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    BaseCustomDfuImpl.this.mInitPacketInProgress = false;
                } else if (baseCustomDfuImpl2.mFirmwareUploadInProgress) {
                    baseCustomDfuImpl2.mProgressInfo.addBytesSent(bluetoothGattCharacteristic.getValue().length);
                    BaseCustomDfuImpl baseCustomDfuImpl3 = BaseCustomDfuImpl.this;
                    int i2 = baseCustomDfuImpl3.mPacketsSentSinceNotification + 1;
                    baseCustomDfuImpl3.mPacketsSentSinceNotification = i2;
                    int i3 = baseCustomDfuImpl3.mPacketsBeforeNotification;
                    boolean z = i3 > 0 && i2 == i3;
                    boolean zIsComplete = baseCustomDfuImpl3.mProgressInfo.isComplete();
                    boolean zIsObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                    if (z) {
                        return;
                    }
                    if (zIsComplete || zIsObjectComplete) {
                        BaseCustomDfuImpl baseCustomDfuImpl4 = BaseCustomDfuImpl.this;
                        baseCustomDfuImpl4.mFirmwareUploadInProgress = false;
                        baseCustomDfuImpl4.notifyLock();
                        return;
                    }
                    try {
                        BaseCustomDfuImpl.this.waitIfPaused();
                        BaseCustomDfuImpl baseCustomDfuImpl5 = BaseCustomDfuImpl.this;
                        if (baseCustomDfuImpl5.mAborted || baseCustomDfuImpl5.mError != 0 || baseCustomDfuImpl5.mRemoteErrorOccurred || baseCustomDfuImpl5.mResetRequestSent) {
                            baseCustomDfuImpl5.mService.sendLogBroadcast(15, "Upload terminated");
                            BaseCustomDfuImpl.this.notifyLock();
                            return;
                        }
                        int availableObjectSizeIsBytes = baseCustomDfuImpl5.mProgressInfo.getAvailableObjectSizeIsBytes();
                        BaseCustomDfuImpl baseCustomDfuImpl6 = BaseCustomDfuImpl.this;
                        byte[] bArr = baseCustomDfuImpl6.mBuffer;
                        if (availableObjectSizeIsBytes < 20) {
                            bArr = new byte[availableObjectSizeIsBytes];
                        }
                        BaseCustomDfuImpl.this.writePacket(bluetoothGatt, bluetoothGattCharacteristic, bArr, baseCustomDfuImpl6.mFirmwareStream.read(bArr));
                        return;
                    } catch (HexFileValidationException unused) {
                        BaseCustomDfuImpl.this.loge("Invalid HEX file");
                        BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_INVALID;
                    } catch (IOException e) {
                        BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                        BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
                    }
                } else {
                    onPacketCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            } else {
                BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                BaseCustomDfuImpl.this.mRequestCompleted = true;
            }
            BaseCustomDfuImpl.this.notifyLock();
        }

        protected void handlePacketReceiptNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BaseCustomDfuImpl baseCustomDfuImpl = BaseCustomDfuImpl.this;
            if (!baseCustomDfuImpl.mFirmwareUploadInProgress) {
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                return;
            }
            BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(baseCustomDfuImpl.getDfuServiceUUID()).getCharacteristic(BaseCustomDfuImpl.this.getPacketCharacteristicUUID());
            try {
                BaseCustomDfuImpl baseCustomDfuImpl2 = BaseCustomDfuImpl.this;
                baseCustomDfuImpl2.mPacketsSentSinceNotification = 0;
                baseCustomDfuImpl2.waitIfPaused();
                BaseCustomDfuImpl baseCustomDfuImpl3 = BaseCustomDfuImpl.this;
                if (baseCustomDfuImpl3.mAborted || baseCustomDfuImpl3.mError != 0 || baseCustomDfuImpl3.mRemoteErrorOccurred || baseCustomDfuImpl3.mResetRequestSent) {
                    baseCustomDfuImpl3.mService.sendLogBroadcast(15, "Upload terminated");
                    return;
                }
                boolean zIsComplete = baseCustomDfuImpl3.mProgressInfo.isComplete();
                boolean zIsObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                if (zIsComplete || zIsObjectComplete) {
                    BaseCustomDfuImpl baseCustomDfuImpl4 = BaseCustomDfuImpl.this;
                    baseCustomDfuImpl4.mFirmwareUploadInProgress = false;
                    baseCustomDfuImpl4.notifyLock();
                    return;
                }
                int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                BaseCustomDfuImpl baseCustomDfuImpl5 = BaseCustomDfuImpl.this;
                byte[] bArr = baseCustomDfuImpl5.mBuffer;
                if (availableObjectSizeIsBytes < 20) {
                    bArr = new byte[availableObjectSizeIsBytes];
                }
                BaseCustomDfuImpl.this.writePacket(bluetoothGatt, characteristic, bArr, baseCustomDfuImpl5.mFirmwareStream.read(bArr));
            } catch (HexFileValidationException unused) {
                BaseCustomDfuImpl.this.loge("Invalid HEX file");
                BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_INVALID;
            } catch (IOException e) {
                BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
            }
        }

        protected void handleNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseCustomDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        }
    }

    BaseCustomDfuImpl(DfuBaseService dfuBaseService) {
        super(dfuBaseService);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(dfuBaseService);
        boolean z = defaultSharedPreferences.getBoolean(DfuSettingsConstants.SETTINGS_PACKET_RECEIPT_NOTIFICATION_ENABLED, Build.VERSION.SDK_INT < 23);
        int i = DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS_DEFAULT;
        int i2 = i;
        try {
            int i3 = Integer.parseInt(defaultSharedPreferences.getString(DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS, String.valueOf(i)));
            if (i3 >= 0 && i3 <= 65535) {
                i2 = i3;
            }
        } catch (NumberFormatException unused) {
            i2 = DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS_DEFAULT;
        }
        this.mPacketsBeforeNotification = z ? i2 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [vpno.nordicsemi.android.dfu.BaseCustomDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19 */
    private void writeInitPacket(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        if (bArr.length != i) {
            bArr = new byte[i];
            System.arraycopy(bArr, 0, bArr, 0, i);
        }
        this.mReceivedData = null;
        this.mError = 0;
        this.mInitPacketInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(bArr);
        logi("Sending init packet (Value = " + parse(bArr) + ")");
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((!r0.mInitPacketInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                            break;
                        } else {
                            this.mLock.wait();
                        }
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
                r0 = obj;
                if (this.mAborted) {
                    throw new UploadAbortedException();
                }
                if (this.mError != 0) {
                    throw new DfuException("Unable to write Init DFU Parameters", this.mError);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to write Init DFU Parameters: device disconnected");
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    private void writePacket(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        if (bArr.length != i) {
            bArr = new byte[i];
            System.arraycopy(bArr, 0, bArr, 0, i);
        }
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(bArr);
        bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    protected abstract UUID getControlPointCharacteristicUUID();

    protected abstract UUID getPacketCharacteristicUUID();

    protected abstract UUID getDfuServiceUUID();

    protected void writeInitData(BluetoothGattCharacteristic bluetoothGattCharacteristic, CRC32 crc32) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        try {
            byte[] bArr = new byte[20];
            while (true) {
                int i = this.mInitPacketStream.read(bArr, 0, 20);
                if (i == -1) {
                    return;
                }
                writeInitPacket(bluetoothGattCharacteristic, bArr, i);
                if (crc32 != null) {
                    crc32.update(bArr, 0, i);
                }
            }
        } catch (IOException e) {
            loge("Error while reading Init packet file", e);
            throw new DfuException("Error while reading Init packet file", DfuBaseService.ERROR_FILE_ERROR);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v28, types: [vpno.nordicsemi.android.dfu.BaseCustomDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v33 */
    protected void uploadFirmwareImage(BluetoothGattCharacteristic bluetoothGattCharacteristic) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        this.mReceivedData = null;
        this.mError = 0;
        this.mFirmwareUploadInProgress = true;
        this.mPacketsSentSinceNotification = 0;
        byte[] bArr = this.mBuffer;
        try {
            int i = this.mFirmwareStream.read(bArr);
            this.mService.sendLogBroadcast(1, "Sending firmware to characteristic " + bluetoothGattCharacteristic.getUuid() + "...");
            writePacket(this.mGatt, bluetoothGattCharacteristic, bArr, i);
            try {
                Object obj = this.mLock;
                synchronized (obj) {
                    while (true) {
                        r0 = this;
                        try {
                            if ((!r0.mFirmwareUploadInProgress || this.mReceivedData != null || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                                break;
                            } else {
                                this.mLock.wait();
                            }
                        } catch (Throwable unused) {
                            throw r0;
                        }
                    }
                    r0 = obj;
                    if (this.mAborted) {
                        throw new UploadAbortedException();
                    }
                    if (this.mError != 0) {
                        throw new DfuException("Uploading Firmware Image failed", this.mError);
                    }
                    if (!this.mConnected) {
                        throw new DeviceDisconnectedException("Uploading Firmware Image failed: device disconnected");
                    }
                }
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        } catch (HexFileValidationException unused2) {
            throw new DfuException("HEX file not valid", DfuBaseService.ERROR_FILE_INVALID);
        } catch (IOException unused3) {
            throw new DfuException("Error while reading file", DfuBaseService.ERROR_FILE_IO_EXCEPTION);
        }
    }

    protected void finalize(Intent intent, boolean z) {
        boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false);
        this.mService.refreshDeviceCache(this.mGatt, z || !booleanExtra);
        this.mService.close(this.mGatt);
        boolean z2 = false;
        if (this.mGatt.getDevice().getBondState() == 12) {
            boolean booleanExtra2 = intent.getBooleanExtra(DfuBaseService.EXTRA_RESTORE_BOND, false);
            if (booleanExtra2 || !booleanExtra || (this.mFileType & 1) > 0) {
                removeBond();
                this.mService.waitFor(2000);
                z2 = true;
            }
            if (booleanExtra2 && (this.mFileType & 4) > 0) {
                createBond();
                z2 = false;
            }
        }
        if (this.mProgressInfo.isLastPart()) {
            if (!z2) {
                this.mService.waitFor(1400);
            }
            this.mProgressInfo.setProgress(-6);
            return;
        }
        this.mService.sendLogBroadcast(1, "Scanning for the DFU Bootloader...");
        String strSearchFor = BootloaderScannerFactory.getScanner().searchFor(this.mGatt.getDevice().getAddress());
        if (strSearchFor != null) {
            this.mService.sendLogBroadcast(5, "DFU Bootloader found with address " + strSearchFor);
        } else {
            this.mService.sendLogBroadcast(5, "DFU Bootloader not found. Trying the same address...");
        }
        logi("Starting service that will upload application");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, DfuBaseService.MIME_TYPE_ZIP);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_TYPE, 4);
        if (strSearchFor != null) {
            intent2.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, strSearchFor);
        }
        intent2.putExtra(DfuBaseService.EXTRA_PART_CURRENT, this.mProgressInfo.getCurrentPart() + 1);
        intent2.putExtra(DfuBaseService.EXTRA_PARTS_TOTAL, this.mProgressInfo.getTotalParts());
        this.mService.startService(intent2);
    }
}
