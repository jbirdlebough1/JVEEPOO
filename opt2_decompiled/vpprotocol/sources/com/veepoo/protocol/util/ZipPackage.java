package com.veepoo.protocol.util;

import androidx.annotation.Keep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import io.runtime.mcumgr.dfu.mcuboot.model.ImageSet;
import io.runtime.mcumgr.dfu.mcuboot.model.TargetImage;
import io.runtime.mcumgr.dfu.suit.model.CacheImageSet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ZipPackage.class */
public final class ZipPackage {
    private Manifest vp_a;
    private final Map vp_b = new HashMap();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ZipPackage$Manifest.class */
    @Keep
    private static class Manifest {
        private int formatVersion;
        private File[] files;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ZipPackage$Manifest$File.class */
        @Keep
        private static class File {
            private String type;
            private String file;
            private int size;
            private int imageIndex = 0;
            private int slot = -1;
            private int partition = 0;

            private File() {
            }
        }

        private Manifest() {
        }
    }

    public ZipPackage(byte[] bArr) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bArr));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (nextEntry.isDirectory()) {
                throw new IOException("Invalid ZIP");
            }
            String strVp_a = vp_a(nextEntry.getName(), ".");
            if (strVp_a.equals("manifest.json")) {
                this.vp_a = (Manifest) new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create().fromJson(new InputStreamReader(zipInputStream), Manifest.class);
            } else if (strVp_a.endsWith(".bin") || strVp_a.endsWith(".suit")) {
                this.vp_b.put(strVp_a, vp_a(zipInputStream));
            }
        }
    }

    public ImageSet vp_a() throws IOException {
        ImageSet imageSetVp_a = vp_a((String) null);
        return imageSetVp_a == null ? new ImageSet() : imageSetVp_a;
    }

    public byte[] vp_c() {
        for (Manifest.File file : this.vp_a.files) {
            if (file.type.equals("suit-envelope")) {
                return (byte[]) this.vp_b.get(file.file);
            }
        }
        for (Manifest.File file2 : this.vp_a.files) {
            if (file2.file.endsWith(".suit")) {
                return (byte[]) this.vp_b.get(file2.file);
            }
        }
        return null;
    }

    public CacheImageSet vp_b() throws IOException {
        CacheImageSet cacheImageSet = null;
        for (Manifest.File file : this.vp_a.files) {
            if (file.type.equals("cache")) {
                String str = file.file;
                byte[] bArr = (byte[]) this.vp_b.get(str);
                if (bArr == null) {
                    throw new IOException("File not found: " + str);
                }
                if (cacheImageSet == null) {
                    cacheImageSet = cacheImageSet;
                    CacheImageSet cacheImageSet2 = new CacheImageSet();
                }
                cacheImageSet.add(file.partition, bArr);
            }
        }
        return cacheImageSet;
    }

    private ImageSet vp_a(String str) throws IOException {
        int i;
        ImageSet imageSet = null;
        int i2 = 0;
        for (Manifest.File file : this.vp_a.files) {
            if (str == null || str.equals(file.type)) {
                String str2 = file.file;
                byte[] bArr = (byte[]) this.vp_b.get(str2);
                if (bArr == null) {
                    throw new IOException("File not found: " + str2);
                }
                if (imageSet == null) {
                    imageSet = imageSet;
                    ImageSet imageSet2 = new ImageSet();
                }
                int i3 = file.slot;
                if (i3 >= 0) {
                    i = i2;
                    i2 = i3;
                } else if ("mcuboot".equals(file.type)) {
                    i = i2 + 1;
                } else {
                    i = i2;
                    i2 = 1;
                }
                imageSet.add(new TargetImage(file.imageIndex, i2, bArr));
                i2 = i;
            }
        }
        return imageSet;
    }

    private byte[] vp_a(ZipInputStream zipInputStream) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = zipInputStream.read(bArr);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    private String vp_a(String str, String str2) throws IOException {
        String canonicalPath = new File(str).getCanonicalPath();
        if (canonicalPath.startsWith(new File(str2).getCanonicalPath())) {
            return canonicalPath.substring(1);
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }
}
