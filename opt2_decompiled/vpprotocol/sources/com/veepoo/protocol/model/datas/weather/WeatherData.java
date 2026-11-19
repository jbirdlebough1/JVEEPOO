package com.veepoo.protocol.model.datas.weather;

import com.veepoo.protocol.model.datas.TimeData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/weather/WeatherData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004BQ\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\u0004\b\u0003\u0010\u0011J\b\u0010(\u001a\u00020\bH\u0016J\u0013\u0010)\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$¨\u0006+"}, d2 = {"Lcom/veepoo/protocol/model/datas/weather/WeatherData;", "", "", "<init>", "()V", "crc", "", "cityName", "", "source", "timeBean", "Lcom/veepoo/protocol/model/datas/TimeData;", "weatherEvery3HourList", "", "Lcom/veepoo/protocol/model/datas/weather/WeatherEvery3Hour;", "weatherEveryDayList", "Lcom/veepoo/protocol/model/datas/weather/WeatherEveryDay;", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/veepoo/protocol/model/datas/TimeData;Ljava/util/List;Ljava/util/List;)V", "getCrc", "()Ljava/lang/Integer;", "setCrc", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCityName", "()Ljava/lang/String;", "setCityName", "(Ljava/lang/String;)V", "getSource", "setSource", "getTimeBean", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setTimeBean", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "getWeatherEvery3HourList", "()Ljava/util/List;", "setWeatherEvery3HourList", "(Ljava/util/List;)V", "weatherEverdayList", "getWeatherEverdayList", "setWeatherEverdayList", "toString", "compareTo", "other", "vpprotocol_release"})
public final class WeatherData implements Comparable<Object> {

    @Nullable
    private Integer crc;

    @Nullable
    private String cityName;

    @Nullable
    private Integer source;

    @Nullable
    private TimeData timeBean;

    @Nullable
    private List<WeatherEvery3Hour> weatherEvery3HourList;

    @Nullable
    private List<WeatherEveryDay> weatherEverdayList;

    public WeatherData() {
        this.crc = 0;
    }

    @Nullable
    public final Integer getCrc() {
        return this.crc;
    }

    public final void setCrc(@Nullable Integer num) {
        this.crc = num;
    }

    @Nullable
    public final String getCityName() {
        return this.cityName;
    }

    public final void setCityName(@Nullable String str) {
        this.cityName = str;
    }

    @Nullable
    public final Integer getSource() {
        return this.source;
    }

    public final void setSource(@Nullable Integer num) {
        this.source = num;
    }

    @Nullable
    public final TimeData getTimeBean() {
        return this.timeBean;
    }

    public final void setTimeBean(@Nullable TimeData timeData) {
        this.timeBean = timeData;
    }

    @Nullable
    public final List<WeatherEvery3Hour> getWeatherEvery3HourList() {
        return this.weatherEvery3HourList;
    }

    public final void setWeatherEvery3HourList(@Nullable List<WeatherEvery3Hour> list) {
        this.weatherEvery3HourList = list;
    }

    @Nullable
    public final List<WeatherEveryDay> getWeatherEverdayList() {
        return this.weatherEverdayList;
    }

    public final void setWeatherEverdayList(@Nullable List<WeatherEveryDay> list) {
        this.weatherEverdayList = list;
    }

    @NotNull
    public String toString() {
        return "WeatherBeanKt(crc=" + this.crc + ", cityName='" + this.cityName + "', source=" + this.source + ", \ntimeBean=" + this.timeBean + ", \nweatherEvery3HourList=" + this.weatherEvery3HourList + ", \nweatherEverdayList=" + this.weatherEverdayList + ")";
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nullable Object other) {
        TimeData timeData;
        WeatherData weatherData = (WeatherData) other;
        int iCompareTo = 0;
        String databaseDateString = (weatherData == null || (timeData = weatherData.timeBean) == null) ? null : timeData.toDatabaseDateString();
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

    public WeatherData(@Nullable Integer num, @Nullable String str, @Nullable Integer num2, @Nullable TimeData timeData, @Nullable List<WeatherEvery3Hour> list, @Nullable List<WeatherEveryDay> list2) {
        this.crc = num;
        this.cityName = str;
        this.source = num2;
        this.timeBean = timeData;
        this.weatherEvery3HourList = list;
        this.weatherEverdayList = list2;
    }
}
