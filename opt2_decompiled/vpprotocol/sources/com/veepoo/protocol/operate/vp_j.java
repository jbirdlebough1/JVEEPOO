package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener;
import com.veepoo.protocol.model.datas.MealInfo;
import com.veepoo.protocol.model.enums.EBloodGlucoseDetectModel;
import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import com.veepoo.protocol.model.enums.EBloodGlucoseStatus;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_j.class */
public class vp_j extends com.veepoo.protocol.vp_a {
    private IBloodGlucoseChangeListener vp_c;
    List vp_d = new ArrayList();

    private EBloodGlucoseRiskLevel vp_b(int i) {
        int i2 = (i & 57344) >> 13;
        if (i2 == 2) {
            return EBloodGlucoseRiskLevel.MIDDLE;
        }
        if (i2 == 3) {
            return EBloodGlucoseRiskLevel.HIGH;
        }
        return i2 == 1 ? EBloodGlucoseRiskLevel.LOW : EBloodGlucoseRiskLevel.NONE;
    }

    public static EBloodGlucoseStatus vp_a(int i) {
        for (int i2 = 0; i2 < EBloodGlucoseStatus.values().length; i2++) {
            if (EBloodGlucoseStatus.values()[i2].status == i) {
                return EBloodGlucoseStatus.values()[i2];
            }
        }
        return EBloodGlucoseStatus.NONSUPPORT;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_az(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_az(bluetoothClient, str, bleWriteResponse);
        send(com.veepoo.protocol.profile.vp_a.vp_bs, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bk(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bk(bluetoothClient, str, bleWriteResponse);
        send(com.veepoo.protocol.profile.vp_a.vp_bu, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_q(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_q(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_br, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_am(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_am(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bw, bluetoothClient, str, bleWriteResponse);
    }

    public void vp_d(byte[] bArr) {
        IBloodGlucoseChangeListener iBloodGlucoseChangeListener;
        new StringBuilder().append("---- Blood Glucose多Calibration设置/读取  data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        byte b = bArr[2];
        if (b == 1) {
            byte b2 = bArr[3];
            if (b2 == 0) {
                IBloodGlucoseChangeListener iBloodGlucoseChangeListener2 = this.vp_c;
                if (iBloodGlucoseChangeListener2 != null) {
                    iBloodGlucoseChangeListener2.onBGMultipleAdjustingSettingFailed();
                    return;
                }
                return;
            }
            if (b2 == 1) {
                if (bArr[4] == 1) {
                    this.vp_d.clear();
                }
                this.vp_d.add(bArr);
                if (bArr[4] != bArr[5] || (iBloodGlucoseChangeListener = this.vp_c) == null) {
                    return;
                }
                iBloodGlucoseChangeListener.onBGMultipleAdjustingSettingSuccess();
                return;
            }
            return;
        }
        if (b == 2) {
            byte b3 = bArr[3];
            if (b3 == 0) {
                IBloodGlucoseChangeListener iBloodGlucoseChangeListener3 = this.vp_c;
                if (iBloodGlucoseChangeListener3 != null) {
                    iBloodGlucoseChangeListener3.onBGMultipleAdjustingReadFailed();
                    return;
                }
                return;
            }
            if (b3 == 1) {
                if (bArr[4] == 1) {
                    this.vp_d.clear();
                }
                this.vp_d.add(bArr);
                if (bArr[4] == bArr[5]) {
                    byte[] bArr2 = new byte[31];
                    System.arraycopy(this.vp_d.get(0), 0, bArr2, 0, 20);
                    System.arraycopy(this.vp_d.get(1), 6, bArr2, 20, 11);
                    if (this.vp_c != null) {
                        byte[] bArr3 = new byte[8];
                        byte[] bArr4 = new byte[8];
                        byte[] bArr5 = new byte[8];
                        boolean z = bArr2[6] == 1;
                        System.arraycopy(bArr2, 7, bArr3, 0, 8);
                        System.arraycopy(bArr2, 15, bArr4, 0, 8);
                        System.arraycopy(bArr2, 23, bArr5, 0, 8);
                        MealInfo mealInfoVp_a = vp_a(1, bArr3);
                        MealInfo mealInfoVp_a2 = vp_a(2, bArr4);
                        MealInfo mealInfoVp_a3 = vp_a(3, bArr5);
                        mealInfoVp_a.checkSelf();
                        mealInfoVp_a2.checkSelf();
                        mealInfoVp_a3.checkSelf();
                        IBloodGlucoseChangeListener iBloodGlucoseChangeListener4 = this.vp_c;
                        if (iBloodGlucoseChangeListener4 != null) {
                            iBloodGlucoseChangeListener4.onBGMultipleAdjustingReadSuccess(z, mealInfoVp_a, mealInfoVp_a2, mealInfoVp_a3);
                        }
                    }
                }
            }
        }
    }

    public void vp_c(byte[] bArr) {
        if (this.vp_c == null) {
            return;
        }
        int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        int i = iArrVp_e[2];
        if (i == 0) {
            this.vp_c.onDetectError(i, EBloodGlucoseStatus.NONSUPPORT);
            return;
        }
        EBloodGlucoseStatus eBloodGlucoseStatusVp_a = vp_a(iArrVp_e[3]);
        new StringBuilder().append("Blood Glucose监测监 ------ BloodGlucoseDetectStatus = ").append(eBloodGlucoseStatusVp_a);
        if (eBloodGlucoseStatusVp_a != EBloodGlucoseStatus.ENABLE && eBloodGlucoseStatusVp_a != EBloodGlucoseStatus.DETECTING) {
            this.vp_c.onDetectError(i, eBloodGlucoseStatusVp_a);
            return;
        }
        if (i == 1) {
            int i2 = iArrVp_e[4];
            int iIntValue = Integer.valueOf(strArrVp_f[6] + strArrVp_f[5], 16).intValue();
            float f = iIntValue & 8191;
            new StringBuilder().append("Blood Glucose监测监 ------ progress = ").append(i2).append(" bloodGlucose = ").append(f);
            this.vp_c.onBloodGlucoseDetect(i2, f / 100.0f, vp_b(iIntValue));
        }
        if (i == 2) {
            this.vp_c.onBloodGlucoseStopDetect();
        }
    }

    private byte[] vp_b(int i, byte[] bArr) {
        if (bArr == null || bArr.length != 25) {
            return null;
        }
        byte[] bArr2 = new byte[20];
        bArr2[0] = -119;
        bArr2[1] = 3;
        bArr2[2] = 1;
        bArr2[3] = (byte) i;
        bArr2[4] = 2;
        if (i == 1) {
            System.arraycopy(bArr, 0, bArr2, 5, 15);
        } else if (i == 2) {
            System.arraycopy(bArr, 15, bArr2, 5, 10);
        }
        return bArr2;
    }

    private MealInfo vp_a(int i, byte[] bArr) {
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        MealInfo mealInfo = new MealInfo(i);
        byte b = bArr[0];
        byte b2 = bArr[1];
        float fIntValue = Integer.valueOf(strArrVp_f[3] + strArrVp_f[2], 16).intValue() / 100.0f;
        mealInfo.beforeMealTime = (b * 60) + b2;
        mealInfo.setBgBeforeMeal(fIntValue);
        byte b3 = bArr[4];
        byte b4 = bArr[5];
        float fIntValue2 = Integer.valueOf(strArrVp_f[7] + strArrVp_f[6], 16).intValue() / 100.0f;
        mealInfo.afterMealTime = (b3 * 60) + b4;
        mealInfo.setBgAfterMeal(fIntValue2);
        return mealInfo;
    }

    private byte[] vp_a(MealInfo mealInfo) {
        int bgBeforeMeal = (int) (mealInfo.getBgBeforeMeal() * 100.0f);
        int i = mealInfo.beforeMealTime;
        byte[] bArrVp_c = com.veepoo.protocol.util.vp_g.vp_c(bgBeforeMeal);
        int bgAfterMeal = (int) (mealInfo.getBgAfterMeal() * 100.0f);
        int i2 = mealInfo.afterMealTime;
        byte[] bArrVp_c2 = com.veepoo.protocol.util.vp_g.vp_c(bgAfterMeal);
        return new byte[]{(byte) (i / 60), (byte) (i % 60), bArrVp_c[0], bArrVp_c[1], (byte) (i2 / 60), (byte) (i2 % 60), bArrVp_c2[0], bArrVp_c2[1]};
    }

    public void vp_b(byte[] bArr) {
        new StringBuilder().append("---- Blood GlucoseCalibration设置/读取  data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 2) {
            byte b = bArr[2];
            if (b == 1 || b == 2) {
                if (bArr[3] != 1) {
                    new StringBuilder().append("---- Blood GlucoseCalibration 【读取】失败  data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
                    IBloodGlucoseChangeListener iBloodGlucoseChangeListener = this.vp_c;
                    if (iBloodGlucoseChangeListener != null) {
                        iBloodGlucoseChangeListener.onBloodGlucoseAdjustingSettingFailed();
                        if (bArr[2] == 1) {
                            this.vp_c.onBloodGlucoseAdjustingSettingFailed();
                            return;
                        } else {
                            this.vp_c.onBloodGlucoseAdjustingReadFailed();
                            return;
                        }
                    }
                    return;
                }
                int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]);
                new StringBuilder().append("---- Blood GlucoseCalibration设置 成功 value = ").append(iVp_b);
                boolean z = bArr[6] == 1;
                VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveBloodGlucoseAdjustingSwitch(z);
                new StringBuilder().append("---- Blood GlucoseCalibration 【读取】成功  bgValue = ").append(iVp_b).append(" isOpen = ").append(z);
                IBloodGlucoseChangeListener iBloodGlucoseChangeListener2 = this.vp_c;
                if (iBloodGlucoseChangeListener2 != null) {
                    if (bArr[2] == 1) {
                        iBloodGlucoseChangeListener2.onBloodGlucoseAdjustingSettingSuccess(z, iVp_b / 100.0f);
                    } else {
                        iBloodGlucoseChangeListener2.onBloodGlucoseAdjustingReadSuccess(z, iVp_b / 100.0f);
                    }
                }
            }
        }
    }

    private List vp_a(boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3) {
        byte[] bArr = new byte[25];
        byte[] bArrVp_a = vp_a(mealInfo);
        byte[] bArrVp_a2 = vp_a(mealInfo2);
        byte[] bArrVp_a3 = vp_a(mealInfo3);
        bArr[0] = z ? (byte) 1 : (byte) 0;
        for (int i = 0; i < 24; i++) {
            if (i < 8) {
                bArr[i + 1] = bArrVp_a[i];
            } else if (i < 16) {
                int i2 = i;
                bArr[i2 + 1] = bArrVp_a2[i2 - 8];
            } else {
                int i3 = i;
                bArr[i3 + 1] = bArrVp_a3[i3 - 16];
            }
        }
        byte[] bArrVp_b = vp_b(1, bArr);
        byte[] bArrVp_b2 = vp_b(2, bArr);
        ArrayList arrayList = new ArrayList();
        arrayList.add(bArrVp_b);
        arrayList.add(bArrVp_b2);
        return arrayList;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, eBloodGlucoseDetectModel);
        if (eBloodGlucoseDetectModel == EBloodGlucoseDetectModel.DETECT_MODEL_PUBLIC) {
            send(com.veepoo.protocol.profile.vp_a.vp_bu, bluetoothClient, str, bleWriteResponse);
        } else {
            send(com.veepoo.protocol.profile.vp_a.vp_bv, bluetoothClient, str, bleWriteResponse);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] vp_a(float f, boolean z) {
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 2;
        bArr[2] = 1;
        short s = (short) (f * 100.0f);
        bArr[3] = VpBleByteUtil.loUint16(s);
        bArr[4] = VpBleByteUtil.hiUint16(s);
        bArr[5] = z;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        this.vp_c = (IBloodGlucoseChangeListener) iListener;
        byte b = bArr[1];
        if (b == 1) {
            vp_c(bArr);
        } else if (b == 2) {
            vp_b(bArr);
        } else if (b == 3) {
            vp_d(bArr);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, mealInfo, mealInfo2, mealInfo3);
        List listVp_a = vp_a(z, mealInfo, mealInfo2, mealInfo3);
        for (int i = 0; i < listVp_a.size(); i++) {
            super.send((byte[]) listVp_a.get(i), bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, float f, boolean z) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, f, z);
        super.send(vp_a(f, z), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, eBloodGlucoseDetectModel);
        if (eBloodGlucoseDetectModel == EBloodGlucoseDetectModel.DETECT_MODEL_PUBLIC) {
            send(com.veepoo.protocol.profile.vp_a.vp_bs, bluetoothClient, str, bleWriteResponse);
        } else {
            send(com.veepoo.protocol.profile.vp_a.vp_bt, bluetoothClient, str, bleWriteResponse);
        }
    }
}
