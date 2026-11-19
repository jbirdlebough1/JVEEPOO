package vpno.nordicsemi.android.dfu;

import android.R;
import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import vpno.nordicsemi.android.dfu.internal.ArchiveInputStream;
import vpno.nordicsemi.android.dfu.internal.HexInputStream;
import vpno.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import vpno.nordicsemi.android.dfu.internal.exception.DfuException;
import vpno.nordicsemi.android.dfu.internal.exception.SizeValidationException;
import vpno.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import vpno.nordicsemi.android.error.GattError;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuBaseService.class */
public abstract class DfuBaseService extends IntentService implements DfuProgressInfo.ProgressListener {
    private static final String TAG = "DfuBaseService";
    static final boolean DEBUG = true;
    public static final int NOTIFICATION_ID = 283;
    public static final String EXTRA_DEVICE_ADDRESS = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS";
    public static final String EXTRA_DEVICE_NAME = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME";
    public static final String EXTRA_DISABLE_NOTIFICATION = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION";
    private static final String EXTRA_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT";
    public static final String EXTRA_RESTORE_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND";
    public static final String EXTRA_KEEP_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND";
    public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH";
    public static final String EXTRA_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI";
    public static final String EXTRA_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID";
    public static final String EXTRA_INIT_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH";
    public static final String EXTRA_INIT_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI";
    public static final String EXTRA_INIT_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID";
    public static final String EXTRA_FILE_MIME_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE";
    public static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_TYPE_ZIP = "application/zip";
    public static final String EXTRA_FILE_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE";
    public static final int TYPE_SOFT_DEVICE = 1;
    public static final int TYPE_BOOTLOADER = 2;
    public static final int TYPE_APPLICATION = 4;
    public static final int TYPE_AUTO = 0;
    public static final String EXTRA_DATA = "no.nordicsemi.android.dfu.extra.EXTRA_DATA";
    public static final String EXTRA_PROGRESS = "no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS";
    public static final String EXTRA_PART_CURRENT = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT";
    public static final String EXTRA_PARTS_TOTAL = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL";
    public static final String EXTRA_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS";
    public static final String EXTRA_AVG_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS";
    public static final String BROADCAST_PROGRESS = "no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS";
    public static final int PROGRESS_CONNECTING = -1;
    public static final int PROGRESS_STARTING = -2;
    public static final int PROGRESS_ENABLING_DFU_MODE = -3;
    public static final int PROGRESS_VALIDATING = -4;
    public static final int PROGRESS_DISCONNECTING = -5;
    public static final int PROGRESS_COMPLETED = -6;
    public static final int PROGRESS_ABORTED = -7;
    public static final String BROADCAST_ERROR = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR";
    public static final String EXTRA_ERROR_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE";
    public static final int ERROR_TYPE_OTHER = 0;
    public static final int ERROR_TYPE_COMMUNICATION_STATE = 1;
    public static final int ERROR_TYPE_COMMUNICATION = 2;
    public static final int ERROR_TYPE_DFU_REMOTE = 3;
    public static final int ERROR_MASK = 4096;
    public static final int ERROR_DEVICE_DISCONNECTED = 4096;
    public static final int ERROR_FILE_NOT_FOUND = 4097;
    public static final int ERROR_FILE_ERROR = 4098;
    public static final int ERROR_FILE_INVALID = 4099;
    public static final int ERROR_FILE_IO_EXCEPTION = 4100;
    public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 4101;
    public static final int ERROR_SERVICE_NOT_FOUND = 4102;
    public static final int ERROR_CHARACTERISTICS_NOT_FOUND = 4103;
    public static final int ERROR_INVALID_RESPONSE = 4104;
    public static final int ERROR_FILE_TYPE_UNSUPPORTED = 4105;
    public static final int ERROR_BLUETOOTH_DISABLED = 4106;
    public static final int ERROR_INIT_PACKET_REQUIRED = 4107;
    public static final int ERROR_FILE_SIZE_INVALID = 4108;
    public static final int ERROR_CRC_ERROR = 4109;
    public static final int ERROR_REMOTE_MASK = 8192;
    public static final int ERROR_CONNECTION_MASK = 16384;
    public static final int ERROR_CONNECTION_STATE_MASK = 32768;
    public static final String BROADCAST_LOG = "no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG";
    public static final String EXTRA_LOG_MESSAGE = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO";
    public static final String EXTRA_LOG_LEVEL = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL";
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_INFO = 5;
    public static final int LOG_LEVEL_APPLICATION = 10;
    public static final int LOG_LEVEL_WARNING = 15;
    public static final int LOG_LEVEL_ERROR = 20;
    public static final String BROADCAST_ACTION = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION";
    public static final String EXTRA_ACTION = "no.nordicsemi.android.dfu.extra.EXTRA_ACTION";
    public static final int ACTION_PAUSE = 0;
    public static final int ACTION_RESUME = 1;
    public static final int ACTION_ABORT = 2;

    @Deprecated
    public static final int DFU_STATUS_SUCCESS = 1;

    @Deprecated
    public static final int DFU_STATUS_INVALID_STATE = 2;

    @Deprecated
    public static final int DFU_STATUS_NOT_SUPPORTED = 3;

    @Deprecated
    public static final int DFU_STATUS_DATA_SIZE_EXCEEDS_LIMIT = 4;

    @Deprecated
    public static final int DFU_STATUS_CRC_ERROR = 5;

    @Deprecated
    public static final int DFU_STATUS_OPERATION_FAILED = 6;
    protected static final int STATE_DISCONNECTED = 0;
    protected static final int STATE_CONNECTING = -1;
    protected static final int STATE_CONNECTED = -2;
    protected static final int STATE_CONNECTED_AND_READY = -3;
    protected static final int STATE_DISCONNECTING = -4;
    protected static final int STATE_CLOSED = -5;
    private final Object mLock;
    private BluetoothAdapter mBluetoothAdapter;
    private String mDeviceAddress;
    private String mDeviceName;
    private boolean mDisableNotification;
    protected int mConnectionState;
    private int mError;
    private int mLastProgress;
    DfuProgressInfo mProgressInfo;
    private long mLastNotificationTime;
    private boolean mAborted;
    private BaseDfuImpl mDfuServiceImpl;
    private final BroadcastReceiver mDfuActionReceiver;
    private final BroadcastReceiver mBondStateBroadcastReceiver;
    private final BroadcastReceiver mConnectionStateBroadcastReceiver;
    private final BluetoothGattCallback mGattCallback;

    public DfuBaseService() {
        super(TAG);
        this.mLock = new Object();
        this.mLastProgress = -1;
        this.mDfuActionReceiver = new BroadcastReceiver() { // from class: vpno.nordicsemi.android.dfu.DfuBaseService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_ACTION, 0);
                DfuBaseService.this.logi("User action received: " + intExtra);
                if (intExtra == 0) {
                    BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                    if (baseDfuImpl != null) {
                        baseDfuImpl.pause();
                        return;
                    }
                    return;
                }
                if (intExtra == 1) {
                    BaseDfuImpl baseDfuImpl2 = DfuBaseService.this.mDfuServiceImpl;
                    if (baseDfuImpl2 != null) {
                        baseDfuImpl2.resume();
                        return;
                    }
                    return;
                }
                if (intExtra != 2) {
                    return;
                }
                DfuBaseService dfuBaseService = DfuBaseService.this;
                dfuBaseService.mAborted = true;
                BaseDfuImpl baseDfuImpl3 = dfuBaseService.mDfuServiceImpl;
                if (baseDfuImpl3 != null) {
                    baseDfuImpl3.abort();
                }
            }
        };
        this.mBondStateBroadcastReceiver = new BroadcastReceiver() { // from class: vpno.nordicsemi.android.dfu.DfuBaseService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra;
                BaseDfuImpl baseDfuImpl;
                if (!((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress) || (intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) == 11 || (baseDfuImpl = DfuBaseService.this.mDfuServiceImpl) == null) {
                    return;
                }
                baseDfuImpl.onBondStateChanged(intExtra);
            }
        };
        this.mConnectionStateBroadcastReceiver = new BroadcastReceiver() { // from class: vpno.nordicsemi.android.dfu.DfuBaseService.3
            /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, java.lang.Throwable] */
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress)) {
                    DfuBaseService.this.logi("Action received: " + intent.getAction());
                    DfuBaseService dfuBaseService = DfuBaseService.this;
                    dfuBaseService.mConnectionState = 0;
                    BaseDfuImpl baseDfuImpl = dfuBaseService.mDfuServiceImpl;
                    if (baseDfuImpl != null) {
                        baseDfuImpl.getGattCallback().onDisconnected();
                    }
                    ?? r0 = DfuBaseService.this.mLock;
                    synchronized (r0) {
                        try {
                            DfuBaseService.this.mLock.notifyAll();
                        } catch (Throwable th) {
                            throw r0;
                        }
                    }
                }
            }
        };
        this.mGattCallback = new BluetoothGattCallback() { // from class: vpno.nordicsemi.android.dfu.DfuBaseService.4
            /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, java.lang.Throwable] */
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                if (i != 0) {
                    DfuBaseService.this.loge("Connection state change Error: " + i + " newState: " + i2);
                    if (i2 == 0) {
                        DfuBaseService dfuBaseService = DfuBaseService.this;
                        dfuBaseService.mConnectionState = 0;
                        BaseDfuImpl baseDfuImpl = dfuBaseService.mDfuServiceImpl;
                        if (baseDfuImpl != null) {
                            baseDfuImpl.getGattCallback().onDisconnected();
                        }
                    }
                    DfuBaseService.this.mError = i | DfuBaseService.ERROR_CONNECTION_STATE_MASK;
                } else if (i2 == 2) {
                    DfuBaseService.this.logi("Connected to GATT server");
                    DfuBaseService.this.sendLogBroadcast(5, "Connected to " + DfuBaseService.this.mDeviceAddress);
                    DfuBaseService.this.mConnectionState = -2;
                    if (bluetoothGatt.getDevice().getBondState() == 12) {
                        DfuBaseService.this.logd("Waiting 1600 ms for a possible Service Changed indication...");
                        DfuBaseService.this.waitFor(1600);
                    }
                    DfuBaseService.this.sendLogBroadcast(1, "Discovering services...");
                    DfuBaseService.this.sendLogBroadcast(0, "gatt.discoverServices()");
                    boolean zDiscoverServices = bluetoothGatt.discoverServices();
                    DfuBaseService.this.logi("Attempting to start service discovery... " + (zDiscoverServices ? "succeed" : "failed"));
                    if (zDiscoverServices) {
                        return;
                    } else {
                        DfuBaseService.this.mError = DfuBaseService.ERROR_SERVICE_DISCOVERY_NOT_STARTED;
                    }
                } else if (i2 == 0) {
                    DfuBaseService.this.logi("Disconnected from GATT server");
                    DfuBaseService dfuBaseService2 = DfuBaseService.this;
                    dfuBaseService2.mConnectionState = 0;
                    BaseDfuImpl baseDfuImpl2 = dfuBaseService2.mDfuServiceImpl;
                    if (baseDfuImpl2 != null) {
                        baseDfuImpl2.getGattCallback().onDisconnected();
                    }
                }
                ?? r0 = DfuBaseService.this.mLock;
                synchronized (r0) {
                    try {
                        DfuBaseService.this.mLock.notifyAll();
                    } catch (Throwable th) {
                        throw r0;
                    }
                }
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, java.lang.Throwable] */
            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                if (i == 0) {
                    DfuBaseService.this.logi("Services discovered");
                    DfuBaseService.this.mConnectionState = -3;
                } else {
                    DfuBaseService.this.loge("Service discovery Error: " + i);
                    DfuBaseService.this.mError = i | DfuBaseService.ERROR_CONNECTION_MASK;
                }
                ?? r0 = DfuBaseService.this.mLock;
                synchronized (r0) {
                    try {
                        DfuBaseService.this.mLock.notifyAll();
                    } catch (Throwable th) {
                        throw r0;
                    }
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                if (baseDfuImpl != null) {
                    baseDfuImpl.getGattCallback().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                if (baseDfuImpl != null) {
                    baseDfuImpl.getGattCallback().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                if (baseDfuImpl != null) {
                    baseDfuImpl.getGattCallback().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                if (baseDfuImpl != null) {
                    baseDfuImpl.getGattCallback().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                BaseDfuImpl baseDfuImpl = DfuBaseService.this.mDfuServiceImpl;
                if (baseDfuImpl != null) {
                    baseDfuImpl.getGattCallback().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
                }
            }
        };
    }

    private static IntentFilter makeDfuActionIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        return intentFilter;
    }

    private InputStream openInputStream(int i, String str, int i2, int i3) throws IOException {
        InputStream inputStreamOpenRawResource = getResources().openRawResource(i);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(inputStreamOpenRawResource, i2, i3);
        }
        inputStreamOpenRawResource.mark(2);
        int i4 = inputStreamOpenRawResource.read();
        inputStreamOpenRawResource.reset();
        return i4 == 58 ? new HexInputStream(inputStreamOpenRawResource, i2) : inputStreamOpenRawResource;
    }

    private void report(int i) {
        sendErrorBroadcast(i);
        if (this.mDisableNotification) {
            return;
        }
        String str = this.mDeviceAddress;
        String str2 = this.mDeviceName;
        String str3 = str2;
        if (str2 == null) {
            str3 = "unnamed device";
        }
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.stat_sys_upload).setOnlyAlertOnce(true).setColor(-65536).setOngoing(false).setContentTitle("Upload failed").setSmallIcon(R.drawable.stat_sys_upload_done).setContentText("DFU process failed").setAutoCancel(true);
        Intent intent = new Intent(this, getNotificationTarget());
        intent.addFlags(268435456);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
        intent.putExtra(EXTRA_DEVICE_NAME, str3);
        intent.putExtra(EXTRA_PROGRESS, i);
        autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
        ((NotificationManager) getSystemService("notification")).notify(NOTIFICATION_ID, autoCancel.build());
    }

    private void sendProgressBroadcast(DfuProgressInfo dfuProgressInfo) {
        Intent intent = new Intent(BROADCAST_PROGRESS);
        intent.putExtra(EXTRA_DATA, dfuProgressInfo.getProgress());
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        intent.putExtra(EXTRA_PART_CURRENT, dfuProgressInfo.getCurrentPart());
        intent.putExtra(EXTRA_PARTS_TOTAL, dfuProgressInfo.getTotalParts());
        intent.putExtra(EXTRA_SPEED_B_PER_MS, dfuProgressInfo.getSpeed());
        intent.putExtra(EXTRA_AVG_SPEED_B_PER_MS, dfuProgressInfo.getAverageSpeed());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendErrorBroadcast(int i) {
        Intent intent = new Intent(BROADCAST_ERROR);
        if ((i & ERROR_CONNECTION_MASK) > 0) {
            intent.putExtra(EXTRA_DATA, i & (-16385));
            intent.putExtra(EXTRA_ERROR_TYPE, 2);
        } else if ((i & ERROR_CONNECTION_STATE_MASK) > 0) {
            intent.putExtra(EXTRA_DATA, i & (-32769));
            intent.putExtra(EXTRA_ERROR_TYPE, 1);
        } else if ((i & ERROR_REMOTE_MASK) > 0) {
            intent.putExtra(EXTRA_DATA, i);
            intent.putExtra(EXTRA_ERROR_TYPE, 3);
        } else {
            intent.putExtra(EXTRA_DATA, i);
            intent.putExtra(EXTRA_ERROR_TYPE, 0);
        }
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private boolean initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            loge("Unable to initialize BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.mBluetoothAdapter = adapter;
        if (adapter != null) {
            return true;
        }
        loge("Unable to obtain a BluetoothAdapter.");
        return false;
    }

    private void loge(String str) {
        Log.e(TAG, str);
    }

    private void logw(String str) {
        Log.w(TAG, str);
    }

    private void logi(String str) {
        Log.i(TAG, str);
    }

    private void logd(String str) {
        Log.d(TAG, str);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        initialize();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilterMakeDfuActionIntentFilter = makeDfuActionIntentFilter();
        localBroadcastManager.registerReceiver(this.mDfuActionReceiver, intentFilterMakeDfuActionIntentFilter);
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED");
        IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.mBondStateBroadcastReceiver, intentFilter2, 2);
            registerReceiver(this.mDfuActionReceiver, intentFilterMakeDfuActionIntentFilter, 2);
            registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.mDfuActionReceiver, intentFilterMakeDfuActionIntentFilter);
            registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter);
            registerReceiver(this.mBondStateBroadcastReceiver, intentFilter2);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mConnectionStateBroadcastReceiver);
        unregisterReceiver(this.mBondStateBroadcastReceiver);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x022a A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:108:0x0249 A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x0267 A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x026f A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TRY_LEAVE, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:121:0x0280 A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:123:0x0288 A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TRY_LEAVE, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x02ee A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x0313 A[Catch: all -> 0x0159, TRY_ENTER, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x031a A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:153:0x033f A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:156:0x0348  */
    /* JADX WARN: Code duplicated, block: B:158:0x034f  */
    /* JADX WARN: Code duplicated, block: B:166:0x0399  */
    /* JADX WARN: Code duplicated, block: B:177:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:181:0x03fa A[Catch: all -> 0x0159, TRY_ENTER, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:190:0x043a A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:196:0x044f A[Catch: all -> 0x0159, TRY_ENTER, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:198:0x0456 A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x047b A[Catch: all -> 0x0159, TRY_ENTER, TRY_LEAVE, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:214:0x04a4 A[Catch: all -> 0x04d4, DfuException -> 0x04db, DeviceDisconnectedException -> 0x04e2, UploadAbortedException -> 0x04e9, TRY_ENTER, TryCatch #22 {DeviceDisconnectedException -> 0x04e2, DfuException -> 0x04db, UploadAbortedException -> 0x04e9, all -> 0x04d4, blocks: (B:211:0x0496, B:214:0x04a4, B:218:0x04b9, B:219:0x04c7, B:233:0x04f8, B:235:0x050d, B:241:0x0527), top: B:377:0x0496 }] */
    /* JADX WARN: Code duplicated, block: B:216:0x04af  */
    /* JADX WARN: Code duplicated, block: B:231:0x04f0  */
    /* JADX WARN: Code duplicated, block: B:235:0x050d A[Catch: all -> 0x04d4, DfuException -> 0x04db, DeviceDisconnectedException -> 0x04e2, UploadAbortedException -> 0x04e9, TRY_ENTER, TryCatch #22 {DeviceDisconnectedException -> 0x04e2, DfuException -> 0x04db, UploadAbortedException -> 0x04e9, all -> 0x04d4, blocks: (B:211:0x0496, B:214:0x04a4, B:218:0x04b9, B:219:0x04c7, B:233:0x04f8, B:235:0x050d, B:241:0x0527), top: B:377:0x0496 }] */
    /* JADX WARN: Code duplicated, block: B:243:0x0538 A[Catch: all -> 0x0159, TRY_ENTER, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:276:0x05ed A[Catch: all -> 0x0159, PHI: r12
  0x05ed: PHI (r12v12 vpno.nordicsemi.android.dfu.BaseDfuImpl) = 
  (r12v10 vpno.nordicsemi.android.dfu.BaseDfuImpl)
  (r12v11 vpno.nordicsemi.android.dfu.BaseDfuImpl)
  (r12v13 vpno.nordicsemi.android.dfu.BaseDfuImpl)
 binds: [B:286:0x063a, B:275:0x05ea, B:281:0x0618] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #25 {all -> 0x0159, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:130:0x02b9, B:132:0x02cb, B:134:0x02d7, B:137:0x02ee, B:139:0x02fe, B:140:0x030b, B:144:0x0313, B:146:0x031a, B:148:0x0329, B:149:0x0337, B:153:0x033f, B:159:0x0355, B:160:0x0370, B:162:0x037e, B:164:0x0386, B:165:0x0390, B:175:0x03dd, B:179:0x03ed, B:181:0x03fa, B:182:0x03ff, B:184:0x041a, B:185:0x0425, B:186:0x042c, B:190:0x043a, B:191:0x0441, B:192:0x0447, B:168:0x039f, B:169:0x03ba, B:171:0x03c8, B:173:0x03d0, B:196:0x044f, B:198:0x0456, B:200:0x0466, B:201:0x0473, B:205:0x047b, B:219:0x04c7, B:220:0x04cc, B:237:0x0517, B:243:0x0538, B:293:0x064e, B:295:0x0655, B:276:0x05ed, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189, B:300:0x0663, B:301:0x066e, B:302:0x0686, B:307:0x06a0, B:308:0x06ad, B:309:0x06c5, B:314:0x06df, B:316:0x06f0, B:321:0x070a, B:323:0x071b, B:328:0x0735, B:330:0x0746), top: B:368:0x013a, inners: #25, #23 }] */
    /* JADX WARN: Code duplicated, block: B:296:0x0656 A[Catch: all -> 0x0159, all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TRY_LEAVE, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:377:0x0496 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x019c A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:72:0x01af A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TRY_LEAVE, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:77:0x01c2 A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x01da  */
    /* JADX WARN: Code duplicated, block: B:84:0x01df A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01fd A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TRY_LEAVE, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x020b A[Catch: all -> 0x0159, Exception -> 0x015d, IOException -> 0x0161, SizeValidationException -> 0x0165, FileNotFoundException -> 0x0169, SecurityException -> 0x016d, TRY_ENTER, TryCatch #25 {SizeValidationException -> 0x0165, IOException -> 0x0161, blocks: (B:42:0x013f, B:44:0x0149, B:67:0x019c, B:78:0x01ce, B:80:0x01d5, B:84:0x01df, B:86:0x01e9, B:87:0x01f3, B:89:0x01fd, B:92:0x020b, B:95:0x0218, B:96:0x0222, B:100:0x022a, B:103:0x0237, B:104:0x0241, B:108:0x0249, B:111:0x0256, B:112:0x0260, B:116:0x0267, B:118:0x026f, B:120:0x0275, B:121:0x0280, B:123:0x0288, B:125:0x028e, B:127:0x0297, B:128:0x02b2, B:296:0x0656, B:297:0x0659, B:298:0x0660, B:72:0x01af, B:74:0x01b5, B:77:0x01c2, B:61:0x0176, B:64:0x0189), top: B:376:0x013f, outer: #25 }] */
    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        int i;
        InputStream inputStreamOpenInputStream;
        InputStream inputStreamOpenRawResource;
        FileInputStream fileInputStream;
        int iAvailable;
        BluetoothGatt bluetoothGattConnect;
        int i2;
        int i3;
        String str;
        BaseDfuImpl dfuImpl;
        int i4;
        String str2;
        ArchiveInputStream archiveInputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2;
        String stringExtra = intent.getStringExtra(EXTRA_DEVICE_ADDRESS);
        String stringExtra2 = intent.getStringExtra(EXTRA_DEVICE_NAME);
        boolean booleanExtra = intent.getBooleanExtra(EXTRA_DISABLE_NOTIFICATION, false);
        String stringExtra3 = intent.getStringExtra(EXTRA_FILE_PATH);
        Uri uri = (Uri) intent.getParcelableExtra(EXTRA_FILE_URI);
        int intExtra = intent.getIntExtra(EXTRA_FILE_RES_ID, 0);
        String stringExtra4 = intent.getStringExtra(EXTRA_INIT_FILE_PATH);
        Uri uri2 = (Uri) intent.getParcelableExtra(EXTRA_INIT_FILE_URI);
        int intExtra2 = intent.getIntExtra(EXTRA_INIT_FILE_RES_ID, 0);
        int intExtra3 = intent.getIntExtra(EXTRA_FILE_TYPE, 0);
        if (stringExtra3 != null && intExtra3 == 0) {
            intExtra3 = stringExtra3.toLowerCase(Locale.US).endsWith("zip") ? 0 : 4;
        }
        String stringExtra5 = intent.getStringExtra(EXTRA_FILE_MIME_TYPE);
        String str3 = stringExtra5;
        if (stringExtra5 == null) {
            str3 = intExtra3 == 0 ? MIME_TYPE_ZIP : MIME_TYPE_OCTET_STREAM;
        }
        if ((intExtra3 & (-8)) > 0 || !(MIME_TYPE_ZIP.equals(str3) || MIME_TYPE_OCTET_STREAM.equals(str3))) {
            logw("File type or file mime-type not supported");
            sendLogBroadcast(15, "File type or file mime-type not supported");
            report(ERROR_FILE_TYPE_UNSUPPORTED);
            return;
        }
        if (MIME_TYPE_OCTET_STREAM.equals(str3) && intExtra3 != 1 && intExtra3 != 2 && intExtra3 != 4) {
            logw("Unable to determine file type");
            sendLogBroadcast(15, "Unable to determine file type");
            report(ERROR_FILE_TYPE_UNSUPPORTED);
            return;
        }
        this.mDeviceAddress = stringExtra;
        this.mDeviceName = stringExtra2;
        this.mDisableNotification = booleanExtra;
        this.mConnectionState = 0;
        this.mError = 0;
        try {
            int i5 = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(DfuSettingsConstants.SETTINGS_MBR_SIZE, String.valueOf(4096)));
            i = i5;
            if (i5 < 0) {
                i = 0;
            }
        } catch (NumberFormatException unused) {
            i = 4096;
        }
        sendLogBroadcast(1, "DFU service started");
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        try {
            try {
                try {
                    sendLogBroadcast(1, "Opening file...");
                    if (uri != null) {
                        inputStreamOpenInputStream = openInputStream(uri, str3, i, intExtra3);
                    } else {
                        if (stringExtra3 == null) {
                            if (intExtra > 0) {
                                inputStreamOpenInputStream = openInputStream(intExtra, str3, i, intExtra3);
                            }
                            if (uri2 == null) {
                                inputStreamOpenRawResource = getContentResolver().openInputStream(uri2);
                            } else {
                                if (stringExtra4 != null) {
                                    inputStream2 = fileInputStream;
                                    fileInputStream = new FileInputStream(stringExtra4);
                                } else if (intExtra2 > 0) {
                                    inputStreamOpenRawResource = getResources().openRawResource(intExtra2);
                                }
                                iAvailable = inputStream.available();
                                if (iAvailable % 4 == 0) {
                                    throw new SizeValidationException("The new firmware is not word-aligned.");
                                }
                                if (intExtra3 == 0 && MIME_TYPE_ZIP.equals(str3)) {
                                    intExtra3 = ((ArchiveInputStream) inputStream).getContentType();
                                }
                                if (MIME_TYPE_ZIP.equals(str3)) {
                                    archiveInputStream = (ArchiveInputStream) inputStream;
                                    if ((intExtra3 & 4) <= 0 && archiveInputStream.applicationImageSize() % 4 != 0) {
                                        throw new SizeValidationException("Application firmware is not word-aligned.");
                                    }
                                    if ((intExtra3 & 2) <= 0 && archiveInputStream.bootloaderImageSize() % 4 != 0) {
                                        throw new SizeValidationException("Bootloader firmware is not word-aligned.");
                                    }
                                    if ((intExtra3 & 1) <= 0 && archiveInputStream.softDeviceImageSize() % 4 != 0) {
                                        throw new SizeValidationException("Soft Device firmware is not word-aligned.");
                                    }
                                    if (intExtra3 == 4) {
                                        if (archiveInputStream.getApplicationInit() != null) {
                                            inputStream2 = byteArrayInputStream2;
                                            byteArrayInputStream2 = new ByteArrayInputStream(archiveInputStream.getApplicationInit());
                                        }
                                    } else if (archiveInputStream.getSystemInit() != null) {
                                        inputStream2 = byteArrayInputStream;
                                        byteArrayInputStream = new ByteArrayInputStream(archiveInputStream.getSystemInit());
                                    }
                                }
                                sendLogBroadcast(5, "Image file opened (" + iAvailable + " bytes in total)");
                                waitFor(1000);
                                waitFor(1000);
                                sendLogBroadcast(1, "Connecting to DFU target...");
                                DfuProgressInfo dfuProgressInfo = new DfuProgressInfo(this);
                                this.mProgressInfo = dfuProgressInfo;
                                dfuProgressInfo.setProgress(-1);
                                bluetoothGattConnect = connect(stringExtra);
                                if (bluetoothGattConnect == null) {
                                    InputStream inputStream3 = inputStream;
                                    loge("Bluetooth adapter disabled");
                                    sendLogBroadcast(20, "Bluetooth adapter disabled");
                                    report(ERROR_BLUETOOTH_DISABLED);
                                    try {
                                        inputStream3.close();
                                        return;
                                    } catch (IOException unused2) {
                                        return;
                                    }
                                }
                                if (this.mConnectionState == 0) {
                                    InputStream inputStream4 = inputStream;
                                    loge("Device got disconnected before service discovery finished");
                                    sendLogBroadcast(5, "Disconnected");
                                    terminateConnection(bluetoothGattConnect, 4096);
                                    try {
                                        inputStream4.close();
                                        return;
                                    } catch (IOException unused3) {
                                        return;
                                    }
                                }
                                i2 = this.mError;
                                if (i2 <= 0) {
                                    if ((i2 & ERROR_CONNECTION_STATE_MASK) > 0) {
                                        int i6 = i2 & (-32769);
                                        loge("An Error occurred while connecting to the device:" + i6);
                                        i4 = 20;
                                        Object[] objArr = new Object[2];
                                        objArr[0] = Integer.valueOf(i6);
                                        objArr[1] = GattError.parseConnectionError(i6);
                                        str2 = String.format("Connection failed (0x%02X): %s", objArr);
                                    } else {
                                        int i7 = i2 & (-16385);
                                        loge("An Error occurred during discovering services:" + i7);
                                        i4 = 20;
                                        Object[] objArr2 = new Object[2];
                                        objArr2[0] = Integer.valueOf(i7);
                                        objArr2[1] = GattError.parse(i7);
                                        str2 = String.format("Connection failed (0x%02X): %s", objArr2);
                                    }
                                    sendLogBroadcast(i4, str2);
                                    if (intent.getIntExtra(EXTRA_ATTEMPT, 0) == 0) {
                                        InputStream inputStream5 = inputStream;
                                        terminateConnection(bluetoothGattConnect, this.mError);
                                        try {
                                            inputStream5.close();
                                            return;
                                        } catch (IOException unused4) {
                                            return;
                                        }
                                    }
                                    sendLogBroadcast(15, "Retrying...");
                                    if (this.mConnectionState != 0) {
                                        disconnect(bluetoothGattConnect);
                                    }
                                    InputStream inputStream6 = inputStream;
                                    refreshDeviceCache(bluetoothGattConnect, true);
                                    close(bluetoothGattConnect);
                                    logi("Restarting the service");
                                    Intent intent2 = new Intent();
                                    intent2.fillIn(intent, 24);
                                    intent2.putExtra(EXTRA_ATTEMPT, 1);
                                    startService(intent2);
                                    try {
                                        inputStream6.close();
                                    } catch (IOException unused5) {
                                        return;
                                    }
                                }
                                if (this.mAborted) {
                                    InputStream inputStream7 = inputStream;
                                    logi("Upload aborted");
                                    sendLogBroadcast(15, "Upload aborted");
                                    terminateConnection(bluetoothGattConnect, -7);
                                    try {
                                        inputStream7.close();
                                        return;
                                    } catch (IOException unused6) {
                                        return;
                                    }
                                }
                                intent.putExtra(EXTRA_ATTEMPT, 0);
                                BaseDfuImpl baseDfuImpl = null;
                                try {
                                    try {
                                        dfuImpl = DfuServiceProvider.getDfuImpl(this, bluetoothGattConnect);
                                        this.mDfuServiceImpl = dfuImpl;
                                        if (dfuImpl != null) {
                                            try {
                                                if (dfuImpl.hasRequiredService(bluetoothGattConnect)) {
                                                    if (dfuImpl.hasRequiredCharacteristics(bluetoothGattConnect)) {
                                                        sendLogBroadcast(5, "Services discovered");
                                                        if (dfuImpl.initialize(intent, bluetoothGattConnect, intExtra3, inputStream, inputStream2)) {
                                                            dfuImpl.performDfu(intent);
                                                        }
                                                        dfuImpl.release();
                                                        try {
                                                            inputStream.close();
                                                            return;
                                                        } catch (IOException unused7) {
                                                            return;
                                                        }
                                                    }
                                                    InputStream inputStream8 = inputStream;
                                                    sendLogBroadcast(15, "Connected. DFU Characteristics not found");
                                                    terminateConnection(bluetoothGattConnect, ERROR_CHARACTERISTICS_NOT_FOUND);
                                                    dfuImpl.release();
                                                    try {
                                                        inputStream8.close();
                                                        return;
                                                    } catch (IOException unused8) {
                                                        return;
                                                    }
                                                }
                                            } catch (DeviceDisconnectedException e) {
                                                e = e;
                                                baseDfuImpl = dfuImpl;
                                                BaseDfuImpl baseDfuImpl2 = baseDfuImpl;
                                                DeviceDisconnectedException deviceDisconnectedException = e;
                                                sendLogBroadcast(20, "Device has disconnected");
                                                loge(deviceDisconnectedException.getMessage());
                                                close(bluetoothGattConnect);
                                                report(4096);
                                                if (baseDfuImpl2 != null) {
                                                    baseDfuImpl.release();
                                                }
                                            } catch (DfuException e2) {
                                                e = e2;
                                                baseDfuImpl = dfuImpl;
                                                int errorNumber = e.getErrorNumber();
                                                if ((errorNumber & ERROR_CONNECTION_STATE_MASK) > 0) {
                                                    int i8 = errorNumber & (-32769);
                                                    i3 = 20;
                                                    Object[] objArr3 = new Object[2];
                                                    objArr3[0] = Integer.valueOf(i8);
                                                    objArr3[1] = GattError.parseConnectionError(i8);
                                                    str = String.format("Error (0x%02X): %s", objArr3);
                                                } else {
                                                    int i9 = errorNumber & (-16385);
                                                    i3 = 20;
                                                    Object[] objArr4 = new Object[2];
                                                    objArr4[0] = Integer.valueOf(i9);
                                                    objArr4[1] = GattError.parse(i9);
                                                    str = String.format("Error (0x%02X): %s", objArr4);
                                                }
                                                sendLogBroadcast(i3, str);
                                                BaseDfuImpl baseDfuImpl3 = baseDfuImpl;
                                                DfuException dfuException = e;
                                                loge(dfuException.getMessage());
                                                terminateConnection(bluetoothGattConnect, dfuException.getErrorNumber());
                                                if (baseDfuImpl3 != null) {
                                                    baseDfuImpl.release();
                                                }
                                            } catch (UploadAbortedException unused9) {
                                                baseDfuImpl = dfuImpl;
                                                BaseDfuImpl baseDfuImpl4 = baseDfuImpl;
                                                logi("Upload aborted");
                                                sendLogBroadcast(15, "Upload aborted");
                                                terminateConnection(bluetoothGattConnect, -7);
                                                if (baseDfuImpl4 != null) {
                                                    baseDfuImpl.release();
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                baseDfuImpl = dfuImpl;
                                                if (baseDfuImpl != null) {
                                                    baseDfuImpl.release();
                                                }
                                                throw th;
                                            }
                                        }
                                        sendLogBroadcast(15, "Connected. DFU Service not found");
                                        terminateConnection(bluetoothGattConnect, ERROR_SERVICE_NOT_FOUND);
                                        if (dfuImpl != null) {
                                            dfuImpl.release();
                                        }
                                        try {
                                            inputStream.close();
                                            return;
                                        } catch (IOException unused10) {
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (DeviceDisconnectedException e3) {
                                    e = e3;
                                } catch (DfuException e4) {
                                    e = e4;
                                } catch (UploadAbortedException unused11) {
                                }
                            }
                            inputStream2 = inputStreamOpenRawResource;
                            iAvailable = inputStream.available();
                            if (iAvailable % 4 == 0) {
                                throw new SizeValidationException("The new firmware is not word-aligned.");
                            }
                            if (intExtra3 == 0) {
                                intExtra3 = ((ArchiveInputStream) inputStream).getContentType();
                            }
                            if (MIME_TYPE_ZIP.equals(str3)) {
                                archiveInputStream = (ArchiveInputStream) inputStream;
                                if ((intExtra3 & 4) <= 0) {
                                }
                                if ((intExtra3 & 2) <= 0) {
                                }
                                if ((intExtra3 & 1) <= 0) {
                                }
                                if (intExtra3 == 4) {
                                    if (archiveInputStream.getApplicationInit() != null) {
                                        inputStream2 = byteArrayInputStream2;
                                        byteArrayInputStream2 = new ByteArrayInputStream(archiveInputStream.getApplicationInit());
                                    }
                                } else if (archiveInputStream.getSystemInit() != null) {
                                    inputStream2 = byteArrayInputStream;
                                    byteArrayInputStream = new ByteArrayInputStream(archiveInputStream.getSystemInit());
                                }
                            }
                            sendLogBroadcast(5, "Image file opened (" + iAvailable + " bytes in total)");
                            waitFor(1000);
                            waitFor(1000);
                            sendLogBroadcast(1, "Connecting to DFU target...");
                            DfuProgressInfo dfuProgressInfo2 = new DfuProgressInfo(this);
                            this.mProgressInfo = dfuProgressInfo2;
                            dfuProgressInfo2.setProgress(-1);
                            bluetoothGattConnect = connect(stringExtra);
                            if (bluetoothGattConnect == null) {
                                InputStream inputStream9 = inputStream;
                                loge("Bluetooth adapter disabled");
                                sendLogBroadcast(20, "Bluetooth adapter disabled");
                                report(ERROR_BLUETOOTH_DISABLED);
                                inputStream9.close();
                                return;
                            }
                            if (this.mConnectionState == 0) {
                                InputStream inputStream10 = inputStream;
                                loge("Device got disconnected before service discovery finished");
                                sendLogBroadcast(5, "Disconnected");
                                terminateConnection(bluetoothGattConnect, 4096);
                                inputStream10.close();
                                return;
                            }
                            i2 = this.mError;
                            if (i2 <= 0) {
                                if (this.mAborted) {
                                    InputStream inputStream11 = inputStream;
                                    logi("Upload aborted");
                                    sendLogBroadcast(15, "Upload aborted");
                                    terminateConnection(bluetoothGattConnect, -7);
                                    inputStream11.close();
                                    return;
                                }
                                intent.putExtra(EXTRA_ATTEMPT, 0);
                                BaseDfuImpl baseDfuImpl5 = null;
                                dfuImpl = DfuServiceProvider.getDfuImpl(this, bluetoothGattConnect);
                                this.mDfuServiceImpl = dfuImpl;
                                if (dfuImpl != null) {
                                    if (dfuImpl.hasRequiredService(bluetoothGattConnect)) {
                                        if (dfuImpl.hasRequiredCharacteristics(bluetoothGattConnect)) {
                                            InputStream inputStream12 = inputStream;
                                            sendLogBroadcast(15, "Connected. DFU Characteristics not found");
                                            terminateConnection(bluetoothGattConnect, ERROR_CHARACTERISTICS_NOT_FOUND);
                                            dfuImpl.release();
                                            inputStream12.close();
                                            return;
                                        }
                                        sendLogBroadcast(5, "Services discovered");
                                        if (dfuImpl.initialize(intent, bluetoothGattConnect, intExtra3, inputStream, inputStream2)) {
                                            dfuImpl.performDfu(intent);
                                        }
                                        dfuImpl.release();
                                        inputStream.close();
                                        return;
                                    }
                                }
                                sendLogBroadcast(15, "Connected. DFU Service not found");
                                terminateConnection(bluetoothGattConnect, ERROR_SERVICE_NOT_FOUND);
                                if (dfuImpl != null) {
                                    dfuImpl.release();
                                }
                                inputStream.close();
                                return;
                            }
                            if ((i2 & ERROR_CONNECTION_STATE_MASK) > 0) {
                                int i10 = i2 & (-32769);
                                loge("An Error occurred while connecting to the device:" + i10);
                                i4 = 20;
                                Object[] objArr5 = new Object[2];
                                objArr5[0] = Integer.valueOf(i10);
                                objArr5[1] = GattError.parseConnectionError(i10);
                                str2 = String.format("Connection failed (0x%02X): %s", objArr5);
                            } else {
                                int i11 = i2 & (-16385);
                                loge("An Error occurred during discovering services:" + i11);
                                i4 = 20;
                                Object[] objArr6 = new Object[2];
                                objArr6[0] = Integer.valueOf(i11);
                                objArr6[1] = GattError.parse(i11);
                                str2 = String.format("Connection failed (0x%02X): %s", objArr6);
                            }
                            sendLogBroadcast(i4, str2);
                            if (intent.getIntExtra(EXTRA_ATTEMPT, 0) == 0) {
                                InputStream inputStream13 = inputStream;
                                terminateConnection(bluetoothGattConnect, this.mError);
                                inputStream13.close();
                                return;
                            }
                            sendLogBroadcast(15, "Retrying...");
                            if (this.mConnectionState != 0) {
                                disconnect(bluetoothGattConnect);
                            }
                            InputStream inputStream14 = inputStream;
                            refreshDeviceCache(bluetoothGattConnect, true);
                            close(bluetoothGattConnect);
                            logi("Restarting the service");
                            Intent intent3 = new Intent();
                            intent3.fillIn(intent, 24);
                            intent3.putExtra(EXTRA_ATTEMPT, 1);
                            startService(intent3);
                            inputStream14.close();
                        }
                        inputStreamOpenInputStream = openInputStream(stringExtra3, str3, i, intExtra3);
                    }
                    inputStream = inputStreamOpenInputStream;
                    if (uri2 == null) {
                        if (stringExtra4 != null) {
                            inputStream2 = fileInputStream;
                            fileInputStream = new FileInputStream(stringExtra4);
                        } else if (intExtra2 > 0) {
                            inputStreamOpenRawResource = getResources().openRawResource(intExtra2);
                        }
                        iAvailable = inputStream.available();
                        if (iAvailable % 4 == 0) {
                            throw new SizeValidationException("The new firmware is not word-aligned.");
                        }
                        if (intExtra3 == 0) {
                            intExtra3 = ((ArchiveInputStream) inputStream).getContentType();
                        }
                        if (MIME_TYPE_ZIP.equals(str3)) {
                            archiveInputStream = (ArchiveInputStream) inputStream;
                            if ((intExtra3 & 4) <= 0) {
                            }
                            if ((intExtra3 & 2) <= 0) {
                            }
                            if ((intExtra3 & 1) <= 0) {
                            }
                            if (intExtra3 == 4) {
                                if (archiveInputStream.getApplicationInit() != null) {
                                    inputStream2 = byteArrayInputStream2;
                                    byteArrayInputStream2 = new ByteArrayInputStream(archiveInputStream.getApplicationInit());
                                }
                            } else if (archiveInputStream.getSystemInit() != null) {
                                inputStream2 = byteArrayInputStream;
                                byteArrayInputStream = new ByteArrayInputStream(archiveInputStream.getSystemInit());
                            }
                        }
                        sendLogBroadcast(5, "Image file opened (" + iAvailable + " bytes in total)");
                        waitFor(1000);
                        waitFor(1000);
                        sendLogBroadcast(1, "Connecting to DFU target...");
                        DfuProgressInfo dfuProgressInfo3 = new DfuProgressInfo(this);
                        this.mProgressInfo = dfuProgressInfo3;
                        dfuProgressInfo3.setProgress(-1);
                        bluetoothGattConnect = connect(stringExtra);
                        if (bluetoothGattConnect == null) {
                            InputStream inputStream15 = inputStream;
                            loge("Bluetooth adapter disabled");
                            sendLogBroadcast(20, "Bluetooth adapter disabled");
                            report(ERROR_BLUETOOTH_DISABLED);
                            inputStream15.close();
                            return;
                        }
                        if (this.mConnectionState == 0) {
                            InputStream inputStream16 = inputStream;
                            loge("Device got disconnected before service discovery finished");
                            sendLogBroadcast(5, "Disconnected");
                            terminateConnection(bluetoothGattConnect, 4096);
                            inputStream16.close();
                            return;
                        }
                        i2 = this.mError;
                        if (i2 <= 0) {
                            if (this.mAborted) {
                                InputStream inputStream17 = inputStream;
                                logi("Upload aborted");
                                sendLogBroadcast(15, "Upload aborted");
                                terminateConnection(bluetoothGattConnect, -7);
                                inputStream17.close();
                                return;
                            }
                            intent.putExtra(EXTRA_ATTEMPT, 0);
                            BaseDfuImpl baseDfuImpl6 = null;
                            dfuImpl = DfuServiceProvider.getDfuImpl(this, bluetoothGattConnect);
                            this.mDfuServiceImpl = dfuImpl;
                            if (dfuImpl != null) {
                                if (dfuImpl.hasRequiredService(bluetoothGattConnect)) {
                                    if (dfuImpl.hasRequiredCharacteristics(bluetoothGattConnect)) {
                                        InputStream inputStream18 = inputStream;
                                        sendLogBroadcast(15, "Connected. DFU Characteristics not found");
                                        terminateConnection(bluetoothGattConnect, ERROR_CHARACTERISTICS_NOT_FOUND);
                                        dfuImpl.release();
                                        inputStream18.close();
                                        return;
                                    }
                                    sendLogBroadcast(5, "Services discovered");
                                    if (dfuImpl.initialize(intent, bluetoothGattConnect, intExtra3, inputStream, inputStream2)) {
                                        dfuImpl.performDfu(intent);
                                    }
                                    dfuImpl.release();
                                    inputStream.close();
                                    return;
                                }
                            }
                            sendLogBroadcast(15, "Connected. DFU Service not found");
                            terminateConnection(bluetoothGattConnect, ERROR_SERVICE_NOT_FOUND);
                            if (dfuImpl != null) {
                                dfuImpl.release();
                            }
                            inputStream.close();
                            return;
                        }
                        if ((i2 & ERROR_CONNECTION_STATE_MASK) > 0) {
                            int i12 = i2 & (-32769);
                            loge("An Error occurred while connecting to the device:" + i12);
                            i4 = 20;
                            Object[] objArr7 = new Object[2];
                            objArr7[0] = Integer.valueOf(i12);
                            objArr7[1] = GattError.parseConnectionError(i12);
                            str2 = String.format("Connection failed (0x%02X): %s", objArr7);
                        } else {
                            int i13 = i2 & (-16385);
                            loge("An Error occurred during discovering services:" + i13);
                            i4 = 20;
                            Object[] objArr8 = new Object[2];
                            objArr8[0] = Integer.valueOf(i13);
                            objArr8[1] = GattError.parse(i13);
                            str2 = String.format("Connection failed (0x%02X): %s", objArr8);
                        }
                        sendLogBroadcast(i4, str2);
                        if (intent.getIntExtra(EXTRA_ATTEMPT, 0) == 0) {
                            InputStream inputStream19 = inputStream;
                            terminateConnection(bluetoothGattConnect, this.mError);
                            inputStream19.close();
                            return;
                        }
                        sendLogBroadcast(15, "Retrying...");
                        if (this.mConnectionState != 0) {
                            disconnect(bluetoothGattConnect);
                        }
                        InputStream inputStream110 = inputStream;
                        refreshDeviceCache(bluetoothGattConnect, true);
                        close(bluetoothGattConnect);
                        logi("Restarting the service");
                        Intent intent4 = new Intent();
                        intent4.fillIn(intent, 24);
                        intent4.putExtra(EXTRA_ATTEMPT, 1);
                        startService(intent4);
                        inputStream110.close();
                    }
                    inputStreamOpenRawResource = getContentResolver().openInputStream(uri2);
                    inputStream2 = inputStreamOpenRawResource;
                    iAvailable = inputStream.available();
                    if (iAvailable % 4 == 0) {
                        throw new SizeValidationException("The new firmware is not word-aligned.");
                    }
                    if (intExtra3 == 0) {
                        intExtra3 = ((ArchiveInputStream) inputStream).getContentType();
                    }
                    if (MIME_TYPE_ZIP.equals(str3)) {
                        archiveInputStream = (ArchiveInputStream) inputStream;
                        if ((intExtra3 & 4) <= 0) {
                        }
                        if ((intExtra3 & 2) <= 0) {
                        }
                        if ((intExtra3 & 1) <= 0) {
                        }
                        if (intExtra3 == 4) {
                            if (archiveInputStream.getApplicationInit() != null) {
                                inputStream2 = byteArrayInputStream2;
                                byteArrayInputStream2 = new ByteArrayInputStream(archiveInputStream.getApplicationInit());
                            }
                        } else if (archiveInputStream.getSystemInit() != null) {
                            inputStream2 = byteArrayInputStream;
                            byteArrayInputStream = new ByteArrayInputStream(archiveInputStream.getSystemInit());
                        }
                    }
                    sendLogBroadcast(5, "Image file opened (" + iAvailable + " bytes in total)");
                    waitFor(1000);
                    waitFor(1000);
                    sendLogBroadcast(1, "Connecting to DFU target...");
                    DfuProgressInfo dfuProgressInfo4 = new DfuProgressInfo(this);
                    this.mProgressInfo = dfuProgressInfo4;
                    dfuProgressInfo4.setProgress(-1);
                    bluetoothGattConnect = connect(stringExtra);
                    if (bluetoothGattConnect == null) {
                        InputStream inputStream111 = inputStream;
                        loge("Bluetooth adapter disabled");
                        sendLogBroadcast(20, "Bluetooth adapter disabled");
                        report(ERROR_BLUETOOTH_DISABLED);
                        inputStream111.close();
                        return;
                    }
                    if (this.mConnectionState == 0) {
                        InputStream inputStream112 = inputStream;
                        loge("Device got disconnected before service discovery finished");
                        sendLogBroadcast(5, "Disconnected");
                        terminateConnection(bluetoothGattConnect, 4096);
                        inputStream112.close();
                        return;
                    }
                    i2 = this.mError;
                    if (i2 <= 0) {
                        if (this.mAborted) {
                            InputStream inputStream113 = inputStream;
                            logi("Upload aborted");
                            sendLogBroadcast(15, "Upload aborted");
                            terminateConnection(bluetoothGattConnect, -7);
                            inputStream113.close();
                            return;
                        }
                        intent.putExtra(EXTRA_ATTEMPT, 0);
                        BaseDfuImpl baseDfuImpl7 = null;
                        dfuImpl = DfuServiceProvider.getDfuImpl(this, bluetoothGattConnect);
                        this.mDfuServiceImpl = dfuImpl;
                        if (dfuImpl != null) {
                            if (dfuImpl.hasRequiredService(bluetoothGattConnect)) {
                                if (dfuImpl.hasRequiredCharacteristics(bluetoothGattConnect)) {
                                    InputStream inputStream114 = inputStream;
                                    sendLogBroadcast(15, "Connected. DFU Characteristics not found");
                                    terminateConnection(bluetoothGattConnect, ERROR_CHARACTERISTICS_NOT_FOUND);
                                    dfuImpl.release();
                                    inputStream114.close();
                                    return;
                                }
                                sendLogBroadcast(5, "Services discovered");
                                if (dfuImpl.initialize(intent, bluetoothGattConnect, intExtra3, inputStream, inputStream2)) {
                                    dfuImpl.performDfu(intent);
                                }
                                dfuImpl.release();
                                inputStream.close();
                                return;
                            }
                        }
                        sendLogBroadcast(15, "Connected. DFU Service not found");
                        terminateConnection(bluetoothGattConnect, ERROR_SERVICE_NOT_FOUND);
                        if (dfuImpl != null) {
                            dfuImpl.release();
                        }
                        inputStream.close();
                        return;
                    }
                    if ((i2 & ERROR_CONNECTION_STATE_MASK) > 0) {
                        int i14 = i2 & (-32769);
                        loge("An Error occurred while connecting to the device:" + i14);
                        i4 = 20;
                        Object[] objArr9 = new Object[2];
                        objArr9[0] = Integer.valueOf(i14);
                        objArr9[1] = GattError.parseConnectionError(i14);
                        str2 = String.format("Connection failed (0x%02X): %s", objArr9);
                    } else {
                        int i15 = i2 & (-16385);
                        loge("An Error occurred during discovering services:" + i15);
                        i4 = 20;
                        Object[] objArr10 = new Object[2];
                        objArr10[0] = Integer.valueOf(i15);
                        objArr10[1] = GattError.parse(i15);
                        str2 = String.format("Connection failed (0x%02X): %s", objArr10);
                    }
                    sendLogBroadcast(i4, str2);
                    if (intent.getIntExtra(EXTRA_ATTEMPT, 0) == 0) {
                        InputStream inputStream115 = inputStream;
                        terminateConnection(bluetoothGattConnect, this.mError);
                        inputStream115.close();
                        return;
                    }
                    sendLogBroadcast(15, "Retrying...");
                    if (this.mConnectionState != 0) {
                        disconnect(bluetoothGattConnect);
                    }
                    InputStream inputStream116 = inputStream;
                    refreshDeviceCache(bluetoothGattConnect, true);
                    close(bluetoothGattConnect);
                    logi("Restarting the service");
                    Intent intent5 = new Intent();
                    intent5.fillIn(intent, 24);
                    intent5.putExtra(EXTRA_ATTEMPT, 1);
                    startService(intent5);
                    inputStream116.close();
                } catch (SizeValidationException e5) {
                    loge("Firmware not word-aligned", e5);
                    sendLogBroadcast(20, "Opening file failed: Firmware size must be word-aligned");
                    report(ERROR_FILE_SIZE_INVALID);
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException unused12) {
                        }
                    }
                } catch (IOException e6) {
                    loge("An exception occurred while calculating file size", e6);
                    sendLogBroadcast(20, "Opening file failed: " + e6.getLocalizedMessage());
                    report(ERROR_FILE_ERROR);
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException unused13) {
                        }
                    }
                }
            } catch (FileNotFoundException e7) {
                loge("An exception occurred while opening file", e7);
                sendLogBroadcast(20, "Opening file failed: File not found");
                report(ERROR_FILE_NOT_FOUND);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused14) {
                    }
                }
            } catch (SecurityException e8) {
                loge("A security exception occurred while opening file", e8);
                sendLogBroadcast(20, "Opening file failed: Permission required");
                report(ERROR_FILE_NOT_FOUND);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused15) {
                    }
                }
            } catch (Exception e9) {
                loge("An exception occurred while opening files. Did you set the firmware file?", e9);
                sendLogBroadcast(20, "Opening file failed: " + e9.getLocalizedMessage());
                report(ERROR_FILE_ERROR);
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused16) {
                    }
                }
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused17) {
                }
            }
            throw th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v7, types: [vpno.nordicsemi.android.dfu.DfuBaseService] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    protected BluetoothGatt connect(String str) {
        ?? r0;
        if (!this.mBluetoothAdapter.isEnabled()) {
            return null;
        }
        this.mConnectionState = -1;
        logi("Connecting to the device...");
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false)");
        BluetoothGatt bluetoothGattConnectGatt = remoteDevice.connectGatt(this, false, this.mGattCallback);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        int i = r0.mConnectionState;
                        if ((i != -1 && i != -2) || this.mError != 0) {
                            break;
                        }
                        this.mLock.wait();
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
                r0 = obj;
                return bluetoothGattConnectGatt;
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    protected void terminateConnection(BluetoothGatt bluetoothGatt, int i) {
        if (this.mConnectionState != 0) {
            disconnect(bluetoothGatt);
        }
        refreshDeviceCache(bluetoothGatt, false);
        close(bluetoothGatt);
        waitFor(600);
        report(i);
    }

    protected void disconnect(BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState == 0) {
            return;
        }
        sendLogBroadcast(1, "Disconnecting...");
        this.mProgressInfo.setProgress(-5);
        this.mConnectionState = -4;
        logi("Disconnecting from the device...");
        sendLogBroadcast(0, "gatt.disconnect()");
        bluetoothGatt.disconnect();
        waitUntilDisconnected();
        sendLogBroadcast(5, "Disconnected");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [vpno.nordicsemi.android.dfu.DfuBaseService] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6 */
    protected void waitUntilDisconnected() {
        ?? r0;
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if (r0.mConnectionState == 0 || this.mError != 0) {
                            break;
                        } else {
                            this.mLock.wait();
                        }
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [vpno.nordicsemi.android.dfu.DfuBaseService] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
    protected void waitFor(int i) {
        ?? r0 = this;
        Object obj = r0.mLock;
        synchronized (obj) {
            try {
                try {
                    r0.sendLogBroadcast(0, "wait(" + i + ")");
                    r0 = r0.mLock;
                    r0.wait(i);
                } catch (InterruptedException e) {
                    loge("Sleeping interrupted", e);
                }
                r0 = obj;
            } catch (Throwable unused) {
                throw r0;
            }
        }
    }

    protected void close(BluetoothGatt bluetoothGatt) {
        logi("Cleaning up...");
        sendLogBroadcast(0, "gatt.close()");
        bluetoothGatt.close();
        this.mConnectionState = -5;
    }

    protected void refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            sendLogBroadcast(0, "gatt.refresh() (hidden)");
            try {
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    logi("Refreshing result: " + ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue());
                }
            } catch (Exception e) {
                loge("An exception occurred while refreshing device", e);
                sendLogBroadcast(15, "Refreshing failed");
            }
        }
    }

    @Override // vpno.nordicsemi.android.dfu.DfuProgressInfo.ProgressListener
    public void updateProgressNotification() {
        DfuProgressInfo dfuProgressInfo = this.mProgressInfo;
        int progress = dfuProgressInfo.getProgress();
        if (this.mLastProgress == progress) {
            return;
        }
        this.mLastProgress = progress;
        sendProgressBroadcast(dfuProgressInfo);
        if (this.mDisableNotification) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.mLastNotificationTime < 250) {
            return;
        }
        this.mLastNotificationTime = jElapsedRealtime;
        String str = this.mDeviceAddress;
        String str2 = this.mDeviceName;
        String str3 = str2;
        if (str2 == null) {
            str3 = "unnamed device";
        }
        NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.stat_sys_upload).setOnlyAlertOnce(true);
        onlyAlertOnce.setColor(-7829368);
        switch (progress) {
            case PROGRESS_ABORTED /* -7 */:
                onlyAlertOnce.setOngoing(false).setContentTitle("Aborted").setSmallIcon(R.drawable.stat_sys_warning).setContentText("Application upload has been canceled").setAutoCancel(true);
                break;
            case PROGRESS_COMPLETED /* -6 */:
                onlyAlertOnce.setOngoing(false).setContentTitle("Done").setSmallIcon(R.drawable.stat_sys_download_done).setContentText("Application has been send successfully").setAutoCancel(true).setColor(-16730086);
                break;
            case -5:
                onlyAlertOnce.setOngoing(true).setContentTitle("Disonnecting...").setContentText(String.format("Disconnecting from", str3)).setProgress(100, 0, true);
                break;
            case -4:
                onlyAlertOnce.setOngoing(true).setContentTitle("Validating").setContentText("Validating application %s").setProgress(100, 0, true);
                break;
            case -3:
                onlyAlertOnce.setOngoing(true).setContentTitle("Starting bootloader...").setContentText("Waiting for bootloader").setProgress(100, 0, true);
                break;
            case -2:
                onlyAlertOnce.setOngoing(true).setContentTitle("").setContentText("Initializing DFU process %s ...").setProgress(100, 0, true);
                break;
            case -1:
                onlyAlertOnce.setOngoing(true).setContentTitle("Connecting...").setContentText(String.format("Connecting to %s...", str3)).setProgress(100, 0, true);
                break;
            default:
                onlyAlertOnce.setOngoing(true).setContentTitle(dfuProgressInfo.getTotalParts() == 1 ? "Uploading..." : "Uploading part  %d/%d...").setContentText(String.format("Transmitting application to %s...", str3)).setProgress(100, progress, false);
                break;
        }
        Intent intent = new Intent(this, getNotificationTarget());
        intent.addFlags(268435456);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
        intent.putExtra(EXTRA_DEVICE_NAME, str3);
        intent.putExtra(EXTRA_PROGRESS, progress);
        onlyAlertOnce.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
        if (progress != -7 && progress != -6) {
            Intent intent2 = new Intent(BROADCAST_ACTION);
            intent2.putExtra(EXTRA_ACTION, 2);
            onlyAlertOnce.addAction(0, "Abort", PendingIntent.getBroadcast(this, 1, intent2, 134217728));
        }
        ((NotificationManager) getSystemService("notification")).notify(NOTIFICATION_ID, onlyAlertOnce.build());
    }

    protected abstract Class<? extends Activity> getNotificationTarget();

    void sendLogBroadcast(int i, String str) {
        Intent intent = new Intent(BROADCAST_LOG);
        intent.putExtra(EXTRA_LOG_MESSAGE, "[DFU] " + str);
        intent.putExtra(EXTRA_LOG_LEVEL, i);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.io.InputStream, vpno.nordicsemi.android.dfu.internal.HexInputStream] */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    private InputStream openInputStream(Uri uri, String str, int i, int i2) throws FileNotFoundException {
        InputStream inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(inputStreamOpenInputStream, i, i2);
        }
        ?? Query = getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        try {
            if (!Query.moveToNext() || !Query.getString(0).toLowerCase(Locale.US).endsWith("hex")) {
                Query.close();
                return inputStreamOpenInputStream;
            }
            Query = new HexInputStream(inputStreamOpenInputStream, i);
            Query.close();
            return Query;
        } catch (Throwable unused) {
            Query.close();
            throw Query;
        }
    }

    private void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    private InputStream openInputStream(String str, String str2, int i, int i2) {
        FileInputStream fileInputStream = new FileInputStream(str);
        if (MIME_TYPE_ZIP.equals(str2)) {
            return new ArchiveInputStream(fileInputStream, i, i2);
        }
        return str.toLowerCase(Locale.US).endsWith("hex") ? new HexInputStream(fileInputStream, i) : fileInputStream;
    }
}
