package com.timaimee.vpdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolylineOptions;
import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IReportGpsDataListener;
import com.veepoo.protocol.model.datas.ReportGpsLatLongData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timaimee on 2017/4/24.
 */
public class GpsReportActivity extends Activity implements LocationSource, AMapLocationListener {
    private Context mContext;
    private final static String TAG = GpsReportActivity.class.getSimpleName();
    WriteResponse writeResponse = new WriteResponse();
    MapView mMapView;
    AMap aMap;

    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;

    TextView mResultTv;

    /**
     * 写入的状态返回
     */
    static class WriteResponse implements IBleWriteResponse {

        @Override
        public void onResponse(int code) {
            Logger.t(TAG).i("write cmd status:" + code);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_report);
        mResultTv = findViewById(R.id.result);
        mContext = getApplicationContext();

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreateExecute mMapView.onCreate(savedInstanceState) to create the map
        mMapView.onCreate(savedInstanceState);

        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }

    }


    List<LatLng> mLatLngsPointList = new ArrayList<>();
    List<String> mPointStrList = new ArrayList<>();

    public void startReport() {
        mLatLngsPointList.clear();

        VPOperateManager.getMangerInstance(mContext).setReportGps(writeResponse, true, new IReportGpsDataListener() {
            @Override
            public void onReportGpsDataDataChange(ReportGpsLatLongData reportGpsLatLongData) {
                String message = "打开Gps上传:" + reportGpsLatLongData.toString();
                mResultTv.setText(message);
                addPolyline(new LatLng(reportGpsLatLongData.getLat(), reportGpsLatLongData.getLon()));
                Logger.t(TAG).i(message);
            }
        });
    }

    public void stopReport() {
        VPOperateManager.getMangerInstance(mContext).setReportGps(writeResponse, false, new IReportGpsDataListener() {
            @Override
            public void onReportGpsDataDataChange(ReportGpsLatLongData reportGpsLatLongData) {
                String message = "关闭Gps上传:" + reportGpsLatLongData.toString();
                mResultTv.setText(message);
                Logger.t(TAG).i(message);
            }
        });
    }


    private void addPolyline(LatLng latLng) {
        mLatLngsPointList.add(latLng);

        addMark(latLng);
        PolylineOptions polylineOptions = new PolylineOptions().
                addAll(mLatLngsPointList).width(10).color(Color.argb(255, 1, 1, 1));
        aMap.addPolyline(polylineOptions);

//        LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
        LatLng latLngLast = mLatLngsPointList.get(mLatLngsPointList.size() - 1);
        aMap.moveCamera(CameraUpdateFactory.newLatLng(latLngLast));
    }

    private void addMark(LatLng latLng) {
        MarkerOptions mark = new MarkerOptions()
                // .setFlat(true)
                .position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.fit_start_point));
        aMap.addMarker(mark);
    }

    void setUpMap() {

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//Initialize the positioning blue dot style class myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//Continuous positioning、And move the view to the center point of the map. The positioning point rotates according to the device's orientation.，And it will follow the movement of the device. (1 second1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.setLocationSource(this);// 设置定位监听
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示

        aMap.setMyLocationEnabled(false);// Set to true to display the positioning layer and enable positioning triggering.，falseIndicates that hiding the positioning layer does not trigger positioning. The default isfalse
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        Logger.t(TAG).i("onLocationChanged");
        mListener.onLocationChanged(amapLocation);// 显示系统小蓝点

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        Logger.t(TAG).i("activate");
//        startLocation();
    }

    private void startLocation() {

        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient = null;
        }

        if (mlocationClient == null) {
            try {
                mlocationClient = new AMapLocationClient(this);
                mlocationClient.setLocationListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);

        mlocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        Logger.t(TAG).i("deactivate");
        if (mlocationClient != null && mlocationClient.isStarted())
            mlocationClient.stopLocation();
        mlocationClient = null;
        mListener = null;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Logger.t(TAG).i("onPointerCaptureChanged");
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResumeExecute mMapView.onResume () and redraw the loaded map
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPauseExecute mMapView.onPause () to pause the rendering of the map
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceStateExecute mMapView.onSaveInstanceState (outState) to save the current state of the map
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroyExecute mMapView.onDestroy() to destroy the map
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }
}
