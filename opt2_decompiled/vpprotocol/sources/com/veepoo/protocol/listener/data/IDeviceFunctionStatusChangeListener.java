package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.enums.EFunctionStatus;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IDeviceFunctionStatusChangeListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IDeviceFunctionStatusChangeListener;", "", "onFunctionStatusChanged", "", "function", "Lcom/veepoo/protocol/listener/data/IDeviceFunctionStatusChangeListener$DeviceFunction;", "status", "Lcom/veepoo/protocol/model/enums/EFunctionStatus;", "DeviceFunction", "vpprotocol_release"})
public interface IDeviceFunctionStatusChangeListener {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IDeviceFunctionStatusChangeListener$DeviceFunction.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/veepoo/protocol/listener/data/IDeviceFunctionStatusChangeListener$DeviceFunction;", "", "des", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDes", "()Ljava/lang/String;", "HeartRateAutoDetect", "BloodOxygenAutoDetect", "HeartRateAlarm", "BloodPressureAlarm", "HighTemperatureAlarm", "LowBloodOxygenAlarm", "UNKNOWN", "vpprotocol_release"})
    public enum DeviceFunction {
        HeartRateAutoDetect("Heart Rate自动检测"),
        BloodOxygenAutoDetect("Blood Oxygen (SpO2)自动检测"),
        HeartRateAlarm("Heart Rate报警"),
        BloodPressureAlarm("Blood Pressure报警"),
        HighTemperatureAlarm("Body Temperature过高报警"),
        LowBloodOxygenAlarm("Blood Oxygen (SpO2)过低报警"),
        UNKNOWN("未知异常警报");

        private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());

        @NotNull
        private final String des;

        DeviceFunction(String str) {
            this.des = str;
        }

        @NotNull
        public static EnumEntries<DeviceFunction> getEntries() {
            return vp_b;
        }

        @NotNull
        public final String getDes() {
            return this.des;
        }
    }

    void onFunctionStatusChanged(@NotNull DeviceFunction function, @NotNull EFunctionStatus status);
}
