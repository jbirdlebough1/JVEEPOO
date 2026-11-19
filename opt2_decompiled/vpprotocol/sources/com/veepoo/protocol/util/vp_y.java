package com.veepoo.protocol.util;

import android.content.Context;
import android.text.TextUtils;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.shareprence.SpUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_y.class */
public class vp_y {
    private static Context vp_a;
    private static volatile vp_y vp_b;

    private void vp_c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SpUtil.saveString(vp_a, "multialarm", str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.vp_y] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public List vp_b(String str) {
        ?? Vp_b = this;
        ?? r6 = 0;
        try {
            Vp_b = Vp_b.vp_b(Vp_b.vp_a(), str);
            r6 = Vp_b;
        } catch (JSONException unused) {
            Vp_b.printStackTrace();
        }
        boolean z = r6 == true ? 1 : 0;
        ?? r7 = r6;
        if (!z) {
            r7 = Collections.EMPTY_LIST;
        }
        return r7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.vp_y] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.veepoo.protocol.util.vp_y] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    public void vp_a(String str, int i) {
        ?? Vp_a = this;
        ?? r9 = 0;
        try {
            Vp_a = Vp_a.vp_a(Vp_a.vp_a(), str, i);
            r9 = Vp_a;
        } catch (JSONException unused) {
            Vp_a.printStackTrace();
        }
        vp_c(r9);
    }

    private List vp_b(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            if (str2.equals(jSONObject.getString("BluetoothAddress"))) {
                arrayList.add(vp_a(jSONObject));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void vp_a(String str) {
        try {
            String strVp_a = vp_a(vp_a(), str);
            new StringBuilder().append("删除").append(str).append("后的新数据：").append(strVp_a);
            vp_c(strVp_a);
        } catch (JSONException unused) {
            printStackTrace();
        }
    }

    private String vp_a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        JSONArray jSONArray = new JSONArray(str);
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            if (!jSONObject.getString("BluetoothAddress").equals(str2)) {
                jSONArray2.put(jSONObject);
            }
        }
        return jSONArray2.toString();
    }

    private String vp_a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str3 = str2 + "-" + i;
        int i2 = 0;
        boolean z = false;
        JSONArray jSONArray = new JSONArray(str);
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            if (((JSONObject) jSONArray.get(i3)).getString("MAFlag").equals(str3)) {
                z = true;
                i2 = i3;
                break;
            }
        }
        if (z) {
            jSONArray.remove(i2);
        }
        return jSONArray.toString();
    }

    private String vp_a(String str, Alarm2Setting alarm2Setting) throws JSONException {
        if (alarm2Setting == null || TextUtils.isEmpty(alarm2Setting.getBluetoothAddress())) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(vp_a(alarm2Setting, (JSONObject) null));
            return jSONArray.toString();
        }
        boolean z = false;
        int i = 0;
        String str2 = alarm2Setting.getBluetoothAddress() + "-" + alarm2Setting.getAlarmId();
        JSONArray jSONArray2 = new JSONArray(str);
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            if (((JSONObject) jSONArray2.get(i2)).getString("MAFlag").equals(str2)) {
                z = true;
                i = i2;
                break;
            }
        }
        if (z) {
            vp_a(alarm2Setting, (JSONObject) jSONArray2.get(i));
        } else {
            jSONArray2.put(vp_a(alarm2Setting, (JSONObject) null));
        }
        return jSONArray2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.util.vp_y>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static vp_y vp_a(Context context) {
        if (vp_b == null) {
            ?? r0 = vp_y.class;
            synchronized (r0) {
                try {
                    if (vp_b == null) {
                        vp_b = new vp_y();
                        vp_a = context.getApplicationContext();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_b;
    }

    private JSONObject vp_a(Alarm2Setting alarm2Setting, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = jSONObject;
            JSONObject jSONObject2 = new JSONObject();
        }
        JSONObject jSONObject3 = jSONObject;
        jSONObject.put("MAFlag", alarm2Setting.getBluetoothAddress() + "-" + alarm2Setting.getAlarmId());
        jSONObject.put("BluetoothAddress", alarm2Setting.getBluetoothAddress());
        jSONObject.put("alarmId", alarm2Setting.getAlarmId());
        jSONObject.put("alarmHour", alarm2Setting.getAlarmHour());
        jSONObject.put("alarmMinute", alarm2Setting.getAlarmMinute());
        jSONObject.put("repeatStatus", alarm2Setting.getRepeatStatus());
        jSONObject.put("scene", alarm2Setting.getScene());
        jSONObject.put("unRepeatDate", alarm2Setting.getUnRepeatDate());
        jSONObject3.put("isOpen", alarm2Setting.isOpen());
        return jSONObject3;
    }

    private Alarm2Setting vp_a(JSONObject jSONObject) {
        Alarm2Setting alarm2Setting = new Alarm2Setting();
        alarm2Setting.setBluetoothAddress(jSONObject.getString("BluetoothAddress"));
        alarm2Setting.setMAFlag(jSONObject.getString("MAFlag"));
        alarm2Setting.setAlarmId(jSONObject.getInt("alarmId"));
        alarm2Setting.setAlarmHour(jSONObject.getInt("alarmHour"));
        alarm2Setting.setAlarmMinute(jSONObject.getInt("alarmMinute"));
        alarm2Setting.setRepeatStatus(jSONObject.getString("repeatStatus"));
        alarm2Setting.setScene(jSONObject.getInt("scene"));
        alarm2Setting.setUnRepeatDate(jSONObject.getString("unRepeatDate"));
        alarm2Setting.setOpen(jSONObject.getBoolean("isOpen"));
        return alarm2Setting;
    }

    private String vp_a() {
        return SpUtil.getString(vp_a, "multialarm", "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.vp_y] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.veepoo.protocol.util.vp_y] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    public void vp_a(Alarm2Setting alarm2Setting) {
        ?? Vp_a = this;
        ?? r7 = 0;
        try {
            Vp_a = Vp_a.vp_a(Vp_a.vp_a(), alarm2Setting);
            r7 = Vp_a;
        } catch (JSONException unused) {
            Vp_a.printStackTrace();
        }
        vp_c(r7);
    }
}
