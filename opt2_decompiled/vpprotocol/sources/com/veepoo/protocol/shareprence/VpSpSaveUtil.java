package com.veepoo.protocol.shareprence;

import android.content.Context;
import android.text.TextUtils;
import com.veepoo.protocol.model.datas.FunctionDeviceSupportData;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.settings.CustomSettingData;
import com.veepoo.protocol.util.vp_j;
import java.util.Objects;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/shareprence/VpSpSaveUtil.class */
public class VpSpSaveUtil {
    private static volatile VpSpSaveUtil vp_a;
    private static Context vp_b;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/shareprence/VpSpSaveUtil$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EFunctionStatus.values().length];
            vp_a = iArr;
            try {
                iArr[EFunctionStatus.SUPPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EFunctionStatus.SUPPORT_CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EFunctionStatus.SUPPORT_OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[EFunctionStatus.UNSUPPORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.shareprence.VpSpSaveUtil>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static VpSpSaveUtil getVpSpVariInstance(Context context) {
        if (vp_a == null) {
            ?? r0 = VpSpSaveUtil.class;
            synchronized (r0) {
                try {
                    if (vp_a == null) {
                        vp_b = context.getApplicationContext();
                        vp_a = new VpSpSaveUtil();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_a;
    }

    private VpSpSaveUtil() {
    }

    private void vp_a() {
        vp_a(false, SputilVari.IS_DETECTING_RATE);
        vp_a(false, SputilVari.IS_DETECTING_BP);
        vp_a(false, SputilVari.IS_DETECTING_SPO2H);
        vp_a(false, SputilVari.IS_DETECTING_FTG);
        vp_a(false, SputilVari.IS_DETECTING_BEATH);
    }

    private void vp_b(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SpUtil.saveInt(vp_b, str, i);
    }

    public void saveUseDateInfo() {
        vp_b(vp_j.vp_c(), SputilVari.INFO_USE_DATEINFO);
    }

    public void saveUpdateInfo(String str, String str2) {
        vp_b(str2, "info_update_dateinfo_" + str);
    }

    public void saveSdkUpateInfo(String str) {
        vp_b(str, SputilVari.INFO_UPDATEUSE_SDKINFO);
    }

    public void saveSdkVersionInfo(String str) {
        vp_a(str, SputilVari.INFO_SDK_PROTOCOL_VERSION);
    }

    public void saveAppInfo(String str, String str2, String str3) {
        vp_a(str, SputilVari.INFO_APP_PACKAGE_NAME);
        vp_a(str2, SputilVari.INFO_APP_NAME);
        vp_a(str3, SputilVari.INFO_APP_VERSION);
    }

    public void savePhoneInfo(String str, String str2, String str3) {
        vp_a(str, SputilVari.INFO_PHONE_MODEL);
        vp_a(str2, SputilVari.INFO_PHONE_VERSION);
        vp_a(str3, SputilVari.INFO_PHONE_GOOGLE_API_LEVEL);
    }

    public void saveDeviceBaseInfo(String str, String str2, boolean z, String str3, String str4) {
        vp_a(z, SputilVari.IS_OAD_MODEL);
        vp_b(str, SputilVari.INFO_DEVICE_MAC);
        vp_b(str2, SputilVari.INFO_DEVICE_NAME);
        vp_b(str3, SputilVari.INFO_DEVICE_CPU);
        vp_b(str4, SputilVari.INFO_DEVICE_CPU_PLATFORM);
    }

    public void saveDeviceName(String str) {
        vp_b(str, SputilVari.INFO_DEVICE_NAME);
    }

    public void saveDevicePwd(String str) {
        vp_b(str, SputilVari.INFO_DEVICE_PWD);
    }

    public void saveDeviceFunctionA7(FunctionDeviceSupportData functionDeviceSupportData) {
        if (functionDeviceSupportData == null) {
            return;
        }
        vp_a(functionDeviceSupportData.getBp(), SputilVari.IS_HAVE_FUNCTION_BP);
        vp_b(functionDeviceSupportData.getBpTag(), SputilVari.FUNCTION_BP_TAG);
        vp_a(functionDeviceSupportData.getDrink(), SputilVari.IS_HAVE_FUNCTION_DRINK);
        vp_a(functionDeviceSupportData.getLongseat(), SputilVari.IS_HAVE_FUNCTION_LONGSEAT);
        vp_a(functionDeviceSupportData.getHealthRemind(), SputilVari.IS_HAVE_FUNCTION_HEALTH_REMIND);
        vp_b(functionDeviceSupportData.getSkinType(), SputilVari.FUNCTION_SKIN_TYPE);
        vp_a(functionDeviceSupportData.getWeChatSport(), SputilVari.IS_HAVE_FUNCTION_WECHAT_SPORT);
        vp_a(functionDeviceSupportData.getCamera(), SputilVari.IS_HAVE_FUNCTION_CAMERA);
        vp_a(functionDeviceSupportData.getFatigue(), SputilVari.IS_HAVE_FUNCTION_FTG);
        vp_b(functionDeviceSupportData.getFatigueTag(), SputilVari.TAG_FATIGUE);
        vp_a(functionDeviceSupportData.getSpo2H(), "is_have_function_spo2h");
        vp_a(functionDeviceSupportData.getSpo2HAdjuster(), SputilVari.IS_HAVE_FUNCTION_SPO2H_ADJUSTER);
        vp_a(functionDeviceSupportData.getSpo2hShow(), SputilVari.IS_HAVE_FUCTION_SPO2H_SHOW);
        vp_a(functionDeviceSupportData.getSpo2HValueZero(), SputilVari.IS_HAVE_FUNCTION_SPO2H_ZERO);
        vp_b(functionDeviceSupportData.getSpo2hType(), SputilVari.FUNCTION_SPO2H_TYPE);
        vp_a(functionDeviceSupportData.getSpo2HBreathBreak(), SputilVari.IS_HAVE_FUNCTION_SPO2H_BREATHER_BREAK);
        vp_b(functionDeviceSupportData.getAllMsgLength(), SputilVari.COUNT_MESSAGE_POST_NUMBER);
        vp_a(functionDeviceSupportData.getHeartWaring(), SputilVari.IS_HAVE_FUNCTION_HEARTWARING);
        vp_a(functionDeviceSupportData.getNightTurnSetting(), SputilVari.IS_HAVE_FUNCTION_NIGHTTURN_SETTING);
        vp_a(functionDeviceSupportData.getWomen(), SputilVari.IS_HAVE_FUNCTION_WOMEN_SETTING);
        vp_b(functionDeviceSupportData.getWomenTag(), SputilVari.WOMEN_TYPE);
        vp_a(functionDeviceSupportData.getScreenLight(), SputilVari.IS_HAVE_FUNCTION_SCREENLIGHT);
        vp_a(functionDeviceSupportData.getNewCalcSport(), SputilVari.IS_HAVE_FUNCTION_CALC_STEP_NEW);
        vp_a(functionDeviceSupportData.getAngioAdjuster(), SputilVari.IS_HAVE_FUNCTION_ANGIO_ADJUSTER);
        vp_a(functionDeviceSupportData.getAlarm2(), SputilVari.IS_HAVE_FUNCTION_MULTI_ALARM);
        vp_a(functionDeviceSupportData.getTextAlarm(), SputilVari.IS_HAVE_FUNCTION_TEXT_ALARM);
        vp_b(functionDeviceSupportData.getAlarmTag(), SputilVari.ALARM_TYPE);
        vp_a(functionDeviceSupportData.getHeartDetect(), SputilVari.IS_HAVE_FUNCTION_RATE);
        vp_b(functionDeviceSupportData.getRateTag(), SputilVari.FUNCTION_RATE_TAG);
        vp_a(functionDeviceSupportData.getCountDown(), SputilVari.IS_HAVE_FUNCTION_COUNTDOWN);
        vp_b(functionDeviceSupportData.getWathcDay(), SputilVari.COUNT_DATA_DAY);
        vp_b(functionDeviceSupportData.getContactMsgLength(), SputilVari.COUNT_NICKNAME_POST_NUMBER);
        vp_a(functionDeviceSupportData.getHidFuction(), SputilVari.IS_HAVE_FUNCTION_HID);
        vp_a(functionDeviceSupportData.getSportModel(), SputilVari.IS_HAVE_FUNCTION_SPORT_MODEL);
        vp_b(functionDeviceSupportData.getSportmodelday(), SputilVari.COUNT_SPORT_MODEl_DAY);
        vp_a(functionDeviceSupportData.getScreenStyleFunction(), SputilVari.IS_HAVE_FUNCTION_SCREEN_STYLE);
        vp_b(functionDeviceSupportData.getScreenstyle(), SputilVari.COUNT_SCREEN_STYLE_TYPE);
        vp_b(functionDeviceSupportData.getOriginProtcolVersion(), SputilVari.COUNT_ORIGIN_PROTOCOL_VERSION);
        vp_a(functionDeviceSupportData.getBeathFunction(), SputilVari.IS_HAVE_FUNCTION_BREATH);
        vp_a(functionDeviceSupportData.getHrvFunction(), SputilVari.IS_HAVE_FUNCTION_HRV);
        vp_a(functionDeviceSupportData.getAllDayHrvFunc(), SputilVari.IS_HAVE_FUNCTION_ALL_DAY_HRV);
        vp_b(functionDeviceSupportData.getHrvType(), SputilVari.HRV_TYPE);
        vp_a(functionDeviceSupportData.getWeatherFunction(), SputilVari.IS_HAVE_FUNCTION_WEATHER);
        vp_b(functionDeviceSupportData.getWeatherStyle(), SputilVari.COUNT_WEATHER_STYLE_TYPE);
        vp_a(functionDeviceSupportData.getScreenLightTime(), SputilVari.IS_HAVE_FUNCTION_SCREEN_TIME);
        vp_a(functionDeviceSupportData.getPrecisionSleep(), SputilVari.IS_HAVE_FUNCTION_PRECISE_SLEEP);
        vp_b(functionDeviceSupportData.getSleepTag(), SputilVari.FUNCTION_SLEEP_TAG);
        vp_a(functionDeviceSupportData.getResetData(), SputilVari.IS_HAVE_FUNCTION_RESET);
        vp_a(functionDeviceSupportData.getEcg(), SputilVari.IS_HAVE_FUNCTION_ECG);
        vp_a(functionDeviceSupportData.getMultSportModel(), SputilVari.IS_HAVE_FUNCTION_MULTSPORTMODEL);
        vp_b(functionDeviceSupportData.getSportModelType(), SputilVari.MULTSPORTMODEL_TYPE);
        vp_a(functionDeviceSupportData.getLowPower(), SputilVari.IS_HAVE_FUNCTION_LOW_POWER);
        vp_b(functionDeviceSupportData.getEcgType(), SputilVari.ECG_TYPE);
        vp_b(functionDeviceSupportData.getBigDataTranType(), SputilVari.COUNT_BIGDATA_TRAN_TYPE);
        vp_b(functionDeviceSupportData.getWatchUiCoustomCount(), SputilVari.COUNT_WATCHUI_COUSTOM);
        vp_b(functionDeviceSupportData.getContactType(), SputilVari.CONTACT_TYPE);
        vp_a(functionDeviceSupportData.getContactFunction(), SputilVari.IS_HAVE_CONTACT_FUNCTION);
        vp_b(functionDeviceSupportData.getWatchUiServerCount(), SputilVari.COUNT_WATCHUI_SERVER);
        vp_b(functionDeviceSupportData.getMusicStyle(), SputilVari.COUNT_MUSIC_STYLE_TYPE);
        int temptureType = functionDeviceSupportData.getTemptureType();
        vp_b(temptureType, SputilVari.COUNT_TEMPTURE_STYLE_TYPE);
        if (temptureType == 0) {
            vp_a(EFunctionStatus.UNSUPPORT, SputilVari.IS_HAVE_TEMPTURE_DETECT_BY_APP);
        } else {
            vp_a(EFunctionStatus.SUPPORT, SputilVari.IS_HAVE_TEMPTURE_DETECT_BY_APP);
        }
        if (temptureType == 0) {
            vp_a(EFunctionStatus.UNSUPPORT, SputilVari.IS_SUPPORT_READ_TEMPTUREDATA);
        } else {
            vp_a(EFunctionStatus.SUPPORT, SputilVari.IS_SUPPORT_READ_TEMPTUREDATA);
        }
        if (temptureType != 0) {
            vp_a(EFunctionStatus.SUPPORT, SputilVari.IS_SUPPORT_SETTING_TEMPERATURE_UNIT);
        } else {
            vp_a(EFunctionStatus.UNSUPPORT, SputilVari.IS_SUPPORT_SETTING_TEMPERATURE_UNIT);
        }
        vp_a(functionDeviceSupportData.getFindDeviceByPhone(), SputilVari.IS_HAVE_FUNCTION_FIND_DEVICE_BY_PHONE);
        vp_a(functionDeviceSupportData.getAgps(), SputilVari.IS_HAVE_FUNCTION_AGSP);
        vp_a(functionDeviceSupportData.getBloodGlucose(), SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE);
        vp_a(functionDeviceSupportData.getBloodGlucoseAdjusting(), SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_ADJUSTING);
        vp_a(functionDeviceSupportData.getBloodGlucoseMultipleAdjusting(), SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_MULTIPLE_ADJUSTING);
        vp_a(functionDeviceSupportData.getBloodGlucoseRiskAssessment(), SputilVari.IS_HAVE_FUNCTION_BLOOD_GLUCOSE_RISK_ASSESSMENT);
        vp_b(functionDeviceSupportData.getBloodGlucoseTag(), SputilVari.BLOOD_GLOCOSE_TAG);
        vp_b(functionDeviceSupportData.getCpuType(), SputilVari.CPU_TYPE);
        vp_a(functionDeviceSupportData.getMet(), SputilVari.IS_HAVE_FUCTION_MET);
        vp_b(functionDeviceSupportData.getMetType(), SputilVari.MET_TYPE);
        vp_a(functionDeviceSupportData.getStress(), SputilVari.IS_HAVE_FUCTION_STRESS);
        vp_b(functionDeviceSupportData.getStressType(), SputilVari.STRESS_TYPE);
        vp_a(functionDeviceSupportData.getBloodComponent(), SputilVari.IS_HAVE_BLOOD_COMPONENT);
        vp_a(functionDeviceSupportData.getBloodComponentSingleCalibration(), SputilVari.IS_HAVE_BLOOD_COMPONENT_SINGLE_CALIBRATION);
        vp_b(functionDeviceSupportData.getBloodComponentTag(), SputilVari.FUNCTION_BLOOD_COMPONENT_TAG);
        vp_a(functionDeviceSupportData.getBodyComponent(), SputilVari.IS_HAVE_BODY_COMPONENT);
        vp_a(functionDeviceSupportData.getWorldClock(), SputilVari.IS_HAVE_WORLD_CLOCK);
        vp_a(functionDeviceSupportData.getGameSetting(), SputilVari.IS_SUPPORT_GAME_CLOCK);
        vp_a(functionDeviceSupportData.getAutoMeasure(), SputilVari.IS_SUPPORT_AUTO_MEASURE);
        vp_a(functionDeviceSupportData.getAiQA(), SputilVari.IS_SUPPORT_AI_QA);
        vp_b(functionDeviceSupportData.getAiQaTag(), SputilVari.AI_QA_TYPE);
        vp_a(functionDeviceSupportData.getAiDial(), SputilVari.IS_SUPPORT_AI_DIAL);
        vp_b(functionDeviceSupportData.getAiDialTag(), SputilVari.AI_DIAL_TYPE);
        vp_a(functionDeviceSupportData.getServer4g(), SputilVari.IS_SUPPORT_SERVER_4G);
        vp_a(functionDeviceSupportData.getHealthAssessment(), SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT);
        vp_a(functionDeviceSupportData.getMiniCheckup(), SputilVari.IS_SUPPORT_MINI_CHECKUP);
        vp_a(functionDeviceSupportData.getDaSport(), SputilVari.IS_SUPPORT_DA_SPORT);
        vp_b(functionDeviceSupportData.getMiniCheckupTag(), SputilVari.MINI_CHECKUP_TYPE);
        vp_b(functionDeviceSupportData.getHealthAssessmentTag(), SputilVari.HEALTH_ASSESSMENT_TYPE);
        vp_a(functionDeviceSupportData.getBloodGlucoseHealthAssessment(), SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BLOOD_GLUCOSE);
        vp_a(functionDeviceSupportData.getBloodComponentHealthAssessment(), SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BLOOD_COMPONENT);
        vp_a(functionDeviceSupportData.getBodyComponentHealthAssessment(), SputilVari.IS_SUPPORT_HEALTH_ASSESSMENT_BODY_COMPONENT);
        vp_a(functionDeviceSupportData.getMiniCheckup(), SputilVari.IS_SUPPORT_MINI_CHECKUP);
        vp_a(functionDeviceSupportData.getTextImagePush(), SputilVari.IS_SUPPORT_TEXT_IMAGE_PUSH);
        vp_a(functionDeviceSupportData.getGSR(), SputilVari.IS_SUPPORT_GSR);
        vp_b(functionDeviceSupportData.getGsrTag(), SputilVari.TAG_GSR);
        vp_a(functionDeviceSupportData.getSafetyProtection(), SputilVari.IS_SUPPORT_SAFETY_PROTECTION);
        vp_b(functionDeviceSupportData.getScreenType(), SputilVari.KEY_SCREEN_TYPE);
        vp_a(functionDeviceSupportData.getBarometer(), SputilVari.IS_SUPPORT_BAROMETER);
        vp_a(functionDeviceSupportData.getPttControl(), SputilVari.IS_SUPPORT_PTT_CONTROL);
        vp_a(functionDeviceSupportData.getSportStatus(), SputilVari.IS_SUPPORT_SPORT_STATUS);
        vp_a(functionDeviceSupportData.getRemindEvent(), SputilVari.IS_SUPPORT_REMIND_EVENT);
        vp_a(functionDeviceSupportData.getHealthLight(), SputilVari.IS_SUPPORT_HEALTH_LIGHT);
        vp_a(functionDeviceSupportData.getMotor(), SputilVari.IS_SUPPORT_MOTOR);
        vp_a(functionDeviceSupportData.getWearHand(), SputilVari.IS_SUPPORT_WEAR_HAND);
        vp_b(functionDeviceSupportData.getPttControlTag(), SputilVari.TAG_PTT_CONTROL);
        vp_b(functionDeviceSupportData.getScreenTypeTag(), SputilVari.TAG_SCREEN_TYPE);
        vp_b(functionDeviceSupportData.getBarometerTag(), SputilVari.TAG_BAROMETER);
        vp_b(functionDeviceSupportData.getTouchICTypeTag(), SputilVari.TAG_TOUCH_IC_TYPE);
        vp_b(functionDeviceSupportData.getNewProductionProtocolTag(), SputilVari.TAG_NEW_PRODUCTION_PROTOCOL);
        vp_b(functionDeviceSupportData.getSportsStatusFeatureTag(), SputilVari.TAG_SPORTS_STATUS_FEATURE);
        vp_b(functionDeviceSupportData.getRemindEventFeatureTag(), SputilVari.TAG_REMIND_EVENT_FEATURE);
        vp_b(functionDeviceSupportData.getEmotionTag(), SputilVari.TAG_EMOTION);
        vp_b(functionDeviceSupportData.getHealthLightTag(), SputilVari.TAG_HEALTH_LIGHT);
        vp_b(functionDeviceSupportData.getMotorTag(), SputilVari.TAG_MOTOR);
        vp_b(functionDeviceSupportData.getWearHandTag(), SputilVari.TAG_WEAR_HAND);
    }

    public void saveDeviceFunctionA1(PwdData pwdData) {
        if (pwdData == null) {
            return;
        }
        vp_a(pwdData.getDeviceTestVersion(), SputilVari.INFO_DEVICE_VERSION_TEST);
        vp_a(pwdData.getDeviceVersion(), SputilVari.INFO_DEVICE_VERSION_RELEASE);
        vp_a(pwdData.getDeviceNumber() + "", SputilVari.INFO_DEVICE_NUMBER);
        vp_a(pwdData.isHaveDrinkData(), SputilVari.IS_HAVE_DRINK_DATA);
        vp_a(pwdData.isOpenNightTurnWriste(), SputilVari.IS_OPEN_NIGHT_TRUN_WRIST, SputilVari.IS_OPEN_NIGHTTURN_SETTING);
        vp_a(pwdData.getFindPhoneFunction(), SputilVari.IS_HAVE_FUNCTION_FIND_DEVICE, SputilVari.IS_OPEN_FIND_DEVICE);
        vp_a(pwdData.getWearDetectFunction(), SputilVari.IS_HAVE_FUNCTION_CHECK_WEAR, SputilVari.IS_OPEN_CHECK_WEAR);
    }

    public void saveDeviceFunctionB8(CustomSettingData customSettingData) {
        if (customSettingData == null) {
            return;
        }
        vp_a(customSettingData.getMetricSystem(), SputilVari.IS_HAVE_FUNCTION_METRICSYSTEM, SputilVari.IS_METRICSYSTEM);
        vp_a(customSettingData.is24Hour(), SputilVari.IS_STATE_HOUR_FORMAT_24);
        vp_a(customSettingData.getAutoHeartDetect(), SputilVari.IS_HAVE_FUNCTION_FIVEMIUTE_HEART, SputilVari.IS_OPEN_FIVEMIUTE_HEART);
        vp_a(customSettingData.getAutoBpDetect(), SputilVari.IS_HAVE_FUNCTION_FIVEMIUTE_BP, SputilVari.IS_OPEN_FIVEMINUTE_BP);
        vp_a(customSettingData.getSportOverRemain(), SputilVari.IS_HAVE_FUNCTION_SPORT_OVER_REMAIN, SputilVari.IS_OPEN_SPORT_OVER_REMAIN);
        vp_a(customSettingData.getVoiceBpHeart(), SputilVari.IS_HAVE_FUNCTION_VOICE_BP_HEART, SputilVari.IS_OPEN_VOICE_BP_HEART);
        vp_a(customSettingData.getFindPhoneUi(), SputilVari.IS_HAVE_FUNCTION_FIND_PHONE_UI, SputilVari.IS_OPEN_FIND_PHONE_UI);
        vp_a(customSettingData.getSecondsWatch(), SputilVari.IS_HAVE_FUNCTION_SECONDS_WATCH, SputilVari.IS_OPEN_SECONDS_WATCH);
        if (VpSpGetUtil.getVpSpVariInstance(vp_b).isSupportSpoh2LowRemain()) {
            vp_a(customSettingData.getLowSpo2hRemain(), SputilVari.IS_HAVE_FUNCTION_SPOH2_LOW_REMAIN, SputilVari.IS_OPEN_SPOH2_LOW_REMAIN);
        } else {
            vp_a(EFunctionStatus.UNSUPPORT, SputilVari.IS_HAVE_FUNCTION_SPOH2_LOW_REMAIN, SputilVari.IS_OPEN_SPOH2_LOW_REMAIN);
        }
        vp_a(customSettingData.getSkin(), SputilVari.IS_HAVE_FUNCTION_SKIN);
        vp_a(customSettingData.getSkinLevel(), SputilVari.COUNT_SKIN_LEVEL);
        vp_a(customSettingData.getAutoHrv(), SputilVari.IS_HAVE_FUNCTION_AUTO_HRV, SputilVari.IS_OPEN_AUTO_HRV);
        vp_a(customSettingData.getAutoIncall(), SputilVari.IS_HAVE_FUNCTION_AUTOINCALL, SputilVari.IS_OPEN_AUTOINCALL);
        vp_a(customSettingData.getDisconnectRemind(), SputilVari.IS_HAVE_FUNCTION_DISCONNECT_REMIND, SputilVari.IS_OPEN_DISCONNECT_REMIND);
        vp_a(customSettingData.getSOS(), SputilVari.IS_HAVE_FUNCTION_SOS, SputilVari.IS_OPEN_SOS);
        vp_a(customSettingData.getPpg(), SputilVari.IS_HAVE_FUNCTION_PPG, SputilVari.IS_OPEN_PPG);
        vp_a(customSettingData.getMusicControl(), SputilVari.IS_HAVE_FUNCTION_MUSIC_CONTROL, SputilVari.IS_OPEN_MUSIC_CONTROL);
        vp_a(customSettingData.getAutoTemperatureDetect(), SputilVari.IS_HAVE_FUNCTION_TEMPERATURE_DETECT, SputilVari.IS_OPEN_AUTO_TEMPERATURE_DETECT);
        vp_a(customSettingData.getTemperatureUnit().getValue(), SputilVari.TEMPERATURE_UNIT);
        vp_a(customSettingData.getEcgAlwaysOpen(), SputilVari.ECG_ALWAYS_OPEN);
        vp_a(customSettingData.getBloodGlucoseDetection(), SputilVari.BLOOD_GLUCOSE_DETECTION);
        vp_a(customSettingData.getMETDetect(), SputilVari.MET_DETECTION);
        vp_a(customSettingData.getBloodGlucoseUnit().getValue(), SputilVari.BLOOD_GLUCOSE_UNIT);
        vp_a(customSettingData.getBloodComponentDetect(), SputilVari.BLOOD_COMPONENT_DETECTION);
        vp_a(customSettingData.getUricAcidUnit().getValue(), SputilVari.URIC_ACID_UNIT);
        vp_a(customSettingData.getBloodFatUnit().getValue(), SputilVari.BLOOD_FAT_UNIT);
        vp_a(customSettingData.getFallDetection(), SputilVari.FALL_DETECTION);
        vp_a(customSettingData.getGsrUnit().getValue(), SputilVari.GSR_UNIT);
    }

    public void saveDeviceFunctionAD1(FunctionSocailMsgData functionSocailMsgData) {
        if (functionSocailMsgData == null) {
            return;
        }
        vp_a(functionSocailMsgData.getPhone(), SputilVari.IS_MSG_OPEN_PHONECALL);
        vp_a(functionSocailMsgData.getMsg(), SputilVari.IS_MSG_OPEN_PHONENOTE);
        vp_a(functionSocailMsgData.getWechat(), SputilVari.IS_MSG_SUPPORT_WECHAT, SputilVari.IS_MSG_OPEN_WECHAT);
        vp_a(functionSocailMsgData.getQq(), SputilVari.IS_MSG_SUPPORT_QQ, SputilVari.IS_MSG_OPEN_QQ);
        vp_a(functionSocailMsgData.getSina(), SputilVari.IS_MSG_SUPPORT_SINA, SputilVari.IS_MSG_OPEN_SINA);
        vp_a(functionSocailMsgData.getFacebook(), SputilVari.IS_MSG_SUPPORT_FACEBOOK, SputilVari.IS_MSG_OPEN_FACEBOOK);
        vp_a(functionSocailMsgData.getTwitter(), SputilVari.IS_MSG_SUPPORT_TWITTER, SputilVari.IS_MSG_OPEN_TWITTER);
        vp_a(functionSocailMsgData.getFlickr(), SputilVari.IS_MSG_SUPPORT_FLICKR, SputilVari.IS_MSG_OPEN_FLICKR);
        vp_a(functionSocailMsgData.getLinkin(), SputilVari.IS_MSG_SUPPORT_LINKEDIN, SputilVari.IS_MSG_OPEN_LINKEDIN);
        vp_a(functionSocailMsgData.getWhats(), SputilVari.IS_MSG_SUPPORT_WHATSAPP, SputilVari.IS_MSG_OPEN_WHATSAPP);
        vp_a(functionSocailMsgData.getLine(), SputilVari.IS_MSG_SUPPORT_LINE, SputilVari.IS_MSG_OPEN_LINE);
        vp_a(functionSocailMsgData.getInstagram(), SputilVari.IS_MSG_SUPPORT_INSTAGRAM, SputilVari.IS_MSG_OPEN_INSTAGRAM);
        vp_a(functionSocailMsgData.getSnapchat(), SputilVari.IS_MSG_SUPPORT_SNAPCHAT, SputilVari.IS_MSG_OPEN_SNAPCHAT);
        vp_a(functionSocailMsgData.getSkype(), SputilVari.IS_MSG_SUPPORT_SKYPE, SputilVari.IS_MSG_OPEN_SKYPE);
        vp_a(functionSocailMsgData.getGmail(), SputilVari.IS_MSG_SUPPORT_GMAIL, "is_msg_open_gmail");
        vp_a(functionSocailMsgData.getOther(), SputilVari.IS_MSG_SUPPORT_OTHER, SputilVari.IS_MSG_OPEN_OTHER);
        vp_a(functionSocailMsgData.getDingding(), SputilVari.IS_MSG_SUPPORT_DINGDING, "is_msg_open_gmail");
        vp_a(functionSocailMsgData.getWxWork(), SputilVari.IS_MSG_SUPPORT_WXWORK, SputilVari.IS_MSG_OPEN_WXWORK);
    }

    public void saveDeviceFunctionAD2(FunctionSocailMsgData functionSocailMsgData) {
        if (functionSocailMsgData == null) {
            return;
        }
        vp_a(functionSocailMsgData.getTikTok(), SputilVari.IS_MSG_TIKTOK, SputilVari.IS_MSG_OPEN_TIKTOK);
        vp_a(functionSocailMsgData.getTelegram(), SputilVari.IS_MSG_TELEGRAM, SputilVari.IS_MSG_OPEN_TELEGRAM);
        vp_a(functionSocailMsgData.getConnected2_me(), SputilVari.IS_MSG_CONNECTED2_ME, SputilVari.IS_MSG_OPEN_CONNECTED2_ME);
        vp_a(functionSocailMsgData.getKakaoTalk(), SputilVari.IS_MSG_KAKAO_TALK, SputilVari.IS_MSG_OPEN_KAKAO_TALK);
        vp_a(functionSocailMsgData.getShieldPolice(), SputilVari.IS_MSG_SHIELD_POLICE, SputilVari.IS_MSG_OPEN_SHIELD_POLICE);
        vp_a(functionSocailMsgData.getMessenger(), SputilVari.IS_MSG_SUPPORT_MESSENGER, SputilVari.IS_MSG_OPEN_MESSENGER);
        vp_a(functionSocailMsgData.getZalo(), SputilVari.IS_MSG_SUPPORT_ZALO, SputilVari.IS_MSG_OPEN_ZALO);
        vp_a(functionSocailMsgData.getViber(), SputilVari.IS_MSG_SUPPORT_VIBER, SputilVari.IS_MSG_OPEN_VIBER);
    }

    public void saveDetectHeart(boolean z) {
        vp_a(z, SputilVari.IS_DETECTING_RATE);
    }

    public void saveDetectBp(boolean z) {
        vp_a(z, SputilVari.IS_DETECTING_BP);
    }

    public void saveDetectSpo2h(boolean z) {
        vp_a(z, SputilVari.IS_DETECTING_SPO2H);
    }

    public void saveBTFunction(boolean z) {
        vp_a(z, SputilVari.IS_HAVE_BT_FUNCTION);
    }

    public void saveDetectFtg(boolean z) {
        vp_a(z, SputilVari.IS_DETECTING_FTG);
    }

    public void saveDetectBreath(boolean z) {
        vp_a(z, SputilVari.IS_DETECTING_BEATH);
    }

    public void saveSPOH2LowRemain(boolean z) {
        vp_a(z, SputilVari.IS_HAVE_FUNCTION_SPO2H_NOT_SUPPORT_REMAIN);
    }

    public void savePersonInfo(int i) {
        if (i <= 0) {
            i = 175;
        }
        vp_b(i, SputilVari.COUNT_PERSON_HEIGHT);
    }

    public void saveMTUValue(int i) {
        vp_b(Math.min(512, Math.max(i, 20)), SputilVari.DEVICE_MTU_VALUE);
    }

    public void saveBloodGlucoseAdjustingSwitch(boolean z) {
        vp_a(z, SputilVari.BLOOD_GLUCOSE_ADJUSTING_SWITCH);
    }

    public void clearAll() {
        SpUtil.clear(vp_b);
    }

    public void clearSomeSp() {
        vp_a();
    }

    private void vp_a(int i, String str) {
        vp_b(i, str);
    }

    private void vp_b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SpUtil.saveString(vp_b, str2, str);
    }

    private void vp_a(EFunctionStatus eFunctionStatus, String str) {
        vp_a(eFunctionStatus, str, "");
    }

    private void vp_a(EFunctionStatus eFunctionStatus, String str, String str2) {
        if (eFunctionStatus == null) {
            vp_a(false, str);
            vp_a(false, str2);
        }
        switch (vp_a.vp_a[((EFunctionStatus) Objects.requireNonNull(eFunctionStatus)).ordinal()]) {
            case 1:
                vp_a(true, str);
                break;
            case 2:
                vp_a(true, str);
                vp_a(false, str2);
                break;
            case 3:
                vp_a(true, str);
                vp_a(true, str2);
                break;
            case 4:
                vp_a(false, str);
                break;
            default:
                vp_a(false, str);
                vp_a(false, str2);
                break;
        }
    }

    private void vp_a(String str, String str2) {
        vp_b(str, str2);
    }

    private void vp_a(boolean z, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SpUtil.saveBoolean(vp_b, str, z);
    }
}
