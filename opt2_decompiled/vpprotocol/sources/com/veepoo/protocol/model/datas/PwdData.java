package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EPwdStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PwdData.class */
public class PwdData {
    private EPwdStatus mStatus;
    private String pwd;
    private int deviceNumber;
    private String deviceVersion;
    private String deviceTestVersion;

    @Deprecated
    private boolean isHaveDrinkData;

    @Deprecated
    private EFunctionStatus isOpenNightTurnWriste;

    @Deprecated
    private EFunctionStatus findPhoneFunction;

    @Deprecated
    private EFunctionStatus wearDetectFunction;

    public EPwdStatus getmStatus() {
        return this.mStatus;
    }

    public void setmStatus(EPwdStatus ePwdStatus) {
        this.mStatus = ePwdStatus;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    public int getDeviceNumber() {
        return this.deviceNumber;
    }

    public void setDeviceNumber(int i) {
        this.deviceNumber = i;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public String getDeviceTestVersion() {
        return this.deviceTestVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public boolean isHaveDrinkData() {
        return this.isHaveDrinkData;
    }

    public void setHaveDrinkData(boolean z) {
        this.isHaveDrinkData = z;
    }

    public EFunctionStatus isOpenNightTurnWriste() {
        return this.isOpenNightTurnWriste;
    }

    public void setOpenNightTurnWriste(EFunctionStatus eFunctionStatus) {
        this.isOpenNightTurnWriste = eFunctionStatus;
    }

    public EFunctionStatus getFindPhoneFunction() {
        return this.findPhoneFunction;
    }

    public void setFindPhoneFunction(EFunctionStatus eFunctionStatus) {
        this.findPhoneFunction = eFunctionStatus;
    }

    public EFunctionStatus getWearDetectFunction() {
        return this.wearDetectFunction;
    }

    public void setWearDetectFunction(EFunctionStatus eFunctionStatus) {
        this.wearDetectFunction = eFunctionStatus;
    }

    public void setDeviceTestVersion(String str) {
        this.deviceTestVersion = str;
    }

    public String toString() {
        return "PwdData{mStatus=" + this.mStatus + ", 设备编号=" + this.deviceNumber + ", 设备版本='" + this.deviceVersion + "', 设备测试版本='" + this.deviceTestVersion + "', 是否有饮酒数据=" + this.isHaveDrinkData + ", 抬手亮屏=" + this.isOpenNightTurnWriste + ", \n设置防丢=" + this.findPhoneFunction + ",佩戴检测=" + this.wearDetectFunction + '}';
    }
}
