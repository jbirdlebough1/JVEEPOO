package com.veepoo.protocol.operate.parse.weather;

import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.weather.WeatherData;
import com.veepoo.protocol.model.datas.weather.WeatherEvery3Hour;
import com.veepoo.protocol.model.datas.weather.WeatherEveryDay;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_f;
import com.veepoo.protocol.util.vp_j;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/parse/weather/vp_a.class */
public final class vp_a {
    private final String vp_a = vp_a.class.getSimpleName();
    private byte vp_b = -16;
    private byte vp_c = -95;
    private byte vp_d = -94;
    private byte vp_e = -93;
    private byte vp_f = -92;
    private byte vp_g = -91;
    private byte vp_h = -90;
    private byte vp_i = -80;
    private byte vp_j = -79;
    private byte vp_k = -78;
    private byte vp_l = -77;
    private byte vp_m = -76;
    private byte vp_n = -75;
    private byte vp_o = -74;
    private byte vp_p = -73;
    private byte vp_q = -64;
    private byte vp_r = -63;
    private byte vp_s = -62;
    private byte vp_t = -61;
    private byte vp_u = -60;
    private byte vp_v = -59;
    private byte vp_w = -58;
    private byte vp_x = -57;
    private byte vp_y = -56;

    private final byte[] vp_a(int i, int i2, List list) {
        int i3;
        byte[] bArr = new byte[20];
        bArr[0] = -56;
        bArr[1] = 4;
        bArr[2] = VpBleByteUtil.loUint16((short) i2);
        bArr[3] = VpBleByteUtil.loUint16((short) i);
        for (int i4 = 0; i4 < 16 && (i3 = ((i - 1) * 16) + i4) < list.size(); i4++) {
            bArr[i4 + 4] = ((Number) list.get(i3)).byteValue();
        }
        return bArr;
    }

    private final List vp_b(List list, byte b) {
        if (list == null || list.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WeatherEveryDay weatherEveryDay = (WeatherEveryDay) list.get(i);
            List listVp_a = vp_a(weatherEveryDay);
            String str = "everyDay:" + weatherEveryDay + ",byte=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_a));
            linkedList.addAll(listVp_a);
        }
        short size2 = (short) linkedList.size();
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.hiUint16(size2)));
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.loUint16(size2)));
        linkedList.addFirst(Byte.valueOf(b));
        return linkedList;
    }

    private final List vp_a(WeatherEvery3Hour weatherEvery3Hour) {
        if (weatherEvery3Hour != null) {
            TimeData timeBean = weatherEvery3Hour.getTimeBean();
            Intrinsics.checkNotNull(timeBean);
            String fullDateTimeString = timeBean.toFullDateTimeString();
            Intrinsics.checkNotNullExpressionValue(fullDateTimeString, "toFullDateTimeString(...)");
            if (vp_a(fullDateTimeString)) {
                String str = "every3Hour valit:" + weatherEvery3Hour;
                vp_f.vp_a vp_aVar = vp_f.vp_a;
                List listVp_b = vp_aVar.vp_b(weatherEvery3Hour.getTimeBean(), this.vp_j);
                List listVp_c = vp_aVar.vp_c(weatherEvery3Hour.getTemperatureF(), this.vp_k);
                List listVp_c2 = vp_aVar.vp_c(weatherEvery3Hour.getTemperatureC(), this.vp_l);
                List listVp_a = vp_aVar.vp_a(weatherEvery3Hour.getYellowLevel(), this.vp_m);
                List listVp_a2 = vp_aVar.vp_a(weatherEvery3Hour.getWeatherState(), this.vp_n);
                List listVp_b2 = vp_aVar.vp_b(weatherEvery3Hour.getWindLevel(), this.vp_o);
                List listVp_b3 = vp_aVar.vp_b(Integer.valueOf((int) (weatherEvery3Hour.getCanSeeWay() * ((double) 1000))), this.vp_p);
                LinkedList linkedList = new LinkedList();
                linkedList.addAll(listVp_b);
                linkedList.addAll(listVp_c);
                linkedList.addAll(listVp_c2);
                linkedList.addAll(listVp_a);
                linkedList.addAll(listVp_a2);
                linkedList.addAll(listVp_b2);
                linkedList.addAll(listVp_b3);
                linkedList.addFirst(Byte.valueOf(VpBleByteUtil.loUint16((short) linkedList.size())));
                linkedList.addFirst(Byte.valueOf(this.vp_i));
                return linkedList;
            }
        }
        return CollectionsKt.emptyList();
    }

    public final List vp_b(WeatherData weatherData) {
        Intrinsics.checkNotNullParameter(weatherData, "weatherData");
        ArrayList arrayList = new ArrayList();
        List listVp_a = vp_a(weatherData);
        int size = listVp_a.size();
        int i = size % 16 == 0 ? size / 16 : (size / 16) + 1;
        int i2 = 1;
        if (1 <= i) {
            while (true) {
                byte[] bArrVp_a = vp_a(i2, i, listVp_a);
                if (bArrVp_a != null) {
                    arrayList.add(bArrVp_a);
                }
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    private final List vp_a(List list, byte b) {
        if (list == null || list.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WeatherEvery3Hour weatherEvery3Hour = (WeatherEvery3Hour) list.get(i);
            List listVp_a = vp_a(weatherEvery3Hour);
            String str = "every3Hour all:" + weatherEvery3Hour + ",byte=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_a));
            linkedList.addAll(listVp_a);
        }
        short size2 = (short) linkedList.size();
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.hiUint16(size2)));
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.loUint16(size2)));
        linkedList.addFirst(Byte.valueOf(b));
        return linkedList;
    }

    private final boolean vp_b(String str) {
        return vp_j.vp_a(vp_j.vp_c(), str) >= 0;
    }

    private final List vp_a(WeatherEveryDay weatherEveryDay) {
        TimeData timeBean = weatherEveryDay.getTimeBean();
        Intrinsics.checkNotNull(timeBean);
        String databaseDateString = timeBean.toDatabaseDateString();
        Intrinsics.checkNotNullExpressionValue(databaseDateString, "toDatabaseDateString(...)");
        if (!vp_b(databaseDateString)) {
            return CollectionsKt.emptyList();
        }
        String str = "everyDay valid:" + weatherEveryDay;
        vp_f.vp_a vp_aVar = vp_f.vp_a;
        List listVp_a = vp_aVar.vp_a(weatherEveryDay.getTimeBean(), this.vp_r);
        List listVp_a2 = vp_aVar.vp_a(weatherEveryDay.getTemperatureMaxF(), weatherEveryDay.getTemperatureMinF(), this.vp_s);
        List listVp_a3 = vp_aVar.vp_a(weatherEveryDay.getTemperatureMaxC(), weatherEveryDay.getTemperatureMinC(), this.vp_t);
        List listVp_a4 = vp_aVar.vp_a(weatherEveryDay.getYellowLevel(), this.vp_u);
        List listVp_a5 = vp_aVar.vp_a(weatherEveryDay.getWeatherStateWhiteDay(), this.vp_v);
        List listVp_a6 = vp_aVar.vp_a(weatherEveryDay.getWeatherStateNightDay(), this.vp_w);
        List listVp_b = vp_aVar.vp_b(weatherEveryDay.getWindLevel(), this.vp_x);
        List listVp_b2 = vp_aVar.vp_b(Integer.valueOf((int) (weatherEveryDay.getCanSeeWay() * ((double) 1000))), this.vp_y);
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(listVp_a);
        linkedList.addAll(listVp_a2);
        linkedList.addAll(listVp_a3);
        linkedList.addAll(listVp_a4);
        linkedList.addAll(listVp_a5);
        linkedList.addAll(listVp_a6);
        linkedList.addAll(listVp_b);
        linkedList.addAll(listVp_b2);
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.loUint16((short) linkedList.size())));
        linkedList.addFirst(Byte.valueOf(this.vp_q));
        return linkedList;
    }

    private final List vp_a(WeatherData weatherData) {
        vp_f.vp_a vp_aVar = vp_f.vp_a;
        List listVp_b = vp_aVar.vp_b(weatherData.getCrc(), this.vp_c);
        List listVp_a = vp_aVar.vp_a(weatherData.getCityName(), this.vp_d);
        List listVp_a2 = vp_aVar.vp_a(weatherData.getSource(), this.vp_e);
        List listVp_b2 = vp_aVar.vp_b(weatherData.getTimeBean(), this.vp_f);
        List listVp_a3 = vp_a(weatherData.getWeatherEvery3HourList(), this.vp_g);
        List listVp_b3 = vp_b(weatherData.getWeatherEverdayList(), this.vp_h);
        String str = "【天气】===》》》A1:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_b));
        String str2 = "【天气】===》》》A2:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_a));
        String str3 = "【天气】===》》》A3:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_a2));
        String str4 = "【天气】===》》》A4:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_b2));
        String str5 = "【天气】===》》》A5:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_a3));
        String str6 = "【天气】===》》》A6:=" + VpBleByteUtil.byte2HexForShow(CollectionsKt.toByteArray(listVp_b3));
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(listVp_b);
        linkedList.addAll(listVp_a);
        linkedList.addAll(listVp_a2);
        linkedList.addAll(listVp_b2);
        linkedList.addAll(listVp_a3);
        linkedList.addAll(listVp_b3);
        short size = (short) linkedList.size();
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.hiUint16(size)));
        linkedList.addFirst(Byte.valueOf(VpBleByteUtil.loUint16(size)));
        linkedList.addFirst(Byte.valueOf(this.vp_b));
        return linkedList;
    }

    private final boolean vp_a(String str) {
        return vp_j.vp_b(str, vp_j.vp_b()) <= 180;
    }
}
