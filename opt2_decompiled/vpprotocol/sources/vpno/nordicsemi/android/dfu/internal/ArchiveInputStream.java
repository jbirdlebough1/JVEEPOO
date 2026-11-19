package vpno.nordicsemi.android.dfu.internal;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import vpno.nordicsemi.android.dfu.internal.manifest.FileInfo;
import vpno.nordicsemi.android.dfu.internal.manifest.Manifest;
import vpno.nordicsemi.android.dfu.internal.manifest.ManifestFile;
import vpno.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/ArchiveInputStream.class */
public class ArchiveInputStream extends ZipInputStream {
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_BIN = "application.bin";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String APPLICATION_INIT = "application.dat";
    private Map<String, byte[]> entries;
    private Manifest manifest;
    private CRC32 crc32;
    private byte[] applicationBytes;
    private byte[] softDeviceBytes;
    private byte[] bootloaderBytes;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] systemInitBytes;
    private byte[] applicationInitBytes;
    private byte[] currentSource;
    private int bytesReadFromCurrentSource;
    private int softDeviceSize;
    private int bootloaderSize;
    private int applicationSize;
    private int bytesRead;
    private byte[] markedSource;
    private int bytesReadFromMarkedSource;

    /* JADX WARN: Code duplicated, block: B:116:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:121:0x03c0 A[Catch: all -> 0x0096, TRY_ENTER, TryCatch #0 {all -> 0x0096, blocks: (B:3:0x0029, B:7:0x003a, B:13:0x004a, B:14:0x0052, B:17:0x0085, B:20:0x0099, B:21:0x00bb, B:22:0x00bc, B:28:0x00d0, B:30:0x00d7, B:31:0x00df, B:34:0x0112, B:36:0x0123, B:37:0x0145, B:38:0x0146, B:39:0x014f, B:40:0x0150, B:46:0x0164, B:47:0x016c, B:50:0x019f, B:52:0x01b0, B:53:0x01d2, B:54:0x01d3, B:62:0x01ed, B:64:0x01f4, B:65:0x01fc, B:67:0x022d, B:118:0x03b6, B:68:0x024b, B:69:0x026d, B:70:0x026e, B:71:0x0277, B:75:0x027f, B:76:0x0288, B:121:0x03c0, B:122:0x03c9, B:108:0x0359, B:109:0x0368, B:111:0x0370, B:112:0x0383, B:115:0x038c, B:95:0x02f7, B:96:0x0306, B:98:0x030e, B:99:0x0321, B:102:0x032a, B:82:0x0295, B:83:0x02a4, B:85:0x02ac, B:86:0x02bf, B:89:0x02c8), top: B:126:0x0029 }] */
    /* JADX WARN: Multi-variable type inference failed */
    public ArchiveInputStream(InputStream inputStream, int i, int i2) throws IOException {
        super(inputStream);
        this.crc32 = new CRC32();
        this.entries = new HashMap();
        this.bytesRead = 0;
        this.bytesReadFromCurrentSource = 0;
        try {
            parseZip(i);
            Manifest manifest = this.manifest;
            if (manifest != null) {
                boolean z = false;
                if (manifest.getApplicationInfo() != null && (i2 == 0 || (i2 & 4) > 0)) {
                    FileInfo applicationInfo = this.manifest.getApplicationInfo();
                    this.applicationBytes = this.entries.get(applicationInfo.getBinFileName());
                    this.applicationInitBytes = this.entries.get(applicationInfo.getDatFileName());
                    byte[] bArr = this.applicationBytes;
                    if (bArr == null) {
                        throw new IOException("Application file " + applicationInfo.getBinFileName() + " not found.");
                    }
                    this.applicationSize = bArr.length;
                    this.currentSource = bArr;
                    z = true;
                }
                if (this.manifest.getBootloaderInfo() != null && (i2 == 0 || (i2 & 2) > 0)) {
                    if (this.systemInitBytes != null) {
                        throw new IOException("Manifest: softdevice and bootloader specified. Use softdevice_bootloader instead.");
                    }
                    FileInfo bootloaderInfo = this.manifest.getBootloaderInfo();
                    this.bootloaderBytes = this.entries.get(bootloaderInfo.getBinFileName());
                    this.systemInitBytes = this.entries.get(bootloaderInfo.getDatFileName());
                    byte[] bArr2 = this.bootloaderBytes;
                    if (bArr2 == null) {
                        throw new IOException("Bootloader file " + bootloaderInfo.getBinFileName() + " not found.");
                    }
                    this.bootloaderSize = bArr2.length;
                    this.currentSource = bArr2;
                    z = true;
                }
                if (this.manifest.getSoftdeviceInfo() != null && (i2 == 0 || (i2 & 1) > 0)) {
                    FileInfo softdeviceInfo = this.manifest.getSoftdeviceInfo();
                    this.softDeviceBytes = this.entries.get(softdeviceInfo.getBinFileName());
                    this.systemInitBytes = this.entries.get(softdeviceInfo.getDatFileName());
                    byte[] bArr3 = this.softDeviceBytes;
                    if (bArr3 == null) {
                        throw new IOException("SoftDevice file " + softdeviceInfo.getBinFileName() + " not found.");
                    }
                    this.softDeviceSize = bArr3.length;
                    this.currentSource = bArr3;
                    z = true;
                }
                if (this.manifest.getSoftdeviceBootloaderInfo() == null || (i2 != 0 && ((i2 & 1) <= 0 || (i2 & 2) <= 0))) {
                    if (!z) {
                        throw new IOException("Manifest file must specify at least one file.");
                    }
                } else {
                    if (this.systemInitBytes != null) {
                        throw new IOException("Manifest: The softdevice_bootloader may not be used together with softdevice or bootloader.");
                    }
                    SoftDeviceBootloaderFileInfo softdeviceBootloaderInfo = this.manifest.getSoftdeviceBootloaderInfo();
                    this.softDeviceAndBootloaderBytes = this.entries.get(softdeviceBootloaderInfo.getBinFileName());
                    this.systemInitBytes = this.entries.get(softdeviceBootloaderInfo.getDatFileName());
                    if (this.softDeviceAndBootloaderBytes == null) {
                        throw new IOException("File " + softdeviceBootloaderInfo.getBinFileName() + " not found.");
                    }
                    this.softDeviceSize = softdeviceBootloaderInfo.getSoftdeviceSize();
                    this.bootloaderSize = softdeviceBootloaderInfo.getBootloaderSize();
                    this.currentSource = this.softDeviceAndBootloaderBytes;
                }
            } else {
                boolean z2 = false;
                if (i2 == 0 || (i2 & 4) > 0) {
                    byte[] bArr4 = this.entries.get(APPLICATION_HEX);
                    this.applicationBytes = bArr4;
                    if (bArr4 == null) {
                        this.applicationBytes = this.entries.get(APPLICATION_BIN);
                    }
                    byte[] bArr5 = this.applicationBytes;
                    if (bArr5 != null) {
                        this.applicationSize = bArr5.length;
                        this.applicationInitBytes = this.entries.get(APPLICATION_INIT);
                        this.currentSource = this.applicationBytes;
                        z2 = true;
                    }
                }
                if (i2 == 0 || (i2 & 2) > 0) {
                    byte[] bArr6 = this.entries.get(BOOTLOADER_HEX);
                    this.bootloaderBytes = bArr6;
                    if (bArr6 == null) {
                        this.bootloaderBytes = this.entries.get(BOOTLOADER_BIN);
                    }
                    byte[] bArr7 = this.bootloaderBytes;
                    if (bArr7 != null) {
                        this.bootloaderSize = bArr7.length;
                        this.systemInitBytes = this.entries.get(SYSTEM_INIT);
                        this.currentSource = this.bootloaderBytes;
                        z2 = true;
                    }
                }
                if (i2 == 0 || (i2 & 1) > 0) {
                    byte[] bArr8 = this.entries.get(SOFTDEVICE_HEX);
                    this.softDeviceBytes = bArr8;
                    if (bArr8 == null) {
                        this.softDeviceBytes = this.entries.get(SOFTDEVICE_BIN);
                    }
                    byte[] bArr9 = this.softDeviceBytes;
                    if (bArr9 != null) {
                        this.softDeviceSize = bArr9.length;
                        this.systemInitBytes = this.entries.get(SYSTEM_INIT);
                        this.currentSource = this.softDeviceBytes;
                    } else if (!z2) {
                        throw new IOException("The ZIP file must contain an Application, a Soft Device and/or a Bootloader.");
                    }
                } else if (!z2) {
                    throw new IOException("The ZIP file must contain an Application, a Soft Device and/or a Bootloader.");
                }
            }
            mark(0);
            super.close();
        } catch (Throwable unused) {
            super.close();
            throw this;
        }
    }

    private void parseZip(int i) throws IOException {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String name = nextEntry.getName();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = super.read(bArr);
                if (i2 == -1) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (name.toLowerCase(Locale.US).endsWith("hex")) {
                HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                byte[] bArr2 = new byte[hexInputStream.available()];
                byteArray = bArr2;
                hexInputStream.read(bArr2);
                hexInputStream.close();
            }
            if (MANIFEST.equals(name)) {
                str = str;
                String str2 = new String(byteArray, "UTF-8");
            } else {
                this.entries.put(name, byteArray);
            }
        }
        if (str != null) {
            this.manifest = ((ManifestFile) new Gson().fromJson(str, ManifestFile.class)).getManifest();
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0034  */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    private byte[] startNextFile() {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3 = this.currentSource;
        if (bArr3 == this.softDeviceBytes) {
            byte[] bArr4 = this.bootloaderBytes;
            bArr2 = bArr4;
            if (bArr4 != null) {
                this.currentSource = bArr2;
            } else {
                bArr = this.applicationBytes;
                bArr2 = bArr;
                if (bArr3 != bArr || bArr2 == null) {
                    bArr2 = null;
                    this.currentSource = null;
                } else {
                    this.currentSource = bArr2;
                }
            }
        } else {
            bArr = this.applicationBytes;
            bArr2 = bArr;
            if (bArr3 != bArr) {
                bArr2 = null;
                this.currentSource = null;
            } else {
                bArr2 = null;
                this.currentSource = null;
            }
        }
        byte[] bArr5 = bArr2;
        this.bytesReadFromCurrentSource = 0;
        return bArr5;
    }

    @Override // java.util.zip.ZipInputStream, java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.bootloaderBytes = null;
        this.softDeviceBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        byte[] bArr2 = this.currentSource;
        int length = bArr2.length;
        int i = this.bytesReadFromCurrentSource;
        int length2 = length - i;
        if (bArr.length <= length2) {
            length2 = bArr.length;
        }
        System.arraycopy(bArr2, i, bArr, 0, length2);
        this.bytesReadFromCurrentSource += length2;
        if (bArr.length > length2) {
            if (startNextFile() == null) {
                int i2 = length2;
                this.bytesRead += length2;
                return i2;
            }
            byte[] bArr3 = this.currentSource;
            int length3 = bArr3.length;
            if (bArr.length - length2 <= length3) {
                length3 = bArr.length - length2;
            }
            System.arraycopy(bArr3, 0, bArr, length2, length3);
            this.bytesReadFromCurrentSource += length3;
            length2 += length3;
        }
        int i3 = length2;
        this.bytesRead += length2;
        this.crc32.update(bArr, 0, length2);
        return i3;
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        byte[] bArr;
        if (this.applicationBytes != null && (this.softDeviceBytes != null || this.bootloaderBytes != null || this.softDeviceAndBootloaderBytes != null)) {
            throw new UnsupportedOperationException("Application must be sent in a separate connection.");
        }
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && (bArr = this.softDeviceBytes) != null) {
            this.crc32.update(bArr);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public Manifest getManifest() {
        return this.manifest;
    }

    public int getContentType() {
        byte b = 0;
        int i = this.softDeviceSize;
        if (i > 0) {
            b = (byte) 1;
        }
        int i2 = this.bootloaderSize;
        if (i2 > 0) {
            b = (byte) (b | 2);
        }
        int i3 = this.applicationSize;
        if (i3 > 0) {
            b = (byte) (b | 4);
        }
        if (i3 == 0 && i == 0 && i2 == 0 && this.softDeviceAndBootloaderBytes != null) {
            b = 3;
        }
        return b;
    }

    public int setContentType(int i) {
        if (this.bytesRead > 0) {
            throw new UnsupportedOperationException("Content type must not be change after reading content");
        }
        int contentType = getContentType() & i;
        if ((contentType & 1) == 0) {
            this.softDeviceBytes = null;
            if (this.softDeviceAndBootloaderBytes != null) {
                this.softDeviceAndBootloaderBytes = null;
                this.bootloaderSize = 0;
            }
            this.softDeviceSize = 0;
        }
        if ((contentType & 2) == 0) {
            this.bootloaderBytes = null;
            if (this.softDeviceAndBootloaderBytes != null) {
                this.softDeviceAndBootloaderBytes = null;
                this.softDeviceSize = 0;
            }
            this.bootloaderSize = 0;
        }
        if ((contentType & 4) == 0) {
            this.applicationBytes = null;
            this.applicationSize = 0;
        }
        mark(0);
        return contentType;
    }

    @Override // java.util.zip.ZipInputStream, java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() {
        byte[] bArr = this.softDeviceAndBootloaderBytes;
        return (bArr != null && this.softDeviceSize == 0 && this.bootloaderSize == 0) ? (bArr.length + this.applicationSize) - this.bytesRead : ((this.softDeviceSize + this.bootloaderSize) + this.applicationSize) - this.bytesRead;
    }

    public int softDeviceImageSize() {
        return this.softDeviceSize;
    }

    public int bootloaderImageSize() {
        return this.bootloaderSize;
    }

    public int applicationImageSize() {
        return this.applicationSize;
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }
}
