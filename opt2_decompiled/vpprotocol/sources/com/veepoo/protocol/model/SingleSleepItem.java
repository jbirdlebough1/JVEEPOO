package com.veepoo.protocol.model;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/SingleSleepItem.class */
public class SingleSleepItem {
    private int position;
    private int sleepCount;
    private int whichday;
    private String content;

    public SingleSleepItem(int i, int i2, int i3, String str) {
        this.position = i;
        this.sleepCount = i2;
        this.whichday = i3;
        this.content = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getSleepCount() {
        return this.sleepCount;
    }

    public void setSleepCount(int i) {
        this.sleepCount = i;
    }

    public int getWhichday() {
        return this.whichday;
    }

    public void setWhichday(int i) {
        this.whichday = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        return "SingleSleepItem{position=" + this.position + ", sleepCount=" + this.sleepCount + ", whichday=" + this.whichday + ", content='" + this.content + "'}";
    }
}
