package com.veepoo.protocol.err;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/err/OadErrorState.class */
public class OadErrorState {
    public static final int UNCONNECT_NETWORK = 11;
    public static final int UNCONNECT_SERVER = 12;
    public static final int SERVER_NOT_HAVE_NEW = 13;
    public static final int DEVICE_IS_NEW = 14;
    public static final int OAD_FILE_UNEXITS = 15;
    public static final int OAD_FILE_MD5_UNSAME = 16;
    public static final int NOT_NORIC_CPU = 17;

    public static String parse(int i) {
        switch (i) {
            case 11:
                return " 网络连接不上";
            case 12:
                return "服务器连接不上";
            case 13:
                return "服务器无最新版本";
            case 14:
                return "设备是最新版本，无需升级";
            case 15:
                return "升级文件不存在";
            case 16:
                return "升级文件MD5不一致";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }
}
