package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.HRVOriginData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/HRVOriginUtil.class */
public class HRVOriginUtil {
    List vp_a;
    private int[][] vp_b = new int[Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT][10];
    ArrayList vp_c = new ArrayList();
    ArrayList vp_d = new ArrayList();
    int vp_e = 0;
    int vp_f = 9999;
    int vp_g = 0;
    int vp_h = 0;
    int vp_i = 0;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/HRVOriginUtil$vp_a.class */
    class vp_a implements Comparator {
        vp_a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public int compare(Map map, Map map2) {
            float fFloatValue = ((Float) map.get("day")).floatValue();
            float fFloatValue2 = ((Float) map2.get("day")).floatValue();
            return fFloatValue == fFloatValue2 ? (int) (((Float) map2.get("time")).floatValue() - ((Float) map.get("time")).floatValue()) : (int) (fFloatValue2 - fFloatValue);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/HRVOriginUtil$vp_b.class */
    class vp_b implements Comparator {
        vp_b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public int compare(Map map, Map map2) {
            float fFloatValue = ((Float) map.get("day")).floatValue();
            float fFloatValue2 = ((Float) map2.get("day")).floatValue();
            return fFloatValue == fFloatValue2 ? (int) (((Float) map.get("time")).floatValue() - ((Float) map2.get("time")).floatValue()) : (int) (fFloatValue - fFloatValue2);
        }
    }

    public HRVOriginUtil(List<HRVOriginData> list) {
        this.vp_a = list;
        vp_a();
    }

    private void vp_c(int i) {
        this.vp_h++;
        this.vp_g += i;
        if (this.vp_e < i) {
            this.vp_e = i;
        }
        if (this.vp_f > i) {
            this.vp_f = i;
        }
    }

    private void vp_a(int[] iArr, int[] iArr2, int i, int i2) {
        if (vp_a(i2)) {
            iArr[i] = iArr[i] + 1;
            iArr2[i] = iArr2[i] + i2;
        }
    }

    private boolean vp_b(int i) {
        return i >= 132;
    }

    public List<Map<String, Float>> getTenMinuteData() {
        ArrayList arrayList = this.vp_c;
        if (arrayList != null && !arrayList.isEmpty()) {
            Collections.sort(arrayList, new vp_a());
        }
        return arrayList;
    }

    public int[] getOnedayDataArr(int i) {
        return new int[]{this.vp_e, this.vp_f, this.vp_i};
    }

    public List<Map<String, Float>> getDetailList(int i) {
        ArrayList arrayList = new ArrayList();
        if (!arrayList.isEmpty()) {
            arrayList.clear();
        }
        for (int i2 = 0; i2 < 10; i2++) {
            arrayList.add(vp_a(this.vp_b[i][i2], (i * 10) + i2, vp_b(i)));
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList, new vp_b());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.veepoo.protocol.util.HRVOriginUtil] */
    public void calcOneDayListAverage(List<HRVOriginData> list) {
        ?? r0 = list;
        int[] iArr = new int[Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT];
        int[] iArr2 = new int[Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT];
        int[] iArr3 = new int[Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT];
        try {
            for (HRVOriginData hRVOriginData : r0) {
                r0 = this;
                int hMValue = hRVOriginData.getmTime().getHMValue();
                int i = hMValue / 10;
                int i2 = hMValue % 10;
                this.vp_b[i][i2] = hRVOriginData.getHrvValue();
                vp_c(this.vp_b[i][i2]);
                r0.vp_a(iArr2, iArr3, i, this.vp_b[i][i2]);
            }
        } catch (ConcurrentModificationException unused) {
            r0.printStackTrace();
        } catch (Exception unused2) {
            r0.printStackTrace();
        }
        vp_a(Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT, iArr, iArr2, iArr3, this.vp_d, this.vp_c);
        int i3 = this.vp_h;
        if (i3 != 0) {
            this.vp_i = this.vp_g / i3;
        }
    }

    public int getPositionDoubleUP(double d) {
        return (int) new BigDecimal(d).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    private void vp_a(int i, int[] iArr, int[] iArr2, int[] iArr3, List list, List list2) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr2[i2] != 0) {
                int i3 = i2;
                int i4 = i3 * 10;
                list.add(vp_a(iArr3[i2], i4, vp_b(i3)));
                int positionDoubleUP = getPositionDoubleUP(((double) iArr3[i2]) / ((double) iArr2[i2]));
                iArr[i2] = positionDoubleUP;
                list2.add(vp_a(positionDoubleUP, i4, vp_b(i2)));
            }
        }
    }

    private void vp_a() {
        ArrayList arrayList = this.vp_c;
        if (arrayList != null && !arrayList.isEmpty()) {
            this.vp_c.clear();
        }
        ArrayList arrayList2 = this.vp_d;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            this.vp_d.clear();
        }
        List list = this.vp_a;
        if (list == null || list.isEmpty()) {
            return;
        }
        calcOneDayListAverage(this.vp_a);
    }

    private boolean vp_a(int i) {
        return i != 0;
    }

    private Map vp_a(float f, int i, boolean z) {
        HashMap map = new HashMap();
        if (z) {
            map.put("day", Float.valueOf(-1.0f));
        } else {
            map.put("day", Float.valueOf(0.0f));
        }
        map.put("time", Float.valueOf(i));
        map.put("value", Float.valueOf(f));
        return map;
    }
}
