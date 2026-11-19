package vpno.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuServiceProvider.class */
class DfuServiceProvider {
    DfuServiceProvider() {
    }

    static BaseDfuImpl getDfuImpl(DfuBaseService dfuBaseService, BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt.getService(SecureDfuImpl.DFU_SERVICE_UUID) != null) {
            return new SecureDfuImpl(dfuBaseService);
        }
        if (bluetoothGatt.getService(LegacyDfuImpl.DFU_SERVICE_UUID) != null) {
            return new LegacyDfuImpl(dfuBaseService);
        }
        return null;
    }
}
