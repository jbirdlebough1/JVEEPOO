package com.veepoo.protocol;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.IntRange;
import com.bluetrum.abpartool.ParTool;
import com.bluetrum.abpartool.utils.ImageUtils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.connect.BleConnectWorker;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleMtuResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleReadRssiResponse;
import com.inuker.bluetooth.library.connect.response.BleResponse;
import com.inuker.bluetooth.library.connect.response.BleUnnotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager;
import com.inuker.bluetooth.library.jieli.dial.JLWatchHolder;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.ota.JLOTAHolder;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.jieli.response.RcspAuthResponse;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.model.BleGattCharacter;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.model.BleGattService;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.veepoo.protocol.customui.WatchUIType;
import com.veepoo.protocol.listener.IHealthRemindListener;
import com.veepoo.protocol.listener.IMiniCheckupOptListener;
import com.veepoo.protocol.listener.base.IABleConnectStatusListener;
import com.veepoo.protocol.listener.base.IABluetoothStateListener;
import com.veepoo.protocol.listener.base.IBleNotifyResponse;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.base.ICallBack;
import com.veepoo.protocol.listener.base.IConnectResponse;
import com.veepoo.protocol.listener.base.INotifyResponse;
import com.veepoo.protocol.listener.data.IActiveMeasurementDataListener;
import com.veepoo.protocol.listener.data.IAlarm2DataListListener;
import com.veepoo.protocol.listener.data.IAlarm2DataReadListener;
import com.veepoo.protocol.listener.data.IAlarmDataListener;
import com.veepoo.protocol.listener.data.IAllHealthDataListener;
import com.veepoo.protocol.listener.data.IAllSetDataListener;
import com.veepoo.protocol.listener.data.IAppReportGpsDataListener;
import com.veepoo.protocol.listener.data.IAutoDetectOriginDataListener;
import com.veepoo.protocol.listener.data.IAutoMeasureSettingDataListener;
import com.veepoo.protocol.listener.data.IBPDetectDataListener;
import com.veepoo.protocol.listener.data.IBPFunctionListener;
import com.veepoo.protocol.listener.data.IBPSettingDataListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.listener.data.IBloodComponentDetectListener;
import com.veepoo.protocol.listener.data.IBloodComponentOptListener;
import com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener;
import com.veepoo.protocol.listener.data.IBodyComponentDetectListener;
import com.veepoo.protocol.listener.data.IBodyComponentReadDataListener;
import com.veepoo.protocol.listener.data.IBodyComponentReadIdListener;
import com.veepoo.protocol.listener.data.IBreathDataListener;
import com.veepoo.protocol.listener.data.ICameraDataListener;
import com.veepoo.protocol.listener.data.IChantingDataListener;
import com.veepoo.protocol.listener.data.ICheckWearDataListener;
import com.veepoo.protocol.listener.data.IContactOptListener;
import com.veepoo.protocol.listener.data.ICountDownListener;
import com.veepoo.protocol.listener.data.ICuffBPDetectDataListener;
import com.veepoo.protocol.listener.data.ICustomDataOptListener;
import com.veepoo.protocol.listener.data.ICustomProtocolStateListener;
import com.veepoo.protocol.listener.data.ICustomSettingDataListener;
import com.veepoo.protocol.listener.data.IDeviceBTConnectionListener;
import com.veepoo.protocol.listener.data.IDeviceBTInfoListener;
import com.veepoo.protocol.listener.data.IDeviceControlPhoneModelState;
import com.veepoo.protocol.listener.data.IDeviceFuctionDataListener;
import com.veepoo.protocol.listener.data.IDeviceFunctionStatusChangeListener;
import com.veepoo.protocol.listener.data.IDeviceManualDetectDataListener;
import com.veepoo.protocol.listener.data.IDeviceRenameListener;
import com.veepoo.protocol.listener.data.IDeviceSportReportListener;
import com.veepoo.protocol.listener.data.IDrinkDataListener;
import com.veepoo.protocol.listener.data.IECGAutoReportListener;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.listener.data.IECGLightDataCallback;
import com.veepoo.protocol.listener.data.IECGPatchMultiLeadSettingListener;
import com.veepoo.protocol.listener.data.IECGPatchSpo2SwitchListener;
import com.veepoo.protocol.listener.data.IECGReadDataListener;
import com.veepoo.protocol.listener.data.IECGReadIdListener;
import com.veepoo.protocol.listener.data.IECGSingleLeadTestDurationListener;
import com.veepoo.protocol.listener.data.IECGSwitchListener;
import com.veepoo.protocol.listener.data.IEmotionDetectListener;
import com.veepoo.protocol.listener.data.IF2DebugListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.listener.data.IFindDeviceDatalistener;
import com.veepoo.protocol.listener.data.IFindDevicelistener;
import com.veepoo.protocol.listener.data.IFindPhonelistener;
import com.veepoo.protocol.listener.data.IFunSwitchListener;
import com.veepoo.protocol.listener.data.IG08ProjectPPGLightCallBack;
import com.veepoo.protocol.listener.data.IG15MessageListener;
import com.veepoo.protocol.listener.data.IG15QRCodeSendListener;
import com.veepoo.protocol.listener.data.IGNSSOptListener;
import com.veepoo.protocol.listener.data.IGameClockOptListener;
import com.veepoo.protocol.listener.data.IGpsLatLonDataListener;
import com.veepoo.protocol.listener.data.IGsrDetectListener;
import com.veepoo.protocol.listener.data.IHRVOriginDataListener;
import com.veepoo.protocol.listener.data.IHealthAlarmIntervalListener;
import com.veepoo.protocol.listener.data.IHealthLightListener;
import com.veepoo.protocol.listener.data.IHeartDataListener;
import com.veepoo.protocol.listener.data.IHeartWaringDataListener;
import com.veepoo.protocol.listener.data.IHrvAnalysisReportListener;
import com.veepoo.protocol.listener.data.IHrvDetectListener;
import com.veepoo.protocol.listener.data.IImageMsgPushListener;
import com.veepoo.protocol.listener.data.IJH76SNCodeOptListener;
import com.veepoo.protocol.listener.data.IJH76SNCodeReadListener;
import com.veepoo.protocol.listener.data.IKaaBaDataListener;
import com.veepoo.protocol.listener.data.IKnocknotifyListener;
import com.veepoo.protocol.listener.data.ILanguageDataListener;
import com.veepoo.protocol.listener.data.ILightDataCallBack;
import com.veepoo.protocol.listener.data.ILongSeatDataListener;
import com.veepoo.protocol.listener.data.ILowPowerListener;
import com.veepoo.protocol.listener.data.IMagneticTherapyListener;
import com.veepoo.protocol.listener.data.IMetDetectListener;
import com.veepoo.protocol.listener.data.IMotorOperateListener;
import com.veepoo.protocol.listener.data.IMtuChangeListener;
import com.veepoo.protocol.listener.data.IMusicControlListener;
import com.veepoo.protocol.listener.data.INetServer4gConfigListener;
import com.veepoo.protocol.listener.data.INewBodyComponentReportListener;
import com.veepoo.protocol.listener.data.INewECGDataReportListener;
import com.veepoo.protocol.listener.data.INightTurnWristeDataListener;
import com.veepoo.protocol.listener.data.IOriginData3Listener;
import com.veepoo.protocol.listener.data.IOriginDataListener;
import com.veepoo.protocol.listener.data.IOriginProgressListener;
import com.veepoo.protocol.listener.data.IPPGRawDataReadListener;
import com.veepoo.protocol.listener.data.IPPGRealTimeTransferOptListener;
import com.veepoo.protocol.listener.data.IPPGRealTimeTransmissionListener;
import com.veepoo.protocol.listener.data.IPPGSwitchOperaterListener;
import com.veepoo.protocol.listener.data.IPersonInfoDataListener;
import com.veepoo.protocol.listener.data.IPhoneListener;
import com.veepoo.protocol.listener.data.IPressureDetectListener;
import com.veepoo.protocol.listener.data.IPttModelStateListener;
import com.veepoo.protocol.listener.data.IPwdCheckTimeoutListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.listener.data.IQH15GnssLocationListener;
import com.veepoo.protocol.listener.data.IQH15HealthDataOptListener;
import com.veepoo.protocol.listener.data.IQH15SafetyProtectionListener;
import com.veepoo.protocol.listener.data.IQX17DataAcquisitionListener;
import com.veepoo.protocol.listener.data.IQX17DataAcquisitionStateListener;
import com.veepoo.protocol.listener.data.IRRIntervalProgressListener;
import com.veepoo.protocol.listener.data.IReadIMEIInfoListener;
import com.veepoo.protocol.listener.data.IReadProductionInfoListener;
import com.veepoo.protocol.listener.data.IRemindEventListener;
import com.veepoo.protocol.listener.data.IReportGpsDataListener;
import com.veepoo.protocol.listener.data.IResponseListener;
import com.veepoo.protocol.listener.data.ISOSCallTimesListener;
import com.veepoo.protocol.listener.data.ISOSListener;
import com.veepoo.protocol.listener.data.ISafetyProtectionOptListener;
import com.veepoo.protocol.listener.data.IScreenLightListener;
import com.veepoo.protocol.listener.data.IScreenLightTimeListener;
import com.veepoo.protocol.listener.data.IScreenStyleListener;
import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.listener.data.ISocialMsgDataListener;
import com.veepoo.protocol.listener.data.ISpo2hBreathBreakRemainListener;
import com.veepoo.protocol.listener.data.ISpo2hDataListener;
import com.veepoo.protocol.listener.data.ISpo2hOriginDataListener;
import com.veepoo.protocol.listener.data.ISportControlOptListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.listener.data.ISportModelDataCrcListener;
import com.veepoo.protocol.listener.data.ISportModelOriginListener;
import com.veepoo.protocol.listener.data.ISportModelStateListener;
import com.veepoo.protocol.listener.data.ITCMDataListener;
import com.veepoo.protocol.listener.data.ITcmDiagnosisDetectListener;
import com.veepoo.protocol.listener.data.ITemptureDataListener;
import com.veepoo.protocol.listener.data.ITemptureDetectDataListener;
import com.veepoo.protocol.listener.data.ITextAlarmDataListener;
import com.veepoo.protocol.listener.data.ITextImageTransferListener;
import com.veepoo.protocol.listener.data.ITextMsgPushListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormCustomListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormImagePushListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoListener;
import com.veepoo.protocol.listener.data.IUIOprateListener;
import com.veepoo.protocol.listener.data.IUiUpdateListener;
import com.veepoo.protocol.listener.data.IWearHandConfigListener;
import com.veepoo.protocol.listener.data.IWeatherStatusDataListener;
import com.veepoo.protocol.listener.data.IWomenDataListener;
import com.veepoo.protocol.listener.data.IWorldClockOptListener;
import com.veepoo.protocol.listener.data.IYM23ProTestStatusListener;
import com.veepoo.protocol.listener.data.IYM23ProWearReportListener;
import com.veepoo.protocol.listener.data.IZT163DeviceAlwaysOffScreenOptListener;
import com.veepoo.protocol.listener.data.OnAIConfigOptListener;
import com.veepoo.protocol.listener.data.OnAIDialOptListener;
import com.veepoo.protocol.listener.data.OnAIQAOptListener;
import com.veepoo.protocol.listener.data.OnAiDialPreviewSendListener;
import com.veepoo.protocol.listener.data.OnDeviceAlarm2ChangedListener;
import com.veepoo.protocol.listener.data.OnOpusDecode2PcmListener;
import com.veepoo.protocol.listener.oad.OnFindOadDeviceListener;
import com.veepoo.protocol.listener.oad.OnGetOadVersionListener;
import com.veepoo.protocol.listener.oad.OnUpdateCheckListener;
import com.veepoo.protocol.model.DayState;
import com.veepoo.protocol.model.G15QRCode;
import com.veepoo.protocol.model.datas.AIDeviceConfigBean;
import com.veepoo.protocol.model.datas.AccelerationData;
import com.veepoo.protocol.model.datas.AlarmData2;
import com.veepoo.protocol.model.datas.AutoMeasureData;
import com.veepoo.protocol.model.datas.BTInfo;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.Contact;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.datas.GameClockBean;
import com.veepoo.protocol.model.datas.GnssLocationData;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.HealthAlarmInterval;
import com.veepoo.protocol.model.datas.HealthRemind;
import com.veepoo.protocol.model.datas.LowPowerData;
import com.veepoo.protocol.model.datas.MagneticTherapy;
import com.veepoo.protocol.model.datas.MealInfo;
import com.veepoo.protocol.model.datas.MusicData;
import com.veepoo.protocol.model.datas.NetworkSever4gConfigInfo;
import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.PersonInfoData;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.datas.QH15HealthData;
import com.veepoo.protocol.model.datas.QH15SecurityProtectionModel;
import com.veepoo.protocol.model.datas.RRLorenzInfo;
import com.veepoo.protocol.model.datas.SleepData;
import com.veepoo.protocol.model.datas.TextAlarmData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.UICustomSetData;
import com.veepoo.protocol.model.datas.UIDataAGPS;
import com.veepoo.protocol.model.datas.UIDataCustom;
import com.veepoo.protocol.model.datas.UIDataG15Img;
import com.veepoo.protocol.model.datas.UIDataImagePush;
import com.veepoo.protocol.model.datas.UIDataServer;
import com.veepoo.protocol.model.datas.WeatherData2;
import com.veepoo.protocol.model.datas.WorldClock;
import com.veepoo.protocol.model.datas.weather.WeatherData;
import com.veepoo.protocol.model.enums.DebugCmd;
import com.veepoo.protocol.model.enums.DeviceManualDataType;
import com.veepoo.protocol.model.enums.EAdcType;
import com.veepoo.protocol.model.enums.EAllSetType;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.EBloodGlucoseDetectModel;
import com.veepoo.protocol.model.enums.ECPUPlatform;
import com.veepoo.protocol.model.enums.ECpuType;
import com.veepoo.protocol.model.enums.EEcgDataType;
import com.veepoo.protocol.model.enums.EEcgPatchSpo2Switch;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EGnssOptType;
import com.veepoo.protocol.model.enums.EHealthAlarmType;
import com.veepoo.protocol.model.enums.EHealthLightStatus;
import com.veepoo.protocol.model.enums.EJH58MeasurementState;
import com.veepoo.protocol.model.enums.EJH76SNCodeError;
import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EMiniCheckupTestErrorCode;
import com.veepoo.protocol.model.enums.EMotorLevel;
import com.veepoo.protocol.model.enums.EMultiAlarmOprate;
import com.veepoo.protocol.model.enums.EPwdStatus;
import com.veepoo.protocol.model.enums.EQH15ComplianceType;
import com.veepoo.protocol.model.enums.EQX17VibrationMode;
import com.veepoo.protocol.model.enums.ERemindEvent;
import com.veepoo.protocol.model.enums.ERenameError;
import com.veepoo.protocol.model.enums.ESocailMsg;
import com.veepoo.protocol.model.enums.ESportControlType;
import com.veepoo.protocol.model.enums.ESportType;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.model.enums.EUiUpdateError;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.model.enums.EWearHandMode;
import com.veepoo.protocol.model.enums.EYM23ProOptStatus;
import com.veepoo.protocol.model.enums.EYM23ProTestStatus;
import com.veepoo.protocol.model.enums.HealthRemindType;
import com.veepoo.protocol.model.enums.MagneticTherapyType;
import com.veepoo.protocol.model.enums.PPGSwitchStatus;
import com.veepoo.protocol.model.enums.PPGTestMode;
import com.veepoo.protocol.model.enums.TCMType;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.model.settings.AlarmSetting;
import com.veepoo.protocol.model.settings.AllSetSetting;
import com.veepoo.protocol.model.settings.AutoDetectStateSetting;
import com.veepoo.protocol.model.settings.BpSetting;
import com.veepoo.protocol.model.settings.BreathBreakRemindSetting;
import com.veepoo.protocol.model.settings.ChantingSetting;
import com.veepoo.protocol.model.settings.CheckWearSetting;
import com.veepoo.protocol.model.settings.ContentSetting;
import com.veepoo.protocol.model.settings.ContentSocailSetting;
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
import com.veepoo.protocol.model.settings.OadSetting;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.model.settings.ReadSleepSetting;
import com.veepoo.protocol.model.settings.ScreenSetting;
import com.veepoo.protocol.model.settings.SoldierContentSetting;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import com.veepoo.protocol.model.settings.WeatherStatusSetting;
import com.veepoo.protocol.model.settings.WomenSetting;
import com.veepoo.protocol.multi_lead.data.ECGPatchMultiLeadSettingData;
import com.veepoo.protocol.multi_lead.data.MultiEcgConfig;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchSettingType;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchSwitch;
import com.veepoo.protocol.multi_lead.listener.IECGMultiLeadDetectListener;
import com.veepoo.protocol.multi_lead.listener.IECGPatchAutoDetectListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgDetectListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgModelStateListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgReadDataListener;
import com.veepoo.protocol.nordic.McuMgrOtaManager;
import com.veepoo.protocol.nordic.OnMcuMgrOtaListener;
import com.veepoo.protocol.operate.AIFunctionOpt;
import com.veepoo.protocol.operate.vp_be;
import com.veepoo.protocol.operate.vp_bh;
import com.veepoo.protocol.operate.vp_bk;
import com.veepoo.protocol.operate.vp_bl;
import com.veepoo.protocol.operate.vp_bm;
import com.veepoo.protocol.operate.vp_bn;
import com.veepoo.protocol.operate.vp_bo;
import com.veepoo.protocol.operate.vp_bp;
import com.veepoo.protocol.operate.vp_bq;
import com.veepoo.protocol.operate.vp_bs;
import com.veepoo.protocol.operate.vp_bt;
import com.veepoo.protocol.operate.vp_bu;
import com.veepoo.protocol.operate.vp_bv;
import com.veepoo.protocol.operate.vp_bw;
import com.veepoo.protocol.operate.vp_bx;
import com.veepoo.protocol.operate.vp_bz;
import com.veepoo.protocol.operate.vp_ca;
import com.veepoo.protocol.operate.vp_cc;
import com.veepoo.protocol.operate.vp_cd;
import com.veepoo.protocol.operate.vp_cf;
import com.veepoo.protocol.operate.vp_ch;
import com.veepoo.protocol.operate.vp_cj;
import com.veepoo.protocol.operate.vp_cl;
import com.veepoo.protocol.operate.vp_cq;
import com.veepoo.protocol.operate.vp_cr;
import com.veepoo.protocol.operate.vp_cs;
import com.veepoo.protocol.operate.vp_cw;
import com.veepoo.protocol.operate.vp_cx;
import com.veepoo.protocol.operate.vp_cz;
import com.veepoo.protocol.operate.vp_da;
import com.veepoo.protocol.operate.vp_de;
import com.veepoo.protocol.operate.vp_dg;
import com.veepoo.protocol.operate.vp_di;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.Alarm2Util;
import com.veepoo.protocol.util.FunctionCheckUtil;
import com.veepoo.protocol.util.TextAlarmSp;
import com.veepoo.protocol.util.UiUpdateUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.opus.AiOpus2PcmManager;
import com.veepoo.protocol.util.thread.HBThreadPools;
import com.veepoo.protocol.util.thread.ThreadPoolsUtil;
import com.veepoo.protocol.zk.IZkOtaUpgradeListener;
import com.veepoo.protocol.zk.ZkOtaManager;
import io.runtime.mcumgr.dfu.mcuboot.FirmwareUpgradeManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager.class */
public final class VPOperateManager {
    public static final int VPPROTOCOL_TYPE = 15;
    public static final String VPPROTOCOL_VERSION = "2.3.96.15";
    private static volatile VPOperateManager vp_dx;
    private static BluetoothClient vp_dy;

    @SuppressLint({"StaticFieldLeak"})
    public static Context mContext;
    public static final int CODE_CONNECTION_CONFIRMATION_TIMEOUT = 9876;
    private IDeviceFunctionStatusChangeListener vp_d;
    IBleNotifyResponse vp_e;
    ISleepDataListener vp_ay;
    ITemptureDataListener vp_az;
    IOriginDataListener vp_ba;
    IOriginData3Listener vp_bb;
    IDeviceManualDetectDataListener vp_bc;
    IAlarm2DataListListener vp_bd;
    ITextAlarmDataListener vp_be;
    ISportModelOriginListener vp_bf;
    ISpo2hOriginDataListener vp_bg;
    IHRVOriginDataListener vp_bh;
    vp_bh vp_bi;
    private String vp_bj;
    IDeviceControlPhoneModelState vp_bn;
    IKnocknotifyListener vp_bo;
    IMusicControlListener vp_bp;
    ISOSListener vp_bq;
    IPttModelStateListener vp_br;
    IMultiEcgModelStateListener vp_bs;
    IPhoneListener vp_bt;
    ISocialMsgDataListener vp_bu;
    IG15MessageListener vp_bv;
    IG15QRCodeSendListener vp_bw;
    IRRIntervalProgressListener vp_bx;
    IECGSwitchListener vp_by;
    IFunSwitchListener vp_bz;
    IF2DebugListener vp_ca;
    IDeviceBTConnectionListener vp_cb;
    IDeviceBTInfoListener vp_cc;
    IHealthRemindListener vp_cd;
    IContactOptListener vp_ce;
    IWorldClockOptListener vp_cf;
    IMotorOperateListener vp_cg;
    ISOSCallTimesListener vp_ch;
    IG08ProjectPPGLightCallBack vp_ci;
    IPPGRealTimeTransmissionListener vp_cj;
    IMagneticTherapyListener vp_ck;
    IAutoMeasureSettingDataListener vp_cl;
    IPwdCheckTimeoutListener vp_cm;
    IScreenStyleListener vp_cn;
    ICustomProtocolStateListener vp_co;
    ICustomDataOptListener vp_cp;
    ITCMDataListener vp_cq;
    IReadIMEIInfoListener vp_cr;
    IQH15HealthDataOptListener vp_cs;
    IJH76SNCodeReadListener vp_ct;
    IJH76SNCodeOptListener vp_cu;
    IYM23ProWearReportListener vp_cv;
    IYM23ProTestStatusListener vp_cw;
    IECGSingleLeadTestDurationListener vp_cx;
    IECGPatchSpo2SwitchListener vp_cy;
    IECGPatchAutoDetectListener vp_cz;
    IECGPatchMultiLeadSettingListener vp_da;
    IWearHandConfigListener vp_db;
    private FunctionSocailMsgData vp_dc;
    IBloodGlucoseChangeListener vp_de;
    private BluetoothDevice vp_di;
    private vp_cc vp_dw;
    static com.veepoo.protocol.vp_b vp_dz = new com.veepoo.protocol.vp_b();
    static VpSpSaveUtil a = null;
    static VpSpGetUtil b = null;
    static FunctionCheckUtil c = null;
    static boolean d = false;
    static boolean e = false;
    static boolean f = false;
    static boolean g = false;
    static boolean h = false;
    static boolean i = false;
    static boolean j = true;
    public static volatile boolean isPasswordConfirmBack = false;
    public static volatile boolean isFirstGetBDReport = false;
    protected static volatile boolean isFindJLService = false;
    public static volatile boolean isUseDefaultBTType = false;
    public static int customBTConnectType = 0;
    final com.veepoo.protocol.vp_c vp_a = new com.veepoo.protocol.vp_c();
    final Handler vp_b = new Handler(Looper.getMainLooper());
    final Handler vp_c = new vp_k(Looper.getMainLooper());
    vp_cl vp_f = new vp_cl();
    vp_cj vp_g = new vp_cj();
    vp_de vp_h = new vp_de();
    vp_bp vp_i = new vp_bp();
    vp_bv vp_j = new vp_bv();
    vp_bu vp_k = new vp_bu();
    com.veepoo.protocol.operate.vp_ab vp_l = new com.veepoo.protocol.operate.vp_ab();
    vp_cx vp_m = new vp_cx();
    vp_bo vp_n = new vp_bo();
    vp_cz vp_o = new vp_cz();
    vp_cs vp_p = new vp_cs();
    vp_cq vp_q = new vp_cq();
    com.veepoo.protocol.operate.vp_ai vp_r = new com.veepoo.protocol.operate.vp_ai();
    vp_cw vp_s = new vp_cw();
    com.veepoo.protocol.multi_lead.operate.vp_a vp_t = new com.veepoo.protocol.multi_lead.operate.vp_a();
    vp_bx vp_u = new vp_bx();
    com.veepoo.protocol.operate.vp_ah vp_v = new com.veepoo.protocol.operate.vp_ah();
    com.veepoo.protocol.multi_lead.operate.vp_e vp_w = new com.veepoo.protocol.multi_lead.operate.vp_e();
    com.veepoo.protocol.operate.vp_az vp_x = new com.veepoo.protocol.operate.vp_az();
    com.veepoo.protocol.operate.vp_at vp_y = new com.veepoo.protocol.operate.vp_at();
    com.veepoo.protocol.operate.vp_au vp_z = new com.veepoo.protocol.operate.vp_au();
    vp_cd vp_aa = new vp_cd();
    com.veepoo.protocol.operate.vp_w vp_ab = new com.veepoo.protocol.operate.vp_w();
    com.veepoo.protocol.operate.vp_v vp_ac = new com.veepoo.protocol.operate.vp_v();
    com.veepoo.protocol.operate.vp_ae vp_ad = new com.veepoo.protocol.operate.vp_ae();
    com.veepoo.protocol.operate.vp_al vp_ae = new com.veepoo.protocol.operate.vp_al();
    com.veepoo.protocol.operate.vp_as vp_af = new com.veepoo.protocol.operate.vp_as();
    com.veepoo.protocol.operate.vp_z vp_ag = new com.veepoo.protocol.operate.vp_z();
    com.veepoo.protocol.operate.vp_j vp_ah = new com.veepoo.protocol.operate.vp_j();
    com.veepoo.protocol.operate.vp_g vp_ai = new com.veepoo.protocol.operate.vp_g();
    com.veepoo.protocol.operate.vp_bc vp_aj = new com.veepoo.protocol.operate.vp_bc();
    com.veepoo.protocol.operate.vp_m vp_ak = new com.veepoo.protocol.operate.vp_m();
    vp_bz vp_al = new vp_bz();
    vp_be vp_am = new vp_be();
    vp_bl vp_an = new vp_bl();
    com.veepoo.protocol.operate.vp_am vp_ao = new com.veepoo.protocol.operate.vp_am();
    com.veepoo.protocol.operate.vp_an vp_ap = new com.veepoo.protocol.operate.vp_an();
    com.veepoo.protocol.operate.vp_ay vp_aq = new com.veepoo.protocol.operate.vp_ay();
    com.veepoo.protocol.operate.vp_k vp_ar = new com.veepoo.protocol.operate.vp_k();
    com.veepoo.protocol.operate.vp_ba vp_as = new com.veepoo.protocol.operate.vp_ba();
    vp_bk vp_at = new vp_bk();
    com.veepoo.protocol.operate.vp_c vp_au = new com.veepoo.protocol.operate.vp_c();
    com.veepoo.protocol.operate.vp_t vp_av = new com.veepoo.protocol.operate.vp_t();
    vp_bq vp_aw = new vp_bq();
    private boolean vp_ax = true;
    private boolean vp_bk = false;
    private ECpuType vp_bl = ECpuType.NORIC;
    private ECPUPlatform vp_bm = ECPUPlatform.NORDIC;
    private volatile boolean vp_dd = true;
    private boolean vp_df = false;
    private int vp_dg = 23;
    boolean vp_dh = false;
    IABleConnectStatusListener vp_dj = null;
    vp_bd vp_dk = null;
    vp_bc vp_dl = new vp_bc();
    vp_bb vp_dm = new vp_bb();
    private int vp_dn = 12;
    private final Handler vp_do = new vp_d(Looper.getMainLooper());
    int vp_dp = 0;
    int vp_dq = 0;
    int vp_dr = 0;
    int vp_ds = 0;
    int vp_dt = 0;
    IBleWriteResponse vp_du = new vp_o();
    private boolean vp_dv = false;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_a.class */
    class vp_a implements Runnable {
        final /* synthetic */ com.veepoo.protocol.util.vp_s vp_a;

        vp_a(com.veepoo.protocol.util.vp_s vp_sVar) {
            this.vp_a = vp_sVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            this.vp_a.vp_c();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_aa.class */
    class vp_aa implements Runnable {
        final /* synthetic */ OnMcuMgrOtaListener vp_a;
        final /* synthetic */ String vp_b;

        vp_aa(OnMcuMgrOtaListener onMcuMgrOtaListener, String str) {
            this.vp_a = onMcuMgrOtaListener;
            this.vp_b = str;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            McuMgrOtaManager.getInstance().addMcuMgrOtaListener(this.vp_a);
            File file = new File(this.vp_b);
            if (!file.exists() || !file.isFile() || file.length() <= 10) {
                new StringBuilder().append("ota文件异常，请检查升级文件：").append(this.vp_b);
                return;
            }
            byte[] fileToBytes = VPOperateManager.readFileToBytes(this.vp_b);
            if (fileToBytes != null) {
                McuMgrOtaManager.getInstance().upgrade(fileToBytes, FirmwareUpgradeManager.Mode.CONFIRM_ONLY, false, 20000, 1, 4);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ab.class */
    class vp_ab implements ThreadPoolsUtil.SmartCall {
        final /* synthetic */ List vp_a;
        final /* synthetic */ IHrvAnalysisReportListener vp_b;
        final /* synthetic */ String vp_c;

        vp_ab(List list, IHrvAnalysisReportListener iHrvAnalysisReportListener, String str) {
            this.vp_a = list;
            this.vp_b = iHrvAnalysisReportListener;
            this.vp_c = str;
        }

        @Override // com.veepoo.protocol.util.thread.ThreadPoolsUtil.SmartCall
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public List doBackground() {
            return com.veepoo.protocol.util.vp_u.vp_a(VPOperateManager.mContext, this.vp_a);
        }

        @Override // com.veepoo.protocol.util.thread.ThreadPoolsUtil.SmartCall
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onDo(List list) {
            IHrvAnalysisReportListener iHrvAnalysisReportListener = this.vp_b;
            if (iHrvAnalysisReportListener != null) {
                iHrvAnalysisReportListener.onHrvAnalysisReport(this.vp_c, list);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ac.class */
    class vp_ac implements BleWriteResponse {
        final /* synthetic */ ITextMsgPushListener vp_a;

        vp_ac(ITextMsgPushListener iTextMsgPushListener) {
            this.vp_a = iTextMsgPushListener;
        }

        public void onResponse(int i) {
            if (i != 0) {
                this.vp_a.onTextMsgPushFailed();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ad.class */
    class vp_ad implements ITextImageTransferListener {
        final /* synthetic */ ITextMsgPushListener vp_a;
        final /* synthetic */ String vp_b;
        final /* synthetic */ BleWriteResponse vp_c;

        vp_ad(ITextMsgPushListener iTextMsgPushListener, String str, BleWriteResponse bleWriteResponse) {
            this.vp_a = iTextMsgPushListener;
            this.vp_b = str;
            this.vp_c = bleWriteResponse;
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferComplete(boolean z) {
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopFailed(boolean z) {
            this.vp_a.onTextMsgPushFailed();
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopSuccess() {
            VPOperateManager.this.vp_a(this.vp_b, this.vp_c, this.vp_a);
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartFailed(boolean z) {
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartSuccess(boolean z) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ae.class */
    class vp_ae implements ITextImageTransferListener {
        final /* synthetic */ String vp_a;
        final /* synthetic */ ITextMsgPushListener vp_b;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ae$vp_a.class */
        class vp_a implements IBleWriteResponse {
            vp_a() {
            }

            @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
            public void onResponse(int i) {
                if (i == 0) {
                    BluetoothLog.i("写入成功");
                }
            }
        }

        vp_ae(String str, ITextMsgPushListener iTextMsgPushListener) {
            this.vp_a = str;
            this.vp_b = iTextMsgPushListener;
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartSuccess(boolean z) {
            new StringBuilder().append("**onTextImageTransferStartSuccess》》》》》 isTextTransfer = ").append(z);
            if (z) {
                VPOperateManager.getInstance().sendSocialMsgContent(new vp_a(), new ContentSocailSetting(ESocailMsg.APP_CUSTOM_MSG, "", this.vp_a));
            }
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartFailed(boolean z) {
            if (z) {
                this.vp_b.onTextMsgPushFailed();
            }
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopSuccess() {
            this.vp_b.onTextMsgPushFailed();
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopFailed(boolean z) {
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferComplete(boolean z) {
            if (z) {
                this.vp_b.onTextMsgPushSuccess();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_af.class */
    class vp_af implements BleWriteResponse {
        final /* synthetic */ IImageMsgPushListener vp_a;

        vp_af(IImageMsgPushListener iImageMsgPushListener) {
            this.vp_a = iImageMsgPushListener;
        }

        public void onResponse(int i) {
            if (i != 0) {
                this.vp_a.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.UNKNOWN);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ag.class */
    class vp_ag implements BleNotifyResponse {
        vp_ag() {
        }

        public void onResponse(int i) {
            new StringBuilder().append("**设置G08W项目PPG数据监听开启 code = ").append(i);
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            BluetoothLog.i("**G08W项目PPG数据监听通知 notify onNotify value = " + com.veepoo.protocol.util.vp_g.vp_b(bArr));
            if (VPOperateManager.this.vp_ci != null) {
                ArrayList arrayList = new ArrayList();
                int length = bArr.length - 4;
                for (int i = 1; i < length; i += 3) {
                    int i2 = (bArr[i] << 16) | (bArr[i + 1] << 8) | bArr[i + 2];
                    if (i2 != 526344) {
                        arrayList.add(Integer.valueOf(vp_bh.vp_a(i2)));
                    }
                }
                byte b = bArr[0];
                int i3 = b == -128 ? 0 : -1;
                if (b == -125) {
                    i3 = 1;
                }
                if (b == -124) {
                    i3 = 2;
                }
                VPOperateManager.this.vp_ci.onPPGLightCallBack(i3, arrayList);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ah.class */
    class vp_ah implements ITextImageTransferListener {
        final /* synthetic */ IImageMsgPushListener vp_a;
        final /* synthetic */ String vp_b;

        vp_ah(IImageMsgPushListener iImageMsgPushListener, String str) {
            this.vp_a = iImageMsgPushListener;
            this.vp_b = str;
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferComplete(boolean z) {
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopFailed(boolean z) {
            this.vp_a.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.TERMINATION);
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStopSuccess() {
            VPOperateManager.this.vp_a(this.vp_b, this.vp_a);
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartFailed(boolean z) {
        }

        @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
        public void onTextImageTransferStartSuccess(boolean z) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ai.class */
    class vp_ai implements IUIBaseInfoFormImagePushListener {
        final /* synthetic */ String vp_a;
        final /* synthetic */ IImageMsgPushListener vp_b;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ai$vp_a.class */
        class vp_a implements BleWriteResponse {
            vp_a() {
            }

            public void onResponse(int i) {
                if (i != 0) {
                    vp_ai.this.vp_b.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.UNKNOWN);
                }
            }
        }

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ai$vp_b.class */
        class vp_b implements ITextImageTransferListener {
            final /* synthetic */ InputStream vp_a;

            /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ai$vp_b$vp_a.class */
            class vp_a implements IUiUpdateListener {
                vp_a() {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onUiUpdateStart() {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onStartClearCache(int i) {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onClearCacheProgress(int i, int i2, int i3) {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onFinishClearCache() {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onUiUpdateProgress(int i, int i2, int i3) {
                    vp_ai.this.vp_b.onImageMsgPushProgress(i, i2, i3);
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onUiUpdateSuccess() {
                }

                @Override // com.veepoo.protocol.listener.data.IUiUpdateListener
                public void onUiUpdateFail(EUiUpdateError eUiUpdateError) {
                    IImageMsgPushListener.ErrorCode errorCode = IImageMsgPushListener.ErrorCode.UNKNOWN;
                    switch (vp_av.vp_a[eUiUpdateError.ordinal()]) {
                        case 1:
                            errorCode = IImageMsgPushListener.ErrorCode.LISTENER_IS_NULL;
                            break;
                        case 2:
                            errorCode = IImageMsgPushListener.ErrorCode.NEED_READ_BASE_INFO;
                            break;
                        case 3:
                            errorCode = IImageMsgPushListener.ErrorCode.FILE_NOT_EXIST;
                            break;
                        case 4:
                            errorCode = IImageMsgPushListener.ErrorCode.LOW_BATTERY;
                            break;
                        case 5:
                            errorCode = IImageMsgPushListener.ErrorCode.INTO_UPDATE_MODE_FAIL;
                            break;
                        case 6:
                            errorCode = IImageMsgPushListener.ErrorCode.FILE_LENGTH_NOT_4_POWER;
                            break;
                        case 7:
                            errorCode = IImageMsgPushListener.ErrorCode.CHECK_CRC_FAIL;
                            break;
                    }
                    vp_ai.this.vp_b.onImageMsgPushFailed(errorCode);
                }
            }

            vp_b(InputStream inputStream) {
                this.vp_a = inputStream;
            }

            @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
            public void onTextImageTransferStartSuccess(boolean z) {
                new StringBuilder().append("-pushImageMsg- : onTextImageTransferStartSuccess isTextTransfer = ").append(z);
                if (z) {
                    return;
                }
                UiUpdateUtil.getInstance().startSetUiStream(EUIFromType.IMAGE_PUSH, this.vp_a, new vp_a());
            }

            @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
            public void onTextImageTransferStartFailed(boolean z) {
                if (z) {
                    return;
                }
                vp_ai.this.vp_b.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.UNKNOWN);
            }

            @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
            public void onTextImageTransferStopSuccess() {
                vp_ai.this.vp_b.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.TERMINATION);
            }

            @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
            public void onTextImageTransferStopFailed(boolean z) {
            }

            @Override // com.veepoo.protocol.listener.data.ITextImageTransferListener
            public void onTextImageTransferComplete(boolean z) {
                vp_ai.this.vp_b.onImageMsgPushSuccess();
            }
        }

        vp_ai(String str, IImageMsgPushListener iImageMsgPushListener) {
            this.vp_a = str;
            this.vp_b = iImageMsgPushListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoFormImagePushListener
        public void onBaseUiInfoFormImagePush(UIDataImagePush uIDataImagePush) {
            new StringBuilder().append("-pushImageMsg-图文推送 : ----图片推送配置信息：").append(uIDataImagePush);
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.vp_a);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            int byteCount = bitmapDecodeFile.getByteCount();
            Log.d("-pushImageMsg-图文推送", "本地裁剪的图片路径：" + this.vp_a + " \n 宽高:" + width + " / " + height + "\n大小:" + byteCount);
            if (width > uIDataImagePush.getWidth() || height > uIDataImagePush.getHeight()) {
                new StringBuilder().append("-pushImageMsg-图文推送 : 错误：裁剪的表盘照片尺寸不符合。\n当前手表的表盘尺寸(宽/高)：").append(uIDataImagePush.getWidth()).append(" / ").append(uIDataImagePush.getHeight()).append("\n本地裁剪照片").append(this.vp_a).append(" :尺寸(宽/高) ").append(width).append(" / ").append(height);
                this.vp_b.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.IMAGE_SIZE_ERROR);
                return;
            }
            byte[] rawImageData = ImageUtils.getRawImageData(bitmapDecodeFile);
            VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).isZK569XDevice();
            byte[] bArrRawToPar = ParTool.rawToPar(rawImageData, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), false, false, false);
            new StringBuilder().append("-pushImageMsg-图文推送 : ----当前图片最终发送的数据总数：").append(bArrRawToPar.length);
            if (bArrRawToPar.length > uIDataImagePush.getDataCanSendLength()) {
                new StringBuilder().append("-pushImageMsg-图文推送 : ----当前图片尺寸大于最大可发生尺寸：").append(byteCount).append(" - ").append(uIDataImagePush.getDataCanSendLength()).append(" = ").append(byteCount - uIDataImagePush.getDataCanSendLength());
                this.vp_b.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.IMAGE_TOO_LARGE);
            } else {
                vp_da.vp_g.vp_a().vp_b(VPOperateManager.vp_dy, VPOperateManager.this.vp_bj, new vp_a(), new vp_b(new ByteArrayInputStream(bArrRawToPar)));
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_aj.class */
    class vp_aj implements BleNotifyResponse {
        final /* synthetic */ IPPGRealTimeTransmissionListener vp_a;

        vp_aj(IPPGRealTimeTransmissionListener iPPGRealTimeTransmissionListener) {
            this.vp_a = iPPGRealTimeTransmissionListener;
        }

        public void onResponse(int i) {
            new StringBuilder().append("**设置PPG高频实时传输数据监听开启 code = ").append(i);
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            BluetoothLog.i("**PPG高频实时传输数据监听通知 notify onNotify value = " + com.veepoo.protocol.util.vp_g.vp_b(bArr));
            if (this.vp_a != null) {
                if (bArr[0] == -128) {
                    ArrayList arrayList = new ArrayList();
                    int length = bArr.length - 4;
                    for (int i = 1; i < length; i += 3) {
                        int i2 = (bArr[i] << 16) | (bArr[i + 1] << 8) | bArr[i + 2];
                        if (i2 != 526344) {
                            arrayList.add(Integer.valueOf(vp_bh.vp_a(i2)));
                        }
                    }
                    this.vp_a.onGreenLightDataReport(arrayList);
                }
                if (bArr[0] == -119) {
                    ArrayList arrayList2 = new ArrayList();
                    int length2 = bArr.length - 4;
                    for (int i3 = 1; i3 < length2; i3 += 6) {
                        arrayList2.add(new AccelerationData(com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 1], bArr[i3]), com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 3], bArr[i3 + 2]), com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 5], bArr[i3 + 4])));
                    }
                    this.vp_a.onAccelerationDataReport(arrayList2);
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ak.class */
    class vp_ak implements BleUnnotifyResponse {
        vp_ak() {
        }

        public void onResponse(int i) {
            new StringBuilder().append("**设置PPG高频实时传输数据监听关闭 code = ").append(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_al.class */
    class vp_al implements IPPGRealTimeTransferOptListener {

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_al$vp_a.class */
        class vp_a implements BleNotifyResponse {
            vp_a() {
            }

            public void onResponse(int i) {
                new StringBuilder().append("**设置PPG高频实时传输数据监听开启 addDevicePPGRealTimeTransferListener code = ").append(i);
                vp_bw.vp_m.vp_a().vp_a(i == 0);
            }

            public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
                BluetoothLog.i("**PPG高频实时传输数据监听通知 addDevicePPGRealTimeTransferListener notify onNotify value = " + com.veepoo.protocol.util.vp_g.vp_b(bArr));
                if (VPOperateManager.this.vp_cj != null) {
                    if (bArr[0] == -128) {
                        ArrayList arrayList = new ArrayList();
                        int length = bArr.length - 4;
                        for (int i = 1; i < length; i += 3) {
                            int i2 = (bArr[i] << 16) | (bArr[i + 1] << 8) | bArr[i + 2];
                            if (i2 != 526344) {
                                arrayList.add(Integer.valueOf(vp_bh.vp_a(i2)));
                            }
                        }
                        VPOperateManager.this.vp_cj.onGreenLightDataReport(arrayList);
                    }
                    if (bArr[0] == -119) {
                        ArrayList arrayList2 = new ArrayList();
                        int length2 = bArr.length - 4;
                        for (int i3 = 1; i3 < length2; i3 += 6) {
                            arrayList2.add(new AccelerationData(com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 1], bArr[i3]), com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 3], bArr[i3 + 2]), com.veepoo.protocol.util.vp_g.vp_d(bArr[i3 + 5], bArr[i3 + 4])));
                        }
                        VPOperateManager.this.vp_cj.onAccelerationDataReport(arrayList2);
                    }
                }
            }
        }

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_al$vp_b.class */
        class vp_b implements BleUnnotifyResponse {
            vp_b() {
            }

            public void onResponse(int i) {
                BluetoothLog.i("**ECG ADC 通知 unnotify onResponse code = " + i);
            }
        }

        vp_al() {
        }

        @Override // com.veepoo.protocol.listener.data.IPPGRealTimeTransferOptListener
        public void onDeviceRequestPPGRealTimeTransfer(boolean z) {
            new StringBuilder().append("**设置PPG高频实时传输数据监听开启 addDevicePPGRealTimeTransferListener 设备请求开启/关闭高频传输 >>> ").append(z);
            IPPGRealTimeTransmissionListener iPPGRealTimeTransmissionListener = VPOperateManager.this.vp_cj;
            if (iPPGRealTimeTransmissionListener != null) {
                iPPGRealTimeTransmissionListener.onDeviceRequestPPGRealTimeTransfer(z);
            }
            if (z) {
                VPOperateManager.vp_dy.notify(VPOperateManager.this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_a());
            } else {
                VPOperateManager.vp_dy.unnotify(VPOperateManager.this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_b());
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPPGRealTimeTransferOptListener
        public void onAppRequestPPGRealTimeTransfer(boolean z) {
            IPPGRealTimeTransmissionListener iPPGRealTimeTransmissionListener = VPOperateManager.this.vp_cj;
            if (iPPGRealTimeTransmissionListener != null) {
                iPPGRealTimeTransmissionListener.onAppRequestPPGRealTimeTransfer(z);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_am.class */
    class vp_am implements BleUnnotifyResponse {

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_am$vp_a.class */
        class vp_a implements BleNotifyResponse {
            vp_a() {
            }

            public void onResponse(int i) {
                new StringBuilder().append("**[QX17] ADC notify onResponse code=").append(i);
            }

            public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
                if (bArr == null || bArr.length <= 0 || bArr[0] != -105) {
                    return;
                }
                VPOperateManager.this.vp_a(bArr);
            }
        }

        vp_am() {
        }

        public void onResponse(int i) {
            new StringBuilder().append("**[QX17] ADC unnotify onResponse code=").append(i);
            VPOperateManager.vp_dy.notify(VPOperateManager.this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_a());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_an.class */
    class vp_an implements BleWriteResponse {
        final /* synthetic */ IBleWriteResponse vp_a;

        vp_an(IBleWriteResponse iBleWriteResponse) {
            this.vp_a = iBleWriteResponse;
        }

        public void onResponse(int i) {
            IBleWriteResponse iBleWriteResponse = this.vp_a;
            if (iBleWriteResponse != null) {
                iBleWriteResponse.onResponse(i);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ao.class */
    class vp_ao implements BleWriteResponse {
        final /* synthetic */ IBleWriteResponse vp_a;

        vp_ao(IBleWriteResponse iBleWriteResponse) {
            this.vp_a = iBleWriteResponse;
        }

        public void onResponse(int i) {
            IBleWriteResponse iBleWriteResponse = this.vp_a;
            if (iBleWriteResponse != null) {
                iBleWriteResponse.onResponse(i);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ap.class */
    class vp_ap implements BleWriteResponse {
        final /* synthetic */ IBleWriteResponse vp_a;

        vp_ap(IBleWriteResponse iBleWriteResponse) {
            this.vp_a = iBleWriteResponse;
        }

        public void onResponse(int i) {
            IBleWriteResponse iBleWriteResponse = this.vp_a;
            if (iBleWriteResponse != null) {
                iBleWriteResponse.onResponse(i);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_aq.class */
    class vp_aq implements BleWriteResponse {
        final /* synthetic */ IBleWriteResponse vp_a;

        vp_aq(IBleWriteResponse iBleWriteResponse) {
            this.vp_a = iBleWriteResponse;
        }

        public void onResponse(int i) {
            IBleWriteResponse iBleWriteResponse = this.vp_a;
            if (iBleWriteResponse != null) {
                iBleWriteResponse.onResponse(i);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ar.class */
    class vp_ar implements BleUnnotifyResponse {
        vp_ar() {
        }

        public void onResponse(int i) {
            BluetoothLog.i("**设置G08W项目PPG数据监听 unnotify onResponse code = " + i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_as.class */
    class vp_as implements IBleWriteResponse {
        vp_as() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_at.class */
    class vp_at implements BleUnnotifyResponse {
        final /* synthetic */ BleResponse vp_a;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_at$vp_a.class */
        class vp_a implements BleNotifyResponse {
            vp_a() {
            }

            public void onResponse(int i) {
                new StringBuilder().append("**[客户自定义透传] ADC notify onResponse code=").append(i);
                vp_at.this.vp_a.onResponse(i);
            }

            public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
                if (bArr == null || bArr.length <= 0 || bArr[0] != 4) {
                    return;
                }
                VPOperateManager.this.vp_cp.onAdcData(bArr);
            }
        }

        vp_at(BleResponse bleResponse) {
            this.vp_a = bleResponse;
        }

        public void onResponse(int i) {
            new StringBuilder().append("**[客户自定义透传] ADC unnotify onResponse code=").append(i);
            VPOperateManager.vp_dy.notify(VPOperateManager.this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_a());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_au.class */
    class vp_au implements BleUnnotifyResponse {
        final /* synthetic */ BleResponse vp_a;

        vp_au(BleResponse bleResponse) {
            this.vp_a = bleResponse;
        }

        public void onResponse(int i) {
            new StringBuilder().append("**[客户自定义透传] ADC unnotify onResponse code=").append(i);
            this.vp_a.onResponse(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_av.class */
    static /* synthetic */ class vp_av {
        static final /* synthetic */ int[] vp_a;
        static final /* synthetic */ int[] vp_b;

        static {
            int[] iArr = new int[EGnssOptType.values().length];
            vp_b = iArr;
            try {
                iArr[EGnssOptType.GNSS_SPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_b[EGnssOptType.SAFETY_PROTECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_b[EGnssOptType.SOS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[EUiUpdateError.values().length];
            vp_a = iArr2;
            try {
                iArr2[EUiUpdateError.LISTENTER_IS_NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[EUiUpdateError.NEED_READ_BASE_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[EUiUpdateError.FILE_UNEXIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[EUiUpdateError.LOW_BATTERY.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[EUiUpdateError.INTO_UPDATE_MODE_FAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[EUiUpdateError.FILE_LENGTH_NOT_4_POWER.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[EUiUpdateError.CHECK_CRC_FAIL.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_aw.class */
    class vp_aw implements BleNotifyResponse {
        vp_aw() {
        }

        public void onResponse(int i) {
            new StringBuilder().append("**ECG ADC 通知 notify onResponse code = ").append(i);
            BluetoothLog.i("**ECG ADC 通知 notify onResponse code = " + i);
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            BluetoothLog.i("**ECG ADC 通知 notify onNotify value = " + com.veepoo.protocol.util.vp_g.vp_b(bArr));
            VPOperateManager.this.vp_ad.vp_c(bArr);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ax.class */
    class vp_ax implements BleUnnotifyResponse {
        vp_ax() {
        }

        public void onResponse(int i) {
            BluetoothLog.i("**ECG ADC 通知 unnotify onResponse code = " + i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ay.class */
    class vp_ay implements BleMtuResponse {
        final /* synthetic */ int vp_a;
        final /* synthetic */ IMtuChangeListener vp_b;

        vp_ay(int i, IMtuChangeListener iMtuChangeListener) {
            this.vp_a = i;
            this.vp_b = iMtuChangeListener;
        }

        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Integer num) {
            int iIntValue = 0;
            new StringBuilder().append("-requestMtu-  MTU = ").append(num);
            if (i == 0) {
                int iIntValue2 = num.intValue();
                int i2 = this.vp_a;
                iIntValue = iIntValue2 > i2 ? i2 - 5 : num.intValue() - 5;
                VPOperateManager.this.vp_dg = iIntValue;
                VPOperateManager.a.saveMTUValue(iIntValue);
                McuMgrOtaManager.getInstance().setMtu(iIntValue);
            }
            this.vp_b.onChangeMtuLength(iIntValue);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_az.class */
    class vp_az implements IBleWriteResponse {
        vp_az() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_b.class */
    class vp_b implements SearchResponse {
        final /* synthetic */ SearchResponse vp_a;

        vp_b(SearchResponse searchResponse) {
            this.vp_a = searchResponse;
        }

        public void onSearchStarted() {
            this.vp_a.onSearchStarted();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
        /* JADX WARN: Type inference failed for: r0v2, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference failed for: r8v5 */
        public void onDeviceFounded(SearchResult searchResult) {
            ?? r8;
            if (searchResult == null) {
                return;
            }
            Log.e("VPOperateManager", "onDeviceFounded-------------> device = " + searchResult.toString());
            ?? IsBrandDevice = searchResult.scanRecord;
            boolean z = false;
            boolean z2 = false;
            try {
                r8 = z2;
                if (VpBleByteUtil.isBeyondVp(IsBrandDevice)) {
                    z = true;
                    IsBrandDevice = VpBleByteUtil.isBrandDevice(IsBrandDevice);
                    r8 = IsBrandDevice;
                }
            } catch (Exception unused) {
                IsBrandDevice.printStackTrace();
                z = false;
                r8 = z2;
            }
            boolean z3 = VPOperateManager.this.vp_dh;
            if ((!z3 || r8 == 0) && (!z || z3)) {
                return;
            }
            this.vp_a.onDeviceFounded(searchResult);
        }

        public void onSearchStopped() {
            this.vp_a.onSearchStopped();
        }

        public void onSearchCanceled() {
            this.vp_a.onSearchCanceled();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_ba.class */
    class vp_ba implements ICallBack {
        final /* synthetic */ PwdData vp_a;

        vp_ba(PwdData pwdData) {
            this.vp_a = pwdData;
        }

        @Override // com.veepoo.protocol.listener.base.ICallBack
        public void savefirstDay() {
        }

        @Override // com.veepoo.protocol.listener.base.ICallBack
        public void saveAllInfo(String str) {
            VPOperateManager.a.saveSdkUpateInfo(str);
        }

        @Override // com.veepoo.protocol.listener.base.ICallBack
        public void saveServerDay(String str) {
            VPOperateManager.a.saveUpdateInfo(this.vp_a.getDeviceNumber() + "", str);
        }

        @Override // com.veepoo.protocol.listener.base.ICallBack
        public void disConnect() {
            VPOperateManager.this.vp_b();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_bb.class */
    private final class vp_bb implements IDeviceBTConnectionListener {
        private vp_bb() {
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTConnectionListener
        public void onDeviceBTConnecting() {
            IDeviceBTConnectionListener iDeviceBTConnectionListener = VPOperateManager.this.vp_cb;
            if (iDeviceBTConnectionListener != null) {
                iDeviceBTConnectionListener.onDeviceBTConnecting();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTConnectionListener
        public void onDeviceBTConnected() {
            IDeviceBTConnectionListener iDeviceBTConnectionListener = VPOperateManager.this.vp_cb;
            if (iDeviceBTConnectionListener != null) {
                iDeviceBTConnectionListener.onDeviceBTConnected();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTConnectionListener
        public void onDeviceBTDisconnected() {
            IDeviceBTConnectionListener iDeviceBTConnectionListener = VPOperateManager.this.vp_cb;
            if (iDeviceBTConnectionListener != null) {
                iDeviceBTConnectionListener.onDeviceBTDisconnected();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTConnectionListener
        public void onDeviceBTConnectTimeout() {
            IDeviceBTConnectionListener iDeviceBTConnectionListener = VPOperateManager.this.vp_cb;
            if (iDeviceBTConnectionListener != null) {
                iDeviceBTConnectionListener.onDeviceBTConnectTimeout();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_bc.class */
    private final class vp_bc implements IDeviceBTInfoListener {
        private vp_bc() {
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTFunctionNotSupport() {
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTFunctionNotSupport();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTInfoSettingSuccess(BTInfo bTInfo) {
            new StringBuilder().append("-[蓝牙3.0]- BT状态设置成功：").append(bTInfo.toString());
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTInfoSettingSuccess(bTInfo);
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTInfoSettingFailed() {
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTInfoSettingFailed();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTInfoReadSuccess(BTInfo bTInfo) {
            new StringBuilder().append("-[蓝牙3.0]- BT状态读取成功：").append(bTInfo.toString());
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTInfoReadSuccess(bTInfo);
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTInfoReadFailed() {
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTInfoReadFailed();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IDeviceBTInfoListener
        public void onDeviceBTInfoReport(BTInfo bTInfo) {
            new StringBuilder().append("-[蓝牙3.0]- BT状态上报：").append(bTInfo.toString());
            IDeviceBTInfoListener iDeviceBTInfoListener = VPOperateManager.this.vp_cc;
            if (iDeviceBTInfoListener != null) {
                iDeviceBTInfoListener.onDeviceBTInfoReport(bTInfo);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_bd.class */
    private final class vp_bd extends IABleConnectStatusListener {
        private vp_bd() {
        }

        public void onConnectStatusChanged(String str, int i) {
            vp_da.vp_g.vp_a().vp_b(false);
            if (i == 16) {
                VPOperateManager vPOperateManager = VPOperateManager.this;
                vPOperateManager.vp_df = false;
                vPOperateManager.vp_di = com.veepoo.protocol.jl.classic.vp_d.vp_a(str);
                McuMgrOtaManager.getInstance().init(VPOperateManager.mContext, VPOperateManager.this.vp_di);
                return;
            }
            if (i == 32) {
                VpSpSaveUtil.getVpSpVariInstance(VPOperateManager.mContext).saveBTFunction(false);
                VPOperateManager.this.releaseJLSDK();
                VPOperateManager vPOperateManager2 = VPOperateManager.this;
                vPOperateManager2.vp_di = null;
                vPOperateManager2.vp_df = false;
                VPOperateManager.vp_dy.registerConnectStatusListener(vPOperateManager2.vp_bj, this);
                UiUpdateUtil.getInstance().resetNotifyFlag();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_c.class */
    class vp_c implements BleConnectResponse {
        final /* synthetic */ String vp_a;
        final /* synthetic */ String vp_b;
        final /* synthetic */ INotifyResponse vp_c;
        final /* synthetic */ IConnectResponse vp_d;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_c$vp_a.class */
        class vp_a implements Runnable {
            vp_a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                vp_c vp_cVar = vp_c.this;
                VPOperateManager.this.vp_a(vp_cVar.vp_b, vp_cVar.vp_c);
            }
        }

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_c$vp_b.class */
        class vp_b implements IMtuChangeListener {
            vp_b() {
            }

            @Override // com.veepoo.protocol.listener.data.IMtuChangeListener
            public void onChangeMtuLength(int i) {
                new StringBuilder().append("The default MTU value is = ").append(i);
            }
        }

        vp_c(String str, String str2, INotifyResponse iNotifyResponse, IConnectResponse iConnectResponse) {
            this.vp_a = str;
            this.vp_b = str2;
            this.vp_c = iNotifyResponse;
            this.vp_d = iConnectResponse;
        }

        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, BleGattProfile bleGattProfile) {
            vp_da.vp_a vp_aVar = vp_da.vp_g;
            vp_aVar.vp_a().vp_b(false);
            if (i == 0) {
                McuMgrOtaManager.getInstance().setMtu(23);
                vp_aVar.vp_a().vp_a(VPOperateManager.vp_dy, VPOperateManager.this.vp_bj);
                VPOperateManager.isFirstGetBDReport = true;
                com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_g();
                VPOperateManager.this.vp_bk = VpBleByteUtil.isOadModel(bleGattProfile);
                VPOperateManager.this.vp_bl = VpBleByteUtil.getCpuType(bleGattProfile);
                VPOperateManager.this.vp_bm = VpBleByteUtil.getCPUPlatform(bleGattProfile);
                VpSpSaveUtil vpSpSaveUtil = VPOperateManager.a;
                VPOperateManager vPOperateManager = VPOperateManager.this;
                vpSpSaveUtil.saveDeviceBaseInfo(vPOperateManager.vp_bj, this.vp_a, vPOperateManager.vp_bk, vPOperateManager.vp_bl.getValue(), VPOperateManager.this.vp_bm.getName());
                new Handler(Looper.getMainLooper()).postDelayed(new vp_a(), 500L);
                VPOperateManager.this.changeMTU(248, new vp_b());
            }
            this.vp_d.connectState(i, bleGattProfile, VPOperateManager.this.vp_bk);
            VPOperateManager.isFindJLService = VPOperateManager.this.vp_a(bleGattProfile);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_d.class */
    class vp_d extends Handler {
        vp_d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IPwdDataListener iPwdDataListener;
            super.handleMessage(message);
            if (message.what != 9876 || (iPwdDataListener = VPOperateManager.this.vp_a.vp_a) == null) {
                return;
            }
            iPwdDataListener.onConnectionConfirmTimeout();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_e.class */
    class vp_e implements IDeviceControlPhoneModelState {
        vp_e() {
        }

        @Override // com.veepoo.protocol.multi_lead.listener.IMultiEcgModelStateListener
        public void inMultiEcgModel() {
            IMultiEcgModelStateListener iMultiEcgModelStateListener = VPOperateManager.this.vp_bs;
            if (iMultiEcgModelStateListener != null) {
                iMultiEcgModelStateListener.inMultiEcgModel();
            }
        }

        @Override // com.veepoo.protocol.multi_lead.listener.IMultiEcgModelStateListener
        public void outMultiEcgModel() {
            IMultiEcgModelStateListener iMultiEcgModelStateListener = VPOperateManager.this.vp_bs;
            if (iMultiEcgModelStateListener != null) {
                iMultiEcgModelStateListener.outMultiEcgModel();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IKnocknotifyListener
        public void knocknotify(int i) {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.knocknotify(i);
            }
            IKnocknotifyListener iKnocknotifyListener = VPOperateManager.this.vp_bo;
            if (iKnocknotifyListener != null) {
                iKnocknotifyListener.knocknotify(i);
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void nextMusic() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.nextMusic();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.nextMusic();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void previousMusic() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.previousMusic();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.previousMusic();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void pauseAndPlayMusic() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.pauseAndPlayMusic();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.pauseAndPlayMusic();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void pauseMusic() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.pauseMusic();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.pauseMusic();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void playMusic() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.playMusic();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.playMusic();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void voiceUp() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.voiceUp();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.voiceUp();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void voiceDown() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.voiceDown();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.voiceDown();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void oprateMusicSuccess() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.oprateMusicSuccess();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.oprateMusicSuccess();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IMusicControlListener
        public void oprateMusicFail() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.oprateMusicFail();
            }
            IMusicControlListener iMusicControlListener = VPOperateManager.this.vp_bp;
            if (iMusicControlListener != null) {
                iMusicControlListener.oprateMusicFail();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPhoneListener
        public void rejectPhone() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.rejectPhone();
            }
            IPhoneListener iPhoneListener = VPOperateManager.this.vp_bt;
            if (iPhoneListener != null) {
                iPhoneListener.rejectPhone();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPhoneListener
        public void cliencePhone() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.cliencePhone();
            }
            IPhoneListener iPhoneListener = VPOperateManager.this.vp_bt;
            if (iPhoneListener != null) {
                iPhoneListener.cliencePhone();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPhoneListener
        public void appAnswerCall() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.appAnswerCall();
            }
            IPhoneListener iPhoneListener = VPOperateManager.this.vp_bt;
            if (iPhoneListener != null) {
                iPhoneListener.appAnswerCall();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPttModelStateListener
        public void inPttModel() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.inPttModel();
            }
            IPttModelStateListener iPttModelStateListener = VPOperateManager.this.vp_br;
            if (iPttModelStateListener != null) {
                iPttModelStateListener.inPttModel();
            }
        }

        @Override // com.veepoo.protocol.listener.data.IPttModelStateListener
        public void outPttModel() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.outPttModel();
            }
            IPttModelStateListener iPttModelStateListener = VPOperateManager.this.vp_br;
            if (iPttModelStateListener != null) {
                iPttModelStateListener.outPttModel();
            }
        }

        @Override // com.veepoo.protocol.listener.data.ISOSListener
        public void sos() {
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = VPOperateManager.this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                iDeviceControlPhoneModelState.sos();
            }
            ISOSListener iSOSListener = VPOperateManager.this.vp_bq;
            if (iSOSListener != null) {
                iSOSListener.sos();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_f.class */
    class vp_f implements Runnable {
        vp_f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VPOperateManager.vp_dy.disconnect(VPOperateManager.this.vp_bj);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_g.class */
    class vp_g implements IAlarm2DataReadListener {
        vp_g() {
        }

        @Override // com.veepoo.protocol.listener.data.IAlarm2DataReadListener
        public void onAlarmDataChangeListener(Alarm2Setting alarm2Setting) {
            com.veepoo.protocol.util.vp_y.vp_a(VPOperateManager.mContext).vp_a(alarm2Setting);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_h.class */
    class vp_h implements ILowPowerListener {
        final /* synthetic */ boolean vp_a;
        final /* synthetic */ IBleWriteResponse vp_b;
        final /* synthetic */ ILowPowerListener vp_c;

        vp_h(boolean z, IBleWriteResponse iBleWriteResponse, ILowPowerListener iLowPowerListener) {
            this.vp_a = z;
            this.vp_b = iBleWriteResponse;
            this.vp_c = iLowPowerListener;
        }

        @Override // com.veepoo.protocol.listener.data.ILowPowerListener
        public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
            VPOperateManager.this.vp_a(this.vp_b, this.vp_c, new LowPowerSetting(1, this.vp_a ? 1 : 2, lowPowerData.getNotify(), lowPowerData.getTrunwrister(), lowPowerData.getNormallight(), lowPowerData.getShockdelay(), lowPowerData.getShocktime()));
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_i.class */
    class vp_i implements ISleepDataListener {
        final /* synthetic */ IAllHealthDataListener vp_a;
        final /* synthetic */ int vp_b;
        final /* synthetic */ int vp_c;
        final /* synthetic */ int vp_d;

        vp_i(IAllHealthDataListener iAllHealthDataListener, int i, int i2, int i3) {
            this.vp_a = iAllHealthDataListener;
            this.vp_b = i;
            this.vp_c = i2;
            this.vp_d = i3;
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepDataChange(String str, SleepData sleepData) {
            this.vp_a.onSleepDataChange(str, sleepData);
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepProgressDetail(String str, int i) {
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepProgress(float f) {
            this.vp_a.onProgress(f * 0.1f);
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onReadSleepComplete() {
            this.vp_a.onReadSleepComplete();
            VPOperateManager.this.vp_a(this.vp_a, this.vp_b, this.vp_c, this.vp_d);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_j.class */
    class vp_j implements IOriginData3Listener {
        final /* synthetic */ IAllHealthDataListener vp_a;

        vp_j(IAllHealthDataListener iAllHealthDataListener) {
            this.vp_a = iAllHealthDataListener;
        }

        @Override // com.veepoo.protocol.listener.data.IOriginData3Listener
        public void onOriginFiveMinuteListDataChange(List list) {
            for (int i = 0; i < list.size(); i++) {
                this.vp_a.onOringinFiveMinuteDataChange((OriginData) list.get(i));
            }
        }

        @Override // com.veepoo.protocol.listener.data.IOriginData3Listener
        public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData) {
            this.vp_a.onOringinHalfHourDataChange(originHalfHourData);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginData3Listener
        public void onOriginHRVOriginListDataChange(List list) {
        }

        @Override // com.veepoo.protocol.listener.data.IOriginData3Listener
        public void onOriginSpo2OriginListDataChange(List list) {
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgressDetail(int i, String str, int i2, int i3) {
            Log.e("Test", "onReadOriginProgressDetail:" + i + ",date:" + str + ",allPkg:" + i2 + ",currentPkg:" + i3);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgress(float f) {
            this.vp_a.onProgress(f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginComplete() {
            this.vp_a.onReadOriginComplete();
            this.vp_a.onProgress(1.0f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadTimeout(int i) {
            this.vp_a.onReadTimeout(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_k.class */
    class vp_k extends Handler {
        vp_k(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IPwdCheckTimeoutListener iPwdCheckTimeoutListener;
            super.handleMessage(message);
            if (message.what != 10086 || (iPwdCheckTimeoutListener = VPOperateManager.this.vp_cm) == null) {
                return;
            }
            iPwdCheckTimeoutListener.onPwdCheckTimeout();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_l.class */
    class vp_l implements IOriginDataListener {
        final /* synthetic */ IAllHealthDataListener vp_a;
        final /* synthetic */ int vp_b;

        vp_l(IAllHealthDataListener iAllHealthDataListener, int i) {
            this.vp_a = iAllHealthDataListener;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgress(float f) {
            this.vp_a.onProgress((f * 0.9f) + 0.1f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgressDetail(int i, String str, int i2, int i3) {
            Log.e("Test", "onReadOriginProgressDetail:" + this.vp_b + ",date:" + str + ",allPkg:" + i2 + ",currentPkg:" + i3);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginDataListener
        public void onOringinFiveMinuteDataChange(OriginData originData) {
            this.vp_a.onOringinFiveMinuteDataChange(originData);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginDataListener
        public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
            this.vp_a.onOringinHalfHourDataChange(originHalfHourData);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginComplete() {
            this.vp_a.onReadOriginComplete();
            this.vp_a.onProgress(1.0f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadTimeout(int i) {
            this.vp_a.onReadTimeout(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_m.class */
    class vp_m implements ISleepDataListener {
        final /* synthetic */ IAllHealthDataListener vp_a;
        final /* synthetic */ int vp_b;

        vp_m(IAllHealthDataListener iAllHealthDataListener, int i) {
            this.vp_a = iAllHealthDataListener;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepDataChange(String str, SleepData sleepData) {
            this.vp_a.onSleepDataChange(str, sleepData);
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepProgressDetail(String str, int i) {
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onSleepProgress(float f) {
            this.vp_a.onProgress(f * 0.1f);
        }

        @Override // com.veepoo.protocol.listener.data.ISleepDataListener
        public void onReadSleepComplete() {
            this.vp_a.onReadSleepComplete();
            VPOperateManager.this.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_n.class */
    class vp_n implements IOriginDataListener {
        final /* synthetic */ IAllHealthDataListener vp_a;

        vp_n(IAllHealthDataListener iAllHealthDataListener) {
            this.vp_a = iAllHealthDataListener;
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgress(float f) {
            this.vp_a.onProgress((f * 0.9f) + 0.1f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginProgressDetail(int i, String str, int i2, int i3) {
        }

        @Override // com.veepoo.protocol.listener.data.IOriginDataListener
        public void onOringinFiveMinuteDataChange(OriginData originData) {
            this.vp_a.onOringinFiveMinuteDataChange(originData);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginDataListener
        public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
            this.vp_a.onOringinHalfHourDataChange(originHalfHourData);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadOriginComplete() {
            this.vp_a.onReadOriginComplete();
            this.vp_a.onProgress(1.0f);
        }

        @Override // com.veepoo.protocol.listener.data.IOriginProgressListener
        public void onReadTimeout(int i) {
            this.vp_a.onReadTimeout(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_o.class */
    class vp_o implements IBleWriteResponse {
        vp_o() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_p.class */
    class vp_p implements INotifyResponse {
        vp_p() {
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            VPOperateManager.this.vp_bi.vp_a((ILightDataCallBack) null);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_q.class */
    class vp_q implements INotifyResponse {
        final /* synthetic */ ILightDataCallBack vp_a;

        vp_q(ILightDataCallBack iLightDataCallBack) {
            this.vp_a = iLightDataCallBack;
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            VPOperateManager.this.vp_bi.vp_a(this.vp_a);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_r.class */
    class vp_r implements INotifyResponse {
        vp_r() {
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            VPOperateManager.this.vp_bi.vp_a((IG08ProjectPPGLightCallBack) null);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_s.class */
    class vp_s implements INotifyResponse {
        final /* synthetic */ IG08ProjectPPGLightCallBack vp_a;

        vp_s(IG08ProjectPPGLightCallBack iG08ProjectPPGLightCallBack) {
            this.vp_a = iG08ProjectPPGLightCallBack;
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            VPOperateManager.this.vp_bi.vp_a(this.vp_a);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_t.class */
    class vp_t implements INotifyResponse {

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_t$vp_a.class */
        class vp_a implements IECGLightDataCallback {
            vp_a() {
            }

            @Override // com.veepoo.protocol.listener.data.IECGLightDataCallback
            public void onEcgLightDataChange(byte[] bArr) {
                if (VPOperateManager.d) {
                    VPOperateManager.this.vp_r.vp_a(bArr);
                }
                if (VPOperateManager.f) {
                    VPOperateManager.this.vp_t.vp_a(bArr);
                }
                if (VPOperateManager.h) {
                    VPOperateManager.this.vp_u.vp_a(bArr);
                }
                if (VPOperateManager.e) {
                    VPOperateManager.this.vp_s.vp_a(bArr);
                }
                if (VPOperateManager.g) {
                    VPOperateManager.this.vp_w.vp_a(bArr);
                }
                if (VPOperateManager.i) {
                    VPOperateManager.this.vp_av.vp_a(bArr);
                }
            }
        }

        vp_t() {
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            if (i == 0) {
                BluetoothLog.i("setEcgLightDataListen");
                VPOperateManager.this.vp_bi.vp_a(new vp_a());
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_u.class */
    class vp_u implements BleNotifyResponse {
        final /* synthetic */ BleNotifyResponse vp_a;

        vp_u(BleNotifyResponse bleNotifyResponse) {
            this.vp_a = bleNotifyResponse;
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            this.vp_a.onNotify(uuid, uuid2, bArr);
        }

        public void onResponse(int i) {
            VPOperateManager.this.vp_df = i == 0;
            this.vp_a.onResponse(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_v.class */
    class vp_v implements BleNotifyResponse {
        final /* synthetic */ INotifyResponse vp_a;

        vp_v(INotifyResponse iNotifyResponse) {
            this.vp_a = iNotifyResponse;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) throws NoWhenBranchMatchedException {
            IBleNotifyResponse iBleNotifyResponse = VPOperateManager.this.vp_e;
            if (iBleNotifyResponse != null) {
                iBleNotifyResponse.onNotify(uuid, uuid2, bArr);
            }
            String strVp_a = com.veepoo.protocol.profile.vp_b.vp_a(com.veepoo.protocol.profile.vp_a.vp_d, bArr);
            BluetoothLog.i("onNotify,operate=" + strVp_a + ",value=" + VpBleByteUtil.byte2HexForShow(bArr));
            VPOperateManager.this.vp_a(strVp_a, bArr);
            if (strVp_a.equals("pwd_comfirm_oprate")) {
                VPOperateManager.this.vp_c.removeCallbacksAndMessages(null);
            }
            if (!strVp_a.equals("pwd_comfirm_oprate") || VPOperateManager.c.isEcgMultiLeadDevice()) {
                return;
            }
            VPOperateManager.this.vp_b(bArr);
        }

        public void onResponse(int i) {
            this.vp_a.notifyState(i);
            if (i == 0) {
                BluetoothLog.i("onNotify DATA,onResponse,success");
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_w.class */
    class vp_w implements BleNotifyResponse {
        final /* synthetic */ BleNotifyResponse vp_a;

        vp_w(BleNotifyResponse bleNotifyResponse) {
            this.vp_a = bleNotifyResponse;
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            this.vp_a.onNotify(uuid, uuid2, bArr);
        }

        public void onResponse(int i) {
            VPOperateManager.this.vp_dv = i == 0;
            this.vp_a.onResponse(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_x.class */
    class vp_x implements IF2DebugListener {
        final /* synthetic */ IResponseListener vp_a;

        vp_x(IResponseListener iResponseListener) {
            this.vp_a = iResponseListener;
        }

        @Override // com.veepoo.protocol.listener.data.IF2DebugListener
        public void onIF2DebugStatusChanged(boolean z, DebugCmd debugCmd) {
            this.vp_a.response(z ? 1 : 0);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_y.class */
    class vp_y implements RcspAuthResponse {
        final /* synthetic */ RcspAuthResponse vp_a;

        vp_y(RcspAuthResponse rcspAuthResponse) {
            this.vp_a = rcspAuthResponse;
        }

        public void onRcspAuthStart() {
            this.vp_a.onRcspAuthStart();
        }

        public void onRcspAuthSuccess() {
            this.vp_a.onRcspAuthSuccess();
        }

        public void onRcspAuthFailed() {
            this.vp_a.onRcspAuthFailed();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_z.class */
    class vp_z implements IUIBaseInfoFormCustomListener {
        final /* synthetic */ String vp_a;
        final /* synthetic */ JLWatchFaceManager.JLTransferPicDialListener vp_b;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/VPOperateManager$vp_z$vp_a.class */
        class vp_a implements com.veepoo.protocol.util.vp_k.vp_c {
            vp_a() {
            }

            @Override // com.veepoo.protocol.util.vp_k.vp_c
            public void onStart() {
            }

            @Override // com.veepoo.protocol.util.vp_k.vp_c
            public void onComplete(String str) {
                new StringBuilder().append("【杰理表盘传输】表盘预览图保存完成 path = ").append(str);
                JLWatchFaceManager jLWatchFaceManager = JLWatchFaceManager.getInstance();
                vp_z vp_zVar = vp_z.this;
                jLWatchFaceManager.startTransferPicDial(vp_zVar.vp_a, str, vp_zVar.vp_b);
            }

            @Override // com.veepoo.protocol.util.vp_k.vp_c
            public void vp_a(Exception exc) {
                vp_z.this.vp_b.onTransferError(-1, "创建表盘预览图异常：" + exc.getMessage());
                new StringBuilder().append("【杰理表盘传输】表盘预览图生成和保存失败，error = ").append(exc.getMessage());
            }
        }

        vp_z(String str, JLWatchFaceManager.JLTransferPicDialListener jLTransferPicDialListener) {
            this.vp_a = str;
            this.vp_b = jLTransferPicDialListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoFormCustomListener
        public void onBaseUiInfoFormCustom(UIDataCustom uIDataCustom) {
            new StringBuilder().append("----表盘类型：").append(uIDataCustom);
            WatchUIType watchUIType = WatchUIType.getInstance(uIDataCustom.getCustomUIType());
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.vp_a);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            Log.d(JLWatchFaceManager.TAG, "本地裁剪的表盘路径：" + this.vp_a + " \n 宽高:" + width + " / " + height);
            bitmapDecodeFile.recycle();
            if (watchUIType.getBigBitmapWidth() == width && watchUIType.getBigBitmapHeight() == height) {
                com.veepoo.protocol.util.vp_k.vp_a(VPOperateManager.mContext, watchUIType, this.vp_a, new vp_a());
            } else {
                this.vp_b.onTransferError(-1, "错误：裁剪的表盘照片尺寸不符合。\n当前手表的表盘尺寸(宽/高)：" + watchUIType.getBigBitmapWidth() + " / " + watchUIType.getBigBitmapHeight() + "\n本地裁剪照片" + this.vp_a + " :尺寸(宽/高) " + width + " / " + height);
            }
        }
    }

    public Handler getUIHandler() {
        return this.vp_b;
    }

    public void setDeviceFunctionStatusChangeListener(IDeviceFunctionStatusChangeListener iDeviceFunctionStatusChangeListener) {
        this.vp_d = iDeviceFunctionStatusChangeListener;
    }

    public void postFunctionStatusChanged(IDeviceFunctionStatusChangeListener.DeviceFunction deviceFunction, EFunctionStatus eFunctionStatus) {
        if (this.vp_d != null) {
            this.vp_b.post(() -> {
                this.vp_d.onFunctionStatusChanged(deviceFunction, eFunctionStatus);
            });
        } else {
            new StringBuilder().append("Device function status change listener not set：").append(deviceFunction.getDes()).append(" - ").append(eFunctionStatus);
        }
    }

    public FunctionSocailMsgData getFunctionSocailMsgData() {
        return this.vp_dc;
    }

    public void setFunctionSocailMsgData(FunctionSocailMsgData functionSocailMsgData) {
        this.vp_dc = functionSocailMsgData;
    }

    public void setPwdCheckTimeoutListener(IPwdCheckTimeoutListener iPwdCheckTimeoutListener) {
        this.vp_cm = iPwdCheckTimeoutListener;
    }

    public void removePwdCheckTimeoutListener() {
        this.vp_cm = null;
    }

    public String getMacAddress() {
        String str = this.vp_bj;
        String str2 = str;
        if (str == null) {
            str2 = "NULL";
        }
        return str2;
    }

    public void setAutoConnectBTBySdk(boolean z) {
        this.vp_dd = z;
    }

    public boolean isAutoConnectBTBySdk() {
        return this.vp_dd;
    }

    public VpSpGetUtil getVPDeviceSP() {
        return b;
    }

    public void setOnUIDataNotify(BleNotifyResponse bleNotifyResponse) {
        vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_l, com.veepoo.protocol.profile.vp_a.vp_m, bleNotifyResponse);
    }

    public void setOnUIDataUnNotify(BleUnnotifyResponse bleUnnotifyResponse) {
        vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_l, com.veepoo.protocol.profile.vp_a.vp_m, bleUnnotifyResponse);
    }

    public void setG08WProjectPPGLightDataCallback(boolean z, IG08ProjectPPGLightCallBack iG08ProjectPPGLightCallBack) {
        new StringBuilder().append("**设置G08W项目PPG数据监听开启/关闭，以及ppg数据回调接口  => 是否开启监听").append(z);
        this.vp_ci = iG08ProjectPPGLightCallBack;
        if (z) {
            vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_ag());
        } else {
            vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_ar());
        }
    }

    public void setTimeServiceNotifyEnable(boolean z) {
        new StringBuilder().append("**ECG ADC 通知 setTimeServiceNotifyEnable  => ").append(z);
        BluetoothLog.i("**ECG ADC 通知 setTimeServiceNotifyEnable  => " + z);
        if (z) {
            vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_aw());
        } else {
            vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_ax());
        }
    }

    public int getCurrentMtu() {
        return this.vp_dg;
    }

    public void changeMTU(int i2, IMtuChangeListener iMtuChangeListener) {
        vp_dy.requestMtu(this.vp_bj, i2, new vp_ay(i2, iMtuChangeListener));
    }

    @Deprecated
    public ECpuType getCpuType() {
        return b.getCpuType();
    }

    public ECPUPlatform getCPUPlatform() {
        return b.getCPUPlatform();
    }

    public boolean isJLCPUPlatform() {
        return b.getCPUType() == 1;
    }

    public boolean isCurrentDeviceConnected() {
        return !TextUtils.isEmpty(this.vp_bj) && vp_dy.getConnectStatus(this.vp_bj) == 2;
    }

    public boolean isDeviceConnected(String str) {
        return !TextUtils.isEmpty(str) && vp_dy.getConnectStatus(str) == 2;
    }

    protected boolean checkDeviceIsConnected() {
        return isCurrentDeviceConnected();
    }

    public BluetoothGatt getConnectGatt(String str) {
        if (vp_dy == null) {
            return null;
        }
        new StringBuilder().append("根据mac地址获取连接的Gatt - mac = ").append(str);
        return vp_dy.getGatt(str);
    }

    public BluetoothGatt getCurrentConnectGatt() {
        return getConnectGatt(this.vp_bj);
    }

    public void init(@NotNull Context context) {
        if (mContext == null) {
            mContext = context.getApplicationContext();
            vp_dx = new VPOperateManager();
            a = VpSpSaveUtil.getVpSpVariInstance(mContext);
            b = VpSpGetUtil.getVpSpVariInstance(mContext);
            c = new FunctionCheckUtil(b, mContext);
            vp_dy = new BluetoothClient(context);
            BluetoothContext.set(mContext);
            vp_dz.vp_a(context);
            vp_h();
        }
    }

    public FunctionCheckUtil getFunctionCheck() {
        return c;
    }

    public void startScanDevice(int i2, SearchResponse searchResponse) {
        vp_dy.search(new SearchRequest.Builder().searchBluetoothLeDevice(i2 * 1000, 1).build(), new vp_b(searchResponse));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    public boolean isVPDevice(byte[] bArr) {
        ?? r6;
        ?? IsBrandDevice = bArr;
        boolean z = false;
        boolean z2 = false;
        try {
            r6 = z2;
            if (VpBleByteUtil.isBeyondVp(IsBrandDevice)) {
                z = true;
                IsBrandDevice = VpBleByteUtil.isBrandDevice(bArr);
                r6 = IsBrandDevice;
            }
        } catch (Exception unused) {
            IsBrandDevice.printStackTrace();
            z = false;
            r6 = z2;
        }
        boolean z3 = this.vp_dh;
        return (z3 && r6 != 0) || (z && !z3);
    }

    public void stopScanDevice() {
        vp_dy.stopSearch();
    }

    public void connectDevice(String str, IConnectResponse iConnectResponse, INotifyResponse iNotifyResponse) {
        VpSpSaveUtil.getVpSpVariInstance(mContext).saveBTFunction(false);
        connectDevice(str, "none", iConnectResponse, iNotifyResponse);
    }

    public void releaseJLSDK() {
        JLBleDataManager jLBleDataManager;
        try {
            JLOTAHolder.getInstance().release();
            JLWatchFaceManager.getInstance().release();
            WatchManager.getInstance().release();
            JLOTAManager.getInstance().setJLOTAInitSuccess(false);
            RcspAuthManager.getInstance().setAuthPass(false);
            jLBleDataManager = JLBleDataManager.getInstance();
            jLBleDataManager.stopSendDataThread();
        } catch (Exception unused) {
            jLBleDataManager.printStackTrace();
        }
    }

    public void release() {
        vp_dy.unregisterConnectStatusListener(this.vp_bj, this.vp_dk);
        UiUpdateUtil.getInstance().resetNotifyFlag();
        removePwdCheckTimeoutListener();
    }

    public List<Alarm2Setting> getAlarm2List() {
        return com.veepoo.protocol.util.vp_y.vp_a(mContext).vp_b(this.vp_bj);
    }

    public List<TextAlarm2Setting> getTextAlarmList() {
        return TextAlarmSp.getInstance(mContext).getTextAlarmSetting(this.vp_bj);
    }

    public void registerBTListener(IDeviceBTInfoListener iDeviceBTInfoListener, IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        this.vp_cc = iDeviceBTInfoListener;
        this.vp_cb = iDeviceBTConnectionListener;
        this.vp_ai.vp_a(this.vp_dl);
        this.vp_ai.vp_a(this.vp_dm);
    }

    public void registerBTInfoListener(IDeviceBTInfoListener iDeviceBTInfoListener) {
        this.vp_cc = iDeviceBTInfoListener;
    }

    public void registerBTConnectionListener(IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        this.vp_cb = iDeviceBTConnectionListener;
    }

    public void registerBluetoothStateListener(IABluetoothStateListener iABluetoothStateListener) {
        vp_dy.registerBluetoothStateListener(iABluetoothStateListener);
    }

    public int getConnectStatus(String str) {
        return vp_dy.getConnectStatus(str);
    }

    public boolean isBluetoothOpened() {
        return vp_dy.isBluetoothOpened();
    }

    public void openBluetooth() {
        vp_dy.openBluetooth();
    }

    public void closeBluetooth() {
        vp_dy.closeBluetooth();
    }

    public void registerConnectStatusListener(String str, IABleConnectStatusListener iABleConnectStatusListener) {
        vp_dy.registerConnectStatusListener(str, iABleConnectStatusListener);
    }

    public void unregisterConnectStatusListener(String str, IABleConnectStatusListener iABleConnectStatusListener) {
        vp_dy.unregisterConnectStatusListener(str, iABleConnectStatusListener);
    }

    public void startDetectBreath(IBleWriteResponse iBleWriteResponse, IBreathDataListener iBreathDataListener) {
        if (c.checkBreath()) {
            this.vp_a.vp_a(iBreathDataListener);
            vp_dz.vp_a("breath_read_oprate").vp_ba(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopDetectBreath(IBleWriteResponse iBleWriteResponse, IBreathDataListener iBreathDataListener) {
        if (c.checkBreath()) {
            this.vp_a.vp_a(iBreathDataListener);
            vp_dz.vp_a("breath_read_oprate").vp_bm(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void startDetectHeart(IBleWriteResponse iBleWriteResponse, IHeartDataListener iHeartDataListener) {
        if (c.checkRate()) {
            a.saveDetectHeart(true);
            this.vp_a.vp_a(iHeartDataListener);
            vp_dz.vp_a("heart_read_oprate").vp_bc(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopDetectHeart(IBleWriteResponse iBleWriteResponse) {
        if (c.checkRate()) {
            a.saveDetectHeart(false);
            vp_dz.vp_a("heart_read_oprate").vp_bp(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void startDetectTempture(IBleWriteResponse iBleWriteResponse, ITemptureDetectDataListener iTemptureDetectDataListener) {
        if (c.checkTemptureByApp()) {
            this.vp_a.vp_a(iTemptureDetectDataListener);
            vp_dz.vp_a("tempture_detect_oprate").vp_be(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopDetectTempture(IBleWriteResponse iBleWriteResponse, ITemptureDetectDataListener iTemptureDetectDataListener) {
        if (c.checkTemptureByApp()) {
            vp_dz.vp_a("tempture_detect_oprate").vp_bv(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingTime(IBleWriteResponse iBleWriteResponse, IResponseListener iResponseListener, DeviceTimeSetting deviceTimeSetting) {
        this.vp_a.vp_c(iResponseListener);
        vp_dz.vp_a("sync_time_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, deviceTimeSetting);
    }

    public void startDetectBP(IBleWriteResponse iBleWriteResponse, IBPDetectDataListener iBPDetectDataListener, EBPDetectModel eBPDetectModel) {
        if (c.checkBp()) {
            a.saveDetectBp(true);
            this.vp_a.vp_a(iBPDetectDataListener);
            vp_dz.vp_a("bp_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eBPDetectModel);
        }
    }

    public void stopDetectBP(IBleWriteResponse iBleWriteResponse, EBPDetectModel eBPDetectModel) {
        if (c.checkBp()) {
            a.saveDetectBp(false);
            vp_dz.vp_a("bp_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, eBPDetectModel);
        }
    }

    public void settingDetectBP(IBleWriteResponse iBleWriteResponse, IBPSettingDataListener iBPSettingDataListener, BpSetting bpSetting) {
        if (c.checkBp()) {
            this.vp_a.vp_a(iBPSettingDataListener);
            vp_dz.vp_a("bp_model_setting_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, bpSetting);
        }
    }

    public void cancelAngioAdjust(IBleWriteResponse iBleWriteResponse, IBPSettingDataListener iBPSettingDataListener, BpSetting bpSetting) {
        if (c.checkAngioAdjuster()) {
            this.vp_a.vp_a(iBPSettingDataListener);
            vp_dz.vp_a("bp_model_setting_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, bpSetting);
        }
    }

    public void readDetectBP(IBleWriteResponse iBleWriteResponse, IBPSettingDataListener iBPSettingDataListener) {
        if (c.checkBp()) {
            this.vp_a.vp_a(iBPSettingDataListener);
            vp_dz.vp_a("bp_model_setting_oprate").vp_u(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingSBBR(IBleWriteResponse iBleWriteResponse, ISpo2hBreathBreakRemainListener iSpo2hBreathBreakRemainListener, BreathBreakRemindSetting breathBreakRemindSetting) {
        if (c.checkSBBR()) {
            this.vp_a.vp_a(iSpo2hBreathBreakRemainListener);
            vp_dz.vp_a("spo2h_breath_break_remind_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, breathBreakRemindSetting);
        }
    }

    public void readSBBR(IBleWriteResponse iBleWriteResponse, ISpo2hBreathBreakRemainListener iSpo2hBreathBreakRemainListener) {
        if (c.checkSBBR()) {
            this.vp_a.vp_a(iSpo2hBreathBreakRemainListener);
            vp_dz.vp_a("spo2h_breath_break_remind_oprate").vp_ah(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void modifyDevicePwd(IBleWriteResponse iBleWriteResponse, IPwdDataListener iPwdDataListener, String str) {
        if (str.length() != 4) {
            throw new IllegalArgumentException("密码的长度应该是4位数字");
        }
        this.vp_a.vp_a(iPwdDataListener);
        vp_dz.vp_a("pwd_modify_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, str + "");
    }

    public void confirmDevicePwd(IBleWriteResponse iBleWriteResponse, IPwdDataListener iPwdDataListener, IDeviceFuctionDataListener iDeviceFuctionDataListener, ISocialMsgDataListener iSocialMsgDataListener, ICustomSettingDataListener iCustomSettingDataListener, String str, boolean z) {
        confirmDevicePwd(iBleWriteResponse, iPwdDataListener, iDeviceFuctionDataListener, iSocialMsgDataListener, iCustomSettingDataListener, str, z, null);
        VpSpSaveUtil.getVpSpVariInstance(mContext).clearSomeSp();
        this.vp_c.removeCallbacksAndMessages(null);
        this.vp_c.sendEmptyMessageDelayed(10086, 4000L);
    }

    public boolean isDeviceShowConfirm() {
        return this.vp_ax;
    }

    public void setDeviceShowConfirm(boolean z) {
        this.vp_ax = z;
    }

    public int getConnectionConfirmTimeout() {
        return this.vp_dn;
    }

    public void setConnectionConfirmTimeout(int i2) {
        if (i2 <= 10) {
            i2 = 10;
        }
        this.vp_dn = i2;
    }

    public void removeConnectionConfirmationTask() {
        Handler handler = this.vp_do;
        if (handler != null) {
            handler.removeMessages(CODE_CONNECTION_CONFIRMATION_TIMEOUT);
        }
    }

    public void readSportStep(IBleWriteResponse iBleWriteResponse, ISportDataListener iSportDataListener) {
        this.vp_a.vp_a(iSportDataListener);
        if (vp_f().isParseSportModel()) {
            vp_dz.vp_a("read_current_sport_sportmodel_oprate").vp_ar(vp_dy, this.vp_bj, iBleWriteResponse);
        } else {
            vp_dz.vp_a("read_current_sport_oprate").vp_aq(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void startGsensorSport(IBleWriteResponse iBleWriteResponse, ISportDataListener iSportDataListener) {
        this.vp_a.vp_a(iSportDataListener);
        vp_dz.vp_a("head_gsensor").vp_bf(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void stopGsensorSport(IBleWriteResponse iBleWriteResponse, ISportDataListener iSportDataListener) {
        this.vp_a.vp_a(iSportDataListener);
        vp_dz.vp_a("head_gsensor").vp_bx(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readBattery(IBleWriteResponse iBleWriteResponse, IBatteryDataListener iBatteryDataListener) {
        this.vp_a.vp_a(iBatteryDataListener);
        vp_dz.vp_a("read_battery_oprate").vp_o(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void enterOad(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("read_battery_oprate").vp_e(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void syncPersonInfo(IBleWriteResponse iBleWriteResponse, IPersonInfoDataListener iPersonInfoDataListener, PersonInfoData personInfoData) {
        a.savePersonInfo(personInfoData.getHeight());
        this.vp_a.vp_a(iPersonInfoDataListener);
        vp_dz.vp_a("person_info_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, personInfoData);
    }

    public void setCameraListener(ICameraDataListener iCameraDataListener) {
        this.vp_a.vp_a(iCameraDataListener);
    }

    public void startCamera(IBleWriteResponse iBleWriteResponse, ICameraDataListener iCameraDataListener) {
        if (c.checkCamera()) {
            this.vp_a.vp_a(iCameraDataListener);
            vp_dz.vp_a("take_photo_oprate").vp_ay(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopCamera(IBleWriteResponse iBleWriteResponse, ICameraDataListener iCameraDataListener) {
        if (c.checkCamera()) {
            this.vp_a.vp_a(iCameraDataListener);
            vp_dz.vp_a("take_photo_oprate").vp_bi(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingAlarm(IBleWriteResponse iBleWriteResponse, IAlarmDataListener iAlarmDataListener, List<AlarmSetting> list) {
        this.vp_a.vp_a(iAlarmDataListener);
        vp_dz.vp_a("alarm_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, list);
    }

    public void readAlarm(IBleWriteResponse iBleWriteResponse, IAlarmDataListener iAlarmDataListener) {
        this.vp_a.vp_a(iAlarmDataListener);
        vp_dz.vp_a("alarm_oprate").vp_j(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void settingLongSeat(IBleWriteResponse iBleWriteResponse, LongSeatSetting longSeatSetting, ILongSeatDataListener iLongSeatDataListener) {
        if (c.checkLongseat()) {
            this.vp_a.vp_a(iLongSeatDataListener);
            vp_dz.vp_a("long_seat_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, longSeatSetting);
        }
    }

    public void readLongSeat(IBleWriteResponse iBleWriteResponse, ILongSeatDataListener iLongSeatDataListener) {
        if (c.checkLongseat()) {
            this.vp_a.vp_a(iLongSeatDataListener);
            vp_dz.vp_a("long_seat_oprate").vp_ab(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingDeviceLanguage(IBleWriteResponse iBleWriteResponse, ILanguageDataListener iLanguageDataListener, ELanguage eLanguage) {
        this.vp_a.vp_a(iLanguageDataListener);
        vp_dz.vp_a("change_watch_language_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eLanguage);
    }

    public void disconnectWatch(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("change_watch_language_oprate").vp_c(vp_dy, this.vp_bj, iBleWriteResponse);
        new Handler(Looper.getMainLooper()).postDelayed(new vp_f(), 500L);
        VpSpSaveUtil.getVpSpVariInstance(mContext).saveBTFunction(false);
    }

    public void settingNightTurnWriste(IBleWriteResponse iBleWriteResponse, INightTurnWristeDataListener iNightTurnWristeDataListener, NightTurnWristSetting nightTurnWristSetting) {
        if (c.checkNightturnSetting()) {
            this.vp_a.vp_a(iNightTurnWristeDataListener);
            vp_dz.vp_a("night_turn_opeate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, nightTurnWristSetting);
        }
    }

    public void readNightTurnWriste(IBleWriteResponse iBleWriteResponse, INightTurnWristeDataListener iNightTurnWristeDataListener) {
        this.vp_a.vp_a(iNightTurnWristeDataListener);
        vp_dz.vp_a("night_turn_opeate").vp_ac(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void changeCustomSetting(IBleWriteResponse iBleWriteResponse, ICustomSettingDataListener iCustomSettingDataListener, CustomSetting customSetting) {
        this.vp_a.vp_a(iCustomSettingDataListener);
        vp_dz.vp_a("custom_setting_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, customSetting);
    }

    public void readCustomSetting(IBleWriteResponse iBleWriteResponse, ICustomSettingDataListener iCustomSettingDataListener) {
        this.vp_a.vp_a(iCustomSettingDataListener);
        vp_dz.vp_a("custom_setting_oprate").vp_t(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void settingFindDevice(IBleWriteResponse iBleWriteResponse, IFindDeviceDatalistener iFindDeviceDatalistener, boolean z) {
        if (c.checkFindDevice()) {
            this.vp_a.vp_a(iFindDeviceDatalistener);
            vp_dz.vp_a("find_watch_by_phon_oprate").vp_c(vp_dy, this.vp_bj, iBleWriteResponse, z);
        }
    }

    public void readFindDevice(IBleWriteResponse iBleWriteResponse, IFindDeviceDatalistener iFindDeviceDatalistener) {
        if (c.checkFindDevice()) {
            this.vp_a.vp_a(iFindDeviceDatalistener);
            vp_dz.vp_a("find_watch_by_phon_oprate").vp_w(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readHeartWarning(IBleWriteResponse iBleWriteResponse, IHeartWaringDataListener iHeartWaringDataListener) {
        if (c.checkHeartwaring()) {
            this.vp_as.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, iHeartWaringDataListener);
        }
    }

    public void settingHeartWarning(IBleWriteResponse iBleWriteResponse, IHeartWaringDataListener iHeartWaringDataListener, HeartWaringSetting heartWaringSetting) {
        if (c.checkHeartwaring()) {
            this.vp_as.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, heartWaringSetting, iHeartWaringDataListener);
        }
    }

    public void readHealthAlarmInterval(EHealthAlarmType eHealthAlarmType, IBleWriteResponse iBleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        if (c.checkHeartwaring()) {
            new StringBuilder().append("**调用读取健康报警区间：").append(eHealthAlarmType.toString());
            this.vp_as.vp_a(eHealthAlarmType, vp_dy, this.vp_bj, iBleWriteResponse, iHealthAlarmIntervalListener);
        }
    }

    public void setHealthAlarmInterval(HealthAlarmInterval healthAlarmInterval, IBleWriteResponse iBleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        if (c.checkHeartwaring()) {
            new StringBuilder().append("**调用设置健康报警区间：").append(healthAlarmInterval.toString());
            this.vp_as.vp_a(healthAlarmInterval, vp_dy, this.vp_bj, iBleWriteResponse, iHealthAlarmIntervalListener);
        }
    }

    public void startDetectSPO2H(IBleWriteResponse iBleWriteResponse, ISpo2hDataListener iSpo2hDataListener) {
        a.saveDetectSpo2h(true);
        this.vp_a.vp_a(iSpo2hDataListener);
        vp_dz.vp_a("spo2h_read_oprate").vp_bd(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void startDetectSPO2HForG08WProject(IBleWriteResponse iBleWriteResponse, ISpo2hDataListener iSpo2hDataListener, IG08ProjectPPGLightCallBack iG08ProjectPPGLightCallBack) {
        startDetectSPO2H(iBleWriteResponse, iSpo2hDataListener);
        vp_a(true, iG08ProjectPPGLightCallBack);
    }

    public void stopDetectSPO2H(IBleWriteResponse iBleWriteResponse, ISpo2hDataListener iSpo2hDataListener) {
        this.vp_a.vp_a(iSpo2hDataListener);
        vp_dz.vp_a("spo2h_read_oprate").vp_bt(vp_dy, this.vp_bj, iBleWriteResponse);
        a.saveDetectSpo2h(false);
    }

    public void readSpo2hAutoDetect(IBleWriteResponse iBleWriteResponse, IAllSetDataListener iAllSetDataListener) {
        this.vp_a.vp_a(iAllSetDataListener);
        vp_dz.vp_a("all_setting_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, new AllSetSetting(EAllSetType.SPO2H_NIGHT_AUTO_DETECT, 22, 0, 8, 0, 1, 0));
    }

    public void readB3(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("all_setting_oprate").vp_m(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void settingSpo2hAutoDetect(IBleWriteResponse iBleWriteResponse, IAllSetDataListener iAllSetDataListener, AllSetSetting allSetSetting) {
        this.vp_a.vp_a(iAllSetDataListener);
        vp_dz.vp_a("all_setting_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, allSetSetting);
    }

    public void startDetectFatigue(IBleWriteResponse iBleWriteResponse, IFatigueDataListener iFatigueDataListener) {
        if (c.checkFtg()) {
            if (b.getFatigueType() != 1) {
                this.vp_ap.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, iFatigueDataListener);
                return;
            }
            a.saveDetectFtg(true);
            this.vp_a.vp_a(iFatigueDataListener);
            vp_dz.vp_a("fatigue_read_oprate").vp_bb(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopDetectFatigue(IBleWriteResponse iBleWriteResponse, IFatigueDataListener iFatigueDataListener) {
        if (c.checkFtg()) {
            if (b.getFatigueType() != 1) {
                this.vp_ap.vp_b(vp_dy, this.vp_bj, iBleWriteResponse, iFatigueDataListener);
                return;
            }
            a.saveDetectFtg(false);
            this.vp_a.vp_a(iFatigueDataListener);
            vp_dz.vp_a("fatigue_read_oprate").vp_bo(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void startFindDeviceByPhone(IBleWriteResponse iBleWriteResponse, IFindDevicelistener iFindDevicelistener) {
        vp_a(iBleWriteResponse, iFindDevicelistener, 1);
    }

    public void stopFindDeviceByPhone(IBleWriteResponse iBleWriteResponse, IFindDevicelistener iFindDevicelistener) {
        vp_a(iBleWriteResponse, iFindDevicelistener, 2);
    }

    public void stopFindCYPhoneByDevice(IBleWriteResponse iBleWriteResponse) {
        if (c.checFindDeviceByPhone()) {
            vp_dz.vp_a("find_phone_by_watch_operate").vp_bw(vp_dy, this.vp_bj, iBleWriteResponse);
        } else {
            Toast.makeText(mContext, "This feature is not supported", 0).show();
        }
    }

    public void settingFindPhoneListener(IFindPhonelistener iFindPhonelistener) {
        this.vp_a.vp_a(iFindPhonelistener);
    }

    public void settingKnocknotifyListener(IKnocknotifyListener iKnocknotifyListener) {
        this.vp_bo = iKnocknotifyListener;
    }

    public void settingMusicControlListener(IMusicControlListener iMusicControlListener) {
        this.vp_bp = iMusicControlListener;
    }

    public void settingMusicData(IBleWriteResponse iBleWriteResponse, MusicData musicData, IMusicControlListener iMusicControlListener) {
        if (c.isMusicWithInfo()) {
            this.vp_bp = iMusicControlListener;
            this.vp_i.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, musicData);
        }
    }

    public void settingVolume(@IntRange(from = 0, to = 100) int i2, IBleWriteResponse iBleWriteResponse, IMusicControlListener iMusicControlListener) {
        if (c.isMusicWithInfo()) {
            this.vp_bp = iMusicControlListener;
            this.vp_i.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, i2);
        }
    }

    public void settingSOSListener(ISOSListener iSOSListener) {
        this.vp_bq = iSOSListener;
    }

    public void sendToSoldierCommand(IBleWriteResponse iBleWriteResponse, IResponseListener iResponseListener) {
        this.vp_a.vp_b(iResponseListener);
        vp_dz.vp_a("sos_oprate").vp_ax(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void settingPttModelListener(IPttModelStateListener iPttModelStateListener) {
        this.vp_br = iPttModelStateListener;
    }

    public void settingMultiEcgModelListener(IMultiEcgModelStateListener iMultiEcgModelStateListener) {
        this.vp_bs = iMultiEcgModelStateListener;
    }

    public void settingPhoneListener(IPhoneListener iPhoneListener) {
        this.vp_bt = iPhoneListener;
    }

    public void settingSocialMsgDataListener(ISocialMsgDataListener iSocialMsgDataListener) {
        this.vp_bu = iSocialMsgDataListener;
        this.vp_a.vp_a(iSocialMsgDataListener);
    }

    public void settingDeviceControlPhone(IDeviceControlPhoneModelState iDeviceControlPhoneModelState) {
        this.vp_bn = iDeviceControlPhoneModelState;
    }

    public void settingRequestAppReportGpsDataListener(IAppReportGpsDataListener iAppReportGpsDataListener) {
        this.vp_a.vp_a(iAppReportGpsDataListener);
    }

    public void readDrinkData(IBleWriteResponse iBleWriteResponse, IDrinkDataListener iDrinkDataListener) {
        if (c.checkDrink()) {
            this.vp_a.vp_a(iDrinkDataListener);
            vp_dz.vp_a("drink_oprate").vp_v(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingSocialMsg(IBleWriteResponse iBleWriteResponse, ISocialMsgDataListener iSocialMsgDataListener, FunctionSocailMsgData functionSocailMsgData) {
        this.vp_a.vp_a(iSocialMsgDataListener);
        vp_dz.vp_a("device_msg_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, functionSocailMsgData);
    }

    public void readSocialMsg(IBleWriteResponse iBleWriteResponse, ISocialMsgDataListener iSocialMsgDataListener) {
        this.vp_a.vp_a(iSocialMsgDataListener);
        vp_dz.vp_a("device_msg_oprate").vp_an(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void sendSocialMsgContent(IBleWriteResponse iBleWriteResponse, ContentSetting contentSetting) {
        vp_dz.vp_a("device_msg_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, contentSetting);
    }

    public void sendG15MsgContent(IBleWriteResponse iBleWriteResponse, String str, String str2, IG15MessageListener iG15MessageListener) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if ((str + ":" + str2).getBytes().length > 300) {
            return;
        }
        vp_dz.vp_a("device_msg_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, new ContentSocailSetting(ESocailMsg.G15MSG, str, str2));
        this.vp_bv = iG15MessageListener;
        this.vp_y.vp_a(iG15MessageListener);
        this.vp_y.vp_b();
    }

    public void sendG15QRCode(IBleWriteResponse iBleWriteResponse, G15QRCode g15QRCode, IG15QRCodeSendListener iG15QRCodeSendListener) {
        if (g15QRCode == null) {
            throw new NullPointerException("qrCode is null");
        }
        this.vp_bw = iG15QRCodeSendListener;
        this.vp_z.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, g15QRCode);
    }

    public void sendToSoldierContent(IBleWriteResponse iBleWriteResponse, SoldierContentSetting soldierContentSetting, IResponseListener iResponseListener) {
        this.vp_a.vp_a(iResponseListener);
        vp_dz.vp_a("device_msg_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, soldierContentSetting);
    }

    public void offhookOrIdlePhone(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("change_watch_language_oprate").vp_g(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void clearDeviceData(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("change_watch_language_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void resetDeviceData(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("change_watch_language_oprate").vp_au(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void settingWomenState(IBleWriteResponse iBleWriteResponse, IWomenDataListener iWomenDataListener, WomenSetting womenSetting) {
        if (c.checkWomenSetting()) {
            this.vp_a.vp_a(iWomenDataListener);
            vp_dz.vp_a("women_mense_setting_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, womenSetting);
        }
    }

    @Deprecated
    public void readWomenState(IBleWriteResponse iBleWriteResponse, IWomenDataListener iWomenDataListener) {
        if (c.checkWomenSetting()) {
            this.vp_a.vp_a(iWomenDataListener);
            vp_dz.vp_a("women_mense_setting_oprate").vp_at(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readCountDown(IBleWriteResponse iBleWriteResponse, ICountDownListener iCountDownListener) {
        if (c.checkCountdown()) {
            this.vp_a.vp_a(iCountDownListener);
            vp_dz.vp_a("count_down_oprate").vp_s(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingCountDown(IBleWriteResponse iBleWriteResponse, CountDownSetting countDownSetting, ICountDownListener iCountDownListener) {
        if (c.checkCountdown()) {
            this.vp_a.vp_a(iCountDownListener);
            vp_dz.vp_a("count_down_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, countDownSetting);
        }
    }

    public void readAlarm2(IBleWriteResponse iBleWriteResponse, IAlarm2DataListListener iAlarm2DataListListener) {
        if (c.checkMultiAlarm()) {
            this.vp_bd = iAlarm2DataListListener;
            List<Alarm2Setting> alarm2List = getAlarm2List();
            Iterator<Alarm2Setting> it = alarm2List.iterator();
            while (it.hasNext()) {
                new StringBuilder().append("新闹钟===》********>>>本地缓存的闹钟： ").append(it.next().toString());
            }
            this.vp_n.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, alarm2List, new vp_g());
        }
    }

    public void deleteAlarm2(IBleWriteResponse iBleWriteResponse, IAlarm2DataListListener iAlarm2DataListListener, Alarm2Setting alarm2Setting) {
        if (c.checkMultiAlarm()) {
            if (alarm2Setting == null) {
                throw new NullPointerException("alarm2Setting is null");
            }
            this.vp_bd = iAlarm2DataListListener;
            alarm2Setting.setBluetoothAddress(this.vp_bj);
            alarm2Setting.setMAFlag(this.vp_bj + "-" + alarm2Setting.getAlarmId());
            this.vp_n.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, alarm2Setting);
        }
    }

    public void addAlarm2(IBleWriteResponse iBleWriteResponse, IAlarm2DataListListener iAlarm2DataListListener, Alarm2Setting alarm2Setting) {
        if (c.checkMultiAlarm()) {
            this.vp_bd = iAlarm2DataListListener;
            if (alarm2Setting == null) {
                return;
            }
            List<Alarm2Setting> alarm2List = getAlarm2List();
            if (Alarm2Util.isAlarmListFull(alarm2List)) {
                iAlarm2DataListListener.onAlarmDataChangeListListener(new AlarmData2(EMultiAlarmOprate.ALARM_FULL, getAlarm2List()));
                return;
            }
            int unAddId = Alarm2Util.getUnAddId(alarm2List);
            new StringBuilder().append("alarmId=").append(unAddId);
            alarm2Setting.setBluetoothAddress(this.vp_bj);
            alarm2Setting.setAlarmId(unAddId);
            alarm2Setting.setMAFlag(this.vp_bj + "-" + unAddId);
            this.vp_n.vp_b(vp_dy, this.vp_bj, iBleWriteResponse, alarm2Setting);
        }
    }

    public void modifyAlarm2(IBleWriteResponse iBleWriteResponse, IAlarm2DataListListener iAlarm2DataListListener, Alarm2Setting alarm2Setting) {
        if (c.checkMultiAlarm()) {
            this.vp_bd = iAlarm2DataListListener;
            if (alarm2Setting == null) {
                return;
            }
            alarm2Setting.setBluetoothAddress(this.vp_bj);
            alarm2Setting.setMAFlag(this.vp_bj + "-" + alarm2Setting.getAlarmId());
            this.vp_n.vp_b(vp_dy, this.vp_bj, iBleWriteResponse, alarm2Setting);
        }
    }

    public void setOnDeviceAlarm2ChangedListener(OnDeviceAlarm2ChangedListener onDeviceAlarm2ChangedListener) {
        if (c.checkMultiAlarm()) {
            this.vp_n.vp_a(onDeviceAlarm2ChangedListener);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    public void addTextAlarm(IBleWriteResponse iBleWriteResponse, ITextAlarmDataListener iTextAlarmDataListener, TextAlarm2Setting textAlarm2Setting) {
        if (c.checkTextAlarm()) {
            ?? length = textAlarm2Setting;
            this.vp_be = iTextAlarmDataListener;
            if (length == 0) {
                return;
            }
            try {
                length = textAlarm2Setting.getContent().getBytes("UTF-8").length;
                if (length > 60) {
                    return;
                }
            } catch (UnsupportedEncodingException unused) {
                length.printStackTrace();
            }
            List<TextAlarm2Setting> textAlarmList = getTextAlarmList();
            if (Alarm2Util.isTextAlarmListFull(textAlarmList)) {
                iTextAlarmDataListener.onAlarmDataChangeListListener(new TextAlarmData(EMultiAlarmOprate.ALARM_FULL, getTextAlarmList()));
                return;
            }
            int unAddTextAlarmId = Alarm2Util.getUnAddTextAlarmId(textAlarmList);
            new StringBuilder().append("alarmId=").append(unAddTextAlarmId);
            textAlarm2Setting.setBluetoothAddress(this.vp_bj);
            textAlarm2Setting.setAlarmId(unAddTextAlarmId);
            textAlarm2Setting.setMAFlag(this.vp_bj + "-" + unAddTextAlarmId);
            this.vp_o.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, textAlarm2Setting, (byte) 2);
        }
    }

    public void modifyTextAlarm(IBleWriteResponse iBleWriteResponse, ITextAlarmDataListener iTextAlarmDataListener, TextAlarm2Setting textAlarm2Setting) {
        if (c.checkTextAlarm()) {
            this.vp_be = iTextAlarmDataListener;
            if (textAlarm2Setting == null) {
                return;
            }
            textAlarm2Setting.setBluetoothAddress(this.vp_bj);
            textAlarm2Setting.setMAFlag(this.vp_bj + "-" + textAlarm2Setting.getAlarmId());
            this.vp_o.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, textAlarm2Setting, (byte) -1);
        }
    }

    public void readTextAlarm(IBleWriteResponse iBleWriteResponse, ITextAlarmDataListener iTextAlarmDataListener) {
        if (c.checkTextAlarm()) {
            this.vp_be = iTextAlarmDataListener;
            List<TextAlarm2Setting> textAlarmList = getTextAlarmList();
            Iterator<TextAlarm2Setting> it = textAlarmList.iterator();
            while (it.hasNext()) {
                new StringBuilder().append("读取文字闹钟前本地缓存：").append(it.next().toString());
            }
            this.vp_o.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, textAlarmList);
        }
    }

    public void deleteTextAlarm(IBleWriteResponse iBleWriteResponse, ITextAlarmDataListener iTextAlarmDataListener, TextAlarm2Setting textAlarm2Setting) {
        if (c.checkTextAlarm()) {
            this.vp_be = iTextAlarmDataListener;
            if (textAlarm2Setting == null) {
                throw new NullPointerException("textAlarm2Setting is null");
            }
            textAlarm2Setting.setBluetoothAddress(this.vp_bj);
            textAlarm2Setting.setMAFlag(this.vp_bj + "-" + textAlarm2Setting.getAlarmId());
            this.vp_o.vp_a(vp_dy, this.vp_bj, (BleWriteResponse) iBleWriteResponse, textAlarm2Setting);
        }
    }

    public void readSportModelOrigin(IBleWriteResponse iBleWriteResponse, ISportModelOriginListener iSportModelOriginListener) {
        if (c.checkSportModel()) {
            this.vp_bf = iSportModelOriginListener;
            this.vp_p.vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void startSportModel(IBleWriteResponse iBleWriteResponse, ISportModelStateListener iSportModelStateListener) {
        if (c.checkSportModel()) {
            this.vp_a.vp_a(iSportModelStateListener);
            vp_dz.vp_a("sport_model_openclose_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, ESportType.NONE);
        }
    }

    public void startMultSportModel(IBleWriteResponse iBleWriteResponse, ISportModelStateListener iSportModelStateListener, ESportType eSportType) {
        if (c.isSupportMultSportModel()) {
            this.vp_a.vp_a(iSportModelStateListener);
            vp_dz.vp_a("sport_model_openclose_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, eSportType);
        }
    }

    public void stopSportModel(IBleWriteResponse iBleWriteResponse, ISportModelStateListener iSportModelStateListener) {
        if (c.checkSportModel()) {
            this.vp_a.vp_a(iSportModelStateListener);
            vp_dz.vp_a("sport_model_openclose_oprate").vp_cc(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readSportModelState(IBleWriteResponse iBleWriteResponse, ISportModelStateListener iSportModelStateListener) {
        if (c.checkSportModel()) {
            this.vp_a.vp_a(iSportModelStateListener);
            vp_dz.vp_a("sport_model_openclose_oprate").vp_ap(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readSpo2hOrigin(IBleWriteResponse iBleWriteResponse, ISpo2hOriginDataListener iSpo2hOriginDataListener, int i2) {
        readSpo2hOriginBySetting(iBleWriteResponse, iSpo2hOriginDataListener, new ReadOriginSetting(0, 1, false, i2));
    }

    public void readSpo2hOriginBySetting(IBleWriteResponse iBleWriteResponse, ISpo2hOriginDataListener iSpo2hOriginDataListener, ReadOriginSetting readOriginSetting) {
        boolean zIsReturnAllSpo2hData = c.isReturnAllSpo2hData(true);
        new StringBuilder().append("isReturnAllSpo2hData=").append(zIsReturnAllSpo2hData);
        this.vp_bg = iSpo2hOriginDataListener;
        this.vp_q.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readOriginSetting, zIsReturnAllSpo2hData);
    }

    public void readAutoDetectOriginDataFromS22(IBleWriteResponse iBleWriteResponse, IAutoDetectOriginDataListener iAutoDetectOriginDataListener, TimeData timeData) {
        int year;
        if (c.isSupportAutoDetect() && (year = timeData.getYear()) <= 2255 && year >= 2000) {
            this.vp_ab.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, timeData, iAutoDetectOriginDataListener);
        }
    }

    public void readAutoDetectStateFromS22(IBleWriteResponse iBleWriteResponse, ICustomProtocolStateListener iCustomProtocolStateListener) {
        if (c.isSupportAutoDetect()) {
            this.vp_co = iCustomProtocolStateListener;
            if (iCustomProtocolStateListener != null) {
                com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(this.vp_co);
            }
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_k(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setAutoDetectStateToS22(IBleWriteResponse iBleWriteResponse, ICustomProtocolStateListener iCustomProtocolStateListener, AutoDetectStateSetting autoDetectStateSetting) {
        if (c.isSupportAutoDetect()) {
            this.vp_co = iCustomProtocolStateListener;
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, autoDetectStateSetting);
        }
    }

    public void setJE136PTCMCustomData(IBleWriteResponse iBleWriteResponse, Map<TCMType, Integer> map, ITCMDataListener iTCMDataListener) {
        this.vp_cq = iTCMDataListener;
        if (iTCMDataListener != null) {
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(iTCMDataListener);
        }
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, map);
    }

    public void readJE136PTCMCustomData(IBleWriteResponse iBleWriteResponse, ITCMDataListener iTCMDataListener) {
        this.vp_cq = iTCMDataListener;
        if (iTCMDataListener != null) {
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(iTCMDataListener);
        }
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_ag(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readIMEIInfo(IBleWriteResponse iBleWriteResponse, IReadIMEIInfoListener iReadIMEIInfoListener) {
        this.vp_cr = iReadIMEIInfoListener;
        if (iReadIMEIInfoListener != null) {
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(iReadIMEIInfoListener);
        }
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_aa(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readHRVOrigin(IBleWriteResponse iBleWriteResponse, IHRVOriginDataListener iHRVOriginDataListener, int i2) {
        readHRVOriginBySetting(iBleWriteResponse, iHRVOriginDataListener, new ReadOriginSetting(0, 1, false, i2));
    }

    public void readHRVOriginBySetting(IBleWriteResponse iBleWriteResponse, IHRVOriginDataListener iHRVOriginDataListener, ReadOriginSetting readOriginSetting) {
        if (c.checkHRV(true)) {
            this.vp_bh = iHRVOriginDataListener;
            this.vp_x.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readOriginSetting);
        }
    }

    public void readECGId(BleWriteResponse bleWriteResponse, TimeData timeData, EEcgDataType eEcgDataType, IECGReadIdListener iECGReadIdListener) {
        if (c.checkECG()) {
            this.vp_v.vp_a(vp_dy, this.vp_bj, bleWriteResponse, timeData, eEcgDataType, iECGReadIdListener);
        }
    }

    public void readMultiECGId(BleWriteResponse bleWriteResponse, TimeData timeData, IECGReadIdListener iECGReadIdListener) {
        if (c.checkECG()) {
            this.vp_w.vp_a(vp_dy, this.vp_bj, bleWriteResponse, timeData, iECGReadIdListener);
        }
    }

    public void readMultiEcgData(BleWriteResponse bleWriteResponse, int[] iArr, IMultiEcgReadDataListener iMultiEcgReadDataListener) {
        if (c.checkECG()) {
            this.vp_w.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iArr, iMultiEcgReadDataListener);
        }
    }

    public void startDetectMultiECG(BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener) {
        if (c.checkECG()) {
            g = true;
            vp_a(z);
            this.vp_w.vp_a(vp_dy, this.vp_bj, bleWriteResponse, z, iMultiEcgDetectListener, c.checkEcgDisease());
        }
    }

    public void stopDetectMultiECG(BleWriteResponse bleWriteResponse) {
        if (c.checkECG()) {
            g = false;
            this.vp_w.vp_br(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void readECGManuallyData(BleWriteResponse bleWriteResponse, int[] iArr, IECGReadDataListener iECGReadDataListener) {
        if (c.checkECG()) {
            boolean zCheckEcgDisease = c.checkEcgDisease();
            new StringBuilder().append("isSupportDisease-------->").append(zCheckEcgDisease);
            this.vp_v.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iArr, zCheckEcgDisease, iECGReadDataListener);
        }
    }

    public void readECGData(BleWriteResponse bleWriteResponse, TimeData timeData, EEcgDataType eEcgDataType, IECGReadDataListener iECGReadDataListener) {
        if (c.checkECG()) {
            this.vp_v.vp_a(vp_dy, this.vp_bj, bleWriteResponse, timeData, eEcgDataType, iECGReadDataListener, c.checkEcgDisease());
        }
    }

    public void setNewEcgDataReportListener(INewECGDataReportListener iNewECGDataReportListener) {
        if (iNewECGDataReportListener == null) {
            return;
        }
        this.vp_v.vp_a(iNewECGDataReportListener);
    }

    public void setNewSportDataReportListener(ISportModelStateListener iSportModelStateListener) {
        if (iSportModelStateListener == null) {
            return;
        }
        this.vp_a.vp_a(iSportModelStateListener);
    }

    public void startDetectECG(BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            d = true;
            boolean zCheckEcgDisease = c.checkEcgDisease();
            vp_a(z);
            this.vp_r.vp_c(vp_dy, this.vp_bj, bleWriteResponse, z, iECGDetectListener, zCheckEcgDisease);
        }
    }

    public void stopDetectECG(BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            d = false;
            this.vp_r.vp_d(vp_dy, this.vp_bj, bleWriteResponse, z, iECGDetectListener, c.checkEcgDisease());
        }
    }

    public void startMultiLeadDetectECG(BleWriteResponse bleWriteResponse, boolean z, IECGMultiLeadDetectListener iECGMultiLeadDetectListener) {
        if (c.checkEcgMultiLead()) {
            f = true;
            vp_a(z);
            this.vp_t.vp_a(vp_dy, this.vp_bj, bleWriteResponse, z, c.checkEcgDisease(), iECGMultiLeadDetectListener);
        }
    }

    public void stopMultiLeadDetectECG(BleWriteResponse bleWriteResponse) {
        if (c.checkEcgMultiLead()) {
            f = false;
            this.vp_t.vp_ca(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void startReadPttSignData(BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            h = true;
            vp_a(eAdcType != EAdcType.NO_ADC_DATA);
            this.vp_u.vp_a(vp_dy, this.vp_bj, bleWriteResponse, eAdcType, iECGDetectListener, c.checkEcgDisease());
        }
    }

    public void openDevicePtt(BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            h = true;
            vp_a(z);
            this.vp_u.vp_b(vp_dy, this.vp_bj, bleWriteResponse, z, iECGDetectListener, c.checkEcgDisease());
        }
    }

    public void stopReadPttSignData(BleWriteResponse bleWriteResponse, EAdcType eAdcType, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            h = false;
            this.vp_u.vp_b(vp_dy, this.vp_bj, bleWriteResponse, eAdcType, iECGDetectListener, c.checkEcgDisease());
        }
    }

    public void closeDevicePtt(BleWriteResponse bleWriteResponse, boolean z, IECGDetectListener iECGDetectListener) {
        if (c.checkECG()) {
            h = false;
            this.vp_u.vp_a(vp_dy, this.vp_bj, bleWriteResponse, z, iECGDetectListener, c.checkEcgDisease());
        }
    }

    public void settingScreenLight(IBleWriteResponse iBleWriteResponse, IScreenLightListener iScreenLightListener, ScreenSetting screenSetting) {
        if (c.checkScreenlight()) {
            this.vp_a.vp_a(iScreenLightListener);
            vp_dz.vp_a("screen_ligth_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, screenSetting);
        }
    }

    public void settingGpsLatLonfromApp(IBleWriteResponse iBleWriteResponse, GpsLatLongSettingFromApp gpsLatLongSettingFromApp) {
        vp_dz.vp_a("gps_lat_lon_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, gpsLatLongSettingFromApp);
    }

    public void settingGpsLatLon(IBleWriteResponse iBleWriteResponse, IGpsLatLonDataListener iGpsLatLonDataListener, GpsLatLongSetting gpsLatLongSetting) {
        this.vp_a.vp_a(iGpsLatLonDataListener);
        vp_dz.vp_a("gps_lat_lon_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, gpsLatLongSetting);
    }

    public void readChantingData(IBleWriteResponse iBleWriteResponse, ChantingSetting chantingSetting, IChantingDataListener iChantingDataListener) {
        this.vp_a.vp_a(iChantingDataListener);
        vp_dz.vp_a("gps_lat_lon_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, chantingSetting);
    }

    public void settingKaaba(IBleWriteResponse iBleWriteResponse, KaabaSetting kaabaSetting, IKaaBaDataListener iKaaBaDataListener) {
        this.vp_a.vp_a(iKaaBaDataListener);
        vp_dz.vp_a("gps_lat_lon_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, kaabaSetting);
    }

    public void setReportGps(IBleWriteResponse iBleWriteResponse, boolean z, IReportGpsDataListener iReportGpsDataListener) {
        this.vp_a.vp_a(iReportGpsDataListener);
        vp_dz.vp_a("gps_lat_lon_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, z);
    }

    public void settingScreenStyle(IBleWriteResponse iBleWriteResponse, IScreenStyleListener iScreenStyleListener, int i2) {
        settingScreenStyle(iBleWriteResponse, iScreenStyleListener, i2, EUIFromType.DEFAULT);
    }

    public void readScreenStyle(IBleWriteResponse iBleWriteResponse, IScreenStyleListener iScreenStyleListener) {
        if (c.checkScreenStyle()) {
            this.vp_cn = iScreenStyleListener;
            this.vp_g.vp_al(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingBpFunctionState(IBleWriteResponse iBleWriteResponse, IBPFunctionListener iBPFunctionListener, boolean z) {
        this.vp_a.vp_a(iBPFunctionListener);
        vp_dz.vp_a("function_bp_oprate").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, z);
    }

    public void readWatchUiInfo(IBleWriteResponse iBleWriteResponse, EUIFromType eUIFromType, IUIBaseInfoListener iUIBaseInfoListener) {
        this.vp_a.vp_a(iUIBaseInfoListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eUIFromType);
    }

    public void setCustomWacthUi(IBleWriteResponse iBleWriteResponse, UICustomSetData uICustomSetData, IUIBaseInfoListener iUIBaseInfoListener) {
        this.vp_a.vp_a(iUIBaseInfoListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uICustomSetData);
    }

    public void makeDeviceIntoUpdateModeServer(IBleWriteResponse iBleWriteResponse, UIDataServer uIDataServer, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uIDataServer);
    }

    public void makeDeviceIntoUpdateModeImagePush(IBleWriteResponse iBleWriteResponse, UIDataImagePush uIDataImagePush, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uIDataImagePush);
    }

    public void makeDeviceIntoUpdateModeG15ImgAppDownloadQRCode(IBleWriteResponse iBleWriteResponse, UIDataG15Img uIDataG15Img, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uIDataG15Img);
    }

    public void makeDeviceIntoUpdateModeG15ImgProfile(IBleWriteResponse iBleWriteResponse, UIDataG15Img uIDataG15Img, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, uIDataG15Img);
    }

    public void makeDeviceIntoUpdateModeG15ImgTheme1(IBleWriteResponse iBleWriteResponse, UIDataG15Img uIDataG15Img, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_c(vp_dy, this.vp_bj, iBleWriteResponse, uIDataG15Img);
    }

    public void makeDeviceIntoUpdateModeG15ImgTheme2(IBleWriteResponse iBleWriteResponse, UIDataG15Img uIDataG15Img, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_d(vp_dy, this.vp_bj, iBleWriteResponse, uIDataG15Img);
    }

    public void makeDeviceIntoUpdateModeAGPS(IBleWriteResponse iBleWriteResponse, UIDataAGPS uIDataAGPS, int i2, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uIDataAGPS, i2);
    }

    public void startUiUpdate(IBleWriteResponse iBleWriteResponse, boolean z) {
        vp_dz.vp_a("function_big_data_tarn").vp_e(vp_dy, this.vp_bj, iBleWriteResponse, z);
    }

    public void startClearUi(IBleWriteResponse iBleWriteResponse, int i2, long j2) {
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, i2, j2);
    }

    public void sendUiData(IBleWriteResponse iBleWriteResponse, byte[] bArr) {
        vp_dz.vp_a("function_big_data_tarn").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, bArr);
    }

    public void endUiUpdate(IBleWriteResponse iBleWriteResponse) {
        vp_dz.vp_a("function_big_data_tarn").vp_d(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void checkUiCrc(IBleWriteResponse iBleWriteResponse, int i2) {
        vp_dz.vp_a("function_big_data_tarn").vp_b(vp_dy, this.vp_bj, iBleWriteResponse, i2);
    }

    public void readBpFunctionState(IBleWriteResponse iBleWriteResponse, IBPFunctionListener iBPFunctionListener) {
        this.vp_a.vp_a(iBPFunctionListener);
        vp_dz.vp_a("function_bp_oprate").vp_r(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readScreenLight(IBleWriteResponse iBleWriteResponse, IScreenLightListener iScreenLightListener) {
        if (c.checkScreenlight()) {
            this.vp_a.vp_a(iScreenLightListener);
            vp_dz.vp_a("screen_ligth_oprate").vp_aj(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readScreenLightTime(IBleWriteResponse iBleWriteResponse, IScreenLightTimeListener iScreenLightTimeListener) {
        if (c.checkScreenlightTime()) {
            this.vp_a.vp_a(iScreenLightTimeListener);
            vp_dz.vp_a("screen_ligth_time_oprate").vp_ak(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setScreenLightTime(IBleWriteResponse iBleWriteResponse, IScreenLightTimeListener iScreenLightTimeListener, int i2) {
        if (c.checkScreenlightTime()) {
            this.vp_a.vp_a(iScreenLightTimeListener);
            vp_dz.vp_a("screen_ligth_time_oprate").vp_d(vp_dy, this.vp_bj, iBleWriteResponse, i2);
        }
    }

    public void setttingCheckWear(IBleWriteResponse iBleWriteResponse, ICheckWearDataListener iCheckWearDataListener, CheckWearSetting checkWearSetting) {
        if (c.checkCheckWear()) {
            this.vp_a.vp_a(iCheckWearDataListener);
            vp_dz.vp_a("check_wear_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, checkWearSetting);
        }
    }

    public void setWearHandConfig(@NotNull EWearHandMode eWearHandMode, @NotNull IWearHandConfigListener iWearHandConfigListener, IBleWriteResponse iBleWriteResponse) {
        if (iWearHandConfigListener == null) {
            return;
        }
        if (eWearHandMode != EWearHandMode.LEFT_HAND && eWearHandMode != EWearHandMode.RIGHT_HAND) {
            new StringBuilder().append("***设置左右手佩戴配置值无效(仅支持LEFT_HAND/RIGHT_HAND): ").append(eWearHandMode);
            return;
        }
        new StringBuilder().append("**调用设置左右手佩戴配置：").append(eWearHandMode.getDes());
        this.vp_db = iWearHandConfigListener;
        vp_dz.vp_k().vp_a(eWearHandMode, vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readWearHandConfig(@NotNull IWearHandConfigListener iWearHandConfigListener, IBleWriteResponse iBleWriteResponse) {
        if (iWearHandConfigListener == null) {
            return;
        }
        this.vp_db = iWearHandConfigListener;
        vp_dz.vp_k().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readLowPower(IBleWriteResponse iBleWriteResponse, ILowPowerListener iLowPowerListener) {
        vp_a(iBleWriteResponse, iLowPowerListener, new LowPowerSetting(2, 0, 0, 0, 0, 0, 0));
    }

    public void settingLowpower(IBleWriteResponse iBleWriteResponse, ILowPowerListener iLowPowerListener, boolean z) {
        readLowPower(iBleWriteResponse, new vp_h(z, iBleWriteResponse, iLowPowerListener));
    }

    public void readSleepData(IBleWriteResponse iBleWriteResponse, ISleepDataListener iSleepDataListener, int i2) {
        readSleepDataFromDay(iBleWriteResponse, iSleepDataListener, 0, i2);
    }

    public void readSleepDataFromDay(IBleWriteResponse iBleWriteResponse, ISleepDataListener iSleepDataListener, int i2, int i3) {
        readSleepDataBySetting(iBleWriteResponse, iSleepDataListener, new ReadSleepSetting(i2, false, i3));
    }

    public void readSleepDataSingleDay(IBleWriteResponse iBleWriteResponse, ISleepDataListener iSleepDataListener, int i2, int i3) {
        readSleepDataBySetting(iBleWriteResponse, iSleepDataListener, new ReadSleepSetting(i2, true, i3));
    }

    public void readSleepDataBySetting(IBleWriteResponse iBleWriteResponse, ISleepDataListener iSleepDataListener, ReadSleepSetting readSleepSetting) {
        this.vp_ay = iSleepDataListener;
        this.vp_f.vp_a(VpSpGetUtil.getVpSpVariInstance(mContext).isSupportPreciseSleep());
        this.vp_f.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readSleepSetting);
    }

    public void readWeatherStatusInfo(IBleWriteResponse iBleWriteResponse, IWeatherStatusDataListener iWeatherStatusDataListener) {
        if (c.checkWeather()) {
            this.vp_a.vp_a(iWeatherStatusDataListener);
            this.vp_h.vp_as(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingWeatherStatusInfo(IBleWriteResponse iBleWriteResponse, WeatherStatusSetting weatherStatusSetting, IWeatherStatusDataListener iWeatherStatusDataListener) {
        if (c.checkWeather()) {
            this.vp_a.vp_a(iWeatherStatusDataListener);
            this.vp_h.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, weatherStatusSetting);
        }
    }

    public void settingWeatherData(IBleWriteResponse iBleWriteResponse, WeatherData weatherData, IWeatherStatusDataListener iWeatherStatusDataListener) {
        if (c.checkWeather() && !c.isUMweather()) {
            this.vp_a.vp_a(iWeatherStatusDataListener);
            this.vp_h.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, weatherData);
        }
    }

    public void settingWeatherData2(IBleWriteResponse iBleWriteResponse, List<WeatherData2> list, IWeatherStatusDataListener iWeatherStatusDataListener) {
        if (c.checkWeather() && c.isUMweather()) {
            this.vp_a.vp_a(iWeatherStatusDataListener);
            this.vp_h.vp_c(vp_dy, this.vp_bj, iBleWriteResponse, list);
        }
    }

    public void readOriginData(IBleWriteResponse iBleWriteResponse, IOriginProgressListener iOriginProgressListener, int i2) {
        readOriginDataBySetting(iBleWriteResponse, iOriginProgressListener, new ReadOriginSetting(0, 1, false, i2));
    }

    public void readOriginDataFromDay(IBleWriteResponse iBleWriteResponse, IOriginProgressListener iOriginProgressListener, int i2, int i3, int i4) {
        readOriginDataBySetting(iBleWriteResponse, iOriginProgressListener, new ReadOriginSetting(i2, i3, false, i4));
    }

    public void readOriginDataSingleDay(IBleWriteResponse iBleWriteResponse, IOriginProgressListener iOriginProgressListener, int i2, int i3, int i4) {
        readOriginDataBySetting(iBleWriteResponse, iOriginProgressListener, new ReadOriginSetting(i2, i3, true, i4));
    }

    public void readOriginDataBySetting(IBleWriteResponse iBleWriteResponse, IOriginProgressListener iOriginProgressListener, ReadOriginSetting readOriginSetting) {
        if (readOriginSetting.getPosition() < 1) {
            readOriginSetting.setPosition(1);
        }
        int originProtocolVersion = VpSpGetUtil.getVpSpVariInstance(mContext).getOriginProtocolVersion();
        if (originProtocolVersion != 3 && originProtocolVersion != 5) {
            if (!(iOriginProgressListener instanceof IOriginDataListener)) {
                new StringBuilder().append("当前协议版本为：").append(originProtocolVersion).append(",传入的接口有误,请使用IOriginDataListener");
                return;
            } else {
                this.vp_ba = (IOriginDataListener) iOriginProgressListener;
                this.vp_j.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readOriginSetting);
                return;
            }
        }
        if (!(iOriginProgressListener instanceof IOriginData3Listener)) {
            new StringBuilder().append("当前协议版本为： ").append(originProtocolVersion).append(" ,传入的接口有误,请使用IOriginData3Listener");
            return;
        }
        this.vp_bb = (IOriginData3Listener) iOriginProgressListener;
        boolean zIsReturnAllSpo2hData = c.isReturnAllSpo2hData(false);
        boolean zCheckHRV = c.checkHRV(false);
        new StringBuilder().append("当前协议版本为：").append(originProtocolVersion).append(",isReturnAllSpo2hData=").append(zIsReturnAllSpo2hData).append(",isReturnHRVData=").append(zCheckHRV);
        this.vp_k.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readOriginSetting, zIsReturnAllSpo2hData, zCheckHRV);
    }

    public void readDeviceManualData(IBleWriteResponse iBleWriteResponse, long j2, List<DeviceManualDataType> list, List<DeviceManualDataType> list2, IDeviceManualDetectDataListener iDeviceManualDetectDataListener) {
        this.vp_bc = iDeviceManualDetectDataListener;
        this.vp_l.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, j2, list, new ArrayList());
    }

    public void readRRIntervalByDay(IBleWriteResponse iBleWriteResponse, IRRIntervalProgressListener iRRIntervalProgressListener, DayState dayState, int i2) {
        if (i2 < 1) {
            i2 = 1;
        }
        this.vp_bx = iRRIntervalProgressListener;
        this.vp_aa.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, dayState, i2);
    }

    public RRLorenzInfo convertRRData2RRLorenzInfo(int[] iArr) {
        if (c.checkLorentz(true) && iArr != null && iArr.length >= 2000) {
            return com.veepoo.protocol.util.vp_ab.vp_a(iArr);
        }
        return null;
    }

    public void readTemptureDataBySetting(IBleWriteResponse iBleWriteResponse, ITemptureDataListener iTemptureDataListener, ReadOriginSetting readOriginSetting) {
        if (c.isCanReadTempture()) {
            this.vp_az = iTemptureDataListener;
            this.vp_m.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, readOriginSetting);
        }
    }

    public void readAllHealthDataBySettingOrigin(IAllHealthDataListener iAllHealthDataListener, int i2, int i3, int i4) {
        readSleepDataFromDay(this.vp_du, new vp_i(iAllHealthDataListener, i2, i3, i4), i2, i4);
    }

    public void readAllHealthData(IAllHealthDataListener iAllHealthDataListener, int i2) {
        readSleepData(this.vp_du, new vp_m(iAllHealthDataListener, i2), i2);
    }

    public void checkVersionAndFile(OadSetting oadSetting, OnUpdateCheckListener onUpdateCheckListener) {
        new vp_bt(mContext, oadSetting, onUpdateCheckListener).vp_a();
    }

    public void getOadVersion(OadSetting oadSetting, OnGetOadVersionListener onGetOadVersionListener) {
        new vp_bt(mContext, oadSetting, onGetOadVersionListener).vp_a();
    }

    public void findOadModelDevice(OadSetting oadSetting, OnFindOadDeviceListener onFindOadDeviceListener) {
        new vp_bs(mContext, oadSetting, onFindOadDeviceListener).vp_c();
    }

    public void listenDeviceCallbackData(IBleNotifyResponse iBleNotifyResponse) {
        this.vp_e = iBleNotifyResponse;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public String traversalShareperence() {
        ?? TraversalShareperence = "";
        ?? r2 = "";
        try {
            TraversalShareperence = VpSpGetUtil.getVpSpVariInstance(mContext).traversalShareperence();
            r2 = TraversalShareperence;
        } catch (IllegalAccessException unused) {
            TraversalShareperence.printStackTrace();
        }
        return r2;
    }

    public void sendOrder(BleWriteResponse bleWriteResponse, byte[] bArr) {
        vp_dz.vp_a("pwd_comfirm_oprate").vp_a(vp_dy, this.vp_bj, bleWriteResponse, bArr);
    }

    public void setECGAutoReportListener(IECGAutoReportListener iECGAutoReportListener) {
        if (iECGAutoReportListener == null) {
            return;
        }
        this.vp_ad.vp_a(iECGAutoReportListener);
    }

    public void readECGSwitchStatus(IBleWriteResponse iBleWriteResponse, IECGSwitchListener iECGSwitchListener) {
        if (iECGSwitchListener == null) {
            return;
        }
        this.vp_by = iECGSwitchListener;
        this.vp_ae.vp_cf(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void openECGSwitch(IBleWriteResponse iBleWriteResponse, IECGSwitchListener iECGSwitchListener) {
        if (iECGSwitchListener == null) {
            return;
        }
        this.vp_by = iECGSwitchListener;
        this.vp_ae.vp_ce(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void closeECGSwitch(IBleWriteResponse iBleWriteResponse, IECGSwitchListener iECGSwitchListener) {
        if (iECGSwitchListener == null) {
            return;
        }
        this.vp_by = iECGSwitchListener;
        this.vp_ae.vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void startBloodGlucoseDetect(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (iBloodGlucoseChangeListener == null) {
            return;
        }
        this.vp_de = iBloodGlucoseChangeListener;
        this.vp_ah.vp_az(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void stopBloodGlucoseDetect(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (iBloodGlucoseChangeListener == null) {
            return;
        }
        this.vp_de = iBloodGlucoseChangeListener;
        this.vp_ah.vp_bk(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void readBloodGlucoseAdjustingData(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (c.checkBgAdjusting()) {
            this.vp_de = iBloodGlucoseChangeListener;
            this.vp_ah.vp_q(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readMultipleCalibrationBGValue(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (c.checkBgMultipleAdjusting()) {
            this.vp_de = iBloodGlucoseChangeListener;
            this.vp_ah.vp_am(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setBloodGlucoseAdjustingData(float f2, boolean z, IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (c.checkBgAdjusting()) {
            this.vp_de = iBloodGlucoseChangeListener;
            this.vp_ah.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, f2, z);
        }
    }

    public void settingMultipleCalibrationBGValue(boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3, IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener) {
        if (c.checkBgMultipleAdjusting()) {
            this.vp_de = iBloodGlucoseChangeListener;
            this.vp_ah.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, z, mealInfo, mealInfo2, mealInfo3);
        }
    }

    public void openJLDataNotify(BleNotifyResponse bleNotifyResponse) {
        vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_o, com.veepoo.protocol.profile.vp_a.vp_q, new vp_u(bleNotifyResponse));
    }

    public void openNordicSPPNotify(BleNotifyResponse bleNotifyResponse) {
        vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_u, com.veepoo.protocol.profile.vp_a.vp_w, new vp_w(bleNotifyResponse));
    }

    public void connectBT(String str, int i2, IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        isUseDefaultBTType = false;
        customBTConnectType = i2;
        this.vp_cb = iDeviceBTConnectionListener;
        com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_j(com.veepoo.protocol.jl.classic.vp_d.vp_a(str));
    }

    public void disconnectBT(String str, IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        this.vp_cb = iDeviceBTConnectionListener;
        BluetoothDevice bluetoothDeviceVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(str);
        if (bluetoothDeviceVp_a == null) {
            new StringBuilder().append("断开BT连接异常->蓝牙设备地址异常:").append(str);
        } else {
            com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_c(bluetoothDeviceVp_a);
            com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_k(bluetoothDeviceVp_a);
        }
    }

    public void readBTInfo(IBleWriteResponse iBleWriteResponse, IDeviceBTInfoListener iDeviceBTInfoListener) {
        this.vp_cc = iDeviceBTInfoListener;
        this.vp_ai.vp_n(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void setBTStatus(boolean z, boolean z2, boolean z3, boolean z4, IBleWriteResponse iBleWriteResponse) {
        this.vp_ai.vp_a(z, z2, z3, z4, vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void setBTSwitchStatus(boolean z, IBleWriteResponse iBleWriteResponse) {
        this.vp_ai.vp_b(z, true, vp_dy, this.vp_bj, (BleWriteResponse) iBleWriteResponse);
    }

    public void bleDeviceRename(String str, @NotNull IDeviceRenameListener iDeviceRenameListener, IBleWriteResponse iBleWriteResponse) {
        vp_a(true, str, iDeviceRenameListener, iBleWriteResponse);
    }

    @Deprecated
    public void classicBTDeviceRename(String str, IDeviceRenameListener iDeviceRenameListener, IBleWriteResponse iBleWriteResponse) {
        vp_a(false, str, iDeviceRenameListener, iBleWriteResponse);
    }

    public void readJH76SNCode(@NotNull IJH76SNCodeReadListener iJH76SNCodeReadListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iJH76SNCodeReadListener != null) {
            this.vp_ct = iJH76SNCodeReadListener;
            vp_dz.vp_h().vp_ce(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setJH76SNCode(String str, @NotNull IJH76SNCodeOptListener iJH76SNCodeOptListener, IBleWriteResponse iBleWriteResponse) {
        if (iJH76SNCodeOptListener == null) {
            return;
        }
        EJH76SNCodeError eJH76SNCodeErrorVp_a = vp_a(str);
        if (eJH76SNCodeErrorVp_a != EJH76SNCodeError.SUCCESS) {
            iJH76SNCodeOptListener.onJH76SNCodeOptResult(eJH76SNCodeErrorVp_a);
        } else if (checkDeviceIsConnected()) {
            this.vp_cu = iJH76SNCodeOptListener;
            vp_dz.vp_h().vp_c(str, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void modifyJH76SNCode(String str, @NotNull IJH76SNCodeOptListener iJH76SNCodeOptListener, IBleWriteResponse iBleWriteResponse) {
        setJH76SNCode(str, iJH76SNCodeOptListener, iBleWriteResponse);
    }

    public void deleteJH76SNCode(@NotNull IJH76SNCodeOptListener iJH76SNCodeOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iJH76SNCodeOptListener != null) {
            this.vp_cu = iJH76SNCodeOptListener;
            vp_dz.vp_h().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void openYM23ProWearReport(@NotNull IYM23ProWearReportListener iYM23ProWearReportListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iYM23ProWearReportListener != null) {
            this.vp_cv = iYM23ProWearReportListener;
            vp_dz.vp_l().vp_ce(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void closeYM23ProWearReport(@NotNull IYM23ProWearReportListener iYM23ProWearReportListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iYM23ProWearReportListener != null) {
            this.vp_cv = iYM23ProWearReportListener;
            vp_dz.vp_l().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGSingleLeadTestDuration(int i2, @NotNull IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGSingleLeadTestDurationListener == null) {
            return;
        }
        if (i2 < 0 || i2 > 65535) {
            new StringBuilder().append("***JH151设置ECG单导测量时长超出范围(0~65535): ").append(i2);
            iECGSingleLeadTestDurationListener.onECGSingleLeadTestDurationResult(false, 0);
        } else if (checkDeviceIsConnected()) {
            this.vp_cx = iECGSingleLeadTestDurationListener;
            vp_dz.vp_g().vp_d(i2, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGSingleLeadTestDuration(@NotNull IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iECGSingleLeadTestDurationListener != null) {
            this.vp_cx = iECGSingleLeadTestDurationListener;
            vp_dz.vp_g().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setJH151ECGSingleLeadTestDuration(int i2, @NotNull IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener, IBleWriteResponse iBleWriteResponse) {
        setECGSingleLeadTestDuration(i2, iECGSingleLeadTestDurationListener, iBleWriteResponse);
    }

    public void readJH151ECGSingleLeadTestDuration(@NotNull IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener, IBleWriteResponse iBleWriteResponse) {
        readECGSingleLeadTestDuration(iECGSingleLeadTestDurationListener, iBleWriteResponse);
    }

    public void setECGPatchSpo2MeasureSwitch(@NotNull EEcgPatchSpo2Switch eEcgPatchSpo2Switch, @NotNull IECGPatchSpo2SwitchListener iECGPatchSpo2SwitchListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchSpo2SwitchListener == null) {
            return;
        }
        if (VpSpGetUtil.getVpSpVariInstance(mContext).getECGType() != 15) {
            iECGPatchSpo2SwitchListener.onECGPatchSpo2SwitchResult(false, EEcgPatchSpo2Switch.NONE);
        } else if (checkDeviceIsConnected()) {
            this.vp_cy = iECGPatchSpo2SwitchListener;
            vp_dz.vp_f().vp_a(eEcgPatchSpo2Switch, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchSpo2MeasureSwitch(@NotNull IECGPatchSpo2SwitchListener iECGPatchSpo2SwitchListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchSpo2SwitchListener == null) {
            return;
        }
        if (VpSpGetUtil.getVpSpVariInstance(mContext).getECGType() != 15) {
            iECGPatchSpo2SwitchListener.onECGPatchSpo2SwitchResult(false, EEcgPatchSpo2Switch.NONE);
        } else if (checkDeviceIsConnected()) {
            this.vp_cy = iECGPatchSpo2SwitchListener;
            vp_dz.vp_f().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void enterECGPatchAutoDetectMode(@NotNull IECGPatchAutoDetectListener iECGPatchAutoDetectListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchAutoDetectListener != null && VpSpGetUtil.getVpSpVariInstance(mContext).getECGType() == 15 && checkDeviceIsConnected()) {
            this.vp_cz = iECGPatchAutoDetectListener;
            vp_dz.vp_d().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void exitECGPatchAutoDetectMode(@NotNull IECGPatchAutoDetectListener iECGPatchAutoDetectListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchAutoDetectListener != null && VpSpGetUtil.getVpSpVariInstance(mContext).getECGType() == 15 && checkDeviceIsConnected()) {
            this.vp_cz = iECGPatchAutoDetectListener;
            vp_dz.vp_d().vp_ce(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGPatchMultiLeadSingleDuration(int i2, @NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener == null) {
            return;
        }
        if (i2 < 0 || i2 > 65535) {
            new StringBuilder().append("***心电贴设置多导联单次测量时长超出范围(0~65535): ").append(i2);
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(EEcgPatchSettingType.SINGLE_MEASURE_DURATION, false, new ECGPatchMultiLeadSettingData());
        } else if (vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_d(i2, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchMultiLeadSingleDuration(@NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener != null && vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_ch(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGPatchMultiLeadDndSetting(@NotNull EEcgPatchSwitch eEcgPatchSwitch, int i2, int i3, int i4, int i5, @NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener == null) {
            return;
        }
        if (eEcgPatchSwitch != EEcgPatchSwitch.CLOSE && eEcgPatchSwitch != EEcgPatchSwitch.OPEN) {
            new StringBuilder().append("***心电贴设置多导联免打扰开关值无效(仅支持CLOSE/OPEN): ").append(eEcgPatchSwitch);
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(EEcgPatchSettingType.DO_NOT_DISTURB, false, new ECGPatchMultiLeadSettingData());
        } else if (vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_a(eEcgPatchSwitch, i2, i3, i4, i5, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchMultiLeadDndSetting(@NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener != null && vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_ce(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGPatchMultiLeadHeartAbnormalRemind(@NotNull EEcgPatchSwitch eEcgPatchSwitch, int i2, int i3, @NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener == null) {
            return;
        }
        if (eEcgPatchSwitch != EEcgPatchSwitch.CLOSE && eEcgPatchSwitch != EEcgPatchSwitch.OPEN) {
            new StringBuilder().append("***心电贴设置多导联Heart Rate异常提醒开关值无效(仅支持CLOSE/OPEN): ").append(eEcgPatchSwitch);
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(EEcgPatchSettingType.HEART_ABNORMAL_REMIND, false, new ECGPatchMultiLeadSettingData());
        } else if (vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_a(eEcgPatchSwitch, i2, i3, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchMultiLeadHeartAbnormalRemind(@NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener != null && vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_cf(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGPatchMultiLeadDiseaseRemindSwitch(@NotNull EEcgPatchSwitch eEcgPatchSwitch, @NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener == null) {
            return;
        }
        if (eEcgPatchSwitch != EEcgPatchSwitch.CLOSE && eEcgPatchSwitch != EEcgPatchSwitch.OPEN) {
            new StringBuilder().append("***心电贴设置多导联疾病提醒开关值无效(仅支持CLOSE/OPEN): ").append(eEcgPatchSwitch);
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(EEcgPatchSettingType.DISEASE_REMIND, false, new ECGPatchMultiLeadSettingData());
        } else if (vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_a(eEcgPatchSwitch, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchMultiLeadDiseaseRemindSwitch(@NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener != null && vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setECGPatchMultiLeadLeadOffRemindSwitch(@NotNull EEcgPatchSwitch eEcgPatchSwitch, @NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener == null) {
            return;
        }
        if (eEcgPatchSwitch != EEcgPatchSwitch.CLOSE && eEcgPatchSwitch != EEcgPatchSwitch.OPEN) {
            new StringBuilder().append("***心电贴设置多导联导联脱落提醒开关值无效(仅支持CLOSE/OPEN): ").append(eEcgPatchSwitch);
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(EEcgPatchSettingType.LEAD_OFF_REMIND, false, new ECGPatchMultiLeadSettingData());
        } else if (vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_b(eEcgPatchSwitch, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readECGPatchMultiLeadLeadOffRemindSwitch(@NotNull IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener, IBleWriteResponse iBleWriteResponse) {
        if (iECGPatchMultiLeadSettingListener != null && vp_a()) {
            this.vp_da = iECGPatchMultiLeadSettingListener;
            vp_dz.vp_e().vp_cg(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setYM23ProWearReportListener(@NotNull IYM23ProWearReportListener iYM23ProWearReportListener) {
        this.vp_cv = iYM23ProWearReportListener;
    }

    public void setYM23ProTestStatus(@NotNull EYM23ProTestStatus eYM23ProTestStatus, @NotNull IYM23ProTestStatusListener iYM23ProTestStatusListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && iYM23ProTestStatusListener != null) {
            if (eYM23ProTestStatus == null || eYM23ProTestStatus == EYM23ProTestStatus.UNKONW) {
                iYM23ProTestStatusListener.onYM23ProTestStatusResult(EYM23ProOptStatus.UNKONW, EYM23ProTestStatus.UNKONW);
            } else {
                this.vp_cw = iYM23ProTestStatusListener;
                vp_dz.vp_l().vp_a(eYM23ProTestStatus, vp_dy, this.vp_bj, iBleWriteResponse);
            }
        }
    }

    public void readHealthRemind(@NotNull HealthRemindType healthRemindType, @NotNull IHealthRemindListener iHealthRemindListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_cd = iHealthRemindListener;
            this.vp_aj.vp_a(healthRemindType, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void settingHealthRemind(@NotNull HealthRemind healthRemind, @NotNull IHealthRemindListener iHealthRemindListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_cd = iHealthRemindListener;
            this.vp_aj.vp_a(healthRemind, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readContact(int i2, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(i2, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void addContact(@NotNull Contact contact, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(contact, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void addContactList(@NotNull List<Contact> list, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && !list.isEmpty() && list.size() <= 10) {
            int size = list.size();
            com.veepoo.protocol.operate.vp_r.vp_a vp_aVar = com.veepoo.protocol.operate.vp_r.vp_m;
            if (size > vp_aVar.vp_a().vp_b()) {
                new StringBuilder().append("新增联系人列表数量已超过可以增加的个数,当前增加：").append(list.size()).append("个，可增加：").append(vp_aVar.vp_a().vp_b()).append("个。\n如确定设备端个数不一致，请先调用readContact更新当前联系人个数");
            } else {
                this.vp_ce = iContactOptListener;
                vp_aVar.vp_a().vp_a(list, vp_dy, this.vp_bj, iBleWriteResponse);
            }
        }
    }

    public void deleteContact(@NotNull Contact contact, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_b(contact, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void deleteContactList(@NotNull List<Contact> list, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected() && !list.isEmpty() && list.size() <= 10) {
            int size = list.size();
            com.veepoo.protocol.operate.vp_r.vp_a vp_aVar = com.veepoo.protocol.operate.vp_r.vp_m;
            if (size > vp_aVar.vp_a().vp_d().size()) {
                new StringBuilder().append("删除的联系人列表数量已超过可以删除的个数,当前删除：").append(list.size()).append("个，可删除：").append(vp_aVar.vp_a().vp_d().size()).append("个。\n如确定设备端个数不一致，请先调用readContact更新当前联系人个数");
            } else {
                this.vp_ce = iContactOptListener;
                vp_aVar.vp_a().vp_c(list, vp_dy, this.vp_bj, iBleWriteResponse);
            }
        }
    }

    public void moveContact(@NotNull Contact contact, @NotNull Contact contact2, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(contact, contact2, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void moveContactWithPosition(@NotNull int i2, @NotNull int i3, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_b(i2, i3, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setContactSOSState(boolean z, @NotNull Contact contact, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(z, contact, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readSOSCallTimes(@NotNull ISOSCallTimesListener iSOSCallTimesListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ch = iSOSCallTimesListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_ai(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setSOSCallTimes(int i2, @NotNull ISOSCallTimesListener iSOSCallTimesListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ch = iSOSCallTimesListener;
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_c(i2, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void powerOffDevice(IBleWriteResponse iBleWriteResponse, IResponseListener iResponseListener) {
        if (iResponseListener == null) {
            return;
        }
        this.vp_ca = new vp_x(iResponseListener);
        this.vp_ag.vp_a(DebugCmd.CMD4, vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void updatePhotoDial() {
        VpSpGetUtil.getVpSpVariInstance(mContext).getCPUType();
    }

    public void startJLDeviceAuth(RcspAuthResponse rcspAuthResponse) {
        vp_dy.startJLDeviceAuth(this.vp_bj, new vp_y(rcspAuthResponse));
    }

    public boolean isJLSDKFileSystemInit() {
        return JLWatchFaceManager.getInstance().isJLFatFileSystemInitSuccess();
    }

    public void listJLWatchList(JLWatchFaceManager.OnWatchDialInfoGetListener onWatchDialInfoGetListener) {
        JLWatchFaceManager.getInstance().checkJLSDKAndInit(onWatchDialInfoGetListener);
    }

    public void setJLWatchPhotoDial(String str, JLWatchFaceManager.JLTransferPicDialListener jLTransferPicDialListener) {
        if (jLTransferPicDialListener == null) {
            return;
        }
        if (!isCurrentDeviceConnected()) {
            jLTransferPicDialListener.onTransferError(-1, "当前无设备连接");
            return;
        }
        if (!isJLDevice()) {
            jLTransferPicDialListener.onTransferError(-1, "当前设备非杰理平台，设置照片表盘请参考 demo中 UiUpdateUtil 设置照片表盘");
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            UiUpdateUtil.getInstance().init(mContext);
            UiUpdateUtil.getInstance().getCustomWatchUiInfo(new vp_z(str, jLTransferPicDialListener));
        } else {
            jLTransferPicDialListener.onTransferError(-1, "当前照片不存在");
            new StringBuilder().append("照片:").append(str).append(", 文件无效，请检查确保该文件存在且有效");
        }
    }

    public void setJLWatchDial(String str, JLWatchHolder.OnSetJLWatchDialListener onSetJLWatchDialListener) {
        if (isCurrentDeviceConnected() && isJLDevice() && onSetJLWatchDialListener != null) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                JLWatchHolder.getInstance().updateJLWatchServerDial(str, onSetJLWatchDialListener);
            } else {
                onSetJLWatchDialListener.onFiled(-1, "当前表盘文件不存在");
                new StringBuilder().append("当前表盘文件:").append(str).append(", 文件无效，请检查确保该文件存在且有效");
            }
        }
    }

    public void startJLDeviceOTAUpgrade(String str, JLOTAHolder.OnJLDeviceOTAListener onJLDeviceOTAListener) {
        if (!isCurrentDeviceConnected() || TextUtils.isEmpty(str) || onJLDeviceOTAListener == null) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            JLOTAHolder.getInstance().checkInit2StartOTA(str, onJLDeviceOTAListener);
        } else {
            onJLDeviceOTAListener.onOTAFailed(new BaseError(4373, "固件:" + str + ", 文件无效，请检查确保该文件存在且有效"));
        }
    }

    public void startZkOtaUpgrade(String str, IZkOtaUpgradeListener iZkOtaUpgradeListener) {
        if (isCurrentDeviceConnected() && VpSpGetUtil.getVpSpVariInstance(mContext).isZKDevice() && !TextUtils.isEmpty(str) && iZkOtaUpgradeListener != null) {
            ZkOtaManager.getInstance().init(vp_dy, this.vp_bj);
            ZkOtaManager.getInstance().startOtaUpgrade(str, iZkOtaUpgradeListener);
        }
    }

    public void startNordicOtaUpgrade(String str, OnMcuMgrOtaListener onMcuMgrOtaListener) {
        if (!VpSpGetUtil.getVpSpVariInstance(mContext).isNewNordicDevice() || TextUtils.isEmpty(str) || onMcuMgrOtaListener == null) {
            return;
        }
        if (McuMgrOtaManager.getInstance().getMtu() <= 23) {
            changeMTU(247, i2 -> {
                vp_a(str, onMcuMgrOtaListener);
            });
        } else {
            vp_a(str, onMcuMgrOtaListener);
        }
    }

    public boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public boolean isBluetrumDevice() {
        return isCurrentDeviceConnected() && VpSpGetUtil.getVpSpVariInstance(mContext).isZKDevice();
    }

    public boolean isNordicDevice() {
        return isCurrentDeviceConnected() && VpSpGetUtil.getVpSpVariInstance(mContext).isNewNordicDevice();
    }

    public boolean isJLDevice() {
        return isCurrentDeviceConnected() && (VpSpGetUtil.getVpSpVariInstance(mContext).isJieLiDevice() || BleConnectWorker.isFindJLService);
    }

    public boolean isJlDfuModel() {
        return isJLDevice() && JLOTAManager.getInstance().isJlDeviceDfuModel();
    }

    public void readRssi(String str, BleReadRssiResponse bleReadRssiResponse) {
        vp_dy.readRssi(str, bleReadRssiResponse);
    }

    public boolean isJLNotifyOpened() {
        return this.vp_df;
    }

    public void readProductionInfo(IBleWriteResponse iBleWriteResponse, IReadProductionInfoListener iReadProductionInfoListener) {
        this.vp_a.vp_a(iReadProductionInfoListener);
        vp_dz.vp_a("read_production_info_oprate").vp_af(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void startDetectBodyComponent(BleWriteResponse bleWriteResponse, IBodyComponentDetectListener iBodyComponentDetectListener) {
        if (c.checkBodyComponent()) {
            this.vp_ak.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iBodyComponentDetectListener);
        }
    }

    public void stopDetectBodyComponent(BleWriteResponse bleWriteResponse) {
        if (c.checkBodyComponent()) {
            this.vp_ak.vp_bl(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void readBodyComponentId(BleWriteResponse bleWriteResponse, IBodyComponentReadIdListener iBodyComponentReadIdListener) {
        if (c.checkBodyComponent()) {
            this.vp_ar.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iBodyComponentReadIdListener);
        }
    }

    public void readBodyComponentData(BleWriteResponse bleWriteResponse, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
        if (c.checkBodyComponent()) {
            this.vp_ar.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iBodyComponentReadDataListener);
        }
    }

    public void setBodyComponentReportListener(INewBodyComponentReportListener iNewBodyComponentReportListener) {
        this.vp_ar.vp_a(iNewBodyComponentReportListener);
    }

    public void startDetectBloodComponent(BleWriteResponse bleWriteResponse, boolean z, IBloodComponentDetectListener iBloodComponentDetectListener) {
        if (c.checkBloodComponent()) {
            this.vp_a.vp_a(iBloodComponentDetectListener);
            vp_dz.vp_a("blood_component_operate").vp_a(vp_dy, this.vp_bj, bleWriteResponse, Boolean.valueOf(z));
        }
    }

    public void stopDetectBloodComponent(BleWriteResponse bleWriteResponse) {
        if (c.checkBloodComponent()) {
            vp_dz.vp_a("blood_component_operate").vp_bj(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void readBloodComponentCalibration(BleWriteResponse bleWriteResponse, IBloodComponentOptListener iBloodComponentOptListener) {
        if (c.checkBloodComponent()) {
            this.vp_a.vp_a(iBloodComponentOptListener);
            vp_dz.vp_a("blood_component_operate").vp_p(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void settingBloodComponentCalibration(BleWriteResponse bleWriteResponse, boolean z, BloodComponent bloodComponent, IBloodComponentOptListener iBloodComponentOptListener) {
        if (c.checkBloodComponent()) {
            this.vp_a.vp_a(iBloodComponentOptListener);
            vp_dz.vp_a("blood_component_operate").vp_a(vp_dy, this.vp_bj, bleWriteResponse, Boolean.valueOf(z), bloodComponent);
        }
    }

    public void getHrvAnalysisReport(List<HRVOriginData> list, IHrvAnalysisReportListener iHrvAnalysisReportListener) {
        if (list != null && !list.isEmpty()) {
            HBThreadPools.getInstance().executeWitSmartCall(new vp_ab(list, iHrvAnalysisReportListener, list.get(0).getDate()));
        } else if (iHrvAnalysisReportListener != null) {
            iHrvAnalysisReportListener.onHrvAnalysisReport(null, null);
        }
    }

    public void readSportDataCrc(BleWriteResponse bleWriteResponse, ISportModelDataCrcListener iSportModelDataCrcListener) {
        if (c.checkSportModel()) {
            this.vp_a.vp_a(iSportModelDataCrcListener);
            vp_dz.vp_a("sport_model_crc_oprate").vp_ao(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void readWorldClock(int i2, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_b(i2, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void addWorldClock(WorldClock worldClock, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_a(worldClock, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void addWorldClocks(List<WorldClock> list, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_b(list, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void deleteWorldClock(WorldClock worldClock, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_b(worldClock, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void deleteWorldClocks(List<WorldClock> list, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_d(list, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void moveWorldClock(WorldClock worldClock, WorldClock worldClock2, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_a(worldClock, worldClock2, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void moveWorldClockPosition(int i2, int i3, BleWriteResponse bleWriteResponse, IWorldClockOptListener iWorldClockOptListener) {
        if (c.checkWorldClock()) {
            this.vp_cf = iWorldClockOptListener;
            vp_dg.vp_k.vp_a().vp_a(i2, i3, vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void openMagneticTherapy(MagneticTherapy magneticTherapy, @IntRange(from = 1, to = 255) int i2, BleWriteResponse bleWriteResponse, IMagneticTherapyListener iMagneticTherapyListener) {
        if (magneticTherapy.getType() == MagneticTherapyType.CONVENTIONAL_MAGNETIC_THERAPY && (i2 < 1 || i2 > 255)) {
            new StringBuilder().append("常规磁疗的时间范围[1-255],您当前设置").append(i2).append("分钟不符合规范");
            iMagneticTherapyListener.onMagneticTherapyDurationError(1, 255);
        } else if (magneticTherapy.getType() != MagneticTherapyType.PULSE_MAGNETIC_THERAPY || (i2 >= 1 && i2 <= 15)) {
            this.vp_ck = iMagneticTherapyListener;
            this.vp_at.vp_a(vp_dy, this.vp_bj, bleWriteResponse, magneticTherapy, i2, iMagneticTherapyListener);
        } else {
            new StringBuilder().append("脉冲磁疗的时间范围[1-15],您当前设置").append(i2).append("分钟不符合规范");
            iMagneticTherapyListener.onMagneticTherapyDurationError(1, 15);
        }
    }

    public void closeMagneticTherapy(MagneticTherapyType magneticTherapyType, BleWriteResponse bleWriteResponse, IMagneticTherapyListener iMagneticTherapyListener) {
        this.vp_ck = iMagneticTherapyListener;
        this.vp_at.vp_a(vp_dy, this.vp_bj, bleWriteResponse, magneticTherapyType, iMagneticTherapyListener);
    }

    public void startDetectTcmDiagnosis(BleWriteResponse bleWriteResponse, ITcmDiagnosisDetectListener iTcmDiagnosisDetectListener) {
        e = true;
        vp_a(true);
        this.vp_s.vp_a(vp_dy, this.vp_bj, bleWriteResponse, 2, iTcmDiagnosisDetectListener);
    }

    public void stopDetectTcmDiagnosis(BleWriteResponse bleWriteResponse) {
        e = false;
        c.checkEcgDisease();
        this.vp_s.vp_bu(vp_dy, this.vp_bj, bleWriteResponse);
    }

    public void setGlobalMagneticTherapy(IMagneticTherapyListener iMagneticTherapyListener) {
        this.vp_ck = iMagneticTherapyListener;
    }

    public void startDetectPressure(BleWriteResponse bleWriteResponse, IPressureDetectListener iPressureDetectListener) {
        if (c.checkStress()) {
            this.vp_al.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iPressureDetectListener);
        }
    }

    public void stopDetectPressure(BleWriteResponse bleWriteResponse) {
        if (c.checkStress()) {
            this.vp_al.vp_bs(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void startDetectHrv(BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
        if (c.checkHrvAppDetect()) {
            this.vp_am.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iHrvDetectListener);
        }
    }

    public void stopDetectHrv(BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
        if (c.checkHrvAppDetect()) {
            this.vp_am.vp_b(vp_dy, this.vp_bj, bleWriteResponse, iHrvDetectListener);
        }
    }

    public void startDetectMet(BleWriteResponse bleWriteResponse, IMetDetectListener iMetDetectListener) {
        if (c.checkMetDetect()) {
            this.vp_an.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iMetDetectListener);
        }
    }

    public void stopDetectMet(BleWriteResponse bleWriteResponse) {
        if (c.checkMetDetect()) {
            this.vp_an.vp_bq(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void startDetectEmotion(BleWriteResponse bleWriteResponse, IEmotionDetectListener iEmotionDetectListener) {
        if (c.checkEmotionDetect()) {
            this.vp_ao.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iEmotionDetectListener);
        }
    }

    public void stopDetectEmotion(BleWriteResponse bleWriteResponse) {
        if (c.checkEmotionDetect()) {
            this.vp_ao.vp_bn(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void startDetectGsr(BleWriteResponse bleWriteResponse, IGsrDetectListener iGsrDetectListener) {
        if (c.checkGSR()) {
            this.vp_aq.vp_a(vp_dy, this.vp_bj, bleWriteResponse, iGsrDetectListener);
        }
    }

    public void stopDetectGsr(BleWriteResponse bleWriteResponse) {
        if (c.checkGSR()) {
            this.vp_aq.vp_by(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void readAutoMeasureSettingData(IBleWriteResponse iBleWriteResponse, IAutoMeasureSettingDataListener iAutoMeasureSettingDataListener) {
        if (c.checkB3()) {
            this.vp_cl = iAutoMeasureSettingDataListener;
            this.vp_au.vp_l(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setAutoMeasureSettingData(IBleWriteResponse iBleWriteResponse, AutoMeasureData autoMeasureData, IAutoMeasureSettingDataListener iAutoMeasureSettingDataListener) {
        if (c.checkB3()) {
            this.vp_cl = iAutoMeasureSettingDataListener;
            this.vp_au.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, autoMeasureData);
        }
    }

    public void openDeviceControlReport(IBleWriteResponse iBleWriteResponse) {
        vp_cr.vp_h.vp_a().vp_h(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void setSportControlInfo(IBleWriteResponse iBleWriteResponse, ESportControlType eSportControlType, ESportType eSportType, ISportControlOptListener iSportControlOptListener) {
        vp_cr.vp_a vp_aVar = vp_cr.vp_h;
        vp_aVar.vp_a().vp_a(iSportControlOptListener);
        vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eSportControlType, eSportType);
    }

    public void setSportControlReportListener(IDeviceSportReportListener iDeviceSportReportListener) {
        vp_cr.vp_h.vp_a().vp_a(iDeviceSportReportListener);
    }

    public void readSportControlInfo(IBleWriteResponse iBleWriteResponse, ESportType eSportType, ISportControlOptListener iSportControlOptListener) {
        vp_cr.vp_a vp_aVar = vp_cr.vp_h;
        vp_aVar.vp_a().vp_a(iSportControlOptListener);
        vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eSportType);
    }

    public void pushTextMsg(String str, BleWriteResponse bleWriteResponse, ITextMsgPushListener iTextMsgPushListener) {
        if (isCurrentDeviceConnected()) {
            if (!c.checkTextImagePush()) {
                iTextMsgPushListener.onFunctionNotSupport();
                return;
            }
            vp_da.vp_a vp_aVar = vp_da.vp_g;
            boolean zVp_d = vp_aVar.vp_a().vp_d();
            new StringBuilder().append("-pushTextMsg- : 是否正在图文推送 = ").append(zVp_d);
            if (!zVp_d) {
                vp_a(str, bleWriteResponse, iTextMsgPushListener);
            } else {
                UiUpdateUtil.getInstance().stopUITransfer();
                vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, new vp_ac(iTextMsgPushListener), new vp_ad(iTextMsgPushListener, str, bleWriteResponse));
            }
        }
    }

    public void pushImageMsg(String str, IImageMsgPushListener iImageMsgPushListener) {
        if (isCurrentDeviceConnected() && iImageMsgPushListener != null) {
            if (!c.checkTextImagePush()) {
                iImageMsgPushListener.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.FUNCTION_NOT_SUPPORT);
                return;
            }
            File file = new File(str);
            if (!file.exists() || !file.isFile()) {
                iImageMsgPushListener.onImageMsgPushFailed(IImageMsgPushListener.ErrorCode.IMAGE_PATH_ERROR);
                new StringBuilder().append("-pushImageMsg- : 图片:").append(str).append(", 文件无效，请检查确保该文件存在且有效");
                return;
            }
            vp_da.vp_a vp_aVar = vp_da.vp_g;
            boolean zVp_d = vp_aVar.vp_a().vp_d();
            new StringBuilder().append("-pushImageMsg- : 是否正在图文推送 = ").append(zVp_d);
            if (!zVp_d) {
                vp_a(str, iImageMsgPushListener);
            } else {
                UiUpdateUtil.getInstance().stopUITransfer();
                vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, new vp_af(iImageMsgPushListener), new vp_ah(iImageMsgPushListener, str));
            }
        }
    }

    public void readPPGSwitchStatus(BleWriteResponse bleWriteResponse) {
        vp_bw.vp_m.vp_a().vp_ad(vp_dy, this.vp_bj, bleWriteResponse);
    }

    public void setPPGSwitchStatus(PPGSwitchStatus pPGSwitchStatus, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("**设置PPG测量开关状态:").append(pPGSwitchStatus);
        vp_bw.vp_m.vp_a().vp_a(pPGSwitchStatus, vp_dy, this.vp_bj, bleWriteResponse);
    }

    public void addPPGTestSwitchStatusListener(IPPGSwitchOperaterListener iPPGSwitchOperaterListener) {
        if (iPPGSwitchOperaterListener == null) {
            return;
        }
        vp_bw.vp_m.vp_a().vp_a(iPPGSwitchOperaterListener);
    }

    public void readPPGRawData(TimeData timeData, PPGTestMode pPGTestMode, BleWriteResponse bleWriteResponse, IPPGRawDataReadListener iPPGRawDataReadListener) {
        if (timeData == null || iPPGRawDataReadListener == null) {
            return;
        }
        new StringBuilder().append("**读取PPG测量原始数据:").append(timeData.toFullDateTimeString()).append(", 模式 = ").append(pPGTestMode);
        vp_bw.vp_m.vp_a().vp_a(timeData, pPGTestMode, vp_dy, this.vp_bj, bleWriteResponse, iPPGRawDataReadListener);
    }

    public void startPPGRealTimeTransmission(BleWriteResponse bleWriteResponse, IPPGRealTimeTransmissionListener iPPGRealTimeTransmissionListener) {
        vp_dy.notify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_aj(iPPGRealTimeTransmissionListener));
        vp_bw.vp_m.vp_a().vp_a(true, vp_dy, this.vp_bj, bleWriteResponse);
    }

    public void stopPPGRealTimeTransmission(BleWriteResponse bleWriteResponse) {
        vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_ak());
        vp_bw.vp_m.vp_a().vp_a(false, vp_dy, this.vp_bj, bleWriteResponse);
    }

    public void addDevicePPGRealTimeTransferListener(IPPGRealTimeTransmissionListener iPPGRealTimeTransmissionListener) {
        this.vp_cj = iPPGRealTimeTransmissionListener;
        vp_bw.vp_m.vp_a().vp_a(new vp_al());
    }

    public void activeJH58MeasurementState(EJH58MeasurementState eJH58MeasurementState, BleWriteResponse bleWriteResponse, IActiveMeasurementDataListener iActiveMeasurementDataListener) {
        new StringBuilder().append("**JH58主动测量状态控制:").append(eJH58MeasurementState.getDes());
        vp_bw.vp_m.vp_a().vp_a(eJH58MeasurementState, vp_dy, this.vp_bj, bleWriteResponse, iActiveMeasurementDataListener);
    }

    public void startMiniCheckup(BleWriteResponse bleWriteResponse, IMiniCheckupOptListener iMiniCheckupOptListener) {
        if (isCurrentDeviceConnected() && iMiniCheckupOptListener != null) {
            if (!c.checkMiniCheckup()) {
                iMiniCheckupOptListener.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.FUNCTION_NOT_SUPPORT);
                return;
            }
            vp_bm.vp_a vp_aVar = vp_bm.vp_e;
            vp_aVar.vp_a().vp_a(iMiniCheckupOptListener);
            vp_aVar.vp_a().vp_bg(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void stopMiniCheckup(BleWriteResponse bleWriteResponse, IMiniCheckupOptListener iMiniCheckupOptListener) {
        if (isCurrentDeviceConnected() && iMiniCheckupOptListener != null) {
            if (!c.checkMiniCheckup()) {
                iMiniCheckupOptListener.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.FUNCTION_NOT_SUPPORT);
                return;
            }
            vp_bm.vp_a vp_aVar = vp_bm.vp_e;
            vp_aVar.vp_a().vp_a(iMiniCheckupOptListener);
            vp_aVar.vp_a().vp_bz(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void setZT163DeviceAlwaysOffScreen(boolean z, BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
        if (isCurrentDeviceConnected() && iZT163DeviceAlwaysOffScreenOptListener != null) {
            vp_di.vp_d.vp_a().vp_a(z, vp_dy, this.vp_bj, bleWriteResponse, iZT163DeviceAlwaysOffScreenOptListener);
        }
    }

    public void readZT163DeviceAlwaysOffScreen(BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
        if (isCurrentDeviceConnected() && iZT163DeviceAlwaysOffScreenOptListener != null) {
            vp_di.vp_d.vp_a().vp_a(vp_dy, this.vp_bj, bleWriteResponse, iZT163DeviceAlwaysOffScreenOptListener);
        }
    }

    public void readFunSwitchState(BleWriteResponse bleWriteResponse, IFunSwitchListener iFunSwitchListener) {
        if (c.checkHealthAssessment()) {
            this.vp_bz = iFunSwitchListener;
            this.vp_af.vp_x(vp_dy, this.vp_bj, bleWriteResponse);
        }
    }

    public void setFunSwitchState(BleWriteResponse bleWriteResponse, IFunSwitchListener iFunSwitchListener, int i2, EFunctionStatus eFunctionStatus) {
        if (c.checkHealthAssessment()) {
            this.vp_bz = iFunSwitchListener;
            this.vp_af.vp_a(vp_dy, this.vp_bj, bleWriteResponse, i2, eFunctionStatus);
        }
    }

    public void setAiListener(OnAIConfigOptListener onAIConfigOptListener, OnAIQAOptListener onAIQAOptListener, OnAIDialOptListener onAIDialOptListener) {
        AIFunctionOpt.vp_a vp_aVar = AIFunctionOpt.vp_h;
        vp_aVar.vp_a().vp_a(onAIConfigOptListener);
        vp_aVar.vp_a().vp_a(onAIQAOptListener);
        vp_aVar.vp_a().vp_a(onAIDialOptListener);
    }

    public void sendAIDialDisableCMD(IBleWriteResponse iBleWriteResponse) {
        AIFunctionOpt.vp_h.vp_a().vp_av(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void sendAIQAStartRecordingResult(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
        AIFunctionOpt.vp_h.vp_a().vp_e(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode);
    }

    public void sendAIQARecordingContent2Device(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str) {
        AIFunctionOpt.vp_h.vp_a().vp_b(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode, str);
    }

    public void sendAIQAResultCmd(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str) {
        AIFunctionOpt.vp_h.vp_a().vp_d(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode, str);
    }

    public void sendAIDialRecordingResult(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str) {
        AIFunctionOpt.vp_h.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode, str);
    }

    public void sendAIQARegenerateResultCmd(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, String str) {
        AIFunctionOpt.vp_h.vp_a().vp_c(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode, str);
    }

    public void readAiConfig(IBleWriteResponse iBleWriteResponse) {
        AIFunctionOpt.vp_h.vp_a().vp_i(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void sendAIDialStartGenerateACK(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
        AIFunctionOpt.vp_h.vp_a().vp_c(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode);
    }

    public void sendAIDialGenerateResult(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode, int i2, int i3) {
        AIFunctionOpt.vp_h.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode, i2, i3);
    }

    public void sendAIDialTerminateACK(IBleWriteResponse iBleWriteResponse) {
        AIFunctionOpt.vp_h.vp_a().vp_aw(vp_dy, this.vp_bj, iBleWriteResponse);
    }

    public void sendAIDialStartRecordingResult(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
        AIFunctionOpt.vp_h.vp_a().vp_d(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode);
    }

    public void aiOpus2Pcm(String str, String str2, OnOpusDecode2PcmListener onOpusDecode2PcmListener) {
        AiOpus2PcmManager.getInstance().init(str, str2, onOpusDecode2PcmListener);
        AiOpus2PcmManager.getInstance().decodeOpus();
    }

    public void sendAiDialPreview(String str, EWatchUIType eWatchUIType, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
        AiDialPreviewManager.getInstance().setWatchUIType(eWatchUIType);
        AiDialPreviewManager.getInstance().sendAiDialPreview(str, onAiDialPreviewSendListener);
    }

    public void setiFunSwitchListener(IFunSwitchListener iFunSwitchListener) {
        this.vp_bz = iFunSwitchListener;
    }

    public void startDetectCuffBP(IBleWriteResponse iBleWriteResponse, boolean z, ICuffBPDetectDataListener iCuffBPDetectDataListener) {
        if (c.checkSportModel()) {
            i = true;
            vp_a(z);
            this.vp_av.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, z, iCuffBPDetectDataListener);
        }
    }

    public void stopDetectCuffBP(IBleWriteResponse iBleWriteResponse) {
        if (c.checkSportModel()) {
            i = false;
            this.vp_av.vp_f(vp_dy, this.vp_bj, iBleWriteResponse, true);
        }
    }

    public void read4gServerInfo(IBleWriteResponse iBleWriteResponse, INetServer4gConfigListener iNetServer4gConfigListener) {
        if (c.check4g()) {
            this.vp_aw.vp_a(iNetServer4gConfigListener);
            this.vp_aw.vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void clear4gAccountInfo(IBleWriteResponse iBleWriteResponse, INetServer4gConfigListener iNetServer4gConfigListener) {
        if (c.check4g()) {
            this.vp_aw.vp_a(iNetServer4gConfigListener);
            this.vp_aw.vp_a(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void set4gServerInfo(NetworkSever4gConfigInfo networkSever4gConfigInfo, IBleWriteResponse iBleWriteResponse, INetServer4gConfigListener iNetServer4gConfigListener) {
        if (c.check4g()) {
            this.vp_aw.vp_a(iNetServer4gConfigListener);
            this.vp_aw.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, networkSever4gConfigInfo);
        }
    }

    public void startSafetyProtection(IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkSafetyProtection()) {
            vp_ch.vp_i.vp_a().vp_bh(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void stopSafetyProtection(IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkSafetyProtection()) {
            vp_ch.vp_i.vp_a().vp_cb(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void requestGnssLocationData(EGnssOptType eGnssOptType, IBleWriteResponse iBleWriteResponse) {
        new StringBuilder().append("**请求-").append(eGnssOptType.getDes()).append("-位置信息");
        if (isCurrentDeviceConnected()) {
            if (eGnssOptType != EGnssOptType.SAFETY_PROTECTION || c.checkSafetyProtection()) {
                vp_ch.vp_i.vp_a().vp_a(eGnssOptType, vp_dy, this.vp_bj, iBleWriteResponse);
            }
        }
    }

    public void pushGnssLocationData(EGnssOptType eGnssOptType, GnssLocationData gnssLocationData, IBleWriteResponse iBleWriteResponse) {
        new StringBuilder().append("**推送-").append(eGnssOptType.getDes()).append("-位置信息");
        if (isCurrentDeviceConnected()) {
            if (eGnssOptType != EGnssOptType.SAFETY_PROTECTION || c.checkSafetyProtection()) {
                int i2 = vp_av.vp_b[eGnssOptType.ordinal()];
                if (i2 == 1) {
                    vp_ch.vp_i.vp_a().vp_a(gnssLocationData, vp_dy, this.vp_bj, iBleWriteResponse);
                } else if (i2 == 2) {
                    vp_ch.vp_i.vp_a().vp_c(gnssLocationData, vp_dy, this.vp_bj, iBleWriteResponse);
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    vp_ch.vp_i.vp_a().vp_b(gnssLocationData, vp_dy, this.vp_bj, iBleWriteResponse);
                }
            }
        }
    }

    public void addGnssLocationOptListener(IGNSSOptListener iGNSSOptListener) {
        if (iGNSSOptListener == null) {
            return;
        }
        vp_ch.vp_i.vp_a().vp_a(iGNSSOptListener);
    }

    public void addSafetyProtectionOptListener(ISafetyProtectionOptListener iSafetyProtectionOptListener) {
        if (iSafetyProtectionOptListener == null) {
            return;
        }
        vp_ch.vp_i.vp_a().vp_a(iSafetyProtectionOptListener);
    }

    public void setQH15SafetyProtection(boolean z, IBleWriteResponse iBleWriteResponse) {
        new StringBuilder().append("**QH15设置亲情守护状态：").append(z ? "开启" : "关闭");
        if (isCurrentDeviceConnected() && c.checkSafetyProtection()) {
            if (z) {
                vp_ch.vp_i.vp_a().vp_bh(vp_dy, this.vp_bj, iBleWriteResponse);
            } else {
                vp_ch.vp_i.vp_a().vp_cb(vp_dy, this.vp_bj, iBleWriteResponse);
            }
        }
    }

    public void addQH15SafetyProtectionListener(IQH15SafetyProtectionListener iQH15SafetyProtectionListener) {
        if (iQH15SafetyProtectionListener == null) {
            return;
        }
        vp_ch.vp_i.vp_a().vp_a(iQH15SafetyProtectionListener);
    }

    public void addQH15GnssLocationListener(IQH15GnssLocationListener iQH15GnssLocationListener) {
        if (iQH15GnssLocationListener == null) {
            return;
        }
        vp_ch.vp_i.vp_a().vp_a(iQH15GnssLocationListener);
    }

    public void pushQH15GnssLocationData(QH15SecurityProtectionModel qH15SecurityProtectionModel, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkSafetyProtection() && qH15SecurityProtectionModel != null) {
            vp_ch.vp_i.vp_a().vp_a(qH15SecurityProtectionModel, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public boolean isNoScreenDevice() {
        return b.getWatchuiServer() == 0 && b.getWatchuiCoustom() == 0;
    }

    public void clearCache(String str) {
        vp_dy.clearRequest(str, 0);
        vp_dy.refreshCache(str);
    }

    public void vpQX17StartDataAcquisition(IBleWriteResponse iBleWriteResponse, IQX17DataAcquisitionListener iQX17DataAcquisitionListener) {
        vp_d().vp_a(iQX17DataAcquisitionListener);
        vp_c();
        vp_d().vp_cd(vp_dy, this.vp_bj, new vp_an(iBleWriteResponse));
    }

    public void vpQX17StopDataAcquisition(IBleWriteResponse iBleWriteResponse) {
        vp_d().vp_ce(vp_dy, this.vp_bj, new vp_ao(iBleWriteResponse));
    }

    public void setVpQX17DataAcquisitionStateListener(IQX17DataAcquisitionStateListener iQX17DataAcquisitionStateListener) {
        this.vp_a.vp_a(iQX17DataAcquisitionStateListener);
    }

    public void vpQX17ContinueDataAcquisition(IBleWriteResponse iBleWriteResponse, IQX17DataAcquisitionListener iQX17DataAcquisitionListener) {
        vp_d().vp_a(iQX17DataAcquisitionListener);
        vp_c();
        vp_d().vp_f(vp_dy, this.vp_bj, new vp_ap(iBleWriteResponse), vp_d().vp_a());
    }

    public void vpQX17SetVibrationMode(IBleWriteResponse iBleWriteResponse, EQX17VibrationMode eQX17VibrationMode, int i2) {
        new StringBuilder().append("**调用[QX17]设置振动模式, mode=").append(eQX17VibrationMode).append(", duration=").append(i2);
        if (eQX17VibrationMode == null) {
            return;
        }
        if (i2 < 0 || i2 > 255) {
            new StringBuilder().append("vpQX17SetVibrationMode: duration超出范围(0~255), duration=").append(i2);
        } else {
            vp_d().vp_a(vp_dy, this.vp_bj, new vp_aq(iBleWriteResponse), eQX17VibrationMode, i2);
        }
    }

    public void readQH15StepData(IBleWriteResponse iBleWriteResponse, IQH15HealthDataOptListener iQH15HealthDataOptListener) {
        if (isCurrentDeviceConnected() && iQH15HealthDataOptListener != null) {
            this.vp_cs = iQH15HealthDataOptListener;
            com.veepoo.protocol.operate.vp_y.vp_a vp_aVar = com.veepoo.protocol.operate.vp_y.vp_i;
            vp_aVar.vp_a().vp_a(iQH15HealthDataOptListener);
            vp_aVar.vp_a().vp_ae(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setQH15HealthData(QH15HealthData qH15HealthData, IBleWriteResponse iBleWriteResponse, IQH15HealthDataOptListener iQH15HealthDataOptListener) {
        if (!isCurrentDeviceConnected() || iQH15HealthDataOptListener == null || qH15HealthData == null) {
            return;
        }
        this.vp_cs = iQH15HealthDataOptListener;
        com.veepoo.protocol.operate.vp_y.vp_a vp_aVar = com.veepoo.protocol.operate.vp_y.vp_i;
        vp_aVar.vp_a().vp_a(iQH15HealthDataOptListener);
        vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, qH15HealthData);
    }

    public void setQH15ComplianceEvent(EQH15ComplianceType eQH15ComplianceType, IBleWriteResponse iBleWriteResponse, IQH15HealthDataOptListener iQH15HealthDataOptListener) {
        if (!isCurrentDeviceConnected() || iQH15HealthDataOptListener == null || eQH15ComplianceType == null) {
            return;
        }
        this.vp_cs = iQH15HealthDataOptListener;
        com.veepoo.protocol.operate.vp_y.vp_a vp_aVar = com.veepoo.protocol.operate.vp_y.vp_i;
        vp_aVar.vp_a().vp_a(iQH15HealthDataOptListener);
        vp_aVar.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eQH15ComplianceType);
    }

    public void getQH15HealthDataTimestamp(IBleWriteResponse iBleWriteResponse, IQH15HealthDataOptListener iQH15HealthDataOptListener) {
        if (isCurrentDeviceConnected() && iQH15HealthDataOptListener != null) {
            this.vp_cs = iQH15HealthDataOptListener;
            com.veepoo.protocol.operate.vp_y.vp_a vp_aVar = com.veepoo.protocol.operate.vp_y.vp_i;
            vp_aVar.vp_a().vp_a(iQH15HealthDataOptListener);
            vp_aVar.vp_a().vp_f(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setReminderEventReportListener(IRemindEventListener iRemindEventListener) {
        vp_cf.vp_g.vp_a().vp_a(iRemindEventListener);
    }

    public void removeReminderEventReportListener() {
        vp_cf.vp_g.vp_a().vp_a((IRemindEventListener) null);
    }

    public void readHistoricalDataReminderEvents(IBleWriteResponse iBleWriteResponse, ERemindEvent eRemindEvent, long j2) {
        if (isCurrentDeviceConnected() && c.checkRemindEvent() && eRemindEvent != null) {
            vp_cf.vp_g.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eRemindEvent, j2);
        }
    }

    public void sendCustomCMD(byte[] bArr) {
        if (isCurrentDeviceConnected()) {
            vp_dy.write(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_c, com.veepoo.protocol.profile.vp_a.vp_e, bArr, new vp_as());
        }
    }

    public void setCustomDataOptListener(ICustomDataOptListener iCustomDataOptListener) {
        this.vp_cp = iCustomDataOptListener;
    }

    public void removeCustomDataOptListener() {
        this.vp_cp = null;
    }

    public int getCustomPassthroughMaxLength() {
        VpSpGetUtil vpSpGetUtil = b;
        if (vpSpGetUtil == null || !vpSpGetUtil.isZKDevice()) {
            return getCurrentMtu();
        }
        return 20;
    }

    public void sendCustomData(IBleWriteResponse iBleWriteResponse, List<byte[]> list) {
        this.vp_ac.vp_a(vp_dy, this.vp_bj, list, getCustomPassthroughMaxLength(), iBleWriteResponse, this.vp_cp);
    }

    public void setCustomADCDataNotify(boolean z, BleResponse bleResponse) {
        if (!z) {
            vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_au(bleResponse));
        } else {
            if (this.vp_cp == null) {
                return;
            }
            vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_at(bleResponse));
        }
    }

    public void removeQH15HealthDataOptListener() {
        this.vp_cs = null;
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a((IQH15HealthDataOptListener) null);
    }

    public void removeTCMDataListener() {
        this.vp_cq = null;
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a((ITCMDataListener) null);
    }

    public void removeCustomProtocolStateListener() {
        this.vp_co = null;
        com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a((ICustomProtocolStateListener) null);
    }

    public void addHealthLightListener(IHealthLightListener iHealthLightListener) {
        com.veepoo.protocol.operate.vp_bb.vp_d.vp_a().vp_a(iHealthLightListener);
    }

    public void setHealthLightStatus(EHealthLightStatus eHealthLightStatus, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkHealthLight()) {
            com.veepoo.protocol.operate.vp_bb.vp_d.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eHealthLightStatus);
        }
    }

    public void readHealthLightStatus(IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkHealthLight()) {
            com.veepoo.protocol.operate.vp_bb.vp_d.vp_a().vp_y(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readMotorLevel(IBleWriteResponse iBleWriteResponse, IMotorOperateListener iMotorOperateListener) {
        if (isCurrentDeviceConnected()) {
            this.vp_cg = iMotorOperateListener;
            vp_bn.vp_d.vp_a().vp_cd(vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setMotorLevel(EMotorLevel eMotorLevel, IBleWriteResponse iBleWriteResponse, IMotorOperateListener iMotorOperateListener) {
        if (isCurrentDeviceConnected()) {
            new StringBuilder().append("**调用设置振动强度 level=").append(eMotorLevel);
            this.vp_cg = iMotorOperateListener;
            vp_bn.vp_d.vp_a().vp_a(eMotorLevel, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readZT79GameClocks(Boolean bool, List<GameClockBean> list, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkGameClockFunction()) {
            new StringBuilder().append("**调用读取游戏控制信息 mainSwitch = ").append(bool);
            com.veepoo.protocol.operate.vp_av.vp_a vp_aVar = com.veepoo.protocol.operate.vp_av.vp_f;
            vp_aVar.vp_b().vp_a(iGameClockOptListener);
            vp_aVar.vp_b().vp_a(vp_a(list), bool, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setZT79GameClockMainSwitch(boolean z, List<GameClockBean> list, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkGameClockFunction()) {
            new StringBuilder().append("**调用设置游戏控制总开关 state = ").append(z);
            com.veepoo.protocol.operate.vp_av.vp_a vp_aVar = com.veepoo.protocol.operate.vp_av.vp_f;
            vp_aVar.vp_b().vp_a(iGameClockOptListener);
            vp_aVar.vp_b().vp_a(vp_a(list), z, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void setZT79GameClocks(boolean z, GameClockBean gameClockBean, List<GameClockBean> list, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (gameClockBean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(gameClockBean);
        setZT79GameClocks(z, arrayList, list, iGameClockOptListener, iBleWriteResponse);
    }

    public void deleteZT79GameClock(boolean z, GameClockBean gameClockBean, List<GameClockBean> list, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (gameClockBean == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(gameClockBean.getClockId()));
        deleteZT79GameClockWithIDs(z, arrayList, list, iGameClockOptListener, iBleWriteResponse);
    }

    public void deleteZT79GameClockWithIDs(boolean z, List<Integer> list, List<GameClockBean> list2, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkGameClockFunction() && list != null && !list.isEmpty()) {
            new StringBuilder().append("**调用批量删除游戏时段 ").append(list);
            com.veepoo.protocol.operate.vp_av.vp_a vp_aVar = com.veepoo.protocol.operate.vp_av.vp_f;
            vp_aVar.vp_b().vp_a(iGameClockOptListener);
            vp_aVar.vp_b().vp_a(z, list, vp_a(list2), vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public static boolean isFindJLService() {
        return isFindJLService;
    }

    public static void setShowFunctionNotSupportToast(boolean z) {
        j = z;
    }

    public static boolean isShowFunctionNotSupportToast() {
        return j;
    }

    private void vp_g() {
        if (c.checkBaseType()) {
            return;
        }
        vp_b();
    }

    private void vp_b() {
        disconnectWatch(new vp_az());
    }

    private byte[] vp_e() {
        byte[] bArr = new byte[20];
        bArr[0] = -73;
        bArr[1] = 4;
        bArr[2] = 1;
        return bArr;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    private void vp_a(String str, byte[] bArr) throws NoWhenBranchMatchedException {
        byte b2;
        byte b3;
        byte b4;
        IScreenStyleListener iScreenStyleListener;
        IFunSwitchListener iFunSwitchListener;
        byte b5;
        BluetoothLog.i("******callBackData,operate=" + str + ",value=" + VpBleByteUtil.byte2HexForShow(bArr));
        if (str.equals("text_transmission_oprate") && bArr[1] == 1) {
            com.veepoo.protocol.operate.vp_au vp_auVar = this.vp_z;
            if (vp_auVar != null) {
                vp_auVar.vp_a(bArr, this.vp_bw);
                return;
            }
            return;
        }
        if (str.equals("send_content_to_watch_oprate") && bArr[1] == -2) {
            IG15MessageListener iG15MessageListener = this.vp_bv;
            if (iG15MessageListener != null) {
                this.vp_y.vp_a(bArr, iG15MessageListener);
                return;
            }
            return;
        }
        if (str.equals("gps_lat_lon_oprate") && bArr[1] == 6) {
            if (this.vp_ck != null) {
                this.vp_at.vp_a(bArr, this.vp_ck);
                return;
            }
            return;
        }
        if (str.equals("gps_lat_lon_oprate") && bArr[1] == 7) {
            vp_di.vp_d.vp_a().handler(bArr);
            return;
        }
        if (str.equals("gps_lat_lon_oprate") && ((b5 = bArr[1]) == 8 || b5 == 9)) {
            vp_ch.vp_i.vp_a().handler(bArr);
            return;
        }
        if (str.equals("read_battery_sleep")) {
            ISleepDataListener iSleepDataListener = this.vp_ay;
            if (iSleepDataListener == null) {
                return;
            }
            this.vp_f.vp_a(bArr, iSleepDataListener);
            return;
        }
        if (str.equals("origal_df_oprate")) {
            IOriginData3Listener iOriginData3Listener = this.vp_bb;
            if (iOriginData3Listener == null) {
                return;
            }
            this.vp_k.vp_a(bArr, iOriginData3Listener);
            return;
        }
        if (str.equals("manual_detect_de")) {
            IDeviceManualDetectDataListener iDeviceManualDetectDataListener = this.vp_bc;
            if (iDeviceManualDetectDataListener == null) {
                return;
            }
            this.vp_l.vp_a(bArr, iDeviceManualDetectDataListener);
            return;
        }
        if (str.equals("tempture_origal_oprate")) {
            ITemptureDataListener iTemptureDataListener = this.vp_az;
            if (iTemptureDataListener == null) {
                return;
            }
            this.vp_m.vp_a(bArr, iTemptureDataListener);
            return;
        }
        if (str.equals("read_battery_original")) {
            IOriginDataListener iOriginDataListener = this.vp_ba;
            if (iOriginDataListener == null) {
                return;
            }
            this.vp_j.vp_a(bArr, (IOriginProgressListener) iOriginDataListener);
            return;
        }
        if (str.equals("multi_alarm_oprate")) {
            if (!c.isSupportTextAlarm()) {
                IAlarm2DataListListener iAlarm2DataListListener = this.vp_bd;
                if (iAlarm2DataListListener == null) {
                    return;
                }
                this.vp_n.vp_a(this.vp_bj, mContext, iAlarm2DataListListener, bArr);
                return;
            }
            if (this.vp_be == null) {
                return;
            }
            if (!this.vp_o.vp_b()) {
                this.vp_o.vp_cd(vp_dy, this.vp_bj, this.vp_du);
            }
            this.vp_o.vp_a(this.vp_bj, mContext, this.vp_be, bArr);
            return;
        }
        if (str.equals("sport_model_origin_oprate")) {
            ISportModelOriginListener iSportModelOriginListener = this.vp_bf;
            if (iSportModelOriginListener != null) {
                this.vp_p.vp_a(bArr, iSportModelOriginListener);
                return;
            }
            return;
        }
        if (str.equals("read_battery_spo2h_original")) {
            ISpo2hOriginDataListener iSpo2hOriginDataListener = this.vp_bg;
            if (iSpo2hOriginDataListener != null) {
                this.vp_q.vp_a(bArr, iSpo2hOriginDataListener);
                return;
            }
            return;
        }
        if (str.equals("ecg_data_app_detect_oprate")) {
            byte b6 = bArr[1];
            if (b6 == 11) {
                if (this.vp_cz == null) {
                    return;
                }
                vp_dz.vp_d().vp_a(this.vp_cz);
                vp_dz.vp_d().handler(bArr);
                return;
            }
            if (b6 == 1) {
                byte b7 = bArr[2];
                if (b7 == 1) {
                    this.vp_r.vp_f(bArr);
                    return;
                } else {
                    if (b7 == 3 || b7 == 5) {
                        this.vp_u.vp_f(bArr);
                        return;
                    }
                    return;
                }
            }
            if (b6 == 2) {
                this.vp_ad.handler(bArr);
                return;
            }
            if (b6 == 4) {
                this.vp_ak.handler(bArr);
                return;
            }
            if (b6 == 5 || b6 == 6) {
                this.vp_ar.handler(bArr);
                return;
            }
            if (b6 == 7 && c.checkEcgMultiLead()) {
                this.vp_t.handler(bArr);
                return;
            }
            byte b8 = bArr[1];
            if (b8 == 9) {
                vp_bm.vp_e.vp_a().handler(bArr);
                return;
            }
            if (b8 == 8 && c.checkEcgMultiLead()) {
                this.vp_w.handler(bArr);
                return;
            }
            byte b9 = bArr[1];
            if (b9 == 8) {
                this.vp_s.vp_e(bArr);
                return;
            } else {
                if (b9 == 10) {
                    this.vp_aq.handler(bArr);
                    return;
                }
                return;
            }
        }
        if (str.equals("ecg_data_use_id_oprate") || str.equals("ecg_data_get_id_oprate")) {
            if (bArr[1] == 5) {
                this.vp_w.handler(bArr);
                return;
            } else {
                this.vp_v.handler(bArr);
                return;
            }
        }
        if (str.equals("custom_passthrough_oprate")) {
            ICustomDataOptListener iCustomDataOptListener = this.vp_cp;
            if (iCustomDataOptListener == null) {
                return;
            }
            iCustomDataOptListener.onPassthroughData(bArr);
            return;
        }
        if (str.equals("custom_protocol_data_oprate")) {
            new StringBuilder().append("CUSTOM_PROTOCOL_DATA_OPRATE has? = ").append(c.isMusicWithInfo());
            if (!c.isMusicWithInfo()) {
                this.vp_ab.handler(bArr);
                return;
            }
            IDeviceControlPhoneModelState iDeviceControlPhoneModelState = this.vp_bn;
            if (iDeviceControlPhoneModelState != null) {
                this.vp_ab.vp_a(bArr, iDeviceControlPhoneModelState);
            }
            IMusicControlListener iMusicControlListener = this.vp_bp;
            if (iMusicControlListener != null) {
                this.vp_ab.vp_a(bArr, iMusicControlListener);
                return;
            }
            return;
        }
        if (str.equals("hrv_origan_oprate")) {
            IHRVOriginDataListener iHRVOriginDataListener = this.vp_bh;
            if (iHRVOriginDataListener != null) {
                this.vp_x.vp_a(bArr, iHRVOriginDataListener);
                return;
            }
            return;
        }
        if (str.equals("rr_interval_oprate")) {
            IRRIntervalProgressListener iRRIntervalProgressListener = this.vp_bx;
            if (iRRIntervalProgressListener == null) {
                return;
            }
            this.vp_aa.vp_a(bArr, iRRIntervalProgressListener);
            return;
        }
        if (str.equals("fun_model_switch_oprate")) {
            byte b10 = bArr[1];
            if (b10 == 5) {
                IECGSwitchListener iECGSwitchListener = this.vp_by;
                if (iECGSwitchListener == null) {
                    return;
                }
                this.vp_ae.vp_a(bArr, iECGSwitchListener);
                return;
            }
            if (b10 != -95 || (iFunSwitchListener = this.vp_bz) == null) {
                return;
            }
            this.vp_af.vp_a(bArr, iFunSwitchListener);
            return;
        }
        if (str.equals("device_debug_oprate_F2")) {
            IF2DebugListener iF2DebugListener = this.vp_ca;
            if (iF2DebugListener == null) {
                return;
            }
            this.vp_ag.vp_a(bArr, iF2DebugListener);
            return;
        }
        if (str.equals("blood_glucose_detect_operate")) {
            byte b11 = bArr[1];
            if (b11 == 6) {
                this.vp_al.handler(bArr);
                return;
            }
            if (b11 == 7) {
                this.vp_am.handler(bArr);
                return;
            }
            if (b11 == 8) {
                this.vp_an.handler(bArr);
                return;
            }
            if (b11 == 9) {
                this.vp_ao.handler(bArr);
                return;
            }
            if (b11 == 10) {
                this.vp_ap.handler(bArr);
                return;
            }
            IBloodGlucoseChangeListener iBloodGlucoseChangeListener = this.vp_de;
            if (iBloodGlucoseChangeListener == null) {
                return;
            }
            this.vp_ah.vp_a(bArr, iBloodGlucoseChangeListener);
            return;
        }
        if (str.equals("bt_operate")) {
            VpSpSaveUtil.getVpSpVariInstance(mContext).saveBTFunction(true);
            if (this.vp_cc != null) {
                IDeviceBTConnectionListener iDeviceBTConnectionListener = this.vp_cb;
            }
            this.vp_ai.vp_a(vp_dy);
            this.vp_ai.handler(bArr);
            return;
        }
        if (str.equals("health_remind_operate")) {
            IHealthRemindListener iHealthRemindListener = this.vp_cd;
            if (iHealthRemindListener == null) {
                return;
            }
            this.vp_aj.vp_a(bArr, iHealthRemindListener);
            return;
        }
        if (str.equals("contact_operate")) {
            if (bArr[1] == 5) {
                if (this.vp_ch == null) {
                    return;
                } else {
                    com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(this.vp_ch);
                }
            } else if (this.vp_ce == null) {
                return;
            } else {
                com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(this.vp_ce);
            }
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().handler(bArr);
            return;
        }
        if (str.equals("world_clock_operate")) {
            if (this.vp_cf == null) {
                return;
            }
            vp_dg.vp_a vp_aVar = vp_dg.vp_k;
            vp_aVar.vp_a().vp_a(this.vp_cf);
            vp_aVar.vp_a().handler(bArr);
            return;
        }
        if (str.equals("motor_operate")) {
            if (this.vp_cg == null) {
                return;
            }
            vp_bn.vp_a vp_aVar2 = vp_bn.vp_d;
            vp_aVar2.vp_a().vp_a(this.vp_cg);
            vp_aVar2.vp_a().handler(bArr);
            return;
        }
        if (str.equals("health_alarm_interval_oprate")) {
            this.vp_as.handler(bArr);
            return;
        }
        if (str.equals("all_setting_oprate") && b.isSupportAutoMeasure()) {
            this.vp_au.vp_a(bArr, this.vp_cl);
            return;
        }
        if (str.equals("sport_model_control_oprate")) {
            vp_cr.vp_h.vp_a().handler(bArr);
            return;
        }
        if (str.equals("screen_style_oprate") && (iScreenStyleListener = this.vp_cn) != null) {
            this.vp_g.vp_a(bArr, iScreenStyleListener);
            return;
        }
        if (str.equals("ai_operate")) {
            AIFunctionOpt.vp_h.vp_a().handler(bArr);
            return;
        }
        if (str.equals("bp_oprate") && b.isSupportBumpBp()) {
            this.vp_av.handler(bArr);
            return;
        }
        if (str.equals("text_transmission_oprate") && bArr[1] == 4) {
            this.vp_aw.handler(bArr);
            return;
        }
        if (str.equals("text_image_push_operate")) {
            vp_da.vp_g.vp_a().handler(bArr);
            return;
        }
        if (str.equals("ppg_test_status_operate")) {
            vp_bw.vp_m.vp_a().handler(bArr);
            return;
        }
        if (str.equals("rename_operate") && bArr[1] == 3) {
            if (this.vp_cu == null) {
                return;
            }
            vp_dz.vp_h().vp_a(this.vp_cu);
            vp_dz.vp_h().handler(bArr);
            return;
        }
        if (str.equals("custom_protocol_setting_oprate") && bArr[1] == 4 && bArr.length > 4 && bArr[4] == 3) {
            if (this.vp_ct == null) {
                return;
            }
            vp_dz.vp_h().vp_a(this.vp_ct);
            vp_dz.vp_h().handler(bArr);
            return;
        }
        if (str.equals("custom_protocol_setting_oprate") && bArr[1] == 6) {
            vp_dz.vp_l().vp_a(this.vp_cv);
            vp_dz.vp_l().vp_a(this.vp_cw);
            vp_dz.vp_l().handler(bArr);
            return;
        }
        if (str.equals("custom_protocol_setting_oprate")) {
            if (this.vp_co != null) {
                com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(this.vp_co);
            }
            if (this.vp_cq != null) {
                com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(this.vp_cq);
            }
            if (this.vp_cs != null) {
                com.veepoo.protocol.operate.vp_y.vp_i.vp_a().vp_a(this.vp_cs);
            }
            com.veepoo.protocol.operate.vp_y.vp_i.vp_a().handler(bArr);
            return;
        }
        if (str.equals("qx17_data_acquisition_oprate")) {
            vp_d().vp_a(bArr, this.vp_a.vp_ba);
            return;
        }
        if (str.equals("remind_event_oprate")) {
            vp_cf.vp_g.vp_a().handler(bArr);
            return;
        }
        if (!str.equals("health_light_oprate")) {
            if (str.equals("check_wear_oprate") && bArr.length > 1 && bArr[1] == 5) {
                if (this.vp_db == null) {
                    return;
                }
                vp_dz.vp_k().vp_a(bArr, this.vp_db);
                return;
            } else {
                if (str.equals("game_setting_operate")) {
                    com.veepoo.protocol.operate.vp_av.vp_f.vp_b().handler(bArr);
                    return;
                }
                if (com.veepoo.protocol.profile.vp_b.vp_a(str)) {
                    this.vp_a.vp_a(str, bArr, vp_dz.vp_a(str));
                    if (str.equals("sos_oprate") && bArr[1] == 4) {
                        sendOrder(this.vp_du, vp_e());
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if (bArr.length > 1 && ((b4 = bArr[1]) == 7 || b4 == 8)) {
            if (this.vp_cy == null) {
                return;
            }
            vp_dz.vp_f().vp_a(this.vp_cy);
            vp_dz.vp_f().handler(bArr);
            return;
        }
        if (bArr.length > 1 && ((b3 = bArr[1]) == 9 || b3 == 10)) {
            if (this.vp_cx == null) {
                return;
            }
            vp_dz.vp_g().vp_a(this.vp_cx);
            vp_dz.vp_g().handler(bArr);
            return;
        }
        if (bArr.length <= 1 || !((b2 = bArr[1]) == 11 || b2 == 12 || b2 == 13 || b2 == 14 || b2 == 15)) {
            com.veepoo.protocol.operate.vp_bb.vp_a vp_aVar3 = com.veepoo.protocol.operate.vp_bb.vp_d;
            if (vp_aVar3.vp_a(bArr)) {
                vp_aVar3.vp_a().handler(bArr);
                return;
            }
            return;
        }
        if (this.vp_da == null) {
            return;
        }
        vp_dz.vp_e().vp_a(this.vp_da);
        vp_dz.vp_e().handler(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.VPOperateManager>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static VPOperateManager getInstance() {
        if (vp_dx == null) {
            ?? r0 = VPOperateManager.class;
            synchronized (r0) {
                try {
                    if (vp_dx == null) {
                        vp_dx = new VPOperateManager();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_dx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Class<com.veepoo.protocol.VPOperateManager>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Deprecated
    public static VPOperateManager getMangerInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (vp_dx == null) {
            ?? r0 = VPOperateManager.class;
            synchronized (r0) {
                try {
                    if (vp_dx == null) {
                        mContext = applicationContext;
                        vp_dx = new VPOperateManager();
                        a = VpSpSaveUtil.getVpSpVariInstance(mContext);
                        b = VpSpGetUtil.getVpSpVariInstance(mContext);
                        c = new FunctionCheckUtil(b, mContext);
                        vp_dy = new BluetoothClient(applicationContext);
                        vp_dz.vp_a(applicationContext);
                        vp_h();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_dx;
    }

    private void vp_i() {
        this.vp_a.vp_a(new vp_e());
    }

    private VpSpGetUtil vp_f() {
        return VpSpGetUtil.getVpSpVariInstance(mContext);
    }

    private static void vp_h() {
        vp_a(mContext);
        a.savePhoneInfo(Build.MODEL + "", Build.VERSION.RELEASE + "", Build.VERSION.SDK_INT + "");
        a.saveSdkVersionInfo(VPPROTOCOL_VERSION);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [long] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v22, types: [int] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.io.PrintStream] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.io.FileInputStream, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v5 */
    public static byte[] readFileToBytes(String str) throws Throwable {
        ?? r6;
        Throwable th;
        int i2;
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            System.err.println("文件不存在或不可读: " + str);
            return null;
        }
        long length = file.length();
        if (length > 2147483647L) {
            System.err.println("文件过大，无法一次性读取到字节数组中。");
            return null;
        }
        int i3 = length;
        IOException iOException = null;
        try {
            try {
                ?? fileInputStream = new FileInputStream(file);
                i3 = (int) i3;
                try {
                    byte[] bArr = new byte[i3];
                    int i4 = 0;
                    while (true) {
                        i2 = i4;
                        if (i2 >= i3) {
                            break;
                        }
                        int i5 = fileInputStream.read(bArr, i2, i3 - i2);
                        if (i5 < 0) {
                            break;
                        }
                        i4 = i2 + i5;
                    }
                    if (i2 < i3) {
                        i3 = System.err;
                        i3.println("文件未完全读取。");
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        fileInputStream.printStackTrace();
                    }
                    return bArr;
                } catch (IOException e2) {
                    iOException = e2;
                    i3.printStackTrace();
                    ?? r0 = iOException;
                    if (r0 != 0) {
                        try {
                            r0 = iOException;
                            r0.close();
                        } catch (IOException unused2) {
                            r0.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    r6 = fileInputStream;
                    ?? r1 = r6;
                    if (r1 != 0) {
                        try {
                            r1 = r6;
                            r1.close();
                        } catch (IOException unused3) {
                            r1.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException unused4) {
            }
        } catch (Throwable th3) {
            r6 = iOException;
            th = th3;
        }
    }

    public static String getCurrentDeviceAddress() {
        return getInstance().vp_bj;
    }

    private vp_cc vp_d() {
        if (this.vp_dw == null) {
            this.vp_dw = (vp_cc) vp_dz.vp_a("qx17_data_acquisition_oprate");
        }
        return this.vp_dw;
    }

    private void vp_c() {
        vp_dy.unnotify(this.vp_bj, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_am());
    }

    public void startScanDevice(SearchResponse searchResponse) {
        startScanDevice(20, searchResponse);
    }

    public synchronized void connectDevice(String str, String str2, IConnectResponse iConnectResponse, INotifyResponse iNotifyResponse) {
        if (mContext == null) {
            iConnectResponse.connectState(-111, null, false);
            return;
        }
        if (!TextUtils.isEmpty(this.vp_bj)) {
            unregisterConnectStatusListener(this.vp_bj, this.vp_dk);
            this.vp_dk = null;
        }
        this.vp_bi = null;
        isFindJLService = false;
        this.vp_bj = str;
        this.vp_ai.vp_b(str);
        AIFunctionOpt.vp_h.vp_a().vp_a((AIDeviceConfigBean) null);
        if (vp_dy.getConnectStatus(this.vp_bj) == 2) {
            return;
        }
        JLOTAManager.getInstance().setJlDeviceDfuModel(false);
        vp_dy.connect(str, new BleConnectOptions.Builder().setConnectRetry(3).setConnectTimeout(30000).setServiceDiscoverRetry(3).setServiceDiscoverTimeout(20000).build(), new vp_c(str2, str, iNotifyResponse, iConnectResponse));
        a.clearSomeSp();
        com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a();
        if (this.vp_dk == null) {
            this.vp_dk = new vp_bd();
            new StringBuilder().append("-----> 注册连接监听 -- address = ").append(this.vp_bj);
            vp_dy.registerConnectStatusListener(this.vp_bj, this.vp_dk);
        }
        this.vp_ai.vp_a(this.vp_dl);
        this.vp_ai.vp_a(this.vp_dm);
    }

    public void confirmDevicePwd(IBleWriteResponse iBleWriteResponse, IPwdDataListener iPwdDataListener, IDeviceFuctionDataListener iDeviceFuctionDataListener, ISocialMsgDataListener iSocialMsgDataListener, ICustomSettingDataListener iCustomSettingDataListener, String str, boolean z, DeviceTimeSetting deviceTimeSetting) {
        if (str.length() > 4) {
            throw new IllegalArgumentException("密码的长度应该是4位数字");
        }
        isPasswordConfirmBack = false;
        int mtuValue = b.getMtuValue();
        a.clearAll();
        a.saveMTUValue(mtuValue);
        a.saveDevicePwd(str);
        this.vp_a.vp_a(iPwdDataListener);
        this.vp_a.vp_a(iDeviceFuctionDataListener);
        this.vp_bu = iSocialMsgDataListener;
        this.vp_a.vp_a(iSocialMsgDataListener);
        this.vp_a.vp_a(iCustomSettingDataListener);
        vp_dz.vp_a("pwd_comfirm_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, str, z, this.vp_ax, deviceTimeSetting);
        vp_i();
        if (this.vp_ax) {
            this.vp_do.sendEmptyMessageDelayed(CODE_CONNECTION_CONFIRMATION_TIMEOUT, this.vp_dn * 1000);
        }
    }

    @Deprecated
    public void settingNightTurnWriste(IBleWriteResponse iBleWriteResponse, INightTurnWristeDataListener iNightTurnWristeDataListener, boolean z) {
        this.vp_a.vp_a(iNightTurnWristeDataListener);
        vp_dz.vp_a("night_turn_opeate").vp_d(vp_dy, this.vp_bj, iBleWriteResponse, z);
    }

    public void startDetectSPO2H(IBleWriteResponse iBleWriteResponse, ISpo2hDataListener iSpo2hDataListener, ILightDataCallBack iLightDataCallBack) {
        startDetectSPO2H(iBleWriteResponse, iSpo2hDataListener);
        vp_a(true, iLightDataCallBack);
    }

    public void readSportModelOrigin(IBleWriteResponse iBleWriteResponse, ISportModelOriginListener iSportModelOriginListener, int i2) {
        if (c.checkSportModel()) {
            this.vp_bf = iSportModelOriginListener;
            this.vp_p.vp_f(vp_dy, this.vp_bj, iBleWriteResponse, i2);
        }
    }

    public void startDetectMultiECG(BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, MultiEcgConfig multiEcgConfig) {
        if (c.checkECG()) {
            g = true;
            vp_a(z);
            this.vp_w.vp_a(vp_dy, this.vp_bj, bleWriteResponse, z, iMultiEcgDetectListener, c.checkEcgDisease(), multiEcgConfig);
        }
    }

    public void settingScreenStyle(IBleWriteResponse iBleWriteResponse, IScreenStyleListener iScreenStyleListener, int i2, EUIFromType eUIFromType) {
        if (c.checkScreenStyle()) {
            this.vp_cn = iScreenStyleListener;
            this.vp_g.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, i2, eUIFromType);
        }
    }

    public void makeDeviceIntoUpdateModeAGPS(IBleWriteResponse iBleWriteResponse, UIDataCustom uIDataCustom, IUIOprateListener iUIOprateListener) {
        this.vp_a.vp_a(iUIOprateListener);
        vp_dz.vp_a("function_big_data_tarn").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, uIDataCustom);
    }

    public RRLorenzInfo convertRRData2RRLorenzInfo(int[] iArr, int[] iArr2, int[] iArr3) {
        if (c.checkLorentz(true) && iArr != null && iArr.length >= 2000 && iArr2.length == 6 && iArr3.length == 7) {
            return com.veepoo.protocol.util.vp_ab.vp_a(iArr);
        }
        return null;
    }

    public void startBloodGlucoseDetect(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
        if (iBloodGlucoseChangeListener == null) {
            return;
        }
        this.vp_de = iBloodGlucoseChangeListener;
        this.vp_ah.vp_a(vp_dy, this.vp_bj, iBleWriteResponse, eBloodGlucoseDetectModel);
    }

    public void stopBloodGlucoseDetect(IBleWriteResponse iBleWriteResponse, IBloodGlucoseChangeListener iBloodGlucoseChangeListener, EBloodGlucoseDetectModel eBloodGlucoseDetectModel) {
        if (iBloodGlucoseChangeListener == null) {
            return;
        }
        this.vp_de = iBloodGlucoseChangeListener;
        this.vp_ah.vp_b(vp_dy, this.vp_bj, iBleWriteResponse, eBloodGlucoseDetectModel);
    }

    public void connectBT(String str, IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        isUseDefaultBTType = true;
        this.vp_cb = iDeviceBTConnectionListener;
        com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_j(com.veepoo.protocol.jl.classic.vp_d.vp_a(str));
    }

    public void readContact(List<Contact> list, @NotNull IContactOptListener iContactOptListener, IBleWriteResponse iBleWriteResponse) {
        if (checkDeviceIsConnected()) {
            this.vp_ce = iContactOptListener;
            int iVp_a = -1;
            if (list != null && list.size() > 0) {
                iVp_a = com.veepoo.protocol.util.vp_h.vp_a.vp_a(list);
            }
            com.veepoo.protocol.operate.vp_r.vp_m.vp_a().vp_a(iVp_a, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    public void readBodyComponentData(BleWriteResponse bleWriteResponse, IBodyComponentReadDataListener iBodyComponentReadDataListener, ArrayList<Integer> arrayList) {
        if (c.checkBodyComponent()) {
            this.vp_ar.vp_a(vp_dy, this.vp_bj, bleWriteResponse, arrayList, iBodyComponentReadDataListener);
        }
    }

    void vp_b(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
        AIFunctionOpt.vp_h.vp_a().vp_b(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode);
    }

    public void readZT79GameClocks(List<GameClockBean> list, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        readZT79GameClocks(null, list, iGameClockOptListener, iBleWriteResponse);
    }

    public void setZT79GameClocks(boolean z, List<GameClockBean> list, List<GameClockBean> list2, IGameClockOptListener iGameClockOptListener, IBleWriteResponse iBleWriteResponse) {
        if (isCurrentDeviceConnected() && c.checkGameClockFunction() && list != null && !list.isEmpty()) {
            new StringBuilder().append("**调用设置游戏时段，共 ").append(list.size()).append(" 条");
            com.veepoo.protocol.operate.vp_av.vp_a vp_aVar = com.veepoo.protocol.operate.vp_av.vp_f;
            vp_aVar.vp_b().vp_a(iGameClockOptListener);
            vp_aVar.vp_b().vp_b(z, list, vp_a(list2), vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    private boolean vp_a() {
        return VpSpGetUtil.getVpSpVariInstance(mContext).getECGType() == 15;
    }

    @Deprecated
    public void confirmDevicePwd(IBleWriteResponse iBleWriteResponse, IPwdDataListener iPwdDataListener, IDeviceFuctionDataListener iDeviceFuctionDataListener, ISocialMsgDataListener iSocialMsgDataListener, String str, boolean z) {
        confirmDevicePwd(iBleWriteResponse, iPwdDataListener, iDeviceFuctionDataListener, iSocialMsgDataListener, null, str, z, null);
        VpSpSaveUtil.getVpSpVariInstance(mContext).clearSomeSp();
    }

    @Deprecated
    public void settingNightTurnWriste(IBleWriteResponse iBleWriteResponse, INightTurnWristeDataListener iNightTurnWristeDataListener, boolean z, TimeData timeData, TimeData timeData2) {
        if (c.checkNightturnSetting()) {
            this.vp_a.vp_a(iNightTurnWristeDataListener);
            vp_dz.vp_a("night_turn_opeate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, z, timeData, timeData2);
        }
    }

    private void vp_b(byte[] bArr) {
        isPasswordConfirmBack = false;
        PwdData pwdDataVp_b = new vp_ca().vp_b(bArr);
        Map mapVp_a = vp_a(mContext);
        if (this.vp_bk) {
            new StringBuilder().append("isOADModel：").append(this.vp_bk);
            return;
        }
        if (pwdDataVp_b == null || pwdDataVp_b.getDeviceTestVersion().equals("00.00.00.00")) {
            return;
        }
        EPwdStatus ePwdStatus = pwdDataVp_b.getmStatus();
        if (ePwdStatus != EPwdStatus.CHECK_SUCCESS && ePwdStatus != EPwdStatus.CHECK_AND_TIME_SUCCESS) {
            isPasswordConfirmBack = false;
            return;
        }
        isPasswordConfirmBack = true;
        if (TextUtils.isEmpty(b.getUseDateInfo())) {
            a.saveUseDateInfo();
        }
        if (isNetworkConnected(mContext)) {
            vp_a(pwdDataVp_b, mapVp_a);
        } else {
            vp_g();
        }
    }

    private EJH76SNCodeError vp_a(String str) {
        if (str == null || str.length() != 10) {
            StringBuilder sbAppend = new StringBuilder().append("***JH76设置SN码");
            EJH76SNCodeError eJH76SNCodeError = EJH76SNCodeError.INVALID_LENGTH;
            sbAppend.append(eJH76SNCodeError.getDes()).append("，snCode = ").append(str);
            return eJH76SNCodeError;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!Character.isDigit(str.charAt(i2))) {
                StringBuilder sbAppend2 = new StringBuilder().append("***JH76设置SN码");
                EJH76SNCodeError eJH76SNCodeError2 = EJH76SNCodeError.INVALID_FORMAT;
                sbAppend2.append(eJH76SNCodeError2.getDes()).append("，snCode = ").append(str);
                return eJH76SNCodeError2;
            }
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) != '0') {
                return EJH76SNCodeError.SUCCESS;
            }
        }
        StringBuilder sbAppend3 = new StringBuilder().append("***JH76设置SN码");
        EJH76SNCodeError eJH76SNCodeError3 = EJH76SNCodeError.ALL_ZERO;
        sbAppend3.append(eJH76SNCodeError3.getDes()).append("，snCode = ").append(str);
        return eJH76SNCodeError3;
    }

    private void vp_a(boolean z, String str, IDeviceRenameListener iDeviceRenameListener, IBleWriteResponse iBleWriteResponse) {
        if (iDeviceRenameListener == null) {
            new StringBuilder().append("***蓝牙").append(z ? "4.0" : "3.0").append("设备重命名监听不能为空");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            new StringBuilder().append("***蓝牙").append(z ? "4.0" : "3.0").append("设备重命名名称不能为空");
            iDeviceRenameListener.onDeviceRenameFail(ERenameError.LENGTH_0_ERROR, str);
            return;
        }
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        int cPUType = VpSpGetUtil.getVpSpVariInstance(mContext).getCPUType();
        if (cPUType == 0 && bytes.length > 8) {
            StringBuilder sbAppend = new StringBuilder().append("***蓝牙").append(z ? "4.0" : "3.0");
            ERenameError eRenameError = ERenameError.LENGTH_1_8_ERROR;
            sbAppend.append(eRenameError.getDes()).append("，deviceName = ").append(str).append(" byteLength = ").append(bytes.length);
            iDeviceRenameListener.onDeviceRenameFail(eRenameError, str);
            return;
        }
        if (cPUType == 1 && bytes.length > 18) {
            StringBuilder sbAppend2 = new StringBuilder().append("***蓝牙").append(z ? "4.0" : "3.0");
            ERenameError eRenameError2 = ERenameError.LENGTH_1_18_ERROR;
            sbAppend2.append(eRenameError2.getDes()).append("，deviceName = ").append(str).append(" byteLength = ").append(bytes.length);
            iDeviceRenameListener.onDeviceRenameFail(eRenameError2, str);
            return;
        }
        this.vp_a.vp_a(iDeviceRenameListener);
        if (z) {
            vp_dz.vp_i().vp_a(str, vp_dy, this.vp_bj, iBleWriteResponse);
        } else {
            vp_dz.vp_i().vp_b(str, vp_dy, this.vp_bj, iBleWriteResponse);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.util.HashMap, java.util.Map] */
    private static Map vp_a(Context context) {
        ?? map = new HashMap();
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            String str = packageManager.getPackageInfo(packageName, 0).versionName;
            String string = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 128)).toString();
            map.put("AppPackageName", packageName);
            map.put("AppName", string);
            map.put("AppVersion", str);
            a.saveAppInfo(packageName, string, str);
            new StringBuilder().append("appPackageName=").append(packageName).append(",appName=").append(string).append(",appVersion=").append(str);
        } catch (PackageManager.NameNotFoundException unused) {
            map.printStackTrace();
        }
        return map;
    }

    private void vp_a(byte[] bArr) {
        vp_d().vp_c(bArr);
    }

    private boolean vp_a(BleGattProfile bleGattProfile) {
        if (bleGattProfile == null || bleGattProfile.getServices().isEmpty()) {
            return false;
        }
        for (BleGattService bleGattService : bleGattProfile.getServices()) {
            if (bleGattService.getUUID().equals(com.veepoo.protocol.profile.vp_a.vp_o)) {
                Iterator it = bleGattService.getCharacters().iterator();
                while (it.hasNext()) {
                    if (((BleGattCharacter) it.next()).getUuid().equals(com.veepoo.protocol.profile.vp_a.vp_q)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void vp_a(IAllHealthDataListener iAllHealthDataListener, int i2) {
        if (VpSpGetUtil.getVpSpVariInstance(mContext).getOriginProtocolVersion() == 3) {
            return;
        }
        readOriginData(this.vp_du, new vp_n(iAllHealthDataListener), i2);
    }

    private void vp_a(IAllHealthDataListener iAllHealthDataListener, int i2, int i3, int i4) {
        IOriginProgressListener iOriginProgressListener;
        int originProtocolVersion = VpSpGetUtil.getVpSpVariInstance(mContext).getOriginProtocolVersion();
        Log.e("Test", "readOriginDataBySetting --------------------" + originProtocolVersion);
        if (originProtocolVersion == 3 || originProtocolVersion == 5) {
            iOriginProgressListener = vp_jVar;
            vp_j vp_jVar = new vp_j(iAllHealthDataListener);
        } else {
            iOriginProgressListener = vp_lVar;
            vp_l vp_lVar = new vp_l(iAllHealthDataListener, i2);
        }
        readOriginDataFromDay(this.vp_du, iOriginProgressListener, i2, i3, i4);
    }

    private void vp_a(String str, OnMcuMgrOtaListener onMcuMgrOtaListener) {
        enterOad(i2 -> {
            HBThreadPools.getInstance().postDelay(new vp_aa(onMcuMgrOtaListener, str), 200L);
        });
    }

    private List vp_a(List list) {
        ArrayList arrayList;
        if (list == null) {
            arrayList = arrayList;
            ArrayList arrayList2 = new ArrayList();
        } else {
            arrayList = arrayList;
            ArrayList arrayList3 = new ArrayList(list);
        }
        return arrayList;
    }

    void vp_a(IBleWriteResponse iBleWriteResponse, AIFunctionOpt.AIAppErrorCode aIAppErrorCode) {
        AIFunctionOpt.vp_h.vp_a().vp_a(vp_dy, this.vp_bj, iBleWriteResponse, aIAppErrorCode);
    }

    private void vp_a(boolean z, INotifyResponse iNotifyResponse) {
        BluetoothLog.i("setChannelNotify");
        if (this.vp_bi == null) {
            this.vp_bi = new vp_bh(vp_dy, this.vp_bj);
        }
        if (z) {
            this.vp_bi.vp_b(iNotifyResponse);
        } else {
            this.vp_bi.vp_a(iNotifyResponse);
        }
    }

    private void vp_a(boolean z) {
        if (z) {
            vp_a(true, (INotifyResponse) new vp_t());
            return;
        }
        vp_bh vp_bhVar = this.vp_bi;
        if (vp_bhVar != null) {
            vp_bhVar.vp_a((IECGLightDataCallback) null);
        }
    }

    private void vp_a(boolean z, ILightDataCallBack iLightDataCallBack) {
        if (z) {
            vp_a(true, (INotifyResponse) new vp_q(iLightDataCallBack));
        } else {
            vp_a(false, (INotifyResponse) new vp_p());
        }
    }

    private void vp_a(boolean z, IG08ProjectPPGLightCallBack iG08ProjectPPGLightCallBack) {
        if (z) {
            vp_a(true, (INotifyResponse) new vp_s(iG08ProjectPPGLightCallBack));
        } else {
            vp_a(false, (INotifyResponse) new vp_r());
        }
    }

    private void vp_a(String str, INotifyResponse iNotifyResponse) {
        this.vp_bj = str;
        vp_dy.notify(str, com.veepoo.protocol.profile.vp_a.vp_c, com.veepoo.protocol.profile.vp_a.vp_d, new vp_v(iNotifyResponse));
    }

    private void vp_a(IBleWriteResponse iBleWriteResponse, IFindDevicelistener iFindDevicelistener, int i2) {
        if (!c.checFindDeviceByPhone()) {
            Toast.makeText(mContext, "This feature is not supported", 0).show();
        } else {
            this.vp_a.vp_a(iFindDevicelistener);
            vp_dz.vp_a("find_phone_by_watch_operate").vp_c(vp_dy, this.vp_bj, iBleWriteResponse, i2);
        }
    }

    private void vp_a(IBleWriteResponse iBleWriteResponse, ILowPowerListener iLowPowerListener, LowPowerSetting lowPowerSetting) {
        if (c.checklowPower()) {
            this.vp_a.vp_a(iLowPowerListener);
            vp_dz.vp_a("battery_lowpower_oprate").vp_a(vp_dy, this.vp_bj, iBleWriteResponse, lowPowerSetting);
        }
    }

    private void vp_a(String str, IImageMsgPushListener iImageMsgPushListener) {
        UiUpdateUtil.getInstance().init(mContext);
        UiUpdateUtil.getInstance().getImagePushUiInfo(new vp_ai(str, iImageMsgPushListener));
    }

    private void vp_a(String str, BleWriteResponse bleWriteResponse, ITextMsgPushListener iTextMsgPushListener) {
        if (str == null) {
            return;
        }
        vp_da.vp_g.vp_a().vp_c(vp_dy, this.vp_bj, bleWriteResponse, new vp_ae(str, iTextMsgPushListener));
    }

    private void vp_a(PwdData pwdData, Map map) {
        new Thread(new vp_a(new com.veepoo.protocol.util.vp_s(pwdData, this.vp_bj, map, b.getUpdateInfo(pwdData.getDeviceNumber() + ""), new vp_ba(pwdData)))).start();
    }
}
