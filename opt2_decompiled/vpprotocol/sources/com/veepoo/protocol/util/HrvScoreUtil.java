package com.veepoo.protocol.util;

import android.util.Log;
import com.veepoo.protocol.model.datas.HRVOriginData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/HrvScoreUtil.class */
public class HrvScoreUtil {
    public static int getSocre(List<HRVOriginData> list) {
        return vp_a(getLorenData(list));
    }

    public static double[] getLorenData(List<HRVOriginData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Collections.sort(list);
            Iterator<HRVOriginData> it = list.iterator();
            while (it.hasNext()) {
                for (int i : VpBleByteUtil.stringToIntArr(it.next().getRate())) {
                    if (i >= 30 && i <= 210) {
                        arrayList.add(Integer.valueOf(i * 10));
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return new double[0];
        }
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            dArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        Log.e("Test", "rr data size  == " + size);
        return vp_a(dArr, 2);
    }

    public static double[] getLorenData4Ecg(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (iIntValue >= 30 && iIntValue <= 210) {
                arrayList.add(Integer.valueOf(iIntValue * 10));
            }
        }
        if (arrayList.isEmpty()) {
            return new double[0];
        }
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i = 0; i < arrayList.size(); i++) {
            dArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        Log.e("Test", "rr data size  == " + size);
        return vp_a(dArr, 2);
    }

    public static int[] getRRDataList4Ecg(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (Integer num : list) {
                if (num.intValue() >= 30 && num.intValue() <= 210) {
                    arrayList.add(num);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return new int[0];
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        Log.e("Test", "rr data size  == " + size);
        return iArr;
    }

    public static int[] getRRDataList(List<HRVOriginData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Collections.sort(list);
            Iterator<HRVOriginData> it = list.iterator();
            while (it.hasNext()) {
                for (int i : VpBleByteUtil.stringToIntArr(it.next().getRate())) {
                    if (i >= 30 && i <= 210) {
                        arrayList.add(Integer.valueOf(i));
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return new int[0];
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        Log.e("Test", "rr data size  == " + size);
        return iArr;
    }

    private static int vp_a(double[] dArr) {
        int length;
        if (dArr == null || dArr.length == 0 || (length = dArr.length - 1) == 0 || length == 1) {
            return 0;
        }
        double[] dArr2 = new double[length];
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                break;
            }
            int i4 = i3 + 1;
            double dAbs = Math.abs(dArr[i4] - dArr[i3]);
            dArr2[i3] = dAbs;
            if (dAbs >= 7.0d && dAbs <= 180.0d) {
                i++;
            }
            i2 = i4;
        }
        if (i == 1) {
            return 0;
        }
        return (i * 100) / length;
    }

    private static double[] vp_a(double[] dArr, int i) {
        if (i > dArr.length) {
            return null;
        }
        int length = (dArr.length - i) + 1;
        double[] dArr2 = new double[length];
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2;
            int i4 = (int) dArr[i2];
            int i5 = i2 + 1;
            i2 = i5;
            dArr2[i3] = (i4 + ((int) dArr[i5])) >> 1;
        }
        return dArr2;
    }
}
