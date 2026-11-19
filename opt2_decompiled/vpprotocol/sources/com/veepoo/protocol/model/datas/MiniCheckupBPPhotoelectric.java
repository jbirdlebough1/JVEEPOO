package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupBPPhotoelectric.class */
public class MiniCheckupBPPhotoelectric {
    private int systolicBloodPressure;
    private int diastolicBloodPressure;

    public MiniCheckupBPPhotoelectric() {
    }

    public MiniCheckupBPPhotoelectric(int i, int i2) {
        this.systolicBloodPressure = i;
        this.diastolicBloodPressure = i2;
    }

    public int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public void setSystolicBloodPressure(int i) {
        this.systolicBloodPressure = i;
    }

    public int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(int i) {
        this.diastolicBloodPressure = i;
    }

    public String toString() {
        return "MiniCheckupBPAirPump{systolicBloodPressure=" + this.systolicBloodPressure + ", diastolicBloodPressure=" + this.diastolicBloodPressure + '}';
    }
}
