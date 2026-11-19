# Veepoo / H Band Firmware, Chipset & DFU Research

## 1. Supported Chipset Platforms

Veepoo (Shenzhen Veepoo Technology) white-labels and manufactures hardware powered by several distinct System-on-Chip (SoC) architectures. The official `HBandSDK` bundles platform-specific DFU/OTA binary drivers depending on which microcontroller family is detected:

### A. Nordic Semiconductor (e.g., nRF52832, nRF52840)
* **Architecture:** ARM Cortex-M4F with integrated 2.4 GHz BLE transceiver.
* **Firmware Update Mechanism:** Standard Nordic DFU / McuManager (`MCUmgr`).
* **Protocol & Transport:** Uses Nordic BLE DFU Service UUID (`00001530-1212-EFDE-1523-785FEABCD123` or newer MCUmgr SMP service `8D53DC1D-1DB7-4CD3-868B-8A527460AA84`).
* **SDK Dependencies:**
  * `no.nordicsemi.android:mcumgr-core`
  * `no.nordicsemi.android:mcumgr-ble`
  * `no.nordicsemi.android.support.v18:scanner`
* **Flashing Tools:** `nrfutil`, `mcumgr-cli`, or Nordic nRF Connect desktop/mobile app.

### B. JieLi / JL (e.g., JL7012, JL7013 series)
* **Architecture:** Proprietary JieLi 32-bit RISC core. Extremely popular in current generation Veepoo smartwatches with Bluetooth phone call audio and AMOLED displays.
* **Firmware Update Mechanism:** JieLi RCSP (Remote Control Service Protocol) and JL OTA.
* **Protocol & Transport:** Custom JieLi SPP / BLE GATT service.
* **SDK Dependencies:**
  * `JL_Watch.aar`
  * `jl_rcsp.aar`
  * `jl_bt_ota.aar`
  * `BmpConvert` (for custom dial/watch-face conversions).

### C. Bluetrum / Zhongke Lanxun
* **Architecture:** 32-bit RISC-V or proprietary DSP cores.
* **Firmware Update Mechanism:** Bluetrum proprietary OTA toolset.
* **SDK Dependencies:** `abpartool-release.aar`.

### D. Goodix (e.g., GR5515 series)
* **Architecture:** ARM Cortex-M4F low-power wearable SoC.
* **Firmware Update Mechanism:** Goodix Fast DFU.
* **SDK Dependencies:** `libcomx`, `libble`, `libdfu`, `libfastdfu`.

---

## 2. Firmware Upgrade (OTA/DFU) Workflow

The Veepoo upgrade lifecycle follows a 3-step sequence:
1. **Device Identification & Version Handshake:**
   * App invokes `getDeviceTestVersion()` or `checkVersionAndFile()`.
   * Watch reports hardware model ID, software firmware version, and target chip vendor tag.
2. **Reboot into Bootloader / Upgrade Mode:**
   * App transmits vendor command to enter OAD/DFU mode.
   * Device resets BLE advertising with a DFU-specific device name or MAC increment.
3. **Firmware Chunk Transport:**
   * App triggers `findOadDevice()`.
   * The appropriate vendor engine (McuManager for Nordic, RCSP for JieLi) streams partitioned binary firmware chunks with packet acknowledgments and CRC checks.

---

## 3. Implications for Custom Firmware / Reverse Engineering

* If the watch uses **Nordic nRF52xxx**, standard SWD debugging (J-Link / ST-Link / CMSIS-DAP) and OpenOCD can be used if physical SWD test pads are accessible on the PCB.
* If the watch uses **JieLi JL7013**, firmware images are packaged in proprietary `.bfu` / `.fw` format with encrypted or segmented loaders. DFU analysis requires inspecting the decompiled `jl_bt_ota.aar` and RCSP command wrappers.
