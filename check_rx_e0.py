with open(r'D:/veepoo/w11_htm_app/packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

rx_e0 = [l.strip() for l in lines if 'rx' in l.lower() and ('e0 ' in l.lower() or ': e0' in l.lower())]
print(f"Total RX E0 packets: {len(rx_e0)}")
for l in rx_e0[:15]:
    print(" ", l)
