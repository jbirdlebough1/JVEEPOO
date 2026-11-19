package com.inuker.bluetooth.library.jieli.dial;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/AppUtil.class */
public class AppUtil {
    public static boolean isHasLocationPermission(Context context) {
        return isHasPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean isHasStoragePermission(Context context) {
        return isHasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") && isHasPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
    }

    public static boolean isHasPermission(Context context, String permission) {
        return context != null;
    }

    public static String createFilePath(Context context, String... dirNames) {
        File file;
        if (context == null || dirNames == null || dirNames.length == 0 || (file = context.getExternalFilesDir(null)) == null || !file.exists()) {
            return null;
        }
        StringBuilder filePath = new StringBuilder(file.getPath());
        if (filePath.toString().endsWith("/")) {
            filePath = new StringBuilder(filePath.substring(0, filePath.lastIndexOf("/")));
        }
        for (String dirName : dirNames) {
            filePath.append("/").append(dirName);
            File file2 = new File(filePath.toString());
            if ((!file2.exists() || file2.isFile()) && !file2.mkdir()) {
                Log.w("jieli", "create dir failed. filePath = " + ((Object) filePath));
                break;
            }
        }
        return filePath.toString();
    }

    public static String obtainUpdateFilePath(String dirPath, String suffix) {
        if (null == dirPath) {
            return null;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return null;
        }
        if (dir.isFile()) {
            if (dirPath.endsWith(suffix)) {
                return dirPath;
            }
            return null;
        }
        if (dir.isDirectory()) {
            String filePath = null;
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    filePath = obtainUpdateFilePath(file.getPath(), suffix);
                    if (filePath != null) {
                        break;
                    }
                }
            }
            return filePath;
        }
        return null;
    }

    public static String getDeviceName(BluetoothDevice device) {
        if (null == device) {
            return null;
        }
        String name = device.getName();
        if (null == name) {
            name = device.getAddress();
        }
        return name;
    }

    public static String getFileNameByPath(String filePath) {
        if (filePath == null) {
            return null;
        }
        int index = filePath.lastIndexOf("/");
        if (index > -1) {
            return filePath.substring(index + 1);
        }
        return filePath;
    }

    public static void copyAssets(Context context, String oldPath, String newPath) {
        try {
            String[] fileNames = context.getAssets().list(oldPath);
            if (fileNames.length > 0) {
                File file = new File(newPath);
                if (!file.exists()) {
                    boolean ret = file.mkdirs();
                    if (!ret) {
                        return;
                    }
                }
                for (String fileName : fileNames) {
                    copyAssets(context, oldPath + File.separator + fileName, newPath + File.separator + fileName);
                }
            } else {
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                while (true) {
                    int byteCount = is.read(buffer);
                    if (byteCount == -1) {
                        break;
                    } else {
                        fos.write(buffer, 0, byteCount);
                    }
                }
                fos.flush();
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
