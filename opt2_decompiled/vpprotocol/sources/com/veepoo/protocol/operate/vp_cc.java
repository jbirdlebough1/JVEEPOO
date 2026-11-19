package com.veepoo.protocol.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IQX17DataAcquisitionListener;
import com.veepoo.protocol.listener.data.IQX17DataAcquisitionStateListener;
import com.veepoo.protocol.model.datas.QX17GPSData;
import com.veepoo.protocol.model.datas.QX17HeartRateData;
import com.veepoo.protocol.model.datas.QX17IMUData;
import com.veepoo.protocol.model.enums.EQX17VibrationMode;
import com.veepoo.protocol.model.enums.EUIFromType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cc.class */
public class vp_cc extends com.veepoo.protocol.vp_a {
    private BluetoothClient vp_h;
    private String vp_i;
    private BleWriteResponse vp_j;
    private IQX17DataAcquisitionListener vp_k;
    private byte[] vp_m;
    private int vp_n;
    private int vp_c = -1;
    private int vp_d = 0;
    private int vp_e = -1;
    private boolean vp_f = false;
    private boolean vp_g = false;
    private int vp_l = 0;

    private void vp_e(byte[] bArr, int i, int i2) {
        int iVp_a = -1;
        List listVp_d = null;
        QX17GPSData qX17GPSDataVp_b = null;
        while (true) {
            int i3 = i + 3;
            if (i3 > i2) {
                return;
            }
            byte b = bArr[i];
            int i4 = (bArr[i + 1] & 255) | ((bArr[i + 2] & 255) << 8);
            int i5 = i3 + i4;
            if (i5 > i2) {
                Log.w("QX17DataAcquisition", "parseTLVContent: TLV数据越界, tag=0x" + String.format("%02X", Byte.valueOf(b)) + ", length=" + i4 + ", remaining=" + (i2 - i3));
                return;
            }
            switch (b) {
                case -96:
                    iVp_a = vp_a(bArr, i3, i4);
                    break;
                case EUIFromType.UICode.A_GPS /* -95 */:
                    listVp_d = vp_d(bArr, i3, i4);
                    break;
                case EUIFromType.UICode.G15ImgAPPDownloadAppQRCode /* -94 */:
                    qX17GPSDataVp_b = vp_b(bArr, i3, i4);
                    break;
                case EUIFromType.UICode.G15ImgProfile /* -93 */:
                    QX17HeartRateData qX17HeartRateDataVp_c = vp_c(bArr, i3, i4);
                    Log.w("QX17DataAcquisition", "parseA3Content: hr = " + qX17HeartRateDataVp_c.getTimestamp());
                    if (!vp_a(qX17HeartRateDataVp_c.getTimestamp(), iVp_a)) {
                        return;
                    }
                    vp_a(listVp_d, qX17GPSDataVp_b, qX17HeartRateDataVp_c);
                    qX17GPSDataVp_b = null;
                    listVp_d = null;
                    iVp_a = -1;
                    break;
                default:
                    Log.w("QX17DataAcquisition", "parseTLVContent: 未知TLV tag=0x" + String.format("%02X", Byte.valueOf(b)));
                    break;
            }
            i = i5;
        }
    }

    private List vp_d(byte[] bArr, int i, int i2) {
        if (i2 < 22) {
            return null;
        }
        int i3 = i2 / 22;
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + (i4 * 22);
            QX17IMUData qX17IMUData = new QX17IMUData();
            qX17IMUData.setAx((short) ((bArr[i5] & 255) | ((bArr[i5 + 1] & 255) << 8)));
            qX17IMUData.setAy((short) ((bArr[i5 + 2] & 255) | ((bArr[i5 + 3] & 255) << 8)));
            qX17IMUData.setAz((short) ((bArr[i5 + 4] & 255) | ((bArr[i5 + 5] & 255) << 8)));
            qX17IMUData.setGx((short) ((bArr[i5 + 6] & 255) | ((bArr[i5 + 7] & 255) << 8)));
            qX17IMUData.setGy((short) ((bArr[i5 + 8] & 255) | ((bArr[i5 + 9] & 255) << 8)));
            qX17IMUData.setGz((short) ((bArr[i5 + 10] & 255) | ((bArr[i5 + 11] & 255) << 8)));
            qX17IMUData.setMx((short) ((bArr[i5 + 12] & 255) | ((bArr[i5 + 13] & 255) << 8)));
            qX17IMUData.setMy((short) ((bArr[i5 + 14] & 255) | ((bArr[i5 + 15] & 255) << 8)));
            qX17IMUData.setMz((short) ((bArr[i5 + 16] & 255) | ((bArr[i5 + 17] & 255) << 8)));
            qX17IMUData.setTimestamp((bArr[i5 + 18] & 255) | ((bArr[i5 + 19] & 255) << 8) | ((bArr[i5 + 20] & 255) << 16) | ((bArr[i5 + 21] & 255) << 24));
            arrayList.add(qX17IMUData);
        }
        return arrayList;
    }

    private boolean vp_a(int i, int i2) {
        int i3 = this.vp_c;
        if (i3 < 0) {
            this.vp_c = i;
            this.vp_f = false;
            return true;
        }
        int i4 = i - i3;
        if (i4 > 1000) {
            Log.w("QX17DataAcquisition", "checkTimestampContinuity: 检测到时间间隙! gap=" + i4 + "ms, lastTs=" + this.vp_c + ", curTs=" + i + ", retranCount=" + this.vp_d + ", packetRetranCount=" + i2);
            boolean z = this.vp_f;
            if (z && i2 == this.vp_d) {
                Log.w("QX17DataAcquisition", "checkTimestampContinuity: 重传后仍缺失数据，以设备最新数据为基准继续监测");
                this.vp_f = false;
                this.vp_c = i;
                return true;
            }
            if (!z) {
                vp_a(this.vp_c);
                this.vp_c = i;
                return false;
            }
        } else {
            this.vp_f = false;
        }
        this.vp_c = i;
        return true;
    }

    private static String vp_b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X ", Byte.valueOf(b)));
        }
        return sb.toString().trim();
    }

    public void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        this.vp_h = bluetoothClient;
        this.vp_i = str;
        this.vp_j = bleWriteResponse;
        this.vp_g = true;
        this.vp_c = -1;
        this.vp_d = 0;
        this.vp_e = -1;
        this.vp_f = false;
        vp_b();
        byte[] bArr = new byte[20];
        bArr[0] = -13;
        bArr[1] = 54;
        bArr[2] = 0;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
        Log.d("QX17DataAcquisition", "startDataAcquisition: 发送开启采集指令");
    }

    public void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        this.vp_g = false;
        this.vp_h = bluetoothClient;
        this.vp_i = str;
        vp_b();
        byte[] bArr = new byte[20];
        bArr[0] = -13;
        bArr[1] = 55;
        bArr[2] = 0;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
        Log.d("QX17DataAcquisition", "stopDataAcquisition: 发送关闭采集指令");
    }

    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        this.vp_h = bluetoothClient;
        this.vp_i = str;
        this.vp_j = bleWriteResponse;
        this.vp_d++;
        this.vp_f = true;
        this.vp_e = i;
        byte[] bArr = new byte[20];
        bArr[0] = -13;
        bArr[1] = 54;
        bArr[2] = 4;
        bArr[3] = (byte) (i & 255);
        bArr[4] = (byte) ((i >> 8) & 255);
        bArr[5] = (byte) ((i >> 16) & 255);
        bArr[6] = (byte) ((i >> 24) & 255);
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
        Log.d("QX17DataAcquisition", "continueDataAcquisition: 发送重传指令, retranTimeStamp=" + i + ", retranCount=" + this.vp_d);
    }

    public void vp_c(byte[] bArr) {
        if (bArr == null || bArr.length < 5 || bArr[0] != -105) {
            return;
        }
        int i = (bArr[1] & 255) | ((bArr[2] & 255) << 8);
        int i2 = (bArr[3] & 255) | ((bArr[4] & 255) << 8);
        Log.d("QX17DataAcquisition", "handleADCData: current=" + i + ", total=" + i2 + ", len=" + bArr.length);
        if (i2 == 1) {
            vp_e(bArr, 5, bArr.length);
        } else {
            vp_a(i, i2, bArr, 5, bArr.length);
        }
    }

    private QX17GPSData vp_b(byte[] bArr, int i, int i2) {
        if (i2 < 16) {
            return null;
        }
        QX17GPSData qX17GPSData = new QX17GPSData();
        qX17GPSData.setLatitude(Float.intBitsToFloat((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24)));
        qX17GPSData.setLongitude(Float.intBitsToFloat((bArr[i + 4] & 255) | ((bArr[i + 5] & 255) << 8) | ((bArr[i + 6] & 255) << 16) | ((bArr[i + 7] & 255) << 24)));
        qX17GPSData.setAccuracy(Float.intBitsToFloat((bArr[i + 8] & 255) | ((bArr[i + 9] & 255) << 8) | ((bArr[i + 10] & 255) << 16) | ((bArr[i + 11] & 255) << 24)));
        qX17GPSData.setTimestamp((bArr[i + 12] & 255) | ((bArr[i + 13] & 255) << 8) | ((bArr[i + 14] & 255) << 16) | ((bArr[i + 15] & 255) << 24));
        return qX17GPSData;
    }

    private QX17HeartRateData vp_c(byte[] bArr, int i, int i2) {
        if (i2 < 5) {
            return null;
        }
        QX17HeartRateData qX17HeartRateData = new QX17HeartRateData();
        qX17HeartRateData.setHeartRate(bArr[i] & 255);
        qX17HeartRateData.setTimestamp((bArr[i + 1] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 3] & 255) << 16) | ((bArr[i + 4] & 255) << 24));
        return qX17HeartRateData;
    }

    public int vp_a() {
        int i = this.vp_c;
        int i2 = i;
        if (i < 0) {
            i2 = 0;
        }
        return i2;
    }

    private void vp_b() {
        this.vp_l = 0;
        this.vp_m = null;
        this.vp_n = 0;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        if (bArr == null || bArr.length < 3) {
            return;
        }
        Log.d("QX17DataAcquisition", "handler F3: " + vp_b(bArr));
        boolean z = bArr[1] == 54;
        if (iListener != null && (iListener instanceof IQX17DataAcquisitionStateListener)) {
            ((IQX17DataAcquisitionStateListener) iListener).onQX17DataAcquisitionStatus(z);
        }
        IQX17DataAcquisitionListener iQX17DataAcquisitionListener = this.vp_k;
        if (iQX17DataAcquisitionListener != null) {
            iQX17DataAcquisitionListener.onQX17DataAcquisitionStatus(z);
        }
        if (z) {
            this.vp_g = true;
        }
    }

    private int vp_a(byte[] bArr, int i, int i2) {
        if (i2 < 4) {
            return -1;
        }
        int i3 = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
        Log.d("QX17DataAcquisition", "parseContentInfo: 重传次数=" + i3);
        return i3;
    }

    private void vp_a(int i, int i2, byte[] bArr, int i3, int i4) {
        if (this.vp_l != i2) {
            vp_b();
            this.vp_l = i2;
            this.vp_m = new byte[i2 * (i4 - i3)];
        }
        int i5 = i4 - i3;
        byte[] bArr2 = this.vp_m;
        if (bArr2 != null) {
            int i6 = this.vp_n;
            if (i6 + i5 <= bArr2.length) {
                System.arraycopy(bArr, i3, bArr2, i6, i5);
                this.vp_n += i5;
            }
        }
        if (i == i2) {
            vp_e(this.vp_m, 0, this.vp_n);
            vp_b();
        }
    }

    private void vp_a(List list, QX17GPSData qX17GPSData, QX17HeartRateData qX17HeartRateData) {
        if (this.vp_k == null) {
            return;
        }
        if (list != null && !list.isEmpty()) {
            this.vp_k.onQX17IMUData(list);
        }
        if (qX17GPSData != null) {
            this.vp_k.onQX17GPSData(qX17GPSData);
        }
        if (qX17HeartRateData != null) {
            this.vp_k.onQX17HeartRateData(qX17HeartRateData);
        }
    }

    public void vp_a(IQX17DataAcquisitionListener iQX17DataAcquisitionListener) {
        this.vp_k = iQX17DataAcquisitionListener;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EQX17VibrationMode eQX17VibrationMode, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -50;
        bArr[1] = 2;
        bArr[2] = (byte) eQX17VibrationMode.getValue();
        bArr[3] = (byte) i;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
        Log.d("QX17DataAcquisition", "setVibrationMode: mode=" + eQX17VibrationMode + ", duration=" + i);
    }

    private void vp_a(int i) {
        if (this.vp_h == null || this.vp_i == null) {
            Log.w("QX17DataAcquisition", "triggerRetransmission: BLE客户端未初始化，无法发送重传指令");
        } else {
            Log.d("QX17DataAcquisition", "triggerRetransmission: 请求从 " + i + "ms 开始重传");
            vp_f(this.vp_h, this.vp_i, this.vp_j, i);
        }
    }
}
