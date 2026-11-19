package com.inuker.bluetooth.library.beacon;

import com.inuker.bluetooth.library.connect.IBluetoothApi;
import com.inuker.bluetooth.library.utils.ByteUtils;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/beacon/BeaconItem.class */
public class BeaconItem {
    public int len;
    public int type;
    public byte[] bytes;

    public String toString() {
        String format;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("@Len = %02X, @Type = 0x%02X", Integer.valueOf(this.len), Integer.valueOf(this.type)));
        switch (this.type) {
            case 8:
            case IBluetoothApi.CODE_REFRESH /* 9 */:
                format = "%c";
                break;
            default:
                format = "%02X ";
                break;
        }
        sb.append(" -> ");
        StringBuilder sbSub = new StringBuilder();
        try {
            for (byte b : this.bytes) {
                sbSub.append(String.format(format, Integer.valueOf(b & 255)));
            }
            sb.append(sbSub.toString());
        } catch (Exception e) {
            sb.append(ByteUtils.byteToString(this.bytes));
        }
        return sb.toString();
    }
}
