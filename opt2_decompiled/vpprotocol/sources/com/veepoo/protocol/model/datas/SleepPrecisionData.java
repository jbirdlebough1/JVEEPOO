package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.util.vp_j;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SleepPrecisionData.class */
public class SleepPrecisionData extends SleepData {
    private int sleepTag;
    private int getUpScore;
    private int deepScore;
    private int sleepEfficiencyScore;
    private int fallAsleepScore;
    private int sleepTimeScore;
    private int exitSleepMode;
    private int deepAndLightMode;
    private int otherDuration;
    private int firstDeepDuration;
    private int getUpDuration;
    private int getUpToDeepAve;
    private int onePointDuration;
    private int accurateType;
    private int insomniaTag;
    private int insomniaScore;
    private int insomniaTimes;
    private int insomniaLength;
    private List<InsomniaTimeData> insomniaBeanList;
    public String startInsomniaTime;
    public String stopInsomniaTime;
    private int insomniaDuration;
    private String sleepSourceStr;
    private int laster;
    private int next;

    public void setPresicionDate(TimeData timeData, TimeData timeData2) {
        this.sleepDown = timeData;
        this.sleepUp = timeData2;
        String databaseDateString = timeData.toDatabaseDateString();
        String databaseDateString2 = timeData2.toDatabaseDateString();
        if (!databaseDateString.equals(databaseDateString2)) {
            this.Date = databaseDateString2;
        } else if (timeData.getHour() >= 20) {
            this.Date = vp_j.vp_a(databaseDateString, 1);
        } else {
            this.Date = databaseDateString;
        }
    }

    public int getSleepTag() {
        return this.sleepTag;
    }

    public void setSleepTag(int i) {
        this.sleepTag = i;
    }

    public int getGetUpScore() {
        return this.getUpScore;
    }

    public void setGetUpScore(int i) {
        this.getUpScore = i;
    }

    public int getDeepScore() {
        return this.deepScore;
    }

    public void setDeepScore(int i) {
        this.deepScore = i;
    }

    public int getSleepEfficiencyScore() {
        return this.sleepEfficiencyScore;
    }

    public void setSleepEfficiencyScore(int i) {
        this.sleepEfficiencyScore = i;
    }

    public int getFallAsleepScore() {
        return this.fallAsleepScore;
    }

    public void setFallAsleepScore(int i) {
        this.fallAsleepScore = i;
    }

    public int getSleepTimeScore() {
        return this.sleepTimeScore;
    }

    public void setSleepTimeScore(int i) {
        this.sleepTimeScore = i;
    }

    public int getExitSleepMode() {
        return this.exitSleepMode;
    }

    public void setExitSleepMode(int i) {
        this.exitSleepMode = i;
    }

    public int getDeepAndLightMode() {
        return this.deepAndLightMode;
    }

    public void setDeepAndLightMode(int i) {
        this.deepAndLightMode = i;
    }

    public int getOtherDuration() {
        return this.otherDuration;
    }

    public void setOtherDuration(int i) {
        this.otherDuration = i;
    }

    public int getFirstDeepDuration() {
        return this.firstDeepDuration;
    }

    public void setFirstDeepDuration(int i) {
        this.firstDeepDuration = i;
    }

    public int getGetUpDuration() {
        return this.getUpDuration;
    }

    public void setGetUpDuration(int i) {
        this.getUpDuration = i;
    }

    public int getGetUpToDeepAve() {
        return this.getUpToDeepAve;
    }

    public void setGetUpToDeepAve(int i) {
        this.getUpToDeepAve = i;
    }

    public int getOnePointDuration() {
        return this.onePointDuration;
    }

    public void setOnePointDuration(int i) {
        this.onePointDuration = i;
    }

    public int getAccurateType() {
        return this.accurateType;
    }

    public void setAccurateType(int i) {
        this.accurateType = i;
    }

    public int getInsomniaTag() {
        return this.insomniaTag;
    }

    public void setInsomniaTag(int i) {
        this.insomniaTag = i;
    }

    public int getInsomniaScore() {
        return this.insomniaScore;
    }

    public void setInsomniaScore(int i) {
        this.insomniaScore = i;
    }

    public int getInsomniaTimes() {
        return this.insomniaTimes;
    }

    public void setInsomniaTimes(int i) {
        this.insomniaTimes = i;
    }

    public int getInsomniaLength() {
        return this.insomniaLength;
    }

    public void setInsomniaLength(int i) {
        this.insomniaLength = i;
    }

    public List<InsomniaTimeData> getInsomniaBeanList() {
        return this.insomniaBeanList;
    }

    public void setInsomniaBeanList(List<InsomniaTimeData> list) {
        this.insomniaBeanList = list;
    }

    public String getStartInsomniaTime() {
        return this.startInsomniaTime;
    }

    public void setStartInsomniaTime(String str) {
        this.startInsomniaTime = str;
    }

    public String getStopInsomniaTime() {
        return this.stopInsomniaTime;
    }

    public void setStopInsomniaTime(String str) {
        this.stopInsomniaTime = str;
    }

    public int getInsomniaDuration() {
        return this.insomniaDuration;
    }

    public void setInsomniaDuration(int i) {
        this.insomniaDuration = i;
    }

    public String getSleepSourceStr() {
        return this.sleepSourceStr;
    }

    public void setSleepSourceStr(String str) {
        this.sleepSourceStr = str;
    }

    public int getLaster() {
        return this.laster;
    }

    public void setLaster(int i) {
        this.laster = i;
    }

    public int getNext() {
        return this.next;
    }

    public void setNext(int i) {
        this.next = i;
    }

    @Override // com.veepoo.protocol.model.datas.SleepData
    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("SleepPrecisionData{, date=").append(this.Date).append(", deepSleepTime=").append(this.deepSleepTime).append(", lowSleepTime=").append(this.lowSleepTime).append(", allSleepTime=").append(this.allSleepTime).append(", sleepDown=").append(this.sleepDown).append(", sleepUp=").append(this.sleepUp).append(", wakeCount=").append(this.wakeCount).append(", sleepQulity=").append(this.sleepQulity).append(", cali_flag=").append(this.cali_flag).append(", sleepTag=").append(this.sleepTag).append(", getUpScore=").append(this.getUpScore).append(", deepScore=").append(this.deepScore).append(", sleepEfficiencyScore=").append(this.sleepEfficiencyScore).append(", fallAsleepScore=").append(this.fallAsleepScore).append(", sleepTimeScore=").append(this.sleepTimeScore).append(", exitSleepMode=").append(this.exitSleepMode).append(", deepAndLightMode=").append(this.deepAndLightMode).append(", otherDuration=").append(this.otherDuration).append(", firstDeepDuration=").append(this.firstDeepDuration).append(", getUpDuration=").append(this.getUpDuration).append(", getUpToDeepAve=").append(this.getUpToDeepAve).append(", onePointDuration=").append(this.onePointDuration).append(", accurateType=").append(this.accurateType).append(", insomniaTag=").append(this.insomniaTag).append(", insomniaScore=").append(this.insomniaScore).append(", insomniaTimes=").append(this.insomniaTimes).append(", insomniaLength=").append(this.insomniaLength).append(", insomniaBeanList=").append(this.insomniaBeanList).append(", startInsomniaTime=").append(this.startInsomniaTime).append(", stopInsomniaTime=").append(this.stopInsomniaTime).append(", sleepLine length=");
        String str = this.sleepLine;
        StringBuilder sbAppend2 = sbAppend.append(str == null ? 0 : str.length()).append(", insomniaDuration=").append(this.insomniaDuration).append(", sleepSourceStr length=");
        String str2 = this.sleepSourceStr;
        return sbAppend2.append(str2 == null ? 0 : str2.length()).append(", laster=").append(this.laster).append(", next=").append(this.next).append('}').toString();
    }
}
