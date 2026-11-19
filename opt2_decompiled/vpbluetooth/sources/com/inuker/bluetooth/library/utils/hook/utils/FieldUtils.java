package com.inuker.bluetooth.library.utils.hook.utils;

import com.inuker.bluetooth.library.utils.StringUtils;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/utils/FieldUtils.class */
public class FieldUtils {
    public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess) {
        Validate.isTrue(cls != null, "The class must not be null", new Object[0]);
        Validate.isTrue(StringUtils.isNotBlank(fieldName), "The field name must not be blank/empty", new Object[0]);
        try {
            Field field = cls.getDeclaredField(fieldName);
            if (!MemberUtils.isAccessible(field)) {
                if (forceAccess) {
                    field.setAccessible(true);
                } else {
                    return null;
                }
            }
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
