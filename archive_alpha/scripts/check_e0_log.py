with open(r'D:/veepoo/w11_htm_app/packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

e0_lines = [l.strip() for l in lines if 'e0' in l.lower() or 'E0' in l]
print(f"Total E0 packet log lines found: {len(e0_lines)}")
for l in e0_lines[:20]:
    print(" ", l)
