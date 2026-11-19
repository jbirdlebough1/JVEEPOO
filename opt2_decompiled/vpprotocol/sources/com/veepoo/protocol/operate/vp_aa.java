package com.veepoo.protocol.operate;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IDeviceFuctionDataListener;
import com.veepoo.protocol.model.datas.DeviceFunctionPackage1;
import com.veepoo.protocol.model.datas.FunctionDeviceSupportData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_aa.class */
public class vp_aa extends com.veepoo.protocol.vp_a {
    public static int vp_e = 0;
    IDeviceFuctionDataListener vp_c;
    FunctionDeviceSupportData vp_d;

    private void vp_g(byte[] bArr) {
        if (bArr.length < 20) {
        }
        switch (bArr[19]) {
            case 1:
                vp_b(bArr);
                this.vp_c.onDeviceFunctionPackage1Report(this.vp_d.getDeviceFunctionPackage1());
                break;
            case 2:
                vp_c(bArr);
                this.vp_c.onDeviceFunctionPackage2Report(this.vp_d.getDeviceFunctionPackage2());
                break;
            case 3:
                vp_d(bArr);
                this.vp_c.onDeviceFunctionPackage3Report(this.vp_d.getDeviceFunctionPackage3());
                break;
            case 4:
                vp_e(bArr);
                this.vp_c.onDeviceFunctionPackage4Report(this.vp_d.getDeviceFunctionPackage4());
                break;
            case 5:
                vp_f(bArr);
                this.vp_c.onDeviceFunctionPackage5Report(this.vp_d.getDeviceFunctionPackage5());
                break;
        }
    }

    private void vp_b(byte[] bArr) {
        DeviceFunctionPackage1 deviceFunctionPackage1 = new DeviceFunctionPackage1();
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        byte b5 = bArr[5];
        vp_e = b5;
        byte b6 = bArr[6];
        byte b7 = bArr[7];
        byte b8 = bArr[8];
        int i = iArrByte2HexToIntArr[9];
        byte b9 = bArr[10];
        byte b10 = bArr[11];
        byte b11 = bArr[12];
        byte b12 = bArr[13];
        byte b13 = bArr[15];
        byte b14 = bArr[16];
        byte b15 = bArr[17];
        byte b16 = bArr[18];
        if (b16 == 1) {
            this.vp_d.setHeartDetect(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setHeartDetect(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setRateTag(b16);
        if (bArr[9] != 0) {
            this.vp_d.setAllMsgLength(i);
        }
        if (b == 0) {
            this.vp_d.setBp(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setBp(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setBpTag(b);
        if (b2 == 1) {
            this.vp_d.setDrink(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setDrink(EFunctionStatus.UNSUPPORT);
        }
        if (b3 == 0) {
            FunctionDeviceSupportData functionDeviceSupportData = this.vp_d;
            EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
            functionDeviceSupportData.setLongseat(eFunctionStatus);
            this.vp_d.setHealthRemind(eFunctionStatus);
        } else if (b3 == 1) {
            this.vp_d.setLongseat(EFunctionStatus.SUPPORT);
            this.vp_d.setHealthRemind(EFunctionStatus.UNSUPPORT);
        } else if (b3 == 2) {
            this.vp_d.setLongseat(EFunctionStatus.UNSUPPORT);
            this.vp_d.setHealthRemind(EFunctionStatus.SUPPORT);
        }
        if (b3 == 1) {
            this.vp_d.setLongseat(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setLongseat(EFunctionStatus.UNSUPPORT);
        }
        this.vp_d.setSkinType(b4);
        if (b9 == 1) {
            this.vp_d.setHeartWaring(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setHeartWaring(EFunctionStatus.UNSUPPORT);
        }
        if (b5 == 0) {
            this.vp_d.setWeChatSport(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setWeChatSport(EFunctionStatus.SUPPORT);
        }
        if (b6 == 1 || b6 == 2) {
            this.vp_d.setCamera(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setCamera(EFunctionStatus.UNSUPPORT);
        }
        if (b7 != 0) {
            this.vp_d.setFatigue(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setFatigue(EFunctionStatus.UNSUPPORT);
        }
        this.vp_d.setFatigueTag(b7);
        int i2 = b8 & 255;
        this.vp_d.setSpo2hType(i2);
        if (b8 == 1 || b8 == 2 || b8 == 3 || b8 == 4 || b8 == 5 || b8 == 6 || b8 == 7 || b8 == 8 || b8 == 9 || b8 == -3 || b8 == -2 || b8 == -32) {
            FunctionDeviceSupportData functionDeviceSupportData2 = this.vp_d;
            EFunctionStatus eFunctionStatus2 = EFunctionStatus.SUPPORT;
            functionDeviceSupportData2.setSpo2H(eFunctionStatus2);
            this.vp_d.setSpo2hType(i2);
            if (-3 == b8) {
                this.vp_d.setSpo2hShow(EFunctionStatus.UNSUPPORT);
            }
            if (4 == b8) {
                this.vp_d.setSpo2HBreathBreak(eFunctionStatus2);
            }
            if (-2 == b8) {
                this.vp_d.setSpo2HValueZero(eFunctionStatus2);
            }
            if (b8 == 2 || b8 == -2 || b8 == 5 || b8 == 6 || b8 == 7 || b8 == 8 || b8 == -3 || b8 == 9) {
                this.vp_d.setSpo2HAdjuster(eFunctionStatus2);
            }
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveSPOH2LowRemain((b8 == -2 || b8 == -3 || b8 == 8) ? false : true);
        } else {
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveSPOH2LowRemain(false);
            FunctionDeviceSupportData functionDeviceSupportData3 = this.vp_d;
            EFunctionStatus eFunctionStatus3 = EFunctionStatus.UNSUPPORT;
            functionDeviceSupportData3.setSpo2H(eFunctionStatus3);
            this.vp_d.setSpo2HAdjuster(eFunctionStatus3);
            this.vp_d.setSpo2hShow(eFunctionStatus3);
        }
        if (b10 == 1) {
            this.vp_d.setNightTurnSetting(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setNightTurnSetting(EFunctionStatus.UNSUPPORT);
        }
        String language = Locale.getDefault().getLanguage();
        this.vp_d.setWomenTag(b11 & 255);
        if (b11 != 0 && (b11 == 1 || b11 == 3 || (b11 == 2 && (language.equals("zh") || language.equals("en"))))) {
            this.vp_d.setWomen(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setWomen(EFunctionStatus.UNSUPPORT);
        }
        if (b14 == 1) {
            this.vp_d.setAngioAdjuster(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setAngioAdjuster(EFunctionStatus.UNSUPPORT);
        }
        this.vp_d.setAlarmTag(b15);
        if (b15 == 0 || b15 == -2) {
            FunctionDeviceSupportData functionDeviceSupportData4 = this.vp_d;
            EFunctionStatus eFunctionStatus4 = EFunctionStatus.UNSUPPORT;
            functionDeviceSupportData4.setTextAlarm(eFunctionStatus4);
            this.vp_d.setAlarm2(eFunctionStatus4);
        } else if (b15 == 5 || b15 == 6 || b15 == 7) {
            FunctionDeviceSupportData functionDeviceSupportData5 = this.vp_d;
            EFunctionStatus eFunctionStatus5 = EFunctionStatus.SUPPORT;
            functionDeviceSupportData5.setTextAlarm(eFunctionStatus5);
            this.vp_d.setAlarm2(eFunctionStatus5);
        } else {
            this.vp_d.setTextAlarm(EFunctionStatus.UNSUPPORT);
            this.vp_d.setAlarm2(EFunctionStatus.SUPPORT);
        }
        if (b12 == 1) {
            this.vp_d.setScreenLight(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setScreenLight(EFunctionStatus.UNSUPPORT);
        }
        if (b13 == 1) {
            this.vp_d.setNewCalcSport(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setNewCalcSport(EFunctionStatus.UNSUPPORT);
        }
        deviceFunctionPackage1.setNewCalcSport(this.vp_d.getNewCalcSport());
    }

    private void vp_c(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        byte b = bArr[1];
        byte b2 = bArr[2];
        int i = iArrByte2HexToIntArr[3];
        byte b3 = bArr[4];
        byte b4 = bArr[5];
        byte b5 = bArr[6];
        byte b6 = bArr[7];
        byte b7 = bArr[8];
        byte b8 = bArr[9];
        int i2 = iArrByte2HexToIntArr[10];
        byte b9 = bArr[12];
        byte b10 = bArr[13];
        byte b11 = bArr[15];
        byte b12 = bArr[16];
        byte b13 = bArr[17];
        new StringBuilder().append("originProtocolVersion:").append(i2);
        this.vp_d.setOriginProtcolVersion(i2);
        this.vp_d.setContactMsgLength(i);
        if (b == 1) {
            this.vp_d.setCountDown(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setCountDown(EFunctionStatus.UNSUPPORT);
        }
        if (b2 != 0) {
            this.vp_d.setWathcDay(iArrByte2HexToIntArr[2]);
        }
        if (b3 == 0) {
            this.vp_d.setHidFuction(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setHidFuction(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setScreenstyle(iArrByte2HexToIntArr[6]);
        if (b6 == 0) {
            this.vp_d.setBeathFunction(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setBeathFunction(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setWeatherStyle(iArrByte2HexToIntArr[9]);
        if (b8 == 0) {
            this.vp_d.setWeatherFunction(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setWeatherFunction(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setAllDayHrvFunc((b7 == 3 || b7 == 4 || b7 == 6 || b7 == 7 || b7 == 8) ? EFunctionStatus.SUPPORT : EFunctionStatus.UNSUPPORT);
        this.vp_d.setHrvType(b7);
        this.vp_d.setHrvAppDetectFunction((b7 == 7 || b7 == 8) ? EFunctionStatus.SUPPORT : EFunctionStatus.UNSUPPORT);
        if (b7 == 0 || b7 == 5) {
            this.vp_d.setHrvFunction(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setHrvFunction(EFunctionStatus.SUPPORT);
        }
        if (b4 == 0) {
            this.vp_d.setSportModel(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setSportModel(EFunctionStatus.SUPPORT);
            this.vp_d.setSportmodelday(iArrByte2HexToIntArr[5]);
        }
        if (b9 == 0) {
            this.vp_d.setScreenLightTime(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setScreenLightTime(EFunctionStatus.SUPPORT);
        }
        if (b11 == 0) {
            this.vp_d.setEcg(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setEcg(EFunctionStatus.SUPPORT);
        }
        this.vp_d.setEcgType(b11);
        this.vp_d.setSleepTag(b10);
        if (b10 == 0) {
            this.vp_d.setPrecisionSleep(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setPrecisionSleep(EFunctionStatus.SUPPORT);
        }
        if (b12 == 0) {
            this.vp_d.setMultSportModel(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setMultSportModel(EFunctionStatus.SUPPORT);
        }
        if (b12 != 0 && (b12 == 1 || b12 == 3 || b12 == 4 || b12 == 5 || b12 == -32 || b12 == -31 || b12 == -30 || b12 == -29 || b12 == -28 || b12 == -27 || b12 == -26 || b12 == -25 || b12 == -23 || b12 == -22 || b12 == -21 || b12 == -20)) {
            this.vp_d.setMultSportModel(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setMultSportModel(EFunctionStatus.UNSUPPORT);
        }
        this.vp_d.setSportModelType(iArrByte2HexToIntArr[16]);
        if (b13 == 0) {
            this.vp_d.setLowPower(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setLowPower(EFunctionStatus.SUPPORT);
        }
    }

    private void vp_d(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        byte b = bArr[2];
        int i2 = (b >> 4) & 15;
        int i3 = b & 15;
        int i4 = iArrByte2HexToIntArr[4];
        int i5 = iArrByte2HexToIntArr[6];
        int i6 = iArrByte2HexToIntArr[7];
        int i7 = iArrByte2HexToIntArr[9];
        int i8 = iArrByte2HexToIntArr[8];
        int i9 = iArrByte2HexToIntArr[15];
        int i10 = iArrByte2HexToIntArr[16];
        int i11 = iArrByte2HexToIntArr[17];
        int i12 = iArrByte2HexToIntArr[18];
        this.vp_d.setBigDataTranType(i);
        this.vp_d.setWatchUiServerCount(i2);
        this.vp_d.setWatchUiCoustomCount(i3);
        this.vp_d.setContactType(i4);
        this.vp_d.setContactFunction(i4 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setMusicStyle(i5);
        this.vp_d.setTemptureType(i6);
        this.vp_d.setTemperatureFunction(i6 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setCpuType(i10);
        this.vp_d.setMet((i11 == 2 || i11 == 3) ? EFunctionStatus.SUPPORT : EFunctionStatus.UNSUPPORT);
        this.vp_d.setMetType(i11);
        this.vp_d.setStressType(i12);
        this.vp_d.setStress((i12 == 2 || i12 == 3) ? EFunctionStatus.SUPPORT : EFunctionStatus.UNSUPPORT);
        if (this.vp_d.getScreenstyle() + i2 + i3 > 1) {
            this.vp_d.setScreenStyleFunction(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setScreenStyleFunction(EFunctionStatus.UNSUPPORT);
        }
        if (i8 == 0) {
            this.vp_d.setFindDeviceByPhone(EFunctionStatus.UNSUPPORT);
        } else {
            this.vp_d.setFindDeviceByPhone(EFunctionStatus.SUPPORT);
        }
        if (i7 != 0) {
            this.vp_d.setAgps(EFunctionStatus.SUPPORT);
        } else {
            this.vp_d.setAgps(EFunctionStatus.UNSUPPORT);
        }
        new StringBuilder().append("Blood Glucose功能-----------------》  ").append(i9);
        FunctionDeviceSupportData functionDeviceSupportData = this.vp_d;
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        EFunctionStatus eFunctionStatus2 = eFunctionStatus;
        functionDeviceSupportData.setBloodGlucoseMultipleAdjusting(eFunctionStatus);
        if (i9 == 0) {
            this.vp_d.setBloodGlucose(eFunctionStatus2);
            this.vp_d.setBloodGlucoseAdjusting(eFunctionStatus2);
        } else if (i9 == 1) {
            this.vp_d.setBloodGlucose(EFunctionStatus.SUPPORT);
            this.vp_d.setBloodGlucoseAdjusting(eFunctionStatus2);
        } else if (i9 == 2 || i9 == 6) {
            FunctionDeviceSupportData functionDeviceSupportData2 = this.vp_d;
            EFunctionStatus eFunctionStatus3 = EFunctionStatus.SUPPORT;
            functionDeviceSupportData2.setBloodGlucose(eFunctionStatus3);
            this.vp_d.setBloodGlucoseAdjusting(eFunctionStatus3);
        } else if (i9 == 3) {
            this.vp_d.setBloodGlucose(eFunctionStatus2);
            this.vp_d.setBloodGlucoseAdjusting(EFunctionStatus.SUPPORT);
        } else if (i9 == 4 || i9 == 5 || i9 == 7 || i9 == 8 || i9 == 9) {
            FunctionDeviceSupportData functionDeviceSupportData3 = this.vp_d;
            EFunctionStatus eFunctionStatus4 = EFunctionStatus.SUPPORT;
            functionDeviceSupportData3.setBloodGlucose(eFunctionStatus4);
            this.vp_d.setBloodGlucoseAdjusting(eFunctionStatus2);
            this.vp_d.setBloodGlucoseMultipleAdjusting(eFunctionStatus4);
        }
        this.vp_d.setBloodGlucoseTag(i9);
        FunctionDeviceSupportData functionDeviceSupportData4 = this.vp_d;
        if (i9 == 5 || i9 == 6 || i9 == 7 || i9 == 9) {
            eFunctionStatus2 = EFunctionStatus.SUPPORT;
        }
        functionDeviceSupportData4.setBloodGlucoseRiskAssessment(eFunctionStatus2);
    }

    private void vp_e(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        int i3 = iArrByte2HexToIntArr[3];
        int i4 = iArrByte2HexToIntArr[4];
        int i5 = iArrByte2HexToIntArr[5];
        int i6 = iArrByte2HexToIntArr[6];
        int i7 = iArrByte2HexToIntArr[7];
        int i8 = iArrByte2HexToIntArr[8];
        int i9 = iArrByte2HexToIntArr[9];
        int i10 = iArrByte2HexToIntArr[10];
        int i11 = iArrByte2HexToIntArr[11];
        int i12 = iArrByte2HexToIntArr[12];
        int i13 = iArrByte2HexToIntArr[13];
        int i14 = iArrByte2HexToIntArr[14];
        int i15 = iArrByte2HexToIntArr[15];
        int i16 = iArrByte2HexToIntArr[17];
        int i17 = iArrByte2HexToIntArr[18];
        this.vp_d.setAiQA(i8 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setAiDial(i9 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setAiDialTag(i9);
        this.vp_d.setAiQaTag(i8);
        this.vp_d.setDistanceCalorieGoal(i10 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setVideoDial(i11 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setWallet(i5 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setPostcard(i6 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setPhotoAlbum(i14 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setTemperatureAlarm(i4 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setGameSetting(i7 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setBloodComponent(i == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setBloodComponentTag(i & 255);
        FunctionDeviceSupportData functionDeviceSupportData = this.vp_d;
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        functionDeviceSupportData.setBloodComponentSingleCalibration(eFunctionStatus);
        this.vp_d.setBodyComponent(eFunctionStatus);
        this.vp_d.setWorldClock(eFunctionStatus);
        this.vp_d.setAutoMeasure(i12 == 1 ? EFunctionStatus.SUPPORT : eFunctionStatus);
        this.vp_d.setServer4g(i15 == 0 ? eFunctionStatus : EFunctionStatus.SUPPORT);
        this.vp_d.setHealthAssessment(i16 == 0 ? eFunctionStatus : EFunctionStatus.SUPPORT);
        this.vp_d.setHealthAssessmentTag(i16);
        this.vp_d.setBloodGlucoseHealthAssessment((i16 & 128) != 0 ? EFunctionStatus.SUPPORT : eFunctionStatus);
        this.vp_d.setBloodComponentHealthAssessment((i16 & 64) != 0 ? EFunctionStatus.SUPPORT : eFunctionStatus);
        this.vp_d.setBodyComponentHealthAssessment((i16 & 32) != 0 ? EFunctionStatus.SUPPORT : eFunctionStatus);
        this.vp_d.setDaSport(i13 != 0 ? EFunctionStatus.SUPPORT : eFunctionStatus);
        this.vp_d.setMiniCheckup(i17 == 0 ? eFunctionStatus : EFunctionStatus.SUPPORT);
        this.vp_d.setMiniCheckupTag(i17);
        if (i == 0) {
            this.vp_d.setBloodComponent(eFunctionStatus);
        } else if (i == 2) {
            this.vp_d.setBloodComponentSingleCalibration(EFunctionStatus.SUPPORT);
        }
        if (i2 == 1) {
            this.vp_d.setBodyComponent(EFunctionStatus.SUPPORT);
        }
        if (i3 == 1) {
            this.vp_d.setWorldClock(EFunctionStatus.SUPPORT);
        }
    }

    private void vp_f(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        int i3 = iArrByte2HexToIntArr[3];
        int i4 = iArrByte2HexToIntArr[4];
        int i5 = iArrByte2HexToIntArr[5];
        int i6 = iArrByte2HexToIntArr[6];
        int i7 = iArrByte2HexToIntArr[7];
        int i8 = iArrByte2HexToIntArr[8];
        int i9 = iArrByte2HexToIntArr[9];
        int i10 = iArrByte2HexToIntArr[10];
        int i11 = iArrByte2HexToIntArr[11];
        int i12 = iArrByte2HexToIntArr[13];
        int i13 = iArrByte2HexToIntArr[14];
        this.vp_d.setTextImagePush(i == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setGSR(i2 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setSafetyProtection(i3 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setPttControlTag(i8);
        this.vp_d.setScreenTypeTag(i4);
        this.vp_d.setBarometerTag(i5);
        this.vp_d.setTouchICTypeTag(i6);
        this.vp_d.setNewProductionProtocolTag(i7);
        this.vp_d.setSportsStatusFeatureTag(i9);
        this.vp_d.setRemindEventFeatureTag(i10);
        this.vp_d.setEmotionTag(i11);
        this.vp_d.setHealthLightTag(i12);
        this.vp_d.setBarometer(i5 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setTouchICType(i6 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setNewProductionTestProtocolTag(i7 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setPttControl(i8 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setSportStatus(i9 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setRemindEvent(i10 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setEmotion(i11 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setHealthLight(i12 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setMotor(i13 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
        this.vp_d.setMotorTag(i13);
        int i14 = iArrByte2HexToIntArr[15];
        this.vp_d.setWearHandTag(i14);
        this.vp_d.setWearHand(i14 == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.SUPPORT);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IDeviceFuctionDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        if (bArr[19] == 1) {
            this.vp_d = null;
        }
        if (this.vp_d == null) {
            this.vp_d = new FunctionDeviceSupportData();
        }
        vp_g(bArr);
        this.vp_c.onFunctionSupportDataChange(this.vp_d);
        VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceFunctionA7(this.vp_d);
    }
}
