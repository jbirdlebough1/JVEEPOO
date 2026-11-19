package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IAutoDetectOriginDataListener;
import com.veepoo.protocol.listener.data.IMusicControlListener;
import com.veepoo.protocol.model.datas.AutoDetectOriginData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_w.class */
public class vp_w extends com.veepoo.protocol.vp_a {
    IAutoDetectOriginDataListener vp_c;
    List vp_d = new ArrayList();
    byte vp_e = 1;
    byte vp_f = 2;

    private byte[] vp_a(TimeData timeData) {
        byte[] bArr = new byte[20];
        bArr[0] = -103;
        bArr[1] = this.vp_f;
        bArr[2] = VpBleByteUtil.loUint16((short) (timeData.getYear() - 2000));
        bArr[3] = VpBleByteUtil.loUint16((short) timeData.getMonth());
        bArr[4] = VpBleByteUtil.loUint16((short) timeData.getDay());
        bArr[5] = VpBleByteUtil.loUint16((short) timeData.getHour());
        bArr[6] = VpBleByteUtil.loUint16((short) timeData.getMinute());
        bArr[7] = VpBleByteUtil.loUint16((short) timeData.getSecond());
        return bArr;
    }

    private AutoDetectOriginData vp_b(byte[] bArr) {
        AutoDetectOriginData autoDetectOriginData = null;
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        TimeData timeData = new TimeData(iArrByte2HexToIntArr[0] + 2000, iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5]);
        int i = iArrByte2HexToIntArr[6];
        int i2 = iArrByte2HexToIntArr[7];
        if (com.veepoo.protocol.util.vp_j.vp_a(timeData)) {
            autoDetectOriginData = autoDetectOriginData;
            AutoDetectOriginData autoDetectOriginData2 = new AutoDetectOriginData(timeData, i, i2);
        }
        return autoDetectOriginData;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IAutoDetectOriginDataListener iAutoDetectOriginDataListener;
        if (bArr == null || bArr.length < 20 || bArr[1] != this.vp_f) {
            return;
        }
        if (bArr[2] != 0 || (iAutoDetectOriginDataListener = this.vp_c) == null) {
            vp_c(bArr);
        } else {
            iAutoDetectOriginDataListener.onAutoDetectOriginDataChangeListener(this.vp_d);
        }
    }

    public void vp_c(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, 3, bArr2, 0, 8);
        AutoDetectOriginData autoDetectOriginDataVp_b = vp_b(bArr2);
        if (autoDetectOriginDataVp_b != null) {
            this.vp_d.add(autoDetectOriginDataVp_b);
        }
        byte[] bArr3 = new byte[8];
        System.arraycopy(bArr, 11, bArr3, 0, 8);
        AutoDetectOriginData autoDetectOriginDataVp_b2 = vp_b(bArr3);
        if (autoDetectOriginDataVp_b2 != null) {
            this.vp_d.add(autoDetectOriginDataVp_b2);
        }
    }

    public void vp_a(byte[] bArr, IMusicControlListener iMusicControlListener) {
        if (bArr == null || bArr.length < 20 || iMusicControlListener == null) {
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b == 1) {
            if (b2 == 0) {
                iMusicControlListener.oprateMusicFail();
                return;
            } else {
                if (b2 != 1) {
                    return;
                }
                iMusicControlListener.oprateMusicSuccess();
                return;
            }
        }
        if (b != 2) {
            if (b != 3) {
                return;
            }
            if (b2 == 5) {
                iMusicControlListener.voiceUp();
                return;
            } else {
                if (b2 != 6) {
                    return;
                }
                iMusicControlListener.voiceDown();
                return;
            }
        }
        switch (b2) {
            case 1:
                iMusicControlListener.playMusic();
                break;
            case 2:
                iMusicControlListener.pauseMusic();
                break;
            case 3:
                iMusicControlListener.previousMusic();
                break;
            case 4:
                iMusicControlListener.nextMusic();
                break;
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, IAutoDetectOriginDataListener iAutoDetectOriginDataListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, timeData, iAutoDetectOriginDataListener);
        this.vp_d.clear();
        this.vp_c = iAutoDetectOriginDataListener;
        super.send(vp_a(timeData), bluetoothClient, str, bleWriteResponse);
    }
}
