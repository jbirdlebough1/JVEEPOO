package com.veepoo.protocol.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.veepoo.protocol.listener.oad.OnDownLoadListener;
import com.veepoo.protocol.model.TUiTheme;
import com.veepoo.protocol.model.datas.UIDataServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiServerHttpUtil.class */
public class UiServerHttpUtil {
    public List<TUiTheme> getThemeInfo(UIDataServer uIDataServer, String str, String str2, String str3, String str4) throws Throwable {
        int deviceAialShape = uIDataServer.getDeviceAialShape();
        int binDataType = uIDataServer.getBinDataType();
        int dataCanSendLength = uIDataServer.getDataCanSendLength();
        HashMap map = new HashMap();
        map.put("dialShape", deviceAialShape + "");
        map.put("binProtocol", binDataType + "");
        map.put("maxLength", dataCanSendLength + "");
        map.put("deviceNumber", str);
        map.put("deviceVersion", str2);
        map.put("appType", str3);
        map.put("appVersion", str4);
        String strVp_a = new com.veepoo.protocol.http.vp_a().vp_a("system/getthemes", map);
        return "Error".equalsIgnoreCase(strVp_a) ? new ArrayList() : (List) new Gson().fromJson(strVp_a, new TypeToken<List<TUiTheme>>() { // from class: com.veepoo.protocol.util.UiServerHttpUtil.1
        }.getType());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.http.vp_a, java.lang.Throwable] */
    public void downloadFile(String str, String str2, OnDownLoadListener onDownLoadListener) throws Throwable {
        ?? vp_aVar = new com.veepoo.protocol.http.vp_a(false);
        try {
            vp_aVar.vp_a(str, str2, onDownLoadListener);
        } catch (IOException unused) {
            vp_aVar.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.veepoo.protocol.http.vp_a, java.lang.Throwable] */
    public void downLoadAGpsFile(boolean z, String str, OnDownLoadListener onDownLoadListener) throws Throwable {
        String aGPSFileDownloadUrl = getAGPSFileDownloadUrl(z);
        ?? vp_aVar = new com.veepoo.protocol.http.vp_a(false);
        try {
            vp_aVar.vp_a(aGPSFileDownloadUrl, str, onDownLoadListener);
        } catch (IOException unused) {
            vp_aVar.printStackTrace();
        }
    }

    public String getAGPSFileDownloadUrl(boolean z) {
        return !z ? "https://starcourse.location.io/IhJNMg2dtc/f1e1G7C7E7J7.pgl" : "https://starcourse.rx-networks.cn/IhJNMg2dtc/f1e1G7C7E7J7.pgl";
    }
}
