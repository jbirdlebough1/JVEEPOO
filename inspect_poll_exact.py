with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'async function pollLiveTelemetryChanges' in l:
        print(f"Start on line {i+1}")
        for j in range(i, min(len(lines), i+185)):
            print(f"{j+1}: {lines[j]}", end='')
        break
