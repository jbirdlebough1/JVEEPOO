package com.inuker.bluetooth.library;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothService.class */
public interface IBluetoothService extends IInterface {
    public static final String DESCRIPTOR = "com.inuker.bluetooth.library.IBluetoothService";

    void callBluetoothApi(int i, Bundle bundle, IResponse iResponse) throws RemoteException;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothService$Default.class */
    public static class Default implements IBluetoothService {
        @Override // com.inuker.bluetooth.library.IBluetoothService
        public void callBluetoothApi(int code, Bundle args, IResponse response) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothService$Stub.class */
    public static abstract class Stub extends Binder implements IBluetoothService {
        static final int TRANSACTION_callBluetoothApi = 1;

        public Stub() {
            attachInterface(this, IBluetoothService.DESCRIPTOR);
        }

        public static IBluetoothService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothService.DESCRIPTOR);
            if (iin != null && (iin instanceof IBluetoothService)) {
                return (IBluetoothService) iin;
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
                data.enforceInterface(IBluetoothService.DESCRIPTOR);
            }
            switch (code) {
                case 1598968902:
                    reply.writeString(IBluetoothService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            int _arg0 = data.readInt();
                            Bundle _arg1 = (Bundle) _Parcel.readTypedObject(data, Bundle.CREATOR);
                            IResponse _arg2 = IResponse.Stub.asInterface(data.readStrongBinder());
                            callBluetoothApi(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            _Parcel.writeTypedObject(reply, _arg1, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothService$Stub$Proxy.class */
        private static class Proxy implements IBluetoothService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothService.DESCRIPTOR;
            }

            @Override // com.inuker.bluetooth.library.IBluetoothService
            public void callBluetoothApi(int code, Bundle args, IResponse response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothService.DESCRIPTOR);
                    _data.writeInt(code);
                    _Parcel.writeTypedObject(_data, args, 0);
                    _data.writeStrongInterface(response);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (0 != _reply.readInt()) {
                        args.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/IBluetoothService$_Parcel.class */
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
