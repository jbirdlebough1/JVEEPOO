package vpno.nordicsemi.android.error;

import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/error/GattError.class */
public class GattError {
    public static String parseConnectionError(int i) {
        switch (i) {
            case 0:
                return "SUCCESS";
            case 1:
                return "GATT CONN L2C FAILURE";
            case 8:
                return "GATT CONN TIMEOUT";
            case 19:
                return "GATT CONN TERMINATE PEER USER";
            case Spo2hOriginUtil.YESTERDAY_HOUR_START /* 22 */:
                return "GATT CONN TERMINATE LOCAL HOST";
            case 34:
                return "GATT CONN LMP TIMEOUT";
            case 62:
                return "GATT CONN FAIL ESTABLISH";
            case 133:
                return "GATT ERROR";
            case 256:
                return "GATT CONN CANCEL ";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }

    public static String parse(int i) {
        if (i == 257) {
            return "TOO MANY OPEN CONNECTIONS";
        }
        switch (i) {
            case 1:
                return "GATT INVALID HANDLE";
            case 2:
                return "GATT READ NOT PERMIT";
            case 3:
                return "GATT WRITE NOT PERMIT";
            case 4:
                return "GATT INVALID PDU";
            case 5:
                return "GATT INSUF AUTHENTICATION";
            case 6:
                return "GATT REQ NOT SUPPORTED";
            case 7:
                return "GATT INVALID OFFSET";
            case 8:
                return "GATT INSUF AUTHORIZATION";
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                return "GATT PREPARE Q FULL";
            case 10:
                return "GATT NOT FOUND";
            case 11:
                return "GATT NOT LONG";
            case 12:
                return "GATT INSUF KEY SIZE";
            case 13:
                return "GATT INVALID ATTR LEN";
            case 14:
                return "GATT ERR UNLIKELY";
            case 15:
                return "GATT INSUF ENCRYPTION";
            case 16:
                return "GATT UNSUPPORT GRP TYPE";
            case 17:
                return "GATT INSUF RESOURCE";
            default:
                switch (i) {
                    case 128:
                        return "GATT NO RESOURCES";
                    case 129:
                        return "GATT INTERNAL ERROR";
                    case 130:
                        return "GATT WRONG STATE";
                    case 131:
                        return "GATT DB FULL";
                    case 132:
                        return "GATT BUSY";
                    case 133:
                        return "GATT ERROR";
                    case 134:
                        return "GATT CMD STARTED";
                    case 135:
                        return "GATT ILLEGAL PARAMETER";
                    case 136:
                        return "GATT PENDING";
                    case 137:
                        return "GATT AUTH FAIL";
                    case 138:
                        return "GATT MORE";
                    case 139:
                        return "GATT INVALID CFG";
                    case 140:
                        return "GATT SERVICE STARTED";
                    case 141:
                        return "GATT ENCRYPTED NO MITM";
                    case 142:
                        return "GATT NOT ENCRYPTED";
                    case 143:
                        return "GATT CONGESTED";
                    default:
                        switch (i) {
                            case 253:
                                return "GATT CCCD CFG ERROR";
                            case 254:
                                return "GATT PROCEDURE IN PROGRESS";
                            case 255:
                                return "GATT VALUE OUT OF RANGE";
                            default:
                                switch (i) {
                                    case 4096:
                                        return "DFU DEVICE DISCONNECTED";
                                    case DfuBaseService.ERROR_FILE_NOT_FOUND /* 4097 */:
                                        return "DFU FILE NOT FOUND";
                                    case DfuBaseService.ERROR_FILE_ERROR /* 4098 */:
                                        return "DFU FILE ERROR";
                                    case DfuBaseService.ERROR_FILE_INVALID /* 4099 */:
                                        return "DFU NOT A VALID HEX FILE";
                                    case DfuBaseService.ERROR_FILE_IO_EXCEPTION /* 4100 */:
                                        return "DFU IO EXCEPTION";
                                    case DfuBaseService.ERROR_SERVICE_DISCOVERY_NOT_STARTED /* 4101 */:
                                        return "DFU SERVICE DISCOVERY NOT STARTED";
                                    case DfuBaseService.ERROR_SERVICE_NOT_FOUND /* 4102 */:
                                        return "DFU SERVICE NOT FOUND";
                                    case DfuBaseService.ERROR_CHARACTERISTICS_NOT_FOUND /* 4103 */:
                                        return "DFU CHARACTERISTICS NOT FOUND";
                                    default:
                                        switch (i) {
                                            case DfuBaseService.ERROR_FILE_TYPE_UNSUPPORTED /* 4105 */:
                                                return "DFU FILE TYPE NOT SUPPORTED";
                                            case DfuBaseService.ERROR_BLUETOOTH_DISABLED /* 4106 */:
                                                return "BLUETOOTH ADAPTER DISABLED";
                                            case DfuBaseService.ERROR_INIT_PACKET_REQUIRED /* 4107 */:
                                                return "INIT PACKET REQUIRED";
                                            case DfuBaseService.ERROR_FILE_SIZE_INVALID /* 4108 */:
                                                return "DFU FILE NOT WORD ALIGNED";
                                            default:
                                                return (i & DfuBaseService.ERROR_REMOTE_MASK) > 0 ? LegacyDfuError.parse(i) : "UNKNOWN (" + i + ")";
                                        }
                                }
                        }
                }
        }
    }
}
