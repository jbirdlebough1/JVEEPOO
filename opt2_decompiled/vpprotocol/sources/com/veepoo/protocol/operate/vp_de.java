package com.veepoo.protocol.operate;

import android.os.SystemClock;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IWeatherStatusDataListener;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.WeatherData2;
import com.veepoo.protocol.model.datas.WeatherStatusData;
import com.veepoo.protocol.model.datas.weather.WeatherData;
import com.veepoo.protocol.model.datas.weather.WeatherEvery3Hour;
import com.veepoo.protocol.model.datas.weather.WeatherEveryDay;
import com.veepoo.protocol.model.enums.EWeatherOprateStatus;
import com.veepoo.protocol.model.enums.EWeatherType;
import com.veepoo.protocol.model.settings.WeatherStatusSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_de.class */
public class vp_de extends com.veepoo.protocol.vp_a {
    IWeatherStatusDataListener vp_h;
    byte vp_c = 1;
    byte vp_d = 4;
    byte vp_e = 2;
    byte vp_f = 3;
    private com.veepoo.protocol.operate.parse.weather.vp_a vp_g = new com.veepoo.protocol.operate.parse.weather.vp_a();
    private boolean vp_i = false;

    private WeatherStatusData vp_c(byte[] bArr) {
        WeatherStatusData weatherStatusData = new WeatherStatusData();
        EWeatherOprateStatus eWeatherOprateStatusVp_b = vp_b(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        boolean z = bArr[5] != 0;
        EWeatherType weatherTypeByValue = EWeatherType.getWeatherTypeByValue(bArr[6] == 0 ? 0 : 1);
        weatherStatusData.setOprate(eWeatherOprateStatusVp_b);
        weatherStatusData.setCrc(iIntValue);
        weatherStatusData.setOpen(z);
        weatherStatusData.setWeatherType(weatherTypeByValue);
        return weatherStatusData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_as(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_as(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    public byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -56;
        bArr[1] = this.vp_e;
        return bArr;
    }

    public EWeatherOprateStatus vp_b(byte[] bArr) {
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b == this.vp_e) {
            return b2 == 0 ? EWeatherOprateStatus.READ_FAIL : EWeatherOprateStatus.READ_SUCCESS;
        }
        if (b == this.vp_f) {
            return b2 == 0 ? EWeatherOprateStatus.SETTING_STATUS_FAIL : EWeatherOprateStatus.SETTING_STATUS_SUCCESS;
        }
        if (b == this.vp_c || b == this.vp_d) {
            return b2 == 0 ? EWeatherOprateStatus.SETTING_CONTENT_FAIL : EWeatherOprateStatus.SETTING_CONTENT_SUCCESS;
        }
        return EWeatherOprateStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, list);
        List listVp_a = vp_a(list);
        if (listVp_a == null || listVp_a.isEmpty()) {
            return;
        }
        Iterator it = listVp_a.iterator();
        while (it.hasNext()) {
            super.send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
        }
    }

    public byte[] vp_a(WeatherData2 weatherData2, int i, int i2) {
        TimeData timeBean;
        byte[] bArr = new byte[20];
        bArr[0] = -56;
        if (weatherData2 != null && (timeBean = weatherData2.getTimeBean()) != null) {
            int year = timeBean.getYear();
            bArr[1] = 1;
            bArr[2] = VpBleByteUtil.loUint16((short) i2);
            bArr[3] = VpBleByteUtil.loUint16((short) i);
            if (year > 1999 && year < 2255) {
                bArr[4] = VpBleByteUtil.loUint16((short) (year - 2000));
            }
            int month = timeBean.getMonth();
            int day = timeBean.getDay();
            int hour = timeBean.getHour();
            int minute = timeBean.getMinute();
            bArr[5] = VpBleByteUtil.loUint16((short) month);
            bArr[6] = VpBleByteUtil.loUint16((short) day);
            bArr[7] = VpBleByteUtil.loUint16((short) hour);
            bArr[8] = VpBleByteUtil.loUint16((short) minute);
            int tempF = weatherData2.getTempF();
            if (tempF > 0) {
                bArr[9] = 1;
            } else {
                bArr[9] = 0;
            }
            bArr[10] = VpBleByteUtil.loUint16((short) Math.abs(tempF));
            bArr[11] = VpBleByteUtil.loUint16((short) weatherData2.getWeathStatus());
            bArr[12] = VpBleByteUtil.loUint16((short) weatherData2.getUvintensityYellow());
            int tempC = weatherData2.getTempC();
            if (tempC > 0) {
                bArr[13] = 1;
            } else {
                bArr[13] = 0;
            }
            bArr[14] = VpBleByteUtil.loUint16((short) Math.abs(tempC));
            bArr[18] = VpBleByteUtil.loUint16((short) weatherData2.getCrc());
            bArr[19] = VpBleByteUtil.hiUint16((short) weatherData2.getCrc());
            return bArr;
        }
        return bArr;
    }

    private List vp_a(List list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        int size = list.size();
        int i = 0;
        while (i < size) {
            WeatherData2 weatherData2 = (WeatherData2) list.get(i);
            int i2 = i + 1;
            i = i2;
            arrayList.add(vp_a(weatherData2, i2, size));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public byte[] vp_a(WeatherStatusSetting weatherStatusSetting) {
        byte[] bArr = new byte[20];
        if (weatherStatusSetting == null) {
            return bArr;
        }
        int value = weatherStatusSetting.geteWeather().getValue();
        boolean zIsOpen = weatherStatusSetting.isOpen();
        bArr[0] = -56;
        bArr[1] = this.vp_f;
        bArr[2] = zIsOpen;
        bArr[3] = VpBleByteUtil.loUint16((short) value);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        IWeatherStatusDataListener iWeatherStatusDataListener = (IWeatherStatusDataListener) iListener;
        this.vp_h = iWeatherStatusDataListener;
        if (bArr == null || bArr.length < 20) {
            iWeatherStatusDataListener.onWeatherDataChange(null);
        } else {
            this.vp_h.onWeatherDataChange(vp_c(bArr));
        }
    }

    private void vp_a(WeatherData weatherData) {
        new StringBuilder().append("====> 城市名:").append(weatherData.getCityName());
        new StringBuilder().append("====> 时 间:").append(weatherData.getTimeBean().toFullDateTimeString());
        Iterator<WeatherEveryDay> it = weatherData.getWeatherEverdayList().iterator();
        while (it.hasNext()) {
            new StringBuilder().append("====> 逐天天气:").append(it.next().toDes());
        }
        Iterator<WeatherEvery3Hour> it2 = weatherData.getWeatherEvery3HourList().iterator();
        while (it2.hasNext()) {
            new StringBuilder().append("====> 逐小时天气:").append(it2.next().toDes());
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WeatherData weatherData) {
        List listVp_b;
        super.vp_a(bluetoothClient, str, bleWriteResponse, weatherData);
        vp_a(weatherData);
        if (this.vp_i || (listVp_b = this.vp_g.vp_b(weatherData)) == null || listVp_b.isEmpty()) {
            return;
        }
        new Thread(() -> {
            for (int i = 0; i < listVp_b.size(); i++) {
                int i2 = i;
                send((byte[]) listVp_b.get(i), bluetoothClient, str, bleWriteResponse);
                this.vp_i = i2 != listVp_b.size() - 1;
                SystemClock.sleep(50L);
            }
        }).start();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WeatherStatusSetting weatherStatusSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, weatherStatusSetting);
        super.send(vp_a(weatherStatusSetting), bluetoothClient, str, bleWriteResponse);
    }
}
