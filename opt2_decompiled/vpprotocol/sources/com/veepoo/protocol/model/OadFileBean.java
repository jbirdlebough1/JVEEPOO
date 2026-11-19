package com.veepoo.protocol.model;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/OadFileBean.class */
public class OadFileBean {
    private String deviceNumber;
    private String deviceVersion;
    private String downUrl;
    private String size;
    private String md5;
    private String des;

    public String getDes() {
        return this.des;
    }

    public void setDes(String str) {
        this.des = str;
    }

    public String getDeviceNumber() {
        return this.deviceNumber;
    }

    public void setDeviceNumber(String str) {
        this.deviceNumber = str;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public String getDownUrl() {
        return this.downUrl;
    }

    public void setDownUrl(String str) {
        this.downUrl = str;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public String toString() {
        return "OadFileBean{deviceNumber='" + this.deviceNumber + "', deviceVersion='" + this.deviceVersion + "', size='" + this.size + "', md5='" + this.md5 + "', downUrl='" + this.downUrl + "',des='" + this.des + "'}";
    }
}
