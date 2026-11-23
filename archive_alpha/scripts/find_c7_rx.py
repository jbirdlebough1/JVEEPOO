with open(r'D:\veepoo\w11_htm_app\packet_log.txt', 'r', encoding='utf-8', errors='ignore') as f:
    lines = f.readlines()

for l in lines:
    if '[rx]' in l.lower() and ' c7 ' in l.lower():
        print(l.strip())
