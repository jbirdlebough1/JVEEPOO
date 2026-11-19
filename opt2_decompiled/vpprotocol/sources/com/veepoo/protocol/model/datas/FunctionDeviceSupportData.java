package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/FunctionDeviceSupportData.class */
public class FunctionDeviceSupportData {
    private EFunctionStatus Bp;
    private int bpTag;
    private EFunctionStatus Drink;
    private EFunctionStatus Longseat;
    private EFunctionStatus healthRemind;
    private EFunctionStatus HeartWaring;
    private EFunctionStatus WeChatSport;
    private EFunctionStatus Camera;
    private EFunctionStatus Fatigue;
    private EFunctionStatus Spo2H;
    private EFunctionStatus Spo2HAdjuster;
    private EFunctionStatus Spo2HBreathBreak;
    private EFunctionStatus Spo2HValueZero;
    private EFunctionStatus Spo2hShow;
    private EFunctionStatus Women;
    private int alarmTag;
    private EFunctionStatus Alarm2;
    private EFunctionStatus newCalcSport;
    private EFunctionStatus CountDown;
    private EFunctionStatus AngioAdjuster;
    private EFunctionStatus ScreenLight;
    private EFunctionStatus heartDetect;
    private int rateTag;
    private EFunctionStatus SportModel;
    private EFunctionStatus NightTurnSetting;
    private EFunctionStatus hidFuction;
    private EFunctionStatus screenStyleFunction;
    private EFunctionStatus beathFunction;
    private EFunctionStatus hrvFunction;
    private EFunctionStatus hrvAppDetectFunction;
    private int hrvType;
    private int skinType;
    private int womenTag;
    private EFunctionStatus allDayHrvFunc;
    private EFunctionStatus weatherFunction;
    private EFunctionStatus screenLightTime;
    private EFunctionStatus precisionSleep;
    private EFunctionStatus resetData;
    private EFunctionStatus ecg;
    private EFunctionStatus multSportModel;
    private EFunctionStatus lowPower;
    private EFunctionStatus findDeviceByPhone;
    private EFunctionStatus agps;
    private EFunctionStatus temperatureFunction;
    private EFunctionStatus contactFunction;
    private EFunctionStatus textAlarm;
    private EFunctionStatus bloodGlucose;
    private int bloodGlucoseTag;
    private EFunctionStatus bloodGlucoseAdjusting;
    private EFunctionStatus bloodGlucoseMultipleAdjusting;
    private EFunctionStatus bloodGlucoseRiskAssessment;
    private int sleepTag;
    private int musicStyle;
    private int contactType;
    private int WathcDay;
    private int contactMsgLength;
    private int allMsgLength;
    private int sportmodelday;
    private int screenstyle;
    private int weatherStyle;
    private int originProtcolVersion;
    private int bigDataTranType;
    private int watchUiServerCount;
    private int watchUiCoustomCount;
    private int temptureType;
    private int cpuType;
    private int stressType;
    private int metType;
    private int ecgType;
    private int sportModelType;
    private int spo2hType;
    private EFunctionStatus bloodComponent;
    private int bloodComponentTag;
    private EFunctionStatus bloodComponentSingleCalibration;
    private EFunctionStatus bodyComponent;
    private EFunctionStatus worldClock;
    private EFunctionStatus autoMeasure;
    private EFunctionStatus daSport;
    private EFunctionStatus stress;
    private EFunctionStatus met;
    private EFunctionStatus temperatureAlarm;
    private EFunctionStatus wallet;
    private EFunctionStatus postcard;
    private EFunctionStatus gameSetting;
    private EFunctionStatus aiQA;
    private EFunctionStatus aiDial;
    private EFunctionStatus distanceCalorieGoal;
    private EFunctionStatus videoDial;
    private EFunctionStatus photoAlbum;
    private EFunctionStatus textImagePush;
    private EFunctionStatus server4g;
    private EFunctionStatus GSR;
    private int gsrTag;
    private EFunctionStatus safetyProtection;
    private int screenTypeTag;
    private int fatigueTag;
    private int barometerTag;
    private int touchICTypeTag;
    private int newProductionProtocolTag;
    private int sportsStatusFeatureTag;
    private int remindEventFeatureTag;
    private int emotionTag;
    private int healthLightTag;
    private int wearHandTag;
    private EFunctionStatus wearHand;
    private EFunctionStatus miniCheckup;
    private EFunctionStatus healthAssessment;
    private EFunctionStatus bloodGlucoseHealthAssessment;
    private EFunctionStatus bloodComponentHealthAssessment;
    private EFunctionStatus bodyComponentHealthAssessment;
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
    private int motorTag;
    private int miniCheckupTag;
    private int aiQaTag;
    private int healthAssessmentTag;
    private int aiDialTag;
    private int pttControlTag;

    public FunctionDeviceSupportData() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.Bp = eFunctionStatus;
        this.bpTag = 0;
        this.Drink = eFunctionStatus;
        this.Longseat = eFunctionStatus;
        this.healthRemind = eFunctionStatus;
        this.HeartWaring = eFunctionStatus;
        this.WeChatSport = eFunctionStatus;
        this.Camera = eFunctionStatus;
        this.Fatigue = eFunctionStatus;
        this.Spo2H = eFunctionStatus;
        this.Spo2HAdjuster = eFunctionStatus;
        this.Spo2HBreathBreak = eFunctionStatus;
        this.Spo2HValueZero = eFunctionStatus;
        this.Spo2hShow = eFunctionStatus;
        this.Women = eFunctionStatus;
        this.alarmTag = 0;
        this.Alarm2 = eFunctionStatus;
        this.newCalcSport = eFunctionStatus;
        this.CountDown = eFunctionStatus;
        this.AngioAdjuster = eFunctionStatus;
        this.ScreenLight = eFunctionStatus;
        this.heartDetect = EFunctionStatus.SUPPORT;
        this.rateTag = 1;
        this.SportModel = eFunctionStatus;
        this.NightTurnSetting = eFunctionStatus;
        this.hidFuction = eFunctionStatus;
        this.screenStyleFunction = eFunctionStatus;
        this.beathFunction = eFunctionStatus;
        this.hrvFunction = eFunctionStatus;
        this.hrvAppDetectFunction = eFunctionStatus;
        this.hrvType = 0;
        this.skinType = 0;
        this.womenTag = 0;
        this.allDayHrvFunc = eFunctionStatus;
        this.weatherFunction = eFunctionStatus;
        this.screenLightTime = eFunctionStatus;
        this.precisionSleep = eFunctionStatus;
        this.resetData = eFunctionStatus;
        this.ecg = eFunctionStatus;
        this.multSportModel = eFunctionStatus;
        this.lowPower = eFunctionStatus;
        this.findDeviceByPhone = eFunctionStatus;
        this.agps = eFunctionStatus;
        this.temperatureFunction = eFunctionStatus;
        this.contactFunction = eFunctionStatus;
        this.textAlarm = eFunctionStatus;
        this.bloodGlucose = eFunctionStatus;
        this.bloodGlucoseTag = 0;
        this.bloodGlucoseAdjusting = eFunctionStatus;
        this.bloodGlucoseMultipleAdjusting = eFunctionStatus;
        this.bloodGlucoseRiskAssessment = eFunctionStatus;
        this.sleepTag = 0;
        this.musicStyle = 0;
        this.contactType = 0;
        this.WathcDay = 0;
        this.contactMsgLength = 0;
        this.allMsgLength = 4;
        this.sportmodelday = 0;
        this.screenstyle = 0;
        this.weatherStyle = 0;
        this.originProtcolVersion = 0;
        this.bigDataTranType = 0;
        this.watchUiServerCount = 0;
        this.watchUiCoustomCount = 0;
        this.temptureType = 0;
        this.cpuType = 0;
        this.stressType = 0;
        this.metType = 0;
        this.ecgType = 0;
        this.sportModelType = 0;
        this.spo2hType = 0;
        this.bloodComponent = eFunctionStatus;
        this.bloodComponentTag = 0;
        this.bloodComponentSingleCalibration = eFunctionStatus;
        this.bodyComponent = eFunctionStatus;
        this.worldClock = eFunctionStatus;
        this.autoMeasure = eFunctionStatus;
        this.daSport = eFunctionStatus;
        this.stress = eFunctionStatus;
        this.met = eFunctionStatus;
        this.temperatureAlarm = eFunctionStatus;
        this.wallet = eFunctionStatus;
        this.postcard = eFunctionStatus;
        this.gameSetting = eFunctionStatus;
        this.aiQA = eFunctionStatus;
        this.aiDial = eFunctionStatus;
        this.distanceCalorieGoal = eFunctionStatus;
        this.videoDial = eFunctionStatus;
        this.photoAlbum = eFunctionStatus;
        this.textImagePush = eFunctionStatus;
        this.server4g = eFunctionStatus;
        this.GSR = eFunctionStatus;
        this.gsrTag = 0;
        this.safetyProtection = eFunctionStatus;
        this.wearHand = eFunctionStatus;
        this.miniCheckup = eFunctionStatus;
        this.healthAssessment = eFunctionStatus;
        this.bloodGlucoseHealthAssessment = eFunctionStatus;
        this.bloodComponentHealthAssessment = eFunctionStatus;
        this.bodyComponentHealthAssessment = eFunctionStatus;
        this.barometer = eFunctionStatus;
        this.touchICType = eFunctionStatus;
        this.newProductionTestProtocolTag = eFunctionStatus;
        this.pttControl = eFunctionStatus;
        this.sportStatus = eFunctionStatus;
        this.remindEvent = eFunctionStatus;
        this.emotion = eFunctionStatus;
        this.healthLight = eFunctionStatus;
        this.motor = eFunctionStatus;
        this.miniCheckupTag = 0;
        this.aiQaTag = 0;
        this.healthAssessmentTag = 0;
        this.aiDialTag = 0;
        this.pttControlTag = 0;
    }

    public int getTemptureType() {
        return this.temptureType;
    }

    public void setTemptureType(int i) {
        this.temptureType = i;
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

    public int getWatchUiCoustomCount() {
        return this.watchUiCoustomCount;
    }

    public void setWatchUiCoustomCount(int i) {
        this.watchUiCoustomCount = i;
    }

    public int getContactMsgLength() {
        return this.contactMsgLength;
    }

    public void setContactMsgLength(int i) {
        this.contactMsgLength = i;
    }

    public int getAllMsgLength() {
        return this.allMsgLength;
    }

    public void setAllMsgLength(int i) {
        this.allMsgLength = i;
    }

    public EFunctionStatus getHeartDetect() {
        return this.heartDetect;
    }

    public void setHeartDetect(EFunctionStatus eFunctionStatus) {
        this.heartDetect = eFunctionStatus;
    }

    public EFunctionStatus getAngioAdjuster() {
        return this.AngioAdjuster;
    }

    public void setAngioAdjuster(EFunctionStatus eFunctionStatus) {
        this.AngioAdjuster = eFunctionStatus;
    }

    public EFunctionStatus getAlarm2() {
        return this.Alarm2;
    }

    public void setAlarm2(EFunctionStatus eFunctionStatus) {
        this.Alarm2 = eFunctionStatus;
    }

    public EFunctionStatus getTextAlarm() {
        return this.textAlarm;
    }

    public void setTextAlarm(EFunctionStatus eFunctionStatus) {
        this.textAlarm = eFunctionStatus;
    }

    public int getWathcDay() {
        if (this.WathcDay == 0) {
            this.WathcDay = 3;
        }
        return this.WathcDay;
    }

    public void setWathcDay(int i) {
        if (i == 0) {
            i = 3;
        }
        this.WathcDay = i;
    }

    public EFunctionStatus getCountDown() {
        return this.CountDown;
    }

    public void setCountDown(EFunctionStatus eFunctionStatus) {
        this.CountDown = eFunctionStatus;
    }

    public EFunctionStatus getBp() {
        return this.Bp;
    }

    public void setBp(EFunctionStatus eFunctionStatus) {
        this.Bp = eFunctionStatus;
    }

    public EFunctionStatus getDrink() {
        return this.Drink;
    }

    public void setDrink(EFunctionStatus eFunctionStatus) {
        this.Drink = eFunctionStatus;
    }

    public EFunctionStatus getLongseat() {
        return this.Longseat;
    }

    public void setLongseat(EFunctionStatus eFunctionStatus) {
        this.Longseat = eFunctionStatus;
    }

    public EFunctionStatus getHeartWaring() {
        return this.HeartWaring;
    }

    public void setHeartWaring(EFunctionStatus eFunctionStatus) {
        this.HeartWaring = eFunctionStatus;
    }

    public EFunctionStatus getWeChatSport() {
        return this.WeChatSport;
    }

    public void setWeChatSport(EFunctionStatus eFunctionStatus) {
        this.WeChatSport = eFunctionStatus;
    }

    public EFunctionStatus getCamera() {
        return this.Camera;
    }

    public void setCamera(EFunctionStatus eFunctionStatus) {
        this.Camera = eFunctionStatus;
    }

    public EFunctionStatus getFatigue() {
        return this.Fatigue;
    }

    public void setFatigue(EFunctionStatus eFunctionStatus) {
        this.Fatigue = eFunctionStatus;
    }

    public EFunctionStatus getSpo2H() {
        return this.Spo2H;
    }

    public void setSpo2H(EFunctionStatus eFunctionStatus) {
        this.Spo2H = eFunctionStatus;
    }

    public EFunctionStatus getSpo2HAdjuster() {
        return this.Spo2HAdjuster;
    }

    public void setSpo2HAdjuster(EFunctionStatus eFunctionStatus) {
        this.Spo2HAdjuster = eFunctionStatus;
    }

    public EFunctionStatus getWomen() {
        return this.Women;
    }

    public void setWomen(EFunctionStatus eFunctionStatus) {
        this.Women = eFunctionStatus;
    }

    public EFunctionStatus getNewCalcSport() {
        return this.newCalcSport;
    }

    public void setNewCalcSport(EFunctionStatus eFunctionStatus) {
        this.newCalcSport = eFunctionStatus;
    }

    public EFunctionStatus getScreenLight() {
        return this.ScreenLight;
    }

    public void setScreenLight(EFunctionStatus eFunctionStatus) {
        this.ScreenLight = eFunctionStatus;
    }

    public EFunctionStatus getSportModel() {
        return this.SportModel;
    }

    public void setSportModel(EFunctionStatus eFunctionStatus) {
        this.SportModel = eFunctionStatus;
    }

    public int getSportModelType() {
        return this.sportModelType;
    }

    public void setSportModelType(int i) {
        this.sportModelType = i;
    }

    public int getSportmodelday() {
        return this.sportmodelday;
    }

    public void setSportmodelday(int i) {
        this.sportmodelday = i;
    }

    public EFunctionStatus getNightTurnSetting() {
        return this.NightTurnSetting;
    }

    public void setNightTurnSetting(EFunctionStatus eFunctionStatus) {
        this.NightTurnSetting = eFunctionStatus;
    }

    public EFunctionStatus getHidFuction() {
        return this.hidFuction;
    }

    public void setHidFuction(EFunctionStatus eFunctionStatus) {
        this.hidFuction = eFunctionStatus;
    }

    public EFunctionStatus getScreenStyleFunction() {
        return this.screenStyleFunction;
    }

    public void setScreenStyleFunction(EFunctionStatus eFunctionStatus) {
        this.screenStyleFunction = eFunctionStatus;
    }

    public int getScreenstyle() {
        return this.screenstyle;
    }

    public void setScreenstyle(int i) {
        this.screenstyle = i;
    }

    public EFunctionStatus getBeathFunction() {
        return this.beathFunction;
    }

    public void setBeathFunction(EFunctionStatus eFunctionStatus) {
        this.beathFunction = eFunctionStatus;
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

    public EFunctionStatus getAllDayHrvFunc() {
        return this.allDayHrvFunc;
    }

    public void setAllDayHrvFunc(EFunctionStatus eFunctionStatus) {
        this.allDayHrvFunc = eFunctionStatus;
    }

    public EFunctionStatus getSpo2HBreathBreak() {
        return this.Spo2HBreathBreak;
    }

    public void setSpo2HBreathBreak(EFunctionStatus eFunctionStatus) {
        this.Spo2HBreathBreak = eFunctionStatus;
    }

    public EFunctionStatus getScreenLightTime() {
        return this.screenLightTime;
    }

    public void setScreenLightTime(EFunctionStatus eFunctionStatus) {
        this.screenLightTime = eFunctionStatus;
    }

    public EFunctionStatus getEcg() {
        return this.ecg;
    }

    public void setEcg(EFunctionStatus eFunctionStatus) {
        this.ecg = eFunctionStatus;
    }

    public int getEcgType() {
        return this.ecgType;
    }

    public void setEcgType(int i) {
        this.ecgType = i;
    }

    public EFunctionStatus getPrecisionSleep() {
        return this.precisionSleep;
    }

    public void setPrecisionSleep(EFunctionStatus eFunctionStatus) {
        this.precisionSleep = eFunctionStatus;
    }

    public int getOriginProtcolVersion() {
        return this.originProtcolVersion;
    }

    public void setOriginProtcolVersion(int i) {
        this.originProtcolVersion = i;
    }

    public EFunctionStatus getMultSportModel() {
        return this.multSportModel;
    }

    public void setMultSportModel(EFunctionStatus eFunctionStatus) {
        this.multSportModel = eFunctionStatus;
    }

    public EFunctionStatus getLowPower() {
        return this.lowPower;
    }

    public void setLowPower(EFunctionStatus eFunctionStatus) {
        this.lowPower = eFunctionStatus;
    }

    public EFunctionStatus getWeatherFunction() {
        return this.weatherFunction;
    }

    public void setWeatherFunction(EFunctionStatus eFunctionStatus) {
        this.weatherFunction = eFunctionStatus;
    }

    public EFunctionStatus getFindDeviceByPhone() {
        return this.findDeviceByPhone;
    }

    public void setFindDeviceByPhone(EFunctionStatus eFunctionStatus) {
        this.findDeviceByPhone = eFunctionStatus;
    }

    public EFunctionStatus getAgps() {
        return this.agps;
    }

    public void setAgps(EFunctionStatus eFunctionStatus) {
        this.agps = eFunctionStatus;
    }

    public int getWeatherStyle() {
        return this.weatherStyle;
    }

    public void setWeatherStyle(int i) {
        this.weatherStyle = i;
    }

    public int getMusicStyle() {
        return this.musicStyle;
    }

    public void setMusicStyle(int i) {
        this.musicStyle = i;
    }

    public EFunctionStatus getTemperatureFunction() {
        return this.temperatureFunction;
    }

    public void setTemperatureFunction(EFunctionStatus eFunctionStatus) {
        this.temperatureFunction = eFunctionStatus;
    }

    public int getCpuType() {
        return this.cpuType;
    }

    public void setCpuType(int i) {
        this.cpuType = i;
    }

    public EFunctionStatus getBloodGlucose() {
        return this.bloodGlucose;
    }

    public void setBloodGlucose(EFunctionStatus eFunctionStatus) {
        this.bloodGlucose = eFunctionStatus;
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

    public EFunctionStatus getResetData() {
        return this.resetData;
    }

    public void setResetData(EFunctionStatus eFunctionStatus) {
        this.resetData = eFunctionStatus;
    }

    public int getSleepTag() {
        return this.sleepTag;
    }

    public void setSleepTag(int i) {
        this.sleepTag = i;
    }

    public EFunctionStatus getContactFunction() {
        return this.contactFunction;
    }

    public void setContactFunction(EFunctionStatus eFunctionStatus) {
        this.contactFunction = eFunctionStatus;
    }

    public int getContactType() {
        return this.contactType;
    }

    public void setContactType(int i) {
        this.contactType = i;
    }

    public EFunctionStatus getBloodComponent() {
        return this.bloodComponent;
    }

    public void setBloodComponent(EFunctionStatus eFunctionStatus) {
        this.bloodComponent = eFunctionStatus;
    }

    public EFunctionStatus getBloodComponentSingleCalibration() {
        return this.bloodComponentSingleCalibration;
    }

    public void setBloodComponentSingleCalibration(EFunctionStatus eFunctionStatus) {
        this.bloodComponentSingleCalibration = eFunctionStatus;
    }

    public EFunctionStatus getBodyComponent() {
        return this.bodyComponent;
    }

    public void setBodyComponent(EFunctionStatus eFunctionStatus) {
        this.bodyComponent = eFunctionStatus;
    }

    public int getSpo2hType() {
        return this.spo2hType;
    }

    public void setSpo2hType(int i) {
        this.spo2hType = i;
    }

    public EFunctionStatus getSpo2HValueZero() {
        return this.Spo2HValueZero;
    }

    public void setSpo2HValueZero(EFunctionStatus eFunctionStatus) {
        this.Spo2HValueZero = eFunctionStatus;
    }

    public EFunctionStatus getSpo2hShow() {
        return this.Spo2hShow;
    }

    public void setSpo2hShow(EFunctionStatus eFunctionStatus) {
        this.Spo2hShow = eFunctionStatus;
    }

    public EFunctionStatus getHealthRemind() {
        return this.healthRemind;
    }

    public void setHealthRemind(EFunctionStatus eFunctionStatus) {
        this.healthRemind = eFunctionStatus;
    }

    public EFunctionStatus getWorldClock() {
        return this.worldClock;
    }

    public void setWorldClock(EFunctionStatus eFunctionStatus) {
        this.worldClock = eFunctionStatus;
    }

    public EFunctionStatus getBloodGlucoseRiskAssessment() {
        return this.bloodGlucoseRiskAssessment;
    }

    public void setBloodGlucoseRiskAssessment(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseRiskAssessment = eFunctionStatus;
    }

    public int getBloodGlucoseTag() {
        return this.bloodGlucoseTag;
    }

    public void setBloodGlucoseTag(int i) {
        this.bloodGlucoseTag = i;
    }

    public int getWomenTag() {
        return this.womenTag;
    }

    public void setWomenTag(int i) {
        this.womenTag = i;
    }

    public int getRateTag() {
        return this.rateTag;
    }

    public void setRateTag(int i) {
        this.rateTag = i;
    }

    public int getBloodComponentTag() {
        return this.bloodComponentTag;
    }

    public void setBloodComponentTag(int i) {
        this.bloodComponentTag = i;
    }

    public int getSkinType() {
        return this.skinType;
    }

    public void setSkinType(int i) {
        this.skinType = i;
    }

    public int getBpTag() {
        return this.bpTag;
    }

    public void setBpTag(int i) {
        this.bpTag = i;
    }

    public EFunctionStatus getStress() {
        return this.stress;
    }

    public void setStress(EFunctionStatus eFunctionStatus) {
        this.stress = eFunctionStatus;
    }

    public EFunctionStatus getWallet() {
        return this.wallet;
    }

    public void setWallet(EFunctionStatus eFunctionStatus) {
        this.wallet = eFunctionStatus;
    }

    public EFunctionStatus getAutoMeasure() {
        return this.autoMeasure;
    }

    public void setAutoMeasure(EFunctionStatus eFunctionStatus) {
        this.autoMeasure = eFunctionStatus;
    }

    public int getStressType() {
        return this.stressType;
    }

    public void setStressType(int i) {
        this.stressType = i;
    }

    public int getAlarmTag() {
        return this.alarmTag;
    }

    public void setAlarmTag(int i) {
        this.alarmTag = i;
    }

    public void setTemperatureAlarm(EFunctionStatus eFunctionStatus) {
        this.temperatureAlarm = eFunctionStatus;
    }

    public EFunctionStatus getTemperatureAlarm() {
        return this.temperatureAlarm;
    }

    public EFunctionStatus getPostcard() {
        return this.postcard;
    }

    public void setPostcard(EFunctionStatus eFunctionStatus) {
        this.postcard = eFunctionStatus;
    }

    public EFunctionStatus getGameSetting() {
        return this.gameSetting;
    }

    public void setGameSetting(EFunctionStatus eFunctionStatus) {
        this.gameSetting = eFunctionStatus;
    }

    public EFunctionStatus getAiQA() {
        return this.aiQA;
    }

    public void setAiQA(EFunctionStatus eFunctionStatus) {
        this.aiQA = eFunctionStatus;
    }

    public EFunctionStatus getAiDial() {
        return this.aiDial;
    }

    public void setAiDial(EFunctionStatus eFunctionStatus) {
        this.aiDial = eFunctionStatus;
    }

    public EFunctionStatus getDistanceCalorieGoal() {
        return this.distanceCalorieGoal;
    }

    public void setDistanceCalorieGoal(EFunctionStatus eFunctionStatus) {
        this.distanceCalorieGoal = eFunctionStatus;
    }

    public EFunctionStatus getVideoDial() {
        return this.videoDial;
    }

    public void setVideoDial(EFunctionStatus eFunctionStatus) {
        this.videoDial = eFunctionStatus;
    }

    public void setPhotoAlbum(EFunctionStatus eFunctionStatus) {
        this.photoAlbum = eFunctionStatus;
    }

    public EFunctionStatus getPhotoAlbum() {
        return this.photoAlbum;
    }

    public int getMiniCheckupTag() {
        return this.miniCheckupTag;
    }

    public void setMiniCheckupTag(int i) {
        this.miniCheckupTag = i;
    }

    public int getAiQaTag() {
        return this.aiQaTag;
    }

    public void setAiQaTag(int i) {
        this.aiQaTag = i;
    }

    public int getAiDialTag() {
        return this.aiDialTag;
    }

    public void setAiDialTag(int i) {
        this.aiDialTag = i;
    }

    public EFunctionStatus getHealthAssessment() {
        return this.healthAssessment;
    }

    public void setHealthAssessment(EFunctionStatus eFunctionStatus) {
        this.healthAssessment = eFunctionStatus;
    }

    public EFunctionStatus getBloodGlucoseHealthAssessment() {
        return this.bloodGlucoseHealthAssessment;
    }

    public void setBloodGlucoseHealthAssessment(EFunctionStatus eFunctionStatus) {
        this.bloodGlucoseHealthAssessment = eFunctionStatus;
    }

    public EFunctionStatus getBloodComponentHealthAssessment() {
        return this.bloodComponentHealthAssessment;
    }

    public void setBloodComponentHealthAssessment(EFunctionStatus eFunctionStatus) {
        this.bloodComponentHealthAssessment = eFunctionStatus;
    }

    public EFunctionStatus getBodyComponentHealthAssessment() {
        return this.bodyComponentHealthAssessment;
    }

    public void setBodyComponentHealthAssessment(EFunctionStatus eFunctionStatus) {
        this.bodyComponentHealthAssessment = eFunctionStatus;
    }

    public int getHealthAssessmentTag() {
        return this.healthAssessmentTag;
    }

    public void setHealthAssessmentTag(int i) {
        this.healthAssessmentTag = i;
    }

    public EFunctionStatus getServer4g() {
        return this.server4g;
    }

    public void setServer4g(EFunctionStatus eFunctionStatus) {
        this.server4g = eFunctionStatus;
    }

    public EFunctionStatus getMet() {
        return this.met;
    }

    public void setMet(EFunctionStatus eFunctionStatus) {
        this.met = eFunctionStatus;
    }

    public int getMetType() {
        return this.metType;
    }

    public void setMetType(int i) {
        this.metType = i;
    }

    public EFunctionStatus getGSR() {
        return this.GSR;
    }

    public void setGSR(EFunctionStatus eFunctionStatus) {
        this.GSR = eFunctionStatus;
    }

    public int getGsrTag() {
        return this.gsrTag;
    }

    public void setGsrTag(int i) {
        this.gsrTag = i;
    }

    public EFunctionStatus getSafetyProtection() {
        return this.safetyProtection;
    }

    public void setSafetyProtection(EFunctionStatus eFunctionStatus) {
        this.safetyProtection = eFunctionStatus;
    }

    public EFunctionStatus getTextImagePush() {
        return this.textImagePush;
    }

    public void setTextImagePush(EFunctionStatus eFunctionStatus) {
        this.textImagePush = eFunctionStatus;
    }

    public EFunctionStatus getMiniCheckup() {
        return this.miniCheckup;
    }

    public void setMiniCheckup(EFunctionStatus eFunctionStatus) {
        this.miniCheckup = eFunctionStatus;
    }

    public EFunctionStatus getDaSport() {
        return this.daSport;
    }

    public void setDaSport(EFunctionStatus eFunctionStatus) {
        this.daSport = eFunctionStatus;
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

    public int getPttControlTag() {
        return this.pttControlTag;
    }

    public void setPttControlTag(int i) {
        this.pttControlTag = i;
    }

    public int getFatigueTag() {
        return this.fatigueTag;
    }

    public void setFatigueTag(int i) {
        this.fatigueTag = i;
    }

    public DeviceFunctionPackage1 getDeviceFunctionPackage1() {
        DeviceFunctionPackage1 deviceFunctionPackage1 = new DeviceFunctionPackage1();
        deviceFunctionPackage1.setHeartRateDetect(this.heartDetect);
        deviceFunctionPackage1.setBloodPressure(this.Bp);
        deviceFunctionPackage1.setDrinking(this.Drink);
        deviceFunctionPackage1.setSedentaryRemind(this.Longseat);
        deviceFunctionPackage1.setHealthRemind(this.healthRemind);
        deviceFunctionPackage1.setSkinType(this.skinType);
        deviceFunctionPackage1.setHeartRateWarning(this.HeartWaring);
        deviceFunctionPackage1.setWeChatSport(this.WeChatSport);
        deviceFunctionPackage1.setCamera(this.Camera);
        deviceFunctionPackage1.setFatigue(this.Fatigue);
        deviceFunctionPackage1.setSpo2hType(getSpo2hType());
        deviceFunctionPackage1.setSpo2H(getSpo2H());
        deviceFunctionPackage1.setSpo2HAdjustment(getSpo2HAdjuster());
        deviceFunctionPackage1.setSpo2hShow(getSpo2hShow());
        deviceFunctionPackage1.setSpo2HBreathBreak(getSpo2HBreathBreak());
        deviceFunctionPackage1.setSpo2HValueZero(getSpo2HValueZero());
        deviceFunctionPackage1.setNightTurnSetting(getNightTurnSetting());
        deviceFunctionPackage1.setWomen(getWomen());
        deviceFunctionPackage1.setAmbulatoryBPAdjustment(getAngioAdjuster());
        deviceFunctionPackage1.setAlarm(getAlarm2());
        deviceFunctionPackage1.setTextAlarm(getTextAlarm());
        deviceFunctionPackage1.setScreenLight(getScreenLight());
        deviceFunctionPackage1.setNewCalcSport(getNewCalcSport());
        return deviceFunctionPackage1;
    }

    public DeviceFunctionPackage2 getDeviceFunctionPackage2() {
        DeviceFunctionPackage2 deviceFunctionPackage2 = new DeviceFunctionPackage2();
        deviceFunctionPackage2.setCountDown(getCountDown());
        deviceFunctionPackage2.setOriginProtocolVersion(getOriginProtcolVersion());
        deviceFunctionPackage2.setContactMsgLength(getContactMsgLength());
        deviceFunctionPackage2.setAllMsgLength(getAllMsgLength());
        deviceFunctionPackage2.setWatchDataDayNumber(getWathcDay());
        deviceFunctionPackage2.setHidFunction(getHidFuction());
        deviceFunctionPackage2.setScreenStyle(getScreenstyle());
        deviceFunctionPackage2.setBreathFunction(getBeathFunction());
        deviceFunctionPackage2.setWeatherFunction(getWeatherFunction());
        deviceFunctionPackage2.setWeatherStyle(getWeatherStyle());
        deviceFunctionPackage2.setHrvFunction(getHrvFunction());
        deviceFunctionPackage2.setHrvAppDetectFunction(getHrvAppDetectFunction());
        deviceFunctionPackage2.setHrvType(getHrvType());
        deviceFunctionPackage2.setSportModelFunction(getSportModel());
        deviceFunctionPackage2.setSportModelDay(getSportmodelday());
        deviceFunctionPackage2.setScreenLightTime(getScreenLightTime());
        deviceFunctionPackage2.setEcgFunction(getEcg());
        deviceFunctionPackage2.setEcgType(getEcgType());
        deviceFunctionPackage2.setSleepTag(getSleepTag());
        deviceFunctionPackage2.setPrecisionSleepFunction(getPrecisionSleep());
        deviceFunctionPackage2.setMultSportMode(getMultSportModel());
        deviceFunctionPackage2.setMultSportModeType(getSportModelType());
        deviceFunctionPackage2.setLowPower(getLowPower());
        return deviceFunctionPackage2;
    }

    public DeviceFunctionPackage3 getDeviceFunctionPackage3() {
        DeviceFunctionPackage3 deviceFunctionPackage3 = new DeviceFunctionPackage3();
        deviceFunctionPackage3.setBigDataTranType(getBigDataTranType());
        deviceFunctionPackage3.setWatchUiServerCount(getWatchUiServerCount());
        deviceFunctionPackage3.setWatchUiCustomCount(getWatchUiCoustomCount());
        deviceFunctionPackage3.setContactType(getContactType());
        deviceFunctionPackage3.setContactFunction(getContactFunction());
        deviceFunctionPackage3.setMusicStyle(getMusicStyle());
        deviceFunctionPackage3.setTemperatureType(getTemptureType());
        deviceFunctionPackage3.setCpuType(getCpuType());
        deviceFunctionPackage3.setStressType(getStressType());
        deviceFunctionPackage3.setStressFunction(getStress());
        deviceFunctionPackage3.setScreenStyleFunction(getScreenStyleFunction());
        deviceFunctionPackage3.setFindDeviceByPhoneFunction(getFindDeviceByPhone());
        deviceFunctionPackage3.setAgpsFunction(getAgps());
        deviceFunctionPackage3.setBloodGlucose(getBloodGlucose());
        deviceFunctionPackage3.setBloodGlucoseTag(getBloodGlucoseTag());
        deviceFunctionPackage3.setBloodGlucoseAdjusting(getBloodGlucoseAdjusting());
        deviceFunctionPackage3.setBloodGlucoseMultipleAdjusting(getBloodGlucoseMultipleAdjusting());
        deviceFunctionPackage3.setBloodGlucoseRiskAssessment(getBloodGlucoseRiskAssessment());
        return deviceFunctionPackage3;
    }

    public DeviceFunctionPackage4 getDeviceFunctionPackage4() {
        DeviceFunctionPackage4 deviceFunctionPackage4 = new DeviceFunctionPackage4();
        deviceFunctionPackage4.setAiQA(getAiQA());
        deviceFunctionPackage4.setAiDial(getAiDial());
        deviceFunctionPackage4.setVideoDial(getVideoDial());
        deviceFunctionPackage4.setAutoMeasure(getAutoMeasure());
        deviceFunctionPackage4.setBloodComponent(getBloodComponent());
        deviceFunctionPackage4.setBodyComponent(getBodyComponent());
        deviceFunctionPackage4.setWallet(getWallet());
        deviceFunctionPackage4.setPostcard(getPostcard());
        deviceFunctionPackage4.setPhotoAlbum(getPhotoAlbum());
        deviceFunctionPackage4.setDistanceCalorieGoal(getDistanceCalorieGoal());
        deviceFunctionPackage4.setTemperatureAlarm(getTemperatureAlarm());
        deviceFunctionPackage4.setGameSetting(getGameSetting());
        deviceFunctionPackage4.setWorldClock(getWorldClock());
        deviceFunctionPackage4.setMiniCheckup(getMiniCheckup());
        deviceFunctionPackage4.setDaSport(getDaSport());
        deviceFunctionPackage4.setBloodComponentSingleCalibration(getBloodComponentSingleCalibration());
        return deviceFunctionPackage4;
    }

    public DeviceFunctionPackage5 getDeviceFunctionPackage5() {
        DeviceFunctionPackage5 deviceFunctionPackage5 = new DeviceFunctionPackage5();
        deviceFunctionPackage5.setTextImagePush(getTextImagePush());
        deviceFunctionPackage5.setGSR(getGSR());
        deviceFunctionPackage5.setSafetyProtection(getSafetyProtection());
        deviceFunctionPackage5.setTouchICType(getTouchICType());
        deviceFunctionPackage5.setBarometer(getBarometer());
        deviceFunctionPackage5.setScreenType(getScreenType());
        deviceFunctionPackage5.setPttControl(getPttControl());
        deviceFunctionPackage5.setSportStatus(getSportStatus());
        deviceFunctionPackage5.setRemindEvent(getRemindEvent());
        deviceFunctionPackage5.setNewProductionTestProtocolTag(getNewProductionTestProtocolTag());
        deviceFunctionPackage5.setEmotion(getEmotion());
        deviceFunctionPackage5.setHealthLight(getHealthLight());
        return deviceFunctionPackage5;
    }

    public String toString() {
        return "功能列表{Blood Pressure检测=" + this.Bp + ", 饮酒检测=" + this.Drink + ", 久坐提醒=" + this.Longseat + ", 肤色类型=" + this.skinType + ", 健康提醒=" + this.healthRemind + ", Heart Rate警告=" + this.HeartWaring + ", 微信运动=" + this.WeChatSport + ", 摇一摇拍照=" + this.Camera + ", 疲劳度检测=" + this.Fatigue + ", 夜间转腕=" + this.NightTurnSetting + ", 运动模式类型=" + this.sportModelType + ", \n Blood Oxygen (SpO2)检测=" + this.Spo2H + ", Blood Oxygen (SpO2)功能类型=" + this.spo2hType + ", Blood Oxygen (SpO2)值为0=" + this.Spo2HValueZero + ", Blood Oxygen (SpO2)主界面显示=" + this.Spo2hShow + ", 女性提醒=" + this.Women + ", 第2版本的路程/卡路里算法=" + this.newCalcSport + ", Blood Pressure动态调整=" + this.AngioAdjuster + ", 倒计时=" + this.CountDown + ", 手表存储的数据天数=" + getWathcDay() + ",\n  第二版的闹钟=" + this.Alarm2 + ", 屏幕亮度调节=" + this.ScreenLight + ", 信息推送第一部分的长度=" + this.contactMsgLength + ", 信息推送总共的长度=" + this.allMsgLength + ", Heart Rate功能=" + this.heartDetect + ", 运动模式功能=" + this.SportModel + ", 运动模式功能【天数】=" + this.sportmodelday + ", HID功能=" + this.hidFuction + "，屏幕样式功能=" + this.screenStyleFunction + "，呼吸率功能=" + this.beathFunction + "，HRV功能=" + this.hrvFunction + "，HRV类型=" + this.hrvType + "，\n 天气功能=" + this.weatherFunction + "，天气功能类型=" + this.weatherStyle + "，Blood Oxygen (SpO2)呼吸暂停提醒=" + this.Spo2HBreathBreak + "，亮屏时长=" + this.screenLightTime + "，精准Sleep=" + this.precisionSleep + "，Sleep标记=" + this.sleepTag + "，\n ECG=" + this.ecg + "，多运动模式=" + this.multSportModel + "，低功耗=" + this.lowPower + "，协议版本=" + this.originProtcolVersion + "，音乐功能类型=" + this.musicStyle + "，\n 手机查找设备=" + this.findDeviceByPhone + "，AGPS=" + this.agps + "，Body Temperature类型=" + this.temptureType + "，Blood Glucose功能=" + this.bloodGlucose + "，市场表盘数量=" + this.watchUiServerCount + "，系统表盘数量=" + this.watchUiCoustomCount + "，\n cpuType=" + this.cpuType + "，Blood GlucoseCalibration功能=" + this.bloodGlucoseAdjusting + "，重置数据=" + this.resetData + "，血液成分=" + this.bloodComponent + "，血液成分单Calibration=" + this.bloodComponentSingleCalibration + "，\n 身体成分=" + this.bodyComponent + "，Blood Glucose多Calibration=" + this.bloodGlucoseMultipleAdjusting + "，Blood Glucose风险评估=" + this.bloodGlucoseRiskAssessment + "，Blood Glucose类型=" + this.bloodGlucoseTag + "，世界时钟=" + this.worldClock + "，压力=" + this.stress + "，梅脱=" + this.met + "，自动测量=" + this.autoMeasure + "，Body Temperature报警=" + this.temperatureAlarm + "，钱包收款码=" + this.wallet + "，名片=" + this.postcard + "，距离卡路里目标=" + this.distanceCalorieGoal + "，游戏=" + this.gameSetting + "，视频表盘=" + this.videoDial + "，\n ai表盘=" + this.aiDial + "，ai问答=" + this.aiQA + "，相册=" + this.photoAlbum + "，4g=" + this.server4g + "，健康辅助评估=" + this.healthAssessment + "，健康辅助评估Tag=" + this.healthAssessmentTag + "，微体检=" + this.miniCheckup + "，图文推送=" + this.textImagePush + "，皮电=" + this.GSR + "，安全守护=" + this.safetyProtection + "，屏幕类型=" + getScreenTypeDes() + "，气压计=" + this.barometer + "，触摸IC类型=" + this.touchICType + "，新产测协议标识=" + this.newProductionTestProtocolTag + "，PTT控制功能=" + this.pttControl + "，运动状态功能=" + this.sportStatus + "，提醒事件功能=" + this.remindEvent + "，情绪=" + this.emotion + "，健康燈=" + this.healthLight + "，马达=" + this.motor + "，左右手佩戴设置=" + this.wearHand + '}';
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
