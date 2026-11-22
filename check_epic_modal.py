import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

# Search for modalEpicUpload
for i, line in enumerate(lines):
    if 'modalEpicUpload' in line:
        print(f"modalEpicUpload at line {i+1}:")
        for j in range(i, min(i+50, len(lines))):
            print(f"  {j+1}: {lines[j]}")
        break
