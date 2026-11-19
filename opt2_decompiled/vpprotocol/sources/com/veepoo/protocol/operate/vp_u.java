package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ICustomSettingDataListener;
import com.veepoo.protocol.listener.data.IDeviceFunctionStatusChangeListener;
import com.veepoo.protocol.model.enums.EBloodFatUnit;
import com.veepoo.protocol.model.enums.EBloodGlucoseUnit;
import com.veepoo.protocol.model.enums.ECustomStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EGsrUnit;
import com.veepoo.protocol.model.enums.ETemperatureUnit;
import com.veepoo.protocol.model.enums.EUricAcidUnit;
import com.veepoo.protocol.model.settings.CustomSetting;
import com.veepoo.protocol.model.settings.CustomSettingData;
import com.veepoo.protocol.shareprence.FunctionSPUtil;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_u.class */
public class vp_u extends com.veepoo.protocol.vp_a {
    byte vp_c = 1;
    byte vp_d = 2;
    byte vp_e = 0;
    byte vp_f = 1;
    byte vp_g = 2;
    byte vp_h = 1;
    byte vp_i = 2;
    byte vp_j = 1;
    byte vp_k = 2;
    ICustomSettingDataListener vp_l;
    CustomSettingData vp_m;

    private void vp_d(byte[] bArr) {
        if (bArr.length < 20) {
            this.vp_m.setStatus(ECustomStatus.UNKNOW);
            return;
        }
        byte b = bArr[19];
        if (b == 0) {
            vp_b(bArr);
        } else if (b == 1) {
            vp_c(bArr);
        }
    }

    private void vp_c(byte[] bArr) {
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        byte b5 = bArr[5];
        byte b6 = bArr[6];
        byte b7 = bArr[7];
        byte b8 = bArr[8];
        byte b9 = bArr[9];
        byte b10 = bArr[10];
        byte b11 = bArr[11];
        byte b12 = bArr[12];
        byte b13 = bArr[13];
        byte b14 = bArr[16];
        byte b15 = bArr[14];
        if (b == this.vp_d) {
            this.vp_m.setStatus(ECustomStatus.READ_SUCCESS);
        } else if (b == this.vp_c) {
            this.vp_m.setStatus(ECustomStatus.SETTING_SUCCESS);
        }
        this.vp_m.setLongClickLockScreen(vp_c(b2));
        this.vp_m.setMessageScreenLight(vp_c(b3));
        this.vp_m.setAutoTemperatureDetect(vp_c(b4));
        this.vp_m.setTemperatureUnit(vp_e(b5));
        this.vp_m.setEcgAlwaysOpen(vp_c(b6));
        this.vp_m.setBloodGlucoseDetection(vp_c(b7));
        this.vp_m.setMETDetect(vp_c(b8));
        this.vp_m.setStressDetect(vp_c(b9));
        this.vp_m.setIndex(1);
        this.vp_m.setBloodGlucoseUnit(vp_b(b10));
        this.vp_m.setBloodComponentDetect(vp_c(b11));
        this.vp_m.setUricAcidUnit(vp_f(b12));
        this.vp_m.setBloodFatUnit(vp_a(b13));
        this.vp_m.setFallDetection(vp_c(b15));
        this.vp_m.setGsrUnit(vp_d(b14));
    }

    private void vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        byte b5 = bArr[5];
        byte b6 = bArr[6];
        byte b7 = bArr[7];
        byte b8 = bArr[8];
        byte b9 = bArr[9];
        byte b10 = bArr[10];
        byte b11 = bArr[11];
        byte b12 = bArr[12];
        byte b13 = bArr[13];
        byte b14 = bArr[14];
        byte b15 = bArr[15];
        byte b16 = bArr[16];
        byte b17 = bArr[17];
        byte b18 = bArr[18];
        if (b == this.vp_d) {
            this.vp_m.setStatus(ECustomStatus.READ_SUCCESS);
        } else if (b == this.vp_c) {
            this.vp_m.setStatus(ECustomStatus.SETTING_SUCCESS);
        }
        if (b2 == this.vp_e) {
            this.vp_m.setHaveMetricSystem(false);
            this.vp_m.setMetricSystemValue(false);
            this.vp_m.setMetricSystem(EFunctionStatus.UNSUPPORT);
        } else if (b2 == this.vp_f) {
            this.vp_m.setHaveMetricSystem(true);
            this.vp_m.setMetricSystemValue(true);
            this.vp_m.setMetricSystem(EFunctionStatus.SUPPORT_OPEN);
        } else if (b2 == this.vp_g) {
            this.vp_m.setHaveMetricSystem(true);
            this.vp_m.setMetricSystemValue(false);
            this.vp_m.setMetricSystem(EFunctionStatus.SUPPORT_CLOSE);
        }
        if (b3 == this.vp_i) {
            this.vp_m.setIs24Hour(false);
        } else if (b3 == this.vp_h) {
            this.vp_m.setIs24Hour(true);
        }
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        if (b4 == this.vp_j) {
            this.vp_m.setOpenAutoHeartDetect(true);
            eFunctionStatus = EFunctionStatus.SUPPORT_OPEN;
        } else if (b4 == this.vp_k) {
            this.vp_m.setOpenAutoHeartDetect(false);
            eFunctionStatus = EFunctionStatus.SUPPORT_CLOSE;
        }
        this.vp_m.setAutoHeartDetect(eFunctionStatus);
        if (FunctionSPUtil.INSTANCE.getHeartRateFunctionStatus() != eFunctionStatus) {
            VPOperateManager.getInstance().postFunctionStatusChanged(IDeviceFunctionStatusChangeListener.DeviceFunction.HeartRateAutoDetect, eFunctionStatus);
        }
        if (b5 == this.vp_j) {
            this.vp_m.setOpenAutoBpDetect(true);
            this.vp_m.setAutoBpDetect(EFunctionStatus.SUPPORT_OPEN);
        } else if (b5 == this.vp_k) {
            this.vp_m.setOpenAutoBpDetect(false);
            this.vp_m.setAutoBpDetect(EFunctionStatus.SUPPORT_CLOSE);
        }
        this.vp_m.setSportOverRemain(vp_c(b6));
        this.vp_m.setVoiceBpHeart(vp_c(b7));
        this.vp_m.setFindPhoneUi(vp_c(b8));
        this.vp_m.setSecondsWatch(vp_c(b9));
        this.vp_m.setLowSpo2hRemain(vp_c(b10));
        this.vp_m.setSkinLevel(iArrByte2HexToIntArr[11]);
        this.vp_m.setSkin(vp_c(b11));
        this.vp_m.setAutoHrv(vp_c(b12));
        this.vp_m.setAutoIncall(vp_c(b13));
        this.vp_m.setDisconnectRemind(vp_c(b14));
        this.vp_m.setSOS(vp_c(b15));
        this.vp_m.setPpg(vp_c(b16));
        this.vp_m.setIndex(0);
        this.vp_m.setMusicControl(vp_c(b18));
    }

    private ETemperatureUnit vp_e(byte b) {
        ETemperatureUnit eTemperatureUnit = ETemperatureUnit.NONE;
        if (b == eTemperatureUnit.getValue()) {
            return eTemperatureUnit;
        }
        ETemperatureUnit eTemperatureUnit2 = ETemperatureUnit.CELSIUS;
        if (b == eTemperatureUnit2.getValue()) {
            return eTemperatureUnit2;
        }
        ETemperatureUnit eTemperatureUnit3 = ETemperatureUnit.FAHRENHEIT;
        return b == eTemperatureUnit3.getValue() ? eTemperatureUnit3 : eTemperatureUnit;
    }

    private EUricAcidUnit vp_f(byte b) {
        EUricAcidUnit eUricAcidUnit = EUricAcidUnit.NONE;
        if (b == eUricAcidUnit.getValue()) {
            return eUricAcidUnit;
        }
        EUricAcidUnit eUricAcidUnit2 = EUricAcidUnit.umol_L;
        if (b == eUricAcidUnit2.getValue()) {
            return eUricAcidUnit2;
        }
        EUricAcidUnit eUricAcidUnit3 = EUricAcidUnit.mg_dl;
        return b == eUricAcidUnit3.getValue() ? eUricAcidUnit3 : eUricAcidUnit;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_t(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_t(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bd, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CustomSetting customSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, customSetting);
        List listVp_c = vp_c(customSetting);
        for (int i = 0; i < listVp_c.size(); i++) {
            super.send((byte[]) listVp_c.get(i), bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr[19] == 0) {
            this.vp_m = null;
        }
        if (this.vp_m == null) {
            this.vp_m = new CustomSettingData();
        }
        vp_d(bArr);
        ICustomSettingDataListener iCustomSettingDataListener = this.vp_l;
        if (iCustomSettingDataListener != null) {
            iCustomSettingDataListener.OnSettingDataChange(this.vp_m);
        }
        VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceFunctionB8(this.vp_m);
    }

    private byte[] vp_b(CustomSetting customSetting) {
        return new byte[]{-72, this.vp_c, vp_a(customSetting.getIsOpenLongClickLockScreen()), vp_a(customSetting.getIsOpenMessageScreenLight()), vp_a(customSetting.getIsOpenAutoTemperatureDetect()), (byte) customSetting.getTemperatureUnit().getValue(), vp_a(customSetting.getEcgAlwaysOpen()), vp_a(customSetting.getIsOpenBloodGlucoseDetect()), vp_a(customSetting.getMETDetect()), vp_a(customSetting.getStressDetect()), (byte) customSetting.getBloodGlucoseUnit().getValue(), vp_a(customSetting.getIsOpenBloodComponentDetect()), (byte) customSetting.getUricAcidUnit().getValue(), (byte) customSetting.getBloodFatUnit().getValue(), vp_a(customSetting.getFallDetection()), 0, (byte) customSetting.getGsrUnit().getValue(), 0, 0, 1};
    }

    private byte vp_a(EFunctionStatus eFunctionStatus) {
        if (eFunctionStatus == EFunctionStatus.SUPPORT_OPEN || eFunctionStatus == EFunctionStatus.SUPPORT) {
            return this.vp_j;
        }
        if (eFunctionStatus == EFunctionStatus.SUPPORT_CLOSE) {
            return this.vp_k;
        }
        return eFunctionStatus == EFunctionStatus.UNSUPPORT ? this.vp_e : this.vp_j;
    }

    private EFunctionStatus vp_c(byte b) {
        if (b == this.vp_e) {
            return EFunctionStatus.UNSUPPORT;
        }
        if (b == this.vp_j) {
            return EFunctionStatus.SUPPORT_OPEN;
        }
        return b == this.vp_k ? EFunctionStatus.SUPPORT_CLOSE : EFunctionStatus.UNKONW;
    }

    private EGsrUnit vp_d(byte b) {
        EGsrUnit eGsrUnit = EGsrUnit.NONE;
        if (b == eGsrUnit.getValue()) {
            return eGsrUnit;
        }
        EGsrUnit eGsrUnit2 = EGsrUnit.ug_dL;
        if (b == eGsrUnit2.getValue()) {
            return eGsrUnit2;
        }
        EGsrUnit eGsrUnit3 = EGsrUnit.nmol_L;
        return b == eGsrUnit3.getValue() ? eGsrUnit3 : eGsrUnit;
    }

    private List vp_c(CustomSetting customSetting) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(vp_a(customSetting));
        if (vp_d(customSetting)) {
            arrayList.add(vp_b(customSetting));
            VPOperateManager.getMangerInstance(this.vp_a).setTimeServiceNotifyEnable(customSetting.getEcgAlwaysOpen() == EFunctionStatus.SUPPORT_OPEN);
        }
        return arrayList;
    }

    private boolean vp_d(CustomSetting customSetting) {
        EFunctionStatus isOpenLongClickLockScreen = customSetting.getIsOpenLongClickLockScreen();
        EFunctionStatus isOpenMessageScreenLight = customSetting.getIsOpenMessageScreenLight();
        EFunctionStatus isOpenAutoTemperatureDetect = customSetting.getIsOpenAutoTemperatureDetect();
        EFunctionStatus ecgAlwaysOpen = customSetting.getEcgAlwaysOpen();
        EFunctionStatus isOpenBloodGlucoseDetect = customSetting.getIsOpenBloodGlucoseDetect();
        EFunctionStatus mETDetect = customSetting.getMETDetect();
        EFunctionStatus stressDetect = customSetting.getStressDetect();
        EBloodGlucoseUnit bloodGlucoseUnit = customSetting.getBloodGlucoseUnit();
        EFunctionStatus isOpenBloodComponentDetect = customSetting.getIsOpenBloodComponentDetect();
        EUricAcidUnit uricAcidUnit = customSetting.getUricAcidUnit();
        EBloodFatUnit bloodFatUnit = customSetting.getBloodFatUnit();
        EFunctionStatus fallDetection = customSetting.getFallDetection();
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        return (isOpenLongClickLockScreen == eFunctionStatus && isOpenMessageScreenLight == eFunctionStatus && ecgAlwaysOpen == eFunctionStatus && isOpenAutoTemperatureDetect == eFunctionStatus && isOpenBloodGlucoseDetect == eFunctionStatus && mETDetect == eFunctionStatus && stressDetect == eFunctionStatus && bloodGlucoseUnit == EBloodGlucoseUnit.NONE && isOpenBloodComponentDetect == eFunctionStatus && uricAcidUnit == EUricAcidUnit.NONE && bloodFatUnit == EBloodFatUnit.NONE && fallDetection == eFunctionStatus) ? false : true;
    }

    private byte[] vp_a(CustomSetting customSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = -72;
        bArr[1] = this.vp_c;
        bArr[2] = this.vp_f;
        bArr[3] = this.vp_h;
        byte b = this.vp_j;
        bArr[4] = b;
        bArr[5] = b;
        bArr[6] = vp_a(customSetting.getIsOpenSportRemain());
        bArr[7] = vp_a(customSetting.getIsOpenVoiceBpHeart());
        bArr[8] = vp_a(customSetting.getIsOpenFindPhoneUI());
        bArr[9] = vp_a(customSetting.getIsOpenStopWatch());
        bArr[10] = vp_a(customSetting.getIsOpenSpo2hLowRemind());
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).getSkinType() == 2) {
            bArr[11] = (byte) customSetting.getSkinType();
        } else {
            bArr[11] = vp_a(customSetting.getIsOpenWearDetectSkin());
        }
        bArr[12] = vp_a(customSetting.getIsOpenAutoHRV());
        bArr[13] = vp_a(customSetting.getIsOpenAutoInCall());
        bArr[14] = vp_a(customSetting.getIsOpenDisconnectRemind());
        bArr[15] = vp_a(customSetting.getIsOpenSOS());
        bArr[16] = vp_a(customSetting.getIsOpenPPG());
        bArr[18] = vp_a(customSetting.getIsOpenMusicControl());
        bArr[19] = 0;
        if (!customSetting.isMetricSystem()) {
            bArr[2] = this.vp_g;
        }
        if (!customSetting.is24Hour()) {
            bArr[3] = this.vp_i;
        }
        if (!VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportFivemiuteHeart()) {
            bArr[4] = this.vp_e;
        } else if (!customSetting.isOpenAutoHeartDetect()) {
            bArr[4] = this.vp_k;
        }
        if (!VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportFivemiuteBp()) {
            bArr[5] = this.vp_e;
        } else if (!customSetting.isOpenAutoBpDetect()) {
            bArr[5] = this.vp_k;
        }
        return bArr;
    }

    private EBloodGlucoseUnit vp_b(byte b) {
        EBloodGlucoseUnit eBloodGlucoseUnit = EBloodGlucoseUnit.NONE;
        if (b == eBloodGlucoseUnit.getValue()) {
            return eBloodGlucoseUnit;
        }
        EBloodGlucoseUnit eBloodGlucoseUnit2 = EBloodGlucoseUnit.mmol_L;
        if (b == eBloodGlucoseUnit2.getValue()) {
            return eBloodGlucoseUnit2;
        }
        EBloodGlucoseUnit eBloodGlucoseUnit3 = EBloodGlucoseUnit.mg_dl;
        return b == eBloodGlucoseUnit3.getValue() ? eBloodGlucoseUnit3 : eBloodGlucoseUnit;
    }

    private EBloodFatUnit vp_a(byte b) {
        EBloodFatUnit eBloodFatUnit = EBloodFatUnit.NONE;
        if (b == eBloodFatUnit.getValue()) {
            return eBloodFatUnit;
        }
        EBloodFatUnit eBloodFatUnit2 = EBloodFatUnit.mmol_L;
        if (b == eBloodFatUnit2.getValue()) {
            return eBloodFatUnit2;
        }
        EBloodFatUnit eBloodFatUnit3 = EBloodFatUnit.mg_dl;
        return b == eBloodFatUnit3.getValue() ? eBloodFatUnit3 : eBloodFatUnit;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        if (iListener != null) {
            this.vp_l = (ICustomSettingDataListener) iListener;
        }
        handler(bArr);
    }
}
