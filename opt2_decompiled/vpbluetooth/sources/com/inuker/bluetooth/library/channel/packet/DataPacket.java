package com.inuker.bluetooth.library.channel.packet;

import com.inuker.bluetooth.library.utils.ByteUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/packet/DataPacket.class */
public class DataPacket extends Packet {
    private int seq;
    private Packet.Bytes bytes;
    private byte[] crc;

    public DataPacket(int seq, Packet.Bytes bytes) {
        this.seq = seq;
        this.bytes = bytes;
    }

    public DataPacket(int seq, byte[] value, int start, int end) {
        this(seq, new Packet.Bytes(value, start, end));
    }

    public int getSeq() {
        return this.seq;
    }

    public int getDataLength() {
        return this.bytes.getSize();
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public String getName() {
        return Packet.DATA;
    }

    public void setLastFrame() {
        this.bytes.end -= 2;
        this.crc = ByteUtils.get(this.bytes.value, this.bytes.end, 2);
    }

    public byte[] getCrc() {
        return this.crc;
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public byte[] toBytes() {
        ByteBuffer buffer;
        int packetSize = getDataLength() + 2;
        if (packetSize == 20) {
            Arrays.fill(BUFFER, (byte) 0);
            buffer = ByteBuffer.wrap(BUFFER);
        } else {
            buffer = ByteBuffer.allocate(packetSize);
        }
        buffer.putShort((short) this.seq);
        fillByteBuffer(buffer);
        return buffer.array();
    }

    public void fillByteBuffer(ByteBuffer buffer) {
        buffer.put(this.bytes.value, this.bytes.start, getDataLength());
    }

    public String toString() {
        return "DataPacket{seq=" + this.seq + ", size=" + this.bytes.getSize() + '}';
    }
}
