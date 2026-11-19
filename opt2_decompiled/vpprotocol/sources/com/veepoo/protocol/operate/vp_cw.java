package com.veepoo.protocol.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ITcmDiagnosisDetectListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.TcmDiagnosis;
import com.veepoo.protocol.model.datas.TcmDiagnosisDetectState;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cw.class */
public class vp_cw extends com.veepoo.protocol.vp_a {
    ITcmDiagnosisDetectListener vp_c;
    TimeData vp_d;
    long vp_e;
    long vp_f;
    TcmDiagnosis vp_g = null;
    byte vp_h = 1;
    byte vp_i = 2;
    byte vp_j = 3;
    byte vp_k = 4;
    int vp_l = 250;
    CopyOnWriteArrayList vp_m = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_n = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_o = new CopyOnWriteArrayList();
    private int vp_p = 0;
    List vp_q = new ArrayList();

    private int[] vp_c() {
        int[] iArr = new int[this.vp_m.size()];
        for (int i = 0; i < this.vp_m.size(); i++) {
            iArr[i] = ((Integer) this.vp_m.get(i)).intValue();
        }
        return iArr;
    }

    private int[] vp_b(int i) {
        int[] iArr = new int[this.vp_o.size()];
        for (int i2 = 0; i2 < this.vp_o.size(); i2++) {
            iArr[i2] = ((Integer) this.vp_o.get(i2)).intValue();
        }
        return iArr;
    }

    private EcgDetectInfo vp_d(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDetectInfo();
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return new EcgDetectInfo(Integer.valueOf(strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4], 16).intValue(), Integer.valueOf(strArrByte2HexToStrArr[7] + strArrByte2HexToStrArr[6], 16).intValue());
    }

    public static int vp_a(int i) {
        return ((i >> 21) & 1) == 0 ? i & 2097151 : i | (-2097152);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bu(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bu(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(this.vp_i, this.vp_p), bluetoothClient, str, bleWriteResponse);
    }

    public void vp_e(byte[] bArr) {
        new StringBuilder().append("**ecg---Handlers-->").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (this.vp_c == null) {
            return;
        }
        Log.e("Test", "handlers ->" + ((int) bArr[4]));
        byte b = bArr[1];
        if (b == 8 && bArr[3] == 0) {
            this.vp_g = null;
            TimeData timeData = new TimeData();
            this.vp_d = timeData;
            timeData.setCurrentTime();
            this.vp_e = System.currentTimeMillis();
            EcgDetectInfo ecgDetectInfoVp_d = vp_d(bArr);
            this.vp_l = ecgDetectInfoVp_d.getDrawFrequency();
            new StringBuilder().append("ECGDetectOprate frequency ：").append(this.vp_l);
            if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType() == 9) {
                ecgDetectInfoVp_d.setFrequency(ecgDetectInfoVp_d.getDrawFrequency());
            }
            this.vp_c.onTcmDiagnosisDetectInfoChange(ecgDetectInfoVp_d);
            return;
        }
        if (b == 8 && bArr[3] == 1) {
            this.vp_c.onTcmDiagnosisDetectStateChange(vp_c(bArr));
            return;
        }
        if (b == 8) {
            Log.e("Test", "value[1]  ->" + ((int) bArr[1]));
            byte b2 = bArr[3];
            if (b2 == 4 || b2 == 3) {
                this.vp_f = System.currentTimeMillis();
                VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
                if (this.vp_g == null) {
                    this.vp_g = new TcmDiagnosis();
                }
                this.vp_g.setTimeBean(this.vp_d);
                this.vp_g.setDuration((int) ((this.vp_f - this.vp_e) / 1000));
                this.vp_g.setFrequency(this.vp_l);
                this.vp_g.setFilterSignals(vp_b());
                this.vp_g.setPpgSignals(vp_c());
                this.vp_g.setPowers(vp_b(this.vp_l));
                this.vp_g.setSuccess(bArr[3] == 4);
                this.vp_c.onTcmDiagnosisDiagnosisChange(this.vp_g);
                new StringBuilder().append("**ecg---Handlers--> 测试成功 mEcgDiagnosis = ").append(this.vp_g);
                return;
            }
            if (b2 == 5) {
                Log.e("Test", "value[3]  ->" + ((int) bArr[4]));
                byte b3 = bArr[4];
                byte b4 = bArr[5];
                new StringBuilder().append("**ecg---Handlers-->  ECG多诊断包[").append((int) b3).append("/").append((int) b4).append("]");
                if (b3 == 1) {
                    this.vp_q.clear();
                }
                this.vp_q.add(bArr);
                if (b3 == b4) {
                    this.vp_g = vp_b(vp_a());
                    new StringBuilder().append("**ecg---Handlers-->  ECG多诊断结果:").append(this.vp_g.toString());
                }
            }
        }
    }

    private int[] vp_b() {
        int[] iArr = new int[this.vp_n.size()];
        for (int i = 0; i < this.vp_n.size(); i++) {
            iArr[i] = ((Integer) this.vp_n.get(i)).intValue();
        }
        return iArr;
    }

    protected byte[] vp_a(byte b, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 8;
        bArr[2] = b;
        bArr[3] = (byte) i;
        return bArr;
    }

    public TcmDiagnosisDetectState vp_c(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new TcmDiagnosisDetectState();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[15] + strArrByte2HexToStrArr[14], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[18] + strArrByte2HexToStrArr[17], 16).intValue();
        int i = iArrByte2HexToIntArr[16];
        TcmDiagnosisDetectState tcmDiagnosisDetectState = new TcmDiagnosisDetectState(iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], vp_a(bArr[4]), iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8], iArrByte2HexToIntArr[9], iArrByte2HexToIntArr[10], iArrByte2HexToIntArr[11], iArrByte2HexToIntArr[12], iArrByte2HexToIntArr[13], iIntValue, iArrByte2HexToIntArr[19]);
        tcmDiagnosisDetectState.setPwv(i / 10.0f);
        tcmDiagnosisDetectState.setPat(iIntValue2 / 10.0f);
        return tcmDiagnosisDetectState;
    }

    private EDeviceStatus vp_a(byte b) {
        if (b != -17) {
            if (b == -4) {
                return EDeviceStatus.UNPASS_WEAR;
            }
            if (b != -3) {
                switch (b) {
                    case DfuBaseService.PROGRESS_CONNECTING /* -1 */:
                        return EDeviceStatus.BUSY;
                    case 0:
                        return EDeviceStatus.FREE;
                    case 1:
                        return EDeviceStatus.DETECT_PPG;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        return EDeviceStatus.BUSY;
                }
            }
            return EDeviceStatus.CHARGING;
        }
        return EDeviceStatus.CHARG_LOW;
    }

    public TcmDiagnosis vp_b(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new TcmDiagnosis();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        TcmDiagnosis tcmDiagnosis = new TcmDiagnosis();
        int i = iArrByte2HexToIntArr[0];
        int[] iArr = {iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8]};
        int i2 = iArrByte2HexToIntArr[9];
        int i3 = iArrByte2HexToIntArr[10];
        int i4 = iArrByte2HexToIntArr[11];
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[13] + strArrByte2HexToStrArr[12], 16).intValue();
        int i5 = iArrByte2HexToIntArr[14];
        int i6 = iArrByte2HexToIntArr[15];
        int i7 = iArrByte2HexToIntArr[16];
        int i8 = iArrByte2HexToIntArr[17];
        int i9 = iArrByte2HexToIntArr[18];
        int i10 = iArrByte2HexToIntArr[19];
        int[] iArr2 = new int[32];
        System.arraycopy(iArrByte2HexToIntArr, 20, iArr2, 0, 32);
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[53] + strArrByte2HexToStrArr[52], 16).intValue();
        int iIntValue3 = Integer.valueOf(strArrByte2HexToStrArr[55] + strArrByte2HexToStrArr[54], 16).intValue();
        int iIntValue4 = Integer.valueOf(strArrByte2HexToStrArr[57] + strArrByte2HexToStrArr[56], 16).intValue();
        int iIntValue5 = Integer.valueOf(strArrByte2HexToStrArr[59] + strArrByte2HexToStrArr[58], 16).intValue();
        int iIntValue6 = Integer.valueOf(strArrByte2HexToStrArr[63] + strArrByte2HexToStrArr[62] + strArrByte2HexToStrArr[61] + strArrByte2HexToStrArr[60], 16).intValue();
        int iIntValue7 = Integer.valueOf(strArrByte2HexToStrArr[67] + strArrByte2HexToStrArr[66] + strArrByte2HexToStrArr[65] + strArrByte2HexToStrArr[64], 16).intValue();
        int iIntValue8 = Integer.valueOf(strArrByte2HexToStrArr[69] + strArrByte2HexToStrArr[68], 16).intValue();
        tcmDiagnosis.setLeadOffType(i);
        tcmDiagnosis.setHeartRate(i2);
        tcmDiagnosis.setRespRate(i3);
        tcmDiagnosis.setHrv(i4);
        tcmDiagnosis.setQtTime(iIntValue);
        tcmDiagnosis.setPwvMeanVal(iIntValue4);
        tcmDiagnosis.setDiagnosis8(iArr);
        tcmDiagnosis.setDiseaseRisk(i5);
        tcmDiagnosis.setPressureIndex(i6);
        tcmDiagnosis.setFatigueIndex(i7);
        tcmDiagnosis.setMyocarditisRisk(i8);
        tcmDiagnosis.setChdRisk(i9);
        tcmDiagnosis.setAngioscleroticRisk(i10);
        tcmDiagnosis.setRisk32(iArr2);
        tcmDiagnosis.setQrsTime(iIntValue2);
        tcmDiagnosis.setQrsAmp(iIntValue3);
        tcmDiagnosis.setStMeanAmp(iIntValue5);
        tcmDiagnosis.setDiseaseSdnn(iIntValue6);
        tcmDiagnosis.setDiseaseRmssd(iIntValue7);
        tcmDiagnosis.setPat(iIntValue8 / 10.0f);
        return tcmDiagnosis;
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[this.vp_q.size() * 14];
        for (int i = 0; i < this.vp_q.size(); i++) {
            System.arraycopy((byte[]) this.vp_q.get(i), 6, bArr, i * 14, 14);
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        if (bArr == null || bArr.length < 20 || this.vp_c == null) {
            return;
        }
        byte b = bArr[bArr.length - 4];
        int i = bArr[bArr.length - 3] & 255;
        int i2 = bArr[bArr.length - 1] & 255;
        ArrayList arrayList = new ArrayList();
        byte b2 = bArr[0];
        int i3 = b2 == -127 ? 1 : 0;
        if (b2 == -126) {
            i3 = 2;
        }
        if (b2 == -120) {
            i3 = 3;
        }
        for (int i4 = 1; i4 < bArr.length - 4; i4 += 3) {
            int i5 = ((bArr[i4] << 16) & 16711680) | ((bArr[i4 + 1] << 8) & 65280) | (bArr[i4 + 2] & 255);
            if (i5 != 8421504) {
                int i6 = i3;
                int iVp_a = vp_a(i5);
                if (i6 == 3) {
                    this.vp_n.add(Integer.valueOf(iVp_a));
                    this.vp_o.add(Integer.valueOf(i));
                } else {
                    this.vp_m.add(Integer.valueOf(iVp_a));
                }
                arrayList.add(Integer.valueOf(iVp_a));
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            iArr[i7] = ((Integer) arrayList.get(i7)).intValue();
        }
        this.vp_c.onEcgADCChange(i3, iArr, i, i2);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, ITcmDiagnosisDetectListener iTcmDiagnosisDetectListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, i, iTcmDiagnosisDetectListener);
        this.vp_c = iTcmDiagnosisDetectListener;
        this.vp_p = i;
        byte[] bArrVp_a = vp_a(this.vp_h, i);
        this.vp_m.clear();
        this.vp_n.clear();
        this.vp_o.clear();
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
