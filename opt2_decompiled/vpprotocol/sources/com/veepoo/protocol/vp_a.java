package com.veepoo.protocol;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.base.IOperate;
import com.veepoo.protocol.listener.data.IAlarm2DataReadListener;
import com.veepoo.protocol.listener.data.IAutoDetectOriginDataListener;
import com.veepoo.protocol.listener.data.IBodyComponentDetectListener;
import com.veepoo.protocol.listener.data.IBodyComponentReadDataListener;
import com.veepoo.protocol.listener.data.IBodyComponentReadIdListener;
import com.veepoo.protocol.listener.data.ICuffBPDetectDataListener;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.listener.data.IECGReadDataListener;
import com.veepoo.protocol.listener.data.IECGReadIdListener;
import com.veepoo.protocol.listener.data.IEmotionDetectListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.listener.data.IGsrDetectListener;
import com.veepoo.protocol.listener.data.IHealthAlarmIntervalListener;
import com.veepoo.protocol.listener.data.IHrvDetectListener;
import com.veepoo.protocol.listener.data.IMagneticTherapyListener;
import com.veepoo.protocol.listener.data.IMetDetectListener;
import com.veepoo.protocol.listener.data.IPPGRawDataReadListener;
import com.veepoo.protocol.listener.data.IPressureDetectListener;
import com.veepoo.protocol.listener.data.ITcmDiagnosisDetectListener;
import com.veepoo.protocol.listener.data.ITextImageTransferListener;
import com.veepoo.protocol.listener.data.IZT163DeviceAlwaysOffScreenOptListener;
import com.veepoo.protocol.model.G15QRCode;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.Contact;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.datas.GameClockBean;
import com.veepoo.protocol.model.datas.GnssLocationData;
import com.veepoo.protocol.model.datas.HealthAlarmInterval;
import com.veepoo.protocol.model.datas.HealthRemind;
import com.veepoo.protocol.model.datas.MagneticTherapy;
import com.veepoo.protocol.model.datas.MealInfo;
import com.veepoo.protocol.model.datas.MusicData;
import com.veepoo.protocol.model.datas.NetworkSever4gConfigInfo;
import com.veepoo.protocol.model.datas.PersonInfoData;
import com.veepoo.protocol.model.datas.QH15HealthData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.UICustomSetData;
import com.veepoo.protocol.model.datas.UIDataAGPS;
import com.veepoo.protocol.model.datas.UIDataCustom;
import com.veepoo.protocol.model.datas.UIDataG15Img;
import com.veepoo.protocol.model.datas.UIDataImagePush;
import com.veepoo.protocol.model.datas.UIDataServer;
import com.veepoo.protocol.model.datas.WorldClock;
import com.veepoo.protocol.model.datas.weather.WeatherData;
import com.veepoo.protocol.model.enums.EAdcType;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.EBloodGlucoseDetectModel;
import com.veepoo.protocol.model.enums.EEcgDataType;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EHealthAlarmType;
import com.veepoo.protocol.model.enums.EHealthLightStatus;
import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EQH15ComplianceType;
import com.veepoo.protocol.model.enums.ERemindEvent;
import com.veepoo.protocol.model.enums.ESportControlType;
import com.veepoo.protocol.model.enums.ESportType;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.model.enums.HealthRemindType;
import com.veepoo.protocol.model.enums.MagneticTherapyType;
import com.veepoo.protocol.model.enums.PPGSwitchStatus;
import com.veepoo.protocol.model.enums.PPGTestMode;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.model.settings.AllSetSetting;
import com.veepoo.protocol.model.settings.AutoDetectStateSetting;
import com.veepoo.protocol.model.settings.BpSetting;
import com.veepoo.protocol.model.settings.BreathBreakRemindSetting;
import com.veepoo.protocol.model.settings.ChantingSetting;
import com.veepoo.protocol.model.settings.CheckWearSetting;
import com.veepoo.protocol.model.settings.ContentSetting;
import com.veepoo.protocol.model.settings.CountDownSetting;
import com.veepoo.protocol.model.settings.CustomSetting;
import com.veepoo.protocol.model.settings.DeviceTimeSetting;
import com.veepoo.protocol.model.settings.GpsLatLongSetting;
import com.veepoo.protocol.model.settings.GpsLatLongSettingFromApp;
import com.veepoo.protocol.model.settings.HeartWaringSetting;
import com.veepoo.protocol.model.settings.KaabaSetting;
import com.veepoo.protocol.model.settings.LongSeatSetting;
import com.veepoo.protocol.model.settings.LowPowerSetting;
import com.veepoo.protocol.model.settings.NightTurnWristSetting;
import com.veepoo.protocol.model.settings.ScreenSetting;
import com.veepoo.protocol.model.settings.SoldierContentSetting;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import com.veepoo.protocol.model.settings.WeatherStatusSetting;
import com.veepoo.protocol.model.settings.WomenSetting;
import com.veepoo.protocol.multi_lead.listener.IECGMultiLeadDetectListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgDetectListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgReadDataListener;
import com.veepoo.protocol.operate.AIFunctionOpt;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/vp_a.class */
public abstract class vp_a implements IOperate {
    public static final SimpleDateFormat vp_b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    protected Context vp_a;

    @Override // com.veepoo.protocol.listener.base.ISend
    public int send(byte[] bArr, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        BluetoothLog.i("onWrite,action=" + com.veepoo.protocol.profile.vp_b.vp_a(com.veepoo.protocol.profile.vp_a.vp_d, bArr) + ",mac=" + str + ",value=" + VpBleByteUtil.byte2HexForShow(bArr));
        if (TextUtils.isEmpty(str)) {
            BluetoothLog.i("Write fail,mac must not be null");
            return -1;
        }
        byte b = bArr[0];
        boolean z = (b == -95 || b == -96) ? false : true;
        if (!JLOTAManager.getInstance().isJLOTAInitSuccess() || ((!JLOTAManager.getInstance().isOTA() && !JLOTAManager.getInstance().isJlDeviceDfuModel()) || !z)) {
            bluetoothClient.write(str, com.veepoo.protocol.profile.vp_a.vp_c, com.veepoo.protocol.profile.vp_a.vp_e, bArr, bleWriteResponse);
            return 0;
        }
        Log.e("vpprotocol", "正在ota升级，不允许其他操作");
        bleWriteResponse.onResponse(-2);
        return 0;
    }

    public void handler(byte[] bArr) {
    }

    public void vp_an(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_j(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_aq(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ar(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ba(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bm(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bt(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ac(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ab(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_z(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bc(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bp(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_w(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bb(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bo(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_v(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_t(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ay(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bi(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_u(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_o(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_at(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_s(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_aj(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_au(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_g(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ap(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_cc(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_al(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_m(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ah(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ak(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bf(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bx(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_br(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_k(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_r(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_as(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ax(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_be(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bv(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_az(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bk(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_n(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_q(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("增加一个联系人：").append(contact.toString());
    }

    public void vp_c(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        for (int i = 0; i < list.size(); i++) {
            new StringBuilder().append("删除一批联系人：index = ").append(i).append(" - ").append(list.get(i));
        }
    }

    public void vp_ai(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_af(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bw(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_am(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bl(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bj(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_p(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ao(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ca(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_b(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        for (int i = 0; i < list.size(); i++) {
            new StringBuilder().append("增加一批世界时钟：index = ").append(i).append(" - ").append(list.get(i));
        }
    }

    public void vp_d(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        for (int i = 0; i < list.size(); i++) {
            new StringBuilder().append("删除一批世界时钟：index = ").append(i).append(" - ").append(list.get(i));
        }
    }

    public void vp_bu(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bs(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bq(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bn(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_l(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_h(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_i(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_by(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ad(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_av(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_aw(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_x(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bg(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bz(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_bh(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_cb(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ag(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_aa(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_ae(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_y(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_b(String str, BluetoothClient bluetoothClient, String str2, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(List list, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        for (int i = 0; i < list.size(); i++) {
            new StringBuilder().append("增加一批联系人：index = ").append(i).append(" - ").append(list.get(i));
        }
    }

    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
    }

    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
    }

    public void vp_a(WorldClock worldClock, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("增加一个世界时钟：").append(worldClock.toString());
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_a(String str, BluetoothClient bluetoothClient, String str2, BleWriteResponse bleWriteResponse) {
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
    }

    public void vp_c(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BpSetting bpSetting) {
    }

    public void vp_b(Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("删除一个联系人：").append(contact.toString());
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str2) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CustomSetting customSetting) {
    }

    public void vp_b(WorldClock worldClock, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("删除一个世界时钟：").append(worldClock.toString());
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str2) {
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
    }

    public void vp_c(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置SOS呼叫次数：").append(i);
    }

    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Alarm2Setting alarm2Setting) {
    }

    public void vp_b(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TextAlarm2Setting textAlarm2Setting) {
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
    }

    public void vp_b(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("读取世界时钟 crc : ").append(i);
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener, boolean z2) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MagneticTherapyType magneticTherapyType, IMagneticTherapyListener iMagneticTherapyListener) {
        new StringBuilder().append("关闭磁疗功能 type:").append(magneticTherapyType.toString());
    }

    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str2) {
    }

    public int vp_b(byte[] bArr, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        String strVp_a = com.veepoo.protocol.profile.vp_b.vp_a(com.veepoo.protocol.profile.vp_a.vp_d, bArr);
        byte b = bArr[0];
        boolean z = (b == -95 || b == -96) ? false : true;
        if ((JLOTAManager.getInstance().isOTA() || JLOTAManager.getInstance().isJlDeviceDfuModel()) && z) {
            Log.e("vpprotocol", "正在ota升级，不允许其他操作");
            bleWriteResponse.onResponse(-2);
            return 0;
        }
        BluetoothLog.i("onWrite,sendUi action=" + strVp_a + " ,value.length =  " + bArr.length + ",value=" + VpBleByteUtil.byte2HexForShow(bArr));
        bluetoothClient.writeNoRsp(str, com.veepoo.protocol.profile.vp_a.vp_l, com.veepoo.protocol.profile.vp_a.vp_n, bArr, bleWriteResponse);
        return 0;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, String str2, boolean z, boolean z2, DeviceTimeSetting deviceTimeSetting) {
        new StringBuilder().append("密码操作-验证, 是否需要确认密码：").append(z2);
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
    }

    public void vp_a(boolean z, List list, List list2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("批量删除游戏设置 >>> isOpen = ").append(z).append(" , deleteIDs = ").append(Arrays.toString(list.toArray()));
        for (int i = 0; i < list2.size(); i++) {
            new StringBuilder().append("[批量删除游戏设置] ").append(i).append(":").append(((GameClockBean) list2.get(i)).toSmartStr());
        }
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
    }

    public void vp_a(byte[] bArr, IListener iListener) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataAGPS uIDataAGPS, int i) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BpSetting bpSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataCustom uIDataCustom) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Alarm2Setting alarm2Setting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataImagePush uIDataImagePush) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportType eSportType) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataServer uIDataServer) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, String str2) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBPDetectModel eBPDetectModel) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
    }

    public void vp_a(Contact contact, Contact contact2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("移动联系人位置 from:").append(contact).append(" -> to:").append(contact2);
    }

    public void vp_a(WorldClock worldClock, WorldClock worldClock2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("移动世界时钟位置 from:").append(worldClock).append(" -> to:").append(worldClock2);
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IFatigueDataListener iFatigueDataListener) {
    }

    public void vp_a(int i, int i2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("移动世界时钟位置 from:").append(i).append(" -> to:").append(i2);
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MagneticTherapy magneticTherapy, int i, IMagneticTherapyListener iMagneticTherapyListener) {
        new StringBuilder().append("打开磁疗功能 magneticTherapy:").append(magneticTherapy.toString());
    }

    public void vp_a(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, IAutoDetectOriginDataListener iAutoDetectOriginDataListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EUIFromType eUIFromType) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ArrayList arrayList, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentReadIdListener iBodyComponentReadIdListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ChantingSetting chantingSetting) {
    }

    public void vp_a(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("读取联系人 crc : ").append(i);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, long j, List list, List list2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, EEcgDataType eEcgDataType, IECGReadIdListener iECGReadIdListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, boolean z, IECGReadDataListener iECGReadDataListener) {
    }

    public void vp_a(EHealthAlarmType eHealthAlarmType, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        new StringBuilder().append("读取健康提醒区间值 healthAlarmType:").append(eHealthAlarmType);
    }

    public void vp_a(HealthRemindType healthRemindType, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list, IAlarm2DataReadListener iAlarm2DataReadListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, IECGReadIdListener iECGReadIdListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, IMultiEcgReadDataListener iMultiEcgReadDataListener) {
    }

    public void vp_a(TimeData timeData, PPGTestMode pPGTestMode, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IPPGRawDataReadListener iPPGRawDataReadListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ERemindEvent eRemindEvent, long j) {
        new StringBuilder().append("读取提醒事件 >> ").append(eRemindEvent).append(",  读取时间差=").append(new TimeData(j).getDateAndClock4GBandDb());
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportType eSportType) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
    }

    public void vp_a(boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(boolean z, boolean z2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置设备BT是否自动连接 isAutoConnect：").append(z);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, int i, int i2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, G15QRCode g15QRCode) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ContentSetting contentSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, SoldierContentSetting soldierContentSetting) {
    }

    public int vp_a(byte[] bArr, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        String strVp_a = com.veepoo.protocol.profile.vp_b.vp_a(com.veepoo.protocol.profile.vp_a.vp_d, bArr);
        byte b = bArr[0];
        boolean z = (b == -95 || b == -96) ? false : true;
        if ((JLOTAManager.getInstance().isOTA() || JLOTAManager.getInstance().isJlDeviceDfuModel()) && z) {
            Log.e("vpprotocol", "正在ota升级，不允许其他操作");
            bleWriteResponse.onResponse(-2);
            return 0;
        }
        BluetoothLog.i("onWrite,sendUi action=" + strVp_a + ",value=" + VpBleByteUtil.byte2HexForShow(bArr));
        bluetoothClient.write(str, com.veepoo.protocol.profile.vp_a.vp_l, com.veepoo.protocol.profile.vp_a.vp_m, bArr, bleWriteResponse);
        return 0;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, NetworkSever4gConfigInfo networkSever4gConfigInfo) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AutoDetectStateSetting autoDetectStateSetting) {
    }

    public void vp_a(boolean z, Contact contact, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置联系人SOS状态：").append(contact).append(" , 是否设置为SOS联系人：").append(z);
    }

    public void vp_a(Context context) {
        this.vp_a = context;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UICustomSetData uICustomSetData) {
    }

    public void vp_a(boolean z, boolean z2, boolean z3, boolean z4, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置设备BT状态 isAutoConnect：").append(z).append(" isBTOpen ：").append(z2).append(" isAudioOpen ：").append(z3).append(" isClearPairInfo ：").append(z4);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, EFunctionStatus eFunctionStatus) {
        new StringBuilder().append("读取-功能的开关和关闭A1-fun=").append(i).append(",switch=").append(eFunctionStatus);
    }

    public void vp_a(List list, boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置游戏设置是否可用:").append(z);
        for (int i = 0; i < list.size(); i++) {
            new StringBuilder().append("[设置游戏设置是否可用] ").append(i).append(":").append(((GameClockBean) list.get(i)).toSmartStr());
        }
    }

    public void vp_a(HealthAlarmInterval healthAlarmInterval, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        new StringBuilder().append("设置健康提醒区间值 healthAlarmType:").append(healthAlarmInterval.toString());
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EHealthLightStatus eHealthLightStatus) {
        new StringBuilder().append("设置健康灯状态 >> ").append(eHealthLightStatus.getDes());
    }

    public void vp_a(byte[] bArr) {
    }

    public void vp_a(PPGSwitchStatus pPGSwitchStatus, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EQH15ComplianceType eQH15ComplianceType) {
        new StringBuilder().append("设置PSAIM达标提醒时间 >> ").append(eQH15ComplianceType);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, QH15HealthData qH15HealthData) {
        new StringBuilder().append("设置PSAIM健康数据时间戳 >> ").append(qH15HealthData.toString());
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Map map) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportControlType eSportControlType, ESportType eSportType) {
    }

    public void vp_a(boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
        new StringBuilder().append(z ? "开启" : "关闭").append("合镁ZT163设备常灭屏");
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AllSetSetting allSetSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Boolean bool, BloodComponent bloodComponent) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, float f, boolean z) {
        new StringBuilder().append("设置Blood GlucoseCalibration值：bloodGlucoseValue = ").append(f).append(" , isOpen = ").append(z);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CountDownSetting countDownSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ELanguage eLanguage) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, GpsLatLongSetting gpsLatLongSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, GpsLatLongSettingFromApp gpsLatLongSettingFromApp) {
    }

    public void vp_a(HealthRemind healthRemind, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("设置健康提醒：").append(healthRemind.toString());
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, HeartWaringSetting heartWaringSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, KaabaSetting kaabaSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, LongSeatSetting longSeatSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, LowPowerSetting lowPowerSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MusicData musicData) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, NightTurnWristSetting nightTurnWristSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, TimeData timeData, TimeData timeData2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BreathBreakRemindSetting breathBreakRemindSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ScreenSetting screenSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, FunctionSocailMsgData functionSocailMsgData) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TextAlarm2Setting textAlarm2Setting, byte b) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, DeviceTimeSetting deviceTimeSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WeatherData weatherData) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WeatherStatusSetting weatherStatusSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WomenSetting womenSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CheckWearSetting checkWearSetting) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, long j) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBPDetectModel eBPDetectModel) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Boolean bool) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentDetectListener iBodyComponentDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, ICuffBPDetectDataListener iCuffBPDetectDataListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IEmotionDetectListener iEmotionDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IFatigueDataListener iFatigueDataListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IMetDetectListener iMetDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, boolean z2) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener, boolean z) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IPressureDetectListener iPressureDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, ITcmDiagnosisDetectListener iTcmDiagnosisDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IGsrDetectListener iGsrDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, boolean z2, IECGMultiLeadDetectListener iECGMultiLeadDetectListener) {
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, PersonInfoData personInfoData) {
    }
}
