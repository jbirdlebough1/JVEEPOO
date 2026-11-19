package com.inuker.bluetooth.library.bluetrum.manager;

import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/manager/OtaDataProvider.class */
final class OtaDataProvider {
    private final byte[] otaData;
    private final boolean isCompressedData;
    private int blockOffset;
    private int blockSize = -1;
    private int packetSize = 20;
    private int fileOffset = 0;

    public OtaDataProvider(byte[] otaData) {
        this.otaData = otaData;
        this.isCompressedData = checkIfCompressedData(otaData);
    }

    public void reset() {
        this.fileOffset = 0;
        this.packetSize = 20;
        this.blockSize = -1;
    }

    public boolean isCompressedData() {
        return this.isCompressedData;
    }

    public int getStartAddress() {
        return this.fileOffset;
    }

    public void setStartAddress(int address) {
        this.fileOffset = address;
    }

    public void setBlockSize(int size) {
        this.blockSize = size;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public int getProgress() {
        return (this.fileOffset * 100) / this.otaData.length;
    }

    public boolean isBlockSendFinish() {
        if (this.blockSize == -1) {
            return isAllDataSent();
        }
        return this.fileOffset - this.blockOffset == this.blockSize || isAllDataSent();
    }

    public boolean isAllDataSent() {
        return this.fileOffset == this.otaData.length;
    }

    public int getTotalLengthToBeSent() {
        int sendLen;
        if (this.blockSize == -1) {
            sendLen = this.otaData.length - this.fileOffset;
        } else {
            int leftLen = this.otaData.length - this.fileOffset;
            sendLen = Math.min(leftLen, this.blockSize);
            this.blockOffset = this.fileOffset;
        }
        return sendLen;
    }

    byte[] getStartData(int headerSize) {
        int dataLen = Math.min(this.blockSize == -1 ? this.otaData.length : this.blockSize, this.packetSize - headerSize);
        if (this.fileOffset + dataLen > this.otaData.length) {
            dataLen = this.otaData.length - this.fileOffset;
        }
        byte[] data = new byte[dataLen];
        System.arraycopy(this.otaData, this.fileOffset, data, 0, dataLen);
        this.fileOffset += dataLen;
        return data;
    }

    byte[] getDataToBeSent(int headerSize) {
        int dataLen = Math.min(this.blockSize == -1 ? this.otaData.length : this.blockSize, this.packetSize - headerSize);
        if (this.blockSize != -1 && (this.fileOffset - this.blockOffset) + dataLen > this.blockSize) {
            dataLen = this.blockSize - (this.fileOffset - this.blockOffset);
        }
        if (this.fileOffset + dataLen > this.otaData.length) {
            dataLen = this.otaData.length - this.fileOffset;
        }
        byte[] data = new byte[dataLen];
        System.arraycopy(this.otaData, this.fileOffset, data, 0, dataLen);
        this.fileOffset += dataLen;
        return data;
    }

    @Nullable
    public byte[] getHash() {
        return getHash(this.otaData);
    }

    @Nullable
    private byte[] getHash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkIfCompressedData(byte[] data) {
        return data.length >= 3 && data[0] == 80 && data[1] == 79 && data[2] == 84;
    }
}
