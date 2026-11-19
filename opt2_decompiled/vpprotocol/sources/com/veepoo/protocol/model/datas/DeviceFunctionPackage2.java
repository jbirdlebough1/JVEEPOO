package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/DeviceFunctionPackage2.class */
public class DeviceFunctionPackage2 {
    private EFunctionStatus countDown;
    private int originProtocolVersion;
    private int contactMsgLength;
    private int allMsgLength;
    private int watchDataDayNumber;
    private EFunctionStatus hidFunction;
    private int screenStyle;
    private EFunctionStatus breathFunction;
    private EFunctionStatus weatherFunction;
    private int weatherStyle;
    private EFunctionStatus hrvFunction;
    private EFunctionStatus hrvAppDetectFunction;
    private int hrvType;
    private EFunctionStatus sportModelFunction;
    private int sportModelDay;
    private EFunctionStatus screenLightTime;
    private EFunctionStatus ecgFunction;
    private int ecgType;
    private int sleepTag;
    private EFunctionStatus precisionSleepFunction;
    private EFunctionStatus multSportMode;
    private int multSportModeType;
    private EFunctionStatus lowPower;

    public DeviceFunctionPackage2() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.countDown = eFunctionStatus;
        this.originProtocolVersion = 0;
        this.contactMsgLength = 0;
        this.allMsgLength = 4;
        this.watchDataDayNumber = 0;
        this.hidFunction = eFunctionStatus;
        this.screenStyle = 0;
        this.breathFunction = eFunctionStatus;
        this.weatherFunction = eFunctionStatus;
        this.weatherStyle = 0;
        this.hrvFunction = eFunctionStatus;
        this.hrvAppDetectFunction = eFunctionStatus;
        this.hrvType = 0;
        this.sportModelFunction = eFunctionStatus;
        this.sportModelDay = 0;
        this.screenLightTime = eFunctionStatus;
        this.ecgFunction = eFunctionStatus;
        this.ecgType = 0;
        this.sleepTag = 0;
        this.precisionSleepFunction = eFunctionStatus;
        this.multSportMode = eFunctionStatus;
        this.multSportModeType = 0;
        this.lowPower = eFunctionStatus;
    }

    public EFunctionStatus getCountDown() {
        return this.countDown;
    }

    public void setCountDown(EFunctionStatus eFunctionStatus) {
        this.countDown = eFunctionStatus;
    }

    public int getOriginProtocolVersion() {
        return this.originProtocolVersion;
    }

    public void setOriginProtocolVersion(int i) {
        this.originProtocolVersion = i;
    }

    public int getContactMsgLength() {
        return this.contactMsgLength;
    }

    public void setContactMsgLength(int i) {
        this.contactMsgLength = i;
    }

    public int getWatchDataDayNumber() {
        return this.watchDataDayNumber;
    }

    public void setWatchDataDayNumber(int i) {
        this.watchDataDayNumber = i;
    }

    public EFunctionStatus getHidFunction() {
        return this.hidFunction;
    }

    public void setHidFunction(EFunctionStatus eFunctionStatus) {
        this.hidFunction = eFunctionStatus;
    }

    public int getScreenStyle() {
        return this.screenStyle;
    }

    public void setScreenStyle(int i) {
        this.screenStyle = i;
    }

    public EFunctionStatus getBreathFunction() {
        return this.breathFunction;
    }

    public void setBreathFunction(EFunctionStatus eFunctionStatus) {
        this.breathFunction = eFunctionStatus;
    }

    public EFunctionStatus getWeatherFunction() {
        return this.weatherFunction;
    }

    public void setWeatherFunction(EFunctionStatus eFunctionStatus) {
        this.weatherFunction = eFunctionStatus;
    }

    public int getWeatherStyle() {
        return this.weatherStyle;
    }

    public void setWeatherStyle(int i) {
        this.weatherStyle = i;
    }

    public EFunctionStatus getHrvFunction() {
        return this.hrvFunction;
    }

    public void setHrvFunction(EFunctionStatus eFunctionStatus) {
        this.hrvFunction = eFunctionStatus;
    }

    public EFunctionStatus getHrvAppDetectFunction() {
        return this.hrvAppDetectFunction;
    }

    public void setHrvAppDetectFunction(EFunctionStatus eFunctionStatus) {
        this.hrvAppDetectFunction = eFunctionStatus;
    }

    public int getHrvType() {
        return this.hrvType;
    }

    public void setHrvType(int i) {
        this.hrvType = i;
    }

    public EFunctionStatus getSportModelFunction() {
        return this.sportModelFunction;
    }

    public void setSportModelFunction(EFunctionStatus eFunctionStatus) {
        this.sportModelFunction = eFunctionStatus;
    }

    public int getSportModelDay() {
        return this.sportModelDay;
    }

    public void setSportModelDay(int i) {
        this.sportModelDay = i;
    }

    public EFunctionStatus getScreenLightTime() {
        return this.screenLightTime;
    }

    public void setScreenLightTime(EFunctionStatus eFunctionStatus) {
        this.screenLightTime = eFunctionStatus;
    }

    public EFunctionStatus getEcgFunction() {
        return this.ecgFunction;
    }

    public void setEcgFunction(EFunctionStatus eFunctionStatus) {
        this.ecgFunction = eFunctionStatus;
    }

    public int getEcgType() {
        return this.ecgType;
    }

    public void setEcgType(int i) {
        this.ecgType = i;
    }

    public int getSleepTag() {
        return this.sleepTag;
    }

    public void setSleepTag(int i) {
        this.sleepTag = i;
    }

    public EFunctionStatus getPrecisionSleepFunction() {
        return this.precisionSleepFunction;
    }

    public void setPrecisionSleepFunction(EFunctionStatus eFunctionStatus) {
        this.precisionSleepFunction = eFunctionStatus;
    }

    public EFunctionStatus getMultSportMode() {
        return this.multSportMode;
    }

    public void setMultSportMode(EFunctionStatus eFunctionStatus) {
        this.multSportMode = eFunctionStatus;
    }

    public int getMultSportModeType() {
        return this.multSportModeType;
    }

    public void setMultSportModeType(int i) {
        this.multSportModeType = i;
    }

    public EFunctionStatus getLowPower() {
        return this.lowPower;
    }

    public void setLowPower(EFunctionStatus eFunctionStatus) {
        this.lowPower = eFunctionStatus;
    }

    public int getAllMsgLength() {
        return this.allMsgLength;
    }

    public void setAllMsgLength(int i) {
        this.allMsgLength = i;
    }

    public String toString() {
        return "设备功能第二包{倒计时功能=" + this.countDown + ", 协议版本=" + this.originProtocolVersion + ", 信息推送第一部分的长度=" + this.contactMsgLength + ", 手表保存的最大天数=" + this.watchDataDayNumber + ", HID功能=" + this.hidFunction + ", 屏幕样式的选择=" + this.screenStyle + ", 呼吸率功能=" + this.breathFunction + ", 天气功能=" + this.weatherFunction + ", 天气类型=" + this.weatherStyle + ", HRV功能=" + this.hrvFunction + ", HRV类型=" + this.hrvType + ", 运动模式=" + this.sportModelFunction + ", 运动模式数据保存的最大天数=" + this.sportModelDay + ", 亮屏时长功能=" + this.screenLightTime + ", ECG功能=" + this.ecgFunction + ", ECG类型=" + this.ecgType + ", Sleep标志位=" + this.sleepTag + ", 精准Sleep=" + this.precisionSleepFunction + ", 多运动功能=" + this.multSportMode + ", 多运动类型=" + this.multSportModeType + ", 低功耗=" + this.lowPower + '}';
    }
}
