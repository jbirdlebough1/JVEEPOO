package vpno.nordicsemi.android.dfu.internal.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/scanner/BootloaderScannerJB.class */
public class BootloaderScannerJB implements BootloaderScanner, BluetoothAdapter.LeScanCallback {
    private final Object mLock = new Object();
    private String mDeviceAddress;
    private String mDeviceAddressIncremented;
    private String mBootloaderAddress;
    private boolean mFound;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScannerJB] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScanner
    public String searchFor(String str) {
        ?? r0;
        String strSubstring = str.substring(0, 15);
        String str2 = String.format("%02X", Integer.valueOf((Integer.valueOf(str.substring(15), 16).intValue() + 1) & 255));
        this.mDeviceAddress = str;
        this.mDeviceAddressIncremented = strSubstring + str2;
        this.mBootloaderAddress = null;
        this.mFound = false;
        new Thread(new Runnable() { // from class: vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScannerJB.1
            /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Throwable] */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(BootloaderScanner.TIMEOUT);
                } catch (InterruptedException unused) {
                }
                BootloaderScannerJB bootloaderScannerJB = BootloaderScannerJB.this;
                if (bootloaderScannerJB.mFound) {
                    return;
                }
                bootloaderScannerJB.mBootloaderAddress = null;
                bootloaderScannerJB.mFound = true;
                ?? r1 = bootloaderScannerJB.mLock;
                synchronized (r1) {
                    try {
                        BootloaderScannerJB.this.mLock.notifyAll();
                    } catch (Throwable th) {
                        throw r1;
                    }
                }
            }
        }, "Scanner timer").start();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        defaultAdapter.startLeScan(this);
        try {
            Object obj = this.mLock;
            synchronized (obj) {
                while (true) {
                    r0 = this;
                    try {
                        if (r0.mFound) {
                            break;
                        }
                        this.mLock.wait();
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
                r0 = obj;
                defaultAdapter.stopLeScan(this);
                return this.mBootloaderAddress;
            }
        } catch (InterruptedException unused2) {
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Throwable] */
    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        String address = bluetoothDevice.getAddress();
        if (this.mDeviceAddress.equals(address) || this.mDeviceAddressIncremented.equals(address)) {
            this.mBootloaderAddress = address;
            this.mFound = true;
            ?? r0 = this.mLock;
            synchronized (r0) {
                try {
                    this.mLock.notifyAll();
                } catch (Throwable th) {
                    throw r0;
                }
            }
        }
    }
}
