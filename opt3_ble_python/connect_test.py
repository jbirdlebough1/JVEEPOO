"""
Direct BLE Connection Tester with Automatic Retry & Diagnostic Output
"""

import asyncio
from bleak import BleakScanner, BleakClient

TARGET_MAC = "8B:DE:40:83:F6:16"


async def main():
    print(f"[*] Starting direct connection attempts to {TARGET_MAC}...")
    print("    [!] IMPORTANT: Keep tapping the watch screen so it does NOT sleep!\n")

    for attempt in range(1, 6):
        print(f"--- Attempt {attempt} of 5 ---")
        device = await BleakScanner.find_device_by_address(TARGET_MAC, timeout=5.0)
        if not device:
            print("[-] Watch advertisement not received in 5s window. Tap screen!")
            continue

        print(f"[+] Found watch (RSSI: {device.rssi} dBm). Connecting GATT...")
        try:
            async with BleakClient(device, timeout=12.0) as client:
                print(f"[+] SUCCESS! Connected = {client.is_connected}")
                print(f"[+] Services found: {len(client.services)}")
                for s in client.services:
                    print(f"    Service: {s.uuid}")
                    for c in s.characteristics:
                        print(f"      Char: {c.uuid} ({', '.join(c.properties)})")
                return
        except Exception as e:
            print(f"[-] Connection error on attempt {attempt}: {e}")
            await asyncio.sleep(1.0)

    print("\n[-] Could not establish GATT connection.")
    print("    Recommendation: Pair 'VEE' once in Windows 11 Settings (Settings > Bluetooth > Add Device).")


if __name__ == "__main__":
    asyncio.run(main())
