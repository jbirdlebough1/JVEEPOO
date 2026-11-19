package com.veepoo.protocol.util;

import android.util.Log;
import com.veepoo.protocol.model.datas.UserDateInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_ac.class */
public abstract class vp_ac {
    public static UserDateInfo vp_a(String str) {
        UserDateInfo userDateInfo = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(URLDecoder.decode(str, "UTF-8").getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            userDateInfo = (UserDateInfo) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception unused) {
        }
        return userDateInfo;
    }

    public static String vp_a(UserDateInfo userDateInfo) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(userDateInfo);
        String strEncode = URLEncoder.encode(byteArrayOutputStream.toString("ISO-8859-1"), "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        Log.d("serial", "serialize str =" + strEncode);
        return strEncode;
    }
}
