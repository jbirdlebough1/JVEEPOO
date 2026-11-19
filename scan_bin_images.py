with open(r"D:\veepoo\stock_dials\C006.bin", "rb") as f:
    data = f.read()

print("File size:", len(data))

# Check for image signatures like PNG, JPG, or RGB565 / raw bitmap tables
pos = 0x24
# At 0x24: 27 27 00 00 -> 0x00002727 = 10023? or length of header table?
import struct
h_len = struct.unpack("<I", data[0x24:0x28])[0]
print(f"Header length at 0x24: {h_len} (0x{h_len:x})")

# Let's inspect data at 0x28 + h_len
next_pos = 0x28 + h_len
print(f"Next section at 0x{next_pos:04x}: {data[next_pos:next_pos+32].hex()}")

# Scan for any PNG (89 50 4E 47), JPEG (FF D8 FF), or bitmap tables
for i in range(0, len(data)-8):
    if data[i:i+4] == b'\x89PNG':
        print(f"Found PNG at 0x{i:06x}")
    elif data[i:i+3] == b'\xff\xd8\xff':
        print(f"Found JPEG at 0x{i:06x}")
    elif data[i:i+2] == b'BM':
        print(f"Found BMP at 0x{i:06x}")
