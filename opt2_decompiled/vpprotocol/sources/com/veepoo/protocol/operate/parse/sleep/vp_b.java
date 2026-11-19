package com.veepoo.protocol.operate.parse.sleep;

import android.text.TextUtils;
import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.model.SingleSleepItem;
import com.veepoo.protocol.model.datas.SleepData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_j;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/parse/sleep/vp_b.class */
public class vp_b implements vp_a {
    ISleepDataListener vp_a;

    public vp_b(ISleepDataListener iSleepDataListener) {
        this.vp_a = iSleepDataListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void vp_b(int i, List list) {
        int sleepCount = 1;
        StringBuilder sb = new StringBuilder();
        if (sb.length() != 0) {
            sb.setLength(0);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SingleSleepItem singleSleepItem = (SingleSleepItem) it.next();
            sleepCount = singleSleepItem.getSleepCount();
            sb.append(singleSleepItem.getContent());
        }
        String[] strArr = new String[sleepCount];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= sleepCount) {
                return;
            }
            int i4 = i3 + 1;
            try {
                String strSubstring = sb.substring(i3 * 192, i4 * 192);
                strArr[i3] = strSubstring;
                vp_a(i, vp_a(i, VpBleByteUtil.spilt2word(strSubstring)));
                i2 = i4;
            } catch (StringIndexOutOfBoundsException unused) {
                printStackTrace();
                return;
            }
        }
    }

    private void vp_a(int i, SleepData sleepData) {
        if (TextUtils.isEmpty(sleepData.getDate())) {
            return;
        }
        String dateAndClockForDb = sleepData.getSleepDown().getDateAndClockForDb();
        String dateAndClockForDb2 = sleepData.getSleepUp().getDateAndClockForDb();
        if (vp_j.vp_c(dateAndClockForDb) && vp_j.vp_c(dateAndClockForDb2)) {
            if (sleepData.getSleepQulity() == 255) {
                sleepData.getSleepQulity();
                return;
            }
            if (sleepData.getSleepQulity() > 5 || sleepData.getSleepQulity() < 1) {
                sleepData.getSleepQulity();
            }
            this.vp_a.onSleepDataChange(String.valueOf(i), sleepData);
        }
    }

    private SleepData vp_a(int i, String[] strArr) {
        for (String str : strArr) {
            new StringBuilder().append("[-getSleepBean-]  content = ").append(str);
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = new int[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            iArr[i2] = Integer.valueOf(strArr[i2], 16).intValue();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        int i3 = calendar.get(1);
        int i4 = calendar.get(2) + 1;
        int i5 = (i4 != 1 || iArr[0] <= i4) ? i3 : i3 - 1;
        if (i4 == 1 && iArr[4] > i4) {
            i3--;
        }
        TimeData timeData = new TimeData(i5, iArr[1], iArr[2], iArr[3], iArr[4]);
        TimeData timeData2 = new TimeData(i3, iArr[5], iArr[6], iArr[7], iArr[8]);
        if (timeData.getDateAndClockForDb().compareTo(timeData2.getDateAndClockForDb()) > 0) {
            timeData.getDateAndClockForDb();
            timeData2.getDateAndClockForDb();
            return new SleepData();
        }
        int i6 = iArr[9];
        int i7 = iArr[10];
        int i8 = iArr[11];
        for (int i9 = 12; i9 < 43; i9++) {
            sb.append(strArr[i9]);
        }
        int i10 = iArr[43];
        new StringBuilder().append("[-getSleepBean-]  allSleepTime = ").append(i10);
        StringBuilder sb2 = new StringBuilder(vp_a(sb.toString()));
        new StringBuilder().append("[-getSleepBean-]  sleepLine = ").append(sb2.toString());
        if (i10 > 248) {
            return new SleepData();
        }
        StringBuilder sb3 = sb;
        StringBuilder sb4 = new StringBuilder(sb2.substring(0, i10));
        int i11 = iArr[55];
        for (int i12 = 44; i12 <= 54; i12++) {
            String str2 = strArr[i12];
        }
        for (int i13 = 56; i13 <= 66; i13++) {
            String str3 = strArr[i13];
        }
        int[] iArr2 = new int[i11];
        int[] iArr3 = new int[i11];
        for (int i14 = 0; i14 < i11; i14++) {
            iArr2[i14] = iArr[i14 + 45];
        }
        for (int i15 = 0; i15 < i11; i15++) {
            iArr3[i15] = iArr[i15 + 57];
        }
        if (i6 != 0) {
            try {
                sb3 = new StringBuilder(vp_a(i11, iArr2, iArr3, sb3.toString(), i7));
            } catch (Exception unused) {
                return new SleepData();
            }
        }
        return new SleepData(0, i8, i11, i6 * 5, i7 * 5, i10 * 5, sb3.toString(), timeData, timeData2);
    }

    private SingleSleepItem vp_a(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        int i3 = iArrByte2HexToIntArr[3];
        StringBuilder sb = new StringBuilder();
        for (int i4 = 4; i4 < 20; i4++) {
            sb.append(strArrByte2HexToStrArr[i4]);
        }
        return new SingleSleepItem(i, i2, i3, sb.toString());
    }

    private String vp_a(String str) {
        if (str == null || str.length() % 2 != 0) {
            return null;
        }
        String str2 = "";
        int i = 0;
        while (i < str.length()) {
            int i2 = i;
            int i3 = i2 + 1;
            i = i3;
            String str3 = "0000" + Integer.toBinaryString(Integer.parseInt(str.substring(i2, i3), 16));
            str2 = str2 + str3.substring(str3.length() - 4);
        }
        return str2;
    }

    private String vp_a(int i, int[] iArr, int[] iArr2, String str, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr2[i3];
            int i5 = iArr[i3];
            if (i2 >= i4 - i5) {
                str = vp_a(str, i5, i4 - i5);
            }
        }
        return str;
    }

    @Override // com.veepoo.protocol.operate.parse.sleep.vp_a
    public void vp_a(int i, List list) {
        if (list.size() < 6) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(vp_a((byte[]) list.get(i2)));
        }
        vp_b(i, arrayList);
    }

    private String vp_a(String str, int i, int i2) {
        char[] charArray = str.toCharArray();
        for (int i3 = i; i3 < i + i2; i3++) {
            charArray[i3] = '2';
        }
        return new String(charArray);
    }
}
