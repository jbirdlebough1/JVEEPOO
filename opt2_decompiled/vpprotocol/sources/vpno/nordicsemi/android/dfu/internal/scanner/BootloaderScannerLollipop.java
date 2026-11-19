package vpno.nordicsemi.android.dfu.internal.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/scanner/BootloaderScannerLollipop.class */
@TargetApi(21)
public class BootloaderScannerLollipop extends ScanCallback implements BootloaderScanner {
    private final Object mLock = new Object();
    private String mDeviceAddress;
    private String mDeviceAddressIncremented;
    private String mBootloaderAddress;
    private boolean mFound;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScannerLollipop] */
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
        new Thread(new Runnable() { // from class: vpno.nordicsemi.android.dfu.internal.scanner.BootloaderScannerLollipop.1
            /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Throwable] */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(BootloaderScanner.TIMEOUT);
                } catch (InterruptedException unused) {
                }
                BootloaderScannerLollipop bootloaderScannerLollipop = BootloaderScannerLollipop.this;
                if (bootloaderScannerLollipop.mFound) {
                    return;
                }
                bootloaderScannerLollipop.mBootloaderAddress = null;
                bootloaderScannerLollipop.mFound = true;
                ?? r1 = bootloaderScannerLollipop.mLock;
                synchronized (r1) {
                    try {
                        BootloaderScannerLollipop.this.mLock.notifyAll();
                    } catch (Throwable th) {
                        throw r1;
                    }
                }
            }
        }, "Scanner timer").start();
        BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        bluetoothLeScanner.startScan((List<ScanFilter>) null, new ScanSettings.Builder().setScanMode(2).build(), this);
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
                bluetoothLeScanner.stopScan(this);
                return this.mBootloaderAddress;
            }
        } catch (InterruptedException unused2) {
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Throwable] */
    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        String address = scanResult.getDevice().getAddress();
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
