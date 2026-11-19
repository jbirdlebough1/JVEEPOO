package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ICountDownListener;
import com.veepoo.protocol.model.datas.CountDownData;
import com.veepoo.protocol.model.enums.ECountDownStatus;
import com.veepoo.protocol.model.settings.CountDownSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_s.class */
public class vp_s extends com.veepoo.protocol.vp_a {
    ICountDownListener vp_c;
    byte vp_d = 1;
    byte vp_e = 2;
    byte vp_f = 0;

    private byte[] vp_a(CountDownSetting countDownSetting, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -78;
        bArr[1] = b;
        if (b == this.vp_e || b == this.vp_f) {
            return bArr;
        }
        if (countDownSetting == null) {
            return bArr;
        }
        bArr[2] = VpBleByteUtil.loUint16((short) countDownSetting.getCountDownID());
        int countDownSecond = countDownSetting.getCountDownSecond();
        bArr[3] = VpBleByteUtil.intToBytes(countDownSecond)[3];
        bArr[4] = VpBleByteUtil.intToBytes(countDownSecond)[2];
        bArr[5] = VpBleByteUtil.intToBytes(countDownSecond)[1];
        if (!countDownSetting.isTestByWatch()) {
            bArr[6] = 2;
        } else if (countDownSetting.isOpenWatchUI()) {
            bArr[6] = 1;
        } else {
            bArr[6] = 0;
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_s(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_s(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((CountDownSetting) null, this.vp_e), bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0044  */
    public CountDownData vp_b(byte[] bArr) {
        CountDownData countDownData = new CountDownData();
        if (bArr.length < 20) {
            return countDownData;
        }
        if (bArr[2] == 0) {
            countDownData.setOprateSuccess(false);
            return countDownData;
        }
        countDownData.setOprateSuccess(true);
        byte b = bArr[1];
        if (b == 1) {
            countDownData.setStatus(ECountDownStatus.COUNT_SETTING);
        } else if (b == 2) {
            countDownData.setStatus(ECountDownStatus.COUNT_READ);
        } else if (b == 3) {
            countDownData.setStatus(ECountDownStatus.COUNT_ING);
        } else if (b == 4) {
            countDownData.setStatus(ECountDownStatus.COUNT_END);
        } else {
            countDownData.setStatus(ECountDownStatus.COUNT_READ);
        }
        countDownData.setCountDownID(VpBleByteUtil.byte2HexToIntArr(bArr)[3]);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        String str = strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4];
        String str2 = strArrByte2HexToStrArr[10] + strArrByte2HexToStrArr[9] + strArrByte2HexToStrArr[8];
        int iIntValue = Integer.valueOf(str, 16).intValue();
        int iIntValue2 = Integer.valueOf(str2, 16).intValue();
        countDownData.setCountDownSecondWatch(iIntValue);
        countDownData.setCountDownSecondApp(iIntValue2);
        countDownData.setOpenWatchUI(bArr[7] != 0);
        countDownData.setCountDownByWatch(bArr[11] == 1);
        return countDownData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ICountDownListener) iListener;
        this.vp_c.OnCountDownDataChange(vp_b(bArr));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CountDownSetting countDownSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, countDownSetting);
        super.send(vp_a(countDownSetting, this.vp_d), bluetoothClient, str, bleWriteResponse);
    }
}
