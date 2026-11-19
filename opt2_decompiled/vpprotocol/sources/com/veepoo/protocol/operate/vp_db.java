package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IResponseListener;
import com.veepoo.protocol.model.enums.ETimeMode;
import com.veepoo.protocol.model.settings.DeviceTimeSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.Calendar;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_db.class */
public class vp_db extends com.veepoo.protocol.vp_a {
    IResponseListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_db$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[ETimeMode.values().length];
            vp_a = iArr;
            try {
                iArr[ETimeMode.MODE_12.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[ETimeMode.MODE_24.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public byte[] vp_a(DeviceTimeSetting deviceTimeSetting) {
        byte[] bArr = new byte[20];
        int year = deviceTimeSetting.getYear();
        int month = deviceTimeSetting.getMonth();
        int day = deviceTimeSetting.getDay();
        int hour = deviceTimeSetting.getHour();
        int minute = deviceTimeSetting.getMinute();
        int second = deviceTimeSetting.getSecond();
        String str = "0" + Integer.toHexString(year);
        bArr[0] = -91;
        bArr[1] = VpBleByteUtil.HexStringToBinary(str.substring(0, 2))[0];
        bArr[2] = VpBleByteUtil.HexStringToBinary(str.substring(2, 4))[0];
        bArr[3] = VpBleByteUtil.loUint16((short) month);
        bArr[4] = VpBleByteUtil.loUint16((short) day);
        bArr[5] = VpBleByteUtil.loUint16((short) hour);
        bArr[6] = VpBleByteUtil.loUint16((short) minute);
        bArr[7] = VpBleByteUtil.loUint16((short) second);
        bArr[8] = vp_a(deviceTimeSetting.geteTimeMode());
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        IResponseListener iResponseListener = this.vp_c;
        if (iResponseListener != null) {
            iResponseListener.response(iArrByte2HexToIntArr[1]);
        }
    }

    private byte vp_a(ETimeMode eTimeMode) {
        int i = vp_a.vp_a[eTimeMode.ordinal()];
        if (i != 1) {
            return i != 2 ? (byte) 0 : (byte) 2;
        }
        return (byte) 1;
    }

    public DeviceTimeSetting vp_a() {
        Calendar calendar = Calendar.getInstance();
        return new DeviceTimeSetting(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IResponseListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, bArr);
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, DeviceTimeSetting deviceTimeSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, deviceTimeSetting);
        if (deviceTimeSetting == null) {
            deviceTimeSetting = vp_a();
        }
        super.send(vp_a(deviceTimeSetting), bluetoothClient, str, bleWriteResponse);
    }
}
