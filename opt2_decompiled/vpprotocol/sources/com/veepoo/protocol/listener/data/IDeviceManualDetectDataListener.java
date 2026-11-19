package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.BloodComponentManualData;
import com.veepoo.protocol.model.datas.BloodGlucoseManualData;
import com.veepoo.protocol.model.datas.BloodOxygenManualData;
import com.veepoo.protocol.model.datas.BloodPressureManualData;
import com.veepoo.protocol.model.datas.BodyTemperatureManualData;
import com.veepoo.protocol.model.datas.EmotionManualData;
import com.veepoo.protocol.model.datas.FatigueManualData;
import com.veepoo.protocol.model.datas.HeartRateManualData;
import com.veepoo.protocol.model.datas.HrvManualData;
import com.veepoo.protocol.model.datas.MetoManualData;
import com.veepoo.protocol.model.datas.MiniCheckupManualData;
import com.veepoo.protocol.model.datas.PressureManualData;
import com.veepoo.protocol.model.datas.SkinConductanceManualData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IDeviceManualDetectDataListener.class */
public interface IDeviceManualDetectDataListener extends IListener {
    void onBloodPressureDataChange(List<BloodPressureManualData> list);

    void onHeartRateDataChange(List<HeartRateManualData> list);

    void onBloodGlucoseDataChange(List<BloodGlucoseManualData> list);

    void onPressureManualDataChange(List<PressureManualData> list);

    void onBloodOxygenDataChange(List<BloodOxygenManualData> list);

    void onBodyTemperatureDataChange(List<BodyTemperatureManualData> list);

    void onMetoManualDataChange(List<MetoManualData> list);

    void onHrvManualDataChange(List<HrvManualData> list);

    void onBloodComponentManualDataChange(List<BloodComponentManualData> list);

    void onMiniCheckupManualDataChange(List<MiniCheckupManualData> list);

    void onEmotionManualDataChange(List<EmotionManualData> list);

    void onFatigueManualDataChange(List<FatigueManualData> list);

    void onSkinConductanceManualDataChange(List<SkinConductanceManualData> list);

    void onReadProgress(float f);

    void onReadComplete();

    void onReadFail();
}
