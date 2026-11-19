package vpno.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.CRC32;
import vpno.nordicsemi.android.dfu.internal.ArchiveInputStream;
import vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import vpno.nordicsemi.android.dfu.internal.exception.DfuException;
import vpno.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import vpno.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import vpno.nordicsemi.android.error.SecureDfuError;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/SecureDfuImpl.class */
class SecureDfuImpl extends BaseCustomDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final int OBJECT_COMMAND = 1;
    private static final int OBJECT_DATA = 2;
    private static final int OP_CODE_CREATE_KEY = 1;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 2;
    private static final int OP_CODE_CALCULATE_CHECKSUM_KEY = 3;
    private static final int OP_CODE_EXECUTE_KEY = 4;
    private static final int OP_CODE_READ_ERROR_KEY = 5;
    private static final int OP_CODE_SELECT_OBJECT_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 96;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    private final SecureBluetoothCallback mBluetoothCallback;
    protected static final UUID DFU_SERVICE_UUID = new UUID(279658205548544L, -9223371485494954757L);
    protected static final UUID DFU_CONTROL_POINT_UUID = new UUID(-8157989241631715488L, -6937650605005804976L);
    protected static final UUID DFU_PACKET_UUID = new UUID(-8157989237336748192L, -6937650605005804976L);
    private static final byte[] OP_CODE_CREATE_COMMAND = {1, 1, 0, 0, 0, 0};
    private static final byte[] OP_CODE_CREATE_DATA = {1, 2, 0, 0, 0, 0};
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {2, 0, 0};
    private static final byte[] OP_CODE_CALCULATE_CHECKSUM = {3};
    private static final byte[] OP_CODE_EXECUTE = {4};
    private static final byte[] OP_CODE_READ_ERROR = {5};
    private static final byte[] OP_CODE_SELECT_OBJECT = {6, 0};

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/SecureDfuImpl$ErrorMessage.class */
    private class ErrorMessage {
        protected int code;
        protected String message;

        private ErrorMessage() {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/SecureDfuImpl$ObjectChecksum.class */
    private class ObjectChecksum {
        protected int offset;
        protected int CRC32;

        private ObjectChecksum() {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/SecureDfuImpl$ObjectInfo.class */
    private class ObjectInfo extends ObjectChecksum {
        protected int maxSize;

        private ObjectInfo() {
            super();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/SecureDfuImpl$SecureBluetoothCallback.class */
    protected class SecureBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected SecureBluetoothCallback() {
            super(SecureDfuImpl.this);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length < 3) {
                SecureDfuImpl.this.loge("Empty response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl secureDfuImpl = SecureDfuImpl.this;
                secureDfuImpl.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
                secureDfuImpl.notifyLock();
                return;
            }
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() != SecureDfuImpl.OP_CODE_RESPONSE_CODE_KEY) {
                SecureDfuImpl.this.loge("Invalid response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
            } else if (bluetoothGattCharacteristic.getIntValue(17, 1).intValue() == 3) {
                SecureDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 3).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!SecureDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    SecureDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            SecureDfuImpl.this.notifyLock();
        }
    }

    SecureDfuImpl(DfuBaseService dfuBaseService) {
        super(dfuBaseService);
        this.mBluetoothCallback = new SecureBluetoothCallback();
    }

    private void sendInitPacket(BluetoothGatt bluetoothGatt) throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        DfuBaseService dfuBaseService;
        int i;
        String str;
        CRC32 crc32 = new CRC32();
        logi("Setting object to Command (Op Code = 6, Type = 1)");
        ObjectInfo objectInfoSelectObject = selectObject(1);
        this.mService.sendLogBroadcast(10, String.format(Locale.US, "Command object info received (Max size = %d, Offset = %d, CRC = %08X)", Integer.valueOf(objectInfoSelectObject.maxSize), Integer.valueOf(objectInfoSelectObject.offset), Integer.valueOf(objectInfoSelectObject.CRC32)));
        int i2 = this.mInitPacketSizeInBytes;
        boolean z = false;
        boolean z2 = false;
        int i3 = objectInfoSelectObject.offset;
        if (i3 > 0 && i3 <= i2) {
            try {
                byte[] bArr = new byte[i3];
                this.mInitPacketStream.read(bArr);
                crc32.update(bArr);
                if (objectInfoSelectObject.CRC32 == ((int) (crc32.getValue() & 4294967295L))) {
                    logi("Init packet CRC is the same");
                    if (objectInfoSelectObject.offset == this.mInitPacketSizeInBytes) {
                        logi("-> Whole Init packet was sent before");
                        z = true;
                        dfuBaseService = this.mService;
                        i = 10;
                        str = "Received CRC match Init packet";
                    } else {
                        logi("-> " + objectInfoSelectObject.offset + " bytes of Init packet were sent before");
                        z2 = true;
                        dfuBaseService = this.mService;
                        i = 10;
                        str = "Resuming sending Init packet...";
                    }
                    dfuBaseService.sendLogBroadcast(i, str);
                } else {
                    this.mInitPacketStream.reset();
                    crc32.reset();
                }
            } catch (IOException e) {
                loge("Error while reading " + objectInfoSelectObject.offset + " bytes from the init packet stream", e);
                try {
                    this.mInitPacketStream.reset();
                    crc32.reset();
                } catch (IOException e2) {
                    loge("Error while resetting the init packet stream", e2);
                    this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                    return;
                }
            }
        }
        if (!z) {
            logi("Disabling Packet Receipt Notifications (Op Code = 2, Value = 0)");
            setPacketReceiptNotifications(0);
            this.mService.sendLogBroadcast(10, "Packet Receipt Notif disabled (Op Code = 2, Value = 0)");
            int i4 = 1;
            while (i4 <= 3) {
                if (!z2) {
                    logi("Creating Init packet object (Op Code = 1, Type = 1, Size = " + this.mInitPacketSizeInBytes + ")");
                    writeCreateRequest(1, this.mInitPacketSizeInBytes);
                    this.mService.sendLogBroadcast(10, "Command object created");
                }
                logi("Sending " + (this.mInitPacketSizeInBytes - objectInfoSelectObject.offset) + " bytes of init packet...");
                writeInitData(this.mPacketCharacteristic, crc32);
                int value = (int) (crc32.getValue() & 4294967295L);
                DfuBaseService dfuBaseService2 = this.mService;
                Locale locale = Locale.US;
                dfuBaseService2.sendLogBroadcast(10, String.format(locale, "Command object sent (CRC = %08X)", Integer.valueOf(value)));
                logi("Sending Calculate Checksum command (Op Code = 3)");
                ObjectChecksum checksum = readChecksum();
                this.mService.sendLogBroadcast(10, String.format(locale, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(checksum.offset), Integer.valueOf(checksum.CRC32)));
                logi(String.format(locale, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(checksum.offset), Integer.valueOf(checksum.CRC32)));
                if (value == checksum.CRC32) {
                    break;
                }
                if (i4 >= 3) {
                    loge("CRC does not match!");
                    this.mService.sendLogBroadcast(20, "CRC does not match!");
                    this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_CRC_ERROR);
                    return;
                }
                i4++;
                logi("CRC does not match! Retrying...(" + i4 + "/3)");
                this.mService.sendLogBroadcast(15, "CRC does not match! Retrying...(" + i4 + "/3)");
                z2 = false;
                try {
                    objectInfoSelectObject.offset = 0;
                    objectInfoSelectObject.CRC32 = 0;
                    this.mInitPacketStream.reset();
                    crc32.reset();
                } catch (IOException e3) {
                    loge("Error while resetting the init packet stream", e3);
                    this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                    return;
                }
            }
        }
        logi("Executing init packet (Op Code = 4)");
        writeExecute();
        this.mService.sendLogBroadcast(10, "Command object executed");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v174 */
    /* JADX WARN: Type inference failed for: r0v92 */
    /* JADX WARN: Type inference failed for: r0v93, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v73, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl, vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException] */
    private void sendFirmware(BluetoothGatt bluetoothGatt) throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        DfuBaseService dfuBaseService;
        int i;
        String str;
        ?? r0;
        int i2 = this.mPacketsBeforeNotification;
        if (i2 > 0) {
            logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i2 + ")");
            setPacketReceiptNotifications(i2);
            this.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 2) sent (Value = " + i2 + ")");
        }
        logi("Setting object to Data (Op Code = 6, Type = 1)");
        ObjectInfo objectInfoSelectObject = selectObject(2);
        this.mService.sendLogBroadcast(10, String.format(Locale.US, "Data object info received (Max size = %d, Offset = %d, CRC = %08X)", Integer.valueOf(objectInfoSelectObject.maxSize), Integer.valueOf(objectInfoSelectObject.offset), Integer.valueOf(objectInfoSelectObject.CRC32)));
        this.mProgressInfo.setMaxObjectSizeInBytes(objectInfoSelectObject.maxSize);
        int i3 = this.mImageSizeInBytes;
        int i4 = objectInfoSelectObject.maxSize;
        int i5 = i4;
        int i6 = ((i3 + i4) - 1) / i5;
        int i7 = 0;
        boolean z = false;
        int i8 = objectInfoSelectObject.offset;
        if (i8 > 0) {
            try {
                int i9 = i8 / i5;
                i7 = i9;
                int i10 = i5 * i9;
                int i11 = i10;
                int i12 = i8 - i10;
                if (i12 == 0) {
                    i11 -= i5;
                } else {
                    i5 = i12;
                }
                if (i11 > 0) {
                    this.mFirmwareStream.read(new byte[i11]);
                    this.mFirmwareStream.mark(objectInfoSelectObject.maxSize);
                }
                if (i5 > 0) {
                    this.mFirmwareStream.read(new byte[i5]);
                }
                if (((int) (((ArchiveInputStream) this.mFirmwareStream).getCrc32() & 4294967295L)) == objectInfoSelectObject.CRC32) {
                    int i13 = i5;
                    this.mProgressInfo.setBytesSent(objectInfoSelectObject.offset);
                    this.mProgressInfo.setBytesReceived(objectInfoSelectObject.offset);
                    this.mService.sendLogBroadcast(10, objectInfoSelectObject.offset + " bytes of data sent before, CRC match");
                    if (i13 != objectInfoSelectObject.maxSize || objectInfoSelectObject.offset >= this.mImageSizeInBytes) {
                        z = true;
                    } else {
                        logi("Executing data object (Op Code = 4)");
                        writeExecute();
                        dfuBaseService = this.mService;
                        i = 10;
                        str = "Data object executed";
                    }
                } else {
                    this.mService.sendLogBroadcast(15, objectInfoSelectObject.offset + " bytes sent before, CRC does not match");
                    this.mProgressInfo.setBytesSent(i11);
                    this.mProgressInfo.setBytesReceived(i11);
                    objectInfoSelectObject.offset -= i5;
                    objectInfoSelectObject.CRC32 = 0;
                    this.mFirmwareStream.reset();
                    dfuBaseService = this.mService;
                    i = 10;
                    str = "Resuming from byte " + objectInfoSelectObject.offset + "...";
                }
                dfuBaseService.sendLogBroadcast(i, str);
            } catch (IOException e) {
                loge("Error while reading firmware stream", e);
                this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                return;
            }
        } else {
            this.mProgressInfo.setBytesSent(0);
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (objectInfoSelectObject.offset < this.mImageSizeInBytes) {
            int i14 = 1;
            while (this.mProgressInfo.getAvailableObjectSizeIsBytes() > 0) {
                if (z) {
                    this.mService.sendLogBroadcast(10, "Resuming uploading firmware...");
                    r0 = 0;
                    z = false;
                } else {
                    int i15 = i7 + 1;
                    logi("Creating Data object (Op Code = 1, Type = 2, Size = " + this.mProgressInfo.getAvailableObjectSizeIsBytes() + ") (" + i15 + "/" + i6 + ")");
                    writeCreateRequest(2, this.mProgressInfo.getAvailableObjectSizeIsBytes());
                    this.mService.sendLogBroadcast(10, "Data object (" + i15 + "/" + i6 + ") created");
                    DfuBaseService dfuBaseService2 = this.mService;
                    dfuBaseService2.sendLogBroadcast(10, "Uploading firmware...");
                    r0 = dfuBaseService2;
                }
                try {
                    logi("Uploading firmware...");
                    uploadFirmwareImage(this.mPacketCharacteristic);
                    logi("Sending Calculate Checksum command (Op Code = 3)");
                    ObjectChecksum checksum = readChecksum();
                    DfuBaseService dfuBaseService3 = this.mService;
                    Locale locale = Locale.US;
                    dfuBaseService3.sendLogBroadcast(10, String.format(locale, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(checksum.offset), Integer.valueOf(checksum.CRC32)));
                    logi(String.format(locale, "Checksum received (Offset = %d, CRC = %08X)", Integer.valueOf(checksum.offset), Integer.valueOf(checksum.CRC32)));
                    if (((int) (((ArchiveInputStream) this.mFirmwareStream).getCrc32() & 4294967295L)) == checksum.CRC32) {
                        logi("Executing data object (Op Code = 4)");
                        writeExecute();
                        this.mService.sendLogBroadcast(10, "Data object executed");
                        i14 = 1;
                        i7++;
                    } else {
                        if (i14 >= 3) {
                            loge("CRC does not match!");
                            this.mService.sendLogBroadcast(20, "CRC does not match!");
                            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_CRC_ERROR);
                            return;
                        }
                        i14++;
                        logi("CRC does not match! Retrying...(" + i14 + "/3)");
                        this.mService.sendLogBroadcast(15, "CRC does not match! Retrying...(" + i14 + "/3)");
                        try {
                            this.mFirmwareStream.reset();
                            this.mProgressInfo.setBytesSent(checksum.offset - objectInfoSelectObject.maxSize);
                        } catch (IOException e2) {
                            loge("Error while resetting the firmware stream", e2);
                            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                            return;
                        }
                    }
                } catch (DeviceDisconnectedException e3) {
                    e3.loge("Disconnected while sending data");
                    throw r0;
                }
            }
        } else {
            logi("Executing data object (Op Code = 4)");
            writeExecute();
            this.mService.sendLogBroadcast(10, "Data object executed");
        }
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        logi("Transfer of " + (this.mProgressInfo.getBytesSent() - objectInfoSelectObject.offset) + " bytes has taken " + jElapsedRealtime2 + " ms");
        this.mService.sendLogBroadcast(10, "Upload completed in " + jElapsedRealtime2 + " ms");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        byte b;
        if (bArr != null && bArr.length >= 3 && bArr[0] == OP_CODE_RESPONSE_CODE_KEY && bArr[1] == i && ((b = bArr[2]) == 1 || b == 2 || b == 3 || b == 4 || b == 5 || b == 7 || b == 10 || b == 11)) {
            return b;
        }
        throw new UnknownResponseException("Invalid response received", bArr, OP_CODE_RESPONSE_CODE_KEY, i);
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    private void setPacketReceiptNotifications(int i) throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i + ")");
        byte[] bArr = OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
        setNumberOfPackets(bArr, i);
        writeOpCode(this.mControlPointCharacteristic, bArr);
        int statusCode = getStatusCode(readNotificationResponse(), 2);
        if (statusCode != 1) {
            throw new RemoteDfuException("Sending the number of packets failed", statusCode);
        }
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        writeOpCode(bluetoothGattCharacteristic, bArr, false);
    }

    private void writeCreateRequest(int i, int i2) throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to create object: device disconnected");
        }
        byte[] bArr = i == 1 ? OP_CODE_CREATE_COMMAND : OP_CODE_CREATE_DATA;
        setObjectSize(bArr, i2);
        writeOpCode(this.mControlPointCharacteristic, bArr);
        int statusCode = getStatusCode(readNotificationResponse(), 1);
        if (statusCode != 1) {
            throw new RemoteDfuException("Creating Command object failed", statusCode);
        }
    }

    private ErrorMessage readExtendedError() throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
        }
        this.mRemoteErrorOccurred = false;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mControlPointCharacteristic;
        writeOpCode(bluetoothGattCharacteristic, OP_CODE_READ_ERROR);
        if (getStatusCode(readNotificationResponse(), 5) != 1) {
            return null;
        }
        int iIntValue = bluetoothGattCharacteristic.getIntValue(18, 3).intValue();
        int iIntValue2 = bluetoothGattCharacteristic.getIntValue(18, 5).intValue();
        if (iIntValue == 0 && iIntValue2 == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bluetoothGattCharacteristic.getStringValue(7));
        while (iIntValue2 - sb.length() > 0) {
            writeOpCode(bluetoothGattCharacteristic, OP_CODE_READ_ERROR);
            readNotificationResponse();
            sb.append(bluetoothGattCharacteristic.getStringValue(3));
            if (bluetoothGattCharacteristic.getValue()[bluetoothGattCharacteristic.getValue().length - 1] == 0) {
                break;
            }
        }
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.code = iIntValue;
        errorMessage.message = sb.toString();
        return errorMessage;
    }

    private ObjectInfo selectObject(int i) throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
        }
        byte[] bArr = OP_CODE_SELECT_OBJECT;
        bArr[1] = (byte) i;
        writeOpCode(this.mControlPointCharacteristic, bArr);
        int statusCode = getStatusCode(readNotificationResponse(), 6);
        if (statusCode != 1) {
            throw new RemoteDfuException("Selecting object failed", statusCode);
        }
        ObjectInfo objectInfo = new ObjectInfo();
        objectInfo.maxSize = this.mControlPointCharacteristic.getIntValue(20, 3).intValue();
        objectInfo.offset = this.mControlPointCharacteristic.getIntValue(20, 7).intValue();
        objectInfo.CRC32 = this.mControlPointCharacteristic.getIntValue(20, 11).intValue();
        return objectInfo;
    }

    private ObjectChecksum readChecksum() throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_CALCULATE_CHECKSUM);
        int statusCode = getStatusCode(readNotificationResponse(), 3);
        if (statusCode != 1) {
            throw new RemoteDfuException("Receiving Checksum failed", statusCode);
        }
        ObjectChecksum objectChecksum = new ObjectChecksum();
        objectChecksum.offset = this.mControlPointCharacteristic.getIntValue(20, 3).intValue();
        objectChecksum.CRC32 = this.mControlPointCharacteristic.getIntValue(20, 7).intValue();
        return objectChecksum;
    }

    private void writeExecute() throws UnknownResponseException, DeviceDisconnectedException, UploadAbortedException, DfuException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_EXECUTE);
        int statusCode = getStatusCode(readNotificationResponse(), 4);
        if (statusCode != 1) {
            throw new RemoteDfuException("Executing object failed", statusCode);
        }
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public boolean hasRequiredService(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.getService(DFU_SERVICE_UUID) != null;
    }

    @Override // vpno.nordicsemi.android.dfu.BaseDfuImpl, vpno.nordicsemi.android.dfu.DfuService
    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2) {
        if (inputStream2 != null) {
            return super.initialize(intent, bluetoothGatt, i, inputStream, inputStream2);
        }
        this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
        this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
        return false;
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public boolean hasRequiredCharacteristics(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        this.mControlPointCharacteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID);
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(DFU_PACKET_UUID);
        this.mPacketCharacteristic = characteristic;
        return (this.mControlPointCharacteristic == null || characteristic == null) ? false : true;
    }

    @Override // vpno.nordicsemi.android.dfu.BaseDfuImpl
    protected BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
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
    /* JADX WARN: Type inference failed for: r0v12, types: [vpno.nordicsemi.android.dfu.DfuBaseService] */
    /* JADX WARN: Type inference failed for: r0v52, types: [vpno.nordicsemi.android.dfu.BaseCustomDfuImpl] */
    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void performDfu(Intent intent) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        DfuBaseService dfuBaseService;
        int i;
        String str;
        this.mProgressInfo.setProgress(-2);
        this.mService.waitFor(1000);
        BluetoothGatt bluetoothGatt = this.mGatt;
        enableCCCD(this.mControlPointCharacteristic, 1);
        this.mService.sendLogBroadcast(10, "Notifications enabled");
        RemoteDfuException remoteDfuException = this.mService;
        remoteDfuException.waitFor(1000);
        try {
            sendInitPacket(bluetoothGatt);
            sendFirmware(bluetoothGatt);
            this.mProgressInfo.setProgress(-5);
            this.mService.waitUntilDisconnected();
            this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
            remoteDfuException = this;
            remoteDfuException.finalize(intent, false);
        } catch (RemoteDfuException unused) {
            RemoteDfuException remoteDfuException2 = remoteDfuException;
            int errorNumber = remoteDfuException2.getErrorNumber() | DfuBaseService.ERROR_REMOTE_MASK;
            loge(remoteDfuException2.getMessage());
            this.mService.sendLogBroadcast(20, String.format("Remote DFU Error: %s", SecureDfuError.parse(errorNumber)));
            if (remoteDfuException.getErrorNumber() == 11) {
                try {
                    ErrorMessage extendedError = readExtendedError();
                    if (extendedError != null) {
                        logi("Error details: " + extendedError.message + " (Code = " + extendedError.code + ")");
                        dfuBaseService = this.mService;
                        i = 20;
                        str = "Details: " + extendedError.message + " (Code = " + extendedError.code + ")";
                    } else {
                        logi("Reading Error details not supported");
                        dfuBaseService = this.mService;
                        i = 15;
                        str = "Reading Error details not supported";
                    }
                    dfuBaseService.sendLogBroadcast(i, str);
                } catch (Exception e) {
                    loge("Reading Error details failed", e);
                    this.mService.sendLogBroadcast(15, "Reading Error details failed");
                }
            }
            this.mService.terminateConnection(bluetoothGatt, errorNumber);
        } catch (UnknownResponseException e2) {
            loge(e2.getMessage());
            this.mService.sendLogBroadcast(20, e2.getMessage());
            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INVALID_RESPONSE);
        }
    }
}
