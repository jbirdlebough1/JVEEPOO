package vpno.nordicsemi.android.dfu.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import vpno.nordicsemi.android.dfu.internal.exception.HexFileValidationException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/HexInputStream.class */
public class HexInputStream extends FilterInputStream {
    private final int LINE_LENGTH;
    private final byte[] localBuf;
    private int localPos;
    private int pos;
    private int size;
    private int lastAddress;
    private int available;
    private int bytesRead;
    private final int MBRSize;

    public HexInputStream(InputStream inputStream, int i) {
        super(new BufferedInputStream(inputStream));
        this.LINE_LENGTH = 128;
        byte[] bArr = new byte[128];
        this.localBuf = bArr;
        this.localPos = 128;
        this.size = bArr.length;
        this.lastAddress = 0;
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [vpno.nordicsemi.android.dfu.internal.HexInputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.io.FilterInputStream, vpno.nordicsemi.android.dfu.internal.HexInputStream] */
    private int calculateBinSize(int i) throws IOException {
        int i2;
        int i3 = 0;
        ?? r0 = ((FilterInputStream) this).in;
        r0.mark(r0.available());
        int i4 = 0;
        try {
            int i5 = r0.read();
            while (true) {
                r0 = this;
                r0.checkComma(i5);
                int i6 = r0.readByte(r0);
                int address = r0.readAddress(r0);
                int i7 = r0.readByte(r0);
                if (i7 != 0) {
                    if (i7 == 1) {
                        int i8 = i3;
                        r0.reset();
                        return i8;
                    }
                    if (i7 == 2) {
                        int i9 = i3;
                        int address2 = readAddress(r0) << 4;
                        if (i9 > 0 && (address2 >> 16) != (i4 >> 16) + 1) {
                            int i10 = i3;
                            r0.reset();
                            return i10;
                        }
                        skip(r0, 2L);
                        i4 = address2;
                    } else if (i7 == 4) {
                        int i11 = i3;
                        int address3 = readAddress(r0);
                        if (i11 > 0 && address3 != (i4 >> 16) + 1) {
                            int i12 = i3;
                            r0.reset();
                            return i12;
                        }
                        i4 = address3 << 16;
                        skip(r0, 2L);
                    }
                    while (true) {
                        i2 = r0.read();
                        i5 = i2;
                        if (i2 != 10 || i5 == 13) {
                        }
                    }
                } else if (i4 + address >= i) {
                    i3 += i6;
                }
                skip(r0, (i6 * 2) + 2);
                while (true) {
                    i2 = r0.read();
                    i5 = i2;
                    if (i2 != 10) {
                    }
                }
            }
        } catch (Throwable unused) {
            r0.reset();
            throw r0;
        }
    }

    private int readLine() throws IOException {
        if (this.pos == -1) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        while (true) {
            int i = inputStream.read();
            this.pos++;
            if (i != 10 && i != 13) {
                checkComma(i);
                int i2 = readByte(inputStream);
                this.pos += 2;
                int address = readAddress(inputStream);
                this.pos += 4;
                int i3 = readByte(inputStream);
                int i4 = this.pos + 2;
                this.pos = i4;
                if (i3 != 0) {
                    if (i3 == 1) {
                        this.pos = -1;
                        return 0;
                    }
                    if (i3 == 2) {
                        int address2 = readAddress(inputStream) << 4;
                        int i5 = this.pos + 4;
                        this.pos = i5;
                        if (this.bytesRead > 0 && (address2 >> 16) != (this.lastAddress >> 16) + 1) {
                            return 0;
                        }
                        this.lastAddress = address2;
                        this.pos = (int) (((long) i5) + skip(inputStream, 2L));
                    } else if (i3 != 4) {
                        this.pos = (int) (((long) i4) + skip(inputStream, (i2 * 2) + 2));
                    } else {
                        int address3 = readAddress(inputStream);
                        int i6 = this.pos + 4;
                        this.pos = i6;
                        if (this.bytesRead > 0 && address3 != (this.lastAddress >> 16) + 1) {
                            return 0;
                        }
                        this.lastAddress = address3 << 16;
                        this.pos = (int) (((long) i6) + skip(inputStream, 2L));
                    }
                } else if (this.lastAddress + address < this.MBRSize) {
                    this.pos = (int) (((long) i4) + skip(inputStream, (i2 * 2) + 2));
                }
                if (i3 == 0) {
                    for (int i7 = 0; i7 < this.localBuf.length && i7 < i2; i7++) {
                        int i8 = readByte(inputStream);
                        this.pos += 2;
                        this.localBuf[i7] = (byte) i8;
                    }
                    this.pos = (int) (((long) this.pos) + skip(inputStream, 2L));
                    this.localPos = 0;
                    return i2;
                }
            }
        }
    }

    private void checkComma(int i) throws HexFileValidationException {
        if (i != 58) {
            throw new HexFileValidationException("Not a HEX file");
        }
    }

    private long skip(InputStream inputStream, long j) throws IOException {
        long jSkip = inputStream.skip(j);
        long jSkip2 = jSkip;
        if (jSkip < j) {
            jSkip2 += inputStream.skip(j - jSkip2);
        }
        return jSkip2;
    }

    private int readByte(InputStream inputStream) {
        return (asciiToInt(inputStream.read()) << 4) | asciiToInt(inputStream.read());
    }

    private int readAddress(InputStream inputStream) {
        return (readByte(inputStream) << 8) | readByte(inputStream);
    }

    private int asciiToInt(int i) {
        if (i >= 65) {
            return i - 55;
        }
        if (i >= 48) {
            return i - 48;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return this.available - this.bytesRead;
    }

    public int readPacket(byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            int i2 = this.localPos;
            if (i2 < this.size) {
                int i3 = i;
                i++;
                byte[] bArr2 = this.localBuf;
                this.localPos = i2 + 1;
                bArr[i3] = bArr2[i2];
            } else {
                int i4 = this.bytesRead;
                int line = readLine();
                this.size = line;
                this.bytesRead = i4 + line;
                if (line == 0) {
                    break;
                }
            }
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }

    public int sizeInBytes() {
        return this.available;
    }

    public int sizeInPackets(int i) {
        int iSizeInBytes = sizeInBytes();
        return (iSizeInBytes / i) + (iSizeInBytes % i > 0 ? 1 : 0);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.pos = 0;
        this.bytesRead = 0;
        this.localPos = 0;
    }

    public HexInputStream(byte[] bArr, int i) {
        super(new ByteArrayInputStream(bArr));
        this.LINE_LENGTH = 128;
        byte[] bArr2 = new byte[128];
        this.localBuf = bArr2;
        this.localPos = 128;
        this.size = bArr2.length;
        this.lastAddress = 0;
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return readPacket(bArr);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }
}
