package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.Spo2hOriginData;
import com.veepoo.protocol.model.enums.ESpo2hDataType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/Spo2hOriginUtil.class */
public class Spo2hOriginUtil {
    public static final int MIN_VALUE_SP02H = 75;
    public static final int MAX_VALUE_SPO2H = 99;
    public static final int MIN_VALUE_HEART = 0;
    public static final int MAX_VALUE_HEART = 100;
    public static final int MIN_VALUE_SLEEP = 0;
    public static final int MAX_VALUE_SLEEP = 300;
    public static final int MIN_VALUE_BEATH = 0;
    public static final int MAX_VALUE_BEATH = 50;
    public static final int MIN_VALUE_SP02H_LOW = 0;
    public static final int MAX_VALUE_SPO2H_LOW = 60;
    public static final int TIME_FLAG = 10;
    public static final int TIME_FLAG_ONE_HOUR_POINT = 6;
    public static final int TIME_FLAG_ONE_DAY_POINT = 144;
    static List vp_ab = new ArrayList();
    static List vp_ac = new ArrayList();
    static List vp_ad = new ArrayList();
    static List vp_ae = new ArrayList();
    static List vp_af = new ArrayList();
    static List vp_ag = new ArrayList();
    static List vp_ah = new ArrayList();
    static List vp_ai = new ArrayList();
    static List vp_aj = new ArrayList();
    static List vp_ak = new ArrayList();
    static List vp_al = new ArrayList();
    static List vp_am = new ArrayList();
    static List vp_an = new ArrayList();
    static int vp_ao = -1;
    static int vp_ap = 0;
    static int vp_aq = 9999;
    static int vp_ar = 0;
    static int vp_as = 0;
    static int vp_at = 0;
    static int vp_au = 0;
    static int vp_av = 9999;
    static int vp_aw = 0;
    static int vp_ax = 0;
    static int vp_ay = 0;
    public static final int YESTERDAY_HOUR_START = 22;
    List vp_a;
    private int[][] vp_b = new int[TIME_FLAG_ONE_DAY_POINT][10];
    private int[][] vp_c = new int[TIME_FLAG_ONE_DAY_POINT][10];
    private int[][] vp_d = new int[TIME_FLAG_ONE_DAY_POINT][10];
    private int[][] vp_e = new int[TIME_FLAG_ONE_DAY_POINT][10];
    private int[][] vp_f = new int[TIME_FLAG_ONE_DAY_POINT][10];
    private int[][] vp_g = new int[TIME_FLAG_ONE_DAY_POINT][10];
    int vp_h = 0;
    int vp_i = 9999;
    int vp_j = 0;
    int vp_k = 0;
    int vp_l = 0;
    int vp_m = 0;
    int vp_n = 9999;
    int vp_o = 0;
    int vp_p = 0;
    int vp_q = 0;
    int vp_r = 0;
    int vp_s = 9999;
    int vp_t = 0;
    int vp_u = 0;
    int vp_v = 0;
    int vp_w = 0;
    int vp_x = 9999;
    int vp_y = 0;
    int vp_z = 0;
    int vp_aa = 0;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/Spo2hOriginUtil$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[ESpo2hDataType.values().length];
            vp_a = iArr;
            try {
                iArr[ESpo2hDataType.TYPE_HEART.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_SLEEP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_BREATH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_LOWSPO2H.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_SPO2H_MIN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_SPO2H.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[ESpo2hDataType.TYPE_BEATH_BREAK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/Spo2hOriginUtil$vp_b.class */
    class vp_b implements Comparator {
        vp_b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public int compare(Map map, Map map2) {
            float fFloatValue = ((Float) map.get("day")).floatValue();
            float fFloatValue2 = ((Float) map2.get("day")).floatValue();
            return fFloatValue == fFloatValue2 ? (int) (((Float) map2.get("time")).floatValue() - ((Float) map.get("time")).floatValue()) : (int) (fFloatValue2 - fFloatValue);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/Spo2hOriginUtil$vp_c.class */
    class vp_c implements Comparator {
        vp_c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public int compare(Map map, Map map2) {
            float fFloatValue = ((Float) map.get("day")).floatValue();
            float fFloatValue2 = ((Float) map2.get("day")).floatValue();
            return fFloatValue == fFloatValue2 ? (int) (((Float) map.get("time")).floatValue() - ((Float) map2.get("time")).floatValue()) : (int) (fFloatValue - fFloatValue2);
        }
    }

    public Spo2hOriginUtil(List<Spo2hOriginData> list) {
        this.vp_a = list;
        vp_a();
    }

    private void vp_b() {
        vp_c();
        vp_ab.clear();
        vp_ac.clear();
        vp_ad.clear();
        vp_ae.clear();
        vp_ag.clear();
        vp_af.clear();
        vp_ah.clear();
        vp_ai.clear();
        vp_aj.clear();
        vp_ak.clear();
        vp_al.clear();
        vp_am.clear();
        vp_an.clear();
    }

    private void vp_j(int i) {
        if (vp_f(i)) {
            vp_ax++;
            vp_aw += i;
        }
    }

    private void vp_k(int i) {
        if (vp_c(i)) {
            this.vp_k++;
            this.vp_j += i;
            if (this.vp_h < i) {
                this.vp_h = i;
            }
            if (this.vp_i > i) {
                this.vp_i = i;
            }
        }
    }

    private void vp_h(int i) {
        if (vp_a(i)) {
            this.vp_p++;
            this.vp_o += i;
            if (this.vp_m < i) {
                this.vp_m = i;
            }
            if (this.vp_n > i) {
                this.vp_n = i;
            }
        }
    }

    private void vp_l(int i) {
        if (vp_d(i)) {
            this.vp_u++;
            this.vp_t += i;
            if (this.vp_r < i) {
                this.vp_r = i;
            }
            if (this.vp_s > i) {
                this.vp_s = i;
            }
        }
    }

    private void vp_i(int i) {
        this.vp_z++;
        this.vp_y += i;
        if (this.vp_w < i) {
            this.vp_w = i;
        }
        if (this.vp_x > i) {
            this.vp_x = i;
        }
    }

    private void vp_a(int[] iArr, int[] iArr2, int i, int i2, ESpo2hDataType eSpo2hDataType) {
        if ((eSpo2hDataType == ESpo2hDataType.TYPE_SPO2H && vp_f(i2)) || ((eSpo2hDataType == ESpo2hDataType.TYPE_HEART && vp_c(i2)) || ((eSpo2hDataType == ESpo2hDataType.TYPE_SLEEP && vp_e(i2)) || ((eSpo2hDataType == ESpo2hDataType.TYPE_BREATH && vp_a(i2)) || ((eSpo2hDataType == ESpo2hDataType.TYPE_LOWSPO2H && vp_d(i2)) || (eSpo2hDataType == ESpo2hDataType.TYPE_BEATH_BREAK && vp_b(i2))))))) {
            iArr[i] = iArr[i] + 1;
            iArr2[i] = iArr2[i] + i2;
        }
    }

    private boolean vp_g(int i) {
        return i >= 132;
    }

    public static int getIntUp(double d) {
        return (int) new BigDecimal(d).setScale(0, 4).doubleValue();
    }

    private boolean vp_c(int i) {
        return i <= 100 && i >= 0;
    }

    private boolean vp_d(int i) {
        return i <= 60 && i >= 0;
    }

    private boolean vp_e(int i) {
        return i <= 300 && i >= 0;
    }

    private static boolean vp_f(int i) {
        return i >= 75 && i <= 99;
    }

    public List<Map<String, Float>> getTenMinuteData(ESpo2hDataType eSpo2hDataType) {
        List<Map<String, Float>> listVp_a = vp_a(eSpo2hDataType);
        if (listVp_a != null && !listVp_a.isEmpty()) {
            Collections.sort(listVp_a, new vp_b());
        }
        return listVp_a;
    }

    public int[] getOnedayDataArr(ESpo2hDataType eSpo2hDataType) {
        switch (vp_a.vp_a[eSpo2hDataType.ordinal()]) {
            case 1:
                return new int[]{this.vp_h, this.vp_i, this.vp_l};
            case 2:
                return new int[]{vp_ap, vp_aq, vp_at};
            case 3:
                return new int[]{this.vp_m, this.vp_n, this.vp_q};
            case 4:
                return new int[]{this.vp_r, this.vp_s, this.vp_v};
            case 5:
            default:
                return new int[]{0, 0, 0};
            case 6:
                return new int[]{vp_au, vp_av, vp_ay};
        }
    }

    public int getIsHypoxia() {
        return vp_ao;
    }

    public List<Map<String, Float>> getApneaList() {
        List<Map<String, Float>> tenMinuteData = getTenMinuteData(ESpo2hDataType.TYPE_BEATH_BREAK);
        if (tenMinuteData == null || tenMinuteData.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < tenMinuteData.size(); i++) {
            Map<String, Float> map = tenMinuteData.get(i);
            if (map.get("value").floatValue() != 0.0f && arrayList.size() <= 5) {
                arrayList.add(map);
            }
        }
        return arrayList;
    }

    public List<Map<String, Float>> getDetailList(ESpo2hDataType eSpo2hDataType, int i) {
        float f;
        int i2;
        ArrayList arrayList = new ArrayList();
        if (!arrayList.isEmpty()) {
            arrayList.clear();
        }
        for (int i3 = 0; i3 < 10; i3++) {
            Map mapVp_a = null;
            if (eSpo2hDataType == ESpo2hDataType.TYPE_HEART) {
                if (vp_c(this.vp_c[i][i3])) {
                    f = this.vp_c[i][i3];
                    i2 = this.vp_g[i][i3];
                    mapVp_a = vp_a(f, i2, (i * 10) + i3, vp_g(i));
                    arrayList.add(mapVp_a);
                }
            } else if (eSpo2hDataType == ESpo2hDataType.TYPE_SLEEP) {
                if (vp_e(this.vp_d[i][i3])) {
                    f = this.vp_d[i][i3];
                    i2 = this.vp_g[i][i3];
                    mapVp_a = vp_a(f, i2, (i * 10) + i3, vp_g(i));
                    arrayList.add(mapVp_a);
                }
            } else if (eSpo2hDataType == ESpo2hDataType.TYPE_BREATH) {
                if (vp_a(this.vp_e[i][i3])) {
                    f = this.vp_e[i][i3];
                    i2 = this.vp_g[i][i3];
                    mapVp_a = vp_a(f, i2, (i * 10) + i3, vp_g(i));
                    arrayList.add(mapVp_a);
                }
            } else if (eSpo2hDataType == ESpo2hDataType.TYPE_LOWSPO2H) {
                if (vp_d(this.vp_f[i][i3])) {
                    f = this.vp_f[i][i3];
                    i2 = this.vp_g[i][i3];
                    mapVp_a = vp_a(f, i2, (i * 10) + i3, vp_g(i));
                    arrayList.add(mapVp_a);
                }
            } else if (eSpo2hDataType != ESpo2hDataType.TYPE_SPO2H) {
                arrayList.add(mapVp_a);
            } else if (vp_f(this.vp_b[i][i3]) && vp_b(this.vp_g[i][i3])) {
                f = this.vp_b[i][i3];
                i2 = this.vp_g[i][i3];
                mapVp_a = vp_a(f, i2, (i * 10) + i3, vp_g(i));
                arrayList.add(mapVp_a);
            }
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList, new vp_c());
        }
        return arrayList;
    }

    public void calcOneDayListAverage(List<Spo2hOriginData> list) {
        int[] iArr = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr2 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr3 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr4 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr5 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr6 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr7 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr8 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr9 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr10 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr11 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr12 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr13 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr14 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr15 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr16 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr17 = new int[TIME_FLAG_ONE_DAY_POINT];
        int[] iArr18 = new int[TIME_FLAG_ONE_DAY_POINT];
        for (Spo2hOriginData spo2hOriginData : list) {
            int hMValue = spo2hOriginData.getmTime().getHMValue();
            int i = hMValue / 10;
            int i2 = hMValue % 10;
            int oxygenValue = spo2hOriginData.getOxygenValue();
            int i3 = oxygenValue;
            int temp1 = spo2hOriginData.getTemp1();
            if (oxygenValue < 95 && ((temp1 & 12) >> 2) == 1) {
                i3 = 96;
            }
            this.vp_b[i][i2] = i3;
            vp_j(i3);
            vp_a(iArr11, iArr17, i, this.vp_b[i][i2]);
            this.vp_c[i][i2] = spo2hOriginData.getCardiacLoad();
            vp_k(this.vp_c[i][i2]);
            this.vp_d[i][i2] = spo2hOriginData.getSportValue();
            this.vp_e[i][i2] = spo2hOriginData.getRespirationRate();
            vp_h(this.vp_e[i][i2]);
            this.vp_f[i][i2] = spo2hOriginData.getHypoxiaTime();
            vp_l(this.vp_f[i][i2]);
            this.vp_g[i][i2] = spo2hOriginData.getApneaResult();
            vp_i(this.vp_g[i][i2]);
            int isHypoxia = spo2hOriginData.getIsHypoxia();
            if (isHypoxia >= vp_ao) {
                vp_ao = isHypoxia;
            }
            vp_a(iArr7, iArr13, i, this.vp_c[i][i2], ESpo2hDataType.TYPE_HEART);
            vp_a(iArr8, iArr14, i, this.vp_d[i][i2], ESpo2hDataType.TYPE_SLEEP);
            vp_a(iArr9, iArr15, i, this.vp_e[i][i2], ESpo2hDataType.TYPE_BREATH);
            vp_a(iArr10, iArr16, i, this.vp_f[i][i2], ESpo2hDataType.TYPE_LOWSPO2H);
            vp_a(iArr12, iArr18, i, this.vp_g[i][i2], ESpo2hDataType.TYPE_BEATH_BREAK);
        }
        vp_a(this.vp_b, iArr18);
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr, iArr7, iArr13, iArr18, vp_ai, vp_ab, ESpo2hDataType.TYPE_HEART);
        vp_as = 0;
        vp_ar = 0;
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr2, iArr8, iArr14, iArr18, vp_aj, vp_ac, ESpo2hDataType.TYPE_SLEEP);
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr3, iArr9, iArr15, iArr18, vp_ak, vp_ad, ESpo2hDataType.TYPE_BREATH);
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr4, iArr10, iArr16, iArr18, vp_al, vp_ae, ESpo2hDataType.TYPE_LOWSPO2H);
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr6, iArr12, iArr18, iArr18, vp_an, vp_ah, ESpo2hDataType.TYPE_BEATH_BREAK);
        vp_a(TIME_FLAG_ONE_DAY_POINT, iArr5, iArr11, iArr17, iArr18, vp_am, vp_af, ESpo2hDataType.TYPE_SPO2H);
        int i4 = this.vp_k;
        if (i4 != 0) {
            this.vp_l = getIntUp(((double) this.vp_j) / ((double) i4));
        }
        int i5 = vp_as;
        if (i5 != 0) {
            vp_at = getIntUp(((double) vp_ar) / ((double) i5));
        }
        int i6 = this.vp_p;
        if (i6 != 0) {
            this.vp_q = getIntUp(((double) this.vp_o) / ((double) i6));
        }
        int i7 = this.vp_y;
        if (i7 != 0) {
            this.vp_v = getIntUp(((double) this.vp_t) / ((double) i7));
        }
        int i8 = vp_ax;
        if (i8 != 0) {
            vp_ay = getIntUp(((double) vp_aw) / ((double) i8));
        }
    }

    private void vp_c() {
        vp_ao = -1;
        this.vp_h = 0;
        this.vp_i = 9999;
        this.vp_j = 0;
        this.vp_k = 0;
        this.vp_l = 0;
        vp_ap = 0;
        vp_aq = 9999;
        vp_ar = 0;
        vp_as = 0;
        vp_at = 0;
        this.vp_m = 0;
        this.vp_n = 9999;
        this.vp_o = 0;
        this.vp_p = 0;
        this.vp_q = 0;
        this.vp_r = 0;
        this.vp_s = 9999;
        this.vp_t = 0;
        this.vp_u = 0;
        this.vp_v = 0;
        vp_au = 0;
        vp_av = 9999;
        vp_aw = 0;
        vp_ax = 0;
        vp_ay = 0;
        this.vp_w = 0;
        this.vp_x = 9999;
        this.vp_y = 0;
        this.vp_z = 0;
        this.vp_aa = 0;
    }

    private void vp_a(int[] iArr, int[] iArr2, int i, int i2) {
        if (vp_f(i2)) {
            iArr[i] = iArr[i] + 1;
            iArr2[i] = iArr2[i] + i2;
        }
    }

    private boolean vp_b(int i) {
        return true;
    }

    private void vp_a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, List list, List list2, ESpo2hDataType eSpo2hDataType) {
        int i2;
        int i3;
        for (int i4 = 0; i4 < i; i4++) {
            if (iArr2[i4] != 0) {
                int i5 = i4;
                int i6 = i5 * 10;
                list.add(vp_a(iArr3[i4], iArr4[i4], i6, vp_g(i5)));
                int intUp = getIntUp(((double) iArr3[i4]) / ((double) iArr2[i4]));
                iArr[i4] = intUp;
                list2.add(vp_a(intUp, iArr4[i4], i6, vp_g(i4)));
            }
            if (eSpo2hDataType == ESpo2hDataType.TYPE_SLEEP && (i3 = iArr3[i4]) != 0) {
                vp_as++;
                vp_ar += i3;
            }
            if (eSpo2hDataType == ESpo2hDataType.TYPE_SPO2H && (i2 = iArr[i4]) != 0) {
                if (vp_au < i2) {
                    vp_au = i2;
                }
                if (vp_av > i2) {
                    vp_av = i2;
                }
            }
        }
        if (eSpo2hDataType == ESpo2hDataType.TYPE_SLEEP) {
            new StringBuilder().append("allSumSleep=").append(vp_ar).append(",allVailtSleep=").append(vp_as);
        }
    }

    private void vp_a() {
        vp_b();
        List list = this.vp_a;
        if (list == null || list.isEmpty()) {
            return;
        }
        calcOneDayListAverage(this.vp_a);
    }

    private void vp_a(int[][] iArr, int[] iArr2) {
        for (int i = 0; i < 144; i++) {
            int i2 = 9999;
            for (int i3 = 0; i3 < 10; i3++) {
                int i4 = iArr[i][i3];
                if (i4 != 0 && i4 <= 99 && i4 <= i2) {
                    i2 = i4;
                }
            }
            if (i2 != 9999) {
                int i5 = i;
                vp_ag.add(vp_a(i2, iArr2[i], i5 * 10, vp_g(i5)));
            }
        }
    }

    private boolean vp_a(int i) {
        return i <= 50 && i >= 0;
    }

    private Map vp_a(float f, float f2, int i, boolean z) {
        HashMap map = new HashMap();
        if (z) {
            map.put("day", Float.valueOf(-1.0f));
        } else {
            map.put("day", Float.valueOf(0.0f));
        }
        map.put("time", Float.valueOf(i));
        map.put("value", Float.valueOf(f));
        map.put("breahbreakvalue", Float.valueOf(f2));
        return map;
    }

    private List vp_a(ESpo2hDataType eSpo2hDataType) {
        switch (vp_a.vp_a[eSpo2hDataType.ordinal()]) {
            case 1:
                return vp_ab;
            case 2:
                return vp_aj;
            case 3:
                return vp_ad;
            case 4:
                return vp_al;
            case 5:
                return vp_ag;
            case 6:
                return vp_af;
            case 7:
                return vp_an;
            default:
                return null;
        }
    }
}
