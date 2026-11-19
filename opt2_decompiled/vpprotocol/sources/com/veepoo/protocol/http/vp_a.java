package com.veepoo.protocol.http;

import com.veepoo.protocol.listener.oad.OnDownLoadListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/http/vp_a.class */
public class vp_a {
    public static String vp_c = "https://www.vphband.com:9001/api/";
    public final String vp_a;
    Boolean vp_b;

    /* JADX INFO: renamed from: com.veepoo.protocol.http.vp_a$vp_a, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/http/vp_a$vp_a.class */
    public interface InterfaceC0001vp_a {
        void vp_a(int i, String str);
    }

    public vp_a() {
        this.vp_a = "http://120.79.208.77:88/api/";
        this.vp_b = Boolean.FALSE;
    }

    public void vp_a(String str, String str2, OnDownLoadListener onDownLoadListener) throws Throwable {
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL(str);
            int contentLength = url.openConnection().getContentLength();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(url.openStream());
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str2, false);
                try {
                    byte[] bArr = new byte[1024];
                    int i = 0;
                    while (true) {
                        int i2 = bufferedInputStream2.read(bArr, 0, 1024);
                        if (i2 == -1) {
                            onDownLoadListener.onFinish();
                            bufferedInputStream2.close();
                            fileOutputStream2.close();
                            return;
                        } else {
                            fileOutputStream2.write(bArr, 0, i2);
                            int i3 = i + i2;
                            i = i3;
                            onDownLoadListener.onProgress(i3 / contentLength);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        onDownLoadListener.onFinish();
                        bufferedInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public vp_a(String str) {
        this.vp_a = "http://120.79.208.77:88/api/";
        this.vp_b = Boolean.FALSE;
        vp_c = str;
    }

    private String vp_a(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            if (entry.getKey() != null) {
                sb.append(String.format("%s=%s", vp_a(entry.getKey().toString()), vp_a(entry.getValue() == null ? "unknown" : entry.getValue().toString())));
            }
        }
        return sb.toString();
    }

    public vp_a(boolean z) {
        this.vp_a = "http://120.79.208.77:88/api/";
        this.vp_b = Boolean.FALSE;
        this.vp_b = Boolean.valueOf(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.http.vp_a] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    public String vp_a(String str, Map map) throws Throwable {
        ?? r9;
        ?? r0;
        ?? line;
        ?? bufferedReader = this;
        ?? sb = new StringBuilder();
        String str2 = "?" + vp_a(map);
        new StringBuilder().append("sendGet param: ").append(str2);
        ?? r10 = 0;
        try {
            try {
                String string = (bufferedReader.vp_b.booleanValue() ? new StringBuilder().append("http://120.79.208.77:88/api/").append(str).append(str2) : new StringBuilder().append(vp_c).append(str).append(str2)).toString();
                new StringBuilder().append("sendGet urlNameString: ").append(string);
                URLConnection uRLConnectionOpenConnection = new URL(string).openConnection();
                uRLConnectionOpenConnection.setRequestProperty("accept", "*/*");
                uRLConnectionOpenConnection.setRequestProperty("connection", "Keep-Alive");
                uRLConnectionOpenConnection.setConnectTimeout(120000);
                uRLConnectionOpenConnection.connect();
                bufferedReader = new BufferedReader(new InputStreamReader(uRLConnectionOpenConnection.getInputStream()));
                while (true) {
                    ?? r1 = bufferedReader;
                    try {
                        line = r1.readLine();
                        if (line != 0) {
                            sb.append(line);
                        } else {
                            try {
                                break;
                            } catch (IOException unused) {
                                line.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        r9 = e;
                        r0 = r1;
                        r0.printStackTrace();
                        ?? r2 = r9;
                        if (r2 != 0) {
                            try {
                                r2 = r9;
                                r2.close();
                            } catch (IOException unused2) {
                                r2.printStackTrace();
                            }
                        }
                        return "Error";
                    } catch (Throwable th) {
                        th = th;
                        r10 = bufferedReader;
                        ?? r3 = r10;
                        if (r3 != 0) {
                            try {
                                r3 = r10;
                                r3.close();
                            } catch (IOException unused3) {
                                r3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                line = bufferedReader;
                line.close();
                return sb.toString();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused4) {
            r0 = bufferedReader;
            r9 = r10;
        }
    }

    /* JADX WARN: Code duplicated, block: B:49:0x0121 A[Catch: IOException -> 0x0119, TRY_ENTER, TryCatch #1 {IOException -> 0x0119, blocks: (B:45:0x0112, B:49:0x0121), top: B:65:0x0112 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x0141 A[Catch: IOException -> 0x0139, TRY_ENTER, TryCatch #0 {IOException -> 0x0139, blocks: (B:55:0x0132, B:59:0x0141), top: B:63:0x0132 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.io.BufferedReader, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    public String vp_a(String str, Map map, InterfaceC0001vp_a interfaceC0001vp_a) throws Throwable {
        ?? r12;
        URL url;
        URL url2;
        PrintWriter printWriter = null;
        ?? r13 = 0;
        String strVp_a = vp_a(map);
        StringBuilder sb = new StringBuilder();
        try {
            if (this.vp_b.booleanValue()) {
                url = url2;
                url2 = new URL("http://120.79.208.77:88/api/" + str);
            } else {
                url = new URL("https://www.vphband.com:9001/api/" + str);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter2 = new PrintWriter(httpURLConnection.getOutputStream());
            try {
                printWriter2.print(strVp_a);
                printWriter2.flush();
                ?? bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    } catch (Exception unused) {
                        r12 = bufferedReader;
                        printWriter = printWriter2;
                        ?? r0 = printWriter;
                        if (r0 != 0) {
                            try {
                                printWriter.close();
                                if (r12 != 0) {
                                    r0 = r12;
                                    r0.close();
                                }
                            } catch (IOException unused2) {
                                r0.printStackTrace();
                            }
                        } else if (r12 != 0) {
                            r0 = r12;
                            r0.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r13 = bufferedReader;
                        printWriter = printWriter2;
                        ?? r1 = printWriter;
                        if (r1 != 0) {
                            try {
                                printWriter.close();
                                if (r13 != 0) {
                                    r1 = r13;
                                    r1.close();
                                }
                            } catch (IOException unused3) {
                                r1.printStackTrace();
                                throw th;
                            }
                        } else if (r13 != 0) {
                            r1 = r13;
                            r1.close();
                        }
                        throw th;
                    }
                }
                interfaceC0001vp_a.vp_a(httpURLConnection.getResponseCode(), sb.toString());
                try {
                    printWriter2.close();
                    bufferedReader.close();
                } catch (IOException unused4) {
                    bufferedReader.printStackTrace();
                }
            } catch (Exception unused5) {
                printWriter = printWriter2;
                r12 = r13;
            } catch (Throwable th2) {
                th = th2;
                printWriter = printWriter2;
            }
        } catch (Exception unused6) {
            r12 = r13;
        } catch (Throwable th3) {
            th = th3;
        }
        return sb.toString();
    }

    private String vp_a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
