package vpno.nordicsemi.android.dfu.internal.exception;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/exception/DfuException.class */
public class DfuException extends Exception {
    private static final long serialVersionUID = -6901728550661937942L;
    private final int mError;

    public DfuException(String str, int i) {
        super(str);
        this.mError = i;
    }

    public int getErrorNumber() {
        return this.mError;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage() + " (Error " + (this.mError & (-16385)) + ")";
    }
}
