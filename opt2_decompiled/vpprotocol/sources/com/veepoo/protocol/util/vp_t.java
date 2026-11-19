package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.HalfHourBpData;
import com.veepoo.protocol.model.datas.HalfHourRateData;
import com.veepoo.protocol.model.datas.HalfHourSportData;
import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginData3;
import com.veepoo.protocol.model.datas.TimeData;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_t.class */
public class vp_t {
    public List vp_c(List list, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        TimeData timeData = null;
        int[] iArr = new int[48];
        int[] iArr2 = new int[48];
        double[] dArr = new double[48];
        double[] dArr2 = new double[48];
        while (it.hasNext()) {
            OriginData originData = (OriginData) it.next();
            TimeData timeData2 = originData.getmTime();
            if (vp_j.vp_c(timeData2.getDateAndClockForDb())) {
                int hour = (timeData2.getHour() * 60) + timeData2.getMinute();
                int stepValue = originData.getStepValue();
                int sportValue = originData.getSportValue();
                originData.getRateValue();
                double disValue = originData.getDisValue();
                double calValue = originData.getCalValue();
                int i2 = hour / 30;
                iArr[i2] = iArr[i2] + stepValue;
                iArr2[i2] = iArr2[i2] + sportValue;
                dArr[i2] = dArr[i2] + disValue;
                dArr2[i2] = dArr2[i2] + calValue;
            }
            timeData = timeData2;
        }
        int sysHour = i == 0 ? ((TimeData.getSysHour() * 60) + TimeData.getSysMiute()) / 30 : 47;
        for (int i3 = 0; i3 <= sysHour; i3++) {
            TimeData timeData3 = new TimeData();
            timeData3.setYear(timeData.getYear());
            timeData3.setMonth(timeData.getMonth());
            timeData3.setDay(timeData.getDay());
            timeData3.setHour(i3 / 2);
            timeData3.setMinute(i3 % 2 == 0 ? 0 : 30);
            String dateAndClockForDb = timeData3.getDateAndClockForDb();
            if (vp_j.vp_c(dateAndClockForDb)) {
                int i4 = iArr2[i3];
                if (i4 != 0) {
                    arrayList.add(new HalfHourSportData(timeData3, iArr[i3], i4, dArr[i3], dArr2[i3]));
                }
            } else {
                new StringBuilder().append("5分钟原始[Heart Rate]数据 日期不合法：").append(dateAndClockForDb);
            }
        }
        return arrayList;
    }

    public List vp_b(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OriginData originData = (OriginData) it.next();
            TimeData timeData = originData.getmTime();
            if (timeData != null) {
                String dateAndClockForDb = timeData.getDateAndClockForDb();
                if (vp_j.vp_c(dateAndClockForDb)) {
                    int highValue = originData.getHighValue();
                    int lowValue = originData.getLowValue();
                    if (vp_a(highValue, lowValue)) {
                        arrayList.add(new HalfHourBpData(timeData, highValue, lowValue));
                    }
                } else {
                    new StringBuilder().append("Original日期不合法：").append(dateAndClockForDb);
                }
            }
        }
        return arrayList;
    }

    public int vp_a(List list) {
        new ArrayList();
        int stepValue = 0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OriginData originData = (OriginData) it.next();
            TimeData timeData = originData.getmTime();
            if (timeData != null) {
                String dateAndClockForDb = timeData.getDateAndClockForDb();
                if (vp_j.vp_c(dateAndClockForDb)) {
                    stepValue += originData.getStepValue();
                } else {
                    new StringBuilder().append("Original日期不合法：").append(dateAndClockForDb);
                }
            }
        }
        return stepValue;
    }

    public List vp_b(List list, int i) {
        System.out.println("=》》》【获取半小时Heart Rate数据】 date = " + i);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        TimeData timeData = null;
        int[] iArr = new int[48];
        int[] iArr2 = new int[48];
        int[] iArr3 = new int[48];
        int[] iArr4 = new int[48];
        while (it.hasNext()) {
            OriginData3 originData3 = (OriginData3) it.next();
            TimeData timeData2 = originData3.getmTime();
            if (vp_j.vp_b(timeData2)) {
                if (timeData == null && vp_j.vp_c(timeData2.getDateAndClockForDb())) {
                    timeData = originData3.getmTime();
                }
                int hour = ((timeData2.getHour() * 60) + timeData2.getMinute()) / 30;
                int[] ecgs = originData3.getEcgs();
                int[] ppgs = originData3.getPpgs();
                if (ecgs != null) {
                    for (int i2 : ecgs) {
                        if (i2 <= 200 && i2 >= 30 && hour < 48) {
                            iArr[hour] = iArr[hour] + i2;
                            iArr2[hour] = iArr2[hour] + 1;
                        }
                    }
                }
                if (ppgs != null) {
                    for (int i3 : ppgs) {
                        if (i3 <= 200 && i3 >= 30 && hour < 48) {
                            iArr3[hour] = iArr3[hour] + i3;
                            iArr4[hour] = iArr4[hour] + 1;
                        }
                    }
                }
            }
        }
        int sysHour = 47;
        if (i == 0) {
            PrintStream printStream = System.out;
            printStream.println("=》》》【获取半小时Heart Rate数据】 TimeData.getSysHour = " + TimeData.getSysHour());
            printStream.println("=》》》【获取半小时Heart Rate数据】 TimeData.getSysMiute = " + TimeData.getSysMiute());
            sysHour = ((TimeData.getSysHour() * 60) + TimeData.getSysMiute()) / 30;
        }
        System.out.println("=》》》【获取半小时Heart Rate数据】 rateDayCount = " + sysHour);
        for (int i4 = 0; i4 <= sysHour; i4++) {
            TimeData timeData3 = new TimeData();
            timeData3.setYear(timeData.getYear());
            timeData3.setMonth(timeData.getMonth());
            timeData3.setDay(timeData.getDay());
            timeData3.setHour(i4 / 2);
            timeData3.setMinute(i4 % 2 == 0 ? 0 : 30);
            int i5 = 0;
            int i6 = iArr[i4];
            int i7 = iArr3[i4];
            if (i6 + i7 != 0) {
                int i8 = iArr2[i4];
                int i9 = iArr4[i4];
                if (i8 + i9 != 0) {
                    i5 = (i6 + i7) / (i8 + i9);
                }
            }
            if (i5 != 0) {
                HalfHourRateData halfHourRateData = new HalfHourRateData(timeData3, i5);
                halfHourRateData.setPpgCount(iArr4[i4]);
                halfHourRateData.setEcgCount(iArr2[i4]);
                arrayList.add(halfHourRateData);
            }
        }
        return arrayList;
    }

    public List vp_a(List list, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        TimeData timeData = null;
        int[] iArr = new int[48];
        int[] iArr2 = new int[48];
        while (it.hasNext()) {
            OriginData originData = (OriginData) it.next();
            TimeData timeData2 = originData.getmTime();
            if (vp_j.vp_c(timeData2.getDateAndClockForDb())) {
                int hour = ((timeData2.getHour() * 60) + timeData2.getMinute()) / 30;
                if (originData.getRateValue() <= 200 && originData.getRateValue() >= 30) {
                    iArr[hour] = iArr[hour] + originData.getRateValue();
                    iArr2[hour] = iArr2[hour] + 1;
                }
            }
            timeData = timeData2;
        }
        int sysHour = i == 0 ? ((TimeData.getSysHour() * 60) + TimeData.getSysMiute()) / 30 : 47;
        for (int i3 = 0; i3 <= sysHour; i3++) {
            TimeData timeData3 = new TimeData();
            timeData3.setYear(timeData.getYear());
            timeData3.setMonth(timeData.getMonth());
            timeData3.setDay(timeData.getDay());
            timeData3.setHour(i3 / 2);
            timeData3.setMinute(i3 % 2 == 0 ? 0 : 30);
            int i4 = 0;
            int i5 = iArr[i3];
            if (i5 != 0 && (i2 = iArr2[i3]) != 0) {
                i4 = i5 / i2;
                new StringBuilder().append("time=").append(timeData3.getDateAndClockForDb()).append("Heart Rate平均：").append(iArr[i3]).append("/").append(iArr2[i3]).append("=").append(iArr[i3] / iArr2[i3]);
            }
            String dateAndClockForDb = timeData3.getDateAndClockForDb();
            if (!vp_j.vp_c(dateAndClockForDb)) {
                new StringBuilder().append("5分钟原始[Heart Rate]数据 日期不合法：").append(dateAndClockForDb);
            } else if (i4 != 0) {
                arrayList.add(new HalfHourRateData(timeData3, i4));
            }
        }
        return arrayList;
    }

    private boolean vp_a(int i, int i2) {
        boolean z = true;
        if (i < 60 || i > 300 || i2 < 20 || i2 > 200) {
            z = false;
        }
        return z;
    }
}
