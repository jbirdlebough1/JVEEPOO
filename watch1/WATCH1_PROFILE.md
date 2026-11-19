# Watch 1 Hardware Profile & Telemetry

## 1. Discovered Hardware Identifiers

* **Device Local Name:** `VEE`
* **Bluetooth MAC Address:** `8B:DE:40:83:F6:16`
* **Manufacturer Data:** `{63736: '16f68340de8b'}`
  * Manufacturer ID: `0xF8F8` (63736)
  * Payload: Reverse MAC `16:F6:83:40:DE:8B`
* **Broadcast Service UUID:** `0000fee7-0000-1000-8000-00805f9b34fb` (Veepoo / WeChat Sports BLE Profile)
* **Nominal Signal Strength (RSSI):** `-59 dBm` (Excellent proximity)

---

## 2. Supported Sensor Capabilities & Handshakes

* **Optical Blood Glucose:** Supported via `0x89` command family.
* **Body Temperature:** Supported via `0x87` command family (Dual skin NTC + ambient algorithm).
* **Heart Rate, PPG, SpO2, ECG:** Supported via `0xB1` / `0xA1` packet channels.
