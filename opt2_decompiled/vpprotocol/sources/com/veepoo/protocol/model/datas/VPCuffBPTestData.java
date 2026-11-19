package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EVPCuffBPTestStatus;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/VPCuffBPTestData.class */
public class VPCuffBPTestData {
    private int sbpValue;
    private int dbpValue;
    private EVPCuffBPTestStatus status = EVPCuffBPTestStatus.EMPTY;
    private int heartValue;
    private int pressureValue;
    private int rate;
    private List<byte[]> ppg80Datas;
    private List<byte[]> ppg85Datas;
    private List<byte[]> gsensorDatas;
    private List<byte[]> cuffDatas;

    public int getSbpValue() {
        return this.sbpValue;
    }

    public void setSbpValue(int i) {
        this.sbpValue = i;
    }

    public int getDbpValue() {
        return this.dbpValue;
    }

    public void setDbpValue(int i) {
        this.dbpValue = i;
    }

    public EVPCuffBPTestStatus getStatus() {
        return this.status;
    }

    public void setStatus(EVPCuffBPTestStatus eVPCuffBPTestStatus) {
        this.status = eVPCuffBPTestStatus;
    }

    public int getHeartValue() {
        return this.heartValue;
    }

    public void setHeartValue(int i) {
        this.heartValue = i;
    }

    public int getPressureValue() {
        return this.pressureValue;
    }

    public void setPressureValue(int i) {
        this.pressureValue = i;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int i) {
        this.rate = i;
    }

    public List<byte[]> getPpg80Datas() {
        return this.ppg80Datas;
    }

    public void setPpg80Datas(List<byte[]> list) {
        this.ppg80Datas = list;
    }

    public List<byte[]> getPpg85Datas() {
        return this.ppg85Datas;
    }

    public void setPpg85Datas(List<byte[]> list) {
        this.ppg85Datas = list;
    }

    public List<byte[]> getGsensorDatas() {
        return this.gsensorDatas;
    }

    public void setGsensorDatas(List<byte[]> list) {
        this.gsensorDatas = list;
    }

    public List<byte[]> getCuffDatas() {
        return this.cuffDatas;
    }

    public void setCuffDatas(List<byte[]> list) {
        this.cuffDatas = list;
    }

    public String toString() {
        return "VPCuffBPTestResult{sbpValue=" + this.sbpValue + ", dbpValue=" + this.dbpValue + ", status=" + this.status + ", heartValue=" + this.heartValue + ", pressureValue=" + this.pressureValue + ", rate=" + this.rate + ", ppg80Datas=" + this.ppg80Datas + ", ppg85Datas=" + this.ppg85Datas + ", gsensorDatas=" + this.gsensorDatas + ", cuffDatas=" + this.cuffDatas + '}';
    }
}
