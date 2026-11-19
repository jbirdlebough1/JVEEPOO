package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HRVOriginData.class */
public class HRVOriginData implements Comparable<HRVOriginData> {
    private String date;
    private TimeData mTime;
    public int currentPackNumber;
    public int allCurrentPackNumber;
    public String rate;
    public int[] rrValue;
    public int hrvValue;
    public int tempOne;
    public int hrvType;

    public HRVOriginData() {
    }

    public HRVOriginData(String str, TimeData timeData, int i, int i2, String str2, int i3, int i4) {
        this.date = str;
        this.mTime = timeData;
        this.allCurrentPackNumber = i;
        this.currentPackNumber = i2;
        this.rate = str2;
        this.hrvValue = i3;
        this.tempOne = i4;
        setRr(str2);
    }

    private void setRr(String str) {
        int[] iArrStringToIntArr = VpBleByteUtil.stringToIntArr(str);
        int length = iArrStringToIntArr.length;
        if (length > 0) {
            this.rrValue = new int[length];
            for (int i = 0; i < length; i++) {
                this.rrValue[i] = iArrStringToIntArr[i] * 10;
            }
        }
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public TimeData getmTime() {
        return this.mTime;
    }

    public void setmTime(TimeData timeData) {
        this.mTime = timeData;
    }

    public int getCurrentPackNumber() {
        return this.currentPackNumber;
    }

    public void setCurrentPackNumber(int i) {
        this.currentPackNumber = i;
    }

    public int getAllCurrentPackNumber() {
        return this.allCurrentPackNumber;
    }

    public void setAllCurrentPackNumber(int i) {
        this.allCurrentPackNumber = i;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String str) {
        this.rate = str;
        setRr(str);
    }

    public int getHrvValue() {
        return this.hrvValue;
    }

    public void setHrvValue(int i) {
        this.hrvValue = i;
    }

    public int getTempOne() {
        return this.tempOne;
    }

    public void setTempOne(int i) {
        this.tempOne = i;
    }

    public int getHrvType() {
        return this.hrvType;
    }

    public void setHrvType(int i) {
        this.hrvType = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(HRVOriginData hRVOriginData) {
        if (hRVOriginData != null) {
            return Integer.compare(0, hRVOriginData.getmTime().getHMValue() - getmTime().getHMValue());
        }
        return 0;
    }

    public String toString() {
        return "HRVOriginData{date='" + this.date + "', mTime=" + this.mTime + ", currentPackNumber=" + this.currentPackNumber + ", allCurrentPackNumber=" + this.allCurrentPackNumber + ", rate='" + this.rate + "', hrvValue=" + this.hrvValue + ", tempOne=" + this.tempOne + '}';
    }
}
