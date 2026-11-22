with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if 'navTabFullScan' in line:
        for j in range(max(0, i-5), min(len(lines), i+20)):
            print(f"{j+1}: {lines[j]}", end='')
        break
