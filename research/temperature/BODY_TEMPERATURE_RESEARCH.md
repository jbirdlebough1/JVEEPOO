# Veepoo / H Band Body Temperature Measurement Research

## 1. Sensor Hardware Architecture

Veepoo devices that support continuous body temperature tracking generally utilize:
1. **Contact Thermistor / Precision NTC:**
   * A conductive metal contact plate (usually stainless steel or titanium alloy) mounted on the inner wrist-facing casing.
   * Measures direct epidermal skin surface temperature.
2. **Ambient / Internal Reference Sensor:**
   * An onboard thermal sensor inside the watch chassis measuring internal component heat and ambient room temperature.
3. **Core Body Temperature Compensation Algorithm:**
   * Epidermal skin temperature is typically `1.5°C – 2.5°C` lower than actual oral or core rectal temperature.
   * The firmware applies a compensation algorithm based on ambient temperature to extrapolate estimated core body temperature.

---

## 2. Protocol APIs (`vpprotocol`)

In the Veepoo protocol (`com.veepoo.protocol.*`):

### Key Classes & Methods:
* `ITemperatureDataListener`: Callback for real-time and continuous temperature telemetry.
* `TemperatureData`: Model containing:
  * `skinTemp`: Raw measured skin contact surface temperature.
  * `bodyTemp`: Algorithmically adjusted core temperature estimate.
  * `tempStatus`: Sensor contact status (e.g. worn properly vs. device floating/detached).
  * `isHighTempAlarm`: Boolean indicating fever threshold exceeded.
* `VPOperateManager.getInstance().startDetectCustomTemp()`: Initiates manual on-demand temperature acquisition.
* `VPOperateManager.getInstance().stopDetectCustomTemp()`: Halts manual test.
* `VPOperateManager.getInstance().readCustomTempData()`: Reads stored periodic interval logs (e.g., measurements taken every 10 or 30 minutes throughout the day).
* `VPOperateManager.getInstance().setCustomTempAlarm()`: Configures high-temperature warning alarm limits.

---

## 3. Data Format in Packets

* Temperature values in payload packets are transmitted as fixed-point integers (e.g. value multiplied by 10 or 100):
  * `Temperature (°C) = Raw Integer / 10.0` or `Raw Integer / 100.0`
* Supports Celsius (`°C`) to Fahrenheit (`°F`) conversion:
  * `°F = (°C * 1.8) + 32`
