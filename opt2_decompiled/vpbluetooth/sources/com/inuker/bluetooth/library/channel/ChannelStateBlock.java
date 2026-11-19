package com.inuker.bluetooth.library.channel;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/ChannelStateBlock.class */
public class ChannelStateBlock {
    public ChannelState state;
    public ChannelEvent event;
    public IChannelStateHandler handler;

    public ChannelStateBlock(ChannelState state, ChannelEvent event, IChannelStateHandler handler) {
        this.state = state;
        this.event = event;
        this.handler = handler;
    }
}
