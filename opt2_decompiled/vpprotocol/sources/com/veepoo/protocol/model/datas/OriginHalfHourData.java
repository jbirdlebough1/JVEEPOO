package com.veepoo.protocol.model.datas;

import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/OriginHalfHourData.class */
public class OriginHalfHourData {
    private List<HalfHourRateData> halfHourRateDatas;
    private List<HalfHourBpData> halfHourBps;
    private List<HalfHourSportData> halfHourSportDatas;
    private int allStep;
    private String date;

    public OriginHalfHourData(List<HalfHourRateData> list, List<HalfHourSportData> list2, List<HalfHourBpData> list3, int i) {
        this.halfHourRateDatas = list;
        this.halfHourSportDatas = list2;
        this.halfHourBps = list3;
        this.allStep = i;
    }

    public List<HalfHourRateData> getHalfHourRateDatas() {
        return this.halfHourRateDatas;
    }

    public void setHalfHourRateDatas(List<HalfHourRateData> list) {
        this.halfHourRateDatas = list;
    }

    public List<HalfHourBpData> getHalfHourBps() {
        return this.halfHourBps;
    }

    public void setHalfHourBps(List<HalfHourBpData> list) {
        this.halfHourBps = list;
    }

    public int getAllStep() {
        return this.allStep;
    }

    public void setAllStep(int i) {
        this.allStep = i;
    }

    public List<HalfHourSportData> getHalfHourSportDatas() {
        return this.halfHourSportDatas;
    }

    public void setHalfHourSportDatas(List<HalfHourSportData> list) {
        this.halfHourSportDatas = list;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String toString() {
        return "OriginHalfHourData{halfHourRateDatas=" + this.halfHourRateDatas + "halfHourSportDatas=" + this.halfHourSportDatas + ", halfHourBps=" + this.halfHourBps + ", allStep=" + this.allStep + '}';
    }
}
