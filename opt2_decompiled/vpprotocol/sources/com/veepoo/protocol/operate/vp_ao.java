package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.model.datas.FatigueData;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.EFatigueStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ao.class */
public class vp_ao extends com.veepoo.protocol.vp_a {
    IFatigueDataListener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_bb(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bb(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bn, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bo(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bo(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bo, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onFatigueDataListener(vp_b(bArr));
    }

    public FatigueData vp_b(byte[] bArr) {
        FatigueData fatigueData = new FatigueData();
        if (bArr.length >= 5) {
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            fatigueData.setFatigueState(vp_b(iArrByte2HexToIntArr[1]));
            fatigueData.setDeviceState(vp_a(iArrByte2HexToIntArr[2]));
            fatigueData.setProgress(iArrByte2HexToIntArr[3]);
            fatigueData.setValue(iArrByte2HexToIntArr[4]);
        }
        return fatigueData;
    }

    public EDeviceStatus vp_a(int i) {
        if (i == 0) {
            return EDeviceStatus.FREE;
        }
        if (i == 1) {
            return EDeviceStatus.DETECT_BP;
        }
        if (i == 2) {
            return EDeviceStatus.DETECT_HEART;
        }
        if (i == 3) {
            return EDeviceStatus.DETECT_AUTO_FIVE;
        }
        if (i == 4) {
            return EDeviceStatus.DETECT_SP;
        }
        return i == 5 ? EDeviceStatus.DETECT_FTG : EDeviceStatus.FREE;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IFatigueDataListener) iListener;
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isDetectingFtg()) {
            handler(bArr);
        }
    }

    public EFatigueStatus vp_b(int i) {
        if (i == 0) {
            return EFatigueStatus.NOT_SUPPORT;
        }
        if (i == 1) {
            return EFatigueStatus.OPEN;
        }
        return i == 2 ? EFatigueStatus.CLOSE : EFatigueStatus.UNKONW;
    }
}
