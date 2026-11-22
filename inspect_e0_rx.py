with open('D:/veepoo/w11_htm_app/packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

rx_e0 = [l.strip() for l in lines if 'RX' in l and ('e0 ' in l or 'e0:' in l or 'e0' in l.lower().split()[-1:] or '[SOMNO' in l or 'Sleep' in l)]
print(f"Found {len(rx_e0)} matching RX lines:")
for l in rx_e0[:30]:
    print("  ", l)

# Also check for "Transmitting Opcode 0xE0"
tx_e0 = [l.strip() for l in lines if '0xE0' in l]
print(f"\nFound {len(tx_e0)} 0xE0 lines:")
for l in tx_e0[:20]:
    print("  ", l)
