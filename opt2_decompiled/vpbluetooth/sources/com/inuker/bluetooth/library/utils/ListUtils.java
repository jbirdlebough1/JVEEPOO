package com.inuker.bluetooth.library.utils;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/ListUtils.class */
public class ListUtils {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() <= 0;
    }

    public static <E> List<E> getEmptyList() {
        return new ArrayList();
    }
}
