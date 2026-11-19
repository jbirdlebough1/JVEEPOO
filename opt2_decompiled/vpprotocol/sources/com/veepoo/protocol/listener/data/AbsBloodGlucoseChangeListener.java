package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.MealInfo;
import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import com.veepoo.protocol.model.enums.EBloodGlucoseStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/AbsBloodGlucoseChangeListener.class */
public class AbsBloodGlucoseChangeListener implements IBloodGlucoseChangeListener {
    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onDetectError(int i, EBloodGlucoseStatus eBloodGlucoseStatus) {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseDetect(int i, float f, EBloodGlucoseRiskLevel eBloodGlucoseRiskLevel) {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseStopDetect() {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseAdjustingSettingSuccess(boolean z, float f) {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseAdjustingSettingFailed() {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseAdjustingReadSuccess(boolean z, float f) {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBloodGlucoseAdjustingReadFailed() {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBGMultipleAdjustingReadSuccess(boolean z, MealInfo mealInfo, MealInfo mealInfo2, MealInfo mealInfo3) {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBGMultipleAdjustingReadFailed() {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBGMultipleAdjustingSettingSuccess() {
    }

    @Override // com.veepoo.protocol.listener.data.IBloodGlucoseChangeListener
    public void onBGMultipleAdjustingSettingFailed() {
    }
}
