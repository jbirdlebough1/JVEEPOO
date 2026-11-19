package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EBloodFatUnit;
import com.veepoo.protocol.model.enums.EBloodGlucoseUnit;
import com.veepoo.protocol.model.enums.ECustomStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EGsrUnit;
import com.veepoo.protocol.model.enums.ETemperatureUnit;
import com.veepoo.protocol.model.enums.EUricAcidUnit;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/CustomSettingData.class */
public class CustomSettingData {
    private ECustomStatus status;
    private boolean is24Hour;
    private EFunctionStatus metricSystem;
    private EFunctionStatus autoHeartDetect;
    private EFunctionStatus autoBpDetect;
    private EFunctionStatus sportOverRemain;
    private EFunctionStatus voiceBpHeart;
    private EFunctionStatus findPhoneUi;
    private EFunctionStatus secondsWatch;
    private EFunctionStatus lowSpo2hRemain;
    private EFunctionStatus skin;
    private EFunctionStatus autoHrv;
    private EFunctionStatus autoIncall;
    private EFunctionStatus disconnectRemind;
    private EFunctionStatus SOS;
    private EFunctionStatus ppg;
    private EFunctionStatus musicControl;
    private EFunctionStatus longClickLockScreen;
    private EFunctionStatus messageScreenLight;
    private EFunctionStatus autoTemperatureDetect;
    private ETemperatureUnit temperatureUnit;
    private EFunctionStatus ecgAlwaysOpen;
    private EFunctionStatus bloodGlucoseDetection;
    private EFunctionStatus METDetect;
    private EFunctionStatus stressDetect;
    private EBloodGlucoseUnit bloodGlucoseUnit;
    private EFunctionStatus bloodComponentDetect;
    private EUricAcidUnit uricAcidUnit;
    private EBloodFatUnit bloodFatUnit;
    private EFunctionStatus fallDetection;
    private EGsrUnit gsrUnit;
    private int skinLevel;
    private boolean isHaveMetricSystem;
    private boolean metricSystemValue;
    private boolean isOpenAutoHeartDetect;
    private boolean isOpenAutoBpDetect;
    private int index;

    public CustomSettingData() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.metricSystem = eFunctionStatus;
        this.autoHeartDetect = eFunctionStatus;
        this.autoBpDetect = eFunctionStatus;
        this.sportOverRemain = eFunctionStatus;
        this.voiceBpHeart = eFunctionStatus;
        this.findPhoneUi = eFunctionStatus;
        this.secondsWatch = eFunctionStatus;
        this.lowSpo2hRemain = eFunctionStatus;
        this.skin = eFunctionStatus;
        this.autoHrv = eFunctionStatus;
        this.autoIncall = eFunctionStatus;
        this.disconnectRemind = eFunctionStatus;
        this.SOS = eFunctionStatus;
        this.ppg = eFunctionStatus;
        this.musicControl = eFunctionStatus;
        this.longClickLockScreen = eFunctionStatus;
        this.messageScreenLight = eFunctionStatus;
        this.autoTemperatureDetect = eFunctionStatus;
        this.temperatureUnit = ETemperatureUnit.NONE;
        this.ecgAlwaysOpen = eFunctionStatus;
        this.bloodGlucoseDetection = eFunctionStatus;
        this.METDetect = eFunctionStatus;
        this.stressDetect = eFunctionStatus;
        this.bloodGlucoseUnit = EBloodGlucoseUnit.NONE;
        this.bloodComponentDetect = eFunctionStatus;
        this.uricAcidUnit = EUricAcidUnit.NONE;
        this.bloodFatUnit = EBloodFatUnit.NONE;
        this.fallDetection = eFunctionStatus;
        this.gsrUnit = EGsrUnit.NONE;
        this.skinLevel = 0;
        this.index = 0;
    }

    public ECustomStatus getStatus() {
        return this.status;
    }

    public void setStatus(ECustomStatus eCustomStatus) {
        this.status = eCustomStatus;
    }

    @Deprecated
    public boolean isHaveMetricSystem() {
        return this.isHaveMetricSystem;
    }

    @Deprecated
    public void setHaveMetricSystem(boolean z) {
        this.isHaveMetricSystem = z;
    }

    @Deprecated
    public boolean isMetricSystemValue() {
        return this.metricSystemValue;
    }

    @Deprecated
    public void setMetricSystemValue(boolean z) {
        this.metricSystemValue = z;
    }

    public boolean is24Hour() {
        return this.is24Hour;
    }

    public void setIs24Hour(boolean z) {
        this.is24Hour = z;
    }

    @Deprecated
    public boolean isOpenAutoHeartDetect() {
        return this.isOpenAutoHeartDetect;
    }

    @Deprecated
    public void setOpenAutoHeartDetect(boolean z) {
        this.isOpenAutoHeartDetect = z;
    }

    @Deprecated
    public boolean isOpenAutoBpDetect() {
        return this.isOpenAutoBpDetect;
    }

    @Deprecated
    public void setOpenAutoBpDetect(boolean z) {
        this.isOpenAutoBpDetect = z;
    }

    public EFunctionStatus getMetricSystem() {
        return this.metricSystem;
    }

    public void setMetricSystem(EFunctionStatus eFunctionStatus) {
        this.metricSystem = eFunctionStatus;
    }

    public EFunctionStatus getAutoHeartDetect() {
        return this.autoHeartDetect;
    }

    public void setAutoHeartDetect(EFunctionStatus eFunctionStatus) {
        this.autoHeartDetect = eFunctionStatus;
    }

    public EFunctionStatus getAutoBpDetect() {
        return this.autoBpDetect;
    }

    public void setAutoBpDetect(EFunctionStatus eFunctionStatus) {
        this.autoBpDetect = eFunctionStatus;
    }

    public EFunctionStatus getSportOverRemain() {
        return this.sportOverRemain;
    }

    public void setSportOverRemain(EFunctionStatus eFunctionStatus) {
        this.sportOverRemain = eFunctionStatus;
    }

    public EFunctionStatus getVoiceBpHeart() {
        return this.voiceBpHeart;
    }

    public void setVoiceBpHeart(EFunctionStatus eFunctionStatus) {
        this.voiceBpHeart = eFunctionStatus;
    }

    public EFunctionStatus getFindPhoneUi() {
        return this.findPhoneUi;
    }

    public void setFindPhoneUi(EFunctionStatus eFunctionStatus) {
        this.findPhoneUi = eFunctionStatus;
    }

    public EFunctionStatus getSecondsWatch() {
        return this.secondsWatch;
    }

    public void setSecondsWatch(EFunctionStatus eFunctionStatus) {
        this.secondsWatch = eFunctionStatus;
    }

    public EFunctionStatus getLowSpo2hRemain() {
        return this.lowSpo2hRemain;
    }

    public void setLowSpo2hRemain(EFunctionStatus eFunctionStatus) {
        this.lowSpo2hRemain = eFunctionStatus;
    }

    public EFunctionStatus getSkin() {
        return this.skin;
    }

    public void setSkin(EFunctionStatus eFunctionStatus) {
        this.skin = eFunctionStatus;
    }

    public int getSkinLevel() {
        return this.skinLevel;
    }

    public void setSkinLevel(int i) {
        this.skinLevel = i;
    }

    public boolean isIs24Hour() {
        return this.is24Hour;
    }

    public EFunctionStatus getAutoHrv() {
        return this.autoHrv;
    }

    public void setAutoHrv(EFunctionStatus eFunctionStatus) {
        this.autoHrv = eFunctionStatus;
    }

    public EFunctionStatus getAutoIncall() {
        return this.autoIncall;
    }

    public void setAutoIncall(EFunctionStatus eFunctionStatus) {
        this.autoIncall = eFunctionStatus;
    }

    public EFunctionStatus getDisconnectRemind() {
        return this.disconnectRemind;
    }

    public void setDisconnectRemind(EFunctionStatus eFunctionStatus) {
        this.disconnectRemind = eFunctionStatus;
    }

    public EFunctionStatus getSOS() {
        return this.SOS;
    }

    public void setSOS(EFunctionStatus eFunctionStatus) {
        this.SOS = eFunctionStatus;
    }

    public EFunctionStatus getPpg() {
        return this.ppg;
    }

    public void setPpg(EFunctionStatus eFunctionStatus) {
        this.ppg = eFunctionStatus;
    }

    public EFunctionStatus getMusicControl() {
        return this.musicControl;
    }

    public void setMusicControl(EFunctionStatus eFunctionStatus) {
        this.musicControl = eFunctionStatus;
    }

    public EFunctionStatus getLongClickLockScreen() {
        return this.longClickLockScreen;
    }

    public void setLongClickLockScreen(EFunctionStatus eFunctionStatus) {
        this.longClickLockScreen = eFunctionStatus;
    }

    public EFunctionStatus getMessageScreenLight() {
        return this.messageScreenLight;
    }

    public void setMessageScreenLight(EFunctionStatus eFunctionStatus) {
        this.messageScreenLight = eFunctionStatus;
    }

    public EFunctionStatus getAutoTemperatureDetect() {
        return this.autoTemperatureDetect;
    }

    public void setAutoTemperatureDetect(EFunctionStatus eFunctionStatus) {
        this.autoTemperatureDetect = eFunctionStatus;
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

    public EFunctionStatus getBloodGlucoseDetection() {
        return this.bloodGlucoseDetection;
    }

    public void setBloodGlucoseDetection(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseDetection = eFunctionStatus;
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

    public EBloodGlucoseUnit getBloodGlucoseUnit() {
        return this.bloodGlucoseUnit;
    }

    public void setBloodGlucoseUnit(EBloodGlucoseUnit eBloodGlucoseUnit) {
        this.bloodGlucoseUnit = eBloodGlucoseUnit;
    }

    public void setStressDetect(EFunctionStatus eFunctionStatus) {
        this.stressDetect = eFunctionStatus;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public EFunctionStatus getBloodComponentDetect() {
        return this.bloodComponentDetect;
    }

    public void setBloodComponentDetect(EFunctionStatus eFunctionStatus) {
        this.bloodComponentDetect = eFunctionStatus;
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

    public String toString() {
        return "CustomSettingData{status=" + this.status + ", 24小时制=" + this.is24Hour + ", 公制=" + this.metricSystem + ", 自动测量Heart Rate=" + this.autoHeartDetect + ", 自动测量Blood Pressure=" + this.autoBpDetect + ", 运动过量提醒=" + this.sportOverRemain + ", \nBlood Pressure/Heart Rate播报=" + this.voiceBpHeart + ", 控制查找手机UI=" + this.findPhoneUi + ", 秒表=" + this.secondsWatch + ", 低氧报警=" + this.lowSpo2hRemain + ", 肤色功能=" + this.skin + ", 肤色等级=" + this.skinLevel + ", \nisHaveMetricSystem=" + this.isHaveMetricSystem + ", metricSystemValue=" + this.metricSystemValue + ", isOpenAutoHeartDetect=" + this.isOpenAutoHeartDetect + ", isOpenAutoBpDetect=" + this.isOpenAutoBpDetect + ", 自动检测HRV=" + this.autoHrv + ", \n自动接听来电=" + this.autoIncall + ", 断连提醒=" + this.disconnectRemind + ", 求救=" + this.SOS + ", ppg=" + this.ppg + ", 音乐控制功能=" + this.musicControl + ", 长按锁屏功能=" + this.longClickLockScreen + ", \n消息亮屏功能=" + this.messageScreenLight + ", Body Temperature自动检测=" + this.autoTemperatureDetect + ", 温度单位=" + this.temperatureUnit + ", ECG常开=" + this.ecgAlwaysOpen + ", Blood Glucose监测=" + this.bloodGlucoseDetection + ", 梅脱=" + this.METDetect + ", 压力=" + this.stressDetect + ", Blood Glucose单位=" + this.bloodGlucoseUnit + ", 血液成分开关=" + this.bloodComponentDetect + ", 尿酸单位=" + this.uricAcidUnit + ", 血脂单位=" + this.bloodFatUnit + ", 跌倒检测=" + this.fallDetection + ", 皮电单位=" + this.gsrUnit + '}';
    }
}
