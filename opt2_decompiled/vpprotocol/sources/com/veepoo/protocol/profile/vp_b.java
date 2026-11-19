package com.veepoo.protocol.profile;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/profile/vp_b.class */
public abstract class vp_b extends vp_a {
    static Map vp_by = vp_b();
    static List vp_bz;

    private static Map vp_b() {
        HashMap map = new HashMap();
        map.put((byte) -32, "read_battery_sleep");
        map.put((byte) -47, "read_battery_original");
        map.put((byte) -31, "long_seat_oprate");
        map.put((byte) -85, "alarm_oprate");
        map.put((byte) -86, "night_turn_opeate");
        map.put((byte) -96, "read_battery_oprate");
        map.put((byte) -111, "bp_model_setting_oprate");
        map.put((byte) -63, "pnone_message");
        map.put((byte) -89, "device_function");
        map.put((byte) -92, "device_number");
        map.put((byte) -48, "heart_read_oprate");
        map.put((byte) -88, "read_current_sport_oprate");
        map.put((byte) -40, "read_current_sport_sportmodel_oprate");
        map.put((byte) -112, "bp_oprate");
        map.put((byte) -93, "person_info_oprate");
        map.put((byte) -41, "drink_oprate");
        map.put((byte) -62, "send_content_to_watch_oprate");
        map.put((byte) -83, "device_msg_oprate");
        map.put((byte) -82, "find_watch_by_phon_oprate");
        map.put((byte) -81, "disconnect_watch_oprate");
        map.put((byte) -30, "check_wear_oprate");
        map.put((byte) -94, "oad_cmd_oprate");
        map.put((byte) -72, "custom_setting_oprate");
        map.put((byte) -74, "take_photo_oprate");
        map.put((byte) -75, "find_phone_by_watch_operate");
        map.put((byte) -12, "change_watch_language_oprate");
        map.put((byte) -128, "spo2h_read_oprate");
        map.put((byte) -126, "breath_read_oprate");
        map.put((byte) -127, "fatigue_read_oprate");
        map.put((byte) -123, "women_mense_setting_oprate");
        map.put((byte) -78, "count_down_oprate");
        map.put((byte) -71, "multi_alarm_oprate");
        map.put((byte) -84, "health_alarm_interval_oprate");
        map.put((byte) -79, "screen_ligth_oprate");
        map.put((byte) -76, "screen_ligth_time_oprate");
        map.put((byte) -57, "screen_style_oprate");
        map.put((byte) -44, "sport_model_origin_oprate");
        map.put((byte) -46, "read_battery_spo2h_original");
        map.put((byte) -43, "sport_model_openclose_oprate");
        map.put((byte) -45, "sport_model_crc_oprate");
        map.put((byte) -77, "all_setting_oprate");
        map.put((byte) -39, "hrv_origan_oprate");
        map.put((byte) -73, "sos_oprate");
        map.put((byte) -122, "spo2h_breath_break_remind_oprate");
        map.put((byte) -70, "knock_notify_oprate");
        map.put((byte) -15, "head_gsensor");
        map.put((byte) -109, "ecg_data_app_detect_oprate");
        map.put((byte) -106, "ecg_data_get_id_oprate");
        map.put((byte) -105, "ecg_data_use_id_oprate");
        map.put((byte) -33, "origal_df_oprate");
        map.put((byte) -34, "manual_detect_de");
        map.put((byte) 1, "auto_callback_oprate");
        map.put((byte) 3, "battery_lowpower_oprate");
        map.put((byte) -110, "function_bp_oprate");
        map.put((byte) -56, "weather_oprateweather_oprate");
        map.put((byte) -103, "custom_protocol_data_oprate");
        map.put((byte) 2, "custom_protocol_setting_oprate");
        map.put((byte) 4, "custom_passthrough_oprate");
        map.put((byte) -91, "sync_time_oprate");
        map.put((byte) 80, "function_big_data_tarn");
        map.put((byte) -113, "gps_lat_lon_oprate");
        map.put((byte) -121, "tempture_detect_oprate");
        map.put((byte) -120, "tempture_origal_oprate");
        map.put((byte) -69, "text_transmission_oprate");
        map.put((byte) 112, "rr_interval_oprate");
        map.put((byte) -108, "fun_model_switch_oprate");
        map.put((byte) -14, "device_debug_oprate_F2");
        map.put((byte) -119, "blood_glucose_detect_operate");
        map.put((byte) -67, "bt_operate");
        map.put((byte) -124, "rename_operate");
        map.put((byte) -25, "health_remind_operate");
        map.put((byte) 114, "contact_operate");
        map.put((byte) -4, "read_production_info_oprate");
        map.put((byte) -118, "blood_component_operate");
        map.put((byte) -66, "world_clock_operate");
        map.put((byte) -38, "sport_model_control_oprate");
        map.put((byte) -24, "ai_operate");
        map.put((byte) -125, "text_image_push_operate");
        map.put((byte) -21, "ppg_test_status_operate");
        map.put((byte) -13, "qx17_data_acquisition_oprate");
        map.put((byte) -102, "remind_event_oprate");
        map.put((byte) -23, "health_light_oprate");
        map.put((byte) -50, "motor_operate");
        map.put((byte) -65, "game_setting_operate");
        return map;
    }

    private static List vp_a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("pwd_comfirm_oprate");
        arrayList.add("pwd_modify_oprate");
        arrayList.add("device_function");
        arrayList.add("device_msg_oprate");
        arrayList.add("heart_read_oprate");
        arrayList.add("bp_oprate");
        arrayList.add("bp_model_setting_oprate");
        arrayList.add("read_current_sport_oprate");
        arrayList.add("read_battery_oprate");
        arrayList.add("take_photo_oprate");
        arrayList.add("alarm_oprate");
        arrayList.add("breath_read_oprate");
        arrayList.add("person_info_oprate");
        arrayList.add("long_seat_oprate");
        arrayList.add("change_watch_language_oprate");
        arrayList.add("night_turn_opeate");
        arrayList.add("custom_setting_oprate");
        arrayList.add("find_watch_by_phon_oprate");
        arrayList.add("spo2h_read_oprate");
        arrayList.add("fatigue_read_oprate");
        arrayList.add("drink_oprate");
        arrayList.add("find_phone_by_watch_operate");
        arrayList.add("device_msg_oprate");
        arrayList.add("women_mense_setting_oprate");
        arrayList.add("count_down_oprate");
        arrayList.add("screen_ligth_oprate");
        arrayList.add("screen_ligth_time_oprate");
        arrayList.add("check_wear_oprate");
        arrayList.add("read_current_sport_sportmodel_oprate");
        arrayList.add("sport_model_openclose_oprate");
        arrayList.add("pnone_message");
        arrayList.add("all_setting_oprate");
        arrayList.add("sos_oprate");
        arrayList.add("spo2h_breath_break_remind_oprate");
        arrayList.add("knock_notify_oprate");
        arrayList.add("head_gsensor");
        arrayList.add("battery_lowpower_oprate");
        arrayList.add("custom_protocol_setting_oprate");
        arrayList.add("auto_callback_oprate");
        arrayList.add("function_bp_oprate");
        arrayList.add("send_content_to_watch_oprate");
        arrayList.add("weather_oprateweather_oprate");
        arrayList.add("sync_time_oprate");
        arrayList.add("function_big_data_tarn");
        arrayList.add("gps_lat_lon_oprate");
        arrayList.add("tempture_detect_oprate");
        arrayList.add("sport_model_crc_oprate");
        arrayList.add("rename_operate");
        arrayList.add("read_production_info_oprate");
        arrayList.add("blood_component_operate");
        return arrayList;
    }

    public static String vp_a(UUID uuid, byte[] bArr) {
        if (!uuid.equals(vp_a.vp_d) && !uuid.equals(vp_a.vp_e) && !uuid.equals(vp_a.vp_g)) {
            return "";
        }
        byte b = bArr[0];
        if (b != -95) {
            return vp_a(b);
        }
        if (bArr.length < 4) {
            return "";
        }
        byte b2 = bArr[3];
        if (b2 == 0 || b2 == 1 || b2 == 6) {
            return "pwd_comfirm_oprate";
        }
        return (b2 == 2 || b2 == 3) ? "pwd_modify_oprate" : "";
    }

    private static String vp_a(byte b) {
        String str = (String) vp_by.get(Byte.valueOf(b));
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            str2 = "unKnow action";
        }
        return str2;
    }

    public static boolean vp_a(String str) {
        if (vp_bz == null) {
            vp_bz = vp_a();
        }
        return vp_bz.contains(str);
    }
}
