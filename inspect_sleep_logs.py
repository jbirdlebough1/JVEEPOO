with open('D:/veepoo/w11_htm_app/packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

sleep_rx = [l.strip() for l in lines if 'SOMNO' in l or 'e0 ' in l.lower() or 'E0 ' in l]
print(f"Sleep related log lines: {len(sleep_rx)}")
for l in sleep_rx[:40]:
    print("  ", l)
