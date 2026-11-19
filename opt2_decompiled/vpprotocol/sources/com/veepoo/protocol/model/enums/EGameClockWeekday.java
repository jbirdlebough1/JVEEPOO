package com.veepoo.protocol.model.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGameClockWeekday.class */
public enum EGameClockWeekday {
    NONE(0, "无", "None"),
    MONDAY(1, "周一", "Mon"),
    TUESDAY(2, "周二", "Tue"),
    WEDNESDAY(4, "周三", "Wed"),
    THURSDAY(8, "周四", "Thu"),
    FRIDAY(16, "周五", "Fri"),
    SATURDAY(32, "周六", "Sat"),
    SUNDAY(64, "周日", "Sun"),
    EVERY_DAY(VALID_MASK, "每天", "Every day");

    public static final int VALID_MASK = 127;
    private final int bit;
    private final String cnName;
    private final String enName;

    EGameClockWeekday(int i, String str, String str2) {
        this.bit = i;
        this.cnName = str;
        this.enName = str2;
    }

    public static EGameClockWeekday[] weekdays() {
        return new EGameClockWeekday[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
    }

    public static int toMask(Collection<EGameClockWeekday> collection) {
        return (collection == null || collection.isEmpty()) ? NONE.bit : toMask((EGameClockWeekday[]) collection.toArray(new EGameClockWeekday[0]));
    }

    public static List<EGameClockWeekday> fromMask(int i) {
        ArrayList arrayList = new ArrayList(7);
        for (EGameClockWeekday eGameClockWeekday : weekdays()) {
            if ((i & eGameClockWeekday.bit) != 0) {
                arrayList.add(eGameClockWeekday);
            }
        }
        return arrayList;
    }

    public static boolean isEveryDay(int i) {
        return (i & VALID_MASK) == EVERY_DAY.bit;
    }

    public static boolean isValidMask(int i) {
        return i > 0 && (i & (-128)) == 0;
    }

    public static boolean contains(int i, EGameClockWeekday eGameClockWeekday) {
        if (eGameClockWeekday == null || eGameClockWeekday == NONE) {
            return false;
        }
        int i2 = eGameClockWeekday.bit;
        return (i & i2) == i2;
    }

    public static String toDisplayString(int i, boolean z) {
        if (!isValidMask(i)) {
            return z ? NONE.cnName : NONE.enName;
        }
        if (isEveryDay(i)) {
            return z ? EVERY_DAY.cnName : EVERY_DAY.enName;
        }
        StringBuilder sb = new StringBuilder();
        for (EGameClockWeekday eGameClockWeekday : fromMask(i)) {
            if (sb.length() > 0) {
                sb.append(z ? "、" : ", ");
            }
            sb.append(z ? eGameClockWeekday.cnName : eGameClockWeekday.enName);
        }
        return sb.toString();
    }

    public int getBit() {
        return this.bit;
    }

    public String getCnName() {
        return this.cnName;
    }

    public String getEnName() {
        return this.enName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.cnName;
    }

    public static int toMask(EGameClockWeekday... eGameClockWeekdayArr) {
        if (eGameClockWeekdayArr == null || eGameClockWeekdayArr.length == 0) {
            return NONE.bit;
        }
        int i = 0;
        for (EGameClockWeekday eGameClockWeekday : eGameClockWeekdayArr) {
            if (eGameClockWeekday != null && eGameClockWeekday != NONE) {
                EGameClockWeekday eGameClockWeekday2 = EVERY_DAY;
                if (eGameClockWeekday == eGameClockWeekday2) {
                    return eGameClockWeekday2.bit;
                }
                i |= eGameClockWeekday.bit;
            }
        }
        return i & VALID_MASK;
    }
}
