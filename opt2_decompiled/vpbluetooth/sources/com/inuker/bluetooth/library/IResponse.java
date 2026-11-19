package com.inuker.bluetooth.library;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IResponse.class */
public interface IResponse extends IInterface {
    public static final String DESCRIPTOR = "com.inuker.bluetooth.library.IResponse";

    void onResponse(int i, Bundle bundle) throws RemoteException;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IResponse$Default.class */
    public static class Default implements IResponse {
        @Override // com.inuker.bluetooth.library.IResponse
        public void onResponse(int code, Bundle data) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IResponse$Stub.class */
    public static abstract class Stub extends Binder implements IResponse {
        static final int TRANSACTION_onResponse = 1;

        public Stub() {
            attachInterface(this, IResponse.DESCRIPTOR);
        }

        public static IResponse asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IResponse.DESCRIPTOR);
            if (iin != null && (iin instanceof IResponse)) {
                return (IResponse) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IResponse.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IResponse.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            int _arg0 = data.readInt();
                            Bundle _arg1 = new Bundle();
                            onResponse(_arg0, _arg1);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _arg1, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IResponse$Stub$Proxy.class */
        private static class Proxy implements IResponse {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IResponse.DESCRIPTOR;
            }

            @Override // com.inuker.bluetooth.library.IResponse
            public void onResponse(int code, Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResponse.DESCRIPTOR);
                    _data.writeInt(code);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (0 != _reply.readInt()) {
                        data.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IResponse$_Parcel.class */
    public static class _Parcel {
        private static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> c) {
            if (parcel.readInt() != 0) {
                return c.createFromParcel(parcel);
            }
            return null;
        }

        private static <T extends Parcelable> void writeTypedObject(Parcel parcel, T value, int parcelableFlags) {
            if (value != null) {
                parcel.writeInt(1);
                value.writeToParcel(parcel, parcelableFlags);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
