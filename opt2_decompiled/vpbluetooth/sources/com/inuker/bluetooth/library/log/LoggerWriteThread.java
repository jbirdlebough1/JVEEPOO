package com.inuker.bluetooth.library.log;

import android.os.SystemClock;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/LoggerWriteThread.class */
public class LoggerWriteThread extends Thread {
    private final LinkedBlockingQueue<LoggerWriteTask> mQueue = new LinkedBlockingQueue<>();
    private volatile boolean isStartWrite = false;
    private volatile boolean isThreadWaiting = false;
    private LoggerWriteTask currentLog = null;
    private volatile int retryNum = 0;
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss||SSS", Locale.CHINA);

    public void addWriteLogTask(LogType logType, String log) {
        LoggerWriteTask task = new LoggerWriteTask(logType, log);
        boolean ret = addWriteTask(task);
        if (!ret) {
            BluetoothLog.e("---------------日志任务添加失败---------------");
        }
    }

    private boolean addWriteTask(LoggerWriteTask loggerWriteTask) {
        boolean ret = false;
        if (this.isStartWrite) {
            try {
                this.mQueue.put(loggerWriteTask);
                ret = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ret && this.isThreadWaiting) {
                this.isThreadWaiting = false;
                synchronized (this.mQueue) {
                    this.mQueue.notify();
                }
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isStartWrite = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isStartWrite = false;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        synchronized (this.mQueue) {
            while (this.isStartWrite) {
                this.isThreadWaiting = false;
                if (this.mQueue.isEmpty()) {
                    this.isThreadWaiting = true;
                    BluetoothLog.e("日志队列为空，等待新的日志");
                    try {
                        this.mQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.currentLog = this.mQueue.peek();
                    if (this.currentLog != null) {
                        boolean ret = writeLog(this.currentLog);
                        if (!ret) {
                            this.retryNum++;
                            if (this.retryNum < 3) {
                                SystemClock.sleep(20L);
                            }
                        } else {
                            this.retryNum = 0;
                            this.mQueue.poll();
                        }
                    }
                }
            }
        }
        this.isThreadWaiting = false;
        this.isStartWrite = false;
        this.mQueue.clear();
    }

    private boolean writeLog(LoggerWriteTask task) {
        BluetoothLog.d(task.getLog());
        boolean ret = true;
        if (task.getLogType() == LogType.BLE_CONNECT) {
            checkConnectLog();
        }
        File dir = new File(VPLocalLogger.getInstance().getLogDirPath());
        if (!dir.exists()) {
            boolean mkRet = dir.mkdirs();
            BluetoothLog.e("创建日志文件夹：" + mkRet);
        }
        File file = new File(VPLocalLogger.getInstance().getFilePathByLogType(task.getLogType()));
        try {
            long current = System.currentTimeMillis();
            String time = sdf.format(new Date(current));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            String timeLog = time + ":-->" + task.getLog();
            pw.println(timeLog);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    private void checkConnectLog() {
        File file = new File(VPLocalLogger.getInstance().getBleConnectFilePath());
        if (file.length() > 5242880) {
            boolean ret = file.delete();
            BluetoothLog.e("---------------蓝牙连接日志超过5M->删除--------------- ret:" + ret);
        }
    }
}
