package com.veepoo.protocol.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDetectState;
import com.veepoo.protocol.model.datas.EcgDiagnosis;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.vp.cso.hrvreport.JNIChange;
import java.util.ArrayList;
import java.util.List;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ai.class */
public class vp_ai extends com.veepoo.protocol.vp_a {
    IECGDetectListener vp_c;
    TimeData vp_d;
    TimeData vp_e;
    long vp_f;
    long vp_g;
    JNIChange vp_j;
    com.veepoo.protocol.util.vp_m vp_q;
    com.veepoo.protocol.util.vp_p vp_r;
    com.veepoo.protocol.util.vp_o vp_s;
    com.veepoo.protocol.util.vp_n vp_t;
    com.veepoo.protocol.util.vp_q vp_u;
    EcgDetectResult vp_h = null;
    EcgDiagnosis vp_i = null;
    byte vp_k = 1;
    byte vp_l = 2;
    byte vp_m = 3;
    byte vp_n = 4;
    boolean vp_o = false;
    int vp_p = 250;
    List vp_v = new ArrayList();

    private int[] vp_b(int i) {
        if (i == 250) {
            return this.vp_q.vp_d();
        }
        if (i == 256) {
            return this.vp_t.vp_d();
        }
        if (i != 500) {
            return i != 512 ? this.vp_u.vp_c() : this.vp_r.vp_d();
        }
        Log.e("Test", "500");
        return this.vp_s.vp_d();
    }

    private EcgDetectInfo vp_e(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDetectInfo();
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return new EcgDetectInfo(Integer.valueOf(strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4], 16).intValue(), Integer.valueOf(strArrByte2HexToStrArr[7] + strArrByte2HexToStrArr[6], 16).intValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected byte[] vp_a(byte b, boolean z) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 1;
        bArr[2] = b;
        bArr[3] = z;
        return bArr;
    }

    public void vp_f(byte[] bArr) {
        new StringBuilder().append("**ecg---Handlers-->").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (this.vp_c == null) {
            return;
        }
        byte b = bArr[1];
        if (b == 1 && bArr[3] == 0) {
            this.vp_h = null;
            TimeData timeData = new TimeData();
            this.vp_d = timeData;
            timeData.setCurrentTime();
            this.vp_f = System.currentTimeMillis();
            EcgDetectInfo ecgDetectInfoVp_e = vp_e(bArr);
            this.vp_p = ecgDetectInfoVp_e.getDrawFrequency();
            new StringBuilder().append("ECGDetectOprate frequency ：").append(this.vp_p);
            if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType() == 9) {
                ecgDetectInfoVp_e.setFrequency(ecgDetectInfoVp_e.getDrawFrequency());
            }
            int i = this.vp_p;
            if (i == 250) {
                this.vp_q.vp_e();
            } else if (i == 512) {
                this.vp_r.vp_e();
            } else if (i == 256) {
                this.vp_t.vp_e();
            } else if (i == 500) {
                this.vp_s.vp_e();
            } else {
                this.vp_u.vp_d();
            }
            this.vp_c.onEcgDetectInfoChange(ecgDetectInfoVp_e);
            return;
        }
        if (b == 1 && bArr[3] == 1) {
            this.vp_c.onEcgDetectStateChange(vp_d(bArr));
            return;
        }
        if (b == 1) {
            byte b2 = bArr[3];
            if (b2 == 2) {
                this.vp_h = vp_c(bArr);
                return;
            }
            if (b2 == 4) {
                this.vp_g = System.currentTimeMillis();
                boolean zIsSupportEcgMultiDiagnoses = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses();
                new StringBuilder().append("**ecg---Handlers--> 测试成功 ecgType = ").append(VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType());
                if (zIsSupportEcgMultiDiagnoses) {
                    if (this.vp_i == null) {
                        this.vp_i = new EcgDiagnosis();
                    }
                    this.vp_i.setTimeBean(this.vp_d);
                    this.vp_i.setDuration((int) ((this.vp_g - this.vp_f) / 1000));
                    this.vp_i.setFrequency(this.vp_p);
                    this.vp_i.setFilterSignals(vp_b(this.vp_p));
                    this.vp_i.setPowers(vp_a(this.vp_p));
                    this.vp_i.setSuccess(true);
                    this.vp_c.onEcgDetectDiagnosisChange(this.vp_i);
                    new StringBuilder().append("**ecg---Handlers--> 测试成功 mEcgDiagnosis = ").append(this.vp_i);
                    return;
                }
                if (this.vp_h == null) {
                    this.vp_h = new EcgDetectResult();
                }
                TimeData timeData2 = new TimeData();
                this.vp_e = timeData2;
                timeData2.setCurrentTime();
                this.vp_h.setTimeBean(this.vp_d);
                this.vp_h.setDuration((int) ((this.vp_g - this.vp_f) / 1000));
                this.vp_h.setSuccess(true);
                this.vp_h.setFrequency(this.vp_p);
                this.vp_h.setOriginSign(vp_b(this.vp_p));
                this.vp_h.setFilterSignals(vp_b(this.vp_p));
                this.vp_h.setPowers(vp_a(this.vp_p));
                this.vp_h.setType(EECGResultType.DATA_FORM_APP);
                new StringBuilder().append("**ecg---Handlers--> 测试成功 mEcgDetectResult = ").append(this.vp_h.toString());
                this.vp_c.onEcgDetectResultChange(this.vp_h);
                return;
            }
            if (b2 != 3) {
                if (b2 == 5) {
                    byte b3 = bArr[4];
                    byte b4 = bArr[5];
                    new StringBuilder().append("**ecg---Handlers-->  ECG多诊断包[").append((int) b3).append("/").append((int) b4).append("]");
                    if (b3 == 1) {
                        this.vp_v.clear();
                    }
                    this.vp_v.add(bArr);
                    if (b3 == b4) {
                        this.vp_i = vp_b(vp_a());
                        new StringBuilder().append("**ecg---Handlers-->  ECG多诊断结果:").append(this.vp_i.toString());
                        return;
                    }
                    return;
                }
                return;
            }
            this.vp_g = System.currentTimeMillis();
            if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses()) {
                if (this.vp_i == null) {
                    this.vp_i = new EcgDiagnosis();
                }
                this.vp_i.setTimeBean(this.vp_d);
                this.vp_i.setDuration((int) ((this.vp_g - this.vp_f) / 1000));
                this.vp_i.setSuccess(false);
                this.vp_i.setFrequency(this.vp_p);
                this.vp_i.setFilterSignals(vp_b(this.vp_p));
                this.vp_i.setPowers(vp_a(this.vp_p));
                this.vp_c.onEcgDetectDiagnosisChange(this.vp_i);
                return;
            }
            if (this.vp_h == null) {
                this.vp_h = new EcgDetectResult();
            }
            TimeData timeData3 = new TimeData();
            this.vp_e = timeData3;
            timeData3.setCurrentTime();
            this.vp_h.setSuccess(false);
            this.vp_h.setTimeBean(this.vp_d);
            this.vp_h.setDuration((int) ((this.vp_g - this.vp_f) / 1000));
            this.vp_h.setFrequency(this.vp_p);
            this.vp_h.setOriginSign(vp_b(this.vp_p));
            this.vp_h.setFilterSignals(vp_b(this.vp_p));
            this.vp_h.setPowers(vp_a(this.vp_p));
            this.vp_c.onEcgDetectResultChange(this.vp_h);
        }
    }

    public EcgDetectState vp_d(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDetectState();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[15] + strArrByte2HexToStrArr[14], 16).intValue();
        int i = iArrByte2HexToIntArr[16];
        EcgDetectState ecgDetectState = new EcgDetectState(iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], vp_a(bArr[4]), iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8], iArrByte2HexToIntArr[9], iArrByte2HexToIntArr[10], iArrByte2HexToIntArr[11], iArrByte2HexToIntArr[12], iArrByte2HexToIntArr[13], iIntValue, iArrByte2HexToIntArr[19]);
        ecgDetectState.setPwv(i);
        return ecgDetectState;
    }

    public EcgDetectResult vp_c(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDetectResult();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        EcgDetectResult ecgDetectResult = new EcgDetectResult();
        ecgDetectResult.setType(EECGResultType.DATA_FORM_APP);
        ecgDetectResult.setLeadSign(iArrByte2HexToIntArr[4]);
        int[] iArr = {iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8], iArrByte2HexToIntArr[9], iArrByte2HexToIntArr[10], iArrByte2HexToIntArr[11], iArrByte2HexToIntArr[12]};
        ecgDetectResult.setResult8(iArr);
        if (this.vp_o) {
            ecgDetectResult.setDiseaseResult(new vp_ac().vp_a(iArr));
        }
        ecgDetectResult.setAveHeart(iArrByte2HexToIntArr[13]);
        ecgDetectResult.setAveResRate(iArrByte2HexToIntArr[14]);
        ecgDetectResult.setAveHrv(iArrByte2HexToIntArr[15]);
        ecgDetectResult.setProgress(iArrByte2HexToIntArr[19]);
        ecgDetectResult.setAveQT(Integer.valueOf(strArrByte2HexToStrArr[17] + strArrByte2HexToStrArr[16], 16).intValue());
        return ecgDetectResult;
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

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, z, iECGDetectListener, z2);
        this.vp_c = iECGDetectListener;
        this.vp_o = z2;
        this.vp_j = new JNIChange();
        VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
        this.vp_q = new com.veepoo.protocol.util.vp_m(this.vp_j, this.vp_c);
        this.vp_r = new com.veepoo.protocol.util.vp_p(this.vp_c);
        this.vp_s = new com.veepoo.protocol.util.vp_o(this.vp_c);
        this.vp_t = new com.veepoo.protocol.util.vp_n(this.vp_c);
        this.vp_u = new com.veepoo.protocol.util.vp_q(this.vp_c);
        this.vp_r.vp_a();
        this.vp_s.vp_a();
        this.vp_t.vp_a();
        this.vp_q.vp_a();
        this.vp_u.vp_a();
        super.send(vp_a(this.vp_k, z), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
        super.vp_d(bluetoothClient, str, bleWriteResponse, z, iECGDetectListener, z2);
        this.vp_c = iECGDetectListener;
        this.vp_o = z2;
        super.send(vp_a(this.vp_l, z), bluetoothClient, str, bleWriteResponse);
        this.vp_q.vp_a();
        this.vp_t.vp_a();
        this.vp_r.vp_a();
        this.vp_s.vp_a();
        this.vp_u.vp_a();
    }

    public EcgDiagnosis vp_b(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDiagnosis();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        EcgDiagnosis ecgDiagnosis = new EcgDiagnosis();
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
        ecgDiagnosis.setLeadOffType(i);
        ecgDiagnosis.setHeartRate(i2);
        ecgDiagnosis.setRespRate(i3);
        ecgDiagnosis.setHrv(i4);
        ecgDiagnosis.setQtTime(iIntValue);
        ecgDiagnosis.setPwvMeanVal(iIntValue4);
        ecgDiagnosis.setDiagnosis8(iArr);
        if (this.vp_o) {
            ecgDiagnosis.setDiseaseResult(new vp_ac().vp_a(iArr));
            ecgDiagnosis.setDiseaseRisk(i5);
            ecgDiagnosis.setPressureIndex(i6);
            ecgDiagnosis.setFatigueIndex(i7);
            ecgDiagnosis.setMyocarditisRisk(i8);
            ecgDiagnosis.setChdRisk(i9);
            ecgDiagnosis.setAngioscleroticRisk(i10);
            ecgDiagnosis.setRisk32(iArr2);
            ecgDiagnosis.setQrsTime(iIntValue2);
            ecgDiagnosis.setQrsAmp(iIntValue3);
            ecgDiagnosis.setStMeanAmp(iIntValue5);
            ecgDiagnosis.setDiseaseSdnn(iIntValue6);
            ecgDiagnosis.setDiseaseRmssd(iIntValue7);
        }
        return ecgDiagnosis;
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[this.vp_v.size() * 14];
        for (int i = 0; i < this.vp_v.size(); i++) {
            System.arraycopy((byte[]) this.vp_v.get(i), 6, bArr, i * 14, 14);
        }
        return bArr;
    }

    private int[] vp_a(int i) {
        if (i == 250) {
            return this.vp_q.vp_c();
        }
        if (i == 256) {
            return this.vp_t.vp_c();
        }
        if (i != 500) {
            return i != 512 ? this.vp_u.vp_b() : this.vp_r.vp_c();
        }
        return this.vp_s.vp_c();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return;
        }
        int i = this.vp_p;
        if (i == 250) {
            this.vp_q.vp_a(bArr);
            return;
        }
        if (i == 256) {
            this.vp_t.vp_b(bArr);
            return;
        }
        if (i == 500) {
            this.vp_s.vp_b(bArr);
        } else if (i != 512) {
            this.vp_u.vp_b(bArr);
        } else {
            this.vp_r.vp_b(bArr);
        }
    }
}
