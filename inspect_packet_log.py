with open('D:/veepoo/w11_htm_app/packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

print(f"Total packet lines: {len(lines)}")
e0_lines = [line for line in lines if 'E0' in line or 'e0' in line]
print(f"E0 packet lines: {len(e0_lines)}")
for l in e0_lines[:20]:
    print("  ", l.strip())

a8_lines = [line for line in lines if 'A8' in line or 'a8' in line or 'pedometer' in line.lower() or 'step' in line.lower()]
print(f"A8 / step lines: {len(a8_lines)}")
for l in a8_lines[:10]:
    print("  ", l.strip())
