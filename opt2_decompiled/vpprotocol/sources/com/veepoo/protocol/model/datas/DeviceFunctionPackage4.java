package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/DeviceFunctionPackage4.class */
public class DeviceFunctionPackage4 {
    private EFunctionStatus bloodComponent;
    private EFunctionStatus bloodComponentSingleCalibration;
    private EFunctionStatus bodyComponent;
    private EFunctionStatus worldClock;
    private EFunctionStatus autoMeasure;
    private EFunctionStatus temperatureAlarm;
    private EFunctionStatus wallet;
    private EFunctionStatus postcard;
    private EFunctionStatus gameSetting;
    private EFunctionStatus aiQA;
    private EFunctionStatus aiDial;
    private EFunctionStatus distanceCalorieGoal;
    private EFunctionStatus videoDial;
    private EFunctionStatus photoAlbum;
    private EFunctionStatus daSport;
    private EFunctionStatus miniCheckup;

    public DeviceFunctionPackage4() {
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        this.bloodComponent = eFunctionStatus;
        this.bloodComponentSingleCalibration = eFunctionStatus;
        this.bodyComponent = eFunctionStatus;
        this.worldClock = eFunctionStatus;
        this.autoMeasure = eFunctionStatus;
        this.temperatureAlarm = eFunctionStatus;
        this.wallet = eFunctionStatus;
        this.postcard = eFunctionStatus;
        this.gameSetting = eFunctionStatus;
        this.aiQA = eFunctionStatus;
        this.aiDial = eFunctionStatus;
        this.distanceCalorieGoal = eFunctionStatus;
        this.videoDial = eFunctionStatus;
        this.photoAlbum = eFunctionStatus;
        this.daSport = eFunctionStatus;
        this.miniCheckup = eFunctionStatus;
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

    public EFunctionStatus getWorldClock() {
        return this.worldClock;
    }

    public void setWorldClock(EFunctionStatus eFunctionStatus) {
        this.worldClock = eFunctionStatus;
    }

    public EFunctionStatus getAutoMeasure() {
        return this.autoMeasure;
    }

    public void setAutoMeasure(EFunctionStatus eFunctionStatus) {
        this.autoMeasure = eFunctionStatus;
    }

    public EFunctionStatus getTemperatureAlarm() {
        return this.temperatureAlarm;
    }

    public void setTemperatureAlarm(EFunctionStatus eFunctionStatus) {
        this.temperatureAlarm = eFunctionStatus;
    }

    public EFunctionStatus getWallet() {
        return this.wallet;
    }

    public void setWallet(EFunctionStatus eFunctionStatus) {
        this.wallet = eFunctionStatus;
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

    public EFunctionStatus getPhotoAlbum() {
        return this.photoAlbum;
    }

    public void setPhotoAlbum(EFunctionStatus eFunctionStatus) {
        this.photoAlbum = eFunctionStatus;
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

    public String toString() {
        return "设备功能第四包{血液成分功能=" + this.bloodComponent + ", 血液成分单次Calibration功能=" + this.bloodComponentSingleCalibration + ", 身体成分功能=" + this.bodyComponent + ", 世界时钟功能=" + this.worldClock + ", 自动测量功能=" + this.autoMeasure + ", Body Temperature报警功能=" + this.temperatureAlarm + ", 钱包收款码功能=" + this.wallet + ", 名片功能=" + this.postcard + ", 游戏功能=" + this.gameSetting + ", ai问答功能=" + this.aiQA + ", ai表盘功能=" + this.aiDial + ", 距离卡路里目标设置功能=" + this.distanceCalorieGoal + ", 视频表盘功能=" + this.videoDial + ", 相册功能=" + this.photoAlbum + ", DA运动=" + this.daSport + ", 微体检=" + this.miniCheckup + '}';
    }
}
