package com.veepoo.protocol.operate;

import java.util.ArrayList;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ac.class */
class vp_ac {
    private int[] vp_a = {11, 12, 13, 14, 21, 22, 23, 33, 34, 42, 52, 63, 64};

    vp_ac() {
    }

    private int[] vp_b(int i, int i2) {
        int i3 = (i2 + 1) * 100;
        int[] iArr = new int[4];
        for (int i4 = 0; i4 < 4; i4++) {
            int iVp_a = vp_a(i, i4);
            iArr[i4] = i3 + ((iVp_a == 0 ? 0 : i4 + 1) * 10) + iVp_a;
        }
        return iArr;
    }

    private boolean vp_a(int i) {
        boolean z = false;
        int i2 = i / 10;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            int[] iArr = this.vp_a;
            if (i4 >= iArr.length) {
                return z;
            }
            if (iArr[i3] == i2) {
                z = true;
            }
            i3++;
        }
    }

    private int vp_a(int i, int i2) {
        int i3 = 0;
        switch (i2) {
            case 0:
                i3 = -64;
                break;
            case 1:
                i3 = 48;
                break;
            case 2:
                i3 = 12;
                break;
            case 3:
                i3 = 3;
                break;
        }
        return (i & i3) >> (6 - (i2 * 2));
    }

    public int[] vp_a(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return new int[0];
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            int[] iArrVp_b = vp_b(iArr[i], i);
            for (int i2 = 0; i2 < iArrVp_b.length; i2++) {
                if (vp_a(iArrVp_b[i2])) {
                    arrayList.add(Integer.valueOf(iArrVp_b[i2] / 10));
                }
            }
        }
        if (arrayList.isEmpty()) {
            return new int[0];
        }
        int[] iArr2 = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }
}
