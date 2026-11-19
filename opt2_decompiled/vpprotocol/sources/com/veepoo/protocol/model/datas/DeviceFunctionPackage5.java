package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/DeviceFunctionPackage5.class */
public class DeviceFunctionPackage5 {
    private EFunctionStatus textImagePush;
    private EFunctionStatus GSR;
    private EFunctionStatus safetyProtection;
    private int screenType;
    private EFunctionStatus barometer;
    private EFunctionStatus touchICType;
    private EFunctionStatus newProductionTestProtocolTag;
    private EFunctionStatus pttControl;
    private EFunctionStatus sportStatus;
    private EFunctionStatus remindEvent;
    private EFunctionStatus emotion;
    private EFunctionStatus healthLight;
    private EFunctionStatus motor;
    private EFunctionStatus wearHand;
    private int screenTypeTag;
    private int barometerTag;
    private int touchICTypeTag;
    private int newProductionProtocolTag;
    private int sportsStatusFeatureTag;
    private int remindEventFeatureTag;
    private int emotionTag;
    private int healthLightTag;
    private int motorTag;
    private int wearHandTag;

    public DeviceFunctionPackage5() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.textImagePush = eFunctionStatus;
        this.GSR = eFunctionStatus;
        this.safetyProtection = eFunctionStatus;
        this.barometer = eFunctionStatus;
        this.touchICType = eFunctionStatus;
        this.newProductionTestProtocolTag = eFunctionStatus;
        this.pttControl = eFunctionStatus;
        this.sportStatus = eFunctionStatus;
        this.remindEvent = eFunctionStatus;
        this.emotion = eFunctionStatus;
        this.healthLight = eFunctionStatus;
        this.motor = eFunctionStatus;
        this.wearHand = eFunctionStatus;
    }

    public int getScreenTypeTag() {
        return this.screenTypeTag;
    }

    public void setScreenTypeTag(int i) {
        this.screenTypeTag = i;
    }

    public int getBarometerTag() {
        return this.barometerTag;
    }

    public void setBarometerTag(int i) {
        this.barometerTag = i;
    }

    public int getTouchICTypeTag() {
        return this.touchICTypeTag;
    }

    public void setTouchICTypeTag(int i) {
        this.touchICTypeTag = i;
    }

    public int getNewProductionProtocolTag() {
        return this.newProductionProtocolTag;
    }

    public void setNewProductionProtocolTag(int i) {
        this.newProductionProtocolTag = i;
    }

    public int getSportsStatusFeatureTag() {
        return this.sportsStatusFeatureTag;
    }

    public void setSportsStatusFeatureTag(int i) {
        this.sportsStatusFeatureTag = i;
    }

    public int getRemindEventFeatureTag() {
        return this.remindEventFeatureTag;
    }

    public void setRemindEventFeatureTag(int i) {
        this.remindEventFeatureTag = i;
    }

    public int getEmotionTag() {
        return this.emotionTag;
    }

    public void setEmotionTag(int i) {
        this.emotionTag = i;
    }

    public int getHealthLightTag() {
        return this.healthLightTag;
    }

    public void setHealthLightTag(int i) {
        this.healthLightTag = i;
    }

    public int getWearHandTag() {
        return this.wearHandTag;
    }

    public void setWearHandTag(int i) {
        this.wearHandTag = i;
    }

    public EFunctionStatus getMotor() {
        return this.motor;
    }

    public void setMotor(EFunctionStatus eFunctionStatus) {
        this.motor = eFunctionStatus;
    }

    public int getMotorTag() {
        return this.motorTag;
    }

    public void setMotorTag(int i) {
        this.motorTag = i;
    }

    public EFunctionStatus getWearHand() {
        return this.wearHand;
    }

    public void setWearHand(EFunctionStatus eFunctionStatus) {
        this.wearHand = eFunctionStatus;
    }

    public EFunctionStatus getTextImagePush() {
        return this.textImagePush;
    }

    public void setTextImagePush(EFunctionStatus eFunctionStatus) {
        this.textImagePush = eFunctionStatus;
    }

    public EFunctionStatus getGSR() {
        return this.GSR;
    }

    public void setGSR(EFunctionStatus eFunctionStatus) {
        this.GSR = eFunctionStatus;
    }

    public EFunctionStatus getSafetyProtection() {
        return this.safetyProtection;
    }

    public void setSafetyProtection(EFunctionStatus eFunctionStatus) {
        this.safetyProtection = eFunctionStatus;
    }

    public int getScreenType() {
        return this.screenType;
    }

    public void setScreenType(int i) {
        this.screenType = i;
    }

    public EFunctionStatus getBarometer() {
        return this.barometer;
    }

    public void setBarometer(EFunctionStatus eFunctionStatus) {
        this.barometer = eFunctionStatus;
    }

    public EFunctionStatus getTouchICType() {
        return this.touchICType;
    }

    public void setTouchICType(EFunctionStatus eFunctionStatus) {
        this.touchICType = eFunctionStatus;
    }

    public EFunctionStatus getNewProductionTestProtocolTag() {
        return this.newProductionTestProtocolTag;
    }

    public void setNewProductionTestProtocolTag(EFunctionStatus eFunctionStatus) {
        this.newProductionTestProtocolTag = eFunctionStatus;
    }

    public EFunctionStatus getPttControl() {
        return this.pttControl;
    }

    public void setPttControl(EFunctionStatus eFunctionStatus) {
        this.pttControl = eFunctionStatus;
    }

    public EFunctionStatus getSportStatus() {
        return this.sportStatus;
    }

    public void setSportStatus(EFunctionStatus eFunctionStatus) {
        this.sportStatus = eFunctionStatus;
    }

    public EFunctionStatus getRemindEvent() {
        return this.remindEvent;
    }

    public void setRemindEvent(EFunctionStatus eFunctionStatus) {
        this.remindEvent = eFunctionStatus;
    }

    public EFunctionStatus getEmotion() {
        return this.emotion;
    }

    public void setEmotion(EFunctionStatus eFunctionStatus) {
        this.emotion = eFunctionStatus;
    }

    public EFunctionStatus getHealthLight() {
        return this.healthLight;
    }

    public void setHealthLight(EFunctionStatus eFunctionStatus) {
        this.healthLight = eFunctionStatus;
    }

    public String toString() {
        return "设备功能第五包{图文推送功能=" + this.textImagePush + "皮电=" + this.GSR + "安全守护=" + this.safetyProtection + "屏幕类型=" + getScreenTypeDes() + "气压计=" + this.barometer + "触摸IC类型=" + this.touchICType + "新产测协议标识=" + this.newProductionTestProtocolTag + "PTT控制功能=" + this.pttControl + "运动状态功能=" + this.sportStatus + "提醒事件功能=" + this.remindEvent + "情绪功能=" + this.emotion + "健康燈=" + this.healthLight + "马达=" + this.motor + "左右手佩戴设置=" + this.wearHand + '}';
    }

    public String getScreenTypeDes() {
        switch (this.screenType) {
            case 1:
                return "无屏手环";
            case 2:
                return "戒指";
            case 3:
                return "方屏";
            case 4:
                return "圆屏";
            default:
                return "未知屏型";
        }
    }
}
