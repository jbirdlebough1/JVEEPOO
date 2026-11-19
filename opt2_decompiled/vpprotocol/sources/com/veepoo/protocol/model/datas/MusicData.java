package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MusicData.class */
public class MusicData {
    private String musicAppId;
    private String musicAlbum;
    private String musicName;
    private String singerName;
    private int palyStatus;
    private int musicVoiceLevel;

    public MusicData(String str, String str2, String str3, int i, int i2) {
        this.singerName = str;
        this.musicName = str2;
        this.musicAlbum = str3;
        this.palyStatus = i2;
        this.musicVoiceLevel = i;
    }

    public String getMusicAppId() {
        return this.musicAppId;
    }

    public void setMusicAppId(String str) {
        this.musicAppId = str;
    }

    public String getSingerName() {
        return this.singerName;
    }

    public void setSingerName(String str) {
        this.singerName = str;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public void setMusicName(String str) {
        this.musicName = str;
    }

    public String getMusicAlbum() {
        return this.musicAlbum;
    }

    public void setMusicAlbum(String str) {
        this.musicAlbum = str;
    }

    public int getPalyStatus() {
        return this.palyStatus;
    }

    public void setPalyStatus(int i) {
        this.palyStatus = i;
    }

    public int getMusicVoiceLevel() {
        return this.musicVoiceLevel;
    }

    public void setMusicVoiceLevel(int i) {
        this.musicVoiceLevel = i;
    }

    public String toString() {
        return "MusicBean{singerName='" + this.singerName + "', musicName='" + this.musicName + "', musicAlbum='" + this.musicAlbum + "', palyStatus=" + this.palyStatus + ", musicVoiceLevel=" + this.musicVoiceLevel + '}';
    }
}
