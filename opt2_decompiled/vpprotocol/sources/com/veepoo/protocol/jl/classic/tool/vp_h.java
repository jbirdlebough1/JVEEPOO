package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_h.class */
public class vp_h extends Thread {
    private final BluetoothDevice vp_a;
    private final BluetoothSocket vp_b;
    private final int vp_c;
    private final vp_a vp_d;
    private volatile boolean vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_h$vp_a.class */
    public interface vp_a {
        void vp_a(long j);

        void vp_a(long j, BluetoothDevice bluetoothDevice, byte[] bArr);

        void vp_a(long j, int i, BluetoothDevice bluetoothDevice);
    }

    public vp_h(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, int i, vp_a vp_aVar) {
        super("ReceiveSppDataThread : " + bluetoothDevice);
        this.vp_a = bluetoothDevice;
        this.vp_b = bluetoothSocket;
        this.vp_c = i;
        this.vp_d = vp_aVar;
    }

    public void vp_a() {
        this.vp_e = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.bluetooth.BluetoothSocket] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r1v21, types: [java.lang.Object] */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        this.vp_e = true;
        int i = 0;
        vp_a vp_aVar = this.vp_d;
        if (vp_aVar != null) {
            vp_aVar.vp_a(getId());
        }
        if (this.vp_a != null) {
            byte[] bArr = new byte[this.vp_c];
            boolean z = false;
            ?? inputStream = this.vp_b;
            ?? r10 = z;
            if (inputStream != 0) {
                try {
                    inputStream = inputStream.getInputStream();
                    r10 = inputStream;
                } catch (IOException unused) {
                    inputStream.printStackTrace();
                    r10 = z;
                }
            }
            new StringBuilder().append("ReceiveDataThread isRunning : ").append(this.vp_e).append(", mBluetoothSocket : ").append(this.vp_b).append(", inputStream : ").append(r10);
            while (this.vp_e && r10 != 0) {
                try {
                    int i2 = r10.read(bArr);
                    if (i2 < 0) {
                        i = 2;
                        break;
                    } else if (i2 == 0) {
                        Thread.sleep(30L);
                    } else {
                        byte[] bArr2 = new byte[i2];
                        System.arraycopy(bArr, 0, bArr2, 0, i2);
                        vp_a vp_aVar2 = this.vp_d;
                        if (vp_aVar2 != null) {
                            vp_aVar2.vp_a(getId(), this.vp_a, bArr2);
                        }
                    }
                } catch (Exception e) {
                    new StringBuilder().append("-ReceiveDataThread- have an exception : ").append(e);
                    e.printStackTrace();
                    i = 2;
                }
            }
        } else {
            i = 1;
        }
        this.vp_e = false;
        vp_a vp_aVar3 = this.vp_d;
        if (vp_aVar3 != null) {
            vp_aVar3.vp_a(getId(), i, this.vp_a);
        }
    }

    public vp_h(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, vp_a vp_aVar) {
        this(bluetoothDevice, bluetoothSocket, 4096, vp_aVar);
    }
}
