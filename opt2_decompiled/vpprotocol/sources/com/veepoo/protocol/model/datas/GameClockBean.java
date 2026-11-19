package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EGameClockType;
import com.veepoo.protocol.model.enums.EGameClockWeekday;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/GameClockBean.class */
public class GameClockBean implements Serializable, Comparable<GameClockBean> {
    private static final long serialVersionUID = 1;
    public static final int MIN_CLOCK_ID = 1;
    public static final int MAX_CLOCK_ID = 10;
    public static final int MIN_DURATION_MINUTES = 5;
    public static final int MAX_CLOCK_COUNT = 10;
    private int clockId;
    private EGameClockType clockType;
    private int year;
    private int month;
    private int day;
    private int repeatWeekMask;
    private int startHour;
    private int startMinute;
    private int startSecond;
    private int duration;
    private boolean open;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/GameClockBean$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EGameClockType.values().length];
            vp_a = iArr;
            try {
                iArr[EGameClockType.WEEKLY_REPEAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EGameClockType.SINGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EGameClockType.TEMPORARY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public GameClockBean() {
        this.clockId = 1;
        this.clockType = EGameClockType.WEEKLY_REPEAT;
        this.repeatWeekMask = EGameClockWeekday.EVERY_DAY.getBit();
        this.duration = 5;
        this.open = true;
    }

    public static GameClockBean weeklyRepeat(int i, int i2, int i3, int i4, EGameClockWeekday... eGameClockWeekdayArr) {
        return new GameClockBean(i, EGameClockType.WEEKLY_REPEAT, 0, 0, 0, (eGameClockWeekdayArr == null || eGameClockWeekdayArr.length == 0) ? EGameClockWeekday.EVERY_DAY.getBit() : EGameClockWeekday.toMask(eGameClockWeekdayArr), i2, i3, 0, i4, true);
    }

    public static GameClockBean single(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return new GameClockBean(i, EGameClockType.SINGLE, i2, i3, i4, EGameClockWeekday.NONE.getBit(), i5, i6, 0, i7, true);
    }

    public static GameClockBean temporary(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return new GameClockBean(i, EGameClockType.TEMPORARY, i2, i3, i4, EGameClockWeekday.NONE.getBit(), i5, i6, 0, i7, true);
    }

    private void normalizeByType() {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || !eGameClockType.isWeeklyRepeat()) {
            this.repeatWeekMask = EGameClockWeekday.NONE.getBit();
            return;
        }
        this.year = 0;
        this.month = 0;
        this.day = 0;
        if (EGameClockWeekday.isValidMask(this.repeatWeekMask)) {
            return;
        }
        this.repeatWeekMask = EGameClockWeekday.EVERY_DAY.getBit();
    }

    private int getEndTotalSeconds() {
        return (this.startHour * 3600) + (this.startMinute * 60) + this.startSecond + (this.duration * 60);
    }

    private Calendar buildEndCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month - 1, this.day, this.startHour, this.startMinute, this.startSecond);
        calendar.add(12, this.duration);
        return calendar;
    }

    public static GameClockBean copyOne(GameClockBean gameClockBean) {
        if (gameClockBean == null) {
            return null;
        }
        return gameClockBean.copy();
    }

    public int getClockId() {
        return this.clockId;
    }

    public void setClockId(int i) {
        this.clockId = i;
    }

    public EGameClockType getClockType() {
        return this.clockType;
    }

    public void setClockType(EGameClockType eGameClockType) {
        if (eGameClockType == null) {
            eGameClockType = EGameClockType.WEEKLY_REPEAT;
        }
        this.clockType = eGameClockType;
        normalizeByType();
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getRepeatWeekMask() {
        return this.repeatWeekMask;
    }

    public void setRepeatWeekMask(int i) {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || !eGameClockType.isWeeklyRepeat()) {
            return;
        }
        this.repeatWeekMask = i & EGameClockWeekday.VALID_MASK;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public int getStartSecond() {
        return this.startSecond;
    }

    public void setStartSecond(int i) {
        this.startSecond = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean z) {
        this.open = z;
    }

    public boolean isRepeat() {
        EGameClockType eGameClockType = this.clockType;
        return eGameClockType != null && eGameClockType.isWeeklyRepeat();
    }

    public boolean isSingle() {
        return this.clockType == EGameClockType.SINGLE;
    }

    public boolean isTemporary() {
        return this.clockType == EGameClockType.TEMPORARY;
    }

    public int getGameType() {
        EGameClockType eGameClockType = this.clockType;
        return eGameClockType == null ? EGameClockType.WEEKLY_REPEAT.getValue() : eGameClockType.getValue();
    }

    public List<EGameClockWeekday> getRepeatWeekdays() {
        return EGameClockWeekday.fromMask(this.repeatWeekMask);
    }

    public void setRepeatWeekdays(List<EGameClockWeekday> list) {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || !eGameClockType.isWeeklyRepeat()) {
            return;
        }
        this.repeatWeekMask = EGameClockWeekday.toMask(list);
    }

    public boolean isRepeatOn(EGameClockWeekday eGameClockWeekday) {
        return EGameClockWeekday.contains(this.repeatWeekMask, eGameClockWeekday);
    }

    public void setRepeatOn(EGameClockWeekday eGameClockWeekday, boolean z) {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || !eGameClockType.isWeeklyRepeat() || eGameClockWeekday == null || eGameClockWeekday == EGameClockWeekday.NONE) {
            return;
        }
        int bit = eGameClockWeekday == EGameClockWeekday.EVERY_DAY ? 127 : eGameClockWeekday.getBit();
        if (z) {
            this.repeatWeekMask |= bit;
        } else {
            this.repeatWeekMask &= bit ^ (-1);
        }
        this.repeatWeekMask &= EGameClockWeekday.VALID_MASK;
    }

    public boolean isEveryDay() {
        return isRepeat() && EGameClockWeekday.isEveryDay(this.repeatWeekMask);
    }

    public String getRepeatDesc() {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null) {
            return "";
        }
        int i = vp_a.vp_a[eGameClockType.ordinal()];
        if (i == 1) {
            return EGameClockWeekday.toDisplayString(this.repeatWeekMask, true);
        }
        if (i != 2) {
            return i != 3 ? "" : "临时";
        }
        return "单次";
    }

    public int getEndHour() {
        return (getEndTotalSeconds() / 3600) % 24;
    }

    public int getEndMinute() {
        return (getEndTotalSeconds() / 60) % 60;
    }

    public int getEndSecond() {
        return getEndTotalSeconds() % 60;
    }

    public boolean isCrossDay() {
        return getEndTotalSeconds() >= 86400;
    }

    public String getTimeRangeDesc() {
        return String.format(Locale.US, "%02d:%02d - %s%02d:%02d", Integer.valueOf(this.startHour), Integer.valueOf(this.startMinute), isCrossDay() ? "次日 " : "", Integer.valueOf(getEndHour()), Integer.valueOf(getEndMinute()));
    }

    public boolean isValid() {
        return getVerifyError() == null;
    }

    public String getVerifyError() {
        int i;
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null) {
            return "时段类型不能为空 / clock type is null";
        }
        int i2 = this.clockId;
        if (i2 < 1 || i2 > 10) {
            return "时段 id 必须在 1~10 之间 / clock id out of range";
        }
        int i3 = this.duration;
        if (i3 < 5) {
            return "持续时长不能小于 5 分钟 / duration must be >= 5";
        }
        if (i3 > 65535) {
            return "持续时长超出协议上限 / duration exceeds protocol limit";
        }
        int i4 = this.startHour;
        if (i4 < 0 || i4 > 23) {
            return "开始时必须在 0~23 / start hour out of range";
        }
        int i5 = this.startMinute;
        if (i5 < 0 || i5 > 59) {
            return "开始分必须在 0~59 / start minute out of range";
        }
        int i6 = this.startSecond;
        if (i6 < 0 || i6 > 59) {
            return "开始秒必须在 0~59 / start second out of range";
        }
        if (eGameClockType.isWeeklyRepeat()) {
            if (EGameClockWeekday.isValidMask(this.repeatWeekMask)) {
                return null;
            }
            return "星期重复类型必须至少选择一天 / weekly repeat needs at least one weekday";
        }
        int i7 = this.year;
        if (i7 < 2000 || i7 > 2255) {
            return "年月日无效，年份需在 2000~2255 / invalid date";
        }
        int i8 = this.month;
        if (i8 < 1 || i8 > 12 || (i = this.day) < 1 || i > 31) {
            return "年月日无效 / invalid date";
        }
        return null;
    }

    public int getRemainSeconds() {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || eGameClockType.isWeeklyRepeat() || !this.open) {
            return 0;
        }
        long timeInMillis = (buildEndCalendar().getTimeInMillis() - System.currentTimeMillis()) / 1000;
        if (timeInMillis > 0) {
            return (int) Math.min(timeInMillis, 2147483647L);
        }
        return 0;
    }

    public boolean isExpired() {
        EGameClockType eGameClockType = this.clockType;
        return (eGameClockType == null || eGameClockType.isWeeklyRepeat() || buildEndCalendar().getTimeInMillis() > System.currentTimeMillis()) ? false : true;
    }

    public boolean isTemporaryGameTimePlanningValid() {
        if (this.clockType == EGameClockType.TEMPORARY && this.open) {
            return !isExpired();
        }
        return false;
    }

    public boolean isSingleGameTimeInvalid() {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || eGameClockType.isWeeklyRepeat()) {
            return false;
        }
        return isExpired();
    }

    public boolean isSingleGameTimeInvalidWithoutYMD() {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || eGameClockType.isWeeklyRepeat()) {
            return false;
        }
        int endTotalSeconds = getEndTotalSeconds() % 86400;
        Calendar calendar = Calendar.getInstance();
        return ((calendar.get(11) * 3600) + (calendar.get(12) * 60)) + calendar.get(13) >= endTotalSeconds;
    }

    public boolean isCurrentTimePassed() {
        return isSingleGameTimeInvalidWithoutYMD();
    }

    public void nextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month - 1, this.day);
        calendar.add(5, 1);
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
    }

    public void curDay() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
    }

    public GameClockBean copy() {
        return new GameClockBean(this.clockId, this.clockType, this.year, this.month, this.day, this.repeatWeekMask, this.startHour, this.startMinute, this.startSecond, this.duration, this.open);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GameClockBean)) {
            return false;
        }
        GameClockBean gameClockBean = (GameClockBean) obj;
        return this.clockId == gameClockBean.clockId && this.year == gameClockBean.year && this.month == gameClockBean.month && this.day == gameClockBean.day && this.repeatWeekMask == gameClockBean.repeatWeekMask && this.startHour == gameClockBean.startHour && this.startMinute == gameClockBean.startMinute && this.startSecond == gameClockBean.startSecond && this.duration == gameClockBean.duration && this.open == gameClockBean.open && this.clockType == gameClockBean.clockType;
    }

    public int hashCode() {
        int i = this.clockId * 31;
        EGameClockType eGameClockType = this.clockType;
        return ((((((((((((((((((i + (eGameClockType == null ? 0 : eGameClockType.hashCode())) * 31) + this.year) * 31) + this.month) * 31) + this.day) * 31) + this.repeatWeekMask) * 31) + this.startHour) * 31) + this.startMinute) * 31) + this.startSecond) * 31) + this.duration) * 31) + (this.open ? 1 : 0);
    }

    @Override // java.lang.Comparable
    public int compareTo(GameClockBean gameClockBean) {
        return Integer.compare(this.clockId, gameClockBean.clockId);
    }

    public String toString() {
        return "GameClockBean{clockId=" + this.clockId + ", clockType=" + this.clockType + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", repeatWeekMask=" + this.repeatWeekMask + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", startSecond=" + this.startSecond + ", duration=" + this.duration + ", open=" + this.open + '}';
    }

    public String toSmartStr() {
        String str;
        if (isRepeat()) {
            str = "重复：" + getRepeatDesc() + "(" + Integer.toBinaryString(this.repeatWeekMask) + ")";
        } else {
            Locale locale = Locale.US;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(this.year);
            objArr[1] = Integer.valueOf(this.month);
            objArr[2] = Integer.valueOf(this.day);
            objArr[3] = isTemporary() ? "临时" : "单次";
            str = String.format(locale, "日期：%04d-%02d-%02d（%s）", objArr);
        }
        Locale locale2 = Locale.US;
        Object[] objArr2 = new Object[6];
        objArr2[0] = Integer.valueOf(this.clockId);
        EGameClockType eGameClockType = this.clockType;
        objArr2[1] = eGameClockType == null ? "未知" : eGameClockType.getCnName();
        objArr2[2] = str;
        objArr2[3] = getTimeRangeDesc();
        objArr2[4] = Integer.valueOf(this.duration);
        objArr2[5] = this.open ? "已开启" : "已关闭";
        return String.format(locale2, "id:%d，类型：%s，%s，%s，持续%d分钟，%s", objArr2);
    }

    public GameClockBean(int i, EGameClockType eGameClockType, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        this.clockId = 1;
        EGameClockType eGameClockType2 = EGameClockType.WEEKLY_REPEAT;
        this.clockType = eGameClockType2;
        this.repeatWeekMask = EGameClockWeekday.EVERY_DAY.getBit();
        this.duration = 5;
        this.open = true;
        this.clockId = i;
        this.clockType = eGameClockType == null ? eGameClockType2 : eGameClockType;
        this.year = i2;
        this.month = i3;
        this.day = i4;
        this.repeatWeekMask = i5;
        this.startHour = i6;
        this.startMinute = i7;
        this.startSecond = i8;
        this.duration = i9;
        this.open = z;
        normalizeByType();
    }

    public void setRepeatWeekdays(EGameClockWeekday... eGameClockWeekdayArr) {
        EGameClockType eGameClockType = this.clockType;
        if (eGameClockType == null || !eGameClockType.isWeeklyRepeat()) {
            return;
        }
        this.repeatWeekMask = EGameClockWeekday.toMask(eGameClockWeekdayArr);
    }
}
