package com.veepoo.protocol.model.datas.weather;

import com.veepoo.protocol.model.datas.TimeData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/weather/WeatherEveryDay.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b(\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004Bk\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0003\u0010\u0013J\u0006\u00104\u001a\u00020\u0010J\u000e\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\bJ\u0013\u00107\u001a\u00020\b2\b\u00108\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u00109\u001a\u00020\u0010H\u0016R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001e\u0010\n\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b$\u0010\u001e\"\u0004\b%\u0010 R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010 R\u001e\u0010\f\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R\u001e\u0010\r\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b*\u0010\u001e\"\u0004\b+\u0010 R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b,\u0010\u001e\"\u0004\b-\u0010 R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0016\"\u0004\b/\u0010\u0018R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006:"}, d2 = {"Lcom/veepoo/protocol/model/datas/weather/WeatherEveryDay;", "", "", "<init>", "()V", "timeBean", "Lcom/veepoo/protocol/model/datas/TimeData;", "temperatureMaxF", "", "temperatureMinF", "temperatureMaxC", "temperatureMinC", "yellowLevel", "weatherStateWhiteDay", "weatherStateNightDay", "windLevel", "", "canSeeWay", "", "(Lcom/veepoo/protocol/model/datas/TimeData;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;D)V", "weatherEveryDayFlag", "getWeatherEveryDayFlag", "()Ljava/lang/String;", "setWeatherEveryDayFlag", "(Ljava/lang/String;)V", "getTimeBean", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setTimeBean", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "getTemperatureMaxF", "()Ljava/lang/Integer;", "setTemperatureMaxF", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTemperatureMinF", "setTemperatureMinF", "getTemperatureMaxC", "setTemperatureMaxC", "getTemperatureMinC", "setTemperatureMinC", "getYellowLevel", "setYellowLevel", "getWeatherStateWhiteDay", "setWeatherStateWhiteDay", "getWeatherStateNightDay", "setWeatherStateNightDay", "getWindLevel", "setWindLevel", "getCanSeeWay", "()D", "setCanSeeWay", "(D)V", "toDes", "getWeatherDes", "weather", "compareTo", "other", "toString", "vpprotocol_release"})
public final class WeatherEveryDay implements Comparable<Object> {

    @Nullable
    private String weatherEveryDayFlag;

    @Nullable
    private TimeData timeBean;

    @Nullable
    private Integer temperatureMaxF;

    @Nullable
    private Integer temperatureMinF;

    @Nullable
    private Integer temperatureMaxC;

    @Nullable
    private Integer temperatureMinC;

    @Nullable
    private Integer yellowLevel;

    @Nullable
    private Integer weatherStateWhiteDay;

    @Nullable
    private Integer weatherStateNightDay;

    @Nullable
    private String windLevel;
    private double canSeeWay;

    public WeatherEveryDay() {
        this.temperatureMaxF = 0;
    }

    @Nullable
    public final String getWeatherEveryDayFlag() {
        return this.weatherEveryDayFlag;
    }

    public final void setWeatherEveryDayFlag(@Nullable String str) {
        this.weatherEveryDayFlag = str;
    }

    @Nullable
    public final TimeData getTimeBean() {
        return this.timeBean;
    }

    public final void setTimeBean(@Nullable TimeData timeData) {
        this.timeBean = timeData;
    }

    @Nullable
    public final Integer getTemperatureMaxF() {
        return this.temperatureMaxF;
    }

    public final void setTemperatureMaxF(@Nullable Integer num) {
        this.temperatureMaxF = num;
    }

    @Nullable
    public final Integer getTemperatureMinF() {
        return this.temperatureMinF;
    }

    public final void setTemperatureMinF(@Nullable Integer num) {
        this.temperatureMinF = num;
    }

    @Nullable
    public final Integer getTemperatureMaxC() {
        return this.temperatureMaxC;
    }

    public final void setTemperatureMaxC(@Nullable Integer num) {
        this.temperatureMaxC = num;
    }

    @Nullable
    public final Integer getTemperatureMinC() {
        return this.temperatureMinC;
    }

    public final void setTemperatureMinC(@Nullable Integer num) {
        this.temperatureMinC = num;
    }

    @Nullable
    public final Integer getYellowLevel() {
        return this.yellowLevel;
    }

    public final void setYellowLevel(@Nullable Integer num) {
        this.yellowLevel = num;
    }

    @Nullable
    public final Integer getWeatherStateWhiteDay() {
        return this.weatherStateWhiteDay;
    }

    public final void setWeatherStateWhiteDay(@Nullable Integer num) {
        this.weatherStateWhiteDay = num;
    }

    @Nullable
    public final Integer getWeatherStateNightDay() {
        return this.weatherStateNightDay;
    }

    public final void setWeatherStateNightDay(@Nullable Integer num) {
        this.weatherStateNightDay = num;
    }

    @Nullable
    public final String getWindLevel() {
        return this.windLevel;
    }

    public final void setWindLevel(@Nullable String str) {
        this.windLevel = str;
    }

    public final double getCanSeeWay() {
        return this.canSeeWay;
    }

    public final void setCanSeeWay(double d) {
        this.canSeeWay = d;
    }

    @NotNull
    public final String toDes() {
        TimeData timeData = this.timeBean;
        String fullDateTimeString = timeData != null ? timeData.toFullDateTimeString() : null;
        Integer num = this.temperatureMinC;
        Integer num2 = this.temperatureMaxC;
        Integer num3 = this.temperatureMinF;
        Integer num4 = this.temperatureMaxF;
        Integer num5 = this.weatherStateWhiteDay;
        Intrinsics.checkNotNull(num5);
        String weatherDes = getWeatherDes(num5.intValue());
        Integer num6 = this.weatherStateNightDay;
        Intrinsics.checkNotNull(num6);
        return fullDateTimeString + " 摄氏度=[" + num + " - " + num2 + "] 华氏度=[" + num3 + " - " + num4 + "] 白天天气=" + weatherDes + " 夜间天气=" + getWeatherDes(num6.intValue()) + " 紫外线强度=" + this.yellowLevel + " 风速=" + this.windLevel + " 能见度=" + this.canSeeWay;
    }

    @NotNull
    public final String getWeatherDes(int weather) {
        if (weather >= 0 && weather < 5) {
            return "晴";
        }
        if (4 <= weather && weather < 13) {
            return "晴转多云";
        }
        if (13 <= weather && weather < 17) {
            return "阴天";
        }
        if (17 <= weather && weather < 21) {
            return "阵雨";
        }
        if (21 <= weather && weather < 25) {
            return "雷阵雨";
        }
        if (25 <= weather && weather < 33) {
            return "冰雹";
        }
        if (33 <= weather && weather < 41) {
            return "小雨";
        }
        if (41 <= weather && weather < 49) {
            return "中雨";
        }
        if (49 <= weather && weather < 57) {
            return "大雨";
        }
        if (57 <= weather && weather < 73) {
            return "暴雨";
        }
        if (73 <= weather && weather < 85) {
            return "小雪";
        }
        if (85 > weather || weather >= 101) {
            return (101 > weather || weather >= 156) ? "未知" : "多云";
        }
        return "大雪";
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nullable Object other) {
        TimeData timeData;
        WeatherEveryDay weatherEveryDay = (WeatherEveryDay) other;
        int iCompareTo = 0;
        String databaseDateString = (weatherEveryDay == null || (timeData = weatherEveryDay.timeBean) == null) ? null : timeData.toDatabaseDateString();
        TimeData timeData2 = this.timeBean;
        if (StringsKt.equals$default(databaseDateString, timeData2 != null ? timeData2.toDatabaseDateString() : null, false, 2, (Object) null)) {
            Intrinsics.checkNotNull(databaseDateString);
            TimeData timeData3 = this.timeBean;
            Intrinsics.checkNotNull(timeData3);
            String databaseDateString2 = timeData3.toDatabaseDateString();
            Intrinsics.checkNotNullExpressionValue(databaseDateString2, "toDatabaseDateString(...)");
            iCompareTo = databaseDateString.compareTo(databaseDateString2);
        }
        return -iCompareTo;
    }

    @NotNull
    public String toString() {
        return "\nWeatherEveryDay(weatherEveryDayFlag=" + this.weatherEveryDayFlag + ", timeBean=" + this.timeBean + ", temperatureMaxF=" + this.temperatureMaxF + ", temperatureMinF=" + this.temperatureMinF + ", temperatureMaxC=" + this.temperatureMaxC + ", temperatureMinC=" + this.temperatureMinC + ", yellowLevel=" + this.yellowLevel + ", weatherStateWhiteDay=" + this.weatherStateWhiteDay + ", weatherStateNightDay=" + this.weatherStateNightDay + ", windLevel=" + this.windLevel + ", canSeeWay=" + this.canSeeWay + ")\n";
    }

    public WeatherEveryDay(@Nullable TimeData timeData, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6, @Nullable Integer num7, @Nullable String str, double d) {
        this.temperatureMaxF = 0;
        this.weatherEveryDayFlag = timeData != null ? timeData.getDateAndClockForDb() : null;
        this.timeBean = timeData;
        this.temperatureMaxF = num;
        this.temperatureMinF = num2;
        this.temperatureMaxC = num3;
        this.temperatureMinC = num4;
        this.yellowLevel = num5;
        this.weatherStateWhiteDay = num6;
        this.weatherStateNightDay = num7;
        this.windLevel = str;
        this.canSeeWay = d;
    }
}
