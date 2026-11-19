package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDetectState;
import com.veepoo.protocol.model.datas.EcgDiagnosis;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EAdcType;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.vp.cso.hrvreport.JNIChange;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bx.class */
public class vp_bx extends com.veepoo.protocol.vp_a {
    IECGDetectListener vp_c;
    TimeData vp_d;
    TimeData vp_e;
    long vp_f;
    long vp_g;
    JNIChange vp_j;
    EcgDetectResult vp_h = null;
    EcgDiagnosis vp_i = null;
    byte vp_k = 1;
    byte vp_l = 2;
    byte vp_m = 3;
    byte vp_n = 4;
    boolean vp_o = false;
    List vp_p = new ArrayList();
    Timer vp_q = new Timer();
    LinkedList vp_r = new LinkedList();
    LinkedList vp_s = new LinkedList();
    LinkedList vp_t = new LinkedList();

    private byte[] vp_b() {
        byte[] bArr = new byte[this.vp_p.size() * 14];
        for (int i = 0; i < this.vp_p.size(); i++) {
            System.arraycopy((byte[]) this.vp_p.get(i), 6, bArr, i * 14, 14);
        }
        return bArr;
    }

    private EcgDetectInfo vp_e(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgDetectInfo();
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return new EcgDetectInfo(Integer.valueOf(strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4], 16).intValue(), Integer.valueOf(strArrByte2HexToStrArr[7] + strArrByte2HexToStrArr[6], 16).intValue());
    }

    private void vp_a() {
        if (!this.vp_r.isEmpty()) {
            this.vp_r.clear();
        }
        if (!this.vp_s.isEmpty()) {
            this.vp_s.clear();
        }
        if (!this.vp_t.isEmpty()) {
            this.vp_t.clear();
        }
        vp_c();
    }

    public void vp_f(byte[] bArr) {
        IECGDetectListener iECGDetectListener;
        IECGDetectListener iECGDetectListener2;
        byte b = bArr[1];
        if (b == 1 && bArr[3] == 0) {
            this.vp_h = null;
            TimeData timeData = new TimeData();
            this.vp_d = timeData;
            timeData.setCurrentTime();
            this.vp_f = System.currentTimeMillis();
            EcgDetectInfo ecgDetectInfoVp_e = vp_e(bArr);
            IECGDetectListener iECGDetectListener3 = this.vp_c;
            if (iECGDetectListener3 != null) {
                iECGDetectListener3.onEcgDetectInfoChange(ecgDetectInfoVp_e);
                return;
            }
            return;
        }
        if (b == 1 && bArr[3] == 1) {
            EcgDetectState ecgDetectStateVp_d = vp_d(bArr);
            IECGDetectListener iECGDetectListener4 = this.vp_c;
            if (iECGDetectListener4 != null) {
                iECGDetectListener4.onEcgDetectStateChange(ecgDetectStateVp_d);
                return;
            }
            return;
        }
        if (b == 1) {
            byte b2 = bArr[3];
            if (b2 == 2) {
                EcgDetectResult ecgDetectResultVp_c = vp_c(bArr);
                this.vp_h = ecgDetectResultVp_c;
                IECGDetectListener iECGDetectListener5 = this.vp_c;
                if (iECGDetectListener5 != null) {
                    iECGDetectListener5.onEcgDetectResultChange(ecgDetectResultVp_c);
                    return;
                }
                return;
            }
            if (b2 == 4) {
                this.vp_g = System.currentTimeMillis();
                if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses()) {
                    if (this.vp_i == null) {
                        this.vp_i = new EcgDiagnosis();
                    }
                    this.vp_i.setTimeBean(this.vp_d);
                    this.vp_i.setDuration((int) ((this.vp_g - this.vp_f) / 1000));
                    this.vp_i.setSuccess(true);
                    IECGDetectListener iECGDetectListener6 = this.vp_c;
                    iECGDetectListener2 = iECGDetectListener6;
                    if (iECGDetectListener6 == null) {
                        return;
                    }
                    iECGDetectListener2.onEcgDetectDiagnosisChange(this.vp_i);
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
                IECGDetectListener iECGDetectListener7 = this.vp_c;
                iECGDetectListener = iECGDetectListener7;
                if (iECGDetectListener7 == null) {
                    return;
                }
                iECGDetectListener.onEcgDetectResultChange(this.vp_h);
            }
            if (b2 != 3) {
                if (b2 == 5) {
                    byte b3 = bArr[4];
                    byte b4 = bArr[5];
                    if (b3 == 1) {
                        this.vp_p.clear();
                    }
                    this.vp_p.add(bArr);
                    if (b3 == b4) {
                        this.vp_i = vp_b(vp_b());
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
                IECGDetectListener iECGDetectListener8 = this.vp_c;
                iECGDetectListener2 = iECGDetectListener8;
                if (iECGDetectListener8 == null) {
                    return;
                }
                iECGDetectListener2.onEcgDetectDiagnosisChange(this.vp_i);
                return;
            }
            if (this.vp_h == null) {
                this.vp_h = new EcgDetectResult();
            }
            TimeData timeData3 = new TimeData();
            this.vp_e = timeData3;
            timeData3.setCurrentTime();
            this.vp_h.setSuccess(false);
            IECGDetectListener iECGDetectListener9 = this.vp_c;
            iECGDetectListener = iECGDetectListener9;
            if (iECGDetectListener9 == null) {
                return;
            }
            iECGDetectListener.onEcgDetectResultChange(this.vp_h);
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

    protected byte[] vp_a(byte b, EAdcType eAdcType) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 1;
        bArr[2] = b;
        bArr[3] = eAdcType.getTag();
        return bArr;
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
        ecgDiagnosis.setDiagnosis8(iArr);
        ecgDiagnosis.setHeartRate(i2);
        ecgDiagnosis.setRespRate(i3);
        ecgDiagnosis.setHrv(i4);
        ecgDiagnosis.setQtTime(iIntValue);
        ecgDiagnosis.setPwvMeanVal(iIntValue4);
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

    public void vp_c() {
        Timer timer = this.vp_q;
        if (timer != null) {
            timer.cancel();
            this.vp_q = null;
        }
        this.vp_q = new Timer();
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
                        return EDeviceStatus.FREE;
                }
            }
            return EDeviceStatus.CHARGING;
        }
        return EDeviceStatus.CHARG_LOW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener, boolean z) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, eAdcType, iECGDetectListener, z);
        this.vp_c = iECGDetectListener;
        this.vp_o = z;
        super.send(vp_a(this.vp_n, eAdcType), bluetoothClient, str, bleWriteResponse);
        vp_a();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return;
        }
        int eCGType = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getECGType();
        VpBleByteUtil.byte2HexToIntArr(bArr);
        int[] iArrVp_a = com.veepoo.protocol.util.vp_l.vp_a.vp_a(bArr, eCGType);
        IECGDetectListener iECGDetectListener = this.vp_c;
        if (iECGDetectListener == null || iArrVp_a == null) {
            return;
        }
        iECGDetectListener.onEcgADCChange(iArrVp_a, null);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener, boolean z) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, eAdcType, iECGDetectListener, z);
        this.vp_c = iECGDetectListener;
        this.vp_o = z;
        this.vp_j = new JNIChange();
        vp_a();
        super.send(vp_a(this.vp_m, eAdcType), bluetoothClient, str, bleWriteResponse);
    }
}
