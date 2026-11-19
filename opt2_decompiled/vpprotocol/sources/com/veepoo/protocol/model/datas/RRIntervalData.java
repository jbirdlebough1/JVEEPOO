package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/RRIntervalData.class */
public class RRIntervalData {
    private int blockNumber;
    private int totalBlock;
    private TimeData date;
    private int[] data;
    private int dayState;

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public void setBlockNumber(int i) {
        this.blockNumber = i;
    }

    public TimeData getDate() {
        return this.date;
    }

    public void setDate(TimeData timeData) {
        this.date = timeData;
    }

    public int[] getData() {
        return this.data;
    }

    public void setData(int[] iArr) {
        this.data = iArr;
    }

    public int getDayState() {
        return this.dayState;
    }

    public void setDayState(int i) {
        this.dayState = i;
    }

    public int getTotalBlock() {
        return this.totalBlock;
    }

    public void setTotalBlock(int i) {
        this.totalBlock = i;
    }

    public String toString() {
        return "RRIntervalData{blockNumber=" + this.blockNumber + ", totalBlock=" + this.totalBlock + ", date=" + this.date + ", data=" + Arrays.toString(this.data) + ", dayState=" + this.dayState + '}';
    }
}
