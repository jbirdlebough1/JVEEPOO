package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ILanguageDataListener;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.datas.LanguageData;
import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EOprateStauts;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bg.class */
public class vp_bg extends com.veepoo.protocol.vp_a {
    ILanguageDataListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bg$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[ELanguage.values().length];
            vp_a = iArr;
            try {
                iArr[ELanguage.CHINA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[ELanguage.CHINA_TRADITIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[ELanguage.ENGLISH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[ELanguage.JAPAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[ELanguage.KOREA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[ELanguage.DEUTSCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[ELanguage.RUSSIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[ELanguage.SPANISH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[ELanguage.ITALIA.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[ELanguage.FRENCH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                vp_a[ELanguage.VIETNAM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                vp_a[ELanguage.PORTUGUESA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                vp_a[ELanguage.THAI.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                vp_a[ELanguage.POLISH.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                vp_a[ELanguage.SWEDISH.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                vp_a[ELanguage.TURKISH.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                vp_a[ELanguage.DUTCH.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                vp_a[ELanguage.CZECH.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                vp_a[ELanguage.ARABIC.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                vp_a[ELanguage.HUNGARY.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                vp_a[ELanguage.GREEK.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                vp_a[ELanguage.ROMANIAN.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                vp_a[ELanguage.SLOVAK.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                vp_a[ELanguage.INDONESIAN.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                vp_a[ELanguage.BRAZIL_PORTUGAL.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                vp_a[ELanguage.CROATIAN.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                vp_a[ELanguage.LITHUANIAN.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                vp_a[ELanguage.UKRAINE.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                vp_a[ELanguage.HINDI.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                vp_a[ELanguage.HEBREW.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                vp_a[ELanguage.DANISH.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                vp_a[ELanguage.PERSIAN.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                vp_a[ELanguage.FINNISH.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                vp_a[ELanguage.MALAY.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
        }
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -15;
        bArr[1] = -128;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_c(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ax, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_g(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_g(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ah, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_g(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_au(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_au(bluetoothClient, str, bleWriteResponse);
        super.send(vp_b(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        this.vp_c.onLanguageDataChange(vp_b(bArr));
    }

    private byte[] vp_b(ELanguage eLanguage) {
        byte[] bArr = new byte[20];
        bArr[0] = -12;
        byte bVp_a = vp_a(eLanguage);
        bArr[1] = bVp_a;
        bArr[2] = bVp_a;
        if (eLanguage == ELanguage.CHINA_TRADITIONAL) {
            bArr[2] = 12;
        }
        bArr[4] = vp_c(eLanguage);
        return bArr;
    }

    private byte vp_c(ELanguage eLanguage) {
        return (eLanguage == ELanguage.CHINA_TRADITIONAL || eLanguage == ELanguage.CHINA) ? (byte) 0 : (byte) 1;
    }

    private ELanguage vp_a(byte b) {
        ELanguage eLanguage;
        ELanguage eLanguage2 = ELanguage.ENGLISH;
        switch (b) {
            case 1:
                eLanguage = ELanguage.CHINA;
                break;
            case 2:
            default:
                return eLanguage2;
            case 3:
                eLanguage = ELanguage.JAPAN;
                break;
            case 4:
                eLanguage = ELanguage.KOREA;
                break;
            case 5:
                eLanguage = ELanguage.DEUTSCH;
                break;
            case 6:
                eLanguage = ELanguage.RUSSIA;
                break;
            case 7:
                eLanguage = ELanguage.SPANISH;
                break;
            case 8:
                eLanguage = ELanguage.ITALIA;
                break;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                eLanguage = ELanguage.FRENCH;
                break;
            case 10:
                eLanguage = ELanguage.VIETNAM;
                break;
            case 11:
                eLanguage = ELanguage.PORTUGUESA;
                break;
            case 12:
                eLanguage = ELanguage.CHINA_TRADITIONAL;
                break;
            case 13:
                eLanguage = ELanguage.THAI;
                break;
            case 14:
                eLanguage = ELanguage.POLISH;
                break;
            case 15:
                eLanguage = ELanguage.SWEDISH;
                break;
            case 16:
                eLanguage = ELanguage.TURKISH;
                break;
            case 17:
                eLanguage = ELanguage.DUTCH;
                break;
            case 18:
                eLanguage = ELanguage.CZECH;
                break;
            case 19:
                eLanguage = ELanguage.ARABIC;
                break;
            case DfuBaseService.LOG_LEVEL_ERROR /* 20 */:
                eLanguage = ELanguage.HUNGARY;
                break;
            case 21:
                eLanguage = ELanguage.GREEK;
                break;
            case Spo2hOriginUtil.YESTERDAY_HOUR_START /* 22 */:
                eLanguage = ELanguage.ROMANIAN;
                break;
            case 23:
                eLanguage = ELanguage.SLOVAK;
                break;
            case 24:
                eLanguage = ELanguage.INDONESIAN;
                break;
            case 25:
                eLanguage = ELanguage.BRAZIL_PORTUGAL;
                break;
            case 26:
                eLanguage = ELanguage.CROATIAN;
                break;
            case 27:
                eLanguage = ELanguage.LITHUANIAN;
                break;
            case 28:
                eLanguage = ELanguage.UKRAINE;
                break;
            case 29:
                eLanguage = ELanguage.HINDI;
                break;
            case 30:
                eLanguage = ELanguage.HEBREW;
                break;
            case 31:
                eLanguage = ELanguage.DANISH;
                break;
            case 32:
                eLanguage = ELanguage.PERSIAN;
                break;
            case 33:
                eLanguage = ELanguage.FINNISH;
                break;
            case 34:
                eLanguage = ELanguage.MALAY;
                break;
        }
        eLanguage2 = eLanguage;
        return eLanguage2;
    }

    private byte[] vp_b() {
        byte[] bArr = new byte[20];
        bArr[0] = -1;
        bArr[1] = 85;
        bArr[2] = 1;
        bArr[3] = 115;
        bArr[4] = 64;
        return bArr;
    }

    private byte vp_a(ELanguage eLanguage) {
        byte b;
        byte b2 = -26;
        switch (vp_a.vp_a[eLanguage.ordinal()]) {
            case 1:
            case 2:
                b = 1;
                break;
            case 3:
                b = 2;
                break;
            case 4:
                b = 3;
                break;
            case 5:
                b = 4;
                break;
            case 6:
                b = 5;
                break;
            case 7:
                b = 6;
                break;
            case 8:
                b = 7;
                break;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                b = 8;
                break;
            case 10:
                b = 9;
                break;
            case 11:
                b = 10;
                break;
            case 12:
                b = 11;
                break;
            case 13:
                b = 13;
                break;
            case 14:
                b = 14;
                break;
            case 15:
                b = 15;
                break;
            case 16:
                b = 16;
                break;
            case 17:
                b = 17;
                break;
            case 18:
                b = 18;
                break;
            case 19:
                b = 19;
                break;
            case DfuBaseService.LOG_LEVEL_ERROR /* 20 */:
                b = 20;
                break;
            case 21:
                b = 21;
                break;
            case Spo2hOriginUtil.YESTERDAY_HOUR_START /* 22 */:
                b = 22;
                break;
            case 23:
                b = 23;
                break;
            case 24:
                b = 24;
                break;
            case 25:
                b = 25;
                break;
            case 26:
                b = 26;
                break;
            case 27:
                b = 27;
                break;
            case 28:
                b = 28;
                break;
            case 29:
                b = 29;
                break;
            case 30:
                b = 30;
                break;
            case 31:
                b = 31;
                break;
            case 32:
                b = 32;
                break;
            case 33:
                b = 33;
                break;
            case 34:
                b = 34;
                break;
            default:
                return b2;
        }
        b2 = b;
        return b2;
    }

    private LanguageData vp_b(byte[] bArr) {
        LanguageData languageData = new LanguageData();
        if (bArr.length < 3) {
            languageData.setStauts(EOprateStauts.UNKNOW);
            return languageData;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[4];
        if (b == 0) {
            languageData.setStauts(EOprateStauts.OPRATE_FAIL);
        } else if (b == 1) {
            languageData.setStauts(EOprateStauts.OPRATE_SUCCESS);
        } else {
            languageData.setStauts(EOprateStauts.UNKNOW);
        }
        if (b2 == 0) {
            languageData.setLanguage(ELanguage.CHINA);
        } else if (b2 == 1) {
            languageData.setLanguage(ELanguage.ENGLISH);
        } else {
            languageData.setLanguage(ELanguage.UNKONW);
        }
        if (bArr.length != 20 || b3 == 0) {
            return languageData;
        }
        languageData.setLanguage(vp_a(b3));
        return languageData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ILanguageDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ELanguage eLanguage) {
        super.send(vp_b(eLanguage), bluetoothClient, str, bleWriteResponse);
    }
}
