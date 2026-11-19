package com.veepoo.protocol.util;

import android.content.Context;
import android.text.TextUtils;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import com.veepoo.protocol.shareprence.SpUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/TextAlarmSp.class */
public class TextAlarmSp {
    private static Context vp_a;
    private static volatile TextAlarmSp vp_b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.util.TextAlarmSp>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static TextAlarmSp getInstance(Context context) {
        if (vp_b == null) {
            ?? r0 = TextAlarmSp.class;
            synchronized (r0) {
                try {
                    if (vp_b == null) {
                        vp_b = new TextAlarmSp();
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
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.TextAlarmSp] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.veepoo.protocol.util.TextAlarmSp] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    public void saveTextAlarmOne(TextAlarm2Setting textAlarm2Setting) {
        ?? Vp_a = this;
        ?? r7 = 0;
        try {
            Vp_a = Vp_a.vp_a(Vp_a.vp_a(), textAlarm2Setting);
            r7 = Vp_a;
        } catch (JSONException unused) {
            Vp_a.printStackTrace();
        }
        vp_a(r7);
    }

    public void saveTextAlarmList(List<TextAlarm2Setting> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<TextAlarm2Setting> it = list.iterator();
        while (it.hasNext()) {
            saveTextAlarmOne(it.next());
        }
    }

    public void changeTextAlarmSwitchState(String str, int i, boolean z) {
        List<TextAlarm2Setting> textAlarmSetting = getTextAlarmSetting(str);
        for (TextAlarm2Setting textAlarm2Setting : textAlarmSetting) {
            if (textAlarm2Setting.getAlarmId() == i) {
                textAlarm2Setting.setOpen(z);
            }
        }
        saveTextAlarmList(textAlarmSetting);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.TextAlarmSp] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<com.veepoo.protocol.model.settings.TextAlarm2Setting>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public List<TextAlarm2Setting> getTextAlarmSetting(String str) {
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

    public TextAlarm2Setting getTextAlarmSettingByID(String str, int i) {
        return vp_b(vp_a(), str, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void deleteAllAlarmMac(String str) {
        try {
            vp_a(vp_a(vp_a(), str));
        } catch (JSONException unused) {
            printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.TextAlarmSp] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.veepoo.protocol.util.TextAlarmSp] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    public void deleteAlarmId(String str, int i) {
        ?? Vp_a = this;
        ?? r9 = 0;
        try {
            Vp_a = Vp_a.vp_a(Vp_a.vp_a(), str, i);
            r9 = Vp_a;
        } catch (JSONException unused) {
            Vp_a.printStackTrace();
        }
        vp_a(r9);
    }

    public void clearTextAlarm() {
        SpUtil.saveString(vp_a, "textAlarm", "");
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.veepoo.protocol.model.settings.TextAlarm2Setting] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    private TextAlarm2Setting vp_b(String str, String str2, int i) {
        ?? IsEmpty = TextUtils.isEmpty(str);
        if (IsEmpty != 0) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                String string = jSONObject.getString("BluetoothAddress");
                int i3 = jSONObject.getInt("alarmId");
                if (str2.equals(string) && i == i3) {
                    IsEmpty = vp_a(jSONObject);
                    return IsEmpty;
                }
            }
            return null;
        } catch (Exception unused) {
            IsEmpty.printStackTrace();
            return null;
        }
    }

    private String vp_a(String str, TextAlarm2Setting textAlarm2Setting) throws JSONException {
        if (textAlarm2Setting == null || TextUtils.isEmpty(textAlarm2Setting.getBluetoothAddress())) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(vp_a(textAlarm2Setting, (JSONObject) null));
            return jSONArray.toString();
        }
        boolean z = false;
        int i = 0;
        String str2 = textAlarm2Setting.getBluetoothAddress() + "-" + textAlarm2Setting.getAlarmId();
        JSONArray jSONArray2 = new JSONArray(str);
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            if (((JSONObject) jSONArray2.get(i2)).getString("MAFlag").equals(str2)) {
                z = true;
                i = i2;
                break;
            }
        }
        if (z) {
            vp_a(textAlarm2Setting, (JSONObject) jSONArray2.get(i));
        } else {
            jSONArray2.put(vp_a(textAlarm2Setting, (JSONObject) null));
        }
        return jSONArray2.toString();
    }

    private JSONObject vp_a(TextAlarm2Setting textAlarm2Setting, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = jSONObject;
            JSONObject jSONObject2 = new JSONObject();
        }
        JSONObject jSONObject3 = jSONObject;
        jSONObject.put("MAFlag", textAlarm2Setting.getBluetoothAddress() + "-" + textAlarm2Setting.getAlarmId());
        jSONObject.put("BluetoothAddress", textAlarm2Setting.getBluetoothAddress());
        jSONObject.put("alarmId", textAlarm2Setting.getAlarmId());
        jSONObject.put("alarmHour", textAlarm2Setting.getAlarmHour());
        jSONObject.put("alarmMinute", textAlarm2Setting.getAlarmMinute());
        jSONObject.put("repeatStatus", textAlarm2Setting.getRepeatStatus());
        jSONObject.put("scene", textAlarm2Setting.getScene());
        jSONObject.put("unRepeatDate", textAlarm2Setting.getUnRepeatDate());
        jSONObject.put("isOpen", textAlarm2Setting.isOpen());
        jSONObject3.put("content", textAlarm2Setting.getContent());
        return jSONObject3;
    }

    private TextAlarm2Setting vp_a(JSONObject jSONObject) {
        TextAlarm2Setting textAlarm2Setting = new TextAlarm2Setting();
        textAlarm2Setting.setBluetoothAddress(jSONObject.getString("BluetoothAddress"));
        textAlarm2Setting.setMAFlag(jSONObject.getString("MAFlag"));
        textAlarm2Setting.setAlarmId(jSONObject.getInt("alarmId"));
        textAlarm2Setting.setAlarmHour(jSONObject.getInt("alarmHour"));
        textAlarm2Setting.setAlarmMinute(jSONObject.getInt("alarmMinute"));
        textAlarm2Setting.setRepeatStatus(jSONObject.getString("repeatStatus"));
        textAlarm2Setting.setScene(jSONObject.getInt("scene"));
        textAlarm2Setting.setUnRepeatDate(jSONObject.getString("unRepeatDate"));
        textAlarm2Setting.setOpen(jSONObject.getBoolean("isOpen"));
        textAlarm2Setting.setContent(jSONObject.getString("content"));
        return textAlarm2Setting;
    }

    private String vp_a() {
        return SpUtil.getString(vp_a, "textAlarm", "");
    }

    private void vp_a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SpUtil.saveString(vp_a, "textAlarm", str);
    }
}
