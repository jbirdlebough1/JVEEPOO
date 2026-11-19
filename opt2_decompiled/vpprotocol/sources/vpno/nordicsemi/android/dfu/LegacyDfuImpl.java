package vpno.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import java.io.InputStream;
import java.util.UUID;
import vpno.nordicsemi.android.dfu.internal.ArchiveInputStream;
import vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import vpno.nordicsemi.android.dfu.internal.exception.DfuException;
import vpno.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import vpno.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import vpno.nordicsemi.android.error.LegacyDfuError;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/LegacyDfuImpl.class */
class LegacyDfuImpl extends BaseCustomDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    private boolean mImageSizeInProgress;
    private final LegacyBluetoothCallback mBluetoothCallback;
    protected static final UUID DFU_SERVICE_UUID = new UUID(23296205844446L, 1523193452336828707L);
    protected static final UUID DFU_CONTROL_POINT_UUID = new UUID(23300500811742L, 1523193452336828707L);
    protected static final UUID DFU_PACKET_UUID = new UUID(23304795779038L, 1523193452336828707L);
    protected static final UUID DFU_VERSION = new UUID(23313385713630L, 1523193452336828707L);
    private static final byte[] OP_CODE_START_DFU = {1, 0};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START = {2, 0};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE = {2, 1};
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE = {3};
    private static final byte[] OP_CODE_VALIDATE = {4};
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET = {5};
    private static final byte[] OP_CODE_RESET = {6};
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {8, 0, 0};

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/LegacyDfuImpl$LegacyBluetoothCallback.class */
    protected class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected LegacyBluetoothCallback() {
            super(LegacyDfuImpl.this);
        }

        @Override // vpno.nordicsemi.android.dfu.BaseCustomDfuImpl.BaseCustomBluetoothCallback
        protected void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LegacyDfuImpl legacyDfuImpl = LegacyDfuImpl.this;
            if (legacyDfuImpl.mImageSizeInProgress) {
                legacyDfuImpl.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 17) {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }
    }

    LegacyDfuImpl(DfuBaseService dfuBaseService) {
        super(dfuBaseService);
        this.mBluetoothCallback = new LegacyBluetoothCallback();
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        byte b;
        if (bArr == null || bArr.length != 3 || bArr[0] != 16 || bArr[1] != i || (b = bArr[2]) < 1 || b > 6) {
            throw new UnknownResponseException("Invalid response received", bArr, 16, i);
        }
        return b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v32 */
    private int readVersion(BluetoothGattCharacteristic bluetoothGattCharacteristic) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read version number: device disconnected");
        }
        if (bluetoothGattCharacteristic == null) {
            return 0;
        }
        this.mReceivedData = null;
        this.mError = 0;
        logi("Reading DFU version number...");
        this.mService.sendLogBroadcast(1, "Reading DFU version number...");
        bluetoothGattCharacteristic.setValue((byte[]) null);
        this.mService.sendLogBroadcast(0, "gatt.readCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.readCharacteristic(bluetoothGattCharacteristic);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if (((r0.mRequestCompleted && bluetoothGattCharacteristic.getValue() != null) || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                            break;
                        }
                        this.mRequestCompleted = false;
                        this.mLock.wait();
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
                r0 = obj;
                if (this.mAborted) {
                    throw new UploadAbortedException();
                }
                if (this.mError != 0) {
                    throw new DfuException("Unable to read version number", this.mError);
                }
                if (this.mConnected) {
                    return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
                }
                throw new DeviceDisconnectedException("Unable to read version number: device disconnected");
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        byte b = bArr[0];
        writeOpCode(bluetoothGattCharacteristic, bArr, b == 6 || b == 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [vpno.nordicsemi.android.dfu.LegacyDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17 */
    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[4]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((!r0.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
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
                    throw new DfuException("Unable to write Image Size", this.mError);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to write Image Size: device disconnected");
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public boolean hasRequiredService(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.getService(DFU_SERVICE_UUID) != null;
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public boolean hasRequiredCharacteristics(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        this.mControlPointCharacteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID);
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(DFU_PACKET_UUID);
        this.mPacketCharacteristic = characteristic;
        return (this.mControlPointCharacteristic == null || characteristic == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // vpno.nordicsemi.android.dfu.BaseDfuImpl
    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    @Override // vpno.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    @Override // vpno.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    @Override // vpno.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v344 */
    /* JADX WARN: Type inference failed for: r0v345 */
    /* JADX WARN: Type inference failed for: r0v35, types: [vpno.nordicsemi.android.dfu.DfuBaseService, vpno.nordicsemi.android.dfu.internal.exception.RemoteDfuException] */
    /* JADX WARN: Type inference failed for: r0v74, types: [java.lang.Throwable] */
    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void performDfu(Intent intent) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        this.mProgressInfo.setProgress(-2);
        this.mService.waitFor(1000);
        BluetoothGatt bluetoothGatt = this.mGatt;
        BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(DFU_SERVICE_UUID).getCharacteristic(DFU_VERSION);
        int i = 0;
        if (characteristic != null) {
            int version = readVersion(characteristic);
            i = version;
            int i2 = version & 15;
            int i3 = version >> 8;
            logi("Version number read: " + i3 + "." + i2);
            this.mService.sendLogBroadcast(10, "Version number read: " + i3 + "." + i2);
        } else {
            this.mService.sendLogBroadcast(10, "DFU Version characteristic not found");
        }
        int i4 = i;
        boolean z = PreferenceManager.getDefaultSharedPreferences(this.mService).getBoolean(DfuSettingsConstants.SETTINGS_ASSUME_DFU_NODE, false);
        if (i4 == 1 || (!z && i == 0 && bluetoothGatt.getServices().size() > 3)) {
            logw("Application with buttonless update found");
            this.mService.sendLogBroadcast(15, "Application with buttonless update found");
            this.mService.sendLogBroadcast(1, "Jumping to the DFU Bootloader...");
            enableCCCD(this.mControlPointCharacteristic, 1);
            this.mService.sendLogBroadcast(10, "Notifications enabled");
            this.mService.waitFor(1000);
            this.mProgressInfo.setProgress(-3);
            byte[] bArr = OP_CODE_START_DFU;
            bArr[1] = 4;
            logi("Sending Start DFU command (Op Code = 1, Upload Mode = 4)");
            writeOpCode(this.mControlPointCharacteristic, bArr, true);
            this.mService.sendLogBroadcast(10, "Jump to bootloader sent (Op Code = 1, Upload Mode = 4)");
            this.mService.waitUntilDisconnected();
            this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
            BluetoothGattService service = bluetoothGatt.getService(BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID);
            this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(BaseDfuImpl.SERVICE_CHANGED_UUID) == null) ? false : true));
            this.mService.close(bluetoothGatt);
            logi("Starting service that will connect to the DFU bootloader");
            Intent intent2 = new Intent();
            intent2.fillIn(intent, 24);
            this.mService.startService(intent2);
            return;
        }
        if (i >= 5 && this.mInitPacketStream == null) {
            logw("Init packet not set for the DFU Bootloader version " + i);
            this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
            return;
        }
        enableCCCD(this.mControlPointCharacteristic, 1);
        this.mService.sendLogBroadcast(10, "Notifications enabled");
        ?? r0 = this.mService;
        r0.waitFor(1000);
        try {
            try {
                int i5 = this.mFileType;
                int iSoftDeviceImageSize = (i5 & 1) > 0 ? this.mImageSizeInBytes : 0;
                int iBootloaderImageSize = (i5 & 2) > 0 ? this.mImageSizeInBytes : 0;
                int i6 = i5 & 4;
                int i7 = i6 > 0 ? this.mImageSizeInBytes : 0;
                InputStream inputStream = this.mFirmwareStream;
                boolean z2 = inputStream instanceof ArchiveInputStream;
                ?? r1 = z2;
                int i8 = i7;
                if (z2) {
                    ArchiveInputStream archiveInputStream = (ArchiveInputStream) inputStream;
                    iSoftDeviceImageSize = archiveInputStream.softDeviceImageSize();
                    iBootloaderImageSize = archiveInputStream.bootloaderImageSize();
                    int iApplicationImageSize = archiveInputStream.applicationImageSize();
                    i8 = iApplicationImageSize;
                    r1 = iApplicationImageSize;
                }
                try {
                    try {
                        byte[] bArr2 = OP_CODE_START_DFU;
                        bArr2[1] = (byte) i5;
                        logi("Sending Start DFU command (Op Code = 1, Upload Mode = " + i5 + ")");
                        writeOpCode(this.mControlPointCharacteristic, bArr2);
                        this.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1, Upload Mode = " + i5 + ")");
                        Thread.sleep(1000L);
                        logi("Sending image size array to DFU Packet (" + iSoftDeviceImageSize + "b, " + iBootloaderImageSize + "b, " + (i8 == true ? 1 : 0) + "b)");
                        writeImageSize(this.mPacketCharacteristic, iSoftDeviceImageSize, iBootloaderImageSize, i8 == true ? 1 : 0);
                        this.mService.sendLogBroadcast(10, "Firmware image size sent (" + iSoftDeviceImageSize + "b, " + iBootloaderImageSize + "b, " + (i8 == true ? 1 : 0) + "b)");
                        byte[] notificationResponse = readNotificationResponse();
                        int statusCode = getStatusCode(notificationResponse, 1);
                        this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse[1]) + " Status = " + statusCode + ")");
                        if (statusCode == 2) {
                            this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
                            this.mProgressInfo.setProgress(-5);
                            logi("Sending Reset command (Op Code = 6)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
                            this.mService.sendLogBroadcast(10, "Reset request sent");
                            this.mService.waitUntilDisconnected();
                            this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
                            BluetoothGattService service2 = bluetoothGatt.getService(BaseDfuImpl.GENERIC_ATTRIBUTE_SERVICE_UUID);
                            this.mService.refreshDeviceCache(bluetoothGatt, !(service2 != null && service2.getCharacteristic(BaseDfuImpl.SERVICE_CHANGED_UUID) != null));
                            this.mService.close(bluetoothGatt);
                            logi("Restarting the service");
                            Intent intent3 = new Intent();
                            intent3.fillIn(intent, 24);
                            this.mService.startService(intent3);
                            return;
                        }
                        if (statusCode != 1) {
                            throw new RemoteDfuException("Starting DFU failed", statusCode);
                        }
                        if (this.mInitPacketStream != null) {
                            this.mService.sendLogBroadcast(10, "Writing Initialize DFU Parameters...");
                            logi("Sending the Initialize DFU Parameters START (Op Code = 2, Value = 0)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_INIT_DFU_PARAMS_START);
                            logi("Sending " + this.mImageSizeInBytes + " bytes of init packet");
                            writeInitData(this.mPacketCharacteristic, null);
                            logi("Sending the Initialize DFU Parameters COMPLETE (Op Code = 2, Value = 1)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_INIT_DFU_PARAMS_COMPLETE);
                            this.mService.sendLogBroadcast(10, "Initialize DFU Parameters completed");
                            byte[] notificationResponse2 = readNotificationResponse();
                            int statusCode2 = getStatusCode(notificationResponse2, 2);
                            this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse2[1]) + ", Status = " + statusCode2 + ")");
                            if (statusCode2 != 1) {
                                throw new RemoteDfuException("Device returned Error after sending init packet", statusCode2);
                            }
                        }
                        int i9 = this.mPacketsBeforeNotification;
                        if (i9 > 0) {
                            logi("Sending the number of packets before notifications (Op Code = 8, Value = " + i9 + ")");
                            byte[] bArr3 = OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
                            setNumberOfPackets(bArr3, i9);
                            writeOpCode(this.mControlPointCharacteristic, bArr3);
                            this.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 8) sent (Value = " + i9 + ")");
                        }
                        logi("Sending Receive Firmware Image request (Op Code = 3)");
                        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RECEIVE_FIRMWARE_IMAGE);
                        this.mService.sendLogBroadcast(10, "Receive Firmware Image request sent");
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        this.mProgressInfo.setBytesSent(0);
                        try {
                            logi("Uploading firmware...");
                            this.mService.sendLogBroadcast(10, "Uploading firmware...");
                            uploadFirmwareImage(this.mPacketCharacteristic);
                            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                            byte[] notificationResponse3 = readNotificationResponse();
                            int statusCode3 = getStatusCode(notificationResponse3, 3);
                            logi("Response received. Op Code: " + ((int) notificationResponse3[0]) + " Req Op Code = " + ((int) notificationResponse3[1]) + ", Status = " + ((int) notificationResponse3[2]));
                            this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse3[1]) + ", Status = " + statusCode3 + ")");
                            if (statusCode3 != 1) {
                                throw new RemoteDfuException("Device returned Error after sending file", statusCode3);
                            }
                            long j = jElapsedRealtime2 - jElapsedRealtime;
                            logi("Transfer of " + this.mProgressInfo.getBytesSent() + " bytes has taken " + j + " ms");
                            this.mService.sendLogBroadcast(10, "Upload completed in " + j + " ms");
                            logi("Sending Validate request (Op Code = 4)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_VALIDATE);
                            this.mService.sendLogBroadcast(10, "Validate request sent");
                            byte[] notificationResponse4 = readNotificationResponse();
                            int statusCode4 = getStatusCode(notificationResponse4, 4);
                            logi("Response received. Op Code: " + ((int) notificationResponse4[0]) + " Req Op Code = " + ((int) notificationResponse4[1]) + ", Status = " + ((int) notificationResponse4[2]));
                            this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse4[1]) + ", Status = " + statusCode4 + ")");
                            if (statusCode4 != 1) {
                                throw new RemoteDfuException("Device returned validation Error", statusCode4);
                            }
                            int i10 = i;
                            this.mProgressInfo.setProgress(-5);
                            logi("Sending Activate and Reset request (Op Code = 5)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_ACTIVATE_AND_RESET);
                            this.mService.sendLogBroadcast(10, "Activate and Reset request sent");
                            this.mService.waitUntilDisconnected();
                            this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
                            finalize(intent, i10 == 5);
                        } catch (DeviceDisconnectedException unused) {
                            loge("Disconnected while sending data");
                            throw "Uploading firmware...";
                        }
                    } catch (RemoteDfuException e) {
                        try {
                            if (e.getErrorNumber() != 3 || i6 <= 0 || (i5 & 3) <= 0) {
                                throw e;
                            }
                            this.mRemoteErrorOccurred = false;
                            logw("DFU target does not support (SD/BL)+App update");
                            this.mService.sendLogBroadcast(15, "DFU target does not support (SD/BL)+App update");
                            int i11 = i5 & (-5);
                            this.mFileType = i11;
                            byte[] bArr4 = OP_CODE_START_DFU;
                            bArr4[1] = (byte) i11;
                            this.mProgressInfo.setTotalPart(2);
                            ((ArchiveInputStream) this.mFirmwareStream).setContentType(i11);
                            this.mService.sendLogBroadcast(1, "Sending only SD/BL");
                            logi("Resending Start DFU command (Op Code = 1, Upload Mode = " + i11 + ")");
                            writeOpCode(this.mControlPointCharacteristic, bArr4);
                            this.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1, Upload Mode = " + i11 + ")");
                            logi("Sending image size array to DFU Packet: [" + iSoftDeviceImageSize + "b, " + iBootloaderImageSize + "b, 0b]");
                            writeImageSize(this.mPacketCharacteristic, iSoftDeviceImageSize, iBootloaderImageSize, 0);
                            this.mService.sendLogBroadcast(10, "Firmware image size sent [" + iSoftDeviceImageSize + "b, " + iBootloaderImageSize + "b, 0b]");
                            byte[] notificationResponse5 = readNotificationResponse();
                            int statusCode5 = getStatusCode(notificationResponse5, 1);
                            this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse5[1]) + " Status = " + statusCode5 + ")");
                            if (statusCode5 != 1) {
                                throw new RemoteDfuException("Starting DFU failed", statusCode5);
                            }
                        } catch (RemoteDfuException e2) {
                            if (e2.getErrorNumber() != 3 || i5 != 4) {
                                throw e2;
                            }
                            this.mRemoteErrorOccurred = false;
                            logw("DFU target does not support DFU v.2");
                            this.mService.sendLogBroadcast(15, "DFU target does not support DFU v.2");
                            this.mService.sendLogBroadcast(1, "Switching to DFU v.1");
                            logi("Resending Start DFU command (Op Code = 1)");
                            writeOpCode(this.mControlPointCharacteristic, OP_CODE_START_DFU);
                            this.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1)");
                            logi("Sending application image size to DFU Packet: " + this.mImageSizeInBytes + " bytes");
                            writeImageSize(this.mPacketCharacteristic, this.mImageSizeInBytes);
                            this.mService.sendLogBroadcast(10, "Firmware image size sent (" + this.mImageSizeInBytes + " bytes)");
                            byte[] notificationResponse6 = readNotificationResponse();
                            int statusCode6 = getStatusCode(notificationResponse6, 1);
                            this.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) notificationResponse6[1]) + ", Status = " + statusCode6 + ")");
                            if (statusCode6 != 1) {
                                throw new RemoteDfuException("Starting DFU failed", statusCode6);
                            }
                        }
                    }
                } catch (InterruptedException unused2) {
                    r1.printStackTrace();
                }
            } catch (UnknownResponseException e3) {
                loge(e3.getMessage());
                this.mService.sendLogBroadcast(20, e3.getMessage());
                logi("Sending Reset command (Op Code = 6)");
                writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
                this.mService.sendLogBroadcast(10, "Reset request sent");
                this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INVALID_RESPONSE);
            }
        } catch (RemoteDfuException unused3) {
            int errorNumber = r0.getErrorNumber() | DfuBaseService.ERROR_REMOTE_MASK;
            loge(r0.getMessage());
            this.mService.sendLogBroadcast(20, String.format("Remote DFU Error: %s", LegacyDfuError.parse(errorNumber)));
            logi("Sending Reset command (Op Code = 6)");
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
            this.mService.sendLogBroadcast(10, "Reset request sent");
            this.mService.terminateConnection(bluetoothGatt, errorNumber);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [vpno.nordicsemi.android.dfu.LegacyDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17 */
    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[12]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        bluetoothGattCharacteristic.setValue(i2, 20, 4);
        bluetoothGattCharacteristic.setValue(i3, 20, 8);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((!r0.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
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
                    throw new DfuException("Unable to write Image Sizes", this.mError);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to write Image Sizes: device disconnected");
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }
}
