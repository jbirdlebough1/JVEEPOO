package com.veepoo.protocol.util;

import android.content.Context;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.HrvAnalysisReport;
import com.vp.cso.hrvreport.JNIChange;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_u.class */
public abstract class vp_u {
    private static String vp_a;
    private static String vp_b;
    private static String vp_c;
    private static String vp_d;
    private static String vp_e;
    private static String vp_f;
    private static String vp_g;
    private static String vp_h;
    private static String vp_i;
    private static String vp_j;
    private static String vp_k;
    private static String vp_l;
    private static String vp_m;
    private static String vp_n;
    private static String vp_o;
    private static String vp_p;
    private static String vp_q;
    private static String vp_r;
    private static String vp_s;
    private static String vp_t;
    private static String vp_u;
    private static String vp_v;
    private static String vp_w;
    private static String vp_x;
    private static String vp_y;
    private static String vp_z;
    private static String vp_aa;
    private static String vp_ab;
    private static String vp_ac;
    private static String vp_ad;
    private static String vp_ae;
    private static String vp_af;
    private static String vp_ag;
    private static String vp_ah;
    private static String vp_ai;
    private static String vp_aj;
    private static String vp_ak;
    private static String vp_al;
    private static String vp_am;
    private static String vp_an;
    private static String vp_ao;
    private static String vp_ap;
    private static String vp_aq;
    private static String vp_ar;
    private static String vp_as;
    private static String vp_at;
    private static String vp_au;
    private static String vp_av;
    private static String vp_aw;
    private static String vp_ax;
    private static String vp_ay;
    private static String vp_az;
    private static String vp_ba;
    private static String vp_bb;
    private static String vp_bc;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v17, types: [int[]] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Exception, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Throwable] */
    public static List vp_a(Context context, List list) {
        ?? HrvAnalysisReport;
        ArrayList arrayList = new ArrayList();
        vp_a(context);
        JNIChange jNIChange = new JNIChange();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HRVOriginData hRVOriginData = (HRVOriginData) it.next();
            if (hRVOriginData.getmTime().getHour() < 7) {
                arrayList2.add(hRVOriginData);
            }
        }
        double[] lorenData = HrvScoreUtil.getLorenData(arrayList2);
        if (lorenData == null || lorenData.length < 1500) {
            return arrayList;
        }
        int length = lorenData.length;
        int[] iArr = new int[length];
        int i = 0;
        while (true) {
            HrvAnalysisReport = i;
            if (HrvAnalysisReport >= length) {
                try {
                    break;
                } catch (Exception e) {
                    HrvAnalysisReport.printStackTrace();
                    return e;
                }
            }
            iArr[i] = (int) lorenData[i];
            i++;
        }
        HrvAnalysisReport = jNIChange.hrvAnalysisReport(iArr, length);
        if (HrvAnalysisReport[0] != 0) {
            int i2 = 1;
            while (true) {
                int i3 = i2;
                if (i3 >= HrvAnalysisReport.length - 1) {
                    break;
                }
                int i4 = i3 + 1;
                ?? r1 = (i4 * 100) + HrvAnalysisReport[i3];
                arrayList.add(new HrvAnalysisReport(i3, vp_c(i3), vp_b(r1), vp_a((int) r1)));
                i2 = i4;
            }
        }
        return arrayList;
    }

    public static String vp_c(int i) {
        switch (i) {
            case 0:
                return vp_a;
            case 1:
                return vp_b;
            case 2:
                return vp_c;
            case 3:
                return vp_d;
            case 4:
                return vp_e;
            default:
                return vp_a;
        }
    }

    public static String vp_b(int i) {
        for (Map.Entry entry : vp_a().entrySet()) {
            if (((Integer) entry.getKey()).intValue() == i) {
                return (String) entry.getValue();
            }
        }
        return "";
    }

    public static Map vp_a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(100, vp_f);
        linkedHashMap.put(211, vp_g);
        linkedHashMap.put(212, vp_h);
        linkedHashMap.put(213, vp_i);
        linkedHashMap.put(221, vp_j);
        linkedHashMap.put(222, vp_k);
        linkedHashMap.put(223, vp_l);
        linkedHashMap.put(231, vp_m);
        linkedHashMap.put(232, vp_n);
        linkedHashMap.put(233, vp_o);
        linkedHashMap.put(234, vp_p);
        linkedHashMap.put(235, vp_q);
        linkedHashMap.put(311, vp_r);
        linkedHashMap.put(312, vp_s);
        linkedHashMap.put(313, vp_t);
        linkedHashMap.put(314, vp_u);
        linkedHashMap.put(321, vp_v);
        linkedHashMap.put(322, vp_w);
        linkedHashMap.put(323, vp_x);
        linkedHashMap.put(331, vp_y);
        linkedHashMap.put(332, vp_z);
        linkedHashMap.put(333, vp_aa);
        linkedHashMap.put(334, vp_ab);
        linkedHashMap.put(411, vp_ac);
        linkedHashMap.put(412, vp_ad);
        linkedHashMap.put(413, vp_ae);
        linkedHashMap.put(414, vp_af);
        linkedHashMap.put(415, vp_ag);
        linkedHashMap.put(416, vp_ah);
        linkedHashMap.put(417, vp_ai);
        linkedHashMap.put(418, vp_aj);
        linkedHashMap.put(419, vp_ak);
        linkedHashMap.put(421, vp_al);
        linkedHashMap.put(422, vp_am);
        linkedHashMap.put(423, vp_an);
        linkedHashMap.put(430, vp_ao);
        linkedHashMap.put(511, vp_ap);
        linkedHashMap.put(512, vp_aq);
        linkedHashMap.put(513, vp_ar);
        linkedHashMap.put(514, vp_as);
        linkedHashMap.put(521, vp_at);
        linkedHashMap.put(522, vp_au);
        linkedHashMap.put(523, vp_av);
        linkedHashMap.put(524, vp_aw);
        linkedHashMap.put(525, vp_ax);
        linkedHashMap.put(526, vp_ay);
        linkedHashMap.put(531, vp_az);
        linkedHashMap.put(532, vp_ba);
        linkedHashMap.put(533, vp_bb);
        linkedHashMap.put(540, vp_bc);
        return linkedHashMap;
    }

    public static int vp_a(int i) {
        if (i == 232 || i == 234 || i == 233 || i == 235 || i == 323 || i == 314 || i == 333 || i == 334 || i == 422 || i == 423 || i == 430 || i == 532 || i == 533 || i == 540) {
            return 1;
        }
        if (i == 223 || i == 231 || i == 331 || i == 332 || i == 417 || i == 419 || i == 421 || i == 522 || i == 524 || i == 526 || i == 531) {
            return 2;
        }
        if (i == 213 || i == 221 || i == 222 || i == 312 || i == 321 || i == 313 || i == 411 || i == 412 || i == 413 || i == 418 || i == 521 || i == 523 || i == 525) {
            return 3;
        }
        if (i == 212 || i == 322 || i == 414 || i == 416 || i == 512 || i == 513 || i == 514) {
            return 4;
        }
        return (i == 211 || i == 311 || i == 415 || i == 511) ? 5 : 0;
    }

    private static String vp_a(Context context, int i) {
        return context.getApplicationContext().getResources().getString(i);
    }

    private static void vp_a(Context context) {
        vp_a = vp_a(context, R.string.ai_hrv_repo_0);
        vp_b = vp_a(context, R.string.ai_hrv_repo_1);
        vp_c = vp_a(context, R.string.ai_hrv_repo_2);
        vp_d = vp_a(context, R.string.ai_hrv_repo_3);
        vp_e = vp_a(context, R.string.ai_hrv_repo_4);
        vp_f = vp_a(context, R.string.ai_hrv_repo_1_00);
        vp_g = vp_a(context, R.string.ai_hrv_repo_2_11);
        vp_h = vp_a(context, R.string.ai_hrv_repo_2_12);
        vp_i = vp_a(context, R.string.ai_hrv_repo_2_13);
        vp_j = vp_a(context, R.string.ai_hrv_repo_2_21);
        vp_k = vp_a(context, R.string.ai_hrv_repo_2_22);
        vp_l = vp_a(context, R.string.ai_hrv_repo_2_23);
        vp_m = vp_a(context, R.string.ai_hrv_repo_2_31);
        vp_n = vp_a(context, R.string.ai_hrv_repo_2_32);
        vp_o = vp_a(context, R.string.ai_hrv_repo_2_33);
        vp_p = vp_a(context, R.string.ai_hrv_repo_2_34);
        vp_q = vp_a(context, R.string.ai_hrv_repo_2_35);
        vp_r = vp_a(context, R.string.ai_hrv_repo_3_11);
        vp_s = vp_a(context, R.string.ai_hrv_repo_3_12);
        vp_t = vp_a(context, R.string.ai_hrv_repo_3_13);
        vp_u = vp_a(context, R.string.ai_hrv_repo_3_14);
        vp_v = vp_a(context, R.string.ai_hrv_repo_3_21);
        vp_w = vp_a(context, R.string.ai_hrv_repo_3_22);
        vp_x = vp_a(context, R.string.ai_hrv_repo_3_23);
        vp_y = vp_a(context, R.string.ai_hrv_repo_3_31);
        vp_z = vp_a(context, R.string.ai_hrv_repo_3_32);
        vp_aa = vp_a(context, R.string.ai_hrv_repo_3_33);
        vp_ab = vp_a(context, R.string.ai_hrv_repo_3_34);
        vp_ac = vp_a(context, R.string.ai_hrv_repo_4_11);
        vp_ad = vp_a(context, R.string.ai_hrv_repo_4_12);
        vp_ae = vp_a(context, R.string.ai_hrv_repo_4_13);
        vp_af = vp_a(context, R.string.ai_hrv_repo_4_14);
        vp_ag = vp_a(context, R.string.ai_hrv_repo_4_15);
        vp_ah = vp_a(context, R.string.ai_hrv_repo_4_16);
        vp_ai = vp_a(context, R.string.ai_hrv_repo_4_17);
        vp_aj = vp_a(context, R.string.ai_hrv_repo_4_18);
        vp_ak = vp_a(context, R.string.ai_hrv_repo_4_19);
        vp_al = vp_a(context, R.string.ai_hrv_repo_4_21);
        vp_am = vp_a(context, R.string.ai_hrv_repo_4_22);
        vp_an = vp_a(context, R.string.ai_hrv_repo_4_23);
        vp_ao = vp_a(context, R.string.ai_hrv_repo_4_30);
        vp_ap = vp_a(context, R.string.ai_hrv_repo_5_11);
        vp_aq = vp_a(context, R.string.ai_hrv_repo_5_12);
        vp_ar = vp_a(context, R.string.ai_hrv_repo_5_13);
        vp_as = vp_a(context, R.string.ai_hrv_repo_5_14);
        vp_at = vp_a(context, R.string.ai_hrv_repo_5_21);
        vp_au = vp_a(context, R.string.ai_hrv_repo_5_22);
        vp_av = vp_a(context, R.string.ai_hrv_repo_5_23);
        vp_aw = vp_a(context, R.string.ai_hrv_repo_5_24);
        vp_ax = vp_a(context, R.string.ai_hrv_repo_5_25);
        vp_ay = vp_a(context, R.string.ai_hrv_repo_5_26);
        vp_az = vp_a(context, R.string.ai_hrv_repo_5_31);
        vp_ba = vp_a(context, R.string.ai_hrv_repo_5_32);
        vp_bb = vp_a(context, R.string.ai_hrv_repo_5_33);
        vp_bc = vp_a(context, R.string.ai_hrv_repo_5_40);
    }
}
