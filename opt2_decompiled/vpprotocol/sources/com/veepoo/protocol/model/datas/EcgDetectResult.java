package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EECGResultType;
import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/EcgDetectResult.class */
public class EcgDetectResult {
    private boolean isSuccess;
    private EECGResultType type;
    private TimeData timeBean;
    private int frequency;
    private int drawfrequency;
    private int duration;
    private int leadSign;
    private int[] originSign;
    private int[] powers;
    private int[] filterSignals;
    private int[] result8;
    private int[] diseaseResult;
    private int aveHeart;
    private int aveResRate;
    private int aveHrv;
    private int aveQT;
    private int progress;
    int[] vp_a;
    int[] vp_b;
    int[] vp_c;
    int[] vp_d;
    private int leadOffType;
    private int[] diagnosis8;
    private int heartRate;
    private int respRate;
    private int id;

    public int[] getPowers() {
        return this.powers;
    }

    public void setPowers(int[] iArr) {
        this.powers = iArr;
    }

    public int[] getDetectHeart() {
        return this.vp_a;
    }

    public void setDetectHeart(int[] iArr) {
        this.vp_a = iArr;
    }

    public int[] getDetectBreath() {
        return this.vp_b;
    }

    public void setDetectBreath(int[] iArr) {
        this.vp_b = iArr;
    }

    public int[] getDetectHrv() {
        return this.vp_c;
    }

    public void setDetectHrv(int[] iArr) {
        this.vp_c = iArr;
    }

    public int[] getDetectQT() {
        return this.vp_d;
    }

    public void setDetectQT(int[] iArr) {
        this.vp_d = iArr;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public EECGResultType getType() {
        return this.type;
    }

    public void setType(EECGResultType eECGResultType) {
        this.type = eECGResultType;
    }

    public TimeData getTimeBean() {
        return this.timeBean;
    }

    public void setTimeBean(TimeData timeData) {
        this.timeBean = timeData;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getDrawfrequency() {
        return this.drawfrequency;
    }

    public void setDrawfrequency(int i) {
        this.drawfrequency = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getLeadSign() {
        return this.leadSign;
    }

    public void setLeadSign(int i) {
        this.leadSign = i;
    }

    public int[] getOriginSign() {
        return this.originSign;
    }

    public void setOriginSign(int[] iArr) {
        this.originSign = iArr;
    }

    public int[] getFilterSignals() {
        return this.filterSignals;
    }

    public void setFilterSignals(int[] iArr) {
        this.filterSignals = iArr;
    }

    public int[] getResult8() {
        return this.result8;
    }

    public int[] getDiseaseResult() {
        return this.diseaseResult;
    }

    public void setDiseaseResult(int[] iArr) {
        this.diseaseResult = iArr;
    }

    public void setResult8(int[] iArr) {
        this.result8 = iArr;
    }

    public int getAveHeart() {
        return this.aveHeart;
    }

    public void setAveHeart(int i) {
        this.aveHeart = i;
    }

    public int getAveResRate() {
        return this.aveResRate;
    }

    public void setAveResRate(int i) {
        this.aveResRate = i;
    }

    public int getAveHrv() {
        return this.aveHrv;
    }

    public void setAveHrv(int i) {
        this.aveHrv = i;
    }

    public int getAveQT() {
        return this.aveQT;
    }

    public void setAveQT(int i) {
        this.aveQT = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("EcgDetectResult{isSuccess=").append(this.isSuccess).append(", type=").append(this.type).append(", timeBean=").append(this.timeBean).append(", frequency=").append(this.frequency).append(", drawfrequency=").append(this.drawfrequency).append(", duration=").append(this.duration).append(", leadSign=").append(this.leadSign).append(", originSign.size=");
        int[] iArr = this.originSign;
        StringBuilder sbAppend2 = sbAppend.append(iArr == null ? 0 : iArr.length).append(", filterSignals.size=");
        int[] iArr2 = this.filterSignals;
        StringBuilder sbAppend3 = sbAppend2.append(iArr2 == null ? 0 : iArr2.length).append(", result8=");
        int[] iArr3 = this.result8;
        StringBuilder sbAppend4 = sbAppend3.append(iArr3 == null ? 0 : Arrays.toString(iArr3)).append(", diseaseResult=");
        int[] iArr4 = this.diseaseResult;
        return sbAppend4.append(iArr4 == null ? 0 : Arrays.toString(iArr4)).append(", aveHeart=").append(this.aveHeart).append(", aveResRate=").append(this.aveResRate).append(", aveHrv=").append(this.aveHrv).append(", aveQT=").append(this.aveQT).append(", progress=").append(this.progress).append(", id=").append(this.id).append('}').toString();
    }
}
