package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICustomDataOptListener.class */
public interface ICustomDataOptListener extends IListener {
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_CONNECTED = 1;
    public static final int ERROR_CMD_LIST_EMPTY = 2;
    public static final int ERROR_CMD_EMPTY = 3;
    public static final int ERROR_CMD_HEAD_INVALID = 4;
    public static final int ERROR_CMD_TOO_LONG = 5;
    public static final int ERROR_WRITE_FAILED = 6;

    void onPassthroughData(byte[] bArr);

    void onAdcData(byte[] bArr);

    void onError(int i, String str);

    void onSendComplete();
}
