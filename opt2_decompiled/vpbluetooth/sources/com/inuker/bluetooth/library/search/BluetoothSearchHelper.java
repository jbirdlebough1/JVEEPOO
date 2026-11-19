package com.inuker.bluetooth.library.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import com.inuker.bluetooth.library.utils.proxy.ProxyBulk;
import com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/BluetoothSearchHelper.class */
public class BluetoothSearchHelper implements IBluetoothSearchHelper, ProxyInterceptor, Handler.Callback {
    private BluetoothSearchRequest mCurrentRequest;
    private static IBluetoothSearchHelper sInstance;
    private Handler mHandler = new Handler(Looper.getMainLooper(), this);

    private BluetoothSearchHelper() {
    }

    public static IBluetoothSearchHelper getInstance() {
        if (sInstance == null) {
            synchronized (BluetoothSearchHelper.class) {
                if (sInstance == null) {
                    BluetoothSearchHelper helper = new BluetoothSearchHelper();
                    sInstance = (IBluetoothSearchHelper) ProxyUtils.getProxy(helper, IBluetoothSearchHelper.class, helper);
                }
            }
        }
        return sInstance;
    }

    @Override // com.inuker.bluetooth.library.search.IBluetoothSearchHelper
    public void startSearch(BluetoothSearchRequest request, BluetoothSearchResponse response) {
        request.setSearchResponse(new BluetoothSearchResponseImpl(response));
        if (!BluetoothUtils.isBluetoothEnabled()) {
            request.cancel();
            return;
        }
        stopSearch();
        if (this.mCurrentRequest == null) {
            this.mCurrentRequest = request;
            this.mCurrentRequest.start();
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/BluetoothSearchHelper$BluetoothSearchResponseImpl.class */
    private class BluetoothSearchResponseImpl implements BluetoothSearchResponse {
        BluetoothSearchResponse response;

        BluetoothSearchResponseImpl(BluetoothSearchResponse response) {
            this.response = response;
        }

        @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
        public void onSearchStarted() {
            this.response.onSearchStarted();
        }

        @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
        public void onDeviceFounded(SearchResult device) {
            this.response.onDeviceFounded(device);
        }

        @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
        public void onSearchStopped() {
            this.response.onSearchStopped();
            BluetoothSearchHelper.this.mCurrentRequest = null;
        }

        @Override // com.inuker.bluetooth.library.search.response.BluetoothSearchResponse
        public void onSearchCanceled() {
            this.response.onSearchCanceled();
            BluetoothSearchHelper.this.mCurrentRequest = null;
        }
    }

    @Override // com.inuker.bluetooth.library.search.IBluetoothSearchHelper
    public void stopSearch() {
        if (this.mCurrentRequest != null) {
            this.mCurrentRequest.cancel();
            this.mCurrentRequest = null;
        }
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        this.mHandler.obtainMessage(0, new ProxyBulk(object, method, args)).sendToTarget();
        return true;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        ProxyBulk.safeInvoke(msg.obj);
        return true;
    }
}
