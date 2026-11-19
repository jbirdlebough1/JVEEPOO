package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EWeatherOprateStatus;
import com.veepoo.protocol.model.enums.EWeatherType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/WeatherStatusData.class */
public class WeatherStatusData {
    private EWeatherOprateStatus oprate;
    private int crc;
    private boolean isOpen;
    private EWeatherType weatherType;

    public WeatherStatusData() {
    }

    public WeatherStatusData(EWeatherOprateStatus eWeatherOprateStatus, int i, boolean z, EWeatherType eWeatherType) {
        this.oprate = eWeatherOprateStatus;
        this.crc = i;
        this.isOpen = z;
        this.weatherType = eWeatherType;
    }

    public EWeatherOprateStatus getOprate() {
        return this.oprate;
    }

    public void setOprate(EWeatherOprateStatus eWeatherOprateStatus) {
        this.oprate = eWeatherOprateStatus;
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

    public EWeatherType getWeatherType() {
        return this.weatherType;
    }

    public void setWeatherType(EWeatherType eWeatherType) {
        this.weatherType = eWeatherType;
    }

    public String toString() {
        return "WeatherStatusData{oprate=" + this.oprate + ", crc=" + this.crc + ", isOpen=" + this.isOpen + ", weatherType=" + this.weatherType + '}';
    }
}
