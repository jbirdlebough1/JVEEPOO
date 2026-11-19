package com.veepoo.protocol.jl.classic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_c.class */
public class vp_c {
    private boolean vp_b;
    private String vp_g;
    private int vp_a = 0;
    private int vp_c = 2000;
    private boolean vp_d = false;
    private boolean vp_e = false;
    private boolean vp_f = true;
    private String vp_h = "JLOTA";
    private int vp_i = 1;
    private int vp_j = 0;
    private int vp_k = 20;
    private final Map vp_l = new HashMap();
    private boolean vp_m = false;
    private boolean vp_n = false;
    private boolean vp_o = false;
    private UUID vp_p = vp_a.vp_h;

    public static vp_c vp_a() {
        return new vp_c().vp_a(true).vp_b(2000).vp_a(20).vp_b(true).vp_a("JLAISDK");
    }

    public vp_c() {
        vp_a(vp_a.vp_b, vp_a.vp_c, vp_a.vp_d);
    }

    public String vp_c() {
        return this.vp_g;
    }

    public int vp_b() {
        return this.vp_i;
    }

    public UUID vp_d() {
        return this.vp_p;
    }

    public String toString() {
        return "BluetoothOption{priority=" + this.vp_a + ", reconnect=" + this.vp_b + ", timeoutMs=" + this.vp_c + ", scanFilterData='" + this.vp_g + "', mtu=" + this.vp_k + ", enterLowPowerMode=" + this.vp_d + ", bleScanStrategy=" + this.vp_i + ", bleScanMode=" + this.vp_j + ", bleUUIDMap=" + this.vp_l + ", sppUUID=" + this.vp_p + ", isUseBleBondWay=" + this.vp_m + ", isAutoConnectBle=" + this.vp_n + ", isUseDeviceAuth=" + this.vp_f + '}';
    }

    public vp_c vp_b(int i) {
        this.vp_c = i;
        return this;
    }

    public vp_c vp_a(UUID uuid, UUID uuid2, UUID uuid3) {
        this.vp_l.put("ble_service_uuid", uuid);
        this.vp_l.put("ble_write_characteristic_uuid", uuid2);
        this.vp_l.put("ble_notification_characteristic_uuid", uuid3);
        return this;
    }

    public vp_c vp_a(int i) {
        this.vp_k = vp_d.vp_a(i);
        return this;
    }

    public vp_c vp_b(boolean z) {
        this.vp_f = z;
        return this;
    }

    public vp_c vp_a(boolean z) {
        this.vp_b = z;
        return this;
    }

    public vp_c vp_a(String str) {
        this.vp_g = str;
        return this;
    }
}
