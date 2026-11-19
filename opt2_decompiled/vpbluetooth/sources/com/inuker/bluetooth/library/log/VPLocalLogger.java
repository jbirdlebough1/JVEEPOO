package com.inuker.bluetooth.library.log;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/VPLocalLogger.class */
public class VPLocalLogger {
    private String bleWriteFilePath;
    private String bleChangeFilePath;
    private String bleConnectFilePath;
    private String jlOTALogFilePath;
    private String jlWatchLogFilePath;
    private LoggerWriteThread loggerWriteThread;
    public static final String LOG_ROOT_DIR = "VpSDK";
    public static final String TAG = VPLocalLogger.class.getSimpleName();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
    private String logDirPath = null;
    private boolean isOpenWriteLog = false;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/VPLocalLogger$HBLoggerHolder.class */
    private static class HBLoggerHolder {
        private static final VPLocalLogger INSTANCE = new VPLocalLogger();

        private HBLoggerHolder() {
        }
    }

    public static VPLocalLogger getInstance() {
        return HBLoggerHolder.INSTANCE;
    }

    private boolean checkInit() {
        return this.logDirPath != null;
    }

    public static void startMonitor(Context context) {
        getInstance().init(context);
        getInstance().setOpenWriteLog(true);
        getInstance().start();
    }

    public static void stopMonitor() {
        getInstance().stop();
    }

    public void init(Context context) {
        this.logDirPath = context.getExternalFilesDir(null) + File.separator + LOG_ROOT_DIR;
        this.bleWriteFilePath = this.logDirPath + File.separator + LogType.BLE_WRITE.getFileName();
        this.bleChangeFilePath = this.logDirPath + File.separator + LogType.BLE_CHANGE.getFileName();
        this.bleConnectFilePath = this.logDirPath + File.separator + LogType.BLE_CONNECT.getFileName();
        this.jlWatchLogFilePath = context.getExternalFilesDir(null) + File.separator + "logcat" + File.separator;
        this.jlOTALogFilePath = context.getExternalFilesDir(null) + File.separator + context.getPackageName() + File.separator + "logcat" + File.separator;
        this.loggerWriteThread = new LoggerWriteThread();
    }

    public void setOpenWriteLog(boolean isOpenWriteLog) {
        this.isOpenWriteLog = isOpenWriteLog;
    }

    public void start() {
        if (this.loggerWriteThread == null) {
            this.loggerWriteThread = new LoggerWriteThread();
        }
        if (!this.loggerWriteThread.isAlive()) {
            this.loggerWriteThread.setName("VpSDK-Logger");
            BluetoothLog.d("开始日志写入线程.....");
            this.loggerWriteThread.start();
        }
    }

    public void stop() {
        if (this.loggerWriteThread != null) {
            if (this.loggerWriteThread.isAlive()) {
                this.loggerWriteThread.interrupt();
            }
            this.loggerWriteThread = null;
        }
    }

    public static void bleWriteLog(String log) {
        getInstance().writeLog(LogType.BLE_WRITE, log);
    }

    public static void bleChangeLog(String log) {
        getInstance().writeLog(LogType.BLE_CHANGE, log);
    }

    public static void bleConnectLog(String log) {
        getInstance().writeLog(LogType.BLE_CONNECT, log);
    }

    private void writeLog(LogType type, String log) {
        if (this.loggerWriteThread != null && this.isOpenWriteLog) {
            this.loggerWriteThread.addWriteLogTask(type, log);
        }
    }

    public static void onCharacteristicChanged(byte[] data) {
        bleChangeLog(ConvertHelper.byte2HexForShow(data));
    }

    public String getLogDirPath() {
        return this.logDirPath;
    }

    public String getBleWriteFilePath() {
        return this.bleWriteFilePath;
    }

    public String getBleChangeFilePath() {
        return this.bleChangeFilePath;
    }

    public String getBleConnectFilePath() {
        return this.bleConnectFilePath;
    }

    public void clearBLEWriteLog() {
        clearLog(LogType.BLE_WRITE);
    }

    public void clearBLEChangeLog() {
        clearLog(LogType.BLE_CHANGE);
    }

    public void clearBLEConnectLog() {
        clearLog(LogType.BLE_CONNECT);
    }

    public static void clearHBLogger(boolean isNeedClearConnectLog) {
        getInstance().clearBLEChangeLog();
        getInstance().clearBLEWriteLog();
        if (isNeedClearConnectLog) {
            getInstance().clearBLEConnectLog();
        }
    }

    private void clearLog(LogType logType) {
        if (checkInit()) {
            String logPath = getFilePathByLogType(logType);
            File dir = new File(this.logDirPath);
            if (!dir.exists()) {
                return;
            }
            File file = new File(logPath);
            if (!file.exists()) {
                return;
            }
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
                printWriter.println("");
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilePathByLogType(LogType logType) {
        switch (logType) {
            case BLE_WRITE:
                return getBleWriteFilePath();
            case BLE_CHANGE:
                return getBleChangeFilePath();
            case BLE_CONNECT:
                return getBleConnectFilePath();
            default:
                return "";
        }
    }

    public void shareLogFile(Activity activity, @NonNull String authority) {
        File[] files;
        File logDir = new File(this.logDirPath);
        if (logDir.exists() && (files = logDir.listFiles()) != null && files.length > 0) {
            for (File file : files) {
                String name = file.getName();
                if (name.contains(JLOTAManager.OTA_ZIP_SUFFIX)) {
                    file.delete();
                }
            }
        }
        ArrayList<String> listFileUri = new ArrayList<>();
        File fileChange = new File(this.bleWriteFilePath);
        if (fileChange.exists()) {
            listFileUri.add(this.bleWriteFilePath);
        }
        File fileWrite = new File(this.bleChangeFilePath);
        if (fileWrite.exists()) {
            listFileUri.add(this.bleChangeFilePath);
        }
        File bleConnect = new File(this.bleConnectFilePath);
        if (bleConnect.exists()) {
            listFileUri.add(this.bleConnectFilePath);
        }
        if (listFileUri.isEmpty()) {
            return;
        }
        int size = listFileUri.size();
        String[] zipFileStr = new String[size];
        for (int i = 0; i < listFileUri.size(); i++) {
            zipFileStr[i] = listFileUri.get(i);
        }
        String zipFileName = this.logDirPath + "log_" + getSystemTimeSimple() + JLOTAManager.OTA_ZIP_SUFFIX;
        BluetoothLog.i("zipFileName：" + zipFileName);
        zip(zipFileStr, zipFileName);
        File zipFile = new File(zipFileName);
        if (!zipFile.exists()) {
            return;
        }
        Uri uri = getUri(activity, zipFileName, authority);
        Intent share_intent = new Intent();
        share_intent.setAction("android.intent.action.SEND");
        share_intent.setType("application/zip");
        share_intent.putExtra("android.intent.extra.STREAM", uri);
        activity.startActivity(Intent.createChooser(share_intent, "分享提醒日志"));
    }

    public void shareJLOTALog(Activity activity, @NonNull String authority) {
        shareLogFile(activity, this.jlOTALogFilePath, "OTA", authority);
    }

    public void shareJLWatchLog(Activity activity, @NonNull String authority) {
        shareLogFile(activity, this.jlWatchLogFilePath, "Watch", authority);
    }

    private void shareLogFile(Activity activity, String logFileDirPath, String zipSuffix, @NonNull String authority) {
        File logDir = new File(logFileDirPath);
        if (logDir.exists()) {
            File[] files = logDir.listFiles();
            ArrayList<String> listFileUri = new ArrayList<>();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = file.getName();
                    if (name.contains(JLOTAManager.OTA_ZIP_SUFFIX)) {
                        file.delete();
                    }
                    if (name.endsWith(".txt") && !listFileUri.contains(file.getAbsolutePath())) {
                        listFileUri.add(file.getAbsolutePath());
                    }
                }
            }
            if (listFileUri.isEmpty()) {
                return;
            }
            int size = listFileUri.size();
            String[] zipFileStr = new String[size];
            for (int i = 0; i < listFileUri.size(); i++) {
                zipFileStr[i] = listFileUri.get(i);
            }
            String zipFileName = logFileDirPath + zipSuffix + "_" + getSystemTimeSimple() + JLOTAManager.OTA_ZIP_SUFFIX;
            BluetoothLog.i("zipFileName：" + zipFileName);
            zip(zipFileStr, zipFileName);
            File zipFile = new File(zipFileName);
            if (!zipFile.exists()) {
                return;
            }
            Uri uri = getUri(activity, zipFileName, authority);
            Intent share_intent = new Intent();
            share_intent.setAction("android.intent.action.SEND");
            share_intent.setType("application/zip");
            share_intent.putExtra("android.intent.extra.STREAM", uri);
            activity.startActivity(Intent.createChooser(share_intent, "分享提醒日志"));
            return;
        }
        BluetoothLog.e(logFileDirPath + ": 没有日志文件");
    }

    public void zip(String[] _files, String zipFileName) {
        try {
            FileOutputStream dest = new FileOutputStream(zipFileName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte[] data = new byte[1024];
            for (String file : _files) {
                FileInputStream fi = new FileInputStream(file);
                BufferedInputStream origin = new BufferedInputStream(fi, 1024);
                ZipEntry entry = new ZipEntry(file.substring(file.lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                while (true) {
                    int count = origin.read(data, 0, 1024);
                    if (count != -1) {
                        out.write(data, 0, count);
                    }
                }
                origin.close();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Uri getUri(Context context, String filePath, @NonNull String authority) {
        Uri dfuFileUri;
        if (Build.VERSION.SDK_INT >= 24) {
            dfuFileUri = FileProvider.getUriForFile(context, authority, new File(filePath));
        } else {
            dfuFileUri = Uri.fromFile(new File(filePath));
        }
        return dfuFileUri;
    }

    public static String getSystemTimeSimple() {
        return sdf.format(new Date());
    }
}
