package com.veepoo.protocol.model.datas;

import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MealInfo.class */
public class MealInfo {
    public int index;
    private float bgBeforeMeal;
    private float bgAfterMeal;
    public int bgBeforeMeal_mgDL;
    public int bgAfterMeal_mgDL;
    public int beforeMealTime;
    public int afterMealTime;
    public boolean isUnitMmolL;

    public MealInfo(int i) {
        this.bgBeforeMeal = 0.0f;
        this.bgAfterMeal = 0.0f;
        this.bgBeforeMeal_mgDL = 0;
        this.bgAfterMeal_mgDL = 0;
        this.isUnitMmolL = true;
        this.index = i;
        this.beforeMealTime = -1;
        this.afterMealTime = -1;
    }

    private String getBeforeTime24() {
        return String.format(Locale.CHINA, "(%02d:%02d)", Integer.valueOf(getBeforeHour()), Integer.valueOf(getBeforeMinute()));
    }

    private String getBeforeTime12() {
        boolean z = false;
        int beforeHour = getBeforeHour();
        int i = beforeHour;
        int beforeMinute = getBeforeMinute();
        if (beforeHour >= 12) {
            if (i != 12) {
                i -= 12;
            }
            z = true;
        }
        if (!z && i == 0 && beforeMinute == 0) {
            i = 12;
        }
        int i2 = this.beforeMealTime;
        if (i2 > 0 && i2 < 60) {
            i = 12;
        }
        boolean z2 = z;
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(beforeMinute);
        objArr[2] = z2 ? "pm" : "am";
        return String.format(locale, "(%02d:%02d %s)", objArr);
    }

    private String getAfterTime24() {
        return String.format(Locale.CHINA, "(%02d:%02d)", Integer.valueOf(getAfterHour()), Integer.valueOf(getAfterMinute()));
    }

    private String getAfterTime12() {
        boolean z = false;
        int afterHour = getAfterHour();
        int i = afterHour;
        int afterMinute = getAfterMinute();
        if (afterHour >= 12) {
            if (i != 12) {
                i -= 12;
            }
            z = true;
        }
        if (!z && i == 0 && afterMinute == 0) {
            i = 12;
        }
        int i2 = this.afterMealTime;
        if (i2 > 0 && i2 < 60) {
            i = 12;
        }
        boolean z2 = z;
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(afterMinute);
        objArr[2] = z2 ? "pm" : "am";
        return String.format(locale, "(%02d:%02d %s)", objArr);
    }

    public static int mmolL2mgdL(float f) {
        return (int) (((double) (f * 18.0f)) + 0.5d);
    }

    public static float mgdL2mmolL(int i) {
        return i / 18.0f;
    }

    public void copy(MealInfo mealInfo) {
        float f = mealInfo.bgAfterMeal;
        this.bgAfterMeal = f;
        this.bgBeforeMeal = mealInfo.bgBeforeMeal;
        this.afterMealTime = mealInfo.afterMealTime;
        this.beforeMealTime = mealInfo.beforeMealTime;
        this.bgAfterMeal_mgDL = mmolL2mgdL(f);
        this.bgBeforeMeal_mgDL = mmolL2mgdL(this.bgBeforeMeal);
    }

    public void checkSelf() {
        if (this.bgBeforeMeal == 0.0f) {
            this.beforeMealTime = -1;
        }
        if (this.bgAfterMeal == 0.0f) {
            this.afterMealTime = -1;
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public float getBgBeforeMeal() {
        return this.bgBeforeMeal;
    }

    public String getBgBeforeMealStr() {
        return this.isUnitMmolL ? this.bgBeforeMeal + "" : this.bgBeforeMeal_mgDL + "";
    }

    public String getBgAfterMealStr() {
        return this.isUnitMmolL ? this.bgAfterMeal + "" : this.bgAfterMeal_mgDL + "";
    }

    public void setBgBeforeMeal(float f) {
        if (f <= 53.0f || this.isUnitMmolL) {
            this.bgBeforeMeal = f;
            this.bgBeforeMeal_mgDL = mmolL2mgdL(f);
        } else {
            int i = (int) f;
            this.bgBeforeMeal_mgDL = i;
            this.bgBeforeMeal = mgdL2mmolL(i);
        }
    }

    public float getBgAfterMeal() {
        return this.bgAfterMeal;
    }

    public void setBgAfterMeal(float f) {
        if (f <= 53.0f || this.isUnitMmolL) {
            this.bgAfterMeal = f;
            this.bgAfterMeal_mgDL = mmolL2mgdL(f);
        } else {
            int i = (int) f;
            this.bgAfterMeal_mgDL = i;
            this.bgAfterMeal = mgdL2mmolL(i);
        }
    }

    public int getBgBeforeMeal_mgDL() {
        return this.bgBeforeMeal_mgDL;
    }

    public void setBgBeforeMeal_mgDL(int i) {
        this.bgBeforeMeal_mgDL = i;
        this.bgBeforeMeal = mgdL2mmolL(i);
    }

    public int getBgAfterMeal_mgDL() {
        return this.bgAfterMeal_mgDL;
    }

    public void setBgAfterMeal_mgDL(int i) {
        this.bgAfterMeal_mgDL = i;
        this.bgAfterMeal = mgdL2mmolL(i);
    }

    public int getBeforeMealTime() {
        return this.beforeMealTime;
    }

    public void setBeforeMealTime(int i) {
        this.beforeMealTime = i;
    }

    public int getAfterMealTime() {
        return this.afterMealTime;
    }

    public void setAfterMealTime(int i) {
        this.afterMealTime = i;
    }

    public int getBeforeHour() {
        return this.beforeMealTime / 60;
    }

    public int getBeforeMinute() {
        return this.beforeMealTime % 60;
    }

    public int getAfterHour() {
        return this.afterMealTime / 60;
    }

    public int getAfterMinute() {
        return this.afterMealTime % 60;
    }

    public boolean isBeforeInfoIntact() {
        return (this.beforeMealTime == -1 || this.bgBeforeMeal == 0.0f) ? false : true;
    }

    public boolean isBeforeInfoNotSetting() {
        return this.beforeMealTime == -1 && this.bgBeforeMeal == 0.0f;
    }

    public boolean isAfterInfoIntact() {
        return (this.afterMealTime == -1 || this.bgAfterMeal == 0.0f) ? false : true;
    }

    public boolean isAfterInfoNotSetting() {
        return this.afterMealTime == -1 && this.bgAfterMeal == 0.0f;
    }

    public boolean isMealInfoPerfectly() {
        if (this.afterMealTime * this.bgAfterMeal >= 0.0f && this.beforeMealTime * this.bgBeforeMeal >= 0.0f) {
            return !isAllNotSetting();
        }
        return false;
    }

    public boolean isHaveSettingButNotPerfectly() {
        if (isAllNotSetting()) {
            return false;
        }
        int i = this.afterMealTime;
        float f = this.bgAfterMeal;
        if (i + f > 0.0f && (i == -1 || f == 0.0f)) {
            return true;
        }
        int i2 = this.beforeMealTime;
        float f2 = this.bgBeforeMeal;
        if (i2 + f2 > 0.0f) {
            return i2 == -1 || f2 == 0.0f;
        }
        return false;
    }

    public boolean isAllNotSetting() {
        return isBeforeInfoNotSetting() && isAfterInfoNotSetting();
    }

    public boolean isBGValueRightful() {
        return this.bgAfterMeal - this.bgBeforeMeal > 0.0f;
    }

    public boolean isSubBGInRightfulRang() {
        float f = this.bgAfterMeal;
        if (f == 0.0f) {
            return true;
        }
        float f2 = this.bgBeforeMeal;
        if (f2 == 0.0f) {
            return true;
        }
        float f3 = f - f2;
        return f3 > 0.0f && f3 <= 4.5f;
    }

    public boolean isIntactMeal() {
        return ((this.bgBeforeMeal == 0.0f || this.beforeMealTime == -1) && (this.bgAfterMeal == 0.0f || this.afterMealTime == -1)) ? false : true;
    }

    public boolean isAllSetting() {
        return (this.bgBeforeMeal == 0.0f || this.beforeMealTime == -1 || this.bgAfterMeal == 0.0f || this.afterMealTime == -1) ? false : true;
    }

    public boolean isTimeValid() {
        int i;
        int i2 = this.afterMealTime;
        return i2 == -1 || (i = this.beforeMealTime) == -1 || i2 > i;
    }

    public boolean isBloodGlucoseValid() {
        float f = this.bgBeforeMeal;
        if (f == 0.0f) {
            return true;
        }
        float f2 = this.bgAfterMeal;
        return f2 == 0.0f || f2 - f > 0.0f;
    }

    public boolean isBloodGlucoseValueWarning() {
        return (isBloodGlucoseValid() && isSubBGInRightfulRang()) ? false : true;
    }

    public boolean isIntactButTimeError() {
        int i;
        int i2 = this.afterMealTime;
        return i2 > 0 && (i = this.beforeMealTime) > 0 && i >= i2;
    }

    public String getBeforeTime(boolean z) {
        return z ? getBeforeTime24() : getBeforeTime12();
    }

    public String getAfterTime(boolean z) {
        return z ? getAfterTime24() : getAfterTime12();
    }

    public String toString() {
        return "MealInfo{index=" + this.index + ", bgBeforeMeal=" + this.bgBeforeMeal + ", bgAfterMeal=" + this.bgAfterMeal + ", beforeMealTime=" + this.beforeMealTime + ", afterMealTime=" + this.afterMealTime + '}';
    }

    public MealInfo(int i, float f, float f2, int i2, int i3) {
        this.bgBeforeMeal_mgDL = 0;
        this.bgAfterMeal_mgDL = 0;
        this.isUnitMmolL = true;
        this.index = i;
        this.bgBeforeMeal = f;
        this.bgAfterMeal = f2;
        this.beforeMealTime = i2;
        this.afterMealTime = i3;
    }
}
