package com.veepoo.protocol;

import android.content.Context;
import com.veepoo.protocol.operate.vp_aa;
import com.veepoo.protocol.operate.vp_ad;
import com.veepoo.protocol.operate.vp_aj;
import com.veepoo.protocol.operate.vp_ak;
import com.veepoo.protocol.operate.vp_ao;
import com.veepoo.protocol.operate.vp_ap;
import com.veepoo.protocol.operate.vp_aq;
import com.veepoo.protocol.operate.vp_aw;
import com.veepoo.protocol.operate.vp_ax;
import com.veepoo.protocol.operate.vp_bd;
import com.veepoo.protocol.operate.vp_bf;
import com.veepoo.protocol.operate.vp_bg;
import com.veepoo.protocol.operate.vp_bi;
import com.veepoo.protocol.operate.vp_bj;
import com.veepoo.protocol.operate.vp_bp;
import com.veepoo.protocol.operate.vp_br;
import com.veepoo.protocol.operate.vp_by;
import com.veepoo.protocol.operate.vp_ca;
import com.veepoo.protocol.operate.vp_cb;
import com.veepoo.protocol.operate.vp_cc;
import com.veepoo.protocol.operate.vp_ce;
import com.veepoo.protocol.operate.vp_cg;
import com.veepoo.protocol.operate.vp_ci;
import com.veepoo.protocol.operate.vp_ck;
import com.veepoo.protocol.operate.vp_cm;
import com.veepoo.protocol.operate.vp_cn;
import com.veepoo.protocol.operate.vp_co;
import com.veepoo.protocol.operate.vp_cp;
import com.veepoo.protocol.operate.vp_ct;
import com.veepoo.protocol.operate.vp_cu;
import com.veepoo.protocol.operate.vp_cv;
import com.veepoo.protocol.operate.vp_cy;
import com.veepoo.protocol.operate.vp_d;
import com.veepoo.protocol.operate.vp_db;
import com.veepoo.protocol.operate.vp_dc;
import com.veepoo.protocol.operate.vp_dd;
import com.veepoo.protocol.operate.vp_de;
import com.veepoo.protocol.operate.vp_df;
import com.veepoo.protocol.operate.vp_dh;
import com.veepoo.protocol.operate.vp_e;
import com.veepoo.protocol.operate.vp_f;
import com.veepoo.protocol.operate.vp_h;
import com.veepoo.protocol.operate.vp_i;
import com.veepoo.protocol.operate.vp_n;
import com.veepoo.protocol.operate.vp_p;
import com.veepoo.protocol.operate.vp_q;
import com.veepoo.protocol.operate.vp_s;
import com.veepoo.protocol.operate.vp_u;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/vp_b.class */
public class vp_b {
    private vp_f vp_a = null;
    private vp_u vp_b = null;
    private vp_cg vp_c = null;
    private volatile vp_bf vp_d = null;
    private volatile vp_dh vp_e = null;
    private volatile vp_ak vp_f = null;
    private volatile vp_aj vp_g = null;
    private volatile com.veepoo.protocol.multi_lead.operate.vp_b vp_h = null;
    private volatile com.veepoo.protocol.multi_lead.operate.vp_c vp_i = null;
    private volatile vp_dd vp_j = null;
    private volatile vp_aa vp_k = null;
    private volatile vp_cm vp_l = null;
    private volatile vp_cc vp_m = null;
    private Context vp_n = null;

    vp_a vp_a(String str) {
        vp_a vp_aVarVp_i;
        if (str.equals("pwd_comfirm_oprate")) {
            vp_aVarVp_i = vp_caVar;
            vp_ca vp_caVar = new vp_ca();
        } else if (str.equals("pwd_modify_oprate")) {
            vp_aVarVp_i = vp_cbVar;
            vp_cb vp_cbVar = new vp_cb();
        } else if (str.equals("device_msg_oprate")) {
            this.vp_l = vp_j();
            vp_aVarVp_i = this.vp_l;
        } else if (str.equals("device_function")) {
            this.vp_k = vp_c();
            vp_aVarVp_i = this.vp_k;
        } else if (str.equals("custom_setting_oprate")) {
            vp_u vp_uVarVp_b = vp_b();
            vp_aVarVp_i = vp_uVarVp_b;
            this.vp_b = vp_uVarVp_b;
        } else if (str.equals("alarm_oprate")) {
            vp_aVarVp_i = vp_aVar;
            com.veepoo.protocol.operate.vp_a vp_aVar = new com.veepoo.protocol.operate.vp_a();
        } else if (str.equals("heart_read_oprate")) {
            vp_aVarVp_i = vp_bdVar;
            vp_bd vp_bdVar = new vp_bd();
        } else if (str.equals("change_watch_language_oprate")) {
            vp_aVarVp_i = vp_bgVar;
            vp_bg vp_bgVar = new vp_bg();
        } else if (str.equals("bp_oprate")) {
            vp_f vp_fVarVp_a = vp_a();
            vp_aVarVp_i = vp_fVarVp_a;
            this.vp_a = vp_fVarVp_a;
        } else if (str.equals("bp_model_setting_oprate")) {
            vp_aVarVp_i = vp_eVar;
            vp_e vp_eVar = new vp_e();
        } else if (str.equals("take_photo_oprate")) {
            vp_aVarVp_i = vp_pVar;
            vp_p vp_pVar = new vp_p();
        } else if (str.equals("read_current_sport_oprate")) {
            vp_aVarVp_i = vp_cvVar;
            vp_cv vp_cvVar = new vp_cv();
        } else if (str.equals("read_current_sport_sportmodel_oprate")) {
            vp_aVarVp_i = vp_cuVar;
            vp_cu vp_cuVar = new vp_cu();
        } else if (str.equals("person_info_oprate")) {
            vp_aVarVp_i = vp_byVar;
            vp_by vp_byVar = new vp_by();
        } else if (str.equals("long_seat_oprate")) {
            vp_aVarVp_i = vp_biVar;
            vp_bi vp_biVar = new vp_bi();
        } else if (str.equals("read_battery_oprate")) {
            vp_aVarVp_i = vp_hVar;
            vp_h vp_hVar = new vp_h();
        } else if (str.equals("night_turn_opeate")) {
            vp_aVarVp_i = vp_brVar;
            vp_br vp_brVar = new vp_br();
        } else if (str.equals("find_watch_by_phon_oprate")) {
            vp_aVarVp_i = vp_apVar;
            vp_ap vp_apVar = new vp_ap();
        } else if (str.equals("breath_read_oprate")) {
            vp_aVarVp_i = vp_nVar;
            vp_n vp_nVar = new vp_n();
        } else if (str.equals("spo2h_read_oprate")) {
            vp_aVarVp_i = vp_cpVar;
            vp_cp vp_cpVar = new vp_cp();
        } else if (str.equals("fatigue_read_oprate")) {
            vp_aVarVp_i = vp_aoVar;
            vp_ao vp_aoVar = new vp_ao();
        } else if (str.equals("drink_oprate")) {
            vp_aVarVp_i = vp_adVar;
            vp_ad vp_adVar = new vp_ad();
        } else if (str.equals("women_mense_setting_oprate")) {
            vp_aVarVp_i = vp_dfVar;
            vp_df vp_dfVar = new vp_df();
        } else if (str.equals("count_down_oprate")) {
            vp_aVarVp_i = vp_sVar;
            vp_s vp_sVar = new vp_s();
        } else if (str.equals("screen_ligth_time_oprate")) {
            vp_aVarVp_i = vp_ckVar;
            vp_ck vp_ckVar = new vp_ck();
        } else if (str.equals("screen_ligth_oprate")) {
            vp_aVarVp_i = vp_ciVar;
            vp_ci vp_ciVar = new vp_ci();
        } else if (str.equals("sport_model_openclose_oprate") || str.equals("sport_model_crc_oprate")) {
            vp_aVarVp_i = vp_ctVar;
            vp_ct vp_ctVar = new vp_ct();
        } else if (str.equals("all_setting_oprate")) {
            vp_aVarVp_i = vp_bVar;
            com.veepoo.protocol.operate.vp_b vp_bVar = new com.veepoo.protocol.operate.vp_b();
        } else if (str.equals("spo2h_breath_break_remind_oprate")) {
            vp_aVarVp_i = vp_coVar;
            vp_co vp_coVar = new vp_co();
        } else if (str.equals("head_gsensor")) {
            vp_aVarVp_i = vp_axVar;
            vp_ax vp_axVar = new vp_ax();
        } else if (str.equals("battery_lowpower_oprate")) {
            vp_aVarVp_i = vp_bjVar;
            vp_bj vp_bjVar = new vp_bj();
        } else if (str.equals("function_bp_oprate")) {
            vp_aVarVp_i = vp_dVar;
            vp_d vp_dVar = new vp_d();
        } else if (str.equals("check_wear_oprate")) {
            vp_aVarVp_i = vp_qVar;
            vp_q vp_qVar = new vp_q();
        } else if (str.equals("sos_oprate")) {
            vp_aVarVp_i = vp_cnVar;
            vp_cn vp_cnVar = new vp_cn();
        } else if (str.equals("sync_time_oprate")) {
            vp_aVarVp_i = vp_dbVar;
            vp_db vp_dbVar = new vp_db();
        } else if (str.equals("function_big_data_tarn")) {
            vp_aVarVp_i = vp_dcVar;
            vp_dc vp_dcVar = new vp_dc();
        } else if (str.equals("find_phone_by_watch_operate")) {
            vp_aVarVp_i = vp_aqVar;
            vp_aq vp_aqVar = new vp_aq();
        } else if (str.equals("gps_lat_lon_oprate")) {
            vp_aVarVp_i = vp_awVar;
            vp_aw vp_awVar = new vp_aw();
        } else if (str.equals("tempture_detect_oprate")) {
            vp_aVarVp_i = vp_cyVar;
            vp_cy vp_cyVar = new vp_cy();
        } else if (str.equals("music_oprate")) {
            vp_aVarVp_i = vp_bpVar;
            vp_bp vp_bpVar = new vp_bp();
        } else if (str.equals("weather_oprateweather_oprate")) {
            vp_aVarVp_i = vp_deVar;
            vp_de vp_deVar = new vp_de();
        } else if (str.equals("rename_operate")) {
            vp_aVarVp_i = vp_i();
        } else if (str.equals("read_production_info_oprate")) {
            vp_aVarVp_i = vp_ceVar;
            vp_ce vp_ceVar = new vp_ce();
        } else if (str.equals("blood_component_operate")) {
            vp_aVarVp_i = vp_iVar;
            vp_i vp_iVar = new vp_i();
        } else if (str.equals("qx17_data_acquisition_oprate")) {
            if (this.vp_m == null) {
                this.vp_m = new vp_cc();
            }
            vp_aVarVp_i = this.vp_m;
        } else {
            vp_aVarVp_i = vp_aVar;
            com.veepoo.protocol.operate.vp_a vp_aVar2 = new com.veepoo.protocol.operate.vp_a();
        }
        vp_a vp_aVar3 = vp_aVarVp_i;
        vp_aVar3.vp_a(this.vp_n);
        return vp_aVar3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_aa vp_c() {
        if (this.vp_k == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_k == null) {
                        this.vp_k = new vp_aa();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_cm vp_j() {
        if (this.vp_l == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_l == null) {
                        this.vp_l = new vp_cm();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_cg vp_i() {
        if (this.vp_c == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_c == null) {
                        this.vp_c = new vp_cg();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_bf vp_h() {
        if (this.vp_d == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_d == null) {
                        this.vp_d = new vp_bf();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_dh vp_l() {
        if (this.vp_e == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_e == null) {
                        this.vp_e = new vp_dh();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_ak vp_g() {
        if (this.vp_f == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_f == null) {
                        this.vp_f = new vp_ak();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_aj vp_f() {
        if (this.vp_g == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_g == null) {
                        this.vp_g = new vp_aj();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public com.veepoo.protocol.multi_lead.operate.vp_b vp_d() {
        if (this.vp_h == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_h == null) {
                        this.vp_h = new com.veepoo.protocol.multi_lead.operate.vp_b();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public com.veepoo.protocol.multi_lead.operate.vp_c vp_e() {
        if (this.vp_i == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_i == null) {
                        this.vp_i = new com.veepoo.protocol.multi_lead.operate.vp_c();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_dd vp_k() {
        if (this.vp_j == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_j == null) {
                        this.vp_j = new vp_dd();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_u vp_b() {
        if (this.vp_b == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_b == null) {
                        this.vp_b = new vp_u();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.vp_b] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public vp_f vp_a() {
        if (this.vp_a == null) {
            ?? r0 = this;
            synchronized (vp_b.class) {
                try {
                    if (r0.vp_a == null) {
                        this.vp_a = new vp_f();
                    }
                    r0 = vp_b.class;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return this.vp_a;
    }

    public void vp_a(Context context) {
        this.vp_n = context;
    }
}
