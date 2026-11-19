package com.veepoo.protocol.operate;

import android.os.SystemClock;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IWorldClockOptListener;
import com.veepoo.protocol.model.datas.WorldClock;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dg.class */
public final class vp_dg extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_k = new vp_a(null);
    private WorldClock vp_c;
    private WorldClock vp_d;
    private WorldClock vp_e;
    private WorldClock vp_f;
    private IWorldClockOptListener vp_i;
    private final List vp_g = new ArrayList();
    private final List vp_h = new ArrayList();
    private List vp_j = new ArrayList();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dg$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_dg vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dg$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_dg vp_b = new vp_dg();

        private vp_b() {
        }

        public final vp_dg vp_a() {
            return vp_b;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dg$vp_c.class */
    public static final class vp_c implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(Byte.valueOf(((byte[]) obj)[3]), Byte.valueOf(((byte[]) obj2)[3]));
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [byte, int, java.lang.Throwable] */
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
                    String str2 = "A0->(世界时钟)->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
                    WorldClock worldClockVp_b = vp_b(bArrVp_a);
                    String str3 = "解析成功：" + worldClockVp_b;
                    arrayList.add(worldClockVp_b);
                } catch (Exception unused) {
                    r0.printStackTrace();
                    Unit unit = Unit.INSTANCE;
                }
            } else {
                String str4 = "====> type = " + com.veepoo.protocol.util.vp_g.vp_d(new byte[]{(byte) r0});
            }
        }
        return arrayList;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(WorldClock worldClock, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(worldClock, "worldClock");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(worldClock, bluetoothClient, str, bleWriteResponse);
        this.vp_c = worldClock;
        byte[] bArrVp_a = vp_a(worldClock);
        String str2 = "世界时钟 byte内容：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
        int length = bArrVp_a.length / 16;
        if (bArrVp_a.length % 16 != 0) {
            length++;
        }
        ArrayList<byte[]> arrayList = new ArrayList();
        int i = 1;
        if (1 <= length) {
            while (true) {
                int i2 = i;
                int i3 = length;
                arrayList.add(vp_a(i, length, bArrVp_a));
                if (i2 == i3) {
                    break;
                } else {
                    i++;
                }
            }
        }
        for (byte[] bArr : arrayList) {
            SystemClock.sleep(10L);
            String str3 = "发送设置世界时钟命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArr);
            send(bArr, bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "worldClocks");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_b(list, bluetoothClient, str, bleWriteResponse);
        new Thread(() -> {
            vp_a(r2, r3, r4, r5, r6);
        }).start();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "worldClocks");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_d(list, bluetoothClient, str, bleWriteResponse);
        new Thread(() -> {
            vp_b(r2, r3, r4, r5, r6);
        }).start();
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IWorldClockOptListener iWorldClockOptListener;
        IWorldClockOptListener iWorldClockOptListener2;
        IWorldClockOptListener iWorldClockOptListener3;
        WorldClock worldClock;
        IWorldClockOptListener iWorldClockOptListener4;
        IWorldClockOptListener iWorldClockOptListener5;
        super.handler(bArr);
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 2) {
                byte b2 = bArr[2];
                if (b2 != 1) {
                    if (b2 != 0 || (iWorldClockOptListener5 = this.vp_i) == null) {
                        return;
                    }
                    iWorldClockOptListener5.onWorldClockOptFailed(IWorldClockOptListener.WorldClockOpt.READ);
                    return;
                }
                byte b3 = bArr[3];
                byte b4 = bArr[4];
                if (b3 == 0 && b4 == 0) {
                    this.vp_j.clear();
                    IWorldClockOptListener iWorldClockOptListener6 = this.vp_i;
                    if (iWorldClockOptListener6 != null) {
                        iWorldClockOptListener6.onWorldClockReadSuccess(new ArrayList(), 0);
                        return;
                    }
                    return;
                }
                if (b3 == 1) {
                    this.vp_g.clear();
                }
                this.vp_g.add(bArr);
                if (b3 == b4) {
                    byte[] bArrVp_a = vp_a(this.vp_g);
                    String str = "读取结束，拼接有效数据：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
                    List<WorldClock> listVp_c = vp_c(bArrVp_a);
                    int i = 0;
                    for (Object obj : listVp_c) {
                        int i2 = i;
                        int i3 = i + 1;
                        if (i2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        String str2 = "读取结束，解析世界时钟【" + i + "】：" + ((WorldClock) obj);
                        i = i3;
                    }
                    this.vp_j.clear();
                    this.vp_j.addAll(listVp_c);
                    IWorldClockOptListener iWorldClockOptListener7 = this.vp_i;
                    if (iWorldClockOptListener7 != null) {
                        iWorldClockOptListener7.onWorldClockReadSuccess(listVp_c, 0);
                        return;
                    }
                    return;
                }
                return;
            }
            if (b == 1) {
                byte b5 = bArr[2];
                if (b5 != 1) {
                    if (b5 != 0 || (iWorldClockOptListener4 = this.vp_i) == null) {
                        return;
                    }
                    iWorldClockOptListener4.onWorldClockOptFailed(IWorldClockOptListener.WorldClockOpt.ADD);
                    return;
                }
                int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[4], bArr[3]);
                List list = this.vp_j;
                WorldClock worldClock2 = this.vp_c;
                Intrinsics.checkNotNull(worldClock2);
                list.add(worldClock2);
                IWorldClockOptListener iWorldClockOptListener8 = this.vp_i;
                if (iWorldClockOptListener8 != null) {
                    iWorldClockOptListener8.onWorldClockOptSuccess(IWorldClockOptListener.WorldClockOpt.ADD, iVp_b);
                    return;
                }
                return;
            }
            if (b == 3) {
                byte b6 = bArr[2];
                if (b6 != 1) {
                    if (b6 != 0 || (iWorldClockOptListener3 = this.vp_i) == null) {
                        return;
                    }
                    iWorldClockOptListener3.onWorldClockOptFailed(IWorldClockOptListener.WorldClockOpt.MOVE);
                    return;
                }
                int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArr[4], bArr[3]);
                if ((!this.vp_j.isEmpty()) && (worldClock = this.vp_e) != null && this.vp_f != null) {
                    Intrinsics.checkNotNull(worldClock);
                    int iVp_a = vp_a(worldClock.getId());
                    WorldClock worldClock3 = this.vp_f;
                    Intrinsics.checkNotNull(worldClock3);
                    int iVp_a2 = vp_a(worldClock3.getId());
                    if (iVp_a != -1 && iVp_a2 != -1) {
                        List list2 = this.vp_j;
                        WorldClock worldClock4 = this.vp_f;
                        Intrinsics.checkNotNull(worldClock4);
                        list2.set(iVp_a, worldClock4);
                        List list3 = this.vp_j;
                        WorldClock worldClock5 = this.vp_e;
                        Intrinsics.checkNotNull(worldClock5);
                        list3.set(iVp_a2, worldClock5);
                    }
                }
                IWorldClockOptListener iWorldClockOptListener9 = this.vp_i;
                if (iWorldClockOptListener9 != null) {
                    iWorldClockOptListener9.onWorldClockOptSuccess(IWorldClockOptListener.WorldClockOpt.MOVE, iVp_b2);
                    return;
                }
                return;
            }
            if (b == 4) {
                byte b7 = bArr[2];
                if (b7 != 1) {
                    if (b7 != 0 || (iWorldClockOptListener2 = this.vp_i) == null) {
                        return;
                    }
                    iWorldClockOptListener2.onWorldClockOptFailed(IWorldClockOptListener.WorldClockOpt.DELETE);
                    return;
                }
                int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArr[4], bArr[3]);
                TypeIntrinsics.asMutableCollection(this.vp_j).remove(this.vp_d);
                IWorldClockOptListener iWorldClockOptListener10 = this.vp_i;
                if (iWorldClockOptListener10 != null) {
                    iWorldClockOptListener10.onWorldClockOptSuccess(IWorldClockOptListener.WorldClockOpt.DELETE, iVp_b3);
                    return;
                }
                return;
            }
            if (b == 5) {
                byte b8 = bArr[2];
                if (b8 != 1) {
                    if (b8 != 0 || (iWorldClockOptListener = this.vp_i) == null) {
                        return;
                    }
                    iWorldClockOptListener.onWorldClockOptFailed(IWorldClockOptListener.WorldClockOpt.DELETE_BY_DEVICE);
                    return;
                }
                int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArr[4], bArr[3]);
                IWorldClockOptListener iWorldClockOptListener11 = this.vp_i;
                if (iWorldClockOptListener11 != null) {
                    iWorldClockOptListener11.onWorldClockOptSuccess(IWorldClockOptListener.WorldClockOpt.DELETE_BY_DEVICE, iVp_b4);
                }
            }
        }
    }

    private static final void vp_a(List list, vp_dg vp_dgVar, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        int i = 0;
        for (Object obj : list) {
            int i2 = i;
            i++;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            vp_dgVar.vp_a((WorldClock) obj, bluetoothClient, str, bleWriteResponse);
            SystemClock.sleep(30L);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(WorldClock worldClock, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(worldClock, "worldClock");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_b(worldClock, bluetoothClient, str, bleWriteResponse);
        this.vp_d = worldClock;
        byte[] bArr = new byte[20];
        bArr[0] = -66;
        bArr[1] = 4;
        bArr[2] = (byte) worldClock.getId();
        String str2 = "发送删除世界时钟命令：delete:" + worldClock + "  . " + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private final byte[] vp_a(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[20];
        bArr2[0] = -66;
        bArr2[1] = 1;
        bArr2[2] = (byte) i;
        bArr2[3] = (byte) i2;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = ((i - 1) * 16) + i3;
            bArr2[i3 + 4] = i4 < bArr.length ? bArr[i4] : (byte) 0;
        }
        return bArr2;
    }

    private static final void vp_b(List list, vp_dg vp_dgVar, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        int i = 0;
        for (Object obj : list) {
            int i2 = i;
            i++;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            vp_dgVar.vp_b((WorldClock) obj, bluetoothClient, str, bleWriteResponse);
            SystemClock.sleep(30L);
        }
    }

    private final WorldClock vp_b(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        String str = "";
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            byte b = byteBufferOrder.get();
            if (b == -95) {
                byte b2 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a = vp_a(b2, byteBufferOrder, "0xA1", bArr);
                int iVp_a = com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArrVp_a);
                String str2 = "A1->(ID)-> " + iVp_a + " -> " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
                i = iVp_a;
            } else if (b == -94) {
                byte b3 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a2 = vp_a(b3, byteBufferOrder, "0xA2", bArr);
                int iVp_a2 = com.veepoo.protocol.util.vp_e.vp_a.vp_a(bArrVp_a2);
                String str3 = "A2->(时差)-> " + iVp_a2 + " -> " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2);
                i2 = iVp_a2;
            } else if (b == -93) {
                byte b4 = byteBufferOrder.get();
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a3 = vp_a(b4, byteBufferOrder, "0xA3", bArr);
                String strVp_b = com.veepoo.protocol.util.vp_e.vp_a.vp_b(bArrVp_a3);
                String str4 = "A3->(时区)-> " + strVp_b + " -> " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a3);
                str = strVp_b;
            } else {
                String str5 = "parse2WorldClock====> type = " + com.veepoo.protocol.util.vp_g.vp_d(new byte[]{b});
            }
        }
        WorldClock worldClock = new WorldClock(i, i2, str, null, 8, null);
        String str6 = "解析读取世界时钟---> " + worldClock;
        return worldClock;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v4, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    private final byte[] vp_a(int i, ByteBuffer byteBuffer, String str, byte[] bArr) {
        ?? byteArray = bArr;
        int iPosition = byteBuffer.position();
        int i2 = iPosition + i;
        String str2 = "--------" + str + "---------- length = " + i + "  [" + iPosition + " - " + i2 + "]";
        String str3 = "--------> = " + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        byteBuffer.position(i2);
        ?? r8 = new byte[0];
        try {
            byteArray = CollectionsKt.toByteArray(ArraysKt.slice((byte[]) byteArray, RangesKt.until(iPosition, i2)));
            r8 = byteArray;
        } catch (Exception unused) {
            byteArray.printStackTrace();
        }
        return r8;
    }

    private final int vp_a(int i) {
        int i2 = -1;
        if (!this.vp_j.isEmpty()) {
            int size = this.vp_j.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (((WorldClock) this.vp_j.get(i3)).getId() == i) {
                    i2 = i3;
                    break;
                }
            }
        }
        return i2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_b(i, bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -66;
        bArr[1] = 2;
        short s = (short) i;
        bArr[2] = com.veepoo.protocol.util.vp_g.vp_b(s);
        bArr[3] = com.veepoo.protocol.util.vp_g.vp_a(s);
        String str2 = "发送读取世界时钟命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private final byte[] vp_a(List list) {
        ArrayList arrayList = new ArrayList();
        for (byte[] bArr : CollectionsKt.sortedWith(list, new vp_c())) {
            com.veepoo.protocol.util.vp_g.vp_d(bArr);
            Iterator it = ArraysKt.slice(bArr, RangesKt.until(5, bArr.length)).iterator();
            while (it.hasNext()) {
                arrayList.add(Byte.valueOf(((Number) it.next()).byteValue()));
            }
        }
        byte[] byteArray = CollectionsKt.toByteArray(arrayList);
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(byteArray[1], byteArray[0]);
        byte[] bArr2 = new byte[iVp_b];
        for (int i = 2; i < iVp_b; i++) {
            bArr2[i - 2] = byteArray[i];
        }
        return bArr2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(WorldClock worldClock, WorldClock worldClock2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(worldClock, "from");
        Intrinsics.checkNotNullParameter(worldClock2, "to");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(worldClock, worldClock2, bluetoothClient, str, bleWriteResponse);
        this.vp_e = worldClock;
        this.vp_f = worldClock2;
        byte[] bArr = new byte[20];
        bArr[0] = -66;
        bArr[1] = 3;
        bArr[2] = (byte) vp_a(worldClock.getId());
        bArr[3] = (byte) vp_a(worldClock2.getId());
        String str2 = "发送移动世界时钟命令：from:" + worldClock + " -> to:" + worldClock2 + " . " + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(int i, int i2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(i, i2, bluetoothClient, str, bleWriteResponse);
        if ((!this.vp_j.isEmpty()) && this.vp_j.size() > i && this.vp_j.size() > i2) {
            this.vp_e = (WorldClock) this.vp_j.get(i);
            this.vp_f = (WorldClock) this.vp_j.get(i2);
        }
        byte[] bArr = new byte[20];
        bArr[0] = -66;
        bArr[1] = 3;
        bArr[2] = (byte) i;
        bArr[3] = (byte) i2;
        String str2 = "发送移动世界时钟命令：from:" + i + " -> to:" + i2 + " . " + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IWorldClockOptListener iWorldClockOptListener) {
        this.vp_i = iWorldClockOptListener;
    }

    private final byte[] vp_a(WorldClock worldClock) {
        byte[] bytes = worldClock.getTimeZoneName().getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String str = worldClock + " ---- " + com.veepoo.protocol.util.vp_g.vp_d(bytes);
        int length = bytes.length;
        byte[] bArr = new byte[length + 11];
        bArr[0] = -96;
        byte[] bArrVp_c = com.veepoo.protocol.util.vp_g.vp_c(length + 8);
        bArr[1] = bArrVp_c[0];
        bArr[2] = bArrVp_c[1];
        bArr[3] = -95;
        bArr[4] = 1;
        bArr[5] = (byte) worldClock.getId();
        bArr[6] = -94;
        bArr[7] = 1;
        bArr[8] = (byte) worldClock.getTimeZone();
        bArr[9] = -93;
        bArr[10] = (byte) bytes.length;
        int i = 0;
        for (byte b : bytes) {
            int i2 = i;
            i++;
            bArr[i2 + 11] = b;
        }
        return bArr;
    }
}
