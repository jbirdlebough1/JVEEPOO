package com.inuker.bluetooth.library.jieli.dial;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/TestError.class */
public class TestError {
    public static final TestError SUCCESS = new TestError(0, "测试完成");
    public static final TestError FAILED = new TestError(-1, "测试失败");
    public int code;
    public String msg;

    public TestError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String toString() {
        return "TestError{code=" + this.code + ", msg='" + this.msg + "'}";
    }
}
