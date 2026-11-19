package com.inuker.bluetooth.library.channel.packet;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/packet/ACKPacket.class */
public class ACKPacket extends Packet {
    public static final int SUCCESS = 0;
    public static final int READY = 1;
    public static final int BUSY = 2;
    public static final int TIMEOUT = 3;
    public static final int CANCEL = 4;
    public static final int SYNC = 5;
    private int status;
    private int seq;

    public ACKPacket(int status) {
        this(status, 0);
    }

    public ACKPacket(int status, int seq) {
        this.status = status;
        this.seq = seq;
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public String getName() {
        return Packet.ACK;
    }

    public int getStatus() {
        return this.status;
    }

    public int getSeq() {
        return this.seq;
    }

    @Override // com.inuker.bluetooth.library.channel.packet.Packet
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.wrap(BUFFER);
        buffer.putShort((short) 0);
        buffer.put((byte) 1);
        buffer.put((byte) 0);
        buffer.putShort((short) this.status);
        buffer.putShort((short) this.seq);
        return buffer.array();
    }

    public String toString() {
        return "ACKPacket{status=" + getStatusDesc(this.status) + ", seq=" + this.seq + '}';
    }

    private String getStatusDesc(int status) {
        for (Field field : getClass().getDeclaredFields()) {
            if ((field.getModifiers() & 24) > 0) {
                try {
                    if (field.get(null) == Integer.valueOf(status)) {
                        return field.getName();
                    }
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return status + "";
    }
}
