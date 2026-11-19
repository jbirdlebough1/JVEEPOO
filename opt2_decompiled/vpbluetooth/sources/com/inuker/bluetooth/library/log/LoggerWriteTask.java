package com.inuker.bluetooth.library.log;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/LoggerWriteTask.class */
public class LoggerWriteTask {
    private LogType logType;
    private String log;

    public LoggerWriteTask(LogType logType, String log) {
        this.logType = logType;
        this.log = log;
    }

    public LogType getLogType() {
        return this.logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String toString() {
        return "LoggerWriteTask{logType=" + this.logType + ", log='" + this.log + "'}";
    }
}
