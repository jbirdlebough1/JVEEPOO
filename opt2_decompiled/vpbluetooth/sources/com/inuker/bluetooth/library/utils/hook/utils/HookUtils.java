package com.inuker.bluetooth.library.utils.hook.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/utils/HookUtils.class */
public class HookUtils {
    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        return MethodUtils.getAccessibleMethod(clazz, name, parameterTypes);
    }

    public static Field getField(Class<?> clazz, String name) {
        if (clazz != null) {
            return FieldUtils.getDeclaredField(clazz, name, true);
        }
        return null;
    }

    public static <T> T getValue(Field field) {
        return (T) getValue(field, null);
    }

    public static <T> T getValue(Field field, Object obj) {
        if (field != null) {
            try {
                return (T) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static <T> T invoke(Method method, Object obj, Object... objArr) {
        try {
            return (T) method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
