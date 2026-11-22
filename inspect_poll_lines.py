with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'function pollLiveTelemetryChanges' in l:
        print(f"Found on line {i+1}")
        for j in range(i, min(len(lines), i+150)):
            print(f"{j+1}: {lines[j]}", end='')
        break
