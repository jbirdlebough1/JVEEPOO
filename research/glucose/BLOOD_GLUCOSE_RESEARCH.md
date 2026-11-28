# Veepoo / H Band Blood Glucose Measurement Research

## 1. Measurement Methodology

Veepoo smartwatches estimate blood glucose using **non-invasive optical photoplethysmography (PPG)** combined with multi-wavelength LED sensors and proprietary algorithmic modeling:

1. **Optical PPG Multi-Wavelength Absorption:**
   * Uses green, red, and near-infrared (NIR) LEDs on the sensor array.
   * Blood glucose molecules alter light absorption and scattering characteristics across specific optical spectra during arterial pulsation.
2. **Pulse Waveform & Transit Analysis:**
   * Blood glucose concentration correlates with blood viscosity, arterial wall stiffness, and blood flow velocity.
   * Algorithms analyze the systolic/diastolic peak slope, dicrotic notch, and pulse transit time (PTT) when combined with ECG/PPG sync.
3. **Personal Calibration Requirement:**
   * Because skin tone, subcutaneous fat, and baseline physiology vary drastically, raw optical readings must be calibrated against invasive capillary fingerstick measurements (e.g. standard glucometer reading) to establish an offset curve.

---

## 2. Veepoo Protocol Blood Glucose APIs (`vpprotocol`)

In the decompiled Veepoo protocol (`com.veepoo.protocol.*`):

### Key Classes & Interfaces:
* `IBloodGlucoseDataListener`: Receives continuous and on-demand blood glucose packets.
* `BloodGlucoseData`: Data model holding parsed values:
  * `glucoseValue` (numeric value, typically in mmol/L or mg/dL)
  * `calibrationStatus` (whether user-specific calibration model is active)
  * `riskLevel` (normal, pre-diabetic risk, hyperglycemic, hypoglycemic)
  * `progress` (0% - 100% test completion)
* `VPOperateManager.getInstance().startDetectBloodGlucose()`: Triggers active real-time optical scan.
* `VPOperateManager.getInstance().stopDetectBloodGlucose()`: Terminates active scan.
* `VPOperateManager.getInstance().readBloodGlucoseData()`: Syncs historical 24-hour periodic glucose log entries.
* `VPOperateManager.getInstance().setBloodGlucoseMultiCalibration()`: Sets baseline personal reference points.

---

## 3. Data Representation & Conversions

* **Units:**
  * SI Standard: `mmol/L`
  * Conventional US: `mg/dL`
  * Conversion formula:
    * `mg/dL = mmol/L * 18.0182`
    * `mmol/L = mg/dL / 18.0182`
* **Packet Byte Structure (GATT payload):**
  * Typically mapped under the proprietary custom service write/notify characteristic.
  * Command byte identifier followed by status flags, integer parts, and decimal multipliers.
