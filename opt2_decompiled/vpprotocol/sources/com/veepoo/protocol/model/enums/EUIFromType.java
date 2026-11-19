package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EUIFromType.class */
public enum EUIFromType {
    DEFAULT((byte) 0),
    CUSTOM((byte) 2),
    SERVER((byte) 1),
    IMAGE_PUSH((byte) 6),
    A_GPS((byte) -95),
    G15_IMG_QR_CODE_APP_DOWNLOAD((byte) -94),
    G15_IMG_PROFILE((byte) -93),
    G15_IMG_THEME_1((byte) -92),
    G15_IMG_THEME_2((byte) -91);

    public byte code;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EUIFromType$UICode.class */
    public interface UICode {
        public static final byte Def = 0;
        public static final byte Service = 1;
        public static final byte Custom = 2;
        public static final byte ImagePush = 6;
        public static final byte A_GPS = -95;
        public static final byte G15ImgAPPDownloadAppQRCode = -94;
        public static final byte G15ImgProfile = -93;
        public static final byte G15ImgTheme1 = -92;
        public static final byte G15ImgTheme2 = -91;
    }

    EUIFromType(byte b) {
        this.code = b;
    }

    EUIFromType(int i) {
        this.code = (byte) i;
    }
}
