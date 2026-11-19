package vpno.nordicsemi.android.dfu.internal.exception;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/exception/UnknownResponseException.class */
public class UnknownResponseException extends Exception {
    private static final long serialVersionUID = -8716125467309979289L;
    private static final char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final byte[] mResponse;
    private final int mExpectedReturnCode;
    private final int mExpectedOpCode;

    public UnknownResponseException(String str, byte[] bArr, int i, int i2) {
        super(str);
        this.mResponse = bArr == null ? new byte[0] : bArr;
        this.mExpectedReturnCode = i;
        this.mExpectedOpCode = i2;
    }

    public static String bytesToHex(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= i || i2 <= 0) {
            return "";
        }
        int iMin = Math.min(i2, bArr.length - i);
        char[] cArr = new char[iMin * 2];
        for (int i3 = 0; i3 < iMin; i3++) {
            byte b = bArr[i + i3];
            int i4 = i3 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i4] = cArr2[(b & 255) >>> 4];
            cArr[i4 + 1] = cArr2[b & 15];
        }
        return "0x" + new String(cArr);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        byte[] bArr = this.mResponse;
        return String.format("%s (response: %s, expected: 0x%02X%02X..)", super.getMessage(), bytesToHex(bArr, 0, bArr.length), Integer.valueOf(this.mExpectedReturnCode), Integer.valueOf(this.mExpectedOpCode));
    }
}
