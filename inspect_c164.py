with open(r'D:\veepoo\stock_dials\shape4_240x280\C164.bin', 'rb') as f:
    header = f.read(128)
print("C164.bin first 128 bytes hex:")
print(" ".join(f"{b:02x}" for b in header[:64]))
print("ASCII:", "".join(chr(b) if 32 <= b < 127 else "." for b in header[:64]))

with open(r'D:\veepoo\add_hybrid_dial_studio.py', 'r', encoding='utf-8') as f:
    text = f.read()
print("\n=== add_hybrid_dial_studio.py ===")
print(text[:2000])
