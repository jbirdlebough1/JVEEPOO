package com.veepoo.protocol;

import com.veepoo.protocol.listener.data.IAlarmDataListener;
import com.veepoo.protocol.listener.data.IAllSetDataListener;
import com.veepoo.protocol.listener.data.IAppReportGpsDataListener;
import com.veepoo.protocol.listener.data.IBPDetectDataListener;
import com.veepoo.protocol.listener.data.IBPFunctionListener;
import com.veepoo.protocol.listener.data.IBPSettingDataListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.listener.data.IBloodComponentDetectListener;
import com.veepoo.protocol.listener.data.IBloodComponentOptListener;
import com.veepoo.protocol.listener.data.IBreathDataListener;
import com.veepoo.protocol.listener.data.ICameraDataListener;
import com.veepoo.protocol.listener.data.IChantingDataListener;
import com.veepoo.protocol.listener.data.ICheckWearDataListener;
import com.veepoo.protocol.listener.data.ICountDownListener;
import com.veepoo.protocol.listener.data.ICustomProtocolStateListener;
import com.veepoo.protocol.listener.data.ICustomSettingDataListener;
import com.veepoo.protocol.listener.data.IDeviceControlPhoneModelState;
import com.veepoo.protocol.listener.data.IDeviceFuctionDataListener;
import com.veepoo.protocol.listener.data.IDeviceRenameListener;
import com.veepoo.protocol.listener.data.IDrinkDataListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.listener.data.IFindDeviceDatalistener;
import com.veepoo.protocol.listener.data.IFindDevicelistener;
import com.veepoo.protocol.listener.data.IFindPhonelistener;
import com.veepoo.protocol.listener.data.IG15MessageListener;
import com.veepoo.protocol.listener.data.IGpsLatLonDataListener;
import com.veepoo.protocol.listener.data.IHeartDataListener;
import com.veepoo.protocol.listener.data.IKaaBaDataListener;
import com.veepoo.protocol.listener.data.ILanguageDataListener;
import com.veepoo.protocol.listener.data.ILongSeatDataListener;
import com.veepoo.protocol.listener.data.ILowPowerListener;
import com.veepoo.protocol.listener.data.INightTurnWristeDataListener;
import com.veepoo.protocol.listener.data.IPersonInfoDataListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.listener.data.IQX17DataAcquisitionStateListener;
import com.veepoo.protocol.listener.data.IReadProductionInfoListener;
import com.veepoo.protocol.listener.data.IReportGpsDataListener;
import com.veepoo.protocol.listener.data.IResponseListener;
import com.veepoo.protocol.listener.data.IScreenLightListener;
import com.veepoo.protocol.listener.data.IScreenLightTimeListener;
import com.veepoo.protocol.listener.data.ISocialMsgDataListener;
import com.veepoo.protocol.listener.data.ISpo2hBreathBreakRemainListener;
import com.veepoo.protocol.listener.data.ISpo2hDataListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.listener.data.ISportModelDataCrcListener;
import com.veepoo.protocol.listener.data.ISportModelStateListener;
import com.veepoo.protocol.listener.data.ITemptureDetectDataListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoListener;
import com.veepoo.protocol.listener.data.IUIOprateListener;
import com.veepoo.protocol.listener.data.IWeatherStatusDataListener;
import com.veepoo.protocol.listener.data.IWomenDataListener;
import com.veepoo.protocol.operate.vp_o;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/vp_c.class */
public class vp_c {
    IPwdDataListener vp_a;
    ISocialMsgDataListener vp_b;
    IDeviceFuctionDataListener vp_c;
    IHeartDataListener vp_d;
    ITemptureDetectDataListener vp_e;
    IBPDetectDataListener vp_f;
    IBPSettingDataListener vp_g;
    ISportDataListener vp_h;
    IBatteryDataListener vp_i;
    IPersonInfoDataListener vp_j;
    ICameraDataListener vp_k;
    IAlarmDataListener vp_l;
    ILongSeatDataListener vp_m;
    ILanguageDataListener vp_n;
    INightTurnWristeDataListener vp_o;
    ICustomSettingDataListener vp_p;
    IFindDeviceDatalistener vp_q;
    ISpo2hDataListener vp_r;
    IFatigueDataListener vp_s;
    IDrinkDataListener vp_t;
    IFindPhonelistener vp_u;
    IFindDevicelistener vp_v;
    IWomenDataListener vp_w;
    ICountDownListener vp_x;
    IScreenLightListener vp_y;
    IGpsLatLonDataListener vp_z;
    IChantingDataListener vp_aa;
    IKaaBaDataListener vp_ab;
    IReportGpsDataListener vp_ac;
    IAppReportGpsDataListener vp_ad;
    IScreenLightTimeListener vp_ae;
    ICheckWearDataListener vp_af;
    ISportModelStateListener vp_ag;
    ISportModelDataCrcListener vp_ah;
    IAllSetDataListener vp_ai;
    ISpo2hBreathBreakRemainListener vp_aj;
    IBreathDataListener vp_ak;
    IDeviceControlPhoneModelState vp_al;
    ILowPowerListener vp_am;
    ICustomProtocolStateListener vp_an;
    IBPFunctionListener vp_ao;
    IUIBaseInfoListener vp_ap;
    IUIOprateListener vp_aq;
    IWeatherStatusDataListener vp_ar;
    IResponseListener vp_as;
    IResponseListener vp_at;
    IResponseListener vp_au;
    IG15MessageListener vp_av;
    IDeviceRenameListener vp_aw;
    IReadProductionInfoListener vp_ax;
    IBloodComponentDetectListener vp_ay;
    IBloodComponentOptListener vp_az;
    IQX17DataAcquisitionStateListener vp_ba;

    private boolean vp_d(byte[] bArr) {
        return bArr != null && bArr.length >= 2 && bArr[1] == 2;
    }

    private boolean vp_c(byte[] bArr) {
        return bArr != null && bArr.length >= 2 && bArr[1] == 1;
    }

    private boolean vp_e(byte[] bArr) {
        return bArr != null && bArr.length >= 2 && bArr[1] == 3;
    }

    private boolean vp_b(byte[] bArr) {
        return bArr != null && bArr.length >= 2 && bArr[1] == 0;
    }

    private boolean vp_a(byte[] bArr) {
        return bArr != null && bArr.length >= 2 && bArr[1] == 2;
    }

    public void vp_b(IResponseListener iResponseListener) {
        this.vp_as = iResponseListener;
    }

    public void vp_c(IResponseListener iResponseListener) {
        this.vp_at = iResponseListener;
    }

    public void vp_a(String str, byte[] bArr, vp_a vp_aVar) {
        IResponseListener iResponseListener;
        ISpo2hBreathBreakRemainListener iSpo2hBreathBreakRemainListener;
        IBPFunctionListener iBPFunctionListener;
        IResponseListener iResponseListener2;
        IWeatherStatusDataListener iWeatherStatusDataListener;
        IUIOprateListener iUIOprateListener;
        ICustomProtocolStateListener iCustomProtocolStateListener;
        ILowPowerListener iLowPowerListener;
        IBreathDataListener iBreathDataListener;
        ICheckWearDataListener iCheckWearDataListener;
        IScreenLightListener iScreenLightListener;
        IScreenLightTimeListener iScreenLightTimeListener;
        ICountDownListener iCountDownListener;
        IWomenDataListener iWomenDataListener;
        IDrinkDataListener iDrinkDataListener;
        IFatigueDataListener iFatigueDataListener;
        ISpo2hDataListener iSpo2hDataListener;
        IFindDeviceDatalistener iFindDeviceDatalistener;
        INightTurnWristeDataListener iNightTurnWristeDataListener;
        ILanguageDataListener iLanguageDataListener;
        ILongSeatDataListener iLongSeatDataListener;
        IPersonInfoDataListener iPersonInfoDataListener;
        IAlarmDataListener iAlarmDataListener;
        ICameraDataListener iCameraDataListener;
        IBatteryDataListener iBatteryDataListener;
        ISportDataListener iSportDataListener;
        ISportDataListener iSportDataListener2;
        ISportDataListener iSportDataListener3;
        IBPSettingDataListener iBPSettingDataListener;
        IBPDetectDataListener iBPDetectDataListener;
        IHeartDataListener iHeartDataListener;
        ITemptureDetectDataListener iTemptureDetectDataListener;
        IPwdDataListener iPwdDataListener;
        ISocialMsgDataListener iSocialMsgDataListener;
        IDeviceFuctionDataListener iDeviceFuctionDataListener;
        IResponseListener iResponseListener3;
        IPwdDataListener iPwdDataListener2;
        if (str.equals("pwd_comfirm_oprate") && (iPwdDataListener2 = this.vp_a) != null) {
            vp_aVar.vp_a(bArr, iPwdDataListener2);
            return;
        }
        if (str.equals("sync_time_oprate") && (iResponseListener3 = this.vp_at) != null) {
            vp_aVar.vp_a(bArr, iResponseListener3);
            return;
        }
        if (str.equals("device_function") && (iDeviceFuctionDataListener = this.vp_c) != null) {
            vp_aVar.vp_a(bArr, iDeviceFuctionDataListener);
            return;
        }
        if (str.equals("device_msg_oprate") && (iSocialMsgDataListener = this.vp_b) != null) {
            vp_aVar.vp_a(bArr, iSocialMsgDataListener);
            return;
        }
        if (str.equals("pwd_modify_oprate") && (iPwdDataListener = this.vp_a) != null) {
            vp_aVar.vp_a(bArr, iPwdDataListener);
            return;
        }
        if (str.equals("tempture_detect_oprate") && (iTemptureDetectDataListener = this.vp_e) != null) {
            vp_aVar.vp_a(bArr, iTemptureDetectDataListener);
            return;
        }
        if (str.equals("heart_read_oprate") && (iHeartDataListener = this.vp_d) != null) {
            vp_aVar.vp_a(bArr, iHeartDataListener);
            return;
        }
        if (str.equals("bp_oprate") && (iBPDetectDataListener = this.vp_f) != null) {
            vp_aVar.vp_a(bArr, iBPDetectDataListener);
            return;
        }
        if (str.equals("bp_model_setting_oprate") && (iBPSettingDataListener = this.vp_g) != null) {
            vp_aVar.vp_a(bArr, iBPSettingDataListener);
            return;
        }
        if (str.equals("head_gsensor") && (iSportDataListener3 = this.vp_h) != null) {
            vp_aVar.vp_a(bArr, iSportDataListener3);
            return;
        }
        if (str.equals("read_current_sport_sportmodel_oprate") && (iSportDataListener2 = this.vp_h) != null) {
            vp_aVar.vp_a(bArr, iSportDataListener2);
            return;
        }
        if (str.equals("read_current_sport_oprate") && (iSportDataListener = this.vp_h) != null) {
            vp_aVar.vp_a(bArr, iSportDataListener);
            return;
        }
        if (str.equals("read_battery_oprate") && (iBatteryDataListener = this.vp_i) != null) {
            vp_aVar.vp_a(bArr, iBatteryDataListener);
            return;
        }
        if (str.equals("take_photo_oprate") && (iCameraDataListener = this.vp_k) != null) {
            vp_aVar.vp_a(bArr, iCameraDataListener);
            return;
        }
        if (str.equals("alarm_oprate") && (iAlarmDataListener = this.vp_l) != null) {
            vp_aVar.vp_a(bArr, iAlarmDataListener);
            return;
        }
        if (str.equals("person_info_oprate") && (iPersonInfoDataListener = this.vp_j) != null) {
            vp_aVar.vp_a(bArr, iPersonInfoDataListener);
            return;
        }
        if (str.equals("long_seat_oprate") && (iLongSeatDataListener = this.vp_m) != null) {
            vp_aVar.vp_a(bArr, iLongSeatDataListener);
            return;
        }
        if (str.equals("change_watch_language_oprate") && (iLanguageDataListener = this.vp_n) != null) {
            vp_aVar.vp_a(bArr, iLanguageDataListener);
            return;
        }
        if (str.equals("night_turn_opeate") && (iNightTurnWristeDataListener = this.vp_o) != null) {
            vp_aVar.vp_a(bArr, iNightTurnWristeDataListener);
            return;
        }
        if (str.equals("custom_setting_oprate")) {
            vp_aVar.vp_a(bArr, this.vp_p);
            return;
        }
        if (str.equals("gps_lat_lon_oprate")) {
            if (bArr == null || bArr.length < 2) {
                return;
            }
            switch (bArr[1]) {
                case 1:
                    IGpsLatLonDataListener iGpsLatLonDataListener = this.vp_z;
                    if (iGpsLatLonDataListener != null) {
                        vp_aVar.vp_a(bArr, iGpsLatLonDataListener);
                    }
                    break;
                case 2:
                    IChantingDataListener iChantingDataListener = this.vp_aa;
                    if (iChantingDataListener != null) {
                        vp_aVar.vp_a(bArr, iChantingDataListener);
                    }
                    break;
                case 3:
                    IKaaBaDataListener iKaaBaDataListener = this.vp_ab;
                    if (iKaaBaDataListener != null) {
                        vp_aVar.vp_a(bArr, iKaaBaDataListener);
                    }
                    break;
                case 4:
                    IReportGpsDataListener iReportGpsDataListener = this.vp_ac;
                    if (iReportGpsDataListener != null) {
                        vp_aVar.vp_a(bArr, iReportGpsDataListener);
                    }
                    break;
                case 5:
                    IAppReportGpsDataListener iAppReportGpsDataListener = this.vp_ad;
                    if (iAppReportGpsDataListener != null) {
                        vp_aVar.vp_a(bArr, iAppReportGpsDataListener);
                    }
                    break;
            }
            return;
        }
        if (str.equals("find_watch_by_phon_oprate") && (iFindDeviceDatalistener = this.vp_q) != null) {
            vp_aVar.vp_a(bArr, iFindDeviceDatalistener);
            return;
        }
        if (str.equals("spo2h_read_oprate") && (iSpo2hDataListener = this.vp_r) != null) {
            vp_aVar.vp_a(bArr, iSpo2hDataListener);
            return;
        }
        if (str.equals("fatigue_read_oprate") && (iFatigueDataListener = this.vp_s) != null) {
            vp_aVar.vp_a(bArr, iFatigueDataListener);
            return;
        }
        if (str.equals("drink_oprate") && (iDrinkDataListener = this.vp_t) != null) {
            vp_aVar.vp_a(bArr, iDrinkDataListener);
            return;
        }
        if (str.equals("find_phone_by_watch_operate")) {
            if (vp_b(bArr)) {
                IFindPhonelistener iFindPhonelistener = this.vp_u;
                if (iFindPhonelistener != null) {
                    iFindPhonelistener.findPhone();
                    return;
                }
                return;
            }
            if (vp_a(bArr)) {
                IFindPhonelistener iFindPhonelistener2 = this.vp_u;
                if (iFindPhonelistener2 != null) {
                    iFindPhonelistener2.deviceFindingCYPhone();
                    return;
                }
                return;
            }
            IFindDevicelistener iFindDevicelistener = this.vp_v;
            if (iFindDevicelistener != null) {
                vp_aVar.vp_a(bArr, iFindDevicelistener);
                return;
            }
            return;
        }
        if (str.equals("women_mense_setting_oprate") && (iWomenDataListener = this.vp_w) != null) {
            vp_aVar.vp_a(bArr, iWomenDataListener);
            return;
        }
        if (str.equals("count_down_oprate") && (iCountDownListener = this.vp_x) != null) {
            vp_aVar.vp_a(bArr, iCountDownListener);
            return;
        }
        if (str.equals("screen_ligth_time_oprate") && (iScreenLightTimeListener = this.vp_ae) != null) {
            vp_aVar.vp_a(bArr, iScreenLightTimeListener);
            return;
        }
        if (str.equals("screen_ligth_oprate") && (iScreenLightListener = this.vp_y) != null) {
            vp_aVar.vp_a(bArr, iScreenLightListener);
            return;
        }
        if (str.equals("check_wear_oprate") && (iCheckWearDataListener = this.vp_af) != null) {
            vp_aVar.vp_a(bArr, iCheckWearDataListener);
            return;
        }
        if (str.equals("all_setting_oprate")) {
            vp_aVar.vp_a(bArr, this.vp_ai);
            return;
        }
        if (str.equals("breath_read_oprate") && (iBreathDataListener = this.vp_ak) != null) {
            vp_aVar.vp_a(bArr, iBreathDataListener);
            return;
        }
        if (str.equals("sport_model_openclose_oprate") || str.equals("sport_model_crc_oprate")) {
            if (bArr[0] != -45 || bArr[1] == 2) {
                ISportModelStateListener iSportModelStateListener = this.vp_ag;
                if (iSportModelStateListener != null) {
                    vp_aVar.vp_a(bArr, iSportModelStateListener);
                    return;
                }
                return;
            }
            ISportModelDataCrcListener iSportModelDataCrcListener = this.vp_ah;
            if (iSportModelDataCrcListener != null) {
                vp_aVar.vp_a(bArr, iSportModelDataCrcListener);
                return;
            }
            return;
        }
        if (str.equals("battery_lowpower_oprate") && (iLowPowerListener = this.vp_am) != null) {
            vp_aVar.vp_a(bArr, iLowPowerListener);
            return;
        }
        if (str.equals("custom_protocol_setting_oprate") && (iCustomProtocolStateListener = this.vp_an) != null) {
            vp_aVar.vp_a(bArr, iCustomProtocolStateListener);
            return;
        }
        if (str.equals("function_big_data_tarn")) {
            if (vp_d(bArr) || vp_e(bArr)) {
                IUIBaseInfoListener iUIBaseInfoListener = this.vp_ap;
                if (iUIBaseInfoListener != null) {
                    vp_aVar.vp_a(bArr, iUIBaseInfoListener);
                    return;
                }
                return;
            }
            if (!vp_c(bArr) || (iUIOprateListener = this.vp_aq) == null) {
                return;
            }
            vp_aVar.vp_a(bArr, iUIOprateListener);
            return;
        }
        if (str.equals("weather_oprateweather_oprate") && (iWeatherStatusDataListener = this.vp_ar) != null) {
            vp_aVar.vp_a(bArr, iWeatherStatusDataListener);
            return;
        }
        if (str.equals("send_content_to_watch_oprate") && (iResponseListener2 = this.vp_au) != null) {
            if (bArr[1] == -1) {
                iResponseListener2.response(bArr[2]);
                return;
            }
            return;
        }
        if (str.equals("function_bp_oprate") && (iBPFunctionListener = this.vp_ao) != null) {
            vp_aVar.vp_a(bArr, iBPFunctionListener);
            return;
        }
        if (str.equals("spo2h_breath_break_remind_oprate") && (iSpo2hBreathBreakRemainListener = this.vp_aj) != null) {
            vp_aVar.vp_a(bArr, iSpo2hBreathBreakRemainListener);
            return;
        }
        if (str.equals("sos_oprate")) {
            byte b = bArr[1];
            if (b == 5 && (iResponseListener = this.vp_as) != null) {
                vp_aVar.vp_a(bArr, iResponseListener);
                return;
            } else {
                if ((b == 4 || b == 1) && this.vp_al != null) {
                    new vp_o().vp_a(str, bArr, this.vp_al);
                    return;
                }
                return;
            }
        }
        if (str.equals("auto_callback_oprate") || str.equals("pnone_message") || str.equals("knock_notify_oprate") || str.equals("device_msg_oprate")) {
            if (this.vp_al != null) {
                new vp_o().vp_a(str, bArr, this.vp_al);
                return;
            }
            return;
        }
        if (str.equals("send_content_to_watch_oprate")) {
            vp_aVar.vp_a(bArr, this.vp_av);
            return;
        }
        if (str.equals("rename_operate")) {
            vp_aVar.vp_a(bArr, this.vp_aw);
            return;
        }
        if (str.equals("read_production_info_oprate")) {
            vp_aVar.vp_a(bArr, this.vp_ax);
            return;
        }
        if (!str.equals("blood_component_operate") || bArr == null || bArr.length <= 2) {
            return;
        }
        if (bArr[1] == 1) {
            vp_aVar.vp_a(bArr, this.vp_ay);
        } else {
            vp_aVar.vp_a(bArr, this.vp_az);
        }
    }

    public void vp_a(IAlarmDataListener iAlarmDataListener) {
        this.vp_l = iAlarmDataListener;
    }

    public void vp_a(IAllSetDataListener iAllSetDataListener) {
        this.vp_ai = iAllSetDataListener;
    }

    public void vp_a(IAppReportGpsDataListener iAppReportGpsDataListener) {
        this.vp_ad = iAppReportGpsDataListener;
    }

    public void vp_a(IBPFunctionListener iBPFunctionListener) {
        this.vp_ao = iBPFunctionListener;
    }

    public void vp_a(IBatteryDataListener iBatteryDataListener) {
        this.vp_i = iBatteryDataListener;
    }

    public void vp_a(IBloodComponentDetectListener iBloodComponentDetectListener) {
        this.vp_ay = iBloodComponentDetectListener;
    }

    public void vp_a(IBloodComponentOptListener iBloodComponentOptListener) {
        this.vp_az = iBloodComponentOptListener;
    }

    public void vp_a(IBreathDataListener iBreathDataListener) {
        this.vp_ak = iBreathDataListener;
    }

    public void vp_a(ICameraDataListener iCameraDataListener) {
        this.vp_k = iCameraDataListener;
    }

    public void vp_a(IChantingDataListener iChantingDataListener) {
        this.vp_aa = iChantingDataListener;
    }

    public void vp_a(ICheckWearDataListener iCheckWearDataListener) {
        this.vp_af = iCheckWearDataListener;
    }

    public void vp_a(IResponseListener iResponseListener) {
        this.vp_au = iResponseListener;
    }

    public void vp_a(ICountDownListener iCountDownListener) {
        this.vp_x = iCountDownListener;
    }

    public void vp_a(ICustomSettingDataListener iCustomSettingDataListener) {
        this.vp_p = iCustomSettingDataListener;
    }

    public void vp_a(IDeviceControlPhoneModelState iDeviceControlPhoneModelState) {
        this.vp_al = iDeviceControlPhoneModelState;
    }

    public void vp_a(IDeviceFuctionDataListener iDeviceFuctionDataListener) {
        this.vp_c = iDeviceFuctionDataListener;
    }

    public void vp_a(IDeviceRenameListener iDeviceRenameListener) {
        this.vp_aw = iDeviceRenameListener;
    }

    public void vp_a(IDrinkDataListener iDrinkDataListener) {
        this.vp_t = iDrinkDataListener;
    }

    public void vp_a(IFatigueDataListener iFatigueDataListener) {
        this.vp_s = iFatigueDataListener;
    }

    public void vp_a(IFindDeviceDatalistener iFindDeviceDatalistener) {
        this.vp_q = iFindDeviceDatalistener;
    }

    public void vp_a(IFindDevicelistener iFindDevicelistener) {
        this.vp_v = iFindDevicelistener;
    }

    public void vp_a(IFindPhonelistener iFindPhonelistener) {
        this.vp_u = iFindPhonelistener;
    }

    public void vp_a(IGpsLatLonDataListener iGpsLatLonDataListener) {
        this.vp_z = iGpsLatLonDataListener;
    }

    public void vp_a(IHeartDataListener iHeartDataListener) {
        this.vp_d = iHeartDataListener;
    }

    public void vp_a(IKaaBaDataListener iKaaBaDataListener) {
        this.vp_ab = iKaaBaDataListener;
    }

    public void vp_a(ILanguageDataListener iLanguageDataListener) {
        this.vp_n = iLanguageDataListener;
    }

    public void vp_a(ILongSeatDataListener iLongSeatDataListener) {
        this.vp_m = iLongSeatDataListener;
    }

    public void vp_a(ILowPowerListener iLowPowerListener) {
        this.vp_am = iLowPowerListener;
    }

    public void vp_a(INightTurnWristeDataListener iNightTurnWristeDataListener) {
        this.vp_o = iNightTurnWristeDataListener;
    }

    public void vp_a(IPersonInfoDataListener iPersonInfoDataListener) {
        this.vp_j = iPersonInfoDataListener;
    }

    public void vp_a(IPwdDataListener iPwdDataListener) {
        this.vp_a = iPwdDataListener;
    }

    public void vp_a(IQX17DataAcquisitionStateListener iQX17DataAcquisitionStateListener) {
        this.vp_ba = iQX17DataAcquisitionStateListener;
    }

    public void vp_a(IReadProductionInfoListener iReadProductionInfoListener) {
        this.vp_ax = iReadProductionInfoListener;
    }

    public void vp_a(IReportGpsDataListener iReportGpsDataListener) {
        this.vp_ac = iReportGpsDataListener;
    }

    public void vp_a(IScreenLightListener iScreenLightListener) {
        this.vp_y = iScreenLightListener;
    }

    public void vp_a(IScreenLightTimeListener iScreenLightTimeListener) {
        this.vp_ae = iScreenLightTimeListener;
    }

    public void vp_a(ISocialMsgDataListener iSocialMsgDataListener) {
        this.vp_b = iSocialMsgDataListener;
    }

    public void vp_a(ISpo2hDataListener iSpo2hDataListener) {
        this.vp_r = iSpo2hDataListener;
    }

    public void vp_a(ISportDataListener iSportDataListener) {
        this.vp_h = iSportDataListener;
    }

    public void vp_a(ISportModelDataCrcListener iSportModelDataCrcListener) {
        this.vp_ah = iSportModelDataCrcListener;
    }

    public void vp_a(ISportModelStateListener iSportModelStateListener) {
        this.vp_ag = iSportModelStateListener;
    }

    public void vp_a(ITemptureDetectDataListener iTemptureDetectDataListener) {
        this.vp_e = iTemptureDetectDataListener;
    }

    public void vp_a(IUIBaseInfoListener iUIBaseInfoListener) {
        this.vp_ap = iUIBaseInfoListener;
    }

    public void vp_a(IUIOprateListener iUIOprateListener) {
        this.vp_aq = iUIOprateListener;
    }

    public void vp_a(IWeatherStatusDataListener iWeatherStatusDataListener) {
        this.vp_ar = iWeatherStatusDataListener;
    }

    public void vp_a(IWomenDataListener iWomenDataListener) {
        this.vp_w = iWomenDataListener;
    }

    public void vp_a(IBPDetectDataListener iBPDetectDataListener) {
        this.vp_f = iBPDetectDataListener;
    }

    public void vp_a(IBPSettingDataListener iBPSettingDataListener) {
        this.vp_g = iBPSettingDataListener;
    }

    public void vp_a(ISpo2hBreathBreakRemainListener iSpo2hBreathBreakRemainListener) {
        this.vp_aj = iSpo2hBreathBreakRemainListener;
    }
}
