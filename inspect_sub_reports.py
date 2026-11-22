with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i, line in enumerate(lines):
    if 'boxSub' in line or 'SUB-REPORT' in line or 'MET' in line and 'box' in line:
        print(f"L{i+1}: {line}")
