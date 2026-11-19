package com.inuker.bluetooth.library.bluetrum.manager;

import com.inuker.bluetooth.library.bluetrum.log.Logger;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/manager/OtaCommandGenerator.class */
final class OtaCommandGenerator {
    private static final String TAG = OtaCommandGenerator.class.getSimpleName();
    private static final byte[] CMD_OTA_IDENTIFICATION = {-52, -86, 85, -18, 18, 25, -28};
    public static final byte CMD_OTA_INFO = -96;
    public static final byte CMD_SEND_DATA = 32;
    public static final byte CMD_GET_INFO = -111;
    public static final byte CMD_NOTIFY_STATUS = -112;
    public static final byte CMD_GET_INFO_TLV = -110;
    public static final byte STATE_OK = 0;
    public static final byte STATE_TWS_DISCONNECTED = -128;
    public static final byte STATE_DONE = -1;
    public static final byte STATE_PAUSE = -3;
    public static final byte STATE_CONTINUE = -2;
    public static final byte CMD_GET_INFO_TYPE_VERSION = 1;
    public static final byte CMD_GET_INFO_TYPE_UPDATE = 2;
    public static final byte CMD_GET_INFO_TYPE_CAPABILITIES = 3;
    public static final int INFO_CAPABILITIES_TWS = 1;
    public static final byte CMD_GET_INFO_TYPE_STATUS = 4;
    public static final int INFO_STATUS_TWS_CONNECTED = 1;
    public static final byte CMD_GET_INFO_TYPE_CHANNEL = 6;
    public static final byte INFO_CHANNEL_RIGHT = 0;
    public static final byte INFO_CHANNEL_LEFT = 1;
    private OtaDataProvider dataProvider;
    private byte seqNum = 0;

    public void reset() {
        Logger.d(TAG, "Reset CommandGenerator");
        this.seqNum = (byte) 0;
    }

    public OtaCommandGenerator() {
    }

    public OtaCommandGenerator(OtaDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setDataProvider(OtaDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    private byte[] generateCmdGetInfoData(byte cmd, String value) {
        byte[] valueData = value.getBytes();
        ByteBuffer bb = ByteBuffer.allocate(2 + valueData.length);
        bb.put(cmd);
        bb.put((byte) valueData.length);
        bb.put(valueData);
        return bb.array();
    }

    private byte[] generateCmdGetInfoData(byte cmd, byte[] value) {
        ByteBuffer bb = ByteBuffer.allocate(2 + value.length);
        bb.put(cmd);
        bb.put((byte) value.length);
        bb.put(value);
        return bb.array();
    }

    private byte[] generateCmdGetInfoData(byte cmd, int value) {
        ByteBuffer bb = ByteBuffer.allocate(6).order(ByteOrder.LITTLE_ENDIAN);
        bb.put(cmd);
        bb.put((byte) 4);
        bb.putInt(value);
        return bb.array();
    }

    private byte[] generateCmdGetInfoData(byte cmd, short value) {
        ByteBuffer bb = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        bb.put(cmd);
        bb.put((byte) 2);
        bb.putShort(value);
        return bb.array();
    }

    private byte[] generateCmdGetInfoData(byte cmd, byte value) {
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put(cmd);
        bb.put((byte) 1);
        bb.put(value);
        return bb.array();
    }

    private byte[] generateCmdGetInfoData(byte cmd) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.put(cmd);
        bb.put((byte) 0);
        return bb.array();
    }

    public byte[] cmdDataGetInfoVersion() {
        return generateCmdGetInfoData((byte) 1);
    }

    public byte[] cmdDataGetInfoUpdate(int version, byte[] hashData) {
        ByteBuffer bb = ByteBuffer.allocate(6).order(ByteOrder.LITTLE_ENDIAN);
        bb.putShort((short) version);
        if (hashData != null) {
            bb.put(hashData, 0, 4);
        } else {
            bb.putInt(-1);
        }
        return generateCmdGetInfoData((byte) 2, bb.array());
    }

    public byte[] cmdDataGetInfoTWS() {
        return generateCmdGetInfoData((byte) 3);
    }

    public byte[] cmdDataGetInfoStatus() {
        return generateCmdGetInfoData((byte) 4);
    }

    public byte[] cmdDataGetInfoChannel() {
        return generateCmdGetInfoData((byte) 6);
    }

    public byte[] cmdGetAllInfo() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(CMD_GET_INFO_TLV);
        byte b = this.seqNum;
        this.seqNum = (byte) (b + 1);
        os.write(b);
        try {
            os.write(cmdDataGetInfoVersion());
            os.write(cmdDataGetInfoTWS());
            os.write(cmdDataGetInfoStatus());
            os.write(cmdDataGetInfoChannel());
        } catch (Exception e) {
            Logger.e(TAG, "Failed to generate Command", e);
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public byte[] cmdOtaIdentification() {
        return CMD_OTA_IDENTIFICATION;
    }

    public byte[] cmdGetInfoVersion() {
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put((byte) -111);
        byte b = this.seqNum;
        this.seqNum = (byte) (b + 1);
        bb.put(b);
        bb.put((byte) 1);
        return bb.array();
    }

    public byte[] cmdGetInfoUpdate(int version, byte[] hashData) {
        ByteBuffer bb = ByteBuffer.allocate(9).order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte) -111);
        byte b = this.seqNum;
        this.seqNum = (byte) (b + 1);
        bb.put(b);
        bb.put((byte) 2);
        bb.putShort((short) version);
        if (hashData != null) {
            bb.put(hashData, 0, 4);
        } else {
            bb.putInt(-1);
        }
        return bb.array();
    }

    public byte[] cmdStartSend() {
        ByteBuffer bb = ByteBuffer.allocate(517).order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte) -96);
        byte b = this.seqNum;
        this.seqNum = (byte) (b + 1);
        bb.put(b);
        bb.putInt(this.dataProvider.getStartAddress());
        bb.putInt(this.dataProvider.getTotalLengthToBeSent());
        bb.put(this.dataProvider.getStartData(bb.position()));
        byte[] cmd = new byte[bb.position()];
        bb.rewind();
        bb.get(cmd);
        return cmd;
    }

    public byte[] cmdSendData() {
        ByteBuffer bb = ByteBuffer.allocate(517).order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte) 32);
        byte b = this.seqNum;
        this.seqNum = (byte) (b + 1);
        bb.put(b);
        bb.put(this.dataProvider.getDataToBeSent(bb.position()));
        byte[] cmd = new byte[bb.position()];
        bb.rewind();
        bb.get(cmd);
        return cmd;
    }
}
