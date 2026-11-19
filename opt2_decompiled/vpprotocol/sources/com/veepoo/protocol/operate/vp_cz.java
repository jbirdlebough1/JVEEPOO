package com.veepoo.protocol.operate;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ITextAlarmDataListener;
import com.veepoo.protocol.model.datas.TextAlarmData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EMultiAlarmOprate;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.Alarm2Util;
import com.veepoo.protocol.util.TextAlarmSp;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cz.class */
public class vp_cz extends com.veepoo.protocol.vp_a {
    private int vp_c;
    ITextAlarmDataListener vp_e;
    private BluetoothClient vp_h;
    private String vp_i;
    private BleWriteResponse vp_j;
    private TextAlarm2Setting vp_k;
    private List vp_d = new ArrayList();
    private List vp_f = new ArrayList();
    private TextAlarm2Setting vp_g = null;
    private List vp_l = new ArrayList();
    private byte[] vp_m = new byte[2];
    private List vp_n = new ArrayList();
    private byte[] vp_o = new byte[2];
    private List vp_p = new ArrayList();
    private byte[] vp_q = new byte[2];
    private byte[] vp_r = new byte[2];
    private List vp_s = new ArrayList();
    private boolean vp_t = false;
    List vp_u = new ArrayList();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cz$vp_a.class */
    class vp_a implements Runnable {
        final /* synthetic */ List vp_a;

        vp_a(List list) {
            this.vp_a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            vp_cz.this.vp_d(this.vp_a);
        }
    }

    public void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        this.vp_h = bluetoothClient;
        this.vp_i = str;
        this.vp_j = bleWriteResponse;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TextAlarm2Setting textAlarm2Setting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, textAlarm2Setting);
        this.vp_k = textAlarm2Setting;
        this.vp_h = bluetoothClient;
        this.vp_i = str;
        this.vp_j = bleWriteResponse;
        vp_a(bluetoothClient, str, bleWriteResponse, textAlarm2Setting, (byte) 1);
    }

    public void vp_c(byte[] bArr) {
        switch (bArr[1]) {
            case 1:
                vp_d(bArr);
                break;
            case 2:
                vp_j(bArr);
                break;
            case 3:
                vp_h(bArr);
                break;
            case 4:
                vp_i(bArr);
                break;
            case 5:
                vp_e(bArr);
                break;
            case 6:
                vp_f(bArr);
                break;
            case 7:
                vp_g(bArr);
                break;
        }
    }

    private void vp_i(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        boolean z = b2 == 1;
        boolean z2 = b2 == b;
        if (z) {
            this.vp_s.clear();
            if (this.vp_r == null) {
                this.vp_r = new byte[2];
            }
            byte[] bArr2 = this.vp_r;
            bArr2[0] = bArr[7];
            bArr2[1] = bArr[8];
            if (bArr[6] != 1) {
                this.vp_t = true;
                ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
                if (iTextAlarmDataListener != null) {
                    iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.UNKONW, null));
                }
            }
        }
        boolean z3 = z2;
        this.vp_s.add(bArr);
        if (!z3 || this.vp_t) {
            return;
        }
        vp_m(vp_c(this.vp_s));
        if (this.vp_u.size() != 1) {
            ITextAlarmDataListener iTextAlarmDataListener2 = this.vp_e;
            if (iTextAlarmDataListener2 != null) {
                iTextAlarmDataListener2.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.ALARM_REPORT_DATE_ERROR, null));
                return;
            }
            return;
        }
        TextAlarm2Setting textAlarm2Setting = (TextAlarm2Setting) this.vp_u.get(0);
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        for (TextAlarm2Setting textAlarm2Setting2 : textAlarmSetting) {
            if (textAlarm2Setting2.getAlarmId() == textAlarm2Setting.getAlarmId()) {
                new StringBuilder().append("更新闹钟状态：Alarm(").append(textAlarm2Setting2.getMAFlag()).append(") state:").append(textAlarm2Setting2.isOpen).append(" to ").append(textAlarm2Setting.isOpen);
                textAlarm2Setting2.setOpen(textAlarm2Setting.isOpen);
                textAlarm2Setting2.setAlarmHour(textAlarm2Setting.alarmHour);
                textAlarm2Setting2.setAlarmMinute(textAlarm2Setting.alarmMinute);
                textAlarm2Setting2.setContent(textAlarm2Setting.getContent());
                textAlarm2Setting2.setRepeatStatus(textAlarm2Setting.repeatStatus);
                textAlarm2Setting2.setUnRepeatDate(textAlarm2Setting.unRepeatDate);
            }
        }
        TextAlarmSp.getInstance(this.vp_a).saveTextAlarmList(textAlarmSetting);
        ITextAlarmDataListener iTextAlarmDataListener3 = this.vp_e;
        if (iTextAlarmDataListener3 != null) {
            iTextAlarmDataListener3.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.ALARM_REPORT, this.vp_u));
        }
    }

    private void vp_h(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        if (b2 == 1) {
            vp_a();
        }
        this.vp_d.add(bArr);
        byte[] bArr2 = {0, 0};
        if (bArr[3] == 1) {
            if (vp_k(bArr)) {
                new StringBuilder().append("App收到读取闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(new byte[]{bArr[7], bArr[8]})));
                if (this.vp_q != null) {
                    new StringBuilder().append("readCrc = ").append(Arrays.toString(com.veepoo.protocol.util.vp_g.vp_f(this.vp_q)));
                }
                byte b3 = bArr[7];
                bArr2[0] = b3;
                byte b4 = bArr[8];
                bArr2[1] = b4;
                byte[] bArr3 = this.vp_q;
                if (bArr3 != null && bArr3[0] == b3 && bArr3[1] == b4) {
                    ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
                    if (iTextAlarmDataListener == null) {
                        return;
                    }
                    if (bArr[0] == 0 && bArr[1] == 0) {
                        iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_SUCCESS_NULL, null));
                    } else {
                        iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_SUCCESS_SAME_CRC, this.vp_p));
                    }
                }
            } else {
                ITextAlarmDataListener iTextAlarmDataListener2 = this.vp_e;
                if (iTextAlarmDataListener2 != null) {
                    iTextAlarmDataListener2.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_FAIL, null));
                }
            }
        }
        if (b == b2) {
            if (bArr2[0] == 0 && bArr2[1] == 0) {
                byte[] bArr4 = this.vp_q;
                bArr4[0] = 0;
                bArr4[1] = 0;
                this.vp_p.clear();
                TextAlarmSp.getInstance(this.vp_a).deleteAllAlarmMac(this.vp_i);
                ITextAlarmDataListener iTextAlarmDataListener3 = this.vp_e;
                if (iTextAlarmDataListener3 != null) {
                    iTextAlarmDataListener3.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_SUCCESS_NULL, null));
                }
            }
            byte[] bArrVp_c = vp_c(this.vp_d);
            vp_m(bArrVp_c);
            if (this.vp_u.size() > 0) {
                TextAlarmSp.getInstance(this.vp_a).saveTextAlarmList(this.vp_u);
                ITextAlarmDataListener iTextAlarmDataListener4 = this.vp_e;
                if (iTextAlarmDataListener4 != null) {
                    iTextAlarmDataListener4.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_SUCCESS_SAVE, this.vp_u));
                    return;
                }
                return;
            }
            ITextAlarmDataListener iTextAlarmDataListener5 = this.vp_e;
            if (iTextAlarmDataListener5 != null) {
                if (bArrVp_c == null || !(bArrVp_c[2] == 0 || bArrVp_c[3] == 0)) {
                    iTextAlarmDataListener5.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.READ_FAIL, null));
                }
            }
        }
    }

    private void vp_e(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte[] bArr2 = new byte[2];
        if (b2 == 1) {
            this.vp_f.clear();
            boolean z = bArr[6] == 1;
            bArr2[0] = bArr[7];
            bArr2[1] = bArr[8];
            new StringBuilder().append("设备添加了一条文字闹钟  crc = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr2)).append("  isCrcChanged = ").append(z);
        }
        this.vp_f.add(bArr);
        if (b == b2) {
            this.vp_g = null;
            byte[] bArrVp_c = vp_c(this.vp_f);
            if (bArrVp_c == null) {
                new StringBuilder().append("Error -disposeDeviceAddOneCmd-  ：拼接的文字闹钟错误（content is null）").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
                return;
            }
            new StringBuilder().append("拼接的文字闹钟内容：").append(com.veepoo.protocol.util.vp_g.vp_d(bArrVp_c));
            vp_l(bArrVp_c);
            TextAlarm2Setting textAlarm2Setting = this.vp_g;
            if (textAlarm2Setting == null) {
                new StringBuilder().append("设备添加了一条文字闹钟 出现错误 ERROR!!! data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
                return;
            }
            if (textAlarm2Setting.getContent() == null) {
                this.vp_g.setContent("");
            }
            TextAlarmSp.getInstance(this.vp_a).saveTextAlarmOne(this.vp_g);
            ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
            if (iTextAlarmDataListener != null) {
                iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.DEVICE_ADD_ONE_TEXT_ALARM, this.vp_u));
            }
            vp_d(this.vp_u);
        }
    }

    private void vp_f(byte[] bArr) {
        new StringBuilder().append("-disposeDeviceDelOneCmd- 设备端删除了单条闹钟 data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        TextAlarmSp.getInstance(this.vp_a).deleteAlarmId(this.vp_i, bArr[4]);
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        vp_d(textAlarmSetting);
        this.vp_u.clear();
        this.vp_u.addAll(textAlarmSetting);
        ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
        if (iTextAlarmDataListener != null) {
            iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.DEVICE_DELETE_ONE_TEXT_ALARM, textAlarmSetting));
        }
    }

    private void vp_g(byte[] bArr) {
        byte b = bArr[4];
        boolean z = bArr[5] == 1;
        boolean z2 = z;
        TextAlarmSp.getInstance(this.vp_a).changeTextAlarmSwitchState(this.vp_i, b, z);
        new StringBuilder().append("-disposeDeviceOptOneCmd- 设备端操作了单条闹钟的开关状态 data = ").append(com.veepoo.protocol.util.vp_g.vp_d(bArr)).append("\n 闹钟id = ").append((int) b).append(" , 开关状态 = ").append(z2 ? "开" : "关");
        this.vp_u.clear();
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        this.vp_u.addAll(textAlarmSetting);
        ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
        if (iTextAlarmDataListener != null) {
            iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.DEVICE_TEXT_ALARM_SWITCH_CHANGED, textAlarmSetting));
        }
        new Handler(Looper.getMainLooper()).postDelayed(new vp_a(textAlarmSetting), 500L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void vp_m(byte[] bArr) {
        this.vp_u.clear();
        if (bArr != 0) {
            int i = (bArr[1] ? 1 : 0) + 2;
            while (i < bArr.length && bArr[i] == -95) {
                int i2 = bArr[i + 1];
                byte[] bArr2 = new byte[i2];
                int i3 = i + 2;
                System.arraycopy(bArr, i3, bArr2, 0, i2);
                i = i3 + i2;
                TextAlarm2Setting textAlarm2SettingVp_b = vp_b(bArr2);
                new StringBuilder().append("从设备读文字闹钟 ： ").append(textAlarm2SettingVp_b.toString());
                this.vp_u.add(textAlarm2SettingVp_b);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void vp_l(byte[] bArr) {
        if (bArr == 0) {
            this.vp_g = null;
            return;
        }
        int i = (bArr[1] ? 1 : 0) + 2;
        while (i < bArr.length && bArr[i] == -95) {
            int i2 = bArr[i + 1];
            byte[] bArr2 = new byte[i2];
            int i3 = i + 2;
            System.arraycopy(bArr, i3, bArr2, 0, i2);
            i = i3 + i2;
            TextAlarm2Setting textAlarm2SettingVp_b = vp_b(bArr2);
            this.vp_g = textAlarm2SettingVp_b;
            this.vp_u.add(textAlarm2SettingVp_b);
            new StringBuilder().append("设备新增的文字闹钟 ： ").append(this.vp_g.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextAlarm2Setting vp_b(byte[] bArr) {
        if (bArr == 0 || bArr.length <= 0 || bArr[0] != -79) {
            return null;
        }
        boolean z = bArr[1];
        boolean z2 = bArr[2];
        boolean z3 = bArr[3] != 0;
        boolean z4 = bArr[4];
        boolean z5 = bArr[5];
        boolean z6 = bArr[6];
        int i = (bArr[7] ? 1 : 0) + 2000;
        byte b = bArr[8];
        byte b2 = bArr[9];
        String str = "";
        if (bArr[10] == -78) {
            int i2 = bArr[11];
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 12, bArr2, 0, i2);
            try {
                str = new String(bArr2, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bArr.printStackTrace();
            }
        }
        TextAlarm2Setting textAlarm2Setting = new TextAlarm2Setting();
        textAlarm2Setting.setBluetoothAddress(this.vp_i);
        textAlarm2Setting.setContent(str);
        textAlarm2Setting.setAlarmHour(z5 ? 1 : 0);
        textAlarm2Setting.setAlarmMinute(z6 ? 1 : 0);
        textAlarm2Setting.setAlarmId(z2 ? 1 : 0);
        textAlarm2Setting.setOpen(z3);
        textAlarm2Setting.setMAFlag(this.vp_i + "-" + (z2 ? 1 : 0));
        textAlarm2Setting.setRepeatStatus(vp_a(z4 ? 1 : 0));
        String str2 = "" + i;
        String str3 = "" + (b == true ? 1 : 0);
        String str4 = "" + (b2 == true ? 1 : 0);
        if (b < 10) {
            str3 = "0" + (b == true ? 1 : 0);
        }
        if (b2 < 10) {
            str4 = "0" + (b2 == true ? 1 : 0);
        }
        textAlarm2Setting.setUnRepeatDate(str2 + "-" + str3 + "-" + str4);
        return textAlarm2Setting;
    }

    private void vp_d(byte[] bArr) {
        byte[] bArr2;
        if (vp_k(bArr)) {
            byte b = bArr[6];
            new StringBuilder().append("App收到删除闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(new byte[]{bArr[7], bArr[8]})));
            if (b == 0) {
                ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
                if (iTextAlarmDataListener != null) {
                    iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.CLEAR_FAIL, TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i)));
                    return;
                }
                return;
            }
            if (b != 1 || (bArr2 = this.vp_m) == null || bArr[7] != bArr2[0] || bArr[8] != bArr2[1]) {
                ITextAlarmDataListener iTextAlarmDataListener2 = this.vp_e;
                if (iTextAlarmDataListener2 != null) {
                    iTextAlarmDataListener2.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.CLEAR_FAIL, TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i)));
                    return;
                }
                return;
            }
            if (this.vp_k != null) {
                TextAlarmSp.getInstance(this.vp_a).deleteAlarmId(this.vp_i, this.vp_k.getAlarmId());
                new StringBuilder().append("删除本地的闹钟数据：").append(this.vp_k.toString());
                this.vp_k = null;
            }
            TextAlarmSp.getInstance(this.vp_a).saveTextAlarmList(this.vp_l);
            this.vp_u.clear();
            this.vp_u.addAll(this.vp_l);
            ITextAlarmDataListener iTextAlarmDataListener3 = this.vp_e;
            if (iTextAlarmDataListener3 != null) {
                iTextAlarmDataListener3.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.CLEAR_SUCCESS, this.vp_l));
            }
        }
    }

    private void vp_j(byte[] bArr) {
        byte[] bArr2;
        if (vp_k(bArr)) {
            new StringBuilder().append("App收到设置闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(new byte[]{bArr[7], bArr[8]})));
            byte b = bArr[6];
            if (b == 0) {
                ITextAlarmDataListener iTextAlarmDataListener = this.vp_e;
                if (iTextAlarmDataListener != null) {
                    iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.SETTING_FAIL, TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i)));
                    return;
                }
                return;
            }
            if (b != 1 || (bArr2 = this.vp_o) == null || bArr[7] != bArr2[0] || bArr[8] != bArr2[1]) {
                ITextAlarmDataListener iTextAlarmDataListener2 = this.vp_e;
                if (iTextAlarmDataListener2 != null) {
                    iTextAlarmDataListener2.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.SETTING_FAIL, TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i)));
                    return;
                }
                return;
            }
            TextAlarmSp.getInstance(this.vp_a).saveTextAlarmList(this.vp_n);
            Iterator it = this.vp_n.iterator();
            while (it.hasNext()) {
                new StringBuilder().append("设置成功保存闹钟：").append(((TextAlarm2Setting) it.next()).toString());
            }
            this.vp_u.clear();
            this.vp_u.addAll(this.vp_n);
            ITextAlarmDataListener iTextAlarmDataListener3 = this.vp_e;
            if (iTextAlarmDataListener3 != null) {
                iTextAlarmDataListener3.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.SETTING_SUCCESS, this.vp_n));
            }
        }
    }

    private boolean vp_k(byte[] bArr) {
        return bArr[4] == -96 && bArr[5] == 3;
    }

    public void vp_a() {
        if (!this.vp_d.isEmpty()) {
            this.vp_d.clear();
        }
        this.vp_u.clear();
    }

    private List vp_c(TextAlarm2Setting textAlarm2Setting) {
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        int i = 0;
        for (int i2 = 0; i2 < textAlarmSetting.size(); i2++) {
            if (textAlarmSetting.get(i2).getAlarmId() == textAlarm2Setting.getAlarmId()) {
                i = i2;
            }
        }
        textAlarmSetting.set(i, textAlarm2Setting);
        return textAlarmSetting;
    }

    private List vp_b(TextAlarm2Setting textAlarm2Setting) {
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        TextAlarm2Setting textAlarm2Setting2 = null;
        for (TextAlarm2Setting textAlarm2Setting3 : textAlarmSetting) {
            if (textAlarm2Setting.getMAFlag().equals(textAlarm2Setting3.getMAFlag()) && textAlarm2Setting.getContent().equals(textAlarm2Setting3.getContent())) {
                textAlarm2Setting2 = textAlarm2Setting3;
            }
        }
        if (textAlarm2Setting2 != null) {
            new StringBuilder().append("移除闹钟 =").append(textAlarm2Setting2.toString());
            textAlarmSetting.remove(textAlarm2Setting2);
        }
        return textAlarmSetting;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [byte[], java.lang.Object, java.lang.Throwable] */
    private byte[] vp_d(TextAlarm2Setting textAlarm2Setting) {
        ?? r0;
        try {
            byte[] bArrVp_e = vp_e(textAlarm2Setting);
            r0 = new byte[bArrVp_e.length + 4];
            r0[0] = -96;
            r0[1] = 2;
            short alarmCrc16 = (short) Alarm2Util.getAlarmCrc16(bArrVp_e);
            r0[2] = VpBleByteUtil.loUint16(alarmCrc16);
            r0[3] = VpBleByteUtil.hiUint16(alarmCrc16);
            System.arraycopy(bArrVp_e, 0, r0, 4, bArrVp_e.length);
            return r0;
        } catch (Exception unused) {
            r0.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v16, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0 */
    private byte[] vp_e(TextAlarm2Setting textAlarm2Setting) {
        ?? content = textAlarm2Setting.getContent();
        ?? r19 = new byte[0];
        try {
            content = content.getBytes("UTF-8");
            r19 = content;
        } catch (UnsupportedEncodingException unused) {
            content.printStackTrace();
        }
        int length = r19.length;
        byte[] bArr = new byte[length + 14];
        bArr[0] = -95;
        bArr[1] = (byte) (length + 12);
        bArr[2] = -79;
        bArr[3] = 8;
        bArr[4] = (byte) textAlarm2Setting.getAlarmId();
        bArr[5] = textAlarm2Setting.isOpen ? (byte) 1 : (byte) 0;
        bArr[6] = VpBleByteUtil.loUint16((short) textAlarm2Setting.getRepeatIntStatus());
        int alarmHour = textAlarm2Setting.getAlarmHour();
        int alarmMinute = textAlarm2Setting.getAlarmMinute();
        bArr[7] = VpBleByteUtil.loUint16((short) alarmHour);
        bArr[8] = VpBleByteUtil.loUint16((short) alarmMinute);
        int i = 0;
        int i2 = 0;
        int day = 0;
        String unRepeatDate = textAlarm2Setting.getUnRepeatDate();
        if (textAlarm2Setting.getRepeatIntStatus() == 0 && !unRepeatDate.equals("0000-00-00")) {
            TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(unRepeatDate);
            int year = timeDataVp_a.getYear();
            int i3 = year;
            if (year > 2000) {
                i3 -= 2000;
            }
            int month = timeDataVp_a.getMonth();
            day = timeDataVp_a.getDay();
            int i4 = i3;
            i2 = month;
            i = i4;
        }
        bArr[9] = VpBleByteUtil.loUint16((short) i);
        bArr[10] = VpBleByteUtil.loUint16((short) i2);
        bArr[11] = VpBleByteUtil.loUint16((short) day);
        bArr[12] = -78;
        bArr[13] = (byte) length;
        System.arraycopy(r19, 0, bArr, 14, length);
        return bArr;
    }

    public void vp_d(List list) {
        byte[] bArr = new byte[20];
        bArr[0] = -71;
        bArr[1] = -1;
        byte[] bArrVp_a = vp_a(list);
        new StringBuilder().append("App下发读取闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArrVp_a)));
        this.vp_q = bArrVp_a;
        bArr[4] = -96;
        bArr[5] = 2;
        bArr[6] = bArrVp_a[0];
        bArr[7] = bArrVp_a[1];
        send(bArr, this.vp_h, this.vp_i, this.vp_j);
    }

    private byte[] vp_b(List list) {
        this.vp_p = list;
        byte[] bArr = new byte[20];
        bArr[0] = -71;
        bArr[1] = 3;
        byte[] bArrVp_a = vp_a(list);
        new StringBuilder().append("App下发读取闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArrVp_a)));
        this.vp_q = bArrVp_a;
        bArr[4] = -96;
        bArr[5] = 2;
        bArr[6] = bArrVp_a[0];
        bArr[7] = bArrVp_a[1];
        return bArr;
    }

    private byte[] vp_c(List list) {
        if (list.size() <= 0) {
            return null;
        }
        int size = list.size() * 4;
        int i = 0;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            int length = bArr.length;
            i += length;
            int i2 = length - 4;
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 4, bArr2, 0, i2);
            arrayList.add(bArr2);
        }
        byte[] bArr3 = new byte[i - size];
        int length2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int i4 = i3;
            byte[] bArr4 = (byte[]) arrayList.get(i3);
            if (i4 > 0) {
                length2 += ((byte[]) arrayList.get(i3 - 1)).length;
            }
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
        }
        return bArr3;
    }

    private List vp_a(TextAlarm2Setting textAlarm2Setting) {
        List<TextAlarm2Setting> textAlarmSetting = TextAlarmSp.getInstance(this.vp_a).getTextAlarmSetting(this.vp_i);
        textAlarmSetting.add(textAlarm2Setting);
        return textAlarmSetting;
    }

    public boolean vp_b() {
        return (this.vp_h == null || this.vp_i == null || this.vp_j == null) ? false : true;
    }

    private byte[] vp_a(int i, int i2, byte[] bArr, byte b) {
        int i3 = this.vp_c;
        byte[] bArr2 = new byte[i3];
        bArr2[0] = -71;
        if (b == -1) {
            b = 2;
        }
        bArr2[1] = b;
        bArr2[2] = (byte) i;
        bArr2[3] = (byte) (i2 + 1);
        int i4 = i3 - 4;
        int i5 = i2 * i4;
        System.arraycopy(bArr, i5, bArr2, 4, Math.min(i4, bArr.length - i5));
        return bArr2;
    }

    private byte[] vp_a(List list) {
        byte[] bArr = new byte[2];
        int length = 0;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TextAlarm2Setting textAlarm2Setting = (TextAlarm2Setting) list.get(i);
            byte[] bArrVp_e = vp_e(textAlarm2Setting);
            new StringBuilder().append("ID = ").append(textAlarm2Setting.getAlarmId()).append(" size = ").append(bArrVp_e.length).append(" => a1Content：").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArrVp_e)));
            length += bArrVp_e.length;
            arrayList.add(bArrVp_e);
        }
        byte[] bArr2 = new byte[length];
        int length2 = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            int i3 = i2;
            byte[] bArr3 = (byte[]) arrayList.get(i2);
            length2 += i3 == 0 ? 0 : ((byte[]) arrayList.get(i2 - 1)).length;
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
        }
        new StringBuilder().append(" data.size = ").append(length).append("计算CRC的内容：").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArr2)));
        short alarmCrc16 = (short) Alarm2Util.getAlarmCrc16(bArr2);
        bArr[0] = VpBleByteUtil.loUint16(alarmCrc16);
        bArr[1] = VpBleByteUtil.hiUint16(alarmCrc16);
        return bArr;
    }

    private String vp_a(int i) {
        if (i > 127) {
            return "0000000";
        }
        String binaryString = Integer.toBinaryString(i);
        return ("0000000" + binaryString).substring(binaryString.length());
    }

    public void vp_a(String str, Context context, ITextAlarmDataListener iTextAlarmDataListener, byte[] bArr) {
        this.vp_a = context;
        this.vp_e = iTextAlarmDataListener;
        this.vp_i = str;
        vp_c(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, list);
        this.vp_i = str;
        this.vp_h = bluetoothClient;
        this.vp_j = bleWriteResponse;
        send(vp_b(list), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TextAlarm2Setting textAlarm2Setting, byte b) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, textAlarm2Setting, b);
        this.vp_i = str;
        this.vp_h = bluetoothClient;
        this.vp_j = bleWriteResponse;
        new StringBuilder().append("----- MTU = ").append(VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue());
        this.vp_c = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue();
        boolean zIsZKDevice = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isZKDevice();
        if (this.vp_c == -1 || zIsZKDevice) {
            this.vp_c = 20;
        }
        if (this.vp_c >= 82) {
            new StringBuilder().append("文字闹钟-一包发送 packageLength = ").append(this.vp_c);
            send(vp_a(textAlarm2Setting, b), this.vp_h, str, bleWriteResponse);
            return;
        }
        new StringBuilder().append("文字闹钟-分包发送 packageLength = ").append(this.vp_c);
        byte[] bArrVp_a = vp_a(textAlarm2Setting, b);
        new StringBuilder().append("文字闹钟内容 data Length = ").append(bArrVp_a.length).append(" - - ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArrVp_a)));
        int length = bArrVp_a.length - 4;
        int i = this.vp_c - 4;
        int i2 = (length / i) + (length % i == 0 ? 0 : 1);
        vp_d(textAlarm2Setting);
        int length2 = bArrVp_a.length - 4;
        byte[] bArr = new byte[length2];
        if (length2 >= 0) {
            System.arraycopy(bArrVp_a, 4, bArr, 0, length2);
        }
        int i3 = 0;
        while (i3 < i2) {
            byte[] bArrVp_a2 = vp_a(i2, i3, bArr, b);
            int i4 = i3 + 1;
            i3 = i4;
            new StringBuilder().append("文字闹钟-发送  => ").append(i4).append("/").append(i2);
            send(bArrVp_a2, this.vp_h, str, bleWriteResponse);
        }
    }

    private byte[] vp_a(TextAlarm2Setting textAlarm2Setting, byte b) {
        byte[] bArrVp_e = vp_e(textAlarm2Setting);
        int length = bArrVp_e.length;
        byte[] bArr = new byte[length + 8];
        bArr[0] = -71;
        bArr[1] = b == -1 ? (byte) 2 : b;
        bArr[2] = 1;
        bArr[3] = 1;
        bArr[4] = -96;
        bArr[5] = 2;
        byte[] bArr2 = new byte[2];
        if (b == 2) {
            List listVp_a = vp_a(textAlarm2Setting);
            this.vp_n = listVp_a;
            byte[] bArrVp_a = vp_a(listVp_a);
            bArr2 = bArrVp_a;
            this.vp_o = bArrVp_a;
            Iterator it = this.vp_n.iterator();
            while (it.hasNext()) {
                new StringBuilder().append("发送的设置闹钟：").append(((TextAlarm2Setting) it.next()).toString());
            }
            new StringBuilder().append("App下发添加闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(this.vp_o)));
        } else if (b == -1) {
            List listVp_c = vp_c(textAlarm2Setting);
            this.vp_n = listVp_c;
            byte[] bArrVp_a2 = vp_a(listVp_c);
            bArr2 = bArrVp_a2;
            this.vp_o = bArrVp_a2;
            new StringBuilder().append("App下发修改闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(this.vp_o)));
        } else if (b == 1) {
            List listVp_b = vp_b(textAlarm2Setting);
            this.vp_l = listVp_b;
            byte[] bArrVp_a3 = vp_a(listVp_b);
            bArr2 = bArrVp_a3;
            this.vp_m = bArrVp_a3;
            new StringBuilder().append("App下发删除闹钟的Crc = ").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(this.vp_m)));
        }
        bArr[6] = bArr2[0];
        bArr[7] = bArr2[1];
        System.arraycopy(bArrVp_e, 0, bArr, 8, length);
        return bArr;
    }
}
