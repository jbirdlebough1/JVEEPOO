import os
import struct

with open(r"D:\veepoo\stock_dials\C006.bin", "rb") as f:
    data = f.read()

print(f"Total file size: {len(data)} bytes")
print("First 128 bytes:")
for i in range(0, 128, 16):
    chunk = data[i:i+16]
    hex_str = " ".join(f"{b:02x}" for b in chunk)
    ascii_str = "".join(chr(b) if 32 <= b < 127 else "." for b in chunk)
    print(f"  0x{i:04x}: {hex_str:<48}  {ascii_str}")

# Look at header fields
# Magic: 6 bytes VEEPOO
magic = data[:6]
print("Magic:", magic)

# Search for common patterns: image tables, coordinate descriptors, fonts
print("\nScanning for markers and sub-headers...")
pos = 6
while pos < 512:
    if data[pos:pos+4] != b'\xff\xff\xff\xff' and data[pos:pos+4] != b'\x00\x00\x00\x00':
        print(f"Non-pad data at 0x{pos:04x}: {data[pos:pos+16].hex()}")
        pos += 16
    else:
        pos += 4
