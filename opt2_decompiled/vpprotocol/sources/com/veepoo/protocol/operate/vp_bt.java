package com.veepoo.protocol.operate;

import android.content.Context;
import android.text.TextUtils;
import com.veepoo.protocol.listener.oad.OnDownLoadListener;
import com.veepoo.protocol.listener.oad.OnGetOadVersionListener;
import com.veepoo.protocol.listener.oad.OnUpdateCheckListener;
import com.veepoo.protocol.model.OadFileBean;
import com.veepoo.protocol.model.settings.OadSetting;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bt.class */
public class vp_bt {
    public static String vp_e = "";
    private Context vp_a;
    OnUpdateCheckListener vp_b;
    OnGetOadVersionListener vp_c;
    OadSetting vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bt$vp_a.class */
    class vp_a implements Runnable {
        final /* synthetic */ Map vp_a;

        vp_a(Map map) {
            this.vp_a = map;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            vp_bt vp_btVar = vp_bt.this;
            vp_btVar.vp_a(vp_btVar.vp_d.getDeviceVersion(), this.vp_a, vp_bt.this.vp_d.isDebug(), vp_bt.this.vp_d.getHostUrl());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bt$vp_b.class */
    class vp_b implements OnDownLoadListener {
        final /* synthetic */ String vp_a;
        final /* synthetic */ String vp_b;

        vp_b(String str, String str2) {
            this.vp_a = str;
            this.vp_b = str2;
        }

        @Override // com.veepoo.protocol.listener.oad.OnDownLoadListener
        public void onProgress(float f) {
            OnUpdateCheckListener onUpdateCheckListener = vp_bt.this.vp_b;
            if (onUpdateCheckListener != null) {
                onUpdateCheckListener.onDownLoadOadFile(f);
            }
        }

        @Override // com.veepoo.protocol.listener.oad.OnDownLoadListener
        public void onFinish() {
            vp_bt.this.vp_b(this.vp_a, this.vp_b);
        }
    }

    public vp_bt(Context context, OadSetting oadSetting, OnGetOadVersionListener onGetOadVersionListener) {
        this.vp_a = context;
        this.vp_d = oadSetting;
        this.vp_c = onGetOadVersionListener;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.http.vp_a, java.lang.Throwable] */
    private void vp_a(String str, String str2, String str3) throws Throwable {
        ?? vp_aVar = new com.veepoo.protocol.http.vp_a(false);
        try {
            vp_aVar.vp_a(str, str2, new vp_b(str2, str3));
        } catch (IOException unused) {
            vp_aVar.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.model.OadFileBean, java.lang.Throwable] */
    private OadFileBean vp_c(String str) {
        ?? oadFileBean = new OadFileBean();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("Version3");
            String string = jSONObject.getString("DeviceType");
            String string2 = jSONObject.getString("NewVersion");
            String string3 = jSONObject.getString("NewMD5");
            String string4 = jSONObject.getString("NewUrl");
            String string5 = jSONObject.getString("NewSize");
            String string6 = jSONObject.getString("Des");
            oadFileBean.setDeviceNumber(string);
            oadFileBean.setDeviceVersion(string2);
            oadFileBean.setMd5(string3);
            oadFileBean.setDownUrl(string4);
            oadFileBean.setSize(string5);
            oadFileBean.setDes(string6);
            oadFileBean.toString();
        } catch (JSONException unused) {
            oadFileBean.printStackTrace();
        }
        return oadFileBean;
    }

    private int vp_f(String str) {
        String str2 = "";
        for (String str3 : str.split("\\.")) {
            str2 = str2 + str3;
        }
        return vp_b(str2);
    }

    private boolean vp_d(String str) {
        File file = new File(vp_e);
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(str).exists();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, java.lang.Throwable] */
    private int vp_b(String str) {
        int iFloatValue = 0;
        ?? Vp_e = vp_e(str);
        if (Vp_e != 0) {
            try {
                iFloatValue = (int) Float.valueOf(str).floatValue();
            } catch (Exception unused) {
                Vp_e.printStackTrace();
            }
        }
        return iFloatValue;
    }

    private boolean vp_e(String str) {
        return (str == null || str.equals("")) ? false : true;
    }

    public vp_bt(Context context, OadSetting oadSetting, OnUpdateCheckListener onUpdateCheckListener) {
        this.vp_a = context;
        this.vp_d = oadSetting;
        this.vp_b = onUpdateCheckListener;
    }

    private boolean vp_c(String str, String str2) {
        int iVp_f = vp_f(str);
        int iVp_f2 = vp_f(str2);
        boolean z = iVp_f2 > iVp_f;
        boolean z2 = z;
        new StringBuilder().append("localVersion=").append(iVp_f).append(",netVersion=").append(iVp_f2).append(",isVersionNew=").append(z);
        return z2;
    }

    private boolean vp_b(String str, String str2) {
        boolean z = false;
        boolean zVp_d = vp_d(str);
        if (zVp_d) {
            boolean zVp_a = vp_a(str, str2);
            z = zVp_a;
            if (zVp_a) {
                OnUpdateCheckListener onUpdateCheckListener = this.vp_b;
                if (onUpdateCheckListener != null) {
                    onUpdateCheckListener.onCheckSuccess(str);
                }
                new vp_bs(this.vp_a, this.vp_d, this.vp_b).vp_c();
            } else {
                OnUpdateCheckListener onUpdateCheckListener2 = this.vp_b;
                if (onUpdateCheckListener2 != null) {
                    onUpdateCheckListener2.onCheckFail(16);
                }
            }
        } else {
            OnUpdateCheckListener onUpdateCheckListener3 = this.vp_b;
            if (onUpdateCheckListener3 != null) {
                onUpdateCheckListener3.onCheckFail(15);
            }
        }
        return zVp_d && z;
    }

    public void vp_a() {
        StringBuilder sbAppend = new StringBuilder().append(this.vp_a.getExternalFilesDir(null));
        String str = File.separator;
        vp_e = sbAppend.append(str).append("vpsdk").append(str).toString();
        HashMap map = new HashMap();
        map.put("type", "android");
        map.put("devicetype", String.valueOf(this.vp_d.getDeviceNumber()));
        map.put("testVersion", this.vp_d.getDeviceTestVersion());
        new Thread(new vp_a(map)).start();
    }

    private void vp_a(String str, Map map, boolean z, String str2) throws Throwable {
        com.veepoo.protocol.http.vp_a vp_aVar;
        StringBuilder sbAppend = new StringBuilder().append(this.vp_a.getExternalFilesDir(null));
        String str3 = File.separator;
        vp_e = sbAppend.append(str3).append("vpsdk").append(str3).toString();
        if (z) {
            vp_aVar = vp_aVar;
            com.veepoo.protocol.http.vp_a vp_aVar2 = new com.veepoo.protocol.http.vp_a(true);
        } else if (TextUtils.isEmpty(str2)) {
            vp_aVar = vp_aVar;
            com.veepoo.protocol.http.vp_a vp_aVar3 = new com.veepoo.protocol.http.vp_a();
        } else {
            vp_aVar = vp_aVar;
            com.veepoo.protocol.http.vp_a vp_aVar4 = new com.veepoo.protocol.http.vp_a(str2 + "/api/");
        }
        String strVp_a = vp_aVar.vp_a("Version/GetVersionForSport", map);
        new StringBuilder().append("jsonStr:").append(strVp_a);
        OadFileBean oadFileBeanVp_c = vp_c(strVp_a);
        if (!vp_a(oadFileBeanVp_c)) {
            OnUpdateCheckListener onUpdateCheckListener = this.vp_b;
            if (onUpdateCheckListener != null) {
                onUpdateCheckListener.onCheckFail(13);
            }
            OnGetOadVersionListener onGetOadVersionListener = this.vp_c;
            if (onGetOadVersionListener != null) {
                onGetOadVersionListener.onNetOadInfo(this.vp_d.getDeviceNumber(), this.vp_d.getDeviceVersion(), "", false);
                OadFileBean oadFileBean = new OadFileBean();
                oadFileBean.setDeviceNumber(this.vp_d.getDeviceNumber() + "");
                oadFileBean.setDeviceVersion(this.vp_d.getDeviceVersion());
                this.vp_c.onNetOadDetailInfo(oadFileBean, false);
                return;
            }
            return;
        }
        boolean zVp_c = vp_c(str, oadFileBeanVp_c.getDeviceVersion());
        OnUpdateCheckListener onUpdateCheckListener2 = this.vp_b;
        if (onUpdateCheckListener2 != null) {
            onUpdateCheckListener2.onNetVersionInfo(vp_b(oadFileBeanVp_c.getDeviceNumber()), oadFileBeanVp_c.getDeviceVersion(), oadFileBeanVp_c.getDes());
        }
        OnGetOadVersionListener onGetOadVersionListener2 = this.vp_c;
        if (onGetOadVersionListener2 != null) {
            onGetOadVersionListener2.onNetOadInfo(vp_b(oadFileBeanVp_c.getDeviceNumber()), oadFileBeanVp_c.getDeviceVersion(), oadFileBeanVp_c.getDes(), zVp_c);
            this.vp_c.onNetOadDetailInfo(oadFileBeanVp_c, zVp_c);
        }
        if (!zVp_c || this.vp_b == null) {
            OnUpdateCheckListener onUpdateCheckListener3 = this.vp_b;
            if (onUpdateCheckListener3 != null) {
                onUpdateCheckListener3.onCheckFail(14);
            }
            OnGetOadVersionListener onGetOadVersionListener3 = this.vp_c;
            if (onGetOadVersionListener3 != null) {
                onGetOadVersionListener3.onNetOadInfo(vp_b(oadFileBeanVp_c.getDeviceNumber()), oadFileBeanVp_c.getDeviceVersion(), oadFileBeanVp_c.getDes(), zVp_c);
                this.vp_c.onNetOadDetailInfo(oadFileBeanVp_c, zVp_c);
                return;
            }
            return;
        }
        if (!this.vp_d.isAutoDownload()) {
            OnUpdateCheckListener onUpdateCheckListener4 = this.vp_b;
            if (onUpdateCheckListener4 != null) {
                onUpdateCheckListener4.onRemoteOadFileGet(oadFileBeanVp_c);
                this.vp_b.onCheckSuccess(oadFileBeanVp_c.getDownUrl());
                return;
            }
            return;
        }
        String str4 = vp_e + oadFileBeanVp_c.getDeviceNumber() + "_" + oadFileBeanVp_c.getDeviceVersion() + "." + vp_a(oadFileBeanVp_c.getDownUrl());
        new StringBuilder().append("保存路径：").append(str4);
        String md5 = oadFileBeanVp_c.getMd5();
        if (vp_b(str4, md5)) {
            return;
        }
        vp_a(oadFileBeanVp_c.getDownUrl(), str4, md5);
    }

    private String vp_a(String str) {
        String[] strArrSplit;
        return (str == null || str.length() <= 0 || (strArrSplit = str.split("\\.")) == null || strArrSplit.length <= 1) ? "zip" : strArrSplit[strArrSplit.length - 1];
    }

    private boolean vp_a(String str, String str2) {
        return com.veepoo.protocol.util.vp_x.vp_a(str).toLowerCase().equals(str2.toLowerCase());
    }

    private boolean vp_a(OadFileBean oadFileBean) {
        return vp_e(oadFileBean.getDeviceNumber()) && vp_e(oadFileBean.getDownUrl()) && vp_e(oadFileBean.getDeviceVersion()) && vp_e(oadFileBean.getSize()) && vp_e(oadFileBean.getMd5());
    }
}
