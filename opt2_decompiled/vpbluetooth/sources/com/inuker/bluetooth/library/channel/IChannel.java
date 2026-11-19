package com.inuker.bluetooth.library.channel;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/IChannel.class */
public interface IChannel {
    void write(byte[] bArr, ChannelCallback channelCallback);

    void onRead(byte[] bArr);

    void onRecv(byte[] bArr);

    void send(byte[] bArr, ChannelCallback channelCallback);
}
