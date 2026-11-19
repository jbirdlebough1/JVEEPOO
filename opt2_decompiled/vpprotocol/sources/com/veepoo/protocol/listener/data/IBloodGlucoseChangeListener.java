package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.MealInfo;
import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import com.veepoo.protocol.model.enums.EBloodGlucoseStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IBloodGlucoseChangeListener.class */
public interface IBloodGlucoseChangeListener extends IListener {
    void onDetectError(int i, EBloodGlucoseStatus eBloodGlucoseStatus);

    void onBloodGlucoseDetect(int i, float f, EBloodGlucoseRiskLevel eBloodGlucoseRiskLevel);

    void onBloodGlucoseStopDetect();

    void onBloodGlucoseAdjustingSettingSuccess(boolean z, float f);

    void onBloodGlucoseAdjustingSettingFailed();

    void onBloodGlucoseAdjustingReadSuccess(boolean z, float f);

    void onBloodGlucoseAdjustingReadFailed();

    void onBGMultipleAdjustingReadSuccess(boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3);

    void onBGMultipleAdjustingReadFailed();

    void onBGMultipleAdjustingSettingSuccess();

    void onBGMultipleAdjustingSettingFailed();
}
