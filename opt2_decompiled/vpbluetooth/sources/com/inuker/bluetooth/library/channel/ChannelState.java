package com.inuker.bluetooth.library.channel;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/ChannelState.class */
public enum ChannelState {
    IDLE,
    READY,
    WAIT_START_ACK,
    WRITING,
    SYNC,
    SYNC_ACK,
    SYNC_WAIT_PACKET,
    READING
}
