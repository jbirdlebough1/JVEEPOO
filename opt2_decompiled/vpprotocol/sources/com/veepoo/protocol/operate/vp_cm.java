package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISocialMsgDataListener;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.ESocailMsg;
import com.veepoo.protocol.model.settings.ContentPhoneSetting;
import com.veepoo.protocol.model.settings.ContentSetting;
import com.veepoo.protocol.model.settings.ContentSmsSetting;
import com.veepoo.protocol.model.settings.ContentSocailSetting;
import com.veepoo.protocol.model.settings.SoldierContentSetting;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cm.class */
public class vp_cm extends com.veepoo.protocol.vp_a {
    ISocialMsgDataListener vp_c;
    byte vp_d = 1;
    FunctionSocailMsgData vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cm$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;
        static final /* synthetic */ int[] vp_b;

        static {
            int[] iArr = new int[ESocailMsg.values().length];
            vp_b = iArr;
            try {
                iArr[ESocailMsg.SMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_b[ESocailMsg.PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[EFunctionStatus.values().length];
            vp_a = iArr2;
            try {
                iArr2[EFunctionStatus.SUPPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[EFunctionStatus.SUPPORT_OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[EFunctionStatus.SUPPORT_CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private FunctionSocailMsgData vp_c(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte b3 = bArr[4];
        byte b4 = bArr[5];
        byte b5 = bArr[6];
        byte b6 = bArr[7];
        byte b7 = bArr[8];
        byte b8 = bArr[9];
        this.vp_e.setTikTok(vp_a(b));
        this.vp_e.setTelegram(vp_a(b2));
        this.vp_e.setConnected2_me(vp_a(b3));
        this.vp_e.setKakaoTalk(vp_a(b4));
        this.vp_e.setShieldPolice(vp_a(b5));
        this.vp_e.setMessenger(vp_a(b6));
        this.vp_e.setZalo(vp_a(b7));
        this.vp_e.setViber(vp_a(b8));
        return this.vp_e;
    }

    private byte[] vp_b(FunctionSocailMsgData functionSocailMsgData, byte b) {
        return new byte[]{-83, b, vp_a(functionSocailMsgData.getTikTok()), vp_a(functionSocailMsgData.getTelegram()), vp_a(functionSocailMsgData.getConnected2_me()), vp_a(functionSocailMsgData.getKakaoTalk()), vp_a(functionSocailMsgData.getShieldPolice()), vp_a(functionSocailMsgData.getMessenger()), vp_a(functionSocailMsgData.getZalo()), vp_a(functionSocailMsgData.getViber()), 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};
    }

    private byte vp_a(EFunctionStatus eFunctionStatus) {
        int i = vp_a.vp_a[eFunctionStatus.ordinal()];
        if (i == 1 || i == 2) {
            return (byte) 1;
        }
        return i != 3 ? (byte) 0 : (byte) 2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_an(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_an(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ai, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        if (bArr.length < 20) {
            this.vp_c.onSocialMsgSupportDataChange(new FunctionSocailMsgData());
        }
        if (this.vp_e == null) {
            this.vp_e = new FunctionSocailMsgData();
        }
        int i = bArr[19] >> 4;
        if (i == 0) {
            vp_b(bArr);
            this.vp_c.onSocialMsgSupportDataChange(this.vp_e);
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceFunctionAD1(this.vp_e);
        } else if (i == 1) {
            vp_c(bArr);
            this.vp_c.onSocialMsgSupportDataChange2(this.vp_e);
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceFunctionAD2(this.vp_e);
            VPOperateManager.getInstance().setFunctionSocailMsgData(this.vp_e);
        }
    }

    private void vp_b(byte[] bArr) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte b3 = bArr[4];
        byte b4 = bArr[5];
        byte b5 = bArr[6];
        byte b6 = bArr[7];
        byte b7 = bArr[8];
        byte b8 = bArr[9];
        byte b9 = bArr[10];
        byte b10 = bArr[11];
        byte b11 = bArr[12];
        byte b12 = bArr[13];
        byte b13 = bArr[14];
        byte b14 = bArr[15];
        byte b15 = bArr[16];
        byte b16 = bArr[17];
        byte b17 = bArr[18];
        byte b18 = bArr[19];
        if (b == 1) {
            this.vp_e.setPhone(EFunctionStatus.SUPPORT_OPEN);
        } else {
            this.vp_e.setPhone(EFunctionStatus.SUPPORT_CLOSE);
        }
        if (b2 == 1) {
            this.vp_e.setMsg(EFunctionStatus.SUPPORT_OPEN);
        } else if (b2 == 2) {
            this.vp_e.setMsg(EFunctionStatus.SUPPORT_CLOSE);
        } else {
            this.vp_e.setMsg(EFunctionStatus.UNSUPPORT);
        }
        this.vp_e.setWechat(vp_a(b3));
        this.vp_e.setQq(vp_a(b4));
        this.vp_e.setSina(vp_a(b5));
        this.vp_e.setFacebook(vp_a(b6));
        this.vp_e.setTwitter(vp_a(b7));
        this.vp_e.setFlickr(vp_a(b8));
        this.vp_e.setLinkin(vp_a(b9));
        this.vp_e.setWhats(vp_a(b10));
        this.vp_e.setLine(vp_a(b11));
        this.vp_e.setInstagram(vp_a(b12));
        this.vp_e.setSnapchat(vp_a(b13));
        this.vp_e.setSkype(vp_a(b14));
        this.vp_e.setGmail(vp_a(b15));
        this.vp_e.setDingding(vp_a(b16));
        this.vp_e.setWxWork(vp_a(b17));
        this.vp_e.setOther(vp_a(b18));
    }

    private EFunctionStatus vp_a(byte b) {
        if (b == 0) {
            return EFunctionStatus.UNSUPPORT;
        }
        if (b == 1) {
            return EFunctionStatus.SUPPORT_OPEN;
        }
        return b == 2 ? EFunctionStatus.SUPPORT_CLOSE : EFunctionStatus.UNSUPPORT;
    }

    private List vp_a(ContentSetting contentSetting) {
        int allLength = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getAllLength();
        int nickNameLength = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getNickNameLength();
        contentSetting.setAllLength(allLength);
        contentSetting.setContactLength(nickNameLength);
        int i = vp_a.vp_b[contentSetting.geteSocailMsg().ordinal()];
        if (i == 1) {
            return nickNameLength == 0 ? com.veepoo.protocol.util.vp_ag.vp_b((ContentSmsSetting) contentSetting) : com.veepoo.protocol.util.vp_ag.vp_a((ContentSmsSetting) contentSetting);
        }
        if (i != 2) {
            return contentSetting.geteSocailMsg() == ESocailMsg.G15MSG ? com.veepoo.protocol.util.vp_ag.vp_b((ContentSocailSetting) contentSetting) : com.veepoo.protocol.util.vp_ag.vp_a((ContentSocailSetting) contentSetting);
        }
        return nickNameLength == 0 ? com.veepoo.protocol.util.vp_ag.vp_b((ContentPhoneSetting) contentSetting) : com.veepoo.protocol.util.vp_ag.vp_a((ContentPhoneSetting) contentSetting);
    }

    private byte[] vp_a(FunctionSocailMsgData functionSocailMsgData, byte b) {
        return new byte[]{-83, b, vp_a(functionSocailMsgData.getPhone()), vp_a(functionSocailMsgData.getMsg()), vp_a(functionSocailMsgData.getWechat()), vp_a(functionSocailMsgData.getQq()), vp_a(functionSocailMsgData.getSina()), vp_a(functionSocailMsgData.getFacebook()), vp_a(functionSocailMsgData.getTwitter()), vp_a(functionSocailMsgData.getFlickr()), vp_a(functionSocailMsgData.getLinkin()), vp_a(functionSocailMsgData.getWhats()), vp_a(functionSocailMsgData.getLine()), vp_a(functionSocailMsgData.getInstagram()), vp_a(functionSocailMsgData.getSnapchat()), vp_a(functionSocailMsgData.getSkype()), vp_a(functionSocailMsgData.getGmail()), vp_a(functionSocailMsgData.getDingding()), vp_a(functionSocailMsgData.getWxWork()), vp_a(functionSocailMsgData.getOther())};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ISocialMsgDataListener) iListener;
        handler(bArr);
    }

    private boolean vp_a(FunctionSocailMsgData functionSocailMsgData) {
        EFunctionStatus tikTok = functionSocailMsgData.getTikTok();
        EFunctionStatus eFunctionStatus = EFunctionStatus.UNSUPPORT;
        return (tikTok != eFunctionStatus) || (functionSocailMsgData.getTelegram() != eFunctionStatus) || (functionSocailMsgData.getConnected2_me() != eFunctionStatus) || (functionSocailMsgData.getKakaoTalk() != eFunctionStatus) || (functionSocailMsgData.getShieldPolice() != eFunctionStatus) || (functionSocailMsgData.getMessenger() != eFunctionStatus) || (functionSocailMsgData.getZalo() != eFunctionStatus) || (functionSocailMsgData.getViber() != eFunctionStatus);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.operate.vp_cm, com.veepoo.protocol.vp_a] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List] */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ContentSetting contentSetting) {
        ?? Vp_a = this;
        super.vp_a(bluetoothClient, str, bleWriteResponse, contentSetting);
        ?? r12 = 0;
        try {
            Vp_a = Vp_a.vp_a(contentSetting);
            r12 = Vp_a;
        } catch (Exception unused) {
            Vp_a.printStackTrace();
        }
        if (r12 == 0 || r12.isEmpty()) {
            return;
        }
        int allLength = contentSetting.getAllLength();
        for (int i = 0; i < r12.size(); i++) {
            if (i < allLength) {
                super.send((byte[]) r12.get(i), bluetoothClient, str, bleWriteResponse);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.model.settings.SoldierContentSetting] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List] */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, SoldierContentSetting soldierContentSetting) {
        ?? Vp_a = soldierContentSetting;
        super.vp_a(bluetoothClient, str, bleWriteResponse, soldierContentSetting);
        ?? r11 = 0;
        try {
            Vp_a = com.veepoo.protocol.util.vp_ag.vp_a((SoldierContentSetting) Vp_a);
            r11 = Vp_a;
        } catch (Exception unused) {
            Vp_a.printStackTrace();
        }
        if (r11 == 0 || r11.isEmpty()) {
            return;
        }
        for (int i = 0; i < r11.size(); i++) {
            super.send((byte[]) r11.get(i), bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, FunctionSocailMsgData functionSocailMsgData) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, functionSocailMsgData);
        super.send(vp_a(functionSocailMsgData, this.vp_d), bluetoothClient, str, bleWriteResponse);
        if (vp_a(functionSocailMsgData)) {
            super.send(vp_b(functionSocailMsgData, this.vp_d), bluetoothClient, str, bleWriteResponse);
        }
    }
}
