package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.bluetrum.abpartool.ParTool;
import com.bluetrum.abpartool.utils.ImageUtils;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import com.veepoo.protocol.util.vp_ah;
import com.veepoo.protocol.util.vp_c;
import com.veepoo.protocol.util.vp_g;
import com.veepoo.protocol.util.vp_k;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/WatchUIType.class */
public abstract class WatchUIType {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/WatchUIType$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EWatchUIType.values().length];
            vp_a = iArr;
            try {
                iArr[EWatchUIType.ROUND_240_240.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_240.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_280.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_280_QFN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[EWatchUIType.ROUND_240_240_QFN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_295.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_295_QFN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[EWatchUIType.ROUND_360_360_QFN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_280_BLACK_QFN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[EWatchUIType.RECT_80_160.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                vp_a[EWatchUIType.RECT_135_240_QFN.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                vp_a[EWatchUIType.RECT_172_320_QFN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                vp_a[EWatchUIType.RECT_167_240_QFN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_284_QFN.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                vp_a[EWatchUIType.RECT_240_286_QFN.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                vp_a[EWatchUIType.RECT_320_380_QFN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                vp_a[EWatchUIType.RECT_200_320_QFN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_360_360.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_280.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_284.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_286.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_368_448.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_466_466.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_412_412.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_380.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_296.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_386.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_410_502.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_292.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_240_240.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_200_320.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_172_320.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_390_390.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_390_450.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_416_416.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_296_APPLE.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_320_320.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_280_APPLE.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_284_APPLE.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_286_APPLE.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_368_448_APPLE.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_380_APPLE.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_386_APPLE.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_410_502_APPLE.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_292_APPLE.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_240_240_APPLE.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_360_360_APPLE.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_412_412_APPLE.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_466_466_APPLE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_466_466_EARTH.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_410_502_EARTH.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_410_502_AURORA.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_480_480.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_240_240.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_228_460.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_360.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_360_360_QHYF.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_410_502_TYPE1.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_360_360.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                vp_a[EWatchUIType.ROUND_JL_480_480_EARTH.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_360_360_LANDSCAPE.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_386_EARTH.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                vp_a[EWatchUIType.RECT_JL_320_386_CANTON_TOWER.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_466.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_360.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_296.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_280.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_284.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_200_320.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_296_YIHUI.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_368_448.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_284_XMD.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_172_320.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_320_386.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_466_P569X.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_360_P569X.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_296_P569X.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_280_P569X.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_240_284_P569X.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_200_320_P569X.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_368_448_P569X.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_172_320_P569X.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_320_386_P569X.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_410_502.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_410_502_P569X.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_390_450.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_390_450_P569X.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_360_360.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_360_360_P569X.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_360_360_EARTH_P569X.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_320_38X_EARTH_P569X.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_466_466_EARTH_P569X.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                vp_a[EWatchUIType.ZK_RECT_410_502_EARTH_P569X.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                vp_a[EWatchUIType.ZK_ROUND_240.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
        }
    }

    public static WatchUIType getInstance(EWatchUIType eWatchUIType) {
        WatchUIType watchUIType = round240240WatchUIType;
        Round240240WatchUIType round240240WatchUIType = new Round240240WatchUIType();
        switch (vp_a.vp_a[eWatchUIType.ordinal()]) {
            case 1:
                return new Round240240WatchUIType();
            case 2:
                return new Rect240240WatchUIType();
            case 3:
                return new Rect240280WatchUIType();
            case 4:
                return new Rect240280QFNWatchUIType();
            case 5:
                return new Round240240QFNWatchUIType();
            case 6:
                return new Rect240295WatchUIType();
            case 7:
                return new Rect240295QFNWatchUIType();
            case 8:
                return new Round360360QFNWatchUIType();
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                return new Rect240280QFNBlackWatchUIType();
            case 10:
                return new Rect80160QFNWatchUIType();
            case 11:
                return new Rect135240WatchUIType();
            case 12:
                return new Rect172320WatchUIType();
            case 13:
                return new Rect167240WatchUIType();
            case 14:
                return new Rect240284QFNWatchUIType();
            case 15:
                return new Rect240286QFNWatchUIType();
            case 16:
                return new Rect320380QFNWatchUIType();
            case 17:
                return new Rect200320WatchUIType();
            case 18:
                watchUIType = jLRound360WatchUIType;
                JLRound360WatchUIType jLRound360WatchUIType = new JLRound360WatchUIType();
                break;
            case 19:
                watchUIType = jLRect240280WatchUIType;
                JLRect240280WatchUIType jLRect240280WatchUIType = new JLRect240280WatchUIType();
                break;
            case DfuBaseService.LOG_LEVEL_ERROR /* 20 */:
                watchUIType = jLRect240284WatchUIType;
                JLRect240284WatchUIType jLRect240284WatchUIType = new JLRect240284WatchUIType();
                break;
            case 21:
                watchUIType = jLRect240286WatchUIType;
                JLRect240286WatchUIType jLRect240286WatchUIType = new JLRect240286WatchUIType();
                break;
            case Spo2hOriginUtil.YESTERDAY_HOUR_START /* 22 */:
                watchUIType = jLRect368448WatchUIType;
                JLRect368448WatchUIType jLRect368448WatchUIType = new JLRect368448WatchUIType();
                break;
            case 23:
                watchUIType = jLRound466WatchUIType;
                JLRound466WatchUIType jLRound466WatchUIType = new JLRound466WatchUIType();
                break;
            case 24:
                watchUIType = jLRound412WatchUIType;
                JLRound412WatchUIType jLRound412WatchUIType = new JLRound412WatchUIType();
                break;
            case 25:
                watchUIType = jLRect320380WatchUIType;
                JLRect320380WatchUIType jLRect320380WatchUIType = new JLRect320380WatchUIType();
                break;
            case 26:
                watchUIType = jLRect240296WatchUIType;
                JLRect240296WatchUIType jLRect240296WatchUIType = new JLRect240296WatchUIType();
                break;
            case 27:
                watchUIType = jLRect320386WatchUIType;
                JLRect320386WatchUIType jLRect320386WatchUIType = new JLRect320386WatchUIType();
                break;
            case 28:
                watchUIType = jLRect410502WatchUIType;
                JLRect410502WatchUIType jLRect410502WatchUIType = new JLRect410502WatchUIType();
                break;
            case 29:
                watchUIType = jLRect240292WatchUIType;
                JLRect240292WatchUIType jLRect240292WatchUIType = new JLRect240292WatchUIType();
                break;
            case 30:
                watchUIType = jLRound240WatchUIType;
                JLRound240WatchUIType jLRound240WatchUIType = new JLRound240WatchUIType();
                break;
            case 31:
                watchUIType = jLRect200320WatchUIType;
                JLRect200320WatchUIType jLRect200320WatchUIType = new JLRect200320WatchUIType();
                break;
            case 32:
                watchUIType = jLRect172320WatchUIType;
                JLRect172320WatchUIType jLRect172320WatchUIType = new JLRect172320WatchUIType();
                break;
            case 33:
                watchUIType = jLRound390WatchUIType;
                JLRound390WatchUIType jLRound390WatchUIType = new JLRound390WatchUIType();
                break;
            case 34:
                watchUIType = jLRect390450WatchUIType;
                JLRect390450WatchUIType jLRect390450WatchUIType = new JLRect390450WatchUIType();
                break;
            case 35:
                watchUIType = jLRound416WatchUIType;
                JLRound416WatchUIType jLRound416WatchUIType = new JLRound416WatchUIType();
                break;
            case 36:
                watchUIType = jLRect240296AppleWatchUIType;
                JLRect240296AppleWatchUIType jLRect240296AppleWatchUIType = new JLRect240296AppleWatchUIType();
                break;
            case 37:
                watchUIType = jLRound320WatchUIType;
                JLRound320WatchUIType jLRound320WatchUIType = new JLRound320WatchUIType();
                break;
            case 38:
                watchUIType = jLRect240280AppleWatchUIType;
                JLRect240280AppleWatchUIType jLRect240280AppleWatchUIType = new JLRect240280AppleWatchUIType();
                break;
            case 39:
                watchUIType = jLRect240284AppleWatchUIType;
                JLRect240284AppleWatchUIType jLRect240284AppleWatchUIType = new JLRect240284AppleWatchUIType();
                break;
            case 40:
                watchUIType = jLRect240286AppleWatchUIType;
                JLRect240286AppleWatchUIType jLRect240286AppleWatchUIType = new JLRect240286AppleWatchUIType();
                break;
            case 41:
                watchUIType = jLRect368448AppleWatchUIType;
                JLRect368448AppleWatchUIType jLRect368448AppleWatchUIType = new JLRect368448AppleWatchUIType();
                break;
            case 42:
                watchUIType = jLRec320380AppleWatchUIType;
                JLRec320380AppleWatchUIType jLRec320380AppleWatchUIType = new JLRec320380AppleWatchUIType();
                break;
            case 43:
                watchUIType = jLRec320386AppleWatchUIType;
                JLRec320386AppleWatchUIType jLRec320386AppleWatchUIType = new JLRec320386AppleWatchUIType();
                break;
            case 44:
                watchUIType = jLRec410502AppleWatchUIType;
                JLRec410502AppleWatchUIType jLRec410502AppleWatchUIType = new JLRec410502AppleWatchUIType();
                break;
            case 45:
                watchUIType = jLRect240292AppleWatchUIType;
                JLRect240292AppleWatchUIType jLRect240292AppleWatchUIType = new JLRect240292AppleWatchUIType();
                break;
            case 46:
                watchUIType = jLRound240AppleWatchUIType;
                JLRound240AppleWatchUIType jLRound240AppleWatchUIType = new JLRound240AppleWatchUIType();
                break;
            case 47:
                watchUIType = jLRound360AppleWatchUIType;
                JLRound360AppleWatchUIType jLRound360AppleWatchUIType = new JLRound360AppleWatchUIType();
                break;
            case 48:
                watchUIType = jLRound412AppleWatchUIType;
                JLRound412AppleWatchUIType jLRound412AppleWatchUIType = new JLRound412AppleWatchUIType();
                break;
            case 49:
                watchUIType = jLRound466AppleWatchUIType;
                JLRound466AppleWatchUIType jLRound466AppleWatchUIType = new JLRound466AppleWatchUIType();
                break;
            case Spo2hOriginUtil.MAX_VALUE_BEATH /* 50 */:
                watchUIType = jLRound466EarthWatchUIType;
                JLRound466EarthWatchUIType jLRound466EarthWatchUIType = new JLRound466EarthWatchUIType();
                break;
            case 51:
                watchUIType = jLRect410502EarthWatchUIType;
                JLRect410502EarthWatchUIType jLRect410502EarthWatchUIType = new JLRect410502EarthWatchUIType();
                break;
            case 52:
                watchUIType = jLRect410502AuroraWatchUIType;
                JLRect410502AuroraWatchUIType jLRect410502AuroraWatchUIType = new JLRect410502AuroraWatchUIType();
                break;
            case 53:
                watchUIType = jLRound480WatchUIType;
                JLRound480WatchUIType jLRound480WatchUIType = new JLRound480WatchUIType();
                break;
            case 54:
                watchUIType = jLRect240240WatchUIType;
                JLRect240240WatchUIType jLRect240240WatchUIType = new JLRect240240WatchUIType();
                break;
            case 55:
                watchUIType = jLRect228460WatchUIType;
                JLRect228460WatchUIType jLRect228460WatchUIType = new JLRect228460WatchUIType();
                break;
            case 56:
                watchUIType = jLRect320360WatchUIType;
                JLRect320360WatchUIType jLRect320360WatchUIType = new JLRect320360WatchUIType();
                break;
            case 57:
                watchUIType = jLRound360QhyfWatchUIType;
                JLRound360QhyfWatchUIType jLRound360QhyfWatchUIType = new JLRound360QhyfWatchUIType();
                break;
            case 58:
                watchUIType = jLRect410502Type1WatchUIType;
                JLRect410502Type1WatchUIType jLRect410502Type1WatchUIType = new JLRect410502Type1WatchUIType();
                break;
            case 59:
                watchUIType = jLRect360360WatchUIType;
                JLRect360360WatchUIType jLRect360360WatchUIType = new JLRect360360WatchUIType();
                break;
            case Spo2hOriginUtil.MAX_VALUE_SPO2H_LOW /* 60 */:
                watchUIType = jLRound480EarthWatchUIType;
                JLRound480EarthWatchUIType jLRound480EarthWatchUIType = new JLRound480EarthWatchUIType();
                break;
            case 61:
                watchUIType = jLRect360360LandscapeWatchUIType;
                JLRect360360LandscapeWatchUIType jLRect360360LandscapeWatchUIType = new JLRect360360LandscapeWatchUIType();
                break;
            case 62:
                watchUIType = jLRect320386EarthWatchUIType;
                JLRect320386EarthWatchUIType jLRect320386EarthWatchUIType = new JLRect320386EarthWatchUIType();
                break;
            case 63:
                watchUIType = jLRect320386CantonTowerUIType;
                JLRect320386CantonTowerUIType jLRect320386CantonTowerUIType = new JLRect320386CantonTowerUIType();
                break;
            case 64:
                watchUIType = zKRound466WatchUIType;
                ZKRound466WatchUIType zKRound466WatchUIType = new ZKRound466WatchUIType();
                break;
            case 65:
                watchUIType = zKRound360WatchUIType;
                ZKRound360WatchUIType zKRound360WatchUIType = new ZKRound360WatchUIType();
                break;
            case 66:
                watchUIType = zKRect240296WatchUIType;
                ZKRect240296WatchUIType zKRect240296WatchUIType = new ZKRect240296WatchUIType();
                break;
            case 67:
                watchUIType = zKRect240280WatchUIType;
                ZKRect240280WatchUIType zKRect240280WatchUIType = new ZKRect240280WatchUIType();
                break;
            case 68:
                watchUIType = zKRect240284WatchUIType;
                ZKRect240284WatchUIType zKRect240284WatchUIType = new ZKRect240284WatchUIType();
                break;
            case 69:
                watchUIType = zKRect200320WatchUIType;
                ZKRect200320WatchUIType zKRect200320WatchUIType = new ZKRect200320WatchUIType();
                break;
            case 70:
                watchUIType = zKRect240296YiHuiWatchUIType;
                ZKRect240296YiHuiWatchUIType zKRect240296YiHuiWatchUIType = new ZKRect240296YiHuiWatchUIType();
                break;
            case 71:
                watchUIType = zKRect368448WatchUIType;
                ZKRect368448WatchUIType zKRect368448WatchUIType = new ZKRect368448WatchUIType();
                break;
            case 72:
                watchUIType = zKRect240284XmdWatchUIType;
                ZKRect240284XmdWatchUIType zKRect240284XmdWatchUIType = new ZKRect240284XmdWatchUIType();
                break;
            case 73:
                watchUIType = zKRect172320WatchUIType;
                ZKRect172320WatchUIType zKRect172320WatchUIType = new ZKRect172320WatchUIType();
                break;
            case 74:
                watchUIType = zKRect320386WatchUIType;
                ZKRect320386WatchUIType zKRect320386WatchUIType = new ZKRect320386WatchUIType();
                break;
            case Spo2hOriginUtil.MIN_VALUE_SP02H /* 75 */:
                watchUIType = zKRound466P569XWatchUIType;
                ZKRound466P569XWatchUIType zKRound466P569XWatchUIType = new ZKRound466P569XWatchUIType();
                break;
            case 76:
                watchUIType = zKRound360P569XWatchUIType;
                ZKRound360P569XWatchUIType zKRound360P569XWatchUIType = new ZKRound360P569XWatchUIType();
                break;
            case 77:
                watchUIType = zKRect240296P569XWatchUIType;
                ZKRect240296P569XWatchUIType zKRect240296P569XWatchUIType = new ZKRect240296P569XWatchUIType();
                break;
            case 78:
                watchUIType = zKRect240280P569XWatchUIType;
                ZKRect240280P569XWatchUIType zKRect240280P569XWatchUIType = new ZKRect240280P569XWatchUIType();
                break;
            case 79:
                watchUIType = zKRect240284P569XWatchUIType;
                ZKRect240284P569XWatchUIType zKRect240284P569XWatchUIType = new ZKRect240284P569XWatchUIType();
                break;
            case 80:
                watchUIType = zKRect200320P569XWatchUIType;
                ZKRect200320P569XWatchUIType zKRect200320P569XWatchUIType = new ZKRect200320P569XWatchUIType();
                break;
            case 81:
                watchUIType = zKRect368448P569XWatchUIType;
                ZKRect368448P569XWatchUIType zKRect368448P569XWatchUIType = new ZKRect368448P569XWatchUIType();
                break;
            case 82:
                watchUIType = zKRect172320P569XWatchUIType;
                ZKRect172320P569XWatchUIType zKRect172320P569XWatchUIType = new ZKRect172320P569XWatchUIType();
                break;
            case 83:
                watchUIType = zKRect320386P569XWatchUIType;
                ZKRect320386P569XWatchUIType zKRect320386P569XWatchUIType = new ZKRect320386P569XWatchUIType();
                break;
            case 84:
                watchUIType = zKRect410502WatchUIType;
                ZKRect410502WatchUIType zKRect410502WatchUIType = new ZKRect410502WatchUIType();
                break;
            case 85:
                watchUIType = zKRect410502P569XWatchUIType;
                ZKRect410502P569XWatchUIType zKRect410502P569XWatchUIType = new ZKRect410502P569XWatchUIType();
                break;
            case 86:
                watchUIType = zKRect390450WatchUIType;
                ZKRect390450WatchUIType zKRect390450WatchUIType = new ZKRect390450WatchUIType();
                break;
            case 87:
                watchUIType = zKRect390450P569XWatchUIType;
                ZKRect390450P569XWatchUIType zKRect390450P569XWatchUIType = new ZKRect390450P569XWatchUIType();
                break;
            case 88:
                watchUIType = zKRect360360WatchUIType;
                ZKRect360360WatchUIType zKRect360360WatchUIType = new ZKRect360360WatchUIType();
                break;
            case 89:
                watchUIType = zKRect360360P569XWatchUIType;
                ZKRect360360P569XWatchUIType zKRect360360P569XWatchUIType = new ZKRect360360P569XWatchUIType();
                break;
            case 90:
                watchUIType = zKRound360EarthP569XWatchUIType;
                ZKRound360EarthP569XWatchUIType zKRound360EarthP569XWatchUIType = new ZKRound360EarthP569XWatchUIType();
                break;
            case 91:
                watchUIType = zKRect32038XEarthP569XWatchUIType;
                ZKRect32038XEarthP569XWatchUIType zKRect32038XEarthP569XWatchUIType = new ZKRect32038XEarthP569XWatchUIType();
                break;
            case 92:
                watchUIType = zKRound466EarthP569XWatchUIType;
                ZKRound466EarthP569XWatchUIType zKRound466EarthP569XWatchUIType = new ZKRound466EarthP569XWatchUIType();
                break;
            case 93:
                watchUIType = zKRect410502EarthP569XWatchUIType;
                ZKRect410502EarthP569XWatchUIType zKRect410502EarthP569XWatchUIType = new ZKRect410502EarthP569XWatchUIType();
                break;
            case 94:
                watchUIType = zKRound240WatchUIType;
                ZKRound240WatchUIType zKRound240WatchUIType = new ZKRound240WatchUIType();
                break;
        }
        return watchUIType;
    }

    public boolean isSupportPosition(EWatchUIElementPosition eWatchUIElementPosition) {
        List<EWatchUIElementPosition> supportPosition = getSupportPosition();
        boolean z = false;
        Iterator<EWatchUIElementPosition> it = supportPosition.iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == eWatchUIElementPosition.getValue()) {
                z = true;
                break;
            }
        }
        return z;
    }

    public abstract List<EWatchUIElementPosition> getSupportPosition();

    public abstract EWatchUIType getUIType();

    public abstract int getDefaultImg();

    public abstract int getElementImg(EWatchUIElementType eWatchUIElementType, EWatchUIElementPosition eWatchUIElementPosition);

    public abstract Bitmap getBigBitmap(Context context, Bitmap bitmap);

    public abstract int getBigBitmapWidth();

    public abstract int getBigBitmapHeight();

    public abstract Bitmap getSmallBitmap(Bitmap bitmap);

    public abstract Bitmap getSmallBackgroundBitmap(Context context);

    public abstract int getSmallBitmapWidth();

    public abstract int getSmallBitmapHeight();

    public abstract int getSmallBroad();

    public abstract int getSmallBitmapContentWidth();

    public abstract int getSmallBitmapContentHeight();

    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return getSendInputStream(context, bitmap, false);
    }

    public InputStream getZKInputStreamByte(Context context, Bitmap bitmap, Bitmap bitmap2, WatchUIType watchUIType) throws Throwable {
        if (watchUIType.getBigBitmapRoundedCorner() != 0) {
            bitmap = vp_k.vp_a(bitmap, watchUIType.getBigBitmapRoundedCorner());
        }
        byte[] rawImageData = ImageUtils.getRawImageData(bitmap);
        Bitmap bitmap3 = bitmap;
        boolean zIsZK569XDevice = VpSpGetUtil.getVpSpVariInstance(context).isZK569XDevice();
        byte[] bArrRawToPar = ParTool.rawToPar(rawImageData, bitmap3.getWidth(), bitmap3.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(context, rawImageData, "bigPar.bin");
        byte[] bArrRawToPar2 = ParTool.rawToPar(ImageUtils.getRawImageData(bitmap2), bitmap2.getWidth(), bitmap2.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(context, bArrRawToPar2, "smallPar.bin");
        int length = bArrRawToPar.length + 4;
        int length2 = bArrRawToPar2.length + 4;
        byte[] bArrVp_b = vp_g.vp_b(length);
        byte[] bArrVp_b2 = vp_g.vp_b(length2);
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArrVp_b, 0, bArr, 0, 4);
        System.arraycopy(bArrRawToPar, 0, bArr, 4, bArrRawToPar.length);
        System.arraycopy(bArrVp_b2, 0, bArr2, 0, 4);
        System.arraycopy(bArrRawToPar2, 0, bArr2, 4, bArrRawToPar2.length);
        return new ByteArrayInputStream(vp_c.vp_a(bArr, bArr2));
    }

    public Boolean isRoundScreen() {
        return Boolean.FALSE;
    }

    public int getBigBitmapRoundedCorner() {
        return 0;
    }

    public int getSmallBitmapRadius() {
        return 0;
    }

    public InputStream getSendInputStream(Context context, Bitmap bitmap, Bitmap bitmap2, boolean z) {
        if (VpSpGetUtil.getVpSpVariInstance(context).isZKDevice()) {
            return getZKInputStreamByte(context, bitmap, bitmap2, this);
        }
        int smallBitmapHeight = getSmallBitmapHeight();
        return vp_c.vp_a(getBigBitmap(context, bitmap), getSmallBackgroundBitmap(context), getSmallBitmapWidth(), smallBitmapHeight, getSmallBitmapContentWidth(), getSmallBitmapContentHeight(), getSmallBroad(), z);
    }

    public InputStream getZKInputStreamByte(Context context, Bitmap bitmap, WatchUIType watchUIType) throws Throwable {
        Bitmap bitmapVp_a = vp_k.vp_a(context, watchUIType, bitmap);
        if (watchUIType.getBigBitmapRoundedCorner() != 0) {
            bitmap = vp_k.vp_a(bitmap, watchUIType.getBigBitmapRoundedCorner());
        }
        byte[] rawImageData = ImageUtils.getRawImageData(bitmap);
        Bitmap bitmap2 = bitmap;
        boolean zIsZK569XDevice = VpSpGetUtil.getVpSpVariInstance(context).isZK569XDevice();
        byte[] bArrRawToPar = ParTool.rawToPar(rawImageData, bitmap2.getWidth(), bitmap2.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(context, rawImageData, "bigPar.bin");
        byte[] bArrRawToPar2 = ParTool.rawToPar(ImageUtils.getRawImageData(bitmapVp_a), bitmapVp_a.getWidth(), bitmapVp_a.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(context, bArrRawToPar2, "smallPar.bin");
        int length = bArrRawToPar.length + 4;
        int length2 = bArrRawToPar2.length + 4;
        byte[] bArrVp_b = vp_g.vp_b(length);
        byte[] bArrVp_b2 = vp_g.vp_b(length2);
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArrVp_b, 0, bArr, 0, 4);
        System.arraycopy(bArrRawToPar, 0, bArr, 4, bArrRawToPar.length);
        System.arraycopy(bArrVp_b2, 0, bArr2, 0, 4);
        System.arraycopy(bArrRawToPar2, 0, bArr2, 4, bArrRawToPar2.length);
        return new ByteArrayInputStream(vp_c.vp_a(bArr, bArr2));
    }

    public InputStream getSendInputStream(Context context, Bitmap bitmap, boolean z) {
        if (VpSpGetUtil.getVpSpVariInstance(context).isZKDevice()) {
            return getZKInputStreamByte(context, bitmap, this);
        }
        int smallBitmapHeight = getSmallBitmapHeight();
        return vp_c.vp_a(getBigBitmap(context, bitmap), getSmallBackgroundBitmap(context), getSmallBitmapWidth(), smallBitmapHeight, getSmallBitmapContentWidth(), getSmallBitmapContentHeight(), getSmallBroad(), z);
    }
}
