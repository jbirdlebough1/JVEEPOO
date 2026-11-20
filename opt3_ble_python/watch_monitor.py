"""
Veepoo Model 5010 Clinical Telemetry Monitor & BLE Daemon
Multi-Metric Protocol Decoders, Interactive CLI Transponder, & SQLite/JSONL Persistence
"""

import asyncio
import datetime
import json
import os
import sqlite3
import sys
import time
from bleak import BleakScanner, BleakClient

# Target Device Parameters
TARGET_NAME = "vee"
TARGET_MAC = "8B:DE:40:83:F6:16"

# Veepoo 128-bit Custom GATT UUIDs
VEEPOO_SERVICE_UUID = "f0080001-0451-4000-b000-000000000000"
VEEPOO_NOTIFY_UUID  = "f0080002-0451-4000-b000-000000000000"
VEEPOO_WRITE_UUID   = "f0080003-0451-4000-b000-000000000000"

DB_PATH = os.path.join(os.path.dirname(__file__), "telemetry.db")
JSONL_PATH = os.path.join(os.path.dirname(__file__), "telemetry_stream.jsonl")

# Non-blocking Windows Console Input Helper
try:
    import msvcrt
    WINDOWS_KEYBOARD = True
except ImportError:
    WINDOWS_KEYBOARD = False


class TelemetryDatabase:
    """Manages SQLite and JSONL offline persistence."""

    def __init__(self, db_path: str, jsonl_path: str):
        self.db_path = db_path
        self.jsonl_path = jsonl_path
        self.conn = sqlite3.connect(self.db_path, check_same_thread=False)
        self._init_db()

    def _init_db(self):
        cursor = self.conn.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS raw_packets (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                timestamp TEXT NOT NULL,
                direction TEXT NOT NULL,
                opcode TEXT NOT NULL,
                hex_data TEXT NOT NULL,
                byte_length INTEGER NOT NULL
            )
        """)
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS vital_measurements (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                timestamp TEXT NOT NULL,
                metric TEXT NOT NULL,
                value1 REAL,
                value2 REAL,
                unit TEXT,
                progress INTEGER,
                status TEXT,
                mode TEXT,
                raw_hex TEXT
            )
        """)
        self.conn.commit()

    def log_packet(self, direction: str, opcode: str, hex_data: str, length: int):
        now_iso = datetime.datetime.now().isoformat()
        try:
            cursor = self.conn.cursor()
            cursor.execute(
                "INSERT INTO raw_packets (timestamp, direction, opcode, hex_data, byte_length) VALUES (?, ?, ?, ?, ?)",
                (now_iso, direction, opcode, hex_data, length)
            )
            self.conn.commit()
        except Exception as e:
            print(f"[DB Error] Packet log failed: {e}")

    def log_vital(self, metric: str, value1: float, value2: float, unit: str, progress: int, status: str, mode: str, raw_hex: str):
        now_iso = datetime.datetime.now().isoformat()
        vital_record = {
            "timestamp": now_iso,
            "metric": metric,
            "value1": value1,
            "value2": value2,
            "unit": unit,
            "progress": progress,
            "status": status,
            "mode": mode,
            "raw_hex": raw_hex
        }
        try:
            cursor = self.conn.cursor()
            cursor.execute(
                """INSERT INTO vital_measurements 
                   (timestamp, metric, value1, value2, unit, progress, status, mode, raw_hex) 
                   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""",
                (now_iso, metric, value1, value2, unit, progress, status, mode, raw_hex)
            )
            self.conn.commit()

            # Append to JSONL stream
            with open(self.jsonl_path, "a", encoding="utf-8") as f:
                f.write(json.dumps(vital_record) + "\n")
        except Exception as e:
            print(f"[DB Error] Vital log failed: {e}")


# Initialize Global Persistence Store
db = TelemetryDatabase(DB_PATH, JSONL_PATH)


class ProtocolParser:
    """Decompiled Veepoo Protocol Packet Parser & Clinical Translators."""

    @staticmethod
    def parse(data: bytearray):
        if len(data) == 0:
            return

        hex_str = " ".join(f"{b:02X}" for b in data)

        # Ignore 1-second system timer heartbeat tick
        if hex_str == "01 00 00 00":
            return

        opcode = f"0x{data[0]:02X}"
        db.log_packet("RX", opcode, hex_str, len(data))

        header = data[0]

        # 0xA1: Handshake, Authentication & Firmware Version
        if header == 0xA1 and len(data) >= 10:
            status_byte = data[3]
            dev_num = (data[4] << 8) | data[5]
            version = f"{data[6]}.{data[7]}.{data[8]}.{data[9]}"
            status_desc = "VERIFIED / LINKED" if status_byte == 0 else f"STATUS_CODE_{status_byte}"
            print(f"\n[★ AUTH / HANDSHAKE 0xA1]")
            print(f"    Transponder Model: Model {dev_num}")
            print(f"    Firmware Version : v{version}")
            print(f"    GATT Link Status : {status_desc}")
            db.log_vital("AUTH", dev_num, float(f"{data[6]}.{data[7]}"), "version", 100, status_desc, "AUTH", hex_str)

        # 0xA0: Battery Gauge & Charging State
        elif header == 0xA0 and len(data) >= 7:
            bars = data[4]
            pct_flag = data[5]
            pct = data[6] if pct_flag == 1 else (bars * 25)
            charging = "CHARGING" if data[3] == 1 else "DISCHARGING"
            low_bat = "LOW BATTERY ALERT" if len(data) > 7 and data[7] == 2 else "NOMINAL"
            print(f"\n[🔋 POWER MATRIX 0xA0]")
            print(f"    Battery Level: {pct}% ({bars}/4 Bars) | State: {charging} | Health: {low_bat}")
            db.log_vital("BATTERY", pct, bars, "%", 100, charging, "SYSTEM", hex_str)

        # 0xA8: 32-bit Hardware Pedometer & Triaxial Accelerometer
        elif header == 0xA8 and len(data) >= 5:
            steps = (data[1] << 24) | (data[2] << 16) | (data[3] << 8) | data[4]
            x, y, z = 0, 0, 0
            if len(data) >= 14:
                x = (data[6] | (data[11] << 8))
                y = (data[7] | (data[12] << 8))
                z = (data[8] | (data[13] << 8))
            km = round(steps * 0.0007, 2)
            kcal = round(steps * 0.04, 1)
            print(f"\n[🏃 ERGONOMICS & ACTIVITY 0xA8]")
            print(f"    Accumulated Steps: {steps:,} steps | Est Distance: {km} km | Caloric Burn: {kcal} kcal")
            if len(data) >= 14:
                print(f"    Triaxial Accelerometer: X={x} | Y={y} | Z={z}")
            db.log_vital("PEDOMETER", steps, kcal, "steps", 100, "ACTIVE", "32BIT_REG", hex_str)

        # 0x87: Dual-Sensor Thermal Telemetry (Body Core & Skin Contact)
        elif header == 0x87 and len(data) >= 7:
            progress = data[4]
            raw_body = (data[6] << 8) | data[5]
            body_c = raw_body / 10.0
            body_f = (body_c * 1.8) + 32.0

            raw_skin = 0
            skin_f = body_f
            if len(data) >= 9:
                raw_skin = (data[8] << 8) | data[7]
                skin_c = raw_skin / 10.0
                skin_f = (skin_c * 1.8) + 32.0

            fever_str = "FEVER" if body_f >= 100.4 else ("HYPOTHERMIA" if body_f < 95.0 else "NOMINAL EU-THERMIC")
            print(f"\n[🌡️ DUAL-SENSOR THERMAL MATRIX 0x87] Progress: {progress}%")
            print(f"    Body Core Temp    : {body_f:.1f}°F ({body_c:.1f}°C) [{fever_str}]")
            if len(data) >= 9:
                print(f"    Skin Contact Plate: {skin_f:.1f}°F ({skin_c:.1f}°C)")
            db.log_vital("TEMPERATURE", body_f, skin_f, "°F", progress, fever_str, "DUAL_SENSOR", hex_str)

        # 0x89: Optical Blood Glucose & Calibration Matrix
        elif header == 0x89 and len(data) >= 5:
            sub = data[1]
            if sub == 0x01 and len(data) >= 7:  # Scan Progress Packet
                progress = data[4]
                raw_word = (data[6] << 8) | data[5]
                glucose_mmol = (raw_word & 0x1FFF) / 100.0
                glucose_mgdl = round(glucose_mmol * 18.0182)
                risk_code = (raw_word & 0xE000) >> 13
                risk_labels = ["Normal / Glycemic Target", "Mild Elevation", "Moderate Impaired Glycemia", "Severe Glycemic Excursion"]
                risk_str = risk_labels[risk_code] if risk_code < len(risk_labels) else "Normal"
                ea1c = round((glucose_mgdl + 46.7) / 28.7, 1)

                print(f"\n[🩸 HEMODYNAMIC GLUCOSE 0x89] Progress: {progress}%")
                print(f"    Blood Glucose     : {glucose_mgdl} mg/dL ({glucose_mmol:.2f} mmol/L)")
                print(f"    Clinical Stratum  : {risk_str}")
                print(f"    Calculated eA1C   : {ea1c}% (ADAG Reference Formula)")
                db.log_vital("GLUCOSE", glucose_mgdl, ea1c, "mg/dL", progress, risk_str, "OPTICAL_PPG", hex_str)

            elif sub == 0x02 and len(data) >= 6:  # Stored Calibration Baseline Packet
                lo = data[4]
                hi = data[5]
                is_open = data[6] if len(data) > 6 else 1
                raw_val = (hi << 8) | lo
                cal_mmol = raw_val / 100.0
                cal_mgdl = round(cal_mmol * 18.0182)
                mode_str = "PRIVATE CALIBRATED" if is_open == 1 else "PUBLIC DEFAULT"
                print(f"\n[⚙️ GLUCOSE CALIBRATION REGISTRY 0x89-02]")
                print(f"    Stored Reference  : {cal_mgdl} mg/dL ({cal_mmol:.2f} mmol/L)")
                print(f"    Operating Mode    : {mode_str}")
                db.log_vital("CALIBRATION", cal_mgdl, is_open, "mg/dL", 100, mode_str, "REGISTRY", hex_str)

        # 0x90: Cardiovascular Blood Pressure
        elif header == 0x90 and len(data) >= 5:
            sys_bp = data[1]
            dia_bp = data[2]
            progress = data[3]
            status_code = data[4]
            status_map = {
                0: "MEASUREMENT OK / NOMINAL",
                252: "CONTACT WARNING: SENSOR OFF WRIST",
                253: "BATTERY CHARGING",
                254: "LOW BATTERY PREVENTED BP MEASUREMENT",
                255: "TRANSDUCER BUSY"
            }
            status_str = status_map.get(status_code, f"CODE_{status_code}")
            map_bp = round((2 * dia_bp + sys_bp) / 3, 1) if (sys_bp > 0 and dia_bp > 0) else 0

            print(f"\n[💓 CARDIOVASCULAR BLOOD PRESSURE 0x90] Progress: {progress}%")
            print(f"    Ambulatory BP     : {sys_bp}/{dia_bp} mmHg")
            if map_bp > 0:
                print(f"    Mean Arterial Press: {map_bp} mmHg")
            print(f"    Transducer Status : {status_str}")
            db.log_vital("BLOOD_PRESSURE", sys_bp, dia_bp, "mmHg", progress, status_str, "AMBULATORY", hex_str)

        # 0x80: Nocturnal SpO2 & Respiration Rate
        elif header == 0x80 and len(data) >= 4:
            spo2_val = data[3]
            is_checking = (data[4] in (1, 2)) if len(data) > 4 else False
            progress = data[5] if len(data) > 5 else 100
            resp_rate = data[7] if len(data) >= 8 and data[6] == 6 else 0

            hypoxia_str = "CRITICAL HYPOXIA (<85%)" if spo2_val < 85 else ("MILD HYPOXIA (<90%)" if spo2_val < 90 else "OPTIMAL (>95%)")
            print(f"\n[🫁 RESPIRATORY SPO2 & APNEA 0x80] Progress: {progress}%")
            print(f"    Arterial Oxygen   : {spo2_val}% [{hypoxia_str}]")
            if resp_rate > 0:
                print(f"    Respiration Rate  : {resp_rate} breaths/min")
            db.log_vital("SPO2", spo2_val, resp_rate, "%", progress, hypoxia_str, "PULSE_OX", hex_str)

        # 0xD0: Real-Time Heart Rate (BPM)
        elif header == 0xD0 and len(data) >= 2:
            bpm = data[1]
            status_code = data[5] if len(data) >= 6 else 0
            status_str = "OFF WRIST" if bpm in (1, 2) else ("ACTIVE SIGNAL" if 30 <= bpm <= 220 else "SEARCHING")
            print(f"\n[❤️ CARDIOVASCULAR HEART RATE 0xD0]")
            print(f"    Pulse Rate        : {bpm} BPM | Signal: {status_str}")
            db.log_vital("HEART_RATE", bpm, 0, "BPM", 100, status_str, "OPTICAL_PPG", hex_str)

        # 0xB8: Hardware CustomSettings Bank
        elif header == 0xB8 and len(data) >= 20:
            bank = data[19]
            print(f"\n[🔧 HARDWARE CONFIGURATION BANK 0xB8] Bank: {bank}")
            print(f"    Raw Byte Dump: {hex_str}")
            db.log_vital("CONFIG_BANK", bank, 0, "bank", 100, f"BANK_{bank}", "CUSTOM_SETTING", hex_str)

        else:
            print(f"\n[RX {opcode}] {hex_str}")


class WatchTransceiver:
    """Manages GATT connection, command execution, and interactive control loop."""

    def __init__(self, client: BleakClient, write_uuid: str):
        self.client = client
        self.write_uuid = write_uuid

    async def send_command(self, cmd_bytes: list, label: str):
        payload = bytearray(20)
        for i, b in enumerate(cmd_bytes):
            payload[i] = b
        hex_repr = " ".join(f"{b:02X}" for b in payload)
        print(f"\n[TX -> WATCH] {label}: {hex_repr}")
        db.log_packet("TX", f"0x{cmd_bytes[0]:02X}", hex_repr, len(payload))
        await self.client.write_gatt_char(self.write_uuid, payload, response=False)

    async def auth_and_time_sync(self):
        now = time.localtime()
        year = now.tm_year
        tz_offset = -int(time.timezone / 60 / 15) & 0xFF
        auth_pkt = [
            0xA1, 0x00, 0x00, 0x00,
            (year >> 8) & 0xFF, year & 0xFF,
            now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec,
            0x00, 0x01, tz_offset
        ]
        await self.send_command(auth_pkt, "Handshake & RTC Time Synchronization (0xA1)")

    # Clinical Scan Triggers
    async def start_glucose_private(self):
        await self.send_command([0x89, 0x01, 0x01, 0x01], "Start Calibrated Private Glucose Scan (0x89)")

    async def start_glucose_public(self):
        await self.send_command([0x89, 0x01, 0x01, 0x00], "Start Uncalibrated Public Glucose Scan (0x89)")

    async def stop_glucose(self):
        await self.send_command([0x89, 0x01, 0x02, 0x01], "Halt Glucose Scan (0x89)")

    async def query_glucose_calibration(self):
        await self.send_command([0x89, 0x02, 0x02, 0x00, 0x00, 0x00], "Query Stored Glucose Calibration Registry (0x89-02)")

    async def start_temperature(self):
        await self.send_command([0x87, 0x01, 0x01], "Start Dual-Sensor Thermal Scan (0x87)")

    async def stop_temperature(self):
        await self.send_command([0x87, 0x01, 0x02], "Halt Dual-Sensor Thermal Scan (0x87)")

    async def start_blood_pressure_private(self):
        await self.send_command([0x90, 0x00, 0x01], "Start Ambulatory Blood Pressure Scan (0x90 Private)")

    async def start_blood_pressure_public(self):
        await self.send_command([0x90, 0x00, 0x00], "Start Ambulatory Blood Pressure Scan (0x90 Public)")

    async def stop_blood_pressure(self):
        await self.send_command([0x90, 0x01, 0x01], "Halt Blood Pressure Scan (0x90)")

    async def start_spo2(self):
        await self.send_command([0x80, 0x01, 0x02], "Start Nocturnal SpO2 & Respiration Scan (0x80)")

    async def stop_spo2(self):
        await self.send_command([0x80, 0x02, 0x02], "Halt SpO2 Scan (0x80)")

    async def start_heart_rate(self):
        await self.send_command([0xD0, 0x01], "Start Continuous Optical Heart Rate Scan (0xD0)")

    async def stop_heart_rate(self):
        await self.send_command([0xD0, 0x02], "Halt Heart Rate Scan (0xD0)")

    async def query_battery(self):
        await self.send_command([0xA0, 0x00], "Query Battery & Power Registry (0xA0)")

    async def query_steps(self):
        await self.send_command([0xA8, 0x00], "Query 32-bit Step Counter & Accelerometer (0xA8)")

    async def query_config_bank0(self):
        await self.send_command([0xB8, 0x01, 0x00], "Read Hardware CustomSetting Bank 0 (0xB8)")

    async def query_config_bank1(self):
        await self.send_command([0xB8, 0x01, 0x01], "Read Hardware CustomSetting Bank 1 (0xB8)")


def print_cli_menu():
    print("""
======================================================================
  MODEL 5010 CLINICAL TELEMETRY MONITOR • COMMAND CONTROL MATRIX
======================================================================
  [g] Start Glucose Scan (Private/Calibrated Mode)
  [G] Start Glucose Scan (Public/Generic Mode)
  [x] Halt Glucose Scan
  [c] Query Stored Glucose Calibration Reference
  --------------------------------------------------------------------
  [t] Start Dual-Sensor Thermal Scan (Body Core & Skin Surface)
  [T] Halt Temperature Scan
  --------------------------------------------------------------------
  [b] Start Blood Pressure Scan (Private Calibrated Mode)
  [B] Halt Blood Pressure Scan
  --------------------------------------------------------------------
  [o] Start Arterial SpO2 & Respiration Rate Scan
  [O] Halt SpO2 Scan
  --------------------------------------------------------------------
  [h] Start Continuous Heart Rate Scan (BPM)
  [H] Halt Heart Rate Scan
  --------------------------------------------------------------------
  [y] Query Battery & Charging Registry (0xA0)
  [s] Query 32-Bit Pedometer & Triaxial Accelerometer (0xA8)
  [1] Query Hardware CustomSettings Bank 0 (0xB8)
  [2] Query Hardware CustomSettings Bank 1 (0xB8)
  --------------------------------------------------------------------
  [?] Display this Control Menu
  [q] Disconnect & Exit Monitor Daemon
======================================================================
""")


async def keyboard_control_loop(transceiver: WatchTransceiver, stop_event: asyncio.Event):
    """Listens for user single-key commands without blocking BLE callbacks."""
    if not WINDOWS_KEYBOARD:
        return

    while not stop_event.is_set():
        if msvcrt.kbhit():
            ch = msvcrt.getch()
            try:
                key = ch.decode("utf-8")
            except UnicodeDecodeError:
                key = ""

            if key == "g":
                await transceiver.start_glucose_private()
            elif key == "G":
                await transceiver.start_glucose_public()
            elif key == "x":
                await transceiver.stop_glucose()
            elif key == "c":
                await transceiver.query_glucose_calibration()
            elif key == "t":
                await transceiver.start_temperature()
            elif key == "T":
                await transceiver.stop_temperature()
            elif key == "b":
                await transceiver.start_blood_pressure_private()
            elif key == "B":
                await transceiver.stop_blood_pressure()
            elif key == "o":
                await transceiver.start_spo2()
            elif key == "O":
                await transceiver.stop_spo2()
            elif key == "h":
                await transceiver.start_heart_rate()
            elif key == "H":
                await transceiver.stop_heart_rate()
            elif key == "y":
                await transceiver.query_battery()
            elif key == "s":
                await transceiver.query_steps()
            elif key == "1":
                await transceiver.query_config_bank0()
            elif key == "2":
                await transceiver.query_config_bank1()
            elif key == "?":
                print_cli_menu()
            elif key == "q":
                print("\n[*] Disconnecting by user command...")
                stop_event.set()
                break

        await asyncio.sleep(0.05)


def notification_callback(sender, data: bytearray):
    ProtocolParser.parse(data)


async def connect_and_monitor(device):
    print(f"\n[+] Connecting to {device.name} ({device.address})...")
    async with BleakClient(device) as client:
        print("[+] GATT Connection Established Successfully!")

        notify_uuid = VEEPOO_NOTIFY_UUID
        write_uuid = VEEPOO_WRITE_UUID

        # Dynamically discover services if custom UUID varies
        for s in client.services:
            for c in s.characteristics:
                if "notify" in c.properties or "indicate" in c.properties:
                    notify_uuid = c.uuid
                if "write" in c.properties or "write-without-response" in c.properties:
                    write_uuid = c.uuid

        print(f"[+] Subscribing to Clinical Notifications: {notify_uuid}")
        await client.start_notify(notify_uuid, notification_callback)

        transceiver = WatchTransceiver(client, write_uuid)

        # Send initial authentication & RTC clock sync
        await transceiver.auth_and_time_sync()
        await asyncio.sleep(0.5)

        # Initial queries for battery & steps
        await transceiver.query_battery()
        await asyncio.sleep(0.2)
        await transceiver.query_steps()
        await asyncio.sleep(0.2)
        await transceiver.query_glucose_calibration()

        print_cli_menu()

        stop_event = asyncio.Event()
        kb_task = asyncio.create_task(keyboard_control_loop(transceiver, stop_event))

        while client.is_connected and not stop_event.is_set():
            await asyncio.sleep(0.5)

        kb_task.cancel()
        print("[*] Session terminated cleanly.")


async def main():
    print("=" * 70)
    print("  MODEL 5010 CLINICAL TELEMETRY DAEMON & BLE TRANSLATOR")
    print(f"  Target Transponder: '{TARGET_NAME.upper()}' ({TARGET_MAC})")
    print(f"  Persistence SQLite: {DB_PATH}")
    print(f"  Persistence JSONL : {JSONL_PATH}")
    print("=" * 70)

    while True:
        print(f"[*] Scanning for transponder advertisement (5s window)...")
        device = await BleakScanner.find_device_by_filter(
            lambda d, ad: (d.name and TARGET_NAME in d.name.lower()) or (d.address and d.address.upper() == TARGET_MAC),
            timeout=5.0
        )
        if device:
            print(f"[+] Transponder identified! Initiating secure GATT bridge...")
            try:
                await connect_and_monitor(device)
            except Exception as e:
                print(f"[-] Session interrupted: {e}")
            print("[*] Scanning resumes for subsequent transponder wakeup...")
        else:
            print("    [.] In standby. Illuminate watch face or press crown button to ping...")
            await asyncio.sleep(1.0)


if __name__ == "__main__":
    try:
        asyncio.run(main())
    except KeyboardInterrupt:
        print("\n[*] Monitor stopped by user signal.")
