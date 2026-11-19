package vpno.nordicsemi.android.dfu.internal.scanner;

import android.os.Build;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/scanner/BootloaderScannerFactory.class */
public class BootloaderScannerFactory {
    public static BootloaderScanner getScanner() {
        return Build.VERSION.SDK_INT >= 21 ? new BootloaderScannerLollipop() : new BootloaderScannerJB();
    }
}
