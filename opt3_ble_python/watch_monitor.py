"""
Continuous Veepoo Watch Connector & Monitor
Continuously listens for watch advertising packets, connects upon wakeup,
and reads device details, battery, and real-time telemetry.
"""

import asyncio
import sys
import time
from bleak import BleakScanner, BleakClient

TARGET_NAME = "vee"
TARGET_MAC = "8B:DE:40:83:F6:16"

# Veepoo Protocol Constants
VEEPOO_SERVICE = "f0080001-0451-4000-b000-000000000000"
NOTIFY_CHAR = "f0080002-0451-4000-b000-000000000000"
WRITE_CHAR = "f0080003-0451-4000-b000-000000000000"


def notification_handler(sender, data: bytearray):
    hex_str = " ".join(f"{b:02X}" for b in data)
    if hex_str == "01 00 00 00":
        return  # Ignore 1s heartbeat tick

    print(f"\n[RX] {hex_str}")

    if len(data) == 0:
        return

    header = data[0]

    # Handshake / Time Sync Response (0xA1)
    if header == 0xA1 and len(data) >= 10:
        status_byte = data[3]
        dev_num = (data[4] << 8) | data[5]
        version = f"{data[6]}.{data[7]}.{data[8]}.{data[9]}"
        print(f"  [★ AUTH SUCCESS] Model: {dev_num} | Firmware: v{version} | Status: {status_byte}")

    # Temperature Telemetry (0x87)
    elif header == 0x87 and len(data) >= 9:
        progress = data[4]
        raw_body = (data[6] << 8) | data[5]
        raw_skin = (data[8] << 8) | data[7]
        body_c = raw_body / 10.0
        skin_c = raw_skin / 10.0
        body_f = (body_c * 1.8) + 32.0
        skin_f = (skin_c * 1.8) + 32.0
        print(f"  [🌡️ Body Temp] Progress: {progress}% | Core: {body_f:.1f}°F ({body_c:.1f}°C) | Skin Contact: {skin_f:.1f}°F ({skin_c:.1f}°C)")

    # Blood Glucose Telemetry (0x89)
    elif header == 0x89 and len(data) >= 7:
        progress = data[4]
        raw_word = (data[6] << 8) | data[5]
        glucose_mmol = (raw_word & 0x1FFF) / 100.0
        glucose_mgdl = round(glucose_mmol * 18.0182)
        risk_code = (raw_word & 0xE000) >> 13
        risk_labels = ["Normal", "Low", "Moderate Risk", "High Risk"]
        risk_str = risk_labels[risk_code] if risk_code < len(risk_labels) else "Normal"
        print(f"  [🩸 Blood Glucose] Progress: {progress}% | Value: {glucose_mgdl} mg/dL ({glucose_mmol:.2f} mmol/L) | Risk: {risk_str}")


async def connect_and_monitor(device):
    print(f"\n[+] Connecting to {device.name} ({device.address})...")
    async with BleakClient(device) as client:
        print("[+] CONNECTED! GATT connection established.")

        # Find notify characteristic
        notify_char = None
        write_char = None
        for s in client.services:
            for c in s.characteristics:
                if "notify" in c.properties or "indicate" in c.properties:
                    notify_char = c.uuid
                if "write" in c.properties or "write-without-response" in c.properties:
                    write_char = c.uuid

        if notify_char:
            print(f"[+] Subscribing to notifications on: {notify_char}")
            await client.start_notify(notify_char, notification_handler)

        # Send 0xA1 Handshake to unlock watch
        if write_char:
            print(f"[+] Sending Authentication & Clock Sync (0xA1) on: {write_char}")
            now = time.localtime()
            year = now.tm_year
            tz_offset = -int(time.timezone / 60 / 15) & 0xFF
            handshake = bytearray(20)
            handshake[0] = 0xA1
            handshake[1] = 0x00
            handshake[2] = 0x00
            handshake[3] = 0x00
            handshake[4] = (year >> 8) & 0xFF
            handshake[5] = year & 0xFF
            handshake[6] = now.tm_mon
            handshake[7] = now.tm_mday
            handshake[8] = now.tm_hour
            handshake[9] = now.tm_min
            handshake[10] = now.tm_sec
            handshake[11] = 0x00
            handshake[12] = 0x01
            handshake[13] = tz_offset
            await client.write_gatt_char(write_char, handshake, response=False)

        print("\n[*] Ready! Commands available:")
        print("    - Press Ctrl+C in this terminal to disconnect.\n")

        # Keep session active
        while client.is_connected:
            await asyncio.sleep(1.0)


async def main():
    print("=" * 65)
    print("  Veepoo / H Band Continuous BLE Monitor")
    print(f"  Targeting device: '{TARGET_NAME.upper()}' ({TARGET_MAC})")
    print("  Tap the watch screen or press the side button to illuminate it...")
    print("=" * 65)

    while True:
        print(f"[*] Scanning (5s window)...")
        device = await BleakScanner.find_device_by_filter(
            lambda d, ad: (d.name and TARGET_NAME in d.name.lower()) or (d.address and d.address.upper() == TARGET_MAC),
            timeout=5.0
        )
        if device:
            print(f"[+] Watch detected! Signal strength: RSSI")
            try:
                await connect_and_monitor(device)
            except Exception as e:
                print(f"[-] Connection ended: {e}")
            print("[*] Resuming scan for next wakeup...")
        else:
            print("    [.] No advertisement seen. Tap watch screen to wake up...")
            await asyncio.sleep(1.0)


if __name__ == "__main__":
    try:
        asyncio.run(main())
    except KeyboardInterrupt:
        print("\n[*] Monitor stopped by user.")
