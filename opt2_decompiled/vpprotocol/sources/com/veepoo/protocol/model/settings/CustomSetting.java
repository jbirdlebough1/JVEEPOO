package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EBloodFatUnit;
import com.veepoo.protocol.model.enums.EBloodGlucoseUnit;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EGsrUnit;
import com.veepoo.protocol.model.enums.ETemperatureUnit;
import com.veepoo.protocol.model.enums.EUricAcidUnit;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/CustomSetting.class */
public class CustomSetting {
    private boolean isHaveMetricSystem;
    private boolean isMetricSystem;
    private boolean is24Hour;
    private boolean isOpenAutoHeartDetect;
    private boolean isOpenAutoBpDetect;
    private ETemperatureUnit temperatureUnit;
    private EFunctionStatus isOpenSportRemain;
    private EFunctionStatus isOpenVoiceBpHeart;
    private EFunctionStatus isOpenFindPhoneUI;
    private EFunctionStatus isOpenStopWatch;
    private EFunctionStatus isOpenSpo2hLowRemind;
    private EFunctionStatus isOpenWearDetectSkin;
    private int skinType;
    private EFunctionStatus isOpenAutoHRV;
    private EFunctionStatus isOpenAutoInCall;
    private EFunctionStatus isOpenDisconnectRemind;
    private EFunctionStatus isOpenSOS;
    private EFunctionStatus isOpenAutoTemperatureDetect;
    private EFunctionStatus ecgAlwaysOpen;
    private EFunctionStatus METDetect;
    private EFunctionStatus stressDetect;
    private EFunctionStatus isOpenPPG;
    private EFunctionStatus isOpenMusicControl;
    private EFunctionStatus isOpenLongClickLockScreen;
    private EFunctionStatus isOpenMessageScreenLight;
    private EFunctionStatus isOpenBloodGlucoseDetect;
    private EBloodGlucoseUnit bloodGlucoseUnit;
    private EFunctionStatus isOpenBloodComponentDetect;
    private EUricAcidUnit uricAcidUnit;
    private EBloodFatUnit bloodFatUnit;
    private EGsrUnit gsrUnit;
    private EFunctionStatus fallDetection;

    public CustomSetting(CustomSettingData customSettingData) {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.isOpenSportRemain = eFunctionStatus;
        this.isOpenVoiceBpHeart = eFunctionStatus;
        this.isOpenFindPhoneUI = eFunctionStatus;
        this.isOpenStopWatch = eFunctionStatus;
        this.isOpenSpo2hLowRemind = eFunctionStatus;
        this.isOpenWearDetectSkin = eFunctionStatus;
        this.skinType = -1;
        this.isOpenAutoHRV = eFunctionStatus;
        this.isOpenAutoInCall = eFunctionStatus;
        this.isOpenDisconnectRemind = eFunctionStatus;
        this.isOpenSOS = eFunctionStatus;
        this.isOpenAutoTemperatureDetect = eFunctionStatus;
        this.ecgAlwaysOpen = eFunctionStatus;
        this.METDetect = eFunctionStatus;
        this.stressDetect = eFunctionStatus;
        this.isOpenPPG = eFunctionStatus;
        this.isOpenMusicControl = eFunctionStatus;
        this.isOpenLongClickLockScreen = eFunctionStatus;
        this.isOpenMessageScreenLight = eFunctionStatus;
        this.isOpenBloodGlucoseDetect = eFunctionStatus;
        this.bloodGlucoseUnit = EBloodGlucoseUnit.NONE;
        this.isOpenBloodComponentDetect = eFunctionStatus;
        this.uricAcidUnit = EUricAcidUnit.NONE;
        this.bloodFatUnit = EBloodFatUnit.NONE;
        this.gsrUnit = EGsrUnit.NONE;
        this.fallDetection = eFunctionStatus;
        this.isHaveMetricSystem = customSettingData.getMetricSystem().isHaveFunction();
        this.isMetricSystem = customSettingData.getMetricSystem().isOpen();
        this.is24Hour = customSettingData.isIs24Hour();
        this.isOpenAutoHeartDetect = customSettingData.getAutoHeartDetect().isOpen();
        this.isOpenAutoBpDetect = customSettingData.getAutoBpDetect().isOpen();
        this.temperatureUnit = customSettingData.getTemperatureUnit();
        this.isOpenSportRemain = customSettingData.getSportOverRemain();
        this.isOpenVoiceBpHeart = customSettingData.getVoiceBpHeart();
        this.isOpenFindPhoneUI = customSettingData.getFindPhoneUi();
        this.isOpenStopWatch = customSettingData.getSecondsWatch();
        this.isOpenSpo2hLowRemind = customSettingData.getLowSpo2hRemain();
        this.isOpenWearDetectSkin = customSettingData.getSkin();
        this.skinType = customSettingData.getSkinLevel();
        this.isOpenAutoHRV = customSettingData.getAutoHrv();
        this.isOpenAutoInCall = customSettingData.getAutoIncall();
        this.isOpenDisconnectRemind = customSettingData.getDisconnectRemind();
        this.isOpenSOS = customSettingData.getSOS();
        this.isOpenAutoTemperatureDetect = customSettingData.getAutoTemperatureDetect();
        this.ecgAlwaysOpen = customSettingData.getEcgAlwaysOpen();
        this.METDetect = customSettingData.getMETDetect();
        this.stressDetect = customSettingData.getStressDetect();
        this.isOpenPPG = customSettingData.getPpg();
        this.isOpenMusicControl = customSettingData.getMusicControl();
        this.isOpenLongClickLockScreen = customSettingData.getLongClickLockScreen();
        this.isOpenMessageScreenLight = customSettingData.getMessageScreenLight();
        this.isOpenBloodGlucoseDetect = customSettingData.getBloodGlucoseDetection();
        this.isOpenBloodComponentDetect = customSettingData.getBloodComponentDetect();
        this.bloodGlucoseUnit = customSettingData.getBloodGlucoseUnit();
        this.uricAcidUnit = customSettingData.getUricAcidUnit();
        this.bloodFatUnit = customSettingData.getBloodFatUnit();
        this.gsrUnit = customSettingData.getGsrUnit();
        this.fallDetection = customSettingData.getFallDetection();
    }

    public boolean isHaveMetricSystem() {
        return this.isHaveMetricSystem;
    }

    public void setHaveMetricSystem(boolean z) {
        this.isHaveMetricSystem = z;
    }

    public boolean isMetricSystem() {
        return this.isMetricSystem;
    }

    public void setMetricSystem(boolean z) {
        this.isMetricSystem = z;
    }

    public boolean is24Hour() {
        return this.is24Hour;
    }

    public void setIs24Hour(boolean z) {
        this.is24Hour = z;
    }

    public boolean isOpenAutoHeartDetect() {
        return this.isOpenAutoHeartDetect;
    }

    public void setOpenAutoHeartDetect(boolean z) {
        this.isOpenAutoHeartDetect = z;
    }

    public boolean isOpenAutoBpDetect() {
        return this.isOpenAutoBpDetect;
    }

    public void setOpenAutoBpDetect(boolean z) {
        this.isOpenAutoBpDetect = z;
    }

    public boolean isIs24Hour() {
        return this.is24Hour;
    }

    public EFunctionStatus getIsOpenSportRemain() {
        return this.isOpenSportRemain;
    }

    public void setIsOpenSportRemain(EFunctionStatus eFunctionStatus) {
        this.isOpenSportRemain = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenVoiceBpHeart() {
        return this.isOpenVoiceBpHeart;
    }

    public void setIsOpenVoiceBpHeart(EFunctionStatus eFunctionStatus) {
        this.isOpenVoiceBpHeart = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenFindPhoneUI() {
        return this.isOpenFindPhoneUI;
    }

    public void setIsOpenFindPhoneUI(EFunctionStatus eFunctionStatus) {
        this.isOpenFindPhoneUI = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenStopWatch() {
        return this.isOpenStopWatch;
    }

    public void setIsOpenStopWatch(EFunctionStatus eFunctionStatus) {
        this.isOpenStopWatch = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenSpo2hLowRemind() {
        return this.isOpenSpo2hLowRemind;
    }

    public void setIsOpenSpo2hLowRemind(EFunctionStatus eFunctionStatus) {
        this.isOpenSpo2hLowRemind = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenWearDetectSkin() {
        return this.isOpenWearDetectSkin;
    }

    public void setIsOpenWearDetectSkin(EFunctionStatus eFunctionStatus) {
        this.isOpenWearDetectSkin = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenAutoHRV() {
        return this.isOpenAutoHRV;
    }

    public void setIsOpenAutoHRV(EFunctionStatus eFunctionStatus) {
        this.isOpenAutoHRV = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenAutoInCall() {
        return this.isOpenAutoInCall;
    }

    public void setIsOpenAutoInCall(EFunctionStatus eFunctionStatus) {
        this.isOpenAutoInCall = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenDisconnectRemind() {
        return this.isOpenDisconnectRemind;
    }

    public void setIsOpenDisconnectRemind(EFunctionStatus eFunctionStatus) {
        this.isOpenDisconnectRemind = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenSOS() {
        return this.isOpenSOS;
    }

    public EFunctionStatus getIsOpenPPG() {
        return this.isOpenPPG;
    }

    public void setIsOpenPPG(EFunctionStatus eFunctionStatus) {
        this.isOpenPPG = eFunctionStatus;
    }

    public void setIsOpenSOS(EFunctionStatus eFunctionStatus) {
        this.isOpenSOS = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenMusicControl() {
        return this.isOpenMusicControl;
    }

    public void setIsOpenMusicControl(EFunctionStatus eFunctionStatus) {
        this.isOpenMusicControl = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenLongClickLockScreen() {
        return this.isOpenLongClickLockScreen;
    }

    public void setIsOpenLongClickLockScreen(EFunctionStatus eFunctionStatus) {
        this.isOpenLongClickLockScreen = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenMessageScreenLight() {
        return this.isOpenMessageScreenLight;
    }

    public void setIsOpenMessageScreenLight(EFunctionStatus eFunctionStatus) {
        this.isOpenMessageScreenLight = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenAutoTemperatureDetect() {
        return this.isOpenAutoTemperatureDetect;
    }

    public void setIsOpenAutoTemperatureDetect(EFunctionStatus eFunctionStatus) {
        this.isOpenAutoTemperatureDetect = eFunctionStatus;
    }

    public void setTemperatureUnit(ETemperatureUnit eTemperatureUnit) {
        this.temperatureUnit = eTemperatureUnit;
    }

    public ETemperatureUnit getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public EFunctionStatus getEcgAlwaysOpen() {
        return this.ecgAlwaysOpen;
    }

    public void setEcgAlwaysOpen(EFunctionStatus eFunctionStatus) {
        this.ecgAlwaysOpen = eFunctionStatus;
    }

    public EFunctionStatus getIsOpenBloodGlucoseDetect() {
        return this.isOpenBloodGlucoseDetect;
    }

    public void setIsOpenBloodGlucoseDetect(EFunctionStatus eFunctionStatus) {
        this.isOpenBloodGlucoseDetect = eFunctionStatus;
    }

    public EFunctionStatus getMETDetect() {
        return this.METDetect;
    }

    public void setMETDetect(EFunctionStatus eFunctionStatus) {
        this.METDetect = eFunctionStatus;
    }

    public EFunctionStatus getStressDetect() {
        return this.stressDetect;
    }

    public void setStressDetect(EFunctionStatus eFunctionStatus) {
        this.stressDetect = eFunctionStatus;
    }

    public EBloodGlucoseUnit getBloodGlucoseUnit() {
        return this.bloodGlucoseUnit;
    }

    public void setBloodGlucoseUnit(EBloodGlucoseUnit eBloodGlucoseUnit) {
        this.bloodGlucoseUnit = eBloodGlucoseUnit;
    }

    public EFunctionStatus getIsOpenBloodComponentDetect() {
        return this.isOpenBloodComponentDetect;
    }

    public void setIsOpenBloodComponentDetect(EFunctionStatus eFunctionStatus) {
        this.isOpenBloodComponentDetect = eFunctionStatus;
    }

    public EUricAcidUnit getUricAcidUnit() {
        return this.uricAcidUnit;
    }

    public void setUricAcidUnit(EUricAcidUnit eUricAcidUnit) {
        this.uricAcidUnit = eUricAcidUnit;
    }

    public EBloodFatUnit getBloodFatUnit() {
        return this.bloodFatUnit;
    }

    public void setBloodFatUnit(EBloodFatUnit eBloodFatUnit) {
        this.bloodFatUnit = eBloodFatUnit;
    }

    public int getSkinType() {
        return this.skinType;
    }

    public void setSkinType(int i) {
        this.skinType = i;
    }

    public EFunctionStatus getFallDetection() {
        return this.fallDetection;
    }

    public void setFallDetection(EFunctionStatus eFunctionStatus) {
        this.fallDetection = eFunctionStatus;
    }

    public EGsrUnit getGsrUnit() {
        return this.gsrUnit;
    }

    public void setGsrUnit(EGsrUnit eGsrUnit) {
        this.gsrUnit = eGsrUnit;
    }

    public CustomSetting(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.isOpenSportRemain = eFunctionStatus;
        this.isOpenVoiceBpHeart = eFunctionStatus;
        this.isOpenFindPhoneUI = eFunctionStatus;
        this.isOpenStopWatch = eFunctionStatus;
        this.isOpenSpo2hLowRemind = eFunctionStatus;
        this.isOpenWearDetectSkin = eFunctionStatus;
        this.skinType = -1;
        this.isOpenAutoHRV = eFunctionStatus;
        this.isOpenAutoInCall = eFunctionStatus;
        this.isOpenDisconnectRemind = eFunctionStatus;
        this.isOpenSOS = eFunctionStatus;
        this.isOpenAutoTemperatureDetect = eFunctionStatus;
        this.ecgAlwaysOpen = eFunctionStatus;
        this.METDetect = eFunctionStatus;
        this.stressDetect = eFunctionStatus;
        this.isOpenPPG = eFunctionStatus;
        this.isOpenMusicControl = eFunctionStatus;
        this.isOpenLongClickLockScreen = eFunctionStatus;
        this.isOpenMessageScreenLight = eFunctionStatus;
        this.isOpenBloodGlucoseDetect = eFunctionStatus;
        this.bloodGlucoseUnit = EBloodGlucoseUnit.NONE;
        this.isOpenBloodComponentDetect = eFunctionStatus;
        this.uricAcidUnit = EUricAcidUnit.NONE;
        this.bloodFatUnit = EBloodFatUnit.NONE;
        this.gsrUnit = EGsrUnit.NONE;
        this.fallDetection = eFunctionStatus;
        this.isHaveMetricSystem = z;
        this.isMetricSystem = z2;
        this.is24Hour = z3;
        this.isOpenAutoHeartDetect = z4;
        this.isOpenAutoBpDetect = z5;
    }

    public CustomSetting(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, EFunctionStatus eFunctionStatus, EFunctionStatus eFunctionStatus2, EFunctionStatus eFunctionStatus3, EFunctionStatus eFunctionStatus4, EFunctionStatus eFunctionStatus5, EFunctionStatus eFunctionStatus6, EFunctionStatus eFunctionStatus7, EFunctionStatus eFunctionStatus8, EFunctionStatus eFunctionStatus9) {
        this(z, z2, z3, z4, z5);
        this.isOpenSportRemain = eFunctionStatus;
        this.isOpenVoiceBpHeart = eFunctionStatus2;
        this.isOpenFindPhoneUI = eFunctionStatus3;
        this.isOpenStopWatch = eFunctionStatus4;
        this.isOpenSpo2hLowRemind = eFunctionStatus5;
        this.isOpenWearDetectSkin = eFunctionStatus6;
        this.isOpenAutoInCall = eFunctionStatus7;
        this.isOpenAutoHRV = eFunctionStatus8;
        this.isOpenDisconnectRemind = eFunctionStatus9;
    }

    public CustomSetting(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, EFunctionStatus eFunctionStatus, EFunctionStatus eFunctionStatus2, EFunctionStatus eFunctionStatus3, EFunctionStatus eFunctionStatus4, EFunctionStatus eFunctionStatus5, EFunctionStatus eFunctionStatus6, EFunctionStatus eFunctionStatus7, EFunctionStatus eFunctionStatus8, EFunctionStatus eFunctionStatus9, EFunctionStatus eFunctionStatus10) {
        this(z, z2, z3, z4, z5);
        this.isOpenSportRemain = eFunctionStatus;
        this.isOpenVoiceBpHeart = eFunctionStatus2;
        this.isOpenFindPhoneUI = eFunctionStatus3;
        this.isOpenStopWatch = eFunctionStatus4;
        this.isOpenSpo2hLowRemind = eFunctionStatus5;
        this.isOpenWearDetectSkin = eFunctionStatus6;
        this.isOpenAutoInCall = eFunctionStatus7;
        this.isOpenAutoHRV = eFunctionStatus8;
        this.isOpenDisconnectRemind = eFunctionStatus9;
        this.isOpenSOS = eFunctionStatus10;
    }

    public CustomSetting(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, EFunctionStatus eFunctionStatus, EFunctionStatus eFunctionStatus2, EFunctionStatus eFunctionStatus3, EFunctionStatus eFunctionStatus4, EFunctionStatus eFunctionStatus5, EFunctionStatus eFunctionStatus6, EFunctionStatus eFunctionStatus7, EFunctionStatus eFunctionStatus8, EFunctionStatus eFunctionStatus9, EFunctionStatus eFunctionStatus10, EFunctionStatus eFunctionStatus11, EFunctionStatus eFunctionStatus12) {
        this(z, z2, z3, z4, z5);
        this.isOpenSportRemain = eFunctionStatus;
        this.isOpenVoiceBpHeart = eFunctionStatus2;
        this.isOpenFindPhoneUI = eFunctionStatus3;
        this.isOpenStopWatch = eFunctionStatus4;
        this.isOpenSpo2hLowRemind = eFunctionStatus5;
        this.isOpenWearDetectSkin = eFunctionStatus6;
        this.isOpenAutoInCall = eFunctionStatus7;
        this.isOpenAutoHRV = eFunctionStatus8;
        this.isOpenDisconnectRemind = eFunctionStatus9;
        this.isOpenSOS = eFunctionStatus10;
        this.isOpenPPG = eFunctionStatus11;
        this.isOpenAutoTemperatureDetect = eFunctionStatus12;
        if (eFunctionStatus12 == EFunctionStatus.UNSUPPORT) {
            this.temperatureUnit = ETemperatureUnit.NONE;
        }
    }
}
