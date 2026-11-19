package vpno.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import vpno.nordicsemi.android.dfu.internal.ArchiveInputStream;
import vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import vpno.nordicsemi.android.dfu.internal.exception.DfuException;
import vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/BaseDfuImpl.class */
abstract class BaseDfuImpl implements DfuService {
    private static final String TAG = "DfuImpl";
    protected static final int NOTIFICATIONS = 1;
    protected static final int INDICATIONS = 2;
    protected static final int MAX_PACKET_SIZE = 20;
    protected InputStream mFirmwareStream;
    protected InputStream mInitPacketStream;
    protected BluetoothGatt mGatt;
    protected int mFileType;
    protected boolean mPaused;
    protected boolean mAborted;
    protected boolean mRequestCompleted;
    protected boolean mResetRequestSent;
    protected int mError;
    protected DfuBaseService mService;
    protected DfuProgressInfo mProgressInfo;
    protected int mImageSizeInBytes;
    protected int mInitPacketSizeInBytes;
    protected static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = new UUID(26392574038016L, -9223371485494954757L);
    protected static final UUID SERVICE_CHANGED_UUID = new UUID(46200963207168L, -9223371485494954757L);
    protected static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, -9223371485494954757L);
    protected static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    protected final Object mLock = new Object();
    protected byte[] mReceivedData = null;
    protected final byte[] mBuffer = new byte[20];
    protected boolean mConnected = true;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/BaseDfuImpl$BaseBluetoothGattCallback.class */
    protected class BaseBluetoothGattCallback extends BluetoothGattCallback {
        protected BaseBluetoothGattCallback() {
        }

        public void onDisconnected() {
            BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
            baseDfuImpl.mConnected = false;
            baseDfuImpl.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "Read Response received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                BaseDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
                BaseDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseDfuImpl.this.loge("Characteristic read Error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                BaseDfuImpl.this.loge("Descriptor read Error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            } else if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "Read Response received from descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                    BaseDfuImpl.this.mRequestCompleted = true;
                } else {
                    BaseDfuImpl.this.loge("Unknown descriptor read");
                }
            }
            BaseDfuImpl.this.notifyLock();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                BaseDfuImpl.this.loge("Descriptor write Error: " + i);
                BaseDfuImpl.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
            } else if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                BaseDfuImpl.this.mService.sendLogBroadcast(5, "Data written to descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                    BaseDfuImpl.this.mService.sendLogBroadcast(1, "Indications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                } else {
                    BaseDfuImpl.this.mService.sendLogBroadcast(1, "Notifications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                }
            }
            BaseDfuImpl.this.notifyLock();
        }

        protected String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return parse(bluetoothGattCharacteristic.getValue());
        }

        protected String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return parse(bluetoothGattDescriptor.getValue());
        }

        private String parse(byte[] bArr) {
            int length;
            if (bArr == null || (length = bArr.length) == 0) {
                return "";
            }
            char[] cArr = new char[(length * 3) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = i;
                byte b = bArr[i];
                int i3 = i * 3;
                char[] cArr2 = BaseDfuImpl.HEX_ARRAY;
                cArr[i3] = cArr2[(b & 255) >>> 4];
                cArr[i3 + 1] = cArr2[b & 15];
                if (i2 != length - 1) {
                    cArr[i3 + 2] = '-';
                }
            }
            return new String(cArr);
        }
    }

    BaseDfuImpl(DfuBaseService dfuBaseService) {
        this.mService = dfuBaseService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v35, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v40 */
    private boolean isServiceChangedCCCDEnabled() throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattDescriptor descriptor;
        ?? r0;
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
        }
        BluetoothGatt bluetoothGatt = this.mGatt;
        BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(SERVICE_CHANGED_UUID)) == null || (descriptor = characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG)) == null) {
            return false;
        }
        this.mRequestCompleted = false;
        this.mError = 0;
        logi("Reading Service Changed CCCD value...");
        this.mService.sendLogBroadcast(1, "Reading Service Changed CCCD value...");
        this.mService.sendLogBroadcast(0, "gatt.readDescriptor(" + descriptor.getUuid() + ")");
        bluetoothGatt.readDescriptor(descriptor);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((r0.mRequestCompleted || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                            break;
                        }
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
                    throw new DfuException("Unable to read Service Changed CCCD", this.mError);
                }
                if (!this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
                }
                if (descriptor.getValue() != null && descriptor.getValue().length == 2) {
                    byte b = descriptor.getValue()[0];
                    byte[] bArr = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
                    if (b == bArr[0] && descriptor.getValue()[1] == bArr[1]) {
                        return true;
                    }
                }
                return false;
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    private boolean createBondApi18(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            if (method == null) {
                return false;
            }
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.w(TAG, "An exception occurred while creating bond", e);
            return false;
        }
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void release() {
        this.mService = null;
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void pause() {
        this.mPaused = true;
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void resume() {
        this.mPaused = false;
        notifyLock();
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void abort() {
        this.mPaused = false;
        this.mAborted = true;
        notifyLock();
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public void onBondStateChanged(int i) {
        this.mRequestCompleted = true;
        notifyLock();
    }

    @Override // vpno.nordicsemi.android.dfu.DfuService
    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        int iAvailable;
        int iAvailable2;
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        this.mGatt = bluetoothGatt;
        this.mFileType = i;
        this.mFirmwareStream = inputStream;
        this.mInitPacketStream = inputStream2;
        int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_PART_CURRENT, 1);
        int intExtra2 = intent.getIntExtra(DfuBaseService.EXTRA_PARTS_TOTAL, 1);
        if (i > 4) {
            logw("DFU target does not support (SD/BL)+App update, splitting into 2 parts");
            this.mService.sendLogBroadcast(15, "Sending system components");
            int i2 = this.mFileType & (-5);
            this.mFileType = i2;
            ((ArchiveInputStream) this.mFirmwareStream).setContentType(i2);
            intExtra2 = 2;
        }
        if (intExtra == 2) {
            this.mService.sendLogBroadcast(15, "Sending application");
        }
        try {
            iAvailable = inputStream2.available();
        } catch (Exception unused) {
            iAvailable = 0;
        }
        this.mInitPacketSizeInBytes = iAvailable;
        try {
            iAvailable2 = inputStream.available();
        } catch (Exception unused2) {
            iAvailable2 = 0;
        }
        this.mImageSizeInBytes = iAvailable2;
        this.mProgressInfo = this.mService.mProgressInfo.init(iAvailable2, intExtra, intExtra2);
        if (bluetoothGatt.getDevice().getBondState() != 12 || (service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID)) == null || (characteristic = service.getCharacteristic(SERVICE_CHANGED_UUID)) == null) {
            return true;
        }
        if (isServiceChangedCCCDEnabled()) {
            this.mService.sendLogBroadcast(10, "Service Changed indications enabled");
            return true;
        }
        enableCCCD(characteristic, 2);
        this.mService.sendLogBroadcast(10, "Service Changed indications enabled");
        if (!intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false) || (this.mFileType & 1) != 0) {
            return true;
        }
        this.mService.sendLogBroadcast(1, "Restarting service...");
        this.mService.disconnect(bluetoothGatt);
        this.mService.close(bluetoothGatt);
        logi("Restarting service");
        this.mService.sendLogBroadcast(1, "Restarting service...");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        this.mService.startService(intent2);
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Throwable] */
    protected void notifyLock() {
        ?? r0 = this.mLock;
        synchronized (r0) {
            try {
                this.mLock.notifyAll();
            } catch (Throwable th) {
                throw r0;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6 */
    protected void waitIfPaused() {
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    ?? r0 = this;
                    try {
                        if (!r0.mPaused) {
                            r0 = obj;
                            return;
                        }
                        this.mLock.wait();
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    protected abstract BaseBluetoothGattCallback getGattCallback();

    /* JADX WARN: Code duplicated, block: B:49:0x0189 A[Catch: all -> 0x017f, TRY_ENTER, TryCatch #1 {all -> 0x017f, blocks: (B:35:0x0148, B:37:0x014f, B:39:0x015e, B:41:0x0168, B:43:0x0172, B:55:0x019b, B:47:0x0182, B:49:0x0189, B:51:0x0190), top: B:79:0x0148 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0190 A[Catch: all -> 0x017f, TRY_ENTER, TryCatch #1 {all -> 0x017f, blocks: (B:35:0x0148, B:37:0x014f, B:39:0x015e, B:41:0x0168, B:43:0x0172, B:55:0x019b, B:47:0x0182, B:49:0x0189, B:51:0x0190), top: B:79:0x0148 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x019a  */
    /* JADX WARN: Code duplicated, block: B:64:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:66:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:68:0x01bf A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:69:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:71:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:73:0x020d  */
    /* JADX WARN: Code duplicated, block: B:79:0x0148 A[EXC_TOP_SPLITTER, PHI: r0
  0x0148: PHI (r0v25 ??) = (r0v58 ??), (r0v59 ??), (r0v60 ??), (r0v61 ??), (r0v62 ??) binds: [B:33:0x0142, B:44:0x0179, B:48:0x0186, B:50:0x018d, B:52:0x0194] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x014c -> B:37:0x014f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x015b -> B:47:0x0182). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0165 -> B:47:0x0182). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x016f -> B:47:0x0182). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0179 -> B:47:0x0182). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    protected void enableCCCD(android.bluetooth.BluetoothGattCharacteristic r10, int r11) throws vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException, vpno.nordicsemi.android.dfu.internal.exception.DfuException {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: vpno.nordicsemi.android.dfu.BaseDfuImpl.enableCCCD(android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v20 */
    protected void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, boolean z) throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        this.mReceivedData = null;
        this.mError = 0;
        this.mRequestCompleted = false;
        this.mResetRequestSent = z;
        bluetoothGattCharacteristic.setWriteType(2);
        bluetoothGattCharacteristic.setValue(bArr);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((r0.mRequestCompleted || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
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
                boolean z2 = this.mResetRequestSent;
                if (!z2 && this.mError != 0) {
                    throw new DfuException("Unable to write Op Code " + ((int) bArr[0]), this.mError);
                }
                if (!z2 && !this.mConnected) {
                    throw new DeviceDisconnectedException("Unable to write Op Code " + ((int) bArr[0]) + ": device disconnected");
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v16 */
    @SuppressLint({"NewApi"})
    protected boolean createBond() {
        boolean zCreateBondApi18;
        ?? r0;
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 12) {
            return true;
        }
        this.mRequestCompleted = false;
        this.mService.sendLogBroadcast(1, "Starting pairing...");
        if (Build.VERSION.SDK_INT >= 19) {
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond()");
            zCreateBondApi18 = device.createBond();
        } else {
            zCreateBondApi18 = createBondApi18(device);
        }
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if (r0.mRequestCompleted || this.mAborted) {
                            break;
                        }
                        this.mLock.wait();
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
                r0 = obj;
                return zCreateBondApi18;
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19 */
    protected boolean removeBond() {
        boolean z;
        ?? r0;
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 10) {
            return true;
        }
        this.mService.sendLogBroadcast(1, "Removing bond information...");
        try {
            Method method = device.getClass().getMethod("removeBond", new Class[0]);
            if (method != null) {
                this.mRequestCompleted = false;
                this.mService.sendLogBroadcast(0, "gatt.getDevice().removeBond() (hidden)");
                ((Boolean) method.invoke(device, new Object[0])).booleanValue();
                try {
                    Object obj = this.mLock;
                    synchronized (obj) {
                        while (true) {
                            r0 = this;
                            try {
                                if (r0.mRequestCompleted || this.mAborted) {
                                    break;
                                }
                                this.mLock.wait();
                            } catch (Throwable unused) {
                                throw r0;
                            }
                        }
                        r0 = obj;
                    }
                } catch (InterruptedException e) {
                    loge("Sleeping interrupted", e);
                }
            }
            z = true;
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while removing bond information", e2);
            z = false;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [vpno.nordicsemi.android.dfu.BaseDfuImpl] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19 */
    protected byte[] readNotificationResponse() throws DeviceDisconnectedException, UploadAbortedException, DfuException {
        ?? r0;
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if ((r0.mReceivedData != null || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                            break;
                        }
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
                    throw new DfuException("Unable to write Op Code", this.mError);
                }
                if (this.mConnected) {
                    return this.mReceivedData;
                }
                throw new DeviceDisconnectedException("Unable to write Op Code: device disconnected");
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    protected String parse(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return "";
        }
        char[] cArr = new char[(length * 3) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = i;
            byte b = bArr[i];
            int i3 = i * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[(b & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b & 15];
            if (i2 != length - 1) {
                cArr[i3 + 2] = '-';
            }
        }
        return new String(cArr);
    }

    protected void loge(String str) {
        Log.e(TAG, str);
    }

    protected void logw(String str) {
        Log.w(TAG, str);
    }

    protected void logi(String str) {
        Log.i(TAG, str);
    }

    protected void logd(String str) {
        Log.d(TAG, str);
    }

    protected void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }
}
