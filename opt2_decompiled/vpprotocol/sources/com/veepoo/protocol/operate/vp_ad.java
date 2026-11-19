package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IDrinkDataListener;
import com.veepoo.protocol.model.datas.DrinkData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ad.class */
public class vp_ad extends com.veepoo.protocol.vp_a {
    IDrinkDataListener vp_c;

    private DrinkData vp_b(byte[] bArr) {
        DrinkData drinkData = new DrinkData();
        TimeData timeData = new TimeData();
        TimeData timeData2 = new TimeData();
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        timeData.setYear(TimeData.getSysYear());
        timeData.setMonth(iArrByte2HexToIntArr[4]);
        timeData.setDay(iArrByte2HexToIntArr[5]);
        timeData.setHour(iArrByte2HexToIntArr[6]);
        timeData.setMinute(iArrByte2HexToIntArr[7]);
        timeData2.setYear(TimeData.getSysYear());
        timeData2.setMonth(iArrByte2HexToIntArr[10]);
        timeData2.setDay(iArrByte2HexToIntArr[11]);
        timeData2.setHour(iArrByte2HexToIntArr[12]);
        timeData2.setMinute(iArrByte2HexToIntArr[13]);
        if (com.veepoo.protocol.util.vp_j.vp_c(timeData.getDateAndClockForDb()) && com.veepoo.protocol.util.vp_j.vp_c(timeData2.getDateAndClockForDb())) {
            boolean z = iArrByte2HexToIntArr[15] != 0;
            drinkData.setDrinkStartTime(timeData);
            drinkData.setDrinkWakeupTime(timeData2);
            drinkData.setDrinkLevel(iArrByte2HexToIntArr[14]);
            drinkData.setDrinkAllergy(z);
            return drinkData;
        }
        return drinkData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_v(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_v(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_at, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IDrinkDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length < 20) {
            return;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        DrinkData drinkDataVp_b = vp_b(bArr);
        if (drinkDataVp_b.getDrinkStartTime() != null) {
            this.vp_c.onDrinkDataChange(b, drinkDataVp_b);
        }
        if (b2 == b3 && b3 == -1) {
            this.vp_c.onReadDrinkComplete();
            return;
        }
        if (b2 == b3 && b3 == 0) {
            this.vp_c.onReadDrinkComplete();
        } else if (b == 0) {
            this.vp_c.onReadDrinkComplete();
        }
    }
}
