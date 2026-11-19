package com.veepoo.protocol.model.datas.weather;

import com.veepoo.protocol.model.datas.TimeData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/weather/WeatherEvery3Hour.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\"\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004BM\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0003\u0010\u0010J\u0013\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u000e\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\bJ\b\u0010/\u001a\u00020\rH\u0016J\u0006\u00100\u001a\u00020\rR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001e\u0010\n\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b#\u0010\u001b\"\u0004\b$\u0010\u001dR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u00061"}, d2 = {"Lcom/veepoo/protocol/model/datas/weather/WeatherEvery3Hour;", "", "", "<init>", "()V", "timeBean", "Lcom/veepoo/protocol/model/datas/TimeData;", "temperatureF", "", "temperatureC", "yellowLevel", "weatherState", "windLevel", "", "canSeeWay", "", "(Lcom/veepoo/protocol/model/datas/TimeData;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;D)V", "weatherEvery3HourFlag", "getWeatherEvery3HourFlag", "()Ljava/lang/String;", "setWeatherEvery3HourFlag", "(Ljava/lang/String;)V", "getTimeBean", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setTimeBean", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "getTemperatureF", "()Ljava/lang/Integer;", "setTemperatureF", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTemperatureC", "setTemperatureC", "getYellowLevel", "setYellowLevel", "getWeatherState", "setWeatherState", "getWindLevel", "setWindLevel", "getCanSeeWay", "()D", "setCanSeeWay", "(D)V", "compareTo", "other", "getWeatherDes", "weather", "toString", "toDes", "vpprotocol_release"})
public final class WeatherEvery3Hour implements Comparable<Object> {

    @Nullable
    private String weatherEvery3HourFlag;

    @Nullable
    private TimeData timeBean;

    @Nullable
    private Integer temperatureF;

    @Nullable
    private Integer temperatureC;

    @Nullable
    private Integer yellowLevel;

    @Nullable
    private Integer weatherState;

    @Nullable
    private String windLevel;
    private double canSeeWay;

    public WeatherEvery3Hour() {
        this.temperatureF = 0;
    }

    @Nullable
    public final String getWeatherEvery3HourFlag() {
        return this.weatherEvery3HourFlag;
    }

    public final void setWeatherEvery3HourFlag(@Nullable String str) {
        this.weatherEvery3HourFlag = str;
    }

    @Nullable
    public final TimeData getTimeBean() {
        return this.timeBean;
    }

    public final void setTimeBean(@Nullable TimeData timeData) {
        this.timeBean = timeData;
    }

    @Nullable
    public final Integer getTemperatureF() {
        return this.temperatureF;
    }

    public final void setTemperatureF(@Nullable Integer num) {
        this.temperatureF = num;
    }

    @Nullable
    public final Integer getTemperatureC() {
        return this.temperatureC;
    }

    public final void setTemperatureC(@Nullable Integer num) {
        this.temperatureC = num;
    }

    @Nullable
    public final Integer getYellowLevel() {
        return this.yellowLevel;
    }

    public final void setYellowLevel(@Nullable Integer num) {
        this.yellowLevel = num;
    }

    @Nullable
    public final Integer getWeatherState() {
        return this.weatherState;
    }

    public final void setWeatherState(@Nullable Integer num) {
        this.weatherState = num;
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

    @Override // java.lang.Comparable
    public int compareTo(@Nullable Object other) {
        TimeData timeBean;
        WeatherEveryDay weatherEveryDay = (WeatherEveryDay) other;
        int iCompareTo = 0;
        String databaseDateString = (weatherEveryDay == null || (timeBean = weatherEveryDay.getTimeBean()) == null) ? null : timeBean.toDatabaseDateString();
        TimeData timeData = this.timeBean;
        if (StringsKt.equals$default(databaseDateString, timeData != null ? timeData.toDatabaseDateString() : null, false, 2, (Object) null)) {
            Intrinsics.checkNotNull(databaseDateString);
            TimeData timeData2 = this.timeBean;
            Intrinsics.checkNotNull(timeData2);
            String databaseDateString2 = timeData2.toDatabaseDateString();
            Intrinsics.checkNotNullExpressionValue(databaseDateString2, "toDatabaseDateString(...)");
            iCompareTo = databaseDateString.compareTo(databaseDateString2);
        }
        return -iCompareTo;
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

    @NotNull
    public String toString() {
        return "WeatherEvery3Hour(weatherEvery3HourFlag=" + this.weatherEvery3HourFlag + ", timeBean=" + this.timeBean + ", temperatureF=" + this.temperatureF + ", temperatureC=" + this.temperatureC + ", yellowLevel=" + this.yellowLevel + ", weatherState=" + this.weatherState + ", windLevel=" + this.windLevel + ", canSeeWay=" + this.canSeeWay + ")\n";
    }

    @NotNull
    public final String toDes() {
        TimeData timeData = this.timeBean;
        String fullDateTimeString = timeData != null ? timeData.toFullDateTimeString() : null;
        Integer num = this.temperatureC;
        Integer num2 = this.temperatureF;
        Integer num3 = this.weatherState;
        Intrinsics.checkNotNull(num3);
        return fullDateTimeString + " 摄氏度=" + num + "  华氏度=" + num2 + " 天气=" + getWeatherDes(num3.intValue()) + " 紫外线强度=" + this.yellowLevel + " 风速=" + this.windLevel + " 能见度=" + this.canSeeWay;
    }

    public WeatherEvery3Hour(@Nullable TimeData timeData, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str, double d) {
        this.temperatureF = 0;
        this.weatherEvery3HourFlag = timeData != null ? timeData.getDateAndClockForDb() : null;
        this.temperatureF = num;
        this.temperatureC = num2;
        this.yellowLevel = num3;
        this.weatherState = num4;
        this.windLevel = str;
        this.canSeeWay = d;
        this.timeBean = timeData;
    }
}
