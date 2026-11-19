package com.veepoo.protocol.model.enums;

import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESocailMsg.class */
public enum ESocailMsg {
    G15MSG((byte) -2),
    PHONE((byte) 0),
    SMS((byte) 1),
    WECHAT((byte) 2),
    QQ((byte) 3),
    SINA((byte) 4),
    FACEBOOK((byte) 5),
    TWITTER((byte) 6),
    FLICKR((byte) 7),
    LINKIN((byte) 8),
    WHATS((byte) 9),
    LINE((byte) 10),
    INSTAGRAM((byte) 11),
    SNAPCHAT((byte) 12),
    SKYPE((byte) 13),
    GMAIL((byte) 14),
    DINGDING((byte) 15),
    WXWORK((byte) 16),
    OTHER((byte) 17),
    TIKTOK((byte) 18),
    TELEGRAM((byte) 19),
    CONNECTED2_ME((byte) 20),
    KAKAO_TALK((byte) 21),
    SHIELD_POLICE((byte) 22),
    MESSENGER((byte) 23),
    ZALO((byte) 24),
    VIBER((byte) 25),
    APP_CUSTOM_MSG((byte) 26);

    byte value;

    ESocailMsg(byte b) {
        this.value = b;
    }

    public static ESocailMsg getSocialMsg(byte b) {
        switch (b) {
            case DfuBaseService.PROGRESS_STARTING /* -2 */:
                return G15MSG;
            case DfuBaseService.PROGRESS_CONNECTING /* -1 */:
            case 1:
            default:
                return OTHER;
            case 0:
                return PHONE;
            case 2:
                return WECHAT;
            case 3:
                return QQ;
            case 4:
                return SINA;
            case 5:
                return FACEBOOK;
            case 6:
                return TWITTER;
            case 7:
                return FLICKR;
            case 8:
                return LINKIN;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                return WHATS;
            case 10:
                return LINE;
            case 11:
                return INSTAGRAM;
            case 12:
                return SNAPCHAT;
            case 13:
                return SKYPE;
            case 14:
                return GMAIL;
            case 15:
                return DINGDING;
            case 16:
                return WXWORK;
            case 17:
                return OTHER;
            case 18:
                return TIKTOK;
            case 19:
                return TELEGRAM;
            case DfuBaseService.LOG_LEVEL_ERROR /* 20 */:
                return CONNECTED2_ME;
            case 21:
                return KAKAO_TALK;
            case Spo2hOriginUtil.YESTERDAY_HOUR_START /* 22 */:
                return SHIELD_POLICE;
            case 23:
                return MESSENGER;
            case 24:
                return ZALO;
            case 25:
                return VIBER;
            case 26:
                return APP_CUSTOM_MSG;
        }
    }

    public byte getValue() {
        return this.value;
    }
}
