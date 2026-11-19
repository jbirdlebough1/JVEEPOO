package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.model.datas.MusicData;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bp.class */
public class vp_bp extends com.veepoo.protocol.vp_a {
    private List vp_c(String str) {
        return vp_a(str, (byte) -94);
    }

    private boolean vp_d(String str) {
        return str == null || str.length() == 0;
    }

    private List vp_b(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, i);
        List listVp_c = vp_c(i);
        if (listVp_c == null || listVp_c.isEmpty()) {
            return;
        }
        Iterator it = listVp_c.iterator();
        while (it.hasNext()) {
            super.send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
        }
    }

    private byte[] vp_a(int i, int i2, List list) {
        int i3;
        byte[] bArr = new byte[20];
        bArr[0] = -103;
        bArr[1] = 1;
        bArr[2] = VpBleByteUtil.loUint16((short) i);
        bArr[3] = VpBleByteUtil.loUint16((short) i2);
        for (int i4 = 0; i4 < 16 && (i3 = ((i - 1) * 16) + i4) < list.size(); i4++) {
            bArr[i4 + 4] = ((Byte) list.get(i3)).byteValue();
        }
        return bArr;
    }

    private List vp_b(String str) {
        return vp_a(str, (byte) -95);
    }

    public List vp_c(int i) {
        List listVp_a = vp_a(i);
        int size = listVp_a.size();
        byte[] bArr = new byte[size + 2];
        bArr[0] = -16;
        bArr[1] = VpBleByteUtil.loUint16((short) size);
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = i2;
            bArr[i3 + 2] = ((Byte) listVp_a.get(i3)).byteValue();
        }
        List listVp_b = vp_b(bArr);
        int size2 = listVp_b.size();
        int i4 = size2 % 16 == 0 ? size2 / 16 : (size2 / 16) + 1;
        ArrayList arrayList = new ArrayList();
        for (int i5 = 1; i5 <= i4; i5++) {
            arrayList.add(vp_a(i5, i4, listVp_b));
        }
        return arrayList;
    }

    private List vp_a(String str) {
        return vp_a(str, (byte) -96);
    }

    private List vp_b(int i) {
        return vp_b(new byte[]{-93, 1, VpBleByteUtil.loUint16((short) i)});
    }

    public List vp_a(MusicData musicData) {
        ArrayList arrayList = new ArrayList();
        if (musicData == null) {
            return arrayList;
        }
        List listVp_a = vp_a(vp_a(musicData.getMusicAlbum()), vp_b(musicData.getMusicName()), vp_c(musicData.getSingerName()), vp_b(musicData.getPalyStatus()), vp_a(musicData.getMusicVoiceLevel()));
        int size = listVp_a.size();
        int i = size % 16 == 0 ? size / 16 : (size / 16) + 1;
        for (int i2 = 1; i2 <= i; i2++) {
            arrayList.add(vp_a(i2, i, listVp_a));
        }
        return arrayList;
    }

    private List vp_a(List list, List list2, List list3, List list4, List list5) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        arrayList.addAll(list2);
        arrayList.addAll(list3);
        arrayList.addAll(list4);
        arrayList.addAll(list5);
        int size = arrayList.size();
        byte[] bArr = new byte[size + 2];
        bArr[0] = -16;
        bArr[1] = VpBleByteUtil.loUint16((short) size);
        for (int i = 0; i < size; i++) {
            int i2 = i;
            bArr[i2 + 2] = ((Byte) arrayList.get(i2)).byteValue();
        }
        return vp_b(bArr);
    }

    private List vp_a(int i) {
        return vp_b(new byte[]{-92, 1, VpBleByteUtil.loUint16((short) i)});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    private List vp_a(String str, byte b) {
        ?? Vp_d = vp_d(str);
        if (Vp_d != 0) {
            return Collections.emptyList();
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            int length = bytes.length;
            byte[] bArr = new byte[length + 2];
            bArr[0] = b;
            bArr[1] = VpBleByteUtil.loUint16((short) length);
            for (int i = 0; i < length; i++) {
                Vp_d = bArr;
                Vp_d[i + 2] = bytes[i];
            }
            return vp_b(bArr);
        } catch (UnsupportedEncodingException unused) {
            Vp_d.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MusicData musicData) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, musicData);
        List listVp_a = vp_a(musicData);
        if (listVp_a == null || listVp_a.isEmpty()) {
            return;
        }
        Iterator it = listVp_a.iterator();
        while (it.hasNext()) {
            super.send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
        }
    }
}
