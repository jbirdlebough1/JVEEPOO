"""
Veepoo / H Band BLE Scanner & Inspection Utility (Windows 11)
Lists ALL nearby BLE devices and highlights candidate Veepoo / H Band wearables.
"""

import asyncio
import sys

try:
    from bleak import BleakScanner, BleakClient
except ImportError:
    print("[!] 'bleak' is not installed.")
    sys.exit(1)


async def scan_all():
    print("[*] Starting broad BLE scan for 15 seconds. Ensure watch screen is awake and Bluetooth is on...")
    devices = await BleakScanner.discover(timeout=15.0, return_adv=True)

    print(f"\n[*] Scan Complete. Found {len(devices)} device(s):\n")
    print(f"{'#':<3} | {'Device Name':<30} | {'Address / MAC':<20} | {'RSSI':<6} | {'Details'}")
    print("-" * 90)

    idx = 1
    for address, (device, adv_data) in devices.items():
        name = device.name or adv_data.local_name or "<Unnamed/Hidden>"
        rssi = f"{adv_data.rssi} dBm"
        details = []
        if adv_data.service_uuids:
            details.append(f"UUIDs: {adv_data.service_uuids}")
        if adv_data.manufacturer_data:
            mf = {k: v.hex() for k, v in adv_data.manufacturer_data.items()}
            details.append(f"MfgData: {mf}")
        
        detail_str = "; ".join(details) if details else ""
        print(f"{idx:<3} | {name:<30} | {address:<20} | {rssi:<6} | {detail_str}")
        idx += 1


if __name__ == "__main__":
    asyncio.run(scan_all())
