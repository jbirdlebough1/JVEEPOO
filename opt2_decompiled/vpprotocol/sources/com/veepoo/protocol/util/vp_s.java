package com.veepoo.protocol.util;

import android.os.Build;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.ICallBack;
import com.veepoo.protocol.listener.base.IUpdateSdkResponse;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.datas.UserDateInfo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_s.class */
public class vp_s {
    private PwdData vp_a;
    private int vp_b;
    private String vp_c;
    private Map vp_d;
    private String vp_e;
    private ICallBack vp_f;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_s$vp_a.class */
    class vp_a implements IUpdateSdkResponse {
        vp_a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v15, types: [com.veepoo.protocol.listener.base.ICallBack] */
        /* JADX WARN: Type inference failed for: r0v6, types: [com.veepoo.protocol.util.vp_s$vp_a] */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
        @Override // com.veepoo.protocol.listener.base.IUpdateSdkResponse
        public void response(int i, String str) {
            if (200 > i || i > 300) {
                new StringBuilder().append("连接成功，信息上传失败:").append(str);
                return;
            }
            JsonElement jsonElement = null;
            JsonElement jsonElement2 = null;
            JsonElement jsonElement3 = null;
            JsonElement jsonElement4 = null;
            JsonElement jsonElement5 = null;
            JsonElement jsonElement6 = null;
            try {
                JsonObject jsonObject = new JsonParser().parse(str);
                jsonElement = jsonObject.get("expire");
                jsonElement2 = jsonObject.get("basicType");
                jsonElement3 = jsonObject.get("oxygenType");
                jsonElement4 = jsonObject.get("hrvType");
                jsonElement5 = jsonObject.get("femaleType");
                jsonElement6 = jsonObject.get("ecgDisearseType");
                new StringBuilder().append("连接成功，信息上传成功:").append(str).append(",expire=").append(jsonElement);
            } catch (JsonSyntaxException unused) {
            }
            ?? r0 = this;
            UserDateInfo userDateInfo = new UserDateInfo();
            userDateInfo.setDeviceNubmer(vp_s.this.vp_b);
            userDateInfo.setUpdateDate(vp_j.vp_c());
            userDateInfo.setExpireDate(jsonElement.getAsString());
            String asString = jsonElement2.getAsString();
            userDateInfo.setBasicType(asString);
            userDateInfo.setOxygenType(jsonElement3.getAsString());
            userDateInfo.setHrvType(jsonElement4.getAsString());
            userDateInfo.setFemaleType(jsonElement5.getAsString());
            userDateInfo.setEcgDisearseType(jsonElement6.getAsString());
            try {
                String strVp_a = vp_ac.vp_a(userDateInfo);
                r0 = vp_s.this.vp_f;
                r0.saveServerDay(strVp_a);
            } catch (IOException unused2) {
                r0.printStackTrace();
            }
            if (asString == null || asString.equals("0")) {
                vp_s.this.vp_f.disConnect();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_s$vp_b.class */
    class vp_b implements com.veepoo.protocol.http.vp_a.InterfaceC0001vp_a {
        final /* synthetic */ IUpdateSdkResponse vp_a;
        final /* synthetic */ StringBuffer vp_b;

        vp_b(IUpdateSdkResponse iUpdateSdkResponse, StringBuffer stringBuffer) {
            this.vp_a = iUpdateSdkResponse;
            this.vp_b = stringBuffer;
        }

        @Override // com.veepoo.protocol.http.vp_a.InterfaceC0001vp_a
        public void vp_a(int i, String str) {
            this.vp_a.response(i, str);
            vp_s.this.vp_f.saveAllInfo(this.vp_b.toString());
        }
    }

    public vp_s(PwdData pwdData, String str, Map map, String str2, ICallBack iCallBack) {
        this.vp_a = pwdData;
        this.vp_b = pwdData.getDeviceNumber();
        this.vp_c = str;
        this.vp_d = map;
        this.vp_e = str2;
        this.vp_f = iCallBack;
    }

    private Map vp_a(PwdData pwdData, String str) {
        HashMap map = new HashMap();
        map.put("DeviceNumber", pwdData.getDeviceNumber() + "");
        map.put("DeviceTestVersion", pwdData.getDeviceTestVersion());
        map.put("Mac", str);
        return map;
    }

    public void vp_c() throws Throwable {
        UserDateInfo userDateInfoVp_a;
        vp_a vp_aVar = new vp_a();
        if (!this.vp_e.equals("") && (userDateInfoVp_a = vp_ac.vp_a(this.vp_e)) != null) {
            new StringBuilder().append("连接成功，userDateInfo=").append(userDateInfoVp_a.toString());
            if (userDateInfoVp_a.getUpdateDate().equals(vp_j.vp_c()) && userDateInfoVp_a.getDeviceNubmer() == this.vp_b) {
                return;
            }
        }
        vp_a(this.vp_a, this.vp_c, this.vp_d, vp_aVar);
    }

    public Map vp_b() {
        HashMap map = new HashMap();
        map.put("VPProtocolVersion", VPOperateManager.VPPROTOCOL_VERSION);
        return map;
    }

    private Map vp_a() {
        HashMap map = new HashMap();
        map.put("PhoneModel", Build.MODEL + "");
        map.put("PhoneVersion", Build.VERSION.RELEASE + "");
        map.put("PhoneGoogleSDK", Build.VERSION.SDK_INT + "");
        return map;
    }

    private void vp_a(PwdData pwdData, String str, Map map, IUpdateSdkResponse iUpdateSdkResponse) throws Throwable {
        Map mapVp_b = vp_b();
        Map mapVp_a = vp_a();
        Map mapVp_a2 = vp_a(pwdData, str);
        HashMap map2 = new HashMap();
        map2.putAll(mapVp_b);
        map2.putAll(map);
        map2.putAll(mapVp_a);
        map2.putAll(mapVp_a2);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry entry : map2.entrySet()) {
            stringBuffer.append(((String) entry.getKey()) + "=" + ((String) entry.getValue()) + "&");
        }
        new com.veepoo.protocol.http.vp_a(false).vp_a("System/AddSDKUserInfo", map2, new vp_b(iUpdateSdkResponse, stringBuffer));
    }
}
