package com.veepoo.protocol.operate;

import android.os.Build;
import android.os.SystemClock;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IContactOptListener;
import com.veepoo.protocol.listener.data.ISOSCallTimesListener;
import com.veepoo.protocol.model.datas.Contact;
import com.veepoo.protocol.model.enums.EContactOpt;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_r.class */
public final class vp_r extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_m = new vp_a(null);
    private List vp_c;
    private Contact vp_d;
    public IContactOptListener vp_e;
    public ISOSCallTimesListener vp_f;
    private ArrayList vp_g;
    private byte vp_h;
    private byte vp_i;
    private byte vp_j;
    private byte vp_k;
    private byte vp_l;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_r$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_r vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_r$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_r vp_b = new vp_r(null);

        private vp_b() {
        }

        public final vp_r vp_a() {
            return vp_b;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_r$vp_c.class */
    public static final class vp_c implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(Byte.valueOf(((byte[]) obj)[3]), Byte.valueOf(((byte[]) obj2)[3]));
        }
    }

    private vp_r() {
        this.vp_c = new ArrayList();
        this.vp_g = new ArrayList();
        this.vp_h = (byte) -96;
        this.vp_i = (byte) -95;
        this.vp_j = (byte) -94;
        this.vp_k = (byte) -93;
        this.vp_l = (byte) -92;
    }

    public final List vp_d() {
        return this.vp_c;
    }

    public final ISOSCallTimesListener vp_e() {
        ISOSCallTimesListener iSOSCallTimesListener = this.vp_f;
        if (iSOSCallTimesListener != null) {
            return iSOSCallTimesListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sosCallTimeListener");
        return null;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(contact, bluetoothClient, str, bleWriteResponse);
        ArrayList arrayList = new ArrayList();
        arrayList.add(contact);
        Iterator it = vp_b(arrayList).iterator();
        while (it.hasNext()) {
            send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_b(contact, bluetoothClient, str, bleWriteResponse);
        this.vp_d = contact;
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = 4;
        bArr[2] = com.veepoo.protocol.util.vp_g.vp_b((short) contact.getContactID());
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "contacts");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_c(list, bluetoothClient, str, bleWriteResponse);
        new Thread(() -> {
            vp_b(r2, r3, r4, r5, r6);
        }).start();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ai(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_ai(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = 5;
        bArr[2] = 2;
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 1) {
                if (bArr[2] != 1) {
                    vp_c().onContactOptFailed(EContactOpt.SETTING);
                    return;
                } else {
                    vp_c().onContactOptSuccess(EContactOpt.SETTING, com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArr[3], bArr[4]));
                    return;
                }
            }
            if (b == 2) {
                if (bArr[2] == 0) {
                    vp_c().onContactReadFailed();
                    return;
                }
                byte b2 = bArr[3];
                if (b2 == 0 && bArr[4] != 0) {
                    vp_c().onContactReadASSameCRC();
                    return;
                }
                if (b2 == 0 && bArr[4] == 0) {
                    vp_c().onContactReadSuccess(new ArrayList());
                    return;
                }
                if (b2 == 1) {
                    this.vp_g.clear();
                }
                this.vp_g.add(bArr);
                if (bArr[3] == bArr[4]) {
                    List listVp_d = vp_d(this.vp_g);
                    this.vp_c.clear();
                    this.vp_c.addAll(listVp_d);
                    vp_c().onContactReadSuccess(this.vp_c);
                    return;
                }
                return;
            }
            if (b == 3) {
                if (bArr[2] != 1) {
                    vp_c().onContactOptFailed(EContactOpt.MOVE);
                    return;
                } else {
                    vp_c().onContactOptSuccess(EContactOpt.MOVE, com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArr[3], bArr[4]));
                    return;
                }
            }
            if (b == 4) {
                if (bArr[2] != 1) {
                    vp_c().onContactOptFailed(EContactOpt.DELETE);
                    return;
                }
                int iVp_a = com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArr[3], bArr[4]);
                Contact contact = this.vp_d;
                if (contact != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        List list = this.vp_c;
                        Function1 function1 = (v1) -> {
                            return vp_a(r1, v1);
                        };
                        list.removeIf((v1) -> {
                            return vp_a(r1, v1);
                        });
                    } else {
                        this.vp_c.remove(contact);
                    }
                }
                vp_c().onContactOptSuccess(EContactOpt.DELETE, iVp_a);
                return;
            }
            if (b != 5) {
                if (b == 6) {
                    if (bArr[2] != 1) {
                        vp_c().onContactOptFailed(EContactOpt.SETTING_SOS);
                        return;
                    } else {
                        vp_c().onContactOptSuccess(EContactOpt.SETTING_SOS, com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArr[3], bArr[4]));
                        return;
                    }
                }
                return;
            }
            byte b3 = bArr[2];
            if (b3 == 1) {
                if (bArr[3] != 1) {
                    vp_e().onSOSCallTimesSettingFailed();
                    return;
                } else {
                    vp_e().onSOSCallTimesSettingSuccess(bArr[4]);
                    return;
                }
            }
            if (b3 == 2) {
                if (bArr[3] != 1) {
                    vp_e().onSOSCallTimesReadFailed();
                    return;
                }
                vp_e().onSOSCallTimesReadSuccess(bArr[4], bArr[5], bArr[6]);
            }
        }
    }

    private final List vp_d(List list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(vp_c(vp_c(list)));
        return arrayList;
    }

    private final List vp_c(Contact contact) {
        ArrayList arrayList = new ArrayList();
        List listVp_b = vp_b(contact);
        int size = listVp_b.size();
        int i = size % 16 == 0 ? size / 16 : (size / 16) + 1;
        int i2 = 1;
        if (1 <= i) {
            while (true) {
                int i3 = i2;
                int i4 = i;
                arrayList.add(vp_a(i2, i, listVp_b, true));
                if (i3 == i4) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    private static final void vp_b(List list, vp_r vp_rVar, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            vp_rVar.vp_b((Contact) it.next(), bluetoothClient, str, bleWriteResponse);
            SystemClock.sleep(100L);
        }
    }

    public /* synthetic */ vp_r(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "contacts");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(list, bluetoothClient, str, bleWriteResponse);
        new Thread(() -> {
            vp_a(r2, r3, r4, r5, r6);
        }).start();
    }

    private static final void vp_a(List list, vp_r vp_rVar, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            vp_rVar.vp_a((Contact) it.next(), bluetoothClient, str, bleWriteResponse);
            SystemClock.sleep(40L);
        }
    }

    public final IContactOptListener vp_c() {
        IContactOptListener iContactOptListener = this.vp_e;
        if (iContactOptListener != null) {
            return iContactOptListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contactOptListener");
        return null;
    }

    public final int vp_b() {
        return 10 - this.vp_c.size();
    }

    private final byte[] vp_c(List list) {
        ArrayList arrayList = new ArrayList();
        CollectionsKt.sortedWith(list, new vp_c());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Iterator it2 = ArraysKt.slice((byte[]) it.next(), new IntRange(5, 19)).iterator();
            while (it2.hasNext()) {
                arrayList.add(Byte.valueOf(((Number) it2.next()).byteValue()));
            }
        }
        return CollectionsKt.toByteArray(arrayList);
    }

    private final List vp_b(Contact contact) {
        LinkedList linkedList = new LinkedList();
        com.veepoo.protocol.util.vp_f.vp_a vp_aVar = com.veepoo.protocol.util.vp_f.vp_a;
        List listVp_a = vp_aVar.vp_a(Integer.valueOf(contact.getContactID()), this.vp_i);
        List listVp_a2 = vp_aVar.vp_a(com.veepoo.protocol.util.vp_g.vp_b(contact.getName(), 20), this.vp_j);
        List listVp_a3 = vp_aVar.vp_a(com.veepoo.protocol.util.vp_g.vp_b(contact.getPhoneNumber(), 22), this.vp_k);
        List listVp_a4 = vp_a(Integer.valueOf(contact.isSettingSOS() ? 1 : 0), this.vp_l);
        linkedList.addAll(listVp_a);
        linkedList.addAll(listVp_a2);
        linkedList.addAll(listVp_a3);
        if (contact.isSupportSOS()) {
            linkedList.addAll(listVp_a4);
        }
        short size = (short) (linkedList.size() + 3);
        byte bVp_a = com.veepoo.protocol.util.vp_g.vp_a(size);
        byte bVp_b = com.veepoo.protocol.util.vp_g.vp_b(size);
        linkedList.addFirst(Byte.valueOf(bVp_a));
        linkedList.addFirst(Byte.valueOf(bVp_b));
        linkedList.addFirst(Byte.valueOf(this.vp_h));
        return linkedList;
    }

    public final void vp_a() {
        this.vp_c.clear();
        this.vp_d = null;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [byte, java.lang.Throwable] */
    private final List vp_c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            ?? r0 = byteBufferOrder.get();
            if (r0 == -96) {
                try {
                    int iVp_a = com.veepoo.protocol.util.vp_e.vp_a.vp_a(byteBufferOrder.get(), byteBufferOrder.get());
                    String str = "A0->(length)->" + iVp_a;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a = vp_a(iVp_a, byteBufferOrder, "0xA0", bArr);
                    String str2 = "A0->(单条联系人)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
                    arrayList.add(vp_b(bArrVp_a));
                } catch (Exception unused) {
                    r0.printStackTrace();
                    Unit unit = Unit.INSTANCE;
                }
            } else {
                String str3 = "冗余字符->" + r0;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v4, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    private final byte[] vp_a(int i, ByteBuffer byteBuffer, String str, byte[] bArr) {
        ?? byteArray = bArr;
        String str2 = "--------" + str + "----------";
        int iPosition = byteBuffer.position();
        int i2 = iPosition + i;
        byteBuffer.position(i2);
        ?? r6 = new byte[0];
        try {
            byteArray = CollectionsKt.toByteArray(ArraysKt.slice((byte[]) byteArray, RangesKt.until(iPosition, i2)));
            r6 = byteArray;
        } catch (Exception unused) {
            byteArray.printStackTrace();
        }
        return r6;
    }

    private final List vp_b(List list) {
        ArrayList arrayList = new ArrayList();
        List listVp_a = vp_a(list);
        int size = listVp_a.size();
        int i = size % 16 == 0 ? size / 16 : (size / 16) + 1;
        int i2 = 1;
        if (1 <= i) {
            while (true) {
                int i3 = i2;
                int i4 = i;
                arrayList.add(vp_a(this, i2, i, listVp_a, false, 8, (Object) null));
                if (i3 == i4) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    private final byte[] vp_a(int i, int i2, List list, boolean z) {
        int i3;
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = z ? (byte) 6 : (byte) 1;
        bArr[2] = com.veepoo.protocol.util.vp_g.vp_b((short) i);
        bArr[3] = com.veepoo.protocol.util.vp_g.vp_b((short) i2);
        for (int i4 = 0; i4 < 16 && (i3 = ((i - 1) * 16) + i4) < list.size(); i4++) {
            bArr[i4 + 4] = ((Number) list.get(i3)).byteValue();
        }
        return bArr;
    }

    public final void vp_b(int i, int i2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = 3;
        bArr[2] = com.veepoo.protocol.util.vp_g.vp_b((short) i);
        bArr[3] = com.veepoo.protocol.util.vp_g.vp_b((short) i2);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_c(i, bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = 5;
        bArr[2] = 1;
        bArr[3] = (byte) i;
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private final Contact vp_b(byte[] bArr) {
        Contact contact = new Contact(-1, "", "", false, false);
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            byte b = byteBufferOrder.get();
            if (b == -95) {
                byte b2 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a = vp_a(b2, byteBufferOrder, "0xA1", bArr);
                contact.setContactID(com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArrVp_a));
                String str = "A1->(ID)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
            } else if (b == -94) {
                byte b3 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a2 = vp_a(b3, byteBufferOrder, "0xA2", bArr);
                String str2 = "A2->(NickName)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2);
                contact.setName(com.veepoo.protocol.util.vp_e.vp_a.vp_b(bArrVp_a2));
            } else if (b == -93) {
                byte b4 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a3 = vp_a(b4, byteBufferOrder, "0xA3", bArr);
                contact.setPhoneNumber(com.veepoo.protocol.util.vp_e.vp_a.vp_b(bArrVp_a3));
                String str3 = "A3->(tel)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a3);
            } else if (b == -92) {
                byte b5 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a4 = vp_a(b5, byteBufferOrder, "0xA4", bArr);
                boolean z = com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArrVp_a4) == 1;
                String str4 = "A4->(SOS)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a4);
                contact.setSettingSOS(z);
                contact.setSupportSOS(VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportSOSContactFunction());
            } else {
                String.valueOf((int) b);
            }
        }
        return contact;
    }

    static /* synthetic */ byte[] vp_a(vp_r vp_rVar, int i, int i2, List list, boolean z, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            z = false;
        }
        return vp_rVar.vp_a(i, i2, list, z);
    }

    private final List vp_a(Contact contact) {
        LinkedList linkedList = new LinkedList();
        com.veepoo.protocol.util.vp_f.vp_a vp_aVar = com.veepoo.protocol.util.vp_f.vp_a;
        List listVp_a = vp_aVar.vp_a(Integer.valueOf(contact.getContactID()), this.vp_i);
        List listVp_a2 = vp_aVar.vp_a(com.veepoo.protocol.util.vp_g.vp_b(contact.getName(), 20), this.vp_j);
        List listVp_a3 = vp_aVar.vp_a(com.veepoo.protocol.util.vp_g.vp_b(contact.getPhoneNumber(), 22), this.vp_k);
        List listVp_a4 = vp_a(Integer.valueOf(contact.isSettingSOS() ? 1 : 0), this.vp_l);
        linkedList.addAll(listVp_a);
        linkedList.addAll(listVp_a2);
        linkedList.addAll(listVp_a3);
        if (contact.isSupportSOS()) {
            linkedList.addAll(listVp_a4);
        }
        short size = (short) (linkedList.size() + 3);
        byte bVp_a = com.veepoo.protocol.util.vp_g.vp_a(size);
        byte bVp_b = com.veepoo.protocol.util.vp_g.vp_b(size);
        linkedList.addFirst(Byte.valueOf(bVp_a));
        linkedList.addFirst(Byte.valueOf(bVp_b));
        linkedList.addFirst(Byte.valueOf(this.vp_h));
        return linkedList;
    }

    private final List vp_a(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(vp_a((Contact) it.next()));
        }
        return arrayList;
    }

    private final List vp_a(Integer num, byte b) {
        Intrinsics.checkNotNull(num);
        return ArraysKt.toList(new byte[]{b, 1, com.veepoo.protocol.util.vp_g.vp_b((short) num.intValue())});
    }

    private static final boolean vp_a(Contact contact, Contact contact2) {
        Intrinsics.checkNotNullParameter(contact2, "ctt");
        return contact.getContactID() == contact2.getContactID();
    }

    private static final boolean vp_a(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(Contact contact, Contact contact2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(contact, "from");
        Intrinsics.checkNotNullParameter(contact2, "to");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(contact, contact2, bluetoothClient, str, bleWriteResponse);
        vp_b(contact.getContactID(), contact2.getContactID(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(i, bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = 114;
        bArr[1] = 2;
        short s = (short) i;
        bArr[2] = com.veepoo.protocol.util.vp_g.vp_b(s);
        bArr[3] = com.veepoo.protocol.util.vp_g.vp_a(s);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IContactOptListener iContactOptListener) {
        Intrinsics.checkNotNullParameter(iContactOptListener, "<set-?>");
        this.vp_e = iContactOptListener;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(z, contact, bluetoothClient, str, bleWriteResponse);
        if (contact.isSupportSOS()) {
            Iterator it = vp_c(contact).iterator();
            while (it.hasNext()) {
                send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
            }
        }
    }

    public final void vp_a(ISOSCallTimesListener iSOSCallTimesListener) {
        Intrinsics.checkNotNullParameter(iSOSCallTimesListener, "<set-?>");
        this.vp_f = iSOSCallTimesListener;
    }
}
