package com.inuker.bluetooth.library.connect;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.ConnectedGattManager;
import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.RuntimeChecker;
import com.inuker.bluetooth.library.connect.listener.GattResponseListener;
import com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse;
import com.inuker.bluetooth.library.connect.listener.ReadCharacterListener;
import com.inuker.bluetooth.library.connect.listener.ReadDescriptorListener;
import com.inuker.bluetooth.library.connect.listener.ReadRssiListener;
import com.inuker.bluetooth.library.connect.listener.RequestMtuListener;
import com.inuker.bluetooth.library.connect.listener.ServiceDiscoverListener;
import com.inuker.bluetooth.library.connect.listener.WriteCharacterListener;
import com.inuker.bluetooth.library.connect.listener.WriteDescriptorListener;
import com.inuker.bluetooth.library.connect.response.BluetoothGattResponse;
import com.inuker.bluetooth.library.jieli.JLConstants;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.ota.JLOTAHolder;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.log.ConvertHelper;
import com.inuker.bluetooth.library.log.VPLocalLogger;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import com.inuker.bluetooth.library.utils.ByteUtils;
import com.inuker.bluetooth.library.utils.Version;
import com.inuker.bluetooth.library.utils.proxy.ProxyBulk;
import com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/BleConnectWorker.class */
public class BleConnectWorker implements Handler.Callback, IBleConnectWorker, IBluetoothGattResponse, ProxyInterceptor, RuntimeChecker {
    private static final int MSG_GATT_RESPONSE = 288;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothDevice mBluetoothDevice;
    private GattResponseListener mGattResponseListener;
    private Handler mWorkerHandler;
    private volatile int mConnectStatus;
    private BleGattProfile mBleGattProfile;
    private Map<UUID, Map<UUID, BluetoothGattCharacteristic>> mDeviceProfile;
    private IBluetoothGattResponse mBluetoothGattResponse;
    private RuntimeChecker mRuntimeChecker;
    public static boolean isFindJLService = false;
    private boolean isJLNotificationOpen = false;
    private int mtu = 20;

    public Handler getWorkerHandler() {
        return this.mWorkerHandler;
    }

    public BleConnectWorker(String mac, RuntimeChecker runtimeChecker) {
        BluetoothAdapter adapter = BluetoothUtils.getBluetoothAdapter();
        if (adapter != null) {
            this.mBluetoothDevice = adapter.getRemoteDevice(mac);
            this.mRuntimeChecker = runtimeChecker;
            this.mWorkerHandler = new Handler(Looper.myLooper(), this);
            this.mDeviceProfile = new HashMap();
            this.mBluetoothGattResponse = (IBluetoothGattResponse) ProxyUtils.getProxy(this, IBluetoothGattResponse.class, this);
            return;
        }
        throw new IllegalStateException("ble adapter null");
    }

    private void refreshServiceProfile() {
        BluetoothLog.v(String.format(Locale.CHINA, "refreshServiceProfile for %s", this.mBluetoothDevice.getAddress()));
        List<BluetoothGattService> services = this.mBluetoothGatt.getServices();
        Map<UUID, Map<UUID, BluetoothGattCharacteristic>> newProfiles = new HashMap<>();
        for (BluetoothGattService service : services) {
            UUID serviceUUID = service.getUuid();
            Map<UUID, BluetoothGattCharacteristic> map = newProfiles.get(serviceUUID);
            if (map == null) {
                BluetoothLog.v("Service: " + serviceUUID);
                map = new HashMap<>();
                newProfiles.put(service.getUuid(), map);
            }
            List<BluetoothGattCharacteristic> characters = service.getCharacteristics();
            for (BluetoothGattCharacteristic character : characters) {
                UUID characterUUID = character.getUuid();
                BluetoothLog.v("character: uuid = " + characterUUID);
                map.put(character.getUuid(), character);
            }
        }
        this.mDeviceProfile.clear();
        this.mDeviceProfile.putAll(newProfiles);
        this.mBleGattProfile = new BleGattProfile(this.mDeviceProfile);
    }

    private BluetoothGattCharacteristic getCharacter(UUID service, UUID character) {
        BluetoothGattService gattService;
        Map<UUID, BluetoothGattCharacteristic> characters;
        BluetoothGattCharacteristic characteristic = null;
        if (service != null && character != null && (characters = this.mDeviceProfile.get(service)) != null) {
            characteristic = characters.get(character);
        }
        if (characteristic == null && this.mBluetoothGatt != null && (gattService = this.mBluetoothGatt.getService(service)) != null) {
            characteristic = gattService.getCharacteristic(character);
        }
        return characteristic;
    }

    private void setConnectStatus(int status) {
        BluetoothLog.v(String.format(Locale.CHINA, "setConnectStatus status = %s", Constants.getStatusText(status)));
        this.mConnectStatus = status;
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onConnectionStateChange(int status, int newState) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onConnectionStateChange for %s: status = %d, newState = %d", this.mBluetoothDevice.getAddress(), Integer.valueOf(status), Integer.valueOf(newState)));
        String msg = "蓝牙操作状态码(status)=" + getBluetoothStateDes(status) + " , 蓝牙连接状态(newState)=" + getBluetoothState(newState);
        VPLocalLogger.bleConnectLog("-onConnectionStateChange- " + msg);
        if (status == 0 && newState == 2) {
            VPLocalLogger.bleConnectLog("-onConnectionStateChange- 连接成功");
            VPLocalLogger.getInstance().clearBLEWriteLog();
            VPLocalLogger.getInstance().clearBLEChangeLog();
            isFindJLService = false;
            JLBleDataManager.getInstance().init(this);
            JLBleDataManager.getInstance().startSendDataThread();
            setConnectStatus(2);
            ConnectedGattManager.getInstance().putConnectedGatt(this.mBluetoothGatt);
            if (this.mGattResponseListener != null) {
                this.mGattResponseListener.onConnectStatusChanged(true);
                return;
            }
            return;
        }
        if (newState == 0) {
            VPLocalLogger.bleConnectLog("-onConnectionStateChange- 连接断开");
            isFindJLService = false;
            JLOTAManager.getInstance().handlerBtDeviceConnection(getBluetoothDevice(), newState);
        }
        ConnectedGattManager.getInstance().removeConnectGatt(this.mBluetoothGatt);
        WatchManager.getInstance().release();
        JLOTAManager.getInstance().setJLOTAInitSuccess(false);
        RcspAuthManager.getInstance().setAuthPass(false);
        JLBleDataManager.getInstance().stopSendDataThread();
        BluetoothLog.e("closeGatt ====> BleConnectWorker:-onConnectionStateChange- STATE_DISCONNECTED");
        closeGatt();
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onServicesDiscovered(int status) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onServicesDiscovered for %s: status = %d", this.mBluetoothDevice.getAddress(), Integer.valueOf(status)));
        VPLocalLogger.bleConnectLog("-onServicesDiscovered- 发现服务：" + statusDes(status));
        if (status == 0) {
            this.mtu = 20;
            setConnectStatus(19);
            broadcastConnectStatus(16);
            refreshServiceProfile();
            isFindJLService = false;
            for (BluetoothGattService service : this.mBluetoothGatt.getServices()) {
                if (service != null && service.getUuid().equals(JLConstants.JL_UUID_SERVICE)) {
                    for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                        if (characteristic.getUuid().equals(JLConstants.JL_UUID_WRITE)) {
                            isFindJLService = true;
                            break;
                        }
                    }
                }
            }
        }
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof ServiceDiscoverListener)) {
            ((ServiceDiscoverListener) this.mGattResponseListener).onServicesDiscovered(status, this.mBleGattProfile);
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onCharacteristicRead(BluetoothGattCharacteristic characteristic, int status, byte[] value) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onCharacteristicRead for %s: status = %d, service = 0x%s, character = 0x%s, value = %s", this.mBluetoothDevice.getAddress(), Integer.valueOf(status), characteristic.getService().getUuid(), characteristic.getUuid(), ByteUtils.byteToString(value)));
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof ReadCharacterListener)) {
            ((ReadCharacterListener) this.mGattResponseListener).onCharacteristicRead(characteristic, status, value);
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onCharacteristicWrite(BluetoothGattCharacteristic characteristic, int status, byte[] value) {
        checkRuntime();
        VPLocalLogger.bleWriteLog("-onCharacteristicWrite- 写入回调：" + statusDes(status) + " | " + ConvertHelper.byte2HexForShow(value));
        BluetoothLog.v(String.format(Locale.CHINA, "onCharacteristicWrite for %s: status = %d, service = 0x%s, character = 0x%s, value = %s", this.mBluetoothDevice.getAddress(), Integer.valueOf(status), characteristic.getService().getUuid(), characteristic.getUuid(), ByteUtils.byteToString(value)));
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof WriteCharacterListener)) {
            ((WriteCharacterListener) this.mGattResponseListener).onCharacteristicWrite(characteristic, status, value);
        }
        JLBleDataManager.getInstance().wakeupSendThread(this.mBluetoothGatt, characteristic.getService().getUuid(), characteristic.getUuid(), status, value);
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onCharacteristicChanged(BluetoothGattCharacteristic characteristic, byte[] value) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onCharacteristicChanged for %s: value = %s, service = 0x%s, character = 0x%s", this.mBluetoothDevice.getAddress(), ByteUtils.byteToString(value), characteristic.getService().getUuid(), characteristic.getUuid()));
        notifyCharacterChanged(characteristic.getService().getUuid(), characteristic.getUuid(), value);
        if (characteristic.getUuid().equals(JLConstants.JL_UUID_NOTIFICATION)) {
            BluetoothLog.d("【杰理】-收到杰理数据- " + Thread.currentThread() + " - " + ByteUtils.byteToString(value));
            WatchManager.getInstance().handReceiveDeviceData(this.mBluetoothGatt.getDevice(), characteristic.getValue());
            JLOTAManager.getInstance().handlerReceiveDeviceData(this.mBluetoothGatt.getDevice(), characteristic.getValue());
            return;
        }
        VPLocalLogger.onCharacteristicChanged(value);
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onDescriptorRead(BluetoothGattDescriptor descriptor, int status, byte[] value) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onDescriptorRead for %s: status = %d, service = 0x%s, character = 0x%s, descriptor = 0x%s", this.mBluetoothDevice.getAddress(), Integer.valueOf(status), descriptor.getCharacteristic().getService().getUuid(), descriptor.getCharacteristic().getUuid(), descriptor.getUuid()));
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof ReadDescriptorListener)) {
            ((ReadDescriptorListener) this.mGattResponseListener).onDescriptorRead(descriptor, status, value);
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onDescriptorWrite(BluetoothGattDescriptor descriptor, int status) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onDescriptorWrite for %s: status = %d, service = 0x%s, character = 0x%s, descriptor = 0x%s", this.mBluetoothDevice.getAddress(), Integer.valueOf(status), descriptor.getCharacteristic().getService().getUuid(), descriptor.getCharacteristic().getUuid(), descriptor.getUuid()));
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof WriteDescriptorListener)) {
            ((WriteDescriptorListener) this.mGattResponseListener).onDescriptorWrite(descriptor, status);
        }
        if (status == 0) {
            UUID uuid = descriptor.getCharacteristic().getUuid();
            if (uuid.equals(JLConstants.JL_UUID_NOTIFICATION)) {
                this.isJLNotificationOpen = true;
                BluetoothLog.e("-杰理- 杰理服务通知打开成功");
            }
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onReadRemoteRssi(int rssi, int status) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onReadRemoteRssi for %s, rssi = %d, status = %d", this.mBluetoothDevice.getAddress(), Integer.valueOf(rssi), Integer.valueOf(status)));
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof ReadRssiListener)) {
            ((ReadRssiListener) this.mGattResponseListener).onReadRemoteRssi(rssi, status);
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse
    public void onMtuChanged(int mtu, int status) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "onMtuChanged for %s, mtu = %d, status = %d", this.mBluetoothDevice.getAddress(), Integer.valueOf(mtu), Integer.valueOf(status)));
        if (mtu > this.mtu) {
            this.mtu -= 5;
        } else {
            this.mtu = mtu - 5;
        }
        JLBleDataManager.getInstance().setMtu(this.mtu);
        if (this.mGattResponseListener != null && (this.mGattResponseListener instanceof RequestMtuListener)) {
            ((RequestMtuListener) this.mGattResponseListener).onMtuChanged(this.mtu, status);
        }
        if (isFindJLService) {
            JLOTAManager.getInstance().handlerMtuChanged(this.mBluetoothGatt, this.mtu, status);
        }
        if (JLOTAHolder.isNeedInitOTAWithDfuLang()) {
            JLOTAManager.getInstance().init(this.mBluetoothGatt);
        }
    }

    private String statusDes(int status) {
        return status == 0 ? "成功[:0]" : "失败[:" + status + "]";
    }

    private String getBluetoothStateDes(int state) {
        switch (state) {
            case 0:
                return "操作成功[:" + state + "]";
            case 8:
                return "连接超时[:" + state + "]";
            case Constants.CODE_WRITE_DESCRIPTOR /* 14 */:
                return "错误异常[:" + state + "]";
            case 19:
                return "被对方用户终止[:" + state + "]";
            case Constants.CODE_REQUEST_MTU /* 22 */:
                return "被本地主机终止[:" + state + "]";
            case 62:
                return "建立失败[:" + state + "]";
            case 133:
                return "Android源码错误[:" + state + "]";
            case 257:
                return "蓝牙操作崩溃[:" + state + "]";
            default:
                return "未知状态[:" + state + "]";
        }
    }

    private String getBluetoothState(int newState) {
        switch (newState) {
            case 0:
                return "STATE_DISCONNECTED[:" + newState + "]";
            case 1:
                return "STATE_CONNECTING[:" + newState + "]";
            case 2:
                return "STATE_CONNECTED[:" + newState + "]";
            case 3:
                return "STATE_DISCONNECTING[:" + newState + "]";
            default:
                return "未知[:" + newState + "]";
        }
    }

    private void broadcastConnectStatus(int status) {
        Intent intent = new Intent("action.connect_status_changed");
        intent.putExtra("extra.mac", this.mBluetoothDevice.getAddress());
        intent.putExtra("extra.status", status);
        BluetoothUtils.sendBroadcast(intent);
    }

    private void notifyCharacterChanged(UUID service, UUID character, byte[] value) {
        CharacterChangeCallbackManager.getInstance().onCharacterChanged(this.mBluetoothDevice.getAddress(), service, character, value);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean openGatt() {
        checkRuntime();
        VPLocalLogger.bleConnectLog(String.format(Locale.CHINA, "-openGatt- 准备连接设备：%s", getAddress()));
        BluetoothLog.v(String.format(Locale.CHINA, "openGatt for %s", getAddress()));
        if (this.mBluetoothGatt != null) {
            BluetoothLog.e("Previous gatt not closed");
            BluetoothLog.v(String.format(Locale.CHINA, "openGatt BluetoothGatt不为空-当前的设备已连接：%s", getAddress()));
            return true;
        }
        Context context = BluetoothUtils.getContext();
        BluetoothGattCallback callback = new BluetoothGattResponse(this.mBluetoothGattResponse);
        if (Version.isMarshmallow()) {
            VPLocalLogger.bleConnectLog(String.format(Locale.CHINA, "-openGatt- connectGatt#TRANSPORT_LE：%s", getAddress()));
            this.mBluetoothGatt = this.mBluetoothDevice.connectGatt(context, false, callback, 2);
        } else {
            VPLocalLogger.bleConnectLog(String.format(Locale.CHINA, "-openGatt- connectGatt：%s", getAddress()));
            this.mBluetoothGatt = this.mBluetoothDevice.connectGatt(context, false, callback);
        }
        if (this.mBluetoothGatt == null) {
            VPLocalLogger.bleConnectLog("openGatt failed: connectGatt return null!");
            BluetoothLog.e("openGatt failed: connectGatt return null!");
            return false;
        }
        return true;
    }

    private String getAddress() {
        return this.mBluetoothDevice.getAddress();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void closeGatt() {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "closeGatt for %s", getAddress()));
        ConnectedGattManager.getInstance().removeConnectGatt(this.mBluetoothGatt);
        if (this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
            if (this.mGattResponseListener != null) {
                this.mGattResponseListener.onConnectStatusChanged(false);
            }
            setConnectStatus(0);
            broadcastConnectStatus(32);
            return;
        }
        BluetoothLog.e("closeGatt ====> Gatt 已近关闭 不需要重复回调");
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean discoverService() {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "discoverService for %s", getAddress()));
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("discoverService but gatt is null!");
            return false;
        }
        if (!this.mBluetoothGatt.discoverServices()) {
            BluetoothLog.e("discoverServices failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public int getCurrentStatus() {
        checkRuntime();
        return this.mConnectStatus;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void registerGattResponseListener(GattResponseListener listener) {
        checkRuntime();
        this.mGattResponseListener = listener;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void clearGattResponseListener(GattResponseListener listener) {
        checkRuntime();
        if (this.mGattResponseListener == listener) {
            this.mGattResponseListener = null;
        }
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean refreshDeviceCache() {
        BluetoothLog.v(String.format(Locale.CHINA, "refreshDeviceCache for %s", getAddress()));
        checkRuntime();
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!BluetoothUtils.refreshGattCache(this.mBluetoothGatt)) {
            BluetoothLog.e("refreshDeviceCache failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readCharacteristic(UUID service, UUID character) {
        BluetoothLog.v(String.format(Locale.CHINA, "readCharacteristic for %s: service = 0x%s, character = 0x%s", this.mBluetoothDevice.getAddress(), service, character));
        checkRuntime();
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.readCharacteristic(characteristic)) {
            BluetoothLog.e("readCharacteristic failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeCharacteristic(UUID service, UUID character, byte[] value) {
        BluetoothLog.v(String.format(Locale.CHINA, "writeCharacteristic for %s: service = 0x%s, character = 0x%s, value = 0x%s", this.mBluetoothDevice.getAddress(), service, character, ByteUtils.byteToString(value)));
        if (service != JLConstants.JL_UUID_SERVICE) {
            checkRuntime();
        }
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        characteristic.setValue(value != null ? value : ByteUtils.EMPTY_BYTES);
        if (!this.mBluetoothGatt.writeCharacteristic(characteristic)) {
            BluetoothLog.e("writeCharacteristic failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readDescriptor(UUID service, UUID character, UUID descriptor) {
        BluetoothLog.v(String.format(Locale.CHINA, "readDescriptor for %s: service = 0x%s, character = 0x%s, descriptor = 0x%s", this.mBluetoothDevice.getAddress(), service, character, descriptor));
        checkRuntime();
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        BluetoothGattDescriptor gattDescriptor = characteristic.getDescriptor(descriptor);
        if (gattDescriptor == null) {
            BluetoothLog.e("descriptor not exist");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.readDescriptor(gattDescriptor)) {
            BluetoothLog.e("readDescriptor failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeDescriptor(UUID service, UUID character, UUID descriptor, byte[] value) {
        BluetoothLog.v(String.format(Locale.CHINA, "writeDescriptor for %s: service = 0x%s, character = 0x%s, descriptor = 0x%s, value = 0x%s", this.mBluetoothDevice.getAddress(), service, character, descriptor, ByteUtils.byteToString(value)));
        checkRuntime();
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        BluetoothGattDescriptor gattDescriptor = characteristic.getDescriptor(descriptor);
        if (gattDescriptor == null) {
            BluetoothLog.e("descriptor not exist");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        gattDescriptor.setValue(value != null ? value : ByteUtils.EMPTY_BYTES);
        if (!this.mBluetoothGatt.writeDescriptor(gattDescriptor)) {
            BluetoothLog.e("writeDescriptor failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeCharacteristicWithNoRsp(UUID service, UUID character, byte[] value) {
        BluetoothLog.v(String.format(Locale.CHINA, "writeCharacteristicWithNoRsp for %s: service = 0x%s, character = 0x%s, value = 0x%s", this.mBluetoothDevice.getAddress(), service, character, ByteUtils.byteToString(value)));
        checkRuntime();
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        characteristic.setValue(value != null ? value : ByteUtils.EMPTY_BYTES);
        characteristic.setWriteType(1);
        if (!this.mBluetoothGatt.writeCharacteristic(characteristic)) {
            BluetoothLog.e("writeCharacteristic failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean setCharacteristicNotification(UUID service, UUID character, boolean enable) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "setCharacteristicNotification for %s, service = %s, character = %s, enable = %b", getAddress(), service, character, Boolean.valueOf(enable)));
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.setCharacteristicNotification(characteristic, enable)) {
            BluetoothLog.e("setCharacteristicNotification failed");
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor == null) {
            BluetoothLog.e("getDescriptor for notify null!");
            return false;
        }
        byte[] value = enable ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
        if (!descriptor.setValue(value)) {
            BluetoothLog.e("setValue for notify descriptor failed!");
            return false;
        }
        if (!this.mBluetoothGatt.writeDescriptor(descriptor)) {
            BluetoothLog.e("writeDescriptor for notify failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean setCharacteristicIndication(UUID service, UUID character, boolean enable) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "setCharacteristicIndication for %s, service = %s, character = %s, enable = %b", getAddress(), service, character, Boolean.valueOf(enable)));
        BluetoothGattCharacteristic characteristic = getCharacter(service, character);
        if (characteristic == null) {
            BluetoothLog.e("characteristic not exist!");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.setCharacteristicNotification(characteristic, enable)) {
            BluetoothLog.e("setCharacteristicIndication failed");
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor == null) {
            BluetoothLog.e("getDescriptor for indicate null!");
            return false;
        }
        byte[] value = enable ? BluetoothGattDescriptor.ENABLE_INDICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
        if (!descriptor.setValue(value)) {
            BluetoothLog.e("setValue for indicate descriptor failed!");
            return false;
        }
        if (!this.mBluetoothGatt.writeDescriptor(descriptor)) {
            BluetoothLog.e("writeDescriptor for indicate failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readRemoteRssi() {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "readRemoteRssi for %s", getAddress()));
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.readRemoteRssi()) {
            BluetoothLog.e("readRemoteRssi failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    @TargetApi(Constants.CODE_REFRESH_CACHE)
    public boolean requestMtu(int mtu) {
        checkRuntime();
        BluetoothLog.v(String.format(Locale.CHINA, "requestMtu for %s, mtu = %d", getAddress(), Integer.valueOf(mtu)));
        this.mtu = mtu;
        if (this.mBluetoothGatt == null) {
            BluetoothLog.e("ble gatt null");
            return false;
        }
        if (!this.mBluetoothGatt.requestMtu(mtu)) {
            BluetoothLog.e("requestMtu failed");
            return false;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public BleGattProfile getGattProfile() {
        return this.mBleGattProfile;
    }

    private boolean isCharacteristicReadable(BluetoothGattCharacteristic characteristic) {
        return (characteristic == null || (characteristic.getProperties() & 2) == 0) ? false : true;
    }

    private boolean isCharacteristicWritable(BluetoothGattCharacteristic characteristic) {
        return (characteristic == null || (characteristic.getProperties() & 8) == 0) ? false : true;
    }

    private boolean isCharacteristicNoRspWritable(BluetoothGattCharacteristic characteristic) {
        return (characteristic == null || (characteristic.getProperties() & 4) == 0) ? false : true;
    }

    private boolean isCharacteristicNotifyable(BluetoothGattCharacteristic characteristic) {
        return (characteristic == null || (characteristic.getProperties() & 16) == 0) ? false : true;
    }

    private boolean isCharacteristicIndicatable(BluetoothGattCharacteristic characteristic) {
        return (characteristic == null || (characteristic.getProperties() & 32) == 0) ? false : true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == MSG_GATT_RESPONSE) {
            ProxyBulk.safeInvoke(msg.obj);
            return true;
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        ProxyBulk.safeInvoke(new ProxyBulk(object, method, args));
        return true;
    }

    @Override // com.inuker.bluetooth.library.RuntimeChecker
    public void checkRuntime() {
    }

    public BluetoothGatt getBluetoothGatt() {
        return this.mBluetoothGatt;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public int getMtu() {
        return this.mtu;
    }
}
