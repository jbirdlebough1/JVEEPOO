package vpno.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuService.class */
interface DfuService {
    void pause();

    void resume();

    void abort();

    boolean hasRequiredService(BluetoothGatt bluetoothGatt);

    boolean hasRequiredCharacteristics(BluetoothGatt bluetoothGatt);

    void onBondStateChanged(int i);

    boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2);

    void performDfu(Intent intent);

    void release();
}
