package com.veepoo.protocol.shareprence;

import android.content.Context;
import android.content.pm.PackageManager;
import com.veepoo.protocol.model.enums.DeviceManualDataType;
import com.veepoo.protocol.model.enums.ECPUPlatform;
import com.veepoo.protocol.model.enums.ECpuType;
import com.veepoo.protocol.model.enums.EScreenType;
import com.veepoo.protocol.model.enums.ETemperatureUnit;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/shareprence/VpSpGetUtil.class */
public class VpSpGetUtil {
    private static volatile VpSpGetUtil vp_a;
    private static Context vp_b;

    private VpSpGetUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.shareprence.VpSpGetUtil>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static VpSpGetUtil getVpSpVariInstance(Context context) {
        if (vp_a == null) {
            ?? r0 = VpSpGetUtil.class;
            synchronized (r0) {
                try {
                    if (vp_a == null) {
                        vp_b = context.getApplicationContext();
                        vp_a = new VpSpGetUtil();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_a;
    }

    private boolean vp_d() {
        return vp_a(SputilVari.FUNCTION_BP_TAG, 0) == 3 || vp_a(SputilVari.FUNCTION_BP_TAG, 0) == 4 || vp_a(SputilVari.FUNCTION_BP_TAG, 0) == 5;
    }

    private boolean vp_h() {
        return vp_a(SputilVari.FUNCTION_RATE_TAG, 0) == 7 || vp_a(SputilVari.FUNCTION_RATE_TAG, 0) == 8;
    }

    private boolean vp_c() {
        return vp_a(SputilVari.FUNCTION_SPO2H_TYPE, 0) == 9;
    }

    private boolean vp_b() {
        return vp_a(SputilVari.BLOOD_GLOCOSE_TAG, 0) == 8 || vp_a(SputilVari.BLOOD_GLOCOSE_TAG, 0) == 9;
    }

    private boolean vp_l() {
        return vp_a(SputilVari.STRESS_TYPE, 0) == 3;
    }

    private boolean vp_e() {
        return false;
    }

    private boolean vp_j() {
        return vp_a(SputilVari.MET_TYPE, 0) == 3;
    }

    private boolean vp_i() {
        return vp_a(SputilVari.HRV_TYPE, 0) == 8;
    }

    private boolean vp_k() {
        return vp_a(SputilVari.MINI_CHECKUP_TYPE, 0) == 1 || vp_a(SputilVari.MINI_CHECKUP_TYPE, 0) == 3 || vp_a(SputilVari.MINI_CHECKUP_TYPE, 0) == 4;
    }

    private boolean vp_f() {
        return vp_a(SputilVari.TAG_EMOTION, 0) == 1;
    }

    private boolean vp_g() {
        return vp_a(SputilVari.TAG_FATIGUE, 0) == 2;
    }

    private boolean vp_m() {
        return vp_a(SputilVari.TAG_GSR, 0) == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private boolean vp_a(String str, boolean z) {
        ?? r0 = 0;
        ?? r5 = 0;
        try {
            r0 = SpUtil.getBoolean(vp_b, str, z);
            r5 = r0;
        } catch (Exception unused) {
            System.out.println("sp boolean->" + str);
            r0.printStackTrace();
        }
        return r5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    public String getVersion() {
        ?? r0 = "";
        ?? r4 = "";
        try {
            r0 = vp_b.getPackageManager().getPackageInfo(vp_b.getPackageName(), 0).versionName;
            r4 = r0;
        } catch (PackageManager.NameNotFoundException unused) {
            r0.printStackTrace();
        }
        ?? r1 = r4;
        new StringBuilder().append("getVersion:").append(r4);
        return r1;
    }

    public boolean isOadModel() {
        return vp_a(SputilVari.IS_OAD_MODEL, false);
    }

    @Deprecated
    public ECpuType getCpuType() {
        return isOadModel() ? ECpuType.getECpuType(vp_a(SputilVari.INFO_DEVICE_CPU, "")) : ECpuType.UNKONW;
    }

    public ECPUPlatform getCPUPlatform() {
        return isOadModel() ? ECPUPlatform.getEPlatform(vp_a(SputilVari.INFO_DEVICE_CPU_PLATFORM, "")) : ECPUPlatform.UNKNOWN;
    }

    public String getUseDateInfo() {
        return vp_a(SputilVari.INFO_USE_DATEINFO, "");
    }

    public String getDeviceName() {
        return vp_a(SputilVari.INFO_DEVICE_NAME, "");
    }

    public String getDeviceNumber() {
        return vp_a(SputilVari.INFO_DEVICE_NUMBER, "");
    }

    public String getTestVersion() {
        return vp_a(SputilVari.INFO_DEVICE_VERSION_TEST, "");
    }

    public String getUpdateInfo(String str) {
        return vp_a("info_update_dateinfo_" + str, "");
    }

    public String getSdkInfo() {
        return vp_a(SputilVari.INFO_UPDATEUSE_SDKINFO, "");
    }

    public int getPersonHeight() {
        return vp_a(SputilVari.COUNT_PERSON_HEIGHT, 175);
    }

    public int getAllLength() {
        return vp_a(SputilVari.COUNT_MESSAGE_POST_NUMBER, 4);
    }

    public int getBigTranType() {
        return vp_a(SputilVari.COUNT_BIGDATA_TRAN_TYPE, 0);
    }

    public int getWatchuiCoustom() {
        return vp_a(SputilVari.COUNT_WATCHUI_COUSTOM, 0);
    }

    public int getWatchuiServer() {
        return vp_a(SputilVari.COUNT_WATCHUI_SERVER, 0);
    }

    public int getNickNameLength() {
        return vp_a(SputilVari.COUNT_NICKNAME_POST_NUMBER, 0);
    }

    public int getOriginProtocolVersion() {
        return vp_a(SputilVari.COUNT_ORIGIN_PROTOCOL_VERSION, 0);
    }

    public int getSportModelDay() {
        return vp_a(SputilVari.COUNT_SPORT_MODEl_DAY, 3);
    }

    public int getCPUType() {
        return vp_a(SputilVari.CPU_TYPE, 0);
    }

    public boolean isJieLiDevice() {
        return getCPUType() == 1;
    }

    public boolean isZKDevice() {
        return getCPUType() == 3 || getCPUType() == 4;
    }

    public boolean isNewNordicDevice() {
        return getCPUType() == 5;
    }

    public boolean isZK569XDevice() {
        return isZKDevice() && getCPUType() == 4;
    }

    public int getECGType() {
        return vp_a(SputilVari.ECG_TYPE, 0);
    }

    public boolean isSupportEcgMultiDiagnoses() {
        switch (getECGType()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
                return false;
            case 6:
            default:
                return true;
        }
    }

    public int getSpo2hType() {
        return vp_a(SputilVari.FUNCTION_SPO2H_TYPE, 0);
    }

    public boolean supportSpo2hAllDay() {
        int iVp_a = vp_a(SputilVari.FUNCTION_SPO2H_TYPE, 0);
        return iVp_a == 6 || iVp_a == 7 || iVp_a == 8 || iVp_a == 9;
    }

    public boolean isSupportFindDevice() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FIND_DEVICE, false);
    }

    public boolean isSupoortAGPS() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_AGSP, false);
    }

    public boolean isSupportFindDeviceByPhone() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FIND_DEVICE_BY_PHONE, false);
    }

    public boolean isSupportCheckWear() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_CHECK_WEAR, false);
    }

    public boolean isSupportLowPower() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_LOW_POWER, false);
    }

    public boolean isSupportBp() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BP, false);
    }

    public boolean isSupportBumpBp() {
        return vp_a(SputilVari.FUNCTION_BP_TAG, 0) == 3 || vp_a(SputilVari.FUNCTION_BP_TAG, 0) == 5;
    }

    public boolean isSupportMetAppDetect() {
        return vp_a(SputilVari.MET_TYPE, 0) == 3;
    }

    public boolean isSupportEmotionAppDetect() {
        return vp_a(SputilVari.TAG_EMOTION, 0) == 1;
    }

    public boolean isSupportEmotion() {
        return vp_a(SputilVari.TAG_EMOTION, 0) != 0;
    }

    public boolean isSupportReadDeviceManual() {
        return getSupportReadDeviceManual().size() > 0;
    }

    public List<DeviceManualDataType> getSupportReadDeviceManual() {
        ArrayList arrayList = new ArrayList();
        if (vp_d()) {
            arrayList.add(DeviceManualDataType.BLOOD_PRESSURE);
        }
        if (vp_h()) {
            arrayList.add(DeviceManualDataType.HEART_RATE);
        }
        if (vp_c()) {
            arrayList.add(DeviceManualDataType.BLOOD_OXYGEN);
        }
        if (vp_b()) {
            arrayList.add(DeviceManualDataType.BLOOD_GLUCOSE);
        }
        if (vp_l()) {
            arrayList.add(DeviceManualDataType.STRESS);
        }
        if (vp_e()) {
            arrayList.add(DeviceManualDataType.BODY_TEMPERATURE);
        }
        if (vp_j()) {
            arrayList.add(DeviceManualDataType.MET);
        }
        if (vp_i()) {
            arrayList.add(DeviceManualDataType.HRV);
        }
        if (vp_a()) {
            arrayList.add(DeviceManualDataType.BLOOD_COMPOSITION);
        }
        if (vp_k()) {
            arrayList.add(DeviceManualDataType.MINI_CHECKUP);
        }
        if (vp_f()) {
            arrayList.add(DeviceManualDataType.EMOTION);
        }
        if (vp_g()) {
            arrayList.add(DeviceManualDataType.FATIGUE);
        }
        if (vp_m()) {
            arrayList.add(DeviceManualDataType.SKIN_CONDUCTANCE);
        }
        return arrayList;
    }

    public boolean isSupportDrink() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_DRINK, false);
    }

    public boolean isSupportLongseat() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_LONGSEAT, false);
    }

    public boolean isSupportHealthRemind() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_HEALTH_REMIND, false);
    }

    public int getSkinType() {
        return vp_a(SputilVari.FUNCTION_SKIN_TYPE, 0);
    }

    public boolean isSupportWechatSport() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_WECHAT_SPORT, false);
    }

    public boolean isSupportCamera() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_CAMERA, false);
    }

    public boolean isSupportFtg() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FTG, false);
    }

    public boolean isSupportSBBR() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SPO2H_BREATHER_BREAK, false);
    }

    public boolean isHaveMusicFunction() {
        if (getMusicType() == 1) {
            return true;
        }
        isHaveMusicControlFunction();
        return true;
    }

    public int getMusicType() {
        return vp_a(SputilVari.COUNT_MUSIC_STYLE_TYPE, 0);
    }

    public boolean isHaveMusicControlFunction() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_MUSIC_CONTROL, false);
    }

    public int getTemperatureType() {
        return vp_a(SputilVari.COUNT_TEMPTURE_STYLE_TYPE, 0);
    }

    public int getWeatherType() {
        return vp_a(SputilVari.COUNT_WEATHER_STYLE_TYPE, 0);
    }

    public boolean isSupportReadTempture() {
        return vp_a(SputilVari.IS_SUPPORT_READ_TEMPTUREDATA, false);
    }

    public boolean isSupportWeather() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_WEATHER, false);
    }

    public boolean isSupportSpoh2NightMonitor() {
        return vp_a("is_have_function_spo2h", false);
    }

    public boolean isSupportECG() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_ECG, false);
    }

    public boolean isSupportMultSportModel() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_MULTSPORTMODEL, false);
    }

    public boolean isSupportHeartwaring() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_HEARTWARING, false);
    }

    public boolean isSupportNightturnSetting() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_NIGHTTURN_SETTING, false);
    }

    public boolean isSupportHRV() {
        if (vp_a(2)) {
            return vp_a(SputilVari.IS_HAVE_FUNCTION_HRV, false);
        }
        return false;
    }

    public boolean isSupportAllDayHRV() {
        if (vp_a(2)) {
            return vp_a(SputilVari.IS_HAVE_FUNCTION_ALL_DAY_HRV, false);
        }
        return false;
    }

    public int getHrvType() {
        return vp_a(SputilVari.HRV_TYPE, 0);
    }

    public boolean isSupportSpo2h() {
        if (vp_a(1)) {
            return vp_a("is_have_function_spo2h", false);
        }
        return false;
    }

    public boolean isSupportAppOpenSport() {
        return vp_a(SputilVari.IS_SUPPORT_DA_SPORT, false);
    }

    public boolean isSupportSpo2hDetectionSwitch() {
        return isSupportSpo2h() && getSpo2hType() != 8;
    }

    public boolean isSupportWomenSetting() {
        if (vp_a(0)) {
            return vp_a(SputilVari.IS_HAVE_FUNCTION_WOMEN_SETTING, false);
        }
        return false;
    }

    public int getWomenTag() {
        return vp_a(SputilVari.WOMEN_TYPE, 0);
    }

    public boolean isSupportScreenlight() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SCREENLIGHT, false);
    }

    public boolean isSupportScreenlightTime() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SCREEN_TIME, false);
    }

    public boolean isSupportCalcStepNew() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_CALC_STEP_NEW, false);
    }

    public boolean isSupportAngioAdjuster() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_ANGIO_ADJUSTER, false);
    }

    public boolean isSupportMultiAlarm() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_MULTI_ALARM, false);
    }

    public boolean isSupportTextAlarm() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_TEXT_ALARM, false);
    }

    public boolean isSupportRate() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_RATE, false);
    }

    public boolean isSupportCheckTemptureByApp() {
        return vp_a(SputilVari.IS_HAVE_TEMPTURE_DETECT_BY_APP, false);
    }

    public boolean isSupportSettingsTemperatureUnit() {
        return vp_a(SputilVari.IS_SUPPORT_SETTING_TEMPERATURE_UNIT, false);
    }

    public boolean isSupportCountdown() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_COUNTDOWN, false);
    }

    public boolean isSupportHid() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_HID, false);
    }

    public boolean isSupportSportModel() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SPORT_MODEL, false);
    }

    public boolean isSupportBloodGlucoseDetect() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE, false);
    }

    public boolean isSupportBloodComponentDetect() {
        return vp_a(SputilVari.IS_HAVE_BLOOD_COMPONENT, false);
    }

    public boolean isSupportFallDetection() {
        return vp_a(SputilVari.FALL_DETECTION, false);
    }

    public boolean isParseSportModel() {
        boolean zIsSupportSportModel = isSupportSportModel();
        int originProtocolVersion = getOriginProtocolVersion();
        return zIsSupportSportModel || (originProtocolVersion == 4 || originProtocolVersion == 5);
    }

    public boolean isSupportScreenStyle() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SCREEN_STYLE, false);
    }

    public boolean isSupportBreath() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BREATH, false);
    }

    public boolean isSupportMetricsystem() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_METRICSYSTEM, false);
    }

    public boolean isSupportFivemiuteHeart() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FIVEMIUTE_HEART, false);
    }

    public boolean isSupportFivemiuteBp() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FIVEMIUTE_BP, false);
    }

    public boolean isSupportSportOverRemain() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SPORT_OVER_REMAIN, false);
    }

    public boolean isSupportAutoIncall() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_AUTOINCALL, false);
    }

    public boolean isOpenAutoIncall() {
        return vp_a(SputilVari.IS_OPEN_AUTOINCALL, false);
    }

    public boolean isOpenDisconnectRemind() {
        return vp_a(SputilVari.IS_OPEN_DISCONNECT_REMIND, false);
    }

    public boolean isOpenTemperatureDetectByApp() {
        return vp_a(SputilVari.IS_OPEN_AUTO_TEMPERATURE_DETECT, false);
    }

    public boolean isOpenBloodGlucoseDetectSwitch() {
        return vp_a(SputilVari.BLOOD_GLUCOSE_DETECTION, false);
    }

    public boolean isOpenMetDetectSwitch() {
        return vp_a(SputilVari.MET_DETECTION, false);
    }

    public boolean isSupportDisconnectRemind() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_DISCONNECT_REMIND, false);
    }

    public boolean isSupportVoiceBpHeart() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_VOICE_BP_HEART, false);
    }

    public boolean isSupportFindPhoneUi() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_FIND_PHONE_UI, false);
    }

    public boolean isSupportSecondsWatch() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SECONDS_WATCH, false);
    }

    public boolean isSupportSpoh2LowRemain() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SPOH2_LOW_REMAIN, false);
    }

    public boolean isSupportSkin() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SKIN, false);
    }

    public boolean isSupportScreenStyle_type() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SCREEN_STYLE, false);
    }

    public boolean isSupportPreciseSleep() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_PRECISE_SLEEP, false);
    }

    public boolean isSupportBTFunction() {
        return vp_a(SputilVari.IS_HAVE_BT_FUNCTION, false);
    }

    public boolean isDetectingHeart() {
        return vp_a(SputilVari.IS_DETECTING_RATE, false);
    }

    public boolean isDetectingBp() {
        return vp_a(SputilVari.IS_DETECTING_BP, false);
    }

    public boolean isDetectingSpo2h() {
        return vp_a(SputilVari.IS_DETECTING_SPO2H, false);
    }

    public boolean isDetectingFtg() {
        return vp_a(SputilVari.IS_DETECTING_FTG, false);
    }

    public boolean isSupportBloodGlucose() {
        return vp_a(SputilVari.IS_HAVE_FUCTION_BLOOD_GLUCOSE, false);
    }

    public boolean isSupportBloodGlucoseAdjusting() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_ADJUSTING, false);
    }

    public boolean isSupportBloodGlucoseMultipleAdjusting() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_MULTIPLE_ADJUSTING, false);
    }

    public boolean isSupportBloodGlucoseRiskAssessment() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_RISK_ASSESSMENT, false);
    }

    public int getBloodGlucoseTag() {
        return vp_a(SputilVari.BLOOD_GLOCOSE_TAG, 0);
    }

    public int getBloodPressureTag() {
        return vp_a(SputilVari.FUNCTION_BP_TAG, 0);
    }

    public boolean isSupportMet() {
        return vp_a(SputilVari.IS_HAVE_FUCTION_MET, false);
    }

    public int getMetType() {
        return vp_a(SputilVari.MET_TYPE, 0);
    }

    public int getEmotionType() {
        return vp_a(SputilVari.TAG_EMOTION, 0);
    }

    public int getFatigueType() {
        return vp_a(SputilVari.TAG_FATIGUE, 0);
    }

    public boolean isSupportFtgAppDetect() {
        return vp_a(SputilVari.TAG_FATIGUE, 0) != 0;
    }

    public boolean isSupportStress() {
        return vp_a(SputilVari.IS_HAVE_FUCTION_STRESS, false);
    }

    public boolean isSupportHrvAppDetect() {
        return vp_a(SputilVari.HRV_TYPE, 0) == 7 || vp_a(SputilVari.HRV_TYPE, 0) == 8;
    }

    public int getStressType() {
        return vp_a(SputilVari.STRESS_TYPE, 0);
    }

    public boolean isDetectingBreath() {
        return vp_a(SputilVari.IS_DETECTING_BEATH, false);
    }

    public boolean isPasswordConfirmBack() {
        return vp_a(SputilVari.IS_PWD_CONFIRM_BACK, false);
    }

    public boolean isSupportSp02hRemain() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_SPO2H_NOT_SUPPORT_REMAIN, false);
    }

    public ETemperatureUnit getTemperatureUnit() {
        int iVp_a = vp_a(SputilVari.TEMPERATURE_UNIT, 0);
        if (iVp_a == 1) {
            return ETemperatureUnit.CELSIUS;
        }
        return iVp_a == 2 ? ETemperatureUnit.FAHRENHEIT : ETemperatureUnit.NONE;
    }

    public int getMtuValue() {
        return vp_a(SputilVari.DEVICE_MTU_VALUE, -1);
    }

    public int getContactType() {
        return vp_a(SputilVari.CONTACT_TYPE, 0);
    }

    public boolean isSupportSOSContactFunction() {
        return isSupportContactFunction() && (getContactType() == 2 || getContactType() == 3);
    }

    public int getMaxContactLimit() {
        return getContactType() == 3 ? 20 : 10;
    }

    public boolean isSupportContactFunction() {
        return vp_a(SputilVari.IS_HAVE_CONTACT_FUNCTION, false);
    }

    public boolean isHeartRateDetectOpen() {
        return vp_a(SputilVari.IS_OPEN_FIVEMIUTE_HEART, false);
    }

    public boolean isSupportBloodComponent() {
        return vp_a(SputilVari.IS_HAVE_BLOOD_COMPONENT, false);
    }

    public boolean isSupportBloodComponentSingleCalibration() {
        return vp_a(SputilVari.IS_HAVE_BLOOD_COMPONENT_SINGLE_CALIBRATION, false);
    }

    public boolean isSupportBodyComponent() {
        return vp_a(SputilVari.IS_HAVE_BODY_COMPONENT, false);
    }

    public boolean isSupportPPG() {
        return vp_a(SputilVari.IS_HAVE_FUNCTION_PPG, false);
    }

    public boolean isSupportWorldClock() {
        return vp_a(SputilVari.IS_HAVE_WORLD_CLOCK, false);
    }

    public boolean isSupportAutoMeasure() {
        return vp_a(SputilVari.IS_SUPPORT_AUTO_MEASURE, false);
    }

    public boolean isSupportEcgMultiLead() {
        return vp_a(SputilVari.ECG_TYPE, 1) == 7 || vp_a(SputilVari.ECG_TYPE, 1) == 10 || vp_a(SputilVari.ECG_TYPE, 1) == 12 || vp_a(SputilVari.ECG_TYPE, 1) == 15;
    }

    public boolean isSupportPttControl() {
        return vp_a(SputilVari.TAG_PTT_CONTROL, 0) == 1;
    }

    public boolean isSupportAiQa() {
        return vp_a(SputilVari.AI_QA_TYPE, 0) != 0;
    }

    public boolean isSupportHealthAssessment() {
        return vp_a(SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT, false);
    }

    public boolean isSupportGSR() {
        return vp_a(SputilVari.IS_SUPPORT_GSR, false);
    }

    public boolean isSupportSafetyProtection() {
        return vp_a(SputilVari.IS_SUPPORT_SAFETY_PROTECTION, false);
    }

    public EScreenType getScreenType() {
        return EScreenType.INSTANCE.getEScreenType(vp_a(SputilVari.KEY_SCREEN_TYPE, 0));
    }

    public boolean isSupportBarometer() {
        return vp_a(SputilVari.IS_SUPPORT_BAROMETER, false);
    }

    public boolean isSupportPTTControl() {
        return vp_a(SputilVari.IS_SUPPORT_PTT_CONTROL, false);
    }

    public boolean isSupportSportStatus() {
        return vp_a(SputilVari.IS_SUPPORT_SPORT_STATUS, false);
    }

    public boolean isSupportRemindEvent() {
        return vp_a(SputilVari.IS_SUPPORT_REMIND_EVENT, false);
    }

    public boolean isSupportBloodGlucoseHealthAssessment() {
        return vp_a(SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BLOOD_GLUCOSE, false);
    }

    public boolean isSupportBloodComponentHealthAssessment() {
        return vp_a(SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BLOOD_COMPONENT, false);
    }

    public boolean isSupportBodyComponentHealthAssessment() {
        return vp_a(SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BODY_COMPONENT, false);
    }

    public boolean isSupportAi() {
        return isSupportAiQa() || isSupportAiDial();
    }

    public boolean isAIQAUseChinaSDK() {
        return vp_a(SputilVari.AI_QA_TYPE, 0) == 2;
    }

    public boolean isAIQAUseAbroadSDK() {
        return vp_a(SputilVari.AI_QA_TYPE, 0) == 1;
    }

    public boolean isSupportAiDial() {
        return vp_a(SputilVari.AI_DIAL_TYPE, 0) != 0;
    }

    public boolean isAIDialUseChinaSDK() {
        return vp_a(SputilVari.AI_DIAL_TYPE, 0) == 2;
    }

    public boolean isAIDialUseAbroadSDK() {
        return vp_a(SputilVari.AI_DIAL_TYPE, 0) == 1;
    }

    public boolean isSupport4G() {
        return vp_a(SputilVari.IS_SUPPORT_SERVER_4G, false);
    }

    public boolean isSupportMiniCheckup() {
        return vp_a(SputilVari.IS_SUPPORT_MINI_CHECKUP, false);
    }

    public int getMiniCheckupType() {
        return vp_a(SputilVari.MINI_CHECKUP_TYPE, 0);
    }

    public boolean isSupportGameClock() {
        return vp_a(SputilVari.IS_SUPPORT_GAME_CLOCK, false);
    }

    public boolean isSupportTextImagePush() {
        return vp_a(SputilVari.IS_SUPPORT_TEXT_IMAGE_PUSH, false);
    }

    public boolean isSupportHealthLight() {
        return vp_a(SputilVari.IS_SUPPORT_HEALTH_LIGHT, false);
    }

    public boolean isSupportMotor() {
        return vp_a(SputilVari.IS_SUPPORT_MOTOR, false);
    }

    public int getMotorTag() {
        return vp_a(SputilVari.TAG_MOTOR, 0);
    }

    public boolean isSupportWearHand() {
        return vp_a(SputilVari.IS_SUPPORT_WEAR_HAND, false);
    }

    public int getWearHandTag() {
        return vp_a(SputilVari.TAG_WEAR_HAND, 0);
    }

    public String traversalShareperence() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        new SputilVari();
        for (Field field : SputilVari.class.getFields()) {
            String name = field.getName();
            String strValueOf = String.valueOf(field.get(name));
            if (name.contains("IS")) {
                str = strValueOf + "-->" + vp_a(strValueOf, false);
            } else if (name.contains("COUNT")) {
                str = strValueOf + "-->" + vp_a(strValueOf, -1);
            } else {
                str = name.contains("INFO") ? strValueOf + "-->" + vp_a(strValueOf, "null") : name + "--> invailt name";
            }
            if (!name.equals("INFO_UPDATEUSE_SDKINFO")) {
                stringBuffer.append(str);
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private int vp_a(String str, int i) {
        ?? r0 = -2;
        ?? r5 = -2;
        try {
            r0 = SpUtil.getInt(vp_b, str, i);
            r5 = r0;
        } catch (Exception unused) {
            System.out.println("sp int->" + str);
            r0.printStackTrace();
        }
        return r5 == true ? 1 : 0;
    }

    boolean vp_a(int i) {
        return ((15 >> i) & 1) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private String vp_a(String str, String str2) {
        ?? string = "none-null";
        ?? r5 = "none-null";
        try {
            string = SpUtil.getString(vp_b, str, str2);
            r5 = string;
        } catch (Exception unused) {
            System.out.println("sp string->" + str);
            string.printStackTrace();
        }
        return r5;
    }

    private boolean vp_a() {
        return vp_a(SputilVari.FUNCTION_BLOOD_COMPONENT_TAG, 0) == 3;
    }
}
