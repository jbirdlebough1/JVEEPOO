package com.veepoo.protocol.err;

import com.veepoo.protocol.util.Spo2hOriginUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/err/VPErrorStatus.class */
public class VPErrorStatus {
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
}
