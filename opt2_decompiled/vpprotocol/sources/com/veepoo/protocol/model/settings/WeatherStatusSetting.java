package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EWeatherType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/WeatherStatusSetting.class */
public class WeatherStatusSetting {
    private int crc;
    private boolean isOpen;
    private EWeatherType eWeather;

    public WeatherStatusSetting() {
    }

    public WeatherStatusSetting(int i, boolean z, EWeatherType eWeatherType) {
        this.crc = i;
        this.isOpen = z;
        this.eWeather = eWeatherType;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public EWeatherType geteWeather() {
        return this.eWeather;
    }

    public void seteWeather(EWeatherType eWeatherType) {
        this.eWeather = eWeatherType;
    }

    public String toString() {
        return "WeatherStatusSetting{crc=" + this.crc + ", isOpen=" + this.isOpen + ", eWeather=" + this.eWeather + '}';
    }
}
