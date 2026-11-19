package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/OadSetting.class */
public class OadSetting {
    private String deviceAddress;
    private String deviceVersion;
    private String deviceTestVersion;
    private int deviceNumber;
    private boolean isOadModel;
    private boolean isDebug;
    private String hostUrl;
    private boolean isAutoDownload;

    public OadSetting(String str, String str2, String str3, int i, boolean z) {
        this.hostUrl = null;
        this.isAutoDownload = true;
        this.deviceAddress = str;
        this.deviceVersion = str2;
        this.deviceTestVersion = str3;
        this.deviceNumber = i;
        this.isOadModel = z;
    }

    public String getHostUrl() {
        return this.hostUrl;
    }

    public void setHostUrl(String str) {
        this.hostUrl = str;
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setDeviceAddress(String str) {
        this.deviceAddress = str;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public void setDeviceTestVersion(String str) {
        this.deviceTestVersion = str;
    }

    public void setDeviceNumber(int i) {
        this.deviceNumber = i;
    }

    public void setOadModel(boolean z) {
        this.isOadModel = z;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public String getDeviceTestVersion() {
        return this.deviceTestVersion;
    }

    public int getDeviceNumber() {
        return this.deviceNumber;
    }

    public boolean isOadModel() {
        return this.isOadModel;
    }

    public boolean isDebug() {
        return this.isDebug;
    }

    public void setDebug(boolean z) {
        this.isDebug = z;
    }

    public boolean isAutoDownload() {
        return this.isAutoDownload;
    }

    public void setAutoDownload(boolean z) {
        this.isAutoDownload = z;
    }

    public OadSetting(String str, String str2, String str3, int i, boolean z, boolean z2) {
        this.hostUrl = null;
        this.isAutoDownload = true;
        this.deviceAddress = str;
        this.deviceVersion = str2;
        this.deviceTestVersion = str3;
        this.deviceNumber = i;
        this.isOadModel = z;
        this.isDebug = z2;
    }

    public OadSetting(String str, String str2, String str3, int i, boolean z, boolean z2, String str4) {
        this.isAutoDownload = true;
        this.deviceAddress = str;
        this.deviceVersion = str2;
        this.deviceTestVersion = str3;
        this.deviceNumber = i;
        this.isOadModel = z;
        this.isDebug = z2;
        this.hostUrl = str4;
    }
}
