package com.veepoo.protocol.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.model.datas.UserDateInfo;
import com.veepoo.protocol.shareprence.VpSpGetUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/FunctionCheckUtil.class */
public final class FunctionCheckUtil {
    private static volatile FunctionCheckUtil vp_h;
    private VpSpGetUtil vp_a;
    private Context vp_b;
    final int vp_c = 0;
    final int vp_d = 1;
    final int vp_e = 2;
    final int vp_f = 3;
    final int vp_g = 4;

    public boolean checkFindDevice() {
        boolean zIsSupportFindDevice = this.vp_a.isSupportFindDevice();
        if (!zIsSupportFindDevice) {
            vp_b();
        }
        return zIsSupportFindDevice;
    }

    public boolean checkB3() {
        boolean zIsSupportAutoMeasure = this.vp_a.isSupportAutoMeasure();
        if (!zIsSupportAutoMeasure) {
            vp_b();
        }
        return zIsSupportAutoMeasure;
    }

    public boolean checkStress() {
        boolean zIsSupportStress = this.vp_a.isSupportStress();
        if (!zIsSupportStress) {
            vp_b();
        }
        return zIsSupportStress;
    }

    public boolean checkHrvAppDetect() {
        boolean zIsSupportHrvAppDetect = this.vp_a.isSupportHrvAppDetect();
        if (!zIsSupportHrvAppDetect) {
            vp_b();
        }
        return zIsSupportHrvAppDetect;
    }

    public boolean checkGSR() {
        boolean zIsSupportGSR = this.vp_a.isSupportGSR();
        if (!zIsSupportGSR) {
            vp_b();
        }
        return zIsSupportGSR;
    }

    public boolean checkSafetyProtection() {
        boolean zIsSupportSafetyProtection = this.vp_a.isSupportSafetyProtection();
        if (!zIsSupportSafetyProtection) {
            vp_b();
        }
        return zIsSupportSafetyProtection;
    }

    public boolean checkMiniCheckup() {
        boolean zIsSupportMiniCheckup = this.vp_a.isSupportMiniCheckup();
        if (!zIsSupportMiniCheckup) {
            vp_b();
        }
        return zIsSupportMiniCheckup;
    }

    public boolean checkHealthLight() {
        boolean zIsSupportHealthLight = this.vp_a.isSupportHealthLight();
        if (!zIsSupportHealthLight) {
            vp_b();
        }
        return zIsSupportHealthLight;
    }

    public boolean checkMetDetect() {
        boolean zIsSupportMetAppDetect = this.vp_a.isSupportMetAppDetect();
        if (!zIsSupportMetAppDetect) {
            vp_b();
        }
        return zIsSupportMetAppDetect;
    }

    public boolean checkEmotionDetect() {
        boolean zIsSupportEmotionAppDetect = this.vp_a.isSupportEmotionAppDetect();
        if (!zIsSupportEmotionAppDetect) {
            vp_b();
        }
        return zIsSupportEmotionAppDetect;
    }

    public boolean checkHealthAssessment() {
        boolean zIsSupportHealthAssessment = this.vp_a.isSupportHealthAssessment();
        if (!zIsSupportHealthAssessment) {
            vp_b();
        }
        return zIsSupportHealthAssessment;
    }

    public boolean checkTextImagePush() {
        boolean zIsSupportTextImagePush = this.vp_a.isSupportTextImagePush();
        if (!zIsSupportTextImagePush) {
            vp_b();
        }
        return zIsSupportTextImagePush;
    }

    public boolean checkGameClockFunction() {
        boolean zIsSupportGameClock = this.vp_a.isSupportGameClock();
        if (!zIsSupportGameClock) {
            vp_b();
        }
        return zIsSupportGameClock;
    }

    public boolean checFindDeviceByPhone() {
        boolean zIsSupportFindDeviceByPhone = this.vp_a.isSupportFindDeviceByPhone();
        if (!zIsSupportFindDeviceByPhone) {
            vp_b();
        }
        return zIsSupportFindDeviceByPhone;
    }

    public boolean checkCheckWear() {
        boolean zIsSupportCheckWear = this.vp_a.isSupportCheckWear();
        if (!zIsSupportCheckWear) {
            vp_b();
        }
        return zIsSupportCheckWear;
    }

    public boolean checklowPower() {
        boolean zIsSupportLowPower = this.vp_a.isSupportLowPower();
        if (!zIsSupportLowPower) {
            vp_b();
        }
        return zIsSupportLowPower;
    }

    public boolean checkBp() {
        boolean zIsSupportBp = this.vp_a.isSupportBp();
        if (!zIsSupportBp) {
            vp_b();
        }
        return zIsSupportBp;
    }

    public boolean checkBgAdjusting() {
        boolean zIsSupportBloodGlucoseAdjusting = this.vp_a.isSupportBloodGlucoseAdjusting();
        if (!zIsSupportBloodGlucoseAdjusting) {
            vp_b();
        }
        return zIsSupportBloodGlucoseAdjusting;
    }

    public boolean checkBgMultipleAdjusting() {
        boolean zIsSupportBloodGlucoseMultipleAdjusting = this.vp_a.isSupportBloodGlucoseMultipleAdjusting();
        if (!zIsSupportBloodGlucoseMultipleAdjusting) {
            vp_b();
        }
        return zIsSupportBloodGlucoseMultipleAdjusting;
    }

    public boolean checkDrink() {
        boolean zIsSupportDrink = this.vp_a.isSupportDrink();
        if (!zIsSupportDrink) {
            vp_b();
        }
        return zIsSupportDrink;
    }

    public boolean checkLongseat() {
        boolean zIsSupportLongseat = this.vp_a.isSupportLongseat();
        if (!zIsSupportLongseat) {
            vp_b();
        }
        return zIsSupportLongseat;
    }

    public boolean checkWechatSport() {
        boolean zIsSupportWechatSport = this.vp_a.isSupportWechatSport();
        if (!zIsSupportWechatSport) {
            vp_b();
        }
        return zIsSupportWechatSport;
    }

    public boolean checkCamera() {
        boolean zIsSupportCamera = this.vp_a.isSupportCamera();
        if (!zIsSupportCamera) {
            vp_b();
        }
        return zIsSupportCamera;
    }

    public boolean checkFtg() {
        boolean zIsSupportFtg = this.vp_a.isSupportFtg();
        if (!zIsSupportFtg) {
            vp_b();
        }
        return zIsSupportFtg;
    }

    public boolean isReturnAllSpo2hData(boolean z) {
        boolean zIsSupportSpo2h = this.vp_a.isSupportSpo2h();
        boolean zVp_a = zIsSupportSpo2h;
        if (zIsSupportSpo2h) {
            zVp_a = vp_a(vp_ad.vp_d, true, 1);
        } else if (z) {
            vp_b();
        }
        return zVp_a;
    }

    public boolean isSupportAutoDetect() {
        boolean zEquals = this.vp_a.getDeviceNumber().equals("323");
        if (!zEquals) {
            vp_b();
        }
        return zEquals;
    }

    public boolean isEcgMultiLeadDevice() {
        return this.vp_a.getDeviceNumber().equals("2501");
    }

    public boolean checkBaseType() {
        return vp_a(vp_ad.vp_a, false, 0);
    }

    public boolean checkSBBR() {
        boolean zIsSupportSBBR = this.vp_a.isSupportSBBR();
        if (!zIsSupportSBBR) {
            vp_b();
        }
        return zIsSupportSBBR;
    }

    public boolean checkWeather() {
        boolean zIsSupportWeather = this.vp_a.isSupportWeather();
        if (!zIsSupportWeather) {
            vp_b();
        }
        return zIsSupportWeather;
    }

    public boolean isCanReadTempture() {
        boolean zIsSupportReadTempture = this.vp_a.isSupportReadTempture();
        boolean z = this.vp_a.getTemperatureType() == 5;
        if (!zIsSupportReadTempture) {
            vp_b();
        }
        if (z) {
            vp_c("Please read daily data");
        }
        return zIsSupportReadTempture && !z;
    }

    public boolean isUMweather() {
        return this.vp_a.getWeatherType() == 2;
    }

    public boolean isMusicWithInfo() {
        return this.vp_a.getMusicType() == 1;
    }

    public boolean checkHRV(boolean z) {
        return this.vp_a.isSupportHRV();
    }

    public boolean checkLorentz(boolean z) {
        boolean zIsSupportHRV = this.vp_a.isSupportHRV();
        boolean zVp_a = zIsSupportHRV;
        if (zIsSupportHRV) {
            zVp_a = vp_a(vp_ad.vp_c, true, 4);
        } else if (z) {
            vp_b();
        }
        return zVp_a;
    }

    public boolean checkEcgDisease() {
        return vp_a(vp_ad.vp_e, true, 4);
    }

    public boolean checkECG() {
        return true;
    }

    public boolean checkHeartwaring() {
        boolean zIsSupportHeartwaring = this.vp_a.isSupportHeartwaring();
        if (!zIsSupportHeartwaring) {
            vp_b();
        }
        return zIsSupportHeartwaring;
    }

    public boolean checkNightturnSetting() {
        boolean zIsSupportNightturnSetting = this.vp_a.isSupportNightturnSetting();
        if (!zIsSupportNightturnSetting) {
            vp_b();
        }
        return zIsSupportNightturnSetting;
    }

    public boolean checkWomenSetting() {
        if (this.vp_a.isSupportWomenSetting()) {
            vp_a(vp_ad.vp_f, false, 3);
            return true;
        }
        vp_b();
        return true;
    }

    public boolean checkScreenlight() {
        boolean zIsSupportScreenlight = this.vp_a.isSupportScreenlight();
        if (!zIsSupportScreenlight) {
            vp_b();
        }
        return zIsSupportScreenlight;
    }

    public boolean checkScreenlightTime() {
        boolean zIsSupportScreenlightTime = this.vp_a.isSupportScreenlightTime();
        if (!zIsSupportScreenlightTime) {
            vp_b();
        }
        return zIsSupportScreenlightTime;
    }

    public boolean checkAngioAdjuster() {
        boolean zIsSupportAngioAdjuster = this.vp_a.isSupportAngioAdjuster();
        if (!zIsSupportAngioAdjuster) {
            vp_b();
        }
        return zIsSupportAngioAdjuster;
    }

    public boolean checkMultiAlarm() {
        boolean zIsSupportMultiAlarm = this.vp_a.isSupportMultiAlarm();
        if (!zIsSupportMultiAlarm) {
            vp_b();
        }
        return zIsSupportMultiAlarm;
    }

    public boolean checkTextAlarm() {
        return vp_a(true);
    }

    public boolean isSupportTextAlarm() {
        return vp_a(false);
    }

    public boolean checkRate() {
        boolean zIsSupportRate = this.vp_a.isSupportRate();
        if (!zIsSupportRate) {
            vp_b();
        }
        return zIsSupportRate;
    }

    public boolean checkTemptureByApp() {
        boolean zIsSupportCheckTemptureByApp = this.vp_a.isSupportCheckTemptureByApp();
        if (!zIsSupportCheckTemptureByApp) {
            vp_b();
        }
        return zIsSupportCheckTemptureByApp;
    }

    public boolean isSupportReadTemperature() {
        return this.vp_a.isSupportReadTempture();
    }

    public boolean checkTemperatureUnitSettings() {
        boolean zIsSupportSettingsTemperatureUnit = this.vp_a.isSupportSettingsTemperatureUnit();
        if (!zIsSupportSettingsTemperatureUnit) {
            vp_b();
        }
        return zIsSupportSettingsTemperatureUnit;
    }

    public boolean checkCountdown() {
        boolean zIsSupportCountdown = this.vp_a.isSupportCountdown();
        if (!zIsSupportCountdown) {
            vp_b();
        }
        return zIsSupportCountdown;
    }

    public boolean checkHid() {
        boolean zIsSupportHid = this.vp_a.isSupportHid();
        if (!zIsSupportHid) {
            vp_b();
        }
        return zIsSupportHid;
    }

    public boolean checkSportModel() {
        boolean zIsSupportSportModel = this.vp_a.isSupportSportModel();
        if (!zIsSupportSportModel) {
            vp_b();
        }
        return zIsSupportSportModel;
    }

    public boolean check4g() {
        boolean zIsSupport4G = this.vp_a.isSupport4G();
        if (!zIsSupport4G) {
            vp_b();
        }
        return zIsSupport4G;
    }

    public boolean isSupportMultSportModel() {
        boolean zIsSupportMultSportModel = this.vp_a.isSupportMultSportModel();
        if (!zIsSupportMultSportModel) {
            vp_b();
        }
        return zIsSupportMultSportModel;
    }

    public boolean checkScreenStyle() {
        boolean zIsSupportScreenStyle = this.vp_a.isSupportScreenStyle();
        if (!zIsSupportScreenStyle) {
            vp_b();
        }
        return zIsSupportScreenStyle;
    }

    public boolean checkBreath() {
        boolean zIsSupportBreath = this.vp_a.isSupportBreath();
        if (!zIsSupportBreath) {
            vp_b();
        }
        return zIsSupportBreath;
    }

    public boolean checkMetricsystem() {
        boolean zIsSupportMetricsystem = this.vp_a.isSupportMetricsystem();
        if (!zIsSupportMetricsystem) {
            vp_b();
        }
        return zIsSupportMetricsystem;
    }

    public boolean checkFivemiuteHeart() {
        boolean zIsSupportFivemiuteHeart = this.vp_a.isSupportFivemiuteHeart();
        if (!zIsSupportFivemiuteHeart) {
            vp_b();
        }
        return zIsSupportFivemiuteHeart;
    }

    public boolean checkFivemiuteBp() {
        boolean zIsSupportFivemiuteBp = this.vp_a.isSupportFivemiuteBp();
        if (!zIsSupportFivemiuteBp) {
            vp_b();
        }
        return zIsSupportFivemiuteBp;
    }

    public boolean checkSportOverRemain() {
        boolean zIsSupportSportOverRemain = this.vp_a.isSupportSportOverRemain();
        if (!zIsSupportSportOverRemain) {
            vp_b();
        }
        return zIsSupportSportOverRemain;
    }

    public boolean checkVoiceBpHeart() {
        boolean zIsSupportVoiceBpHeart = this.vp_a.isSupportVoiceBpHeart();
        if (!zIsSupportVoiceBpHeart) {
            vp_b();
        }
        return zIsSupportVoiceBpHeart;
    }

    public boolean checkFindPhoneUi() {
        boolean zIsSupportFindPhoneUi = this.vp_a.isSupportFindPhoneUi();
        if (!zIsSupportFindPhoneUi) {
            vp_b();
        }
        return zIsSupportFindPhoneUi;
    }

    public boolean checkSecondsWatch() {
        boolean zIsSupportSecondsWatch = this.vp_a.isSupportSecondsWatch();
        if (!zIsSupportSecondsWatch) {
            vp_b();
        }
        return zIsSupportSecondsWatch;
    }

    public boolean checkSpoh2LowRemain() {
        boolean zIsSupportSpoh2LowRemain = this.vp_a.isSupportSpoh2LowRemain();
        if (!zIsSupportSpoh2LowRemain) {
            vp_b();
        }
        return zIsSupportSpoh2LowRemain;
    }

    public boolean checkSkin() {
        boolean zIsSupportSkin = this.vp_a.isSupportSkin();
        if (!zIsSupportSkin) {
            vp_b();
        }
        return zIsSupportSkin;
    }

    public boolean checkBloodComponent() {
        boolean zIsSupportBloodComponent = this.vp_a.isSupportBloodComponent();
        if (!zIsSupportBloodComponent) {
            vp_b();
        }
        return zIsSupportBloodComponent;
    }

    public boolean checkBodyComponent() {
        boolean zIsSupportBodyComponent = this.vp_a.isSupportBodyComponent();
        if (!zIsSupportBodyComponent) {
            vp_b();
        }
        return zIsSupportBodyComponent;
    }

    public boolean checkWorldClock() {
        boolean zIsSupportWorldClock = this.vp_a.isSupportWorldClock();
        if (!zIsSupportWorldClock) {
            vp_b();
        }
        return zIsSupportWorldClock;
    }

    public boolean checkEcgMultiLead() {
        boolean zIsSupportEcgMultiLead = this.vp_a.isSupportEcgMultiLead();
        if (!zIsSupportEcgMultiLead) {
            vp_b();
        }
        return zIsSupportEcgMultiLead;
    }

    public boolean checkBarometer() {
        boolean zIsSupportBarometer = this.vp_a.isSupportBarometer();
        if (!zIsSupportBarometer) {
            vp_b();
        }
        return zIsSupportBarometer;
    }

    public boolean checkSportStatus() {
        boolean zIsSupportSportStatus = this.vp_a.isSupportSportStatus();
        if (!zIsSupportSportStatus) {
            vp_b();
        }
        return zIsSupportSportStatus;
    }

    public boolean checkRemindEvent() {
        boolean zIsSupportRemindEvent = this.vp_a.isSupportRemindEvent();
        if (!zIsSupportRemindEvent) {
            vp_b();
        }
        return zIsSupportRemindEvent;
    }

    public boolean checkPTTControl() {
        boolean zIsSupportPTTControl = this.vp_a.isSupportPTTControl();
        if (!zIsSupportPTTControl) {
            vp_b();
        }
        return zIsSupportPTTControl;
    }

    public FunctionCheckUtil(VpSpGetUtil vpSpGetUtil, Context context) {
        this.vp_a = vpSpGetUtil;
        this.vp_b = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.util.FunctionCheckUtil>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static FunctionCheckUtil getInstance(Context context) {
        if (vp_h == null) {
            ?? r0 = FunctionCheckUtil.class;
            synchronized (r0) {
                try {
                    if (vp_h == null) {
                        vp_h = new FunctionCheckUtil(VpSpGetUtil.getVpSpVariInstance(context.getApplicationContext()), context.getApplicationContext());
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_h;
    }

    private boolean vp_a(boolean z) {
        boolean zIsSupportTextAlarm = this.vp_a.isSupportTextAlarm();
        if (!zIsSupportTextAlarm && z) {
            vp_b();
        }
        return zIsSupportTextAlarm;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    private int vp_d(String str) {
        ?? r0 = str;
        ?? r3 = 0;
        try {
            r0 = Integer.parseInt(r0);
            r3 = r0;
        } catch (NumberFormatException unused) {
            r0.printStackTrace();
        }
        return r3 == true ? 1 : 0;
    }

    private void vp_c(String str) {
        VPOperateManager.getInstance().getUIHandler().post(() -> {
            Toast.makeText(this.vp_b, str, 0).show();
        });
    }

    private String vp_a(int i, UserDateInfo userDateInfo) {
        String basicType = "";
        switch (i) {
            case 0:
                basicType = userDateInfo.getBasicType();
                break;
            case 1:
                basicType = userDateInfo.getOxygenType();
                break;
            case 2:
                basicType = userDateInfo.getHrvType();
                break;
            case 3:
                basicType = userDateInfo.getFemaleType();
                break;
            case 4:
                basicType = userDateInfo.getEcgDisearseType();
                break;
        }
        return basicType;
    }

    private void vp_b() {
        if (VPOperateManager.isShowFunctionNotSupportToast()) {
            VPOperateManager.getInstance().getUIHandler().post(() -> {
                Toast.makeText(this.vp_b, "This feature is not supported", 0).show();
            });
        }
    }

    private boolean vp_a(int[] iArr, boolean z, int i) {
        boolean zVp_a;
        UserDateInfo userDateInfoVp_a;
        String deviceNumber = this.vp_a.getDeviceNumber();
        String updateInfo = this.vp_a.getUpdateInfo(deviceNumber);
        new StringBuilder().append("服务器信息:").append(updateInfo);
        if (updateInfo == null || updateInfo.equals("") || (userDateInfoVp_a = vp_ac.vp_a(updateInfo)) == null) {
            zVp_a = vp_a(iArr, z, deviceNumber);
        } else {
            new StringBuilder().append("服务器信息不为空，反序列化成功:").append(userDateInfoVp_a);
            String strVp_a = vp_a(i, userDateInfoVp_a);
            boolean z2 = strVp_a != null && strVp_a.equals("1");
            String expireDate = userDateInfoVp_a.getExpireDate();
            if (vp_a(expireDate)) {
                new StringBuilder().append("服务器信息不为空，反序列化成功，授权过期:").append(expireDate);
                zVp_a = false;
            } else {
                zVp_a = z2;
            }
        }
        return zVp_a;
    }

    private boolean vp_a(String str, int[] iArr) {
        boolean z = false;
        if (iArr == null || TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i : iArr) {
            if (vp_d(str) == i) {
                z = true;
                break;
            }
        }
        return z;
    }

    private boolean vp_a(String str) {
        return vp_j.vp_a(vp_j.vp_c(), str) <= 0;
    }

    private boolean vp_a(int[] iArr, boolean z, String str) {
        if (!vp_a(str, iArr)) {
            z = !z;
        }
        boolean z2 = z;
        new StringBuilder().append("本地信息 isSupport:").append(z);
        return z2;
    }
}
