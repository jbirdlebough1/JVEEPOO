package com.inuker.bluetooth.library.connect.options;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/options/GeneralOption.class */
public class GeneralOption implements Parcelable {
    private int maxRetry;
    private int timeoutInMillis;
    public static final Parcelable.Creator<GeneralOption> CREATOR = new Parcelable.Creator<GeneralOption>() { // from class: com.inuker.bluetooth.library.connect.options.GeneralOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeneralOption createFromParcel(Parcel in) {
            return new GeneralOption(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeneralOption[] newArray(int size) {
            return new GeneralOption[size];
        }
    };

    public int getMaxRetry() {
        return this.maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = Math.max(maxRetry, 0);
    }

    public int getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    public void setTimeoutInMillis(int timeoutInMillis) {
        this.timeoutInMillis = Math.max(timeoutInMillis, 1000);
    }

    protected GeneralOption(Parcel in) {
        this.maxRetry = in.readInt();
        this.timeoutInMillis = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.maxRetry);
        dest.writeInt(this.timeoutInMillis);
    }
}
