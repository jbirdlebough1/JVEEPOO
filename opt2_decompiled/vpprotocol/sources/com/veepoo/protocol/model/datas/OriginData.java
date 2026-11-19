package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/OriginData.class */
public class OriginData {
    private String date;
    private int allPackage;
    private int packageNumber;
    private TimeData mTime;
    private int rateValue;
    private int sportValue;
    private int stepValue;
    private int highValue;
    private int lowValue;
    private int wear;
    private int tempOne;
    private int tempTwo;
    public double calValue;
    public double disValue;
    public int calcType;
    public String drinkPartOne = "";
    public String drinkPartTwo = "";
    private double baseTemperature;
    private double temperature;

    public OriginData() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getAllPackage() {
        return this.allPackage;
    }

    public void setAllPackage(int i) {
        this.allPackage = i;
    }

    public int getPackageNumber() {
        return this.packageNumber;
    }

    public void setPackageNumber(int i) {
        this.packageNumber = i;
    }

    public TimeData getmTime() {
        return this.mTime;
    }

    public void setmTime(TimeData timeData) {
        this.mTime = timeData;
    }

    public int getRateValue() {
        return this.rateValue;
    }

    public void setRateValue(int i) {
        this.rateValue = i;
    }

    public int getSportValue() {
        return this.sportValue;
    }

    public void setSportValue(int i) {
        this.sportValue = i;
    }

    public int getSportValueLevel() {
        int i = this.sportValue;
        if (i <= 220) {
            return 1;
        }
        if (i <= 700) {
            return 2;
        }
        if (i <= 1400) {
            return 3;
        }
        if (i <= 3200) {
            return 4;
        }
        return i <= 65535 ? 5 : 1;
    }

    public int getStepValue() {
        return this.stepValue;
    }

    public void setStepValue(int i) {
        this.stepValue = i;
    }

    public int getHighValue() {
        return this.highValue;
    }

    public void setHighValue(int i) {
        this.highValue = i;
    }

    public int getLowValue() {
        return this.lowValue;
    }

    public void setLowValue(int i) {
        this.lowValue = i;
    }

    public int getWear() {
        return this.wear;
    }

    public void setWear(int i) {
        this.wear = i;
    }

    public int getTempOne() {
        return this.tempOne;
    }

    public void setTempOne(int i) {
        this.tempOne = i;
    }

    public int getTempTwo() {
        return this.tempTwo;
    }

    public void setTempTwo(int i) {
        this.tempTwo = i;
    }

    public double getCalValue() {
        return this.calValue;
    }

    public void setCalValue(double d) {
        this.calValue = d;
    }

    public double getDisValue() {
        return this.disValue;
    }

    public void setDisValue(double d) {
        this.disValue = d;
    }

    public int getCalcType() {
        return this.calcType;
    }

    public void setCalcType(int i) {
        this.calcType = i;
    }

    public String getDrinkPartOne() {
        return this.drinkPartOne;
    }

    public void setDrinkPartOne(String str) {
        this.drinkPartOne = str;
    }

    public String getDrinkPartTwo() {
        return this.drinkPartTwo;
    }

    public void setDrinkPartTwo(String str) {
        this.drinkPartTwo = str;
    }

    public double getBaseTemperature() {
        return this.baseTemperature;
    }

    public void setBaseTemperature(double d) {
        this.baseTemperature = d;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double d) {
        this.temperature = d;
    }

    public String toString() {
        return "OriginData{date='" + this.date + "', allPackage=" + this.allPackage + ", packageNumber=" + this.packageNumber + ", mTime=" + this.mTime + ", rateValue=" + this.rateValue + ", sportValue=" + this.sportValue + ", stepValue=" + this.stepValue + ", highValue=" + this.highValue + ", lowVaamlue=" + this.lowValue + ", wear=" + this.wear + ", tempOne=" + this.tempOne + ", tempTwo=" + this.tempTwo + ", calValue=" + this.calValue + ", disValue=" + this.disValue + ", calcType=" + this.calcType + ", baseTemperature=" + this.baseTemperature + ", temperature=" + this.temperature + ", drinkPartOne='" + this.drinkPartOne + "', drinkPartTwo='" + this.drinkPartTwo + "'}";
    }

    public OriginData(String str, int i, int i2, TimeData timeData, int i3, int i4, int i5, int i6, int i7) {
        this.date = str;
        this.allPackage = i;
        this.packageNumber = i2;
        this.mTime = timeData;
        this.rateValue = i3;
        this.sportValue = i4;
        this.stepValue = i5;
        this.highValue = i6;
        this.lowValue = i7;
    }

    public OriginData(String str, int i, int i2, TimeData timeData, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, double d, double d2) {
        this.allPackage = i;
        this.packageNumber = i2;
        this.date = str;
        this.mTime = timeData;
        this.rateValue = i3;
        this.sportValue = i4;
        this.stepValue = i5;
        this.highValue = i6;
        this.lowValue = i7;
        this.wear = i8;
        this.tempOne = i9;
        this.tempTwo = i10;
        this.disValue = d;
        this.calValue = d2;
    }
}
