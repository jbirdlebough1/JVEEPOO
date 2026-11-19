package com.inuker.bluetooth.library.utils.hook.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/utils/MemberUtils.class */
public class MemberUtils {
    static boolean isAccessible(Member m) {
        return (m == null || !Modifier.isPublic(m.getModifiers()) || m.isSynthetic()) ? false : true;
    }
}
