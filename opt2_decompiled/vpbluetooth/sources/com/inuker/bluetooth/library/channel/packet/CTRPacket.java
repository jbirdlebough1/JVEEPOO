package com.inuker.bluetooth.library.channel.packet;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/packet/CTRPacket.class */
public class CTRPacket extends Packet {
    private int frameCount;

    public CTRPacket(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public String getName() {
        return Packet.CTR;
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(BUFFER);
        buffer.putShort((short) 0);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.putShort((short) this.frameCount);
        return buffer.array();
    }

    public String toString() {
        return "FlowPacket{frameCount=" + this.frameCount + '}';
    }
}
