package com.veepoo.protocol.jl.classic;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.text.TextUtils;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.operate.vp_aa;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_d.class */
public abstract class vp_d {
    public static boolean vp_g(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = false;
        try {
            Object objInvoke = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            zBooleanValue = ((Boolean) objInvoke).booleanValue();
        } catch (Exception e) {
            new StringBuilder().append("Invoke removeBond : ").append(e.getMessage());
        }
        return zBooleanValue;
    }

    public static String vp_f(BluetoothDevice bluetoothDevice) {
        return vp_a(bluetoothDevice, true);
    }

    public static boolean vp_c(BluetoothDevice bluetoothDevice) {
        return vp_a(bluetoothDevice, vp_a.vp_f);
    }

    public static boolean vp_b(BluetoothDevice bluetoothDevice) {
        return vp_a(bluetoothDevice, vp_a.vp_g);
    }

    public static boolean vp_a(BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) {
        if (bluetoothA2dp == null || bluetoothDevice == null) {
            return false;
        }
        Class<?> cls = bluetoothA2dp.getClass();
        boolean zBooleanValue = false;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    int iVp_a = vp_a((BluetoothProfile) bluetoothA2dp, bluetoothDevice);
                    new StringBuilder().append("-connectDeviceA2dp- getPriority : ").append(iVp_a);
                    if (iVp_a != 100) {
                        new StringBuilder().append("-connectDeviceA2dp- setRet : ").append(vp_a(bluetoothA2dp, bluetoothDevice, 100));
                    }
                } catch (Exception unused) {
                }
            }
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Object objInvoke = cls.getMethod("connect", clsArr).invoke(bluetoothA2dp, bluetoothDevice);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            zBooleanValue = ((Boolean) objInvoke).booleanValue();
        } catch (Exception unused2) {
        }
        return zBooleanValue;
    }

    public static boolean vp_d(BluetoothDevice bluetoothDevice) {
        boolean zBooleanValue = false;
        if (bluetoothDevice != null) {
            try {
                Object objInvoke = bluetoothDevice.getClass().getDeclaredMethod("isConnected", new Class[0]).invoke(bluetoothDevice, new Object[0]);
                if (!(objInvoke instanceof Boolean)) {
                    return false;
                }
                zBooleanValue = ((Boolean) objInvoke).booleanValue();
            } catch (Exception unused) {
            }
        }
        return zBooleanValue;
    }

    public static boolean vp_e(BluetoothDevice bluetoothDevice) {
        return VPOperateManager.isFindJLService() || bluetoothDevice.getType() == 3 || vp_aa.vp_e != 2;
    }

    public static boolean vp_c(Context context) {
        return context != null && context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static boolean vp_b(BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) {
        if (bluetoothA2dp == null || bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = false;
        try {
            Class<?> cls = bluetoothA2dp.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = cls.getMethod("disconnect", clsArr);
            method.setAccessible(true);
            Object objInvoke = method.invoke(bluetoothA2dp, bluetoothDevice);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            zBooleanValue = ((Boolean) objInvoke).booleanValue();
        } catch (Exception e) {
            new StringBuilder().append("-disconnectDeviceA2dp- have an exception : ").append(e);
        }
        return zBooleanValue;
    }

    public static boolean vp_a(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = false;
        Class<?> cls = bluetoothHeadset.getClass();
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    int iVp_a = vp_a((BluetoothProfile) bluetoothHeadset, bluetoothDevice);
                    new StringBuilder().append("-connectDeviceHfp- getPriority : ").append(iVp_a);
                    if (iVp_a != 100) {
                        new StringBuilder().append("-connectDeviceHfp- setRet : ").append(vp_a(bluetoothHeadset, bluetoothDevice, 100));
                    }
                } catch (Exception unused) {
                }
            }
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = cls.getMethod("connect", clsArr);
            method.setAccessible(true);
            Object objInvoke = method.invoke(bluetoothHeadset, bluetoothDevice);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            zBooleanValue = ((Boolean) objInvoke).booleanValue();
        } catch (Exception unused2) {
        }
        return zBooleanValue;
    }

    public static boolean vp_a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = false;
        if (Build.VERSION.SDK_INT >= 20) {
            zBooleanValue = bluetoothDevice.createBond();
        } else {
            try {
                Object objInvoke = bluetoothDevice.getClass().getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
                if (!(objInvoke instanceof Boolean)) {
                    return false;
                }
                zBooleanValue = ((Boolean) objInvoke).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
                new StringBuilder().append("Invoke createBond : ").append(e.getMessage());
            }
        }
        return zBooleanValue;
    }

    public static String vp_c(int i) {
        switch (i) {
            case 0:
                return "STATE_DISCONNECTED (0:已断开)";
            case 1:
                return "STATE_CONNECTING (1:正在连接)";
            case 2:
                return "STATE_CONNECTED (2:已连接)";
            case 3:
                return "STATE_DISCONNECTING (3:正在断开)";
            default:
                return "UN KNOW :" + i;
        }
    }

    public static boolean vp_b(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothHeadset == null || bluetoothDevice == null) {
            return false;
        }
        boolean zBooleanValue = false;
        try {
            Class<?> cls = bluetoothHeadset.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            Method method = cls.getMethod("disconnect", clsArr);
            method.setAccessible(true);
            Object objInvoke = method.invoke(bluetoothHeadset, bluetoothDevice);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            zBooleanValue = ((Boolean) objInvoke).booleanValue();
        } catch (Exception e) {
            new StringBuilder().append("-disconnectFromHfp- have an exception : ").append(e);
        }
        return zBooleanValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    public static boolean vp_a(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return false;
        }
        ?? BooleanValue = bluetoothDevice;
        ?? r9 = 0;
        try {
            Class<?> cls = BooleanValue.getClass();
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = Integer.TYPE;
            Method declaredMethod = cls.getDeclaredMethod("createBond", clsArr);
            declaredMethod.setAccessible(true);
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(i);
            Object objInvoke = declaredMethod.invoke(bluetoothDevice, objArr);
            if (!(objInvoke instanceof Boolean)) {
                return false;
            }
            BooleanValue = ((Boolean) objInvoke).booleanValue();
            r9 = BooleanValue;
        } catch (Exception unused) {
            BooleanValue.printStackTrace();
        }
        return r9;
    }

    public static List vp_b(Context context) {
        ArrayList arrayList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            int iVp_a = vp_a(defaultAdapter);
            if (iVp_a == 2 || iVp_a == 0) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (vp_d(bluetoothDevice)) {
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
            if (context != null) {
                List<BluetoothDevice> listVp_a = vp_a(context);
                if (listVp_a != null) {
                    for (BluetoothDevice bluetoothDevice2 : listVp_a) {
                        if (!arrayList.contains(bluetoothDevice2)) {
                            arrayList.add(bluetoothDevice2);
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (arrayList.size() <= 0) {
            arrayList = null;
        }
        return arrayList;
    }

    public static boolean vp_a(BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
        return (bluetoothDevice == null || bluetoothDevice2 == null || !bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) ? false : true;
    }

    public static boolean vp_b() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static String vp_b(int i) {
        switch (i) {
            case 10:
                return "BOND_NONE(10:未绑定)";
            case 11:
                return "BOND_BONDING(11:正在绑定)";
            case 12:
                return "BOND_BONDED(12:已绑定)";
            default:
                return "UN KNOW :" + i;
        }
    }

    public static boolean vp_a(BluetoothDevice bluetoothDevice, UUID uuid) {
        if (!vp_b() || bluetoothDevice == null || uuid == null || TextUtils.isEmpty(uuid.toString())) {
            return false;
        }
        boolean z = false;
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null) {
            return false;
        }
        for (ParcelUuid parcelUuid : uuids) {
            if (parcelUuid != null && uuid.toString().equalsIgnoreCase(parcelUuid.toString())) {
                z = true;
                break;
            }
        }
        return z;
    }

    public static int vp_a(int i) {
        if (i < 20) {
            i = 20;
        }
        if (i > 514) {
            i = 514;
        }
        return i;
    }

    public static int vp_a(BluetoothAdapter bluetoothAdapter) {
        int iIntValue = -1;
        if (bluetoothAdapter != null) {
            try {
                Method declaredMethod = bluetoothAdapter.getClass().getDeclaredMethod("getConnectionState", new Class[0]);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(bluetoothAdapter, new Object[0]);
                if (!(objInvoke instanceof Integer)) {
                    return -1;
                }
                iIntValue = ((Integer) objInvoke).intValue();
            } catch (Exception unused) {
            }
        }
        return iIntValue;
    }

    public static List vp_a(Context context) {
        BluetoothManager bluetoothManager;
        if (context == null || (bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth")) == null) {
            return null;
        }
        return bluetoothManager.getConnectedDevices(7);
    }

    public static Method vp_a() {
        return Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
    }

    public static int vp_a(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return 0;
        }
        try {
            Method methodVp_a = vp_a();
            Class<?> cls = bluetoothProfile.getClass();
            Object[] objArr = new Object[2];
            objArr[0] = "getPriority";
            Class[] clsArr = new Class[1];
            clsArr[0] = BluetoothDevice.class;
            objArr[1] = clsArr;
            Method method = (Method) methodVp_a.invoke(cls, objArr);
            if (method == null) {
                return 0;
            }
            Object[] objArr2 = new Object[1];
            objArr2[0] = bluetoothDevice;
            Object objInvoke = method.invoke(bluetoothProfile, objArr2);
            return !(objInvoke instanceof Integer) ? 0 : ((Integer) objInvoke).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static BluetoothDevice vp_a(String str) {
        BluetoothAdapter defaultAdapter;
        if (!BluetoothAdapter.checkBluetoothAddress(str) || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            return null;
        }
        try {
            return defaultAdapter.getRemoteDevice(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String vp_a(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null) {
            return "null";
        }
        return z ? "name : " + bluetoothDevice.getName() + ", type : " + bluetoothDevice.getType() + ", address : " + bluetoothDevice.getAddress() : bluetoothDevice.getAddress();
    }

    public static boolean vp_a(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            Method methodVp_a = vp_a();
            Class<?> cls = bluetoothProfile.getClass();
            Object[] objArr = new Object[2];
            objArr[0] = "setPriority";
            Class[] clsArr = new Class[2];
            clsArr[0] = BluetoothDevice.class;
            clsArr[1] = Integer.TYPE;
            objArr[1] = clsArr;
            Method method = (Method) methodVp_a.invoke(cls, objArr);
            if (method == null) {
                return false;
            }
            Object[] objArr2 = new Object[2];
            objArr2[0] = bluetoothDevice;
            objArr2[1] = Integer.valueOf(i);
            Object objInvoke = method.invoke(bluetoothProfile, objArr2);
            return (objInvoke instanceof Boolean) && ((Boolean) objInvoke).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
