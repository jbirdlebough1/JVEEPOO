package com.inuker.bluetooth.library.jieli.dial;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/ITestTask.class */
public interface ITestTask extends INextTask {
    void startTest();

    void stopTest();

    void setINextTask(INextTask iNextTask);

    void onTestLog(String str);

    void setOnTestLogCallback(OnTestLogCallback onTestLogCallback);

    String getName();
}
