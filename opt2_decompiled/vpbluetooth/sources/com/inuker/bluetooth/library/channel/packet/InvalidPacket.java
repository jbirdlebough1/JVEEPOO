package com.inuker.bluetooth.library.channel.packet;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/packet/InvalidPacket.class */
public class InvalidPacket extends Packet {
    public String toString() {
        return "InvalidPacket{}";
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public String getName() {
        return "invalid";
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public byte[] toBytes() {
        return new byte[0];
    }
}
