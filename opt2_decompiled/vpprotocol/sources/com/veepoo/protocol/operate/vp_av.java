package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IGameClockOptListener;
import com.veepoo.protocol.model.datas.GameClockBean;
import com.veepoo.protocol.model.enums.EGameClockType;
import com.veepoo.protocol.util.opus.AiOpus2PcmManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_av.class */
public final class vp_av extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_f = new vp_a(null);
    private static int[] vp_g = {0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, 61441, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, AiOpus2PcmManager.DEFAULT_AUDIO_SAMPLE_RATE, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, AiOpus2PcmManager.BITRATE, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};
    private final List vp_c = new ArrayList();
    private boolean vp_d;
    private IGameClockOptListener vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_av$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_av vp_b() {
            return vp_b.vp_a.vp_a();
        }

        public final int vp_a(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return 0;
            }
            int i = 65535;
            for (byte b : bArr) {
                int i2 = i;
                i = (((i2 >> 8) & 255) ^ vp_a()[(i2 & 255) ^ (b & 255)]) & 65535;
            }
            return i & 65535;
        }

        public final int[] vp_a() {
            return vp_av.vp_g;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_av$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static vp_av vp_b = new vp_av();

        private vp_b() {
        }

        public final vp_av vp_a() {
            return vp_b;
        }
    }

    private final byte[] vp_b(List list, Boolean bool) {
        byte[] bArr = new byte[20];
        bArr[0] = -65;
        bArr[1] = 3;
        bArr[2] = 1;
        bArr[3] = 1;
        bArr[4] = -96;
        bArr[5] = 2;
        byte[] bArrVp_a = vp_a(list, bool);
        bArr[6] = bArrVp_a[0];
        bArr[7] = bArrVp_a[1];
        return bArr;
    }

    private final List vp_d(byte[] bArr) {
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[9], bArr[8]);
        int i = iVp_b / 28;
        int i2 = i;
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "数据：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "数据总长度：" + iVp_b + "，游戏时段个数：" + i2);
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            return arrayList;
        }
        int length = (bArr.length - 12) / 28;
        if (length < i2) {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "设备上报长度与内容不符，按实际可解析条数 " + length + " 处理");
            i2 = length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = new byte[26];
            System.arraycopy(bArr, (i3 * 28) + 12, bArr2, 0, 26);
            com.veepoo.protocol.util.vp_w.vp_d("-游戏设置-", "第" + i3 + "条：" + com.veepoo.protocol.util.vp_g.vp_d(bArr2));
            arrayList.add(vp_c(bArr2));
        }
        return arrayList;
    }

    private final GameClockBean vp_c(byte[] bArr) {
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "解析一条数据：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        int i = bArr[2] & 255;
        EGameClockType eGameClockTypeFromValue = EGameClockType.fromValue(bArr[5] & 255);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        if (eGameClockTypeFromValue.hasDate()) {
            i2 = (bArr[8] & 255) + 2000;
            i3 = bArr[9] & 255;
            i4 = bArr[10] & 255;
        }
        GameClockBean gameClockBean = new GameClockBean(i, eGameClockTypeFromValue, i2, i3, i4, bArr[13] & 255, bArr[16] & 255, bArr[17] & 255, bArr[18] & 255, com.veepoo.protocol.util.vp_g.vp_b(bArr[22], bArr[21]), bArr[25] == 1);
        com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "解析单条游戏时段 body：" + gameClockBean.toSmartStr());
        return gameClockBean;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "收到上报数据 " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr.length < 3) {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "游戏设置上报数据长度不足，忽略");
            return;
        }
        byte b = bArr[1];
        if (b == 1) {
            IGameClockOptListener iGameClockOptListener = this.vp_e;
            if (iGameClockOptListener != null) {
                iGameClockOptListener.onDeleteResult(bArr[2] == 1);
                return;
            }
            return;
        }
        if (b == 2) {
            IGameClockOptListener iGameClockOptListener2 = this.vp_e;
            if (iGameClockOptListener2 != null) {
                iGameClockOptListener2.onSettingResult(bArr[2] == 1);
                return;
            }
            return;
        }
        if (b == 3) {
            vp_b(bArr);
        } else {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "未知的游戏设置子命令：" + b);
        }
    }

    private final byte[] vp_b() {
        if (this.vp_c.isEmpty()) {
            return null;
        }
        int length = 0;
        Iterator it = this.vp_c.iterator();
        while (it.hasNext()) {
            length += ((byte[]) it.next()).length - 4;
        }
        int i = length;
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", " === > 总数据 " + i + " 字节，共 " + this.vp_c.size() + " 包");
        if (i <= 0) {
            return null;
        }
        byte[] bArr = new byte[length];
        int i2 = 0;
        int i3 = 0;
        for (Object obj : this.vp_c) {
            int i4 = i3;
            int i5 = i3 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            byte[] bArr2 = (byte[]) obj;
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", " === > 第[" + i3 + "]包 = " + com.veepoo.protocol.util.vp_g.vp_d(bArr2));
            int length2 = bArr2.length - 4;
            if (length2 > 0) {
                System.arraycopy(bArr2, 4, bArr, i2, length2);
                i2 += length2;
            }
            i3 = i5;
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, List list, List list2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "deleteIDs");
        Intrinsics.checkNotNullParameter(list2, "allBeans");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_a(z, list, list2, bluetoothClient, str, bleWriteResponse);
        if (!list.isEmpty()) {
            byte[] bArrVp_a = vp_a(z, list, list2);
            com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送【批量删除】游戏设置 id：" + list);
            com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送【批量删除】游戏设置命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
            send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
            return;
        }
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "批量删除游戏设置失败：deleteIDs 为空");
        IGameClockOptListener iGameClockOptListener = this.vp_e;
        if (iGameClockOptListener != null) {
            iGameClockOptListener.onDeleteResult(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_b(boolean z, List list, List list2) {
        int size = list.size() * 28;
        byte[] bArr = new byte[size];
        int i = 0;
        for (Object obj : list) {
            int i2 = i;
            int i3 = i + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            byte[] bArrVp_a = vp_a((GameClockBean) obj);
            System.arraycopy(bArrVp_a, 0, bArr, i * 28, bArrVp_a.length);
            i = i3;
        }
        byte[] bArr2 = new byte[size + 10];
        bArr2[0] = -96;
        bArr2[1] = 2;
        byte[] bArrVp_a2 = vp_a(list2, Boolean.valueOf(z));
        bArr2[2] = bArrVp_a2[0];
        bArr2[3] = bArrVp_a2[1];
        bArr2[4] = -95;
        bArr2[5] = 1;
        bArr2[6] = z;
        bArr2[7] = -93;
        byte[] bArrVp_d = com.veepoo.protocol.util.vp_g.vp_d(size);
        bArr2[8] = bArrVp_d[1];
        bArr2[9] = bArrVp_d[0];
        System.arraycopy(bArr, 0, bArr2, 10, size);
        return bArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final byte[] vp_a(List list, Boolean bool) {
        byte[] bArr = new byte[2];
        boolean zBooleanValue = bool != null ? bool.booleanValue() : this.vp_d;
        byte[] bArr2 = new byte[(list.size() * 28) + 1];
        bArr2[0] = zBooleanValue;
        int i = 0;
        for (Object obj : list) {
            int i2 = i;
            int i3 = i + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            byte[] bArrVp_a = vp_a((GameClockBean) obj);
            System.arraycopy(bArrVp_a, 0, bArr2, (i * 28) + 1, bArrVp_a.length);
            i = i3;
        }
        int iVp_a = vp_f.vp_a(bArr2);
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "本地数据 CRC 内容 = " + com.veepoo.protocol.util.vp_g.vp_d(bArr2));
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "本地数据 CRC 值 = " + iVp_a);
        short s = (short) iVp_a;
        bArr[0] = com.veepoo.protocol.util.vp_g.vp_b(s);
        bArr[1] = com.veepoo.protocol.util.vp_g.vp_a(s);
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(boolean z, List list, List list2) {
        Set set = CollectionsKt.toSet(list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            if (!set.contains(Integer.valueOf(((GameClockBean) obj).getClockId()))) {
                arrayList.add(obj);
            }
        }
        byte[] bArr = new byte[list.size() + 13];
        bArr[0] = -65;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = 1;
        bArr[4] = -96;
        bArr[5] = 2;
        byte[] bArrVp_a = vp_a(arrayList, Boolean.valueOf(z));
        bArr[6] = bArrVp_a[0];
        bArr[7] = bArrVp_a[1];
        bArr[8] = -95;
        bArr[9] = 1;
        bArr[10] = z;
        bArr[11] = -94;
        bArr[12] = (byte) list.size();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int i2 = i;
            bArr[i2 + 13] = (byte) ((Number) list.get(i2)).intValue();
        }
        return bArr;
    }

    private final void vp_b(byte[] bArr) {
        if (bArr.length < 4) {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "读取游戏设置应答长度不足，忽略");
            return;
        }
        int i = bArr[2] & 255;
        int i2 = bArr[3] & 255;
        if (i2 == 0) {
            this.vp_c.clear();
            if (i == 0) {
                IGameClockOptListener iGameClockOptListener = this.vp_e;
                if (iGameClockOptListener != null) {
                    iGameClockOptListener.onGameClockEmpty();
                    return;
                }
                return;
            }
            IGameClockOptListener iGameClockOptListener2 = this.vp_e;
            if (iGameClockOptListener2 != null) {
                iGameClockOptListener2.onGameClockNotNeed2Update();
                return;
            }
            return;
        }
        if (i2 == 1) {
            this.vp_c.clear();
        }
        this.vp_c.add(bArr);
        if (i2 != i) {
            return;
        }
        byte[] bArrVp_b = vp_b();
        if (bArrVp_b == null || bArrVp_b.length < 10) {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "读取游戏设置内容不足，忽略");
            return;
        }
        com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "----->" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_b));
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_b[3], bArrVp_b[2]);
        boolean z = bArrVp_b[6] == 1;
        this.vp_d = z;
        List<GameClockBean> listVp_d = vp_d(bArrVp_b);
        IGameClockOptListener iGameClockOptListener3 = this.vp_e;
        if (iGameClockOptListener3 != null) {
            iGameClockOptListener3.onGameTimePlanningRead(iVp_b, z, listVp_d);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(GameClockBean gameClockBean) {
        byte[] bArr = new byte[28];
        bArr[0] = -80;
        bArr[1] = 26;
        bArr[2] = -64;
        bArr[3] = 1;
        bArr[4] = (byte) gameClockBean.getClockId();
        bArr[5] = -63;
        bArr[6] = 1;
        bArr[7] = (byte) gameClockBean.getGameType();
        bArr[8] = -62;
        bArr[9] = 3;
        if (gameClockBean.isRepeat()) {
            bArr[10] = 0;
            bArr[11] = 0;
            bArr[12] = 0;
        } else {
            bArr[10] = (byte) (gameClockBean.getYear() - 2000);
            bArr[11] = (byte) gameClockBean.getMonth();
            bArr[12] = (byte) gameClockBean.getDay();
        }
        bArr[13] = -61;
        bArr[14] = 1;
        bArr[15] = (byte) gameClockBean.getRepeatWeekMask();
        bArr[16] = -60;
        bArr[17] = 3;
        bArr[18] = (byte) gameClockBean.getStartHour();
        bArr[19] = (byte) gameClockBean.getStartMinute();
        bArr[20] = (byte) gameClockBean.getStartSecond();
        bArr[21] = -59;
        bArr[22] = 2;
        byte[] bArrVp_d = com.veepoo.protocol.util.vp_g.vp_d(gameClockBean.getDuration());
        bArr[23] = bArrVp_d[1];
        bArr[24] = bArrVp_d[0];
        bArr[25] = -58;
        bArr[26] = 1;
        bArr[27] = gameClockBean.isOpen();
        return bArr;
    }

    public final void vp_b(boolean z, List list, List list2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "setBeans");
        Intrinsics.checkNotNullParameter(list2, "allBeans");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        if (list.isEmpty()) {
            com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "设置游戏设置失败：setBeans 为空");
            IGameClockOptListener iGameClockOptListener = this.vp_e;
            if (iGameClockOptListener != null) {
                iGameClockOptListener.onSettingResult(false);
                return;
            }
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GameClockBean gameClockBean = (GameClockBean) it.next();
            String verifyError = gameClockBean.getVerifyError();
            if (verifyError != null) {
                com.veepoo.protocol.util.vp_w.vp_b("-游戏设置-", "设置游戏设置失败：" + gameClockBean.toSmartStr() + " -> " + verifyError);
                IGameClockOptListener iGameClockOptListener2 = this.vp_e;
                if (iGameClockOptListener2 != null) {
                    iGameClockOptListener2.onSettingResult(false);
                    return;
                }
                return;
            }
        }
        byte[] bArrVp_b = vp_b(z, list, list2);
        int length = (bArrVp_b.length + 15) / 16;
        com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送【设置】游戏设置，共 " + list.size() + " 条：" + CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, vp_av::vp_b, 31, (Object) null));
        int i = 1;
        if (1 > length) {
            return;
        }
        while (true) {
            int i2 = i;
            byte[] bArrVp_a = vp_a(i, length, bArrVp_b);
            com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送【设置】游戏设置命令第[" + i + "/" + length + "]包：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
            send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
            if (i2 == length) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(boolean z, List list) {
        byte[] bArrVp_a = vp_a(list, Boolean.valueOf(z));
        return new byte[]{-65, 2, 1, 1, -96, 2, bArrVp_a[0], bArrVp_a[1], -95, 1, z, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private static final CharSequence vp_b(GameClockBean gameClockBean) {
        Intrinsics.checkNotNullParameter(gameClockBean, "it");
        String smartStr = gameClockBean.toSmartStr();
        Intrinsics.checkNotNullExpressionValue(smartStr, "toSmartStr(...)");
        return smartStr;
    }

    private final byte[] vp_a(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[20];
        bArr2[0] = -65;
        bArr2[1] = 2;
        bArr2[2] = (byte) i2;
        bArr2[3] = (byte) i;
        int i3 = (i - 1) * 16;
        for (int i4 = 4; i4 < 20; i4++) {
            int i5 = (i3 + i4) - 4;
            bArr2[i4] = i5 < bArr.length ? bArr[i5] : (byte) 0;
        }
        return bArr2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
    }

    public final void vp_a(List list, Boolean bool, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "data");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        this.vp_c.clear();
        byte[] bArrVp_b = vp_b(list, bool);
        com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送读取游戏设置命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_b));
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(List list, boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(list, "data");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(list, z, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(z, list);
        com.veepoo.protocol.util.vp_w.vp_a("-游戏设置-", "发送设置游戏" + (z ? "【可用】" : "【不可用】") + "命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IGameClockOptListener iGameClockOptListener) {
        this.vp_e = iGameClockOptListener;
    }
}
