with open(r'D:\veepoo\w11_htm_app\packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

print(f"Total lines in packet_log.txt: {len(lines)}")
for l in lines:
    if ' c7 ' in l.lower() or 'c7:' in l.lower() or l.strip().startswith('C7') or ' 0xC7' in l:
        print(l.strip())
