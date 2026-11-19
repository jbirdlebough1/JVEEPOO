package vpno.nordicsemi.android.dfu.internal.exception;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/exception/RemoteDfuException.class */
public class RemoteDfuException extends Exception {
    private static final long serialVersionUID = -6901728550661937942L;
    private final int mState;

    public RemoteDfuException(String str, int i) {
        super(str);
        this.mState = i;
    }

    public int getErrorNumber() {
        return this.mState;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage() + " (Error " + this.mState + ")";
    }
}
