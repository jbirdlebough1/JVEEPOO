package vpno.nordicsemi.android.error;

import com.veepoo.protocol.model.datas.FunSwitchFlags;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/error/SecureDfuError.class */
public final class SecureDfuError {
    public static final int OP_CODE_NOT_SUPPORTED = 2;
    public static final int INVALID_PARAM = 3;
    public static final int INSUFFICIENT_RESOURCES = 4;
    public static final int INVALID_OBJECT = 5;
    public static final int UNSUPPORTED_TYPE = 7;
    public static final int OPERATION_NOT_PERMITTED = 8;
    public static final int OPERATION_FAILED = 10;
    public static final int EXTENDED_ERROR = 11;

    public static String parse(int i) {
        switch (i & (-8193)) {
            case 2:
                return "REMOTE DFU OP CODE NOT SUPPORTED";
            case 3:
                return "REMOTE DFU INVALID PARAM";
            case 4:
                return "REMOTE DFU INSUFFICIENT RESOURCES";
            case 5:
                return "REMOTE DFU INVALID OBJECT";
            case 6:
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
            default:
                return "UNKNOWN (" + i + ")";
            case 7:
                return "REMOTE DFU UNSUPPORTED TYPE";
            case 8:
                return "REMOTE DFU OPERATION NOT PERMITTED";
            case 10:
                return "REMOTE DFU OPERATION FAILED";
            case 11:
                return "REMOTE DFU EXTENDED ERROR";
        }
    }
}
