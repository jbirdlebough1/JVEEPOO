package com.veepoo.protocol.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_ah.class */
public abstract class vp_ah {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v8 */
    public static void vp_a(Context context, byte[] bArr, String str) throws Throwable {
        ?? r0;
        StringBuilder sbAppend = new StringBuilder().append(context.getExternalFilesDir(null));
        String str2 = File.separator;
        String string = sbAppend.append(str2).append("VP_SDK").append(str2).append("UI").append(str2).toString();
        ?? r9 = 0;
        ?? fileOutputStream = string + str;
        try {
            try {
                try {
                    File file = new File(string);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File file2 = new File((String) fileOutputStream);
                    if (file2.exists()) {
                        file2.delete();
                        file2.createNewFile();
                    } else {
                        file2.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream((String) fileOutputStream);
                    try {
                        fileOutputStream.write(bArr);
                        System.out.println("字节数组成功写入文件 " + fileOutputStream);
                        fileOutputStream.close();
                        fileOutputStream = fileOutputStream;
                    } catch (IOException e) {
                        r9 = e;
                        r0 = fileOutputStream;
                        r0.printStackTrace();
                        if (!(r9 == true ? 1 : 0)) {
                            return;
                        }
                        FileOutputStream fileOutputStream2 = r9 == true ? 1 : 0;
                        fileOutputStream2.close();
                        fileOutputStream = fileOutputStream2;
                    } catch (Throwable th) {
                        r9 = fileOutputStream;
                        th = th;
                        ?? r1 = r9;
                        if (r1 != 0) {
                            try {
                                r1 = r9;
                                r1.close();
                            } catch (IOException unused) {
                                r1.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused2) {
                    fileOutputStream.printStackTrace();
                }
            } catch (IOException unused3) {
                r0 = fileOutputStream;
                r9 = r9;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
