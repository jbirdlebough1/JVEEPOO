package vpno.nordicsemi.android.dfu.internal.scanner;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/scanner/BootloaderScanner.class */
public interface BootloaderScanner {
    public static final long TIMEOUT = 5000;
    public static final int ADDRESS_DIFF = 1;

    String searchFor(String str);
}
