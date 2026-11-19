package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/DeviceFunctionPackage3.class */
public class DeviceFunctionPackage3 {
    private int bigDataTranType = 0;
    private int watchUiServerCount = 0;
    private int watchUiCustomCount = 0;
    private int temperatureType = 0;
    private int cpuType = 0;
    private int stressType = 0;
    private EFunctionStatus stressFunction;
    private int contactType;
    private EFunctionStatus contactFunction;
    private int musicStyle;
    private EFunctionStatus screenStyleFunction;
    private EFunctionStatus findDeviceByPhoneFunction;
    private EFunctionStatus agpsFunction;
    private EFunctionStatus bloodGlucose;
    private int bloodGlucoseTag;
    private EFunctionStatus bloodGlucoseAdjusting;
    private EFunctionStatus bloodGlucoseMultipleAdjusting;
    private EFunctionStatus bloodGlucoseRiskAssessment;
    private EFunctionStatus temperatureFunction;

    public DeviceFunctionPackage3() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.stressFunction = eFunctionStatus;
        this.contactType = 0;
        this.contactFunction = eFunctionStatus;
        this.musicStyle = 0;
        this.screenStyleFunction = eFunctionStatus;
        this.findDeviceByPhoneFunction = eFunctionStatus;
        this.agpsFunction = eFunctionStatus;
        this.bloodGlucose = eFunctionStatus;
        this.bloodGlucoseTag = 0;
        this.bloodGlucoseAdjusting = eFunctionStatus;
        this.bloodGlucoseMultipleAdjusting = eFunctionStatus;
        this.bloodGlucoseRiskAssessment = eFunctionStatus;
        this.temperatureFunction = eFunctionStatus;
    }

    public int getBigDataTranType() {
        return this.bigDataTranType;
    }

    public void setBigDataTranType(int i) {
        this.bigDataTranType = i;
    }

    public int getWatchUiServerCount() {
        return this.watchUiServerCount;
    }

    public void setWatchUiServerCount(int i) {
        this.watchUiServerCount = i;
    }

    public int getWatchUiCustomCount() {
        return this.watchUiCustomCount;
    }

    public void setWatchUiCustomCount(int i) {
        this.watchUiCustomCount = i;
    }

    public int getTemperatureType() {
        return this.temperatureType;
    }

    public void setTemperatureType(int i) {
        this.temperatureType = i;
    }

    public int getCpuType() {
        return this.cpuType;
    }

    public void setCpuType(int i) {
        this.cpuType = i;
    }

    public int getStressType() {
        return this.stressType;
    }

    public void setStressType(int i) {
        this.stressType = i;
    }

    public int getContactType() {
        return this.contactType;
    }

    public void setContactType(int i) {
        this.contactType = i;
    }

    public EFunctionStatus getContactFunction() {
        return this.contactFunction;
    }

    public void setContactFunction(EFunctionStatus eFunctionStatus) {
        this.contactFunction = eFunctionStatus;
    }

    public int getMusicStyle() {
        return this.musicStyle;
    }

    public void setMusicStyle(int i) {
        this.musicStyle = i;
    }

    public EFunctionStatus getStressFunction() {
        return this.stressFunction;
    }

    public void setStressFunction(EFunctionStatus eFunctionStatus) {
        this.stressFunction = eFunctionStatus;
    }

    public EFunctionStatus getScreenStyleFunction() {
        return this.screenStyleFunction;
    }

    public void setScreenStyleFunction(EFunctionStatus eFunctionStatus) {
        this.screenStyleFunction = eFunctionStatus;
    }

    public EFunctionStatus getFindDeviceByPhoneFunction() {
        return this.findDeviceByPhoneFunction;
    }

    public void setFindDeviceByPhoneFunction(EFunctionStatus eFunctionStatus) {
        this.findDeviceByPhoneFunction = eFunctionStatus;
    }

    public EFunctionStatus getAgpsFunction() {
        return this.agpsFunction;
    }

    public void setAgpsFunction(EFunctionStatus eFunctionStatus) {
        this.agpsFunction = eFunctionStatus;
    }

    public EFunctionStatus getBloodGlucose() {
        return this.bloodGlucose;
    }

    public void setBloodGlucose(EFunctionStatus eFunctionStatus) {
        this.bloodGlucose = eFunctionStatus;
    }

    public int getBloodGlucoseTag() {
        return this.bloodGlucoseTag;
    }

    public void setBloodGlucoseTag(int i) {
        this.bloodGlucoseTag = i;
    }

    public EFunctionStatus getBloodGlucoseAdjusting() {
        return this.bloodGlucoseAdjusting;
    }

    public void setBloodGlucoseAdjusting(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseAdjusting = eFunctionStatus;
    }

    public EFunctionStatus getBloodGlucoseMultipleAdjusting() {
        return this.bloodGlucoseMultipleAdjusting;
    }

    public void setBloodGlucoseMultipleAdjusting(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseMultipleAdjusting = eFunctionStatus;
    }

    public EFunctionStatus getBloodGlucoseRiskAssessment() {
        return this.bloodGlucoseRiskAssessment;
    }

    public void setBloodGlucoseRiskAssessment(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseRiskAssessment = eFunctionStatus;
    }

    public EFunctionStatus getTemperatureFunction() {
        return this.temperatureFunction;
    }

    public void setTemperatureFunction(EFunctionStatus eFunctionStatus) {
        this.temperatureFunction = eFunctionStatus;
    }

    public String toString() {
        return "设备功能第三包{大块传输类型=" + this.bigDataTranType + ", 表盘市场的个数=" + this.watchUiServerCount + ", 自定义表盘的个数=" + this.watchUiCustomCount + ", Body Temperature功能=" + this.temperatureFunction + ", Body Temperature类型=" + this.temperatureType + ", CPU类型=" + this.cpuType + ", 压力功能=" + this.stressFunction + ", 压力类型=" + this.stressType + ", 联系人功能=" + this.contactFunction + ", 联系人类型=" + this.contactType + ", 音乐类型=" + this.musicStyle + ", 屏幕样式功能=" + this.screenStyleFunction + ", 手机查找设备功能=" + this.findDeviceByPhoneFunction + ", 星历功能=" + this.agpsFunction + ", Blood Glucose标志位=" + this.bloodGlucoseTag + ", Blood Glucose功能=" + this.bloodGlucose + ", Blood GlucoseCalibration功能=" + this.bloodGlucoseAdjusting + ", Blood Glucose多Calibration功能=" + this.bloodGlucoseMultipleAdjusting + ", Blood Glucose分析评估功能=" + this.bloodGlucoseRiskAssessment + '}';
    }
}
