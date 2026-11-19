package com.inuker.bluetooth.library.utils;

import java.util.Random;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/RandomUtils.class */
public class RandomUtils {
    private static Random mRandom;

    public static double randFloat() {
        if (mRandom == null) {
            mRandom = new Random();
            mRandom.setSeed(System.currentTimeMillis());
        }
        return mRandom.nextDouble();
    }
}
