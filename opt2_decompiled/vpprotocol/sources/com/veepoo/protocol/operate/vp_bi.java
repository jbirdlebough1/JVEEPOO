package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ILongSeatDataListener;
import com.veepoo.protocol.model.datas.LongSeatData;
import com.veepoo.protocol.model.enums.ELongSeatStatus;
import com.veepoo.protocol.model.settings.LongSeatSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bi.class */
public class vp_bi extends com.veepoo.protocol.vp_a {
    ILongSeatDataListener vp_c;

    private LongSeatData vp_b(byte[] bArr) {
        byte b;
        LongSeatData longSeatData = new LongSeatData();
        if (bArr.length < 8) {
            longSeatData.setStatus(ELongSeatStatus.UNKONW);
            return longSeatData;
        }
        if (bArr.length < 9) {
            b = bArr[7];
        } else {
            b = bArr[8];
            longSeatData.setOpen(bArr[7] == 1);
        }
        byte b2 = bArr[1];
        if (b2 == 16 || bArr[3] == 16) {
            longSeatData.setStatus(ELongSeatStatus.UNSUPPORT);
            return longSeatData;
        }
        if (b != 1 && b != 0 && b != 2) {
            longSeatData.setStatus(ELongSeatStatus.UNKONW);
            return longSeatData;
        }
        if (b2 != 1 && b2 != 0) {
            longSeatData.setStatus(ELongSeatStatus.UNKONW);
            return longSeatData;
        }
        if (b == 1) {
            if (b2 == 1) {
                longSeatData.setStatus(ELongSeatStatus.OPEN_SUCCESS);
            } else {
                longSeatData.setStatus(ELongSeatStatus.OPEN_FAIL);
            }
        } else if (b == 0) {
            if (b2 == 1) {
                longSeatData.setStatus(ELongSeatStatus.CLOSE_SUCCESS);
            } else {
                longSeatData.setStatus(ELongSeatStatus.CLOSE_FAIL);
            }
        } else if (b != 2) {
            longSeatData.setStatus(ELongSeatStatus.UNKONW);
        } else if (b2 == 1) {
            longSeatData.setStatus(ELongSeatStatus.READ_SUCCESS);
        } else {
            longSeatData.setStatus(ELongSeatStatus.READ_FAIL);
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        longSeatData.setStartHour(iArrByte2HexToIntArr[2]);
        longSeatData.setStartMinute(iArrByte2HexToIntArr[3]);
        longSeatData.setEndHour(iArrByte2HexToIntArr[4]);
        longSeatData.setEndMinute(iArrByte2HexToIntArr[5]);
        longSeatData.setThreshold(iArrByte2HexToIntArr[6]);
        return longSeatData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ab(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ab(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_x, bluetoothClient, str, bleWriteResponse);
    }

    public byte[] vp_a(LongSeatSetting longSeatSetting) {
        return new byte[]{-31, VpBleByteUtil.intToBytes(longSeatSetting.getStartHour())[3], VpBleByteUtil.intToBytes(longSeatSetting.getStartMiute())[3], VpBleByteUtil.intToBytes(longSeatSetting.getEndHour())[3], VpBleByteUtil.intToBytes(longSeatSetting.getEndMinute())[3], VpBleByteUtil.intToBytes(longSeatSetting.getThreshold())[3], VpBleByteUtil.intToBytes(longSeatSetting.isOpen() ? 1 : 0)[3]};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ILongSeatDataListener) iListener;
        this.vp_c.onLongSeatDataChange(vp_b(bArr));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, LongSeatSetting longSeatSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, longSeatSetting);
        super.send(vp_a(longSeatSetting), bluetoothClient, str, bleWriteResponse);
    }
}
