package com.inuker.bluetooth.library.connect.options;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/options/BleConnectOption.class */
public class BleConnectOption extends GeneralOption {
    public static final Parcelable.Creator<BleConnectOption> CREATOR = new Parcelable.Creator<BleConnectOption>() { // from class: com.inuker.bluetooth.library.connect.options.BleConnectOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleConnectOption createFromParcel(Parcel in) {
            return new BleConnectOption(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleConnectOption[] newArray(int size) {
            return new BleConnectOption[size];
        }
    };

    protected BleConnectOption(Parcel in) {
        super(in);
    }

    @Override // com.inuker.bluetooth.library.connect.options.GeneralOption, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.inuker.bluetooth.library.connect.options.GeneralOption, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
    }
}
