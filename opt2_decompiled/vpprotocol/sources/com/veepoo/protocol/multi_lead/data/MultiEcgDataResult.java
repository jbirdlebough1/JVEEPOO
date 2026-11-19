package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EDisease;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/MultiEcgDataResult.class */
public class MultiEcgDataResult {
    private long startTime = 0;
    private int duration = 0;
    private int avgHeart;
    private int avgQt;
    private int avgHrv;
    private List<EDisease> diseaseList;
    private int frequency;
    private int id;
    private List<DiseaseInfo> diseaseInfoList;
    private List<EcgRRDataInfo> rrDataInfoList;
    private int adcModel;
    private int ecgType;
    private MultiEcgWaveBaseInfo lastlyEcgBaseInfo;

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getAvgHeart() {
        return this.avgHeart;
    }

    public void setAvgHeart(int i) {
        this.avgHeart = i;
    }

    public int getAvgQt() {
        return this.avgQt;
    }

    public void setAvgQt(int i) {
        this.avgQt = i;
    }

    public int getAvgHrv() {
        return this.avgHrv;
    }

    public void setAvgHrv(int i) {
        this.avgHrv = i;
    }

    public List<EDisease> getDiseaseList() {
        return this.diseaseList;
    }

    public void setDiseaseList(List<EDisease> list) {
        this.diseaseList = list;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public List<DiseaseInfo> getDiseaseInfoList() {
        return this.diseaseInfoList;
    }

    public void setDiseaseInfoList(List<DiseaseInfo> list) {
        this.diseaseInfoList = list;
    }

    public List<EcgRRDataInfo> getRrDataInfoList() {
        return this.rrDataInfoList;
    }

    public void setRrDataInfoList(List<EcgRRDataInfo> list) {
        this.rrDataInfoList = list;
    }

    public int getAdcModel() {
        return this.adcModel;
    }

    public void setAdcModel(int i) {
        this.adcModel = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getEcgType() {
        return this.ecgType;
    }

    public void setEcgType(int i) {
        this.ecgType = i;
    }

    public MultiEcgWaveBaseInfo getLastlyEcgBaseInfo() {
        return this.lastlyEcgBaseInfo;
    }

    public void setLastlyEcgBaseInfo(MultiEcgWaveBaseInfo multiEcgWaveBaseInfo) {
        this.lastlyEcgBaseInfo = multiEcgWaveBaseInfo;
    }

    public String toString() {
        return "MultiEcgDataResult{startTime=" + this.startTime + ", duration=" + this.duration + ", avgHeart=" + this.avgHeart + ", avgQt=" + this.avgQt + ", avgHrv=" + this.avgHrv + ", diseaseList=" + this.diseaseList + ", frequency=" + this.frequency + ", id=" + this.id + ", diseaseInfoList=" + this.diseaseInfoList + ", rrDataInfoList=" + this.rrDataInfoList + ", adcModel=" + this.adcModel + ", ecgType=" + this.ecgType + ", lastlyEcgBaseInfo=" + this.lastlyEcgBaseInfo + '}';
    }
}
