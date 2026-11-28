"""
Veepoo / H Band BLE Device Prober (Windows 11 WinRT Optimized)
Finds the target BLEDevice object and connects to enumerate GATT services & characteristics.
"""

import asyncio
import sys
from bleak import BleakScanner, BleakClient

TARGET_MAC = sys.argv[1] if len(sys.argv) > 1 else "8B:DE:40:83:F6:16"


async def probe(mac: str):
    print(f"[*] Scanning to locate watch ({mac}). Keep watch screen awake...")
    
    device = await BleakScanner.find_device_by_address(mac, timeout=15.0)
    if not device:
        print(f"[-] Could not find advertisement for {mac}. Trying search by name 'VEE'...")
        device = await BleakScanner.find_device_by_filter(
            lambda d, ad: (d.name and "vee" in d.name.lower()) or (ad.local_name and "vee" in ad.local_name.lower()),
            timeout=10.0
        )

    if not device:
        print("[-] Watch not found in advertising window. Ensure screen is illuminated.")
        return

    print(f"[+] Found device: {device.name} ({device.address}). Connecting...")
    try:
        async with BleakClient(device, timeout=20.0) as client:
            print(f"[+] Successfully connected to {device.name}!")
            print("\n[*] Enumerating GATT Services and Characteristics:")
            print("=" * 70)
            
            for service in client.services:
                print(f"\n[Service] {service.uuid} ({service.description})")
                for char in service.characteristics:
                    props = ", ".join(char.properties)
                    val_str = ""
                    if "read" in char.properties:
                        try:
                            val = await client.read_gatt_char(char.uuid)
                            try:
                                ascii_val = val.decode('utf-8', errors='ignore').strip()
                                val_str = f" | Value: '{ascii_val}' (Hex: {val.hex()})"
                            except Exception as dec_err:
                                print(f"[MIL-SPEC HANDLED FAULT] ASCII decode fallback on {char.uuid}: {dec_err}")
                                val_str = f" | Hex: {val.hex()}"
                        except Exception as e:
                            print(f"[MIL-SPEC HANDLED FAULT] GATT read failed on {char.uuid}: {e}")
                            val_str = f" | (Read error: {e})"
                    print(f"   [Char] {char.uuid} [{props}]{val_str}")

            print("\n[+] Service enumeration complete!")
    except Exception as e:
        print(f"[-] GATT Connection error: {e}")


if __name__ == "__main__":
    asyncio.run(probe(TARGET_MAC))
