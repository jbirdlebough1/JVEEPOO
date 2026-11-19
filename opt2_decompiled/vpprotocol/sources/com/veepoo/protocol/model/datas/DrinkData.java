package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/DrinkData.class */
public class DrinkData {
    private TimeData drinkStartTime;
    private TimeData drinkWakeupTime;
    private int drinkLevel;
    private boolean isDrinkAllergy;

    public TimeData getDrinkStartTime() {
        return this.drinkStartTime;
    }

    public void setDrinkStartTime(TimeData timeData) {
        this.drinkStartTime = timeData;
    }

    public TimeData getDrinkWakeupTime() {
        return this.drinkWakeupTime;
    }

    public void setDrinkWakeupTime(TimeData timeData) {
        this.drinkWakeupTime = timeData;
    }

    public int getDrinkLevel() {
        return this.drinkLevel;
    }

    public void setDrinkLevel(int i) {
        this.drinkLevel = i;
    }

    public boolean isDrinkAllergy() {
        return this.isDrinkAllergy;
    }

    public void setDrinkAllergy(boolean z) {
        this.isDrinkAllergy = z;
    }

    public String toString() {
        return "DrinkData{drinkStartTime=" + this.drinkStartTime + ", drinkWakeupTime=" + this.drinkWakeupTime + ", drinkLevel=" + this.drinkLevel + ", isDrinkAllergy=" + this.isDrinkAllergy + '}';
    }
}
