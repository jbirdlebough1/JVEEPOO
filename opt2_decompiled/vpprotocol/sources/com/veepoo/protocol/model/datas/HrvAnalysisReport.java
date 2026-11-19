package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HrvAnalysisReport.class */
public class HrvAnalysisReport {
    private int id;
    private String title;
    private String content;
    private int starCount;

    public HrvAnalysisReport(int i, String str, String str2, int i2) {
        this.id = i;
        this.title = str;
        this.content = str2;
        this.starCount = i2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getStarCount() {
        return this.starCount;
    }

    public void setStarCount(int i) {
        this.starCount = i;
    }

    public String toString() {
        return "HrvAnalysisReport{id=" + this.id + ", title='" + this.title + "', content='" + this.content + "', starCount='" + this.starCount + "'}";
    }
}
