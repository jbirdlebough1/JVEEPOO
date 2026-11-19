package com.veepoo.protocol.model;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/TUiTheme.class */
public class TUiTheme {
    private String crc;
    private String binProtocol;
    private String dialShape;
    private String fileUrl;
    private String previewUrl;
    private String name;
    private long fileLenght;
    private boolean available;

    public TUiTheme(String str, String str2, String str3, String str4, String str5) {
        this.crc = str;
        this.binProtocol = str2;
        this.dialShape = str3;
        this.fileUrl = str4;
        this.previewUrl = str5;
    }

    public String getCrc() {
        return this.crc;
    }

    public void setCrc(String str) {
        this.crc = str;
    }

    public String getBinProtocol() {
        return this.binProtocol;
    }

    public void setBinProtocol(String str) {
        this.binProtocol = str;
    }

    public String getDialShape() {
        return this.dialShape;
    }

    public void setDialShape(String str) {
        this.dialShape = str;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public String getPreviewUrl() {
        return this.previewUrl;
    }

    public void setPreviewUrl(String str) {
        this.previewUrl = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getFileLenght() {
        return this.fileLenght;
    }

    public void setFileLenght(long j) {
        this.fileLenght = j;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public String toString() {
        return "TUiTheme{crc='" + this.crc + "', binProtocol='" + this.binProtocol + "', dialShape='" + this.dialShape + "', fileUrl='" + this.fileUrl + "', previewUrl='" + this.previewUrl + "', name='" + this.name + "', fileLenght=" + this.fileLenght + ", available=" + this.available + '}';
    }
}
