package vpno.nordicsemi.android.error;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/error/LegacyDfuError.class */
public final class LegacyDfuError {
    public static final int INVALID_STATE = 2;
    public static final int NOT_SUPPORTED = 3;
    public static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
    public static final int CRC_ERROR = 5;
    public static final int OPERATION_FAILED = 6;

    public static String parse(int i) {
        switch (i & (-8193)) {
            case 2:
                return "REMOTE DFU INVALID STATE";
            case 3:
                return "REMOTE DFU NOT SUPPORTED";
            case 4:
                return "REMOTE DFU DATA SIZE EXCEEDS LIMIT";
            case 5:
                return "REMOTE DFU INVALID CRC ERROR";
            case 6:
                return "REMOTE DFU OPERATION FAILED";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }
}
