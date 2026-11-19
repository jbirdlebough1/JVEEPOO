package com.veepoo.protocol.operate;

import android.util.SparseArray;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IRRIntervalProgressListener;
import com.veepoo.protocol.model.DayState;
import com.veepoo.protocol.model.datas.RRIntervalData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cd.class */
public class vp_cd extends com.veepoo.protocol.vp_a {
    BluetoothClient vp_d;
    String vp_e;
    BleWriteResponse vp_f;
    private int vp_g;
    private SparseArray vp_l;
    private IRRIntervalProgressListener vp_m;
    private boolean vp_c = false;
    int vp_h = -1;
    int vp_i = 1;
    int vp_j = 0;
    private List vp_k = new ArrayList();
    int[] vp_n = {1, 2, 4, 4, 4, 6};
    int[] vp_o = {0, 0, 0, 0, 0, 0, 1};

    public vp_cd() {
        this.vp_l = new SparseArray();
        SparseArray sparseArray = new SparseArray();
        this.vp_l = sparseArray;
        sparseArray.put(DayState.TODAY.getState(), new ArrayList());
        this.vp_l.put(DayState.YESTERDAY.getState(), new ArrayList());
        this.vp_l.put(DayState.BEFORE_YESTERDAY.getState(), new ArrayList());
    }

    private RRIntervalData vp_a(RRIntervalData rRIntervalData, int i, int i2, int[] iArr) {
        int length = iArr.length;
        int[] data = rRIntervalData.getData();
        int length2 = data.length;
        new StringBuilder().append("-- rrCount[rr数据长度] = ").append(length2).append("  fillIndex[rr源数据开始填充位置] = ").append(i).append(" startIndex[data 开始截取的位置] = ").append(i2).append("  dataLength = ").append(length);
        for (int i3 = i2; i3 < length; i3++) {
            int i4 = (i + i3) - i2;
            if (i3 < length2 && i4 < length2) {
                data[i4] = iArr[i3];
                this.vp_j = i4;
            }
        }
        rRIntervalData.setData(data);
        return rRIntervalData;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        int iTwoByteToUnsignedInt;
        super.handler(bArr);
        if (bArr.length >= 20 && (iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(bArr[2], bArr[1])) != 0) {
            this.vp_i = VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[3]);
            byte b = bArr[5];
            byte b2 = bArr[6];
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            if (b == 0) {
                int i = iArrByte2HexToIntArr[7];
                this.vp_h = i;
                int i2 = iArrByte2HexToIntArr[8] + 2000;
                int i3 = iArrByte2HexToIntArr[9];
                int i4 = iArrByte2HexToIntArr[10];
                int i5 = iArrByte2HexToIntArr[11];
                int i6 = iArrByte2HexToIntArr[12];
                int i7 = iArrByte2HexToIntArr[13];
                int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt(bArr[15], bArr[14]);
                RRIntervalData rRIntervalData = new RRIntervalData();
                rRIntervalData.setBlockNumber(iTwoByteToUnsignedInt);
                TimeData timeData = new TimeData();
                timeData.setYear(i2);
                timeData.setMonth(i3);
                timeData.setDay(i4);
                timeData.setHour(i5);
                timeData.setMinute(i6);
                timeData.setSecond(i7);
                rRIntervalData.setDate(timeData);
                rRIntervalData.setDayState(i);
                rRIntervalData.setTotalBlock(this.vp_i);
                rRIntervalData.setData(new int[iTwoByteToUnsignedInt2]);
                this.vp_j = 0;
                vp_a(rRIntervalData, 0, 16, iArrByte2HexToIntArr);
                vp_a(i, rRIntervalData);
            } else {
                RRIntervalData rRIntervalDataVp_b = vp_b(this.vp_h, iTwoByteToUnsignedInt);
                if (rRIntervalDataVp_b != null) {
                    vp_a(rRIntervalDataVp_b, this.vp_j + 1, 7, iArrByte2HexToIntArr);
                }
            }
            if (b == b2) {
                RRIntervalData rRIntervalDataVp_b2 = vp_b(this.vp_h, iTwoByteToUnsignedInt);
                if (rRIntervalDataVp_b2 != null) {
                    new StringBuilder().append("一块逐跳帧数据解析完毕：RRIntervalData ").append(rRIntervalDataVp_b2.toString());
                    IRRIntervalProgressListener iRRIntervalProgressListener = this.vp_m;
                    if (iRRIntervalProgressListener != null) {
                        iRRIntervalProgressListener.onReadRRIntervalProgressChanged(vp_a(iTwoByteToUnsignedInt, this.vp_i), rRIntervalDataVp_b2);
                    }
                }
                if (iTwoByteToUnsignedInt != this.vp_i || this.vp_m == null) {
                    return;
                }
                new StringBuilder().append("onReadRRIntervalComplete --》 currentReadDay = ").append(this.vp_h).append(" || -> blockNumber = ").append(iTwoByteToUnsignedInt).append(" || -> allBlock = ").append(this.vp_i);
                this.vp_m.onReadRRIntervalComplete(DayState.getDayState(this.vp_h), (List) this.vp_l.get(this.vp_h));
            }
        }
    }

    public RRIntervalData vp_b(int i, int i2) {
        for (RRIntervalData rRIntervalData : (List) this.vp_l.get(DayState.getDayState(i).getState())) {
            if (rRIntervalData.getBlockNumber() == i2) {
                return rRIntervalData;
            }
        }
        return null;
    }

    private float vp_a(int i, int i2) {
        int i3 = this.vp_g;
        int i4 = i - i3;
        int i5 = i2 - i3;
        if (i5 == 0) {
            return 0.0f;
        }
        return i4 / (i5 * 1.0f);
    }

    public byte[] vp_a(DayState dayState, int i) {
        int state = dayState.getState();
        byte[] bArr = new byte[20];
        bArr[0] = 112;
        bArr[1] = VpBleByteUtil.loUint16((short) state);
        short s = (short) i;
        bArr[2] = VpBleByteUtil.loUint16(s);
        bArr[3] = VpBleByteUtil.hiUint16(s);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_m = (IRRIntervalProgressListener) iListener;
        handler(bArr);
    }

    private void vp_a(int i, RRIntervalData rRIntervalData) {
        ((List) this.vp_l.get(DayState.getDayState(i).getState())).add(rRIntervalData);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, DayState dayState, int i) {
        this.vp_d = bluetoothClient;
        this.vp_e = str;
        this.vp_g = i;
        this.vp_h = dayState.getState();
        this.vp_f = bleWriteResponse;
        this.vp_j = 0;
        ((List) this.vp_l.get(dayState.getState())).clear();
        send(vp_a(dayState, i), bluetoothClient, str, bleWriteResponse);
    }
}
