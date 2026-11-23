with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(18530, 18920):
    line = lines[i-1].rstrip()
    if any(k in line for k in ['function executeScan', 'function finalizeScan', 'fullScanResults.', 'last', 'final', 'const cur']):
        print(f"{i}: {line}")
